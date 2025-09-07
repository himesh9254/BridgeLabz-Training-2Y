import java.util.Scanner;

public class FrequencyNestedLoops {
    
    // Method to find frequency using nested loops
    public static String[] findFrequencyNestedLoops(String text) {
        char[] chars = text.toCharArray();
        int[] frequency = new int[chars.length];
        
        // Find frequency using nested loops
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') { // Skip already processed characters
                frequency[i] = 1;
                
                for (int j = i + 1; j < chars.length; j++) {
                    if (chars[i] == chars[j]) {
                        frequency[i]++;
                        chars[j] = '0'; // Mark as processed
                    }
                }
            }
        }
        
        // Count unique characters
        int uniqueCount = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') {
                uniqueCount++;
            }
        }
        
        // Create result array
        String[] result = new String[uniqueCount];
        int index = 0;
        
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') {
                result[index] = "'" + chars[i] + "': " + frequency[i];
                index++;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String text = scanner.nextLine();
        
        String[] charFreq = findFrequencyNestedLoops(text);
        
        System.out.println("\n--- Character Frequency Using Nested Loops ---");
        System.out.println("String: \"" + text + "\"");
        System.out.println("Character frequencies:");
        
        for (int i = 0; i < charFreq.length; i++) {
            System.out.println(charFreq[i]);
        }
        
        scanner.close();
    }
}
