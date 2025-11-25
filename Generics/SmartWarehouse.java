import java.util.ArrayList;
import java.util.List;

abstract class WarehouseItem {
    private String name;
    private double weight;

    public WarehouseItem(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public abstract String getCategory();
}

class ElectronicsItem extends WarehouseItem {
    private String brand;

    public ElectronicsItem(String name, double weight, String brand) {
        super(name, weight);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String getCategory() {
        return "Electronics";
    }
}

class GroceryItem extends WarehouseItem {
    private String expiryDate;

    public GroceryItem(String name, double weight, String expiryDate) {
        super(name, weight);
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String getCategory() {
        return "Grocery";
    }
}

class FurnitureItem extends WarehouseItem {
    private String material;

    public FurnitureItem(String name, double weight, String material) {
        super(name, weight);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public String getCategory() {
        return "Furniture";
    }
}

class Storage<T extends WarehouseItem> {
    private List<T> items = new ArrayList<>();
    private String storageName;

    public Storage(String storageName) {
        this.storageName = storageName;
    }

    public void addItem(T item) {
        items.add(item);
        System.out.println("Added: " + item.getName() + " to " + storageName);
    }

    public T retrieveItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    public List<T> getAllItems() {
        return items;
    }

    public void displayItems() {
        System.out.println("\n" + storageName + " contains:");
        for (T item : items) {
            System.out.println("- " + item.getName() + " (" + item.getCategory() + ") - " + item.getWeight() + " kg");
        }
    }
}

public class SmartWarehouse {
    public static void displayAllItems(List<? extends WarehouseItem> items) {
        System.out.println("\nAll Warehouse Items:");
        for (WarehouseItem item : items) {
            System.out.println("- " + item.getName() + " [" + item.getCategory() + "] - " + item.getWeight() + " kg");
        }
    }

    public static void main(String[] args) {
        Storage<ElectronicsItem> electronicsStorage = new Storage<>("Electronics Storage");
        electronicsStorage.addItem(new ElectronicsItem("Laptop", 2.5, "Dell"));
        electronicsStorage.addItem(new ElectronicsItem("Smartphone", 0.2, "Samsung"));
        electronicsStorage.addItem(new ElectronicsItem("Television", 15.0, "LG"));
        electronicsStorage.displayItems();

        Storage<GroceryItem> groceryStorage = new Storage<>("Grocery Storage");
        groceryStorage.addItem(new GroceryItem("Rice", 10.0, "2025-12-31"));
        groceryStorage.addItem(new GroceryItem("Milk", 1.0, "2025-01-15"));
        groceryStorage.addItem(new GroceryItem("Bread", 0.5, "2025-01-10"));
        groceryStorage.displayItems();

        Storage<FurnitureItem> furnitureStorage = new Storage<>("Furniture Storage");
        furnitureStorage.addItem(new FurnitureItem("Sofa", 50.0, "Leather"));
        furnitureStorage.addItem(new FurnitureItem("Table", 20.0, "Wood"));
        furnitureStorage.displayItems();

        System.out.println("\n--- Using Wildcard Method ---");
        displayAllItems(electronicsStorage.getAllItems());
        displayAllItems(groceryStorage.getAllItems());
        displayAllItems(furnitureStorage.getAllItems());
    }
}
