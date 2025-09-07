import java.util.Scanner;

public class BMICalculator {
    
    // Method to calculate BMI and status
    public static String[][] calculateBMI(double[][] heightWeight) {
        String[][] result = new String[heightWeight.length][4]; // height, weight, BMI, status
        
        for (int i = 0; i < heightWeight.length; i++) {
            double weight = heightWeight[i][0];
            double heightCm = heightWeight[i][1];
            double heightM = heightCm / 100.0; // Convert cm to meters
            
            double bmi = weight / (heightM * heightM);
            String status;
            
            if (bmi < 18.5) {
                status = "Underweight";
            } else if (bmi < 25) {
                status = "Normal";
            } else if (bmi < 30) {
                status = "Overweight";
            } else {
                status = "Obese";
            }
            
            result[i][0] = String.format("%.1f", heightCm);
            result[i][1] = String.format("%.1f", weight);
            result[i][2] = String.format("%.2f", bmi);
            result[i][3] = status;
        }
        
        return result;
    }
    
    // Method to process BMI data
    public static String[][] processBMIData(double[][] heightWeight) {
        return calculateBMI(heightWeight);
    }
    
    // Method to display BMI table
    public static void displayBMITable(String[][] bmiData) {
        System.out.println("\n--- BMI Analysis Results ---");
        System.out.println("Person\tHeight(cm)\tWeight(kg)\tBMI\tStatus");
        System.out.println("-------------------------------------------------------");
        
        for (int i = 0; i < bmiData.length; i++) {
            System.out.printf("%d\t%s\t\t%s\t\t%s\t%s%n",
                (i + 1),
                bmiData[i][0],
                bmiData[i][1],
                bmiData[i][2],
                bmiData[i][3]);
        }
        
        System.out.println("\nBMI Categories:");
        System.out.println("Underweight: < 18.5 | Normal: 18.5-24.9 | Overweight: 25-29.9 | Obese: ≥ 30");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== BMI Calculator for Team of 10 Members ===");
        
        double[][] heightWeight = new double[10][2]; // [weight, height]
        
        // Take input for 10 team members
        for (int i = 0; i < 10; i++) {
            System.out.println("\nPerson " + (i + 1) + ":");
            System.out.print("Enter weight (kg): ");
            heightWeight[i][0] = scanner.nextDouble();
            
            System.out.print("Enter height (cm): ");
            heightWeight[i][1] = scanner.nextDouble();
        }
        
        // Process BMI data
        String[][] bmiResults = processBMIData(heightWeight);
        
        // Display results
        displayBMITable(bmiResults);
        
        scanner.close();
    }
}
