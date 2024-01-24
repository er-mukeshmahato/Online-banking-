package twentythree.fall.oop.e1.m23w0336;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class LoginManager {

    private static LoginManager instance;



    private static String loggedInUsername;
    private static String loggedInUserRole;
    private static int loggedInUserID;



    private static int accountnumber;
    LoginManager() {
        // private constructor to enforce singleton pattern
    }

    public static synchronized LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }



    static boolean authenticateUser(String username, String hashedPassword) {
        String sql = "SELECT * FROM UserCredential WHERE Username = ? AND PasswordHash = ?";
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashedPassword);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                return resultSet.next(); // If a row is returned, the user is authenticated
            }
        } catch (SQLException e) {
            // Log or provide a more user-friendly error message
            e.printStackTrace();
            return false;
        }
    }

    public boolean isLoggedIn() {
        return loggedInUsername != null;
    }

    public void logout() {
        loggedInUsername = null;
    }

    public String getLoggedInUsername() {
        return loggedInUsername;
    }
    public static void setLoggedInUsername(String loggedInUsername) {
        LoginManager.loggedInUsername = loggedInUsername;
    }
    public static void setUserRole(String retrievedRoles) {
        LoginManager.loggedInUserRole=retrievedRoles;
    }
    public static String getUserRole() {
        return loggedInUserRole;
    }

    public static int getAccountnumber() {
        return accountnumber;
    }

    public static void setAccountnumber(int accountnumber) {
        LoginManager.accountnumber = accountnumber;
    }
    public static void setLoggedInUserID(int loggedInUserID) {
        LoginManager.loggedInUserID = loggedInUserID;
    }

    public int getLoggedInUserID() {
        return loggedInUserID;
    }

    public void setLoginData(String username, String hashedPassword) {
        String sql = "SELECT UserID, Username,AccountNumber,Role FROM UserCredential WHERE Username = ? AND PasswordHash = ?";
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashedPassword);


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Check if the query returned any rows
                if (resultSet.next()) {
                    // Retrieve user data from the result set
                    int userId = resultSet.getInt("UserID");
                    int accountNumber = resultSet.getInt("AccountNumber");
                    String retrievedUsername = resultSet.getString("Username");
                    String retrievedRoles = resultSet.getString("Role");

                    // Create a User object with the retrieved details
                    setLoggedInUserID(userId);
                    setLoggedInUsername(retrievedUsername);
                    setAccountnumber(accountNumber);
                    setUserRole(retrievedRoles);

                }
            } catch (SQLException e) {
                // Log or provide a more user-friendly error message
                e.printStackTrace();
            }
        } catch (SQLException e) {
            // Log or provide a more user-friendly error message
            e.printStackTrace();
        }



    }



    public static void Logout(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Transfer Fund:");

        System.out.println("Receiver Details :");
        System.out.println("Account Holder Name: ");
        String name=scanner.next();
        System.out.println("Account Number: ");
        int AccountNumber=scanner.nextInt();
        System.out.println("Amount : ");
        double Amount=scanner.nextDouble();
        //var data=AccountManager.getAccountDetails(LoginManager.getInstance().getAccountnumber());
        //AccountManager.transferFund(AccountNumber,name,Amount);

    }

}
