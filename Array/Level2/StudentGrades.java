import java.util.Scanner;

public class StudentGrades {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of students: ");
        int numberOfStudents = scanner.nextInt();
        
        // Arrays to store marks, percentages, and grades
        double[] physicsMarks = new double[numberOfStudents];
        double[] chemistryMarks = new double[numberOfStudents];
        double[] mathsMarks = new double[numberOfStudents];
        double[] percentages = new double[numberOfStudents];
        String[] grades = new String[numberOfStudents];
        
        System.out.println("\\n=== STUDENT MARKS DATA COLLECTION ===");
        System.out.println("Enter marks for Physics, Chemistry, and Mathematics (out of 100)");
        
        // Take input for marks of all students
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("\\nStudent " + (i + 1) + ":");
            
            // Physics marks with validation
            while (true) {
                System.out.print("Enter Physics marks: ");
                physicsMarks[i] = scanner.nextDouble();
                if (physicsMarks[i] < 0 || physicsMarks[i] > 100) {
                    System.out.println("Please enter marks between 0 and 100.");
                    i--; // Decrement to re-enter for this student
                    break;
                } else {
                    break;
                }
            }
            
            if (physicsMarks[i] < 0 || physicsMarks[i] > 100) continue;
            
            // Chemistry marks with validation
            while (true) {
                System.out.print("Enter Chemistry marks: ");
                chemistryMarks[i] = scanner.nextDouble();
                if (chemistryMarks[i] < 0 || chemistryMarks[i] > 100) {
                    System.out.println("Please enter marks between 0 and 100.");
                    i--; // Decrement to re-enter for this student
                    break;
                } else {
                    break;
                }
            }
            
            if (chemistryMarks[i] < 0 || chemistryMarks[i] > 100) continue;
            
            // Mathematics marks with validation
            while (true) {
                System.out.print("Enter Mathematics marks: ");
                mathsMarks[i] = scanner.nextDouble();
                if (mathsMarks[i] < 0 || mathsMarks[i] > 100) {
                    System.out.println("Please enter marks between 0 and 100.");
                    i--; // Decrement to re-enter for this student
                    break;
                } else {
                    break;
                }
            }
            
            if (mathsMarks[i] < 0 || mathsMarks[i] > 100) continue;
        }
        
        // Calculate percentage and grade for all students
        for (int i = 0; i < numberOfStudents; i++) {
            // Calculate percentage (total marks out of 300)
            double totalMarks = physicsMarks[i] + chemistryMarks[i] + mathsMarks[i];
            percentages[i] = (totalMarks / 300.0) * 100;
            
            // Determine grade based on percentage
            if (percentages[i] >= 91) {
                grades[i] = "A1";
            } else if (percentages[i] >= 81) {
                grades[i] = "A2";
            } else if (percentages[i] >= 71) {
                grades[i] = "B1";
            } else if (percentages[i] >= 61) {
                grades[i] = "B2";
            } else if (percentages[i] >= 51) {
                grades[i] = "C1";
            } else if (percentages[i] >= 41) {
                grades[i] = "C2";
            } else if (percentages[i] >= 33) {
                grades[i] = "D";
            } else {
                grades[i] = "E1";
            }
        }
        
        // Display results for all students
        System.out.println("\\n=== STUDENT GRADE REPORT ===");
        System.out.println("============================");
        System.out.printf("%-8s %-8s %-10s %-8s %-8s %-10s %-6s%n", 
                "Student", "Physics", "Chemistry", "Maths", "Total", "Percentage", "Grade");
        System.out.println("----------------------------------------------------------------------");
        
        for (int i = 0; i < numberOfStudents; i++) {
            double totalMarks = physicsMarks[i] + chemistryMarks[i] + mathsMarks[i];
            System.out.printf("%-8d %-8.1f %-10.1f %-8.1f %-8.1f %-10.2f%% %-6s%n",
                    (i + 1), physicsMarks[i], chemistryMarks[i], mathsMarks[i], 
                    totalMarks, percentages[i], grades[i]);
        }
        
        // Calculate and display class statistics
        System.out.println("\\n=== CLASS STATISTICS ===");
        System.out.println("========================");
        
        // Calculate subject-wise averages
        double avgPhysics = 0, avgChemistry = 0, avgMaths = 0, avgPercentage = 0;
        for (int i = 0; i < numberOfStudents; i++) {
            avgPhysics += physicsMarks[i];
            avgChemistry += chemistryMarks[i];
            avgMaths += mathsMarks[i];
            avgPercentage += percentages[i];
        }
        
        avgPhysics /= numberOfStudents;
        avgChemistry /= numberOfStudents;
        avgMaths /= numberOfStudents;
        avgPercentage /= numberOfStudents;
        
        System.out.printf("Average Physics marks: %.2f%n", avgPhysics);
        System.out.printf("Average Chemistry marks: %.2f%n", avgChemistry);
        System.out.printf("Average Mathematics marks: %.2f%n", avgMaths);
        System.out.printf("Class Average Percentage: %.2f%%%n", avgPercentage);
        
        // Grade distribution
        int[] gradeCount = new int[8]; // A1, A2, B1, B2, C1, C2, D, E1
        String[] gradeLabels = {"A1", "A2", "B1", "B2", "C1", "C2", "D", "E1"};
        
        for (int i = 0; i < numberOfStudents; i++) {
            for (int j = 0; j < gradeLabels.length; j++) {
                if (grades[i].equals(gradeLabels[j])) {
                    gradeCount[j]++;
                    break;
                }
            }
        }
        
        System.out.println("\\n=== GRADE DISTRIBUTION ===");
        for (int i = 0; i < gradeLabels.length; i++) {
            if (gradeCount[i] > 0) {
                System.out.println("Grade " + gradeLabels[i] + ": " + gradeCount[i] + " students");
            }
        }
        
        System.out.println("\\n=== GRADING SCALE ===");
        System.out.println("A1: 91-100% | A2: 81-90% | B1: 71-80% | B2: 61-70%");
        System.out.println("C1: 51-60%  | C2: 41-50% | D: 33-40%   | E1: Below 33%");
        
        scanner.close();
    }
}
