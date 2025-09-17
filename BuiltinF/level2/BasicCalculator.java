import java.util.Scanner;

public class BasicCalculator {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== Basic Calculator ===");
        System.out.println("This calculator performs basic mathematical operations.");
        System.out.println("Each operation is implemented using modular functions.");
        System.out.println();
        
        boolean continueCalculating = true;
        
        while (continueCalculating) {
            // Display menu
            displayMenu();
            
            // Get user choice
            int choice = getUserChoice();
            
            // Process user choice
            switch (choice) {
                case 1:
                    performAddition();
                    break;
                case 2:
                    performSubtraction();
                    break;
                case 3:
                    performMultiplication();
                    break;
                case 4:
                    performDivision();
                    break;
                case 5:
                    performAdvancedOperations();
                    break;
                case 6:
                    showCalculatorHistory();
                    break;
                case 7:
                    continueCalculating = false;
                    System.out.println("Thank you for using the Basic Calculator!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            
            if (continueCalculating) {
                System.out.println();
                System.out.print("Press Enter to continue...");
                scanner.nextLine();
                System.out.println();
            }
        }
        
        scanner.close();
    }
    
    /**
     * Displays the calculator menu
     */
    public static void displayMenu() {
        System.out.println("Choose an operation:");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (×)");
        System.out.println("4. Division (÷)");
        System.out.println("5. Advanced Operations");
        System.out.println("6. Calculator Tips & History");
        System.out.println("7. Exit");
        System.out.println();
    }
    
    /**
     * Gets user menu choice with validation
     * @return valid menu choice (1-7)
     */
    public static int getUserChoice() {
        while (true) {
            try {
                System.out.print("Enter your choice (1-7): ");
                int choice = Integer.parseInt(scanner.nextLine());
                
                if (choice >= 1 && choice <= 7) {
                    return choice;
                } else {
                    System.out.println("Please enter a number between 1 and 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
    
    /**
     * Gets a double input from user with validation
     * @param prompt the message to display
     * @return valid double input
     */
    public static double getNumberInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
    
    /**
     * Addition function
     * @param a first number
     * @param b second number
     * @return sum of a and b
     */
    public static double add(double a, double b) {
        return a + b;
    }
    
    /**
     * Subtraction function
     * @param a first number (minuend)
     * @param b second number (subtrahend)
     * @return difference of a and b
     */
    public static double subtract(double a, double b) {
        return a - b;
    }
    
    /**
     * Multiplication function
     * @param a first number
     * @param b second number
     * @return product of a and b
     */
    public static double multiply(double a, double b) {
        return a * b;
    }
    
    /**
     * Division function with error handling
     * @param a dividend
     * @param b divisor
     * @return quotient of a divided by b
     * @throws ArithmeticException if b is zero
     */
    public static double divide(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero!");
        }
        return a / b;
    }
    
    /**
     * Power function (exponentiation)
     * @param base the base number
     * @param exponent the exponent
     * @return base raised to the power of exponent
     */
    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }
    
    /**
     * Square root function
     * @param number the number to find square root of
     * @return square root of the number
     * @throws ArithmeticException if number is negative
     */
    public static double squareRoot(double number) throws ArithmeticException {
        if (number < 0) {
            throw new ArithmeticException("Cannot calculate square root of negative number!");
        }
        return Math.sqrt(number);
    }
    
    /**
     * Percentage calculation function
     * @param value the value
     * @param percentage the percentage
     * @return percentage of the value
     */
    public static double calculatePercentage(double value, double percentage) {
        return (value * percentage) / 100.0;
    }
    
    /**
     * Performs addition operation with user interaction
     */
    public static void performAddition() {
        System.out.println();
        System.out.println("=== Addition ===");
        
        double num1 = getNumberInput("Enter first number: ");
        double num2 = getNumberInput("Enter second number: ");
        
        double result = add(num1, num2);
        
        displayResult(num1, "+", num2, result);
        showOperationDetails("Addition", "Sum of two numbers", 
            "Properties: Commutative (a + b = b + a), Associative");
    }
    
    /**
     * Performs subtraction operation with user interaction
     */
    public static void performSubtraction() {
        System.out.println();
        System.out.println("=== Subtraction ===");
        
        double num1 = getNumberInput("Enter first number (minuend): ");
        double num2 = getNumberInput("Enter second number (subtrahend): ");
        
        double result = subtract(num1, num2);
        
        displayResult(num1, "-", num2, result);
        showOperationDetails("Subtraction", "Difference between two numbers", 
            "Note: Order matters! a - b ≠ b - a (not commutative)");
        
        // Show reverse calculation
        System.out.println("Reverse calculation: " + num2 + " - " + num1 + " = " + subtract(num2, num1));
    }
    
    /**
     * Performs multiplication operation with user interaction
     */
    public static void performMultiplication() {
        System.out.println();
        System.out.println("=== Multiplication ===");
        
        double num1 = getNumberInput("Enter first number: ");
        double num2 = getNumberInput("Enter second number: ");
        
        double result = multiply(num1, num2);
        
        displayResult(num1, "×", num2, result);
        showOperationDetails("Multiplication", "Product of two numbers", 
            "Properties: Commutative (a × b = b × a), Associative, Distributive");
        
        // Show interesting facts
        System.out.println("Interesting facts:");
        if (num1 == 0 || num2 == 0) {
            System.out.println("• Multiplying by zero always gives zero");
        }
        if (num1 == 1) {
            System.out.println("• " + num1 + " is the multiplicative identity");
        }
        if (num2 == 1) {
            System.out.println("• " + num2 + " is the multiplicative identity");
        }
    }
    
    /**
     * Performs division operation with user interaction
     */
    public static void performDivision() {
        System.out.println();
        System.out.println("=== Division ===");
        
        double num1 = getNumberInput("Enter dividend (number to be divided): ");
        double num2 = getNumberInput("Enter divisor (number to divide by): ");
        
        try {
            double result = divide(num1, num2);
            displayResult(num1, "÷", num2, result);
            
            // Show additional information
            System.out.println();
            System.out.println("Division details:");
            System.out.printf("Quotient: %.6f%n", result);
            
            // Show integer division if applicable
            if (num1 % 1 == 0 && num2 % 1 == 0 && num2 != 0) {
                int intNum1 = (int) num1;
                int intNum2 = (int) num2;
                System.out.println("Integer division: " + intNum1 + " ÷ " + intNum2 + " = " + (intNum1 / intNum2));
                System.out.println("Remainder: " + (intNum1 % intNum2));
            }
            
            // Show reciprocal
            if (num2 != 0) {
                System.out.println("Reciprocal calculation: " + num2 + " ÷ " + num1 + " = " + divide(num2, num1));
            }
            
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Division by zero is undefined in mathematics.");
        }
        
        showOperationDetails("Division", "Quotient of two numbers", 
            "Note: Division by zero is undefined!");
    }
    
    /**
     * Performs advanced operations
     */
    public static void performAdvancedOperations() {
        System.out.println();
        System.out.println("=== Advanced Operations ===");
        System.out.println("1. Power (Exponentiation)");
        System.out.println("2. Square Root");
        System.out.println("3. Percentage Calculation");
        System.out.println("4. Multiple Operations");
        
        int choice = getUserAdvancedChoice();
        
        switch (choice) {
            case 1:
                performPowerOperation();
                break;
            case 2:
                performSquareRootOperation();
                break;
            case 3:
                performPercentageOperation();
                break;
            case 4:
                performMultipleOperations();
                break;
        }
    }
    
    /**
     * Gets user choice for advanced operations
     * @return valid choice (1-4)
     */
    public static int getUserAdvancedChoice() {
        while (true) {
            try {
                System.out.print("Choose advanced operation (1-4): ");
                int choice = Integer.parseInt(scanner.nextLine());
                
                if (choice >= 1 && choice <= 4) {
                    return choice;
                } else {
                    System.out.println("Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
    
    /**
     * Performs power operation
     */
    public static void performPowerOperation() {
        System.out.println();
        System.out.println("=== Power (Exponentiation) ===");
        
        double base = getNumberInput("Enter base number: ");
        double exponent = getNumberInput("Enter exponent: ");
        
        double result = power(base, exponent);
        
        System.out.println();
        System.out.println("=== Result ===");
        System.out.printf("%.2f ^ %.2f = %.6f%n", base, exponent, result);
        
        // Show special cases
        System.out.println();
        System.out.println("Special cases:");
        if (exponent == 0) {
            System.out.println("• Any number to the power of 0 equals 1 (except 0^0 which is undefined)");
        }
        if (exponent == 1) {
            System.out.println("• Any number to the power of 1 equals itself");
        }
        if (base == 2) {
            System.out.println("• Powers of 2: useful in computer science");
        }
    }
    
    /**
     * Performs square root operation
     */
    public static void performSquareRootOperation() {
        System.out.println();
        System.out.println("=== Square Root ===");
        
        double number = getNumberInput("Enter number to find square root of: ");
        
        try {
            double result = squareRoot(number);
            
            System.out.println();
            System.out.println("=== Result ===");
            System.out.printf("√%.2f = %.6f%n", number, result);
            
            // Verification
            System.out.println();
            System.out.println("Verification:");
            double verification = multiply(result, result);
            System.out.printf("%.6f × %.6f = %.6f%n", result, result, verification);
            
            // Show perfect square check
            if (Math.abs(verification - number) < 0.0001) {
                int intResult = (int) result;
                if (intResult == result) {
                    System.out.println(number + " is a perfect square!");
                }
            }
            
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Note: For complex numbers, √(-a) = i√a where i is the imaginary unit");
        }
    }
    
    /**
     * Performs percentage operation
     */
    public static void performPercentageOperation() {
        System.out.println();
        System.out.println("=== Percentage Calculation ===");
        
        double value = getNumberInput("Enter the value: ");
        double percentage = getNumberInput("Enter the percentage: ");
        
        double result = calculatePercentage(value, percentage);
        
        System.out.println();
        System.out.println("=== Result ===");
        System.out.printf("%.2f%% of %.2f = %.2f%n", percentage, value, result);
        
        // Show additional calculations
        System.out.println();
        System.out.println("Related calculations:");
        System.out.printf("%.2f + %.2f%% = %.2f%n", value, percentage, add(value, result));
        System.out.printf("%.2f - %.2f%% = %.2f%n", value, percentage, subtract(value, result));
        
        if (result != 0) {
            double reversePercentage = (value / result) * 100;
            System.out.printf("%.2f is %.2f%% of %.2f%n", value, reversePercentage, result);
        }
    }
    
    /**
     * Performs multiple operations in sequence
     */
    public static void performMultipleOperations() {
        System.out.println();
        System.out.println("=== Multiple Operations ===");
        System.out.println("Calculate: (a + b) × c ÷ d - e");
        System.out.println();
        
        double a = getNumberInput("Enter value for a: ");
        double b = getNumberInput("Enter value for b: ");
        double c = getNumberInput("Enter value for c: ");
        double d = getNumberInput("Enter value for d: ");
        double e = getNumberInput("Enter value for e: ");
        
        try {
            // Step-by-step calculation
            System.out.println();
            System.out.println("=== Step-by-step calculation ===");
            
            double step1 = add(a, b);
            System.out.printf("Step 1: %.2f + %.2f = %.2f%n", a, b, step1);
            
            double step2 = multiply(step1, c);
            System.out.printf("Step 2: %.2f × %.2f = %.2f%n", step1, c, step2);
            
            double step3 = divide(step2, d);
            System.out.printf("Step 3: %.2f ÷ %.2f = %.2f%n", step2, d, step3);
            
            double finalResult = subtract(step3, e);
            System.out.printf("Step 4: %.2f - %.2f = %.2f%n", step3, e, finalResult);
            
            System.out.println();
            System.out.printf("Final Result: (%.2f + %.2f) × %.2f ÷ %.2f - %.2f = %.2f%n", 
                             a, b, c, d, e, finalResult);
            
        } catch (ArithmeticException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
    /**
     * Displays the result of a binary operation
     * @param num1 first operand
     * @param operator the operation symbol
     * @param num2 second operand
     * @param result the calculation result
     */
    public static void displayResult(double num1, String operator, double num2, double result) {
        System.out.println();
        System.out.println("=== Result ===");
        System.out.printf("%.2f %s %.2f = %.6f%n", num1, operator, num2, result);
        
        // Show rounded result if it's close to an integer
        if (Math.abs(result - Math.round(result)) < 0.0001) {
            System.out.printf("Rounded: %.2f %s %.2f = %.0f%n", num1, operator, num2, result);
        }
    }
    
    /**
     * Shows details about the mathematical operation
     * @param operation name of the operation
     * @param description description of the operation
     * @param properties mathematical properties
     */
    public static void showOperationDetails(String operation, String description, String properties) {
        System.out.println();
        System.out.println("=== Operation Details ===");
        System.out.println("Operation: " + operation);
        System.out.println("Description: " + description);
        System.out.println(properties);
    }
    
    /**
     * Shows calculator tips and history
     */
    public static void showCalculatorHistory() {
        System.out.println();
        System.out.println("=== Calculator Tips & History ===");
        
        System.out.println("Mathematical Operation Order (PEMDAS/BODMAS):");
        System.out.println("1. Parentheses/Brackets");
        System.out.println("2. Exponents/Orders");
        System.out.println("3. Multiplication and Division (left to right)");
        System.out.println("4. Addition and Subtraction (left to right)");
        
        System.out.println();
        System.out.println("Calculator Tips:");
        System.out.println("• Always check for division by zero");
        System.out.println("• Use parentheses to clarify order of operations");
        System.out.println("• Be aware of floating-point precision limits");
        System.out.println("• Consider using scientific notation for very large/small numbers");
        
        System.out.println();
        System.out.println("Common Mathematical Constants:");
        System.out.println("• π (Pi) ≈ 3.14159265359");
        System.out.println("• e (Euler's number) ≈ 2.71828182846");
        System.out.println("• √2 ≈ 1.41421356237");
        System.out.println("• φ (Golden ratio) ≈ 1.61803398875");
        
        System.out.println();
        System.out.println("History of Calculators:");
        System.out.println("• Abacus: Ancient calculating device (~2400 BC)");
        System.out.println("• Slide rule: Mechanical calculator (1600s)");
        System.out.println("• Mechanical calculators: Pascal's calculator (1642)");
        System.out.println("• Electronic calculators: First appeared in 1960s");
        System.out.println("• Modern software calculators: Like this program!");
        
        System.out.println();
        System.out.println("Fun Math Facts:");
        System.out.println("• 0! (0 factorial) = 1");
        System.out.println("• Any number to the power of 0 equals 1");
        System.out.println("• The sum of angles in any triangle is 180°");
        System.out.println("• There are infinitely many prime numbers");
    }
}
