/**
 * Student class demonstrating static, this, final, and instanceof concepts
 * Complete university student management system
 */
public class UniversityStudentManagement {
    // Static variables - shared across all students
    private static String universityName = "Global University";
    private static int totalStudents = 0;
    private static double totalFees = 0.0;
    
    // Instance variables
    private String name;
    private final String rollNumber; // final - cannot be changed
    private String grade;
    private String course;
    private double fees;
    private boolean isActive;
    
    // Constructor using 'this' to initialize name, rollNumber, and grade
    public UniversityStudentManagement(String name, String rollNumber, String grade, String course, double fees) {
        // Using 'this' to distinguish between parameter and instance variable
        this.name = name;
        this.rollNumber = rollNumber; // final variable initialization
        this.grade = grade;
        this.course = course;
        this.fees = fees;
        this.isActive = true;
        
        // Update static counters
        totalStudents++;
        totalFees += fees;
        
        System.out.println("Student enrolled: " + this.name + " (Roll: " + this.rollNumber + ")");
    }
    
    // Overloaded constructor with default course
    public UniversityStudentManagement(String name, String rollNumber, String grade) {
        // Using 'this' to call another constructor (constructor chaining)
        this(name, rollNumber, grade, "General Studies", 5000.0);
        System.out.println("Default course and fees assigned.");
    }
    
    // Constructor with minimal info
    public UniversityStudentManagement(String name, String grade) {
        this(name, generateRollNumber(), grade, "Undecided", 0.0);
        System.out.println("Auto-generated roll number assigned.");
    }
    
    // Static method to display total students
    public static void displayTotalStudents() {
        System.out.println("=== University Statistics ===");
        System.out.println("University: " + universityName);
        System.out.println("Total Students Enrolled: " + totalStudents);
        System.out.printf("Total Fees Collected: $%.2f%n", totalFees);
        if (totalStudents > 0) {
            System.out.printf("Average Fees per Student: $%.2f%n", totalFees / totalStudents);
        }
        System.out.println("============================");
    }
    
    // Static method to get university name
    public static String getUniversityName() {
        return universityName;
    }
    
    // Static method to update university name
    public static void updateUniversityName(String newUniversityName) {
        if (newUniversityName != null && !newUniversityName.trim().isEmpty()) {
            String oldName = universityName;
            universityName = newUniversityName;
            System.out.println("University name updated: " + oldName + " -> " + universityName);
            System.out.println("This change affects all " + totalStudents + " students!");
        } else {
            System.out.println("Invalid university name provided!");
        }
    }
    
    // Method to update grade using 'this'
    public void updateGrade(String newGrade) {
        if (newGrade != null && !newGrade.trim().isEmpty()) {
            String oldGrade = this.grade;
            this.grade = newGrade;
            System.out.println("Grade updated for " + this.name + " (Roll: " + this.rollNumber + 
                             "): " + oldGrade + " -> " + this.grade);
        } else {
            System.out.println("Invalid grade provided!");
        }
    }
    
    // Method to update course using 'this'
    public void updateCourse(String newCourse, double newFees) {
        if (newCourse != null && !newCourse.trim().isEmpty()) {
            // Update total fees
            totalFees -= this.fees;
            totalFees += newFees;
            
            String oldCourse = this.course;
            double oldFees = this.fees;
            this.course = newCourse;
            this.fees = newFees;
            
            System.out.println("Course updated for " + this.name + ": " + oldCourse + " -> " + this.course);
            System.out.printf("Fees updated: $%.2f -> $%.2f%n", oldFees, this.fees);
        } else {
            System.out.println("Invalid course name provided!");
        }
    }
    
    // Method to pay fees using 'this'
    public void payFees(double amount) {
        if (amount > 0 && amount <= this.fees) {
            this.fees -= amount;
            totalFees -= amount;
            System.out.printf("Payment of $%.2f received from %s. Remaining fees: $%.2f%n", 
                             amount, this.name, this.fees);
        } else if (amount > this.fees) {
            System.out.println("Payment amount exceeds remaining fees!");
        } else {
            System.out.println("Invalid payment amount!");
        }
    }
    
    // Method to suspend/reactivate student using 'this'
    public void updateStatus(boolean active) {
        this.isActive = active;
        System.out.println("Student " + this.name + " status: " + 
                         (this.isActive ? "Active" : "Suspended"));
    }
    
    // Method to calculate GPA (simulated) using 'this'
    public double calculateGPA() {
        // Simple grade to GPA conversion
        switch (this.grade.toUpperCase()) {
            case "A": return 4.0;
            case "B": return 3.0;
            case "C": return 2.0;
            case "D": return 1.0;
            default: return 0.0;
        }
    }
    
    // Method to display student details
    public void displayStudentDetails() {
        System.out.println("=== Student Details ===");
        System.out.println("University: " + universityName); // static variable access
        System.out.println("Name: " + this.name);
        System.out.println("Roll Number: " + this.rollNumber); // final variable access
        System.out.println("Grade: " + this.grade);
        System.out.println("Course: " + this.course);
        System.out.printf("Fees: $%.2f%n", this.fees);
        System.out.printf("GPA: %.1f%n", this.calculateGPA());
        System.out.println("Status: " + (this.isActive ? "Active" : "Suspended"));
        System.out.println("======================");
    }
    
    // Static method to validate and process student using instanceof
    public static void processStudent(Object obj) {
        // Using instanceof to check object type
        if (obj instanceof UniversityStudentManagement) {
            System.out.println("✓ Object is a valid Student instance");
            UniversityStudentManagement student = (UniversityStudentManagement) obj; // Safe casting
            student.displayStudentDetails();
        } else {
            System.out.println("✗ Object is not a Student instance!");
            System.out.println("Object type: " + (obj != null ? obj.getClass().getSimpleName() : "null"));
        }
    }
    
    // Method to compare students using 'this'
    public boolean isSameStudent(UniversityStudentManagement other) {
        // Using 'this' to refer to current object and final rollNumber for comparison
        return other != null && this.rollNumber.equals(other.rollNumber);
    }
    
    // Method to check if same course using 'this'
    public boolean isInSameCourse(UniversityStudentManagement other) {
        return other != null && this.course.equalsIgnoreCase(other.course);
    }
    
    // Method to check if better grade using 'this'
    public boolean hasBetterGradeThan(UniversityStudentManagement other) {
        return other != null && this.calculateGPA() > other.calculateGPA();
    }
    
    // Utility method to generate unique roll number
    private static String generateRollNumber() {
        return "STU" + String.format("%06d", totalStudents + 1);
    }
    
    // Getter methods using 'this'
    public String getName() {
        return this.name;
    }
    
    public String getRollNumber() {
        return this.rollNumber; // final variable - read-only access
    }
    
    public String getGrade() {
        return this.grade;
    }
    
    public String getCourse() {
        return this.course;
    }
    
    public double getFees() {
        return this.fees;
    }
    
    public boolean isActive() {
        return this.isActive;
    }
    
    // Static getter methods
    public static int getTotalStudents() {
        return totalStudents;
    }
    
    public static double getTotalFees() {
        return totalFees;
    }
    
    // Method to get student summary using 'this'
    public String getStudentSummary() {
        return String.format("%s (Roll: %s) - %s, Grade: %s, GPA: %.1f", 
                           this.name, this.rollNumber, this.course, this.grade, this.calculateGPA());
    }
    
    @Override
    public String toString() {
        return String.format("Student{name='%s', roll='%s', grade='%s', course='%s'}", 
                           name, rollNumber, grade, course);
    }
    
    // Main method for testing
    public static void main(String[] args) {
        System.out.println("=== University Student Management Demo ===\\n");
        
        System.out.println("1. Initial university state:");
        System.out.println("University: " + getUniversityName());
        displayTotalStudents();
        
        System.out.println("\\n2. Enrolling students (using 'this' in constructors):");
        UniversityStudentManagement student1 = new UniversityStudentManagement("Alice Johnson", "STU001", "A", "Computer Science", 8000.0);
        UniversityStudentManagement student2 = new UniversityStudentManagement("Bob Smith", "STU002", "B");
        UniversityStudentManagement student3 = new UniversityStudentManagement("Carol Davis", "A");
        
        System.out.println("\\n3. University statistics after enrollment:");
        displayTotalStudents();
        
        System.out.println("\\n4. Testing instanceof with valid student:");
        processStudent(student1);
        
        System.out.println("\\n5. Testing instanceof with invalid objects:");
        processStudent("Not a student");
        processStudent(456);
        processStudent(null);
        
        System.out.println("\\n6. Student operations using 'this':");
        student1.updateGrade("A+");
        student2.updateCourse("Mathematics", 6000.0);
        student3.payFees(2000.0);
        student1.payFees(3000.0);
        
        System.out.println("\\n7. Updated student details:");
        processStudent(student1);
        processStudent(student2);
        
        System.out.println("\\n8. Testing final variable (rollNumber cannot be changed):");
        System.out.println("Student1 Roll Number: " + student1.getRollNumber());
        // student1.rollNumber = "NEWROLL001"; // This would cause compilation error
        System.out.println("Note: Roll Number is final and cannot be modified after initialization");
        
        System.out.println("\\n9. Modifying static variable (affects all students):");
        updateUniversityName("Advanced Learning Institute");
        
        System.out.println("\\n10. All students now show updated university name:");
        processStudent(student1);
        processStudent(student3);
        
        System.out.println("\\n11. Student comparisons using 'this':");
        UniversityStudentManagement student4 = new UniversityStudentManagement("David Wilson", "STU004", "B", "Physics", 7000.0);
        System.out.println("Are student1 and student4 the same? " + student1.isSameStudent(student4));
        System.out.println("Are student1 and student2 in same course? " + student1.isInSameCourse(student2));
        System.out.println("Does student1 have better grade than student2? " + student1.hasBetterGradeThan(student2));
        
        System.out.println("\\n12. Multiple instanceof checks with mixed objects:");
        Object[] objects = {student1, student2, "String", 789, student3, null, new java.util.ArrayList()};
        
        for (int i = 0; i < objects.length; i++) {
            System.out.printf("Object %d: ", i + 1);
            if (objects[i] instanceof UniversityStudentManagement) {
                UniversityStudentManagement stu = (UniversityStudentManagement) objects[i];
                System.out.println("Student - " + stu.getStudentSummary());
            } else {
                System.out.println("Not a Student - " + 
                                 (objects[i] != null ? objects[i].getClass().getSimpleName() : "null"));
            }
        }
        
        System.out.println("\\n13. Student status management:");
        student2.updateStatus(false); // Suspend student
        student2.displayStudentDetails();
        student2.updateStatus(true);  // Reactivate student
        
        System.out.println("\\n14. Final university statistics:");
        displayTotalStudents();
        
        System.out.println("\\n=== Concepts Demonstrated ===");
        System.out.println("✓ Static: universityName and totalStudents shared across all instances");
        System.out.println("✓ This: Used in constructors and methods to refer to current object");
        System.out.println("✓ Final: Roll Number cannot be changed once assigned");
        System.out.println("✓ Instanceof: Safe type checking before casting and operations");
    }
}
