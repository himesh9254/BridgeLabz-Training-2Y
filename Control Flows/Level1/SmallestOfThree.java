import java.util.Scanner;

/**
 * Problem 2: Check if the first is the smallest of the 3 numbers
 * I/P => number1, number2, number3
 * O/P => Is the first number the smallest? ____
 */
public class SmallestOfThree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the first number: ");
        int number1 = scanner.nextInt();
        System.out.print("Enter the second number: ");
        int number2 = scanner.nextInt();
        System.out.print("Enter the third number: ");
        int number3 = scanner.nextInt();
        
        // Check if the first number is the smallest
        boolean isFirstSmallest = (number1 <= number2) && (number1 <= number3);
        
        System.out.println("Is the first number the smallest? " + isFirstSmallest);
        
        scanner.close();
    }
}
