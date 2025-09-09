import java.util.Scanner;

/**
 * Problem 18: Create a program to find the multiplication table of a number entered by the user from 6 to 9
 * Hint: Take integer input and store it in the variable number
 * Using a for loop, find the multiplication table of number from 6 to 9 and print it in the format number * i = ___
 */
public class MultiplicationTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        System.out.println("Multiplication table of " + number + " from 6 to 9:");
        
        // For loop to generate multiplication table from 6 to 9
        for (int i = 6; i <= 9; i++) {
            int result = number * i;
            System.out.println(number + " * " + i + " = " + result);
        }
        
        scanner.close();
    }
}
