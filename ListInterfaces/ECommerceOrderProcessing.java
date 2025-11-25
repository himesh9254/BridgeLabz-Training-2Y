import java.util.*;

class Order {
    private String orderId;
    private String customerName;
    private double amount;
    private String status;

    public Order(String orderId, String customerName, double amount) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
        this.status = "Placed";
    }

    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId.equals(order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public String toString() {
        return String.format("Order[%s, %s, $%.2f, %s]", orderId, customerName, amount, status);
    }
}

public class ECommerceOrderProcessing {
    private List<Order> allOrders;
    private Set<Order> uniqueOrders;
    private Queue<Order> processingQueue;
    private Stack<Order> failedOrders;

    public ECommerceOrderProcessing() {
        allOrders = new ArrayList<>();
        uniqueOrders = new HashSet<>();
        processingQueue = new LinkedList<>();
        failedOrders = new Stack<>();
    }

    public void addOrder(Order order) {
        allOrders.add(order);
        System.out.println("Order added: " + order);
    }

    public void removeDuplicates() {
        System.out.println("\n=== Removing Duplicate Orders ===");
        int originalCount = allOrders.size();

        for (Order order : allOrders) {
            if (!uniqueOrders.add(order)) {
                System.out.println("Duplicate removed: " + order.getOrderId());
            }
        }

        System.out.println("Original count: " + originalCount);
        System.out.println("Unique count: " + uniqueOrders.size());
        System.out.println("Duplicates removed: " + (originalCount - uniqueOrders.size()));
    }

    public void queueOrdersForProcessing() {
        System.out.println("\n=== Queueing Orders for Processing ===");
        for (Order order : uniqueOrders) {
            processingQueue.add(order);
            System.out.println("Queued: " + order.getOrderId());
        }
    }

    public void processOrders() {
        System.out.println("\n=== Processing Orders (FIFO) ===");
        Random random = new Random();

        while (!processingQueue.isEmpty()) {
            Order order = processingQueue.poll();

            if (random.nextInt(10) < 2) {
                order.setStatus("Failed");
                failedOrders.push(order);
                System.out.println("FAILED: " + order);
            } else {
                order.setStatus("Completed");
                System.out.println("PROCESSED: " + order);
            }
        }
    }

    public void retryFailedOrders() {
        System.out.println("\n=== Retrying Failed Orders (LIFO) ===");

        while (!failedOrders.isEmpty()) {
            Order order = failedOrders.pop();
            order.setStatus("Retrying");
            System.out.println("Retrying: " + order);
            order.setStatus("Completed");
            System.out.println("SUCCESS: " + order);
        }
    }

    public void displayAllOrders() {
        System.out.println("\n=== All Orders ===");
        for (Order order : allOrders) {
            System.out.println(order);
        }
    }

    public void displayUniqueOrders() {
        System.out.println("\n=== Unique Orders ===");
        for (Order order : uniqueOrders) {
            System.out.println(order);
        }
    }

    public static void main(String[] args) {
        ECommerceOrderProcessing system = new ECommerceOrderProcessing();

        system.addOrder(new Order("ORD001", "John Doe", 150.00));
        system.addOrder(new Order("ORD002", "Jane Smith", 250.00));
        system.addOrder(new Order("ORD001", "John Doe", 150.00));
        system.addOrder(new Order("ORD003", "Bob Wilson", 75.00));
        system.addOrder(new Order("ORD002", "Jane Smith", 250.00));
        system.addOrder(new Order("ORD004", "Alice Brown", 320.00));
        system.addOrder(new Order("ORD005", "Charlie Davis", 180.00));
        system.addOrder(new Order("ORD003", "Bob Wilson", 75.00));

        system.displayAllOrders();
        system.removeDuplicates();
        system.displayUniqueOrders();
        system.queueOrdersForProcessing();
        system.processOrders();
        system.retryFailedOrders();
    }
}
