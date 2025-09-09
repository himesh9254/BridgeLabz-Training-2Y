import java.util.Scanner;

/**
 * Problem 6: Check whether a number is positive, negative, or zero
 * Hint: Get integer input from the user and store it in the number variable.
 * If the number is positive, print positive.
 * If the number is negative, print negative.
 * If the number is zero, print zero.
 */
public class PositiveNegativeZero {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        // Check if the number is positive, negative, or zero
        if (number > 0) {
            System.out.println("positive");
        } else if (number < 0) {
            System.out.println("negative");
        } else {
            System.out.println("zero");
        }
        
        scanner.close();
    }
}
