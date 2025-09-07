import java.util.Scanner;

public class NumberFormatExceptionDemo {
    
    // Method to generate NumberFormatException
    public static void generateNumberFormatException(String text) {
        System.out.println("\n--- Generating NumberFormatException ---");
        System.out.println("Input text: " + text);
        System.out.println("Attempting to parse text as integer using Integer.parseInt()...");
        
        // This will throw NumberFormatException if text is not a valid number
        int number = Integer.parseInt(text);
        System.out.println("Parsed number: " + number);
    }
    
    // Method to handle NumberFormatException using try-catch
    public static void handleNumberFormatException(String text) {
        System.out.println("\n--- Handling NumberFormatException ---");
        System.out.println("Input text: " + text);
        
        try {
            System.out.println("Attempting to parse text as integer using Integer.parseInt()...");
            int number = Integer.parseInt(text);
            System.out.println("✓ Successfully parsed number: " + number);
        } catch (NumberFormatException e) {
            System.out.println("✓ NumberFormatException caught and handled!");
            System.out.println("Error message: " + e.getMessage());
            System.out.println("The input text '" + text + "' is not a valid integer.");
        } catch (RuntimeException e) {
            System.out.println("Generic runtime exception caught: " + e.getClass().getSimpleName());
            System.out.println("Error message: " + e.getMessage());
        }
        
        System.out.println("Program continues to execute after handling the exception.");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== NumberFormatException Demonstration ===");
        
        // Take user input
        System.out.print("Enter a text (try entering non-numeric text like 'hello'): ");
        String text = scanner.next();
        
        // First, demonstrate the exception being thrown (will be caught)
        try {
            generateNumberFormatException(text);
        } catch (NumberFormatException e) {
            System.out.println("✗ Program caught unhandled NumberFormatException!");
            System.out.println("Error: " + e.getMessage());
        }
        
        // Then demonstrate proper exception handling
        handleNumberFormatException(text);
        
        // Additional demonstration with valid number
        System.out.println("\n--- Testing with valid number ---");
        handleNumberFormatException("12345");
        
        System.out.println("\n=== Program completed successfully ===");
        scanner.close();
    }
}
