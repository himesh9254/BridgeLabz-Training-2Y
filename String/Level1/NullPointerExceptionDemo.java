public class NullPointerExceptionDemo {
    
    // Method to generate NullPointerException
    public static void generateNullPointerException() {
        System.out.println("\n--- Generating NullPointerException ---");
        String text = null;
        System.out.println("Attempting to call length() on null string...");
        
        // This will throw NullPointerException
        int length = text.length();
        System.out.println("Length: " + length);
    }
    
    // Method to handle NullPointerException using try-catch
    public static void handleNullPointerException() {
        System.out.println("\n--- Handling NullPointerException ---");
        String text = null;
        
        try {
            System.out.println("Attempting to call length() on null string...");
            int length = text.length();
            System.out.println("Length: " + length);
        } catch (NullPointerException e) {
            System.out.println("✓ NullPointerException caught and handled!");
            System.out.println("Error message: " + e.getMessage());
            System.out.println("The string variable was null, so we cannot call methods on it.");
        } catch (Exception e) {
            System.out.println("Generic exception caught: " + e.getMessage());
        }
        
        System.out.println("Program continues to execute after handling the exception.");
    }
    
    public static void main(String[] args) {
        System.out.println("=== NullPointerException Demonstration ===");
        
        // First, demonstrate the exception being thrown (will crash the program)
        try {
            generateNullPointerException();
        } catch (NullPointerException e) {
            System.out.println("✗ Program crashed due to unhandled NullPointerException!");
            System.out.println("Error: " + e.getMessage());
        }
        
        // Then demonstrate proper exception handling
        handleNullPointerException();
        
        System.out.println("\n=== Program completed successfully ===");
    }
}
