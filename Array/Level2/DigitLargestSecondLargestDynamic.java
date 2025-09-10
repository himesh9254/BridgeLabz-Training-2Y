import java.util.Scanner;

public class DigitLargestSecondLargestDynamic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        long number = scanner.nextLong();
        long originalNumber = number;
        
        // Array to store digits with dynamic expansion capability
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
            
            // Extract digits and store in array with dynamic expansion
            while (number != 0) {
                // Check if we need to expand the array
                if (index == maxDigits) {
                    // Increase array size by 10
                    maxDigits += 10;
                    int[] temp = new int[maxDigits];
                    
                    // Copy existing digits to new array
                    for (int i = 0; i < index; i++) {
                        temp[i] = digits[i];
                    }
                    
                    // Replace old array with new expanded array
                    digits = temp;
                    System.out.println("Array expanded to accommodate more digits (new size: " + maxDigits + ")");
                }
                
                // Extract last digit and add to array
                int lastDigit = (int)(number % 10);
                digits[index] = lastDigit;
                number = number / 10;
                index++;
            }
        }
        
        // Display extracted digits
        System.out.println("\\nExtracted digits (in reverse order): ");
        for (int i = 0; i < index; i++) {
            System.out.print(digits[i] + " ");
        }
        System.out.println();
        
        // Display digits in original order
        System.out.println("Digits in original order: ");
        for (int i = index - 1; i >= 0; i--) {
            System.out.print(digits[i] + " ");
        }
        System.out.println();
        
        // Find largest and second largest digits
        int largest = -1;
        int secondLargest = -1;
        
        // Find largest and second largest using improved algorithm
        for (int i = 0; i < index; i++) {
            if (digits[i] > largest) {
                secondLargest = largest;
                largest = digits[i];
            } else if (digits[i] > secondLargest && digits[i] != largest) {
                secondLargest = digits[i];
            }
        }
        
        // Display results
        System.out.println("\\n=== DYNAMIC DIGIT ANALYSIS ===");
        System.out.println("Original Number: " + originalNumber);
        System.out.println("Total digits processed: " + index);
        System.out.println("Array capacity used: " + index + "/" + maxDigits);
        
        System.out.println("\\nAll digits in array: ");
        for (int i = 0; i < index; i++) {
            System.out.print(digits[i] + " ");
        }
        System.out.println();
        
        System.out.println("\\nLargest digit: " + largest);
        if (secondLargest >= 0) {
            System.out.println("Second largest digit: " + secondLargest);
        } else {
            System.out.println("Second largest digit: Not found (all digits are the same)");
        }
        
        // Additional comprehensive statistics
        System.out.println("\\n=== COMPREHENSIVE STATISTICS ===");
        
        // Sum and average
        int sum = 0;
        for (int i = 0; i < index; i++) {
            sum += digits[i];
        }
        System.out.println("Sum of all digits: " + sum);
        System.out.printf("Average of digits: %.2f%n", (double)sum / index);
        
        // Frequency count
        int[] frequency = new int[10];
        for (int i = 0; i < index; i++) {
            frequency[digits[i]]++;
        }
        
        System.out.println("\\nDigit Frequency:");
        for (int i = 0; i <= 9; i++) {
            if (frequency[i] > 0) {
                System.out.println("Digit " + i + ": " + frequency[i] + " times");
            }
        }
        
        // Find smallest digit
        int smallest = 9;
        for (int i = 0; i < index; i++) {
            if (digits[i] < smallest) {
                smallest = digits[i];
            }
        }
        System.out.println("\\nSmallest digit: " + smallest);
        System.out.println("Range (largest - smallest): " + (largest - smallest));
        
        scanner.close();
    }
}
