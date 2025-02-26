import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import passwordSecurity.BCrypt;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Hauptklasse der Anwendung, die als Einstiegspunkt für die JavaFX-Applikation dient.
 */
public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    /**
     * Der Einstiegspunkt der Anwendung.
     *
     * @param args Die Kommandozeilenargumente
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Startet die JavaFX-Anwendung und lädt die Login-Oberfläche.
     *
     * @param primaryStage Die Hauptbühne der Anwendung
     * @throws Exception Falls ein Fehler beim Laden der FXML-Datei auftritt
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        Scene scene = new Scene(root);

        // Entfernt die Standard-Titelleiste
        primaryStage.initStyle(StageStyle.UNDECORATED);

        // Setzt das Anwendungs-Icon
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/Logo_Recrutify_small.png")));

        // Deaktiviert das Ändern der Fenstergröße
        primaryStage.setResizable(false);

        // Ermöglicht das Verschieben des Fensters durch Ziehen mit der Maus
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        //auskommentieren um ersten Benutzer zu erstellen
        //UserService.register("JentschJ", hashPassword("1234"), "FBB", "Jamie", "Jentsch", true);


        root.setOnMouseDragged((MouseEvent event) -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Recrutify | Login");
        primaryStage.show();
    }

    /**
     * Erstellt einen sicheren Hash für das übergebene Passwort.
     *
     * @param plainTextPassword Das zu hashende Passwort im Klartext
     * @return Der gehashte Passwort-String
     */
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
}
