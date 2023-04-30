package Model;

/**
 * This class represents a user.
 * @Author Mahmoud Daabas
 */
public class User {
    /**
     * Declare variables.
     */
    private String username;
    private String password;
    private String dateOfBirth;
    private String email;

    /**
     * First constructor for registering a user.
     * @param username
     * @param password
     * @param dateOfBirth
     * @param email
     */

    public User(String username, String password, String dateOfBirth, String email){
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    /**
     * Second constructor for logging in a user.
     * @param username
     * @param password
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the date of birth.
     * @return dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Gets the email.
     * @return email.
     */
    public String getEmail() {
        return email;
    }
}
