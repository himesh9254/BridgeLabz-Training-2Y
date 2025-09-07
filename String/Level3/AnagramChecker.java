import java.util.Scanner;

public class AnagramChecker {
    
    // Method to check if two texts are anagrams
    public static boolean areAnagrams(String text1, String text2) {
        // Check if lengths are equal
        if (text1.length() != text2.length()) {
            return false;
        }
        
        // Create frequency arrays for both texts
        int[] freq1 = new int[256]; // ASCII characters
        int[] freq2 = new int[256];
        
        // Count frequency of characters in both texts
        for (int i = 0; i < text1.length(); i++) {
            freq1[text1.charAt(i)]++;
            freq2[text2.charAt(i)]++;
        }
        
        // Compare frequency arrays
        for (int i = 0; i < 256; i++) {
            if (freq1[i] != freq2[i]) {
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter first text: ");
        String text1 = scanner.nextLine().toLowerCase().replaceAll("[^a-z]", "");
        
        System.out.print("Enter second text: ");
        String text2 = scanner.nextLine().toLowerCase().replaceAll("[^a-z]", "");
        
        boolean result = areAnagrams(text1, text2);
        
        System.out.println("\n--- Anagram Check Results ---");
        System.out.println("First text (processed): \"" + text1 + "\"");
        System.out.println("Second text (processed): \"" + text2 + "\"");
        System.out.println("Length of first text: " + text1.length());
        System.out.println("Length of second text: " + text2.length());
        
        if (result) {
            System.out.println("✓ The texts ARE anagrams!");
        } else {
            System.out.println("✗ The texts are NOT anagrams!");
        }
        
        // Show character frequency for both texts
        System.out.println("\nCharacter analysis:");
        int[] freq1 = new int[256];
        int[] freq2 = new int[256];
        
        for (int i = 0; i < text1.length(); i++) {
            freq1[text1.charAt(i)]++;
        }
        
        for (int i = 0; i < text2.length(); i++) {
            freq2[text2.charAt(i)]++;
        }
        
        System.out.println("Text 1 characters:");
        for (int i = 0; i < 256; i++) {
            if (freq1[i] > 0) {
                System.out.print("'" + (char)i + "':" + freq1[i] + " ");
            }
        }
        
        System.out.println("\nText 2 characters:");
        for (int i = 0; i < 256; i++) {
            if (freq2[i] > 0) {
                System.out.print("'" + (char)i + "':" + freq2[i] + " ");
            }
        }
        System.out.println();
        
        scanner.close();
    }
}
