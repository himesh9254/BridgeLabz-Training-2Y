import java.util.Scanner;

public class ShortestLongestFinder {
    
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
        words[wordIndex] = currentWord;
        
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
    
    public static int[] findShortestLongest(String[][] wordsWithLengths) {
        int[] result = new int[4]; // [shortestIndex, shortestLength, longestIndex, longestLength]
        
        int shortestLength = Integer.parseInt(wordsWithLengths[0][1]);
        int longestLength = Integer.parseInt(wordsWithLengths[0][1]);
        int shortestIndex = 0;
        int longestIndex = 0;
        
        for (int i = 1; i < wordsWithLengths.length; i++) {
            int currentLength = Integer.parseInt(wordsWithLengths[i][1]);
            
            if (currentLength < shortestLength) {
                shortestLength = currentLength;
                shortestIndex = i;
            }
            
            if (currentLength > longestLength) {
                longestLength = currentLength;
                longestIndex = i;
            }
        }
        
        result[0] = shortestIndex;
        result[1] = shortestLength;
        result[2] = longestIndex;
        result[3] = longestLength;
        
        return result;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a text with multiple words: ");
        String text = scanner.nextLine();
        
        String[] words = splitTextIntoWords(text);
        String[][] wordsWithLengths = getWordsWithLengths(words);
        int[] shortestLongest = findShortestLongest(wordsWithLengths);
        
        System.out.println("\n--- All Words with Lengths ---");
        System.out.println("Word\t\tLength");
        System.out.println("------------------------");
        
        for (int i = 0; i < wordsWithLengths.length; i++) {
            String word = wordsWithLengths[i][0];
            int length = Integer.parseInt(wordsWithLengths[i][1]);
            System.out.println(word + "\t\t" + length);
        }
        
        System.out.println("\n--- Shortest and Longest Words ---");
        System.out.println("Shortest word: \"" + wordsWithLengths[shortestLongest[0]][0] + "\" (Length: " + shortestLongest[1] + ")");
        System.out.println("Longest word: \"" + wordsWithLengths[shortestLongest[2]][0] + "\" (Length: " + shortestLongest[3] + ")");
        
        scanner.close();
    }
}
