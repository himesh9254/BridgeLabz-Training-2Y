import java.util.ArrayList;

// Book class - can exist independently
class Book {
    private String title;
    private String author;
    
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    @Override
    public String toString() {
        return "Book: " + title + " by " + author;
    }
}

// Library class - aggregates Book objects
class Library {
    private String name;
    private ArrayList<Book> books;
    
    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }
    
    public void addBook(Book book) {
        books.add(book);
        System.out.println(book.getTitle() + " added to " + name);
    }
    
    public void removeBook(Book book) {
        books.remove(book);
        System.out.println(book.getTitle() + " removed from " + name);
    }
    
    public void displayBooks() {
        System.out.println("\nBooks in " + name + ":");
        for (Book book : books) {
            System.out.println("  " + book);
        }
    }
    
    public String getName() {
        return name;
    }
}

// Main class to demonstrate aggregation
public class AssistedProblem1_LibraryBooks {
    public static void main(String[] args) {
        // Create books independently
        Book book1 = new Book("Java Programming", "James Gosling");
        Book book2 = new Book("Data Structures", "Mark Weiss");
        Book book3 = new Book("Algorithms", "Robert Sedgewick");
        
        // Create libraries
        Library centralLibrary = new Library("Central Library");
        Library communityLibrary = new Library("Community Library");
        
        // Add books to libraries - demonstrating aggregation
        centralLibrary.addBook(book1);
        centralLibrary.addBook(book2);
        centralLibrary.addBook(book3);
        
        // Same book can be added to different libraries
        communityLibrary.addBook(book1);
        communityLibrary.addBook(book3);
        
        // Display books
        centralLibrary.displayBooks();
        communityLibrary.displayBooks();
        
        // Books exist independently - removing from one library doesn't affect the other
        centralLibrary.removeBook(book1);
        System.out.println("\nAfter removing from Central Library:");
        centralLibrary.displayBooks();
        communityLibrary.displayBooks();
    }
}
