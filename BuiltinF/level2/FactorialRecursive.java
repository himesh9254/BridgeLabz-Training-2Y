import java.util.Scanner;
import java.math.BigInteger;

public class FactorialRecursive {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== Factorial Calculator (Recursive) ===");
        System.out.println("This program calculates the factorial of a number using recursion.");
        System.out.println("Factorial of n (n!) = n × (n-1) × (n-2) × ... × 2 × 1");
        System.out.println();
        
        // Get input from user
        int number = getValidInput();
        
        // Calculate factorial using recursion
        long result = calculateFactorialRecursive(number);
        
        // Display results
        displayResults(number, result);
        
        // Show additional analysis
        showAdditionalAnalysis(number);
        
        scanner.close();
    }
    
    /**
     * Gets valid input from user with error handling
     * @return valid non-negative integer
     */
    public static int getValidInput() {
        while (true) {
            try {
                System.out.print("Enter a non-negative integer: ");
                int number = Integer.parseInt(scanner.nextLine());
                
                if (number < 0) {
                    System.out.println("Factorial is not defined for negative numbers. Please enter a non-negative integer.");
                } else if (number > 20) {
                    System.out.println("Warning: Factorial of numbers > 20 may cause overflow with long data type.");
                    System.out.print("Do you want to continue? (y/n): ");
                    String response = scanner.nextLine().toLowerCase();
                    if (response.startsWith("y")) {
                        return number;
                    }
                } else {
                    return number;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }
    }
    
    /**
     * Calculates factorial using recursion
     * @param n the number to calculate factorial for
     * @return factorial of n
     */
    public static long calculateFactorialRecursive(int n) {
        // Base case
        if (n == 0 || n == 1) {
            return 1;
        }
        
        // Recursive case: n! = n × (n-1)!
        return n * calculateFactorialRecursive(n - 1);
    }
    
    /**
     * Alternative recursive method with step tracking
     * @param n the number to calculate factorial for
     * @return factorial of n
     */
    public static long calculateFactorialWithSteps(int n) {
        System.out.println("\\nRecursive calculation steps:");
        return calculateFactorialRecursiveWithPrint(n, 0);
    }
    
    /**
     * Helper method that shows recursive steps
     * @param n current number
     * @param depth recursion depth for indentation
     * @return factorial result
     */
    private static long calculateFactorialRecursiveWithPrint(int n, int depth) {
        // Create indentation based on recursion depth
        String indent = "  ".repeat(depth);
        
        if (n == 0 || n == 1) {
            System.out.println(indent + "Base case: " + n + "! = 1");
            return 1;
        }
        
        System.out.println(indent + "Calculating: " + n + "! = " + n + " × " + (n-1) + "!");
        long result = n * calculateFactorialRecursiveWithPrint(n - 1, depth + 1);
        System.out.println(indent + "Result: " + n + "! = " + result);
        
        return result;
    }
    
    /**
     * Calculates factorial using iteration (for comparison)
     * @param n the number to calculate factorial for
     * @return factorial of n
     */
    public static long calculateFactorialIterative(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    /**
     * Calculates factorial using BigInteger (for very large numbers)
     * @param n the number to calculate factorial for
     * @return factorial of n as BigInteger
     */
    public static BigInteger calculateFactorialBigInteger(int n) {
        if (n == 0 || n == 1) {
            return BigInteger.ONE;
        }
        
        return BigInteger.valueOf(n).multiply(calculateFactorialBigInteger(n - 1));
    }
    
    /**
     * Displays the factorial calculation results
     * @param number the input number
     * @param result the factorial result
     */
    public static void displayResults(int number, long result) {
        System.out.println();
        System.out.println("=== Results ===");
        System.out.println("Number: " + number);
        System.out.println("Factorial (" + number + "!) = " + result);
        
        // Show mathematical notation
        if (number <= 10) {
            System.out.println("Mathematical expansion:");
            StringBuilder expansion = new StringBuilder();
            for (int i = number; i >= 1; i--) {
                expansion.append(i);
                if (i > 1) {
                    expansion.append(" × ");
                }
            }
            System.out.println(number + "! = " + expansion.toString() + " = " + result);
        }
    }
    
    /**
     * Shows additional analysis and information
     * @param number the input number
     */
    public static void showAdditionalAnalysis(int number) {
        System.out.println();
        System.out.println("=== Additional Analysis ===");
        
        // Show step-by-step recursive calculation
        if (number <= 6) {
            calculateFactorialWithSteps(number);
        }
        
        // Performance comparison
        System.out.println();
        System.out.println("=== Performance Comparison ===");
        comparePerformance(number);
        
        // Show factorial properties
        System.out.println();
        System.out.println("=== Factorial Properties ===");
        showFactorialProperties(number);
        
        // Handle large numbers with BigInteger
        if (number > 20) {
            System.out.println();
            System.out.println("=== Large Number Calculation ===");
            System.out.println("Using BigInteger for accurate large factorial:");
            BigInteger bigResult = calculateFactorialBigInteger(number);
            System.out.println(number + "! = " + bigResult);
            System.out.println("Number of digits: " + bigResult.toString().length());
        }
        
        // Show factorial table
        System.out.println();
        System.out.println("=== Factorial Table ===");
        showFactorialTable();
    }
    
    /**
     * Compares performance between recursive and iterative methods
     * @param number the number to test with
     */
    public static void comparePerformance(int number) {
        if (number > 15) {
            System.out.println("Performance test skipped for large numbers to avoid stack overflow.");
            return;
        }
        
        int iterations = 10000;
        
        // Test recursive method
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            calculateFactorialRecursive(number);
        }
        long recursiveTime = System.nanoTime() - startTime;
        
        // Test iterative method
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            calculateFactorialIterative(number);
        }
        long iterativeTime = System.nanoTime() - startTime;
        
        System.out.printf("Performance (%d iterations):%n", iterations);
        System.out.printf("Recursive method: %d nanoseconds%n", recursiveTime);
        System.out.printf("Iterative method: %d nanoseconds%n", iterativeTime);
        
        if (iterativeTime > 0 && recursiveTime > 0) {
            double ratio = (double) recursiveTime / iterativeTime;
            if (ratio > 1) {
                System.out.printf("Iterative is %.2fx faster than recursive%n", ratio);
            } else {
                System.out.printf("Recursive is %.2fx faster than iterative%n", 1/ratio);
            }
        }
    }
    
    /**
     * Shows mathematical properties of factorials
     * @param number the input number
     */
    public static void showFactorialProperties(int number) {
        System.out.println("Mathematical properties:");
        System.out.println("• 0! = 1 (by definition)");
        System.out.println("• 1! = 1");
        System.out.println("• n! = n × (n-1)! for n > 1");
        System.out.println("• n! grows very rapidly (faster than exponential)");
        
        if (number >= 2) {
            long current = calculateFactorialRecursive(number);
            long previous = calculateFactorialRecursive(number - 1);
            System.out.println("• " + number + "! = " + number + " × " + (number-1) + "! = " + number + " × " + previous + " = " + current);
        }
        
        // Show growth rate
        if (number >= 3) {
            System.out.println();
            System.out.println("Growth rate comparison:");
            for (int i = Math.max(1, number - 2); i <= Math.min(number + 2, 10); i++) {
                long fact = calculateFactorialRecursive(i);
                System.out.println(i + "! = " + fact);
            }
        }
    }
    
    /**
     * Shows a factorial table for reference
     */
    public static void showFactorialTable() {
        System.out.println("Quick reference table:");
        System.out.println("n    n!");
        System.out.println("--------");
        
        for (int i = 0; i <= Math.min(12, 20); i++) {
            long fact = calculateFactorialRecursive(i);
            System.out.printf("%-2d   %d%n", i, fact);
        }
        
        System.out.println();
        System.out.println("Note: Factorials grow extremely fast!");
        System.out.println("      13! = 6,227,020,800 (exceeds int range)");
        System.out.println("      21! exceeds long range, use BigInteger for larger numbers.");
    }
}
