public class MovieTicket {
    // Attributes
    private String movieName;
    private String seatNumber;
    private double price;
    private boolean isBooked;
    private String customerName;
    
    // Constructor
    public MovieTicket(String movieName, String seatNumber, double price) {
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.price = price;
        this.isBooked = false;
        this.customerName = "";
    }
    
    // Method to book a ticket
    public boolean bookTicket(String customerName) {
        if (!isBooked) {
            this.customerName = customerName;
            this.isBooked = true;
            System.out.println("=== Ticket Booking Successful ===");
            System.out.println("Movie: " + movieName);
            System.out.println("Seat: " + seatNumber);
            System.out.println("Customer: " + customerName);
            System.out.println("Price: $" + price);
            System.out.println("=================================");
            return true;
        } else {
            System.out.println("=== Booking Failed ===");
            System.out.println("Seat " + seatNumber + " for " + movieName + " is already booked!");
            System.out.println("======================");
            return false;
        }
    }
    
    // Method to cancel booking
    public boolean cancelBooking() {
        if (isBooked) {
            System.out.println("=== Booking Cancelled ===");
            System.out.println("Movie: " + movieName);
            System.out.println("Seat: " + seatNumber);
            System.out.println("Customer: " + customerName);
            System.out.println("Refund Amount: $" + price);
            
            this.customerName = "";
            this.isBooked = false;
            System.out.println("=========================");
            return true;
        } else {
            System.out.println("No booking found to cancel for seat " + seatNumber);
            return false;
        }
    }
    
    // Method to display ticket details
    public void displayTicketDetails() {
        System.out.println("=== Movie Ticket Details ===");
        System.out.println("Movie: " + movieName);
        System.out.println("Seat Number: " + seatNumber);
        System.out.println("Price: $" + price);
        System.out.println("Status: " + (isBooked ? "BOOKED" : "AVAILABLE"));
        if (isBooked) {
            System.out.println("Customer: " + customerName);
        }
        System.out.println("============================");
    }
    
    // Method to display booking summary
    public void displayBookingSummary() {
        if (isBooked) {
            System.out.println("=== Booking Summary ===");
            System.out.println("🎬 " + movieName);
            System.out.println("💺 Seat: " + seatNumber);
            System.out.println("👤 Customer: " + customerName);
            System.out.println("💰 Price: $" + price);
            System.out.println("✅ Status: CONFIRMED");
            System.out.println("======================");
        } else {
            System.out.println("No booking found for this ticket.");
        }
    }
    
    // Method to check availability
    public boolean isAvailable() {
        return !isBooked;
    }
    
    // Getters and Setters
    public String getMovieName() {
        return movieName;
    }
    
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    
    public String getSeatNumber() {
        return seatNumber;
    }
    
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public boolean isBooked() {
        return isBooked;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    // Main method to test the movie ticket booking system
    public static void main(String[] args) {
        // Create movie ticket objects
        MovieTicket ticket1 = new MovieTicket("Avengers: Endgame", "A12", 15.00);
        MovieTicket ticket2 = new MovieTicket("The Lion King", "B5", 12.50);
        MovieTicket ticket3 = new MovieTicket("Spider-Man", "C8", 14.00);
        MovieTicket ticket4 = new MovieTicket("Avatar", "A1", 16.00);
        
        // Display initial ticket status
        System.out.println("=== Initial Ticket Status ===");
        ticket1.displayTicketDetails();
        ticket2.displayTicketDetails();
        ticket3.displayTicketDetails();
        ticket4.displayTicketDetails();
        
        // Book tickets
        System.out.println("\\n=== Booking Tickets ===");
        ticket1.bookTicket("John Doe");
        ticket2.bookTicket("Jane Smith");
        ticket1.bookTicket("Bob Wilson"); // Should fail - already booked
        ticket3.bookTicket("Alice Johnson");
        
        // Display booking summaries
        System.out.println("\\n=== Booking Summaries ===");
        ticket1.displayBookingSummary();
        ticket2.displayBookingSummary();
        ticket3.displayBookingSummary();
        ticket4.displayBookingSummary();
        
        // Cancel a booking and try to book again
        System.out.println("\\n=== Cancellation and Re-booking ===");
        ticket2.cancelBooking();
        ticket2.bookTicket("Charlie Brown");
        ticket2.displayBookingSummary();
    }
}
