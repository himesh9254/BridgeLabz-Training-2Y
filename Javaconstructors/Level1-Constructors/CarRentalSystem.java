/**
 * CarRental class demonstrating constructors with cost calculation functionality
 * Comprehensive car rental management system
 */
public class CarRentalSystem {
    // Instance variables
    private String customerName;
    private String carModel;
    private int rentalDays;
    private double dailyRate;
    private String rentalId;
    private java.time.LocalDate rentalDate;
    private String carCategory;
    private boolean insuranceAdded;
    private static int rentalCounter = 5000;
    private static int totalRentals = 0;
    
    // Constants for car categories and rates
    private static final String[] CAR_CATEGORIES = {"Economy", "Compact", "Standard", "Luxury", "SUV"};
    private static final double[] DAILY_RATES = {30.0, 40.0, 50.0, 80.0, 70.0};
    private static final double INSURANCE_RATE = 15.0; // per day
    private static final double TAX_RATE = 8.5; // percentage
    
    // Default constructor
    public CarRentalSystem() {
        this.customerName = "Customer";
        this.carModel = "Standard Vehicle";
        this.rentalDays = 1;
        this.carCategory = "Economy";
        this.dailyRate = 30.0;
        this.insuranceAdded = false;
        this.rentalDate = java.time.LocalDate.now();
        this.rentalId = generateRentalId();
        totalRentals++;
        System.out.println("Default constructor - Car rental created with default values");
    }
    
    // Basic parameterized constructor
    public CarRentalSystem(String customerName, String carModel, int rentalDays) {
        this.customerName = (customerName != null && !customerName.trim().isEmpty()) ? customerName : "Customer";
        this.carModel = (carModel != null && !carModel.trim().isEmpty()) ? carModel : "Standard Vehicle";
        this.rentalDays = (rentalDays > 0) ? rentalDays : 1;
        this.carCategory = "Economy"; // Default category
        this.dailyRate = 30.0; // Default rate
        this.insuranceAdded = false;
        this.rentalDate = java.time.LocalDate.now();
        this.rentalId = generateRentalId();
        totalRentals++;
        System.out.println("Basic constructor - Car rental created for: " + this.customerName);
    }
    
    // Extended parameterized constructor with category
    public CarRentalSystem(String customerName, String carModel, int rentalDays, String carCategory) {
        this.customerName = (customerName != null && !customerName.trim().isEmpty()) ? customerName : "Customer";
        this.carModel = (carModel != null && !carModel.trim().isEmpty()) ? carModel : "Standard Vehicle";
        this.rentalDays = (rentalDays > 0) ? rentalDays : 1;
        this.carCategory = validateCarCategory(carCategory);
        this.dailyRate = getDailyRate(this.carCategory);
        this.insuranceAdded = false;
        this.rentalDate = java.time.LocalDate.now();
        this.rentalId = generateRentalId();
        totalRentals++;
        System.out.println("Extended constructor - Car rental created for: " + this.customerName + " (" + this.carCategory + ")");
    }
    
    // Full parameterized constructor
    public CarRentalSystem(String customerName, String carModel, int rentalDays, String carCategory, 
                          boolean insuranceAdded, java.time.LocalDate rentalDate) {
        this.customerName = (customerName != null && !customerName.trim().isEmpty()) ? customerName : "Customer";
        this.carModel = (carModel != null && !carModel.trim().isEmpty()) ? carModel : "Standard Vehicle";
        this.rentalDays = (rentalDays > 0) ? rentalDays : 1;
        this.carCategory = validateCarCategory(carCategory);
        this.dailyRate = getDailyRate(this.carCategory);
        this.insuranceAdded = insuranceAdded;
        this.rentalDate = (rentalDate != null) ? rentalDate : java.time.LocalDate.now();
        this.rentalId = generateRentalId();
        totalRentals++;
        System.out.println("Full constructor - Complete car rental created for: " + this.customerName);
    }
    
    // Copy constructor
    public CarRentalSystem(CarRentalSystem other) {
        if (other != null) {
            this.customerName = other.customerName;
            this.carModel = other.carModel;
            this.rentalDays = other.rentalDays;
            this.carCategory = other.carCategory;
            this.dailyRate = other.dailyRate;
            this.insuranceAdded = other.insuranceAdded;
            this.rentalDate = other.rentalDate;
            this.rentalId = generateRentalId(); // New rental ID for copy
            totalRentals++;
            System.out.println("Copy constructor - Rental copied for: " + this.customerName);
        } else {
            this(); // Call default constructor
            System.out.println("Warning: Null rental passed to copy constructor");
        }
    }
    
    // Utility methods
    private static String generateRentalId() {
        return "CR" + (rentalCounter++);
    }
    
    private String validateCarCategory(String category) {
        if (category != null) {
            for (String validCategory : CAR_CATEGORIES) {
                if (validCategory.equalsIgnoreCase(category)) {
                    return validCategory;
                }
            }
        }
        return "Economy"; // Default category
    }
    
    private double getDailyRate(String category) {
        for (int i = 0; i < CAR_CATEGORIES.length; i++) {
            if (CAR_CATEGORIES[i].equals(category)) {
                return DAILY_RATES[i];
            }
        }
        return DAILY_RATES[0]; // Default rate
    }
    
    // Calculate base cost
    public double calculateBaseCost() {
        return dailyRate * rentalDays;
    }
    
    // Calculate insurance cost
    public double calculateInsuranceCost() {
        return insuranceAdded ? (INSURANCE_RATE * rentalDays) : 0.0;
    }
    
    // Calculate tax
    public double calculateTax() {
        double subtotal = calculateBaseCost() + calculateInsuranceCost();
        return subtotal * (TAX_RATE / 100.0);
    }
    
    // Calculate total cost
    public double calculateTotalCost() {
        return calculateBaseCost() + calculateInsuranceCost() + calculateTax();
    }
    
    // Apply discount
    public void applyDiscount(double discountPercentage) {
        if (discountPercentage > 0 && discountPercentage <= 100) {
            double originalRate = dailyRate;
            dailyRate -= (dailyRate * discountPercentage / 100);
            System.out.printf("Discount of %.1f%% applied. Daily rate: $%.2f -> $%.2f%n", 
                             discountPercentage, originalRate, dailyRate);
        } else {
            System.out.println("Invalid discount percentage!");
        }
    }
    
    // Extend rental
    public void extendRental(int additionalDays) {
        if (additionalDays > 0) {
            rentalDays += additionalDays;
            System.out.println("Rental extended by " + additionalDays + " days. Total: " + rentalDays + " days");
        } else {
            System.out.println("Invalid number of additional days!");
        }
    }
    
    // Add or remove insurance
    public void updateInsurance(boolean addInsurance) {
        if (addInsurance && !insuranceAdded) {
            insuranceAdded = true;
            System.out.println("Insurance added to rental. Additional cost: $" + INSURANCE_RATE + " per day");
        } else if (!addInsurance && insuranceAdded) {
            insuranceAdded = false;
            System.out.println("Insurance removed from rental.");
        } else {
            System.out.println("Insurance status unchanged.");
        }
    }
    
    // Get return date
    public java.time.LocalDate getReturnDate() {
        return rentalDate.plusDays(rentalDays);
    }
    
    // Display rental details
    public void displayRentalDetails() {
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("=== Car Rental Details ===");
        System.out.println("Rental ID: " + rentalId);
        System.out.println("Customer: " + customerName);
        System.out.println("Car Model: " + carModel);
        System.out.println("Category: " + carCategory);
        System.out.println("Rental Days: " + rentalDays);
        System.out.println("Rental Date: " + rentalDate.format(formatter));
        System.out.println("Return Date: " + getReturnDate().format(formatter));
        System.out.printf("Daily Rate: $%.2f%n", dailyRate);
        System.out.println("Insurance: " + (insuranceAdded ? "Yes" : "No"));
        
        System.out.println("\\n--- Cost Breakdown ---");
        System.out.printf("Base Cost: $%.2f%n", calculateBaseCost());
        System.out.printf("Insurance: $%.2f%n", calculateInsuranceCost());
        System.out.printf("Tax (%.1f%%): $%.2f%n", TAX_RATE, calculateTax());
        System.out.printf("TOTAL COST: $%.2f%n", calculateTotalCost());
        System.out.println("==========================");
    }
    
    // Display available car categories
    public static void displayCarCategories() {
        System.out.println("Available Car Categories:");
        for (int i = 0; i < CAR_CATEGORIES.length; i++) {
            System.out.printf("- %s: $%.2f per day%n", CAR_CATEGORIES[i], DAILY_RATES[i]);
        }
        System.out.printf("Insurance: $%.2f per day (optional)%n", INSURANCE_RATE);
        System.out.printf("Tax Rate: %.1f%%%n", TAX_RATE);
    }
    
    // Getters
    public String getCustomerName() { return customerName; }
    public String getCarModel() { return carModel; }
    public int getRentalDays() { return rentalDays; }
    public String getRentalId() { return rentalId; }
    public String getCarCategory() { return carCategory; }
    public boolean hasInsurance() { return insuranceAdded; }
    public static int getTotalRentals() { return totalRentals; }
    
    @Override
    public String toString() {
        return String.format("CarRental{id='%s', customer='%s', car='%s', days=%d, total=$%.2f}", 
                           rentalId, customerName, carModel, rentalDays, calculateTotalCost());
    }
    
    // Main method for testing
    public static void main(String[] args) {
        System.out.println("=== Car Rental System Demonstration ===\\n");
        
        System.out.println("Available car categories and rates:");
        displayCarCategories();
        System.out.println();
        
        System.out.println("1. Creating rentals with different constructors:");
        CarRentalSystem rental1 = new CarRentalSystem();
        CarRentalSystem rental2 = new CarRentalSystem("John Doe", "Toyota Camry", 5);
        CarRentalSystem rental3 = new CarRentalSystem("Jane Smith", "BMW X5", 3, "Luxury");
        CarRentalSystem rental4 = new CarRentalSystem("Bob Wilson", "Honda Civic", 7, "Compact", 
                                                     true, java.time.LocalDate.now().plusDays(2));
        
        System.out.println("\\n2. Rental details:");
        rental2.displayRentalDetails();
        rental3.displayRentalDetails();
        
        System.out.println("\\n3. Creating copy of rental:");
        CarRentalSystem rental5 = new CarRentalSystem(rental2);
        rental5.displayRentalDetails();
        
        System.out.println("\\n4. Modifying rentals:");
        rental1.updateInsurance(true);
        rental2.applyDiscount(10.0);
        rental3.extendRental(2);
        
        System.out.println("\\n5. Updated rental details:");
        rental1.displayRentalDetails();
        rental2.displayRentalDetails();
        
        System.out.println("\\n6. Total rentals created: " + getTotalRentals());
        
        System.out.println("\\n=== Constructor Features Demonstrated ===");
        System.out.println("✓ Default Constructor: Basic rental setup");
        System.out.println("✓ Basic Constructor: Customer, car, days");
        System.out.println("✓ Extended Constructor: Includes car category");
        System.out.println("✓ Full Constructor: All rental parameters");
        System.out.println("✓ Copy Constructor: Duplicate existing rental");
        System.out.println("✓ Cost Calculation: Comprehensive pricing system");
        System.out.println("✓ Input Validation: Safe parameter handling");
    }
}
