public class PalindromeChecker {
    // Attribute
    private String text;
    
    // Constructor
    public PalindromeChecker(String text) {
        this.text = text;
    }
    
    // Method to check if the text is a palindrome
    public boolean isPalindrome() {
        // Remove spaces and convert to lowercase for better comparison
        String cleanText = text.replaceAll("\\s+", "").toLowerCase();
        
        int left = 0;
        int right = cleanText.length() - 1;
        
        while (left < right) {
            if (cleanText.charAt(left) != cleanText.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    // Alternative method using StringBuilder for palindrome check
    public boolean isPalindromeReverse() {
        String cleanText = text.replaceAll("\\s+", "").toLowerCase();
        String reversed = new StringBuilder(cleanText).reverse().toString();
        return cleanText.equals(reversed);
    }
    
    // Method to display the result
    public void displayResult() {
        boolean result = isPalindrome();
        System.out.println("Palindrome Check:");
        System.out.println("Original text: \"" + text + "\"");
        System.out.println("Is palindrome: " + (result ? "Yes" : "No"));
        
        if (result) {
            System.out.println("The text reads the same forwards and backwards!");
        } else {
            System.out.println("The text does not read the same forwards and backwards.");
        }
        System.out.println("-------------------");
    }
    
    // Method to update text and check again
    public void setText(String newText) {
        this.text = newText;
    }
    
    // Main method to test the class
    public static void main(String[] args) {
        // Creating PalindromeChecker objects
        PalindromeChecker checker1 = new PalindromeChecker("racecar");
        PalindromeChecker checker2 = new PalindromeChecker("hello");
        PalindromeChecker checker3 = new PalindromeChecker("A man a plan a canal Panama");
        PalindromeChecker checker4 = new PalindromeChecker("Madam");
        
        // Display results
        checker1.displayResult();
        checker2.displayResult();
        checker3.displayResult();
        checker4.displayResult();
        
        // Test updating text
        checker2.setText("level");
        System.out.println("After updating text:");
        checker2.displayResult();
    }
}
