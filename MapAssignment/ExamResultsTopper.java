import java.util.*;

public class ExamResultsTopper {
    private Map<String, Map<String, Integer>> subjectResults;
    
    public ExamResultsTopper() {
        subjectResults = new HashMap<>();
    }
    
    public void addSubject(String subject) {
        if (!subjectResults.containsKey(subject)) {
            subjectResults.put(subject, new HashMap<>());
            System.out.println("Subject added: " + subject);
        }
    }
    
    public void addStudentScore(String subject, String studentName, int score) {
        if (!subjectResults.containsKey(subject)) {
            addSubject(subject);
        }
        
        if (score < 0 || score > 100) {
            System.out.println("Invalid score. Must be between 0 and 100.");
            return;
        }
        
        subjectResults.get(subject).put(studentName, score);
        System.out.printf("Added: %s scored %d in %s%n", studentName, score, subject);
    }
    
    public String getTopper(String subject) {
        if (!subjectResults.containsKey(subject)) {
            return null;
        }
        
        Map<String, Integer> students = subjectResults.get(subject);
        String topper = null;
        int maxScore = -1;
        
        for (Map.Entry<String, Integer> entry : students.entrySet()) {
            if (entry.getValue() > maxScore) {
                maxScore = entry.getValue();
                topper = entry.getKey();
            }
        }
        
        return topper;
    }
    
    public void displaySubjectResults(String subject) {
        if (!subjectResults.containsKey(subject)) {
            System.out.println("Subject not found: " + subject);
            return;
        }
        
        System.out.println("\n=== " + subject + " Results ===");
        Map<String, Integer> students = subjectResults.get(subject);
        
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(students.entrySet());
        sorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        int rank = 1;
        for (Map.Entry<String, Integer> entry : sorted) {
            String badge = rank == 1 ? " [TOPPER]" : "";
            System.out.printf("%d. %s: %d%s%n", rank++, entry.getKey(), entry.getValue(), badge);
        }
    }
    
    public void displayAllSubjectToppers() {
        System.out.println("\n=== Subject-wise Toppers ===");
        
        TreeMap<String, Map<String, Integer>> sortedSubjects = new TreeMap<>(subjectResults);
        
        for (String subject : sortedSubjects.keySet()) {
            String topper = getTopper(subject);
            if (topper != null) {
                int score = subjectResults.get(subject).get(topper);
                System.out.printf("%s: %s (%d)%n", subject, topper, score);
            }
        }
    }
    
    public void displayOverallTopper() {
        System.out.println("\n=== Overall Topper ===");
        
        Map<String, Integer> studentTotals = new HashMap<>();
        Map<String, Integer> studentSubjectCount = new HashMap<>();
        
        for (Map.Entry<String, Map<String, Integer>> subjectEntry : subjectResults.entrySet()) {
            for (Map.Entry<String, Integer> studentEntry : subjectEntry.getValue().entrySet()) {
                String student = studentEntry.getKey();
                int score = studentEntry.getValue();
                studentTotals.put(student, studentTotals.getOrDefault(student, 0) + score);
                studentSubjectCount.put(student, studentSubjectCount.getOrDefault(student, 0) + 1);
            }
        }
        
        String overallTopper = null;
        double maxAverage = -1;
        
        for (String student : studentTotals.keySet()) {
            double average = (double) studentTotals.get(student) / studentSubjectCount.get(student);
            if (average > maxAverage) {
                maxAverage = average;
                overallTopper = student;
            }
        }
        
        if (overallTopper != null) {
            System.out.printf("Overall Topper: %s with average %.2f%n", overallTopper, maxAverage);
        }
    }
    
    public void displayStudentReport(String studentName) {
        System.out.println("\n=== Report Card: " + studentName + " ===");
        
        int totalScore = 0;
        int subjectCount = 0;
        
        for (Map.Entry<String, Map<String, Integer>> entry : subjectResults.entrySet()) {
            String subject = entry.getKey();
            Map<String, Integer> students = entry.getValue();
            
            if (students.containsKey(studentName)) {
                int score = students.get(studentName);
                String topperStatus = getTopper(subject).equals(studentName) ? " [TOPPER]" : "";
                System.out.printf("%s: %d%s%n", subject, score, topperStatus);
                totalScore += score;
                subjectCount++;
            }
        }
        
        if (subjectCount > 0) {
            System.out.printf("\nTotal: %d / %d%n", totalScore, subjectCount * 100);
            System.out.printf("Average: %.2f%n", (double) totalScore / subjectCount);
        } else {
            System.out.println("No records found for " + studentName);
        }
    }
    
    public static void main(String[] args) {
        ExamResultsTopper exam = new ExamResultsTopper();
        
        System.out.println("=== Adding Exam Results ===");
        
        exam.addStudentScore("Mathematics", "Alice", 95);
        exam.addStudentScore("Mathematics", "Bob", 87);
        exam.addStudentScore("Mathematics", "Charlie", 92);
        exam.addStudentScore("Mathematics", "Diana", 88);
        
        exam.addStudentScore("Physics", "Alice", 88);
        exam.addStudentScore("Physics", "Bob", 94);
        exam.addStudentScore("Physics", "Charlie", 85);
        exam.addStudentScore("Physics", "Diana", 91);
        
        exam.addStudentScore("Chemistry", "Alice", 92);
        exam.addStudentScore("Chemistry", "Bob", 79);
        exam.addStudentScore("Chemistry", "Charlie", 96);
        exam.addStudentScore("Chemistry", "Diana", 84);
        
        exam.addStudentScore("English", "Alice", 89);
        exam.addStudentScore("English", "Bob", 92);
        exam.addStudentScore("English", "Charlie", 78);
        exam.addStudentScore("English", "Diana", 95);
        
        exam.displaySubjectResults("Mathematics");
        exam.displaySubjectResults("Physics");
        exam.displaySubjectResults("Chemistry");
        exam.displaySubjectResults("English");
        
        exam.displayAllSubjectToppers();
        exam.displayOverallTopper();
        
        System.out.println("\n=== Individual Student Reports ===");
        exam.displayStudentReport("Alice");
        exam.displayStudentReport("Charlie");
    }
}
