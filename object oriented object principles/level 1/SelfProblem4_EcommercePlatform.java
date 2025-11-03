import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

// Product class
class Product {
    private String productName;
    private String productId;
    private double price;
    private int quantity;
    
    public Product(String productName, String productId, double price) {
        this.productName = productName;
        this.productId = productId;
        this.price = price;
        this.quantity = 1;
    }
    
    public Product(String productName, String productId, double price, int quantity) {
        this.productName = productName;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public String getProductId() {
        return productId;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public double getTotalPrice() {
        return price * quantity;
    }
    
    @Override
    public String toString() {
        return productName + " (ID: " + productId + ") - $" + price + " x " + quantity + " = $" + getTotalPrice();
    }
}

// Order class - aggregates products
class Order {
    private String orderId;
    private Customer customer;
    private ArrayList<Product> products;
    private Date orderDate;
    private String status;
    
    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = new ArrayList<>();
        this.orderDate = new Date();
        this.status = "Pending";
    }
    
    public void addProduct(Product product) {
        products.add(product);
        System.out.println(product.getProductName() + " added to order " + orderId);
    }
    
    public double calculateTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getTotalPrice();
        }
        return total;
    }
    
    public void displayOrderDetails() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("\n--- Order Details ---");
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Order Date: " + sdf.format(orderDate));
        System.out.println("Status: " + status);
        System.out.println("Products:");
        for (Product product : products) {
            System.out.println("  - " + product);
        }
        System.out.println("Total Amount: $" + calculateTotal());
    }
    
    public void updateStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Order " + orderId + " status updated to: " + status);
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    public String getStatus() {
        return status;
    }
}

// Customer class
class Customer {
    private String name;
    private String customerId;
    private String email;
    private ArrayList<Order> orders;
    
    public Customer(String name, String customerId, String email) {
        this.name = name;
        this.customerId = customerId;
        this.email = email;
        this.orders = new ArrayList<>();
    }
    
    public Order placeOrder(String orderId) {
        Order order = new Order(orderId, this);
        orders.add(order);
        System.out.println(name + " placed order " + orderId);
        return order;
    }
    
    public void viewOrders() {
        System.out.println("\n" + name + "'s Orders:");
        for (Order order : orders) {
            System.out.println("  - Order " + order.getOrderId() + " - Status: " + order.getStatus());
        }
    }
    
    public String getName() {
        return name;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public String getEmail() {
        return email;
    }
}

// EcommercePlatform class
class EcommercePlatform {
    private String platformName;
    private ArrayList<Customer> customers;
    private ArrayList<Order> allOrders;
    private ArrayList<Product> productCatalog;
    
    public EcommercePlatform(String platformName) {
        this.platformName = platformName;
        this.customers = new ArrayList<>();
        this.allOrders = new ArrayList<>();
        this.productCatalog = new ArrayList<>();
    }
    
    public void registerCustomer(Customer customer) {
        customers.add(customer);
        System.out.println(customer.getName() + " registered on " + platformName);
    }
    
    public void addProductToCatalog(Product product) {
        productCatalog.add(product);
    }
    
    public void trackOrder(Order order) {
        allOrders.add(order);
    }
    
    public void displayPlatformStats() {
        System.out.println("\n=== " + platformName + " Statistics ===");
        System.out.println("Total Customers: " + customers.size());
        System.out.println("Total Orders: " + allOrders.size());
        System.out.println("Products in Catalog: " + productCatalog.size());
    }
    
    public String getPlatformName() {
        return platformName;
    }
}

// Main class
public class SelfProblem4_EcommercePlatform {
    public static void main(String[] args) {
        // Create e-commerce platform
        EcommercePlatform amazon = new EcommercePlatform("Amazon");
        
        // Register customers
        Customer customer1 = new Customer("Alice Johnson", "C001", "alice@email.com");
        Customer customer2 = new Customer("Bob Smith", "C002", "bob@email.com");
        Customer customer3 = new Customer("Carol White", "C003", "carol@email.com");
        
        amazon.registerCustomer(customer1);
        amazon.registerCustomer(customer2);
        amazon.registerCustomer(customer3);
        
        System.out.println("\n--- Customer Orders ---");
        
        // Customer 1 places an order
        Order order1 = customer1.placeOrder("ORD001");
        order1.addProduct(new Product("Laptop", "P001", 999.99));
        order1.addProduct(new Product("Mouse", "P002", 25.99, 2));
        order1.addProduct(new Product("Keyboard", "P003", 79.99));
        amazon.trackOrder(order1);
        
        System.out.println();
        
        // Customer 2 places an order
        Order order2 = customer2.placeOrder("ORD002");
        order2.addProduct(new Product("Smartphone", "P004", 699.99));
        order2.addProduct(new Product("Phone Case", "P005", 15.99));
        order2.addProduct(new Product("Screen Protector", "P006", 9.99, 3));
        amazon.trackOrder(order2);
        
        System.out.println();
        
        // Customer 3 places an order
        Order order3 = customer3.placeOrder("ORD003");
        order3.addProduct(new Product("Headphones", "P007", 149.99));
        order3.addProduct(new Product("USB Cable", "P008", 12.99, 5));
        amazon.trackOrder(order3);
        
        System.out.println();
        
        // Customer 1 places another order
        Order order4 = customer1.placeOrder("ORD004");
        order4.addProduct(new Product("Monitor", "P009", 299.99, 2));
        amazon.trackOrder(order4);
        
        // Display order details
        order1.displayOrderDetails();
        order2.displayOrderDetails();
        order3.displayOrderDetails();
        order4.displayOrderDetails();
        
        // Update order statuses
        System.out.println("\n--- Order Status Updates ---");
        order1.updateStatus("Shipped");
        order2.updateStatus("Processing");
        order3.updateStatus("Delivered");
        order4.updateStatus("Shipped");
        
        // Customers view their orders
        customer1.viewOrders();
        customer2.viewOrders();
        customer3.viewOrders();
        
        // Platform statistics
        amazon.displayPlatformStats();
    }
}
