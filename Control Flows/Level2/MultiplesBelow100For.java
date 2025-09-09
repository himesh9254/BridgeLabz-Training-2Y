import java.util.Scanner;

/**
 * Problem 11: Create a program to find all the multiples of a number taken as user input below 100
 * Hint: Get the input value for a variable named number. Check the number is a positive integer and less than 100.
 * Run a for loop backward: from i = 100 to i = 1.
 * Inside the loop, check if i perfectly divide the number. If true, print the number and continue the loop.
 */
public class MultiplesBelow100For {
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
        
        // For loop backward from 99 to 1 (checking multiples below 100)
        for (int i = 99; i >= 1; i--) {
            if (i % number == 0) {
                System.out.println(i);
            }
        }
        
        scanner.close();
    }
}
