import java.util.*;

public class LibraryBookCatalog {
    private Map<String, String> catalog;
    
    public LibraryBookCatalog() {
        catalog = new HashMap<>();
    }
    
    public void addBook(String isbn, String title) {
        if (catalog.containsKey(isbn)) {
            System.out.println("ISBN already exists: " + isbn);
            return;
        }
        catalog.put(isbn, title);
        System.out.println("Added: " + isbn + " -> " + title);
    }
    
    public String searchByISBN(String isbn) {
        String title = catalog.get(isbn);
        if (title == null) {
            System.out.println("Book not found with ISBN: " + isbn);
            return null;
        }
        System.out.println("Found: " + title);
        return title;
    }
    
    public List<String> searchByTitle(String searchTitle) {
        List<String> foundISBNs = new ArrayList<>();
        String searchLower = searchTitle.toLowerCase();
        
        for (Map.Entry<String, String> entry : catalog.entrySet()) {
            if (entry.getValue().toLowerCase().contains(searchLower)) {
                foundISBNs.add(entry.getKey());
            }
        }
        
        if (foundISBNs.isEmpty()) {
            System.out.println("No books found matching: " + searchTitle);
        } else {
            System.out.println("Books matching '" + searchTitle + "':");
            for (String isbn : foundISBNs) {
                System.out.println("  " + isbn + " -> " + catalog.get(isbn));
            }
        }
        
        return foundISBNs;
    }
    
    public void removeBook(String isbn) {
        String removed = catalog.remove(isbn);
        if (removed != null) {
            System.out.println("Removed: " + isbn + " -> " + removed);
        } else {
            System.out.println("Book not found with ISBN: " + isbn);
        }
    }
    
    public void displayAllBooksSortedByISBN() {
        System.out.println("\n=== All Books (Sorted by ISBN) ===");
        TreeMap<String, String> sortedCatalog = new TreeMap<>(catalog);
        
        for (Map.Entry<String, String> entry : sortedCatalog.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
    
    public void displayAllBooksSortedByTitle() {
        System.out.println("\n=== All Books (Sorted by Title) ===");
        
        List<Map.Entry<String, String>> entries = new ArrayList<>(catalog.entrySet());
        entries.sort(Comparator.comparing(Map.Entry::getValue));
        
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getValue() + " (" + entry.getKey() + ")");
        }
    }
    
    public int getTotalBooks() {
        return catalog.size();
    }
    
    public static void main(String[] args) {
        LibraryBookCatalog library = new LibraryBookCatalog();
        
        System.out.println("=== Adding Books to Catalog ===");
        library.addBook("978-0134685991", "Effective Java");
        library.addBook("978-0596009205", "Head First Java");
        library.addBook("978-0132350884", "Clean Code");
        library.addBook("978-0201633610", "Design Patterns");
        library.addBook("978-0596007126", "Head First Design Patterns");
        library.addBook("978-0321125217", "Domain-Driven Design");
        library.addBook("978-1617292545", "Java in Action");
        library.addBook("978-0321356680", "Effective Java");
        
        library.displayAllBooksSortedByISBN();
        
        System.out.println("\n=== Searching by ISBN ===");
        library.searchByISBN("978-0134685991");
        library.searchByISBN("978-0000000000");
        
        System.out.println("\n=== Searching by Title ===");
        library.searchByTitle("Java");
        library.searchByTitle("Design");
        library.searchByTitle("Python");
        
        System.out.println("\n=== Removing Books ===");
        library.removeBook("978-0596009205");
        library.removeBook("978-0000000000");
        
        library.displayAllBooksSortedByISBN();
        library.displayAllBooksSortedByTitle();
        
        System.out.println("\nTotal books in catalog: " + library.getTotalBooks());
    }
}
