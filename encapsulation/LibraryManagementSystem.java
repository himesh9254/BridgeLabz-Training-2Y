/**
 * Library Management System demonstrating all OOP concepts
 * Includes abstract classes, interfaces, encapsulation, inheritance, and polymorphism
 */

import java.util.*;
import java.time.LocalDate;

interface Reservable {
    boolean reserveItem(String borrowerName, String borrowerId);
    boolean checkAvailability();
}

abstract class LibraryItem {
    private String itemId;
    private String title;
    private String author;
    private boolean isAvailable;
    private LocalDate addedDate;
    private String borrowerName;
    private String borrowerId;
    private LocalDate borrowedDate;
    
    public LibraryItem(String itemId, String title, String author) {
        if (itemId == null || title == null || author == null) {
            throw new IllegalArgumentException("Item details cannot be null");
        }
        this.itemId = itemId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.addedDate = LocalDate.now();
    }
    
    public abstract int getLoanDuration(); // Abstract method
    
    public void getItemDetails() {
        System.out.println("=== Library Item Details ===");
        System.out.println("ID: " + itemId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Type: " + this.getClass().getSimpleName());
        System.out.println("Available: " + isAvailable);
        System.out.println("Loan Duration: " + getLoanDuration() + " days");
        if (!isAvailable) {
            System.out.println("Borrowed by: " + borrowerName + " (" + borrowerId + ")");
            System.out.println("Borrowed on: " + borrowedDate);
            System.out.println("Due date: " + borrowedDate.plusDays(getLoanDuration()));
        }
        System.out.println("Added on: " + addedDate);
        System.out.println("============================");
    }
    
    // Encapsulated getters and setters
    public String getItemId() { return itemId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }
    public LocalDate getAddedDate() { return addedDate; }
    
    protected void setBorrowerDetails(String name, String id, LocalDate date) {
        this.borrowerName = name;
        this.borrowerId = id;
        this.borrowedDate = date;
        this.isAvailable = false;
    }
    
    protected void clearBorrowerDetails() {
        this.borrowerName = null;
        this.borrowerId = null;
        this.borrowedDate = null;
        this.isAvailable = true;
    }
    
    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title;
        }
    }
}

class Book extends LibraryItem implements Reservable {
    private String isbn;
    private int pageCount;
    private String genre;
    private List<String> reservationQueue;
    
    public Book(String itemId, String title, String author, String isbn, int pageCount, String genre) {
        super(itemId, title, author);
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.genre = genre;
        this.reservationQueue = new ArrayList<>();
    }
    
    @Override
    public int getLoanDuration() {
        return pageCount > 500 ? 21 : 14; // Longer loan for thick books
    }
    
    @Override
    public boolean reserveItem(String borrowerName, String borrowerId) {
        if (!isAvailable()) {
            reservationQueue.add(borrowerName + ":" + borrowerId);
            System.out.println("Book reserved for " + borrowerName);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean checkAvailability() {
        return isAvailable();
    }
    
    public String getIsbn() { return isbn; }
    public int getPageCount() { return pageCount; }
    public String getGenre() { return genre; }
}

class Magazine extends LibraryItem implements Reservable {
    private int issueNumber;
    private String month;
    private List<String> reservationQueue;
    
    public Magazine(String itemId, String title, String author, int issueNumber, String month) {
        super(itemId, title, author);
        this.issueNumber = issueNumber;
        this.month = month;
        this.reservationQueue = new ArrayList<>();
    }
    
    @Override
    public int getLoanDuration() {
        return 7; // Short loan period for magazines
    }
    
    @Override
    public boolean reserveItem(String borrowerName, String borrowerId) {
        if (!isAvailable()) {
            reservationQueue.add(borrowerName + ":" + borrowerId);
            System.out.println("Magazine reserved for " + borrowerName);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean checkAvailability() {
        return isAvailable();
    }
    
    public int getIssueNumber() { return issueNumber; }
    public String getMonth() { return month; }
}

class DVD extends LibraryItem implements Reservable {
    private String director;
    private int duration; // in minutes
    private String rating;
    private List<String> reservationQueue;
    
    public DVD(String itemId, String title, String author, String director, int duration, String rating) {
        super(itemId, title, author);
        this.director = director;
        this.duration = duration;
        this.rating = rating;
        this.reservationQueue = new ArrayList<>();
    }
    
    @Override
    public int getLoanDuration() {
        return 5; // Short loan period for DVDs
    }
    
    @Override
    public boolean reserveItem(String borrowerName, String borrowerId) {
        if (!isAvailable()) {
            reservationQueue.add(borrowerName + ":" + borrowerId);
            System.out.println("DVD reserved for " + borrowerName);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean checkAvailability() {
        return isAvailable();
    }
    
    public String getDirector() { return director; }
    public int getDuration() { return duration; }
    public String getRating() { return rating; }
}

public class LibraryManagementSystem {
    private List<LibraryItem> items;
    private static int itemCounter = 1;
    
    public LibraryManagementSystem() {
        this.items = new ArrayList<>();
    }
    
    public void addItem(LibraryItem item) {
        items.add(item);
        System.out.println("Added: " + item.getTitle());
    }
    
    public void displayAllItems() {
        System.out.println("\n=== ALL LIBRARY ITEMS ===");
        for (LibraryItem item : items) {
            item.getItemDetails(); // Polymorphic call
            System.out.println();
        }
    }
    
    public void processReservations() {
        System.out.println("\n=== RESERVATION PROCESSING ===");
        for (LibraryItem item : items) {
            if (item instanceof Reservable) {
                Reservable reservableItem = (Reservable) item;
                System.out.println(item.getTitle() + " - Available: " + reservableItem.checkAvailability());
            }
        }
    }
    
    public static String generateItemId(String prefix) {
        return prefix + String.format("%04d", itemCounter++);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Library Management System Demo ===\n");
        
        LibraryManagementSystem library = new LibraryManagementSystem();
        
        // Create different types of library items
        Book book1 = new Book(generateItemId("B"), "Java Programming", "James Gosling", "978-0123456789", 600, "Programming");
        Magazine mag1 = new Magazine(generateItemId("M"), "Tech Today", "Editor Tech", 45, "September");
        DVD dvd1 = new DVD(generateItemId("D"), "The Matrix", "Wachowski", "Wachowski Sisters", 136, "R");
        
        library.addItem(book1);
        library.addItem(mag1);
        library.addItem(dvd1);
        
        library.displayAllItems();
        library.processReservations();
        
        System.out.println("\n=== Concepts Demonstrated ===");
        System.out.println("✓ Abstract Classes: LibraryItem with abstract getLoanDuration()");
        System.out.println("✓ Inheritance: Book, Magazine, DVD extend LibraryItem");
        System.out.println("✓ Polymorphism: LibraryItem references calling overridden methods");
        System.out.println("✓ Interface: Reservable interface implementation");
        System.out.println("✓ Encapsulation: Protected borrower data and validation");
    }
}
