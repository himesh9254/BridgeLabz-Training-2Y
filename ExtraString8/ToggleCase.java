/**
 * Program to toggle the case of each character in a given string
 * Convert uppercase letters to lowercase and vice versa
 */
public class ToggleCase {
    
    /**
     * Method to toggle case using StringBuilder
     * @param str Input string
     * @return String with toggled case
     */
    public static String toggleCaseUsingStringBuilder(String str) {
        if (str == null) {
            return null;
        }
        
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            if (Character.isUpperCase(ch)) {
                result.append(Character.toLowerCase(ch));
            } else if (Character.isLowerCase(ch)) {
                result.append(Character.toUpperCase(ch));
            } else {
                // Keep non-alphabetic characters as they are
                result.append(ch);
            }
        }
        
        return result.toString();
    }
    
    /**
     * Method to toggle case using character array
     * @param str Input string
     * @return String with toggled case
     */
    public static String toggleCaseUsingCharArray(String str) {
        if (str == null) {
            return null;
        }
        
        char[] chars = str.toCharArray();
        
        for (int i = 0; i < chars.length; i++) {
            if (Character.isUpperCase(chars[i])) {
                chars[i] = Character.toLowerCase(chars[i]);
            } else if (Character.isLowerCase(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
            }
            // Non-alphabetic characters remain unchanged
        }
        
        return new String(chars);
    }
    
    /**
     * Method to toggle case using ASCII values
     * @param str Input string
     * @return String with toggled case
     */
    public static String toggleCaseUsingASCII(String str) {
        if (str == null) {
            return null;
        }
        
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            // Check if uppercase letter (A-Z: 65-90)
            if (ch >= 'A' && ch <= 'Z') {
                result.append((char)(ch + 32)); // Convert to lowercase
            }
            // Check if lowercase letter (a-z: 97-122)
            else if (ch >= 'a' && ch <= 'z') {
                result.append((char)(ch - 32)); // Convert to uppercase
            }
            else {
                result.append(ch); // Keep non-alphabetic characters
            }
        }
        
        return result.toString();
    }
    
    /**
     * Method to toggle case with custom rules
     * @param str Input string
     * @param preserveSpaces Whether to preserve space positions
     * @param toggleNumbers Whether to apply special handling to numbers
     * @return String with toggled case
     */
    public static String toggleCaseCustom(String str, boolean preserveSpaces, boolean toggleNumbers) {
        if (str == null) {
            return null;
        }
        
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            if (Character.isUpperCase(ch)) {
                result.append(Character.toLowerCase(ch));
            } else if (Character.isLowerCase(ch)) {
                result.append(Character.toUpperCase(ch));
            } else if (Character.isDigit(ch) && toggleNumbers) {
                // Custom rule: convert digits to their complement (0->9, 1->8, etc.)
                int digit = Character.getNumericValue(ch);
                int complement = 9 - digit;
                result.append(complement);
            } else if (ch == ' ' && !preserveSpaces) {
                result.append('_'); // Replace spaces with underscores
            } else {
                result.append(ch);
            }
        }
        
        return result.toString();
    }
    
    /**
     * Method to toggle case for each word separately
     * @param str Input string
     * @return String with each word's case toggled
     */
    public static String toggleCaseByWord(String str) {
        if (str == null) {
            return null;
        }
        
        String[] words = str.split("\\\\s+");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            result.append(toggleCaseUsingStringBuilder(words[i]));
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        
        return result.toString();
    }
    
    /**
     * Method to alternate case (first char uppercase, second lowercase, etc.)
     * @param str Input string
     * @return String with alternating case
     */
    public static String alternatingCase(String str) {
        if (str == null) {
            return null;
        }
        
        StringBuilder result = new StringBuilder();
        boolean shouldBeUppercase = true;
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            if (Character.isLetter(ch)) {
                if (shouldBeUppercase) {
                    result.append(Character.toUpperCase(ch));
                } else {
                    result.append(Character.toLowerCase(ch));
                }
                shouldBeUppercase = !shouldBeUppercase;
            } else {
                result.append(ch);
            }
        }
        
        return result.toString();
    }
    
    /**
     * Method to analyze case toggling with detailed information
     * @param str Input string
     */
    public static void analyzeCaseToggling(String str) {
        System.out.println("=== Case Toggle Analysis ===");
        System.out.println("Original String: \"" + str + "\"");
        
        if (str == null) {
            System.out.println("Error: Null string provided!");
            System.out.println("============================");
            return;
        }
        
        // Count different character types
        int uppercase = 0, lowercase = 0, digits = 0, spaces = 0, others = 0;
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isUpperCase(ch)) uppercase++;
            else if (Character.isLowerCase(ch)) lowercase++;
            else if (Character.isDigit(ch)) digits++;
            else if (ch == ' ') spaces++;
            else others++;
        }
        
        System.out.println("Character Analysis:");
        System.out.println("  Uppercase letters: " + uppercase);
        System.out.println("  Lowercase letters: " + lowercase);
        System.out.println("  Digits: " + digits);
        System.out.println("  Spaces: " + spaces);
        System.out.println("  Other characters: " + others);
        System.out.println("  Total length: " + str.length());
        
        // Apply different toggle methods
        String result1 = toggleCaseUsingStringBuilder(str);
        String result2 = toggleCaseUsingCharArray(str);
        String result3 = toggleCaseUsingASCII(str);
        String result4 = toggleCaseCustom(str, true, false);
        String result5 = toggleCaseByWord(str);
        String result6 = alternatingCase(str);
        
        System.out.println("\\nToggle Results:");
        System.out.println("StringBuilder: \"" + result1 + "\"");
        System.out.println("Char Array: \"" + result2 + "\"");
        System.out.println("ASCII Method: \"" + result3 + "\"");
        System.out.println("Custom Toggle: \"" + result4 + "\"");
        System.out.println("By Word: \"" + result5 + "\"");
        System.out.println("Alternating: \"" + result6 + "\"");
        
        // Verify consistency
        boolean consistent = result1.equals(result2) && result2.equals(result3);
        System.out.println("\\nMethods Consistent: " + (consistent ? "✓ YES" : "✗ NO"));
        
        // Show character-by-character transformation
        if (str.length() <= 30) {
            System.out.println("\\nCharacter-by-Character Transformation:");
            for (int i = 0; i < str.length(); i++) {
                char original = str.charAt(i);
                char toggled = result1.charAt(i);
                String transformation = "";
                
                if (Character.isUpperCase(original)) {
                    transformation = "UPPER → lower";
                } else if (Character.isLowerCase(original)) {
                    transformation = "lower → UPPER";
                } else {
                    transformation = "unchanged";
                }
                
                System.out.println("  " + i + ": '" + original + "' → '" + toggled + "' (" + transformation + ")");
            }
        }
        
        System.out.println("============================");
    }
    
    public static void main(String[] args) {
        System.out.println("🔄 TOGGLE CASE OF CHARACTERS PROGRAM 🔄");
        System.out.println("========================================");
        
        // Test cases
        String[] testStrings = {
            "Hello World",
            "Java Programming",
            "UPPERCASE",
            "lowercase",
            "MiXeD CaSe",
            "123ABC456def",
            "Hello, World! How are you?",
            "THE QUICK brown FOX jumps OVER the LAZY dog",
            "",
            "A",
            "a",
            "Programming@123#Java$",
            "   Spaces   Around   ",
            "Numbers 123 and Letters ABC",
            null
        };
        
        for (String testStr : testStrings) {
            analyzeCaseToggling(testStr);
            System.out.println();
        }
        
        // Performance comparison
        System.out.println("=== PERFORMANCE COMPARISON ===");
        String longString = "The Quick Brown Fox Jumps Over The Lazy Dog ".repeat(1000);
        
        long startTime, endTime;
        
        startTime = System.nanoTime();
        String result1 = toggleCaseUsingStringBuilder(longString);
        endTime = System.nanoTime();
        System.out.println("StringBuilder method: " + result1.length() + " chars (Time: " + (endTime - startTime) + " ns)");
        
        startTime = System.nanoTime();
        String result2 = toggleCaseUsingCharArray(longString);
        endTime = System.nanoTime();
        System.out.println("CharArray method: " + result2.length() + " chars (Time: " + (endTime - startTime) + " ns)");
        
        startTime = System.nanoTime();
        String result3 = toggleCaseUsingASCII(longString);
        endTime = System.nanoTime();
        System.out.println("ASCII method: " + result3.length() + " chars (Time: " + (endTime - startTime) + " ns)");
        
        // Special demonstrations
        System.out.println("\\n=== SPECIAL DEMONSTRATIONS ===");
        
        String demo = "Hello World 123";
        System.out.println("Original: \"" + demo + "\"");
        System.out.println("Normal Toggle: \"" + toggleCaseUsingStringBuilder(demo) + "\"");
        System.out.println("Custom (preserve spaces): \"" + toggleCaseCustom(demo, true, false) + "\"");
        System.out.println("Custom (toggle numbers): \"" + toggleCaseCustom(demo, true, true) + "\"");
        System.out.println("Alternating Case: \"" + alternatingCase(demo) + "\"");
        
        System.out.println("==============================");
    }
}
