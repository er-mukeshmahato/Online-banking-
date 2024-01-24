package twentythree.fall.oop.e1.m23w0336;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private final String accholder;
    private final double balance;
    private static final Connection connection = DBManager.getConnection();


    public AccountManager(String accholder, double balance) {
        this.accholder = accholder;
        this.balance = balance;

    }

    // Method to simulate adding funds to the account
    public static boolean receiverFund(int receiverAccountNumber, double amount) {
        // Retrieve account details
        var data = getAccountDetails(receiverAccountNumber);

        if (data != null) {
            // Calculate new balance after receiving funds
            double balance = data.getBalance();
            double receiverAmt = balance + amount;

            // Transfer funds to the receiver's account
            return transferFund(receiverAccountNumber, receiverAmt);
        }

        return false; // Failed to retrieve account details
    }

    // Method to simulate withdrawing funds from the account
    public static boolean senderFund(double amount) {
        // Retrieve account details
        var data = getAccountDetails(LoginManager.getAccountnumber());

        if (data != null) {
            // Calculate new balance after sending funds
            double balance = data.getBalance();
            double senderAmt = balance - amount;

            // Transfer funds from the sender's account
            return transferFund(LoginManager.getAccountnumber(), senderAmt);
        }

        return false; // Failed to retrieve account details
    }


    public static boolean transferFund(int reciverAccountNumber, double amount) {

        String updateSql = "UPDATE BankAccount SET Balance = ? WHERE AccountNumber = ?";

        try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
            updateStatement.setDouble(1, amount);
            updateStatement.setInt(2, reciverAccountNumber);

            int rowsAffected = updateStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    public static List<Account> getAccountList() {
        List<Account> accountList = new ArrayList<>();
        String selectSql = "SELECT * FROM BankAccount";

        try (PreparedStatement selectStatement = connection.prepareStatement(selectSql);
             ResultSet resultSet = selectStatement.executeQuery()) {

            while (resultSet.next()) {
                // Retrieve account details from the result set
                int accountNumber = resultSet.getInt("AccountNumber");
                String accountHolderName = resultSet.getString("AccountHolderName");
                double balance = resultSet.getDouble("Balance");

                // Create a BankAccount object with the retrieved details
                Account account = new Account(accountNumber, accountHolderName, balance);

                // Add the account to the list
                accountList.add(account);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return accountList;
    }

    public int registerAccount() {
        String insertSql = "INSERT INTO BankAccount (AccountHolderName, Balance) VALUES (?, ?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            insertStatement.setString(1, accholder);
            insertStatement.setDouble(2, balance);
            insertStatement.executeUpdate();

            return retrieveGeneratedAccountNumber(insertStatement);
        } catch (SQLException e) {
            handleSQLException(e);
            return 0;
        }
    }

    public static Account getAccountDetails(int accountNumber) {
        String selectSql = "SELECT * FROM BankAccount WHERE AccountNumber = ?";
        Account account = new Account();

        try (PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {
            selectStatement.setInt(1, accountNumber);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                while (resultSet.next()) {
                    String accountHolderName = resultSet.getString("AccountHolderName");
                    double balance = resultSet.getDouble("Balance");
                      account = new Account(accountNumber, accountHolderName, balance);
                     return account;

                }
            }

        } catch (SQLException e) {
            handleSQLException(e);

        }

        return  account;
    }

    private int retrieveGeneratedAccountNumber(PreparedStatement insertStatement) throws SQLException {
        int accnumber = 0;
        try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                accnumber = generatedKeys.getInt(1);
                return accnumber;
            } else {
                System.err.println("Error retrieving generated account number.");
            }
        }
        return accnumber;
    }

    private static void handleSQLException(SQLException e) {
        // Log or throw a custom exception
        e.printStackTrace();
        System.err.println("Error handling SQL exception: " + e.getMessage());
    }

}
