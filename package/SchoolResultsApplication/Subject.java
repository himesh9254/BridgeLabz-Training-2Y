package SchoolResultsApplication;

/**
 * Subject class represents a subject with marks and grade calculation
 * Part of School Results Application
 */
public class Subject {
    private String subjectName;
    private double marks;
    private double maxMarks;
    
    /**
     * Constructor to create a Subject
     * @param subjectName Name of the subject
     * @param marks Marks obtained
     * @param maxMarks Maximum marks possible
     */
    public Subject(String subjectName, double marks, double maxMarks) {
        this.subjectName = subjectName;
        this.marks = marks;
        this.maxMarks = maxMarks;
    }
    
    /**
     * Calculate percentage for this subject
     * @return Percentage value
     */
    public double getPercentage() {
        return (marks / maxMarks) * 100;
    }
    
    /**
     * Calculate grade based on percentage
     * @return Grade (A, B, C, D, F)
     */
    public String getGrade() {
        double percentage = getPercentage();
        if (percentage >= 90) return "A";
        else if (percentage >= 80) return "B";
        else if (percentage >= 70) return "C";
        else if (percentage >= 60) return "D";
        else return "F";
    }
    
    /**
     * Display subject information
     */
    public void displayInfo() {
        System.out.printf("Subject: %-15s | Marks: %.2f/%.2f | Percentage: %.2f%% | Grade: %s%n",
                          subjectName, marks, maxMarks, getPercentage(), getGrade());
    }
    
    // Getters
    public String getSubjectName() {
        return subjectName;
    }
    
    public double getMarks() {
        return marks;
    }
    
    public double getMaxMarks() {
        return maxMarks;
    }
    
    // Setters
    public void setMarks(double marks) {
        this.marks = marks;
    }
}
