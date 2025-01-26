import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;
import passwordSecurity.BCrypt;

import java.util.Objects;

public class AccountSettingController {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Button closeButton;

    @FXML
    private Button minimizeButton;

    @FXML
    private Button goBackToMainMenuButton;

    @FXML
    private TextField companyName;

    @FXML
    private TextField surname;

    @FXML
    private TextField lastName;

    @FXML
    private TextField username;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField confirmNewPassword;

    @FXML
    private Button editButton;

    @FXML
    private Button saveAccountButton;

    @FXML
    private Label labelNewPassword;

    @FXML
    private Label labelConfirmNewPassword;

    @FXML
    public void initialize() {
        User user = UserSession.getCurrentUser();
        System.out.println("Aktuelle UserID: " + user.getUserID());
        User currenUserInformation = UserService.loadCurrentAccountInformation(user.getUserID());
        if(currenUserInformation != null) {
            companyName.setText(currenUserInformation.getCompany());
            surname.setText(currenUserInformation.getSurname());
            lastName.setText(currenUserInformation.getLastname());
            username.setText(currenUserInformation.getusername());
        }
        companyName.setEditable(false);
        surname.setEditable(false);
        lastName.setEditable(false);
        username.setEditable(false);
        newPassword.setEditable(false);
        confirmNewPassword.setEditable(false);

        newPassword.setVisible(false);
        confirmNewPassword.setVisible(false);
        labelNewPassword.setVisible(false);
        labelConfirmNewPassword.setVisible(false);
        saveAccountButton.setVisible(false);
        saveAccountButton.setDisable(true);

    }

    @FXML
    private void openStage(String fxmlFile) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/Logo_Recrutify_small.png")));
        stage.setResizable(false);
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
    private void goBackToMainMenuButtonAction() throws Exception{
        openStage("/user.fxml");
        Stage stage = (Stage) goBackToMainMenuButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void editButtonAction() throws Exception {
        User user = UserSession.getCurrentUser();
        User currenUserInformation = UserService.loadCurrentAccountInformation(user.getUserID());

        companyName.setEditable(true);
        surname.setEditable(true);
        lastName.setEditable(true);
        username.setEditable(true);
        newPassword.setEditable(true);
        confirmNewPassword.setEditable(true);

        if(currenUserInformation != null) {
            surname.setText("");
            lastName.setText("");
            username.setText("");
            newPassword.setText("");
            confirmNewPassword.setText("");

            surname.setPromptText(currenUserInformation.getSurname());
            lastName.setPromptText(currenUserInformation.getLastname());
            username.setPromptText(currenUserInformation.getusername());

            newPassword.setVisible(true);
            confirmNewPassword.setVisible(true);
            labelNewPassword.setVisible(true);
            labelConfirmNewPassword.setVisible(true);
            editButton.setVisible(false);
            editButton.setDisable(true);
            saveAccountButton.setVisible(true);
            saveAccountButton.setDisable(false);
        }

    }

    @FXML
    private void saveAccountButtonAction() throws Exception {
        User user = UserSession.getCurrentUser();
        User currentUserInformation = UserService.loadCurrentAccountInformation(user.getUserID());

        String enteredSurname = surname.getText();
        String enteredLastName = lastName.getText();
        String enteredUsername = username.getText();
        String enteredNewPassword = newPassword.getText();
        String enteredConfirmNewPassword = confirmNewPassword.getText();

        if(!Objects.equals(enteredNewPassword, enteredConfirmNewPassword)) {
            showErrorDialog("Passwörter stimmen nicht überein.");
            return;
        }

        if(enteredSurname.equals("")) {
            enteredSurname = currentUserInformation.getSurname();
        }
        if(enteredLastName.equals("")) {
            enteredLastName = currentUserInformation.getLastname();
        }
        if(enteredUsername.equals("")) {
            enteredUsername = currentUserInformation.getusername();
        }
        if(enteredNewPassword.equals("")) {
            enteredNewPassword = (currentUserInformation.getpassword());
        }
        enteredNewPassword = UserService.hashPassword(enteredNewPassword);

        if (!UserService.usernameExists(enteredUsername)) {
            boolean accountGotUpdated = UserService.accountInformationUpdate(enteredSurname, enteredLastName, enteredUsername, enteredNewPassword, currentUserInformation.getUserID());

            if (accountGotUpdated) {
                showSuccessDialog("Ihr Account wurde erfolgreich bearbeitet.");
                openStage("/user.fxml");
                Stage stage = (Stage) saveAccountButton.getScene().getWindow();
                stage.close();
            }
        } else {
            showErrorDialog("Der eingegeben Nutzername existiert bereits");
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle("Recrutify");
        alert.setHeaderText(null);
        alert.showAndWait();
    }



}
