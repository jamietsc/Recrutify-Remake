public class User {
    private String username;
    private String password;
    private int UID;
    private String firstName;
    private String lastName;
    private String company;
    private boolean admin;

    private static int idCounter = 1;

    public User(String username, String password, String firstName, String lastName, String company, boolean admin) {
        this.username = username;
        this.password = password;
        this.UID = idCounter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.admin = false;
        idCounter++;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public boolean isAdmin() {
        return admin;
    }

    @Override
    public String toString() {
        return "Username: " + username + "\n" + "Passwort: " + password + "\n" + "UID: " + UID + "\n" + "First Name: " + firstName + "\n" + "Last Name: " + lastName + "\n" + "Company: " + company;
    }
}
