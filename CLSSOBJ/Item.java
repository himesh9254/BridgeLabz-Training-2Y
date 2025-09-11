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
        System.out.println("Item Details:");
        System.out.println("Item Code: " + itemCode);
        System.out.println("Item Name: " + itemName);
        System.out.println("Price: $" + price);
        System.out.println("-------------------");
    }
    
    // Method to calculate total cost for a given quantity
    public double calculateTotalCost(int quantity) {
        return price * quantity;
    }
    
    // Method to display total cost information
    public void displayTotalCost(int quantity) {
        double totalCost = calculateTotalCost(quantity);
        System.out.println("Total Cost Calculation:");
        System.out.println("Item: " + itemName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Unit Price: $" + price);
        System.out.println("Total Cost: $" + totalCost);
        System.out.println("-------------------");
    }
    
    // Main method to test the class
    public static void main(String[] args) {
        // Creating Item objects
        Item item1 = new Item("A001", "Laptop", 799.99);
        Item item2 = new Item("B002", "Mouse", 25.50);
        
        // Displaying item details
        item1.displayItemDetails();
        item2.displayItemDetails();
        
        // Calculating and displaying total cost
        item1.displayTotalCost(3);
        item2.displayTotalCost(10);
    }
}
