import java.util.*;

/**
 * Program to find the longest word in a sentence
 */
public class LongestWord {
    
    /**
     * Method to find the longest word in a sentence
     * @param sentence Input sentence
     * @return The longest word
     */
    public static String findLongestWord(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) {
            return "";
        }
        
        // Split sentence into words
        String[] words = sentence.trim().split("\\\\s+");
        String longestWord = "";
        
        for (String word : words) {
            // Remove punctuation from word for accurate length measurement
            String cleanWord = word.replaceAll("[^a-zA-Z0-9]", "");
            if (cleanWord.length() > longestWord.length()) {
                longestWord = word; // Keep original word with punctuation
            }
        }
        
        return longestWord;
    }
    
    /**
     * Method to find all words with maximum length
     * @param sentence Input sentence
     * @return List of all longest words
     */
    public static List<String> findAllLongestWords(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String[] words = sentence.trim().split("\\\\s+");
        List<String> longestWords = new ArrayList<>();
        int maxLength = 0;
        
        // First pass: find maximum length
        for (String word : words) {
            String cleanWord = word.replaceAll("[^a-zA-Z0-9]", "");
            maxLength = Math.max(maxLength, cleanWord.length());
        }
        
        // Second pass: collect all words with maximum length
        for (String word : words) {
            String cleanWord = word.replaceAll("[^a-zA-Z0-9]", "");
            if (cleanWord.length() == maxLength) {
                longestWords.add(word);
            }
        }
        
        return longestWords;
    }
    
    /**
     * Method to get detailed word analysis
     * @param sentence Input sentence
     * @return Map containing word statistics
     */
    public static Map<String, Object> getWordStatistics(String sentence) {
        Map<String, Object> stats = new HashMap<>();
        
        if (sentence == null || sentence.trim().isEmpty()) {
            stats.put("wordCount", 0);
            stats.put("longestWord", "");
            stats.put("shortestWord", "");
            stats.put("averageLength", 0.0);
            return stats;
        }
        
        String[] words = sentence.trim().split("\\\\s+");
        String longestWord = "";
        String shortestWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
        int totalLength = 0;
        
        for (String word : words) {
            String cleanWord = word.replaceAll("[^a-zA-Z0-9]", "");
            totalLength += cleanWord.length();
            
            if (cleanWord.length() > longestWord.replaceAll("[^a-zA-Z0-9]", "").length()) {
                longestWord = word;
            }
            if (cleanWord.length() < shortestWord.length()) {
                shortestWord = word;
            }
        }
        
        stats.put("wordCount", words.length);
        stats.put("longestWord", longestWord);
        stats.put("shortestWord", shortestWord);
        stats.put("averageLength", (double) totalLength / words.length);
        stats.put("totalCharacters", totalLength);
        
        return stats;
    }
    
    /**
     * Method to find longest word by category
     * @param sentence Input sentence
     */
    public static void findLongestWordsByCategory(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) {
            System.out.println("Empty sentence provided.");
            return;
        }
        
        String[] words = sentence.trim().split("\\\\s+");
        String longestAlphabetic = "";
        String longestNumeric = "";
        String longestAlphanumeric = "";
        
        for (String word : words) {
            String cleanWord = word.replaceAll("[^a-zA-Z0-9]", "");
            
            // Check if word is alphabetic only
            if (cleanWord.matches("[a-zA-Z]+")) {
                if (cleanWord.length() > longestAlphabetic.replaceAll("[^a-zA-Z0-9]", "").length()) {
                    longestAlphabetic = word;
                }
            }
            // Check if word is numeric only
            else if (cleanWord.matches("[0-9]+")) {
                if (cleanWord.length() > longestNumeric.replaceAll("[^a-zA-Z0-9]", "").length()) {
                    longestNumeric = word;
                }
            }
            // Check if word is alphanumeric
            else if (cleanWord.matches("[a-zA-Z0-9]+")) {
                if (cleanWord.length() > longestAlphanumeric.replaceAll("[^a-zA-Z0-9]", "").length()) {
                    longestAlphanumeric = word;
                }
            }
        }
        
        System.out.println("=== Longest Words by Category ===");
        System.out.println("Longest Alphabetic: \"" + longestAlphabetic + "\"");
        System.out.println("Longest Numeric: \"" + longestNumeric + "\"");
        System.out.println("Longest Alphanumeric: \"" + longestAlphanumeric + "\"");
        System.out.println("==================================");
    }
    
    /**
     * Method to analyze sentence and find longest word with details
     * @param sentence Input sentence
     */
    public static void analyzeLongestWord(String sentence) {
        System.out.println("=== Longest Word Analysis ===");
        System.out.println("Input Sentence: \"" + sentence + "\"");
        
        if (sentence == null || sentence.trim().isEmpty()) {
            System.out.println("No words found in the sentence.");
            System.out.println("=============================");
            return;
        }
        
        // Get word statistics
        Map<String, Object> stats = getWordStatistics(sentence);
        
        System.out.println("Total Words: " + stats.get("wordCount"));
        System.out.println("Total Characters: " + stats.get("totalCharacters"));
        System.out.printf("Average Word Length: %.2f\\n", (Double) stats.get("averageLength"));
        
        // Find longest word(s)
        String longestWord = findLongestWord(sentence);
        List<String> allLongestWords = findAllLongestWords(sentence);
        
        System.out.println("\\nLongest Word: \"" + longestWord + "\"");
        System.out.println("Length: " + longestWord.replaceAll("[^a-zA-Z0-9]", "").length());
        
        if (allLongestWords.size() > 1) {
            System.out.println("All longest words: " + allLongestWords);
        }
        
        System.out.println("Shortest Word: \"" + stats.get("shortestWord") + "\"");
        System.out.println("Length: " + ((String) stats.get("shortestWord")).replaceAll("[^a-zA-Z0-9]", "").length());
        
        // Show all words with lengths
        String[] words = sentence.trim().split("\\\\s+");
        System.out.println("\\nAll Words with Lengths:");
        for (int i = 0; i < words.length; i++) {
            String cleanWord = words[i].replaceAll("[^a-zA-Z0-9]", "");
            System.out.println("  " + (i + 1) + ". \"" + words[i] + "\" → " + cleanWord.length() + " chars");
        }
        
        // Category analysis
        findLongestWordsByCategory(sentence);
        
        System.out.println("=============================");
    }
    
    public static void main(String[] args) {
        System.out.println("📏 LONGEST WORD IN SENTENCE PROGRAM 📏");
        System.out.println("======================================");
        
        // Test sentences
        String[] testSentences = {
            "The quick brown fox jumps over the lazy dog",
            "Java programming is fascinating and challenging",
            "Hello world",
            "Supercalifragilisticexpialidocious is a very long word",
            "I love programming in Java language",
            "This sentence has words of different lengths",
            "A",
            "",
            "Programming, debugging, testing, and deployment are important phases",
            "The word 'internationalization' has 20 characters",
            "Short words: a, an, is, of, to, in",
            "Numbers like 12345 and words like hello123 are mixed",
            "Pneumonoultramicroscopicsilicovolcanoconiosiswasinthevocabulary"
        };
        
        for (String sentence : testSentences) {
            analyzeLongestWord(sentence);
            System.out.println();
        }
        
        // Interactive-like demonstration
        System.out.println("=== SPECIAL CASES DEMONSTRATION ===");
        
        // Test with punctuation
        String punctuatedSentence = "Hello, world! How are you today? Fine, thank you.";
        analyzeLongestWord(punctuatedSentence);
        
        // Test with mixed content
        String mixedSentence = "The password is abc123xyz and the ID is 987654321";
        analyzeLongestWord(mixedSentence);
        
        System.out.println("===================================");
    }
}
