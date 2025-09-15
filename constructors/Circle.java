public class Circle {
    private double radius;
    private static final double DEFAULT_RADIUS = 1.0;
    
    // Default constructor - uses constructor chaining
    public Circle() {
        this(DEFAULT_RADIUS); // Calls parameterized constructor with default value
    }
    
    // Parameterized constructor
    public Circle(double radius) {
        if (radius > 0) {
            this.radius = radius;
        } else {
            System.out.println("Invalid radius! Setting to default value.");
            this.radius = DEFAULT_RADIUS;
        }
    }
    
    // Getter method
    public double getRadius() {
        return radius;
    }
    
    // Setter method
    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
        } else {
            System.out.println("Invalid radius! Radius unchanged.");
        }
    }
    
    // Calculate area
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    
    // Calculate circumference
    public double calculateCircumference() {
        return 2 * Math.PI * radius;
    }
    
    // Display circle information
    public void displayCircle() {
        System.out.println("Circle Information:");
        System.out.println("Radius: " + radius);
        System.out.printf("Area: %.2f%n", calculateArea());
        System.out.printf("Circumference: %.2f%n", calculateCircumference());
        System.out.println("------------------------");
    }
    
    // Main method for testing
    public static void main(String[] args) {
        // Using default constructor (constructor chaining)
        Circle circle1 = new Circle();
        System.out.println("Circle created with default constructor:");
        circle1.displayCircle();
        
        // Using parameterized constructor
        Circle circle2 = new Circle(5.0);
        System.out.println("Circle created with parameterized constructor:");
        circle2.displayCircle();
        
        // Testing with invalid radius
        Circle circle3 = new Circle(-3.0);
        System.out.println("Circle created with invalid radius:");
        circle3.displayCircle();
        
        // Modifying circle1
        circle1.setRadius(7.5);
        System.out.println("Circle1 after modification:");
        circle1.displayCircle();
    }
}
