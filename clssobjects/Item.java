public class Item {
    // Attributes
    private String itemCode;
    private String itemName;
    private double price;
    
    // Constructor
    public Item(String itemCode, String itemName, double price) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.price = price;
    }
    
    // Method to display item details
    public void displayItemDetails() {
        System.out.println("=== Item Details ===");
        System.out.println("Item Code: " + itemCode);
        System.out.println("Item Name: " + itemName);
        System.out.println("Price: $" + price);
        System.out.println("===================");
    }
    
    // Method to calculate total cost for given quantity
    public double calculateTotalCost(int quantity) {
        return price * quantity;
    }
    
    // Method to display total cost for given quantity
    public void displayTotalCost(int quantity) {
        double totalCost = calculateTotalCost(quantity);
        System.out.println("=== Total Cost Calculation ===");
        System.out.println("Item: " + itemName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price per unit: $" + price);
        System.out.println("Total Cost: $" + totalCost);
        System.out.println("==============================");
    }
    
    // Getters and Setters
    public String getItemCode() {
        return itemCode;
    }
    
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
    
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
        this.price = price;
    }
    
    // Main method to test the class
    public static void main(String[] args) {
        // Create item objects
        Item item1 = new Item("ITM001", "Laptop", 999.99);
        Item item2 = new Item("ITM002", "Mouse", 25.50);
        Item item3 = new Item("ITM003", "Keyboard", 75.00);
        
        // Display item details
        item1.displayItemDetails();
        item2.displayItemDetails();
        item3.displayItemDetails();
        
        // Calculate and display total cost for different quantities
        item1.displayTotalCost(2);
        item2.displayTotalCost(5);
        item3.displayTotalCost(3);
    }
}
