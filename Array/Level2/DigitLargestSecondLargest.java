import java.util.Scanner;

public class DigitLargestSecondLargest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        long number = scanner.nextLong();
        long originalNumber = number;
        
        // Array to store digits with initial size
        int maxDigits = 10;
        int[] digits = new int[maxDigits];
        int index = 0;
        
        // Handle special case of 0
        if (number == 0) {
            digits[0] = 0;
            index = 1;
        } else {
            // Handle negative numbers by converting to positive
            if (number < 0) {
                number = -number;
            }
            
            // Extract digits and store in array
            while (number != 0 && index < maxDigits) {
                int lastDigit = (int)(number % 10);
                digits[index] = lastDigit;
                number = number / 10;
                index++;
                
                // Break if we reach maximum capacity
                if (index == maxDigits) {
                    System.out.println("Warning: Number has more than " + maxDigits + " digits. Only first " + maxDigits + " digits processed.");
                    break;
                }
            }
        }
        
        // Display extracted digits
        System.out.println("\\nExtracted digits (in reverse order): ");
        for (int i = 0; i < index; i++) {
            System.out.print(digits[i] + " ");
        }
        System.out.println();
        
        // Find largest and second largest digits
        int largest = 0;
        int secondLargest = 0;
        
        // Initialize largest and second largest
        for (int i = 0; i < index; i++) {
            if (digits[i] > largest) {
                secondLargest = largest;
                largest = digits[i];
            } else if (digits[i] > secondLargest && digits[i] != largest) {
                secondLargest = digits[i];
            }
        }
        
        // Display results
        System.out.println("\\n=== DIGIT ANALYSIS ===");
        System.out.println("Original Number: " + originalNumber);
        System.out.println("Total digits processed: " + index);
        System.out.println("Digits in array: ");
        for (int i = 0; i < index; i++) {
            System.out.print(digits[i] + " ");
        }
        System.out.println();
        
        System.out.println("\\nLargest digit: " + largest);
        if (secondLargest > 0) {
            System.out.println("Second largest digit: " + secondLargest);
        } else {
            System.out.println("Second largest digit: Not found (all digits are the same or only one unique digit)");
        }
        
        // Additional statistics
        int sum = 0;
        for (int i = 0; i < index; i++) {
            sum += digits[i];
        }
        System.out.println("Sum of all digits: " + sum);
        System.out.printf("Average of digits: %.2f%n", (double)sum / index);
        
        scanner.close();
    }
}
