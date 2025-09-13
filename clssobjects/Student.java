public class Student {
    // Attributes
    private String name;
    private int rollNumber;
    private double marks;
    
    // Constructor
    public Student(String name, int rollNumber, double marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }
    
    // Method to calculate grade based on marks
    public char calculateGrade() {
        if (marks >= 90) {
            return 'A';
        } else if (marks >= 80) {
            return 'B';
        } else if (marks >= 70) {
            return 'C';
        } else if (marks >= 60) {
            return 'D';
        } else if (marks >= 50) {
            return 'E';
        } else {
            return 'F';
        }
    }
    
    // Method to get grade description
    public String getGradeDescription(char grade) {
        switch (grade) {
            case 'A': return "Excellent";
            case 'B': return "Very Good";
            case 'C': return "Good";
            case 'D': return "Satisfactory";
            case 'E': return "Pass";
            case 'F': return "Fail";
            default: return "Invalid Grade";
        }
    }
    
    // Method to display student details and grade
    public void displayStudentDetails() {
        char grade = calculateGrade();
        String gradeDesc = getGradeDescription(grade);
        
        System.out.println("=== Student Report ===");
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Marks: " + marks + "%");
        System.out.println("Grade: " + grade + " (" + gradeDesc + ")");
        System.out.println("=====================");
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getRollNumber() {
        return rollNumber;
    }
    
    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }
    
    public double getMarks() {
        return marks;
    }
    
    public void setMarks(double marks) {
        this.marks = marks;
    }
    
    // Main method to test the class
    public static void main(String[] args) {
        // Create student objects
        Student student1 = new Student("Alice Johnson", 101, 95.5);
        Student student2 = new Student("Bob Smith", 102, 78.0);
        Student student3 = new Student("Charlie Brown", 103, 65.5);
        Student student4 = new Student("Diana Prince", 104, 45.0);
        Student student5 = new Student("Eve Wilson", 105, 88.5);
        
        // Display student details and grades
        System.out.println("=== Class Report ===");
        student1.displayStudentDetails();
        student2.displayStudentDetails();
        student3.displayStudentDetails();
        student4.displayStudentDetails();
        student5.displayStudentDetails();
    }
}
