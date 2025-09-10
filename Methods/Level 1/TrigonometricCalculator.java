import java.util.Scanner;

class TrigonometricCalculator {
    
    public static double[] calculateTrigonometricFunctions(double angle) {
        double radians = Math.toRadians(angle);
        double sine = Math.sin(radians);
        double cosine = Math.cos(radians);
        double tangent = Math.tan(radians);
        
        return new double[]{sine, cosine, tangent};
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter angle in degrees: ");
        double angle = input.nextDouble();
        
        double[] trigFunctions = calculateTrigonometricFunctions(angle);
        
        System.out.printf("For angle %.2f degrees:%n", angle);
        System.out.printf("Sine: %.6f%n", trigFunctions[0]);
        System.out.printf("Cosine: %.6f%n", trigFunctions[1]);
        System.out.printf("Tangent: %.6f%n", trigFunctions[2]);
        
        input.close();
    }
}
