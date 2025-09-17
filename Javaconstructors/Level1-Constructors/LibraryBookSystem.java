/**
 * LibraryBook class demonstrating constructors with book management functionality
 * Includes borrowing system with availability tracking
 */
public class LibraryBookSystem {
    // Instance variables
    private String title;
    private String author;
    private double price;
    private boolean availability;
    private String isbn;
    private String borrowerName;
    private java.time.LocalDate borrowDate;
    private java.time.LocalDate dueDate;
    private static int totalBooks = 0;
    private static int borrowedBooks = 0;
    
    // Default constructor
    public LibraryBookSystem() {
        this.title = "Unknown Title";
        this.author = "Unknown Author";
        this.price = 0.0;
        this.availability = true;
        this.isbn = generateISBN();
        this.borrowerName = null;
        this.borrowDate = null;
        this.dueDate = null;
        totalBooks++;
        System.out.println("Default constructor - Library book created with default values");
    }
    
    // Parameterized constructor
    public LibraryBookSystem(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.availability = true;
        this.isbn = generateISBN();
        this.borrowerName = null;
        this.borrowDate = null;
        this.dueDate = null;
        totalBooks++;
        System.out.println("Parameterized constructor - Library book created: " + title);
    }
    
    // Full constructor with availability
    public LibraryBookSystem(String title, String author, double price, boolean availability) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.availability = availability;
        this.isbn = generateISBN();
        this.borrowerName = null;
        this.borrowDate = null;
        this.dueDate = null;
        totalBooks++;
        if (!availability) borrowedBooks++;
        System.out.println("Full constructor - Library book created: " + title + " (Available: " + availability + ")");
    }
    
    // Copy constructor
    public LibraryBookSystem(LibraryBookSystem other) {
        if (other != null) {
            this.title = other.title;
            this.author = other.author;
            this.price = other.price;
            this.availability = true; // New copy should be available
            this.isbn = generateISBN(); // New ISBN for copy
            this.borrowerName = null;
            this.borrowDate = null;
            this.dueDate = null;
            totalBooks++;
            System.out.println("Copy constructor - Book copied: " + this.title);
        }
    }
    
    // Method to borrow book
    public boolean borrowBook(String borrowerName) {
        if (availability && borrowerName != null && !borrowerName.trim().isEmpty()) {
            this.availability = false;
            this.borrowerName = borrowerName;
            this.borrowDate = java.time.LocalDate.now();
            this.dueDate = borrowDate.plusWeeks(2); // 2 weeks loan period
            borrowedBooks++;
            System.out.println("Book '" + title + "' borrowed by: " + borrowerName);
            System.out.println("Due date: " + dueDate.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            return true;
        } else if (!availability) {
            System.out.println("Book '" + title + "' is currently not available (borrowed by: " + this.borrowerName + ")");
            return false;
        } else {
            System.out.println("Invalid borrower name provided!");
            return false;
        }
    }
    
    // Method to return book
    public boolean returnBook() {
        if (!availability) {
            System.out.println("Book '" + title + "' returned by: " + borrowerName);
            this.availability = true;
            this.borrowerName = null;
            this.borrowDate = null;
            this.dueDate = null;
            borrowedBooks--;
            return true;
        } else {
            System.out.println("Book '" + title + "' is already available!");
            return false;
        }
    }
    
    // Check if book is overdue
    public boolean isOverdue() {
        if (!availability && dueDate != null) {
            return java.time.LocalDate.now().isAfter(dueDate);
        }
        return false;
    }
    
    // Calculate fine for overdue book
    public double calculateFine() {
        if (isOverdue()) {
            long daysOverdue = java.time.temporal.ChronoUnit.DAYS.between(dueDate, java.time.LocalDate.now());
            return daysOverdue * 1.0; // $1 per day fine
        }
        return 0.0;
    }
    
    // Generate unique ISBN
    private static String generateISBN() {
        return "ISBN-" + (totalBooks + 1000);
    }
    
    // Getter methods
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return availability; }
    public String getISBN() { return isbn; }
    public String getBorrowerName() { return borrowerName; }
    public java.time.LocalDate getBorrowDate() { return borrowDate; }
    public java.time.LocalDate getDueDate() { return dueDate; }
    
    // Static methods for library statistics
    public static int getTotalBooks() { return totalBooks; }
    public static int getBorrowedBooks() { return borrowedBooks; }
    public static int getAvailableBooks() { return totalBooks - borrowedBooks; }
    
    // Display book details
    public void displayBookDetails() {
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("=== Library Book Details ===");
        System.out.println("ISBN: " + isbn);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.printf("Price: $%.2f%n", price);
        System.out.println("Available: " + (availability ? "Yes" : "No"));
        
        if (!availability) {
            System.out.println("Borrowed by: " + borrowerName);
            System.out.println("Borrow date: " + borrowDate.format(formatter));
            System.out.println("Due date: " + dueDate.format(formatter));
            System.out.println("Overdue: " + (isOverdue() ? "Yes" : "No"));
            if (isOverdue()) {
                System.out.printf("Fine amount: $%.2f%n", calculateFine());
            }
        }
        System.out.println("============================");
    }
    
    // Display library statistics
    public static void displayLibraryStatistics() {
        System.out.println("=== Library Statistics ===");
        System.out.println("Total books: " + totalBooks);
        System.out.println("Available books: " + getAvailableBooks());
        System.out.println("Borrowed books: " + borrowedBooks);
        System.out.printf("Availability rate: %.1f%%%n", (getAvailableBooks() / (double)totalBooks) * 100);
        System.out.println("==========================");
    }
    
    @Override
    public String toString() {
        return String.format("LibraryBook{isbn='%s', title='%s', author='%s', available=%s}", 
                           isbn, title, author, availability);
    }
    
    // Main method for testing
    public static void main(String[] args) {
        System.out.println("=== Library Book System Demonstration ===\\n");
        
        System.out.println("1. Creating books with different constructors:");
        LibraryBookSystem book1 = new LibraryBookSystem();
        LibraryBookSystem book2 = new LibraryBookSystem("Java Programming", "John Smith", 45.99);
        LibraryBookSystem book3 = new LibraryBookSystem("Python Basics", "Jane Doe", 39.99, true);
        LibraryBookSystem book4 = new LibraryBookSystem(book2); // Copy constructor
        
        System.out.println("\\n2. Initial library state:");
        displayLibraryStatistics();
        
        System.out.println("\\n3. Book details:");
        book1.displayBookDetails();
        book2.displayBookDetails();
        
        System.out.println("\\n4. Borrowing books:");
        book2.borrowBook("Alice Johnson");
        book3.borrowBook("Bob Wilson");
        
        System.out.println("\\n5. Library state after borrowing:");
        displayLibraryStatistics();
        
        System.out.println("\\n6. Updated book details:");
        book2.displayBookDetails();
        book3.displayBookDetails();
        
        System.out.println("\\n7. Attempting to borrow already borrowed book:");
        book2.borrowBook("Charlie Brown");
        
        System.out.println("\\n8. Returning a book:");
        book3.returnBook();
        
        System.out.println("\\n9. Final library state:");
        displayLibraryStatistics();
        
        System.out.println("\\n10. Testing overdue functionality (simulated):");
        // Manually set due date to past for testing
        book2.dueDate = java.time.LocalDate.now().minusDays(3);
        System.out.println("Is book2 overdue? " + book2.isOverdue());
        System.out.printf("Fine for book2: $%.2f%n", book2.calculateFine());
        
        System.out.println("\\nLibrary Book System demonstrates:");
        System.out.println("✓ Multiple constructor types");
        System.out.println("✓ Book borrowing and returning functionality");
        System.out.println("✓ Availability tracking");
        System.out.println("✓ Fine calculation for overdue books");
        System.out.println("✓ Library statistics management");
    }
}
