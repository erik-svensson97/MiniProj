package DataAccessLayer;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;

public class UserProcedures {

    /**
     * Creates a user in the database.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param dateOfBirth The date of birth of the user.
     * @param email The email of the user.
     * @return true or false depending on success
     */
    public boolean createUser(String username, String password, String dateOfBirth, String email){
        DatabaseConnection dc = new DatabaseConnection();
       try( CallableStatement statement = dc.getConnection().prepareCall("{ ? = call create_user(?, ?, ?, ?) }")) {
            statement.registerOutParameter(1, Types.BOOLEAN);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setDate(4, Date.valueOf(dateOfBirth));
            statement.setString(5, email);
            statement.execute();
            boolean result = statement.getBoolean(1);
            return result;
        } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

    /**
     * Function to sign in the user.
     * @param username The users name.
     * @param password The users password.
     * @return result depending on success
     */
    public boolean signInUser(String username, String password) {
        DatabaseConnection dc = new DatabaseConnection();
        try( CallableStatement statement = dc.getConnection().prepareCall("{ ? = call sign_in(?, ?) }")) {
            statement.registerOutParameter(1, Types.BOOLEAN);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.execute();
            boolean result = statement.getBoolean(1);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
