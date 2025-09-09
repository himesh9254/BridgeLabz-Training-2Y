import java.util.Scanner;

/**
 * Problem 4: Check for natural number and write the sum of n natural numbers
 * Hint: A Natural Number is a positive integer (1,2,3, etc) sometimes with the inclusion of 0
 * A sum of n natural numbers is n * (n+1) / 2
 * I/P => number
 * O/P => If the number is a positive integer then the output is
 *         The sum of ___ natural numbers is ___
 *         Otherwise 
 *         The number ___ is not a natural number
 */
public class NaturalNumberSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        // Check if the number is a natural number (positive integer)
        if (number > 0) {
            // Calculate sum using the formula n * (n + 1) / 2
            int sum = number * (number + 1) / 2;
            System.out.println("The sum of " + number + " natural numbers is " + sum);
        } else {
            System.out.println("The number " + number + " is not a natural number");
        }
        
        scanner.close();
    }
}
