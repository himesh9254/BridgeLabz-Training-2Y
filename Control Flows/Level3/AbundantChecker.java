import java.util.Scanner;

/**
 * Problem 9: Create a program to check if a number is an Abundant Number
 * Hint:
 * a. An abundant number is an integer in which the sum of all the divisors of the number
 *    is greater than the number itself. For example:
 *    Divisors of 12: 1, 2, 3, 4, 6
 *    Sum of divisors: 1 + 2 + 3 + 4 + 6 = 16 > 12
 * b. Get an integer input for the number variable
 * c. Create an integer variable sum with initial value 0
 * d. Run a for loop from i = 1 to i < number
 * e. Inside the loop, check if number is divisible by i
 * f. If true, add i to sum
 * g. Outside the loop Check if sum is greater than number
 * h. If the sum is greater than the number, print Abundant Number. Otherwise, print Not an Abundant Number
 */
public class AbundantChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a positive integer: ");
        int number = scanner.nextInt();
        
        if (number <= 0) {
            System.out.println("Please enter a positive integer");
            scanner.close();
            return;
        }
        
        // Create sum variable with initial value 0
        int sum = 0;
        
        System.out.print("Divisors of " + number + ": ");
        
        // Run for loop from i = 1 to i < number
        for (int i = 1; i < number; i++) {
            // Check if number is divisible by i
            if (number % i == 0) {
                System.out.print(i + " ");
                // Add i to sum
                sum += i;
            }
        }
        
        System.out.println();
        System.out.println("Sum of divisors: " + sum);
        
        // Check if sum is greater than number
        if (sum > number) {
            System.out.println(number + " is an Abundant Number");
            System.out.println("Verification: " + sum + " > " + number);
        } else {
            System.out.println(number + " is not an Abundant Number");
            System.out.println("Verification: " + sum + " ≤ " + number);
        }
        
        scanner.close();
    }
}
