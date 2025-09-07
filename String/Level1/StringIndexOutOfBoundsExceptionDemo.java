import java.util.Scanner;

public class StringIndexOutOfBoundsExceptionDemo {
    
    // Method to generate StringIndexOutOfBoundsException
    public static void generateStringIndexOutOfBoundsException(String text) {
        System.out.println("\n--- Generating StringIndexOutOfBoundsException ---");
        System.out.println("String: " + text);
        System.out.println("String length: " + text.length());
        
        int invalidIndex = text.length() + 5; // Index beyond string length
        System.out.println("Attempting to access index " + invalidIndex + " using charAt()...");
        
        // This will throw StringIndexOutOfBoundsException
        char character = text.charAt(invalidIndex);
        System.out.println("Character at index " + invalidIndex + ": " + character);
    }
    
    // Method to handle StringIndexOutOfBoundsException using try-catch
    public static void handleStringIndexOutOfBoundsException(String text) {
        System.out.println("\n--- Handling StringIndexOutOfBoundsException ---");
        System.out.println("String: " + text);
        System.out.println("String length: " + text.length());
        
        int invalidIndex = text.length() + 5; // Index beyond string length
        
        try {
            System.out.println("Attempting to access index " + invalidIndex + " using charAt()...");
            char character = text.charAt(invalidIndex);
            System.out.println("Character at index " + invalidIndex + ": " + character);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("✓ StringIndexOutOfBoundsException caught and handled!");
            System.out.println("Error message: " + e.getMessage());
            System.out.println("Valid indices for this string are 0 to " + (text.length() - 1));
        } catch (RuntimeException e) {
            System.out.println("Generic runtime exception caught: " + e.getMessage());
        }
        
        System.out.println("Program continues to execute after handling the exception.");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== StringIndexOutOfBoundsException Demonstration ===");
        
        // Take user input
        System.out.print("Enter a string: ");
        String text = scanner.next();
        
        // First, demonstrate the exception being thrown (will be caught)
        try {
            generateStringIndexOutOfBoundsException(text);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("✗ Program caught unhandled StringIndexOutOfBoundsException!");
            System.out.println("Error: " + e.getMessage());
        }
        
        // Then demonstrate proper exception handling
        handleStringIndexOutOfBoundsException(text);
        
        System.out.println("\n=== Program completed successfully ===");
        scanner.close();
    }
}
