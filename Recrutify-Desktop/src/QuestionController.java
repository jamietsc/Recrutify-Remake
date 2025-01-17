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

public class QuestionController {

    private int questioncounter =1;
    private int answerCounter = 1;

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
        textFieldQuestion.setId("question" + questioncounter);
        questioncounter++;

        CheckBox checkBox1 = new CheckBox();
        checkBox1.setId("checkbox" + answerCounter);
        TextField textFieldAnswer1 = new TextField();
        textFieldAnswer1.setPromptText("Antwort 1");
        textFieldAnswer1.setId("answer" + answerCounter);
        answerCounter++;

        CheckBox checkBox2 = new CheckBox();
        checkBox2.setId("checkbox" + answerCounter);
        TextField textFieldAnswer2 = new TextField();
        textFieldAnswer2.setPromptText("Antwort 2");
        textFieldAnswer2.setId("answer" + answerCounter);
        answerCounter++;

        CheckBox checkBox3 = new CheckBox();
        checkBox3.setId("checkbox" + answerCounter);
        TextField textFieldAnswer3 = new TextField();
        textFieldAnswer3.setPromptText("Antwort 3");
        textFieldAnswer3.setId("answer" + answerCounter);
        answerCounter++;

        CheckBox checkBox4 = new CheckBox();
        checkBox4.setId("checkbox" + answerCounter);
        TextField textFieldAnswer4 = new TextField();
        textFieldAnswer4.setPromptText("Antwort 4");
        textFieldAnswer4.setId("answer" + answerCounter);
        answerCounter++;

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

        hBox1.getStyleClass().add("hbox-top");
        hBox2.getStyleClass().add("hbox");
        hBox3.getStyleClass().add("hbox");
        hBox4.getStyleClass().add("hbox-bottom");

        questionContainer.getChildren().addAll(textFieldQuestion, hBox1, hBox2, hBox3, hBox4);
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
