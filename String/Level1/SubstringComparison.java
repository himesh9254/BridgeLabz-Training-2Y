import java.util.Scanner;

public class SubstringComparison {
    
    // Method to create substring using charAt() method
    public static String createSubstringUsingCharAt(String text, int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex > text.length() || startIndex >= endIndex) {
            return "";
        }
        
        String result = "";
        for (int i = startIndex; i < endIndex; i++) {
            result += text.charAt(i);
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
        
        // Take user input
        System.out.print("Enter a string: ");
        String text = scanner.next();
        
        System.out.print("Enter start index: ");
        int startIndex = scanner.nextInt();
        
        System.out.print("Enter end index: ");
        int endIndex = scanner.nextInt();
        
        // Validate indices
        if (startIndex < 0 || endIndex > text.length() || startIndex >= endIndex) {
            System.out.println("Invalid indices! Please ensure 0 <= start < end <= " + text.length());
            scanner.close();
            return;
        }
        
        // Create substring using custom charAt() method
        String customSubstring = createSubstringUsingCharAt(text, startIndex, endIndex);
        
        // Create substring using built-in substring() method
        String builtInSubstring = text.substring(startIndex, endIndex);
        
        // Compare the two substrings
        boolean areEqual = compareStringsUsingCharAt(customSubstring, builtInSubstring);
        
        // Display results
        System.out.println("\n--- Substring Comparison Results ---");
        System.out.println("Original string: " + text);
        System.out.println("Start index: " + startIndex);
        System.out.println("End index: " + endIndex);
        System.out.println("Substring using charAt(): " + customSubstring);
        System.out.println("Substring using substring(): " + builtInSubstring);
        System.out.println("Are both substrings equal? " + areEqual);
        
        if (areEqual) {
            System.out.println("✓ Both methods produce the same substring!");
        } else {
            System.out.println("✗ Methods produce different substrings!");
        }
        
        scanner.close();
    }
}
