import java.util.Scanner;

/**
 * Problem 11: Write a program DayOfWeek that takes a date as input and prints the day of the week
 * that the date falls on. Your program should take three inputs: m (month), d (day), and y (year).
 * For m use 1 for January, 2 for February, and so forth.
 * For output print 0 for Sunday, 1 for Monday, 2 for Tuesday, and so forth.
 * 
 * Use the following formulas for the Gregorian calendar:
 * y0 = y - (14 - m) / 12
 * x = y0 + y0/4 - y0/100 + y0/400
 * m0 = m + 12 * ((14 - m) / 12) - 2
 * d0 = (d + x + 31*m0 / 12) mod 7
 */
public class DayOfWeek {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter month (1-12): ");
        int m = scanner.nextInt();
        System.out.print("Enter day (1-31): ");
        int d = scanner.nextInt();
        System.out.print("Enter year: ");
        int y = scanner.nextInt();
        
        // Validate input
        if (m < 1 || m > 12 || d < 1 || d > 31 || y < 1582) {
            System.out.println("Please enter a valid date (Gregorian calendar starts from 1582)");
            scanner.close();
            return;
        }
        
        // Apply the formulas for Gregorian calendar
        // y0 = y - (14 - m) / 12
        int y0 = y - (14 - m) / 12;
        
        // x = y0 + y0/4 - y0/100 + y0/400
        int x = y0 + y0/4 - y0/100 + y0/400;
        
        // m0 = m + 12 * ((14 - m) / 12) - 2
        int m0 = m + 12 * ((14 - m) / 12) - 2;
        
        // d0 = (d + x + 31*m0 / 12) mod 7
        int d0 = (d + x + 31*m0 / 12) % 7;
        
        // Array to convert day number to day name
        String[] dayNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        
        // Display results
        System.out.println("\nDate: " + d + "/" + m + "/" + y);
        System.out.println("Day of the week: " + d0 + " (" + dayNames[d0] + ")");
        
        // Show calculation steps
        System.out.println("\nCalculation steps:");
        System.out.println("y0 = " + y + " - (14 - " + m + ") / 12 = " + y0);
        System.out.println("x = " + y0 + " + " + y0 + "/4 - " + y0 + "/100 + " + y0 + "/400 = " + x);
        System.out.println("m0 = " + m + " + 12 * ((14 - " + m + ") / 12) - 2 = " + m0);
        System.out.println("d0 = (" + d + " + " + x + " + 31*" + m0 + " / 12) mod 7 = " + d0);
        
        scanner.close();
    }
}
