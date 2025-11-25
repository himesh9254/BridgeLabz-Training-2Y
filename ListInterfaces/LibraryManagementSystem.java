import java.util.*;

class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean available;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        String status = available ? "Available" : "Issued";
        return String.format("Book[%s, %s by %s, %s]", bookId, title, author, status);
    }
}

public class LibraryManagementSystem {
    private List<Book> allBooks;
    private Set<String> memberIds;
    private Queue<Book> issuingQueue;
    private Stack<Book> recentlyReturned;
    private Map<String, Book> bookMap;

    public LibraryManagementSystem() {
        allBooks = new ArrayList<>();
        memberIds = new HashSet<>();
        issuingQueue = new LinkedList<>();
        recentlyReturned = new Stack<>();
        bookMap = new HashMap<>();
    }

    public void addBook(Book book) {
        allBooks.add(book);
        bookMap.put(book.getBookId(), book);
        System.out.println("Book added: " + book.getTitle());
    }

    public boolean registerMember(String memberId) {
        if (memberIds.contains(memberId)) {
            System.out.println("Member already registered: " + memberId);
            return false;
        }
        memberIds.add(memberId);
        System.out.println("Member registered: " + memberId);
        return true;
    }

    public void requestBook(String bookId) {
        Book book = bookMap.get(bookId);
        if (book == null) {
            System.out.println("Book not found: " + bookId);
            return;
        }
        if (!book.isAvailable()) {
            System.out.println("Book not available: " + book.getTitle());
            return;
        }
        issuingQueue.add(book);
        System.out.println("Book queued for issuing: " + book.getTitle());
    }

    public Book issueNextBook(String memberId) {
        if (!memberIds.contains(memberId)) {
            System.out.println("Invalid member ID: " + memberId);
            return null;
        }
        if (issuingQueue.isEmpty()) {
            System.out.println("No books in issuing queue.");
            return null;
        }
        Book book = issuingQueue.poll();
        book.setAvailable(false);
        System.out.println("Book issued to " + memberId + ": " + book.getTitle());
        return book;
    }

    public void returnBook(String bookId) {
        Book book = bookMap.get(bookId);
        if (book == null) {
            System.out.println("Book not found: " + bookId);
            return;
        }
        if (book.isAvailable()) {
            System.out.println("Book was not issued: " + book.getTitle());
            return;
        }
        book.setAvailable(true);
        recentlyReturned.push(book);
        System.out.println("Book returned: " + book.getTitle());
    }

    public Book reissueLastReturned(String memberId) {
        if (!memberIds.contains(memberId)) {
            System.out.println("Invalid member ID: " + memberId);
            return null;
        }
        if (recentlyReturned.isEmpty()) {
            System.out.println("No recently returned books.");
            return null;
        }
        Book book = recentlyReturned.pop();
        book.setAvailable(false);
        System.out.println("Book re-issued to " + memberId + ": " + book.getTitle());
        return book;
    }

    public void displayAllBooks() {
        System.out.println("\n=== All Books (" + allBooks.size() + ") ===");
        for (Book book : allBooks) {
            System.out.println("  " + book);
        }
    }

    public void displayAvailableBooks() {
        System.out.println("\n=== Available Books ===");
        for (Book book : allBooks) {
            if (book.isAvailable()) {
                System.out.println("  " + book);
            }
        }
    }

    public void displayMembers() {
        System.out.println("\n=== Registered Members (" + memberIds.size() + ") ===");
        for (String memberId : memberIds) {
            System.out.println("  " + memberId);
        }
    }

    public void displayIssuingQueue() {
        System.out.println("\n=== Books in Issuing Queue ===");
        if (issuingQueue.isEmpty()) {
            System.out.println("  Queue is empty");
            return;
        }
        for (Book book : issuingQueue) {
            System.out.println("  " + book.getTitle());
        }
    }

    public void displayRecentlyReturned() {
        System.out.println("\n=== Recently Returned Books (Stack) ===");
        if (recentlyReturned.isEmpty()) {
            System.out.println("  No recently returned books");
            return;
        }
        for (Book book : recentlyReturned) {
            System.out.println("  " + book.getTitle());
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();

        System.out.println("=== Adding Books ===");
        library.addBook(new Book("BK001", "Java Programming", "James Gosling"));
        library.addBook(new Book("BK002", "Clean Code", "Robert Martin"));
        library.addBook(new Book("BK003", "Design Patterns", "Gang of Four"));
        library.addBook(new Book("BK004", "Data Structures", "Mark Allen"));
        library.addBook(new Book("BK005", "Algorithms", "Thomas Cormen"));

        System.out.println("\n=== Registering Members ===");
        library.registerMember("MEM001");
        library.registerMember("MEM002");
        library.registerMember("MEM003");
        library.registerMember("MEM001");

        library.displayAllBooks();
        library.displayMembers();

        System.out.println("\n=== Book Requests ===");
        library.requestBook("BK001");
        library.requestBook("BK002");
        library.requestBook("BK003");

        library.displayIssuingQueue();

        System.out.println("\n=== Issuing Books ===");
        library.issueNextBook("MEM001");
        library.issueNextBook("MEM002");

        library.displayAllBooks();

        System.out.println("\n=== Returning Books ===");
        library.returnBook("BK001");
        library.returnBook("BK002");

        library.displayRecentlyReturned();

        System.out.println("\n=== Re-issuing Last Returned ===");
        library.reissueLastReturned("MEM003");

        library.displayAllBooks();
    }
}
