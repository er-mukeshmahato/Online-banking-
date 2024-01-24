package twentythree.fall.oop.e1.m23w0336;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Represents user credentials and provides methods for user registration.
 */
public class UserManager {
    private final String username;
    private final String password;
    private final String email;
    private final String role;
    private final int accountnumber;

    private final Connection connection;

    /**
     * Constructs a new UserCredential object.
     *
     * @param username User's username
     * @param password User's hashed password
     * @param email    User's email address
     * @param role     User's role (e.g., "USER" or "ADMIN")
     */
    public UserManager(String username, String password, String email, String role, int accountnumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.accountnumber = accountnumber;

        // Get the database connection
        this.connection = DBManager.getConnection();
    }

    /**
     * Registers a new user by inserting their credentials into the database.
     *
     * @return true if registration is successful, false otherwise
     */
    public boolean register() {
        String insertSql = "INSERT INTO UserCredential (Username, PasswordHash, Email, Role,AccountNumber) VALUES (?, ?, ?, ?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            // Set parameters for the prepared statement
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, role);
            preparedStatement.setInt(5, accountnumber);
            // Execute the SQL query to insert user credentials
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // Handle SQL exception
            //e.printStackTrace();
           System.err.println("User with same name Already Exists");
            return false;
        }
    }
}
