/**
 * Online Food Delivery System demonstrating:
 * Abstract classes, interfaces, encapsulation, inheritance, polymorphism
 */

import java.util.*;

interface Discountable {
    double applyDiscount();
    String getDiscountDetails();
}

abstract class FoodItem {
    private String itemName;
    private double price;
    private int quantity;
    private String category;
    private boolean isAvailable;
    
    public FoodItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.isAvailable = true;
    }
    
    public abstract double calculateTotalPrice();
    
    public void getItemDetails() {
        System.out.println("Item: " + itemName + ", Price: $" + price + 
                         ", Qty: " + quantity + ", Total: $" + calculateTotalPrice());
    }
    
    // Getters and Setters
    public String getItemName() { return itemName; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public boolean isAvailable() { return isAvailable; }
    
    public void setQuantity(int quantity) {
        if (quantity > 0) this.quantity = quantity;
    }
}

class VegItem extends FoodItem implements Discountable {
    private boolean isOrganic;
    
    public VegItem(String itemName, double price, int quantity, boolean isOrganic) {
        super(itemName, price, quantity);
        this.isOrganic = isOrganic;
    }
    
    @Override
    public double calculateTotalPrice() {
        double basePrice = getPrice() * getQuantity();
        double discount = applyDiscount();
        return basePrice - discount;
    }
    
    @Override
    public double applyDiscount() {
        return isOrganic ? getPrice() * getQuantity() * 0.10 : 0; // 10% organic discount
    }
    
    @Override
    public String getDiscountDetails() {
        return isOrganic ? "10% Organic Vegetarian Discount Applied" : "No Discount";
    }
    
    public boolean isOrganic() { return isOrganic; }
}

class NonVegItem extends FoodItem implements Discountable {
    private String meatType;
    private static final double NON_VEG_SURCHARGE = 2.0;
    
    public NonVegItem(String itemName, double price, int quantity, String meatType) {
        super(itemName, price, quantity);
        this.meatType = meatType;
    }
    
    @Override
    public double calculateTotalPrice() {
        double basePrice = (getPrice() + NON_VEG_SURCHARGE) * getQuantity();
        double discount = applyDiscount();
        return basePrice - discount;
    }
    
    @Override
    public double applyDiscount() {
        return getQuantity() > 3 ? getPrice() * 0.05 * getQuantity() : 0; // 5% bulk discount
    }
    
    @Override
    public String getDiscountDetails() {
        return getQuantity() > 3 ? "5% Bulk Non-Veg Discount Applied" : "No Discount";
    }
    
    public String getMeatType() { return meatType; }
}

public class OnlineFoodDeliverySystem {
    private List<FoodItem> orderItems;
    private String customerName;
    private double deliveryFee = 5.0;
    
    public OnlineFoodDeliverySystem(String customerName) {
        this.customerName = customerName;
        this.orderItems = new ArrayList<>();
    }
    
    public void addItem(FoodItem item) {
        orderItems.add(item);
        System.out.println("Added: " + item.getItemName());
    }
    
    public void displayOrder() {
        System.out.println("\n=== ORDER FOR " + customerName + " ===");
        double total = 0;
        for (FoodItem item : orderItems) {
            item.getItemDetails();
            total += item.calculateTotalPrice();
            if (item instanceof Discountable) {
                System.out.println("  " + ((Discountable) item).getDiscountDetails());
            }
        }
        System.out.println("Subtotal: $" + total);
        System.out.println("Delivery: $" + deliveryFee);
        System.out.println("TOTAL: $" + (total + deliveryFee));
        System.out.println("=============================");
    }
    
    public static void main(String[] args) {
        System.out.println("=== Food Delivery System Demo ===\n");
        
        OnlineFoodDeliverySystem order = new OnlineFoodDeliverySystem("John Doe");
        
        VegItem salad = new VegItem("Caesar Salad", 12.0, 2, true);
        NonVegItem chicken = new NonVegItem("Grilled Chicken", 18.0, 4, "Chicken");
        VegItem pizza = new VegItem("Margherita Pizza", 15.0, 1, false);
        
        order.addItem(salad);
        order.addItem(chicken);
        order.addItem(pizza);
        
        order.displayOrder();
        
        System.out.println("\n=== Concepts Demonstrated ===");
        System.out.println("✓ Abstract Classes: FoodItem with abstract calculateTotalPrice()");
        System.out.println("✓ Inheritance: VegItem and NonVegItem extend FoodItem");
        System.out.println("✓ Polymorphism: Different price calculations per item type");
        System.out.println("✓ Interface: Discountable with different discount strategies");
        System.out.println("✓ Encapsulation: Protected order details and pricing logic");
    }
}
