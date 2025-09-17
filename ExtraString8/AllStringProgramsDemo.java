/**
 * Comprehensive demonstration of all Extra String Programs
 * This class showcases the functionality of all 12 string manipulation programs
 */
public class AllStringProgramsDemo {
    
    public static void main(String[] args) {
        System.out.println("===================================================");
        System.out.println("     EXTRA STRING PROGRAMS COMPREHENSIVE DEMO");
        System.out.println("===================================================");
        
        // Demo string for consistent testing
        String demoString = "Programming in Java Language";
        String demoString2 = "Hello World, Welcome to Java Programming!";
        
        System.out.println("Demo Strings:");
        System.out.println("String 1: \"" + demoString + "\"");
        System.out.println("String 2: \"" + demoString2 + "\"");
        System.out.println();
        
        // Program 1: Count Vowels and Consonants
        System.out.println("1️⃣  COUNT VOWELS AND CONSONANTS");
        System.out.println("--------------------------------");
        CountVowelsConsonants.countVowelsAndConsonants(demoString);
        System.out.println();
        
        // Program 2: Reverse a String
        System.out.println("2️⃣  REVERSE STRING");
        System.out.println("------------------");
        String reversed = ReverseString.reverseUsingArray(demoString);
        System.out.println("Original: \"" + demoString + "\"");
        System.out.println("Reversed: \"" + reversed + "\"");
        System.out.println();
        
        // Program 3: Palindrome Check
        System.out.println("3️⃣  PALINDROME CHECK");
        System.out.println("--------------------");
        String palindromeTest = "racecar";
        boolean isPalindrome = PalindromeCheck.isPalindromeUsingPointers(palindromeTest);
        System.out.println("Testing: \"" + palindromeTest + "\"");
        System.out.println("Is Palindrome: " + (isPalindrome ? "✓ YES" : "✗ NO"));
        System.out.println();
        
        // Program 4: Remove Duplicates
        System.out.println("4️⃣  REMOVE DUPLICATES");
        System.out.println("---------------------");
        String withDuplicates = "programming";
        String noDuplicates = RemoveDuplicates.removeDuplicatesUsingSet(withDuplicates);
        System.out.println("Original: \"" + withDuplicates + "\"");
        System.out.println("No Duplicates: \"" + noDuplicates + "\"");
        System.out.println();
        
        // Program 5: Longest Word
        System.out.println("5️⃣  LONGEST WORD");
        System.out.println("----------------");
        String longestWord = LongestWord.findLongestWord(demoString2);
        System.out.println("Sentence: \"" + demoString2 + "\"");
        System.out.println("Longest Word: \"" + longestWord + "\"");
        System.out.println();
        
        // Program 6: Substring Occurrences
        System.out.println("6️⃣  SUBSTRING OCCURRENCES");
        System.out.println("-------------------------");
        String mainStr = "Java is great, Java is powerful, Java is versatile";
        String subStr = "Java";
        int occurrences = SubstringOccurrences.countOccurrencesUsingIndexOf(mainStr, subStr);
        System.out.println("Main String: \"" + mainStr + "\"");
        System.out.println("Substring: \"" + subStr + "\"");
        System.out.println("Occurrences: " + occurrences);
        System.out.println();
        
        // Program 7: Toggle Case
        System.out.println("7️⃣  TOGGLE CASE");
        System.out.println("---------------");
        String toggleResult = ToggleCase.toggleCaseUsingStringBuilder(demoString);
        System.out.println("Original: \"" + demoString + "\"");
        System.out.println("Toggled: \"" + toggleResult + "\"");
        System.out.println();
        
        // Program 8: Compare Strings
        System.out.println("8️⃣  COMPARE STRINGS");
        System.out.println("-------------------");
        String str1 = "apple";
        String str2 = "banana";
        int comparison = CompareStrings.compareStringsLexicographically(str1, str2);
        String comparisonDesc = CompareStrings.getComparisonDescription(str1, str2);
        System.out.println("String 1: \"" + str1 + "\"");
        System.out.println("String 2: \"" + str2 + "\"");
        System.out.println("Comparison Result: " + comparison);
        System.out.println("Description: " + comparisonDesc);
        System.out.println();
        
        // Program 9: Most Frequent Character
        System.out.println("9️⃣  MOST FREQUENT CHARACTER");
        System.out.println("---------------------------");
        String freqTest = "success";
        char mostFrequent = MostFrequentChar.findMostFrequentCharUsingHashMap(freqTest);
        System.out.println("String: \"" + freqTest + "\"");
        System.out.println("Most Frequent Character: '" + mostFrequent + "'");
        System.out.println();
        
        // Program 10: Remove Specific Character
        System.out.println("🔟 REMOVE SPECIFIC CHARACTER");
        System.out.println("----------------------------");
        String removeTest = "Hello World";
        char charToRemove = 'l';
        String afterRemoval = RemoveSpecificChar.removeCharUsingStringBuilder(removeTest, charToRemove);
        System.out.println("Original: \"" + removeTest + "\"");
        System.out.println("Remove Character: '" + charToRemove + "'");
        System.out.println("Result: \"" + afterRemoval + "\"");
        System.out.println();
        
        // Program 11: Anagrams Check
        System.out.println("1️⃣1️⃣ ANAGRAMS CHECK");
        System.out.println("-------------------");
        String anagram1 = "listen";
        String anagram2 = "silent";
        boolean areAnagrams = AnagramsCheck.areAnagramsUsingSorting(anagram1, anagram2);
        System.out.println("String 1: \"" + anagram1 + "\"");
        System.out.println("String 2: \"" + anagram2 + "\"");
        System.out.println("Are Anagrams: " + (areAnagrams ? "✓ YES" : "✗ NO"));
        System.out.println();
        
        // Program 12: String Replace
        System.out.println("1️⃣2️⃣ STRING REPLACE");
        System.out.println("-------------------");
        String sentence = "Java is amazing. Java is powerful.";
        String oldWord = "Java";
        String newWord = "Python";
        String replaced = StringReplace.replaceWord(sentence, oldWord, newWord);
        System.out.println("Original: \"" + sentence + "\"");
        System.out.println("Replace '" + oldWord + "' with '" + newWord + "':");
        System.out.println("Result: \"" + replaced + "\"");
        
        System.out.println();
        System.out.println("===================================================");
        System.out.println("          ALL STRING PROGRAMS DEMONSTRATED");
        System.out.println("===================================================");
        
        // Summary
        System.out.println("\\n📊 PROGRAM SUMMARY:");
        System.out.println("✅ 1. Count Vowels and Consonants - Character analysis");
        System.out.println("✅ 2. Reverse String - Multiple reversal methods");
        System.out.println("✅ 3. Palindrome Check - Forward/backward reading");
        System.out.println("✅ 4. Remove Duplicates - Unique character extraction");
        System.out.println("✅ 5. Longest Word - Word length analysis");
        System.out.println("✅ 6. Substring Occurrences - Pattern matching");
        System.out.println("✅ 7. Toggle Case - Case conversion");
        System.out.println("✅ 8. Compare Strings - Lexicographical comparison");
        System.out.println("✅ 9. Most Frequent Character - Frequency analysis");
        System.out.println("✅ 10. Remove Specific Character - Targeted removal");
        System.out.println("✅ 11. Anagrams Check - Character rearrangement");
        System.out.println("✅ 12. String Replace - Word substitution");
        
        System.out.println("\\n🎯 All 12 String Manipulation Programs Successfully Implemented!");
    }
}
