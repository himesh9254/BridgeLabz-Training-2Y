import java.util.Scanner;

/**
 * Problem 11: Rewrite the program 10 to find the sum until the user enters 0 or a negative number using while loop and break statement
 * Hint: Use infinite while loop as in while (true)
 * Take the user entry and check if the user entered 0 or a negative number to break the loop using break;
 */
public class SumUntilZeroOrNegative {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        double total = 0.0;
        double userInput;
        
        System.out.println("Enter numbers to sum (enter 0 or negative number to stop):");
        
        // Infinite while loop
        while (true) {
            System.out.print("Enter a number: ");
            userInput = scanner.nextDouble();
            
            // Check if user entered 0 or negative number
            if (userInput <= 0) {
                break;
            }
            
            total += userInput;
        }
        
        System.out.println("Total sum: " + total);
        
        scanner.close();
    }
}
