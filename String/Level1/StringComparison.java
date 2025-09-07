import java.util.Scanner;

public class StringComparison {
    
    // Method to compare two strings using charAt() method
    public static boolean compareStringsUsingCharAt(String str1, String str2) {
        // Check if lengths are different
        if (str1.length() != str2.length()) {
            return false;
        }
        
        // Compare each character
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Take user input for two strings
        System.out.print("Enter first string: ");
        String string1 = scanner.next();
        
        System.out.print("Enter second string: ");
        String string2 = scanner.next();
        
        // Compare using custom charAt() method
        boolean customResult = compareStringsUsingCharAt(string1, string2);
        
        // Compare using built-in equals() method
        boolean builtInResult = string1.equals(string2);
        
        // Display results
        System.out.println("\n--- String Comparison Results ---");
        System.out.println("String 1: " + string1);
        System.out.println("String 2: " + string2);
        System.out.println("Result using charAt() method: " + customResult);
        System.out.println("Result using equals() method: " + builtInResult);
        
        // Check if both methods give the same result
        if (customResult == builtInResult) {
            System.out.println("✓ Both methods give the same result!");
        } else {
            System.out.println("✗ Methods give different results!");
        }
        
        scanner.close();
    }
}
