import java.io.File;
import java.sql.*;
import java.io.IOException;

public class UserService {

    /**
     * with this function a local DB will created if not already exists
     * the goal is that no one has to create a new db everytime
     * @return dbpath which is the path of the database
     */
    private static String initializeDatabase() {
        String projectPath = System.getProperty("user.dir"); //current project folder
        String dbPath = projectPath + File.separator + "recrutify.db";

        File dbFile = new File(dbPath);

        if(!dbFile.exists()) {
            System.out.println("Database does not exist. A new database will be created.");

            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {
                if (connection != null) {
                    System.out.println("New Database created: " + dbPath);

                }
            } catch (SQLException e) {
                System.out.println("Fehler while creating the database: " + e.getMessage());
            }
        } else {
            System.out.println("Database already exists: " + dbPath);
        }
        return dbPath;
    }

    // Methode zum Einloggen und gleichzeitigen Verbinden mit der Datenbank
    public static User login(String username, String password) throws Exception {
        String url = "jdbc:sqlite:" + initializeDatabase(); //database will be initialized
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

    public static void register(String username, String password, String company, String firstName, String lastName) {
        String url = "jdbc:sqlite:" + initializeDatabase(); //database will be initialized
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
}
