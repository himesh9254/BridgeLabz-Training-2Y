/**
 * Ride-Hailing Application demonstrating:
 * Abstract classes, interfaces, encapsulation, inheritance, polymorphism
 */

import java.util.*;

interface GPS {
    void getCurrentLocation();
    void updateLocation(double latitude, double longitude);
}

abstract class Vehicle {
    private String vehicleId;
    private String driverName;
    private double ratePerKm;
    private boolean isAvailable;
    private double currentLatitude;
    private double currentLongitude;
    private String driverLicense; // Sensitive data
    private double earnings;
    
    public Vehicle(String vehicleId, String driverName, double ratePerKm, String driverLicense) {
        this.vehicleId = vehicleId;
        this.driverName = driverName;
        this.ratePerKm = ratePerKm;
        this.driverLicense = driverLicense; // Encapsulated sensitive data
        this.isAvailable = true;
        this.earnings = 0.0;
        this.currentLatitude = 0.0;
        this.currentLongitude = 0.0;
    }
    
    public abstract double calculateFare(double distance);
    
    public void getVehicleDetails() {
        System.out.println("=== Vehicle Details ===");
        System.out.println("ID: " + vehicleId);
        System.out.println("Driver: " + driverName);
        System.out.println("Type: " + this.getClass().getSimpleName());
        System.out.println("Rate: $" + ratePerKm + "/km");
        System.out.println("Available: " + isAvailable);
        System.out.println("Earnings: $" + earnings);
        System.out.println("License: " + getMaskedLicense()); // Protected sensitive data
        System.out.println("Location: (" + currentLatitude + ", " + currentLongitude + ")");
        System.out.println("======================");
    }
    
    private String getMaskedLicense() {
        if (driverLicense.length() > 4) {
            return "****" + driverLicense.substring(driverLicense.length() - 4);
        }
        return "****";
    }
    
    public boolean acceptRide(double distance) {
        if (!isAvailable) return false;
        
        double fare = calculateFare(distance);
        earnings += fare;
        isAvailable = false;
        
        System.out.println(driverName + " accepted ride. Fare: $" + fare);
        return true;
    }
    
    public void completeRide() {
        isAvailable = true;
        System.out.println(driverName + " completed ride and is now available.");
    }
    
    // Encapsulated getters
    public String getVehicleId() { return vehicleId; }
    public String getDriverName() { return driverName; }
    public double getRatePerKm() { return ratePerKm; }
    public boolean isAvailable() { return isAvailable; }
    public double getEarnings() { return earnings; }
    
    // Secure setter for driver license (requires authorization)
    protected void updateDriverLicense(String newLicense, String authCode) {
        if (!"ADMIN123".equals(authCode)) {
            throw new SecurityException("Unauthorized license update attempt");
        }
        this.driverLicense = newLicense;
        System.out.println("Driver license updated securely for " + driverName);
    }
    
    protected void updateEarnings(double amount) {
        this.earnings += amount;
    }
    
    public void setCurrentLocation(double lat, double lng) {
        this.currentLatitude = lat;
        this.currentLongitude = lng;
    }
    
    public double getCurrentLatitude() { return currentLatitude; }
    public double getCurrentLongitude() { return currentLongitude; }
}

class Car extends Vehicle implements GPS {
    private int seatingCapacity;
    private boolean hasAC;
    private String carModel;
    
    public Car(String vehicleId, String driverName, double ratePerKm, String driverLicense,
               int seatingCapacity, boolean hasAC, String carModel) {
        super(vehicleId, driverName, ratePerKm, driverLicense);
        this.seatingCapacity = seatingCapacity;
        this.hasAC = hasAC;
        this.carModel = carModel;
    }
    
    @Override
    public double calculateFare(double distance) {
        double baseFare = getRatePerKm() * distance;
        double surcharge = 0;
        
        // Premium for AC cars
        if (hasAC) surcharge += baseFare * 0.15; // 15% AC surcharge
        
        // Premium for larger capacity
        if (seatingCapacity > 4) surcharge += baseFare * 0.10; // 10% large car surcharge
        
        // Base booking fee
        return baseFare + surcharge + 3.0;
    }
    
    @Override
    public void getCurrentLocation() {
        System.out.println("Car " + getVehicleId() + " location: (" + 
                         getCurrentLatitude() + ", " + getCurrentLongitude() + ")");
    }
    
    @Override
    public void updateLocation(double latitude, double longitude) {
        setCurrentLocation(latitude, longitude);
        System.out.println("Car location updated to: (" + latitude + ", " + longitude + ")");
    }
    
    public int getSeatingCapacity() { return seatingCapacity; }
    public boolean hasAC() { return hasAC; }
    public String getCarModel() { return carModel; }
}

class Bike extends Vehicle implements GPS {
    private int engineCapacity;
    private boolean isElectric;
    
    public Bike(String vehicleId, String driverName, double ratePerKm, String driverLicense,
                int engineCapacity, boolean isElectric) {
        super(vehicleId, driverName, ratePerKm, driverLicense);
        this.engineCapacity = engineCapacity;
        this.isElectric = isElectric;
    }
    
    @Override
    public double calculateFare(double distance) {
        double baseFare = getRatePerKm() * distance;
        
        // Discount for electric bikes (eco-friendly)
        if (isElectric) baseFare *= 0.90; // 10% discount
        
        // Small booking fee for bikes
        return baseFare + 1.5;
    }
    
    @Override
    public void getCurrentLocation() {
        System.out.println("Bike " + getVehicleId() + " location: (" + 
                         getCurrentLatitude() + ", " + getCurrentLongitude() + ")");
    }
    
    @Override
    public void updateLocation(double latitude, double longitude) {
        setCurrentLocation(latitude, longitude);
        System.out.println("Bike location updated to: (" + latitude + ", " + longitude + ")");
    }
    
    public int getEngineCapacity() { return engineCapacity; }
    public boolean isElectric() { return isElectric; }
}

class Auto extends Vehicle implements GPS {
    private boolean isShared;
    private int maxPassengers = 3;
    
    public Auto(String vehicleId, String driverName, double ratePerKm, String driverLicense, boolean isShared) {
        super(vehicleId, driverName, ratePerKm, driverLicense);
        this.isShared = isShared;
    }
    
    @Override
    public double calculateFare(double distance) {
        double baseFare = getRatePerKm() * distance;
        
        // Discount for shared rides
        if (isShared) baseFare *= 0.75; // 25% discount for shared
        
        // Standard booking fee
        return baseFare + 2.0;
    }
    
    @Override
    public void getCurrentLocation() {
        System.out.println("Auto " + getVehicleId() + " location: (" + 
                         getCurrentLatitude() + ", " + getCurrentLongitude() + ")");
    }
    
    @Override
    public void updateLocation(double latitude, double longitude) {
        setCurrentLocation(latitude, longitude);
        System.out.println("Auto location updated to: (" + latitude + ", " + longitude + ")");
    }
    
    public boolean isShared() { return isShared; }
    public int getMaxPassengers() { return maxPassengers; }
}

public class RideHailingApplication {
    private List<Vehicle> fleet;
    private static int vehicleCounter = 1;
    
    public RideHailingApplication() {
        this.fleet = new ArrayList<>();
    }
    
    public void addVehicle(Vehicle vehicle) {
        fleet.add(vehicle);
        System.out.println("Vehicle added: " + vehicle.getVehicleId());
    }
    
    public void displayFleet() {
        System.out.println("\n=== VEHICLE FLEET ===");
        for (Vehicle vehicle : fleet) {
            vehicle.getVehicleDetails(); // Polymorphic call
            System.out.println();
        }
    }
    
    public void calculateFares(double distance) {
        System.out.println("\n=== FARE CALCULATION FOR " + distance + "km ===");
        for (Vehicle vehicle : fleet) {
            double fare = vehicle.calculateFare(distance); // Polymorphic call
            System.out.println(vehicle.getClass().getSimpleName() + " (" + vehicle.getDriverName() + 
                             "): $" + String.format("%.2f", fare));
        }
    }
    
    public void updateAllLocations() {
        System.out.println("\n=== GPS LOCATION UPDATES ===");
        Random rand = new Random();
        for (Vehicle vehicle : fleet) {
            if (vehicle instanceof GPS) {
                GPS gpsVehicle = (GPS) vehicle;
                double lat = 40.0 + rand.nextDouble();
                double lng = -74.0 + rand.nextDouble();
                gpsVehicle.updateLocation(lat, lng);
            }
        }
    }
    
    public Vehicle findNearestAvailableVehicle(String vehicleType) {
        for (Vehicle vehicle : fleet) {
            if (vehicle.getClass().getSimpleName().equalsIgnoreCase(vehicleType) && vehicle.isAvailable()) {
                return vehicle;
            }
        }
        return null;
    }
    
    public boolean bookRide(String vehicleType, double distance) {
        Vehicle vehicle = findNearestAvailableVehicle(vehicleType);
        if (vehicle != null) {
            return vehicle.acceptRide(distance);
        } else {
            System.out.println("No available " + vehicleType + " found.");
            return false;
        }
    }
    
    public static String generateVehicleId(String prefix) {
        return prefix + String.format("%03d", vehicleCounter++);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Ride-Hailing Application Demo ===\n");
        
        RideHailingApplication app = new RideHailingApplication();
        
        // Add different types of vehicles
        Car car1 = new Car(generateVehicleId("CAR"), "John Smith", 2.5, "DL123456789", 4, true, "Toyota Camry");
        Bike bike1 = new Bike(generateVehicleId("BIKE"), "Alice Johnson", 1.2, "DL987654321", 150, false);
        Auto auto1 = new Auto(generateVehicleId("AUTO"), "Bob Wilson", 1.8, "DL456789123", true);
        
        app.addVehicle(car1);
        app.addVehicle(bike1);
        app.addVehicle(auto1);
        
        app.displayFleet();
        app.calculateFares(10.0); // 10km trip
        app.updateAllLocations();
        
        // Book some rides
        System.out.println("\n=== RIDE BOOKING ===");
        app.bookRide("Car", 15.0);
        app.bookRide("Bike", 8.0);
        
        // Complete rides
        car1.completeRide();
        bike1.completeRide();
        
        System.out.println("\n=== UPDATED FLEET STATUS ===");
        app.displayFleet();
        
        System.out.println("\n=== Concepts Demonstrated ===");
        System.out.println("✓ Abstract Classes: Vehicle with abstract calculateFare()");
        System.out.println("✓ Inheritance: Car, Bike, Auto extend Vehicle");
        System.out.println("✓ Polymorphism: Dynamic fare calculation per vehicle type");
        System.out.println("✓ Interface: GPS interface for location tracking");
        System.out.println("✓ Encapsulation: Protected driver license and earnings data");
    }
}
