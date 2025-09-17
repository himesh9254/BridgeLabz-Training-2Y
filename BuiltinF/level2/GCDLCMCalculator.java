import java.util.Scanner;

public class GCDLCMCalculator {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== GCD and LCM Calculator ===");
        System.out.println("This program calculates the Greatest Common Divisor (GCD) and");
        System.out.println("Least Common Multiple (LCM) of two numbers using modular functions.");
        System.out.println();
        
        // Get two numbers from user
        int[] numbers = getTwoNumbers();
        int num1 = numbers[0];
        int num2 = numbers[1];
        
        // Calculate GCD and LCM
        int gcd = calculateGCD(num1, num2);
        int lcm = calculateLCM(num1, num2);
        
        // Display results
        displayResults(num1, num2, gcd, lcm);
        
        // Show additional analysis
        showAdditionalAnalysis(num1, num2, gcd, lcm);
        
        scanner.close();
    }
    
    /**
     * Gets two positive integers from user
     * @return array containing two positive integers
     */
    public static int[] getTwoNumbers() {
        int[] numbers = new int[2];
        
        numbers[0] = getValidPositiveInteger("Enter the first positive integer: ");
        numbers[1] = getValidPositiveInteger("Enter the second positive integer: ");
        
        return numbers;
    }
    
    /**
     * Gets a valid positive integer from user with error handling
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
     * Calculates GCD using Euclidean Algorithm (recursive)
     * @param a first number
     * @param b second number
     * @return GCD of a and b
     */
    public static int calculateGCD(int a, int b) {
        return calculateGCDRecursive(a, b);
    }
    
    /**
     * Calculates GCD using recursive Euclidean algorithm
     * @param a first number
     * @param b second number
     * @return GCD of a and b
     */
    public static int calculateGCDRecursive(int a, int b) {
        // Base case: if b is 0, GCD is a
        if (b == 0) {
            return a;
        }
        
        // Recursive case: GCD(a, b) = GCD(b, a % b)
        return calculateGCDRecursive(b, a % b);
    }
    
    /**
     * Calculates GCD using iterative Euclidean algorithm
     * @param a first number
     * @param b second number
     * @return GCD of a and b
     */
    public static int calculateGCDIterative(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    /**
     * Calculates LCM using the relationship: LCM(a,b) = (a * b) / GCD(a,b)
     * @param a first number
     * @param b second number
     * @return LCM of a and b
     */
    public static int calculateLCM(int a, int b) {
        // Use the mathematical relationship: LCM(a,b) = (a * b) / GCD(a,b)
        int gcd = calculateGCD(a, b);
        return (a * b) / gcd;
    }
    
    /**
     * Alternative LCM calculation using step-by-step method
     * @param a first number
     * @param b second number
     * @return LCM of a and b
     */
    public static int calculateLCMAlternative(int a, int b) {
        int max = Math.max(a, b);
        int lcm = max;
        
        while (lcm % a != 0 || lcm % b != 0) {
            lcm += max;
        }
        
        return lcm;
    }
    
    /**
     * Calculates GCD with step tracking for educational purposes
     * @param a first number
     * @param b second number
     * @return GCD of a and b
     */
    public static int calculateGCDWithSteps(int a, int b) {
        System.out.println("\\nGCD calculation using Euclidean Algorithm:");
        System.out.println("Step-by-step process:");
        
        int originalA = a, originalB = b;
        int step = 1;
        
        while (b != 0) {
            int quotient = a / b;
            int remainder = a % b;
            
            System.out.printf("Step %d: %d = %d × %d + %d%n", step, a, b, quotient, remainder);
            
            a = b;
            b = remainder;
            step++;
        }
        
        System.out.println("GCD(" + originalA + ", " + originalB + ") = " + a);
        return a;
    }
    
    /**
     * Displays the calculation results
     * @param num1 first number
     * @param num2 second number  
     * @param gcd calculated GCD
     * @param lcm calculated LCM
     */
    public static void displayResults(int num1, int num2, int gcd, int lcm) {
        System.out.println();
        System.out.println("=== Results ===");
        System.out.println("Numbers: " + num1 + " and " + num2);
        System.out.println("GCD (Greatest Common Divisor): " + gcd);
        System.out.println("LCM (Least Common Multiple): " + lcm);
        
        // Verify the relationship
        System.out.println();
        System.out.println("Verification:");
        System.out.println("GCD × LCM = " + gcd + " × " + lcm + " = " + (gcd * lcm));
        System.out.println("num1 × num2 = " + num1 + " × " + num2 + " = " + (num1 * num2));
        System.out.println("Relationship verified: " + (gcd * lcm == num1 * num2 ? "✓" : "✗"));
    }
    
    /**
     * Shows additional analysis and information
     * @param num1 first number
     * @param num2 second number
     * @param gcd calculated GCD
     * @param lcm calculated LCM
     */
    public static void showAdditionalAnalysis(int num1, int num2, int gcd, int lcm) {
        System.out.println();
        System.out.println("=== Additional Analysis ===");
        
        // Show step-by-step GCD calculation
        calculateGCDWithSteps(num1, num2);
        
        // Show different calculation methods
        System.out.println();
        System.out.println("=== Different Methods Comparison ===");
        
        int gcdRecursive = calculateGCDRecursive(num1, num2);
        int gcdIterative = calculateGCDIterative(num1, num2);
        int lcmAlternative = calculateLCMAlternative(num1, num2);
        
        System.out.println("GCD (Recursive): " + gcdRecursive);
        System.out.println("GCD (Iterative): " + gcdIterative);
        System.out.println("LCM (Formula): " + lcm);
        System.out.println("LCM (Step-by-step): " + lcmAlternative);
        
        // Show mathematical properties
        System.out.println();
        System.out.println("=== Mathematical Properties ===");
        showMathematicalProperties(num1, num2, gcd, lcm);
        
        // Show factors
        System.out.println();
        System.out.println("=== Factor Analysis ===");
        showFactorAnalysis(num1, num2, gcd, lcm);
        
        // Performance comparison
        System.out.println();
        System.out.println("=== Performance Comparison ===");
        comparePerformance(num1, num2);
        
        // Show practical applications
        System.out.println();
        System.out.println("=== Practical Applications ===");
        showPracticalApplications(num1, num2, gcd, lcm);
    }
    
    /**
     * Shows mathematical properties of GCD and LCM
     * @param num1 first number
     * @param num2 second number
     * @param gcd calculated GCD
     * @param lcm calculated LCM
     */
    public static void showMathematicalProperties(int num1, int num2, int gcd, int lcm) {
        System.out.println("Key properties:");
        System.out.println("• GCD(a,b) × LCM(a,b) = a × b");
        System.out.println("• GCD(a,b) ≤ min(a,b)");
        System.out.println("• LCM(a,b) ≥ max(a,b)");
        System.out.println("• If GCD(a,b) = 1, then a and b are coprime");
        
        System.out.println();
        System.out.println("For your numbers:");
        System.out.println("• GCD(" + num1 + "," + num2 + ") = " + gcd + " ≤ " + Math.min(num1, num2) + " ✓");
        System.out.println("• LCM(" + num1 + "," + num2 + ") = " + lcm + " ≥ " + Math.max(num1, num2) + " ✓");
        
        if (gcd == 1) {
            System.out.println("• " + num1 + " and " + num2 + " are coprime (relatively prime)");
        } else {
            System.out.println("• " + num1 + " and " + num2 + " share common factor(s)");
        }
    }
    
    /**
     * Shows factor analysis
     * @param num1 first number
     * @param num2 second number
     * @param gcd calculated GCD
     * @param lcm calculated LCM
     */
    public static void showFactorAnalysis(int num1, int num2, int gcd, int lcm) {
        System.out.println("Common factors of " + num1 + " and " + num2 + ":");
        
        StringBuilder commonFactors = new StringBuilder();
        for (int i = 1; i <= gcd; i++) {
            if (num1 % i == 0 && num2 % i == 0) {
                if (commonFactors.length() > 0) {
                    commonFactors.append(", ");
                }
                commonFactors.append(i);
            }
        }
        System.out.println(commonFactors.toString());
        System.out.println("Greatest common factor: " + gcd);
        
        // Check divisibility
        System.out.println();
        System.out.println("Divisibility check:");
        System.out.println(num1 + " ÷ " + gcd + " = " + (num1 / gcd));
        System.out.println(num2 + " ÷ " + gcd + " = " + (num2 / gcd));
        System.out.println("LCM ÷ " + num1 + " = " + (lcm / num1));
        System.out.println("LCM ÷ " + num2 + " = " + (lcm / num2));
    }
    
    /**
     * Compares performance of different algorithms
     * @param num1 first number
     * @param num2 second number
     */
    public static void comparePerformance(int num1, int num2) {
        int iterations = 100000;
        
        // Test recursive GCD
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            calculateGCDRecursive(num1, num2);
        }
        long recursiveTime = System.nanoTime() - startTime;
        
        // Test iterative GCD
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            calculateGCDIterative(num1, num2);
        }
        long iterativeTime = System.nanoTime() - startTime;
        
        // Test alternative LCM
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            calculateLCMAlternative(num1, num2);
        }
        long alternativeLCMTime = System.nanoTime() - startTime;
        
        // Test formula-based LCM
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            calculateLCM(num1, num2);
        }
        long formulaLCMTime = System.nanoTime() - startTime;
        
        System.out.printf("Performance (%d iterations):%n", iterations);
        System.out.printf("GCD Recursive: %d nanoseconds%n", recursiveTime);
        System.out.printf("GCD Iterative: %d nanoseconds%n", iterativeTime);
        System.out.printf("LCM Formula: %d nanoseconds%n", formulaLCMTime);
        System.out.printf("LCM Alternative: %d nanoseconds%n", alternativeLCMTime);
        
        System.out.println();
        System.out.println("Efficiency ranking:");
        if (iterativeTime <= recursiveTime) {
            System.out.println("1. Iterative GCD (most efficient)");
            System.out.println("2. Recursive GCD");
        } else {
            System.out.println("1. Recursive GCD (most efficient)");
            System.out.println("2. Iterative GCD");
        }
        
        if (formulaLCMTime <= alternativeLCMTime) {
            System.out.println("3. Formula-based LCM");
            System.out.println("4. Step-by-step LCM");
        } else {
            System.out.println("3. Step-by-step LCM");
            System.out.println("4. Formula-based LCM");
        }
    }
    
    /**
     * Shows practical applications of GCD and LCM
     * @param num1 first number
     * @param num2 second number
     * @param gcd calculated GCD
     * @param lcm calculated LCM
     */
    public static void showPracticalApplications(int num1, int num2, int gcd, int lcm) {
        System.out.println("Real-world applications:");
        System.out.println();
        
        System.out.println("1. Fraction Simplification:");
        System.out.println("   " + num1 + "/" + num2 + " can be simplified to " + (num1/gcd) + "/" + (num2/gcd));
        
        System.out.println();
        System.out.println("2. Finding Common Denominators:");
        System.out.println("   To add fractions with denominators " + num1 + " and " + num2 + ",");
        System.out.println("   use common denominator = " + lcm);
        
        System.out.println();
        System.out.println("3. Scheduling Problems:");
        System.out.println("   If one event occurs every " + num1 + " days and another every " + num2 + " days,");
        System.out.println("   they will coincide every " + lcm + " days.");
        
        System.out.println();
        System.out.println("4. Gear Ratios:");
        System.out.println("   Gears with " + num1 + " and " + num2 + " teeth will align every " + lcm + " rotations.");
        
        System.out.println();
        System.out.println("5. Resource Distribution:");
        System.out.println("   " + lcm + " items can be divided equally into groups of " + num1 + " or " + num2 + ".");
    }
}
