import java.util.*;

public class WordFrequencySentence {
    public static Map<String, Integer> countWords(String sentence) {
        Map<String, Integer> wordCount = new LinkedHashMap<>();
        
        String normalized = sentence.toLowerCase().replaceAll("[^a-z0-9\\s]", "");
        String[] words = normalized.split("\\s+");
        
        for (String word : words) {
            if (!word.isEmpty()) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }
        
        return wordCount;
    }
    
    public static void displayWordFrequency(String sentence) {
        System.out.println("Sentence: \"" + sentence + "\"");
        System.out.println("\nWord Frequency:");
        
        Map<String, Integer> wordCount = countWords(sentence);
        
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
    }
    
    public static void displaySortedByFrequency(String sentence) {
        Map<String, Integer> wordCount = countWords(sentence);
        
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordCount.entrySet());
        sortedList.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        System.out.println("\nSorted by Frequency (Descending):");
        for (Map.Entry<String, Integer> entry : sortedList) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
    }
    
    public static void displaySortedAlphabetically(String sentence) {
        Map<String, Integer> wordCount = countWords(sentence);
        TreeMap<String, Integer> sortedMap = new TreeMap<>(wordCount);
        
        System.out.println("\nSorted Alphabetically:");
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
    }
    
    public static String getMostFrequentWord(String sentence) {
        Map<String, Integer> wordCount = countWords(sentence);
        
        String mostFrequent = null;
        int maxCount = 0;
        
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        
        return mostFrequent;
    }
    
    public static int getUniqueWordCount(String sentence) {
        return countWords(sentence).size();
    }
    
    public static int getTotalWordCount(String sentence) {
        Map<String, Integer> wordCount = countWords(sentence);
        int total = 0;
        for (int count : wordCount.values()) {
            total += count;
        }
        return total;
    }
    
    public static void main(String[] args) {
        String sentence1 = "Java is fun and Java is powerful";
        System.out.println("=== Example 1 ===");
        displayWordFrequency(sentence1);
        displaySortedByFrequency(sentence1);
        System.out.println("\nMost frequent word: " + getMostFrequentWord(sentence1));
        
        String sentence2 = "The quick brown fox jumps over the lazy dog. The dog was not amused by the fox.";
        System.out.println("\n=== Example 2 ===");
        displayWordFrequency(sentence2);
        displaySortedByFrequency(sentence2);
        displaySortedAlphabetically(sentence2);
        
        String sentence3 = "To be or not to be, that is the question. To be is to exist.";
        System.out.println("\n=== Example 3 ===");
        displayWordFrequency(sentence3);
        displaySortedByFrequency(sentence3);
        
        System.out.println("\n=== Statistics ===");
        System.out.println("Sentence: \"" + sentence3 + "\"");
        System.out.println("Total words: " + getTotalWordCount(sentence3));
        System.out.println("Unique words: " + getUniqueWordCount(sentence3));
        System.out.println("Most frequent: " + getMostFrequentWord(sentence3));
    }
}
