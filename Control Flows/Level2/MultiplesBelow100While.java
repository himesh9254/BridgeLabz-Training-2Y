import java.util.Scanner;

/**
 * Problem 13: Rewrite the program to find all the multiples of a number below 100 using while loop
 * Hint: Get the input value for a variable named number. Check the number is a positive integer and less than 100.
 * Create a counter variable and assign counter = number - 1; Use a while till the counter is > 1
 * Inside the loop, check if the counter perfectly divides the number. If true, print the number and continue the loop.
 */
public class MultiplesBelow100While {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a positive integer less than 100: ");
        int number = scanner.nextInt();
        
        if (number <= 0 || number >= 100) {
            System.out.println("Please enter a positive integer less than 100");
            scanner.close();
            return;
        }
        
        System.out.println("Multiples of " + number + " below 100 are:");
        
        // While loop from 99 down to 1
        int counter = 99;
        while (counter >= 1) {
            if (counter % number == 0) {
                System.out.println(counter);
            }
            counter--;
        }
        
        scanner.close();
    }
}
