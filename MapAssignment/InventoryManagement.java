import java.util.*;

public class InventoryManagement {
    private Map<String, Integer> inventory;
    
    public InventoryManagement() {
        inventory = new HashMap<>();
    }
    
    public void addProduct(String productName, int quantity) {
        if (quantity < 0) {
            System.out.println("Quantity cannot be negative.");
            return;
        }
        inventory.put(productName, quantity);
        System.out.println("Added: " + productName + " with quantity " + quantity);
    }
    
    public void sellProduct(String productName, int quantity) {
        if (!inventory.containsKey(productName)) {
            System.out.println("Product not stocked: " + productName);
            return;
        }
        
        int currentStock = inventory.get(productName);
        if (quantity > currentStock) {
            System.out.println("Insufficient stock for " + productName + ". Available: " + currentStock);
            return;
        }
        
        int newStock = currentStock - quantity;
        if (newStock == 0) {
            inventory.put(productName, 0);
            System.out.println("Sold " + quantity + " " + productName + ". Product is now OUT OF STOCK!");
        } else {
            inventory.put(productName, newStock);
            System.out.println("Sold " + quantity + " " + productName + ". Remaining: " + newStock);
        }
    }
    
    public void restockProduct(String productName, int quantity) {
        if (quantity < 0) {
            System.out.println("Quantity cannot be negative.");
            return;
        }
        
        int currentStock = inventory.getOrDefault(productName, 0);
        inventory.put(productName, currentStock + quantity);
        System.out.println("Restocked " + productName + ": +" + quantity + ". New total: " + (currentStock + quantity));
    }
    
    public void queryProduct(String productName) {
        if (!inventory.containsKey(productName)) {
            System.out.println(productName + ": Not stocked");
            return;
        }
        
        int stock = inventory.get(productName);
        if (stock == 0) {
            System.out.println(productName + ": OUT OF STOCK");
        } else {
            System.out.println(productName + ": " + stock + " units available");
        }
    }
    
    public void displayOutOfStock() {
        System.out.println("\n=== Out of Stock Products ===");
        boolean found = false;
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            if (entry.getValue() == 0) {
                System.out.println("- " + entry.getKey());
                found = true;
            }
        }
        if (!found) {
            System.out.println("All products are in stock!");
        }
    }
    
    public void displayAllProducts() {
        System.out.println("\n=== Current Inventory ===");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            String status = entry.getValue() == 0 ? " [OUT OF STOCK]" : "";
            System.out.println(entry.getKey() + ": " + entry.getValue() + status);
        }
    }
    
    public void displayLowStock(int threshold) {
        System.out.println("\n=== Low Stock Products (Below " + threshold + " units) ===");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            if (entry.getValue() > 0 && entry.getValue() < threshold) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " units");
            }
        }
    }
    
    public static void main(String[] args) {
        InventoryManagement store = new InventoryManagement();
        
        System.out.println("=== Adding Products ===");
        store.addProduct("Laptop", 50);
        store.addProduct("Mouse", 100);
        store.addProduct("Keyboard", 75);
        store.addProduct("Monitor", 30);
        store.addProduct("Headphones", 5);
        
        store.displayAllProducts();
        
        System.out.println("\n=== Customer Purchases ===");
        store.sellProduct("Laptop", 10);
        store.sellProduct("Mouse", 100);
        store.sellProduct("Keyboard", 20);
        store.sellProduct("Headphones", 5);
        store.sellProduct("Webcam", 5);
        store.sellProduct("Monitor", 50);
        
        store.displayAllProducts();
        store.displayOutOfStock();
        
        System.out.println("\n=== New Shipment Arrives ===");
        store.restockProduct("Mouse", 150);
        store.restockProduct("Headphones", 50);
        store.restockProduct("Webcam", 30);
        
        store.displayAllProducts();
        
        System.out.println("\n=== Query Products ===");
        store.queryProduct("Laptop");
        store.queryProduct("Mouse");
        store.queryProduct("Monitor");
        store.queryProduct("Tablet");
        
        store.displayLowStock(40);
    }
}
