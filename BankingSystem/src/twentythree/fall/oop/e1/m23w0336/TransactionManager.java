package twentythree.fall.oop.e1.m23w0336;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    private static final Connection connection = DBManager.getConnection();

    public static boolean addTransaction(int receiverAccountNumber, double amount, String description) {
          var senderAccountNo=LoginManager.getAccountnumber();
        String insertSql = "INSERT INTO [Transaction](senderAccountNo,receiverAccountNo,Amount,Description,createdOn) VALUES (?,?,?,?,?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            insertStatement.setInt(1, senderAccountNo);
            insertStatement.setInt(2, receiverAccountNumber);
            insertStatement.setDouble(3, amount);
            insertStatement.setString(4, description);
            insertStatement.setDate(5, Date.valueOf(LocalDate.now()));
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }
    private static void handleSQLException(SQLException e) {
        // Log or throw a custom exception
        e.printStackTrace();
        System.err.println("Error handling SQL exception: " + e.getMessage());
    }


    public static List<Transaction> getTransactionList() {
        List<Transaction> transactionList = new ArrayList<>();
        String selectSql = "SELECT * FROM [Transaction]";

        try (PreparedStatement selectStatement = connection.prepareStatement(selectSql);
             ResultSet resultSet = selectStatement.executeQuery()) {

            while (resultSet.next()) {
                // Retrieve account details from the result set
                int transactionID = resultSet.getInt("transactionID");
                int senderAccountnumber = resultSet.getInt("senderAccountNo");
                int receiverAccountnumber = resultSet.getInt("receiverAccountNo");
                double Amount = resultSet.getDouble("Amount");
                String description = resultSet.getString("Description");
                Date date = resultSet.getDate("createdOn");

                // Create a BankAccount object with the retrieved details
                Transaction trs = new Transaction(transactionID, senderAccountnumber,receiverAccountnumber, Amount, description,date);

                // Add the account to the list
                transactionList.add(trs);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return transactionList;
    }
    public static List<Transaction> getUserStatement() {
        List<Transaction> transactionList = new ArrayList<>();
        String selectSql = "SELECT * FROM [Transaction] WHERE senderAccountNo=? OR receiverAccountNo=?";

        try (PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {
            selectStatement.setInt(1, LoginManager.getAccountnumber());
            selectStatement.setInt(2, LoginManager.getAccountnumber());

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Retrieve account details from the result set
                    int transactionID = resultSet.getInt("transactionID");
                    double amount = resultSet.getDouble("Amount");
                    String description = resultSet.getString("Description");
                    Date date = resultSet.getDate("createdOn");

                    // Create a Transaction object with the retrieved details
                    Transaction trs = new Transaction(transactionID,
                            resultSet.getInt("senderAccountNo"),
                            resultSet.getInt("receiverAccountNo"),
                            amount, description, date);

                    // Add the transaction to the list
                    transactionList.add(trs);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return transactionList;
    }

}
