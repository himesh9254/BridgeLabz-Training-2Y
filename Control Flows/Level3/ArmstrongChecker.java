import java.util.Scanner;

/**
 * Problem 5: Create a program to check if a number is armstrong or not
 * Hint:
 * a. Armstrong Number is a number whose Sum of cubes of each digit results in the original number
 *    e.g. 153 = 1^3 + 5^3 + 3^3
 * b. Get an integer input and store it in the number variable and define sum variable, initialize it to zero
 *    and originalNumber variable and assign it to input number variable
 * c. Use the while loop till the originalNumber is not equal to zero
 * d. In the while loop find each digit which is the reminder of the modulus operation number % 10
 *    Find the cube of the number and add it to the sum variable
 * e. Again in while loop find the quotient of the number using the division operation number/10
 *    and assign it to the original number. This removes the last digit of the original number.
 * f. Finally check if the number and the sum are the same, if same its an Armstrong number else not
 */
public class ArmstrongChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        // Store original number and initialize sum
        int originalNumber = number;
        int sum = 0;
        
        // Use while loop till originalNumber is not equal to zero
        while (originalNumber != 0) {
            // Find each digit (remainder of modulus operation)
            int digit = originalNumber % 10;
            
            // Find the cube of the digit and add to sum
            sum += digit * digit * digit;
            
            // Remove the last digit (quotient of division by 10)
            originalNumber = originalNumber / 10;
        }
        
        // Check if the number and sum are the same
        if (number == sum) {
            System.out.println(number + " is an Armstrong number");
            System.out.println("Verification: Sum of cubes of digits = " + sum);
        } else {
            System.out.println(number + " is not an Armstrong number");
            System.out.println("Sum of cubes of digits = " + sum + " (not equal to " + number + ")");
        }
        
        scanner.close();
    }
}
