import java.util.Scanner;

/**
 * Problem 14: Find the factorial of an integer entered by the user
 * Hint: For example, the factorial of 4 is 1 * 2 * 3 * 4 which is 24.
 * Take an integer input from the user and assign it to the variable. Check the user has entered a positive integer.
 * Using a while loop, compute the factorial.
 * Print the factorial at the end.
 */
public class FactorialWhile {
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
        int counter = 1;
        
        // Calculate factorial using while loop
        while (counter <= number) {
            factorial *= counter;
            counter++;
        }
        
        System.out.println("Factorial of " + number + " is: " + factorial);
        
        scanner.close();
    }
}
