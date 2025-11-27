import java.io.*;
import java.util.*;

public class WordFrequencyCounter {
    public static Map<String, Integer> countWordFrequency(String text) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        
        String cleanedText = text.toLowerCase().replaceAll("[^a-z0-9\\s]", "");
        String[] words = cleanedText.split("\\s+");
        
        for (String word : words) {
            if (!word.isEmpty()) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }
        }
        
        return frequencyMap;
    }
    
    public static Map<String, Integer> countWordFrequencyFromFile(String filePath) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String cleanedLine = line.toLowerCase().replaceAll("[^a-z0-9\\s]", "");
                String[] words = cleanedLine.split("\\s+");
                
                for (String word : words) {
                    if (!word.isEmpty()) {
                        frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
        return frequencyMap;
    }
    
    public static void main(String[] args) {
        String text1 = "Hello world, hello Java!";
        System.out.println("Input: \"" + text1 + "\"");
        System.out.println("Output: " + countWordFrequency(text1));
        
        String text2 = "Java is fun and Java is powerful. Java makes programming fun!";
        System.out.println("\nInput: \"" + text2 + "\"");
        System.out.println("Output: " + countWordFrequency(text2));
        
        String text3 = "The quick brown fox jumps over the lazy dog. The dog was not amused.";
        System.out.println("\nInput: \"" + text3 + "\"");
        Map<String, Integer> result = countWordFrequency(text3);
        System.out.println("Output: " + result);
        
        System.out.println("\n=== Sorted by Frequency (Descending) ===");
        result.entrySet().stream()
            .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
            .forEach(entry -> System.out.println(entry.getKey() + " = " + entry.getValue()));
    }
}
