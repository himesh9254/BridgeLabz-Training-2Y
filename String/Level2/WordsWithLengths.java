import java.util.Scanner;

public class WordsWithLengths {
    
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
    
    public static String[] splitTextIntoWords(String text) {
        int textLength = findStringLength(text);
        int wordCount = 1;
        
        // Count words
        for (int i = 0; i < textLength; i++) {
            if (text.charAt(i) == ' ') wordCount++;
        }
        
        String[] words = new String[wordCount];
        String currentWord = "";
        int wordIndex = 0;
        
        for (int i = 0; i < textLength; i++) {
            if (text.charAt(i) == ' ') {
                words[wordIndex++] = currentWord;
                currentWord = "";
            } else {
                currentWord += text.charAt(i);
            }
        }
        words[wordIndex] = currentWord; // Add last word
        
        return words;
    }
    
    public static String[][] getWordsWithLengths(String[] words) {
        String[][] result = new String[words.length][2];
        
        for (int i = 0; i < words.length; i++) {
            result[i][0] = words[i];
            result[i][1] = String.valueOf(findStringLength(words[i]));
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a text with spaces: ");
        String text = scanner.nextLine();
        
        String[] words = splitTextIntoWords(text);
        String[][] wordsWithLengths = getWordsWithLengths(words);
        
        System.out.println("\n--- Words with Their Lengths ---");
        System.out.println("Word\t\tLength");
        System.out.println("------------------------");
        
        for (int i = 0; i < wordsWithLengths.length; i++) {
            String word = wordsWithLengths[i][0];
            int length = Integer.parseInt(wordsWithLengths[i][1]);
            System.out.println(word + "\t\t" + length);
        }
        
        scanner.close();
    }
}
