

/**
 * the class is to set the current company user so that you can call it from the whole program
 */
public class UserSession {
    /**
     * the current user object
     */
    private static User currentUser;

    /**
     * get the current user
     * @return current user data
     */

    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * set the current user
     * @param user logged in user
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
}

