package SchoolResultsApplication;

import java.util.List;

/**
 * GradeCalculator computes overall grades and generates reports
 * Demonstrates separation of business logic
 */
public class GradeCalculator {
    
    /**
     * Calculate overall percentage for a student
     * @param student Student object
     * @return Overall percentage
     */
    public double calculateOverallPercentage(Student student) {
        List<Subject> subjects = student.getSubjects();
        
        if (subjects.isEmpty()) {
            return 0.0;
        }
        
        double totalMarks = 0;
        double totalMaxMarks = 0;
        
        for (Subject subject : subjects) {
            totalMarks += subject.getMarks();
            totalMaxMarks += subject.getMaxMarks();
        }
        
        return (totalMarks / totalMaxMarks) * 100;
    }
    
    /**
     * Calculate overall grade for a student
     * @param student Student object
     * @return Overall grade (A, B, C, D, F)
     */
    public String calculateOverallGrade(Student student) {
        double percentage = calculateOverallPercentage(student);
        
        if (percentage >= 90) return "A";
        else if (percentage >= 80) return "B";
        else if (percentage >= 70) return "C";
        else if (percentage >= 60) return "D";
        else return "F";
    }
    
    /**
     * Calculate total marks obtained
     * @param student Student object
     * @return Total marks
     */
    public double calculateTotalMarks(Student student) {
        double total = 0;
        for (Subject subject : student.getSubjects()) {
            total += subject.getMarks();
        }
        return total;
    }
    
    /**
     * Calculate total maximum marks
     * @param student Student object
     * @return Total maximum marks
     */
    public double calculateTotalMaxMarks(Student student) {
        double total = 0;
        for (Subject subject : student.getSubjects()) {
            total += subject.getMaxMarks();
        }
        return total;
    }
    
    /**
     * Generate complete report for a student
     * @param student Student object
     */
    public void generateReport(Student student) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                    STUDENT REPORT CARD                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        student.displayInfo();
        student.displaySubjects();
        
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                    OVERALL RESULTS                         ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        double totalMarks = calculateTotalMarks(student);
        double totalMaxMarks = calculateTotalMaxMarks(student);
        double percentage = calculateOverallPercentage(student);
        String grade = calculateOverallGrade(student);
        
        System.out.printf("Total Marks: %.2f / %.2f%n", totalMarks, totalMaxMarks);
        System.out.printf("Overall Percentage: %.2f%%%n", percentage);
        System.out.printf("Overall Grade: %s%n", grade);
        
        // Performance comment
        System.out.println("\nPerformance: " + getPerformanceComment(grade));
        System.out.println("═══════════════════════════════════════════════════════════════\n");
    }
    
    /**
     * Get performance comment based on grade
     * @param grade Grade letter
     * @return Performance comment
     */
    private String getPerformanceComment(String grade) {
        switch (grade) {
            case "A": return "Excellent! Outstanding performance.";
            case "B": return "Very Good! Keep up the good work.";
            case "C": return "Good! There's room for improvement.";
            case "D": return "Satisfactory! Need to work harder.";
            case "F": return "Failed! Serious improvement needed.";
            default: return "No comment available.";
        }
    }
    
    /**
     * Compare two students based on overall percentage
     * @param student1 First student
     * @param student2 Second student
     */
    public void compareStudents(Student student1, Student student2) {
        double perc1 = calculateOverallPercentage(student1);
        double perc2 = calculateOverallPercentage(student2);
        
        System.out.println("\n--- Student Comparison ---");
        System.out.printf("%s: %.2f%%%n", student1.getName(), perc1);
        System.out.printf("%s: %.2f%%%n", student2.getName(), perc2);
        
        if (perc1 > perc2) {
            System.out.printf("%s performed better by %.2f%%%n", 
                            student1.getName(), (perc1 - perc2));
        } else if (perc2 > perc1) {
            System.out.printf("%s performed better by %.2f%%%n", 
                            student2.getName(), (perc2 - perc1));
        } else {
            System.out.println("Both students have equal performance!");
        }
    }
}
