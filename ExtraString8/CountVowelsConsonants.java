/**
 * Program to count the number of vowels and consonants in a given string
 */
public class CountVowelsConsonants {
    
    /**
     * Method to count vowels and consonants in a string
     * @param str Input string
     */
    public static void countVowelsAndConsonants(String str) {
        int vowels = 0;
        int consonants = 0;
        int digits = 0;
        int spaces = 0;
        int specialChars = 0;
        
        // Convert string to lowercase for easy comparison
        str = str.toLowerCase();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            // Check if character is a vowel
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vowels++;
            }
            // Check if character is a consonant (alphabetic but not vowel)
            else if (ch >= 'a' && ch <= 'z') {
                consonants++;
            }
            // Check if character is a digit
            else if (ch >= '0' && ch <= '9') {
                digits++;
            }
            // Check if character is a space
            else if (ch == ' ') {
                spaces++;
            }
            // Everything else is a special character
            else {
                specialChars++;
            }
        }
        
        // Display results
        System.out.println("=== Vowels and Consonants Analysis ===");
        System.out.println("Input String: \"" + str + "\"");
        System.out.println("Length: " + str.length());
        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
        System.out.println("Digits: " + digits);
        System.out.println("Spaces: " + spaces);
        System.out.println("Special Characters: " + specialChars);
        System.out.println("======================================");
    }
    
    /**
     * Method to get detailed vowel breakdown
     * @param str Input string
     */
    public static void detailedVowelAnalysis(String str) {
        int a = 0, e = 0, i = 0, o = 0, u = 0;
        
        str = str.toLowerCase();
        
        for (int j = 0; j < str.length(); j++) {
            char ch = str.charAt(j);
            switch (ch) {
                case 'a': a++; break;
                case 'e': e++; break;
                case 'i': i++; break;
                case 'o': o++; break;
                case 'u': u++; break;
            }
        }
        
        System.out.println("=== Detailed Vowel Breakdown ===");
        System.out.println("A: " + a);
        System.out.println("E: " + e);
        System.out.println("I: " + i);
        System.out.println("O: " + o);
        System.out.println("U: " + u);
        System.out.println("Total Vowels: " + (a + e + i + o + u));
        System.out.println("================================");
    }
    
    public static void main(String[] args) {
        // Test cases
        String[] testStrings = {
            "Hello World",
            "Programming in Java",
            "AEIOUaeiou",
            "BCDFG bcdfg",
            "Hello123 World!",
            "The quick brown fox jumps over the lazy dog"
        };
        
        System.out.println("🔤 COUNT VOWELS AND CONSONANTS PROGRAM 🔤");
        System.out.println("=========================================");
        
        for (String testStr : testStrings) {
            countVowelsAndConsonants(testStr);
            detailedVowelAnalysis(testStr);
            System.out.println();
        }
    }
}
