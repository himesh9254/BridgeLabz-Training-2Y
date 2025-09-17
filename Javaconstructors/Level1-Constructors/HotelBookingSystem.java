import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * HotelBooking class demonstrating default, parameterized, and copy constructors
 * Represents a hotel booking system with guest details and booking information
 */
public class HotelBookingSystem {
    // Instance variables
    private String guestName;
    private String roomType;
    private int nights;
    private LocalDate checkInDate;
    private double pricePerNight;
    private String bookingId;
    private static int bookingCounter = 1000;
    
    // Constants for room types and prices
    private static final String[] ROOM_TYPES = {"Standard", "Deluxe", "Suite", "Presidential"};
    private static final double[] ROOM_PRICES = {100.0, 150.0, 250.0, 500.0};
    
    // Default constructor
    public HotelBookingSystem() {
        this.guestName = "Guest";
        this.roomType = "Standard";
        this.nights = 1;
        this.checkInDate = LocalDate.now();
        this.pricePerNight = 100.0;
        this.bookingId = generateBookingId();
        System.out.println("Default constructor called - Booking created with default values");
    }
    
    // Parameterized constructor (basic)
    public HotelBookingSystem(String guestName, String roomType, int nights) {
        this.guestName = (guestName != null && !guestName.trim().isEmpty()) ? guestName : "Guest";
        this.roomType = validateRoomType(roomType);
        this.nights = (nights > 0) ? nights : 1;
        this.checkInDate = LocalDate.now();
        this.pricePerNight = getRoomPrice(this.roomType);
        this.bookingId = generateBookingId();
        System.out.println("Parameterized constructor (3 args) called - Booking created for: " + this.guestName);
    }
    
    // Parameterized constructor (with check-in date)
    public HotelBookingSystem(String guestName, String roomType, int nights, LocalDate checkInDate) {
        this.guestName = (guestName != null && !guestName.trim().isEmpty()) ? guestName : "Guest";
        this.roomType = validateRoomType(roomType);
        this.nights = (nights > 0) ? nights : 1;
        this.checkInDate = (checkInDate != null && !checkInDate.isBefore(LocalDate.now())) ? 
                          checkInDate : LocalDate.now();
        this.pricePerNight = getRoomPrice(this.roomType);
        this.bookingId = generateBookingId();
        System.out.println("Parameterized constructor (4 args) called - Booking created for: " + this.guestName);
    }
    
    // Copy constructor
    public HotelBookingSystem(HotelBookingSystem other) {
        if (other != null) {
            this.guestName = other.guestName;
            this.roomType = other.roomType;
            this.nights = other.nights;
            this.checkInDate = other.checkInDate;
            this.pricePerNight = other.pricePerNight;
            this.bookingId = generateBookingId(); // Generate new booking ID for copy
            System.out.println("Copy constructor called - Booking copied for: " + this.guestName);
        } else {
            // If null object passed, use default constructor
            this();
            System.out.println("Warning: Null booking passed to copy constructor, using defaults");
        }
    }
    
    // Utility method to generate booking ID
    private static String generateBookingId() {
        return "BK" + (bookingCounter++);
    }
    
    // Utility method to validate room type
    private String validateRoomType(String roomType) {
        if (roomType != null) {
            for (String validType : ROOM_TYPES) {
                if (validType.equalsIgnoreCase(roomType)) {
                    return validType;
                }
            }
        }
        return "Standard"; // Default room type
    }
    
    // Utility method to get room price
    private double getRoomPrice(String roomType) {
        for (int i = 0; i < ROOM_TYPES.length; i++) {
            if (ROOM_TYPES[i].equals(roomType)) {
                return ROOM_PRICES[i];
            }
        }
        return ROOM_PRICES[0]; // Default price
    }
    
    // Getter methods
    public String getGuestName() {
        return guestName;
    }
    
    public String getRoomType() {
        return roomType;
    }
    
    public int getNights() {
        return nights;
    }
    
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    
    public LocalDate getCheckOutDate() {
        return checkInDate.plusDays(nights);
    }
    
    public double getPricePerNight() {
        return pricePerNight;
    }
    
    public String getBookingId() {
        return bookingId;
    }
    
    // Setter methods with validation
    public void setGuestName(String guestName) {
        if (guestName != null && !guestName.trim().isEmpty()) {
            this.guestName = guestName;
            System.out.println("Guest name updated to: " + guestName);
        } else {
            System.out.println("Invalid guest name provided!");
        }
    }
    
    public void setRoomType(String roomType) {
        String validatedRoomType = validateRoomType(roomType);
        if (!validatedRoomType.equals(this.roomType)) {
            this.roomType = validatedRoomType;
            this.pricePerNight = getRoomPrice(this.roomType);
            System.out.println("Room type updated to: " + this.roomType + 
                             " (Price per night: $" + this.pricePerNight + ")");
        } else if (roomType != null && !validateRoomType(roomType).equals(roomType)) {
            System.out.println("Invalid room type! Using: " + this.roomType);
        }
    }
    
    public void setNights(int nights) {
        if (nights > 0) {
            this.nights = nights;
            System.out.println("Number of nights updated to: " + nights);
        } else {
            System.out.println("Invalid number of nights! Must be positive.");
        }
    }
    
    public void setCheckInDate(LocalDate checkInDate) {
        if (checkInDate != null && !checkInDate.isBefore(LocalDate.now())) {
            this.checkInDate = checkInDate;
            System.out.println("Check-in date updated to: " + 
                             checkInDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        } else {
            System.out.println("Invalid check-in date! Must be today or future date.");
        }
    }
    
    // Calculate total cost
    public double calculateTotalCost() {
        return pricePerNight * nights;
    }
    
    // Calculate total cost with tax
    public double calculateTotalCostWithTax(double taxRate) {
        double totalCost = calculateTotalCost();
        return totalCost + (totalCost * taxRate / 100);
    }
    
    // Apply discount
    public void applyDiscount(double discountPercentage) {
        if (discountPercentage > 0 && discountPercentage <= 100) {
            double discountAmount = pricePerNight * (discountPercentage / 100);
            pricePerNight -= discountAmount;
            System.out.printf("Discount of %.1f%% applied. New price per night: $%.2f%n", 
                             discountPercentage, pricePerNight);
        } else {
            System.out.println("Invalid discount percentage!");
        }
    }
    
    // Display booking details
    public void displayBookingDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("=== Hotel Booking Details ===");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room Type: " + roomType);
        System.out.println("Number of Nights: " + nights);
        System.out.println("Check-in Date: " + checkInDate.format(formatter));
        System.out.println("Check-out Date: " + getCheckOutDate().format(formatter));
        System.out.printf("Price per Night: $%.2f%n", pricePerNight);
        System.out.printf("Total Cost: $%.2f%n", calculateTotalCost());
        System.out.printf("Total Cost (with 10%% tax): $%.2f%n", calculateTotalCostWithTax(10.0));
        System.out.println("=============================");
    }
    
    // Check if booking is for today
    public boolean isCheckInToday() {
        return checkInDate.equals(LocalDate.now());
    }
    
    // Get available room types
    public static void displayAvailableRoomTypes() {
        System.out.println("Available Room Types:");
        for (int i = 0; i < ROOM_TYPES.length; i++) {
            System.out.printf("- %s: $%.2f per night%n", ROOM_TYPES[i], ROOM_PRICES[i]);
        }
    }
    
    // Override toString method
    @Override
    public String toString() {
        return String.format("HotelBooking{bookingId='%s', guestName='%s', roomType='%s', nights=%d, totalCost=%.2f}", 
                           bookingId, guestName, roomType, nights, calculateTotalCost());
    }
    
    // Override equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HotelBookingSystem booking = (HotelBookingSystem) obj;
        return bookingId.equals(booking.bookingId);
    }
    
    // Main method for testing
    public static void main(String[] args) {
        System.out.println("=== Hotel Booking System Constructor Demonstration ===\\n");
        
        System.out.println("Available Room Types and Prices:");
        displayAvailableRoomTypes();
        System.out.println();
        
        System.out.println("1. Creating booking with default constructor:");
        HotelBookingSystem booking1 = new HotelBookingSystem();
        booking1.displayBookingDetails();
        
        System.out.println("\\n2. Creating booking with parameterized constructor (3 args):");
        HotelBookingSystem booking2 = new HotelBookingSystem("John Doe", "Deluxe", 3);
        booking2.displayBookingDetails();
        
        System.out.println("\\n3. Creating booking with parameterized constructor (4 args):");
        LocalDate futureDate = LocalDate.now().plusDays(7);
        HotelBookingSystem booking3 = new HotelBookingSystem("Jane Smith", "Suite", 2, futureDate);
        booking3.displayBookingDetails();
        
        System.out.println("\\n4. Creating booking using copy constructor:");
        HotelBookingSystem booking4 = new HotelBookingSystem(booking2);
        booking4.displayBookingDetails();
        
        System.out.println("\\n5. Verifying copy independence:");
        booking4.setGuestName("John Doe Jr.");
        booking4.setRoomType("Presidential");
        System.out.println("Original booking2: " + booking2);
        System.out.println("Modified booking4: " + booking4);
        
        System.out.println("\\n6. Testing invalid inputs:");
        HotelBookingSystem booking5 = new HotelBookingSystem("", "InvalidRoom", -5);
        booking5.displayBookingDetails();
        
        System.out.println("\\n7. Updating booking details:");
        booking1.setGuestName("Alice Johnson");
        booking1.setRoomType("Suite");
        booking1.setNights(4);
        booking1.displayBookingDetails();
        
        System.out.println("\\n8. Applying discount:");
        booking2.applyDiscount(15.0);
        booking2.displayBookingDetails();
        
        System.out.println("\\n9. Testing copy constructor with null:");
        HotelBookingSystem nullBooking = new HotelBookingSystem(null);
        nullBooking.displayBookingDetails();
        
        System.out.println("\\n10. Booking status checks:");
        System.out.println("Is booking1 check-in today? " + booking1.isCheckInToday());
        System.out.println("Is booking3 check-in today? " + booking3.isCheckInToday());
        
        System.out.println("\\n=== Constructor Types Demonstrated ===");
        System.out.println("✓ Default Constructor: Creates booking with standard values");
        System.out.println("✓ Parameterized Constructor (3 args): Basic booking details");
        System.out.println("✓ Parameterized Constructor (4 args): Includes check-in date");
        System.out.println("✓ Copy Constructor: Creates independent copy of existing booking");
        System.out.println("✓ Input Validation: All constructors validate and sanitize inputs");
    }
}
