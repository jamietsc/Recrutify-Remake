import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;

public class LoginController {
    @FXML
    private TextField usernameLogin;

    @FXML
    private TextField passwordLogin;

    @FXML
    private Button closeButton;

    @FXML
    private Button minimizeButton;

    @FXML
    private void loginButtonAction() {
        String enteredUsername = usernameLogin.getText();
        String enteredPassword = passwordLogin.getText();

        try {
            User user = UserService.login(enteredUsername, enteredPassword);
            if (user != null) {
                // Schließt das aktuelle Fenster
                Stage currentStage = (Stage) usernameLogin.getScene().getWindow();
                currentStage.close();

                // Öffnet das entsprechende Fenster basierend auf der Rolle des Benutzers
                if (user.isAdmin()) {
                    openStage("/admin.fxml");
                } else {
                    openStage("/user.fxml");
                }
            } else {
                showErrorDialog("Benutzername oder Passwort falsch. Bitte erneut versuchen.");
            }
            System.out.println("Eingeloggter User: \n" + user.toString());
        } catch (Exception e) {
            showErrorDialog("Ein Fehler ist aufgetreten: " + e.getMessage());
        }
    }

    @FXML
    private void openStage(String fxmlFile) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Logo_Recrutify_small.png")));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Fehler!");
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
