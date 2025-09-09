import java.util.Scanner;

/**
 * Problem 4: Write a program FizzBuzz, take a number as user input, and check for a positive integer.
 * If positive integer, loop and print the number, but for multiples of 3 print "Fizz" instead of the number,
 * for multiples of 5 print "Buzz", and for multiples of both print "FizzBuzz".
 * Hint: Take the user input number, check for a positive integer, and use for loop to display
 */
public class FizzBuzzFor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a positive integer: ");
        int number = scanner.nextInt();
        
        if (number <= 0) {
            System.out.println("Please enter a positive integer");
            scanner.close();
            return;
        }
        
        System.out.println("FizzBuzz sequence from 1 to " + number + ":");
        
        // For loop to implement FizzBuzz
        for (int i = 1; i <= number; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
        
        scanner.close();
    }
}
