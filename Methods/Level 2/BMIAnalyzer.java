import java.util.Scanner;

class BMIAnalyzer {
    
    public static void calculateBMI(double[][] personData) {
        for (int i = 0; i < personData.length; i++) {
            double weight = personData[i][0];
            double heightInCm = personData[i][1];
            double heightInMeters = heightInCm / 100.0;
            
            double bmi = weight / (heightInMeters * heightInMeters);
            personData[i][2] = bmi;
        }
    }
    
    public static String[] determineBMIStatus(double[][] personData) {
        String[] statuses = new String[personData.length];
        
        for (int i = 0; i < personData.length; i++) {
            double bmi = personData[i][2];
            
            if (bmi < 18.5) {
                statuses[i] = "Underweight";
            } else if (bmi < 25.0) {
                statuses[i] = "Normal";
            } else if (bmi < 30.0) {
                statuses[i] = "Overweight";
            } else {
                statuses[i] = "Obese";
            }
        }
        
        return statuses;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double[][] personData = new double[10][3];
        
        System.out.println("Enter weight (kg) and height (cm) for 10 persons:");
        
        for (int i = 0; i < 10; i++) {
            System.out.print("Person " + (i + 1) + " weight (kg): ");
            personData[i][0] = input.nextDouble();
            System.out.print("Person " + (i + 1) + " height (cm): ");
            personData[i][1] = input.nextDouble();
        }
        
        calculateBMI(personData);
        String[] statuses = determineBMIStatus(personData);
        
        System.out.println("\nBMI Analysis Results:");
        System.out.printf("%-8s %-10s %-10s %-8s %-12s%n", "Person", "Weight(kg)", "Height(cm)", "BMI", "Status");
        System.out.println("--------------------------------------------------------");
        
        for (int i = 0; i < 10; i++) {
            System.out.printf("%-8d %-10.2f %-10.2f %-8.2f %-12s%n", 
                            (i + 1), personData[i][0], personData[i][1], personData[i][2], statuses[i]);
        }
        
        input.close();
    }
}
