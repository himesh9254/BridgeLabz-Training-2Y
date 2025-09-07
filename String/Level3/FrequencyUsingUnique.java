import java.util.Scanner;

public class FrequencyUsingUnique {
    
    // Method to find unique characters
    public static char[] findUniqueCharacters(String text) {
        char[] tempArray = new char[text.length()];
        int uniqueCount = 0;
        
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            boolean isUnique = true;
            
            for (int j = 0; j < i; j++) {
                if (text.charAt(j) == currentChar) {
                    isUnique = false;
                    break;
                }
            }
            
            if (isUnique) {
                tempArray[uniqueCount] = currentChar;
                uniqueCount++;
            }
        }
        
        char[] uniqueChars = new char[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            uniqueChars[i] = tempArray[i];
        }
        
        return uniqueChars;
    }
    
    // Method to find frequency using unique characters
    public static String[][] findFrequencyUsingUnique(String text) {
        int[] frequency = new int[256]; // ASCII characters
        
        // Count frequency of each character
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            frequency[ch]++;
        }
        
        // Get unique characters
        char[] uniqueChars = findUniqueCharacters(text);
        
        // Create 2D array for unique characters and their frequencies
        String[][] result = new String[uniqueChars.length][2];
        
        for (int i = 0; i < uniqueChars.length; i++) {
            char ch = uniqueChars[i];
            result[i][0] = String.valueOf(ch);
            result[i][1] = String.valueOf(frequency[ch]);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String text = scanner.nextLine();
        
        String[][] charFreq = findFrequencyUsingUnique(text);
        
        System.out.println("\n--- Character Frequency Using Unique Characters ---");
        System.out.println("String: \"" + text + "\"");
        System.out.println("Character\tFrequency");
        System.out.println("---------------------");
        
        for (int i = 0; i < charFreq.length; i++) {
            System.out.println("'" + charFreq[i][0] + "'\t\t" + charFreq[i][1]);
        }
        
        scanner.close();
    }
}
