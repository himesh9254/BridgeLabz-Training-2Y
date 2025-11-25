import java.util.*;

class Parcel implements Comparable<Parcel> {
    private String parcelId;
    private String sender;
    private String receiver;
    private String destination;
    private int priority;
    private String status;

    public Parcel(String parcelId, String sender, String receiver, String destination, int priority) {
        this.parcelId = parcelId;
        this.sender = sender;
        this.receiver = receiver;
        this.destination = destination;
        this.priority = priority;
        this.status = "Pending";
    }

    public String getParcelId() { return parcelId; }
    public String getSender() { return sender; }
    public String getReceiver() { return receiver; }
    public String getDestination() { return destination; }
    public int getPriority() { return priority; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public int compareTo(Parcel other) {
        return Integer.compare(other.priority, this.priority);
    }

    @Override
    public String toString() {
        return String.format("Parcel[%s, %s->%s, Priority:%d, %s]", 
            parcelId, sender, receiver, priority, status);
    }
}

public class CourierDeliveryRouting {
    private PriorityQueue<Parcel> priorityDeliveries;
    private Queue<Parcel> normalDeliveries;
    private Set<String> assignedDeliveryIds;
    private List<Parcel> completedDeliveries;

    public CourierDeliveryRouting() {
        priorityDeliveries = new PriorityQueue<>();
        normalDeliveries = new LinkedList<>();
        assignedDeliveryIds = new HashSet<>();
        completedDeliveries = new ArrayList<>();
    }

    public boolean addParcel(Parcel parcel) {
        if (assignedDeliveryIds.contains(parcel.getParcelId())) {
            System.out.println("Duplicate delivery ID: " + parcel.getParcelId());
            return false;
        }
        
        assignedDeliveryIds.add(parcel.getParcelId());
        
        if (parcel.getPriority() >= 8) {
            priorityDeliveries.add(parcel);
            System.out.println("High priority parcel added: " + parcel);
        } else {
            normalDeliveries.add(parcel);
            System.out.println("Normal parcel added: " + parcel);
        }
        return true;
    }

    public void assignDeliveryAgents() {
        System.out.println("\n=== Assigning Delivery Agents (Priority First) ===");
        
        while (!priorityDeliveries.isEmpty()) {
            Parcel parcel = priorityDeliveries.poll();
            parcel.setStatus("Assigned - Express");
            System.out.println("EXPRESS: " + parcel.getParcelId() + " assigned to Agent");
            deliverParcel(parcel);
        }

        System.out.println("\n=== Processing Normal Deliveries ===");
        while (!normalDeliveries.isEmpty()) {
            Parcel parcel = normalDeliveries.poll();
            parcel.setStatus("Assigned - Standard");
            System.out.println("STANDARD: " + parcel.getParcelId() + " assigned to Agent");
            deliverParcel(parcel);
        }
    }

    private void deliverParcel(Parcel parcel) {
        parcel.setStatus("Delivered");
        completedDeliveries.add(parcel);
        System.out.println("  -> Delivered: " + parcel.getParcelId() + " to " + parcel.getReceiver());
    }

    public void displayPendingDeliveries() {
        System.out.println("\n=== Pending Priority Deliveries ===");
        PriorityQueue<Parcel> tempPriority = new PriorityQueue<>(priorityDeliveries);
        while (!tempPriority.isEmpty()) {
            System.out.println("  " + tempPriority.poll());
        }

        System.out.println("\n=== Pending Normal Deliveries ===");
        for (Parcel parcel : normalDeliveries) {
            System.out.println("  " + parcel);
        }
    }

    public void displayCompletedDeliveries() {
        System.out.println("\n=== Completed Deliveries (" + completedDeliveries.size() + ") ===");
        for (Parcel parcel : completedDeliveries) {
            System.out.println("  " + parcel);
        }
    }

    public void displayAssignedIds() {
        System.out.println("\n=== All Assigned Delivery IDs ===");
        System.out.println("  " + assignedDeliveryIds);
    }

    public static void main(String[] args) {
        CourierDeliveryRouting system = new CourierDeliveryRouting();

        System.out.println("=== Adding Parcels ===");
        system.addParcel(new Parcel("DEL001", "Amazon", "John Doe", "New York", 5));
        system.addParcel(new Parcel("DEL002", "FedEx", "Jane Smith", "Los Angeles", 9));
        system.addParcel(new Parcel("DEL003", "UPS", "Bob Wilson", "Chicago", 3));
        system.addParcel(new Parcel("DEL004", "DHL", "Alice Brown", "Miami", 10));
        system.addParcel(new Parcel("DEL005", "USPS", "Charlie Davis", "Seattle", 7));
        system.addParcel(new Parcel("DEL001", "Duplicate", "Test", "Test City", 5));
        system.addParcel(new Parcel("DEL006", "Express", "Eve Wilson", "Boston", 8));

        system.displayAssignedIds();
        system.displayPendingDeliveries();
        system.assignDeliveryAgents();
        system.displayCompletedDeliveries();
    }
}
