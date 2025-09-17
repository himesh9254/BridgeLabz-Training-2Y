/**
 * Vehicle Rental System demonstrating:
 * - Abstract classes with abstract methods
 * - Inheritance and polymorphism
 * - Interface implementation (Insurable)
 * - Encapsulation with sensitive data protection
 * - Rental cost calculation with different vehicle types
 */

import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

// Interface for vehicles that can be insured
interface Insurable {
    double calculateInsurance();
    String getInsuranceDetails();
}

// Abstract Vehicle class with encapsulation
abstract class Vehicle {
    // Private fields - encapsulated sensitive data
    private String vehicleNumber;
    private String type;
    private double rentalRate; // per day
    private String ownerName;
    private boolean isAvailable;
    private LocalDate purchaseDate;
    private String insurancePolicyNumber; // Sensitive data - highly encapsulated
    
    // Constructor
    public Vehicle(String vehicleNumber, String type, double rentalRate, String ownerName) {
        validateVehicleData(vehicleNumber, type, rentalRate, ownerName);
        
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.rentalRate = rentalRate;
        this.ownerName = ownerName;
        this.isAvailable = true;
        this.purchaseDate = LocalDate.now();
        this.insurancePolicyNumber = generateInsurancePolicyNumber();
    }
    
    // Data validation method
    private void validateVehicleData(String vehicleNumber, String type, double rentalRate, String ownerName) {
        if (vehicleNumber == null || vehicleNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle number cannot be null or empty");
        }
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle type cannot be null or empty");
        }
        if (rentalRate <= 0) {
            throw new IllegalArgumentException("Rental rate must be positive");
        }
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be null or empty");
        }
    }
    
    // Abstract method to be implemented by subclasses
    public abstract double calculateRentalCost(int days);
    
    // Method to display vehicle details (protecting sensitive data)
    public void displayVehicleDetails() {
        System.out.println("=== Vehicle Details ===");
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Type: " + type);
        System.out.printf("Rental Rate: $%.2f per day%n", rentalRate);
        System.out.println("Owner: " + ownerName);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("Purchase Date: " + purchaseDate);
        
        // Display insurance details if vehicle is insurable
        if (this instanceof Insurable) {
            System.out.printf("Insurance Cost: $%.2f%n", ((Insurable) this).calculateInsurance());
            System.out.println("Insurance: " + ((Insurable) this).getInsuranceDetails());
            // Insurance policy number is sensitive - only show masked version
            System.out.println("Policy Number: " + getMaskedPolicyNumber());
        } else {
            System.out.println("Insurance: Not Available");
        }
        
        System.out.println("======================");
    }
    
    // Method to get masked policy number (protecting sensitive data)
    private String getMaskedPolicyNumber() {
        if (insurancePolicyNumber == null || insurancePolicyNumber.length() < 4) {
            return "****";
        }
        String lastFour = insurancePolicyNumber.substring(insurancePolicyNumber.length() - 4);
        return "****-****-" + lastFour;
    }
    
    // Encapsulated getters
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    
    public String getType() {
        return type;
    }
    
    public double getRentalRate() {
        return rentalRate;
    }
    
    public String getOwnerName() {
        return ownerName;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }
    
    // Highly restricted access to sensitive data
    protected String getInsurancePolicyNumber() {
        // Only accessible within the class hierarchy
        return insurancePolicyNumber;
    }
    
    // Encapsulated setters with validation
    public void setRentalRate(double rentalRate) {
        if (rentalRate <= 0) {
            throw new IllegalArgumentException("Rental rate must be positive");
        }
        this.rentalRate = rentalRate;
    }
    
    public void setOwnerName(String ownerName) {
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be null or empty");
        }
        this.ownerName = ownerName;
    }
    
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
    
    // Secure method to update insurance policy (with authorization check)
    protected void updateInsurancePolicyNumber(String newPolicyNumber, String authorizationCode) {
        if (!"ADMIN_AUTH_2024".equals(authorizationCode)) {
            throw new SecurityException("Unauthorized access to insurance policy update");
        }
        if (newPolicyNumber == null || newPolicyNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Policy number cannot be null or empty");
        }
        this.insurancePolicyNumber = newPolicyNumber;
        System.out.println("Insurance policy updated successfully for vehicle: " + vehicleNumber);
    }
    
    // Method to rent vehicle
    public boolean rentVehicle() {
        if (!isAvailable) {
            return false;
        }
        isAvailable = false;
        System.out.println("Vehicle " + vehicleNumber + " has been rented.");
        return true;
    }
    
    // Method to return vehicle
    public void returnVehicle() {
        isAvailable = true;
        System.out.println("Vehicle " + vehicleNumber + " has been returned and is now available.");
    }
    
    // Utility method to generate insurance policy number
    private String generateInsurancePolicyNumber() {
        Random random = new Random();
        return String.format("INS-%04d-%04d-%04d", 
            random.nextInt(10000), 
            random.nextInt(10000), 
            random.nextInt(10000));
    }
}

// Car class - implements Insurable
class Car extends Vehicle implements Insurable {
    private String fuelType;
    private int seatingCapacity;
    private boolean hasAC;
    private static final double CAR_INSURANCE_BASE_RATE = 50.0;
    private static final double LUXURY_SURCHARGE = 1.2; // 20% extra for luxury cars
    
    public Car(String vehicleNumber, double rentalRate, String ownerName, 
               String fuelType, int seatingCapacity, boolean hasAC) {
        super(vehicleNumber, "Car", rentalRate, ownerName);
        setFuelType(fuelType);
        setSeatingCapacity(seatingCapacity);
        this.hasAC = hasAC;
    }
    
    @Override
    public double calculateRentalCost(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Number of days must be positive");
        }
        
        double baseCost = getRentalRate() * days;
        double multiplier = 1.0;
        
        // Premium for AC
        if (hasAC) {
            multiplier += 0.1; // 10% extra for AC
        }
        
        // Premium for larger seating capacity
        if (seatingCapacity > 5) {
            multiplier += 0.15; // 15% extra for larger cars
        }
        
        // Discount for long-term rental
        if (days > 7) {
            multiplier -= 0.05; // 5% discount for weekly rentals
        }
        
        return baseCost * multiplier;
    }
    
    @Override
    public double calculateInsurance() {
        double baseInsurance = CAR_INSURANCE_BASE_RATE;
        
        // Higher insurance for luxury features
        if (hasAC && seatingCapacity > 5) {
            baseInsurance *= LUXURY_SURCHARGE;
        }
        
        return baseInsurance;
    }
    
    @Override
    public String getInsuranceDetails() {
        return String.format("Car Insurance - Base: $%.2f, Total: $%.2f", 
            CAR_INSURANCE_BASE_RATE, calculateInsurance());
    }
    
    // Encapsulated getters and setters
    public String getFuelType() {
        return fuelType;
    }
    
    public void setFuelType(String fuelType) {
        if (fuelType == null || fuelType.trim().isEmpty()) {
            throw new IllegalArgumentException("Fuel type cannot be null or empty");
        }
        this.fuelType = fuelType;
    }
    
    public int getSeatingCapacity() {
        return seatingCapacity;
    }
    
    public void setSeatingCapacity(int seatingCapacity) {
        if (seatingCapacity < 2 || seatingCapacity > 12) {
            throw new IllegalArgumentException("Seating capacity must be between 2 and 12");
        }
        this.seatingCapacity = seatingCapacity;
    }
    
    public boolean hasAC() {
        return hasAC;
    }
    
    public void setHasAC(boolean hasAC) {
        this.hasAC = hasAC;
    }
    
    @Override
    public void displayVehicleDetails() {
        super.displayVehicleDetails();
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Seating Capacity: " + seatingCapacity);
        System.out.println("Air Conditioning: " + (hasAC ? "Yes" : "No"));
        System.out.println("======================");
    }
}

// Bike class - implements Insurable
class Bike extends Vehicle implements Insurable {
    private int engineCapacity; // in CC
    private boolean isElectric;
    private static final double BIKE_INSURANCE_BASE_RATE = 20.0;
    private static final double ELECTRIC_DISCOUNT = 0.8; // 20% discount for electric bikes
    
    public Bike(String vehicleNumber, double rentalRate, String ownerName, 
                int engineCapacity, boolean isElectric) {
        super(vehicleNumber, "Bike", rentalRate, ownerName);
        setEngineCapacity(engineCapacity);
        this.isElectric = isElectric;
    }
    
    @Override
    public double calculateRentalCost(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Number of days must be positive");
        }
        
        double baseCost = getRentalRate() * days;
        double multiplier = 1.0;
        
        // Premium for high-performance bikes
        if (engineCapacity > 500) {
            multiplier += 0.2; // 20% extra for high-performance bikes
        }
        
        // Discount for electric bikes (eco-friendly)
        if (isElectric) {
            multiplier -= 0.1; // 10% discount for electric bikes
        }
        
        return baseCost * multiplier;
    }
    
    @Override
    public double calculateInsurance() {
        double baseInsurance = BIKE_INSURANCE_BASE_RATE;
        
        // Higher insurance for powerful bikes
        if (engineCapacity > 500) {
            baseInsurance *= 1.5; // 50% more for powerful bikes
        }
        
        // Discount for electric bikes
        if (isElectric) {
            baseInsurance *= ELECTRIC_DISCOUNT;
        }
        
        return baseInsurance;
    }
    
    @Override
    public String getInsuranceDetails() {
        return String.format("Bike Insurance - Base: $%.2f, Total: $%.2f", 
            BIKE_INSURANCE_BASE_RATE, calculateInsurance());
    }
    
    // Encapsulated getters and setters
    public int getEngineCapacity() {
        return engineCapacity;
    }
    
    public void setEngineCapacity(int engineCapacity) {
        if (engineCapacity < 50 || engineCapacity > 2000) {
            throw new IllegalArgumentException("Engine capacity must be between 50 and 2000 CC");
        }
        this.engineCapacity = engineCapacity;
    }
    
    public boolean isElectric() {
        return isElectric;
    }
    
    public void setElectric(boolean electric) {
        this.isElectric = electric;
    }
    
    @Override
    public void displayVehicleDetails() {
        super.displayVehicleDetails();
        System.out.println("Engine Capacity: " + engineCapacity + " CC");
        System.out.println("Electric: " + (isElectric ? "Yes" : "No"));
        System.out.println("======================");
    }
}

// Truck class - no insurance available
class Truck extends Vehicle {
    private double loadCapacity; // in tons
    private String licenseRequired;
    private static final double HEAVY_LOAD_MULTIPLIER = 1.5;
    
    public Truck(String vehicleNumber, double rentalRate, String ownerName, 
                 double loadCapacity, String licenseRequired) {
        super(vehicleNumber, "Truck", rentalRate, ownerName);
        setLoadCapacity(loadCapacity);
        setLicenseRequired(licenseRequired);
    }
    
    @Override
    public double calculateRentalCost(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Number of days must be positive");
        }
        
        double baseCost = getRentalRate() * days;
        double multiplier = 1.0;
        
        // Premium for heavy-duty trucks
        if (loadCapacity > 10.0) {
            multiplier *= HEAVY_LOAD_MULTIPLIER;
        }
        
        // Premium for special license requirements
        if ("CDL".equals(licenseRequired)) {
            multiplier += 0.2; // 20% extra for CDL requirement
        }
        
        return baseCost * multiplier;
    }
    
    // Encapsulated getters and setters
    public double getLoadCapacity() {
        return loadCapacity;
    }
    
    public void setLoadCapacity(double loadCapacity) {
        if (loadCapacity <= 0 || loadCapacity > 50) {
            throw new IllegalArgumentException("Load capacity must be between 0 and 50 tons");
        }
        this.loadCapacity = loadCapacity;
    }
    
    public String getLicenseRequired() {
        return licenseRequired;
    }
    
    public void setLicenseRequired(String licenseRequired) {
        if (licenseRequired == null || licenseRequired.trim().isEmpty()) {
            throw new IllegalArgumentException("License requirement cannot be null or empty");
        }
        this.licenseRequired = licenseRequired;
    }
    
    @Override
    public void displayVehicleDetails() {
        super.displayVehicleDetails();
        System.out.println("Load Capacity: " + loadCapacity + " tons");
        System.out.println("License Required: " + licenseRequired);
        System.out.println("======================");
    }
}

// Vehicle Rental System main class
public class VehicleRentalSystem {
    private List<Vehicle> vehicles;
    private static int vehicleCounter = 1;
    
    public VehicleRentalSystem() {
        this.vehicles = new ArrayList<>();
    }
    
    // Method to add vehicle (demonstrates polymorphism)
    public void addVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        vehicles.add(vehicle);
        System.out.println("Vehicle added successfully: " + vehicle.getVehicleNumber());
    }
    
    // Method to display all vehicles (demonstrates polymorphism)
    public void displayAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }
        
        System.out.println("\n=== ALL VEHICLES ===");
        for (Vehicle vehicle : vehicles) {
            vehicle.displayVehicleDetails(); // Polymorphic call
            System.out.println();
        }
    }
    
    // Method to calculate rental and insurance costs (demonstrates polymorphism)
    public void calculateRentalAndInsuranceCosts(int days) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }
        
        System.out.println("\n=== RENTAL & INSURANCE COST CALCULATION ===");
        double totalRentalCost = 0;
        double totalInsuranceCost = 0;
        
        for (Vehicle vehicle : vehicles) {
            double rentalCost = vehicle.calculateRentalCost(days); // Polymorphic call
            double insuranceCost = 0;
            
            if (vehicle instanceof Insurable) {
                insuranceCost = ((Insurable) vehicle).calculateInsurance(); // Interface method call
            }
            
            totalRentalCost += rentalCost;
            totalInsuranceCost += insuranceCost;
            
            System.out.printf("%s (%s) for %d days:%n", 
                vehicle.getVehicleNumber(), 
                vehicle.getClass().getSimpleName(), 
                days);
            System.out.printf("  Rental: $%.2f | Insurance: $%.2f | Total: $%.2f%n", 
                rentalCost, insuranceCost, rentalCost + insuranceCost);
        }
        
        System.out.printf("Fleet Total - Rental: $%.2f | Insurance: $%.2f | Combined: $%.2f%n", 
            totalRentalCost, totalInsuranceCost, totalRentalCost + totalInsuranceCost);
        System.out.println("============================================");
    }
    
    // Method to find vehicle by number
    public Vehicle findVehicleByNumber(String vehicleNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleNumber().equals(vehicleNumber)) {
                return vehicle;
            }
        }
        return null;
    }
    
    // Method to get available vehicles
    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isAvailable()) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }
    
    // Method to get insurable vehicles
    public List<Vehicle> getInsurableVehicles() {
        List<Vehicle> insurableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Insurable) {
                insurableVehicles.add(vehicle);
            }
        }
        return insurableVehicles;
    }
    
    // Method to rent vehicle
    public boolean rentVehicle(String vehicleNumber) {
        Vehicle vehicle = findVehicleByNumber(vehicleNumber);
        if (vehicle == null) {
            System.out.println("Vehicle not found: " + vehicleNumber);
            return false;
        }
        
        return vehicle.rentVehicle();
    }
    
    // Method to return vehicle
    public void returnVehicle(String vehicleNumber) {
        Vehicle vehicle = findVehicleByNumber(vehicleNumber);
        if (vehicle == null) {
            System.out.println("Vehicle not found: " + vehicleNumber);
            return;
        }
        
        vehicle.returnVehicle();
    }
    
    // Utility method to generate vehicle number
    public static String generateVehicleNumber(String prefix) {
        return prefix + "-" + String.format("%04d", vehicleCounter++);
    }
    
    // Main method demonstrating all concepts
    public static void main(String[] args) {
        System.out.println("=== Vehicle Rental System Demo ===\n");
        
        VehicleRentalSystem rentalSystem = new VehicleRentalSystem();
        
        try {
            // Create different types of vehicles (Polymorphism)
            System.out.println("1. Creating Vehicles (Demonstrating Encapsulation & Inheritance):");
            
            Car car1 = new Car(
                generateVehicleNumber("CAR"), 
                80.0, 
                "John Smith", 
                "Petrol", 
                5, 
                true
            );
            
            Car car2 = new Car(
                generateVehicleNumber("CAR"), 
                120.0, 
                "Alice Johnson", 
                "Diesel", 
                7, 
                true
            );
            
            Bike bike1 = new Bike(
                generateVehicleNumber("BIKE"), 
                30.0, 
                "Bob Wilson", 
                150, 
                false
            );
            
            Bike bike2 = new Bike(
                generateVehicleNumber("BIKE"), 
                25.0, 
                "Carol Davis", 
                0, // Electric bike - no engine capacity
                true
            );
            
            Truck truck1 = new Truck(
                generateVehicleNumber("TRUCK"), 
                200.0, 
                "David Miller", 
                5.0, 
                "Regular"
            );
            
            Truck truck2 = new Truck(
                generateVehicleNumber("TRUCK"), 
                350.0, 
                "Emma Brown", 
                15.0, 
                "CDL"
            );
            
            // Add vehicles to system
            rentalSystem.addVehicle(car1);
            rentalSystem.addVehicle(car2);
            rentalSystem.addVehicle(bike1);
            rentalSystem.addVehicle(bike2);
            rentalSystem.addVehicle(truck1);
            rentalSystem.addVehicle(truck2);
            
            System.out.println("\n2. Displaying All Vehicles (Demonstrating Polymorphism):");
            rentalSystem.displayAllVehicles();
            
            System.out.println("\n3. Rental and Insurance Cost Calculation (Polymorphic Method Calls):");
            rentalSystem.calculateRentalAndInsuranceCosts(5); // 5-day rental
            
            System.out.println("\n4. Testing Encapsulation (Getters/Setters with Validation):");
            
            // Test valid updates
            System.out.println("Testing valid updates:");
            car1.setRentalRate(85.0);
            bike1.setEngineCapacity(200);
            truck1.setLoadCapacity(7.0);
            System.out.println("✓ Valid updates successful");
            
            // Test invalid updates (will throw exceptions)
            System.out.println("\nTesting invalid updates (Exception handling):");
            try {
                car2.setSeatingCapacity(15); // Should throw exception
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Caught expected exception: " + e.getMessage());
            }
            
            try {
                bike2.setEngineCapacity(3000); // Should throw exception
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Caught expected exception: " + e.getMessage());
            }
            
            System.out.println("\n5. Testing Security (Sensitive Data Protection):");
            
            // Test unauthorized insurance policy update
            try {
                car1.updateInsurancePolicyNumber("NEW-POLICY-123", "WRONG_CODE");
            } catch (SecurityException e) {
                System.out.println("✓ Security exception caught: " + e.getMessage());
            }
            
            // Test authorized insurance policy update
            try {
                car1.updateInsurancePolicyNumber("NEW-POLICY-123", "ADMIN_AUTH_2024");
                System.out.println("✓ Authorized policy update successful");
            } catch (Exception e) {
                System.out.println("✗ Authorized update failed: " + e.getMessage());
            }
            
            System.out.println("\n6. Vehicle Rental Operations:");
            
            // Check available vehicles
            List<Vehicle> availableVehicles = rentalSystem.getAvailableVehicles();
            System.out.println("Available vehicles: " + availableVehicles.size());
            
            // Rent some vehicles
            rentalSystem.rentVehicle("CAR-0001");
            rentalSystem.rentVehicle("BIKE-0001");
            rentalSystem.rentVehicle("TRUCK-0001");
            
            // Check available vehicles again
            availableVehicles = rentalSystem.getAvailableVehicles();
            System.out.println("Available vehicles after rental: " + availableVehicles.size());
            
            // Return a vehicle
            rentalSystem.returnVehicle("CAR-0001");
            availableVehicles = rentalSystem.getAvailableVehicles();
            System.out.println("Available vehicles after return: " + availableVehicles.size());
            
            System.out.println("\n7. Interface Demonstration (Insurable Vehicles):");
            System.out.println("=== INSURANCE BREAKDOWN ===");
            for (Vehicle vehicle : rentalSystem.getInsurableVehicles()) {
                if (vehicle instanceof Insurable) {
                    Insurable insurableVehicle = (Insurable) vehicle;
                    System.out.printf("%s: %s%n", 
                        vehicle.getVehicleNumber(), 
                        insurableVehicle.getInsuranceDetails());
                }
            }
            System.out.println("===========================");
            
            System.out.println("\n8. Long-term Rental Calculation (7 days):");
            rentalSystem.calculateRentalAndInsuranceCosts(7);
            
            System.out.println("\n=== Concepts Demonstrated ===");
            System.out.println("✓ Abstract Classes: Vehicle class with abstract calculateRentalCost()");
            System.out.println("✓ Inheritance: Car, Bike, Truck extend Vehicle");
            System.out.println("✓ Polymorphism: Vehicle references calling overridden methods");
            System.out.println("✓ Interface: Insurable interface with calculateInsurance() and getInsuranceDetails()");
            System.out.println("✓ Encapsulation: Private fields with sensitive data protection");
            System.out.println("✓ Security: Restricted access to insurance policy numbers");
            System.out.println("✓ Data Validation: Input validation preventing invalid vehicle data");
            System.out.println("✓ Exception Handling: Proper error handling for invalid inputs");
            
        } catch (Exception e) {
            System.err.println("Error in Vehicle Rental System: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
