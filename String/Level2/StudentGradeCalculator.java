import java.util.Scanner;

public class StudentGradeCalculator {
    
    public static int[][] generateScores(int numStudents) {
        int[][] scores = new int[numStudents][3]; // Physics, Chemistry, Math
        
        for (int i = 0; i < numStudents; i++) {
            scores[i][0] = (int)(Math.random() * 91) + 10; // Physics (10-100)
            scores[i][1] = (int)(Math.random() * 91) + 10; // Chemistry (10-100)
            scores[i][2] = (int)(Math.random() * 91) + 10; // Math (10-100)
        }
        
        return scores;
    }
    
    public static double[][] calculateTotalAvgPercentage(int[][] scores) {
        double[][] result = new double[scores.length][3]; // Total, Average, Percentage
        
        for (int i = 0; i < scores.length; i++) {
            int total = scores[i][0] + scores[i][1] + scores[i][2];
            double average = total / 3.0;
            double percentage = (total / 300.0) * 100;
            
            result[i][0] = Math.round(total * 100.0) / 100.0;
            result[i][1] = Math.round(average * 100.0) / 100.0;
            result[i][2] = Math.round(percentage * 100.0) / 100.0;
        }
        
        return result;
    }
    
    public static String[] calculateGrades(double[][] calculations) {
        String[] grades = new String[calculations.length];
        
        for (int i = 0; i < calculations.length; i++) {
            double percentage = calculations[i][2];
            
            if (percentage >= 90) {
                grades[i] = "A+";
            } else if (percentage >= 80) {
                grades[i] = "A";
            } else if (percentage >= 70) {
                grades[i] = "B";
            } else if (percentage >= 60) {
                grades[i] = "C";
            } else if (percentage >= 50) {
                grades[i] = "D";
            } else {
                grades[i] = "F";
            }
        }
        
        return grades;
    }
    
    public static void displayScorecard(int[][] scores, double[][] calculations, String[] grades) {
        System.out.println("\n--- Student Scorecard ---");
        System.out.println("Student\tPhysics\tChemistry\tMath\tTotal\tAverage\tPercentage\tGrade");
        System.out.println("---------------------------------------------------------------------------------");
        
        for (int i = 0; i < scores.length; i++) {
            System.out.printf("%d\t%d\t%d\t\t%d\t%.0f\t%.2f\t%.2f%%\t\t%s%n",
                (i + 1),
                scores[i][0],
                scores[i][1], 
                scores[i][2],
                calculations[i][0],
                calculations[i][1],
                calculations[i][2],
                grades[i]);
        }
        
        System.out.println("\n--- Grade Scale ---");
        System.out.println("A+: 90-100%  A: 80-89%  B: 70-79%  C: 60-69%  D: 50-59%  F: Below 50%");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number of students: ");
        int numStudents = scanner.nextInt();
        
        if (numStudents <= 0) {
            System.out.println("Number of students must be positive!");
            return;
        }
        
        // Generate random scores
        int[][] scores = generateScores(numStudents);
        
        // Calculate totals, averages, and percentages
        double[][] calculations = calculateTotalAvgPercentage(scores);
        
        // Calculate grades
        String[] grades = calculateGrades(calculations);
        
        // Display scorecard
        displayScorecard(scores, calculations, grades);
        
        scanner.close();
    }
}
