import java.util.ArrayList;
import java.util.List;

abstract class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Mobile extends Product {
    public Mobile(String name, double price) {
        super(name, price);
    }
}

class Laptop extends Product {
    public Laptop(String name, double price) {
        super(name, price);
    }
}

class Tablet extends Product {
    public Tablet(String name, double price) {
        super(name, price);
    }
}

public class PriceCalculator {
    public static double calculateTotal(List<? extends Product> items) {
        double total = 0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public static void main(String[] args) {
        List<Mobile> mobiles = new ArrayList<>();
        mobiles.add(new Mobile("iPhone 15", 999.99));
        mobiles.add(new Mobile("Samsung S24", 899.99));
        mobiles.add(new Mobile("Pixel 8", 699.99));
        System.out.println("Total price of mobiles: $" + calculateTotal(mobiles));

        List<Laptop> laptops = new ArrayList<>();
        laptops.add(new Laptop("MacBook Pro", 2499.99));
        laptops.add(new Laptop("Dell XPS", 1799.99));
        System.out.println("Total price of laptops: $" + calculateTotal(laptops));

        List<Product> allProducts = new ArrayList<>();
        allProducts.add(new Mobile("OnePlus 12", 799.99));
        allProducts.add(new Laptop("HP Spectre", 1599.99));
        allProducts.add(new Tablet("iPad Pro", 1099.99));
        System.out.println("Total price of all products: $" + calculateTotal(allProducts));
    }
}
