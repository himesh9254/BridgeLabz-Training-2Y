import java.util.Scanner;

/**
 * Problem 7: Create a program to find the BMI of a person
 * Hint:
 * a. Take user input in double for the weight (in kg) of the person and height (in cm) for the person
 * b. Use the formula BMI = weight / (height * height). Note unit is kg/m^2. Convert cm to meter
 * c. Use the BMI table to determine the weight status:
 *    ≤ 18.4: Underweight
 *    18.5 - 24.9: Normal
 *    25.0 - 39.9: Overweight
 *    ≥ 40.0: Obese
 */
public class BMICalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your weight in kg: ");
        double weight = scanner.nextDouble();
        System.out.print("Enter your height in cm: ");
        double heightCm = scanner.nextDouble();
        
        // Validate input
        if (weight <= 0 || heightCm <= 0) {
            System.out.println("Please enter valid positive values for weight and height");
            scanner.close();
            return;
        }
        
        // Convert height from cm to meters
        double heightM = heightCm / 100.0;
        
        // Calculate BMI using formula: BMI = weight / (height * height)
        double bmi = weight / (heightM * heightM);
        
        // Determine weight status using BMI table
        String status;
        if (bmi <= 18.4) {
            status = "Underweight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            status = "Normal";
        } else if (bmi >= 25.0 && bmi <= 39.9) {
            status = "Overweight";
        } else { // bmi >= 40.0
            status = "Obese";
        }
        
        // Display results
        System.out.println("\n=== BMI Calculation Results ===");
        System.out.println("Weight: " + weight + " kg");
        System.out.println("Height: " + heightCm + " cm (" + heightM + " m)");
        System.out.println("BMI: " + String.format("%.2f", bmi) + " kg/m²");
        System.out.println("Status: " + status);
        
        scanner.close();
    }
}
