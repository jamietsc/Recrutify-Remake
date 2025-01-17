import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

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

    @FXML
    private void registerButtonAction() {
        String enteredCompany = companyRegister.getText();
        String enteredFirstName = firstNameRegister.getText();
        String enteredLastName = lastNameRegister.getText();
        String enteredUsername = usernameRegister.getText();
        String enteredPassword = passwordRegister.getText();
        String enteredPasswordRepeat = passwordRegisterRepeat.getText();

        if (enteredCompany.isEmpty() || enteredFirstName.isEmpty() || enteredLastName.isEmpty() || enteredUsername.isEmpty() || enteredPassword.isEmpty() || enteredPasswordRepeat.isEmpty()) {
            showErrorDialog("Alle Felder müssen ausgefüllt werden.");
            return;
        }

        if (!enteredPassword.equals(enteredPasswordRepeat)) {
            showErrorDialog("Passwörter stimmen nicht überein.");
            return;
        }

        try {
            UserService.register(enteredUsername, enteredPassword, enteredCompany, enteredFirstName, enteredLastName);
            showSuccessDialog("Unternehmen erfolgreich erstellt!");
        } catch (Exception e) {
            showErrorDialog("Bitte wenden sie sich an den Administrator.");
        }

    }

    @FXML
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Fehler!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    private void showSuccessDialog(String message) {
        Alert alert = new Alert(AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle("Recrutify");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minimizeButtonAction() {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }
}
