import java.util.Scanner;

public class MaximumOfThree {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== Maximum of Three Numbers ===");
        System.out.println("This program finds the maximum of three integers.");
        System.out.println();
        
        // Get three numbers from user
        int[] numbers = getThreeNumbers();
        
        // Find maximum
        int maximum = findMaximum(numbers[0], numbers[1], numbers[2]);
        
        // Display result
        displayResult(numbers[0], numbers[1], numbers[2], maximum);
        
        scanner.close();
    }
    
    /**
     * Gets three integer inputs from the user
     * @return array of three integers
     */
    public static int[] getThreeNumbers() {
        int[] numbers = new int[3];
        String[] ordinals = {"first", "second", "third"};
        
        for (int i = 0; i < 3; i++) {
            numbers[i] = getValidInteger("Enter the " + ordinals[i] + " number: ");
        }
        
        return numbers;
    }
    
    /**
     * Gets a valid integer input from user with error handling
     * @param prompt the message to display to user
     * @return valid integer input
     */
    public static int getValidInteger(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }
    }
    
    /**
     * Finds the maximum of three numbers using multiple approaches
     * @param num1 first number
     * @param num2 second number  
     * @param num3 third number
     * @return the maximum of the three numbers
     */
    public static int findMaximum(int num1, int num2, int num3) {
        // Method 1: Using nested if-else statements
        return findMaximumUsingIfElse(num1, num2, num3);
    }
    
    /**
     * Alternative method 1: Using nested if-else
     */
    public static int findMaximumUsingIfElse(int a, int b, int c) {
        if (a >= b && a >= c) {
            return a;
        } else if (b >= a && b >= c) {
            return b;
        } else {
            return c;
        }
    }
    
    /**
     * Alternative method 2: Using Math.max()
     */
    public static int findMaximumUsingMathMax(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }
    
    /**
     * Alternative method 3: Using ternary operator
     */
    public static int findMaximumUsingTernary(int a, int b, int c) {
        return (a >= b) ? ((a >= c) ? a : c) : ((b >= c) ? b : c);
    }
    
    /**
     * Displays the result with detailed comparison
     * @param num1 first number
     * @param num2 second number
     * @param num3 third number
     * @param maximum the maximum number
     */
    public static void displayResult(int num1, int num2, int num3, int maximum) {
        System.out.println();
        System.out.println("=== Results ===");
        System.out.println("Numbers entered: " + num1 + ", " + num2 + ", " + num3);
        System.out.println("Maximum number: " + maximum);
        
        // Show which number(s) are maximum
        System.out.println();
        System.out.println("Comparison details:");
        System.out.println("Number 1 (" + num1 + ") is " + 
            (num1 == maximum ? "the maximum" : "not the maximum"));
        System.out.println("Number 2 (" + num2 + ") is " + 
            (num2 == maximum ? "the maximum" : "not the maximum"));
        System.out.println("Number 3 (" + num3 + ") is " + 
            (num3 == maximum ? "the maximum" : "not the maximum"));
        
        // Demonstrate alternative methods
        System.out.println();
        System.out.println("=== Alternative Methods ===");
        System.out.println("Using Math.max(): " + findMaximumUsingMathMax(num1, num2, num3));
        System.out.println("Using ternary operator: " + findMaximumUsingTernary(num1, num2, num3));
        
        // Check for ties
        checkForTies(num1, num2, num3, maximum);
    }
    
    /**
     * Checks and reports if there are tied maximum values
     */
    public static void checkForTies(int num1, int num2, int num3, int maximum) {
        int count = 0;
        if (num1 == maximum) count++;
        if (num2 == maximum) count++;
        if (num3 == maximum) count++;
        
        if (count > 1) {
            System.out.println();
            System.out.println("Note: There are " + count + " numbers tied for maximum!");
        }
    }
}
