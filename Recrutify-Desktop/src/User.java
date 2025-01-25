import java.rmi.server.UID;

public class User {
    private String username;
    private String password;
    private int UID;
    private boolean admin;
    private String surname;
    private String lastname;
    private String company;


    public User(String username, String password, int UID, boolean admin) {
        this.username = username;
        this.password = password;
        this.UID = UID;
        this.admin = admin;
    }

    //user model for the account information
    public User(String company, String surname, String lastname, String username, String password, Integer UID) {
        this.company = company;
        this.surname = surname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.UID = UID;
    }

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

    @Override
    public String toString() {
        return "Username: " + username + "\n" + "Passwort: " + password + "\n" + "UID: " + UID + "\n" + "Admin: " + admin;
    }
}
