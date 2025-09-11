public class MovieTicket {
    // Attributes
    private String movieName;
    private String seatNumber;
    private double price;
    private boolean isBooked;
    
    // Constructor
    public MovieTicket(String movieName) {
        this.movieName = movieName;
        this.seatNumber = null;
        this.price = 0.0;
        this.isBooked = false;
    }
    
    // Method to book a ticket (assign seat and update price)
    public boolean bookTicket(String seatNumber, double price) {
        if (!isBooked) {
            this.seatNumber = seatNumber;
            this.price = price;
            this.isBooked = true;
            System.out.println("Ticket booked successfully!");
            return true;
        } else {
            System.out.println("Ticket is already booked!");
            return false;
        }
    }
    
    // Method to cancel booking
    public void cancelBooking() {
        if (isBooked) {
            System.out.println("Booking cancelled for seat: " + seatNumber);
            this.seatNumber = null;
            this.price = 0.0;
            this.isBooked = false;
        } else {
            System.out.println("No booking found to cancel.");
        }
    }
    
    // Method to display ticket details
    public void displayTicketDetails() {
        System.out.println("Movie Ticket Details:");
        System.out.println("Movie Name: " + movieName);
        
        if (isBooked) {
            System.out.println("Seat Number: " + seatNumber);
            System.out.println("Price: $" + price);
            System.out.println("Status: Booked");
        } else {
            System.out.println("Status: Not Booked");
        }
        System.out.println("-------------------");
    }
    
    // Method to check if ticket is available
    public boolean isAvailable() {
        return !isBooked;
    }
    
    // Getter methods
    public String getMovieName() {
        return movieName;
    }
    
    public String getSeatNumber() {
        return seatNumber;
    }
    
    public double getPrice() {
        return price;
    }
    
    // Main method to test the class
    public static void main(String[] args) {
        // Creating MovieTicket objects
        MovieTicket ticket1 = new MovieTicket("Avengers: Endgame");
        MovieTicket ticket2 = new MovieTicket("The Dark Knight");
        
        // Display initial state
        ticket1.displayTicketDetails();
        ticket2.displayTicketDetails();
        
        // Book tickets
        ticket1.bookTicket("A15", 12.50);
        ticket2.bookTicket("B20", 15.00);
        
        // Display after booking
        ticket1.displayTicketDetails();
        ticket2.displayTicketDetails();
        
        // Try to book the same ticket again
        ticket1.bookTicket("A16", 12.50);
        
        // Cancel a booking
        ticket1.cancelBooking();
        ticket1.displayTicketDetails();
        
        // Book again after cancellation
        ticket1.bookTicket("C10", 10.00);
        ticket1.displayTicketDetails();
    }
}
