import java.util.*;

/**
 * Program to remove all duplicate characters from a given string
 * and return the modified string
 */
public class RemoveDuplicates {
    
    /**
     * Method to remove duplicates using LinkedHashSet (preserves order)
     * @param str Input string
     * @return String with duplicates removed
     */
    public static String removeDuplicatesUsingSet(String str) {
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        
        // Add characters to set (automatically removes duplicates)
        for (int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
        }
        
        // Convert set back to string
        StringBuilder result = new StringBuilder();
        for (Character ch : set) {
            result.append(ch);
        }
        
        return result.toString();
    }
    
    /**
     * Method to remove duplicates using StringBuilder and indexOf
     * @param str Input string
     * @return String with duplicates removed
     */
    public static String removeDuplicatesUsingStringBuilder(String str) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            // Check if character is already in result
            if (result.indexOf(String.valueOf(ch)) == -1) {
                result.append(ch);
            }
        }
        
        return result.toString();
    }
    
    /**
     * Method to remove duplicates using nested loops
     * @param str Input string
     * @return String with duplicates removed
     */
    public static String removeDuplicatesUsingLoop(String str) {
        String result = "";
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            boolean isDuplicate = false;
            
            // Check if character already exists in result
            for (int j = 0; j < result.length(); j++) {
                if (result.charAt(j) == ch) {
                    isDuplicate = true;
                    break;
                }
            }
            
            // Add character if not duplicate
            if (!isDuplicate) {
                result += ch;
            }
        }
        
        return result;
    }
    
    /**
     * Method to remove duplicates using boolean array (for ASCII characters)
     * @param str Input string
     * @return String with duplicates removed
     */
    public static String removeDuplicatesUsingArray(String str) {
        boolean[] charSeen = new boolean[256]; // ASCII character set
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            // Check if character is seen before
            if (!charSeen[ch]) {
                charSeen[ch] = true;
                result.append(ch);
            }
        }
        
        return result.toString();
    }
    
    /**
     * Method to remove duplicates case-insensitively
     * @param str Input string
     * @return String with duplicates removed (case-insensitive)
     */
    public static String removeDuplicatesCaseInsensitive(String str) {
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        String lowerStr = str.toLowerCase();
        
        for (int i = 0; i < str.length(); i++) {
            char lowerCh = lowerStr.charAt(i);
            char originalCh = str.charAt(i);
            
            if (!set.contains(lowerCh)) {
                set.add(lowerCh);
                // Keep the first occurrence's case
                for (Character c : set) {
                    // This approach keeps the original character case
                }
            }
        }
        
        // Better approach for case-insensitive
        StringBuilder result = new StringBuilder();
        String processed = "";
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (processed.toLowerCase().indexOf(Character.toLowerCase(ch)) == -1) {
                result.append(ch);
                processed += ch;
            }
        }
        
        return result.toString();
    }
    
    /**
     * Method to remove only consecutive duplicates
     * @param str Input string
     * @return String with consecutive duplicates removed
     */
    public static String removeConsecutiveDuplicates(String str) {
        if (str.length() <= 1) {
            return str;
        }
        
        StringBuilder result = new StringBuilder();
        result.append(str.charAt(0));
        
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != str.charAt(i - 1)) {
                result.append(str.charAt(i));
            }
        }
        
        return result.toString();
    }
    
    /**
     * Method to get character frequency map
     * @param str Input string
     * @return Map of character frequencies
     */
    public static Map<Character, Integer> getCharacterFrequency(String str) {
        Map<Character, Integer> freqMap = new LinkedHashMap<>();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        
        return freqMap;
    }
    
    /**
     * Method to display duplicate removal analysis
     * @param str Input string
     */
    public static void analyzeDuplicateRemoval(String str) {
        System.out.println("=== Duplicate Removal Analysis ===");
        System.out.println("Original String: \"" + str + "\"");
        System.out.println("Length: " + str.length());
        
        // Show character frequency
        Map<Character, Integer> freqMap = getCharacterFrequency(str);
        System.out.println("Character Frequencies:");
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            char ch = entry.getKey();
            int count = entry.getValue();
            String displayChar = (ch == ' ') ? "SPACE" : String.valueOf(ch);
            System.out.println("  '" + displayChar + "': " + count + 
                             (count > 1 ? " (DUPLICATE)" : ""));
        }
        
        // Test different methods
        String result1 = removeDuplicatesUsingSet(str);
        String result2 = removeDuplicatesUsingStringBuilder(str);
        String result3 = removeDuplicatesUsingLoop(str);
        String result4 = removeDuplicatesUsingArray(str);
        String result5 = removeDuplicatesCaseInsensitive(str);
        String result6 = removeConsecutiveDuplicates(str);
        
        System.out.println("\\nResults:");
        System.out.println("Using Set: \"" + result1 + "\" (Length: " + result1.length() + ")");
        System.out.println("Using StringBuilder: \"" + result2 + "\" (Length: " + result2.length() + ")");
        System.out.println("Using Loop: \"" + result3 + "\" (Length: " + result3.length() + ")");
        System.out.println("Using Array: \"" + result4 + "\" (Length: " + result4.length() + ")");
        System.out.println("Case Insensitive: \"" + result5 + "\" (Length: " + result5.length() + ")");
        System.out.println("Consecutive Only: \"" + result6 + "\" (Length: " + result6.length() + ")");
        
        System.out.println("Characters Removed: " + (str.length() - result1.length()));
        System.out.println("==================================");
    }
    
    public static void main(String[] args) {
        System.out.println("🔄 REMOVE DUPLICATES FROM STRING PROGRAM 🔄");
        System.out.println("============================================");
        
        // Test cases
        String[] testStrings = {
            "programming",
            "hello world",
            "aabbccdd",
            "abcdef",
            "aAbBcC",
            "1122334455",
            "Mississippi",
            "Java Programming Language",
            "aaaaaa",
            "abcdefghijklmnopqrstuvwxyz",
            "!!@@##$$%%",
            "The quick brown fox jumps over the lazy dog"
        };
        
        for (String testStr : testStrings) {
            analyzeDuplicateRemoval(testStr);
            System.out.println();
        }
        
        // Performance comparison
        System.out.println("=== PERFORMANCE COMPARISON ===");
        String longString = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz" +
                           "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        
        long startTime, endTime;
        
        startTime = System.nanoTime();
        String result1 = removeDuplicatesUsingSet(longString);
        endTime = System.nanoTime();
        System.out.println("Set method: " + result1.length() + " chars (Time: " + (endTime - startTime) + " ns)");
        
        startTime = System.nanoTime();
        String result2 = removeDuplicatesUsingArray(longString);
        endTime = System.nanoTime();
        System.out.println("Array method: " + result2.length() + " chars (Time: " + (endTime - startTime) + " ns)");
        
        startTime = System.nanoTime();
        String result3 = removeDuplicatesUsingLoop(longString);
        endTime = System.nanoTime();
        System.out.println("Loop method: " + result3.length() + " chars (Time: " + (endTime - startTime) + " ns)");
        
        System.out.println("==============================");
    }
}
