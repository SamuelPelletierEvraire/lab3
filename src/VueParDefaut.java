import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VueParDefaut extends VueAbstraite {

    public VueParDefaut(Stage primaryStage, MediateurFormulaire mediateur) {
        super(primaryStage, mediateur);
    }

    @Override
    protected Scene creationScene() {
        Label titreLabel = new Label("Paramètre pour la visualisation d'un tri");
        Label algoritmeLabel = new Label("Algorithme de tri");
        Label collectionLabel = new Label("Collection d'entier a trier");
        Label vitesseSimulationLabel = new Label("Vitesse de simulation");

        ChoiceBox choiceBoxTypeDeTri = new ChoiceBox<>();
        ChoiceBox choiceBoxRapidite = new ChoiceBox<>();

        choiceBoxTypeDeTri.getItems().addAll("QuickSort", "MergeSort");
        choiceBoxTypeDeTri.setOnAction(event -> {
            super.mediateur.triSelectionner(choiceBoxTypeDeTri.getValue().toString());
        });
        choiceBoxRapidite.getItems().addAll("Lente", "Moyenne", "Rapide");
        choiceBoxRapidite.setOnAction(event -> {
            super.mediateur.rapiditeSelectionner(choiceBoxRapidite.getValue().toString());
        });

        Button buttonOk = new Button();
        buttonOk.setText("Ok");
        Button buttonAnnuler = new Button();
        buttonAnnuler.setText("Annuler");
        Button buttonAppliquer = new Button();
        buttonAppliquer.setText("Appliquer");
        HBox boutonHBox = new HBox();
        boutonHBox.getChildren().addAll(buttonOk,buttonAnnuler,buttonAppliquer);
        boutonHBox.alignmentProperty().set(Pos.BOTTOM_RIGHT);
        boutonHBox.setSpacing(10);
        Insets insets = new Insets(0,10,0,0);
        boutonHBox.setPadding(insets);

        TextField listeValeur = new TextField();

        buttonOk.setOnAction(event -> {
            super.mediateur.clickButton(listeValeur.getText());
        });

        VBox verticalBox = new VBox();
        GridPane grid = this.createGrid();
        grid.add(titreLabel, 1, 1);
        grid.add(algoritmeLabel, 1, 2);
        grid.add(choiceBoxTypeDeTri, 2, 2);
        grid.add(collectionLabel, 1, 3);
        grid.add(listeValeur, 2, 3);
        grid.add(vitesseSimulationLabel, 1, 4);
        grid.add(choiceBoxRapidite, 2, 4);

        verticalBox.getChildren().addAll(grid, boutonHBox);
        Scene testStyle=new Scene(verticalBox, 600, 250);
        return testStyle;
    }

}
