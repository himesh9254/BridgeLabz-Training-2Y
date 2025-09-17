import java.util.Scanner;

public class PalindromeChecker {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== Palindrome Checker ===");
        System.out.println("This program checks if a string is a palindrome.");
        System.out.println("A palindrome reads the same forwards and backwards.");
        System.out.println();
        
        // Get input from user
        String input = getStringInput();
        
        // Check if palindrome using different methods
        boolean isPalindrome = isPalindrome(input);
        
        // Display results
        displayResults(input, isPalindrome);
        
        // Show additional analysis
        showAdditionalAnalysis(input);
        
        scanner.close();
    }
    
    /**
     * Gets string input from user
     * @return user input string
     */
    public static String getStringInput() {
        System.out.print("Enter a string to check: ");
        return scanner.nextLine();
    }
    
    /**
     * Checks if a string is a palindrome (case-sensitive, exact match)
     * @param str the string to check
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindrome(String str) {
        return isPalindromeIterative(str);
    }
    
    /**
     * Checks palindrome using iterative approach
     * @param str the string to check
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindromeIterative(String str) {
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
     * Checks palindrome using recursive approach
     * @param str the string to check
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindromeRecursive(String str) {
        return isPalindromeRecursiveHelper(str, 0, str.length() - 1);
    }
    
    /**
     * Helper method for recursive palindrome check
     * @param str the string to check
     * @param left left index
     * @param right right index
     * @return true if palindrome, false otherwise
     */
    private static boolean isPalindromeRecursiveHelper(String str, int left, int right) {
        // Base case
        if (left >= right) {
            return true;
        }
        
        // Check if characters match
        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }
        
        // Recursive call
        return isPalindromeRecursiveHelper(str, left + 1, right - 1);
    }
    
    /**
     * Checks palindrome using StringBuilder reverse method
     * @param str the string to check
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindromeUsingReverse(String str) {
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }
    
    /**
     * Checks palindrome ignoring case and spaces
     * @param str the string to check
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindromeIgnoreCaseAndSpaces(String str) {
        // Remove spaces and convert to lowercase
        String cleaned = str.replaceAll("\\s+", "").toLowerCase();
        return isPalindromeIterative(cleaned);
    }
    
    /**
     * Checks palindrome ignoring case, spaces, and punctuation
     * @param str the string to check
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindromeIgnoreAllNonAlphabetic(String str) {
        // Keep only alphabetic characters and convert to lowercase
        String cleaned = str.replaceAll("[^a-zA-Z]", "").toLowerCase();
        return isPalindromeIterative(cleaned);
    }
    
    /**
     * Displays the palindrome checking results
     * @param input the original input string
     * @param isPalindrome whether it's a palindrome
     */
    public static void displayResults(String input, boolean isPalindrome) {
        System.out.println();
        System.out.println("=== Results ===");
        System.out.println("Input string: \"" + input + "\"");
        System.out.println("Length: " + input.length() + " characters");
        
        if (isPalindrome) {
            System.out.println("✓ \"" + input + "\" IS a palindrome!");
            System.out.println("  It reads the same forwards and backwards.");
        } else {
            System.out.println("✗ \"" + input + "\" is NOT a palindrome.");
            System.out.println("  It reads differently forwards and backwards.");
        }
        
        // Show the string reversed
        String reversed = new StringBuilder(input).reverse().toString();
        System.out.println("Reversed: \"" + reversed + "\"");
    }
    
    /**
     * Shows additional analysis of the string
     * @param input the input string
     */
    public static void showAdditionalAnalysis(String input) {
        System.out.println();
        System.out.println("=== Additional Analysis ===");
        
        // Test different palindrome checking methods
        System.out.println("Different checking methods:");
        System.out.println("• Exact match (case-sensitive): " + isPalindromeIterative(input));
        System.out.println("• Using recursion: " + isPalindromeRecursive(input));
        System.out.println("• Using reverse method: " + isPalindromeUsingReverse(input));
        System.out.println("• Ignoring case and spaces: " + isPalindromeIgnoreCaseAndSpaces(input));
        System.out.println("• Ignoring case and punctuation: " + isPalindromeIgnoreAllNonAlphabetic(input));
        
        // Show character analysis
        System.out.println();
        System.out.println("Character analysis:");
        showCharacterAnalysis(input);
        
        // Performance comparison
        System.out.println();
        System.out.println("=== Performance Comparison ===");
        comparePerformance(input);
        
        // Show examples
        System.out.println();
        System.out.println("=== Example Palindromes ===");
        showExamplePalindromes();
    }
    
    /**
     * Shows character-by-character analysis
     * @param str the string to analyze
     */
    public static void showCharacterAnalysis(String str) {
        System.out.println("Characters (position: character):");
        for (int i = 0; i < str.length(); i++) {
            System.out.print(i + ": '" + str.charAt(i) + "'");
            if (i < str.length() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        
        if (str.length() > 1) {
            System.out.println("Character comparisons:");
            int pairs = str.length() / 2;
            for (int i = 0; i < pairs; i++) {
                int leftIndex = i;
                int rightIndex = str.length() - 1 - i;
                char leftChar = str.charAt(leftIndex);
                char rightChar = str.charAt(rightIndex);
                
                System.out.printf("Position %d ('%c') vs Position %d ('%c'): %s%n",
                    leftIndex, leftChar, rightIndex, rightChar,
                    (leftChar == rightChar) ? "✓ Match" : "✗ No match");
            }
        }
    }
    
    /**
     * Compares performance of different methods
     * @param str the string to test
     */
    public static void comparePerformance(String str) {
        int iterations = 10000;
        
        // Test iterative method
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            isPalindromeIterative(str);
        }
        long iterativeTime = System.nanoTime() - startTime;
        
        // Test recursive method
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            isPalindromeRecursive(str);
        }
        long recursiveTime = System.nanoTime() - startTime;
        
        // Test reverse method
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            isPalindromeUsingReverse(str);
        }
        long reverseTime = System.nanoTime() - startTime;
        
        System.out.printf("Performance (%d iterations):%n", iterations);
        System.out.printf("Iterative method: %d nanoseconds%n", iterativeTime);
        System.out.printf("Recursive method: %d nanoseconds%n", recursiveTime);
        System.out.printf("Reverse method: %d nanoseconds%n", reverseTime);
        
        // Find fastest method
        long fastest = Math.min(iterativeTime, Math.min(recursiveTime, reverseTime));
        if (fastest == iterativeTime) {
            System.out.println("Fastest: Iterative method");
        } else if (fastest == recursiveTime) {
            System.out.println("Fastest: Recursive method");
        } else {
            System.out.println("Fastest: Reverse method");
        }
    }
    
    /**
     * Shows example palindromes
     */
    public static void showExamplePalindromes() {
        String[] examples = {
            "racecar", "level", "madam", "noon", "civic",
            "A man a plan a canal Panama", "race a car", "hello"
        };
        
        for (String example : examples) {
            boolean exact = isPalindromeIterative(example);
            boolean flexible = isPalindromeIgnoreAllNonAlphabetic(example);
            
            System.out.printf("%-25s - Exact: %-5s Flexible: %-5s%n", 
                "\"" + example + "\"", exact, flexible);
        }
        
        System.out.println();
        System.out.println("Note: 'Exact' means case-sensitive with all characters.");
        System.out.println("      'Flexible' means ignoring case, spaces, and punctuation.");
    }
}
