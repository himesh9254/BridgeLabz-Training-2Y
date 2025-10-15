// 3. Smart Vehicle Dashboard
// All vehicles have a method displaySpeed(), but electric vehicles also display battery percentage
// Use default methods to add this new feature

interface VehicleDashboard {
    void displaySpeed();
    String getVehicleType();
    
    // Default method for battery display (added later)
    default void displayBatteryPercentage() {
        System.out.println("Battery Status: Not Applicable (Non-electric vehicle)");
    }
    
    // Default method for complete dashboard
    default void showDashboard() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("VEHICLE DASHBOARD - " + getVehicleType());
        System.out.println("=".repeat(40));
        displaySpeed();
        displayBatteryPercentage();
        System.out.println("=".repeat(40));
    }
}

class PetrolCar implements VehicleDashboard {
    private int speed;
    private String model;
    
    public PetrolCar(String model, int speed) {
        this.model = model;
        this.speed = speed;
    }
    
    @Override
    public void displaySpeed() {
        System.out.println("Current Speed: " + speed + " km/h");
        System.out.println("Fuel Type: Petrol");
    }
    
    @Override
    public String getVehicleType() {
        return "Petrol Car - " + model;
    }
    
    // Uses default battery display (not applicable)
}

class ElectricCar implements VehicleDashboard {
    private int speed;
    private int batteryPercentage;
    private String model;
    
    public ElectricCar(String model, int speed, int batteryPercentage) {
        this.model = model;
        this.speed = speed;
        this.batteryPercentage = batteryPercentage;
    }
    
    @Override
    public void displaySpeed() {
        System.out.println("Current Speed: " + speed + " km/h");
        System.out.println("Fuel Type: Electric");
    }
    
    @Override
    public String getVehicleType() {
        return "Electric Car - " + model;
    }
    
    // Override to show actual battery percentage
    @Override
    public void displayBatteryPercentage() {
        System.out.print("Battery: ");
        
        // Visual battery indicator
        int bars = batteryPercentage / 10;
        System.out.print("[");
        for (int i = 0; i < 10; i++) {
            if (i < bars) {
                System.out.print("█");
            } else {
                System.out.print("░");
            }
        }
        System.out.println("] " + batteryPercentage + "%");
        
        if (batteryPercentage < 20) {
            System.out.println("⚠️  Warning: Low battery! Please charge soon.");
        } else if (batteryPercentage < 50) {
            System.out.println("ℹ️  Battery is moderate. Consider charging.");
        } else {
            System.out.println("✓  Battery level is good.");
        }
    }
}

class HybridCar implements VehicleDashboard {
    private int speed;
    private int batteryPercentage;
    private String model;
    
    public HybridCar(String model, int speed, int batteryPercentage) {
        this.model = model;
        this.speed = speed;
        this.batteryPercentage = batteryPercentage;
    }
    
    @Override
    public void displaySpeed() {
        System.out.println("Current Speed: " + speed + " km/h");
        System.out.println("Fuel Type: Hybrid (Petrol + Electric)");
    }
    
    @Override
    public String getVehicleType() {
        return "Hybrid Car - " + model;
    }
    
    @Override
    public void displayBatteryPercentage() {
        System.out.println("Electric Battery: " + batteryPercentage + "%");
        System.out.println("Petrol Tank: Available as backup");
    }
}

public class SmartVehicleDashboard {
    public static void main(String[] args) {
        System.out.println("=== Smart Vehicle Dashboard System ===");
        
        VehicleDashboard petrolCar = new PetrolCar("Honda Civic", 80);
        VehicleDashboard electricCar1 = new ElectricCar("Tesla Model 3", 100, 85);
        VehicleDashboard electricCar2 = new ElectricCar("Nissan Leaf", 60, 15);
        VehicleDashboard hybridCar = new HybridCar("Toyota Prius", 70, 60);
        
        // Display all dashboards
        petrolCar.showDashboard();
        electricCar1.showDashboard();
        electricCar2.showDashboard();
        hybridCar.showDashboard();
        
        // Individual displays
        System.out.println("\n\n=== QUICK STATUS CHECK ===");
        System.out.println("-".repeat(40));
        System.out.println("Vehicle 1: " + petrolCar.getVehicleType());
        petrolCar.displaySpeed();
        
        System.out.println("\nVehicle 2: " + electricCar1.getVehicleType());
        electricCar1.displaySpeed();
        electricCar1.displayBatteryPercentage();
    }
}
