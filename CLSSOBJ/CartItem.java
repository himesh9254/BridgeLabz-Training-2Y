public class CartItem {
    // Attributes
    private String itemName;
    private double price;
    private int quantity;
    
    // Constructor
    public CartItem(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = 0;  // Initially no items in cart
    }
    
    // Constructor with initial quantity
    public CartItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = Math.max(0, quantity);  // Ensure non-negative quantity
    }
    
    // Method to add items to the cart
    public void addItem(int quantityToAdd) {
        if (quantityToAdd > 0) {
            this.quantity += quantityToAdd;
            System.out.println("Added " + quantityToAdd + " " + itemName + "(s) to cart.");
            System.out.println("Total quantity: " + this.quantity);
        } else {
            System.out.println("Invalid quantity. Please enter a positive number.");
        }
        System.out.println("-------------------");
    }
    
    // Method to remove items from the cart
    public void removeItem(int quantityToRemove) {
        if (quantityToRemove > 0) {
            if (quantityToRemove <= this.quantity) {
                this.quantity -= quantityToRemove;
                System.out.println("Removed " + quantityToRemove + " " + itemName + "(s) from cart.");
                System.out.println("Remaining quantity: " + this.quantity);
            } else {
                System.out.println("Cannot remove " + quantityToRemove + " items. Only " + this.quantity + " available.");
            }
        } else {
            System.out.println("Invalid quantity. Please enter a positive number.");
        }
        System.out.println("-------------------");
    }
    
    // Method to clear all items from cart
    public void clearCart() {
        this.quantity = 0;
        System.out.println("All " + itemName + " items removed from cart.");
        System.out.println("-------------------");
    }
    
    // Method to calculate total cost for this item
    public double calculateTotalCost() {
        return price * quantity;
    }
    
    // Method to display item details and total cost
    public void displayCartItem() {
        double totalCost = calculateTotalCost();
        System.out.println("Cart Item Details:");
        System.out.println("Item Name: " + itemName);
        System.out.println("Unit Price: $" + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("Total Cost: $" + totalCost);
        System.out.println("-------------------");
    }
    
    // Method to update item price
    public void updatePrice(double newPrice) {
        if (newPrice > 0) {
            this.price = newPrice;
            System.out.println("Price updated to $" + newPrice + " for " + itemName);
        } else {
            System.out.println("Invalid price. Please enter a positive value.");
        }
    }
    
    // Getter methods
    public String getItemName() {
        return itemName;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    // Main method to test the class
    public static void main(String[] args) {
        // Creating CartItem objects
        CartItem item1 = new CartItem("Laptop", 999.99);
        CartItem item2 = new CartItem("Mouse", 25.99, 2);
        
        // Display initial state
        item1.displayCartItem();
        item2.displayCartItem();
        
        // Add items to cart
        item1.addItem(1);
        item2.addItem(3);
        
        // Display after adding
        item1.displayCartItem();
        item2.displayCartItem();
        
        // Remove some items
        item2.removeItem(2);
        item2.displayCartItem();
        
        // Try to remove more items than available
        item1.removeItem(5);
        
        // Update price and display
        item1.updatePrice(899.99);
        item1.displayCartItem();
        
        // Clear cart
        item2.clearCart();
        item2.displayCartItem();
        
        // Calculate total for multiple items
        System.out.println("Shopping Cart Summary:");
        double totalCartValue = item1.calculateTotalCost() + item2.calculateTotalCost();
        System.out.println("Total Cart Value: $" + totalCartValue);
    }
}
