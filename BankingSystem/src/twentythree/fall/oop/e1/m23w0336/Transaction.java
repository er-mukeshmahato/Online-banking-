package twentythree.fall.oop.e1.m23w0336;

import java.sql.Date;

public class Transaction {
    private int transactionID;
    private int senderAccountNumber;
    private int receiverAccountNumber;
    private double balance;
    private String description;
    private Date date;

    // Constructors (you can add more if needed)

    public Transaction() {
        // Default constructor
    }

    public Transaction(int transactionID, int senderAccountNumber, int receiverAccountNumber,
                       double balance, String description, Date date) {
        this.transactionID = transactionID;
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.balance = balance;
        this.description = description;
        this.date = date;
    }

    // Getters

    public int getTransactionID() {
        return transactionID;
    }

    public int getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public int getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    // Setters

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public void setSenderAccountNumber(int senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }


    public void setReceiverAccountNumber(int receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
