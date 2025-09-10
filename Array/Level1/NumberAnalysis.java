import java.util.Scanner;

public class NumberAnalysis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[5];
        
        System.out.println("Enter 5 numbers:");
        
        // Take input for all 5 numbers
        for (int i = 0; i < numbers.length; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            numbers[i] = scanner.nextInt();
        }
        
        System.out.println("\nNumber Analysis:");
        System.out.println("================");
        
        // Analyze each number
        for (int i = 0; i < numbers.length; i++) {
            System.out.print("Number " + (i + 1) + " (" + numbers[i] + "): ");
            
            if (numbers[i] > 0) {
                System.out.print("Positive");
                if (numbers[i] % 2 == 0) {
                    System.out.println(" and Even");
                } else {
                    System.out.println(" and Odd");
                }
            } else if (numbers[i] < 0) {
                System.out.println("Negative");
            } else {
                System.out.println("Zero");
            }
        }
        
        // Compare first and last elements
        System.out.println("\nComparison of First and Last Elements:");
        System.out.println("======================================");
        System.out.println("First element: " + numbers[0]);
        System.out.println("Last element: " + numbers[4]);
        
        if (numbers[0] == numbers[4]) {
            System.out.println("First and last elements are equal.");
        } else if (numbers[0] > numbers[4]) {
            System.out.println("First element is greater than last element.");
        } else {
            System.out.println("First element is less than last element.");
        }
        
        scanner.close();
    }
}
