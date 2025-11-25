import java.util.*;

class StudentApplicant implements Comparable<StudentApplicant> {
    private String studentId;
    private String name;
    private double marks;
    private String stream;
    private boolean interviewed;

    public StudentApplicant(String studentId, String name, double marks, String stream) {
        this.studentId = studentId;
        this.name = name;
        this.marks = marks;
        this.stream = stream;
        this.interviewed = false;
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public double getMarks() { return marks; }
    public String getStream() { return stream; }
    public boolean isInterviewed() { return interviewed; }
    public void setInterviewed(boolean interviewed) { this.interviewed = interviewed; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentApplicant that = (StudentApplicant) o;
        return studentId.equals(that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    @Override
    public int compareTo(StudentApplicant other) {
        int marksCompare = Double.compare(other.marks, this.marks);
        if (marksCompare != 0) return marksCompare;
        return this.studentId.compareTo(other.studentId);
    }

    @Override
    public String toString() {
        return String.format("Student[%s, %s, %.2f%%, %s]", studentId, name, marks, stream);
    }
}

public class CollegeAdmissionSystem {
    private List<StudentApplicant> allApplicants;
    private Set<StudentApplicant> shortlistedStudents;
    private Queue<StudentApplicant> interviewQueue;
    private TreeSet<StudentApplicant> meritList;
    private double minimumMarks;

    public CollegeAdmissionSystem(double minimumMarks) {
        this.allApplicants = new ArrayList<>();
        this.shortlistedStudents = new HashSet<>();
        this.interviewQueue = new LinkedList<>();
        this.meritList = new TreeSet<>();
        this.minimumMarks = minimumMarks;
    }

    public void acceptApplication(StudentApplicant student) {
        allApplicants.add(student);
        System.out.println("Application received: " + student);
    }

    public void shortlistEligibleStudents() {
        System.out.println("\n=== Shortlisting Students (Min marks: " + minimumMarks + "%) ===");
        for (StudentApplicant student : allApplicants) {
            if (student.getMarks() >= minimumMarks) {
                if (shortlistedStudents.add(student)) {
                    System.out.println("Shortlisted: " + student.getName() + " (" + student.getMarks() + "%)");
                }
            } else {
                System.out.println("Not eligible: " + student.getName() + " (" + student.getMarks() + "%)");
            }
        }
    }

    public void queueForInterview() {
        System.out.println("\n=== Queueing Shortlisted Students for Interview ===");
        for (StudentApplicant student : shortlistedStudents) {
            interviewQueue.add(student);
            System.out.println("Queued: " + student.getName());
        }
    }

    public void conductInterviews() {
        System.out.println("\n=== Conducting Interviews ===");
        Random random = new Random();

        while (!interviewQueue.isEmpty()) {
            StudentApplicant student = interviewQueue.poll();
            student.setInterviewed(true);

            boolean passed = random.nextInt(10) > 2;

            if (passed) {
                meritList.add(student);
                System.out.println("PASSED: " + student.getName() + " - Added to merit list");
            } else {
                System.out.println("FAILED: " + student.getName() + " - Interview not cleared");
            }
        }
    }

    public void displayAllApplicants() {
        System.out.println("\n=== All Applicants (" + allApplicants.size() + ") ===");
        for (StudentApplicant student : allApplicants) {
            System.out.println("  " + student);
        }
    }

    public void displayShortlistedStudents() {
        System.out.println("\n=== Shortlisted Students (" + shortlistedStudents.size() + ") ===");
        for (StudentApplicant student : shortlistedStudents) {
            System.out.println("  " + student);
        }
    }

    public void displayInterviewQueue() {
        System.out.println("\n=== Interview Queue ===");
        if (interviewQueue.isEmpty()) {
            System.out.println("  No students in queue");
            return;
        }
        for (StudentApplicant student : interviewQueue) {
            System.out.println("  " + student.getName());
        }
    }

    public void displayMeritList() {
        System.out.println("\n=== FINAL MERIT LIST (Sorted by Marks) ===");
        int rank = 1;
        for (StudentApplicant student : meritList) {
            System.out.println(rank + ". " + student);
            rank++;
        }
        System.out.println("Total selected: " + meritList.size());
    }

    public static void main(String[] args) {
        CollegeAdmissionSystem system = new CollegeAdmissionSystem(60.0);

        System.out.println("=== Accepting Applications ===");
        system.acceptApplication(new StudentApplicant("STU001", "Alice", 85.5, "Science"));
        system.acceptApplication(new StudentApplicant("STU002", "Bob", 72.0, "Commerce"));
        system.acceptApplication(new StudentApplicant("STU003", "Charlie", 55.0, "Arts"));
        system.acceptApplication(new StudentApplicant("STU004", "Diana", 91.5, "Science"));
        system.acceptApplication(new StudentApplicant("STU005", "Eve", 68.0, "Commerce"));
        system.acceptApplication(new StudentApplicant("STU006", "Frank", 45.0, "Arts"));
        system.acceptApplication(new StudentApplicant("STU007", "Grace", 78.5, "Science"));
        system.acceptApplication(new StudentApplicant("STU008", "Henry", 62.0, "Commerce"));

        system.displayAllApplicants();
        system.shortlistEligibleStudents();
        system.displayShortlistedStudents();
        system.queueForInterview();
        system.displayInterviewQueue();
        system.conductInterviews();
        system.displayMeritList();
    }
}
