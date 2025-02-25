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

public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Lädt die FXML-Datei
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));

        // Erstellt eine Scene mit dem geladenen FXML-Inhalt
        Scene scene = new Scene(root);
        // Setzt die Stage auf "undecorated", um die Titelleiste zu entfernen
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/Logo_Recrutify_small.png")));
        // Setzt die Scene auf die Bühne (Stage)
        primaryStage.setResizable(false);
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

    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
}