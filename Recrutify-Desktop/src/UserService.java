import java.sql.*;

public class UserService {
    // Methode zum Einloggen und gleichzeitigen Verbinden mit der Datenbank
    public static User login(String username, String password) throws Exception {
        String url = "jdbc:sqlite:C:/Users/fynni/Documents/HWR/Software Engineering II/Recrutify-Remake/recrutify.db"; // Pfad zur SQLite-Datenbank
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                String sql = "SELECT * FROM Unternehmen WHERE Benutzername = ? AND Passwort = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    ps.setString(2, password);

                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        // Extrahiere Benutzerdaten aus dem ResultSet
                        int id = rs.getInt("UID");
                        boolean isAdmin = rs.getBoolean("is_admin");

                        User user = new User(username, password, id, isAdmin);
                        UserSession.setCurrentUser(user);
                        return user;
                    } else {
                        throw new Exception("Benutzername oder Passwort falsch. Bitte versuchen Sie es erneut.");
                    }
                } catch (SQLException e) {
                    System.out.println("hä?");
                }
            }
        } catch (SQLException e) {
            System.out.println("Verbindungsfehler: " + e.getMessage());
        } return null;
    }

    public static void register(String username, String password, String company, String firstName, String lastName) {
        String url = "jdbc:sqlite:C:/Users/fynni/Documents/HWR/Software Engineering II/Recrutify-Remake/recrutify.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Verbindung zur SQLite-Datenbank hergestellt!");
                // insert into
                String sql = "INSERT INTO Unternehmen (Name, Benutzername, Passwort, Vorname, Nachname) VALUES (?,?,?,?,?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, company);
                    ps.setString(2, username);
                    ps.setString(3, password);
                    ps.setString(4, firstName);
                    ps.setString(5, lastName);

                    ps.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Fehler beim Einfügen der Daten: "  + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Verbindungsfehler: " + e.getMessage());
        }
    }

    public void saveQuestion() {

    }
}
