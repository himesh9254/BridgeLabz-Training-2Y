/**
 * Product class for Shopping Cart System demonstrating static, this, final, and instanceof concepts
 * Complete e-commerce product management system
 */
public class ShoppingCartSystem {
    // Static variables - shared by all products
    private static double discount = 0.0; // percentage discount
    private static int totalProducts = 0;
    private static double totalValue = 0.0;
    
    // Instance variables
    private String productName;
    private double price;
    private int quantity;
    private final String productID; // final - unique identifier that cannot be changed
    private String category;
    private boolean inStock;
    
    // Constructor using 'this' to initialize productName, price, and quantity
    public ShoppingCartSystem(String productName, double price, int quantity, String category) {
        // Using 'this' to distinguish between parameter and instance variable
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.productID = generateProductID(); // final variable initialization
        this.inStock = true;
        
        // Update static counters
        totalProducts++;
        totalValue += (price * quantity);
        
        System.out.println("Product added: " + this.productName + " (ID: " + this.productID + ")");
    }
    
    // Overloaded constructor with default category
    public ShoppingCartSystem(String productName, double price, int quantity) {
        // Using 'this' to call another constructor (constructor chaining)
        this(productName, price, quantity, "General");
        System.out.println("Default category 'General' assigned.");
    }
    
    // Constructor with minimal info
    public ShoppingCartSystem(String productName, double price) {
        this(productName, price, 1, "Uncategorized");
        System.out.println("Default quantity 1 assigned.");
    }
    
    // Static method to update discount
    public static void updateDiscount(double newDiscount) {
        if (newDiscount >= 0 && newDiscount <= 100) {
            double oldDiscount = discount;
            discount = newDiscount;
            System.out.printf("Discount updated: %.1f%% -> %.1f%%\n", oldDiscount, discount);
            System.out.println("This discount applies to all " + totalProducts + " products!");
        } else {
            System.out.println("Invalid discount percentage! Must be between 0 and 100.");
        }
    }
    
    // Static method to get current discount
    public static double getDiscount() {
        return discount;
    }
    
    // Static method to display cart statistics
    public static void displayCartStatistics() {
        System.out.println("=== Shopping Cart Statistics ===");
        System.out.println("Total Products: " + totalProducts);
        System.out.printf("Total Cart Value: $%.2f\n", totalValue);
        System.out.printf("Current Discount: %.1f%%\n", discount);
        if (totalValue > 0) {
            double discountAmount = totalValue * (discount / 100);
            System.out.printf("Discount Amount: $%.2f\n", discountAmount);
            System.out.printf("Final Cart Value: $%.2f\n", totalValue - discountAmount);
        }
        System.out.println("================================");
    }
    
    // Method to calculate discounted price using 'this'
    public double getDiscountedPrice() {
        return this.price * (1 - discount / 100);
    }
    
    // Method to calculate total value for this product using 'this'
    public double getTotalProductValue() {
        return this.getDiscountedPrice() * this.quantity;
    }
    
    // Method to update quantity using 'this'
    public void updateQuantity(int newQuantity) {
        if (newQuantity >= 0) {
            // Update total cart value
            totalValue -= (this.price * this.quantity);
            totalValue += (this.price * newQuantity);
            
            int oldQuantity = this.quantity;
            this.quantity = newQuantity;
            
            System.out.printf("Quantity updated for %s (ID: %s): %d -> %d\n", 
                             this.productName, this.productID, oldQuantity, this.quantity);
            
            // Update stock status
            this.inStock = (this.quantity > 0);
        } else {
            System.out.println("Invalid quantity! Must be non-negative.");
        }
    }
    
    // Method to update price using 'this'
    public void updatePrice(double newPrice) {
        if (newPrice >= 0) {
            // Update total cart value
            totalValue -= (this.price * this.quantity);
            totalValue += (newPrice * this.quantity);
            
            double oldPrice = this.price;
            this.price = newPrice;
            
            System.out.printf("Price updated for %s (ID: %s): $%.2f -> $%.2f\n", 
                             this.productName, this.productID, oldPrice, this.price);
        } else {
            System.out.println("Invalid price! Must be non-negative.");
        }
    }
    
    // Method to apply special product discount using 'this'
    public void applySpecialDiscount(double specialDiscount) {
        if (specialDiscount > 0 && specialDiscount <= 100) {
            double discountAmount = this.price * (specialDiscount / 100);
            this.updatePrice(this.price - discountAmount);
            System.out.printf("Special discount of %.1f%% applied to %s\n", 
                             specialDiscount, this.productName);
        } else {
            System.out.println("Invalid special discount percentage!");
        }
    }
    
    // Method to display product details
    public void displayProductDetails() {
        System.out.println("=== Product Details ===");
        System.out.println("Product Name: " + this.productName);
        System.out.println("Product ID: " + this.productID); // final variable access
        System.out.println("Category: " + this.category);
        System.out.printf("Original Price: $%.2f\n", this.price);
        System.out.printf("Discounted Price: $%.2f (%.1f%% off)\n", 
                         this.getDiscountedPrice(), discount);
        System.out.println("Quantity: " + this.quantity);
        System.out.printf("Total Value: $%.2f\n", this.getTotalProductValue());
        System.out.println("In Stock: " + (this.inStock ? "Yes" : "No"));
        System.out.println("======================");
    }
    
    // Static method to validate and process product using instanceof
    public static void processProduct(Object obj) {
        // Using instanceof to check object type
        if (obj instanceof ShoppingCartSystem) {
            System.out.println("✓ Object is a valid Product instance");
            ShoppingCartSystem product = (ShoppingCartSystem) obj; // Safe casting
            product.displayProductDetails();
        } else {
            System.out.println("✗ Object is not a Product instance!");
            System.out.println("Object type: " + (obj != null ? obj.getClass().getSimpleName() : "null"));
        }
    }
    
    // Method to compare products using 'this'
    public boolean isSameProduct(ShoppingCartSystem other) {
        // Using 'this' to refer to current object and final productID for comparison
        return other != null && this.productID.equals(other.productID);
    }
    
    // Method to check if same category using 'this'
    public boolean isInSameCategory(ShoppingCartSystem other) {
        return other != null && this.category.equalsIgnoreCase(other.category);
    }
    
    // Method to check if more expensive using 'this'
    public boolean isMoreExpensiveThan(ShoppingCartSystem other) {
        return other != null && this.price > other.price;
    }
    
    // Utility method to generate unique product ID
    private static String generateProductID() {
        return "PROD" + String.format("%06d", totalProducts + 1);
    }
    
    // Getter methods using 'this'
    public String getProductName() {
        return this.productName;
    }
    
    public String getProductID() {
        return this.productID; // final variable - read-only access
    }
    
    public double getPrice() {
        return this.price;
    }
    
    public int getQuantity() {
        return this.quantity;
    }
    
    public String getCategory() {
        return this.category;
    }
    
    public boolean isInStock() {
        return this.inStock;
    }
    
    // Static getter methods
    public static int getTotalProducts() {
        return totalProducts;
    }
    
    public static double getTotalValue() {
        return totalValue;
    }
    
    // Method to get product summary using 'this'
    public String getProductSummary() {
        return String.format("%s (ID: %s) - $%.2f x %d = $%.2f", 
                           this.productName, this.productID, this.getDiscountedPrice(), 
                           this.quantity, this.getTotalProductValue());
    }
    
    @Override
    public String toString() {
        return String.format("Product{name='%s', id='%s', price=%.2f, quantity=%d}", 
                           productName, productID, price, quantity);
    }
    
    // Main method for testing
    public static void main(String[] args) {
        System.out.println("=== Shopping Cart System Demo ===\\n");
        
        System.out.println("1. Initial cart state:");
        System.out.println("Current discount: " + getDiscount() + "%");
        displayCartStatistics();
        
        System.out.println("\\n2. Adding products to cart (using 'this' in constructors):");
        ShoppingCartSystem product1 = new ShoppingCartSystem("Gaming Laptop", 1299.99, 1, "Electronics");
        ShoppingCartSystem product2 = new ShoppingCartSystem("Wireless Mouse", 29.99, 2);
        ShoppingCartSystem product3 = new ShoppingCartSystem("Coffee Mug", 12.50);
        
        System.out.println("\\n3. Cart statistics after adding products:");
        displayCartStatistics();
        
        System.out.println("\\n4. Testing instanceof with valid product:");
        processProduct(product1);
        
        System.out.println("\\n5. Testing instanceof with invalid objects:");
        processProduct("Not a product");
        processProduct(123);
        processProduct(null);
        
        System.out.println("\\n6. Updating products using 'this':");
        product1.updateQuantity(2);
        product2.updatePrice(24.99);
        product3.applySpecialDiscount(20.0);
        
        System.out.println("\\n7. Updated product details:");
        processProduct(product1);
        processProduct(product2);
        
        System.out.println("\\n8. Testing final variable (productID cannot be changed):");
        System.out.println("Product1 ID: " + product1.getProductID());
        // product1.productID = "NEWID001"; // This would cause compilation error
        System.out.println("Note: Product ID is final and cannot be modified after initialization");
        
        System.out.println("\\n9. Applying global discount (affects all products):");
        updateDiscount(15.0);
        
        System.out.println("\\n10. All products now show discounted prices:");
        processProduct(product1);
        processProduct(product3);
        
        System.out.println("\\n11. Product comparisons using 'this':");
        ShoppingCartSystem product4 = new ShoppingCartSystem("Mechanical Keyboard", 89.99, 1, "Electronics");
        System.out.println("Are product1 and product4 the same? " + product1.isSameProduct(product4));
        System.out.println("Are product1 and product4 in same category? " + product1.isInSameCategory(product4));
        System.out.println("Is product1 more expensive than product2? " + product1.isMoreExpensiveThan(product2));
        
        System.out.println("\\n12. Multiple instanceof checks with mixed objects:");
        Object[] objects = {product1, product2, "String", 456, product3, null, new java.util.HashMap()};
        
        for (int i = 0; i < objects.length; i++) {
            System.out.printf("Object %d: ", i + 1);
            if (objects[i] instanceof ShoppingCartSystem) {
                ShoppingCartSystem prod = (ShoppingCartSystem) objects[i];
                System.out.println("Product - " + prod.getProductSummary());
            } else {
                System.out.println("Not a Product - " + 
                                 (objects[i] != null ? objects[i].getClass().getSimpleName() : "null"));
            }
        }
        
        System.out.println("\\n13. Final cart statistics with discount:");
        displayCartStatistics();
        
        System.out.println("\\n=== Concepts Demonstrated ===");
        System.out.println("✓ Static: discount and totalProducts shared across all instances");
        System.out.println("✓ This: Used in constructors and methods to refer to current object");
        System.out.println("✓ Final: Product ID cannot be changed once assigned");
        System.out.println("✓ Instanceof: Safe type checking before casting and operations");
    }
}
