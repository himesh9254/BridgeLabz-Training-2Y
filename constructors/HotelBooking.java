public class HotelBooking {
    private String guestName;
    private String roomType;
    private int nights;
    private double pricePerNight;
    
    // Default constructor
    public HotelBooking() {
        this.guestName = "Guest";
        this.roomType = "Standard";
        this.nights = 1;
        this.pricePerNight = 100.0;
    }
    
    // Parameterized constructor
    public HotelBooking(String guestName, String roomType, int nights) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.nights = nights > 0 ? nights : 1;
        this.pricePerNight = calculatePricePerNight(roomType);
    }
    
    // Parameterized constructor with custom price
    public HotelBooking(String guestName, String roomType, int nights, double pricePerNight) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.nights = nights > 0 ? nights : 1;
        this.pricePerNight = pricePerNight > 0 ? pricePerNight : 100.0;
    }
    
    // Copy constructor
    public HotelBooking(HotelBooking other) {
        if (other != null) {
            this.guestName = other.guestName;
            this.roomType = other.roomType;
            this.nights = other.nights;
            this.pricePerNight = other.pricePerNight;
        } else {
            // Default values if null is passed
            this.guestName = "Guest";
            this.roomType = "Standard";
            this.nights = 1;
            this.pricePerNight = 100.0;
        }
    }
    
    // Helper method to calculate price per night based on room type
    private double calculatePricePerNight(String roomType) {
        switch (roomType.toLowerCase()) {
            case "standard":
                return 100.0;
            case "deluxe":
                return 150.0;
            case "suite":
                return 250.0;
            case "presidential":
                return 500.0;
            default:
                return 100.0; // Default to standard room price
        }
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
    
    public double getPricePerNight() {
        return pricePerNight;
    }
    
    // Setter methods
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
    
    public void setRoomType(String roomType) {
        this.roomType = roomType;
        this.pricePerNight = calculatePricePerNight(roomType);
    }
    
    public void setNights(int nights) {
        this.nights = nights > 0 ? nights : 1;
    }
    
    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight > 0 ? pricePerNight : 100.0;
    }
    
    // Calculate total cost
    public double calculateTotalCost() {
        return nights * pricePerNight;
    }
    
    // Calculate total cost with tax
    public double calculateTotalCostWithTax(double taxRate) {
        double totalCost = calculateTotalCost();
        return totalCost + (totalCost * taxRate / 100);
    }
    
    // Display booking information
    public void displayBooking() {
        System.out.println("Hotel Booking Details:");
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room Type: " + roomType);
        System.out.println("Number of Nights: " + nights);
        System.out.printf("Price per Night: $%.2f%n", pricePerNight);
        System.out.printf("Total Cost: $%.2f%n", calculateTotalCost());
        System.out.printf("Total Cost (with 10%% tax): $%.2f%n", calculateTotalCostWithTax(10));
        System.out.println("------------------------");
    }
    
    // Main method for testing
    public static void main(String[] args) {
        // Using default constructor
        HotelBooking booking1 = new HotelBooking();
        System.out.println("Booking created with default constructor:");
        booking1.displayBooking();
        
        // Using parameterized constructor
        HotelBooking booking2 = new HotelBooking("John Smith", "Deluxe", 3);
        System.out.println("Booking created with parameterized constructor:");
        booking2.displayBooking();
        
        // Using parameterized constructor with custom price
        HotelBooking booking3 = new HotelBooking("Alice Johnson", "Suite", 2, 300.0);
        System.out.println("Booking created with custom price:");
        booking3.displayBooking();
        
        // Using copy constructor
        HotelBooking booking4 = new HotelBooking(booking2);
        System.out.println("Booking created using copy constructor:");
        booking4.displayBooking();
        
        // Modify the copied booking to show independence
        booking4.setGuestName("Jane Doe");
        booking4.setRoomType("Presidential");
        booking4.setNights(1);
        
        System.out.println("Original booking2:");
        booking2.displayBooking();
        System.out.println("Modified copied booking4:");
        booking4.displayBooking();
        
        // Test with null copy constructor
        HotelBooking booking5 = new HotelBooking(null);
        System.out.println("Booking created from null:");
        booking5.displayBooking();
    }
}
