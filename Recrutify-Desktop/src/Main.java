import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;

public class Main extends Application {
    User user = null;

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
    private TextField usernameLogin;

    @FXML
    private TextField passwordLogin;

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
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Logo_Recrutify_small.png")));
        // Setzt die Scene auf die Bühne (Stage)
        primaryStage.setScene(scene);
        primaryStage.setTitle("Recrutify | Login");
        primaryStage.show();
    }

    public void openAdminStage() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/register.fxml"));
        Parent root = loader.load();
        Stage adminStage = new Stage();
        adminStage.initStyle(StageStyle.UNDECORATED);
        adminStage.setScene(new Scene(root));
        adminStage.setTitle("Recrutify | Registrierung");
        adminStage.show();
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Fehler!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    private void loginButtonAction() {
        String enteredUsername = usernameLogin.getText();
        String enteredPassword = passwordLogin.getText();

        try {
            user = UserService.login(enteredUsername, enteredPassword);
            if (user.isAdmin()) {
                Stage primaryStage = (Stage) usernameLogin.getScene().getWindow();
                primaryStage.close();  // Schließt die Login-Stage
                openAdminStage();

            } else {
                // öffne test erstellungs Stage
                System.out.println("Titus ab hier musst du kochen");
            }
            System.out.println("Eingeloggter User: \n" + user.toString());
        } catch (Exception e) {
            showErrorDialog("Benutzername oder Passwort falsch. Bitte erneut versuchen.");
        }
    }

    @FXML
    private void registerButtonAction() {
        String enteredCompany = companyRegister.getText();
        String enteredFirstName = firstNameRegister.getText();
        String enteredLastName = lastNameRegister.getText();
        String enteredUsername = usernameRegister.getText();
        String enteredPassword = passwordRegister.getText();

        if (enteredCompany.isEmpty() || enteredFirstName.isEmpty() || enteredLastName.isEmpty() || enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
            showErrorDialog("Alle Felder müssen ausgefüllt werden.");
            return;
        }

        UserService.register(enteredUsername, enteredPassword, enteredCompany, enteredFirstName, enteredLastName);
    }
}