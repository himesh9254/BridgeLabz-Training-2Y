import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number to generate multiplication table: ");
        int number = scanner.nextInt();
        
        // Create array to store multiplication results
        int[] multiplicationTable = new int[10];
        
        // Generate multiplication table from 1 to 10
        for (int i = 1; i <= 10; i++) {
            multiplicationTable[i - 1] = number * i;
        }
        
        // Display the multiplication table
        System.out.println("\nMultiplication Table of " + number + ":");
        System.out.println("============================");
        
        for (int i = 0; i < multiplicationTable.length; i++) {
            System.out.println(number + " * " + (i + 1) + " = " + multiplicationTable[i]);
        }
        
        scanner.close();
    }
}
