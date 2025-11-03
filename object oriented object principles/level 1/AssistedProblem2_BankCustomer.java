import java.util.ArrayList;

// Customer class
class Customer {
    private String name;
    private String customerId;
    private double balance;
    private Bank bank;
    
    public Customer(String name, String customerId) {
        this.name = name;
        this.customerId = customerId;
        this.balance = 0.0;
    }
    
    public void associateBank(Bank bank) {
        this.bank = bank;
    }
    
    public void viewBalance() {
        System.out.println(name + "'s balance: $" + balance);
    }
    
    public void deposit(double amount) {
        balance += amount;
        System.out.println(name + " deposited $" + amount);
    }
    
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(name + " withdrew $" + amount);
        } else {
            System.out.println("Insufficient balance for " + name);
        }
    }
    
    public String getName() {
        return name;
    }
    
    public String getCustomerId() {
        return customerId;
    }
}

// Bank class
class Bank {
    private String bankName;
    private ArrayList<Customer> customers;
    
    public Bank(String bankName) {
        this.bankName = bankName;
        this.customers = new ArrayList<>();
    }
    
    public void openAccount(Customer customer) {
        customers.add(customer);
        customer.associateBank(this);
        System.out.println("Account opened for " + customer.getName() + " at " + bankName);
    }
    
    public void closeAccount(Customer customer) {
        customers.remove(customer);
        System.out.println("Account closed for " + customer.getName() + " at " + bankName);
    }
    
    public void displayCustomers() {
        System.out.println("\nCustomers at " + bankName + ":");
        for (Customer customer : customers) {
            System.out.println("  " + customer.getName() + " (ID: " + customer.getCustomerId() + ")");
        }
    }
    
    public String getBankName() {
        return bankName;
    }
}

// Main class to demonstrate association
public class AssistedProblem2_BankCustomer {
    public static void main(String[] args) {
        // Create bank
        Bank nationalBank = new Bank("National Bank");
        
        // Create customers
        Customer customer1 = new Customer("Alice Johnson", "C001");
        Customer customer2 = new Customer("Bob Smith", "C002");
        Customer customer3 = new Customer("Carol White", "C003");
        
        // Open accounts - demonstrating association
        nationalBank.openAccount(customer1);
        nationalBank.openAccount(customer2);
        nationalBank.openAccount(customer3);
        
        // Display customers
        nationalBank.displayCustomers();
        
        // Demonstrate communication through methods
        System.out.println("\n--- Banking Operations ---");
        customer1.deposit(1000);
        customer1.viewBalance();
        
        customer2.deposit(500);
        customer2.withdraw(200);
        customer2.viewBalance();
        
        customer3.deposit(2000);
        customer3.withdraw(2500); // Insufficient balance
        customer3.viewBalance();
    }
}
