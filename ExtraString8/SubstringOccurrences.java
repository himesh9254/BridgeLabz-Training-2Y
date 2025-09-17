import java.util.*;

/**
 * Program to count how many times a given substring occurs in a string
 */
public class SubstringOccurrences {
    
    /**
     * Method to count substring occurrences using indexOf
     * @param mainString The main string to search in
     * @param substring The substring to search for
     * @return Number of occurrences
     */
    public static int countOccurrencesUsingIndexOf(String mainString, String substring) {
        if (mainString == null || substring == null || substring.isEmpty()) {
            return 0;
        }
        
        int count = 0;
        int index = 0;
        
        while ((index = mainString.indexOf(substring, index)) != -1) {
            count++;
            index += substring.length(); // Move past the current match
        }
        
        return count;
    }
    
    /**
     * Method to count overlapping substring occurrences
     * @param mainString The main string to search in
     * @param substring The substring to search for
     * @return Number of overlapping occurrences
     */
    public static int countOverlappingOccurrences(String mainString, String substring) {
        if (mainString == null || substring == null || substring.isEmpty()) {
            return 0;
        }
        
        int count = 0;
        int index = 0;
        
        while ((index = mainString.indexOf(substring, index)) != -1) {
            count++;
            index++; // Move by 1 character to find overlapping matches
        }
        
        return count;
    }
    
    /**
     * Method to count substring occurrences using manual search
     * @param mainString The main string to search in
     * @param substring The substring to search for
     * @return Number of occurrences
     */
    public static int countOccurrencesManual(String mainString, String substring) {
        if (mainString == null || substring == null || substring.isEmpty()) {
            return 0;
        }
        
        int count = 0;
        int mainLen = mainString.length();
        int subLen = substring.length();
        
        for (int i = 0; i <= mainLen - subLen; i++) {
            boolean match = true;
            
            // Check if substring matches at position i
            for (int j = 0; j < subLen; j++) {
                if (mainString.charAt(i + j) != substring.charAt(j)) {
                    match = false;
                    break;
                }
            }
            
            if (match) {
                count++;
                i += subLen - 1; // Skip to avoid overlapping (will be incremented by loop)
            }
        }
        
        return count;
    }
    
    /**
     * Method to count case-insensitive substring occurrences
     * @param mainString The main string to search in
     * @param substring The substring to search for
     * @return Number of case-insensitive occurrences
     */
    public static int countOccurrencesCaseInsensitive(String mainString, String substring) {
        if (mainString == null || substring == null || substring.isEmpty()) {
            return 0;
        }
        
        return countOccurrencesUsingIndexOf(mainString.toLowerCase(), substring.toLowerCase());
    }
    
    /**
     * Method to find all positions where substring occurs
     * @param mainString The main string to search in
     * @param substring The substring to search for
     * @return List of starting positions
     */
    public static List<Integer> findAllPositions(String mainString, String substring) {
        List<Integer> positions = new ArrayList<>();
        
        if (mainString == null || substring == null || substring.isEmpty()) {
            return positions;
        }
        
        int index = 0;
        while ((index = mainString.indexOf(substring, index)) != -1) {
            positions.add(index);
            index += substring.length();
        }
        
        return positions;
    }
    
    /**
     * Method to find all overlapping positions where substring occurs
     * @param mainString The main string to search in
     * @param substring The substring to search for
     * @return List of starting positions (including overlapping)
     */
    public static List<Integer> findAllOverlappingPositions(String mainString, String substring) {
        List<Integer> positions = new ArrayList<>();
        
        if (mainString == null || substring == null || substring.isEmpty()) {
            return positions;
        }
        
        int index = 0;
        while ((index = mainString.indexOf(substring, index)) != -1) {
            positions.add(index);
            index++; // Move by 1 to find overlapping occurrences
        }
        
        return positions;
    }
    
    /**
     * Method to replace all occurrences of substring with replacement
     * @param mainString The main string
     * @param oldSubstring The substring to replace
     * @param newSubstring The replacement substring
     * @return String with replacements made
     */
    public static String replaceAllOccurrences(String mainString, String oldSubstring, String newSubstring) {
        if (mainString == null || oldSubstring == null || oldSubstring.isEmpty()) {
            return mainString;
        }
        
        StringBuilder result = new StringBuilder();
        int lastIndex = 0;
        int index;
        
        while ((index = mainString.indexOf(oldSubstring, lastIndex)) != -1) {
            result.append(mainString.substring(lastIndex, index));
            result.append(newSubstring);
            lastIndex = index + oldSubstring.length();
        }
        
        result.append(mainString.substring(lastIndex));
        return result.toString();
    }
    
    /**
     * Method to analyze substring occurrences with detailed information
     * @param mainString The main string to search in
     * @param substring The substring to search for
     */
    public static void analyzeSubstringOccurrences(String mainString, String substring) {
        System.out.println("=== Substring Occurrence Analysis ===");
        System.out.println("Main String: \"" + mainString + "\"");
        System.out.println("Substring: \"" + substring + "\"");
        System.out.println("Main String Length: " + mainString.length());
        System.out.println("Substring Length: " + substring.length());
        
        if (substring.isEmpty()) {
            System.out.println("Error: Empty substring provided!");
            System.out.println("=====================================");
            return;
        }
        
        // Count occurrences using different methods
        int count1 = countOccurrencesUsingIndexOf(mainString, substring);
        int count2 = countOverlappingOccurrences(mainString, substring);
        int count3 = countOccurrencesManual(mainString, substring);
        int count4 = countOccurrencesCaseInsensitive(mainString, substring);
        
        System.out.println("\\nOccurrence Counts:");
        System.out.println("Non-overlapping: " + count1);
        System.out.println("Overlapping: " + count2);
        System.out.println("Manual count: " + count3);
        System.out.println("Case-insensitive: " + count4);
        
        // Find positions
        List<Integer> positions = findAllPositions(mainString, substring);
        List<Integer> overlappingPositions = findAllOverlappingPositions(mainString, substring);
        
        if (!positions.isEmpty()) {
            System.out.println("\\nNon-overlapping Positions: " + positions);
            System.out.println("Overlapping Positions: " + overlappingPositions);
            
            // Show context around each occurrence
            System.out.println("\\nOccurrences with Context:");
            for (int i = 0; i < positions.size(); i++) {
                int pos = positions.get(i);
                int start = Math.max(0, pos - 5);
                int end = Math.min(mainString.length(), pos + substring.length() + 5);
                String context = mainString.substring(start, end);
                String highlightedContext = context.replace(substring, "[" + substring + "]");
                System.out.println("  " + (i + 1) + ". Position " + pos + ": \"..." + highlightedContext + "...\"");
            }
        } else {
            System.out.println("\\nNo occurrences found!");
        }
        
        // Calculate percentage
        if (mainString.length() > 0) {
            double percentage = (double) (count1 * substring.length()) * 100 / mainString.length();
            System.out.printf("\\nSubstring Coverage: %.2f%% of main string\\n", percentage);
        }
        
        System.out.println("=====================================");
    }
    
    public static void main(String[] args) {
        System.out.println("🔍 SUBSTRING OCCURRENCES PROGRAM 🔍");
        System.out.println("===================================");
        
        // Test cases - Main string and substring pairs
        String[][] testCases = {
            {"Hello world, hello universe, hello everyone", "hello"},
            {"abcabcabc", "abc"},
            {"aaaa", "aa"},  // Overlapping case
            {"Java programming in Java language", "Java"},
            {"The quick brown fox jumps over the lazy dog", "the"},
            {"Mississippi", "iss"},  // Multiple overlapping
            {"abcdefghijklmnop", "xyz"},  // Not found
            {"", "test"},  // Empty main string
            {"test string", ""},  // Empty substring
            {"Programming", "programming"},  // Case sensitivity
            {"1010101010", "101"},  // Binary pattern
            {"She sells sea shells by the sea shore", "se"}
        };
        
        for (String[] testCase : testCases) {
            analyzeSubstringOccurrences(testCase[0], testCase[1]);
            System.out.println();
        }
        
        // Demonstration of replacement
        System.out.println("=== REPLACEMENT DEMONSTRATION ===");
        String original = "Java is great. Java is powerful. Java is versatile.";
        String oldWord = "Java";
        String newWord = "Python";
        String replaced = replaceAllOccurrences(original, oldWord, newWord);
        
        System.out.println("Original: \"" + original + "\"");
        System.out.println("Replace '" + oldWord + "' with '" + newWord + "':");
        System.out.println("Result: \"" + replaced + "\"");
        
        // Performance comparison
        System.out.println("\\n=== PERFORMANCE COMPARISON ===");
        String longString = "abcdefghij".repeat(1000) + "target".repeat(100) + "abcdefghij".repeat(1000);
        String target = "target";
        
        long startTime, endTime;
        
        startTime = System.nanoTime();
        int result1 = countOccurrencesUsingIndexOf(longString, target);
        endTime = System.nanoTime();
        System.out.println("IndexOf method: " + result1 + " occurrences (Time: " + (endTime - startTime) + " ns)");
        
        startTime = System.nanoTime();
        int result2 = countOccurrencesManual(longString, target);
        endTime = System.nanoTime();
        System.out.println("Manual method: " + result2 + " occurrences (Time: " + (endTime - startTime) + " ns)");
        
        System.out.println("=================================");
    }
}
