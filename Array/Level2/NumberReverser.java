import java.util.Scanner;

public class NumberReverser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number to reverse: ");
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
            System.out.println("Reversed Number: 0");
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
        
        // Create array to store digits
        int[] digits = new int[digitCount];
        
        // Extract digits and store in array
        int index = 0;
        tempNumber = number;
        while (tempNumber > 0) {
            digits[index] = (int)(tempNumber % 10);
            tempNumber = tempNumber / 10;
            index++;
        }
        
        // Display original digits (in extraction order - already reversed)
        System.out.println("\\nDigits extracted (reverse order): ");
        for (int i = 0; i < digitCount; i++) {
            System.out.print(digits[i] + " ");
        }
        System.out.println();
        
        // Display original number digit by digit
        System.out.println("Original number digits (left to right): ");
        for (int i = digitCount - 1; i >= 0; i--) {
            System.out.print(digits[i] + " ");
        }
        System.out.println();
        
        // Create reversed array (though digits are already in reverse order in our extraction)
        int[] reversedDigits = new int[digitCount];
        for (int i = 0; i < digitCount; i++) {
            reversedDigits[i] = digits[i];
        }
        
        // Display reversed number
        System.out.println("\\n=== NUMBER REVERSAL RESULTS ===");
        System.out.println("Original Number: " + originalNumber);
        
        System.out.print("Reversed Number: ");
        if (isNegative) {
            System.out.print("-");
        }
        
        // Construct reversed number from array
        for (int i = 0; i < digitCount; i++) {
            System.out.print(reversedDigits[i]);
        }
        System.out.println();
        
        // Calculate reversed number as a long value
        long reversedNumber = 0;
        for (int i = 0; i < digitCount; i++) {
            reversedNumber = reversedNumber * 10 + reversedDigits[i];
        }
        
        if (isNegative) {
            reversedNumber = -reversedNumber;
        }
        
        System.out.println("Reversed Number (as long): " + reversedNumber);
        
        // Additional analysis
        System.out.println("\\n=== ADDITIONAL ANALYSIS ===");
        System.out.println("Is the number a palindrome? " + (originalNumber == reversedNumber ? "Yes" : "No"));
        
        // Sum of digits
        int digitSum = 0;
        for (int i = 0; i < digitCount; i++) {
            digitSum += digits[i];
        }
        System.out.println("Sum of digits: " + digitSum);
        
        // Product of digits
        int digitProduct = 1;
        for (int i = 0; i < digitCount; i++) {
            digitProduct *= digits[i];
        }
        System.out.println("Product of digits: " + digitProduct);
        
        // Digit frequency
        int[] frequency = new int[10];
        for (int i = 0; i < digitCount; i++) {
            frequency[digits[i]]++;
        }
        
        System.out.println("\\nDigit frequency in the number:");
        for (int i = 0; i <= 9; i++) {
            if (frequency[i] > 0) {
                System.out.println("Digit " + i + ": " + frequency[i] + " times");
            }
        }
        
        scanner.close();
    }
}
