import java.util.ArrayList;
import java.util.List;

abstract class Vehicle {
    private String model;
    private String registrationNumber;

    public Vehicle(String model, String registrationNumber) {
        this.model = model;
        this.registrationNumber = registrationNumber;
    }

    public String getModel() {
        return model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public abstract String getType();
}

class Truck extends Vehicle {
    private double loadCapacity;

    public Truck(String model, String registrationNumber, double loadCapacity) {
        super(model, registrationNumber);
        this.loadCapacity = loadCapacity;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    @Override
    public String getType() {
        return "Truck";
    }
}

class Car extends Vehicle {
    private int seatingCapacity;

    public Car(String model, String registrationNumber, int seatingCapacity) {
        super(model, registrationNumber);
        this.seatingCapacity = seatingCapacity;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    @Override
    public String getType() {
        return "Car";
    }
}

class Bike extends Vehicle {
    private String bikeType;

    public Bike(String model, String registrationNumber, String bikeType) {
        super(model, registrationNumber);
        this.bikeType = bikeType;
    }

    public String getBikeType() {
        return bikeType;
    }

    @Override
    public String getType() {
        return "Bike";
    }
}

class FleetManager<T extends Vehicle> {
    private List<T> fleet = new ArrayList<>();

    public void addVehicle(T vehicle) {
        fleet.add(vehicle);
        System.out.println(vehicle.getType() + " added: " + vehicle.getModel());
    }

    public void showFleet() {
        System.out.println("\nFleet Details:");
        for (T vehicle : fleet) {
            System.out.println("- " + vehicle.getType() + ": " + vehicle.getModel() + " [" + vehicle.getRegistrationNumber() + "]");
        }
        System.out.println("Total vehicles: " + fleet.size());
    }
}

public class GenericFleetManager {
    public static void main(String[] args) {
        FleetManager<Truck> truckFleet = new FleetManager<>();
        truckFleet.addVehicle(new Truck("Volvo FH", "TRK-001", 20.5));
        truckFleet.addVehicle(new Truck("Scania R", "TRK-002", 25.0));
        truckFleet.addVehicle(new Truck("MAN TGX", "TRK-003", 22.0));
        truckFleet.showFleet();

        System.out.println();

        FleetManager<Bike> bikeFleet = new FleetManager<>();
        bikeFleet.addVehicle(new Bike("Yamaha R15", "BKE-001", "Sport"));
        bikeFleet.addVehicle(new Bike("Royal Enfield", "BKE-002", "Cruiser"));
        bikeFleet.addVehicle(new Bike("Honda Activa", "BKE-003", "Scooter"));
        bikeFleet.showFleet();

        System.out.println();

        FleetManager<Car> carFleet = new FleetManager<>();
        carFleet.addVehicle(new Car("Toyota Camry", "CAR-001", 5));
        carFleet.addVehicle(new Car("Honda Civic", "CAR-002", 5));
        carFleet.showFleet();
    }
}
