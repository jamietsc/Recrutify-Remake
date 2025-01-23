public class User {
    private String username;
    private String password;
    private int UID;
    private boolean admin;


    public User(String username, String password, int UID, boolean admin) {
        this.username = username;
        this.password = password;
        this.UID = UID;
        this.admin = admin;
    }

    public User() {

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

    @Override
    public String toString() {
        return "Username: " + username + "\n" + "Passwort: " + password + "\n" + "UID: " + UID + "\n" + "Admin: " + admin;
    }
}
