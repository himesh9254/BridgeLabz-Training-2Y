import java.util.Scanner;

public class FizzBuzzArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a positive integer: ");
        int number = scanner.nextInt();
        
        // Check if the number is positive
        if (number <= 0) {
            System.out.println("Please enter a positive integer!");
            scanner.close();
            return;
        }
        
        // Create String array to store FizzBuzz results
        String[] fizzBuzzArray = new String[number + 1]; // +1 to include the number itself
        
        // Generate FizzBuzz from 0 to number
        for (int i = 0; i <= number; i++) {
            if (i % 15 == 0) { // Multiple of both 3 and 5
                fizzBuzzArray[i] = "FizzBuzz";
            } else if (i % 3 == 0) { // Multiple of 3
                fizzBuzzArray[i] = "Fizz";
            } else if (i % 5 == 0) { // Multiple of 5
                fizzBuzzArray[i] = "Buzz";
            } else {
                fizzBuzzArray[i] = String.valueOf(i); // Convert number to string
            }
        }
        
        // Display the FizzBuzz results with position formatting
        System.out.println("\\nFizzBuzz Results (0 to " + number + "):");
        System.out.println("==============================");
        
        for (int i = 0; i < fizzBuzzArray.length; i++) {
            System.out.println("Position " + i + " = " + fizzBuzzArray[i]);
        }
        
        // Summary
        System.out.println("\\nSummary:");
        System.out.println("========");
        int fizzCount = 0, buzzCount = 0, fizzBuzzCount = 0, numberCount = 0;
        
        for (int i = 0; i < fizzBuzzArray.length; i++) {
            switch (fizzBuzzArray[i]) {
                case "Fizz":
                    fizzCount++;
                    break;
                case "Buzz":
                    buzzCount++;
                    break;
                case "FizzBuzz":
                    fizzBuzzCount++;
                    break;
                default:
                    numberCount++;
                    break;
            }
        }
        
        System.out.println("Numbers: " + numberCount);
        System.out.println("Fizz: " + fizzCount);
        System.out.println("Buzz: " + buzzCount);
        System.out.println("FizzBuzz: " + fizzBuzzCount);
        System.out.println("Total positions: " + fizzBuzzArray.length);
        
        scanner.close();
    }
}
