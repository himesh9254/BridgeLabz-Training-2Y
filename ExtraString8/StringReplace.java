/**
 * Program to replace a given word with another word in a sentence
 */
public class StringReplace {
    
    /**
     * Replace word using simple approach
     */
    public static String replaceWord(String sentence, String oldWord, String newWord) {
        if (sentence == null || oldWord == null || newWord == null) {
            return sentence;
        }
        
        StringBuilder result = new StringBuilder();
        String[] words = sentence.split("\\\\s+");
        
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(oldWord)) {
                result.append(newWord);
            } else {
                result.append(words[i]);
            }
            
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        
        return result.toString();
    }
    
    /**
     * Replace word case-insensitively
     */
    public static String replaceWordCaseInsensitive(String sentence, String oldWord, String newWord) {
        if (sentence == null || oldWord == null || newWord == null) {
            return sentence;
        }
        
        StringBuilder result = new StringBuilder();
        String[] words = sentence.split("\\\\s+");
        
        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase(oldWord)) {
                result.append(newWord);
            } else {
                result.append(words[i]);
            }
            
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        
        return result.toString();
    }
    
    /**
     * Replace word with punctuation handling
     */
    public static String replaceWordWithPunctuation(String sentence, String oldWord, String newWord) {
        if (sentence == null || oldWord == null || newWord == null) {
            return sentence;
        }
        
        StringBuilder result = new StringBuilder();
        String[] words = sentence.split("\\\\s+");
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String punctuation = "";
            
            // Extract punctuation from end of word
            while (!word.isEmpty() && !Character.isLetterOrDigit(word.charAt(word.length() - 1))) {
                punctuation = word.charAt(word.length() - 1) + punctuation;
                word = word.substring(0, word.length() - 1);
            }
            
            // Replace if matches
            if (word.equalsIgnoreCase(oldWord)) {
                result.append(newWord).append(punctuation);
            } else {
                result.append(words[i]);
            }
            
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        
        return result.toString();
    }
    
    /**
     * Analyze word replacement with details
     */
    public static void analyzeWordReplacement(String sentence, String oldWord, String newWord) {
        System.out.println("=== Word Replacement Analysis ===");
        System.out.println("Original Sentence: \"" + sentence + "\"");
        System.out.println("Replace: \"" + oldWord + "\" → \"" + newWord + "\"");
        
        if (sentence == null || oldWord == null || newWord == null) {
            System.out.println("Error: One or more parameters are null!");
            System.out.println("=================================");
            return;
        }
        
        // Count occurrences
        String[] words = sentence.split("\\\\s+");
        int exactMatches = 0;
        int caseInsensitiveMatches = 0;
        
        for (String word : words) {
            if (word.equals(oldWord)) exactMatches++;
            if (word.equalsIgnoreCase(oldWord)) caseInsensitiveMatches++;
        }
        
        String result1 = replaceWord(sentence, oldWord, newWord);
        String result2 = replaceWordCaseInsensitive(sentence, oldWord, newWord);
        String result3 = replaceWordWithPunctuation(sentence, oldWord, newWord);
        
        System.out.println("\\nResults:");
        System.out.println("Exact match: \"" + result1 + "\"");
        System.out.println("Case-insensitive: \"" + result2 + "\"");
        System.out.println("With punctuation: \"" + result3 + "\"");
        
        System.out.println("\\nStatistics:");
        System.out.println("Exact matches found: " + exactMatches);
        System.out.println("Case-insensitive matches: " + caseInsensitiveMatches);
        System.out.println("Total words: " + words.length);
        
        System.out.println("=================================");
    }
    
    public static void main(String[] args) {
        System.out.println("🔄 STRING REPLACE PROGRAM 🔄");
        System.out.println("=============================");
        
        // Test cases
        Object[][] testCases = {
            {"Hello world, hello universe", "hello", "hi"},
            {"Java is great. Java is powerful.", "Java", "Python"},
            {"The cat sat on the mat", "cat", "dog"},
            {"Programming in Java language", "Java", "C++"},
            {"Hello, world! How are you?", "Hello", "Hi"},
            {"", "test", "demo"},
            {"One word", "word", "term"},
            {"Case Case case CASE", "case", "example"}
        };
        
        for (Object[] testCase : testCases) {
            analyzeWordReplacement((String) testCase[0], (String) testCase[1], (String) testCase[2]);
            System.out.println();
        }
    }
}
