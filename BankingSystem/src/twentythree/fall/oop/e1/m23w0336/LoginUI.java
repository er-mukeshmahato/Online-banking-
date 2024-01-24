package twentythree.fall.oop.e1.m23w0336;

import java.util.Scanner;

public class LoginUI {

    private static final LoginManager loginManager = new LoginManager();

    public static void loginUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.next();

        System.out.print("Enter password: ");
        String password = scanner.next();

        PasswordHasher passwordHasher = new PasswordHasher();
        String hashedPassword = passwordHasher.passwordToHash(password);

        if (LoginManager.authenticateUser(username, hashedPassword)) {
            loginManager.setLoginData(username,hashedPassword);
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }
}
