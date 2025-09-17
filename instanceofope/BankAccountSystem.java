/**
 * BankAccount class demonstrating static, this, final, and instanceof concepts
 * Complete banking system with account management features
 */
public class BankAccountSystem {
    // Static variable - shared across all accounts
    private static String bankName = "State Bank of India";
    private static int totalAccounts = 0;
    
    // Instance variables
    private String accountHolderName;
    private final String accountNumber; // final - cannot be changed once assigned
    private double balance;
    private String accountType;
    
    // Constructor using 'this' to resolve ambiguity
    public BankAccountSystem(String accountHolderName, String accountNumber, double balance, String accountType) {
        // Using 'this' to distinguish between parameter and instance variable
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber; // final variable initialization
        this.balance = balance;
        this.accountType = accountType;
        
        // Update static counter
        totalAccounts++;
        
        System.out.println("Bank account created for: " + this.accountHolderName);
    }
    
    // Overloaded constructor with default account type
    public BankAccountSystem(String accountHolderName, String accountNumber, double balance) {
        // Using 'this' to call another constructor (constructor chaining)
        this(accountHolderName, accountNumber, balance, "Savings");
        System.out.println("Default account type 'Savings' assigned.");
    }
    
    // Default constructor
    public BankAccountSystem() {
        this("Default Account Holder", generateAccountNumber(), 0.0, "Savings");
        System.out.println("Default account created.");
    }
    
    // Static method to get total accounts
    public static int getTotalAccounts() {
        return totalAccounts;
    }
    
    // Static method to get bank name
    public static String getBankName() {
        return bankName;
    }
    
    // Static method to update bank name
    public static void setBankName(String newBankName) {
        bankName = newBankName;
        System.out.println("Bank name updated to: " + bankName);
        System.out.println("This change affects all " + totalAccounts + " accounts!");
    }
    
    // Method to deposit money using 'this'
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.printf("Deposited $%.2f to account %s. New balance: $%.2f%n", 
                             amount, this.accountNumber, this.balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }
    
    // Method to withdraw money using 'this'
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            System.out.printf("Withdrew $%.2f from account %s. New balance: $%.2f%n", 
                             amount, this.accountNumber, this.balance);
            return true;
        } else if (amount > this.balance) {
            System.out.println("Insufficient funds! Current balance: $" + this.balance);
            return false;
        } else {
            System.out.println("Invalid withdrawal amount!");
            return false;
        }
    }
    
    // Method to transfer money to another account using 'this'
    public boolean transferTo(BankAccountSystem targetAccount, double amount) {
        if (targetAccount != null && amount > 0 && amount <= this.balance) {
            if (this.withdraw(amount)) {
                targetAccount.deposit(amount);
                System.out.printf("Transfer successful: $%.2f from %s to %s%n", 
                                 amount, this.accountNumber, targetAccount.accountNumber);
                return true;
            }
        }
        System.out.println("Transfer failed!");
        return false;
    }
    
    // Method to display account details with instanceof check
    public void displayAccountDetails() {
        System.out.println("=== Account Details ===");
        System.out.println("Bank: " + bankName);
        System.out.println("Account Holder: " + this.accountHolderName);
        System.out.println("Account Number: " + this.accountNumber); // final variable access
        System.out.println("Account Type: " + this.accountType);
        System.out.printf("Current Balance: $%.2f%n", this.balance);
        System.out.println("======================");
    }
    
    // Static method to validate and display account using instanceof
    public static void processAccount(Object obj) {
        // Using instanceof to check object type
        if (obj instanceof BankAccountSystem) {
            System.out.println("✓ Object is a valid BankAccount instance");
            BankAccountSystem account = (BankAccountSystem) obj; // Safe casting
            account.displayAccountDetails();
        } else {
            System.out.println("✗ Object is not a BankAccount instance!");
            System.out.println("Object type: " + (obj != null ? obj.getClass().getSimpleName() : "null"));
        }
    }
    
    // Method to compare accounts using 'this'
    public boolean hasSameBank(BankAccountSystem other) {
        // Using 'this' to refer to current object
        return other != null && this.getBankName().equals(other.getBankName());
    }
    
    // Utility method to generate account number
    private static String generateAccountNumber() {
        return "ACC" + String.format("%06d", totalAccounts + 1);
    }
    
    // Getter methods
    public String getAccountHolderName() {
        return this.accountHolderName;
    }
    
    public String getAccountNumber() {
        return this.accountNumber; // final variable - read-only access
    }
    
    public double getBalance() {
        return this.balance;
    }
    
    public String getAccountType() {
        return this.accountType;
    }
    
    // Setter methods (note: accountNumber cannot be changed as it's final)
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
    
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
    // Method to get account summary using 'this'
    public String getAccountSummary() {
        return String.format("Account: %s | Holder: %s | Balance: $%.2f | Type: %s", 
                           this.accountNumber, this.accountHolderName, this.balance, this.accountType);
    }
    
    @Override
    public String toString() {
        return String.format("BankAccount{holder='%s', number='%s', balance=%.2f, type='%s'}", 
                           accountHolderName, accountNumber, balance, accountType);
    }
    
    // Main method for testing
    public static void main(String[] args) {
        System.out.println("=== Bank Account System Demo ===\\n");
        
        System.out.println("1. Initial state:");
        System.out.println("Bank: " + getBankName());
        System.out.println("Total accounts: " + getTotalAccounts());
        
        System.out.println("\\n2. Creating accounts (using 'this' in constructors):");
        BankAccountSystem account1 = new BankAccountSystem("John Doe", "ACC001", 1000.0, "Checking");
        BankAccountSystem account2 = new BankAccountSystem("Jane Smith", "ACC002", 2500.0);
        BankAccountSystem account3 = new BankAccountSystem();
        
        System.out.println("\\n3. Static method - Total accounts: " + getTotalAccounts());
        
        System.out.println("\\n4. Testing instanceof with valid account:");
        processAccount(account1);
        
        System.out.println("\\n5. Testing instanceof with invalid object:");
        processAccount("Not a bank account");
        processAccount(null);
        
        System.out.println("\\n6. Account operations using 'this':");
        account1.deposit(500.0);
        account2.withdraw(300.0);
        account1.transferTo(account2, 200.0);
        
        System.out.println("\\n7. Updated account details:");
        account1.displayAccountDetails();
        account2.displayAccountDetails();
        
        System.out.println("\\n8. Testing final variable (accountNumber cannot be changed):");
        System.out.println("Account1 number: " + account1.getAccountNumber());
        // account1.accountNumber = "NEW001"; // This would cause compilation error
        System.out.println("Note: accountNumber is final and cannot be modified after initialization");
        
        System.out.println("\\n9. Modifying static variable (affects all accounts):");
        setBankName("National Bank of India");
        
        System.out.println("\\n10. All accounts now show updated bank name:");
        processAccount(account1);
        processAccount(account2);
        
        System.out.println("\\n11. Account comparison using 'this':");
        System.out.println("Do account1 and account2 belong to same bank? " + 
                         account1.hasSameBank(account2));
        
        System.out.println("\\n12. Multiple instanceof checks:");
        Object[] objects = {account1, "String", 123, account2, null, new Object()};
        
        for (int i = 0; i < objects.length; i++) {
            System.out.printf("Object %d: ", i + 1);
            if (objects[i] instanceof BankAccountSystem) {
                BankAccountSystem acc = (BankAccountSystem) objects[i];
                System.out.println("BankAccount - " + acc.getAccountSummary());
            } else {
                System.out.println("Not a BankAccount - " + 
                                 (objects[i] != null ? objects[i].getClass().getSimpleName() : "null"));
            }
        }
        
        System.out.println("\\n=== Concepts Demonstrated ===");
        System.out.println("✓ Static: bankName and totalAccounts shared across all instances");
        System.out.println("✓ This: Used in constructors and methods to refer to current object");
        System.out.println("✓ Final: accountNumber cannot be changed once assigned");
        System.out.println("✓ Instanceof: Safe type checking before casting and operations");
    }
}
