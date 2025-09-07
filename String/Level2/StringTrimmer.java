import java.util.Scanner;

public class StringTrimmer {
    
    public static int[] findTrimPositions(String text) {
        int start = 0;
        int end = text.length() - 1;
        
        // Find first non-space character
        while (start < text.length() && text.charAt(start) == ' ') {
            start++;
        }
        
        // Find last non-space character
        while (end >= 0 && text.charAt(end) == ' ') {
            end--;
        }
        
        return new int[]{start, end + 1}; // end+1 for substring method
    }
    
    public static String createSubstring(String text, int start, int end) {
        String result = "";
        for (int i = start; i < end; i++) {
            result += text.charAt(i);
        }
        return result;
    }
    
    public static boolean compareStrings(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string with leading/trailing spaces: ");
        String text = scanner.nextLine();
        
        // Trim using custom method
        int[] positions = findTrimPositions(text);
        String customTrimmed = createSubstring(text, positions[0], positions[1]);
        
        // Trim using built-in method
        String builtInTrimmed = text.trim();
        
        // Compare results
        boolean areEqual = compareStrings(customTrimmed, builtInTrimmed);
        
        System.out.println("\n--- String Trimming Results ---");
        System.out.println("Original string: \"" + text + "\"");
        System.out.println("Custom trimmed: \"" + customTrimmed + "\"");
        System.out.println("Built-in trimmed: \"" + builtInTrimmed + "\"");
        System.out.println("Are both results equal? " + areEqual);
        
        if (areEqual) {
            System.out.println("✓ Both methods produce the same result!");
        } else {
            System.out.println("✗ Methods produce different results!");
        }
        
        scanner.close();
    }
}
