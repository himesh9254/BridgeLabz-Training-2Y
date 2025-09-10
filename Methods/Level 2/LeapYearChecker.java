import java.util.Scanner;

class LeapYearChecker {
    
    public static boolean isLeapYear(int year) {
        if (year < 1582) {
            return false;
        }
        
        if (year % 4 != 0) {
            return false;
        }
        
        if (year % 100 == 0) {
            return year % 400 == 0;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter a year: ");
        int year = input.nextInt();
        
        if (year < 1582) {
            System.out.println("This program only works for years >= 1582 (Gregorian calendar).");
        } else {
            boolean leap = isLeapYear(year);
            
            if (leap) {
                System.out.println(year + " is a Leap Year.");
            } else {
                System.out.println(year + " is not a Leap Year.");
            }
        }
        
        input.close();
    }
}
