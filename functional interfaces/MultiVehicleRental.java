// 2. Multi-Vehicle Rental System
// Cars, bikes, and buses share rent() and returnVehicle() methods

interface Rentable {
    void rent(String customerName);
    void returnVehicle();
}

class Car implements Rentable {
    private String model;
    private double pricePerDay;
    private String currentCustomer;
    
    public Car(String model, double pricePerDay) {
        this.model = model;
        this.pricePerDay = pricePerDay;
    }
    
    @Override
    public void rent(String customerName) {
        currentCustomer = customerName;
        System.out.println(customerName + " rented " + model + " car at $" + pricePerDay + "/day");
    }
    
    @Override
    public void returnVehicle() {
        System.out.println(currentCustomer + " returned " + model + " car");
        currentCustomer = null;
    }
}

class Bike implements Rentable {
    private String type;
    private double pricePerDay;
    private String currentCustomer;
    
    public Bike(String type, double pricePerDay) {
        this.type = type;
        this.pricePerDay = pricePerDay;
    }
    
    @Override
    public void rent(String customerName) {
        currentCustomer = customerName;
        System.out.println(customerName + " rented " + type + " bike at $" + pricePerDay + "/day");
    }
    
    @Override
    public void returnVehicle() {
        System.out.println(currentCustomer + " returned " + type + " bike");
        currentCustomer = null;
    }
}

class Bus implements Rentable {
    private int capacity;
    private double pricePerDay;
    private String currentCustomer;
    
    public Bus(int capacity, double pricePerDay) {
        this.capacity = capacity;
        this.pricePerDay = pricePerDay;
    }
    
    @Override
    public void rent(String customerName) {
        currentCustomer = customerName;
        System.out.println(customerName + " rented " + capacity + "-seater bus at $" + pricePerDay + "/day");
    }
    
    @Override
    public void returnVehicle() {
        System.out.println(currentCustomer + " returned " + capacity + "-seater bus");
        currentCustomer = null;
    }
}

public class MultiVehicleRental {
    public static void main(String[] args) {
        Rentable car = new Car("Honda Civic", 50);
        Rentable bike = new Bike("Mountain", 15);
        Rentable bus = new Bus(40, 200);
        
        car.rent("John Doe");
        bike.rent("Jane Smith");
        bus.rent("ABC Tours");
        
        System.out.println();
        
        car.returnVehicle();
        bike.returnVehicle();
        bus.returnVehicle();
    }
}
