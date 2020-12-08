/**
 * A class to represent an administrator of the system. Inherits from User.
 */
public class Admin extends User {

    /**
     * 
     * @param username The username of the admin.
     * @param password The password of the admin.
     */
    public Admin(String username, String password) {
        super(username, password);
    }
}