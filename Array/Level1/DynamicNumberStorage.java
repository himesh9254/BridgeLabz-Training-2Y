import java.util.Scanner;

public class DynamicNumberStorage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] numbers = new double[10];
        double total = 0.0;
        int index = 0;
        
        System.out.println("Enter numbers (up to 10 numbers, enter 0 or negative to stop):");
        
        while (true) {
            System.out.print("Enter number " + (index + 1) + ": ");
            double number = scanner.nextDouble();
            
            // Break if user enters 0 or negative number
            if (number <= 0) {
                System.out.println("Stopping input as you entered " + number);
                break;
            }
            
            // Break if array is full (10 elements)
            if (index == 10) {
                System.out.println("Maximum limit of 10 numbers reached!");
                break;
            }
            
            // Store the number and increment index
            numbers[index] = number;
            index++;
        }
        
        // Calculate sum of all stored numbers
        for (int i = 0; i < index; i++) {
            total += numbers[i];
        }
        
        // Display results
        System.out.println("\nStored Numbers:");
        System.out.println("===============");
        for (int i = 0; i < index; i++) {
            System.out.println("Number " + (i + 1) + ": " + numbers[i]);
        }
        
        System.out.println("\nTotal count of numbers: " + index);
        System.out.println("Sum of all numbers: " + total);
        
        scanner.close();
    }
}
