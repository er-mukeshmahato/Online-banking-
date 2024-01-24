package twentythree.fall.oop.e1.m23w0336;

import java.util.Scanner;

/**
 * The UserUI class handles user-related operations.
 */
public class UserUI {

    /**
     * Adds a new account by taking user input.
     */
    public static void addAccount() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter account details:");

        // Get user input for account details
        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Validate input
        if (isValidInput(fullName, username, email, password)) {
            // Hash the password using PasswordHasher
            PasswordHasher passwordHasher = new PasswordHasher();
            String securePassword = passwordHasher.passwordToHash(password);

            // Register user credentials and account
            AccountManager accountManager = new AccountManager(fullName, 1000);
            int accountNumber = accountManager.registerAccount();

            if (accountNumber != 0) {
                UserManager userManager = new UserManager(username, securePassword, email, "USER", accountNumber);
                if (userManager.register()) {
                    System.out.println("Registered Successfully.");
                } else {
                    //System.out.println("Failed to register user credentials.");
                    System.err.println("User with same name Already Exists");
                }
            } else {
                System.out.println("Failed to register account.");
            }
        } else {
            System.out.println("Invalid input. Please provide valid information.");
        }
    }

    /**
     * Validates user input for account creation.
     */
    private static boolean isValidInput(String fullName, String username, String email, String password) {
        return !fullName.isEmpty() && !username.isEmpty() && !email.isEmpty() && !password.isEmpty();
    }
}
