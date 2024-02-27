import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class VueQuickSort extends VueAbstraite implements KeyFrameObserver{

    private Timeline timeline = new Timeline();
    public VueQuickSort(Stage primaryStage, MediateurFormulaire mediateur) {
        super(primaryStage, mediateur);
        mediateur.addKeyFrameObserver(this);
    }

    @Override
    protected Scene creationScene() {
        Button buttonStartStop = new Button("Start");
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Étape de tri");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Temps (ms)");

        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        chart.setTitle("Temps pris pour chaque étape du tri");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        chart.getData().add(series);

        chart.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        buttonStartStop.setOnAction(event -> {
            if (buttonStartStop.getText().equals("Start")) {
                Thread chartUpdateThread = new Thread(() -> {
                    mediateur.addDataToChart(series);
                    Platform.runLater(() -> timeline.play());
                });
                chartUpdateThread.start();
                buttonStartStop.setText("Stop");
            } else if (buttonStartStop.getText().equals("Stop")) {
                if (timeline.getStatus() == Animation.Status.RUNNING) {
                    buttonStartStop.setText("Resume");
                    timeline.pause();
                }
            } else {
                buttonStartStop.setText("Stop");
                timeline.play();
            }
        });

        GridPane grid = this.createGrid();
        grid.add(chart, 2, 1);
        grid.add(buttonStartStop, 2, 3);
        Scene scene = new Scene(grid, 600, 400);
        return scene;
    }
    @Override
    public void updateKeyFrame(Timeline keyFrame) {
        this.timeline = keyFrame;
        timeline.play();
    }
}
