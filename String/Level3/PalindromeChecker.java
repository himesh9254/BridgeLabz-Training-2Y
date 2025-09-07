import java.util.Scanner;

public class PalindromeChecker {
    
    // Logic 1: Compare characters from start and end
    public static boolean isPalindromeLogic1(String text) {
        int start = 0;
        int end = text.length() - 1;
        
        while (start < end) {
            if (text.charAt(start) != text.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    
    // Logic 2: Recursive method
    public static boolean isPalindromeLogic2(String text, int start, int end) {
        if (start >= end) {
            return true;
        }
        
        if (text.charAt(start) != text.charAt(end)) {
            return false;
        }
        
        return isPalindromeLogic2(text, start + 1, end - 1);
    }
    
    // Helper method for reverse string using charAt()
    public static char[] reverseString(String text) {
        char[] reversed = new char[text.length()];
        
        for (int i = 0; i < text.length(); i++) {
            reversed[i] = text.charAt(text.length() - 1 - i);
        }
        
        return reversed;
    }
    
    // Logic 3: Compare original and reversed character arrays
    public static boolean isPalindromeLogic3(String text) {
        char[] original = text.toCharArray();
        char[] reversed = reverseString(text);
        
        for (int i = 0; i < original.length; i++) {
            if (original[i] != reversed[i]) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string to check for palindrome: ");
        String text = scanner.nextLine().toLowerCase().replaceAll("[^a-z0-9]", "");
        
        // Test all three logic methods
        boolean result1 = isPalindromeLogic1(text);
        boolean result2 = isPalindromeLogic2(text, 0, text.length() - 1);
        boolean result3 = isPalindromeLogic3(text);
        
        System.out.println("\n--- Palindrome Check Results ---");
        System.out.println("Processed text: \"" + text + "\"");
        System.out.println("Logic 1 (Start-End comparison): " + result1);
        System.out.println("Logic 2 (Recursive method): " + result2);
        System.out.println("Logic 3 (Array comparison): " + result3);
        
        if (result1 && result2 && result3) {
            System.out.println("✓ All methods confirm: The text IS a palindrome!");
        } else if (!result1 && !result2 && !result3) {
            System.out.println("✗ All methods confirm: The text is NOT a palindrome!");
        } else {
            System.out.println("⚠ Methods give different results - check implementation!");
        }
        
        scanner.close();
    }
}
