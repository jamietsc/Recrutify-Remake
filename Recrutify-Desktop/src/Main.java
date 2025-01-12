import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = null;
        int decision = 0;

        System.out.println("1: Login | 2: Registrieren");
        decision = scanner.nextInt();
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
        }
    }
}