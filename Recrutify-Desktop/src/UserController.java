import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;

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

    @FXML
    private void testErstellenButtonAction() throws Exception {
        openStage("/questions.fxml");
        Stage stage = (Stage) testErstellenButton.getScene().getWindow();
        stage.close();
    }

    /**
     * method which will open the view for the results
     * @throws Exception
     */
    @FXML
    private void auswertungÖffnenButtonAction() throws Exception {
        openStage("/adminPage.fxml");
        Stage stage = (Stage) auswertungÖffnenButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void testBearbeitenButtonAction() throws Exception {
        openStage("/edit.fxml");
        Stage stage = (Stage) testBearbeitenButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void accountButtonAction() throws Exception {
        openStage("/accountSetting.fxml");
        Stage stage = (Stage) accountButton.getScene().getWindow();
        stage.close();
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
    private void logoutButtonAction() throws Exception {
        openStage("/login.fxml");
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
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
