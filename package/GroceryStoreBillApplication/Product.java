package GroceryStoreBillApplication;

/**
 * Product class represents an item in the grocery store
 * Part of Grocery Store Bill Generation Application
 */
public class Product {
    private String productName;
    private double quantity;
    private double pricePerUnit;
    private String category;
    
    /**
     * Constructor to create a Product
     * @param productName Name of the product
     * @param quantity Quantity purchased
     * @param pricePerUnit Price per unit
     * @param category Product category
     */
    public Product(String productName, double quantity, double pricePerUnit, String category) {
        this.productName = productName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.category = category;
    }
    
    /**
     * Calculate total price for this product
     * @return Total price (quantity × price per unit)
     */
    public double getTotalPrice() {
        return quantity * pricePerUnit;
    }
    
    /**
     * Display product information
     */
    public void displayInfo() {
        System.out.printf("%-20s | Qty: %-8.2f | Price: $%-8.2f | Total: $%-8.2f | Category: %s%n",
                          productName, quantity, pricePerUnit, getTotalPrice(), category);
    }
    
    /**
     * Display product info for receipt
     */
    public void displayReceiptLine() {
        System.out.printf("%-20s  %6.2f × $%-6.2f = $%-8.2f%n",
                          productName, quantity, pricePerUnit, getTotalPrice());
    }
    
    // Getters
    public String getProductName() {
        return productName;
    }
    
    public double getQuantity() {
        return quantity;
    }
    
    public double getPricePerUnit() {
        return pricePerUnit;
    }
    
    public String getCategory() {
        return category;
    }
    
    // Setters
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    
    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
