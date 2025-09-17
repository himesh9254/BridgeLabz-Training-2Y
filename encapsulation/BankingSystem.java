/**
 * Banking System demonstrating:
 * - Abstract classes with abstract methods
 * - Inheritance and polymorphism
 * - Interface implementation (Loanable)
 * - Secure encapsulation for sensitive banking data
 * - Different account types with unique interest calculations
 */

import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

// Interface for accounts that can provide loans
interface Loanable {
    boolean applyForLoan(double amount);
    double calculateLoanEligibility();
}

// Abstract BankAccount class with secure encapsulation
abstract class BankAccount {
    // Private fields - highly sensitive banking data
    private String accountNumber;
    private String holderName;
    private double balance;
    private String pin; // Highly sensitive - encrypted access only
    private LocalDate openingDate;
    private boolean isActive;
    private List<String> transactionHistory;
    private double creditScore; // Sensitive data for loan calculations
    
    // Constructor
    public BankAccount(String accountNumber, String holderName, double initialBalance, String pin) {
        validateAccountData(accountNumber, holderName, initialBalance, pin);
        
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
        this.pin = encryptPin(pin);
        this.openingDate = LocalDate.now();
        this.isActive = true;
        this.transactionHistory = new ArrayList<>();
        this.creditScore = 750.0; // Default credit score
        
        addTransaction("Account opened with balance: $" + initialBalance);
    }
    
    // Data validation method
    private void validateAccountData(String accountNumber, String holderName, double initialBalance, String pin) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be null or empty");
        }
        if (holderName == null || holderName.trim().isEmpty()) {
            throw new IllegalArgumentException("Holder name cannot be null or empty");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        if (pin == null || pin.length() != 4 || !pin.matches("\\d{4}")) {
            throw new IllegalArgumentException("PIN must be exactly 4 digits");
        }
    }
    
    // Simple PIN encryption (in real systems, use proper encryption)
    private String encryptPin(String pin) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : pin.toCharArray()) {
            encrypted.append((char)(c + 5)); // Simple Caesar cipher
        }
        return encrypted.toString();
    }
    
    // PIN verification method
    private boolean verifyPin(String inputPin) {
        return this.pin.equals(encryptPin(inputPin));
    }
    
    // Abstract method to be implemented by subclasses
    public abstract double calculateInterest();
    
    // Concrete methods for banking operations
    public boolean deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        if (!isActive) {
            throw new IllegalStateException("Account is inactive");
        }
        
        balance += amount;
        addTransaction("Deposited: $" + amount + " | Balance: $" + balance);
        System.out.printf("Deposited $%.2f. New balance: $%.2f%n", amount, balance);
        return true;
    }
    
    public boolean withdraw(double amount, String inputPin) {
        // Secure withdrawal with PIN verification
        if (!verifyPin(inputPin)) {
            addTransaction("Failed withdrawal attempt: Incorrect PIN");
            throw new SecurityException("Incorrect PIN provided");
        }
        
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (!isActive) {
            throw new IllegalStateException("Account is inactive");
        }
        if (amount > balance) {
            addTransaction("Failed withdrawal: Insufficient funds for $" + amount);
            System.out.println("Insufficient funds. Available balance: $" + balance);
            return false;
        }
        
        balance -= amount;
        addTransaction("Withdrawn: $" + amount + " | Balance: $" + balance);
        System.out.printf("Withdrawn $%.2f. New balance: $%.2f%n", amount, balance);
        return true;
    }
    
    // Method to display account details (protecting sensitive data)
    public void displayAccountDetails() {
        System.out.println("=== Account Details ===");
        System.out.println("Account Number: " + getMaskedAccountNumber());
        System.out.println("Holder Name: " + holderName);
        System.out.println("Account Type: " + this.getClass().getSimpleName());
        System.out.printf("Balance: $%.2f%n", balance);
        System.out.printf("Interest Rate: %.2f%%/year%n", calculateInterest() * 100);
        System.out.println("Opening Date: " + openingDate);
        System.out.println("Status: " + (isActive ? "Active" : "Inactive"));
        System.out.println("Credit Score: " + creditScore);
        
        // Display loan eligibility if account is loanable
        if (this instanceof Loanable) {
            System.out.printf("Loan Eligibility: $%.2f%n", ((Loanable) this).calculateLoanEligibility());
        }
        
        System.out.println("======================");
    }
    
    // Method to get masked account number (protecting sensitive data)
    private String getMaskedAccountNumber() {
        if (accountNumber.length() < 4) {
            return "****";
        }
        String lastFour = accountNumber.substring(accountNumber.length() - 4);
        return "****-" + lastFour;
    }
    
    // Method to add transaction to history
    private void addTransaction(String transaction) {
        String timestamp = LocalDateTime.now().toString().substring(0, 19);
        transactionHistory.add(timestamp + ": " + transaction);
        
        // Keep only last 50 transactions
        if (transactionHistory.size() > 50) {
            transactionHistory.remove(0);
        }
    }
    
    // Method to display transaction history (with PIN verification)
    public void displayTransactionHistory(String inputPin) {
        if (!verifyPin(inputPin)) {
            throw new SecurityException("Incorrect PIN provided");
        }
        
        System.out.println("\n=== Transaction History ===");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            int count = Math.min(10, transactionHistory.size()); // Show last 10 transactions
            for (int i = transactionHistory.size() - count; i < transactionHistory.size(); i++) {
                System.out.println(transactionHistory.get(i));
            }
        }
        System.out.println("===========================");
    }
    
    // Encapsulated getters (with appropriate access control)
    public String getAccountNumber() {
        return accountNumber; // Should only be accessible internally
    }
    
    public String getHolderName() {
        return holderName;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public LocalDate getOpeningDate() {
        return openingDate;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    protected double getCreditScore() {
        // Protected access for loan calculations
        return creditScore;
    }
    
    // Encapsulated setters with validation
    public void setHolderName(String holderName, String inputPin) {
        if (!verifyPin(inputPin)) {
            throw new SecurityException("Incorrect PIN provided");
        }
        if (holderName == null || holderName.trim().isEmpty()) {
            throw new IllegalArgumentException("Holder name cannot be null or empty");
        }
        
        String oldName = this.holderName;
        this.holderName = holderName;
        addTransaction("Name changed from '" + oldName + "' to '" + holderName + "'");
        System.out.println("Account holder name updated successfully.");
    }
    
    public void updatePin(String oldPin, String newPin) {
        if (!verifyPin(oldPin)) {
            throw new SecurityException("Incorrect current PIN provided");
        }
        if (newPin == null || newPin.length() != 4 || !newPin.matches("\\d{4}")) {
            throw new IllegalArgumentException("New PIN must be exactly 4 digits");
        }
        
        this.pin = encryptPin(newPin);
        addTransaction("PIN updated successfully");
        System.out.println("PIN updated successfully.");
    }
    
    public void setActive(boolean active) {
        this.isActive = active;
        addTransaction("Account status changed to: " + (active ? "Active" : "Inactive"));
    }
    
    protected void updateCreditScore(double newScore) {
        if (newScore < 300 || newScore > 850) {
            throw new IllegalArgumentException("Credit score must be between 300 and 850");
        }
        this.creditScore = newScore;
        addTransaction("Credit score updated to: " + newScore);
    }
    
    // Method to transfer funds to another account
    public boolean transferFunds(BankAccount targetAccount, double amount, String inputPin) {
        if (!verifyPin(inputPin)) {
            throw new SecurityException("Incorrect PIN provided");
        }
        if (targetAccount == null) {
            throw new IllegalArgumentException("Target account cannot be null");
        }
        
        if (withdraw(amount, inputPin)) {
            targetAccount.deposit(amount);
            addTransaction("Transferred $" + amount + " to account " + targetAccount.getMaskedAccountNumber());
            System.out.printf("Successfully transferred $%.2f to %s%n", amount, targetAccount.getHolderName());
            return true;
        }
        return false;
    }
}

// Savings Account - implements Loanable
class SavingsAccount extends BankAccount implements Loanable {
    private double interestRate;
    private int minimumBalance;
    private static final double DEFAULT_INTEREST_RATE = 0.035; // 3.5% per year
    private static final int DEFAULT_MINIMUM_BALANCE = 100;
    
    public SavingsAccount(String accountNumber, String holderName, double initialBalance, String pin) {
        super(accountNumber, holderName, Math.max(initialBalance, DEFAULT_MINIMUM_BALANCE), pin);
        this.interestRate = DEFAULT_INTEREST_RATE;
        this.minimumBalance = DEFAULT_MINIMUM_BALANCE;
    }
    
    public SavingsAccount(String accountNumber, String holderName, double initialBalance, 
                         String pin, double interestRate, int minimumBalance) {
        super(accountNumber, holderName, Math.max(initialBalance, minimumBalance), pin);
        setInterestRate(interestRate);
        setMinimumBalance(minimumBalance);
    }
    
    @Override
    public double calculateInterest() {
        // Compound interest calculation for savings
        double monthlyRate = interestRate / 12;
        return monthlyRate; // Monthly interest rate
    }
    
    @Override
    public boolean applyForLoan(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Loan amount must be positive");
        }
        
        double eligibleAmount = calculateLoanEligibility();
        if (amount <= eligibleAmount && getCreditScore() >= 650) {
            System.out.printf("Loan application approved for $%.2f%n", amount);
            updateCreditScore(getCreditScore() - 10); // Slight credit score decrease for new loan
            return true;
        } else {
            System.out.printf("Loan application denied. Requested: $%.2f, Eligible: $%.2f, Credit Score: %.0f%n", 
                amount, eligibleAmount, getCreditScore());
            return false;
        }
    }
    
    @Override
    public double calculateLoanEligibility() {
        // Savings account holders can borrow up to 10x their balance with good credit
        double baseEligibility = getBalance() * 10;
        
        // Adjust based on credit score
        if (getCreditScore() >= 750) {
            baseEligibility *= 1.2; // 20% bonus for excellent credit
        } else if (getCreditScore() < 650) {
            baseEligibility *= 0.5; // 50% reduction for poor credit
        }
        
        return Math.min(baseEligibility, 100000); // Cap at $100,000
    }
    
    // Method to calculate monthly interest earned
    public double calculateMonthlyInterest() {
        return getBalance() * calculateInterest();
    }
    
    // Method to credit monthly interest
    public void creditMonthlyInterest() {
        double interest = calculateMonthlyInterest();
        if (interest > 0) {
            deposit(interest);
            System.out.printf("Monthly interest credited: $%.2f%n", interest);
        }
    }
    
    // Encapsulated getters and setters
    public double getInterestRate() {
        return interestRate;
    }
    
    public void setInterestRate(double interestRate) {
        if (interestRate < 0 || interestRate > 0.1) {
            throw new IllegalArgumentException("Interest rate must be between 0% and 10%");
        }
        this.interestRate = interestRate;
    }
    
    public int getMinimumBalance() {
        return minimumBalance;
    }
    
    public void setMinimumBalance(int minimumBalance) {
        if (minimumBalance < 0) {
            throw new IllegalArgumentException("Minimum balance cannot be negative");
        }
        this.minimumBalance = minimumBalance;
    }
    
    @Override
    public void displayAccountDetails() {
        super.displayAccountDetails();
        System.out.printf("Interest Rate: %.2f%% per year%n", interestRate * 100);
        System.out.println("Minimum Balance: $" + minimumBalance);
        System.out.printf("Monthly Interest: $%.2f%n", calculateMonthlyInterest());
        System.out.println("======================");
    }
}

// Current Account - implements Loanable
class CurrentAccount extends BankAccount implements Loanable {
    private double overdraftLimit;
    private double overdraftFee;
    private int freeTransactionLimit;
    private int transactionCount;
    private static final double DEFAULT_OVERDRAFT_LIMIT = 1000.0;
    private static final double DEFAULT_OVERDRAFT_FEE = 35.0;
    private static final int DEFAULT_FREE_TRANSACTIONS = 50;
    
    public CurrentAccount(String accountNumber, String holderName, double initialBalance, String pin) {
        super(accountNumber, holderName, initialBalance, pin);
        this.overdraftLimit = DEFAULT_OVERDRAFT_LIMIT;
        this.overdraftFee = DEFAULT_OVERDRAFT_FEE;
        this.freeTransactionLimit = DEFAULT_FREE_TRANSACTIONS;
        this.transactionCount = 0;
    }
    
    public CurrentAccount(String accountNumber, String holderName, double initialBalance, 
                         String pin, double overdraftLimit, int freeTransactionLimit) {
        super(accountNumber, holderName, initialBalance, pin);
        setOverdraftLimit(overdraftLimit);
        this.overdraftFee = DEFAULT_OVERDRAFT_FEE;
        setFreeTransactionLimit(freeTransactionLimit);
        this.transactionCount = 0;
    }
    
    @Override
    public double calculateInterest() {
        // Current accounts typically have very low or no interest
        if (getBalance() > 10000) {
            return 0.01; // 1% for high balances
        }
        return 0.0; // No interest for normal balances
    }
    
    @Override
    public boolean withdraw(double amount, String inputPin) {
        // Current accounts can go into overdraft
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (!isActive()) {
            throw new IllegalStateException("Account is inactive");
        }
        
        double availableAmount = getBalance() + overdraftLimit;
        if (amount > availableAmount) {
            System.out.printf("Withdrawal denied. Amount: $%.2f, Available (including overdraft): $%.2f%n", 
                amount, availableAmount);
            return false;
        }
        
        // Use parent's withdraw method if sufficient balance, otherwise handle overdraft
        if (amount <= getBalance()) {
            return super.withdraw(amount, inputPin);
        } else {
            // Going into overdraft
            double overdraftAmount = amount - getBalance();
            super.withdraw(getBalance(), inputPin); // Withdraw all available balance
            
            // Apply overdraft fee
            deposit(-overdraftAmount - overdraftFee); // Negative deposit for overdraft
            transactionCount++;
            
            System.out.printf("Overdraft used: $%.2f, Fee: $%.2f, New balance: $%.2f%n", 
                overdraftAmount, overdraftFee, getBalance());
            return true;
        }
    }
    
    @Override
    public boolean applyForLoan(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Loan amount must be positive");
        }
        
        double eligibleAmount = calculateLoanEligibility();
        if (amount <= eligibleAmount && getCreditScore() >= 700) { // Stricter requirement for current accounts
            System.out.printf("Business loan approved for $%.2f%n", amount);
            updateCreditScore(getCreditScore() - 15); // Higher credit score impact for business loans
            return true;
        } else {
            System.out.printf("Business loan denied. Requested: $%.2f, Eligible: $%.2f, Credit Score: %.0f%n", 
                amount, eligibleAmount, getCreditScore());
            return false;
        }
    }
    
    @Override
    public double calculateLoanEligibility() {
        // Current account holders can borrow more for business purposes
        double baseEligibility = (getBalance() + overdraftLimit) * 15;
        
        // Adjust based on credit score and transaction activity
        if (getCreditScore() >= 750 && transactionCount > 20) {
            baseEligibility *= 1.5; // 50% bonus for active, high-credit accounts
        } else if (getCreditScore() < 700) {
            baseEligibility *= 0.3; // Significant reduction for poor credit
        }
        
        return Math.min(baseEligibility, 500000); // Higher cap for business loans
    }
    
    // Method to reset monthly transaction count
    public void resetMonthlyTransactionCount() {
        this.transactionCount = 0;
        System.out.println("Monthly transaction count reset.");
    }
    
    // Method to calculate transaction fees
    public double calculateTransactionFees() {
        if (transactionCount <= freeTransactionLimit) {
            return 0.0;
        }
        return (transactionCount - freeTransactionLimit) * 2.0; // $2 per excess transaction
    }
    
    // Encapsulated getters and setters
    public double getOverdraftLimit() {
        return overdraftLimit;
    }
    
    public void setOverdraftLimit(double overdraftLimit) {
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative");
        }
        this.overdraftLimit = overdraftLimit;
    }
    
    public int getFreeTransactionLimit() {
        return freeTransactionLimit;
    }
    
    public void setFreeTransactionLimit(int freeTransactionLimit) {
        if (freeTransactionLimit < 0) {
            throw new IllegalArgumentException("Free transaction limit cannot be negative");
        }
        this.freeTransactionLimit = freeTransactionLimit;
    }
    
    public int getTransactionCount() {
        return transactionCount;
    }
    
    @Override
    public void displayAccountDetails() {
        super.displayAccountDetails();
        System.out.printf("Overdraft Limit: $%.2f%n", overdraftLimit);
        System.out.println("Free Transactions: " + freeTransactionLimit + " per month");
        System.out.println("Current Transactions: " + transactionCount);
        System.out.printf("Transaction Fees: $%.2f%n", calculateTransactionFees());
        System.out.printf("Available Credit: $%.2f%n", getBalance() + overdraftLimit);
        System.out.println("======================");
    }
}

// Banking System main class
public class BankingSystem {
    private List<BankAccount> accounts;
    private static int accountCounter = 1001;
    
    public BankingSystem() {
        this.accounts = new ArrayList<>();
    }
    
    // Method to add account (demonstrates polymorphism)
    public void addAccount(BankAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        accounts.add(account);
        System.out.println("Account added successfully: " + account.getAccountNumber());
    }
    
    // Method to display all accounts (demonstrates polymorphism)
    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        
        System.out.println("\n=== ALL ACCOUNTS ===");
        for (BankAccount account : accounts) {
            account.displayAccountDetails(); // Polymorphic call
            System.out.println();
        }
    }
    
    // Method to calculate interest for all accounts (demonstrates polymorphism)
    public void calculateInterestForAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        
        System.out.println("\n=== INTEREST CALCULATION ===");
        double totalInterest = 0;
        
        for (BankAccount account : accounts) {
            double accountInterest = account.calculateInterest(); // Polymorphic call
            double interestAmount = account.getBalance() * accountInterest;
            totalInterest += interestAmount;
            
            System.out.printf("%s (%s): %.2f%% rate, $%.2f monthly interest%n", 
                account.getHolderName(),
                account.getClass().getSimpleName(),
                accountInterest * 100,
                interestAmount);
        }
        
        System.out.printf("Total Monthly Interest Payout: $%.2f%n", totalInterest);
        System.out.println("=============================");
    }
    
    // Method to find account by account number
    public BankAccount findAccountByNumber(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
    
    // Method to get loanable accounts
    public List<BankAccount> getLoanableAccounts() {
        List<BankAccount> loanableAccounts = new ArrayList<>();
        for (BankAccount account : accounts) {
            if (account instanceof Loanable) {
                loanableAccounts.add(account);
            }
        }
        return loanableAccounts;
    }
    
    // Method to process loan applications
    public void processLoanApplications(double loanAmount) {
        System.out.println("\n=== LOAN APPLICATION PROCESSING ===");
        for (BankAccount account : getLoanableAccounts()) {
            if (account instanceof Loanable) {
                Loanable loanableAccount = (Loanable) account;
                System.out.printf("Processing loan for %s (%s):%n", 
                    account.getHolderName(), account.getClass().getSimpleName());
                loanableAccount.applyForLoan(loanAmount);
                System.out.println();
            }
        }
        System.out.println("===================================");
    }
    
    // Utility method to generate account number
    public static String generateAccountNumber() {
        return "ACC" + String.format("%06d", accountCounter++);
    }
    
    // Main method demonstrating all concepts
    public static void main(String[] args) {
        System.out.println("=== Banking System Demo ===\n");
        
        BankingSystem bank = new BankingSystem();
        
        try {
            // Create different types of accounts (Polymorphism)
            System.out.println("1. Creating Bank Accounts (Demonstrating Encapsulation & Inheritance):");
            
            SavingsAccount savings1 = new SavingsAccount(
                generateAccountNumber(),
                "John Smith", 
                5000.0, 
                "1234"
            );
            
            SavingsAccount savings2 = new SavingsAccount(
                generateAccountNumber(),
                "Alice Johnson", 
                15000.0, 
                "5678",
                0.045, // 4.5% interest
                500    // $500 minimum balance
            );
            
            CurrentAccount current1 = new CurrentAccount(
                generateAccountNumber(),
                "Bob Wilson", 
                8000.0, 
                "9012"
            );
            
            CurrentAccount current2 = new CurrentAccount(
                generateAccountNumber(),
                "Carol Davis", 
                25000.0, 
                "3456",
                5000.0, // $5000 overdraft
                100     // 100 free transactions
            );
            
            // Add accounts to bank
            bank.addAccount(savings1);
            bank.addAccount(savings2);
            bank.addAccount(current1);
            bank.addAccount(current2);
            
            System.out.println("\n2. Displaying All Accounts (Demonstrating Polymorphism):");
            bank.displayAllAccounts();
            
            System.out.println("\n3. Banking Operations:");
            
            // Test deposits and withdrawals
            savings1.deposit(1000.0);
            savings1.withdraw(500.0, "1234");
            current1.deposit(2000.0);
            current1.withdraw(12000.0, "9012"); // Test overdraft
            
            System.out.println("\n4. Interest Calculation (Polymorphic Method Calls):");
            bank.calculateInterestForAllAccounts();
            
            System.out.println("\n5. Testing Encapsulation (PIN Security & Data Protection):");
            
            // Test valid operations with correct PIN
            System.out.println("Testing valid operations:");
            savings2.setHolderName("Alice Smith Johnson", "5678");
            current2.updatePin("3456", "7890");
            System.out.println("✓ Secure operations successful");
            
            // Test invalid operations (will throw exceptions)
            System.out.println("\nTesting security violations (Exception handling):");
            try {
                savings1.withdraw(100.0, "0000"); // Wrong PIN
            } catch (SecurityException e) {
                System.out.println("✓ Security exception caught: " + e.getMessage());
            }
            
            try {
                current1.setHolderName("Hacker Bob", "0000"); // Wrong PIN
            } catch (SecurityException e) {
                System.out.println("✓ Security exception caught: " + e.getMessage());
            }
            
            System.out.println("\n6. Fund Transfer Operations:");
            savings1.transferFunds(current1, 1000.0, "1234");
            current2.transferFunds(savings2, 5000.0, "7890");
            
            System.out.println("\n7. Transaction History (Secure Access):");
            savings1.displayTransactionHistory("1234");
            
            System.out.println("\n8. Loan Application Processing (Interface Implementation):");
            bank.processLoanApplications(50000.0); // Apply for $50,000 loan
            
            System.out.println("\n9. Monthly Interest Credit for Savings Accounts:");
            if (savings1 instanceof SavingsAccount) {
                ((SavingsAccount) savings1).creditMonthlyInterest();
            }
            if (savings2 instanceof SavingsAccount) {
                ((SavingsAccount) savings2).creditMonthlyInterest();
            }
            
            System.out.println("\n10. Updated Account Details After All Operations:");
            bank.displayAllAccounts();
            
            System.out.println("\n=== Concepts Demonstrated ===");
            System.out.println("✓ Abstract Classes: BankAccount class with abstract calculateInterest()");
            System.out.println("✓ Inheritance: SavingsAccount and CurrentAccount extend BankAccount");
            System.out.println("✓ Polymorphism: BankAccount references calling overridden methods");
            System.out.println("✓ Interface: Loanable interface with applyForLoan() and calculateLoanEligibility()");
            System.out.println("✓ Encapsulation: Secure PIN handling and sensitive data protection");
            System.out.println("✓ Security: PIN verification for sensitive operations");
            System.out.println("✓ Data Validation: Input validation preventing invalid banking operations");
            System.out.println("✓ Exception Handling: Proper error handling for security violations");
            
        } catch (Exception e) {
            System.err.println("Error in Banking System: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
