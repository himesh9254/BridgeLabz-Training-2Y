import java.util.Scanner;

/**
 * Problem 12: Create a program to find the power of a number
 * Hint: Get integer input for two variables - number and power and check for positive integer
 * Create a result variable with an initial value of 1.
 * Run a for loop from i = 1 to i <= power. In each iteration of the loop, multiply the result by the number
 * and assign the value to the result. Finally, print the result
 */
public class PowerFor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the base number: ");
        int number = scanner.nextInt();
        System.out.print("Enter the power: ");
        int power = scanner.nextInt();
        
        if (power < 0) {
            System.out.println("Please enter a non-negative power");
            scanner.close();
            return;
        }
        
        long result = 1;
        
        // For loop to calculate power
        for (int i = 1; i <= power; i++) {
            result *= number;
        }
        
        System.out.println(number + " raised to the power " + power + " is: " + result);
        
        scanner.close();
    }
}
