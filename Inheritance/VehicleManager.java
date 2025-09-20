interface Refuelable {
    void refuel();
}

class Vehicle {
    int maxSpeed;
    String model;

    Vehicle(int maxSpeed, String model) {
        this.maxSpeed = maxSpeed;
        this.model = model;
    }

    void displayVehicleInfo() {
        System.out.println("Model: " + model);
        System.out.println("Max Speed: " + maxSpeed + " km/h");
    }
}

class ElectricVehicle extends Vehicle {
    double batteryCapacity;
    double currentCharge;

    ElectricVehicle(int maxSpeed, String model, double batteryCapacity, double currentCharge) {
        super(maxSpeed, model);
        this.batteryCapacity = batteryCapacity;
        this.currentCharge = currentCharge;
    }

    void charge() {
        System.out.println("Charging " + model + "...");
        currentCharge = batteryCapacity;
        System.out.println("Battery fully charged: " + currentCharge + " kWh");
    }

    void displayInfo() {
        System.out.println("Vehicle Type: Electric Vehicle");
        displayVehicleInfo();
        System.out.println("Battery Capacity: " + batteryCapacity + " kWh");
        System.out.println("Current Charge: " + currentCharge + " kWh");
    }
}

class PetrolVehicle extends Vehicle implements Refuelable {
    double tankCapacity;
    double currentFuel;

    PetrolVehicle(int maxSpeed, String model, double tankCapacity, double currentFuel) {
        super(maxSpeed, model);
        this.tankCapacity = tankCapacity;
        this.currentFuel = currentFuel;
    }

    @Override
    public void refuel() {
        System.out.println("Refueling " + model + "...");
        double fuelAdded = tankCapacity - currentFuel;
        currentFuel = tankCapacity;
        System.out.println("Added " + fuelAdded + " liters of petrol");
        System.out.println("Tank full: " + currentFuel + " liters");
    }

    void displayInfo() {
        System.out.println("Vehicle Type: Petrol Vehicle");
        displayVehicleInfo();
        System.out.println("Tank Capacity: " + tankCapacity + " liters");
        System.out.println("Current Fuel: " + currentFuel + " liters");
    }
}

public class VehicleManager {
    public static void main(String[] args) {
        ElectricVehicle tesla = new ElectricVehicle(250, "Tesla Model 3", 75.0, 30.0);
        PetrolVehicle toyota = new PetrolVehicle(180, "Toyota Camry", 60.0, 15.0);

        System.out.println("=== Vehicle Management System ===\n");
        
        System.out.println("Electric Vehicle Details:");
        tesla.displayInfo();
        System.out.println("\nCharging Electric Vehicle:");
        tesla.charge();
        
        System.out.println("\n\nPetrol Vehicle Details:");
        toyota.displayInfo();
        System.out.println("\nRefueling Petrol Vehicle:");
        toyota.refuel();
        
        System.out.println("\n\nHybrid Inheritance Demonstration:");
        System.out.println("ElectricVehicle extends Vehicle (single inheritance)");
        System.out.println("PetrolVehicle extends Vehicle and implements Refuelable");
        System.out.println("This shows how interfaces enable multiple behavior inheritance");
        
        System.out.println("\nRefuelable interface in action:");
        Refuelable refuelableVehicle = toyota;
        refuelableVehicle.refuel();
    }
}