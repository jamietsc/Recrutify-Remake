import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.sql.*;


public class UserService {

    /**
     * function to initizalize the database
     * @return the path where the db is located
     */
    private static String initializeDatabase() {
        String projectPath = System.getProperty("user.dir");
        String dbPath = projectPath + File.separator + "recrutify.db";

        File dbFile = new File(dbPath);

        if (!dbFile.exists()) {
            System.out.println("Datenbank existiert nicht. Erstelle neue Datenbank...");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {
                if (conn != null) {
                    System.out.println("Neue Datenbank erstellt: " + dbPath);
                    createTables(conn); // Tabellen erstellen
                }
            } catch (SQLException e) {
                System.out.println("Fehler beim Erstellen der Datenbank: " + e.getMessage());
            }
        } else {
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {
                if (conn != null) {
                    System.out.println("Datenbank gefunden: " + dbPath);
                    createTables(conn); // Tabellen prüfen und ggf. erstellen
                }
            } catch (SQLException e) {
                System.out.println("Fehler beim Prüfen der Tabellen: " + e.getMessage());
            }
        }

        return dbPath;
    }

    /**
     * Method to create the tables
     * @param conn the variable of the connection
     */
    private static void createTables(Connection conn) {
        String[] tableCreationScripts = {
                """
            CREATE TABLE IF NOT EXISTS Unternehmen (
                UID INTEGER PRIMARY KEY AUTOINCREMENT,
                Name VARCHAR(255) NOT NULL,
                Benutzername VARCHAR(255) NOT NULL,
                Passwort VARCHAR(255) NOT NULL,
                Vorname VARCHAR(255),
                Nachname VARCHAR(255),
                is_admin BOOLEAN NOT NULL DEFAULT 0
            );
            """,
                """
            CREATE TABLE IF NOT EXISTS Test (
                TID INT PRIMARY KEY,
                Dauer TIME,
                UID INT,
                FOREIGN KEY (UID) REFERENCES Unternehmen(UID)
            );
            """,
                """
            CREATE TABLE IF NOT EXISTS Bewerber (
                BID INT PRIMARY KEY,
                Vorname VARCHAR(255),
                Nachname VARCHAR(255),
                Ergebnis INT
            );
            """,
                """
            CREATE TABLE IF NOT EXISTS MultipleChoiceFragen (
                FID INT PRIMARY KEY,
                Text TEXT,
                Antwort_1 TEXT,
                Antwort_2 TEXT,
                Antwort_3 TEXT,
                Antwort_4 TEXT,
                Richtig_1 BOOLEAN,
                Richtig_2 BOOLEAN,
                Richtig_3 BOOLEAN,
                Richtig_4 BOOLEAN,
                AID INT,
                TID INT,
                FOREIGN KEY (TID) REFERENCES Test(TID)
            );
            """,
                """
            CREATE TABLE IF NOT EXISTS Bewerber_Test (
                BID INT,
                TID INT,
                PRIMARY KEY (BID, TID),
                FOREIGN KEY (BID) REFERENCES Bewerber(BID),
                FOREIGN KEY (TID) REFERENCES Test(TID)
            );
            """
        };

        for (String sql : tableCreationScripts) {
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(sql);
                System.out.println("Table exists or will be created.");
            } catch (SQLException e) {
                System.out.println("Error while Creating the table: " + e.getMessage());
            }
        }
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

    public static ObservableList<String> getTestsFromCompany(int UID){
        String url = "jdbc:sqlite:" + initializeDatabase();
        ObservableList<String> results = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Connected to the database.");
                //Select from
                String sql = "SELECT TID FROM TEST WHERE UID = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setInt(1, UID);
                    try (ResultSet rs = ps.executeQuery()){
                        while(rs.next()) {
                            results.add(rs.getString("TID"));
                        }
                    }
                    return results;
                } catch (SQLException e){
                    System.out.println("Error While Loading the Data");
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while connecting to the database.");
        }
        return null;
    }
}
