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

public class Main extends Application {
    User user = new User();

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

public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Button auswertungÖffnenButton;

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
        primaryStage.setResizable(false);
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        primaryStage.setScene(scene);
        primaryStage.setTitle("Recrutify | Login");
        primaryStage.show();
    }


    @FXML
    public void openAuswertungStage() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminPage.fxml"));

        //Laden der FXML Datei
        Parent root = loader.load();

        Stage auswertungStage = new Stage();
        auswertungStage.initStyle(StageStyle.UNDECORATED);
        auswertungStage.getIcons().add(new Image(getClass().getResourceAsStream("/Logo_Recrutify_small.png")));
        Scene auswertungScene = new Scene(root);
        auswertungStage.setScene(auswertungScene);
        auswertungScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        auswertungStage.setTitle("Recrutify");
        auswertungStage.show();
    }

    @FXML
    private void showErrorDialog(String message) {
        Alert alert = new Alert(AlertType.ERROR, message, ButtonType.OK);
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


    /**
     * method which will open the view for the results
     * @throws Exception
     */
    @FXML
    private void auswertungÖffnenButtonAction() throws Exception {
        openAuswertungStage();
        Stage stage = (Stage) auswertungÖffnenButton.getScene().getWindow();
        stage.close();
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

    /**
     * Method for the button which will go back to the main menu
     * can be user in the whole program
     * @throws Exception
     */
    @FXML
    private void goBackToMainMenuButtonAction() throws Exception{
        openUserStage();
        Stage stage = (Stage) goBackToMainMenuButton.getScene().getWindow();
        stage.close();
    }
}