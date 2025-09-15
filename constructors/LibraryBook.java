public class LibraryBook {
    private String title;
    private String author;
    private double price;
    private boolean availability;
    private String borrowerName;
    private String borrowDate;
    
    // Default constructor
    public LibraryBook() {
        this.title = "Unknown Title";
        this.author = "Unknown Author";
        this.price = 0.0;
        this.availability = true;
        this.borrowerName = null;
        this.borrowDate = null;
    }
    
    // Parameterized constructor
    public LibraryBook(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.availability = true;
        this.borrowerName = null;
        this.borrowDate = null;
    }
    
    // Parameterized constructor with availability
    public LibraryBook(String title, String author, double price, boolean availability) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.availability = availability;
        this.borrowerName = null;
        this.borrowDate = null;
    }
    
    // Copy constructor
    public LibraryBook(LibraryBook other) {
        if (other != null) {
            this.title = other.title;
            this.author = other.author;
            this.price = other.price;
            this.availability = other.availability;
            this.borrowerName = other.borrowerName;
            this.borrowDate = other.borrowDate;
        } else {
            this.title = "Unknown Title";
            this.author = "Unknown Author";
            this.price = 0.0;
            this.availability = true;
            this.borrowerName = null;
            this.borrowDate = null;
        }
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
    
    public boolean isAvailable() {
        return availability;
    }
    
    public String getBorrowerName() {
        return borrowerName;
    }
    
    public String getBorrowDate() {
        return borrowDate;
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
    
    // Method to borrow a book
    public boolean borrowBook(String borrowerName, String borrowDate) {
        if (availability) {
            this.availability = false;
            this.borrowerName = borrowerName;
            this.borrowDate = borrowDate;
            System.out.println("Book '" + title + "' has been borrowed by " + borrowerName);
            return true;
        } else {
            System.out.println("Book '" + title + "' is not available. Currently borrowed by " + this.borrowerName);
            return false;
        }
    }
    
    // Method to return a book
    public boolean returnBook() {
        if (!availability) {
            System.out.println("Book '" + title + "' has been returned by " + borrowerName);
            this.availability = true;
            this.borrowerName = null;
            this.borrowDate = null;
            return true;
        } else {
            System.out.println("Book '" + title + "' was not borrowed.");
            return false;
        }
    }
    
    // Method to check if book can be borrowed
    public boolean canBorrow() {
        return availability;
    }
    
    // Method to get current status
    public String getStatus() {
        if (availability) {
            return "Available";
        } else {
            return "Borrowed by " + borrowerName + " on " + borrowDate;
        }
    }
    
    // Display book information
    public void displayBook() {
        System.out.println("Library Book Information:");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: $" + price);
        System.out.println("Status: " + getStatus());
        System.out.println("------------------------");
    }
    
    // Main method for testing
    public static void main(String[] args) {
        // Create books using different constructors
        LibraryBook book1 = new LibraryBook();
        System.out.println("Book created with default constructor:");
        book1.displayBook();
        
        LibraryBook book2 = new LibraryBook("Java Programming", "John Smith", 29.99);
        System.out.println("Book created with parameterized constructor:");
        book2.displayBook();
        
        LibraryBook book3 = new LibraryBook("Python Basics", "Jane Doe", 24.99, false);
        System.out.println("Book created with full parameterized constructor:");
        book3.displayBook();
        
        // Test borrowing functionality
        System.out.println("\\n=== Testing Borrow Functionality ===");
        
        // Try to borrow available book
        book2.borrowBook("Alice Johnson", "2025-09-15");
        book2.displayBook();
        
        // Try to borrow already borrowed book
        book2.borrowBook("Bob Wilson", "2025-09-16");
        
        // Return the book
        book2.returnBook();
        book2.displayBook();
        
        // Try to return book that wasn't borrowed
        book2.returnBook();
        
        // Test copy constructor
        System.out.println("\\n=== Testing Copy Constructor ===");
        LibraryBook book4 = new LibraryBook(book2);
        System.out.println("Book created using copy constructor:");
        book4.displayBook();
        
        // Borrow original book and show copy is independent
        book2.borrowBook("Charlie Brown", "2025-09-17");
        System.out.println("Original book after borrowing:");
        book2.displayBook();
        System.out.println("Copied book (should still be available):");
        book4.displayBook();
    }
}
