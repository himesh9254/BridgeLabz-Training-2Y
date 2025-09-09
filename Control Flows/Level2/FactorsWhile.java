import java.util.Scanner;

/**
 * Problem 8: Rewrite the above program 7 to find the factors of a number using the while loop
 * Hint: Get the input value for a variable named number and check if it is a positive integer.
 * Create a counter variable and run the while loop till the counter is less than the user input number.
 * In each iteration of the loop, check if the number is perfectly divisible by the counter.
 * If true, print the value of the counter.
 */
public class FactorsWhile {
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
        
        // While loop to find factors
        int counter = 1;
        while (counter <= number) {
            if (number % counter == 0) {
                System.out.println(counter);
            }
            counter++;
        }
        
        scanner.close();
    }
}
