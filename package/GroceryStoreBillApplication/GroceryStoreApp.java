package GroceryStoreBillApplication;

/**
 * Main Application for Grocery Store Bill Generation System
 * Demonstrates Class Diagram, Object Diagram, and Sequence Diagram concepts
 * Shows Composition relationship - Cart items are owned by Customer's purchase
 */
public class GroceryStoreApp {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║       GROCERY STORE BILL GENERATION SYSTEM                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        // Create BillGenerator (Service object)
        BillGenerator billGenerator = new BillGenerator(); // 8% tax rate
        
        // ===== Scenario 1: Customer Alice =====
        System.out.println("===== Customer Alice's Shopping =====\n");
        
        // Create Customer
        Customer alice = new Customer("C001", "Alice", "123-456-7890");
        
        // Alice adds products to cart (Composition - products belong to this purchase)
        System.out.println("Alice is shopping...");
        alice.addToCart(new Product("Apples", 2.0, 3.0, "Fruits"));
        alice.addToCart(new Product("Milk", 1.0, 2.0, "Dairy"));
        alice.addToCart(new Product("Bread", 2.0, 1.5, "Bakery"));
        alice.addToCart(new Product("Eggs", 1.0, 4.0, "Dairy"));
        alice.addToCart(new Product("Bananas", 1.5, 2.0, "Fruits"));
        
        // Display cart
        alice.displayCart();
        
        // Checkout - Generate bill (Sequence diagram demonstration)
        System.out.println("\nAlice proceeds to checkout...");
        billGenerator.generateBill(alice);
        
        // Clear cart after checkout (Composition - cart items destroyed)
        alice.clearCart();
        
        // ===== Scenario 2: Customer Bob =====
        System.out.println("\n\n===== Customer Bob's Shopping =====\n");
        
        Customer bob = new Customer("C002", "Bob", "987-654-3210");
        
        System.out.println("Bob is shopping...");
        bob.addToCart(new Product("Rice", 5.0, 2.5, "Grains"));
        bob.addToCart(new Product("Chicken", 2.0, 8.0, "Meat"));
        bob.addToCart(new Product("Tomatoes", 1.0, 3.0, "Vegetables"));
        bob.addToCart(new Product("Cheese", 0.5, 10.0, "Dairy"));
        
        bob.displayCart();
        
        // Apply discount for Bob
        System.out.println("\nBob has a loyalty card!");
        billGenerator.applyDiscount(0.10); // 10% discount
        
        System.out.println("Bob proceeds to checkout...");
        billGenerator.generateBill(bob);
        
        // Reset discount
        billGenerator.applyDiscount(0.0);
        
        // ===== Scenario 3: Customer Carol with large order =====
        System.out.println("\n\n===== Customer Carol's Shopping =====\n");
        
        Customer carol = new Customer("C003", "Carol", "555-123-4567");
        
        System.out.println("Carol is shopping for a party...");
        carol.addToCart(new Product("Potato Chips", 10.0, 2.0, "Snacks"));
        carol.addToCart(new Product("Soda", 12.0, 1.5, "Beverages"));
        carol.addToCart(new Product("Pizza", 3.0, 12.0, "Frozen"));
        carol.addToCart(new Product("Ice Cream", 4.0, 5.0, "Frozen"));
        carol.addToCart(new Product("Cookies", 5.0, 3.0, "Snacks"));
        carol.addToCart(new Product("Orange Juice", 2.0, 4.0, "Beverages"));
        
        carol.displayCart();
        
        // Large order discount
        System.out.println("\nCarol qualifies for bulk purchase discount!");
        billGenerator.applyDiscount(0.15); // 15% discount
        
        System.out.println("Carol proceeds to checkout...");
        billGenerator.generateBill(carol);
        
        billGenerator.applyDiscount(0.0); // Reset
        
        // ===== Scenario 4: Demonstrating cart modifications =====
        System.out.println("\n\n===== Customer David's Shopping with Changes =====\n");
        
        Customer david = new Customer("C004", "David", "444-555-6666");
        
        System.out.println("David is shopping...");
        Product cereal = new Product("Cereal", 2.0, 4.5, "Breakfast");
        Product coffee = new Product("Coffee", 1.0, 8.0, "Beverages");
        Product butter = new Product("Butter", 1.0, 3.5, "Dairy");
        
        david.addToCart(cereal);
        david.addToCart(coffee);
        david.addToCart(butter);
        
        david.displayCart();
        
        // David changes his mind
        System.out.println("\nDavid decides he doesn't need cereal...");
        david.removeFromCart(cereal);
        
        david.displayCart();
        
        System.out.println("\nDavid proceeds to checkout...");
        billGenerator.generateBill(david);
        
        // ===== Demonstrate Composition =====
        System.out.println("\n\n===== Demonstrating Composition =====");
        System.out.println("In composition, cart items are tightly bound to the customer's purchase.");
        System.out.println("When a customer checks out and cart is cleared, the items cease to exist");
        System.out.println("as part of that shopping session (unlike aggregation where objects persist).\n");
        
        Customer temp = new Customer("C005", "Temp Customer", "000-000-0000");
        temp.addToCart(new Product("Test Item", 1.0, 5.0, "Test"));
        System.out.println("Cart size before clearing: " + temp.getCartSize());
        temp.clearCart();
        System.out.println("Cart size after clearing: " + temp.getCartSize());
        System.out.println("Items are gone - they were composed within the customer's shopping session.");
        
        // ===== Compare Bills =====
        System.out.println("\n\n===== Bill Comparison =====");
        
        // Recreate carts for comparison
        alice.addToCart(new Product("Apples", 2.0, 3.0, "Fruits"));
        alice.addToCart(new Product("Milk", 1.0, 2.0, "Dairy"));
        
        bob.addToCart(new Product("Rice", 5.0, 2.5, "Grains"));
        bob.addToCart(new Product("Chicken", 2.0, 8.0, "Meat"));
        
        billGenerator.compareBills(alice, bob);
        
        // ===== Summary Statistics =====
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                    SUMMARY STATISTICS                      ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        System.out.println("\nDaily Transactions Summary:");
        System.out.println("Total Customers Served: 4");
        System.out.println("\nCustomer Spending:");
        
        // Calculate and display individual totals
        double aliceTotal = billGenerator.calculateTotal(alice);
        double bobTotal = billGenerator.calculateTotal(bob);
        
        System.out.printf("1. Alice: $%.2f (%d items)%n", aliceTotal, alice.getCartSize());
        System.out.printf("2. Bob: $%.2f (%d items)%n", bobTotal, bob.getCartSize());
        
        System.out.println("\nTax Rate: " + (billGenerator.getTaxRate() * 100) + "%");
        
        System.out.println("\n═══════════════════════════════════════════════════════════════");
        System.out.println("       Thank you for using Grocery Store Bill System!");
        System.out.println("═══════════════════════════════════════════════════════════════\n");
    }
}
