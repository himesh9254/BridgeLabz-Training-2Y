import java.util.Scanner;

/**
 * Problem 10: Rewrite the above program to print the greatest factor of a number beside itself using a while loop
 * Hint: Get an integer input and assign it to the number variable. As well as define a greatestFactor variable and assign it to 1
 * Create a variable counter and assign counter = number - 1; Use the while loop till the counter is equal to 1.
 * Inside the loop, check if the number is perfectly divisible by the counter then assign the counter to greatestFactor variable and break the loop.
 * Display the greatestFactor variable outside the loop
 */
public class GreatestFactorWhile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a positive integer: ");
        int number = scanner.nextInt();
        
        if (number <= 0) {
            System.out.println("Please enter a positive integer");
            scanner.close();
            return;
        }
        
        int greatestFactor = 1;
        int counter = number - 1;
        
        // While loop from number-1 down to 1
        while (counter >= 1) {
            if (number % counter == 0) {
                greatestFactor = counter;
                break; // Found the greatest factor, exit loop
            }
            counter--;
        }
        
        System.out.println("Greatest factor of " + number + " beside itself is: " + greatestFactor);
        
        scanner.close();
    }
}
