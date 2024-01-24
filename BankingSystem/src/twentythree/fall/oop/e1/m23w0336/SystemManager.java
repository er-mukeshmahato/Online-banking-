package twentythree.fall.oop.e1.m23w0336;

import java.util.Objects;

public class SystemManager {
    private static final LoginManager loginManager = new LoginManager();
    public static void main(String[] args) {
        runBankApp();
    }

    // Main method to run the banking app
    private static void runBankApp() {
        while (true) {
            displayMainMenu();

            int choice = SystemUI.getUserChoice();
            SystemUI.processUserChoice(choice);
        }
    }

    // Display the main menu based on user login status and roles
    private static void displayMainMenu() {


        if (loginManager.isLoggedIn()) {
            System.out.println("Welcome,"+" "+ loginManager.getLoggedInUsername());
            System.out.println("************App Menu*****************");
            // Display menu for logged-in users
            SystemUI.displayLoggedInMenu();

            // If the user has admin role, display admin menu
            if (Objects.equals(LoginManager.getUserRole(), "ADMIN")) {
                SystemUI.displayAdminMenu();
            }
            System.out.println("8. Logout");
        } else {
            // Display menu for logged-out users
            SystemUI.displayLoggedOutMenu();
        }
    }
}
