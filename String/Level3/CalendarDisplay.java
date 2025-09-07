import java.util.Scanner;

public class CalendarDisplay {
    
    // Method to get month name
    public static String getMonthName(int month) {
        String[] months = {"January", "February", "March", "April", "May", "June",
                          "July", "August", "September", "October", "November", "December"};
        return months[month - 1];
    }
    
    // Method to check if year is leap year
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    
    // Method to get number of days in month
    public static int getDaysInMonth(int month, int year) {
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        if (month == 2 && isLeapYear(year)) {
            return 29;
        }
        
        return days[month - 1];
    }
    
    // Method to get first day of month using Gregorian calendar algorithm
    public static int getFirstDayOfMonth(int day, int month, int year) {
        int y0 = year - (14 - month) / 12;
        int x = y0 + y0/4 - y0/100 + y0/400;
        int m0 = month + 12 * ((14 - month) / 12) - 2;
        int d0 = (day + x + (31 * m0) / 12) % 7;
        
        return d0;
    }
    
    // Method to display calendar
    public static void displayCalendar(int month, int year) {
        String monthName = getMonthName(month);
        int daysInMonth = getDaysInMonth(month, year);
        int firstDay = getFirstDayOfMonth(1, month, year);
        
        System.out.println("\n--- Calendar for " + monthName + " " + year + " ---");
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");
        System.out.println("---------------------------");
        
        // Print spaces for the first day
        for (int i = 0; i < firstDay; i++) {
            System.out.print("    ");
        }
        
        // Print days of the month
        for (int day = 1; day <= daysInMonth; day++) {
            System.out.printf("%3d ", day);
            
            // Move to next line after Saturday
            if ((day + firstDay) % 7 == 0) {
                System.out.println();
            }
        }
        
        System.out.println("\n");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter month (1-12): ");
        int month = scanner.nextInt();
        
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        
        // Validate input
        if (month < 1 || month > 12) {
            System.out.println("Invalid month! Please enter a value between 1 and 12.");
            scanner.close();
            return;
        }
        
        if (year < 1) {
            System.out.println("Invalid year! Please enter a positive year.");
            scanner.close();
            return;
        }
        
        // Display calendar
        displayCalendar(month, year);
        
        // Additional information
        System.out.println("Additional Information:");
        System.out.println("Month: " + getMonthName(month));
        System.out.println("Year: " + year);
        System.out.println("Days in month: " + getDaysInMonth(month, year));
        System.out.println("Is leap year: " + (isLeapYear(year) ? "Yes" : "No"));
        
        scanner.close();
    }
}
