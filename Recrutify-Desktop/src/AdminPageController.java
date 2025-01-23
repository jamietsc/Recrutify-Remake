import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class AdminPageController {

    ObservableList<String> options = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> dropDownMenu;

    @FXML
    private Button searchTIDResults;

    @FXML
    private Label noEntriesToThisTestLabel;

    @FXML
    private TableView<Bewerber> resultTable;

    @FXML
    private TableColumn<Bewerber, String> nameColumn;

    @FXML
    private TableColumn<Bewerber, String> lastNameColumn;

    @FXML
    private TableColumn<Bewerber, Integer> scoreColumn;

    @FXML
    private Button goBackToMainMenuButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button minimizeButton;

    private double xOffset = 0;
    private double yOffset = 0;

    /**
     * Method for the button which will go back to the main menu
     * can be user in the whole program
     * @throws Exception
     */
    @FXML
    private void goBackToMainMenuButtonAction() throws Exception{
        openStage("/user.fxml");
        Stage stage = (Stage) goBackToMainMenuButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void openStage(String fxmlFile) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Logo_Recrutify_small.png")));
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

    /**
     * method for the dropdown menu where all the test ids of the company will be load and displayed in the dropdownmenu
     * @throws Exception
     */
    @FXML
    private void onOpenDropDownMenu() throws Exception{
        User regiseredUser = UserSession.getCurrentUser();
        options = FXCollections.observableArrayList(
                UserService.getTIDsFromCompany(regiseredUser.getUserID())
        );

        dropDownMenu.setItems(options);
    }

    /**
     * method for the button where the test results for a company will be load and displayed in the table view
     * @throws Exception
     */
    @FXML
    private void searchResultsButtonAction() throws Exception{
        ObservableList<Bewerber> allApplicants = FXCollections.observableArrayList(
                UserService.loadAllTestResults(Integer.parseInt(dropDownMenu.getValue()))
        );
        if(!allApplicants.isEmpty()){
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("vorname"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("nachname"));
            scoreColumn.setCellValueFactory(new PropertyValueFactory<>("ergebnis"));
            resultTable.setItems(allApplicants);
            resultTable.setVisible(true);
            noEntriesToThisTestLabel.setVisible(false);
        } else {
            noEntriesToThisTestLabel.setVisible(true);
        }
        System.out.println("Die Dropdown Value beträgt: " + dropDownMenu.getValue());
    }

}
