import java.util.Scanner;

/**
 * Problem 10: Write a program to create a calculator using switch...case
 * Hint:
 * a. Create two double variables named first and second and a String variable named op
 * b. Get input values for all variables
 * c. The input for the operator can only be one of: "+", "-", "*", "/"
 * e. Based on the input value of the op, perform operation using switch...case and print result
 */
public class SwitchCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double first = scanner.nextDouble();
        System.out.print("Enter second number: ");
        double second = scanner.nextDouble();
        System.out.print("Enter operator (+, -, *, /): ");
        String op = scanner.next();

        double result;
        boolean valid = true;

        switch (op) {
            case "+":
                result = first + second;
                System.out.println("Result: " + result);
                break;
            case "-":
                result = first - second;
                System.out.println("Result: " + result);
                break;
            case "*":
                result = first * second;
                System.out.println("Result: " + result);
                break;
            case "/":
                if (second == 0) {
                    System.out.println("Error: Division by zero is not allowed");
                } else {
                    result = first / second;
                    System.out.println("Result: " + result);
                }
                break;
            default:
                valid = false;
                System.out.println("Invalid Operator");
        }

        scanner.close();
    }
}

