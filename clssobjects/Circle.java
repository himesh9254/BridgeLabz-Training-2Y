public class Circle {
    // Attribute
    private double radius;
    private final double PI = 3.14159;
    
    // Constructor
    public Circle(double radius) {
        this.radius = radius;
    }
    
    // Method to calculate area
    public double calculateArea() {
        return PI * radius * radius;
    }
    
    // Method to calculate circumference
    public double calculateCircumference() {
        return 2 * PI * radius;
    }
    
    // Method to display area and circumference
    public void displayDetails() {
        System.out.println("=== Circle Details ===");
        System.out.println("Radius: " + radius);
        System.out.printf("Area: %.2f%n", calculateArea());
        System.out.printf("Circumference: %.2f%n", calculateCircumference());
        System.out.println("=====================");
    }
    
    // Getters and Setters
    public double getRadius() {
        return radius;
    }
    
    public void setRadius(double radius) {
        this.radius = radius;
    }
    
    // Main method to test the class
    public static void main(String[] args) {
        // Create circle objects
        Circle circle1 = new Circle(5.0);
        Circle circle2 = new Circle(7.5);
        Circle circle3 = new Circle(10.0);
        
        // Display circle details
        circle1.displayDetails();
        circle2.displayDetails();
        circle3.displayDetails();
    }
}
