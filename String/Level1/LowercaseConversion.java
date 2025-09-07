import java.util.Scanner;

public class LowercaseConversion {
    
    // Method to convert text to lowercase using charAt() and ASCII values
    public static String convertToLowercaseUsingCharAt(String text) {
        String result = "";
        
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            
            // Check if character is uppercase (ASCII 'A' = 65, 'Z' = 90)
            if (currentChar >= 'A' && currentChar <= 'Z') {
                // Convert to lowercase by adding 32 (ASCII 'a' = 97, 'A' = 65, difference = 32)
                char lowercaseChar = (char)(currentChar + 32);
                result += lowercaseChar;
            } else {
                // Keep the character as is (already lowercase, digit, or special character)
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
        
        System.out.println("=== Lowercase Conversion Demonstration ===");
        
        // Take user input using nextLine() to capture complete text with spaces
        System.out.print("Enter a text (can include spaces): ");
        String text = scanner.nextLine();
        
        // Convert to lowercase using custom method (charAt() and ASCII logic)
        String customLowercase = convertToLowercaseUsingCharAt(text);
        
        // Convert to lowercase using built-in toLowerCase() method
        String builtInLowercase = text.toLowerCase();
        
        // Compare the two results
        boolean areEqual = compareStringsUsingCharAt(customLowercase, builtInLowercase);
        
        // Display results
        System.out.println("\n--- Lowercase Conversion Results ---");
        System.out.println("Original text: \"" + text + "\"");
        System.out.println("Lowercase using charAt() method: \"" + customLowercase + "\"");
        System.out.println("Lowercase using toLowerCase() method: \"" + builtInLowercase + "\"");
        System.out.println("Are both results equal? " + areEqual);
        
        if (areEqual) {
            System.out.println("✓ Both methods produce the same lowercase text!");
        } else {
            System.out.println("✗ Methods produce different results!");
        }
        
        scanner.close();
    }
}
