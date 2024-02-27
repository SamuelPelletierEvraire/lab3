import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public abstract class VueAbstraite extends TimeTookObservable {
    protected Stage primaryStage;

    // Médiateur pour la communication entre les différentes vues.
    protected MediateurFormulaire mediateur;

    // Constructeur.
    public VueAbstraite(Stage primaryStage, MediateurFormulaire mediateur) {
        this.primaryStage = primaryStage;
        this.mediateur = mediateur;
    }
    // Méthode abstraite pour créer la scène spécifique à chaque vue.
    protected abstract Scene creationScene();

    // Méthode pour créer un GridPane avec une mise en page standard.
    protected GridPane createGrid(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(25,25,25,25));
        return grid;
    }
}
