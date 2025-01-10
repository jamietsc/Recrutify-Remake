import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDatabase {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:C:/Users/fynni/Documents/HWR/Software Engineering II/Recrutify-Remake/recrutify.db"; // Pfad zur SQLite-Datenbank
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Verbindung zur SQLite-Datenbank hergestellt!");
            }
        } catch (SQLException e) {
            System.out.println("Verbindungsfehler: " + e.getMessage());
        }
    }
}
