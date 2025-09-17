import java.util.Scanner;

public class FibonacciGenerator {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== Fibonacci Sequence Generator ===");
        System.out.println("This program generates the Fibonacci sequence.");
        System.out.println();
        
        // Get number of terms from user
        int terms = getValidPositiveInteger("Enter the number of terms to generate: ");
        
        // Generate and display Fibonacci sequence
        generateAndDisplayFibonacci(terms);
        
        // Show additional information
        showAdditionalInfo(terms);
        
        scanner.close();
    }
    
    /**
     * Gets a valid positive integer from user
     * @param prompt the message to display
     * @return valid positive integer
     */
    public static int getValidPositiveInteger(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int number = Integer.parseInt(scanner.nextLine());
                if (number > 0) {
                    return number;
                } else {
                    System.out.println("Please enter a positive integer (greater than 0).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }
    }
    
    /**
     * Generates and displays Fibonacci sequence using iterative method
     * @param terms number of terms to generate
     */
    public static void generateAndDisplayFibonacci(int terms) {
        System.out.println();
        System.out.println("=== Fibonacci Sequence (Iterative Method) ===");
        System.out.println("First " + terms + " terms:");
        
        if (terms >= 1) {
            long first = 0, second = 1;
            
            // Handle first term
            System.out.print("0");
            
            // Handle second term if needed
            if (terms > 1) {
                System.out.print(", 1");
            }
            
            // Generate remaining terms
            for (int i = 3; i <= terms; i++) {
                long next = first + second;
                System.out.print(", " + next);
                
                // Update for next iteration
                first = second;
                second = next;
            }
            
            System.out.println(); // New line after sequence
        }
    }
    
    /**
     * Calculates nth Fibonacci number using recursion
     * @param n the position in sequence (0-indexed)
     * @return the nth Fibonacci number
     */
    public static long fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }
    
    /**
     * Calculates nth Fibonacci number using iterative approach
     * @param n the position in sequence (0-indexed)
     * @return the nth Fibonacci number
     */
    public static long fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }
        
        long first = 0, second = 1;
        for (int i = 2; i <= n; i++) {
            long temp = first + second;
            first = second;
            second = temp;
        }
        return second;
    }
    
    /**
     * Generates Fibonacci sequence and returns as array
     * @param terms number of terms to generate
     * @return array containing Fibonacci sequence
     */
    public static long[] generateFibonacciArray(int terms) {
        long[] fibonacci = new long[terms];
        
        if (terms >= 1) {
            fibonacci[0] = 0;
        }
        if (terms >= 2) {
            fibonacci[1] = 1;
        }
        
        for (int i = 2; i < terms; i++) {
            fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
        }
        
        return fibonacci;
    }
    
    /**
     * Shows additional information about Fibonacci sequence
     * @param terms number of terms generated
     */
    public static void showAdditionalInfo(int terms) {
        System.out.println();
        System.out.println("=== Additional Information ===");
        
        // Show sequence with positions
        System.out.println("Sequence with positions:");
        long[] fibArray = generateFibonacciArray(terms);
        
        for (int i = 0; i < Math.min(terms, 10); i++) { // Show first 10 positions
            System.out.println("F(" + i + ") = " + fibArray[i]);
        }
        
        if (terms > 10) {
            System.out.println("... (showing first 10 terms only)");
        }
        
        // Show mathematical properties
        System.out.println();
        System.out.println("=== Mathematical Properties ===");
        System.out.println("• Each number is the sum of the two preceding numbers");
        System.out.println("• The sequence starts with 0 and 1");
        System.out.println("• Formula: F(n) = F(n-1) + F(n-2)");
        System.out.println("• Base cases: F(0) = 0, F(1) = 1");
        
        // Show ratios (Golden Ratio approximation)
        if (terms >= 3) {
            System.out.println();
            System.out.println("=== Golden Ratio Approximation ===");
            System.out.println("Ratios of consecutive Fibonacci numbers approach φ (phi) ≈ 1.618033988749...");
            
            for (int i = 1; i < Math.min(terms-1, 6); i++) {
                if (fibArray[i] != 0) {
                    double ratio = (double)fibArray[i+1] / fibArray[i];
                    System.out.printf("F(%d)/F(%d) = %.2f/%.2f = %.10f%n", 
                        i+1, i, (double)fibArray[i+1], (double)fibArray[i], ratio);
                }
            }
        }
        
        // Performance comparison (for small terms only)
        if (terms <= 15) {
            System.out.println();
            System.out.println("=== Performance Comparison ===");
            comparePerformance(terms);
        }
        
        // Show sum of sequence
        long sum = 0;
        for (long num : fibArray) {
            sum += num;
        }
        System.out.println();
        System.out.println("Sum of first " + terms + " Fibonacci numbers: " + sum);
    }
    
    /**
     * Compares performance between recursive and iterative methods
     * @param terms number of terms to test
     */
    public static void comparePerformance(int terms) {
        // Test iterative method
        long startTime = System.nanoTime();
        generateFibonacciArray(terms);
        long endTime = System.nanoTime();
        long iterativeTime = endTime - startTime;
        
        // Test recursive method (only for small numbers)
        startTime = System.nanoTime();
        for (int i = 0; i < terms; i++) {
            fibonacciRecursive(i);
        }
        endTime = System.nanoTime();
        long recursiveTime = endTime - startTime;
        
        System.out.println("Iterative method: " + iterativeTime + " nanoseconds");
        System.out.println("Recursive method: " + recursiveTime + " nanoseconds");
        
        if (recursiveTime > 0 && iterativeTime > 0) {
            System.out.printf("Iterative is %.2fx faster than recursive%n", 
                (double)recursiveTime / iterativeTime);
        }
        
        System.out.println("Note: Recursive method becomes very slow for large numbers!");
    }
}
