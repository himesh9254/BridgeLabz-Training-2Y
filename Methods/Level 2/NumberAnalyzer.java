import java.util.Scanner;

class NumberAnalyzer {
    
    public static boolean isPositive(int number) {
        return number > 0;
    }
    
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
    
    public static int compare(int number1, int number2) {
        if (number1 > number2) {
            return 1;
        } else if (number1 == number2) {
            return 0;
        } else {
            return -1;
        }
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] numbers = new int[5];
        
        System.out.println("Enter 5 numbers:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print("Number " + (i + 1) + ": ");
            numbers[i] = input.nextInt();
        }
        
        System.out.println("\nNumber Analysis:");
        for (int i = 0; i < numbers.length; i++) {
            boolean positive = isPositive(numbers[i]);
            
            if (positive) {
                boolean even = isEven(numbers[i]);
                if (even) {
                    System.out.println("Number " + (i + 1) + ": " + numbers[i] + " - Positive and Even");
                } else {
                    System.out.println("Number " + (i + 1) + ": " + numbers[i] + " - Positive and Odd");
                }
            } else if (numbers[i] == 0) {
                System.out.println("Number " + (i + 1) + ": " + numbers[i] + " - Zero");
            } else {
                System.out.println("Number " + (i + 1) + ": " + numbers[i] + " - Negative");
            }
        }
        
        int comparison = compare(numbers[0], numbers[numbers.length - 1]);
        System.out.println("\nComparison of first and last elements:");
        
        if (comparison == 1) {
            System.out.println("First element (" + numbers[0] + ") is greater than last element (" + numbers[numbers.length - 1] + ")");
        } else if (comparison == 0) {
            System.out.println("First element (" + numbers[0] + ") is equal to last element (" + numbers[numbers.length - 1] + ")");
        } else {
            System.out.println("First element (" + numbers[0] + ") is less than last element (" + numbers[numbers.length - 1] + ")");
        }
        
        input.close();
    }
}
