import java.util.Scanner;

public class PrimeChecker {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== Prime Number Checker ===");
        System.out.println("This program checks if a number is prime.");
        System.out.println();
        
        // Get number from user
        int number = getValidPositiveInteger("Enter a positive integer: ");
        
        // Check if prime
        boolean isPrime = isPrimeNumber(number);
        
        // Display result
        displayPrimeResult(number, isPrime);
        
        // Show additional information
        showAdditionalInfo(number);
        
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
     * Checks if a number is prime using optimized algorithm
     * @param number the number to check
     * @return true if prime, false otherwise
     */
    public static boolean isPrimeNumber(int number) {
        // Handle special cases
        if (number <= 1) {
            return false; // Numbers <= 1 are not prime
        }
        if (number == 2) {
            return true; // 2 is the only even prime number
        }
        if (number % 2 == 0) {
            return false; // Even numbers > 2 are not prime
        }
        
        // Check odd divisors up to square root
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false; // Found a divisor, not prime
            }
        }
        
        return true; // No divisors found, it's prime
    }
    
    /**
     * Alternative method - Basic prime check (less efficient)
     * @param number the number to check
     * @return true if prime, false otherwise
     */
    public static boolean isPrimeBasic(int number) {
        if (number <= 1) {
            return false;
        }
        
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Displays the prime checking result
     * @param number the number that was checked
     * @param isPrime whether the number is prime
     */
    public static void displayPrimeResult(int number, boolean isPrime) {
        System.out.println();
        System.out.println("=== Result ===");
        System.out.println("Number: " + number);
        
        if (isPrime) {
            System.out.println("✓ " + number + " is a PRIME number!");
            System.out.println("  A prime number has exactly two factors: 1 and itself.");
        } else {
            System.out.println("✗ " + number + " is NOT a prime number.");
            System.out.println("  It has factors other than 1 and itself.");
        }
    }
    
    /**
     * Shows additional information about the number
     * @param number the number to analyze
     */
    public static void showAdditionalInfo(int number) {
        System.out.println();
        System.out.println("=== Additional Information ===");
        
        // Show factors
        System.out.println("Factors of " + number + ": " + getFactorsAsString(number));
        
        // Show if it's a special case
        if (number == 1) {
            System.out.println("Note: 1 is neither prime nor composite.");
        } else if (number == 2) {
            System.out.println("Note: 2 is the only even prime number.");
        } else if (number % 2 == 0) {
            System.out.println("Note: " + number + " is even, so it's divisible by 2.");
        }
        
        // Performance comparison
        System.out.println();
        System.out.println("=== Algorithm Performance ===");
        long startTime, endTime;
        
        // Test optimized method
        startTime = System.nanoTime();
        boolean result1 = isPrimeNumber(number);
        endTime = System.nanoTime();
        long optimizedTime = endTime - startTime;
        
        // Test basic method (only for smaller numbers to avoid long wait)
        if (number < 10000) {
            startTime = System.nanoTime();
            boolean result2 = isPrimeBasic(number);
            endTime = System.nanoTime();
            long basicTime = endTime - startTime;
            
            System.out.println("Optimized algorithm: " + optimizedTime + " nanoseconds");
            System.out.println("Basic algorithm: " + basicTime + " nanoseconds");
            System.out.println("Performance improvement: " + 
                (basicTime > 0 ? (basicTime / (double)optimizedTime) + "x faster" : "N/A"));
        } else {
            System.out.println("Optimized algorithm: " + optimizedTime + " nanoseconds");
            System.out.println("(Basic algorithm skipped for large numbers)");
        }
    }
    
    /**
     * Gets all factors of a number as a formatted string
     * @param number the number to find factors for
     * @return string representation of factors
     */
    public static String getFactorsAsString(int number) {
        StringBuilder factors = new StringBuilder();
        
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                if (factors.length() > 0) {
                    factors.append(", ");
                }
                factors.append(i);
            }
        }
        
        return factors.toString();
    }
}
