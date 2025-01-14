import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert.AlertType;
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

    @FXML
    private Button closeButton;

    @FXML
    private Button minimizeButton;

    @FXML
    private VBox questionContainer;

    @FXML
    private Button testErstellenButton;

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

    @FXML
    public void openAdminStage() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/register.fxml"));
        Parent root = loader.load();
        Stage adminStage = new Stage();
        adminStage.initStyle(StageStyle.UNDECORATED);
        adminStage.getIcons().add(new Image(getClass().getResourceAsStream("/Logo_Recrutify_small.png")));
        adminStage.setScene(new Scene(root));
        adminStage.setTitle("Recrutify | Registrierung");
        adminStage.show();
    }

    @FXML
    public void openUserStage() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/user.fxml"));
        Parent root = loader.load();
        Stage userStage = new Stage();
        userStage.initStyle(StageStyle.UNDECORATED);
        userStage.getIcons().add(new Image(getClass().getResourceAsStream("/Logo_Recrutify_small.png")));
        userStage.setScene(new Scene(root));
        userStage.setTitle("Recrutify");
        userStage.show();
    }

    @FXML
    public void openQuestionStage() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/questions.fxml"));
        Parent root = loader.load();
        Stage questionStage = new Stage();
        questionStage.initStyle(StageStyle.UNDECORATED);
        questionStage.getIcons().add(new Image(getClass().getResourceAsStream("/Logo_Recrutify_small.png")));
        Scene questionScene = new Scene(root);
        questionStage.setScene(questionScene);
        questionScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        questionStage.setTitle("Recrutify");
        questionStage.show();
    }

    @FXML
    private void multipleChoiceButtonAction() {
        TextField textFieldQuestion = new TextField();
        textFieldQuestion.setPromptText("Frage");
        CheckBox checkBox1 = new CheckBox();
        TextField textField1 = new TextField();
        textField1.setPromptText("Antwort 1");
        CheckBox checkBox2 = new CheckBox();
        TextField textField2 = new TextField();
        textField2.setPromptText("Antwort 2");
        CheckBox checkBox3 = new CheckBox();
        TextField textField3 = new TextField();
        textField3.setPromptText("Antwort 3");
        CheckBox checkBox4 = new CheckBox();
        TextField textField4 = new TextField();
        textField4.setPromptText("Antwort 4");

        textFieldQuestion.getStyleClass().add("question-text-field");
        textField1.getStyleClass().add("answer-text-field");
        textField2.getStyleClass().add("answer-text-field");
        textField3.getStyleClass().add("answer-text-field");
        textField4.getStyleClass().add("answer-text-field");
        checkBox1.getStyleClass().add("checkbox");
        checkBox2.getStyleClass().add("checkbox");
        checkBox3.getStyleClass().add("checkbox");
        checkBox4.getStyleClass().add("checkbox");

        HBox hBox1 = new HBox(10, checkBox1, textField1);
        HBox hBox2 = new HBox(10, checkBox2, textField2);
        HBox hBox3 = new HBox(10, checkBox3, textField3);
        HBox hBox4 = new HBox(10, checkBox4, textField4);

        hBox1.getStyleClass().add("hbox-top");
        hBox2.getStyleClass().add("hbox");
        hBox3.getStyleClass().add("hbox");
        hBox4.getStyleClass().add("hbox-bottom");

        questionContainer.getChildren().addAll(textFieldQuestion, hBox1, hBox2, hBox3, hBox4);
    }

    @FXML
    private void showErrorDialog(String message) {
        Alert alert = new Alert(AlertType.ERROR, message, ButtonType.OK);
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

    @FXML
    private void loginButtonAction() {
        String enteredUsername = usernameLogin.getText();
        String enteredPassword = passwordLogin.getText();

        try {
            user = UserService.login(enteredUsername, enteredPassword);
            if (user != null) {
                Stage primaryStage = (Stage) usernameLogin.getScene().getWindow();
                primaryStage.close();
                if (user.isAdmin()) {
                    openAdminStage();
                } else {
                    openUserStage();
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

        try {
            UserService.register(enteredUsername, enteredPassword, enteredCompany, enteredFirstName, enteredLastName);
            showSuccessDialog("Unternehmen erfolgreich erstellt!");
        } catch (Exception e) {
            showErrorDialog("Bitte wenden sie sich an den Administrator.");
        }

    }

    @FXML
    private void testErstellenButtonAction() throws Exception {
        openQuestionStage();
        Stage stage = (Stage) testErstellenButton.getScene().getWindow();
        stage.close();
    }
}