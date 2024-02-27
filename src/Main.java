import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialisation de la variable médiateur
        MediateurFormulaire mediateur = new MediateurFormulaire(primaryStage);

        // Initialisation de la classe VueParDefaut
        VueParDefaut vueParDefaut = new VueParDefaut(primaryStage, mediateur);
        primaryStage.setScene(vueParDefaut.creationScene());
        primaryStage.setTitle("Logiciel de trie de liste");
        primaryStage.show();
    }
}