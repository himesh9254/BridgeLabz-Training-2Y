import java.util.*;

public class OnlineShoppingCart {
    private Map<String, Double> productPrices;
    private Map<String, Integer> cart;
    
    public OnlineShoppingCart() {
        productPrices = new HashMap<>();
        cart = new LinkedHashMap<>();
        initializeProducts();
    }
    
    private void initializeProducts() {
        productPrices.put("Laptop", 999.99);
        productPrices.put("Mouse", 29.99);
        productPrices.put("Keyboard", 79.99);
        productPrices.put("Monitor", 299.99);
        productPrices.put("Headphones", 149.99);
        productPrices.put("USB Cable", 9.99);
        productPrices.put("Webcam", 89.99);
        productPrices.put("Speaker", 199.99);
    }
    
    public void displayAvailableProducts() {
        System.out.println("\n=== Available Products ===");
        TreeMap<String, Double> sorted = new TreeMap<>(productPrices);
        for (Map.Entry<String, Double> entry : sorted.entrySet()) {
            System.out.printf("%s: $%.2f%n", entry.getKey(), entry.getValue());
        }
    }
    
    public void addToCart(String productName, int quantity) {
        if (!productPrices.containsKey(productName)) {
            System.out.println("Product not found: " + productName);
            return;
        }
        if (quantity <= 0) {
            System.out.println("Invalid quantity");
            return;
        }
        
        int currentQty = cart.getOrDefault(productName, 0);
        cart.put(productName, currentQty + quantity);
        System.out.printf("Added %d x %s to cart%n", quantity, productName);
    }
    
    public void removeFromCart(String productName) {
        if (cart.remove(productName) != null) {
            System.out.println("Removed " + productName + " from cart");
        } else {
            System.out.println("Product not in cart: " + productName);
        }
    }
    
    public void updateQuantity(String productName, int newQuantity) {
        if (!cart.containsKey(productName)) {
            System.out.println("Product not in cart: " + productName);
            return;
        }
        if (newQuantity <= 0) {
            removeFromCart(productName);
            return;
        }
        cart.put(productName, newQuantity);
        System.out.printf("Updated %s quantity to %d%n", productName, newQuantity);
    }
    
    public void displayCart() {
        System.out.println("\n=== Shopping Cart (Insertion Order) ===");
        if (cart.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }
        
        double subtotal = 0;
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String product = entry.getKey();
            int quantity = entry.getValue();
            double price = productPrices.get(product);
            double itemTotal = price * quantity;
            subtotal += itemTotal;
            
            System.out.printf("%s x %d @ $%.2f = $%.2f%n", 
                product, quantity, price, itemTotal);
        }
        System.out.printf("Subtotal: $%.2f%n", subtotal);
    }
    
    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            total += productPrices.get(entry.getKey()) * entry.getValue();
        }
        return total;
    }
    
    public void applyDiscount(double discountPercentage) {
        double subtotal = calculateTotal();
        double discount = subtotal * (discountPercentage / 100.0);
        double finalTotal = subtotal - discount;
        
        System.out.println("\n=== Order Summary ===");
        System.out.printf("Subtotal: $%.2f%n", subtotal);
        System.out.printf("Discount (%.1f%%): -$%.2f%n", discountPercentage, discount);
        System.out.printf("Final Total: $%.2f%n", finalTotal);
    }
    
    public void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Cannot checkout: Cart is empty");
            return;
        }
        
        System.out.println("\n========== CHECKOUT ==========");
        displayCart();
        
        double total = calculateTotal();
        double tax = total * 0.08;
        double grandTotal = total + tax;
        
        System.out.println("\n--- Final Bill ---");
        System.out.printf("Subtotal: $%.2f%n", total);
        System.out.printf("Tax (8%%): $%.2f%n", tax);
        System.out.printf("Grand Total: $%.2f%n", grandTotal);
        System.out.println("================================");
        
        cart.clear();
        System.out.println("\nThank you for your purchase!");
    }
    
    public static void main(String[] args) {
        OnlineShoppingCart shop = new OnlineShoppingCart();
        
        shop.displayAvailableProducts();
        
        System.out.println("\n=== Adding Items to Cart ===");
        shop.addToCart("Laptop", 1);
        shop.addToCart("Mouse", 2);
        shop.addToCart("Keyboard", 1);
        shop.addToCart("USB Cable", 3);
        shop.addToCart("Headphones", 1);
        
        shop.displayCart();
        
        System.out.println("\n=== Modifying Cart ===");
        shop.updateQuantity("USB Cable", 5);
        shop.removeFromCart("Keyboard");
        shop.addToCart("Monitor", 1);
        
        shop.displayCart();
        
        shop.applyDiscount(10);
        
        shop.checkout();
        
        shop.displayCart();
    }
}
