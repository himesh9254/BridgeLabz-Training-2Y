import java.util.Scanner;

public class StringLengthFinder {
    
    // Method to find string length without using length() method
    public static int findStringLength(String text) {
        int count = 0;
        try {
            while (true) {
                text.charAt(count);
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            // Exception thrown when we reach the end of string
        }
        return count;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String text = scanner.next();
        
        // Find length using custom method
        int customLength = findStringLength(text);
        
        // Find length using built-in method
        int builtInLength = text.length();
        
        // Display results
        System.out.println("\n--- String Length Results ---");
        System.out.println("String: " + text);
        System.out.println("Length using custom method: " + customLength);
        System.out.println("Length using length() method: " + builtInLength);
        
        if (customLength == builtInLength) {
            System.out.println("✓ Both methods give the same result!");
        } else {
            System.out.println("✗ Methods give different results!");
        }
        
        scanner.close();
    }
}
