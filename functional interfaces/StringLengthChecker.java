// 2. String Length Checker
// Check if a message exceeds character limit using Function<String, Integer>

import java.util.function.Function;

public class StringLengthChecker {
    public static void main(String[] args) {
        // Define character limit
        int characterLimit = 50;
        
        // Function to get string length
        Function<String, Integer> getLength = String::length;
        
        // Test messages
        String[] messages = {
            "Hello World!",
            "This is a short message.",
            "This is a very long message that definitely exceeds the character limit for testing purposes.",
            "OK",
            "Medium length message to test the functionality properly."
        };
        
        System.out.println("String Length Checker (Limit: " + characterLimit + " characters)");
        System.out.println("=".repeat(60));
        
        for (String message : messages) {
            int length = getLength.apply(message);
            
            if (length > characterLimit) {
                System.out.println("❌ EXCEEDS LIMIT:");
                System.out.println("   Message: \"" + message + "\"");
                System.out.println("   Length: " + length + " (exceeds by " + (length - characterLimit) + " characters)");
            } else {
                System.out.println("✓ WITHIN LIMIT:");
                System.out.println("   Message: \"" + message + "\"");
                System.out.println("   Length: " + length + " (remaining: " + (characterLimit - length) + " characters)");
            }
            System.out.println();
        }
        
        // Advanced: Chain functions
        Function<String, Integer> getLengthAndDouble = getLength.andThen(len -> len * 2);
        
        System.out.println("Chained Function Example (Length * 2):");
        System.out.println("=".repeat(60));
        String testMsg = "Test message";
        System.out.println("Message: \"" + testMsg + "\"");
        System.out.println("Original length: " + getLength.apply(testMsg));
        System.out.println("Doubled: " + getLengthAndDouble.apply(testMsg));
    }
}
