import java.util.Scanner;

/**
 * Problem 8: Create a program to check if a number taken from the user is a Harshad Number
 * Hint:
 * a. A Harshad number is an integer which is divisible by the sum of its digits
 *    For example, 21 which is perfectly divided by 3 (sum of digits: 2 + 1)
 * b. Get an integer input for the number variable
 * c. Create an integer variable sum with initial value 0
 * d. Create a while loop to access each digit of the number
 * e. Inside the loop, add each digit of the number to sum
 * f. Check if the number is perfectly divisible by the sum
 * g. If the number is divisible by the sum, print Harshad Number. Otherwise, print Not a Harshad Number
 */
public class HarshadChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a positive integer: ");
        int number = scanner.nextInt();
        
        if (number <= 0) {
            System.out.println("Please enter a positive integer");
            scanner.close();
            return;
        }
        
        // Store original number and create sum variable with initial value 0
        int originalNumber = number;
        int sum = 0;
        
        // Create while loop to access each digit of the number
        while (number != 0) {
            // Add each digit to sum
            int digit = number % 10;
            sum += digit;
            
            // Remove the last digit
            number = number / 10;
        }
        
        // Check if the number is perfectly divisible by the sum
        if (originalNumber % sum == 0) {
            System.out.println(originalNumber + " is a Harshad Number");
            System.out.println("Verification: " + originalNumber + " ÷ " + sum + " = " + (originalNumber / sum));
        } else {
            System.out.println(originalNumber + " is not a Harshad Number");
            System.out.println("Verification: " + originalNumber + " ÷ " + sum + " = " + 
                             String.format("%.2f", (double)originalNumber / sum) + " (not a whole number)");
        }
        
        scanner.close();
    }
}
