import java.util.*;

class Driver {
    private String driverId;
    private String name;
    private String vehicleNumber;
    private boolean available;

    public Driver(String driverId, String name, String vehicleNumber) {
        this.driverId = driverId;
        this.name = name;
        this.vehicleNumber = vehicleNumber;
        this.available = true;
    }

    public String getDriverId() { return driverId; }
    public String getName() { return name; }
    public String getVehicleNumber() { return vehicleNumber; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return driverId.equals(driver.driverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverId);
    }

    @Override
    public String toString() {
        return String.format("Driver[%s, %s, %s, %s]", driverId, name, vehicleNumber, available ? "Available" : "Busy");
    }
}

class RideRequest implements Comparable<RideRequest> {
    private String requestId;
    private String passengerName;
    private String pickup;
    private String destination;
    private int priority;
    private double distance;

    public RideRequest(String requestId, String passengerName, String pickup, String destination, int priority, double distance) {
        this.requestId = requestId;
        this.passengerName = passengerName;
        this.pickup = pickup;
        this.destination = destination;
        this.priority = priority;
        this.distance = distance;
    }

    public String getRequestId() { return requestId; }
    public String getPassengerName() { return passengerName; }
    public String getPickup() { return pickup; }
    public String getDestination() { return destination; }
    public int getPriority() { return priority; }
    public double getDistance() { return distance; }

    @Override
    public int compareTo(RideRequest other) {
        return Integer.compare(other.priority, this.priority);
    }

    @Override
    public String toString() {
        return String.format("Request[%s, %s, %s->%s, Priority:%d]", requestId, passengerName, pickup, destination, priority);
    }
}

class Ride {
    private RideRequest request;
    private Driver driver;
    private String status;

    public Ride(RideRequest request, Driver driver) {
        this.request = request;
        this.driver = driver;
        this.status = "Completed";
    }

    @Override
    public String toString() {
        return String.format("Ride[%s, Driver:%s, Status:%s]", request.getRequestId(), driver.getName(), status);
    }
}

public class RideSharingDispatch {
    private Queue<RideRequest> pendingRequests;
    private PriorityQueue<RideRequest> priorityRequests;
    private Set<Driver> availableDrivers;
    private List<Ride> completedRides;

    public RideSharingDispatch() {
        pendingRequests = new LinkedList<>();
        priorityRequests = new PriorityQueue<>();
        availableDrivers = new HashSet<>();
        completedRides = new ArrayList<>();
    }

    public void addDriver(Driver driver) {
        if (availableDrivers.add(driver)) {
            System.out.println("Driver added: " + driver);
        } else {
            System.out.println("Driver already exists: " + driver.getDriverId());
        }
    }

    public void addRideRequest(RideRequest request) {
        if (request.getPriority() > 5) {
            priorityRequests.add(request);
            System.out.println("High priority request added: " + request);
        } else {
            pendingRequests.add(request);
            System.out.println("Normal request added: " + request);
        }
    }

    public void processHighPriorityRequests() {
        System.out.println("\n=== Processing High Priority Requests ===");
        while (!priorityRequests.isEmpty()) {
            RideRequest request = priorityRequests.poll();
            Driver driver = getAvailableDriver();
            if (driver != null) {
                assignRide(request, driver);
            } else {
                System.out.println("No driver available for: " + request);
                pendingRequests.add(request);
            }
        }
    }

    public void processNormalRequests() {
        System.out.println("\n=== Processing Normal Requests ===");
        while (!pendingRequests.isEmpty()) {
            RideRequest request = pendingRequests.poll();
            Driver driver = getAvailableDriver();
            if (driver != null) {
                assignRide(request, driver);
            } else {
                System.out.println("No driver available for: " + request);
                break;
            }
        }
    }

    private Driver getAvailableDriver() {
        for (Driver driver : availableDrivers) {
            if (driver.isAvailable()) {
                return driver;
            }
        }
        return null;
    }

    private void assignRide(RideRequest request, Driver driver) {
        driver.setAvailable(false);
        System.out.println("Assigned " + request.getRequestId() + " to " + driver.getName());

        Ride ride = new Ride(request, driver);
        completedRides.add(ride);
        driver.setAvailable(true);
        System.out.println("Ride completed: " + ride);
    }

    public void displayAvailableDrivers() {
        System.out.println("\n=== Available Drivers ===");
        for (Driver driver : availableDrivers) {
            System.out.println("  " + driver);
        }
    }

    public void displayPendingRequests() {
        System.out.println("\n=== Pending Requests ===");
        System.out.println("Normal queue: " + pendingRequests.size());
        System.out.println("Priority queue: " + priorityRequests.size());
    }

    public void displayCompletedRides() {
        System.out.println("\n=== Completed Rides History ===");
        for (Ride ride : completedRides) {
            System.out.println("  " + ride);
        }
        System.out.println("Total completed: " + completedRides.size());
    }

    public static void main(String[] args) {
        RideSharingDispatch system = new RideSharingDispatch();

        system.addDriver(new Driver("DRV001", "Raj Kumar", "KA01AB1234"));
        system.addDriver(new Driver("DRV002", "Amit Singh", "KA01CD5678"));
        system.addDriver(new Driver("DRV003", "Priya Sharma", "KA01EF9012"));
        system.addDriver(new Driver("DRV001", "Duplicate Driver", "XX00XX0000"));

        system.displayAvailableDrivers();

        System.out.println("\n=== Adding Ride Requests ===");
        system.addRideRequest(new RideRequest("REQ001", "John", "Mall", "Airport", 3, 15.5));
        system.addRideRequest(new RideRequest("REQ002", "Alice", "Hospital", "Home", 8, 8.2));
        system.addRideRequest(new RideRequest("REQ003", "Bob", "Office", "Station", 2, 12.0));
        system.addRideRequest(new RideRequest("REQ004", "Carol", "Emergency", "Hospital", 10, 5.0));
        system.addRideRequest(new RideRequest("REQ005", "David", "Home", "Office", 4, 20.0));

        system.displayPendingRequests();
        system.processHighPriorityRequests();
        system.processNormalRequests();
        system.displayCompletedRides();
    }
}
