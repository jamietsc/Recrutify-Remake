import java.sql.*;

public class UserService {
    // Methode zum Einloggen und gleichzeitigen Verbinden mit der Datenbank
    public static User login(String username, String password) throws Exception {
        String url = "jdbc:sqlite:C:/Users/fynni/Documents/HWR/Software Engineering II/Recrutify-Remake/recrutify.db"; // Pfad zur SQLite-Datenbank
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Verbindung zur SQLite-Datenbank hergestellt!");
                String sql = "SELECT * FROM Unternehmen WHERE Benutzername = ? AND Passwort = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    ps.setString(2, password);

                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        // Extrahiere Benutzerdaten aus dem ResultSet
                        int id = rs.getInt("UID");
                        boolean isAdmin = rs.getBoolean("is_admin");

                        // Erstelle und gib das User-Objekt zurück
                        return new User(username, password, id, isAdmin);
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

    public static void register(String username, String password, String company, String firstName, String lastName, Boolean isAdmin) {
        String url = "jdbc:sqlite:C:/Users/fynni/Documents/HWR/Software Engineering II/Recrutify-Remake/recrutify.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Verbindung zur SQLite-Datenbank hergestellt!");
                // insert into
            }
        } catch (SQLException e) {
            System.out.println("Verbindungsfehler: " + e.getMessage());
        }
    }
}
