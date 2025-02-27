import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import passwordSecurity.BCrypt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class UserService {
    public static final String url = "jdbc:postgresql://ep-still-bonus-a9a9tyuk-pooler.gwc.azure.neon.tech/neondb?sslmode=require";
    public static final String dbPassword = "npg_aVfGvA2U4nOp";
    public static final String dbUsername = "neondb_owner";




    /**
     * method to login as a company user
     * @param username username of the company account
     * @param plainTextPassword password in plain text which was entered by the user of the company account
     * @return the logged in user
     * @throws Exception if the sql query is not possible
     */
    public static User login(String username, String plainTextPassword) throws Exception {
        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            if (conn != null) {
                String sql = "SELECT * FROM Unternehmen WHERE Benutzername = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        String storedHashedPassword = rs.getString("Passwort");
                        if (verifyPassword(plainTextPassword, storedHashedPassword)) {
                            int id = rs.getInt("UID");
                            boolean isAdmin = rs.getBoolean("is_admin");

                            User user = new User(username, storedHashedPassword, id, isAdmin);
                            UserSession.setCurrentUser(user);
                            return user;
                        } else {
                            throw new Exception("Benutzername oder Passwort falsch. Bitte versuchen Sie es erneut.");
                        }
                    } else {
                        throw new Exception("Benutzername oder Passwort falsch. Bitte versuchen Sie es erneut.");
                    }
                } catch (SQLException e) {
                    System.out.println("Datenbankfehler: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Verbindungsfehler: " + e.getMessage());
        }
        return null;
    }

    /**
     * method to register a new company account
     * @param username username of the company account
     * @param password password of the company account
     * @param company name of the company
     * @param firstName first name of the user
     * @param lastName last name of the user
     */
    public static void register(String username, String password, String company, String firstName, String lastName, Boolean is_admin) throws Exception {
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
                if (conn != null) {
                    //System.out.println("Verbindung zur SQLite-Datenbank hergestellt!");
                    // insert into
                    String sql = "INSERT INTO Unternehmen (Name, Benutzername, Passwort, Vorname, Nachname, is_admin) VALUES (?,?,?,?,?,?)";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setString(1, company);
                        ps.setString(2, username);
                        ps.setString(3, password);
                        ps.setString(4, firstName);
                        ps.setString(5, lastName);
                        ps.setBoolean(6, is_admin);

                        ps.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println("Fehler beim Einfügen der Daten: "  + e.getMessage());
                    }
                }
            } catch (SQLException e) {
                System.out.println("Verbindungsfehler: " + e.getMessage());
            }
    }

    /**
     * method to load all test ids from a company to display them in the combo box
     * @param UID ID of the company
     * @return a list with all test ids from the company
     */
    public static ObservableList<String> getTIDsFromCompany(int UID){
        ObservableList<String> results = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            if (conn != null) {
                //System.out.println("Connected to the database.");
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

    public static ArrayList<Integer> getFIDsFromTest(int TestID) {
        ArrayList<Integer> questionIDs = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            if (conn != null) {
                String sql = "SELECT FID FROM Fragen WHERE TID = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setInt(1, TestID);
                    try (ResultSet rs = ps.executeQuery()){
                        while(rs.next()) {
                            questionIDs.add(rs.getInt("FID"));
                            System.out.println(questionIDs);
                        }
                    }
                } catch (SQLException e){
                    System.out.println("Error While Loading the Data" + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while connecting to the database.");
        } return questionIDs;
    }

    /**
     * method to load all test results into a list
     * @param TID Test ID of the test the user want to see
     * @return list of alle results
     */
    public static ObservableList<Bewerber> loadAllTestResults(int TID){
        //wichtig ist nur der Vorname, der Nachname und die Punktzahl
        ObservableList<Bewerber> allApplicants = FXCollections.observableArrayList();
        List<String> allBIDs = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            if (conn != null) {
                //System.out.println("Connected to the database.");
                String sql = "SELECT BID FROM BEWERBER_TEST WHERE TID = ? ";
                try (PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setInt(1, TID);
                    try (ResultSet rs = ps.executeQuery()){
                        while(rs.next()) {
                            allBIDs.add(rs.getString("BID"));
                        }
                    }
                }

                //get all applicants
                String sqlQuery = "SELECT BID, VORNAME, NACHNAME, ANTWORT_FREITEXT_1, Bewertung_1, ANTWORT_FREITEXT_2, Bewertung_2, ANTWORT_FREITEXT_3, Bewertung_3, ERGEBNIS FROM BEWERBER WHERE BID = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)) {
                    for (String bid : allBIDs) {
                        preparedStatement.setInt(1, Integer.parseInt(bid));
                        try (ResultSet rs = preparedStatement.executeQuery()) {
                            while (rs.next()) {
                                Bewerber applicant = new Bewerber(
                                        rs.getString("BID"),
                                        rs.getString("VORNAME"),
                                        rs.getString("NACHNAME"),
                                        rs.getString("ANTWORT_FREITEXT_1") != "" ? rs.getString("ANTWORT_FREITEXT_1") : "Keine Antwort" ,
                                        rs.getInt("Bewertung_1"),
                                        rs.getString("ANTWORT_FREITEXT_2") != "" ? rs.getString("ANTWORT_FREITEXT_2") : "Keine Antwort",
                                        rs.getInt("Bewertung_2"),
                                        rs.getString("ANTWORT_FREITEXT_3") != "" ? rs.getString("ANTWORT_FREITEXT_3") : "Keine Antwort",
                                        rs.getInt("Bewertung_3"),
                                        rs.getInt("ERGEBNIS")
                                );
                                //System.out.println("Neuer Bewerber: " + applicant.toString());
                                allApplicants.add(applicant);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while connecting to the database.");
            e.printStackTrace();
        }
        return allApplicants;
    }

    /**
     * method to load the current account information
     * @param UID company id to load the information for
     * @return a user object with the information of the current account
     */
    public static User loadCurrentAccountInformation(Integer UID) {
        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            if (conn != null) {
                String sql = "SELECT NAME, VORNAME, NACHNAME, BENUTZERNAME FROM UNTERNEHMEN WHERE UID = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, UID);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String name = rs.getString("NAME");
                            String vorname = rs.getString("VORNAME");
                            String nachname = rs.getString("NACHNAME");
                            String benutzername = rs.getString("BENUTZERNAME");

                            // Erstelle ein neues User-Objekt (Passwort wird nicht zurückgegeben)
                            return new User(name, vorname, nachname, benutzername, null, UID); // Null, da Passwort nicht übergeben wird
                        } else {
                            System.out.println("Kein Benutzer mit diesem Benutzernamen gefunden.");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Datenbankfehler: " + e.getMessage());
        }
        return null; // Rückgabe null, wenn Benutzer nicht gefunden oder Fehler aufgetreten
    }

    /**
     * method to update the current account information
     * @param enteredSurname new Surname
     * @param enteredLastname new Lastname
     * @param enteredUsername new username
     * @param enteredPassword new password
     * @param UID company id to change the information
     * @return true if successful changed, otherwise false
     */
    public static boolean accountInformationUpdate(String enteredSurname, String enteredLastname, String enteredUsername, String enteredPassword, Integer UID) {
        String sql;
        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            if (conn != null) {
                if(!enteredPassword.isBlank()) {
                    sql = "UPDATE UNTERNEHMEN SET VORNAME = ?, NACHNAME = ?, BENUTZERNAME = ?, PASSWORT = ? WHERE UID = ?";
                    System.out.println("Passwort ändert sich: " + enteredPassword);
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setString(1, enteredSurname);
                        ps.setString(2, enteredLastname);
                        ps.setString(3, enteredUsername);
                        ps.setString(4, enteredPassword);
                        ps.setInt(5, UID);

                        ps.executeUpdate();
                        System.out.println("Account succesfully updated.");
                        return true;
                    } catch (SQLException e) {
                        System.out.println("Fehler beim Einfügen der Daten: "  + e.getMessage());
                    }
                } else {
                    sql = "UPDATE UNTERNEHMEN SET VORNAME = ?, NACHNAME = ?, BENUTZERNAME = ? WHERE UID = ?";
                    System.out.println("Passwort bleibt gleich.");
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setString(1, enteredSurname);
                        ps.setString(2, enteredLastname);
                        ps.setString(3, enteredUsername);
                        ps.setInt(4, UID);

                        ps.executeUpdate();
                        System.out.println("Account succesfully updated.");
                        return true;
                    } catch (SQLException e) {
                    System.out.println("Fehler beim Einfügen der Daten: "  + e.getMessage());
                    }
                }



            }
        } catch (SQLException e) {
            System.out.println("Verbindungsfehler: " + e.getMessage());
        }
        return false;
    }

    /**
     * checks if a username already exits
     * @param username username the account will get if it doesnt already exits
     * @return true if exits, otherwise false
     */
    public static boolean usernameExists(String username, int UID){
        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String sql = "SELECT * FROM UNTERNEHMEN WHERE BENUTZERNAME = ?";
            if (conn != null) {
                if(UID != 0){
                    System.out.println("Aktueller Benutzer kann seinen Benutzernamenm verändern");
                    return false;
                }
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            return true;
                        } else {
                            System.out.println("Kein Benutzer mit diesem Benutzernamen gefunden.");
                            return false;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Datenbankfehler: " + e.getMessage());
        }
        return true;
    }

    /**
     * method to update the evaluation in for the free text in the database
     * @param eva_text_1 first evaluation
     * @param eva_text_2 second evaluation
     * @param eva_text_3 third evaluation
     * @param BID bewerer id of the evaluated applicant
     * @return
     */
    public static boolean updateEvaluation(Integer eva_text_1, Integer eva_text_2, Integer eva_text_3, Integer BID){
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            if (connection != null) {
                String sql = "UPDATE BEWERBER SET Bewertung_1 = ?, Bewertung_2 = ?, Bewertung_3 = ? WHERE BID = ?";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setInt(1, eva_text_1);
                    ps.setInt(2, eva_text_2);
                    ps.setInt(3, eva_text_3);
                    ps.setInt(4, BID);
                    ps.executeUpdate();
                    System.out.println("Evaluation succesfully updated.");
                    return true;
                }
            }
        } catch (SQLException e){
            System.out.println("Error while updating evaluation: " + e.getMessage());
        }
        return false;
    }

    /**
     * check if the inserted Password is the stored hash password
     * @param plainTextPassword inserted password
     * @param storedHashedPassword the stored hashPassword
     * @return true if correct password, otherwise false
     */
    public static boolean verifyPassword(String plainTextPassword, String storedHashedPassword) {
        return BCrypt.checkpw(plainTextPassword, storedHashedPassword);
    }

    /**
     * hash a plain text password
     * @param plainTextPassword the password which will be get hashed
     * @return the hash?
     */
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }



}
