public class CartItem {
    // Attributes
    private String itemName;
    private double price;
    private int quantity;
    
    // Constructor
    public CartItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
    
    // Constructor with default quantity
    public CartItem(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = 1;
    }
    
    // Method to add items to the cart (increase quantity)
    public void addItem(int additionalQuantity) {
        if (additionalQuantity > 0) {
            this.quantity += additionalQuantity;
            System.out.println("=== Item Added ===");
            System.out.println("Item: " + itemName);
            System.out.println("Added Quantity: " + additionalQuantity);
            System.out.println("New Quantity: " + quantity);
            System.out.println("Total Cost: $" + calculateTotalCost());
            System.out.println("==================");
        } else {
            System.out.println("Invalid quantity. Please enter a positive number.");
        }
    }
    
    // Method to remove items from the cart (decrease quantity)
    public boolean removeItem(int removeQuantity) {
        if (removeQuantity > 0) {
            if (removeQuantity <= quantity) {
                this.quantity -= removeQuantity;
                System.out.println("=== Item Removed ===");
                System.out.println("Item: " + itemName);
                System.out.println("Removed Quantity: " + removeQuantity);
                System.out.println("Remaining Quantity: " + quantity);
                if (quantity > 0) {
                    System.out.println("Total Cost: $" + calculateTotalCost());
                } else {
                    System.out.println("Item removed from cart completely.");
                }
                System.out.println("====================");
                return true;
            } else {
                System.out.println("=== Removal Failed ===");
                System.out.println("Cannot remove " + removeQuantity + " items.");
                System.out.println("Only " + quantity + " items available in cart.");
                System.out.println("======================");
                return false;
            }
        } else {
            System.out.println("Invalid quantity. Please enter a positive number.");
            return false;
        }
    }
    
    // Method to calculate total cost
    public double calculateTotalCost() {
        return price * quantity;
    }
    
    // Method to display the total cost
    public void displayTotalCost() {
        double totalCost = calculateTotalCost();
        System.out.println("=== Total Cost ===");
        System.out.println("Item: " + itemName);
        System.out.println("Price per unit: $" + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("Total Cost: $" + totalCost);
        System.out.println("==================");
    }
    
    // Method to display cart item details
    public void displayCartItemDetails() {
        System.out.println("=== Cart Item Details ===");
        System.out.println("Item Name: " + itemName);
        System.out.println("Unit Price: $" + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("Total Cost: $" + calculateTotalCost());
        System.out.println("=========================");
    }
    
    // Method to update quantity directly
    public void setQuantity(int newQuantity) {
        if (newQuantity >= 0) {
            this.quantity = newQuantity;
            System.out.println("Quantity updated to: " + quantity);
        } else {
            System.out.println("Invalid quantity. Quantity cannot be negative.");
        }
    }
    
    // Method to check if cart item is empty
    public boolean isEmpty() {
        return quantity == 0;
    }
    
    // Method to clear the cart item
    public void clearItem() {
        this.quantity = 0;
        System.out.println("Cart item '" + itemName + "' has been cleared.");
    }
    
    // Getters and Setters
    public String getItemName() {
        return itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("Invalid price. Price cannot be negative.");
        }
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    // Main method to test the shopping cart functionality
    public static void main(String[] args) {
        // Create cart item objects
        CartItem item1 = new CartItem("Laptop", 999.99, 1);
        CartItem item2 = new CartItem("Mouse", 25.50);
        CartItem item3 = new CartItem("Keyboard", 75.00, 2);
        CartItem item4 = new CartItem("Monitor", 299.99, 1);
        
        // Display initial cart items
        System.out.println("=== Initial Cart Items ===");
        item1.displayCartItemDetails();
        item2.displayCartItemDetails();
        item3.displayCartItemDetails();
        item4.displayCartItemDetails();
        
        // Test adding items
        System.out.println("\\n=== Adding Items to Cart ===");
        item1.addItem(1); // Add 1 more laptop
        item2.addItem(3); // Add 3 more mice
        item3.addItem(1); // Add 1 more keyboard
        
        // Test removing items
        System.out.println("\\n=== Removing Items from Cart ===");
        item1.removeItem(1); // Remove 1 laptop
        item2.removeItem(2); // Remove 2 mice
        item3.removeItem(5); // Try to remove more than available (should fail)
        
        // Display updated cart
        System.out.println("\\n=== Updated Cart ===");
        item1.displayTotalCost();
        item2.displayTotalCost();
        item3.displayTotalCost();
        item4.displayTotalCost();
        
        // Calculate total cart value
        System.out.println("\\n=== Cart Summary ===");
        double totalCartValue = item1.calculateTotalCost() + 
                               item2.calculateTotalCost() + 
                               item3.calculateTotalCost() + 
                               item4.calculateTotalCost();
        System.out.println("Total Cart Value: $" + totalCartValue);
        
        // Test clearing an item
        System.out.println("\\n=== Clearing Item ===");
        item4.clearItem();
        item4.displayCartItemDetails();
    }
}
