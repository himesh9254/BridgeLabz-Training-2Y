/**
 * Program to remove all occurrences of a specific character from a string
 * Example: "Hello World" -> remove 'l' -> "Heo Word"
 */
public class RemoveSpecificChar {
    
    /**
     * Remove specific character using StringBuilder
     */
    public static String removeCharUsingStringBuilder(String str, char charToRemove) {
        if (str == null) return null;
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != charToRemove) {
                result.append(str.charAt(i));
            }
        }
        return result.toString();
    }
    
    /**
     * Remove specific character using replaceAll
     */
    public static String removeCharUsingReplaceAll(String str, char charToRemove) {
        if (str == null) return null;
        return str.replace(String.valueOf(charToRemove), "");
    }
    
    /**
     * Remove character case-insensitively
     */
    public static String removeCharCaseInsensitive(String str, char charToRemove) {
        if (str == null) return null;
        
        StringBuilder result = new StringBuilder();
        char lowerChar = Character.toLowerCase(charToRemove);
        
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            if (Character.toLowerCase(currentChar) != lowerChar) {
                result.append(currentChar);
            }
        }
        return result.toString();
    }
    
    /**
     * Analyze character removal with details
     */
    public static void analyzeCharRemoval(String str, char charToRemove) {
        System.out.println("=== Remove Character Analysis ===");
        System.out.println("Original String: \"" + str + "\"");
        System.out.println("Character to Remove: '" + charToRemove + "'");
        
        if (str == null) {
            System.out.println("Error: Null string!");
            System.out.println("=================================");
            return;
        }
        
        // Count occurrences
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == charToRemove) count++;
        }
        
        String result1 = removeCharUsingStringBuilder(str, charToRemove);
        String result2 = removeCharUsingReplaceAll(str, charToRemove);
        String result3 = removeCharCaseInsensitive(str, charToRemove);
        
        System.out.println("\\nResults:");
        System.out.println("StringBuilder: \"" + result1 + "\"");
        System.out.println("ReplaceAll: \"" + result2 + "\"");
        System.out.println("Case-insensitive: \"" + result3 + "\"");
        System.out.println("\\nOccurrences removed: " + count);
        System.out.println("Original length: " + str.length());
        System.out.println("New length: " + result1.length());
        System.out.println("=================================");
    }
    
    public static void main(String[] args) {
        System.out.println("🗑️ REMOVE SPECIFIC CHARACTER PROGRAM 🗑️");
        System.out.println("========================================");
        
        // Test cases
        Object[][] testCases = {
            {"Hello World", 'l'},
            {"Programming", 'm'},
            {"aabbccddee", 'a'},
            {"Java Language", 'a'},
            {"12345", '3'},
            {"Hello, World!", ','},
            {"", 'x'},
            {"aaaa", 'a'},
            {"xyz", 'a'} // Character not present
        };
        
        for (Object[] testCase : testCases) {
            analyzeCharRemoval((String) testCase[0], (Character) testCase[1]);
            System.out.println();
        }
    }
}
