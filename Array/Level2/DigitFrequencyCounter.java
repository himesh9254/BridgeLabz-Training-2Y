import java.util.Scanner;

public class DigitFrequencyCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        long number = scanner.nextLong();
        long originalNumber = number;
        boolean isNegative = false;
        
        // Handle negative numbers
        if (number < 0) {
            isNegative = true;
            number = -number;
        }
        
        // Handle special case of 0
        if (number == 0) {
            System.out.println("Original Number: " + originalNumber);
            System.out.println("Digit frequency:");
            System.out.println("Digit 0: 1 time");
            scanner.close();
            return;
        }
        
        // Count digits in the number
        long tempNumber = number;
        int digitCount = 0;
        while (tempNumber > 0) {
            digitCount++;
            tempNumber = tempNumber / 10;
        }
        
        System.out.println("Number of digits: " + digitCount);
        
        // Create array to store all digits
        int[] digits = new int[digitCount];
        
        // Extract digits and store in array
        int index = 0;
        tempNumber = number;
        while (tempNumber > 0) {
            digits[index] = (int)(tempNumber % 10);
            tempNumber = tempNumber / 10;
            index++;
        }
        
        // Display extracted digits
        System.out.println("\\nExtracted digits (in reverse order): ");
        for (int i = 0; i < digitCount; i++) {
            System.out.print(digits[i] + " ");
        }
        System.out.println();
        
        // Display digits in original order
        System.out.println("Digits in original order: ");
        for (int i = digitCount - 1; i >= 0; i--) {
            System.out.print(digits[i] + " ");
        }
        System.out.println();
        
        // Create frequency array for digits 0-9
        int[] frequency = new int[10];
        
        // Calculate frequency of each digit
        for (int i = 0; i < digitCount; i++) {
            frequency[digits[i]]++;
        }
        
        // Display results
        System.out.println("\\n=== DIGIT FREQUENCY ANALYSIS ===");
        System.out.println("Original Number: " + originalNumber);
        System.out.println("Total digits: " + digitCount);
        
        System.out.println("\\nFrequency of each digit:");
        System.out.println("========================");
        
        boolean hasDigits = false;
        for (int digit = 0; digit <= 9; digit++) {
            if (frequency[digit] > 0) {
                hasDigits = true;
                System.out.println("Digit " + digit + ": " + frequency[digit] + 
                                 (frequency[digit] == 1 ? " time" : " times"));
            }
        }
        
        if (!hasDigits) {
            System.out.println("No digits found.");
        }
        
        // Additional statistics
        System.out.println("\\n=== ADDITIONAL STATISTICS ===");
        
        // Most frequent digit(s)
        int maxFrequency = 0;
        for (int i = 0; i <= 9; i++) {
            if (frequency[i] > maxFrequency) {
                maxFrequency = frequency[i];
            }
        }
        
        System.out.print("Most frequent digit(s): ");
        boolean first = true;
        for (int i = 0; i <= 9; i++) {
            if (frequency[i] == maxFrequency && maxFrequency > 0) {
                if (!first) System.out.print(", ");
                System.out.print(i + " (" + maxFrequency + " times)");
                first = false;
            }
        }
        System.out.println();
        
        // Least frequent digit(s) (among present digits)
        int minFrequency = Integer.MAX_VALUE;
        for (int i = 0; i <= 9; i++) {
            if (frequency[i] > 0 && frequency[i] < minFrequency) {
                minFrequency = frequency[i];
            }
        }
        
        if (minFrequency != Integer.MAX_VALUE) {
            System.out.print("Least frequent digit(s): ");
            first = true;
            for (int i = 0; i <= 9; i++) {
                if (frequency[i] == minFrequency) {
                    if (!first) System.out.print(", ");
                    System.out.print(i + " (" + minFrequency + " times)");
                    first = false;
                }
            }
            System.out.println();
        }
        
        // Count unique digits
        int uniqueDigits = 0;
        for (int i = 0; i <= 9; i++) {
            if (frequency[i] > 0) {
                uniqueDigits++;
            }
        }
        System.out.println("Number of unique digits: " + uniqueDigits);
        
        // Missing digits
        System.out.print("Missing digits: ");
        first = true;
        for (int i = 0; i <= 9; i++) {
            if (frequency[i] == 0) {
                if (!first) System.out.print(", ");
                System.out.print(i);
                first = false;
            }
        }
        if (first) {
            System.out.print("None (all digits 0-9 are present)");
        }
        System.out.println();
        
        // Check if number has repeated digits
        boolean hasRepeatedDigits = false;
        for (int i = 0; i <= 9; i++) {
            if (frequency[i] > 1) {
                hasRepeatedDigits = true;
                break;
            }
        }
        System.out.println("Has repeated digits: " + (hasRepeatedDigits ? "Yes" : "No"));
        
        // Display frequency table
        System.out.println("\\n=== FREQUENCY TABLE ===");
        System.out.println("Digit | Frequency | Bar Chart");
        System.out.println("------|-----------|----------");
        for (int i = 0; i <= 9; i++) {
            System.out.printf("  %d   |     %d     | ", i, frequency[i]);
            for (int j = 0; j < frequency[i]; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        
        scanner.close();
    }
}
