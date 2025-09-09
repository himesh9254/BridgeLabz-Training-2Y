import java.util.Scanner;

/**
 * Problem 4: Write a Program to check if the given number is a prime number or not
 * Hint:
 * a. A number that can be divided exactly only by itself and 1 are Prime Numbers
 * b. Prime Numbers checks are done for numbers greater than 1
 * c. Loop through all the numbers from 2 to the user input number and check if the reminder is zero
 * d. If the reminder is zero break out from the loop as the number is divisible by some other number
 * e. Use the isPrime boolean variable to store the result
 */
public class PrimeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        // Prime numbers check is done for numbers greater than 1
        if (number <= 1) {
            System.out.println(number + " is not a prime number");
            scanner.close();
            return;
        }
        
        boolean isPrime = true; // Assume the number is prime initially
        
        // Loop through all numbers from 2 to number-1
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                isPrime = false; // Found a divisor, not prime
                break; // Break out from the loop
            }
        }
        
        // Display result
        if (isPrime) {
            System.out.println(number + " is a prime number");
        } else {
            System.out.println(number + " is not a prime number");
        }
        
        scanner.close();
    }
}
