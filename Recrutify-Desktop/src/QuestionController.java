import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestionController {
    private final List<TextField> questionFieldsMultipleChoice = new ArrayList<>();
    private final List<HBox> answerBoxesMultipleChoice = new ArrayList<>();

    private final List<TextField> questionFieldsSingleChoice = new ArrayList<>();
    private final List<HBox> answerBoxesSingleChoice = new ArrayList<>();

    private final List<TextField> questionFieldsFreitext = new ArrayList<>();

    private final List<TextField> questionFieldsWahrFalsch = new ArrayList<>();
    private final List<RadioButton> answerBoxesWahrFalsch = new ArrayList<>();

    int testID = getTestID();
    int time = 0;

    @FXML
    private VBox questionContainer;

    @FXML
    private Button closeButton;

    @FXML
    private Button minimizeButton;

    @FXML
    private void multipleChoiceButtonAction() {
        TextField textFieldQuestion = new TextField();
        textFieldQuestion.setPromptText("Frage");
        questionFieldsMultipleChoice.add(textFieldQuestion);

        CheckBox checkBox1 = new CheckBox();
        TextField textFieldAnswer1 = new TextField();
        textFieldAnswer1.setPromptText("Antwort 1");

        CheckBox checkBox2 = new CheckBox();
        TextField textFieldAnswer2 = new TextField();
        textFieldAnswer2.setPromptText("Antwort 2");

        CheckBox checkBox3 = new CheckBox();
        TextField textFieldAnswer3 = new TextField();
        textFieldAnswer3.setPromptText("Antwort 3");

        CheckBox checkBox4 = new CheckBox();
        TextField textFieldAnswer4 = new TextField();
        textFieldAnswer4.setPromptText("Antwort 4");

        textFieldQuestion.getStyleClass().add("question-text-field");
        textFieldAnswer1.getStyleClass().add("answer-text-field");
        textFieldAnswer2.getStyleClass().add("answer-text-field");
        textFieldAnswer3.getStyleClass().add("answer-text-field");
        textFieldAnswer4.getStyleClass().add("answer-text-field");
        checkBox1.getStyleClass().add("checkbox");
        checkBox2.getStyleClass().add("checkbox");
        checkBox3.getStyleClass().add("checkbox");
        checkBox4.getStyleClass().add("checkbox");

        HBox hBox1 = new HBox(10, checkBox1, textFieldAnswer1);
        HBox hBox2 = new HBox(10, checkBox2, textFieldAnswer2);
        HBox hBox3 = new HBox(10, checkBox3, textFieldAnswer3);
        HBox hBox4 = new HBox(10, checkBox4, textFieldAnswer4);
        answerBoxesMultipleChoice.add(hBox1);
        answerBoxesMultipleChoice.add(hBox2);
        answerBoxesMultipleChoice.add(hBox3);
        answerBoxesMultipleChoice.add(hBox4);

        //hBox1.getStyleClass().add("hbox-top");
        //hBox2.getStyleClass().add("hbox");
        //hBox3.getStyleClass().add("hbox");
        hBox4.getStyleClass().add("hbox-bottom");

        Button buttonDeleteQuestion = new Button("\uD83D\uDDD9");
        buttonDeleteQuestion.getStyleClass().add("delete-button");
        buttonDeleteQuestion.setOnAction(e -> {
            questionContainer.getChildren().removeAll(buttonDeleteQuestion, textFieldQuestion, hBox1, hBox2, hBox3, hBox4);
            questionFieldsMultipleChoice.remove(textFieldQuestion);
            answerBoxesMultipleChoice.remove(hBox1);
            answerBoxesMultipleChoice.remove(hBox2);
            answerBoxesMultipleChoice.remove(hBox3);
            answerBoxesMultipleChoice.remove(hBox4);
        });

        questionContainer.setSpacing(20);
        questionContainer.getChildren().addAll(buttonDeleteQuestion, textFieldQuestion, hBox1, hBox2, hBox3, hBox4);
    }

    @FXML
    private void singleChoiceButtonAction() {
        TextField textFieldQuestion = new TextField();
        textFieldQuestion.setPromptText("Frage");
        questionFieldsSingleChoice.add(textFieldQuestion);

        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton radioButton1 = new RadioButton();
        TextField textFieldAnswer1 = new TextField();
        textFieldAnswer1.setPromptText("Antwort 1");

        RadioButton radioButton2 = new RadioButton();
        TextField textFieldAnswer2 = new TextField();
        textFieldAnswer2.setPromptText("Antwort 2");

        RadioButton radioButton3 = new RadioButton();
        TextField textFieldAnswer3 = new TextField();
        textFieldAnswer3.setPromptText("Antwort 3");

        RadioButton radioButton4 = new RadioButton();
        TextField textFieldAnswer4 = new TextField();
        textFieldAnswer4.setPromptText("Antwort 4");

        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        radioButton3.setToggleGroup(toggleGroup);
        radioButton4.setToggleGroup(toggleGroup);

        textFieldQuestion.getStyleClass().add("question-text-field");
        textFieldAnswer1.getStyleClass().add("answer-text-field");
        textFieldAnswer2.getStyleClass().add("answer-text-field");
        textFieldAnswer3.getStyleClass().add("answer-text-field");
        textFieldAnswer4.getStyleClass().add("answer-text-field");
        radioButton1.getStyleClass().add("radio-button");
        radioButton2.getStyleClass().add("radio-button");
        radioButton3.getStyleClass().add("radio-button");
        radioButton4.getStyleClass().add("radio-button");

        HBox hBox1 = new HBox(10, radioButton1, textFieldAnswer1);
        HBox hBox2 = new HBox(10, radioButton2, textFieldAnswer2);
        HBox hBox3 = new HBox(10, radioButton3, textFieldAnswer3);
        HBox hBox4 = new HBox(10, radioButton4, textFieldAnswer4);
        answerBoxesSingleChoice.add(hBox1);
        answerBoxesSingleChoice.add(hBox2);
        answerBoxesSingleChoice.add(hBox3);
        answerBoxesSingleChoice.add(hBox4);

        //hBox1.getStyleClass().add("hbox-top");
        //hBox2.getStyleClass().add("hbox");
        //hBox3.getStyleClass().add("hbox");
        hBox4.getStyleClass().add("hbox-bottom");

        Button buttonDeleteQuestion = new Button("\uD83D\uDDD9");
        buttonDeleteQuestion.getStyleClass().add("delete-button");
        buttonDeleteQuestion.setOnAction(e -> {
            questionContainer.getChildren().removeAll(buttonDeleteQuestion, textFieldQuestion, hBox1, hBox2, hBox3, hBox4);
            questionFieldsSingleChoice.remove(textFieldQuestion);
            answerBoxesSingleChoice.remove(hBox1);
            answerBoxesSingleChoice.remove(hBox2);
            answerBoxesSingleChoice.remove(hBox3);
            answerBoxesSingleChoice.remove(hBox4);
        });

        questionContainer.setSpacing(20);
        questionContainer.getChildren().addAll(buttonDeleteQuestion, textFieldQuestion, hBox1, hBox2, hBox3, hBox4);
    }

    @FXML
    private void freitextButtonAction() {
        TextField textFieldQuestion = new TextField();
        textFieldQuestion.setPromptText("Frage");
        questionFieldsFreitext.add(textFieldQuestion);

        textFieldQuestion.getStyleClass().add("question-text-field");

        Button buttonDeleteQuestion = new Button("\uD83D\uDDD9");
        buttonDeleteQuestion.getStyleClass().add("delete-button");
        buttonDeleteQuestion.setOnAction(e -> {
            questionContainer.getChildren().removeAll(buttonDeleteQuestion, textFieldQuestion);
            questionFieldsFreitext.remove(textFieldQuestion);
        });

        questionContainer.setSpacing(20);
        questionContainer.getChildren().addAll(buttonDeleteQuestion, textFieldQuestion);
    }

    @FXML
    private void wahrFalschButtonAction() {
        TextField textFieldQuestion = new TextField();
        textFieldQuestion.setPromptText("Frage");
        questionFieldsWahrFalsch.add(textFieldQuestion);

        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton radioButton1 = new RadioButton("Wahr");
        radioButton1.setToggleGroup(toggleGroup);
        answerBoxesWahrFalsch.add(radioButton1);
        RadioButton radioButton2 = new RadioButton("Falsch");
        radioButton2.setToggleGroup(toggleGroup);

        textFieldQuestion.getStyleClass().add("question-text-field");
        radioButton1.getStyleClass().add("radio-button");
        radioButton2.getStyleClass().add("radio-button");

        Button buttonDeleteQuestion = new Button("\uD83D\uDDD9");
        buttonDeleteQuestion.getStyleClass().add("delete-button");
        buttonDeleteQuestion.setOnAction(e -> {
            questionContainer.getChildren().removeAll(buttonDeleteQuestion, textFieldQuestion, radioButton1, radioButton2);
            questionFieldsWahrFalsch.remove(textFieldQuestion);
            answerBoxesWahrFalsch.remove(radioButton1);
            answerBoxesWahrFalsch.remove(radioButton2);
        });

        questionContainer.setSpacing(20);
        questionContainer.getChildren().addAll(buttonDeleteQuestion, textFieldQuestion, radioButton1, radioButton2);
    }

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    private void setTime () {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Zeit einstellen");
        dialog.setHeaderText("Hier können sie eine Zeitbegrenzung für den Test festlegen");
        dialog.setContentText("Zeit (in Minuten):");

        boolean validInput = false;
        while (!validInput) {
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String timeString = result.get();
                try {
                    time = Integer.parseInt(timeString);
                    if (time > 0) {
                        validInput = true;
                    } else {
                        dialog.setHeaderText("Ungültige Eingabe! Bitte eine gültige Zahl eingeben.");
                    }
                } catch (NumberFormatException e) {
                    dialog.setHeaderText("Ungültige Eingabe! Bitte eine gültige Zahl eingeben.");
                }
            } else {
                break;
            }
        }

    }

    @FXML
    private void minimizeButtonAction() {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    private int getTestID() {
        String url = "jdbc:sqlite:C:/Users/fynni/Documents/HWR/Software Engineering II/Recrutify-Remake/recrutify.db"; // Pfad zur SQLite-Datenbank
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                String sqlTID = "SELECT MAX(TID) FROM Test";
                // String sqlInsertFragen = "INSERT INTO Fragen () VALUES()";
                try (PreparedStatement ps = conn.prepareStatement(sqlTID)) {
                    ResultSet rs = ps.executeQuery();
                    if (rs.getInt(1) != 0) {
                        testID = rs.getInt(1);
                        testID++;
                    }
                } catch (SQLException e) {
                    System.out.println("Fehler beim abrufen der TID: " + e.getMessage());
                }
                System.out.println(testID);
            }
        } catch (SQLException e) {
            System.out.println("Verbindungsfehler: " + e.getMessage());
        }
        return testID;
    }

    @FXML
    private void zeitButtonAction() {
        setTime();
    }

    @FXML
    private boolean saveQuestionsButtonAction() {
        if (time == 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warnung");
            alert.setHeaderText(null);
            alert.setContentText("Sie haben keine Zeit eingestellt, sind sie sicher?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                String url = "jdbc:sqlite:C:/Users/fynni/Documents/HWR/Software Engineering II/Recrutify-Remake/recrutify.db";
                try (Connection conn = DriverManager.getConnection(url)) {
                    String sqlDeleteQuestions = "DELETE FROM Fragen WHERE TID = ?";
                    String sqlDeleteTest = "DELETE FROM Test WHERE TID = ?";
                    String sqlInsertTest = "INSERT INTO Test (TID, Dauer, UID) VALUES (?,?,?)";

                    try (PreparedStatement ps = conn.prepareStatement(sqlDeleteQuestions)) {
                        ps.setInt(1, testID);
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println("Fehler beim löschen aus der Tabelle Fragen: " + e.getMessage());
                    }

                    try (PreparedStatement ps = conn.prepareStatement(sqlDeleteTest)) {
                        ps.setInt(1, testID);
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println("Fehler beim löschen aus der Tabelle Test: " + e.getMessage());
                    }

                    try (PreparedStatement ps = conn.prepareStatement(sqlInsertTest)) {
                        ps.setInt(1, testID);
                        ps.setInt(2, time);
                        ps.setInt(3, UserSession.getCurrentUser().getUserID());
                        ps.executeUpdate();
                        showSuccessDialog("Die Fragen wurden erfolgreich gespeichert!");
                    } catch (SQLException e) {
                        System.out.println("Fehler beim einfügen der Daten in die Tabelle Test: " + e.getMessage());
                    }

                } catch (SQLException e) {
                    System.out.println("Verbindungsfehler: " + e.getMessage());
                }
                saveMultipleChoiceQuestions();
                saveSingleChoiceQuestions();
                saveFreitextQuestions();
                saveWahrFalschQuestions();
            } else return false;
        } return true;
    }

        private void saveMultipleChoiceQuestions() {
        String url = "jdbc:sqlite:C:/Users/fynni/Documents/HWR/Software Engineering II/Recrutify-Remake/recrutify.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                for (int i = 0; i < questionFieldsMultipleChoice.size(); i++) {
                    String question = questionFieldsMultipleChoice.get(i).getText();

                    HBox hBox1 = answerBoxesMultipleChoice.get(i * 4);
                    HBox hBox2 = answerBoxesMultipleChoice.get(i * 4 + 1);
                    HBox hBox3 = answerBoxesMultipleChoice.get(i * 4 + 2);
                    HBox hBox4 = answerBoxesMultipleChoice.get(i * 4 + 3);

                    String answer1 = ((TextField)hBox1.getChildren().get(1)).getText();
                    String answer2 = ((TextField)hBox2.getChildren().get(1)).getText();
                    String answer3 = ((TextField)hBox3.getChildren().get(1)).getText();
                    String answer4 = ((TextField)hBox4.getChildren().get(1)).getText();

                    Boolean correct1 = ((CheckBox)hBox1.getChildren().get(0)).isSelected();
                    Boolean correct2 = ((CheckBox)hBox2.getChildren().get(0)).isSelected();
                    Boolean correct3 = ((CheckBox)hBox3.getChildren().get(0)).isSelected();
                    Boolean correct4 = ((CheckBox)hBox4.getChildren().get(0)).isSelected();

                    String sql = "INSERT INTO Fragen (Fragentyp, Fragentext, Antwort_1, Antwort_2, Antwort_3, Antwort_4, Richtig_1, Richtig_2, Richtig_3, Richtig_4, TID) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setInt(1,1 );
                        ps.setString(2, question);
                        ps.setString(3, answer1);
                        ps.setString(4, answer2);
                        ps.setString(5, answer3);
                        ps.setString(6, answer4);
                        ps.setBoolean(7, correct1);
                        ps.setBoolean(8, correct2);
                        ps.setBoolean(9, correct3);
                        ps.setBoolean(10, correct4);
                        ps.setInt(11, testID);
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println("Fehler beim Einfügen der Daten: " + e.getMessage());
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Verbindungsfehler: " + e.getMessage());
        }
    }

    private void saveSingleChoiceQuestions() {
        String url = "jdbc:sqlite:C:/Users/fynni/Documents/HWR/Software Engineering II/Recrutify-Remake/recrutify.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                for (int i = 0; i < questionFieldsSingleChoice.size(); i++) {
                    String question = questionFieldsSingleChoice.get(i).getText();

                    HBox hBox1 = answerBoxesSingleChoice.get(i * 4);
                    HBox hBox2 = answerBoxesSingleChoice.get(i * 4 + 1);
                    HBox hBox3 = answerBoxesSingleChoice.get(i * 4 + 2);
                    HBox hBox4 = answerBoxesSingleChoice.get(i * 4 + 3);

                    String answer1 = ((TextField)hBox1.getChildren().get(1)).getText();
                    String answer2 = ((TextField)hBox2.getChildren().get(1)).getText();
                    String answer3 = ((TextField)hBox3.getChildren().get(1)).getText();
                    String answer4 = ((TextField)hBox4.getChildren().get(1)).getText();

                    Boolean correct1 = ((RadioButton)hBox1.getChildren().get(0)).isSelected();
                    Boolean correct2 = ((RadioButton)hBox2.getChildren().get(0)).isSelected();
                    Boolean correct3 = ((RadioButton)hBox3.getChildren().get(0)).isSelected();
                    Boolean correct4 = ((RadioButton)hBox4.getChildren().get(0)).isSelected();

                    String sql = "INSERT INTO Fragen (Fragentyp, Fragentext, Antwort_1, Antwort_2, Antwort_3, Antwort_4, Richtig_1, Richtig_2, Richtig_3, Richtig_4, TID) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setInt(1,2 );
                        ps.setString(2, question);
                        ps.setString(3, answer1);
                        ps.setString(4, answer2);
                        ps.setString(5, answer3);
                        ps.setString(6, answer4);
                        ps.setBoolean(7, correct1);
                        ps.setBoolean(8, correct2);
                        ps.setBoolean(9, correct3);
                        ps.setBoolean(10, correct4);
                        ps.setInt(11, testID);
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println("Fehler beim Einfügen der Daten: " + e.getMessage());
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Verbindungsfehler: " + e.getMessage());
        }
    }

    public void saveFreitextQuestions() {
        String url = "jdbc:sqlite:C:/Users/fynni/Documents/HWR/Software Engineering II/Recrutify-Remake/recrutify.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                for (TextField textFieldQuestion : questionFieldsFreitext ) {
                    String question = textFieldQuestion.getText();

                    String sql = "INSERT INTO Fragen (Fragentyp, Fragentext, TID) VALUES (?,?,?)";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setInt(1,0 );
                        ps.setString(2, question);
                        ps.setInt(3, testID);
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println("Fehler beim Einfügen der Daten: " + e.getMessage());
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Verbindungsfehler: " + e.getMessage());
        }
    }

    public void saveWahrFalschQuestions() {
        String url = "jdbc:sqlite:C:/Users/fynni/Documents/HWR/Software Engineering II/Recrutify-Remake/recrutify.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                for (int i = 0; i < questionFieldsWahrFalsch.size(); i++) {
                    String question = questionFieldsWahrFalsch.get(i).getText();

                    RadioButton radioButton1 = answerBoxesWahrFalsch.get(i);

                    Boolean AntwortJaNein = radioButton1.isSelected();

                    String sql = "INSERT INTO Fragen (Fragentyp, Fragentext, Antwort_JaNein, TID) VALUES (?,?,?,?)";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setInt(1,3);
                        ps.setString(2, question);
                        ps.setBoolean(3, AntwortJaNein);
                        ps.setInt(4, testID);
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println("Fehler beim Einfügen der Daten: " + e.getMessage());
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Verbindungsfehler: " + e.getMessage());
        }
    }



    @FXML
    private void showSuccessDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle("Recrutify");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Fehler!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
