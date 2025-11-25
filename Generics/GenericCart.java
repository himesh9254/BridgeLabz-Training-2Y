import java.util.ArrayList;
import java.util.List;

class Electronics {
    private String name;

    public Electronics(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Clothing {
    private String name;

    public Clothing(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Book {
    private String name;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Cart<T> {
    private List<T> items = new ArrayList<>();

    public void addItem(T item) {
        items.add(item);
        System.out.println("Item added to cart.");
    }

    public void removeItem(T item) {
        if (items.remove(item)) {
            System.out.println("Item removed from cart.");
        } else {
            System.out.println("Item not found in cart.");
        }
    }

    public void displayItems() {
        System.out.println("Cart contains " + items.size() + " item(s):");
        for (T item : items) {
            if (item instanceof Electronics) {
                System.out.println("- Electronics: " + ((Electronics) item).getName());
            } else if (item instanceof Clothing) {
                System.out.println("- Clothing: " + ((Clothing) item).getName());
            } else if (item instanceof Book) {
                System.out.println("- Book: " + ((Book) item).getName());
            } else {
                System.out.println("- " + item);
            }
        }
    }
}

public class GenericCart {
    public static void main(String[] args) {
        Cart<Electronics> electronicsCart = new Cart<>();
        electronicsCart.addItem(new Electronics("Laptop"));
        electronicsCart.addItem(new Electronics("Smartphone"));
        electronicsCart.addItem(new Electronics("Headphones"));
        electronicsCart.displayItems();

        System.out.println();

        Cart<Clothing> clothingCart = new Cart<>();
        clothingCart.addItem(new Clothing("T-Shirt"));
        clothingCart.addItem(new Clothing("Jeans"));
        clothingCart.displayItems();

        System.out.println();

        Cart<Book> bookCart = new Cart<>();
        bookCart.addItem(new Book("Java Programming"));
        bookCart.addItem(new Book("Data Structures"));
        bookCart.displayItems();
    }
}
