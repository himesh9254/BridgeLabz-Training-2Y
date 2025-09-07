import java.util.Scanner;

public class FirstNonRepeatingChar {
    
    // Method to find first non-repeating character
    public static char findFirstNonRepeatingChar(String text) {
        int[] frequency = new int[256]; // ASCII characters
        
        // Count frequency of each character
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            frequency[ch]++;
        }
        
        // Find first character with frequency 1
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (frequency[ch] == 1) {
                return ch;
            }
        }
        
        return '\0'; // No non-repeating character found
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String text = scanner.nextLine();
        
        char firstNonRepeating = findFirstNonRepeatingChar(text);
        
        System.out.println("\n--- First Non-Repeating Character Results ---");
        System.out.println("String: \"" + text + "\"");
        
        if (firstNonRepeating != '\0') {
            System.out.println("First non-repeating character: '" + firstNonRepeating + "'");
        } else {
            System.out.println("No non-repeating character found.");
        }
        
        scanner.close();
    }
}
