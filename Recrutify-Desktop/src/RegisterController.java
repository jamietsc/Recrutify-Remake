import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Controller für das Registrierungsfenster.
 * Verwaltet die Registrierung eines neuen Unternehmens und Benutzers.
 */
public class RegisterController {

    @FXML
    private TextField companyRegister;

    @FXML
    private TextField firstNameRegister;

    @FXML
    private TextField lastNameRegister;

    @FXML
    private TextField usernameRegister;

    @FXML
    private TextField passwordRegister;

    @FXML
    private TextField passwordRegisterRepeat;

    @FXML
    private Button closeButton;

    @FXML
    private Button minimizeButton;

    /**
     * Wird aufgerufen, wenn der Registrierungs-Button gedrückt wird.
     * Überprüft die Eingaben, validiert die Passwörter und führt die Registrierung durch.
     */
    @FXML
    private void registerButtonAction() {
        String enteredCompany = companyRegister.getText();
        String enteredFirstName = firstNameRegister.getText();
        String enteredLastName = lastNameRegister.getText();
        String enteredUsername = usernameRegister.getText();
        String enteredPassword = passwordRegister.getText();
        String enteredPasswordRepeat = passwordRegisterRepeat.getText();

        if (enteredCompany.isEmpty() || enteredFirstName.isEmpty() || enteredLastName.isEmpty() ||
                enteredUsername.isEmpty() || enteredPassword.isEmpty() || enteredPasswordRepeat.isEmpty()) {
            showErrorDialog("Alle Felder müssen ausgefüllt werden.");
            return;
        }

        if (!enteredPassword.equals(enteredPasswordRepeat)) {
            showErrorDialog("Passwörter stimmen nicht überein.");
            return;
        }

        try {
            if(!UserService.usernameExists(enteredUsername, 0)){
                String hashedPassword = UserService.hashPassword(enteredPassword);
                UserService.register(enteredUsername, hashedPassword, enteredCompany, enteredFirstName, enteredLastName, false);
                showSuccessDialog("Unternehmen erfolgreich erstellt!");
            } else {
                showErrorDialog("Der eingegebene Nutzername existiert bereits.");
            }
        } catch (Exception e) {
            showErrorDialog("Bitte wenden sie sich an den Administrator.");
        }
    }

    /**
     * Zeigt ein Fehlerdialogfeld mit der angegebenen Nachricht an.
     *
     * @param message Die anzuzeigende Fehlermeldung.
     */
    @FXML
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Fehler!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    /**
     * Zeigt ein Informationsdialogfeld mit der angegebenen Nachricht an.
     *
     * @param message Die anzuzeigende Nachricht.
     */
    @FXML
    private void showSuccessDialog(String message) {
        Alert alert = new Alert(AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle("Recrutify");
        alert.setHeaderText(null);
        alert.showAndWait();
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
