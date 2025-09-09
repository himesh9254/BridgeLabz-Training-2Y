import java.util.Scanner;

/**
 * Problem 2: Rewrite program 1 to determine Leap Year with single if condition using logical and && and or || operators
 */
public class LeapYearSingle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a year: ");
        int year = scanner.nextInt();
        
        // Check if year is valid for Gregorian calendar
        if (year < 1582) {
            System.out.println("Please enter a year from 1582 onwards (Gregorian calendar)");
            scanner.close();
            return;
        }
        
        // Single if condition with logical operators
        // Leap year: divisible by 4 AND (not divisible by 100 OR divisible by 400)
        if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
            System.out.println("Year " + year + " is a Leap Year");
        } else {
            System.out.println("Year " + year + " is not a Leap Year");
        }
        
        scanner.close();
    }
}
