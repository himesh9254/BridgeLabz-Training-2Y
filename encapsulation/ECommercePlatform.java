/**
 * E-Commerce Platform demonstrating:
 * - Abstract classes with abstract methods
 * - Inheritance and polymorphism
 * - Interface implementation (Taxable)
 * - Encapsulation with validation
 * - Final price calculation with discounts and taxes
 */

import java.util.*;
import java.time.LocalDate;

// Interface for products that can be taxed
interface Taxable {
    double calculateTax();
    String getTaxDetails();
}

// Abstract Product class with encapsulation
abstract class Product {
    // Private fields - encapsulated data
    private String productId;
    private String name;
    private double price;
    private int stockQuantity;
    private String category;
    private LocalDate addedDate;
    private boolean isAvailable;
    
    // Constructor
    public Product(String productId, String name, double price, int stockQuantity, String category) {
        validateProductData(productId, name, price, stockQuantity, category);
        
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.addedDate = LocalDate.now();
        this.isAvailable = stockQuantity > 0;
    }
    
    // Data validation method
    private void validateProductData(String productId, String name, double price, int stockQuantity, String category) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Product category cannot be null or empty");
        }
    }
    
    // Abstract method to be implemented by subclasses
    public abstract double calculateDiscount();
    
    // Concrete method to calculate final price
    public double calculateFinalPrice() {
        double basePrice = price;
        double discount = calculateDiscount();
        double tax = 0;
        
        // Apply tax if product is taxable
        if (this instanceof Taxable) {
            tax = ((Taxable) this).calculateTax();
        }
        
        return basePrice - discount + tax;
    }
    
    // Method to display product details
    public void displayProductDetails() {
        System.out.println("=== Product Details ===");
        System.out.println("Product ID: " + productId);
        System.out.println("Name: " + name);
        System.out.println("Category: " + category);
        System.out.printf("Original Price: $%.2f%n", price);
        System.out.printf("Discount: $%.2f%n", calculateDiscount());
        
        if (this instanceof Taxable) {
            System.out.printf("Tax: $%.2f%n", ((Taxable) this).calculateTax());
            System.out.println("Tax Details: " + ((Taxable) this).getTaxDetails());
        } else {
            System.out.println("Tax: Not Applicable");
        }
        
        System.out.printf("Final Price: $%.2f%n", calculateFinalPrice());
        System.out.println("Stock: " + stockQuantity);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("Added Date: " + addedDate);
        System.out.println("======================");
    }
    
    // Encapsulated getters
    public String getProductId() {
        return productId;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getStockQuantity() {
        return stockQuantity;
    }
    
    public String getCategory() {
        return category;
    }
    
    public LocalDate getAddedDate() {
        return addedDate;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    // Encapsulated setters with validation
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        this.name = name;
    }
    
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }
        this.price = price;
    }
    
    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
        this.stockQuantity = stockQuantity;
        this.isAvailable = stockQuantity > 0;
    }
    
    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Product category cannot be null or empty");
        }
        this.category = category;
    }
    
    // Method to reduce stock when purchased
    public boolean purchaseProduct(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Purchase quantity must be positive");
        }
        if (quantity > stockQuantity) {
            return false; // Not enough stock
        }
        
        stockQuantity -= quantity;
        isAvailable = stockQuantity > 0;
        return true;
    }
    
    // Method to add stock
    public void addStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Stock addition quantity must be positive");
        }
        stockQuantity += quantity;
        isAvailable = true;
    }
}

// Electronics product class - implements Taxable
class Electronics extends Product implements Taxable {
    private String brand;
    private int warrantyPeriod; // in months
    private static final double ELECTRONICS_TAX_RATE = 0.18; // 18% tax
    private static final double BASE_DISCOUNT_RATE = 0.05; // 5% base discount
    
    public Electronics(String productId, String name, double price, int stockQuantity, String brand, int warrantyPeriod) {
        super(productId, name, price, stockQuantity, "Electronics");
        setBrand(brand);
        setWarrantyPeriod(warrantyPeriod);
    }
    
    @Override
    public double calculateDiscount() {
        // Electronics get base discount + additional discount for high-value items
        double baseDiscount = getPrice() * BASE_DISCOUNT_RATE;
        double additionalDiscount = 0;
        
        if (getPrice() > 1000) {
            additionalDiscount = getPrice() * 0.02; // Additional 2% for expensive items
        }
        
        return baseDiscount + additionalDiscount;
    }
    
    @Override
    public double calculateTax() {
        return getPrice() * ELECTRONICS_TAX_RATE;
    }
    
    @Override
    public String getTaxDetails() {
        return String.format("Electronics Tax (%.0f%%): $%.2f", ELECTRONICS_TAX_RATE * 100, calculateTax());
    }
    
    // Encapsulated getters and setters
    public String getBrand() {
        return brand;
    }
    
    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be null or empty");
        }
        this.brand = brand;
    }
    
    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }
    
    public void setWarrantyPeriod(int warrantyPeriod) {
        if (warrantyPeriod < 0) {
            throw new IllegalArgumentException("Warranty period cannot be negative");
        }
        this.warrantyPeriod = warrantyPeriod;
    }
    
    @Override
    public void displayProductDetails() {
        super.displayProductDetails();
        System.out.println("Brand: " + brand);
        System.out.println("Warranty: " + warrantyPeriod + " months");
        System.out.println("======================");
    }
}

// Clothing product class - implements Taxable
class Clothing extends Product implements Taxable {
    private String size;
    private String material;
    private static final double CLOTHING_TAX_RATE = 0.12; // 12% tax
    private static final double SEASONAL_DISCOUNT_RATE = 0.15; // 15% seasonal discount
    
    public Clothing(String productId, String name, double price, int stockQuantity, String size, String material) {
        super(productId, name, price, stockQuantity, "Clothing");
        setSize(size);
        setMaterial(material);
    }
    
    @Override
    public double calculateDiscount() {
        // Clothing gets seasonal discount
        return getPrice() * SEASONAL_DISCOUNT_RATE;
    }
    
    @Override
    public double calculateTax() {
        return getPrice() * CLOTHING_TAX_RATE;
    }
    
    @Override
    public String getTaxDetails() {
        return String.format("Clothing Tax (%.0f%%): $%.2f", CLOTHING_TAX_RATE * 100, calculateTax());
    }
    
    // Encapsulated getters and setters
    public String getSize() {
        return size;
    }
    
    public void setSize(String size) {
        if (size == null || size.trim().isEmpty()) {
            throw new IllegalArgumentException("Size cannot be null or empty");
        }
        this.size = size;
    }
    
    public String getMaterial() {
        return material;
    }
    
    public void setMaterial(String material) {
        if (material == null || material.trim().isEmpty()) {
            throw new IllegalArgumentException("Material cannot be null or empty");
        }
        this.material = material;
    }
    
    @Override
    public void displayProductDetails() {
        super.displayProductDetails();
        System.out.println("Size: " + size);
        System.out.println("Material: " + material);
        System.out.println("======================");
    }
}

// Groceries product class - no tax applicable
class Groceries extends Product {
    private LocalDate expiryDate;
    private boolean isOrganic;
    private static final double BULK_DISCOUNT_RATE = 0.10; // 10% bulk discount
    private static final double EXPIRY_DISCOUNT_RATE = 0.20; // 20% discount for near-expiry items
    
    public Groceries(String productId, String name, double price, int stockQuantity, LocalDate expiryDate, boolean isOrganic) {
        super(productId, name, price, stockQuantity, "Groceries");
        setExpiryDate(expiryDate);
        this.isOrganic = isOrganic;
    }
    
    @Override
    public double calculateDiscount() {
        double discount = 0;
        
        // Bulk discount for high stock quantities
        if (getStockQuantity() > 50) {
            discount += getPrice() * BULK_DISCOUNT_RATE;
        }
        
        // Near expiry discount
        if (isNearExpiry()) {
            discount += getPrice() * EXPIRY_DISCOUNT_RATE;
        }
        
        return discount;
    }
    
    // Method to check if product is near expiry
    public boolean isNearExpiry() {
        if (expiryDate == null) return false;
        return expiryDate.isBefore(LocalDate.now().plusDays(7)); // Within 7 days
    }
    
    // Method to check if product is expired
    public boolean isExpired() {
        if (expiryDate == null) return false;
        return expiryDate.isBefore(LocalDate.now());
    }
    
    // Encapsulated getters and setters
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    
    public void setExpiryDate(LocalDate expiryDate) {
        if (expiryDate != null && expiryDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiry date cannot be in the past");
        }
        this.expiryDate = expiryDate;
    }
    
    public boolean isOrganic() {
        return isOrganic;
    }
    
    public void setOrganic(boolean organic) {
        this.isOrganic = organic;
    }
    
    @Override
    public void displayProductDetails() {
        super.displayProductDetails();
        System.out.println("Expiry Date: " + (expiryDate != null ? expiryDate : "No Expiry"));
        System.out.println("Organic: " + (isOrganic ? "Yes" : "No"));
        System.out.println("Near Expiry: " + (isNearExpiry() ? "Yes" : "No"));
        System.out.println("Expired: " + (isExpired() ? "Yes" : "No"));
        System.out.println("======================");
    }
}

// E-Commerce Platform main class
public class ECommercePlatform {
    private List<Product> products;
    private static int productCounter = 1;
    
    public ECommercePlatform() {
        this.products = new ArrayList<>();
    }
    
    // Method to add product (demonstrates polymorphism)
    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        products.add(product);
        System.out.println("Product added successfully: " + product.getName());
    }
    
    // Method to display all products (demonstrates polymorphism)
    public void displayAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }
        
        System.out.println("\n=== ALL PRODUCTS ===");
        for (Product product : products) {
            product.displayProductDetails(); // Polymorphic call
            System.out.println();
        }
    }
    
    // Method to calculate and display final prices (demonstrates polymorphism)
    public void calculateAndDisplayFinalPrices() {
        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }
        
        System.out.println("\n=== FINAL PRICE CALCULATION ===");
        double totalValue = 0;
        
        for (Product product : products) {
            double originalPrice = product.getPrice();
            double discount = product.calculateDiscount(); // Polymorphic call
            double tax = 0;
            
            if (product instanceof Taxable) {
                tax = ((Taxable) product).calculateTax(); // Interface method call
            }
            
            double finalPrice = product.calculateFinalPrice(); // Polymorphic call
            totalValue += finalPrice;
            
            System.out.printf("%s (%s):%n", product.getName(), product.getClass().getSimpleName());
            System.out.printf("  Original: $%.2f | Discount: -$%.2f | Tax: +$%.2f | Final: $%.2f%n", 
                originalPrice, discount, tax, finalPrice);
        }
        
        System.out.printf("Total Catalog Value: $%.2f%n", totalValue);
        System.out.println("================================");
    }
    
    // Method to find product by ID
    public Product findProductById(String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }
    
    // Method to get products by category
    public List<Product> getProductsByCategory(String category) {
        List<Product> categoryProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                categoryProducts.add(product);
            }
        }
        return categoryProducts;
    }
    
    // Method to get taxable products
    public List<Product> getTaxableProducts() {
        List<Product> taxableProducts = new ArrayList<>();
        for (Product product : products) {
            if (product instanceof Taxable) {
                taxableProducts.add(product);
            }
        }
        return taxableProducts;
    }
    
    // Method to simulate purchase
    public boolean purchaseProduct(String productId, int quantity) {
        Product product = findProductById(productId);
        if (product == null) {
            System.out.println("Product not found: " + productId);
            return false;
        }
        
        if (product.purchaseProduct(quantity)) {
            double totalCost = product.calculateFinalPrice() * quantity;
            System.out.printf("Purchase successful: %d x %s = $%.2f%n", 
                quantity, product.getName(), totalCost);
            return true;
        } else {
            System.out.println("Insufficient stock for product: " + product.getName());
            return false;
        }
    }
    
    // Utility method to generate product ID
    public static String generateProductId(String categoryPrefix) {
        return categoryPrefix + String.format("%04d", productCounter++);
    }
    
    // Main method demonstrating all concepts
    public static void main(String[] args) {
        System.out.println("=== E-Commerce Platform Demo ===\n");
        
        ECommercePlatform platform = new ECommercePlatform();
        
        try {
            // Create different types of products (Polymorphism)
            System.out.println("1. Creating Products (Demonstrating Encapsulation & Inheritance):");
            
            Electronics laptop = new Electronics(
                generateProductId("ELE"), 
                "Gaming Laptop", 
                1200.0, 
                15, 
                "TechBrand", 
                24
            );
            
            Electronics phone = new Electronics(
                generateProductId("ELE"), 
                "Smartphone", 
                800.0, 
                30, 
                "PhoneCorp", 
                12
            );
            
            Clothing tshirt = new Clothing(
                generateProductId("CLO"), 
                "Cotton T-Shirt", 
                25.0, 
                100, 
                "L", 
                "Cotton"
            );
            
            Clothing jeans = new Clothing(
                generateProductId("CLO"), 
                "Denim Jeans", 
                80.0, 
                50, 
                "32", 
                "Denim"
            );
            
            Groceries apple = new Groceries(
                generateProductId("GRO"), 
                "Organic Apples", 
                5.0, 
                200, 
                LocalDate.now().plusDays(10), 
                true
            );
            
            Groceries milk = new Groceries(
                generateProductId("GRO"), 
                "Fresh Milk", 
                3.0, 
                80, 
                LocalDate.now().plusDays(5), // Near expiry for discount demo
                false
            );
            
            // Add products to platform
            platform.addProduct(laptop);
            platform.addProduct(phone);
            platform.addProduct(tshirt);
            platform.addProduct(jeans);
            platform.addProduct(apple);
            platform.addProduct(milk);
            
            System.out.println("\n2. Displaying All Products (Demonstrating Polymorphism):");
            platform.displayAllProducts();
            
            System.out.println("\n3. Final Price Calculation (Polymorphic Method Calls & Interface Usage):");
            platform.calculateAndDisplayFinalPrices();
            
            System.out.println("\n4. Testing Encapsulation (Getters/Setters with Validation):");
            
            // Test valid updates
            System.out.println("Testing valid updates:");
            laptop.setPrice(1100.0);
            tshirt.setStockQuantity(120);
            milk.setOrganic(true);
            System.out.println("✓ Valid updates successful");
            
            // Test invalid updates (will throw exceptions)
            System.out.println("\nTesting invalid updates (Exception handling):");
            try {
                phone.setPrice(-100); // Should throw exception
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Caught expected exception: " + e.getMessage());
            }
            
            try {
                apple.setExpiryDate(LocalDate.now().minusDays(1)); // Should throw exception
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Caught expected exception: " + e.getMessage());
            }
            
            System.out.println("\n5. Product Search and Filtering:");
            
            // Find product by ID
            Product foundProduct = platform.findProductById("ELE0001");
            if (foundProduct != null) {
                System.out.println("Found product:");
                foundProduct.displayProductDetails();
            }
            
            // Get products by category
            List<Product> electronics = platform.getProductsByCategory("Electronics");
            System.out.println("Electronics products: " + electronics.size());
            
            // Get taxable products
            List<Product> taxableProducts = platform.getTaxableProducts();
            System.out.println("Taxable products: " + taxableProducts.size());
            
            System.out.println("\n6. Purchase Simulation:");
            platform.purchaseProduct("ELE0001", 2); // Buy 2 laptops
            platform.purchaseProduct("GRO0001", 10); // Buy 10 kg apples
            platform.purchaseProduct("CLO0001", 150); // Try to buy more than available
            
            System.out.println("\n7. Updated Product Details After Purchase:");
            foundProduct = platform.findProductById("ELE0001");
            if (foundProduct != null) {
                foundProduct.displayProductDetails();
            }
            
            System.out.println("\n8. Interface Demonstration (Taxable Products):");
            System.out.println("=== TAX BREAKDOWN ===");
            for (Product product : platform.getTaxableProducts()) {
                if (product instanceof Taxable) {
                    Taxable taxableProduct = (Taxable) product;
                    System.out.printf("%s: %s%n", product.getName(), taxableProduct.getTaxDetails());
                }
            }
            System.out.println("=====================");
            
            System.out.println("\n=== Concepts Demonstrated ===");
            System.out.println("✓ Abstract Classes: Product class with abstract calculateDiscount()");
            System.out.println("✓ Inheritance: Electronics, Clothing, Groceries extend Product");
            System.out.println("✓ Polymorphism: Product references calling overridden methods");
            System.out.println("✓ Interface: Taxable interface with calculateTax() and getTaxDetails()");
            System.out.println("✓ Encapsulation: Private fields with validated getters/setters");
            System.out.println("✓ Final Price Calculation: price + tax - discount using polymorphism");
            System.out.println("✓ Data Validation: Input validation preventing invalid product data");
            System.out.println("✓ Exception Handling: Proper error handling for invalid inputs");
            
        } catch (Exception e) {
            System.err.println("Error in E-Commerce Platform: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
