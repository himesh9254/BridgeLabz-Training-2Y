import java.util.ArrayList;

// Course class
class Course {
    private String courseName;
    private String courseCode;
    private ArrayList<Student> enrolledStudents;
    
    public Course(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.enrolledStudents = new ArrayList<>();
    }
    
    public void enrollStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
        }
    }
    
    public void displayEnrolledStudents() {
        System.out.println("\nStudents enrolled in " + courseName + " (" + courseCode + "):");
        for (Student student : enrolledStudents) {
            System.out.println("  - " + student.getName() + " (" + student.getStudentId() + ")");
        }
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public String getCourseCode() {
        return courseCode;
    }
}

// Student class
class Student {
    private String name;
    private String studentId;
    private ArrayList<Course> enrolledCourses;
    
    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
        this.enrolledCourses = new ArrayList<>();
    }
    
    public void enrollInCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
            course.enrollStudent(this);
            System.out.println(name + " enrolled in " + course.getCourseName());
        }
    }
    
    public void viewCourses() {
        System.out.println("\n" + name + "'s enrolled courses:");
        for (Course course : enrolledCourses) {
            System.out.println("  - " + course.getCourseName() + " (" + course.getCourseCode() + ")");
        }
    }
    
    public String getName() {
        return name;
    }
    
    public String getStudentId() {
        return studentId;
    }
}

// School class - aggregation with students
class School {
    private String schoolName;
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    
    public School(String schoolName) {
        this.schoolName = schoolName;
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
    }
    
    public void addStudent(Student student) {
        students.add(student);
        System.out.println(student.getName() + " added to " + schoolName);
    }
    
    public void addCourse(Course course) {
        courses.add(course);
        System.out.println(course.getCourseName() + " added to " + schoolName);
    }
    
    public void displaySchoolInfo() {
        System.out.println("\n=== " + schoolName + " ===");
        System.out.println("Total Students: " + students.size());
        System.out.println("Total Courses: " + courses.size());
    }
    
    public String getSchoolName() {
        return schoolName;
    }
}

// Main class
public class SelfProblem1_SchoolStudentsCourses {
    public static void main(String[] args) {
        // Create school
        School highSchool = new School("Springfield High School");
        
        // Create courses
        Course math = new Course("Mathematics", "MATH101");
        Course science = new Course("Science", "SCI101");
        Course history = new Course("History", "HIST101");
        Course english = new Course("English", "ENG101");
        
        // Add courses to school
        highSchool.addCourse(math);
        highSchool.addCourse(science);
        highSchool.addCourse(history);
        highSchool.addCourse(english);
        
        // Create students
        Student student1 = new Student("Alice Brown", "S001");
        Student student2 = new Student("Bob Wilson", "S002");
        Student student3 = new Student("Carol Davis", "S003");
        
        // Add students to school (aggregation)
        highSchool.addStudent(student1);
        highSchool.addStudent(student2);
        highSchool.addStudent(student3);
        
        System.out.println("\n--- Course Enrollment ---");
        
        // Students enroll in courses (many-to-many association)
        student1.enrollInCourse(math);
        student1.enrollInCourse(science);
        student1.enrollInCourse(english);
        
        student2.enrollInCourse(math);
        student2.enrollInCourse(history);
        
        student3.enrollInCourse(science);
        student3.enrollInCourse(history);
        student3.enrollInCourse(english);
        
        // Display information
        highSchool.displaySchoolInfo();
        
        // Students view their courses
        student1.viewCourses();
        student2.viewCourses();
        student3.viewCourses();
        
        // Courses show enrolled students
        math.displayEnrolledStudents();
        science.displayEnrolledStudents();
    }
}
