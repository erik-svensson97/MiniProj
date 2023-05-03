package Model;

import java.io.Serial;
import java.io.Serializable;

/**
 * This class represents a user.
 * @Author Mahmoud Daabas
 */
public class User implements Serializable {
    /**
     * Declare variables.
     */
    private String username;
    private String password;
    private String dateOfBirth;
    private String email;
    private boolean registered;

    /**
     * First constructor for registering a user.
     * @param username
     * @param password
     * @param dateOfBirth
     * @param email
     */

    public User(String username, String password, String dateOfBirth, String email, boolean registered){
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.registered = registered;
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

    /**
     * Returns the register boolean.
     * @return register.
     */
    public boolean isRegistered(){
        return this.registered;
    }
}
