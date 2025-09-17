import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DateComparison {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        System.out.println("Date Comparison Program");
        System.out.println("======================");
        System.out.println("Enter two dates to compare (dd/MM/yyyy format)");
        System.out.println();
        
        try {
            // Get first date
            System.out.print("Enter first date: ");
            String firstDateInput = scanner.nextLine();
            LocalDate firstDate = LocalDate.parse(firstDateInput, formatter);
            
            // Get second date
            System.out.print("Enter second date: ");
            String secondDateInput = scanner.nextLine();
            LocalDate secondDate = LocalDate.parse(secondDateInput, formatter);
            
            System.out.println();
            System.out.println("Date Comparison Results:");
            System.out.println("========================");
            System.out.println("First Date:  " + firstDate.format(formatter));
            System.out.println("Second Date: " + secondDate.format(formatter));
            System.out.println();
            
            // Compare the dates using different methods
            if (firstDate.isBefore(secondDate)) {
                System.out.println("✓ First date is BEFORE second date");
                System.out.println("  - isBefore(): " + firstDate.isBefore(secondDate));
                System.out.println("  - isAfter(): " + firstDate.isAfter(secondDate));
                System.out.println("  - isEqual(): " + firstDate.isEqual(secondDate));
                
                long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(firstDate, secondDate);
                System.out.println("  - Days between: " + daysBetween + " days");
                
            } else if (firstDate.isAfter(secondDate)) {
                System.out.println("✓ First date is AFTER second date");
                System.out.println("  - isBefore(): " + firstDate.isBefore(secondDate));
                System.out.println("  - isAfter(): " + firstDate.isAfter(secondDate));
                System.out.println("  - isEqual(): " + firstDate.isEqual(secondDate));
                
                long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(secondDate, firstDate);
                System.out.println("  - Days between: " + daysBetween + " days");
                
            } else if (firstDate.isEqual(secondDate)) {
                System.out.println("✓ Both dates are EQUAL");
                System.out.println("  - isBefore(): " + firstDate.isBefore(secondDate));
                System.out.println("  - isAfter(): " + firstDate.isAfter(secondDate));
                System.out.println("  - isEqual(): " + firstDate.isEqual(secondDate));
                System.out.println("  - Days between: 0 days");
            }
            
            // Additional comparison using compareTo method
            System.out.println();
            System.out.println("Using compareTo() method:");
            int comparisonResult = firstDate.compareTo(secondDate);
            if (comparisonResult < 0) {
                System.out.println("compareTo() result: " + comparisonResult + " (first date is earlier)");
            } else if (comparisonResult > 0) {
                System.out.println("compareTo() result: " + comparisonResult + " (first date is later)");
            } else {
                System.out.println("compareTo() result: " + comparisonResult + " (dates are equal)");
            }
            
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format! Please use dd/MM/yyyy format.");
            System.out.println("Example: 15/03/2024");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        
        scanner.close();
    }
}
