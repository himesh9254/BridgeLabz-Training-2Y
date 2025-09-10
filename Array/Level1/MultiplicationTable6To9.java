import java.util.Scanner;

public class MultiplicationTable6To9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number to generate multiplication table from 6 to 9: ");
        int number = scanner.nextInt();
        
        // Create array to store multiplication results for 6 to 9
        int[] multiplicationResult = new int[4]; // Array size 4 for numbers 6, 7, 8, 9
        
        // Generate multiplication table from 6 to 9
        for (int i = 6; i <= 9; i++) {
            multiplicationResult[i - 6] = number * i; // Store at index (i-6)
        }
        
        // Display the multiplication table
        System.out.println("\nMultiplication Table of " + number + " from 6 to 9:");
        System.out.println("==========================================");
        
        for (int i = 0; i < multiplicationResult.length; i++) {
            int multiplier = i + 6; // Convert index back to multiplier (6, 7, 8, 9)
            System.out.println(number + " * " + multiplier + " = " + multiplicationResult[i]);
        }
        
        scanner.close();
    }
}
