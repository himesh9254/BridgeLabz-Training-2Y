import java.util.Scanner;

/**
 * Problem 7: Create a program to find the factors of a number taken as user input
 * Hint: Get the input value for a variable named number and check if it is a positive integer.
 * Run a for loop from i = 1 to i < number. In each iteration of the loop, check if the number
 * is perfectly divisible by i. If true, print the value of i.
 */
public class FactorsFor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a positive integer: ");
        int number = scanner.nextInt();
        
        if (number <= 0) {
            System.out.println("Please enter a positive integer");
            scanner.close();
            return;
        }
        
        System.out.println("Factors of " + number + " are:");
        
        // For loop to find factors
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                System.out.println(i);
            }
        }
        
        scanner.close();
    }
}
