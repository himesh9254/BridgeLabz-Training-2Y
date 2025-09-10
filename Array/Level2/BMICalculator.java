import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of persons: ");
        int numberOfPersons = scanner.nextInt();
        
        // Create arrays to store data for all persons
        double[] weights = new double[numberOfPersons];
        double[] heights = new double[numberOfPersons];
        double[] bmis = new double[numberOfPersons];
        String[] weightStatus = new String[numberOfPersons];
        
        System.out.println("\\n=== BMI DATA COLLECTION ===");
        
        // Take input for weight and height of all persons
        for (int i = 0; i < numberOfPersons; i++) {
            System.out.println("\\nPerson " + (i + 1) + ":");
            
            // Get weight with validation
            while (true) {
                System.out.print("Enter weight (in kg): ");
                weights[i] = scanner.nextDouble();
                if (weights[i] <= 0) {
                    System.out.println("Please enter a positive weight value.");
                } else {
                    break;
                }
            }
            
            // Get height with validation
            while (true) {
                System.out.print("Enter height (in meters): ");
                heights[i] = scanner.nextDouble();
                if (heights[i] <= 0) {
                    System.out.println("Please enter a positive height value.");
                } else {
                    break;
                }
            }
        }
        
        // Calculate BMI and determine weight status for all persons
        for (int i = 0; i < numberOfPersons; i++) {
            // Calculate BMI: weight (kg) / height (m)^2
            bmis[i] = weights[i] / (heights[i] * heights[i]);
            
            // Determine weight status based on BMI
            if (bmis[i] < 18.5) {
                weightStatus[i] = "Underweight";
            } else if (bmis[i] >= 18.5 && bmis[i] < 25.0) {
                weightStatus[i] = "Normal weight";
            } else if (bmis[i] >= 25.0 && bmis[i] < 30.0) {
                weightStatus[i] = "Overweight";
            } else {
                weightStatus[i] = "Obese";
            }
        }
        
        // Display results for all persons
        System.out.println("\\n=== BMI ANALYSIS RESULTS ===");
        System.out.println("==============================");
        System.out.printf("%-8s %-10s %-10s %-8s %-15s%n", 
                "Person", "Weight(kg)", "Height(m)", "BMI", "Status");
        System.out.println("----------------------------------------------------------");
        
        for (int i = 0; i < numberOfPersons; i++) {
            System.out.printf("%-8d %-10.1f %-10.2f %-8.2f %-15s%n",
                    (i + 1), weights[i], heights[i], bmis[i], weightStatus[i]);
        }
        
        // Calculate and display statistics
        System.out.println("\\n=== GROUP STATISTICS ===");
        System.out.println("========================");
        
        // Calculate averages
        double totalWeight = 0, totalHeight = 0, totalBMI = 0;
        for (int i = 0; i < numberOfPersons; i++) {
            totalWeight += weights[i];
            totalHeight += heights[i];
            totalBMI += bmis[i];
        }
        
        double avgWeight = totalWeight / numberOfPersons;
        double avgHeight = totalHeight / numberOfPersons;
        double avgBMI = totalBMI / numberOfPersons;
        
        System.out.printf("Average Weight: %.2f kg%n", avgWeight);
        System.out.printf("Average Height: %.2f m%n", avgHeight);
        System.out.printf("Average BMI: %.2f%n", avgBMI);
        
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
        
        System.out.println("\\n=== BMI REFERENCE TABLE ===");
        System.out.println("Underweight: BMI less than 18.5");
        System.out.println("Normal weight: BMI 18.5 to 24.9");
        System.out.println("Overweight: BMI 25.0 to 29.9");
        System.out.println("Obese: BMI 30.0 and above");
        
        scanner.close();
    }
}
