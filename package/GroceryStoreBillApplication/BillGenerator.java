package GroceryStoreBillApplication;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * BillGenerator computes bills and generates receipts
 * Demonstrates separation of business logic for billing
 */
public class BillGenerator {
    private double taxRate;
    private double discountRate;
    
    /**
     * Constructor with default tax rate (8%)
     */
    public BillGenerator() {
        this.taxRate = 0.08; // 8% tax
        this.discountRate = 0.0; // No discount by default
    }
    
    /**
     * Constructor with custom tax rate
     * @param taxRate Tax rate (e.g., 0.08 for 8%)
     */
    public BillGenerator(double taxRate) {
        this.taxRate = taxRate;
        this.discountRate = 0.0;
    }
    
    /**
     * Calculate subtotal for customer's cart
     * @param customer Customer object
     * @return Subtotal amount
     */
    public double calculateSubtotal(Customer customer) {
        double subtotal = 0;
        for (Product product : customer.getCart()) {
            subtotal += product.getTotalPrice();
        }
        return subtotal;
    }
    
    /**
     * Calculate tax amount
     * @param subtotal Subtotal amount
     * @return Tax amount
     */
    public double calculateTax(double subtotal) {
        return subtotal * taxRate;
    }
    
    /**
     * Calculate discount amount
     * @param subtotal Subtotal amount
     * @return Discount amount
     */
    public double calculateDiscount(double subtotal) {
        return subtotal * discountRate;
    }
    
    /**
     * Calculate final total
     * @param customer Customer object
     * @return Final total amount
     */
    public double calculateTotal(Customer customer) {
        double subtotal = calculateSubtotal(customer);
        double tax = calculateTax(subtotal);
        double discount = calculateDiscount(subtotal);
        return subtotal + tax - discount;
    }
    
    /**
     * Apply discount to the bill
     * @param discountRate Discount rate (e.g., 0.10 for 10%)
     */
    public void applyDiscount(double discountRate) {
        this.discountRate = discountRate;
        System.out.printf("Discount of %.0f%% applied!%n", discountRate * 100);
    }
    
    /**
     * Generate and display bill for customer
     * @param customer Customer object
     */
    public void generateBill(Customer customer) {
        if (customer.isCartEmpty()) {
            System.out.println("\nCannot generate bill - cart is empty!");
            return;
        }
        
        printReceipt(customer);
    }
    
    /**
     * Print detailed receipt
     * @param customer Customer object
     */
    public void printReceipt(Customer customer) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = dateFormat.format(new Date());
        
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  GROCERY STORE RECEIPT                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("Date/Time: " + timestamp);
        System.out.println("Customer: " + customer.getName() + " (ID: " + customer.getCustomerId() + ")");
        System.out.println("Phone: " + customer.getPhone());
        System.out.println("════════════════════════════════════════════════════════════════");
        
        System.out.println("\nITEMS PURCHASED:");
        System.out.println("────────────────────────────────────────────────────────────────");
        System.out.printf("%-20s  %6s   %8s   %10s%n", "ITEM", "QTY", "PRICE", "TOTAL");
        System.out.println("────────────────────────────────────────────────────────────────");
        
        for (Product product : customer.getCart()) {
            product.displayReceiptLine();
        }
        
        System.out.println("────────────────────────────────────────────────────────────────");
        
        double subtotal = calculateSubtotal(customer);
        double tax = calculateTax(subtotal);
        double discount = calculateDiscount(subtotal);
        double total = subtotal + tax - discount;
        
        System.out.println("\nBILL SUMMARY:");
        System.out.println("────────────────────────────────────────────────────────────────");
        System.out.printf("Subtotal:%51s$%.2f%n", "", subtotal);
        
        if (discountRate > 0) {
            System.out.printf("Discount (%.0f%%):%46s-$%.2f%n", discountRate * 100, "", discount);
            System.out.printf("Subtotal after discount:%38s$%.2f%n", "", subtotal - discount);
        }
        
        System.out.printf("Tax (%.0f%%):%53s$%.2f%n", taxRate * 100, "", tax);
        System.out.println("────────────────────────────────────────────────────────────────");
        System.out.printf("TOTAL:%55s$%.2f%n", "", total);
        System.out.println("════════════════════════════════════════════════════════════════");
        
        System.out.println("\n           Thank you for shopping with us!");
        System.out.println("              Please visit again!");
        System.out.println("════════════════════════════════════════════════════════════════\n");
    }
    
    /**
     * Generate comparative bill for multiple customers
     * @param customer1 First customer
     * @param customer2 Second customer
     */
    public void compareBills(Customer customer1, Customer customer2) {
        double total1 = calculateTotal(customer1);
        double total2 = calculateTotal(customer2);
        
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  BILL COMPARISON                           ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.printf("%s's Total: $%.2f (Items: %d)%n", 
                         customer1.getName(), total1, customer1.getCartSize());
        System.out.printf("%s's Total: $%.2f (Items: %d)%n", 
                         customer2.getName(), total2, customer2.getCartSize());
        
        double difference = Math.abs(total1 - total2);
        if (total1 > total2) {
            System.out.printf("\n%s spent $%.2f more than %s%n", 
                            customer1.getName(), difference, customer2.getName());
        } else if (total2 > total1) {
            System.out.printf("\n%s spent $%.2f more than %s%n", 
                            customer2.getName(), difference, customer1.getName());
        } else {
            System.out.println("\nBoth customers spent the same amount!");
        }
        System.out.println("════════════════════════════════════════════════════════════════\n");
    }
    
    // Getters and Setters
    public double getTaxRate() {
        return taxRate;
    }
    
    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }
    
    public double getDiscountRate() {
        return discountRate;
    }
}
