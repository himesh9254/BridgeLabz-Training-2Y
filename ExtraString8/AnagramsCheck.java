import java.util.*;

/**
 * Program to check if two strings are anagrams of each other
 * Anagrams contain the same characters in any order
 */
public class AnagramsCheck {
    
    /**
     * Check anagrams using sorting
     */
    public static boolean areAnagramsUsingSorting(String str1, String str2) {
        if (str1 == null || str2 == null) return false;
        if (str1.length() != str2.length()) return false;
        
        char[] arr1 = str1.toLowerCase().toCharArray();
        char[] arr2 = str2.toLowerCase().toCharArray();
        
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        
        return Arrays.equals(arr1, arr2);
    }
    
    /**
     * Check anagrams using character frequency
     */
    public static boolean areAnagramsUsingFrequency(String str1, String str2) {
        if (str1 == null || str2 == null) return false;
        if (str1.length() != str2.length()) return false;
        
        Map<Character, Integer> freq1 = new HashMap<>();
        Map<Character, Integer> freq2 = new HashMap<>();
        
        // Count frequencies
        for (char ch : str1.toLowerCase().toCharArray()) {
            freq1.put(ch, freq1.getOrDefault(ch, 0) + 1);
        }
        
        for (char ch : str2.toLowerCase().toCharArray()) {
            freq2.put(ch, freq2.getOrDefault(ch, 0) + 1);
        }
        
        return freq1.equals(freq2);
    }
    
    /**
     * Check anagrams using character array counting
     */
    public static boolean areAnagramsUsingArray(String str1, String str2) {
        if (str1 == null || str2 == null) return false;
        if (str1.length() != str2.length()) return false;
        
        int[] charCount = new int[26]; // For lowercase letters a-z
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        // Count characters in first string
        for (int i = 0; i < str1.length(); i++) {
            char ch = str1.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                charCount[ch - 'a']++;
            }
        }
        
        // Subtract characters from second string
        for (int i = 0; i < str2.length(); i++) {
            char ch = str2.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                charCount[ch - 'a']--;
            }
        }
        
        // Check if all counts are zero
        for (int count : charCount) {
            if (count != 0) return false;
        }
        
        return true;
    }
    
    /**
     * Analyze anagram check with detailed information
     */
    public static void analyzeAnagramCheck(String str1, String str2) {
        System.out.println("=== Anagram Check Analysis ===");
        System.out.println("String 1: \"" + str1 + "\"");
        System.out.println("String 2: \"" + str2 + "\"");
        
        if (str1 == null || str2 == null) {
            System.out.println("Error: One or both strings are null!");
            System.out.println("==============================");
            return;
        }
        
        System.out.println("Length 1: " + str1.length());
        System.out.println("Length 2: " + str2.length());
        
        // Test different methods
        boolean result1 = areAnagramsUsingSorting(str1, str2);
        boolean result2 = areAnagramsUsingFrequency(str1, str2);
        boolean result3 = areAnagramsUsingArray(str1, str2);
        
        System.out.println("\\nResults:");
        System.out.println("Using Sorting: " + (result1 ? "✓ ARE ANAGRAMS" : "✗ NOT ANAGRAMS"));
        System.out.println("Using Frequency: " + (result2 ? "✓ ARE ANAGRAMS" : "✗ NOT ANAGRAMS"));
        System.out.println("Using Array: " + (result3 ? "✓ ARE ANAGRAMS" : "✗ NOT ANAGRAMS"));
        
        boolean consistent = result1 == result2 && result2 == result3;
        System.out.println("Methods Consistent: " + (consistent ? "✓ YES" : "✗ NO"));
        
        // Character frequency analysis
        if (str1.length() <= 20 && str2.length() <= 20) {
            System.out.println("\\nCharacter Frequency Analysis:");
            Map<Character, Integer> freq1 = new HashMap<>();
            Map<Character, Integer> freq2 = new HashMap<>();
            
            for (char ch : str1.toLowerCase().toCharArray()) {
                freq1.put(ch, freq1.getOrDefault(ch, 0) + 1);
            }
            for (char ch : str2.toLowerCase().toCharArray()) {
                freq2.put(ch, freq2.getOrDefault(ch, 0) + 1);
            }
            
            Set<Character> allChars = new HashSet<>();
            allChars.addAll(freq1.keySet());
            allChars.addAll(freq2.keySet());
            
            for (char ch : allChars) {
                int count1 = freq1.getOrDefault(ch, 0);
                int count2 = freq2.getOrDefault(ch, 0);
                String status = count1 == count2 ? "✓" : "✗";
                System.out.println("  '" + ch + "': " + count1 + " vs " + count2 + " " + status);
            }
        }
        
        System.out.println("==============================");
    }
    
    public static void main(String[] args) {
        System.out.println("🔄 ANAGRAMS CHECK PROGRAM 🔄");
        System.out.println("=============================");
        
        // Test cases
        String[][] testPairs = {
            {"listen", "silent"},
            {"evil", "vile"},
            {"a gentleman", "elegant man"},
            {"conversation", "voices rant on"},
            {"hello", "bello"},
            {"abc", "bca"},
            {"hello", "world"},
            {"", ""},
            {"a", "a"},
            {"ab", "ba"},
            {"abc", "def"},
            {"Astronomer", "Moon starer"},
            {"The Eyes", "They See"}
        };
        
        for (String[] pair : testPairs) {
            analyzeAnagramCheck(pair[0], pair[1]);
            System.out.println();
        }
    }
}
