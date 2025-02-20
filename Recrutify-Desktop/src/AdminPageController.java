import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.ComboBoxBaseSkin;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;


public class AdminPageController {

    ObservableList<String> options = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> dropDownMenu;

    @FXML
    private Label noEntriesToThisTestLabel;

    @FXML
    private TableView<Bewerber> resultTable;

    @FXML
    private TableColumn<Bewerber, String> nameColumn;

    @FXML
    private TableColumn<Bewerber, String> lastNameColumn;

    @FXML
    private TableColumn<Bewerber, String> text_1;

    @FXML
    private TableColumn<Bewerber, String> text_2;

    @FXML
    private TableColumn<Bewerber, String> text_3;

    @FXML
    private TableColumn<Bewerber, Integer> eva_text_1;

    @FXML
    private TableColumn<Bewerber, Integer> eva_text_2;

    @FXML
    private TableColumn<Bewerber, Integer> eva_text_3;

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

    private final ObservableList<String> evaluations = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

    /**
     * Method for the button which will go back to the main menu
     * can be user in the whole program
     */
    @FXML
    private void goBackToMainMenuButtonAction() throws Exception {
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

    /**
     * method for the dropdown menu where all the test ids of the company will be load and displayed in the dropdownmenu
     *
     * @throws Exception
     */
    @FXML
    private void onOpenDropDownMenu() throws Exception {
        User regiseredUser = UserSession.getCurrentUser();
        options = FXCollections.observableArrayList(
                UserService.getTIDsFromCompany(regiseredUser.getUserID())
        );

        dropDownMenu.setItems(options);
    }

    /**
     * method for the button where the test results for a company will be load and displayed in the table view
     *
     * @throws Exception
     */
    @FXML
    private void searchResultsButtonAction() throws Exception {
        User registeredUser = UserSession.getCurrentUser();

        ObservableList<Bewerber> allApplicants = FXCollections.observableArrayList(
                UserService.loadAllTestResults(Integer.parseInt(dropDownMenu.getValue()))
        );
        if (!allApplicants.isEmpty()) {
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("vorname"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("nachname"));
            text_1.setCellValueFactory(new PropertyValueFactory<>("text_1"));

            // Bewertungsoptionen für alle drei Spalten
            ObservableList<Integer> evaluationOptions = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

            // Setzt die Werte-Felder
            eva_text_1.setCellValueFactory(new PropertyValueFactory<>("eva_text_1"));
            eva_text_2.setCellValueFactory(new PropertyValueFactory<>("eva_text_2"));
            eva_text_3.setCellValueFactory(new PropertyValueFactory<>("eva_text_3"));

            // Gemeinsame CellFactory-Funktion für alle Bewertungs-Spalten
            Callback<TableColumn<Bewerber, Integer>, TableCell<Bewerber, Integer>> comboBoxCellFactory = column -> new TableCell<>() {
                private final ComboBox<Integer> comboBox = new ComboBox<>(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

                {
                    // CSS-Style der ComboBox setzen
                    comboBox.getStyleClass().add("my-combo-box");

                    // Event-Handler für Änderungen
                    comboBox.setOnAction(event -> {
                        Bewerber bewerber = getTableRow().getItem();
                        if (bewerber != null) {
                            Integer selectedValue = comboBox.getValue();

                            // Richtige Bewertungsspalte setzen
                            if (column == eva_text_1) {
                                bewerber.setEva_text_1(selectedValue);
                            } else if (column == eva_text_2) {
                                bewerber.setEva_text_2(selectedValue);
                            } else if (column == eva_text_3) {
                                bewerber.setEva_text_3(selectedValue);
                            }

                            // Datenbank-Update
                            boolean success = UserService.updateEvaluation(
                                    bewerber.getEva_text_1(),
                                    bewerber.getEva_text_2(),
                                    bewerber.getEva_text_3(),
                                    Integer.parseInt(bewerber.getBewerberID())
                            );

                            if (success) {
                                System.out.println("Datenbank aktualisiert für BID: " + bewerber.getBewerberID());
                            } else {
                                System.out.println("Fehler beim Aktualisieren der Datenbank.");
                            }
                        }
                    });
                }

                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    Bewerber bewerber = getTableRow() != null ? getTableRow().getItem() : null;

                    if (empty || bewerber == null) {
                        setGraphic(null);
                    } else {
                        // Prüfen, ob das zugehörige Textfeld leer ist
                        boolean hasText = false;
                        if (column == eva_text_1) {
                            hasText = bewerber.getText_1() != null;
                        } else if (column == eva_text_2) {
                            hasText = bewerber.getText_2() != null;
                        } else if (column == eva_text_3) {
                            hasText = bewerber.getText_3() != null;
                        }

                        if (!hasText) {
                            // Falls kein zugehöriger Text existiert, keine ComboBox anzeigen
                            setGraphic(null);
                        } else {
                            // Den aktuellen Wert aus der DB setzen
                            Integer valueFromDB = null;
                            if (column == eva_text_1) {
                                valueFromDB = bewerber.getEva_text_1();
                            } else if (column == eva_text_2) {
                                valueFromDB = bewerber.getEva_text_2();
                            } else if (column == eva_text_3) {
                                valueFromDB = bewerber.getEva_text_3();
                            }

                            comboBox.setValue(valueFromDB); // Setzt den aktuellen Wert als Standard
                            setGraphic(comboBox);
                        }
                    }
                }
            };


// CellFactory für alle drei Spalten setzen
            eva_text_1.setCellFactory(comboBoxCellFactory);
            eva_text_2.setCellFactory(comboBoxCellFactory);
            eva_text_3.setCellFactory(comboBoxCellFactory);

            text_2.setCellValueFactory(new PropertyValueFactory<>("text_2"));
            text_3.setCellValueFactory(new PropertyValueFactory<>("text_3"));
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
