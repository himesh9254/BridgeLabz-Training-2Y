import java.util.Scanner;

/**
 * Problem 15: Rewrite program 14 using for loop
 * Hint: Take the integer input, check for natural number and determine the factorial using for loop and finally print the result.
 */
public class FactorialFor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a positive integer: ");
        int number = scanner.nextInt();
        
        if (number < 0) {
            System.out.println("Factorial is not defined for negative numbers");
            scanner.close();
            return;
        }
        
        long factorial = 1;
        
        // Calculate factorial using for loop
        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }
        
        System.out.println("Factorial of " + number + " is: " + factorial);
        
        scanner.close();
    }
}
