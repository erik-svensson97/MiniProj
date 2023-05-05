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
        System.out.println(dateOfBirth);
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
}
