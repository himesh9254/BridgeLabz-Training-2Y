import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatting {
    public static void main(String[] args) {
        // Get current date
        LocalDate currentDate = LocalDate.now();
        
        // Define different date formatters
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter format3 = DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy");
        
        System.out.println("Current Date in Different Formats");
        System.out.println("=================================");
        System.out.println("Current Date (default format): " + currentDate);
        System.out.println();
        
        // Display date in three different formats
        System.out.println("Format 1 (dd/MM/yyyy): " + currentDate.format(format1));
        System.out.println("Format 2 (yyyy-MM-dd): " + currentDate.format(format2));
        System.out.println("Format 3 (EEE, MMM dd, yyyy): " + currentDate.format(format3));
        
        System.out.println();
        System.out.println("Additional Date Formatting Examples:");
        System.out.println("===================================");
        
        // Additional formatting examples
        DateTimeFormatter longFormat = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
        DateTimeFormatter shortFormat = DateTimeFormatter.ofPattern("MMM dd, yy");
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        DateTimeFormatter yearMonthFormat = DateTimeFormatter.ofPattern("yyyy/MM");
        
        System.out.println("Long format: " + currentDate.format(longFormat));
        System.out.println("Short format: " + currentDate.format(shortFormat));
        System.out.println("Custom format: " + currentDate.format(customFormat));
        System.out.println("Year-Month format: " + currentDate.format(yearMonthFormat));
        
        // Demonstrating pattern symbols
        System.out.println();
        System.out.println("Pattern Symbols Explanation:");
        System.out.println("============================");
        System.out.println("dd = Day of month (01-31)");
        System.out.println("MM = Month number (01-12)");
        System.out.println("MMM = Month short name (Jan, Feb, etc.)");
        System.out.println("MMMM = Month full name (January, February, etc.)");
        System.out.println("yyyy = 4-digit year");
        System.out.println("yy = 2-digit year");
        System.out.println("EEE = Day of week short name (Mon, Tue, etc.)");
        System.out.println("EEEE = Day of week full name (Monday, Tuesday, etc.)");
    }
}
