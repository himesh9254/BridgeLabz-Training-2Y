class Book {
    String title;
    int publicationYear;

    Book(String title, int publicationYear) {
        this.title = title;
        this.publicationYear = publicationYear;
    }

    void displayInfo() {
        System.out.println("Book Title: " + title);
        System.out.println("Publication Year: " + publicationYear);
    }
}

class Author extends Book {
    String name;
    String bio;

    Author(String title, int publicationYear, String name, String bio) {
        super(title, publicationYear);
        this.name = name;
        this.bio = bio;
    }

    @Override
    void displayInfo() {
        super.displayInfo();
        System.out.println("Author Name: " + name);
        System.out.println("Author Bio: " + bio);
    }
}

public class LibraryManagement {
    public static void main(String[] args) {
        Book book1 = new Book("Clean Code", 2008);
        Author book2 = new Author("The Pragmatic Programmer", 1999, 
                                  "Andrew Hunt and David Thomas", 
                                  "Renowned software developers and authors");

        System.out.println("=== Library Management System ===\n");
        
        System.out.println("Book without author details:");
        book1.displayInfo();
        
        System.out.println("\nBook with author details:");
        book2.displayInfo();
    }
}