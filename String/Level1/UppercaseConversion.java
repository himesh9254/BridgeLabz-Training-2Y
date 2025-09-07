import java.util.Scanner;

public class UppercaseConversion {
    
    // Method to convert text to uppercase using charAt() and ASCII values
    public static String convertToUppercaseUsingCharAt(String text) {
        String result = "";
        
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            
            // Check if character is lowercase (ASCII 'a' = 97, 'z' = 122)
            if (currentChar >= 'a' && currentChar <= 'z') {
                // Convert to uppercase by subtracting 32 (ASCII 'A' = 65, 'a' = 97, difference = 32)
                char uppercaseChar = (char)(currentChar - 32);
                result += uppercaseChar;
            } else {
                // Keep the character as is (already uppercase, digit, or special character)
                result += currentChar;
            }
        }
        
        return result;
    }
    
    // Method to compare two strings using charAt() method
    public static boolean compareStringsUsingCharAt(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Uppercase Conversion Demonstration ===");
        
        // Take user input using nextLine() to capture complete text with spaces
        System.out.print("Enter a text (can include spaces): ");
        String text = scanner.nextLine();
        
        // Convert to uppercase using custom method (charAt() and ASCII logic)
        String customUppercase = convertToUppercaseUsingCharAt(text);
        
        // Convert to uppercase using built-in toUpperCase() method
        String builtInUppercase = text.toUpperCase();
        
        // Compare the two results
        boolean areEqual = compareStringsUsingCharAt(customUppercase, builtInUppercase);
        
        // Display results
        System.out.println("\n--- Uppercase Conversion Results ---");
        System.out.println("Original text: \"" + text + "\"");
        System.out.println("Uppercase using charAt() method: \"" + customUppercase + "\"");
        System.out.println("Uppercase using toUpperCase() method: \"" + builtInUppercase + "\"");
        System.out.println("Are both results equal? " + areEqual);
        
        if (areEqual) {
            System.out.println("✓ Both methods produce the same uppercase text!");
        } else {
            System.out.println("✗ Methods produce different results!");
        }
        
        // Additional demonstration with ASCII values
        System.out.println("\n--- ASCII Value Demonstration ---");
        System.out.println("ASCII value of 'a': " + (int)'a');
        System.out.println("ASCII value of 'A': " + (int)'A');
        System.out.println("Difference: " + ((int)'a' - (int)'A'));
        
        scanner.close();
    }
}
