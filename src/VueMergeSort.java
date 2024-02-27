import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;

import java.awt.*;
import java.util.ArrayList;

public class VueMergeSort extends VueAbstraite implements KeyFrameObserver {
    private Timeline timeline = new Timeline();

    public VueMergeSort(Stage primaryStage, MediateurFormulaire mediateur) {
        super(primaryStage, mediateur);
        mediateur.addKeyFrameObserver(this);
    }

    @Override
    protected Scene creationScene() {
        Button buttonStartStop = new Button("Start");
        Button buttonRestart = new Button("Restart");

        buttonStartStop.setOnAction(event -> {
            mediateur.clickButtonStarTop(buttonStartStop.getText());
        });

        buttonRestart.setOnAction(event -> {
            mediateur.clickButtonRestart();
            mediateur.clearTimeLine();
        });
        HBox boutonHBox = new HBox();
        boutonHBox.getChildren().addAll(buttonStartStop,buttonRestart);
        boutonHBox.alignmentProperty().set(Pos.BOTTOM_RIGHT);
        boutonHBox.setSpacing(10);

        GridPane grid = this.createGrid();
        grid.add(mediateur.setBarChart(), 2, 1);
        grid.add(boutonHBox, 2,4);
        Scene scene = new Scene(grid, 600, 400);
        return scene;
    }

    @Override
    public void updateKeyFrame(Timeline timeline) {
        this.timeline = timeline;
        timeline.play();
    }
}
