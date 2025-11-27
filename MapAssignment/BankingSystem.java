import java.util.*;

public class BankingSystem {
    private Map<String, Double> customerBalances;
    
    public BankingSystem() {
        customerBalances = new HashMap<>();
    }
    
    public void addCustomer(String customerId, double initialBalance) {
        if (customerBalances.containsKey(customerId)) {
            System.out.println("Customer already exists: " + customerId);
            return;
        }
        customerBalances.put(customerId, initialBalance);
        System.out.printf("Account created for %s with balance $%.2f%n", customerId, initialBalance);
    }
    
    public void deposit(String customerId, double amount) {
        if (!customerBalances.containsKey(customerId)) {
            System.out.println("Customer not found: " + customerId);
            return;
        }
        if (amount <= 0) {
            System.out.println("Invalid deposit amount");
            return;
        }
        double newBalance = customerBalances.get(customerId) + amount;
        customerBalances.put(customerId, newBalance);
        System.out.printf("%s deposited $%.2f. New balance: $%.2f%n", customerId, amount, newBalance);
    }
    
    public void withdraw(String customerId, double amount) {
        if (!customerBalances.containsKey(customerId)) {
            System.out.println("Customer not found: " + customerId);
            return;
        }
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount");
            return;
        }
        double currentBalance = customerBalances.get(customerId);
        if (amount > currentBalance) {
            System.out.println("Insufficient funds. Available: $" + currentBalance);
            return;
        }
        double newBalance = currentBalance - amount;
        customerBalances.put(customerId, newBalance);
        System.out.printf("%s withdrew $%.2f. New balance: $%.2f%n", customerId, amount, newBalance);
    }
    
    public void transfer(String fromCustomer, String toCustomer, double amount) {
        if (!customerBalances.containsKey(fromCustomer)) {
            System.out.println("Sender not found: " + fromCustomer);
            return;
        }
        if (!customerBalances.containsKey(toCustomer)) {
            System.out.println("Recipient not found: " + toCustomer);
            return;
        }
        
        double fromBalance = customerBalances.get(fromCustomer);
        if (amount > fromBalance) {
            System.out.println("Insufficient funds for transfer");
            return;
        }
        
        customerBalances.put(fromCustomer, fromBalance - amount);
        customerBalances.put(toCustomer, customerBalances.get(toCustomer) + amount);
        System.out.printf("Transferred $%.2f from %s to %s%n", amount, fromCustomer, toCustomer);
    }
    
    public void checkBalance(String customerId) {
        if (!customerBalances.containsKey(customerId)) {
            System.out.println("Customer not found: " + customerId);
            return;
        }
        System.out.printf("%s's balance: $%.2f%n", customerId, customerBalances.get(customerId));
    }
    
    public void displayAllCustomers() {
        System.out.println("\n=== All Customer Balances ===");
        TreeMap<String, Double> sorted = new TreeMap<>(customerBalances);
        
        for (Map.Entry<String, Double> entry : sorted.entrySet()) {
            System.out.printf("%s: $%.2f%n", entry.getKey(), entry.getValue());
        }
    }
    
    public void displayTop3Customers() {
        System.out.println("\n=== Top 3 Customers by Balance ===");
        
        List<Map.Entry<String, Double>> sorted = new ArrayList<>(customerBalances.entrySet());
        sorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        int count = Math.min(3, sorted.size());
        for (int i = 0; i < count; i++) {
            Map.Entry<String, Double> entry = sorted.get(i);
            System.out.printf("%d. %s: $%.2f%n", i + 1, entry.getKey(), entry.getValue());
        }
    }
    
    public void displayBankStatistics() {
        System.out.println("\n=== Bank Statistics ===");
        System.out.println("Total Customers: " + customerBalances.size());
        
        double totalDeposits = 0;
        for (double balance : customerBalances.values()) {
            totalDeposits += balance;
        }
        System.out.printf("Total Deposits: $%.2f%n", totalDeposits);
        
        if (!customerBalances.isEmpty()) {
            System.out.printf("Average Balance: $%.2f%n", totalDeposits / customerBalances.size());
        }
    }
    
    public static void main(String[] args) {
        BankingSystem bank = new BankingSystem();
        
        System.out.println("=== Creating Accounts ===");
        bank.addCustomer("C001", 5000.00);
        bank.addCustomer("C002", 12000.00);
        bank.addCustomer("C003", 8500.00);
        bank.addCustomer("C004", 3200.00);
        bank.addCustomer("C005", 25000.00);
        bank.addCustomer("C006", 7800.00);
        
        bank.displayAllCustomers();
        bank.displayTop3Customers();
        
        System.out.println("\n=== Transactions ===");
        bank.deposit("C001", 2500.00);
        bank.withdraw("C002", 3000.00);
        bank.transfer("C005", "C004", 5000.00);
        bank.withdraw("C003", 10000.00);
        
        System.out.println("\n=== Balance Checks ===");
        bank.checkBalance("C001");
        bank.checkBalance("C004");
        bank.checkBalance("C005");
        
        bank.displayTop3Customers();
        bank.displayBankStatistics();
    }
}
