import java.util.Scanner;

public class ArrayIndexOutOfBoundsExceptionDemo {
    
    // Method to generate ArrayIndexOutOfBoundsException
    public static void generateArrayIndexOutOfBoundsException(String[] names) {
        System.out.println("\n--- Generating ArrayIndexOutOfBoundsException ---");
        System.out.println("Array length: " + names.length);
        System.out.print("Array contents: [");
        for (int i = 0; i < names.length; i++) {
            System.out.print(names[i]);
            if (i < names.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        
        int invalidIndex = names.length + 3; // Index beyond array length
        System.out.println("Attempting to access index " + invalidIndex + "...");
        
        // This will throw ArrayIndexOutOfBoundsException
        String name = names[invalidIndex];
        System.out.println("Name at index " + invalidIndex + ": " + name);
    }
    
    // Method to handle ArrayIndexOutOfBoundsException using try-catch
    public static void handleArrayIndexOutOfBoundsException(String[] names) {
        System.out.println("\n--- Handling ArrayIndexOutOfBoundsException ---");
        System.out.println("Array length: " + names.length);
        System.out.print("Array contents: [");
        for (int i = 0; i < names.length; i++) {
            System.out.print(names[i]);
            if (i < names.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        
        int invalidIndex = names.length + 3; // Index beyond array length
        
        try {
            System.out.println("Attempting to access index " + invalidIndex + "...");
            String name = names[invalidIndex];
            System.out.println("Name at index " + invalidIndex + ": " + name);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("✓ ArrayIndexOutOfBoundsException caught and handled!");
            System.out.println("Error message: " + e.getMessage());
            System.out.println("Valid indices for this array are 0 to " + (names.length - 1));
        } catch (RuntimeException e) {
            System.out.println("Generic runtime exception caught: " + e.getClass().getSimpleName());
            System.out.println("Error message: " + e.getMessage());
        }
        
        System.out.println("Program continues to execute after handling the exception.");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== ArrayIndexOutOfBoundsException Demonstration ===");
        
        // Take user input for array size
        System.out.print("Enter the number of names you want to input: ");
        int size = scanner.nextInt();
        
        if (size <= 0) {
            System.out.println("Array size must be greater than 0.");
            scanner.close();
            return;
        }
        
        // Create array and take input
        String[] names = new String[size];
        System.out.println("Enter " + size + " names:");
        
        for (int i = 0; i < size; i++) {
            System.out.print("Name " + (i + 1) + ": ");
            names[i] = scanner.next();
        }
        
        // First, demonstrate the exception being thrown (will be caught)
        try {
            generateArrayIndexOutOfBoundsException(names);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("✗ Program caught unhandled ArrayIndexOutOfBoundsException!");
            System.out.println("Error: " + e.getMessage());
        }
        
        // Then demonstrate proper exception handling
        handleArrayIndexOutOfBoundsException(names);
        
        System.out.println("\n=== Program completed successfully ===");
        scanner.close();
    }
}
