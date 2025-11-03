package SchoolResultsApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Student class represents a student with multiple subjects
 * Demonstrates Aggregation - Student HAS subjects
 */
public class Student {
    private String studentId;
    private String name;
    private String email;
    private List<Subject> subjects;
    
    /**
     * Constructor to create a Student
     * @param studentId Unique student identifier
     * @param name Student's name
     * @param email Student's email
     */
    public Student(String studentId, String name, String email) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.subjects = new ArrayList<>();
    }
    
    /**
     * Add a subject to student's list
     * @param subject Subject to add
     */
    public void addSubject(Subject subject) {
        subjects.add(subject);
        System.out.println(subject.getSubjectName() + " added for " + name);
    }
    
    /**
     * Remove a subject from student's list
     * @param subject Subject to remove
     */
    public void removeSubject(Subject subject) {
        subjects.remove(subject);
        System.out.println(subject.getSubjectName() + " removed for " + name);
    }
    
    /**
     * Get all subjects
     * @return List of subjects
     */
    public List<Subject> getSubjects() {
        return subjects;
    }
    
    /**
     * Display student information
     */
    public void displayInfo() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              STUDENT INFORMATION                           ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Total Subjects: " + subjects.size());
    }
    
    /**
     * Display all subjects with details
     */
    public void displaySubjects() {
        System.out.println("\n--- Subjects ---");
        for (Subject subject : subjects) {
            subject.displayInfo();
        }
    }
    
    // Getters
    public String getStudentId() {
        return studentId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
}
