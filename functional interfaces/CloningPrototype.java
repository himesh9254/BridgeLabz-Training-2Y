// 2. Cloning Prototype Objects
// Clone a predefined object model
// Use Cloneable marker interface

class Product implements Cloneable {
    private String name;
    private double price;
    private String category;
    private int stockQuantity;
    
    public Product(String name, double price, String category, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
    }
    
    // Getters and setters
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    @Override
    public String toString() {
        return "Product{name='" + name + "', price=$" + price + 
               ", category='" + category + "', stock=" + stockQuantity + "}";
    }
}

class Employee implements Cloneable {
    private String name;
    private String department;
    private double salary;
    private String email;
    
    public Employee(String name, String department, double salary, String email) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.email = email;
    }
    
    // Getters and setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setSalary(double salary) { this.salary = salary; }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    @Override
    public String toString() {
        return "Employee{name='" + name + "', department='" + department + 
               "', salary=$" + salary + ", email='" + email + "'}";
    }
}

class Document {
    // Not implementing Cloneable - cannot be cloned
    private String title;
    private String content;
    
    public Document(String title, String content) {
        this.title = title;
        this.content = content;
    }
    
    @Override
    public String toString() {
        return "Document{title='" + title + "', content='" + content + "'}";
    }
}

class PrototypeManager {
    public static Object cloneObject(Object prototype) {
        if (prototype instanceof Cloneable) {
            try {
                // Use reflection to call protected clone method
                return prototype.getClass().getMethod("clone").invoke(prototype);
            } catch (Exception e) {
                System.err.println("✗ Cloning failed: " + e.getMessage());
                return null;
            }
        } else {
            System.out.println("✗ Cannot clone: " + prototype.getClass().getSimpleName() + 
                             " (not Cloneable)");
            return null;
        }
    }
}

public class CloningPrototype {
    public static void main(String[] args) {
        System.out.println("=== Prototype Cloning System ===\n");
        
        // Create prototype objects
        Product prototypeProduct = new Product("Laptop", 999.99, "Electronics", 50);
        Employee prototypeEmployee = new Employee("John Doe", "IT", 75000, "john@company.com");
        Document document = new Document("Report", "This is a sample report");
        
        System.out.println("ORIGINAL PROTOTYPES:");
        System.out.println("-".repeat(60));
        System.out.println("Product: " + prototypeProduct);
        System.out.println("Employee: " + prototypeEmployee);
        System.out.println("Document: " + document);
        
        System.out.println("\nCLONING OBJECTS:");
        System.out.println("-".repeat(60));
        
        // Clone Product
        try {
            Product clonedProduct1 = (Product) prototypeProduct.clone();
            clonedProduct1.setName("Gaming Laptop");
            clonedProduct1.setPrice(1499.99);
            
            Product clonedProduct2 = (Product) prototypeProduct.clone();
            clonedProduct2.setName("Office Laptop");
            clonedProduct2.setPrice(799.99);
            clonedProduct2.setStockQuantity(100);
            
            System.out.println("✓ Cloned Product 1: " + clonedProduct1);
            System.out.println("✓ Cloned Product 2: " + clonedProduct2);
            System.out.println("  Original Product (unchanged): " + prototypeProduct);
        } catch (CloneNotSupportedException e) {
            System.err.println("✗ Product cloning failed");
        }
        
        System.out.println();
        
        // Clone Employee
        try {
            Employee clonedEmployee1 = (Employee) prototypeEmployee.clone();
            clonedEmployee1.setName("Jane Smith");
            clonedEmployee1.setEmail("jane@company.com");
            clonedEmployee1.setSalary(80000);
            
            Employee clonedEmployee2 = (Employee) prototypeEmployee.clone();
            clonedEmployee2.setName("Bob Johnson");
            clonedEmployee2.setEmail("bob@company.com");
            
            System.out.println("✓ Cloned Employee 1: " + clonedEmployee1);
            System.out.println("✓ Cloned Employee 2: " + clonedEmployee2);
            System.out.println("  Original Employee (unchanged): " + prototypeEmployee);
        } catch (CloneNotSupportedException e) {
            System.err.println("✗ Employee cloning failed");
        }
        
        System.out.println();
        
        // Try to clone Document (should fail)
        Object clonedDocument = PrototypeManager.cloneObject(document);
        
        // Demonstrate prototype pattern benefit
        System.out.println("\nBENEFIT DEMONSTRATION:");
        System.out.println("-".repeat(60));
        System.out.println("Creating 5 similar products using cloning:");
        
        try {
            for (int i = 1; i <= 5; i++) {
                Product clone = (Product) prototypeProduct.clone();
                clone.setName("Laptop Model " + i);
                clone.setPrice(999.99 + (i * 100));
                System.out.println("  Product " + i + ": " + clone);
            }
        } catch (CloneNotSupportedException e) {
            System.err.println("Cloning failed");
        }
    }
}
