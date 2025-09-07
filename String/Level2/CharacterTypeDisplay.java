import java.util.Scanner;

public class CharacterTypeDisplay {
    
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
    
    public static String[][] analyzeCharacters(String text) {
        String[][] result = new String[text.length()][2];
        
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            result[i][0] = String.valueOf(ch);
            result[i][1] = checkCharacterType(ch);
        }
        
        return result;
    }
    
    public static void displayTable(String[][] data) {
        System.out.println("Character\tType");
        System.out.println("---------------------");
        
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i][0] + "\t\t" + data[i][1]);
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String text = scanner.nextLine();
        
        String[][] characterAnalysis = analyzeCharacters(text);
        
        System.out.println("\n--- Character Type Analysis ---");
        System.out.println("String: \"" + text + "\"");
        System.out.println();
        displayTable(characterAnalysis);
        
        scanner.close();
    }
}
