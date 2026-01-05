import java.util.*;

public class BankaApp {
    private static List<BankAccount> accounts = FileManager.loadAccounts();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Delete Account");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> viewAccounts();
                case 3 -> depositMoney();
                case 4 -> withdrawMoney();
                case 5 -> deleteAccount();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid option!");
            }
        } while (choice != 0);

        FileManager.saveAccounts(accounts);
    }

    private static void createAccount() {
        System.out.print("Enter Account Number: ");
        String accNo = sc.next();
        System.out.print("Enter Holder Name: ");
        String name = sc.next();
        System.out.print("Enter Initial Balance: ");
        double bal = sc.nextDouble();

        accounts.add(new BankAccount(accNo, name, bal));
        System.out.println("Account created!");
    }

    private static void viewAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (BankAccount acc : accounts) {
                System.out.println(acc);
            }
        }
    }

    private static void depositMoney() {
        System.out.print("Enter Account Number: ");
        String accNo = sc.next();
        System.out.print("Enter Amount: ");
        double amt = sc.nextDouble();

        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(accNo)) {
                acc.deposit(amt);
                System.out.println("Deposit successful.");
                return;
            }
        }
        System.out.println("Account not found.");
    }

    private static void withdrawMoney() {
        System.out.print("Enter Account Number: ");
        String accNo = sc.next();
        System.out.print("Enter Amount: ");
        double amt = sc.nextDouble();

        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(accNo)) {
                if (acc.withdraw(amt)) {
                    System.out.println("Withdrawal successful.");
                } else {
                    System.out.println("Insufficient funds.");
                }
                return;
            }
        }
        System.out.println("Account not found.");
    }

    private static void deleteAccount() {
        System.out.print("Enter Account Number to delete: ");
        String accNo = sc.next();

        accounts.removeIf(acc -> acc.getAccountNumber().equals(accNo));
        System.out.println("Account deleted if it existed.");
    }
}
