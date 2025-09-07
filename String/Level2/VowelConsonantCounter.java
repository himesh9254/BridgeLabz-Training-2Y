import java.util.Scanner;

public class VowelConsonantCounter {
    
    public static String checkCharacterType(char ch) {
        // Convert to lowercase if uppercase
        if (ch >= 'A' && ch <= 'Z') {
            ch = (char)(ch + 32);
        }
        
        // Check if it's a letter
        if (ch < 'a' || ch > 'z') {
            return "Not a Letter";
        }
        
        // Check if it's a vowel
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            return "Vowel";
        }
        
        return "Consonant";
    }
    
    public static int[] countVowelsConsonants(String text) {
        int vowelCount = 0;
        int consonantCount = 0;
        
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            String type = checkCharacterType(ch);
            
            if (type.equals("Vowel")) {
                vowelCount++;
            } else if (type.equals("Consonant")) {
                consonantCount++;
            }
        }
        
        return new int[]{vowelCount, consonantCount};
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String text = scanner.nextLine();
        
        int[] counts = countVowelsConsonants(text);
        
        System.out.println("\n--- Vowel and Consonant Count ---");
        System.out.println("String: \"" + text + "\"");
        System.out.println("Number of Vowels: " + counts[0]);
        System.out.println("Number of Consonants: " + counts[1]);
        System.out.println("Total Letters: " + (counts[0] + counts[1]));
        
        scanner.close();
    }
}
