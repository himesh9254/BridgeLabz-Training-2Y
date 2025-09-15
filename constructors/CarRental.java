public class CarRental {
    private String customerName;
    private String carModel;
    private int rentalDays;
    private double dailyRate;
    private boolean insuranceIncluded;
    
    // Default constructor
    public CarRental() {
        this.customerName = "Unknown Customer";
        this.carModel = "Economy Car";
        this.rentalDays = 1;
        this.dailyRate = calculateDailyRate(carModel);
        this.insuranceIncluded = false;
    }
    
    // Parameterized constructor
    public CarRental(String customerName, String carModel, int rentalDays) {
        this.customerName = customerName;
        this.carModel = carModel;
        this.rentalDays = rentalDays > 0 ? rentalDays : 1;
        this.dailyRate = calculateDailyRate(carModel);
        this.insuranceIncluded = false;
    }
    
    // Parameterized constructor with insurance option
    public CarRental(String customerName, String carModel, int rentalDays, boolean insuranceIncluded) {
        this.customerName = customerName;
        this.carModel = carModel;
        this.rentalDays = rentalDays > 0 ? rentalDays : 1;
        this.dailyRate = calculateDailyRate(carModel);
        this.insuranceIncluded = insuranceIncluded;
    }
    
    // Parameterized constructor with custom daily rate
    public CarRental(String customerName, String carModel, int rentalDays, double customDailyRate, boolean insuranceIncluded) {
        this.customerName = customerName;
        this.carModel = carModel;
        this.rentalDays = rentalDays > 0 ? rentalDays : 1;
        this.dailyRate = customDailyRate > 0 ? customDailyRate : calculateDailyRate(carModel);
        this.insuranceIncluded = insuranceIncluded;
    }
    
    // Copy constructor
    public CarRental(CarRental other) {
        if (other != null) {
            this.customerName = other.customerName;
            this.carModel = other.carModel;
            this.rentalDays = other.rentalDays;
            this.dailyRate = other.dailyRate;
            this.insuranceIncluded = other.insuranceIncluded;
        } else {
            this.customerName = "Unknown Customer";
            this.carModel = "Economy Car";
            this.rentalDays = 1;
            this.dailyRate = calculateDailyRate("Economy Car");
            this.insuranceIncluded = false;
        }
    }
    
    // Helper method to calculate daily rate based on car model
    private double calculateDailyRate(String carModel) {
        switch (carModel.toLowerCase()) {
            case "economy car":
            case "economy":
                return 35.0;
            case "compact car":
            case "compact":
                return 45.0;
            case "mid-size car":
            case "mid-size":
                return 55.0;
            case "full-size car":
            case "full-size":
                return 65.0;
            case "luxury car":
            case "luxury":
                return 95.0;
            case "suv":
                return 75.0;
            case "convertible":
                return 85.0;
            case "sports car":
            case "sports":
                return 120.0;
            default:
                return 35.0; // Default to economy car rate
        }
    }
    
    // Getter methods
    public String getCustomerName() {
        return customerName;
    }
    
    public String getCarModel() {
        return carModel;
    }
    
    public int getRentalDays() {
        return rentalDays;
    }
    
    public double getDailyRate() {
        return dailyRate;
    }
    
    public boolean isInsuranceIncluded() {
        return insuranceIncluded;
    }
    
    // Setter methods
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public void setCarModel(String carModel) {
        this.carModel = carModel;
        this.dailyRate = calculateDailyRate(carModel); // Update rate when model changes
    }
    
    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays > 0 ? rentalDays : 1;
    }
    
    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate > 0 ? dailyRate : this.dailyRate;
    }
    
    public void setInsuranceIncluded(boolean insuranceIncluded) {
        this.insuranceIncluded = insuranceIncluded;
    }
    
    // Calculate base rental cost
    public double calculateBaseCost() {
        return rentalDays * dailyRate;
    }
    
    // Calculate insurance cost (20% of base cost if included)
    public double calculateInsuranceCost() {
        return insuranceIncluded ? calculateBaseCost() * 0.20 : 0.0;
    }
    
    // Calculate total cost with discounts for long rentals
    public double calculateTotalCost() {
        double baseCost = calculateBaseCost();
        double insuranceCost = calculateInsuranceCost();
        double subtotal = baseCost + insuranceCost;
        
        // Apply discount for rentals longer than 7 days
        if (rentalDays >= 7) {
            subtotal *= 0.90; // 10% discount
        }
        // Apply additional discount for rentals longer than 30 days
        else if (rentalDays >= 30) {
            subtotal *= 0.85; // 15% discount
        }
        
        return subtotal;
    }
    
    // Calculate total cost with tax
    public double calculateTotalCostWithTax(double taxRate) {
        double totalCost = calculateTotalCost();
        return totalCost + (totalCost * taxRate / 100);
    }
    
    // Get discount information
    public String getDiscountInfo() {
        if (rentalDays >= 30) {
            return "15% discount applied (30+ days rental)";
        } else if (rentalDays >= 7) {
            return "10% discount applied (7+ days rental)";
        } else {
            return "No discount applied";
        }
    }
    
    // Display rental information
    public void displayRental() {
        System.out.println("Car Rental Details:");
        System.out.println("Customer: " + customerName);
        System.out.println("Car Model: " + carModel);
        System.out.println("Rental Days: " + rentalDays);
        System.out.printf("Daily Rate: $%.2f%n", dailyRate);
        System.out.println("Insurance: " + (insuranceIncluded ? "Included" : "Not Included"));
        System.out.printf("Base Cost: $%.2f%n", calculateBaseCost());
        System.out.printf("Insurance Cost: $%.2f%n", calculateInsuranceCost());
        System.out.println("Discount: " + getDiscountInfo());
        System.out.printf("Total Cost: $%.2f%n", calculateTotalCost());
        System.out.printf("Total Cost (with 8%% tax): $%.2f%n", calculateTotalCostWithTax(8));
        System.out.println("------------------------");
    }
    
    // Main method for testing
    public static void main(String[] args) {
        // Using default constructor
        CarRental rental1 = new CarRental();
        System.out.println("Rental created with default constructor:");
        rental1.displayRental();
        
        // Using parameterized constructor
        CarRental rental2 = new CarRental("John Smith", "Luxury Car", 5);
        System.out.println("Rental created with parameterized constructor:");
        rental2.displayRental();
        
        // Using parameterized constructor with insurance
        CarRental rental3 = new CarRental("Alice Johnson", "SUV", 10, true);
        System.out.println("Rental created with insurance included:");
        rental3.displayRental();
        
        // Using parameterized constructor with custom rate
        CarRental rental4 = new CarRental("Bob Wilson", "Sports Car", 3, 150.0, true);
        System.out.println("Rental created with custom daily rate:");
        rental4.displayRental();
        
        // Using copy constructor
        CarRental rental5 = new CarRental(rental3);
        System.out.println("Rental created using copy constructor:");
        rental5.displayRental();
        
        // Test long rental discount
        CarRental rental6 = new CarRental("Charlie Brown", "Mid-Size Car", 8, false);
        System.out.println("Long rental (8 days) with discount:");
        rental6.displayRental();
        
        // Test very long rental discount
        CarRental rental7 = new CarRental("Diana Prince", "Compact Car", 35, true);
        System.out.println("Very long rental (35 days) with maximum discount:");
        rental7.displayRental();
        
        // Modify copied rental to show independence
        rental5.setCustomerName("Modified Customer");
        rental5.setCarModel("Convertible");
        rental5.setRentalDays(2);
        
        System.out.println("Original rental3:");
        rental3.displayRental();
        System.out.println("Modified copied rental5:");
        rental5.displayRental();
    }
}
