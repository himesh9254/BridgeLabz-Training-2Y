import java.util.Scanner;

public class FactorFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number to find its factors: ");
        int number = scanner.nextInt();
        
        if (number <= 0) {
            System.out.println("Please enter a positive number!");
            scanner.close();
            return;
        }
        
        // Initialize variables for dynamic array
        int maxFactors = 10;
        int[] factors = new int[maxFactors];
        int index = 0;
        
        System.out.println("\nFinding factors of " + number + "...");
        
        // Find factors of the number
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                // Check if we need to expand the array
                if (index == maxFactors) {
                    // Double the array size
                    maxFactors = maxFactors * 2;
                    int[] temp = new int[maxFactors];
                    
                    // Copy existing elements to new array
                    for (int j = 0; j < index; j++) {
                        temp[j] = factors[j];
                    }
                    
                    // Replace old array with new larger array
                    factors = temp;
                    System.out.println("Array expanded to accommodate more factors...");
                }
                
                // Add factor to array
                factors[index] = i;
                index++;
            }
        }
        
        // Display the factors
        System.out.println("\nFactors of " + number + ":");
        System.out.println("==================");
        
        System.out.print("Factors: ");
        for (int i = 0; i < index; i++) {
            System.out.print(factors[i]);
            if (i < index - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        
        System.out.println("Total number of factors: " + index);
        System.out.println("Array capacity used: " + index + "/" + maxFactors);
        
        scanner.close();
    }
}
