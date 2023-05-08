import java.util.ArrayList;
import java.util.Scanner;

public class ATMInterface2 {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Transaction> transactionHistory = new ArrayList<>();
    private static Account account;

    public static void main(String[] args) {
        boolean isAuthenticated = false;

        while (!isAuthenticated) {
            System.out.print("Please enter your user ID: ");
            String userId = scanner.nextLine();
            System.out.print("Please enter your user PIN: ");
            String userPin = scanner.nextLine();

            isAuthenticated = authenticateUser(userId, userPin);
        }

        while (true) {
            displayMenu();
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    checkAccountBalance(account);
                    break;
                case 2:
                    showTransactionHistory();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    depositMoney();
                    break;
                case 5:
                    transferMoney();
                    break;
                case 6:
                    System.out.println("Thank you for using our ATM. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static boolean authenticateUser(String userId, String userPin) {
        
        if (userId.trim().isEmpty() || userPin.trim().isEmpty()) {
            System.out.println("Invalid user ID or user PIN. Please try again.");
            return false;
        }

        account = new Account("John Doe", "1234567890", 1000.0);
        return true;
    }

    private static void displayMenu() {
        System.out.println("Welcome to our ATM, " + account.getOwnerName() + "!");
        System.out.println("Please select an option:");
        System.out.println("1. Check account balance");
        System.out.println("2. Show transaction history");
        System.out.println("3. Withdraw money");
        System.out.println("4. Deposit money");
        System.out.println("5. Transfer money");
        System.out.println("6. Quit");
    }

    private static void checkAccountBalance(Account account) {
        System.out.println("Your account balance is: Rs." + account.getBalance());
    }

    private static void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions to display.");
        } else {
            System.out.println("Transaction history:");
            for (Transaction transaction : transactionHistory) {
                System.out.println(transaction.toString());
            }
        }
    }

    private static void withdrawMoney() {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (account.withdraw(amount)) {
            System.out.println("Successfully withdrew Rs." + amount);
            transactionHistory.add(new Transaction(TransactionType.WITHDRAWAL, amount));
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private static void depositMoney() {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.println("Successfully deposited Rs." + amount);
        transactionHistory.add(new Transaction(TransactionType.DEPOSIT, amount));
    }

    private static void transferMoney() {
        System.out.print("Enter the account number to transfer to: ");
        String accountNumber = scanner.next();
        System.out.print("Enter the amount to transfer: ");
        double amount = scanner.nextDouble();
        if (account.transfer(amount)) {
            System.out.println("Successfully transferred Rs." + amount + " to account " + accountNumber);
        } else {
            System.out.println("Insufficient funds.");
        }
    }
}

class Account {
    private String ownerName;
    private String accountNumber;
    private double balance;
    public Account(String ownerName, String accountNumber, double balance) {
        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    
    public String getOwnerName() {
        return ownerName;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
    
    public void deposit(double amount) {
        balance += amount;
    }
    
    public boolean transfer(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

class Transaction {
    private TransactionType transactionType;
    private double amount;
    private String description;
    public Transaction(TransactionType transactionType, double amount) {
        this.transactionType = transactionType;
        this.amount = amount;
    
        switch (transactionType) {
            case WITHDRAWAL:
                this.description = "Withdrawal of Rs." + amount;
                break;
            case DEPOSIT:
                this.description = "Deposit of Rs." + amount;
                break;
            case TRANSFER:
                this.description = "Transfer of Rs." + amount;
                break;
        }
    }
    
    public TransactionType getTransactionType() {
        return transactionType;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return description;
    }
}
enum TransactionType {
    WITHDRAWAL,
    DEPOSIT,
    TRANSFER
    }    


