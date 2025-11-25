import java.util.*;

class Booking implements Comparable<Booking> {
    private String bookingId;
    private String userId;
    private String eventName;
    private int numberOfTickets;
    private boolean isVIP;
    private String status;

    public Booking(String bookingId, String userId, String eventName, int numberOfTickets, boolean isVIP) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.eventName = eventName;
        this.numberOfTickets = numberOfTickets;
        this.isVIP = isVIP;
        this.status = "Pending";
    }

    public String getBookingId() { return bookingId; }
    public String getUserId() { return userId; }
    public String getEventName() { return eventName; }
    public int getNumberOfTickets() { return numberOfTickets; }
    public boolean isVIP() { return isVIP; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public int compareTo(Booking other) {
        if (this.isVIP && !other.isVIP) return -1;
        if (!this.isVIP && other.isVIP) return 1;
        return this.bookingId.compareTo(other.bookingId);
    }

    @Override
    public String toString() {
        String vipTag = isVIP ? " [VIP]" : "";
        return String.format("Booking[%s, User:%s, %s, Tickets:%d, %s]%s", 
            bookingId, userId, eventName, numberOfTickets, status, vipTag);
    }
}

public class EventTicketReservation {
    private List<Booking> allBookings;
    private Set<String> registeredUsers;
    private Queue<Booking> confirmationQueue;
    private PriorityQueue<Booking> vipPriorityQueue;

    public EventTicketReservation() {
        allBookings = new ArrayList<>();
        registeredUsers = new HashSet<>();
        confirmationQueue = new LinkedList<>();
        vipPriorityQueue = new PriorityQueue<>();
    }

    public boolean registerUser(String userId) {
        if (registeredUsers.contains(userId)) {
            System.out.println("User already registered: " + userId);
            return false;
        }
        registeredUsers.add(userId);
        System.out.println("User registered: " + userId);
        return true;
    }

    public boolean acceptBooking(Booking booking) {
        if (!registeredUsers.contains(booking.getUserId())) {
            System.out.println("User not registered: " + booking.getUserId());
            return false;
        }

        if (booking.isVIP()) {
            vipPriorityQueue.add(booking);
            System.out.println("VIP booking queued: " + booking.getBookingId());
        } else {
            confirmationQueue.add(booking);
            System.out.println("Normal booking queued: " + booking.getBookingId());
        }
        return true;
    }

    public void processBookings() {
        System.out.println("\n=== Processing VIP Bookings First ===");
        while (!vipPriorityQueue.isEmpty()) {
            Booking booking = vipPriorityQueue.poll();
            booking.setStatus("Confirmed");
            allBookings.add(booking);
            System.out.println("CONFIRMED (VIP): " + booking);
        }

        System.out.println("\n=== Processing Normal Bookings ===");
        while (!confirmationQueue.isEmpty()) {
            Booking booking = confirmationQueue.poll();
            booking.setStatus("Confirmed");
            allBookings.add(booking);
            System.out.println("CONFIRMED: " + booking);
        }
    }

    public void displayRegisteredUsers() {
        System.out.println("\n=== Registered Users (" + registeredUsers.size() + ") ===");
        for (String userId : registeredUsers) {
            System.out.println("  " + userId);
        }
    }

    public void displayPendingQueues() {
        System.out.println("\n=== Pending VIP Bookings ===");
        for (Booking b : vipPriorityQueue) {
            System.out.println("  " + b);
        }

        System.out.println("\n=== Pending Normal Bookings ===");
        for (Booking b : confirmationQueue) {
            System.out.println("  " + b);
        }
    }

    public void displayAllBookings() {
        System.out.println("\n=== All Confirmed Bookings (" + allBookings.size() + ") ===");
        for (Booking booking : allBookings) {
            System.out.println("  " + booking);
        }
    }

    public static void main(String[] args) {
        EventTicketReservation system = new EventTicketReservation();

        System.out.println("=== Registering Users ===");
        system.registerUser("USR001");
        system.registerUser("USR002");
        system.registerUser("USR003");
        system.registerUser("USR004");
        system.registerUser("USR001");

        system.displayRegisteredUsers();

        System.out.println("\n=== Accepting Bookings ===");
        system.acceptBooking(new Booking("BK001", "USR001", "Concert", 2, false));
        system.acceptBooking(new Booking("BK002", "USR002", "Concert", 4, true));
        system.acceptBooking(new Booking("BK003", "USR003", "Concert", 1, false));
        system.acceptBooking(new Booking("BK004", "USR004", "Concert", 3, true));
        system.acceptBooking(new Booking("BK005", "USR005", "Concert", 2, false));
        system.acceptBooking(new Booking("BK006", "USR001", "Concert", 2, false));

        system.displayPendingQueues();
        system.processBookings();
        system.displayAllBookings();
    }
}
