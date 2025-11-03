package GroceryStoreBillApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Customer class represents a customer with a shopping cart
 * Demonstrates Composition - Customer OWNS cart items
 */
public class Customer {
    private String customerId;
    private String name;
    private String phone;
    private List<Product> cart;
    
    /**
     * Constructor to create a Customer
     * @param customerId Unique customer identifier
     * @param name Customer's name
     * @param phone Customer's phone number
     */
    public Customer(String customerId, String name, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.cart = new ArrayList<>();
    }
    
    /**
     * Add product to shopping cart
     * @param product Product to add
     */
    public void addToCart(Product product) {
        cart.add(product);
        System.out.println(product.getProductName() + " added to " + name + "'s cart");
    }
    
    /**
     * Remove product from shopping cart
     * @param product Product to remove
     */
    public void removeFromCart(Product product) {
        if (cart.remove(product)) {
            System.out.println(product.getProductName() + " removed from " + name + "'s cart");
        } else {
            System.out.println("Product not found in cart");
        }
    }
    
    /**
     * Clear all items from cart (after checkout)
     */
    public void clearCart() {
        cart.clear();
        System.out.println(name + "'s cart has been cleared");
    }
    
    /**
     * Get shopping cart
     * @return List of products in cart
     */
    public List<Product> getCart() {
        return cart;
    }
    
    /**
     * Check if cart is empty
     * @return true if cart is empty
     */
    public boolean isCartEmpty() {
        return cart.isEmpty();
    }
    
    /**
     * Get number of items in cart
     * @return Cart size
     */
    public int getCartSize() {
        return cart.size();
    }
    
    /**
     * Display customer information
     */
    public void displayInfo() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              CUSTOMER INFORMATION                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Items in Cart: " + cart.size());
    }
    
    /**
     * Display all items in cart
     */
    public void displayCart() {
        if (cart.isEmpty()) {
            System.out.println("\n" + name + "'s cart is empty");
            return;
        }
        
        System.out.println("\n--- Shopping Cart for " + name + " ---");
        System.out.println("─────────────────────────────────────────────────────────────────────────────");
        for (Product product : cart) {
            product.displayInfo();
        }
        System.out.println("─────────────────────────────────────────────────────────────────────────────");
    }
    
    // Getters
    public String getCustomerId() {
        return customerId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPhone() {
        return phone;
    }
}
