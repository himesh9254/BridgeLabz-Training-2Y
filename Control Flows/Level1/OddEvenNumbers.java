import java.util.Scanner;

/**
 * Problem 16: Create a program to print odd and even numbers between 1 to the number entered by the user
 * Hint: Get an integer input from the user, assign to a variable number and check for Natural Number
 * Using a for loop, iterate from 1 to the number
 * In each iteration of the loop, print the number is odd or even number
 */
public class OddEvenNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a natural number: ");
        int number = scanner.nextInt();
        
        if (number <= 0) {
            System.out.println("Please enter a natural number (positive integer)");
            scanner.close();
            return;
        }
        
        System.out.println("Numbers from 1 to " + number + ":");
        
        // For loop to iterate from 1 to number
        for (int i = 1; i <= number; i++) {
            if (i % 2 == 0) {
                System.out.println(i + " is even number");
            } else {
                System.out.println(i + " is odd number");
            }
        }
        
        scanner.close();
    }
}
