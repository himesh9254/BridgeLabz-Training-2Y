import java.util.Scanner;

public class StudentGrades2D {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of students: ");
        int numberOfStudents = scanner.nextInt();
        
        // 2D array to store marks for 3 subjects (Physics, Chemistry, Maths)
        // Column 0: Physics, Column 1: Chemistry, Column 2: Mathematics
        double[][] marks = new double[numberOfStudents][3];
        double[] percentages = new double[numberOfStudents];
        String[] grades = new String[numberOfStudents];
        
        // Subject names for reference
        String[] subjects = {"Physics", "Chemistry", "Mathematics"};
        
        System.out.println("\\n=== STUDENT MARKS DATA COLLECTION (2D Array Version) ===");
        System.out.println("Enter marks for Physics, Chemistry, and Mathematics (out of 100)");
        
        // Take input for marks of all students using 2D array
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("\\nStudent " + (i + 1) + ":");
            
            for (int j = 0; j < 3; j++) { // j represents subject (0=Physics, 1=Chemistry, 2=Maths)
                while (true) {
                    System.out.print("Enter " + subjects[j] + " marks: ");
                    marks[i][j] = scanner.nextDouble();
                    if (marks[i][j] < 0 || marks[i][j] > 100) {
                        System.out.println("Please enter marks between 0 and 100.");
                        i--; // Decrement to re-enter for this student
                        j = 3; // Break out of inner loop
                        break;
                    } else {
                        break;
                    }
                }
                if (marks[i][j] < 0 || marks[i][j] > 100) break; // Break out if invalid
            }
        }
        
        // Calculate percentage and grade for all students using 2D array
        for (int i = 0; i < numberOfStudents; i++) {
            // Calculate total marks using 2D array
            double totalMarks = 0;
            for (int j = 0; j < 3; j++) {
                totalMarks += marks[i][j];
            }
            
            // Calculate percentage (total marks out of 300)
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
        
        // Display results for all students using 2D array
        System.out.println("\\n=== STUDENT GRADE REPORT (2D Array Version) ===");
        System.out.println("================================================");
        System.out.printf("%-8s %-8s %-10s %-8s %-8s %-10s %-6s%n", 
                "Student", "Physics", "Chemistry", "Maths", "Total", "Percentage", "Grade");
        System.out.println("----------------------------------------------------------------------");
        
        for (int i = 0; i < numberOfStudents; i++) {
            double totalMarks = marks[i][0] + marks[i][1] + marks[i][2]; // Using 2D array
            System.out.printf("%-8d %-8.1f %-10.1f %-8.1f %-8.1f %-10.2f%% %-6s%n",
                    (i + 1), marks[i][0], marks[i][1], marks[i][2], 
                    totalMarks, percentages[i], grades[i]);
        }
        
        // Calculate and display class statistics using 2D array
        System.out.println("\\n=== CLASS STATISTICS (2D Array Processing) ===");
        System.out.println("===============================================");
        
        // Calculate subject-wise averages using 2D array
        double[] subjectAverages = new double[3];
        for (int j = 0; j < 3; j++) { // For each subject
            double subjectTotal = 0;
            for (int i = 0; i < numberOfStudents; i++) { // For each student
                subjectTotal += marks[i][j];
            }
            subjectAverages[j] = subjectTotal / numberOfStudents;
        }
        
        double avgPercentage = 0;
        for (int i = 0; i < numberOfStudents; i++) {
            avgPercentage += percentages[i];
        }
        avgPercentage /= numberOfStudents;
        
        for (int j = 0; j < 3; j++) {
            System.out.printf("Average %s marks: %.2f%n", subjects[j], subjectAverages[j]);
        }
        System.out.printf("Class Average Percentage: %.2f%%%n", avgPercentage);
        
        // Find highest and lowest scores in each subject using 2D array
        System.out.println("\\n=== SUBJECT-WISE ANALYSIS ===");
        for (int j = 0; j < 3; j++) {
            double highestMark = marks[0][j];
            double lowestMark = marks[0][j];
            int highestStudent = 0, lowestStudent = 0;
            
            for (int i = 1; i < numberOfStudents; i++) {
                if (marks[i][j] > highestMark) {
                    highestMark = marks[i][j];
                    highestStudent = i;
                }
                if (marks[i][j] < lowestMark) {
                    lowestMark = marks[i][j];
                    lowestStudent = i;
                }
            }
            
            System.out.printf("%s - Highest: %.1f (Student %d), Lowest: %.1f (Student %d)%n",
                    subjects[j], highestMark, (highestStudent + 1), lowestMark, (lowestStudent + 1));
        }
        
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
        
        // Display 2D array structure information
        System.out.println("\\n=== 2D ARRAY DATA STRUCTURE ===");
        System.out.println("marks[student][subject] where:");
        System.out.println("Column 0: Physics marks");
        System.out.println("Column 1: Chemistry marks");
        System.out.println("Column 2: Mathematics marks");
        System.out.println("Array dimensions: " + numberOfStudents + " students × 3 subjects");
        
        System.out.println("\\n=== GRADING SCALE ===");
        System.out.println("A1: 91-100% | A2: 81-90% | B1: 71-80% | B2: 61-70%");
        System.out.println("C1: 51-60%  | C2: 41-50% | D: 33-40%   | E1: Below 33%");
        
        scanner.close();
    }
}
