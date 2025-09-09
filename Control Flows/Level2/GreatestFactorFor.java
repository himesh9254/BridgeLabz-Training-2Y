import java.util.Scanner;

/**
 * Problem 9: Create a program to print the greatest factor of a number beside itself using a loop
 * Hint: Get an integer input and assign it to the number variable. As well as define a greatestFactor variable and assign it to 1
 * Create a for loop that runs from last but one till 1 as in i = number - 1 to i = 1.
 * Inside the loop, check if the number is perfectly divisible by i then assign i to greatestFactor variable and break the loop.
 * Display the greatestFactor variable outside the loop
 */
public class GreatestFactorFor {
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
        
        // For loop from number-1 down to 1
        for (int i = number - 1; i >= 1; i--) {
            if (number % i == 0) {
                greatestFactor = i;
                break; // Found the greatest factor, exit loop
            }
        }
        
        System.out.println("Greatest factor of " + number + " beside itself is: " + greatestFactor);
        
        scanner.close();
    }
}
