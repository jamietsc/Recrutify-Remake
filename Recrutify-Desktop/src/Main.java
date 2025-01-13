import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Main extends Application {

    User user = null;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button loginButton;

    public static void main(String[] args) {
        launch(args);
        /** Scanner scanner = new Scanner(System.in);
        User user = null;
        int decision = 0;

        System.out.println("1: Login | 2: Registrieren");
        decision = scanner.nextInt();
        scanner.nextLine();

        switch (decision) {
            case 1:
                while (user == null) {
                    System.out.print("Benutzername Eingeben: ");
                    String username = scanner.nextLine();
                    System.out.print("Passwort Eingeben: ");
                    String password = scanner.nextLine();

                    try {
                        user = UserService.login(username, password);
                        System.out.println("Eingeloggter User: \n" + user.toString());
                    } catch (Exception e) {
                        System.out.println("Benutzername oder Passwort falsch. Bitte erneut versuchen.");
                    }
                }
                break;
            case 2:
                System.out.print("Unternehmen: ");
                String company = scanner.nextLine();
                System.out.print("Vorname: ");
                String firstName = scanner.nextLine();
                System.out.print("Nachname: ");
                String lastName = scanner.nextLine();
                System.out.print("Benutzername: ");
                String username = scanner.nextLine();
                System.out.print("Passwort: ");
                String password = scanner.nextLine();
                System.out.print("Admin?");
                Boolean admin = scanner.nextBoolean();
                UserService.register(username, password, company, firstName, lastName, admin);
                break;
        } **/
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Lädt die FXML-Datei
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));

        // Erstellt eine Scene mit dem geladenen FXML-Inhalt
        Scene scene = new Scene(root);

        // Setzt die Stage auf "undecorated", um die Titelleiste zu entfernen
        primaryStage.initStyle(StageStyle.UNDECORATED);
        // Setzt die Scene auf die Bühne (Stage)
        primaryStage.setScene(scene);
        primaryStage.setTitle("Recrutify | Login");
        primaryStage.show();
    }

    @FXML
    private void LoginButtonAction() {
        String enteredUsername = username.getText();
        String enteredPassword = password.getText();

        try {
            user = UserService.login(enteredUsername, enteredPassword);
            System.out.println("Eingeloggter User: \n" + user.toString());
        } catch (Exception e) {
            System.out.println("Benutzername oder Passwort falsch. Bitte erneut versuchen.");
        }
    }
}