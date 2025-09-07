import java.util.Scanner;

public class TextSplitter {
    
    // Method to find string length without length() method
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
    
    // Method to split text into words using charAt()
    public static String[] splitTextIntoWords(String text) {
        int textLength = findStringLength(text);
        
        // Count words and find space positions
        int wordCount = 0;
        int[] spacePositions = new int[textLength];
        int spaceIndex = 0;
        
        // Add starting position
        spacePositions[spaceIndex++] = -1;
        
        for (int i = 0; i < textLength; i++) {
            if (text.charAt(i) == ' ') {
                spacePositions[spaceIndex++] = i;
                wordCount++;
            }
        }
        wordCount++; // Add last word
        spacePositions[spaceIndex] = textLength; // Add end position
        
        // Extract words
        String[] words = new String[wordCount];
        int wordIndex = 0;
        
        for (int i = 0; i < wordCount; i++) {
            String word = "";
            int start = spacePositions[i] + 1;
            int end = spacePositions[i + 1];
            
            for (int j = start; j < end; j++) {
                word += text.charAt(j);
            }
            words[wordIndex++] = word;
        }
        
        return words;
    }
    
    // Method to compare two string arrays
    public static boolean compareStringArrays(String[] arr1, String[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        
        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a text with spaces: ");
        String text = scanner.nextLine();
        
        // Split using custom method
        String[] customWords = splitTextIntoWords(text);
        
        // Split using built-in method
        String[] builtInWords = text.split(" ");
        
        // Compare arrays
        boolean areEqual = compareStringArrays(customWords, builtInWords);
        
        // Display results
        System.out.println("\n--- Text Splitting Results ---");
        System.out.println("Original text: \"" + text + "\"");
        
        System.out.print("Words using custom method: [");
        for (int i = 0; i < customWords.length; i++) {
            System.out.print("\"" + customWords[i] + "\"");
            if (i < customWords.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        
        System.out.print("Words using split() method: [");
        for (int i = 0; i < builtInWords.length; i++) {
            System.out.print("\"" + builtInWords[i] + "\"");
            if (i < builtInWords.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        
        System.out.println("Are both results equal? " + areEqual);
        
        scanner.close();
    }
}
