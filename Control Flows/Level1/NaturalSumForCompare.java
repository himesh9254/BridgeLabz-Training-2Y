import java.util.Scanner;

/**
 * Problem 13: Rewrite the program number 12 with the for loop instead of a while loop to find the sum of n Natural Numbers
 * Hint: Take the user input number and check whether it's a Natural number
 * If it's a natural number Compute using formulae as well as compute using for loop
 * Compare the two results and print the result
 */
public class NaturalSumForCompare {
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
        
        // Calculate using for loop
        int loopSum = 0;
        for (int i = 1; i <= n; i++) {
            loopSum += i;
        }
        
        System.out.println("Sum using formula: " + formulaSum);
        System.out.println("Sum using for loop: " + loopSum);
        
        if (formulaSum == loopSum) {
            System.out.println("Both computations are correct!");
        } else {
            System.out.println("Error in computation!");
        }
        
        scanner.close();
    }
}
