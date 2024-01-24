package twentythree.fall.oop.e1.m23w0336;

import java.util.List;
import java.util.Scanner;

public  class AccountUI {
    private static final Scanner scanner = new Scanner(System.in);
    public static void displayAccountList(){

        var account= AccountManager.getAccountDetails(LoginManager.getAccountnumber());
            System.out.println("Your Account Details:");
            System.out.println("Account Holder Name: " + account.getAccountHolderName());
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("-----------------------");



    }

    public static void getAccountList() {
        // Retrieve the list of accounts
        List<Account> accountList = AccountManager.getAccountList();

        // Print the table header
        System.out.printf("%-20s%-20s%-20s%n", "Account Holder Name", "Account Number", "Balance");
        System.out.println("----------------------------------------------------------");

        // Iterate over each account in the list and print details
        for (Account account : accountList) {
            System.out.printf("%-20s%-20s%-20s%n",
                    account.getAccountHolderName(),
                    account.getAccountNumber(),
                    account.getBalance());
        }
    }






}
