package twentythree.fall.oop.e1.m23w0336;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static final String DB_URL = "jdbc:sqlserver://Mukesh\\SQLEXPRESS;databaseName=bank;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "muk@123";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error Connecting to Database");
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }



}




