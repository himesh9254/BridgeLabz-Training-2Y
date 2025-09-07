import java.util.Scanner;

public class CharacterFrequency {
    
    // Method to find character frequency
    public static String[][] findCharacterFrequency(String text) {
        int[] frequency = new int[256]; // ASCII characters
        
        // Count frequency of each character
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            frequency[ch]++;
        }
        
        // Count unique characters
        int uniqueCount = 0;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (frequency[ch] > 0) {
                uniqueCount++;
                frequency[ch] = -frequency[ch]; // Mark as counted
            }
        }
        
        // Reset frequency array
        for (int i = 0; i < 256; i++) {
            if (frequency[i] < 0) {
                frequency[i] = -frequency[i];
            }
        }
        
        // Create result array
        String[][] result = new String[uniqueCount][2];
        int index = 0;
        
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (frequency[ch] > 0) {
                result[index][0] = String.valueOf(ch);
                result[index][1] = String.valueOf(frequency[ch]);
                frequency[ch] = 0; // Mark as processed
                index++;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String text = scanner.nextLine();
        
        String[][] charFreq = findCharacterFrequency(text);
        
        System.out.println("\n--- Character Frequency Results ---");
        System.out.println("String: \"" + text + "\"");
        System.out.println("Character\tFrequency");
        System.out.println("---------------------");
        
        for (int i = 0; i < charFreq.length; i++) {
            System.out.println("'" + charFreq[i][0] + "'\t\t" + charFreq[i][1]);
        }
        
        scanner.close();
    }
}
