import java.util.Scanner;

/**
 * Problem 1: Write a LeapYear program that takes a year as input and outputs the Year is a Leap Year or not a Leap Year
 * Hint: 
 * a. The LeapYear program only works for year >= 1582, corresponding to a year in the Gregorian calendar
 * b. Further, the Leap Year is a Year divisible by 4 and not 100 unless it is divisible by 400
 * c. Write code having multiple if else statements based on conditions provided above
 */
public class LeapYearMultiple {
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
        
        boolean isLeapYear = false;
        
        // Multiple if-else conditions to check leap year
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    isLeapYear = true; // Divisible by 400
                } else {
                    isLeapYear = false; // Divisible by 100 but not 400
                }
            } else {
                isLeapYear = true; // Divisible by 4 but not 100
            }
        } else {
            isLeapYear = false; // Not divisible by 4
        }
        
        if (isLeapYear) {
            System.out.println("Year " + year + " is a Leap Year");
        } else {
            System.out.println("Year " + year + " is not a Leap Year");
        }
        
        scanner.close();
    }
}
