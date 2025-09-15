public class Book {
    private String title;
    private String author;
    private double price;
    
    // Default constructor
    public Book() {
        this.title = "Unknown Title";
        this.author = "Unknown Author";
        this.price = 0.0;
    }
    
    // Parameterized constructor
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
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
        this.price = price;
    }
    
    // Display method
    public void displayBook() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: $" + price);
        System.out.println("------------------------");
    }
    
    // Main method for testing
    public static void main(String[] args) {
        // Using default constructor
        Book book1 = new Book();
        System.out.println("Book created with default constructor:");
        book1.displayBook();
        
        // Using parameterized constructor
        Book book2 = new Book("Java Programming", "John Smith", 29.99);
        System.out.println("Book created with parameterized constructor:");
        book2.displayBook();
        
        // Modifying book1 using setters
        book1.setTitle("Python Basics");
        book1.setAuthor("Jane Doe");
        book1.setPrice(24.99);
        System.out.println("Book1 after modification:");
        book1.displayBook();
    }
}
