import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;

import java.sql.Time;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MediateurFormulaire extends KeyFrameObservable implements Mediateur, TimeTookObserver{

    private final Stage primaryStage;
    private TriRapide triRapide = new TriRapide();
    private FusionnerEtTrier triFusion = new FusionnerEtTrier();
    private String triSelectionner;
    private int rapidite;

    private String listeDeNombre;

    private boolean startStop = false;

    private XYChart.Series<String, Number> series;

    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();
    private BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);

    private Timeline timeline;

    private final String[] categories = {
            "Création Collection",
            "Séparer collection",
            "Trier collection",
            "Fusionner collection"
    };

    public MediateurFormulaire(Stage primaryStage) {
        this.primaryStage = primaryStage;
        triRapide.addTimeTookObserver(this);
        triFusion.addTimeTookObserver(this);
    }

    @Override
    public void clickButton(String listeDeNombre) {
        this.listeDeNombre = listeDeNombre;
        if(Objects.equals(triSelectionner, "QuickSort")) {
            VueQuickSort vueTriRapide = new VueQuickSort(primaryStage, this);
            primaryStage.setScene(vueTriRapide.creationScene());
        }else{
            VueMergeSort vueTriFusion = new VueMergeSort(primaryStage, this);
            primaryStage.setScene(vueTriFusion.creationScene());
        }
    }

    @Override
    public void triSelectionner(String triSelectionner) {
        this.triSelectionner = triSelectionner;
    }

    public void rapiditeSelectionner(String rapidite) {
        switch (rapidite){
            case "Lente":
                this.rapidite = 40;
                break;
            case "Moyenne":
                this.rapidite = 20;
                break;
            case "Rapide":
                this.rapidite = 10;
                break;
        }
    }

    public void addDataToChart(XYChart.Series<String, Number> series) {
        this.series = series;
        if(Objects.equals(triSelectionner, "QuickSort")){
            triRapide.EtapeDeTriage(listeDeNombre);
        }
        else{
            System.out.println(triFusion.EtapeDeTriage(listeDeNombre));
        }
    }

    @Override
    public String clickButtonStarTop(String text) {
        this.startStop = !this.startStop;
        if (text.equals("Start")) {
            Thread chartUpdateThread = new Thread(() -> {
                addDataToChart(series);
                //Platform.runLater(() -> timeline.play());
            });
            chartUpdateThread.start();
            return "Stop";

        } else if (text.equals("Stop")) {
            //if (timeline.getStatus() == Animation.Status.RUNNING) {
                return "Resume";
                //timeline.pause();
            //}
        } else {
            return "Stop";
            //timeline.play();
        }
    }

    @Override
    public void updateTimeTook(long duration, int index) {
        double initialTime = 0;
        double finalTime = this.rapidite / 4;

        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        XYChart.Series<String, Number> localSeries = this.series; // Copie locale de la série pour éviter les problèmes de concurrence
        KeyFrame initialKeyFrame = new KeyFrame(Duration.seconds(initialTime), event -> {
            Platform.runLater(() -> localSeries.getData().add(new XYChart.Data<>(categories[index], 0)));
        });

        KeyFrame finalKeyFrame = new KeyFrame(Duration.seconds(finalTime), event -> {
            Platform.runLater(() -> localSeries.getData().add(new XYChart.Data<>(categories[index], duration)));
        });
        timeline.getKeyFrames().addAll(initialKeyFrame, finalKeyFrame);

        notifyKeyFrameObservers(timeline);

        try {
            TimeUnit.SECONDS.sleep((long) this.rapidite / 4);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
    public BarChart<String, Number> clickButtonRestart() {
        chart.getData().clear(); // Effacer les données du graphique
        return chart;
    }

    public Timeline clearTimeLine(){
        timeline.stop(); // Arrêter la timeline
        timeline.getKeyFrames().clear(); // Vider les keyframes de la timeline
        return null;
    }

    public BarChart<String,Number> setBarChart(){
        xAxis.setLabel("Étapes de tri");
        yAxis.setLabel("Temps (ms)");
        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                // Formater les valeurs avec une précision de deux décimales
                return String.format("%.3f", number.doubleValue());
            }

            @Override
            public Number fromString(String string) {
                return Double.parseDouble(string);
            }
        });
        chart.setTitle("Temps pris pour chaque étape du tri");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        chart.getData().add(series);
        setSeries(series);

        chart.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        return chart;
    }

    public void setSeries(XYChart.Series<String, Number> series){
        this.series = series;
    }
}
