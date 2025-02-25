public class User {
    // Attribute für Benutzerinformationen
    private String username; // Benutzername des Users
    private String password; // Passwort des Users (sollte idealerweise gehasht gespeichert werden)
    private int UID; // Eindeutige Benutzer-ID
    private boolean admin; // Gibt an, ob der User Administratorrechte hat
    private String surname; // Vorname des Users
    private String lastname; // Nachname des Users
    private String company; // Zugehöriges Unternehmen des Users

    /**
     * Konstruktor für die Erstellung eines Benutzers mit grundlegenden Informationen.
     *
     * @param username Benutzername
     * @param password Passwort
     * @param UID Eindeutige Benutzer-ID
     * @param admin Gibt an, ob der Benutzer Adminrechte hat
     */
    public User(String username, String password, int UID, boolean admin) {
        this.username = username;
        this.password = password;
        this.UID = UID;
        this.admin = admin;
    }

    /**
     * Konstruktor für Benutzer mit zusätzlichen Informationen wie Vorname, Nachname und Unternehmen.
     *
     * @param company Unternehmen des Benutzers
     * @param surname Vorname des Benutzers
     * @param lastname Nachname des Benutzers
     * @param username Benutzername
     * @param password Passwort
     * @param UID Eindeutige Benutzer-ID
     */
    public User(String company, String surname, String lastname, String username, String password, Integer UID) {
        this.company = company;
        this.surname = surname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.UID = UID;
    }

    // Getter- und Setter-Methoden für die Attribute

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getusername() {
        return username;
    }

    public String getpassword() {
        return password;
    }

    public int getUserID() {
        return UID;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Gibt eine String-Darstellung des Benutzers zurück.
     */
    @Override
    public String toString() {
        return "Username: " + username + "\n"
                + "Passwort: " + password + "\n"
                + "UID: " + UID + "\n"
                + "Admin: " + admin;
    }
}
