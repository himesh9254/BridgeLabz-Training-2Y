import java.util.Scanner;

/**
 * Problem 12: Find the sum of n natural numbers using while loop compare the result with the formulae n*(n+1)/2
 * and show the result from both computations was correct
 * Hint: Take the user input number and check whether it's a Natural number
 * If it's a natural number Compute using formulae as well as compute using while loop
 * Compare the two results and print the result
 */
public class NaturalSumWhileCompare {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a natural number: ");
        int n = scanner.nextInt();
        
        if (n <= 0) {
            System.out.println("The number " + n + " is not a natural number");
            scanner.close();
            return;
        }
        
        // Calculate using formula
        int formulaSum = n * (n + 1) / 2;
        
        // Calculate using while loop
        int loopSum = 0;
        int counter = 1;
        while (counter <= n) {
            loopSum += counter;
            counter++;
        }
        
        System.out.println("Sum using formula: " + formulaSum);
        System.out.println("Sum using while loop: " + loopSum);
        
        if (formulaSum == loopSum) {
            System.out.println("Both computations are correct!");
        } else {
            System.out.println("Error in computation!");
        }
        
        scanner.close();
    }
}
