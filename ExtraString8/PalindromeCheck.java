/**
 * Program to check if a given string is a palindrome
 * A palindrome is a string that reads the same forward and backward
 */
public class PalindromeCheck {
    
    /**
     * Method to check if a string is palindrome using two pointers approach
     * @param str Input string
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindromeUsingPointers(String str) {
        // Convert to lowercase for case-insensitive comparison
        str = str.toLowerCase();
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    /**
     * Method to check if a string is palindrome by reversing it
     * @param str Input string
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindromeUsingReverse(String str) {
        String original = str.toLowerCase();
        String reversed = "";
        
        // Create reversed string
        for (int i = original.length() - 1; i >= 0; i--) {
            reversed += original.charAt(i);
        }
        
        return original.equals(reversed);
    }
    
    /**
     * Method to check if a string is palindrome using recursion
     * @param str Input string
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindromeUsingRecursion(String str) {
        return isPalindromeRecursiveHelper(str.toLowerCase(), 0, str.length() - 1);
    }
    
    /**
     * Helper method for recursive palindrome check
     * @param str Input string
     * @param left Left pointer
     * @param right Right pointer
     * @return true if palindrome, false otherwise
     */
    private static boolean isPalindromeRecursiveHelper(String str, int left, int right) {
        // Base case: if pointers meet or cross
        if (left >= right) {
            return true;
        }
        
        // Check current characters and recurse
        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }
        
        return isPalindromeRecursiveHelper(str, left + 1, right - 1);
    }
    
    /**
     * Method to check if a string is palindrome ignoring spaces and punctuation
     * @param str Input string
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindromeIgnoringSpaces(String str) {
        // Remove spaces and punctuation, convert to lowercase
        String cleaned = str.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        return isPalindromeUsingPointers(cleaned);
    }
    
    /**
     * Method to find the longest palindromic substring
     * @param str Input string
     * @return Longest palindromic substring
     */
    public static String longestPalindromicSubstring(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        
        String longest = "";
        
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                String substring = str.substring(i, j);
                if (isPalindromeUsingPointers(substring) && substring.length() > longest.length()) {
                    longest = substring;
                }
            }
        }
        
        return longest;
    }
    
    /**
     * Method to display palindrome check results with details
     * @param str Input string
     */
    public static void checkPalindromeWithDetails(String str) {
        System.out.println("=== Palindrome Check Analysis ===");
        System.out.println("Input String: \"" + str + "\"");
        System.out.println("Length: " + str.length());
        
        // Test different methods
        boolean result1 = isPalindromeUsingPointers(str);
        boolean result2 = isPalindromeUsingReverse(str);
        boolean result3 = isPalindromeUsingRecursion(str);
        boolean result4 = isPalindromeIgnoringSpaces(str);
        
        System.out.println("Using Pointers: " + (result1 ? "✓ PALINDROME" : "✗ NOT PALINDROME"));
        System.out.println("Using Reverse: " + (result2 ? "✓ PALINDROME" : "✗ NOT PALINDROME"));
        System.out.println("Using Recursion: " + (result3 ? "✓ PALINDROME" : "✗ NOT PALINDROME"));
        System.out.println("Ignoring Spaces/Punct: " + (result4 ? "✓ PALINDROME" : "✗ NOT PALINDROME"));
        
        // Show character comparison for small strings
        if (str.length() <= 20) {
            System.out.println("Character Comparison:");
            String lowerStr = str.toLowerCase();
            for (int i = 0; i < lowerStr.length() / 2; i++) {
                char leftChar = lowerStr.charAt(i);
                char rightChar = lowerStr.charAt(lowerStr.length() - 1 - i);
                boolean match = leftChar == rightChar;
                System.out.println("  Position " + i + " vs " + (lowerStr.length() - 1 - i) + 
                                 ": '" + leftChar + "' vs '" + rightChar + "' → " + 
                                 (match ? "✓" : "✗"));
            }
        }
        
        // Find longest palindromic substring
        String longestPalindrome = longestPalindromicSubstring(str);
        if (!longestPalindrome.isEmpty()) {
            System.out.println("Longest Palindromic Substring: \"" + longestPalindrome + "\"");
        }
        
        System.out.println("=================================");
    }
    
    public static void main(String[] args) {
        System.out.println("🔄 PALINDROME STRING CHECK PROGRAM 🔄");
        System.out.println("=====================================");
        
        // Test cases
        String[] testStrings = {
            "racecar",
            "A man a plan a canal Panama",
            "race a car",
            "hello",
            "Madam",
            "noon",
            "12321",
            "12345",
            "a",
            "",
            "Was it a car or a cat I saw?",
            "Never odd or even",
            "programming",
            "level",
            "Able was I ere I saw Elba"
        };
        
        for (String testStr : testStrings) {
            checkPalindromeWithDetails(testStr);
            System.out.println();
        }
        
        // Performance test
        System.out.println("=== PERFORMANCE COMPARISON ===");
        String longPalindrome = "abcdefghijklmnopqrstuvwxyzzyxwvutsrqponmlkjihgfedcba";
        
        long startTime, endTime;
        
        startTime = System.nanoTime();
        boolean result1 = isPalindromeUsingPointers(longPalindrome);
        endTime = System.nanoTime();
        System.out.println("Pointers method: " + result1 + " (Time: " + (endTime - startTime) + " ns)");
        
        startTime = System.nanoTime();
        boolean result2 = isPalindromeUsingReverse(longPalindrome);
        endTime = System.nanoTime();
        System.out.println("Reverse method: " + result2 + " (Time: " + (endTime - startTime) + " ns)");
        
        startTime = System.nanoTime();
        boolean result3 = isPalindromeUsingRecursion(longPalindrome);
        endTime = System.nanoTime();
        System.out.println("Recursion method: " + result3 + " (Time: " + (endTime - startTime) + " ns)");
        
        System.out.println("==============================");
    }
}
