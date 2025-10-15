// 3. Date Format Utility
// An invoice generator must format dates in multiple formats
// Use static interface method to format dates

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

interface DateFormatter {
    static String formatToStandard(LocalDate date) {
        // Format: YYYY-MM-DD
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
    
    static String formatToUS(LocalDate date) {
        // Format: MM/DD/YYYY
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return date.format(formatter);
    }
    
    static String formatToEuropean(LocalDate date) {
        // Format: DD/MM/YYYY
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }
    
    static String formatToLong(LocalDate date) {
        // Format: Month DD, YYYY
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        return date.format(formatter);
    }
    
    static String formatToShort(LocalDate date) {
        // Format: MMM DD, YY
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yy");
        return date.format(formatter);
    }
    
    static String formatCustom(LocalDate date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }
    
    static String formatForInvoice(LocalDate date) {
        // Format: DD-MMM-YYYY (e.g., 15-Oct-2025)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return date.format(formatter);
    }
}

public class DateFormatUtility {
    public static void main(String[] args) {
        System.out.println("=== Invoice Date Formatter ===\n");
        
        LocalDate currentDate = LocalDate.now();
        LocalDate invoiceDate = LocalDate.of(2025, 10, 15);
        LocalDate dueDate = invoiceDate.plusDays(30);
        
        System.out.println("AVAILABLE DATE FORMATS:");
        System.out.println("=".repeat(60));
        
        System.out.println("Standard (ISO):     " + DateFormatter.formatToStandard(invoiceDate));
        System.out.println("US Format:          " + DateFormatter.formatToUS(invoiceDate));
        System.out.println("European Format:    " + DateFormatter.formatToEuropean(invoiceDate));
        System.out.println("Long Format:        " + DateFormatter.formatToLong(invoiceDate));
        System.out.println("Short Format:       " + DateFormatter.formatToShort(invoiceDate));
        System.out.println("Invoice Format:     " + DateFormatter.formatForInvoice(invoiceDate));
        System.out.println("Custom (dd.MM.yy):  " + DateFormatter.formatCustom(invoiceDate, "dd.MM.yy"));
        
        // Invoice example
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SAMPLE INVOICE");
        System.out.println("=".repeat(60));
        System.out.println("Invoice No:         INV-2025-001");
        System.out.println("Invoice Date:       " + DateFormatter.formatForInvoice(invoiceDate));
        System.out.println("Due Date:           " + DateFormatter.formatForInvoice(dueDate));
        System.out.println("Current Date:       " + DateFormatter.formatForInvoice(currentDate));
        System.out.println("-".repeat(60));
        System.out.println("Customer:           Acme Corporation");
        System.out.println("Amount:             $1,500.00");
        System.out.println("Status:             Pending");
        System.out.println("=".repeat(60));
        
        // Multiple format demonstration
        System.out.println("\nMULTI-FORMAT DISPLAY:");
        System.out.println("-".repeat(60));
        System.out.println("US Client:          " + DateFormatter.formatToUS(invoiceDate));
        System.out.println("EU Client:          " + DateFormatter.formatToEuropean(invoiceDate));
        System.out.println("Internal Use:       " + DateFormatter.formatToStandard(invoiceDate));
        System.out.println("Customer-Facing:    " + DateFormatter.formatToLong(invoiceDate));
    }
}
