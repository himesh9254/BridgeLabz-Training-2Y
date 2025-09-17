/**
 * Product class demonstrating Instance vs Class Variables and Methods
 * Shows the difference between instance variables (unique to each object)
 * and class variables (shared among all objects)
 */
public class ProductInventory {
    // Instance Variables - each product object has its own copy
    private String productName;
    private double price;
    private int stockQuantity;
    private String productId;
    
    // Class Variable - shared among all products
    private static int totalProducts = 0;
    private static double totalInventoryValue = 0.0;
    private static String companyName = "TechMart Inc.";
    
    // Constructor
    public ProductInventory(String productName, double price, int stockQuantity) {
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productId = generateProductId();
        
        // Update class variables when a new product is created
        totalProducts++;
        totalInventoryValue += (price * stockQuantity);
        
        System.out.println("Product created: " + productName + " (ID: " + productId + ")");
    }
    
    // Default constructor
    public ProductInventory() {
        this("Unknown Product", 0.0, 0);
    }
    
    // Instance Method - operates on instance variables of a specific object
    public void displayProductDetails() {
        System.out.println("=== Product Details ===");
        System.out.println("Company: " + companyName); // Accessing class variable
        System.out.println("Product ID: " + productId);
        System.out.println("Product Name: " + productName);
        System.out.printf("Price: $%.2f%n", price);
        System.out.println("Stock Quantity: " + stockQuantity);
        System.out.printf("Total Value: $%.2f%n", calculateProductValue());
        System.out.println("======================");
    }
    
    // Instance Method - calculates value for this specific product
    public double calculateProductValue() {
        return price * stockQuantity;
    }
    
    // Instance Method - updates stock for this specific product
    public void updateStock(int newQuantity) {
        if (newQuantity >= 0) {
            // Update the total inventory value by removing old value and adding new
            totalInventoryValue -= calculateProductValue();
            this.stockQuantity = newQuantity;
            totalInventoryValue += calculateProductValue();
            
            System.out.println("Stock updated for " + productName + ": " + newQuantity + " units");
        } else {
            System.out.println("Invalid stock quantity! Must be non-negative.");
        }
    }
    
    // Instance Method - applies discount to this specific product
    public void applyDiscount(double discountPercentage) {
        if (discountPercentage > 0 && discountPercentage <= 100) {
            double oldPrice = price;
            totalInventoryValue -= calculateProductValue(); // Remove old value
            
            price = price * (1 - discountPercentage / 100);
            
            totalInventoryValue += calculateProductValue(); // Add new value
            
            System.out.printf("Discount applied to %s: %.1f%% off. Price: $%.2f -> $%.2f%n", 
                             productName, discountPercentage, oldPrice, price);
        } else {
            System.out.println("Invalid discount percentage!");
        }
    }
    
    // Class Method - displays total number of products (shared information)
    public static void displayTotalProducts() {
        System.out.println("=== Company Inventory Summary ===");
        System.out.println("Company: " + companyName);
        System.out.println("Total Products in Catalog: " + totalProducts);
        System.out.printf("Total Inventory Value: $%.2f%n", totalInventoryValue);
        if (totalProducts > 0) {
            System.out.printf("Average Product Value: $%.2f%n", totalInventoryValue / totalProducts);
        }
        System.out.println("================================");
    }
    
    // Class Method - updates company name for all products
    public static void updateCompanyName(String newCompanyName) {
        if (newCompanyName != null && !newCompanyName.trim().isEmpty()) {
            String oldName = companyName;
            companyName = newCompanyName;
            System.out.println("Company name updated: " + oldName + " -> " + companyName);
            System.out.println("This change affects all " + totalProducts + " products!");
        } else {
            System.out.println("Invalid company name provided!");
        }
    }
    
    // Class Method - gets inventory statistics
    public static void displayInventoryStatistics() {
        System.out.println("=== Detailed Inventory Statistics ===");
        System.out.println("Company: " + companyName);
        System.out.println("Total Products: " + totalProducts);
        System.out.printf("Total Inventory Value: $%.2f%n", totalInventoryValue);
        
        if (totalProducts > 0) {
            System.out.printf("Average Value per Product: $%.2f%n", 
                             totalInventoryValue / totalProducts);
        }
        
        System.out.println("Note: These statistics are shared across all Product objects");
        System.out.println("=====================================");
    }
    
    // Class Method - resets inventory (affects all products)
    public static void resetInventoryCounters() {
        totalProducts = 0;
        totalInventoryValue = 0.0;
        System.out.println("Inventory counters reset! This affects the class-level data.");
        System.out.println("Note: Individual product objects still exist, but counters are reset.");
    }
    
    // Utility method to generate unique product IDs
    private static String generateProductId() {
        return "PRD" + String.format("%04d", totalProducts + 1);
    }
    
    // Getter methods (instance methods)
    public String getProductName() { return productName; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
    public String getProductId() { return productId; }
    
    // Static getter methods (class methods)
    public static int getTotalProducts() { return totalProducts; }
    public static double getTotalInventoryValue() { return totalInventoryValue; }
    public static String getCompanyName() { return companyName; }
    
    @Override
    public String toString() {
        return String.format("Product{id='%s', name='%s', price=%.2f, stock=%d, value=%.2f}", 
                           productId, productName, price, stockQuantity, calculateProductValue());
    }
    
    // Main method for testing
    public static void main(String[] args) {
        System.out.println("=== Product Inventory: Instance vs Class Variables Demo ===\\n");
        
        System.out.println("1. Initial state - no products created yet:");
        displayTotalProducts();
        
        System.out.println("\\n2. Creating products (instance variables are unique to each object):");
        ProductInventory laptop = new ProductInventory("Gaming Laptop", 1299.99, 10);
        ProductInventory mouse = new ProductInventory("Wireless Mouse", 29.99, 50);
        ProductInventory keyboard = new ProductInventory("Mechanical Keyboard", 89.99, 25);
        
        System.out.println("\\n3. Displaying individual product details (instance methods):");
        laptop.displayProductDetails();
        mouse.displayProductDetails();
        
        System.out.println("\\n4. Class variable shared among all objects:");
        displayTotalProducts(); // Shows data shared by all products
        
        System.out.println("\\n5. Modifying instance variables (affects only specific objects):");
        laptop.updateStock(15); // Changes only laptop's stock
        mouse.applyDiscount(20); // Changes only mouse's price
        
        System.out.println("\\n6. After modifications - individual objects:");
        laptop.displayProductDetails();
        mouse.displayProductDetails();
        
        System.out.println("\\n7. Class variables updated automatically:");
        displayTotalProducts(); // Shows updated shared data
        
        System.out.println("\\n8. Modifying class variable (affects ALL objects):");
        updateCompanyName("SuperTech Electronics");
        
        System.out.println("\\n9. All products now show the new company name:");
        laptop.displayProductDetails();
        keyboard.displayProductDetails();
        
        System.out.println("\\n10. Creating more products:");
        ProductInventory monitor = new ProductInventory("4K Monitor", 399.99, 8);
        ProductInventory headphones = new ProductInventory("Gaming Headset", 79.99, 30);
        
        System.out.println("\\n11. Final inventory statistics:");
        displayInventoryStatistics();
        
        System.out.println("\\n12. Demonstrating static method access:");
        System.out.println("Accessing via class name: " + ProductInventory.getTotalProducts() + " products");
        System.out.println("Accessing via object: " + laptop.getTotalProducts() + " products (same value)");
        
        System.out.println("\\n=== Key Concepts Demonstrated ===");
        System.out.println("✓ Instance Variables: productName, price, stockQuantity (unique per object)");
        System.out.println("✓ Class Variables: totalProducts, totalInventoryValue, companyName (shared)");
        System.out.println("✓ Instance Methods: displayProductDetails(), updateStock() (work on specific object)");
        System.out.println("✓ Class Methods: displayTotalProducts(), updateCompanyName() (work on shared data)");
        System.out.println("✓ Data Sharing: Class variables maintain state across all object instances");
    }
}
