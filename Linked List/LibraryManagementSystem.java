public class LibraryManagementSystem {
    private static class Book {
        private final String title;
        private final String author;
        private final String genre;
        private final int bookId;
        private boolean available;

        private Book(String title, String author, String genre, int bookId, boolean available) {
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.bookId = bookId;
            this.available = available;
        }

        @Override
        public String toString() {
            return bookId + " | " + title + " | " + author + " | " + genre + " | " + (available ? "Available" : "Checked Out");
        }
    }

    private static class BookNode {
        private Book book;
        private BookNode prev;
        private BookNode next;

        private BookNode(Book book) {
            this.book = book;
        }
    }

    private static class LibraryList {
        private BookNode head;
        private BookNode tail;
        private int size;

        private void addAtBeginning(String title, String author, String genre, int bookId, boolean available) {
            BookNode node = new BookNode(new Book(title, author, genre, bookId, available));

            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.next = head;
                head.prev = node;
                head = node;
            }

            size++;
        }

        private void addAtEnd(String title, String author, String genre, int bookId, boolean available) {
            BookNode node = new BookNode(new Book(title, author, genre, bookId, available));

            if (tail == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }

            size++;
        }

        private boolean addAtPosition(String title, String author, String genre, int bookId, boolean available, int position) {
            if (position < 1 || position > size + 1) {
                return false;
            }

            if (position == 1) {
                addAtBeginning(title, author, genre, bookId, available);
                return true;
            }

            if (position == size + 1) {
                addAtEnd(title, author, genre, bookId, available);
                return true;
            }

            BookNode current = head;

            for (int index = 1; index < position - 1; index++) {
                current = current.next;
            }

            BookNode node = new BookNode(new Book(title, author, genre, bookId, available));
            node.next = current.next;
            node.prev = current;
            current.next.prev = node;
            current.next = node;
            size++;
            return true;
        }

        private boolean removeByBookId(int bookId) {
            BookNode node = findNodeByBookId(bookId);

            if (node == null) {
                return false;
            }

            if (node == head) {
                head = node.next;
            }

            if (node == tail) {
                tail = node.prev;
            }

            if (node.prev != null) {
                node.prev.next = node.next;
            }

            if (node.next != null) {
                node.next.prev = node.prev;
            }

            size--;
            return true;
        }

        private boolean updateAvailability(int bookId, boolean available) {
            BookNode node = findNodeByBookId(bookId);

            if (node == null) {
                return false;
            }

            node.book.available = available;
            return true;
        }

        private String searchByTitle(String title) {
            StringBuilder builder = new StringBuilder();
            BookNode current = head;

            while (current != null) {
                if (current.book.title.equalsIgnoreCase(title)) {
                    if (builder.length() > 0) {
                        builder.append(System.lineSeparator());
                    }

                    builder.append(current.book);
                }

                current = current.next;
            }

            return builder.length() == 0 ? "No books found" : builder.toString();
        }

        private String searchByAuthor(String author) {
            StringBuilder builder = new StringBuilder();
            BookNode current = head;

            while (current != null) {
                if (current.book.author.equalsIgnoreCase(author)) {
                    if (builder.length() > 0) {
                        builder.append(System.lineSeparator());
                    }

                    builder.append(current.book);
                }

                current = current.next;
            }

            return builder.length() == 0 ? "No books found" : builder.toString();
        }

        private String displayForward() {
            StringBuilder builder = new StringBuilder();
            BookNode current = head;

            while (current != null) {
                if (builder.length() > 0) {
                    builder.append(System.lineSeparator());
                }

                builder.append(current.book);
                current = current.next;
            }

            return builder.toString();
        }

        private String displayReverse() {
            StringBuilder builder = new StringBuilder();
            BookNode current = tail;

            while (current != null) {
                if (builder.length() > 0) {
                    builder.append(System.lineSeparator());
                }

                builder.append(current.book);
                current = current.prev;
            }

            return builder.toString();
        }

        private int countBooks() {
            return size;
        }

        private BookNode findNodeByBookId(int bookId) {
            BookNode current = head;

            while (current != null) {
                if (current.book.bookId == bookId) {
                    return current;
                }

                current = current.next;
            }

            return null;
        }
    }

    public static void main(String[] args) {
        LibraryList library = new LibraryList();
        library.addAtEnd("Clean Code", "Robert Martin", "Programming", 501, true);
        library.addAtEnd("The Hobbit", "J.R.R. Tolkien", "Fantasy", 502, true);
        library.addAtBeginning("Effective Java", "Joshua Bloch", "Programming", 500, true);
        library.addAtPosition("Dune", "Frank Herbert", "Science Fiction", 503, false, 3);

        System.out.println("Forward");
        System.out.println(library.displayForward());
        System.out.println();

        System.out.println("Reverse");
        System.out.println(library.displayReverse());
        System.out.println();

        System.out.println("Search Title");
        System.out.println(library.searchByTitle("Dune"));
        System.out.println();

        System.out.println("Search Author");
        System.out.println(library.searchByAuthor("Joshua Bloch"));
        System.out.println();

        library.updateAvailability(503, true);
        library.removeByBookId(502);

        System.out.println("Updated Library");
        System.out.println(library.displayForward());
        System.out.println();
        System.out.println("Book Count");
        System.out.println(library.countBooks());
    }
}
