import java.util.Scanner;

public class IllegalArgumentExceptionDemo {
    
    // Method to generate IllegalArgumentException
    public static void generateIllegalArgumentException(String text) {
        System.out.println("\n--- Generating IllegalArgumentException ---");
        System.out.println("String: " + text);
        System.out.println("String length: " + text.length());
        
        int startIndex = 5;
        int endIndex = 2; // End index is less than start index
        System.out.println("Attempting substring with start index " + startIndex + " and end index " + endIndex + "...");
        
        // This will throw IllegalArgumentException (or StringIndexOutOfBoundsException)
        String substring = text.substring(startIndex, endIndex);
        System.out.println("Substring: " + substring);
    }
    
    // Method to handle IllegalArgumentException using try-catch
    public static void handleIllegalArgumentException(String text) {
        System.out.println("\n--- Handling IllegalArgumentException ---");
        System.out.println("String: " + text);
        System.out.println("String length: " + text.length());
        
        int startIndex = 5;
        int endIndex = 2; // End index is less than start index
        
        try {
            System.out.println("Attempting substring with start index " + startIndex + " and end index " + endIndex + "...");
            String substring = text.substring(startIndex, endIndex);
            System.out.println("Substring: " + substring);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ IllegalArgumentException caught and handled!");
            System.out.println("Error message: " + e.getMessage());
            System.out.println("Start index must be less than or equal to end index.");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("✓ StringIndexOutOfBoundsException caught and handled!");
            System.out.println("Error message: " + e.getMessage());
            System.out.println("The start index (" + startIndex + ") is greater than the end index (" + endIndex + ").");
        } catch (RuntimeException e) {
            System.out.println("Generic runtime exception caught: " + e.getClass().getSimpleName());
            System.out.println("Error message: " + e.getMessage());
        }
        
        System.out.println("Program continues to execute after handling the exception.");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== IllegalArgumentException Demonstration ===");
        
        // Take user input
        System.out.print("Enter a string (at least 6 characters): ");
        String text = scanner.next();
        
        if (text.length() < 6) {
            System.out.println("Please enter a string with at least 6 characters for this demonstration.");
            scanner.close();
            return;
        }
        
        // First, demonstrate the exception being thrown (will be caught)
        try {
            generateIllegalArgumentException(text);
        } catch (RuntimeException e) {
            System.out.println("✗ Program caught unhandled " + e.getClass().getSimpleName() + "!");
            System.out.println("Error: " + e.getMessage());
        }
        
        // Then demonstrate proper exception handling
        handleIllegalArgumentException(text);
        
        System.out.println("\n=== Program completed successfully ===");
        scanner.close();
    }
}
