public class PalindromeChecker {
    // Attribute
    private String text;
    
    // Constructor
    public PalindromeChecker(String text) {
        this.text = text;
    }
    
    // Method to check if the text is a palindrome
    public boolean isPalindrome() {
        // Convert to lowercase and remove spaces for accurate checking
        String cleanText = text.toLowerCase().replaceAll("\\\\s+", "");
        
        int length = cleanText.length();
        for (int i = 0; i < length / 2; i++) {
            if (cleanText.charAt(i) != cleanText.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }
    
    // Method to check palindrome using reverse string approach
    public boolean isPalindromeReverse() {
        String cleanText = text.toLowerCase().replaceAll("\\\\s+", "");
        String reversed = "";
        
        // Reverse the string
        for (int i = cleanText.length() - 1; i >= 0; i--) {
            reversed += cleanText.charAt(i);
        }
        
        return cleanText.equals(reversed);
    }
    
    // Method to display the palindrome check result
    public void displayResult() {
        boolean result = isPalindrome();
        
        System.out.println("=== Palindrome Check Result ===");
        System.out.println("Text: \\\"" + text + "\\\"");
        System.out.println("Is Palindrome: " + (result ? "YES" : "NO"));
        
        if (result) {
            System.out.println("✓ The text reads the same forwards and backwards!");
        } else {
            System.out.println("✗ The text does not read the same forwards and backwards.");
        }
        System.out.println("===============================");
    }
    
    // Method to display detailed analysis
    public void displayDetailedAnalysis() {
        String cleanText = text.toLowerCase().replaceAll("\\\\s+", "");
        boolean result = isPalindrome();
        
        System.out.println("=== Detailed Palindrome Analysis ===");
        System.out.println("Original Text: \\\"" + text + "\\\"");
        System.out.println("Cleaned Text: \\\"" + cleanText + "\\\"");
        System.out.println("Length: " + cleanText.length());
        System.out.println("Is Palindrome: " + (result ? "YES" : "NO"));
        
        // Show character comparison
        System.out.println("Character Comparison:");
        for (int i = 0; i < cleanText.length() / 2; i++) {
            char leftChar = cleanText.charAt(i);
            char rightChar = cleanText.charAt(cleanText.length() - 1 - i);
            boolean match = leftChar == rightChar;
            System.out.println("  Position " + i + " vs " + (cleanText.length() - 1 - i) + 
                             ": '" + leftChar + "' vs '" + rightChar + "' → " + 
                             (match ? "✓" : "✗"));
        }
        System.out.println("====================================");
    }
    
    // Getters and Setters
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    // Main method to test the class
    public static void main(String[] args) {
        // Create palindrome checker objects with different texts
        PalindromeChecker checker1 = new PalindromeChecker("racecar");
        PalindromeChecker checker2 = new PalindromeChecker("A man a plan a canal Panama");
        PalindromeChecker checker3 = new PalindromeChecker("hello");
        PalindromeChecker checker4 = new PalindromeChecker("madam");
        PalindromeChecker checker5 = new PalindromeChecker("Was it a car or a cat I saw");
        
        // Display results for all checkers
        System.out.println("=== Palindrome Checker Tests ===");
        checker1.displayResult();
        checker2.displayResult();
        checker3.displayResult();
        checker4.displayResult();
        checker5.displayResult();
        
        // Show detailed analysis for one example
        System.out.println("\\n=== Detailed Analysis Example ===");
        checker2.displayDetailedAnalysis();
    }
}
