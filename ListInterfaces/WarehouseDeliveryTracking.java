import java.util.*;

class Package {
    private String packageId;
    private String sender;
    private String receiver;
    private String address;
    private String status;
    private double weight;

    public Package(String packageId, String sender, String receiver, String address, double weight) {
        this.packageId = packageId;
        this.sender = sender;
        this.receiver = receiver;
        this.address = address;
        this.weight = weight;
        this.status = "Pending";
    }

    public String getPackageId() { return packageId; }
    public String getSender() { return sender; }
    public String getReceiver() { return receiver; }
    public String getAddress() { return address; }
    public String getStatus() { return status; }
    public double getWeight() { return weight; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("Package[%s, %s->%s, %.1fkg, %s]", 
            packageId, sender, receiver, weight, status);
    }
}

public class WarehouseDeliveryTracking {
    private Queue<Package> pendingDeliveries;
    private Set<String> packageIds;
    private List<Package> deliveredPackages;
    private Stack<Package> returnedPackages;

    public WarehouseDeliveryTracking() {
        pendingDeliveries = new LinkedList<>();
        packageIds = new HashSet<>();
        deliveredPackages = new ArrayList<>();
        returnedPackages = new Stack<>();
    }

    public boolean addDeliveryRequest(Package pkg) {
        if (packageIds.contains(pkg.getPackageId())) {
            System.out.println("Duplicate package ID: " + pkg.getPackageId());
            return false;
        }
        packageIds.add(pkg.getPackageId());
        pendingDeliveries.add(pkg);
        System.out.println("Delivery request added: " + pkg);
        return true;
    }

    public void processDeliveries() {
        System.out.println("\n=== Processing Deliveries ===");
        Random random = new Random();

        while (!pendingDeliveries.isEmpty()) {
            Package pkg = pendingDeliveries.poll();

            int outcome = random.nextInt(10);

            if (outcome < 7) {
                pkg.setStatus("Delivered");
                deliveredPackages.add(pkg);
                System.out.println("DELIVERED: " + pkg);
            } else if (outcome < 9) {
                pkg.setStatus("Returned - Recipient not available");
                returnedPackages.push(pkg);
                System.out.println("RETURNED: " + pkg);
            } else {
                pkg.setStatus("Cancelled");
                returnedPackages.push(pkg);
                System.out.println("CANCELLED: " + pkg);
            }
        }
    }

    public Package getLastReturnedPackage() {
        if (returnedPackages.isEmpty()) {
            System.out.println("No returned packages.");
            return null;
        }
        return returnedPackages.peek();
    }

    public void reprocessReturnedPackage() {
        System.out.println("\n=== Reprocessing Last Returned Package ===");
        if (returnedPackages.isEmpty()) {
            System.out.println("No packages to reprocess.");
            return;
        }

        Package pkg = returnedPackages.pop();
        pkg.setStatus("Requeued");
        pendingDeliveries.add(pkg);
        System.out.println("Requeued for delivery: " + pkg);
    }

    public void displayPendingDeliveries() {
        System.out.println("\n=== Pending Deliveries (" + pendingDeliveries.size() + ") ===");
        if (pendingDeliveries.isEmpty()) {
            System.out.println("  No pending deliveries");
            return;
        }
        for (Package pkg : pendingDeliveries) {
            System.out.println("  " + pkg);
        }
    }

    public void displayDeliveredPackages() {
        System.out.println("\n=== Delivered Packages (" + deliveredPackages.size() + ") ===");
        for (Package pkg : deliveredPackages) {
            System.out.println("  " + pkg);
        }
    }

    public void displayReturnedPackages() {
        System.out.println("\n=== Returned/Cancelled Packages (" + returnedPackages.size() + ") ===");
        if (returnedPackages.isEmpty()) {
            System.out.println("  No returned packages");
            return;
        }
        for (Package pkg : returnedPackages) {
            System.out.println("  " + pkg);
        }
    }

    public void displaySummary() {
        System.out.println("\n=== DELIVERY SUMMARY ===");
        System.out.println("Total packages processed: " + packageIds.size());
        System.out.println("Successfully delivered: " + deliveredPackages.size());
        System.out.println("Returned/Cancelled: " + returnedPackages.size());
        System.out.println("Pending: " + pendingDeliveries.size());
    }

    public static void main(String[] args) {
        WarehouseDeliveryTracking system = new WarehouseDeliveryTracking();

        System.out.println("=== Adding Delivery Requests ===");
        system.addDeliveryRequest(new Package("PKG001", "Amazon", "John Doe", "123 Main St", 2.5));
        system.addDeliveryRequest(new Package("PKG002", "Flipkart", "Jane Smith", "456 Oak Ave", 1.2));
        system.addDeliveryRequest(new Package("PKG003", "eBay", "Bob Wilson", "789 Pine Rd", 5.0));
        system.addDeliveryRequest(new Package("PKG001", "Duplicate", "Test", "Test Address", 1.0));
        system.addDeliveryRequest(new Package("PKG004", "Walmart", "Alice Brown", "321 Elm St", 3.3));
        system.addDeliveryRequest(new Package("PKG005", "Target", "Charlie Davis", "654 Maple Dr", 0.8));

        system.displayPendingDeliveries();
        system.processDeliveries();
        system.displayDeliveredPackages();
        system.displayReturnedPackages();

        system.reprocessReturnedPackage();
        system.displayPendingDeliveries();

        if (!system.pendingDeliveries.isEmpty()) {
            system.processDeliveries();
        }

        system.displaySummary();
    }
}
