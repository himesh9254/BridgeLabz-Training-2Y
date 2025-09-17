/**
 * Vehicle Registration System demonstrating static, this, final, and instanceof concepts
 * Complete vehicle management with different vehicle types
 */
public class VehicleRegistrationSystem {
    // Static variables - shared across all vehicles
    private static String registrationAuthority = "State Motor Vehicle Department";
    private static int totalVehiclesRegistered = 0;
    private static double totalRegistrationFees = 0.0;
    private static String[] validVehicleTypes = {"Car", "Motorcycle", "Truck", "Bus", "Van"};
    
    // Instance variables
    private String ownerName;
    private final String vehicleId; // final - unique identifier that cannot be changed
    private String vehicleType;
    private String model;
    private int year;
    private double registrationFee;
    private boolean isActive;
    private final String registrationDate; // final - cannot be modified after registration
    
    // Constructor using 'this' to initialize all fields
    public VehicleRegistrationSystem(String ownerName, String vehicleId, String vehicleType, 
                                   String model, int year, double registrationFee) {
        // Using 'this' to distinguish between parameters and instance variables
        this.ownerName = ownerName;
        this.vehicleId = vehicleId; // final variable initialization
        this.vehicleType = vehicleType;
        this.model = model;
        this.year = year;
        this.registrationFee = registrationFee;
        this.isActive = true;
        this.registrationDate = java.time.LocalDate.now().toString(); // final variable initialization
        
        // Update static counters
        totalVehiclesRegistered++;
        totalRegistrationFees += registrationFee;
        
        System.out.println("Vehicle registered: " + this.vehicleType + " (" + this.vehicleId + ") for " + this.ownerName);
    }
    
    // Overloaded constructor with default registration fee
    public VehicleRegistrationSystem(String ownerName, String vehicleId, String vehicleType, String model, int year) {
        // Using 'this' to call another constructor (constructor chaining)
        this(ownerName, vehicleId, vehicleType, model, year, calculateDefaultFee(vehicleType));
        System.out.println("Default registration fee applied: $" + this.registrationFee);
    }
    
    // Constructor with minimal vehicle information
    public VehicleRegistrationSystem(String ownerName, String vehicleType) {
        this(ownerName, generateVehicleId(), vehicleType, "Unknown Model", 
             java.time.LocalDate.now().getYear(), calculateDefaultFee(vehicleType));
        System.out.println("Auto-generated vehicle ID and default values assigned.");
    }
    
    // Static method to display registration statistics
    public static void displayRegistrationStats() {
        System.out.println("=== Vehicle Registration Statistics ===");
        System.out.println("Registration Authority: " + registrationAuthority);
        System.out.println("Total Vehicles Registered: " + totalVehiclesRegistered);
        System.out.printf("Total Registration Fees Collected: $%.2f%n", totalRegistrationFees);
        if (totalVehiclesRegistered > 0) {
            System.out.printf("Average Registration Fee: $%.2f%n", totalRegistrationFees / totalVehiclesRegistered);
        }
        System.out.println("Valid Vehicle Types: " + String.join(", ", validVehicleTypes));
        System.out.println("=======================================");
    }
    
    // Static method to get registration authority
    public static String getRegistrationAuthority() {
        return registrationAuthority;
    }
    
    // Static method to update registration authority
    public static void updateRegistrationAuthority(String newAuthority) {
        if (newAuthority != null && !newAuthority.trim().isEmpty()) {
            String oldAuthority = registrationAuthority;
            registrationAuthority = newAuthority;
            System.out.println("Registration authority updated: " + oldAuthority + " -> " + registrationAuthority);
            System.out.println("This change affects all " + totalVehiclesRegistered + " registered vehicles!");
        } else {
            System.out.println("Invalid registration authority provided!");
        }
    }
    
    // Method to update owner name using 'this'
    public void updateOwnerName(String newOwnerName) {
        if (newOwnerName != null && !newOwnerName.trim().isEmpty()) {
            String oldOwner = this.ownerName;
            this.ownerName = newOwnerName;
            System.out.println("Owner updated for vehicle " + this.vehicleId + ": " + oldOwner + " -> " + this.ownerName);
        } else {
            System.out.println("Invalid owner name provided!");
        }
    }
    
    // Method to update vehicle model using 'this'
    public void updateVehicleModel(String newModel, int newYear) {
        if (newModel != null && !newModel.trim().isEmpty() && newYear > 1900) {
            String oldModel = this.model;
            int oldYear = this.year;
            this.model = newModel;
            this.year = newYear;
            System.out.println("Vehicle details updated for " + this.vehicleId + 
                             ": " + oldModel + " (" + oldYear + ") -> " + this.model + " (" + this.year + ")");
        } else {
            System.out.println("Invalid model or year provided!");
        }
    }
    
    // Method to renew registration using 'this'
    public void renewRegistration(double renewalFee) {
        if (renewalFee > 0) {
            this.registrationFee += renewalFee;
            totalRegistrationFees += renewalFee;
            System.out.printf("Registration renewed for %s (%s). Additional fee: $%.2f, Total: $%.2f%n", 
                             this.ownerName, this.vehicleId, renewalFee, this.registrationFee);
        } else {
            System.out.println("Invalid renewal fee!");
        }
    }
    
    // Method to deactivate/reactivate vehicle using 'this'
    public void updateRegistrationStatus(boolean active) {
        this.isActive = active;
        System.out.println("Vehicle " + this.vehicleId + " registration status: " + 
                         (this.isActive ? "Active" : "Inactive"));
    }
    
    // Method to calculate vehicle age using 'this'
    public int getVehicleAge() {
        return java.time.LocalDate.now().getYear() - this.year;
    }
    
    // Method to check if vehicle needs inspection using 'this'
    public boolean needsInspection() {
        return this.getVehicleAge() > 5; // Vehicles older than 5 years need inspection
    }
    
    // Method to display vehicle details
    public void displayVehicleDetails() {
        System.out.println("=== Vehicle Registration Details ===");
        System.out.println("Authority: " + registrationAuthority); // static variable access
        System.out.println("Owner: " + this.ownerName);
        System.out.println("Vehicle ID: " + this.vehicleId); // final variable access
        System.out.println("Registration Date: " + this.registrationDate); // final variable access
        System.out.println("Vehicle Type: " + this.vehicleType);
        System.out.println("Model: " + this.model);
        System.out.println("Year: " + this.year);
        System.out.println("Age: " + this.getVehicleAge() + " years");
        System.out.printf("Registration Fee: $%.2f%n", this.registrationFee);
        System.out.println("Status: " + (this.isActive ? "Active" : "Inactive"));
        System.out.println("Inspection Required: " + (this.needsInspection() ? "Yes" : "No"));
        System.out.println("===================================");
    }
    
    // Static method to validate and process vehicle using instanceof
    public static void processVehicle(Object obj) {
        // Using instanceof to check object type
        if (obj instanceof VehicleRegistrationSystem) {
            System.out.println("✓ Object is a valid Vehicle Registration instance");
            VehicleRegistrationSystem vehicle = (VehicleRegistrationSystem) obj; // Safe casting
            vehicle.displayVehicleDetails();
        } else {
            System.out.println("✗ Object is not a Vehicle Registration instance!");
            System.out.println("Object type: " + (obj != null ? obj.getClass().getSimpleName() : "null"));
        }
    }
    
    // Method to compare vehicles using 'this'
    public boolean isSameVehicle(VehicleRegistrationSystem other) {
        // Using 'this' to refer to current object and final vehicleId for comparison
        return other != null && this.vehicleId.equals(other.vehicleId);
    }
    
    // Method to check if same owner using 'this'
    public boolean hasSameOwner(VehicleRegistrationSystem other) {
        return other != null && this.ownerName.equalsIgnoreCase(other.ownerName);
    }
    
    // Method to check if same vehicle type using 'this'
    public boolean isSameVehicleType(VehicleRegistrationSystem other) {
        return other != null && this.vehicleType.equalsIgnoreCase(other.vehicleType);
    }
    
    // Method to check if newer vehicle using 'this'
    public boolean isNewerThan(VehicleRegistrationSystem other) {
        return other != null && this.year > other.year;
    }
    
    // Static utility method to calculate default registration fee
    private static double calculateDefaultFee(String vehicleType) {
        switch (vehicleType.toLowerCase()) {
            case "motorcycle": return 50.0;
            case "car": return 100.0;
            case "van": return 150.0;
            case "truck": return 200.0;
            case "bus": return 300.0;
            default: return 75.0;
        }
    }
    
    // Static utility method to generate unique vehicle ID
    private static String generateVehicleId() {
        return "VH" + String.format("%08d", totalVehiclesRegistered + 1);
    }
    
    // Static method to validate vehicle type
    public static boolean isValidVehicleType(String vehicleType) {
        for (String validType : validVehicleTypes) {
            if (validType.equalsIgnoreCase(vehicleType)) {
                return true;
            }
        }
        return false;
    }
    
    // Getter methods using 'this'
    public String getOwnerName() {
        return this.ownerName;
    }
    
    public String getVehicleId() {
        return this.vehicleId; // final variable - read-only access
    }
    
    public String getRegistrationDate() {
        return this.registrationDate; // final variable - read-only access
    }
    
    public String getVehicleType() {
        return this.vehicleType;
    }
    
    public String getModel() {
        return this.model;
    }
    
    public int getYear() {
        return this.year;
    }
    
    public double getRegistrationFee() {
        return this.registrationFee;
    }
    
    public boolean isActive() {
        return this.isActive;
    }
    
    // Static getter methods
    public static int getTotalVehiclesRegistered() {
        return totalVehiclesRegistered;
    }
    
    public static double getTotalRegistrationFees() {
        return totalRegistrationFees;
    }
    
    public static String[] getValidVehicleTypes() {
        return validVehicleTypes.clone(); // Return copy to prevent modification
    }
    
    // Method to get vehicle summary using 'this'
    public String getVehicleSummary() {
        return String.format("%s %s (%d) - ID: %s, Owner: %s, Status: %s", 
                           this.vehicleType, this.model, this.year, this.vehicleId, 
                           this.ownerName, (this.isActive ? "Active" : "Inactive"));
    }
    
    @Override
    public String toString() {
        return String.format("Vehicle{id='%s', owner='%s', type='%s', model='%s', year=%d}", 
                           vehicleId, ownerName, vehicleType, model, year);
    }
    
    // Main method for testing
    public static void main(String[] args) {
        System.out.println("=== Vehicle Registration System Demo ===\\n");
        
        System.out.println("1. Initial registration system state:");
        System.out.println("Authority: " + getRegistrationAuthority());
        displayRegistrationStats();
        
        System.out.println("\\n2. Registering vehicles (using 'this' in constructors):");
        VehicleRegistrationSystem car1 = new VehicleRegistrationSystem("John Smith", "CAR001", "Car", "Toyota Camry", 2020, 120.0);
        VehicleRegistrationSystem bike1 = new VehicleRegistrationSystem("Alice Johnson", "BIKE001", "Motorcycle", "Honda CBR", 2019);
        VehicleRegistrationSystem truck1 = new VehicleRegistrationSystem("Bob Wilson", "Truck");
        
        System.out.println("\\n3. Registration statistics after vehicle enrollment:");
        displayRegistrationStats();
        
        System.out.println("\\n4. Testing instanceof with valid vehicle:");
        processVehicle(car1);
        
        System.out.println("\\n5. Testing instanceof with invalid objects:");
        processVehicle("Not a vehicle");
        processVehicle(123);
        processVehicle(null);
        
        System.out.println("\\n6. Vehicle operations using 'this':");
        car1.updateOwnerName("John Doe Smith");
        bike1.updateVehicleModel("Honda CBR600RR", 2021);
        truck1.renewRegistration(50.0);
        car1.renewRegistration(25.0);
        
        System.out.println("\\n7. Updated vehicle details:");
        processVehicle(car1);
        processVehicle(bike1);
        
        System.out.println("\\n8. Testing final variables (vehicleId and registrationDate cannot be changed):");
        System.out.println("Car1 Vehicle ID: " + car1.getVehicleId());
        System.out.println("Car1 Registration Date: " + car1.getRegistrationDate());
        // car1.vehicleId = "NEWCAR001"; // This would cause compilation error
        // car1.registrationDate = "2024-01-01"; // This would cause compilation error
        System.out.println("Note: Vehicle ID and Registration Date are final and cannot be modified");
        
        System.out.println("\\n9. Modifying static variable (affects all vehicles):");
        updateRegistrationAuthority("National Vehicle Registration Board");
        
        System.out.println("\\n10. All vehicles now show updated authority:");
        processVehicle(car1);
        processVehicle(truck1);
        
        System.out.println("\\n11. Vehicle comparisons using 'this':");
        VehicleRegistrationSystem car2 = new VehicleRegistrationSystem("Mary Davis", "CAR002", "Car", "Honda Accord", 2018, 110.0);
        System.out.println("Are car1 and car2 the same vehicle? " + car1.isSameVehicle(car2));
        System.out.println("Do car1 and car2 have the same owner? " + car1.hasSameOwner(car2));
        System.out.println("Are car1 and car2 the same vehicle type? " + car1.isSameVehicleType(car2));
        System.out.println("Is car1 newer than car2? " + car1.isNewerThan(car2));
        
        System.out.println("\\n12. Multiple instanceof checks with mixed objects:");
        Object[] objects = {car1, bike1, "String", 456, truck1, null, new java.util.HashMap()};
        
        for (int i = 0; i < objects.length; i++) {
            System.out.printf("Object %d: ", i + 1);
            if (objects[i] instanceof VehicleRegistrationSystem) {
                VehicleRegistrationSystem vehicle = (VehicleRegistrationSystem) objects[i];
                System.out.println("Vehicle - " + vehicle.getVehicleSummary());
            } else {
                System.out.println("Not a Vehicle - " + 
                                 (objects[i] != null ? objects[i].getClass().getSimpleName() : "null"));
            }
        }
        
        System.out.println("\\n13. Vehicle inspection and status management:");
        System.out.println("Car1 needs inspection: " + car1.needsInspection());
        System.out.println("Car2 needs inspection: " + car2.needsInspection());
        bike1.updateRegistrationStatus(false); // Suspend registration
        bike1.displayVehicleDetails();
        bike1.updateRegistrationStatus(true);  // Reactivate registration
        
        System.out.println("\\n14. Vehicle type validation:");
        System.out.println("Valid vehicle types: " + String.join(", ", getValidVehicleTypes()));
        System.out.println("Is 'Car' valid? " + isValidVehicleType("Car"));
        System.out.println("Is 'Airplane' valid? " + isValidVehicleType("Airplane"));
        
        System.out.println("\\n15. Final registration statistics:");
        displayRegistrationStats();
        
        System.out.println("\\n=== Concepts Demonstrated ===");
        System.out.println("✓ Static: registrationAuthority and totalVehiclesRegistered shared across all instances");
        System.out.println("✓ This: Used in constructors and methods to refer to current object");
        System.out.println("✓ Final: Vehicle ID and Registration Date cannot be changed once assigned");
        System.out.println("✓ Instanceof: Safe type checking before casting and operations");
    }
}
