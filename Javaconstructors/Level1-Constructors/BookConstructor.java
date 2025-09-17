/**
 * Book class demonstrating default and parameterized constructors
 * This class represents a book with title, author, and price attributes
 */
public class BookConstructor {
    // Instance variables
    private String title;
    private String author;
    private double price;
    
    // Default constructor
    public BookConstructor() {
        this.title = "Unknown Title";
        this.author = "Unknown Author";
        this.price = 0.0;
        System.out.println("Default constructor called - Book created with default values");
    }
    
    // Parameterized constructor with all fields
    public BookConstructor(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
        System.out.println("Parameterized constructor called - Book created with custom values");
    }
    
    // Parameterized constructor with title and author only
    public BookConstructor(String title, String author) {
        this.title = title;
        this.author = author;
        this.price = 0.0;
        System.out.println("Parameterized constructor (2 args) called");
    }
    
    // Getter methods
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public double getPrice() {
        return price;
    }
    
    // Setter methods
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("Price cannot be negative!");
        }
    }
    
    // Method to display book details
    public void displayBookDetails() {
        System.out.println("=== Book Details ===");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.printf("Price: $%.2f%n", price);
        System.out.println("====================");
    }
    
    // Method to apply discount
    public void applyDiscount(double discountPercentage) {
        if (discountPercentage > 0 && discountPercentage <= 100) {
            double discountAmount = price * (discountPercentage / 100);
            price -= discountAmount;
            System.out.printf("Discount of %.1f%% applied. New price: $%.2f%n", 
                             discountPercentage, price);
        } else {
            System.out.println("Invalid discount percentage!");
        }
    }
    
    // Override toString method
    @Override
    public String toString() {
        return String.format("Book{title='%s', author='%s', price=%.2f}", 
                           title, author, price);
    }
    
    // Main method for testing
    public static void main(String[] args) {
        System.out.println("=== Book Constructor Demonstration ===\n");
        
        // Using default constructor
        System.out.println("1. Creating book with default constructor:");
        BookConstructor book1 = new BookConstructor();
        book1.displayBookDetails();
        
        System.out.println("\n2. Creating book with parameterized constructor (3 args):");
        BookConstructor book2 = new BookConstructor("To Kill a Mockingbird", 
                                                  "Harper Lee", 12.99);
        book2.displayBookDetails();
        
        System.out.println("\n3. Creating book with parameterized constructor (2 args):");
        BookConstructor book3 = new BookConstructor("1984", "George Orwell");
        book3.displayBookDetails();
        
        System.out.println("\n4. Updating book1 details using setter methods:");
        book1.setTitle("The Great Gatsby");
        book1.setAuthor("F. Scott Fitzgerald");
        book1.setPrice(15.50);
        book1.displayBookDetails();
        
        System.out.println("\n5. Applying discount to book2:");
        book2.applyDiscount(20); // 20% discount
        book2.displayBookDetails();
        
        System.out.println("\n6. Using toString method:");
        System.out.println("Book1: " + book1.toString());
        System.out.println("Book2: " + book2.toString());
        System.out.println("Book3: " + book3.toString());
        
        System.out.println("\n=== Constructor Types Demonstrated ===");
        System.out.println("✓ Default Constructor: Initializes with default values");
        System.out.println("✓ Parameterized Constructor (3 args): Full initialization");
        System.out.println("✓ Parameterized Constructor (2 args): Partial initialization");
        System.out.println("✓ Constructor Overloading: Multiple constructors with different parameters");
    }
}
