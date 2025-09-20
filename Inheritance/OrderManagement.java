class Order {
    String orderId;
    String orderDate;

    Order(String orderId, String orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
    }

    String getOrderStatus() {
        return "Order Placed";
    }

    void displayOrderDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Status: " + getOrderStatus());
    }
}

class ShippedOrder extends Order {
    String trackingNumber;

    ShippedOrder(String orderId, String orderDate, String trackingNumber) {
        super(orderId, orderDate);
        this.trackingNumber = trackingNumber;
    }

    @Override
    String getOrderStatus() {
        return "Order Shipped";
    }

    @Override
    void displayOrderDetails() {
        super.displayOrderDetails();
        System.out.println("Tracking Number: " + trackingNumber);
    }
}

class DeliveredOrder extends ShippedOrder {
    String deliveryDate;

    DeliveredOrder(String orderId, String orderDate, String trackingNumber, String deliveryDate) {
        super(orderId, orderDate, trackingNumber);
        this.deliveryDate = deliveryDate;
    }

    @Override
    String getOrderStatus() {
        return "Order Delivered";
    }

    @Override
    void displayOrderDetails() {
        super.displayOrderDetails();
        System.out.println("Delivery Date: " + deliveryDate);
    }
}

public class OrderManagement {
    public static void main(String[] args) {
        Order order1 = new Order("ORD001", "2025-09-15");
        ShippedOrder order2 = new ShippedOrder("ORD002", "2025-09-16", "TRK123456");
        DeliveredOrder order3 = new DeliveredOrder("ORD003", "2025-09-14", "TRK789012", "2025-09-19");

        System.out.println("=== Online Retail Order Management ===\n");
        
        System.out.println("Order 1:");
        order1.displayOrderDetails();
        
        System.out.println("\nOrder 2:");
        order2.displayOrderDetails();
        
        System.out.println("\nOrder 3:");
        order3.displayOrderDetails();
        
        System.out.println("\nDemonstrating multilevel inheritance:");
        System.out.println("DeliveredOrder extends ShippedOrder which extends Order");
    }
}