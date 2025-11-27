import java.util.*;

public class StudentGradeTracker {
    private Map<String, Double> studentGrades;
    
    public StudentGradeTracker() {
        studentGrades = new HashMap<>();
    }
    
    public void addStudent(String name, double grade) {
        if (grade < 0 || grade > 100) {
            System.out.println("Invalid grade. Grade must be between 0 and 100.");
            return;
        }
        studentGrades.put(name, grade);
        System.out.println("Added: " + name + " with grade " + grade);
    }
    
    public void updateGrade(String name, double newGrade) {
        if (!studentGrades.containsKey(name)) {
            System.out.println("Student not found: " + name);
            return;
        }
        if (newGrade < 0 || newGrade > 100) {
            System.out.println("Invalid grade. Grade must be between 0 and 100.");
            return;
        }
        double oldGrade = studentGrades.get(name);
        studentGrades.put(name, newGrade);
        System.out.println("Updated " + name + "'s grade from " + oldGrade + " to " + newGrade);
    }
    
    public void removeStudent(String name) {
        if (studentGrades.remove(name) != null) {
            System.out.println("Removed student: " + name);
        } else {
            System.out.println("Student not found: " + name);
        }
    }
    
    public Double getGrade(String name) {
        return studentGrades.get(name);
    }
    
    public void displayAllStudentsSorted() {
        System.out.println("\n=== Students and Grades (Alphabetical Order) ===");
        TreeMap<String, Double> sortedGrades = new TreeMap<>(studentGrades);
        
        for (Map.Entry<String, Double> entry : sortedGrades.entrySet()) {
            System.out.printf("%s: %.2f%n", entry.getKey(), entry.getValue());
        }
    }
    
    public void displayStatistics() {
        if (studentGrades.isEmpty()) {
            System.out.println("No students in the system.");
            return;
        }
        
        double sum = 0;
        double highest = Double.MIN_VALUE;
        double lowest = Double.MAX_VALUE;
        String topStudent = "";
        String lowestStudent = "";
        
        for (Map.Entry<String, Double> entry : studentGrades.entrySet()) {
            double grade = entry.getValue();
            sum += grade;
            
            if (grade > highest) {
                highest = grade;
                topStudent = entry.getKey();
            }
            if (grade < lowest) {
                lowest = grade;
                lowestStudent = entry.getKey();
            }
        }
        
        System.out.println("\n=== Class Statistics ===");
        System.out.println("Total Students: " + studentGrades.size());
        System.out.printf("Average Grade: %.2f%n", sum / studentGrades.size());
        System.out.printf("Highest Grade: %.2f (%s)%n", highest, topStudent);
        System.out.printf("Lowest Grade: %.2f (%s)%n", lowest, lowestStudent);
    }
    
    public static void main(String[] args) {
        StudentGradeTracker tracker = new StudentGradeTracker();
        
        System.out.println("=== Adding Students ===");
        tracker.addStudent("Alice", 85.5);
        tracker.addStudent("Bob", 72.0);
        tracker.addStudent("Charlie", 91.5);
        tracker.addStudent("Diana", 78.0);
        tracker.addStudent("Eve", 88.5);
        tracker.addStudent("Frank", 65.0);
        
        tracker.displayAllStudentsSorted();
        
        System.out.println("\n=== Updating Grades (Re-take Tests) ===");
        tracker.updateGrade("Bob", 82.0);
        tracker.updateGrade("Frank", 75.5);
        tracker.updateGrade("Unknown", 90.0);
        
        System.out.println("\n=== Removing Dropped Students ===");
        tracker.removeStudent("Diana");
        tracker.removeStudent("Unknown");
        
        tracker.displayAllStudentsSorted();
        tracker.displayStatistics();
    }
}
