import java.util.Scanner;

public class UniqueCharacterFinder {
    
    // Method to find string length without length()
    public static int findStringLength(String text) {
        int count = 0;
        try {
            while (true) {
                text.charAt(count);
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {}
        return count;
    }
    
    // Method to find unique characters
    public static char[] findUniqueCharacters(String text) {
        int textLength = findStringLength(text);
        char[] tempArray = new char[textLength];
        int uniqueCount = 0;
        
        // Find unique characters using nested loops
        for (int i = 0; i < textLength; i++) {
            char currentChar = text.charAt(i);
            boolean isUnique = true;
            
            // Check if character already exists in previous positions
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
        
        // Create final array with exact size
        char[] uniqueChars = new char[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            uniqueChars[i] = tempArray[i];
        }
        
        return uniqueChars;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String text = scanner.nextLine();
        
        char[] uniqueCharacters = findUniqueCharacters(text);
        
        System.out.println("\n--- Unique Characters Results ---");
        System.out.println("Original string: \"" + text + "\"");
        System.out.print("Unique characters: [");
        
        for (int i = 0; i < uniqueCharacters.length; i++) {
            System.out.print("'" + uniqueCharacters[i] + "'");
            if (i < uniqueCharacters.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        System.out.println("Total unique characters: " + uniqueCharacters.length);
        
        scanner.close();
    }
}
