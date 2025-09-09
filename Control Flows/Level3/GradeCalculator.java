import java.util.Scanner;

/**
 * Problem 3: Write a program to input marks in 3 subjects physics, chemistry and maths.
 * Compute the percentage and then calculate the grade as per the following guidelines:
 * Grade A: 80% and above (Level 4, above agency-normalized standards)
 * Grade B: 70-79% (Level 3, at agency-normalized standards)
 * Grade C: 60-69% (Level 2, below, but approaching agency-normalized standards)
 * Grade D: 50-59% (Level 1, well below agency-normalized standards)
 * Grade E: 40-49% (Level 1-, too below agency-normalized standards)
 * Grade R: 39% and below (Remedial standards)
 */
public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter marks in Physics (out of 100): ");
        double physics = scanner.nextDouble();
        System.out.print("Enter marks in Chemistry (out of 100): ");
        double chemistry = scanner.nextDouble();
        System.out.print("Enter marks in Mathematics (out of 100): ");
        double maths = scanner.nextDouble();
        
        // Validate marks
        if (physics < 0 || physics > 100 || chemistry < 0 || chemistry > 100 || maths < 0 || maths > 100) {
            System.out.println("Please enter valid marks between 0 and 100");
            scanner.close();
            return;
        }
        
        // Calculate total marks and percentage
        double totalMarks = physics + chemistry + maths;
        double averageMark = totalMarks / 3;
        double percentage = averageMark; // Since marks are out of 100
        
        // Determine grade and remarks
        String grade;
        String remarks;
        
        if (percentage >= 80) {
            grade = "A";
            remarks = "Level 4, above agency-normalized standards";
        } else if (percentage >= 70) {
            grade = "B";
            remarks = "Level 3, at agency-normalized standards";
        } else if (percentage >= 60) {
            grade = "C";
            remarks = "Level 2, below, but approaching agency-normalized standards";
        } else if (percentage >= 50) {
            grade = "D";
            remarks = "Level 1, well below agency-normalized standards";
        } else if (percentage >= 40) {
            grade = "E";
            remarks = "Level 1-, too below agency-normalized standards";
        } else {
            grade = "R";
            remarks = "Remedial standards";
        }
        
        // Display results
        System.out.println("\n=== Grade Report ===");
        System.out.println("Physics: " + physics);
        System.out.println("Chemistry: " + chemistry);
        System.out.println("Mathematics: " + maths);
        System.out.println("Total Marks: " + totalMarks + "/300");
        System.out.println("Average Mark: " + String.format("%.2f", averageMark));
        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        System.out.println("Grade: " + grade);
        System.out.println("Remarks: " + remarks);
        
        scanner.close();
    }
}
