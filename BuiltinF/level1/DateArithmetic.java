import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DateArithmetic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        System.out.println("Date Arithmetic Program");
        System.out.println("======================");
        System.out.print("Enter a date (dd/MM/yyyy format): ");
        String inputDate = scanner.nextLine();
        
        try {
            // Parse the input date
            LocalDate date = LocalDate.parse(inputDate, formatter);
            System.out.println("Original Date: " + date.format(formatter));
            
            // Step 1: Add 7 days, 1 month, and 2 years
            LocalDate modifiedDate = date
                .plusDays(7)      // Add 7 days
                .plusMonths(1)    // Add 1 month
                .plusYears(2);    // Add 2 years
            
            System.out.println("After adding 7 days, 1 month, and 2 years: " + modifiedDate.format(formatter));
            
            // Step 2: Subtract 3 weeks from the result
            LocalDate finalDate = modifiedDate.minusWeeks(3);  // Subtract 3 weeks
            System.out.println("After subtracting 3 weeks: " + finalDate.format(formatter));
            
            // Show detailed breakdown
            System.out.println("\nDetailed Breakdown:");
            System.out.println("===================");
            System.out.println("1. Original Date: " + date.format(formatter));
            System.out.println("2. After adding 7 days: " + date.plusDays(7).format(formatter));
            System.out.println("3. After adding 1 month: " + date.plusDays(7).plusMonths(1).format(formatter));
            System.out.println("4. After adding 2 years: " + date.plusDays(7).plusMonths(1).plusYears(2).format(formatter));
            System.out.println("5. After subtracting 3 weeks: " + finalDate.format(formatter));
            
        } catch (Exception e) {
            System.out.println("Invalid date format! Please use dd/MM/yyyy format.");
        }
        
        scanner.close();
    }
}
