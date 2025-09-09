import java.util.Scanner;

/**
 * Problem 14: Rewrite the above program to find the power of a number using a while loop
 * Hint: Get integer input for two variables named number and power.
 * Create a result variable with an initial value of 1.
 * Create a temp variable counter and initialize to zero. Use the while loop till counter == power.
 * In each iteration of the loop, multiply the result by the number and assign the value to the result.
 * Also, increment the counter. Finally, print the result
 */
public class PowerWhile {
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
        int counter = 0;
        
        // While loop to calculate power
        while (counter < power) {
            result *= number;
            counter++;
        }
        
        System.out.println(number + " raised to the power " + power + " is: " + result);
        
        scanner.close();
    }
}
