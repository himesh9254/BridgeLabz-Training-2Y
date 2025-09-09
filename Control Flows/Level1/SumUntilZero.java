import java.util.Scanner;

/**
 * Problem 10: Find the sum of numbers until the user enters 0
 * Hint: Create a variable total of type double initialize to 0.0. Also, create a variable to store the double value the user enters
 * Use the while loop to check if the user entered is 0
 * If the user entered value is not 0 then inside the while block add user entered value to the total and ask the user to input again
 * The loop will continue till the user enters zero and outside the loop display the total value
 */
public class SumUntilZero {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        double total = 0.0;
        double userInput;
        
        System.out.println("Enter numbers to sum (enter 0 to stop):");
        
        // Get first input
        System.out.print("Enter a number: ");
        userInput = scanner.nextDouble();
        
        // While loop to continue until user enters 0
        while (userInput != 0) {
            total += userInput;
            System.out.print("Enter a number: ");
            userInput = scanner.nextDouble();
        }
        
        System.out.println("Total sum: " + total);
        
        scanner.close();
    }
}
