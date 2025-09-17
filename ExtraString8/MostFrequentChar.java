import java.util.*;

/**
 * Program to find the most frequent character in a string
 */
public class MostFrequentChar {
    
    /**
     * Method to find the most frequent character using HashMap
     * @param str Input string
     * @return The most frequent character
     */
    public static char findMostFrequentCharUsingHashMap(String str) {
        if (str == null || str.isEmpty()) {
            return '\0'; // Return null character for empty string
        }
        
        Map<Character, Integer> charFreq = new HashMap<>();
        
        // Count frequency of each character
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            charFreq.put(ch, charFreq.getOrDefault(ch, 0) + 1);
        }
        
        // Find character with maximum frequency
        char mostFrequent = str.charAt(0);
        int maxFreq = 1;
        
        for (Map.Entry<Character, Integer> entry : charFreq.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        
        return mostFrequent;
    }
    
    /**
     * Method to find most frequent character using array (for ASCII characters)
     * @param str Input string
     * @return The most frequent character
     */
    public static char findMostFrequentCharUsingArray(String str) {
        if (str == null || str.isEmpty()) {
            return '\0';
        }
        
        int[] charCount = new int[256]; // ASCII character set
        
        // Count frequency
        for (int i = 0; i < str.length(); i++) {
            charCount[str.charAt(i)]++;
        }
        
        // Find character with maximum frequency
        char mostFrequent = str.charAt(0);
        int maxFreq = charCount[str.charAt(0)];
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (charCount[ch] > maxFreq) {
                maxFreq = charCount[ch];
                mostFrequent = ch;
            }
        }
        
        return mostFrequent;
    }
    
    /**
     * Method to find most frequent character (case-insensitive)
     * @param str Input string
     * @return The most frequent character
     */
    public static char findMostFrequentCharCaseInsensitive(String str) {
        if (str == null || str.isEmpty()) {
            return '\0';
        }
        
        return findMostFrequentCharUsingHashMap(str.toLowerCase());
    }
    
    /**
     * Method to get all characters with their frequencies
     * @param str Input string
     * @return Map of characters and their frequencies
     */
    public static Map<Character, Integer> getCharacterFrequencies(String str) {
        Map<Character, Integer> frequencies = new LinkedHashMap<>();
        
        if (str == null || str.isEmpty()) {
            return frequencies;
        }
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            frequencies.put(ch, frequencies.getOrDefault(ch, 0) + 1);
        }
        
        return frequencies;
    }
    
    /**
     * Method to find all characters with maximum frequency
     * @param str Input string
     * @return List of all most frequent characters
     */
    public static List<Character> findAllMostFrequentChars(String str) {
        List<Character> mostFrequentChars = new ArrayList<>();
        
        if (str == null || str.isEmpty()) {
            return mostFrequentChars;
        }
        
        Map<Character, Integer> frequencies = getCharacterFrequencies(str);
        
        // Find maximum frequency
        int maxFreq = Collections.max(frequencies.values());
        
        // Collect all characters with maximum frequency
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            if (entry.getValue() == maxFreq) {
                mostFrequentChars.add(entry.getKey());
            }
        }
        
        return mostFrequentChars;
    }
    
    /**
     * Method to find most frequent alphabetic character only
     * @param str Input string
     * @return Most frequent alphabetic character
     */
    public static char findMostFrequentAlphabeticChar(String str) {
        if (str == null || str.isEmpty()) {
            return '\0';
        }
        
        Map<Character, Integer> frequencies = new HashMap<>();
        
        // Count only alphabetic characters
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isLetter(ch)) {
                frequencies.put(ch, frequencies.getOrDefault(ch, 0) + 1);
            }
        }
        
        if (frequencies.isEmpty()) {
            return '\0';
        }
        
        // Find most frequent
        char mostFrequent = '\0';
        int maxFreq = 0;
        
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        
        return mostFrequent;
    }
    
    /**
     * Method to analyze character frequencies with detailed information
     * @param str Input string
     */
    public static void analyzeMostFrequentCharacter(String str) {
        System.out.println("=== Most Frequent Character Analysis ===");
        System.out.println("Input String: \"" + str + "\"");
        
        if (str == null || str.isEmpty()) {
            System.out.println("Error: Empty or null string provided!");
            System.out.println("=======================================");
            return;
        }
        
        System.out.println("String Length: " + str.length());
        
        // Get all frequencies
        Map<Character, Integer> frequencies = getCharacterFrequencies(str);
        
        // Find most frequent characters
        char mostFrequent1 = findMostFrequentCharUsingHashMap(str);
        char mostFrequent2 = findMostFrequentCharUsingArray(str);
        char mostFrequentAlpha = findMostFrequentAlphabeticChar(str);
        char mostFrequentCaseInsensitive = findMostFrequentCharCaseInsensitive(str);
        
        List<Character> allMostFrequent = findAllMostFrequentChars(str);
        
        System.out.println("\\nMost Frequent Character Results:");
        System.out.println("Using HashMap: '" + displayChar(mostFrequent1) + "' (Frequency: " + frequencies.get(mostFrequent1) + ")");
        System.out.println("Using Array: '" + displayChar(mostFrequent2) + "' (Frequency: " + frequencies.get(mostFrequent2) + ")");
        System.out.println("Alphabetic Only: '" + displayChar(mostFrequentAlpha) + "'");
        System.out.println("Case-insensitive: '" + displayChar(mostFrequentCaseInsensitive) + "'");
        
        if (allMostFrequent.size() > 1) {
            System.out.println("All most frequent chars: " + allMostFrequent + " (Frequency: " + frequencies.get(allMostFrequent.get(0)) + ")");
        }
        
        // Display frequency table
        System.out.println("\\nCharacter Frequency Table:");
        System.out.println("Char | Freq | ASCII | Type");
        System.out.println("-----|------|-------|--------");
        
        // Sort by frequency (descending)
        frequencies.entrySet().stream()
            .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
            .forEach(entry -> {
                char ch = entry.getKey();
                int freq = entry.getValue();
                String charType = getCharacterType(ch);
                String charDisplay = displayChar(ch);
                System.out.printf("%-4s | %-4d | %-5d | %s%n", 
                    charDisplay, freq, (int)ch, charType);
            });
        
        // Statistics
        int totalChars = str.length();
        int uniqueChars = frequencies.size();
        double avgFrequency = (double) totalChars / uniqueChars;
        int maxFreq = Collections.max(frequencies.values());
        int minFreq = Collections.min(frequencies.values());
        
        System.out.println("\\nStatistics:");
        System.out.println("Total Characters: " + totalChars);
        System.out.println("Unique Characters: " + uniqueChars);
        System.out.printf("Average Frequency: %.2f%n", avgFrequency);
        System.out.println("Maximum Frequency: " + maxFreq);
        System.out.println("Minimum Frequency: " + minFreq);
        
        // Character type breakdown
        Map<String, Integer> typeCount = new HashMap<>();
        for (char ch : frequencies.keySet()) {
            String type = getCharacterType(ch);
            typeCount.put(type, typeCount.getOrDefault(type, 0) + 1);
        }
        
        System.out.println("\\nCharacter Type Breakdown:");
        typeCount.forEach((type, count) -> 
            System.out.println(type + ": " + count + " unique characters"));
        
        System.out.println("=======================================");
    }
    
    /**
     * Helper method to display character (handle special characters)
     * @param ch Character to display
     * @return Display string for character
     */
    private static String displayChar(char ch) {
        if (ch == '\0') return "NULL";
        if (ch == ' ') return "SPACE";
        if (ch == '\t') return "TAB";
        if (ch == '\n') return "NEWLINE";
        return String.valueOf(ch);
    }
    
    /**
     * Helper method to get character type
     * @param ch Character to analyze
     * @return Type of character
     */
    private static String getCharacterType(char ch) {
        if (Character.isUpperCase(ch)) return "Uppercase";
        if (Character.isLowerCase(ch)) return "Lowercase";
        if (Character.isDigit(ch)) return "Digit";
        if (ch == ' ') return "Space";
        if (Character.isWhitespace(ch)) return "Whitespace";
        return "Special";
    }
    
    public static void main(String[] args) {
        System.out.println("🔤 MOST FREQUENT CHARACTER PROGRAM 🔤");
        System.out.println("====================================");
        
        // Test cases
        String[] testStrings = {
            "success", // Given example
            "hello world",
            "programming",
            "aabbccddee",
            "Java Programming Language",
            "The quick brown fox jumps over the lazy dog",
            "aaaaaa",
            "abcdef", // All unique characters
            "Mississippi",
            "Hello Hello Hello",
            "12321",
            "A man a plan a canal Panama",
            "!@#$%^&*()",
            "",
            "a",
            "AaAaAa", // Case sensitivity test
            "   spaces   "
        };
        
        for (String testStr : testStrings) {
            analyzeMostFrequentCharacter(testStr);
            System.out.println();
        }
        
        // Performance comparison
        System.out.println("=== PERFORMANCE COMPARISON ===");
        String longString = "The quick brown fox jumps over the lazy dog. ".repeat(1000);
        
        long startTime, endTime;
        
        startTime = System.nanoTime();
        char result1 = findMostFrequentCharUsingHashMap(longString);
        endTime = System.nanoTime();
        System.out.println("HashMap method: '" + result1 + "' (Time: " + (endTime - startTime) + " ns)");
        
        startTime = System.nanoTime();
        char result2 = findMostFrequentCharUsingArray(longString);
        endTime = System.nanoTime();
        System.out.println("Array method: '" + result2 + "' (Time: " + (endTime - startTime) + " ns)");
        
        System.out.println("Results match: " + (result1 == result2 ? "✓ YES" : "✗ NO"));
        
        System.out.println("==============================");
    }
}
