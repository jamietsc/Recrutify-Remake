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
 * Diese Klasse verwaltet die Benutzeranmeldung und steuert die
 * Benutzeroberfläche für das Login-Fenster.
 */
public class LoginController {

    /** Speichert die X-Position der Maus für das Verschieben des Fensters. */
    private double xOffset = 0;

    /** Speichert die Y-Position der Maus für das Verschieben des Fensters. */
    private double yOffset = 0;

    /** Eingabefeld für den Benutzernamen. */
    @FXML
    private TextField usernameLogin;

    /** Eingabefeld für das Passwort. */
    @FXML
    private TextField passwordLogin;

    /** Button zum Schließen des Fensters. */
    @FXML
    private Button closeButton;

    /** Button zum Minimieren des Fensters. */
    @FXML
    private Button minimizeButton;

    /**
     * Wird aufgerufen, wenn der Login-Button geklickt wird.
     * Überprüft den Benutzernamen und das Passwort und öffnet das entsprechende Fenster.
     */
    @FXML
    private void loginButtonAction() {
        String enteredUsername = usernameLogin.getText(); // Benutzereingabe des Usernames
        String enteredPassword = passwordLogin.getText(); // Benutzereingabe des Passworts

        try {
            // Überprüfung der Benutzerdaten über den UserService
            User user = UserService.login(enteredUsername, enteredPassword);

            // Falls der Benutzer existiert
            if (user != null) {
                // Schließt das aktuelle Login-Fenster
                Stage currentStage = (Stage) usernameLogin.getScene().getWindow();
                currentStage.close();

                // Öffnet je nach Benutzerrolle das passende Fenster
                if (user.isAdmin()) {
                    openStage("/register.fxml"); // Admin-Bereich
                } else {
                    openStage("/user.fxml"); // Normaler Benutzerbereich
                }
            } else {
                // Zeigt eine Fehlermeldung an, falls die Eingabe falsch war
                showErrorDialog("Benutzername oder Passwort falsch. Bitte erneut versuchen.");
            }
        } catch (Exception e) {
            // Zeigt eine allgemeine Fehlermeldung an, falls ein Fehler auftritt
            showErrorDialog("Ein Fehler ist aufgetreten: " + e.getMessage());
        }
    }

    /**
     * Öffnet ein neues Fenster basierend auf der angegebenen FXML-Datei.
     *
     * @param fxmlFile Der Pfad zur FXML-Datei des neuen Fensters.
     * @throws Exception Falls das Laden der FXML-Datei fehlschlägt.
     */
    @FXML
    private void openStage(String fxmlFile) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Stage stage = new Stage();

        // Setzt das Fenster auf undekoriert (ohne Standardrahmen)
        stage.initStyle(StageStyle.UNDECORATED);

        // Setzt das Icon des Fensters
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/Logo_Recrutify_small.png")));

        // Deaktiviert das Verändern der Fenstergröße
        stage.setResizable(false);

        // Ermöglicht das Verschieben des Fensters per Drag & Drop
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        // Setzt die Szene und zeigt das Fenster an
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Zeigt eine Fehlermeldung als Dialogfenster an.
     *
     * @param message Die Nachricht, die im Fehlerdialog angezeigt werden soll.
     */
    @FXML
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Fehler!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    /**
     * Wird aufgerufen, wenn der Schließen-Button gedrückt wird.
     * Schließt das aktuelle Fenster.
     */
    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Wird aufgerufen, wenn der Minimieren-Button gedrückt wird.
     * Minimiert das aktuelle Fenster.
     */
    @FXML
    private void minimizeButtonAction() {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }
}
