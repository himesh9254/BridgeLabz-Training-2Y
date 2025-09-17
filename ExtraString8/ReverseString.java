/**
 * Program to reverse a given string without using any built-in reverse functions
 */
public class ReverseString {
    
    /**
     * Method to reverse a string using character array approach
     * @param str Input string to reverse
     * @return Reversed string
     */
    public static String reverseUsingArray(String str) {
        char[] charArray = str.toCharArray();
        int left = 0;
        int right = charArray.length - 1;
        
        // Swap characters from both ends moving towards center
        while (left < right) {
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;
            right--;
        }
        
        return new String(charArray);
    }
    
    /**
     * Method to reverse a string using StringBuilder approach
     * @param str Input string to reverse
     * @return Reversed string
     */
    public static String reverseUsingStringBuilder(String str) {
        StringBuilder reversed = new StringBuilder();
        
        // Iterate from last character to first
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed.append(str.charAt(i));
        }
        
        return reversed.toString();
    }
    
    /**
     * Method to reverse a string using recursion
     * @param str Input string to reverse
     * @return Reversed string
     */
    public static String reverseUsingRecursion(String str) {
        // Base case: if string is empty or has one character
        if (str == null || str.length() <= 1) {
            return str;
        }
        
        // Recursive case: reverse substring and add first character at the end
        return reverseUsingRecursion(str.substring(1)) + str.charAt(0);
    }
    
    /**
     * Method to reverse a string using simple loop and concatenation
     * @param str Input string to reverse
     * @return Reversed string
     */
    public static String reverseUsingLoop(String str) {
        String reversed = "";
        
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }
        
        return reversed;
    }
    
    /**
     * Method to reverse only words in a sentence (keeping word order)
     * @param sentence Input sentence
     * @return Sentence with reversed words
     */
    public static String reverseWordsInSentence(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            result.append(reverseUsingArray(words[i]));
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        
        return result.toString();
    }
    
    /**
     * Method to display reverse operation with details
     * @param original Original string
     * @param method Method name used for reversal
     */
    public static void displayReverseOperation(String original, String method) {
        String reversed = "";
        long startTime = System.nanoTime();
        
        switch (method) {
            case "Array":
                reversed = reverseUsingArray(original);
                break;
            case "StringBuilder":
                reversed = reverseUsingStringBuilder(original);
                break;
            case "Recursion":
                reversed = reverseUsingRecursion(original);
                break;
            case "Loop":
                reversed = reverseUsingLoop(original);
                break;
        }
        
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        
        System.out.println("=== String Reverse Operation ===");
        System.out.println("Method: " + method);
        System.out.println("Original: \"" + original + "\"");
        System.out.println("Reversed: \"" + reversed + "\"");
        System.out.println("Length: " + original.length());
        System.out.println("Time taken: " + duration + " nanoseconds");
        System.out.println("================================");
    }
    
    public static void main(String[] args) {
        System.out.println("🔄 STRING REVERSE PROGRAM 🔄");
        System.out.println("=============================");
        
        // Test strings
        String[] testStrings = {
            "Hello World",
            "Java Programming",
            "Reverse",
            "12345",
            "A",
            "",
            "Madam",
            "The quick brown fox"
        };
        
        String[] methods = {"Array", "StringBuilder", "Recursion", "Loop"};
        
        // Test different reversal methods
        for (String testStr : testStrings) {
            System.out.println("\\n--- Testing with: \"" + testStr + "\" ---");
            for (String method : methods) {
                displayReverseOperation(testStr, method);
            }
        }
        
        // Test reverse words in sentence
        System.out.println("\\n=== REVERSE WORDS IN SENTENCE ===");
        String sentence = "Hello World Java Programming";
        System.out.println("Original sentence: \"" + sentence + "\"");
        System.out.println("Words reversed: \"" + reverseWordsInSentence(sentence) + "\"");
        System.out.println("=================================");
        
        // Performance comparison
        System.out.println("\\n=== PERFORMANCE COMPARISON ===");
        String longString = "This is a very long string to test the performance of different reverse methods";
        
        for (String method : methods) {
            displayReverseOperation(longString, method);
        }
    }
}
