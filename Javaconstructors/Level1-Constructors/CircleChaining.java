/**
 * Circle class demonstrating constructor chaining
 * Shows how to use this() to call other constructors
 */
public class CircleChaining {
    // Instance variable
    private double radius;
    private String color;
    private static final double DEFAULT_RADIUS = 1.0;
    private static final String DEFAULT_COLOR = "Red";
    
    // Default constructor - chains to parameterized constructor
    public CircleChaining() {
        this(DEFAULT_RADIUS); // Constructor chaining - calls single parameter constructor
        System.out.println("Default constructor called - using default radius");
    }
    
    // Single parameter constructor - chains to two parameter constructor
    public CircleChaining(double radius) {
        this(radius, DEFAULT_COLOR); // Constructor chaining - calls two parameter constructor
        System.out.println("Single parameter constructor called - using default color");
    }
    
    // Two parameter constructor - the main constructor that does actual initialization
    public CircleChaining(double radius, String color) {
        System.out.println("Two parameter constructor called - performing actual initialization");
        if (radius > 0) {
            this.radius = radius;
        } else {
            this.radius = DEFAULT_RADIUS;
            System.out.println("Invalid radius provided, using default radius: " + DEFAULT_RADIUS);
        }
        
        this.color = (color != null && !color.trim().isEmpty()) ? color : DEFAULT_COLOR;
        System.out.printf("Circle created: radius=%.2f, color=%s%n", this.radius, this.color);
    }
    
    // Copy constructor - chains to two parameter constructor
    public CircleChaining(CircleChaining other) {
        this(other.radius, other.color); // Constructor chaining - copies from another circle
        System.out.println("Copy constructor called - circle cloned");
    }
    
    // Getter methods
    public double getRadius() {
        return radius;
    }
    
    public String getColor() {
        return color;
    }
    
    // Setter methods
    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
            System.out.printf("Radius updated to: %.2f%n", radius);
        } else {
            System.out.println("Invalid radius! Radius must be positive.");
        }
    }
    
    public void setColor(String color) {
        if (color != null && !color.trim().isEmpty()) {
            this.color = color;
            System.out.println("Color updated to: " + color);
        } else {
            System.out.println("Invalid color! Using current color: " + this.color);
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
    
    // Calculate diameter
    public double calculateDiameter() {
        return 2 * radius;
    }
    
    // Display circle details
    public void displayCircleDetails() {
        System.out.println("=== Circle Details ===");
        System.out.printf("Radius: %.2f units%n", radius);
        System.out.println("Color: " + color);
        System.out.printf("Diameter: %.2f units%n", calculateDiameter());
        System.out.printf("Circumference: %.2f units%n", calculateCircumference());
        System.out.printf("Area: %.2f square units%n", calculateArea());
        System.out.println("=====================");
    }
    
    // Method to compare two circles
    public boolean isLargerThan(CircleChaining other) {
        return this.radius > other.radius;
    }
    
    // Method to scale the circle
    public void scale(double factor) {
        if (factor > 0) {
            this.radius *= factor;
            System.out.printf("Circle scaled by factor %.2f. New radius: %.2f%n", factor, radius);
        } else {
            System.out.println("Scale factor must be positive!");
        }
    }
    
    // Override toString method
    @Override
    public String toString() {
        return String.format("Circle{radius=%.2f, color='%s', area=%.2f}", 
                           radius, color, calculateArea());
    }
    
    // Override equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CircleChaining circle = (CircleChaining) obj;
        return Double.compare(circle.radius, radius) == 0 && color.equals(circle.color);
    }
    
    // Main method for testing
    public static void main(String[] args) {
        System.out.println("=== Circle Constructor Chaining Demonstration ===\n");
        
        System.out.println("1. Creating circle with default constructor:");
        CircleChaining circle1 = new CircleChaining();
        circle1.displayCircleDetails();
        
        System.out.println("\n2. Creating circle with radius parameter:");
        CircleChaining circle2 = new CircleChaining(5.0);
        circle2.displayCircleDetails();
        
        System.out.println("\n3. Creating circle with radius and color parameters:");
        CircleChaining circle3 = new CircleChaining(3.5, "Blue");
        circle3.displayCircleDetails();
        
        System.out.println("\n4. Creating circle using copy constructor:");
        CircleChaining circle4 = new CircleChaining(circle3);
        circle4.displayCircleDetails();
        
        System.out.println("\n5. Testing invalid radius:");
        CircleChaining circle5 = new CircleChaining(-2.0, "Green");
        circle5.displayCircleDetails();
        
        System.out.println("\n6. Updating circle properties:");
        circle1.setRadius(7.0);
        circle1.setColor("Yellow");
        circle1.displayCircleDetails();
        
        System.out.println("\n7. Comparing circles:");
        System.out.println("Is circle1 larger than circle2? " + circle1.isLargerThan(circle2));
        System.out.println("Is circle2 larger than circle3? " + circle2.isLargerThan(circle3));
        
        System.out.println("\n8. Scaling a circle:");
        circle2.scale(1.5);
        circle2.displayCircleDetails();
        
        System.out.println("\n9. Using toString method:");
        System.out.println("Circle1: " + circle1);
        System.out.println("Circle2: " + circle2);
        System.out.println("Circle3: " + circle3);
        System.out.println("Circle4: " + circle4);
        
        System.out.println("\n10. Testing equality:");
        System.out.println("Are circle3 and circle4 equal? " + circle3.equals(circle4));
        
        System.out.println("\n=== Constructor Chaining Benefits ===");
        System.out.println("✓ Code Reusability: Avoids duplicate initialization code");
        System.out.println("✓ Maintenance: Changes in one constructor affect others automatically");
        System.out.println("✓ Consistency: Ensures all constructors follow same initialization logic");
        System.out.println("✓ Validation: Centralized validation in the main constructor");
    }
}
