/**
 * Program to compare two strings lexicographically (dictionary order) 
 * without using built-in compare methods
 */
public class CompareStrings {
    
    /**
     * Method to compare two strings lexicographically
     * @param str1 First string
     * @param str2 Second string
     * @return Negative if str1 < str2, 0 if equal, Positive if str1 > str2
     */
    public static int compareStringsLexicographically(String str1, String str2) {
        // Handle null cases
        if (str1 == null && str2 == null) return 0;
        if (str1 == null) return -1;
        if (str2 == null) return 1;
        
        int minLength = Math.min(str1.length(), str2.length());
        
        // Compare character by character
        for (int i = 0; i < minLength; i++) {
            char ch1 = str1.charAt(i);
            char ch2 = str2.charAt(i);
            
            if (ch1 != ch2) {
                return ch1 - ch2; // Return ASCII difference
            }
        }
        
        // If all compared characters are equal, compare lengths
        return str1.length() - str2.length();
    }
    
    /**
     * Method to compare two strings case-insensitively
     * @param str1 First string
     * @param str2 Second string
     * @return Negative if str1 < str2, 0 if equal, Positive if str1 > str2
     */
    public static int compareStringsCaseInsensitive(String str1, String str2) {
        if (str1 == null && str2 == null) return 0;
        if (str1 == null) return -1;
        if (str2 == null) return 1;
        
        return compareStringsLexicographically(str1.toLowerCase(), str2.toLowerCase());
    }
    
    /**
     * Method to compare strings by length first, then lexicographically
     * @param str1 First string
     * @param str2 Second string
     * @return Negative if str1 < str2, 0 if equal, Positive if str1 > str2
     */
    public static int compareStringsByLengthThenLex(String str1, String str2) {
        if (str1 == null && str2 == null) return 0;
        if (str1 == null) return -1;
        if (str2 == null) return 1;
        
        // First compare by length
        int lengthDiff = str1.length() - str2.length();
        if (lengthDiff != 0) {
            return lengthDiff;
        }
        
        // If lengths are equal, compare lexicographically
        return compareStringsLexicographically(str1, str2);
    }
    
    /**
     * Method to compare strings ignoring spaces and punctuation
     * @param str1 First string
     * @param str2 Second string
     * @return Negative if str1 < str2, 0 if equal, Positive if str1 > str2
     */
    public static int compareStringsAlphanumericOnly(String str1, String str2) {
        if (str1 == null && str2 == null) return 0;
        if (str1 == null) return -1;
        if (str2 == null) return 1;
        
        // Remove non-alphanumeric characters
        String clean1 = str1.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String clean2 = str2.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        
        return compareStringsLexicographically(clean1, clean2);
    }
    
    /**
     * Method to get comparison result as descriptive text
     * @param str1 First string
     * @param str2 Second string
     * @return Descriptive comparison result
     */
    public static String getComparisonDescription(String str1, String str2) {
        int result = compareStringsLexicographically(str1, str2);
        
        if (result < 0) {
            return "\"" + str1 + "\" comes before \"" + str2 + "\" in lexicographical order";
        } else if (result > 0) {
            return "\"" + str1 + "\" comes after \"" + str2 + "\" in lexicographical order";
        } else {
            return "\"" + str1 + "\" and \"" + str2 + "\" are lexicographically equal";
        }
    }
    
    /**
     * Method to find the first differing character position
     * @param str1 First string
     * @param str2 Second string
     * @return Position of first difference, or -1 if strings are equal
     */
    public static int findFirstDifferencePosition(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return str1 == str2 ? -1 : 0;
        }
        
        int minLength = Math.min(str1.length(), str2.length());
        
        for (int i = 0; i < minLength; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return i;
            }
        }
        
        // If all compared characters are equal but lengths differ
        return str1.length() == str2.length() ? -1 : minLength;
    }
    
    /**
     * Method to perform comprehensive string comparison analysis
     * @param str1 First string
     * @param str2 Second string
     */
    public static void analyzeStringComparison(String str1, String str2) {
        System.out.println("=== String Comparison Analysis ===");
        System.out.println("String 1: \"" + (str1 == null ? "null" : str1) + "\"");
        System.out.println("String 2: \"" + (str2 == null ? "null" : str2) + "\"");
        
        if (str1 != null) System.out.println("Length 1: " + str1.length());
        if (str2 != null) System.out.println("Length 2: " + str2.length());
        
        // Various comparison methods
        int lexResult = compareStringsLexicographically(str1, str2);
        int caseInsensitiveResult = compareStringsCaseInsensitive(str1, str2);
        int lengthThenLexResult = compareStringsByLengthThenLex(str1, str2);
        int alphanumericResult = compareStringsAlphanumericOnly(str1, str2);
        
        System.out.println("\\nComparison Results:");
        System.out.println("Lexicographic: " + lexResult + " (" + getResultDescription(lexResult) + ")");
        System.out.println("Case-insensitive: " + caseInsensitiveResult + " (" + getResultDescription(caseInsensitiveResult) + ")");
        System.out.println("Length then Lex: " + lengthThenLexResult + " (" + getResultDescription(lengthThenLexResult) + ")");
        System.out.println("Alphanumeric only: " + alphanumericResult + " (" + getResultDescription(alphanumericResult) + ")");
        
        System.out.println("\\nDescription: " + getComparisonDescription(str1, str2));
        
        // Find first difference
        int diffPos = findFirstDifferencePosition(str1, str2);
        if (diffPos != -1) {
            System.out.println("First difference at position: " + diffPos);
            if (str1 != null && str2 != null) {
                if (diffPos < str1.length() && diffPos < str2.length()) {
                    char ch1 = str1.charAt(diffPos);
                    char ch2 = str2.charAt(diffPos);
                    System.out.println("Characters: '" + ch1 + "' (" + (int)ch1 + ") vs '" + ch2 + "' (" + (int)ch2 + ")");
                } else {
                    System.out.println("Difference due to length: " + str1.length() + " vs " + str2.length());
                }
            }
        } else {
            System.out.println("Strings are identical");
        }
        
        // Character-by-character comparison for short strings
        if (str1 != null && str2 != null && str1.length() <= 20 && str2.length() <= 20) {
            System.out.println("\\nCharacter-by-Character Comparison:");
            int maxLen = Math.max(str1.length(), str2.length());
            for (int i = 0; i < maxLen; i++) {
                char ch1 = i < str1.length() ? str1.charAt(i) : ' ';
                char ch2 = i < str2.length() ? str2.charAt(i) : ' ';
                String status = (i < str1.length() && i < str2.length()) ? 
                               (ch1 == ch2 ? "=" : "≠") : "length diff";
                
                String ch1Str = i < str1.length() ? "'" + ch1 + "'" : "end";
                String ch2Str = i < str2.length() ? "'" + ch2 + "'" : "end";
                
                System.out.println("  " + i + ": " + ch1Str + " vs " + ch2Str + " → " + status);
            }
        }
        
        System.out.println("==================================");
    }
    
    /**
     * Helper method to describe comparison result
     * @param result Comparison result
     * @return Description of result
     */
    private static String getResultDescription(int result) {
        if (result < 0) return "str1 < str2";
        else if (result > 0) return "str1 > str2";
        else return "str1 = str2";
    }
    
    /**
     * Method to sort an array of strings using custom comparison
     * @param strings Array of strings to sort
     * @return Sorted array
     */
    public static String[] sortStrings(String[] strings) {
        if (strings == null) return null;
        
        String[] sorted = strings.clone();
        
        // Simple bubble sort using custom comparison
        for (int i = 0; i < sorted.length - 1; i++) {
            for (int j = 0; j < sorted.length - 1 - i; j++) {
                if (compareStringsLexicographically(sorted[j], sorted[j + 1]) > 0) {
                    String temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }
        
        return sorted;
    }
    
    public static void main(String[] args) {
        System.out.println("🔤 STRING COMPARISON PROGRAM 🔤");
        System.out.println("===============================");
        
        // Test cases - pairs of strings to compare
        String[][] testPairs = {
            {"apple", "banana"},
            {"hello", "hello"},
            {"Java", "java"},
            {"Programming", "Program"},
            {"abc", "abcd"},
            {"xyz", "abc"},
            {"Hello World", "Hello world"},
            {"123", "456"},
            {"Apple", "apple"},
            {"", "empty"},
            {"", ""},
            {"Special@123", "Special 123"},
            {"The quick", "The quirky"},
            null, // Will test with null
            {"a", "A"},
            {"zebra", "apple"}
        };
        
        // Handle the null test case separately
        for (int i = 0; i < testPairs.length; i++) {
            if (testPairs[i] == null) {
                analyzeStringComparison("test", null);
            } else {
                analyzeStringComparison(testPairs[i][0], testPairs[i][1]);
            }
            System.out.println();
        }
        
        // Demonstration with the given example
        System.out.println("=== GIVEN EXAMPLE DEMONSTRATION ===");
        String str1 = "apple";
        String str2 = "banana";
        
        System.out.println("Example Input:");
        System.out.println("String 1: \"" + str1 + "\"");
        System.out.println("String 2: \"" + str2 + "\"");
        System.out.println("\\nExpected Output:");
        System.out.println(getComparisonDescription(str1, str2));
        
        // Array sorting demonstration
        System.out.println("\\n=== ARRAY SORTING DEMONSTRATION ===");
        String[] fruits = {"banana", "apple", "orange", "grape", "kiwi", "mango"};
        
        System.out.println("Original array: " + java.util.Arrays.toString(fruits));
        String[] sortedFruits = sortStrings(fruits);
        System.out.println("Sorted array: " + java.util.Arrays.toString(sortedFruits));
        
        // Performance comparison
        System.out.println("\\n=== PERFORMANCE COMPARISON ===");
        String longStr1 = "The quick brown fox jumps over the lazy dog";
        String longStr2 = "The quick brown fox jumps over the lazy cat";
        
        long startTime = System.nanoTime();
        int customResult = compareStringsLexicographically(longStr1, longStr2);
        long endTime = System.nanoTime();
        long customTime = endTime - startTime;
        
        startTime = System.nanoTime();
        int builtInResult = longStr1.compareTo(longStr2);
        endTime = System.nanoTime();
        long builtInTime = endTime - startTime;
        
        System.out.println("Custom method result: " + customResult + " (Time: " + customTime + " ns)");
        System.out.println("Built-in method result: " + builtInResult + " (Time: " + builtInTime + " ns)");
        System.out.println("Results match: " + (customResult == builtInResult ? "✓ YES" : "✗ NO"));
        
        System.out.println("=====================================");
    }
}
