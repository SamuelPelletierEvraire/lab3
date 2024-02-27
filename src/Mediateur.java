import javafx.animation.Timeline;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.XYChart;

public interface Mediateur {
    void clickButton(String listeDeNombre);

    void triSelectionner(String triSelectionner);

    void rapiditeSelectionner(String rapidite);

    void addDataToChart(XYChart.Series<String, Number> series);

    String clickButtonStarTop(String text);

    BarChart<String, Number> clickButtonRestart();

    BarChart<String,Number>  setBarChart();
}
