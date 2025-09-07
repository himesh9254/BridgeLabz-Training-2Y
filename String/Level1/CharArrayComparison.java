import java.util.Scanner;

public class CharArrayComparison {
    
    // Method to return characters in a string without using toCharArray()
    public static char[] getCharactersUsingCharAt(String text) {
        char[] result = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            result[i] = text.charAt(i);
        }
        return result;
    }
    
    // Method to compare two character arrays
    public static boolean compareCharArrays(char[] array1, char[] array2) {
        if (array1.length != array2.length) {
            return false;
        }
        
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
    
    // Helper method to display character array
    public static void displayCharArray(char[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print("'" + array[i] + "'");
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Take user input
        System.out.print("Enter a string: ");
        String text = scanner.next();
        
        // Get characters using custom method (charAt())
        char[] customCharArray = getCharactersUsingCharAt(text);
        
        // Get characters using built-in toCharArray() method
        char[] builtInCharArray = text.toCharArray();
        
        // Compare the two arrays
        boolean areEqual = compareCharArrays(customCharArray, builtInCharArray);
        
        // Display results
        System.out.println("\n--- Character Array Comparison Results ---");
        System.out.println("Original string: " + text);
        
        System.out.print("Array using charAt() method: ");
        displayCharArray(customCharArray);
        System.out.println();
        
        System.out.print("Array using toCharArray() method: ");
        displayCharArray(builtInCharArray);
        System.out.println();
        
        System.out.println("Are both arrays equal? " + areEqual);
        
        if (areEqual) {
            System.out.println("✓ Both methods produce the same character array!");
        } else {
            System.out.println("✗ Methods produce different character arrays!");
        }
        
        scanner.close();
    }
}
