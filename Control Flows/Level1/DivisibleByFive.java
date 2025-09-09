import java.util.Scanner;

/**
 * Problem 1: Check if a number is divisible by 5
 * I/P => number
 * O/P => Is the number ___ divisible by 5? ___
 */
public class DivisibleByFive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        // Check if number is divisible by 5
        boolean isDivisible = (number % 5 == 0);
        
        System.out.println("Is the number " + number + " divisible by 5? " + isDivisible);
        
        scanner.close();
    }
}
