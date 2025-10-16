import java.util.*;

class Product {
    private String name;
    private double price;
    private double rating;
    private double discount;
    
    public Product(String name, double price, double rating, double discount) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.discount = discount;
    }
    
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getRating() { return rating; }
    public double getDiscount() { return discount; }
    
    @Override
    public String toString() {
        return String.format("%-20s Price:$%-8.2f Rating:%.1f Discount:%.0f%%", 
                           name, price, rating, discount);
    }
}

public class EcommerceSorting {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 999.99, 4.5, 10),
            new Product("Smartphone", 699.99, 4.8, 15),
            new Product("Headphones", 149.99, 4.2, 20),
            new Product("Tablet", 499.99, 4.6, 5),
            new Product("Smartwatch", 299.99, 4.3, 25),
            new Product("Camera", 799.99, 4.7, 12),
            new Product("Speaker", 199.99, 4.1, 30)
        );
        
        System.out.println("=== E-Commerce Product Sorting ===\n");
        
        System.out.println("ORIGINAL LIST:");
        System.out.println("=".repeat(70));
        products.forEach(System.out::println);
        
        System.out.println("\n\nCAMPAIGN 1: Budget Sale - Sort by Price (Low to High)");
        System.out.println("=".repeat(70));
        List<Product> sortedByPrice = new ArrayList<>(products);
        sortedByPrice.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        sortedByPrice.forEach(System.out::println);
        
        System.out.println("\n\nCAMPAIGN 2: Premium Collection - Sort by Price (High to Low)");
        System.out.println("=".repeat(70));
        List<Product> sortedByPriceDesc = new ArrayList<>(products);
        sortedByPriceDesc.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        sortedByPriceDesc.forEach(System.out::println);
        
        System.out.println("\n\nCAMPAIGN 3: Top Rated - Sort by Rating (High to Low)");
        System.out.println("=".repeat(70));
        List<Product> sortedByRating = new ArrayList<>(products);
        sortedByRating.sort((p1, p2) -> Double.compare(p2.getRating(), p1.getRating()));
        sortedByRating.forEach(System.out::println);
        
        System.out.println("\n\nCAMPAIGN 4: Biggest Discounts - Sort by Discount (High to Low)");
        System.out.println("=".repeat(70));
        List<Product> sortedByDiscount = new ArrayList<>(products);
        sortedByDiscount.sort((p1, p2) -> Double.compare(p2.getDiscount(), p1.getDiscount()));
        sortedByDiscount.forEach(System.out::println);
        
        System.out.println("\n\nCAMPAIGN 5: Best Value - Sort by Rating then Price");
        System.out.println("=".repeat(70));
        List<Product> sortedByValue = new ArrayList<>(products);
        sortedByValue.sort(Comparator
            .comparing(Product::getRating).reversed()
            .thenComparing(Product::getPrice));
        sortedByValue.forEach(System.out::println);
        
        System.out.println("\n\nCAMPAIGN 6: Flash Sale - Sort by Discount then Rating");
        System.out.println("=".repeat(70));
        List<Product> sortedByFlashSale = new ArrayList<>(products);
        sortedByFlashSale.sort(Comparator
            .comparing(Product::getDiscount).reversed()
            .thenComparing(Product::getRating).reversed());
        sortedByFlashSale.forEach(System.out::println);
    }
}
