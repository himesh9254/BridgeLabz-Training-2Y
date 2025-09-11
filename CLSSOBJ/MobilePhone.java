public class MobilePhone {
    // Attributes
    private String brand;
    private String model;
    private double price;
    
    // Constructor
    public MobilePhone(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }
    
    // Method to display all phone details
    public void displayDetails() {
        System.out.println("Mobile Phone Details:");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Price: $" + price);
        System.out.println("-------------------");
    }
    
    // Additional method to get phone info as string
    public String getPhoneInfo() {
        return brand + " " + model + " - $" + price;
    }
    
    // Main method to test the class
    public static void main(String[] args) {
        // Creating MobilePhone objects
        MobilePhone phone1 = new MobilePhone("Apple", "iPhone 14", 999.99);
        MobilePhone phone2 = new MobilePhone("Samsung", "Galaxy S23", 849.99);
        MobilePhone phone3 = new MobilePhone("Google", "Pixel 7", 599.99);
        
        // Displaying details
        phone1.displayDetails();
        phone2.displayDetails();
        phone3.displayDetails();
        
        // Using getPhoneInfo method
        System.out.println("Quick Info:");
        System.out.println(phone1.getPhoneInfo());
        System.out.println(phone2.getPhoneInfo());
        System.out.println(phone3.getPhoneInfo());
    }
}
