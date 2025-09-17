/**
 * Book class demonstrating static, this, final, and instanceof concepts
 * Complete library management system with book tracking features
 */
public class LibraryManagementSystem {
    // Static variable - shared across all books
    private static String libraryName = "Central Public Library";
    private static int totalBooks = 0;
    private static int borrowedBooks = 0;
    
    // Instance variables
    private String title;
    private String author;
    private final String isbn; // final - cannot be changed once assigned
    private boolean isAvailable;
    private String borrowerName;
    private java.time.LocalDate borrowDate;
    
    // Constructor using 'this' to resolve ambiguity
    public LibraryManagementSystem(String title, String author, String isbn) {
        // Using 'this' to distinguish between parameter and instance variable
        this.title = title;
        this.author = author;
        this.isbn = isbn; // final variable initialization
        this.isAvailable = true;
        this.borrowerName = null;
        this.borrowDate = null;
        
        // Update static counter
        totalBooks++;
        
        System.out.println("Book added to library: " + this.title);
    }
    
    // Overloaded constructor with availability status
    public LibraryManagementSystem(String title, String author, String isbn, boolean isAvailable) {
        // Using 'this' to call another constructor (constructor chaining)
        this(title, author, isbn);
        this.isAvailable = isAvailable;
        if (!isAvailable) {
            borrowedBooks++;
        }
        System.out.println("Book availability set to: " + this.isAvailable);
    }
    
    // Default constructor
    public LibraryManagementSystem() {
        this("Unknown Title", "Unknown Author", generateISBN());
        System.out.println("Default book entry created.");
    }
    
    // Static method to display library name
    public static void displayLibraryName() {
        System.out.println("=== " + libraryName + " ===");
        System.out.println("Your trusted source for knowledge and learning");
    }
    
    // Static method to get total books
    public static int getTotalBooks() {
        return totalBooks;
    }
    
    // Static method to get library statistics
    public static void displayLibraryStatistics() {
        System.out.println("=== Library Statistics ===");
        System.out.println("Library: " + libraryName);
        System.out.println("Total Books: " + totalBooks);
        System.out.println("Available Books: " + (totalBooks - borrowedBooks));
        System.out.println("Borrowed Books: " + borrowedBooks);
        if (totalBooks > 0) {
            System.out.printf("Availability Rate: %.1f%%%n", 
                             ((totalBooks - borrowedBooks) / (double)totalBooks) * 100);
        }
        System.out.println("=========================");
    }
    
    // Static method to update library name
    public static void updateLibraryName(String newLibraryName) {
        if (newLibraryName != null && !newLibraryName.trim().isEmpty()) {
            String oldName = libraryName;
            libraryName = newLibraryName;
            System.out.println("Library name updated: " + oldName + " -> " + libraryName);
            System.out.println("This change affects all " + totalBooks + " books in the system!");
        } else {
            System.out.println("Invalid library name provided!");
        }
    }
    
    // Method to borrow book using 'this'
    public boolean borrowBook(String borrowerName) {
        if (this.isAvailable && borrowerName != null && !borrowerName.trim().isEmpty()) {
            this.isAvailable = false;
            this.borrowerName = borrowerName;
            this.borrowDate = java.time.LocalDate.now();
            borrowedBooks++;
            
            System.out.println("Book borrowed successfully!");
            System.out.println("Title: " + this.title);
            System.out.println("Borrower: " + this.borrowerName);
            System.out.println("Borrow Date: " + this.borrowDate);
            return true;
        } else if (!this.isAvailable) {
            System.out.println("Book '" + this.title + "' is currently borrowed by: " + this.borrowerName);
            return false;
        } else {
            System.out.println("Invalid borrower name!");
            return false;
        }
    }
    
    // Method to return book using 'this'
    public boolean returnBook() {
        if (!this.isAvailable) {
            System.out.println("Book returned: " + this.title);
            System.out.println("Returned by: " + this.borrowerName);
            
            this.isAvailable = true;
            this.borrowerName = null;
            this.borrowDate = null;
            borrowedBooks--;
            return true;
        } else {
            System.out.println("Book '" + this.title + "' is already available!");
            return false;
        }
    }
    
    // Method to check if book is overdue using 'this'
    public boolean isOverdue() {
        if (!this.isAvailable && this.borrowDate != null) {
            java.time.LocalDate dueDate = this.borrowDate.plusWeeks(2); // 2 weeks loan period
            return java.time.LocalDate.now().isAfter(dueDate);
        }
        return false;
    }
    
    // Method to display book details
    public void displayBookDetails() {
        System.out.println("=== Book Details ===");
        System.out.println("Library: " + libraryName); // static variable access
        System.out.println("Title: " + this.title);
        System.out.println("Author: " + this.author);
        System.out.println("ISBN: " + this.isbn); // final variable access
        System.out.println("Available: " + (this.isAvailable ? "Yes" : "No"));
        
        if (!this.isAvailable) {
            System.out.println("Borrowed by: " + this.borrowerName);
            System.out.println("Borrow Date: " + this.borrowDate);
            System.out.println("Overdue: " + (this.isOverdue() ? "Yes" : "No"));
        }
        System.out.println("===================");
    }
    
    // Static method to validate and display book using instanceof
    public static void processBook(Object obj) {
        // Using instanceof to check object type
        if (obj instanceof LibraryManagementSystem) {
            System.out.println("✓ Object is a valid Book instance");
            LibraryManagementSystem book = (LibraryManagementSystem) obj; // Safe casting
            book.displayBookDetails();
        } else {
            System.out.println("✗ Object is not a Book instance!");
            System.out.println("Object type: " + (obj != null ? obj.getClass().getSimpleName() : "null"));
        }
    }
    
    // Method to compare books using 'this'
    public boolean isSameBook(LibraryManagementSystem other) {
        // Using 'this' to refer to current object and final ISBN for comparison
        return other != null && this.isbn.equals(other.isbn);
    }
    
    // Method to check if same author using 'this'
    public boolean isSameAuthor(LibraryManagementSystem other) {
        return other != null && this.author.equalsIgnoreCase(other.author);
    }
    
    // Method to update book info (note: ISBN cannot be changed as it's final)
    public void updateBookInfo(String title, String author) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title;
        }
        if (author != null && !author.trim().isEmpty()) {
            this.author = author;
        }
        System.out.println("Book information updated for ISBN: " + this.isbn);
    }
    
    // Utility method to generate unique ISBN
    private static String generateISBN() {
        return "ISBN-" + String.format("%010d", totalBooks + 1);
    }
    
    // Getter methods using 'this'
    public String getTitle() {
        return this.title;
    }
    
    public String getAuthor() {
        return this.author;
    }
    
    public String getISBN() {
        return this.isbn; // final variable - read-only access
    }
    
    public boolean isAvailable() {
        return this.isAvailable;
    }
    
    public String getBorrowerName() {
        return this.borrowerName;
    }
    
    public java.time.LocalDate getBorrowDate() {
        return this.borrowDate;
    }
    
    // Static getter methods
    public static String getLibraryName() {
        return libraryName;
    }
    
    public static int getBorrowedBooks() {
        return borrowedBooks;
    }
    
    // Method to get book summary using 'this'
    public String getBookSummary() {
        return String.format("'%s' by %s (ISBN: %s) - %s", 
                           this.title, this.author, this.isbn, 
                           this.isAvailable ? "Available" : "Borrowed");
    }
    
    @Override
    public String toString() {
        return String.format("Book{title='%s', author='%s', isbn='%s', available=%s}", 
                           title, author, isbn, isAvailable);
    }
    
    // Main method for testing
    public static void main(String[] args) {
        System.out.println("=== Library Management System Demo ===\\n");
        
        System.out.println("1. Library Information:");
        displayLibraryName();
        System.out.println("Initial books count: " + getTotalBooks());
        
        System.out.println("\\n2. Creating books (using 'this' in constructors):");
        LibraryManagementSystem book1 = new LibraryManagementSystem("Java Programming", "James Gosling", "ISBN-1234567890");
        LibraryManagementSystem book2 = new LibraryManagementSystem("Python Basics", "Guido van Rossum", "ISBN-0987654321", true);
        LibraryManagementSystem book3 = new LibraryManagementSystem("Data Structures", "Robert Sedgewick", "ISBN-1122334455", false);
        LibraryManagementSystem book4 = new LibraryManagementSystem(); // Default constructor
        
        System.out.println("\\n3. Library statistics after adding books:");
        displayLibraryStatistics();
        
        System.out.println("\\n4. Testing instanceof with valid book:");
        processBook(book1);
        
        System.out.println("\\n5. Testing instanceof with invalid objects:");
        processBook("Not a book");
        processBook(123);
        processBook(null);
        
        System.out.println("\\n6. Book operations using 'this':");
        book1.borrowBook("Alice Johnson");
        book2.borrowBook("Bob Wilson");
        book3.returnBook(); // Was already borrowed
        
        System.out.println("\\n7. Updated library statistics:");
        displayLibraryStatistics();
        
        System.out.println("\\n8. Testing final variable (ISBN cannot be changed):");
        System.out.println("Book1 ISBN: " + book1.getISBN());
        // book1.isbn = "NEW-ISBN"; // This would cause compilation error
        System.out.println("Note: ISBN is final and cannot be modified after initialization");
        
        System.out.println("\\n9. Modifying static variable (affects all books):");
        updateLibraryName("Advanced Learning Library");
        
        System.out.println("\\n10. All books now show updated library name:");
        processBook(book1);
        processBook(book2);
        
        System.out.println("\\n11. Book comparisons using 'this':");
        LibraryManagementSystem book5 = new LibraryManagementSystem("Another Java Book", "James Gosling", "ISBN-5555666677");
        System.out.println("Are book1 and book5 the same book? " + book1.isSameBook(book5));
        System.out.println("Do book1 and book5 have same author? " + book1.isSameAuthor(book5));
        
        System.out.println("\\n12. Multiple instanceof checks with mixed objects:");
        Object[] objects = {book1, book2, "String object", 456, book3, null, new java.util.ArrayList()};
        
        for (int i = 0; i < objects.length; i++) {
            System.out.printf("Object %d: ", i + 1);
            if (objects[i] instanceof LibraryManagementSystem) {
                LibraryManagementSystem book = (LibraryManagementSystem) objects[i];
                System.out.println("Book - " + book.getBookSummary());
            } else {
                System.out.println("Not a Book - " + 
                                 (objects[i] != null ? objects[i].getClass().getSimpleName() : "null"));
            }
        }
        
        System.out.println("\\n13. Testing overdue functionality:");
        // Simulate overdue by setting past borrow date
        book1.borrowDate = java.time.LocalDate.now().minusWeeks(3);
        System.out.println("Is book1 overdue? " + book1.isOverdue());
        book1.displayBookDetails();
        
        System.out.println("\\n=== Concepts Demonstrated ===");
        System.out.println("✓ Static: libraryName and counters shared across all instances");
        System.out.println("✓ This: Used in constructors and methods to refer to current object");
        System.out.println("✓ Final: ISBN cannot be changed once assigned");
        System.out.println("✓ Instanceof: Safe type checking before casting and operations");
    }
}
