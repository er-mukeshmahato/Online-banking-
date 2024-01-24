package twentythree.fall.oop.e1.m23w0336;

import java.util.Scanner;

public class SystemUI {

    // Scanner for user input
    private static final Scanner scanner = new Scanner(System.in);

    // Managers for different responsibilities


    // Display menu options for logged-in users
    static void displayLoggedInMenu() {
        System.out.println("3. Account Details");
        System.out.println("4. Transfer Fund");
        System.out.println("5. Statement");

    }

    // Display menu options for logged-out users
    static void displayLoggedOutMenu() {

            System.out.println("1. Sign Up");
            System.out.println("2. Login");

    }

    // Display additional menu options for admin users
    static void displayAdminMenu() {
        System.out.println("6. Accounts List");
        System.out.println("7. Transaction History");


    }

    // Get user choice from the console
    static int getUserChoice() {
        int choice = -1;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                isValidInput = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer option.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        return choice;
    }

    // Process user choice and invoke corresponding functionality
    static void processUserChoice(int choice) {
        switch (choice) {
            case 1:
                UserUI.addAccount();
                break;
            case 2:
                LoginUI.loginUser();
                break;
            case 3:
                AccountUI.displayAccountList();
                break;
            case 4:
                TransactionUI.transferFund();
                break;
            case 5:
                TransactionUI.getUserStatement();
                break;
            case 6:
                AccountUI.getAccountList();
                break;
            case 7:
                TransactionUI.getTransactionHistory();
                break;
            case 8:
                System.out.println("Exiting the Banking App. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
    }
}
