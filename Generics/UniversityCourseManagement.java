import java.util.ArrayList;
import java.util.List;

abstract class CourseType {
    private String evaluationMethod;

    public CourseType(String evaluationMethod) {
        this.evaluationMethod = evaluationMethod;
    }

    public String getEvaluationMethod() {
        return evaluationMethod;
    }

    public abstract String getTypeName();
}

class ExamCourse extends CourseType {
    private int examDuration;

    public ExamCourse(int examDuration) {
        super("Written Examination");
        this.examDuration = examDuration;
    }

    public int getExamDuration() {
        return examDuration;
    }

    @Override
    public String getTypeName() {
        return "Exam-Based";
    }
}

class AssignmentCourse extends CourseType {
    private int numberOfAssignments;

    public AssignmentCourse(int numberOfAssignments) {
        super("Assignment Submission");
        this.numberOfAssignments = numberOfAssignments;
    }

    public int getNumberOfAssignments() {
        return numberOfAssignments;
    }

    @Override
    public String getTypeName() {
        return "Assignment-Based";
    }
}

class ResearchCourse extends CourseType {
    private String researchTopic;

    public ResearchCourse(String researchTopic) {
        super("Research Paper & Thesis");
        this.researchTopic = researchTopic;
    }

    public String getResearchTopic() {
        return researchTopic;
    }

    @Override
    public String getTypeName() {
        return "Research-Based";
    }
}

class Course<T extends CourseType> {
    private String courseName;
    private String department;
    private T courseType;
    private List<String> enrolledStudents = new ArrayList<>();

    public Course(String courseName, String department, T courseType) {
        this.courseName = courseName;
        this.department = department;
        this.courseType = courseType;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDepartment() {
        return department;
    }

    public T getCourseType() {
        return courseType;
    }

    public void enrollStudent(String studentName) {
        enrolledStudents.add(studentName);
    }

    public void displayCourseInfo() {
        System.out.println("Course: " + courseName);
        System.out.println("Department: " + department);
        System.out.println("Type: " + courseType.getTypeName());
        System.out.println("Evaluation: " + courseType.getEvaluationMethod());
        System.out.println("Enrolled Students: " + enrolledStudents.size());
    }
}

class CourseManager {
    public static void displayAllCourses(List<? extends Course<? extends CourseType>> courses) {
        System.out.println("\n=== All University Courses ===");
        for (Course<? extends CourseType> course : courses) {
            course.displayCourseInfo();
            System.out.println("---");
        }
    }
}

public class UniversityCourseManagement {
    public static void main(String[] args) {
        Course<ExamCourse> mathCourse = new Course<>("Advanced Mathematics", "Science", new ExamCourse(180));
        mathCourse.enrollStudent("John");
        mathCourse.enrollStudent("Jane");

        Course<ExamCourse> physicsCourse = new Course<>("Quantum Physics", "Science", new ExamCourse(150));
        physicsCourse.enrollStudent("Alice");

        Course<AssignmentCourse> programmingCourse = new Course<>("Java Programming", "Computer Science", new AssignmentCourse(5));
        programmingCourse.enrollStudent("Bob");
        programmingCourse.enrollStudent("Charlie");
        programmingCourse.enrollStudent("David");

        Course<AssignmentCourse> webDevCourse = new Course<>("Web Development", "Computer Science", new AssignmentCourse(8));
        webDevCourse.enrollStudent("Eve");

        Course<ResearchCourse> aiResearch = new Course<>("AI Research Methods", "Computer Science", new ResearchCourse("Machine Learning Applications"));
        aiResearch.enrollStudent("Frank");
        aiResearch.enrollStudent("Grace");

        List<Course<ExamCourse>> examCourses = new ArrayList<>();
        examCourses.add(mathCourse);
        examCourses.add(physicsCourse);

        List<Course<AssignmentCourse>> assignmentCourses = new ArrayList<>();
        assignmentCourses.add(programmingCourse);
        assignmentCourses.add(webDevCourse);

        List<Course<? extends CourseType>> allCourses = new ArrayList<>();
        allCourses.add(mathCourse);
        allCourses.add(physicsCourse);
        allCourses.add(programmingCourse);
        allCourses.add(webDevCourse);
        allCourses.add(aiResearch);

        System.out.println("=== Exam-Based Courses ===");
        CourseManager.displayAllCourses(examCourses);

        System.out.println("\n=== Assignment-Based Courses ===");
        CourseManager.displayAllCourses(assignmentCourses);

        CourseManager.displayAllCourses(allCourses);
    }
}
