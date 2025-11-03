package SchoolResultsApplication;

/**
 * Main Application for School Results Management System
 * Demonstrates Class Diagram, Object Diagram, and Sequence Diagram concepts
 */
public class SchoolResultsApp {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║       SCHOOL RESULTS MANAGEMENT SYSTEM                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        // Create GradeCalculator (Service object)
        GradeCalculator calculator = new GradeCalculator();
        
        // ===== Scenario 1: Student John =====
        System.out.println("===== Creating Student: John =====\n");
        
        // Create Student object
        Student john = new Student("S001", "John", "john@school.com");
        
        // Create Subject objects (Aggregation - subjects can exist independently)
        Subject math = new Subject("Mathematics", 90, 100);
        Subject science = new Subject("Science", 85, 100);
        Subject english = new Subject("English", 88, 100);
        Subject history = new Subject("History", 92, 100);
        
        // Add subjects to student
        john.addSubject(math);
        john.addSubject(science);
        john.addSubject(english);
        john.addSubject(history);
        
        // Generate report (Sequence diagram demonstration)
        calculator.generateReport(john);
        
        // ===== Scenario 2: Student Alice =====
        System.out.println("\n===== Creating Student: Alice =====\n");
        
        Student alice = new Student("S002", "Alice", "alice@school.com");
        
        // Add subjects for Alice
        alice.addSubject(new Subject("Mathematics", 95, 100));
        alice.addSubject(new Subject("Science", 88, 100));
        alice.addSubject(new Subject("English", 90, 100));
        alice.addSubject(new Subject("History", 85, 100));
        
        // Generate report
        calculator.generateReport(alice);
        
        // ===== Scenario 3: Student Bob with fewer subjects =====
        System.out.println("\n===== Creating Student: Bob =====\n");
        
        Student bob = new Student("S003", "Bob", "bob@school.com");
        
        bob.addSubject(new Subject("Mathematics", 75, 100));
        bob.addSubject(new Subject("Science", 70, 100));
        bob.addSubject(new Subject("English", 78, 100));
        
        calculator.generateReport(bob);
        
        // ===== Compare Students =====
        calculator.compareStudents(john, alice);
        calculator.compareStudents(alice, bob);
        
        // ===== Demonstrate Aggregation =====
        System.out.println("\n===== Demonstrating Aggregation =====");
        System.out.println("Subjects exist independently of students.");
        System.out.println("The same subject 'math' can exist even if we remove it from John's list.\n");
        
        System.out.println("Before removal:");
        System.out.println("John has " + john.getSubjects().size() + " subjects");
        
        john.removeSubject(math);
        
        System.out.println("\nAfter removal:");
        System.out.println("John has " + john.getSubjects().size() + " subjects");
        System.out.println("But 'math' subject still exists:");
        math.displayInfo();
        
        // ===== Interactive Grade Checking =====
        System.out.println("\n===== Individual Subject Performance =====");
        
        for (Subject subject : alice.getSubjects()) {
            System.out.println("\n" + alice.getName() + "'s performance in " + subject.getSubjectName() + ":");
            subject.displayInfo();
        }
        
        // ===== Summary Statistics =====
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                    SUMMARY STATISTICS                      ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        System.out.println("\nStudent Rankings by Overall Percentage:");
        System.out.printf("1. %s - %.2f%% (Grade %s)%n", 
                         alice.getName(), 
                         calculator.calculateOverallPercentage(alice),
                         calculator.calculateOverallGrade(alice));
        System.out.printf("2. %s - %.2f%% (Grade %s)%n", 
                         john.getName(), 
                         calculator.calculateOverallPercentage(john),
                         calculator.calculateOverallGrade(john));
        System.out.printf("3. %s - %.2f%% (Grade %s)%n", 
                         bob.getName(), 
                         calculator.calculateOverallPercentage(bob),
                         calculator.calculateOverallGrade(bob));
        
        System.out.println("\n═══════════════════════════════════════════════════════════════");
        System.out.println("          Thank you for using School Results System!");
        System.out.println("═══════════════════════════════════════════════════════════════\n");
    }
}
