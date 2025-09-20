class BankAccount {
    String accountNumber;
    double balance;

    BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    void displayAccountType() {
        System.out.println("Generic Bank Account");
    }

    void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
        displayAccountType();
    }
}

class SavingsAccount extends BankAccount {
    double interestRate;

    SavingsAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    @Override
    void displayAccountType() {
        System.out.println("Account Type: Savings Account");
    }

    void calculateInterest() {
        double interest = balance * interestRate / 100;
        System.out.println("Annual Interest: $" + interest);
    }

    @Override
    void displayAccountDetails() {
        super.displayAccountDetails();
        System.out.println("Interest Rate: " + interestRate + "%");
    }
}

class CheckingAccount extends BankAccount {
    double withdrawalLimit;

    CheckingAccount(String accountNumber, double balance, double withdrawalLimit) {
        super(accountNumber, balance);
        this.withdrawalLimit = withdrawalLimit;
    }

    @Override
    void displayAccountType() {
        System.out.println("Account Type: Checking Account");
    }

    void withdraw(double amount) {
        if (amount <= withdrawalLimit && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + balance);
        } else {
            System.out.println("Withdrawal failed. Check limit or balance.");
        }
    }

    @Override
    void displayAccountDetails() {
        super.displayAccountDetails();
        System.out.println("Daily Withdrawal Limit: $" + withdrawalLimit);
    }
}

class FixedDepositAccount extends BankAccount {
    int termMonths;
    double interestRate;

    FixedDepositAccount(String accountNumber, double balance, int termMonths, double interestRate) {
        super(accountNumber, balance);
        this.termMonths = termMonths;
        this.interestRate = interestRate;
    }

    @Override
    void displayAccountType() {
        System.out.println("Account Type: Fixed Deposit Account");
    }

    double calculateMaturityAmount() {
        double maturityAmount = balance * (1 + (interestRate * termMonths) / (100 * 12));
        return maturityAmount;
    }

    @Override
    void displayAccountDetails() {
        super.displayAccountDetails();
        System.out.println("Term: " + termMonths + " months");
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Maturity Amount: $" + calculateMaturityAmount());
    }
}

public class BankAccounts {
    public static void main(String[] args) {
        SavingsAccount savings = new SavingsAccount("SAV001", 5000, 3.5);
        CheckingAccount checking = new CheckingAccount("CHK001", 2500, 1000);
        FixedDepositAccount fixedDeposit = new FixedDepositAccount("FD001", 10000, 12, 6.5);

        System.out.println("=== Bank Account Management System ===\n");
        
        System.out.println("Savings Account:");
        savings.displayAccountDetails();
        savings.calculateInterest();
        
        System.out.println("\nChecking Account:");
        checking.displayAccountDetails();
        checking.withdraw(500);
        
        System.out.println("\nFixed Deposit Account:");
        fixedDeposit.displayAccountDetails();
        
        System.out.println("\nHierarchical Inheritance Structure:");
        System.out.println("BankAccount is the parent class");
        System.out.println("SavingsAccount, CheckingAccount, and FixedDepositAccount are siblings");
    }
}