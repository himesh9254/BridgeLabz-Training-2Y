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
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful!");
            System.out.println("Amount deposited: $" + amount);
            System.out.println("New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
        }
        System.out.println("-------------------");
    }
    
    // Method for withdrawing money
    public void withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawal successful!");
                System.out.println("Amount withdrawn: $" + amount);
                System.out.println("Remaining balance: $" + balance);
            } else {
                System.out.println("Insufficient balance!");
                System.out.println("Current balance: $" + balance);
                System.out.println("Requested amount: $" + amount);
            }
        } else {
            System.out.println("Invalid withdrawal amount. Please enter a positive value.");
        }
        System.out.println("-------------------");
    }
    
    // Method to display current balance
    public void displayBalance() {
        System.out.println("Account Balance:");
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: $" + balance);
        System.out.println("-------------------");
    }
    
    // Getter method for balance (useful for external checks)
    public double getBalance() {
        return balance;
    }
    
    // Main method to test the class
    public static void main(String[] args) {
        // Creating BankAccount object
        BankAccount account = new BankAccount("John Doe", "ACC12345", 1000.0);
        
        // Display initial balance
        account.displayBalance();
        
        // Perform some transactions
        account.deposit(500.0);
        account.withdraw(200.0);
        account.withdraw(1500.0); // This should fail due to insufficient balance
        account.deposit(-50.0);   // This should fail due to invalid amount
        
        // Display final balance
        account.displayBalance();
    }
}
