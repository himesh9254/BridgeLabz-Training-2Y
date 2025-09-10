import java.util.Scanner;

public class OddEvenSeparator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a natural number: ");
        int number = scanner.nextInt();
        
        // Check if the number is a natural number
        if (number <= 0) {
            System.out.println("Error: Please enter a natural number (positive integer)!");
            scanner.close();
            return;
        }
        
        // Create arrays for odd and even numbers
        int[] evenNumbers = new int[number / 2 + 1];
        int[] oddNumbers = new int[number / 2 + 1];
        
        // Index variables for odd and even arrays
        int evenIndex = 0;
        int oddIndex = 0;
        
        // Separate odd and even numbers from 1 to number
        for (int i = 1; i <= number; i++) {
            if (i % 2 == 0) {
                evenNumbers[evenIndex] = i;
                evenIndex++;
            } else {
                oddNumbers[oddIndex] = i;
                oddIndex++;
            }
        }
        
        // Display results
        System.out.println("\nOdd and Even Number Separation (1 to " + number + "):");
        System.out.println("=====================================================");
        
        // Print even numbers
        System.out.print("Even Numbers: ");
        if (evenIndex == 0) {
            System.out.println("None");
        } else {
            for (int i = 0; i < evenIndex; i++) {
                System.out.print(evenNumbers[i]);
                if (i < evenIndex - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
        
        // Print odd numbers
        System.out.print("Odd Numbers: ");
        if (oddIndex == 0) {
            System.out.println("None");
        } else {
            for (int i = 0; i < oddIndex; i++) {
                System.out.print(oddNumbers[i]);
                if (i < oddIndex - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
        
        System.out.println("\nCount of Even Numbers: " + evenIndex);
        System.out.println("Count of Odd Numbers: " + oddIndex);
        
        scanner.close();
    }
}
