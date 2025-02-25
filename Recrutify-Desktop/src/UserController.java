import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;

/**
 * Controller-Klasse für die Benutzeroberfläche.
 * Verwaltet die Navigation zwischen verschiedenen Ansichten und grundlegende Fenstersteuerungen.
 */
public class UserController {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Button testBearbeitenButton;

    @FXML
    private Button auswertungÖffnenButton;

    @FXML
    private Button testErstellenButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button minimizeButton;

    @FXML
    private Button accountButton;

    @FXML
    private Button logoutButton;

    /**
     * Öffnet die Ansicht zum Erstellen eines neuen Tests und schließt das aktuelle Fenster.
     *
     * @throws Exception Falls ein Fehler beim Laden der FXML-Datei auftritt.
     */
    @FXML
    private void testErstellenButtonAction() throws Exception {
        openStage("/questions.fxml");
        Stage stage = (Stage) testErstellenButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Öffnet die Auswertungsseite und schließt das aktuelle Fenster.
     *
     * @throws Exception Falls ein Fehler beim Laden der FXML-Datei auftritt.
     */
    @FXML
    private void auswertungÖffnenButtonAction() throws Exception {
        openStage("/adminPage.fxml");
        Stage stage = (Stage) auswertungÖffnenButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Öffnet die Ansicht zum Bearbeiten eines Tests und schließt das aktuelle Fenster.
     *
     * @throws Exception Falls ein Fehler beim Laden der FXML-Datei auftritt.
     */
    @FXML
    private void testBearbeitenButtonAction() throws Exception {
        openStage("/edit.fxml");
        Stage stage = (Stage) testBearbeitenButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Öffnet die Kontoeinstellungen und schließt das aktuelle Fenster.
     *
     * @throws Exception Falls ein Fehler beim Laden der FXML-Datei auftritt.
     */
    @FXML
    private void accountButtonAction() throws Exception {
        openStage("/accountSetting.fxml");
        Stage stage = (Stage) accountButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Öffnet eine neue Ansicht basierend auf der übergebenen FXML-Datei.
     *
     * @param fxmlFile Der Pfad zur FXML-Datei der neuen Ansicht.
     * @throws Exception Falls ein Fehler beim Laden der FXML-Datei auftritt.
     */
    @FXML
    private void openStage(String fxmlFile) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/Logo_Recrutify_small.png")));
        stage.setResizable(false);

        // Ermöglicht das Verschieben des Fensters durch Ziehen mit der Maus
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Meldet den Benutzer ab, öffnet die Login-Seite und schließt das aktuelle Fenster.
     *
     * @throws Exception Falls ein Fehler beim Laden der FXML-Datei auftritt.
     */
    @FXML
    private void logoutButtonAction() throws Exception {
        openStage("/login.fxml");
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Schließt das aktuelle Fenster.
     */
    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Minimiert das aktuelle Fenster.
     */
    @FXML
    private void minimizeButtonAction() {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }
}
