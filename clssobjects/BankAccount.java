public class BankAccount {
    // Attributes
    private String accountHolder;
    private String accountNumber;
    private double balance;
    
    // Constructor
    public BankAccount(String accountHolder, String accountNumber, double initialBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }
    
    // Method for depositing money
    public void depositMoney(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("=== Deposit Successful ===");
            System.out.println("Amount deposited: $" + amount);
            System.out.println("New balance: $" + balance);
            System.out.println("==========================");
        } else {
            System.out.println("Invalid deposit amount. Amount must be positive.");
        }
    }
    
    // Method for withdrawing money
    public void withdrawMoney(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("=== Withdrawal Successful ===");
                System.out.println("Amount withdrawn: $" + amount);
                System.out.println("Remaining balance: $" + balance);
                System.out.println("=============================");
            } else {
                System.out.println("=== Withdrawal Failed ===");
                System.out.println("Insufficient balance!");
                System.out.println("Requested amount: $" + amount);
                System.out.println("Available balance: $" + balance);
                System.out.println("=========================");
            }
        } else {
            System.out.println("Invalid withdrawal amount. Amount must be positive.");
        }
    }
    
    // Method to display current balance
    public void displayCurrentBalance() {
        System.out.println("=== Account Balance ===");
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: $" + balance);
        System.out.println("=======================");
    }
    
    // Method to display account summary
    public void displayAccountSummary() {
        System.out.println("=== Account Summary ===");
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
        System.out.println("=======================");
    }
    
    // Getters and Setters
    public String getAccountHolder() {
        return accountHolder;
    }
    
    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public double getBalance() {
        return balance;
    }
    
    // Main method to test the ATM simulation
    public static void main(String[] args) {
        // Create bank account objects
        BankAccount account1 = new BankAccount("John Doe", "ACC001", 1000.00);
        BankAccount account2 = new BankAccount("Jane Smith", "ACC002", 500.00);
        
        // Display initial account details
        System.out.println("=== Initial Account Status ===");
        account1.displayAccountSummary();
        account2.displayAccountSummary();
        
        // Perform ATM operations on account1
        System.out.println("\\n=== ATM Operations for " + account1.getAccountHolder() + " ===");
        account1.displayCurrentBalance();
        account1.depositMoney(250.00);
        account1.withdrawMoney(100.00);
        account1.withdrawMoney(2000.00); // Should fail
        account1.displayCurrentBalance();
        
        // Perform ATM operations on account2
        System.out.println("\\n=== ATM Operations for " + account2.getAccountHolder() + " ===");
        account2.displayCurrentBalance();
        account2.depositMoney(300.00);
        account2.withdrawMoney(200.00);
        account2.withdrawMoney(-50.00); // Should fail
        account2.displayCurrentBalance();
    }
}
