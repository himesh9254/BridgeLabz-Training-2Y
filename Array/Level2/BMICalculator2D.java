import java.util.Scanner;

public class BMICalculator2D {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of persons: ");
        int numberOfPersons = scanner.nextInt();
        
        // Create 2D array to store weight, height and BMI
        // Column 0: Weight, Column 1: Height, Column 2: BMI
        double[][] personData = new double[numberOfPersons][3];
        String[] weightStatus = new String[numberOfPersons];
        
        System.out.println("\\n=== BMI DATA COLLECTION (2D Array Version) ===");
        
        // Take input for weight and height of all persons
        for (int i = 0; i < numberOfPersons; i++) {
            System.out.println("\\nPerson " + (i + 1) + ":");
            
            // Get weight with validation
            while (true) {
                System.out.print("Enter weight (in kg): ");
                personData[i][0] = scanner.nextDouble();
                if (personData[i][0] <= 0) {
                    System.out.println("Please enter a positive weight value.");
                } else {
                    break;
                }
            }
            
            // Get height with validation
            while (true) {
                System.out.print("Enter height (in meters): ");
                personData[i][1] = scanner.nextDouble();
                if (personData[i][1] <= 0) {
                    System.out.println("Please enter a positive height value.");
                } else {
                    break;
                }
            }
        }
        
        // Calculate BMI and determine weight status for all persons
        for (int i = 0; i < numberOfPersons; i++) {
            // Calculate BMI: weight (kg) / height (m)^2
            personData[i][2] = personData[i][0] / (personData[i][1] * personData[i][1]);
            
            // Determine weight status based on BMI
            double bmi = personData[i][2];
            if (bmi < 18.5) {
                weightStatus[i] = "Underweight";
            } else if (bmi >= 18.5 && bmi < 25.0) {
                weightStatus[i] = "Normal weight";
            } else if (bmi >= 25.0 && bmi < 30.0) {
                weightStatus[i] = "Overweight";
            } else {
                weightStatus[i] = "Obese";
            }
        }
        
        // Display results for all persons
        System.out.println("\\n=== BMI ANALYSIS RESULTS (2D Array) ===");
        System.out.println("========================================");
        System.out.printf("%-8s %-10s %-10s %-8s %-15s%n", 
                "Person", "Weight(kg)", "Height(m)", "BMI", "Status");
        System.out.println("----------------------------------------------------------");
        
        for (int i = 0; i < numberOfPersons; i++) {
            System.out.printf("%-8d %-10.1f %-10.2f %-8.2f %-15s%n",
                    (i + 1), personData[i][0], personData[i][1], personData[i][2], weightStatus[i]);
        }
        
        // Calculate and display statistics using 2D array
        System.out.println("\\n=== GROUP STATISTICS (2D Array Processing) ===");
        System.out.println("===============================================");
        
        // Calculate totals using 2D array
        double totalWeight = 0, totalHeight = 0, totalBMI = 0;
        for (int i = 0; i < numberOfPersons; i++) {
            totalWeight += personData[i][0];  // Weight column
            totalHeight += personData[i][1];  // Height column
            totalBMI += personData[i][2];     // BMI column
        }
        
        double avgWeight = totalWeight / numberOfPersons;
        double avgHeight = totalHeight / numberOfPersons;
        double avgBMI = totalBMI / numberOfPersons;
        
        System.out.printf("Average Weight: %.2f kg%n", avgWeight);
        System.out.printf("Average Height: %.2f m%n", avgHeight);
        System.out.printf("Average BMI: %.2f%n", avgBMI);
        
        // Find min and max values from 2D array
        double minWeight = personData[0][0], maxWeight = personData[0][0];
        double minHeight = personData[0][1], maxHeight = personData[0][1];
        double minBMI = personData[0][2], maxBMI = personData[0][2];
        
        for (int i = 1; i < numberOfPersons; i++) {
            if (personData[i][0] < minWeight) minWeight = personData[i][0];
            if (personData[i][0] > maxWeight) maxWeight = personData[i][0];
            if (personData[i][1] < minHeight) minHeight = personData[i][1];
            if (personData[i][1] > maxHeight) maxHeight = personData[i][1];
            if (personData[i][2] < minBMI) minBMI = personData[i][2];
            if (personData[i][2] > maxBMI) maxBMI = personData[i][2];
        }
        
        System.out.printf("Weight Range: %.1f kg to %.1f kg%n", minWeight, maxWeight);
        System.out.printf("Height Range: %.2f m to %.2f m%n", minHeight, maxHeight);
        System.out.printf("BMI Range: %.2f to %.2f%n", minBMI, maxBMI);
        
        // Count people in each BMI category
        int underweight = 0, normal = 0, overweight = 0, obese = 0;
        for (int i = 0; i < numberOfPersons; i++) {
            switch (weightStatus[i]) {
                case "Underweight":
                    underweight++;
                    break;
                case "Normal weight":
                    normal++;
                    break;
                case "Overweight":
                    overweight++;
                    break;
                case "Obese":
                    obese++;
                    break;
            }
        }
        
        System.out.println("\\n=== BMI CATEGORY DISTRIBUTION ===");
        System.out.println("Underweight (BMI < 18.5): " + underweight + " persons");
        System.out.println("Normal weight (18.5 ≤ BMI < 25.0): " + normal + " persons");
        System.out.println("Overweight (25.0 ≤ BMI < 30.0): " + overweight + " persons");
        System.out.println("Obese (BMI ≥ 30.0): " + obese + " persons");
        
        // Display 2D array structure
        System.out.println("\\n=== 2D ARRAY DATA STRUCTURE ===");
        System.out.println("personData[row][column] where:");
        System.out.println("Column 0: Weight (kg)");
        System.out.println("Column 1: Height (m)");
        System.out.println("Column 2: BMI");
        
        scanner.close();
    }
}
