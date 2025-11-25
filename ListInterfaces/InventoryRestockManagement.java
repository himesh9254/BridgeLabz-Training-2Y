import java.util.*;

class Product {
    private String productId;
    private String name;
    private double price;
    private int stock;
    private int minStock;

    public Product(String productId, String name, double price, int stock, int minStock) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.minStock = minStock;
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public int getMinStock() { return minStock; }
    public void setStock(int stock) { this.stock = stock; }
    public void addStock(int quantity) { this.stock += quantity; }

    public boolean isLowStock() {
        return stock < minStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId.equals(product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return String.format("Product[%s, %s, $%.2f, Stock:%d, Min:%d]", 
            productId, name, price, stock, minStock);
    }
}

class RestockRecord {
    private Product product;
    private int previousStock;
    private int addedQuantity;

    public RestockRecord(Product product, int previousStock, int addedQuantity) {
        this.product = product;
        this.previousStock = previousStock;
        this.addedQuantity = addedQuantity;
    }

    public Product getProduct() { return product; }
    public int getPreviousStock() { return previousStock; }
    public int getAddedQuantity() { return addedQuantity; }

    @Override
    public String toString() {
        return String.format("Restock[%s, Added:%d, %d->%d]", 
            product.getName(), addedQuantity, previousStock, product.getStock());
    }
}

public class InventoryRestockManagement {
    private Set<String> productNames;
    private List<Product> productList;
    private Queue<Product> restockQueue;
    private Stack<RestockRecord> restockHistory;
    private Map<String, Product> productMap;

    public InventoryRestockManagement() {
        productNames = new HashSet<>();
        productList = new ArrayList<>();
        restockQueue = new LinkedList<>();
        restockHistory = new Stack<>();
        productMap = new HashMap<>();
    }

    public boolean addProduct(Product product) {
        if (productNames.contains(product.getName())) {
            System.out.println("Product already exists: " + product.getName());
            return false;
        }
        productNames.add(product.getName());
        productList.add(product);
        productMap.put(product.getProductId(), product);
        System.out.println("Product added: " + product);
        return true;
    }

    public void checkAndQueueLowStock() {
        System.out.println("\n=== Checking for Low Stock Items ===");
        for (Product product : productList) {
            if (product.isLowStock()) {
                if (!restockQueue.contains(product)) {
                    restockQueue.add(product);
                    System.out.println("Queued for restock: " + product.getName() + 
                        " (Current: " + product.getStock() + ", Min: " + product.getMinStock() + ")");
                }
            }
        }
    }

    public void processRestockQueue(int defaultQuantity) {
        System.out.println("\n=== Processing Restock Queue ===");
        while (!restockQueue.isEmpty()) {
            Product product = restockQueue.poll();
            int previousStock = product.getStock();
            int quantityToAdd = Math.max(defaultQuantity, product.getMinStock() * 2 - product.getStock());

            product.addStock(quantityToAdd);

            RestockRecord record = new RestockRecord(product, previousStock, quantityToAdd);
            restockHistory.push(record);

            System.out.println("Restocked: " + record);
        }
    }

    public void undoLastRestock() {
        System.out.println("\n=== Undoing Last Restock ===");
        if (restockHistory.isEmpty()) {
            System.out.println("No restock to undo!");
            return;
        }

        RestockRecord record = restockHistory.pop();
        record.getProduct().setStock(record.getPreviousStock());
        System.out.println("Undone: " + record.getProduct().getName() + 
            " reverted to " + record.getPreviousStock() + " units");
    }

    public void displayAllProducts() {
        System.out.println("\n=== All Products ===");
        for (Product product : productList) {
            String status = product.isLowStock() ? " [LOW STOCK]" : "";
            System.out.println("  " + product + status);
        }
    }

    public void displayRestockQueue() {
        System.out.println("\n=== Restock Queue ===");
        if (restockQueue.isEmpty()) {
            System.out.println("  No items pending restock");
            return;
        }
        for (Product product : restockQueue) {
            System.out.println("  " + product.getName() + " (Stock: " + product.getStock() + ")");
        }
    }

    public void displayRestockHistory() {
        System.out.println("\n=== Recent Restock History ===");
        if (restockHistory.isEmpty()) {
            System.out.println("  No restock history");
            return;
        }
        Stack<RestockRecord> temp = new Stack<>();
        temp.addAll(restockHistory);
        while (!temp.isEmpty()) {
            System.out.println("  " + temp.pop());
        }
    }

    public static void main(String[] args) {
        InventoryRestockManagement system = new InventoryRestockManagement();

        System.out.println("=== Adding Products ===");
        system.addProduct(new Product("PRD001", "Milk", 3.99, 5, 20));
        system.addProduct(new Product("PRD002", "Bread", 2.49, 30, 25));
        system.addProduct(new Product("PRD003", "Eggs", 4.99, 8, 15));
        system.addProduct(new Product("PRD004", "Butter", 5.99, 50, 20));
        system.addProduct(new Product("PRD005", "Cheese", 6.99, 3, 10));
        system.addProduct(new Product("PRD006", "Milk", 3.99, 100, 20));

        system.displayAllProducts();
        system.checkAndQueueLowStock();
        system.displayRestockQueue();

        system.processRestockQueue(50);
        system.displayAllProducts();
        system.displayRestockHistory();

        system.undoLastRestock();
        system.displayAllProducts();

        system.undoLastRestock();
        system.displayAllProducts();
    }
}
