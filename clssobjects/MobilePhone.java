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
    public void displayPhoneDetails() {
        System.out.println("=== Mobile Phone Details ===");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Price: $" + price);
        System.out.println("============================");
    }
    
    // Method to display phone summary
    public void displayPhoneSummary() {
        System.out.println(brand + " " + model + " - $" + price);
    }
    
    // Getters and Setters
    public String getBrand() {
        return brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    // Main method to test the class
    public static void main(String[] args) {
        // Create mobile phone objects
        MobilePhone phone1 = new MobilePhone("Apple", "iPhone 15", 999.99);
        MobilePhone phone2 = new MobilePhone("Samsung", "Galaxy S24", 899.99);
        MobilePhone phone3 = new MobilePhone("Google", "Pixel 8", 699.99);
        MobilePhone phone4 = new MobilePhone("OnePlus", "11T", 549.99);
        
        // Display phone details
        System.out.println("=== Available Mobile Phones ===");
        phone1.displayPhoneDetails();
        phone2.displayPhoneDetails();
        phone3.displayPhoneDetails();
        phone4.displayPhoneDetails();
        
        // Display phone summaries
        System.out.println("=== Phone Summary ===");
        phone1.displayPhoneSummary();
        phone2.displayPhoneSummary();
        phone3.displayPhoneSummary();
        phone4.displayPhoneSummary();
    }
}
