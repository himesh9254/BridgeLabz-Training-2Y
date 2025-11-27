import java.util.*;

public class StudentAttendanceTracker {
    private Map<String, Integer> attendanceRecord;
    private int totalClasses;
    
    public StudentAttendanceTracker() {
        attendanceRecord = new HashMap<>();
        totalClasses = 0;
    }
    
    public void addStudent(String name) {
        if (!attendanceRecord.containsKey(name)) {
            attendanceRecord.put(name, 0);
            System.out.println("Student added: " + name);
        } else {
            System.out.println("Student already exists: " + name);
        }
    }
    
    public void markAttendance(String name) {
        if (!attendanceRecord.containsKey(name)) {
            System.out.println("Student not found: " + name);
            return;
        }
        attendanceRecord.put(name, attendanceRecord.get(name) + 1);
    }
    
    public void conductClass(String... presentStudents) {
        totalClasses++;
        System.out.println("\n=== Class " + totalClasses + " Conducted ===");
        System.out.print("Present: ");
        for (String student : presentStudents) {
            markAttendance(student);
            System.out.print(student + " ");
        }
        System.out.println();
    }
    
    public double getAttendancePercentage(String name) {
        if (!attendanceRecord.containsKey(name) || totalClasses == 0) {
            return 0.0;
        }
        return (attendanceRecord.get(name) * 100.0) / totalClasses;
    }
    
    public void displayAllAttendance() {
        System.out.println("\n=== Attendance Report ===");
        System.out.println("Total Classes Conducted: " + totalClasses);
        System.out.println("\nStudent-wise Attendance:");
        
        TreeMap<String, Integer> sortedAttendance = new TreeMap<>(attendanceRecord);
        for (Map.Entry<String, Integer> entry : sortedAttendance.entrySet()) {
            double percentage = getAttendancePercentage(entry.getKey());
            System.out.printf("%s: %d/%d (%.2f%%)%n", 
                entry.getKey(), entry.getValue(), totalClasses, percentage);
        }
    }
    
    public void displayBelowThreshold(double threshold) {
        System.out.println("\n=== Students Below " + threshold + "% Attendance ===");
        boolean found = false;
        
        for (Map.Entry<String, Integer> entry : attendanceRecord.entrySet()) {
            double percentage = getAttendancePercentage(entry.getKey());
            if (percentage < threshold) {
                System.out.printf("%s: %.2f%%%n", entry.getKey(), percentage);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("All students meet the attendance threshold!");
        }
    }
    
    public void displayAboveThreshold(double threshold) {
        System.out.println("\n=== Students Above " + threshold + "% Attendance ===");
        
        for (Map.Entry<String, Integer> entry : attendanceRecord.entrySet()) {
            double percentage = getAttendancePercentage(entry.getKey());
            if (percentage >= threshold) {
                System.out.printf("%s: %.2f%%%n", entry.getKey(), percentage);
            }
        }
    }
    
    public static void main(String[] args) {
        StudentAttendanceTracker tracker = new StudentAttendanceTracker();
        
        System.out.println("=== Adding Students ===");
        tracker.addStudent("Alice");
        tracker.addStudent("Bob");
        tracker.addStudent("Charlie");
        tracker.addStudent("Diana");
        tracker.addStudent("Eve");
        
        tracker.conductClass("Alice", "Bob", "Charlie", "Diana", "Eve");
        tracker.conductClass("Alice", "Bob", "Charlie", "Diana");
        tracker.conductClass("Alice", "Charlie", "Eve");
        tracker.conductClass("Alice", "Bob", "Charlie", "Diana", "Eve");
        tracker.conductClass("Alice", "Bob", "Diana");
        tracker.conductClass("Alice", "Bob", "Charlie", "Eve");
        tracker.conductClass("Alice", "Charlie", "Diana", "Eve");
        tracker.conductClass("Alice", "Bob", "Charlie", "Diana", "Eve");
        tracker.conductClass("Alice", "Bob");
        tracker.conductClass("Alice", "Bob", "Charlie", "Diana", "Eve");
        
        tracker.displayAllAttendance();
        
        tracker.displayBelowThreshold(75.0);
        tracker.displayAboveThreshold(80.0);
    }
}
