package twentythree.fall.oop.e1.m23w0336;

import java.util.List;
import java.util.Scanner;

public class TransactionUI {
    // Scanner for user input
    private static final Scanner scanner = new Scanner(System.in);

    // Method to facilitate fund transfer
    public static void transferFund() {
        System.out.println("Transfer Fund:");

        // Prompt for receiver details
        System.out.println("Receiver Details :");

        // Variables to store user input
        String name;
        int receiverAccountNumber;
        double amount;
        String description;

        try {
            // Get user input for receiver details
            System.out.println("Account Holder Name: ");
            name = scanner.nextLine();

            System.out.println("Account Number: ");
            receiverAccountNumber = scanner.nextInt();

            System.out.println("Amount (Must be greater than 100): ");
            amount = scanner.nextDouble();

            // Validate the transfer amount
            if (amount < 100) {
                System.out.println("Invalid amount for transfer.");
                return;
            }

            System.out.println("Description : ");
            description = scanner.next();

        } catch (java.util.InputMismatchException e) {
            // Handle invalid input
            System.out.println("Invalid input. Please enter valid data.");
            scanner.nextLine(); // Consume the invalid input to prevent an infinite loop
            return;
        }

        // Initialize a loading thread
        Thread loadingThread = new Thread(() -> {
            simulateLoading();
        });

        // Start the loading thread
        loadingThread.start();

        try {
            // Perform fund transfer operations
            boolean transferSuccess1 = AccountManager.receiverFund(receiverAccountNumber, amount);
            boolean transferSuccess = AccountManager.senderFund(amount);
            boolean transferSuccess2 = TransactionManager.addTransaction(receiverAccountNumber, amount, description);

            // Wait for the loading thread to finish before printing the transfer result
            loadingThread.interrupt();

            // Print transfer result
            if (transferSuccess && transferSuccess1) {
                System.out.println("Transfer successful!");
            } else {
                System.out.println("Transfer failed. Please check the details and try again.");
            }
        } catch (Exception e) {
            // Handle exceptions during the transfer process
            System.out.println("An error occurred during the transfer: " + e.getMessage());
        }
    }

    // Method to simulate loading animation
    static void simulateLoading() {
        System.out.print("Processing");

        char[] bar = {'|', '/', '-', '\\'};
        try {
            // Display loading animation until interrupted
            while (!Thread.interrupted()) {
                for (int i = 0; i < 4; i++) {
                    System.out.print("\b" + bar[i % bar.length]);
                    Thread.sleep(5000);
                }
            }
        } catch (InterruptedException e) {
            // Thread interrupted, exit loading animation
        }
    }

    // Method to get transaction history and print it
    public static void getTransactionHistory() {
        // Retrieve the list of transactions
        List<Transaction> transList = TransactionManager.getTransactionList();

        // Print the table header
        System.out.printf("%-15s%-15s%-20s%-30s%n",
                "Transaction ID", "Balance", "Date", "Description");
        System.out.println("----------------------------------------------------------");

        // Iterate over each transaction in the list and print details
        for (Transaction transaction : transList) {
            System.out.printf("%-15s%-15s%-20s%-30s%n",
                    transaction.getTransactionID(),
                    transaction.getBalance(),
                    transaction.getDate(),
                    transaction.getDescription());
        }
    }
    public static void getUserStatement() {
        // Retrieve the list of transactions
        List<Transaction> trans = TransactionManager.getUserStatement();

        // Print the table header
        System.out.printf("%-15s%-20s%-20s%-15s%-30s%n",
                "Transaction ID", "Balance", "Date", "Status", "Description");
        System.out.println("----------------------------------------------------------");

        // Iterate over each transaction in the list and print details
        for (Transaction transaction : trans) {
            var status = "";
            if (transaction.getSenderAccountNumber() == LoginManager.getAccountnumber()) {
                status = "Sent";
            } else {
                status = "Received";
            }

            System.out.printf("%-15s%-20s%-20s%-15s%-30s%n",
                    transaction.getTransactionID(),
                    transaction.getBalance(),
                    transaction.getDate(),
                    status,
                    transaction.getDescription());
        }
    }

}
