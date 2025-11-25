import java.util.ArrayList;
import java.util.List;

interface Category {
    String getCategoryName();
}

class BookCategory implements Category {
    @Override
    public String getCategoryName() {
        return "Books";
    }
}

class ClothingCategory implements Category {
    @Override
    public String getCategoryName() {
        return "Clothing";
    }
}

class GadgetCategory implements Category {
    @Override
    public String getCategoryName() {
        return "Gadgets";
    }
}

class MarketProduct<T extends Category> {
    private String name;
    private double price;
    private T category;

    public MarketProduct(String name, double price, T category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public T getCategory() {
        return category;
    }

    public void display() {
        System.out.println("Product: " + name + " | Category: " + category.getCategoryName() + " | Price: $" + price);
    }
}

class ProductCatalog {
    private List<MarketProduct<? extends Category>> products = new ArrayList<>();

    public void addProduct(MarketProduct<? extends Category> product) {
        products.add(product);
    }

    public static <T extends Category> void applyDiscount(MarketProduct<T> product, double percentage) {
        double originalPrice = product.getPrice();
        double discountAmount = originalPrice * (percentage / 100);
        double newPrice = originalPrice - discountAmount;
        product.setPrice(newPrice);
        System.out.println("Applied " + percentage + "% discount on " + product.getName() + ": $" + originalPrice + " -> $" + newPrice);
    }

    public void displayAllProducts() {
        System.out.println("\nProduct Catalog:");
        for (MarketProduct<? extends Category> product : products) {
            product.display();
        }
    }
}

public class DynamicMarketplace {
    public static void main(String[] args) {
        MarketProduct<BookCategory> book1 = new MarketProduct<>("Java Programming Guide", 49.99, new BookCategory());
        MarketProduct<BookCategory> book2 = new MarketProduct<>("Data Structures Handbook", 39.99, new BookCategory());

        MarketProduct<ClothingCategory> shirt = new MarketProduct<>("Cotton T-Shirt", 29.99, new ClothingCategory());
        MarketProduct<ClothingCategory> jeans = new MarketProduct<>("Denim Jeans", 59.99, new ClothingCategory());

        MarketProduct<GadgetCategory> phone = new MarketProduct<>("Smartphone X", 699.99, new GadgetCategory());
        MarketProduct<GadgetCategory> tablet = new MarketProduct<>("Tablet Pro", 499.99, new GadgetCategory());

        ProductCatalog catalog = new ProductCatalog();
        catalog.addProduct(book1);
        catalog.addProduct(book2);
        catalog.addProduct(shirt);
        catalog.addProduct(jeans);
        catalog.addProduct(phone);
        catalog.addProduct(tablet);

        System.out.println("=== Before Discounts ===");
        catalog.displayAllProducts();

        System.out.println("\n=== Applying Discounts ===");
        ProductCatalog.applyDiscount(book1, 10);
        ProductCatalog.applyDiscount(shirt, 20);
        ProductCatalog.applyDiscount(phone, 15);

        System.out.println("\n=== After Discounts ===");
        catalog.displayAllProducts();
    }
}
