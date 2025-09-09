import java.util.Scanner;

/**
 * Problem 6: Create a program to count the number of digits in an integer
 * Hint:
 * a. Get an integer input for the number variable
 * b. Create an integer variable count with value 0
 * c. Use a loop to iterate until number is not equal to 0
 * d. Remove the last digit from number in each iteration
 * e. Increase count by 1 in each iteration
 * f. Finally display the count to show the number of digits
 */
public class DigitCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        // Handle special case for 0
        if (number == 0) {
            System.out.println("Number of digits in " + number + " is: 1");
            scanner.close();
            return;
        }
        
        // Make number positive for counting (handle negative numbers)
        int originalNumber = number;
        number = Math.abs(number);
        
        // Create count variable with initial value 0
        int count = 0;
        
        // Use loop to iterate until number is not equal to 0
        while (number != 0) {
            // Remove the last digit from number
            number = number / 10;
            
            // Increase count by 1 in each iteration
            count++;
        }
        
        // Display the count
        System.out.println("Number of digits in " + originalNumber + " is: " + count);
        
        scanner.close();
    }
}
