import java.util.ArrayList;

// Course class
class UniversityCourse {
    private String courseName;
    private String courseCode;
    private int credits;
    private Professor assignedProfessor;
    private ArrayList<UniversityStudent> enrolledStudents;
    
    public UniversityCourse(String courseName, String courseCode, int credits) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
        this.enrolledStudents = new ArrayList<>();
    }
    
    public void assignProfessor(Professor professor) {
        this.assignedProfessor = professor;
        professor.addCourse(this);
        System.out.println("Prof. " + professor.getName() + " assigned to teach " + courseName);
    }
    
    public void enrollStudent(UniversityStudent student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
        }
    }
    
    public void displayCourseInfo() {
        System.out.println("\n--- Course Information ---");
        System.out.println("Course: " + courseName + " (" + courseCode + ")");
        System.out.println("Credits: " + credits);
        if (assignedProfessor != null) {
            System.out.println("Professor: " + assignedProfessor.getName());
        } else {
            System.out.println("Professor: Not assigned");
        }
        System.out.println("Enrolled Students: " + enrolledStudents.size());
        for (UniversityStudent student : enrolledStudents) {
            System.out.println("  - " + student.getName() + " (" + student.getStudentId() + ")");
        }
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public String getCourseCode() {
        return courseCode;
    }
    
    public int getCredits() {
        return credits;
    }
}

// Student class
class UniversityStudent {
    private String name;
    private String studentId;
    private String major;
    private ArrayList<UniversityCourse> enrolledCourses;
    
    public UniversityStudent(String name, String studentId, String major) {
        this.name = name;
        this.studentId = studentId;
        this.major = major;
        this.enrolledCourses = new ArrayList<>();
    }
    
    // Communication method
    public void enrollCourse(UniversityCourse course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
            course.enrollStudent(this);
            System.out.println(name + " enrolled in " + course.getCourseName() + " (" + course.getCourseCode() + ")");
        } else {
            System.out.println(name + " is already enrolled in " + course.getCourseName());
        }
    }
    
    public void dropCourse(UniversityCourse course) {
        if (enrolledCourses.contains(course)) {
            enrolledCourses.remove(course);
            System.out.println(name + " dropped " + course.getCourseName());
        }
    }
    
    public void viewSchedule() {
        System.out.println("\n" + name + "'s Course Schedule:");
        System.out.println("Major: " + major);
        int totalCredits = 0;
        for (UniversityCourse course : enrolledCourses) {
            System.out.println("  - " + course.getCourseName() + " (" + course.getCourseCode() + ") - " + course.getCredits() + " credits");
            totalCredits += course.getCredits();
        }
        System.out.println("Total Credits: " + totalCredits);
    }
    
    public String getName() {
        return name;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public String getMajor() {
        return major;
    }
}

// Professor class
class Professor {
    private String name;
    private String professorId;
    private String department;
    private ArrayList<UniversityCourse> teachingCourses;
    
    public Professor(String name, String professorId, String department) {
        this.name = name;
        this.professorId = professorId;
        this.department = department;
        this.teachingCourses = new ArrayList<>();
    }
    
    public void addCourse(UniversityCourse course) {
        if (!teachingCourses.contains(course)) {
            teachingCourses.add(course);
        }
    }
    
    public void viewTeachingSchedule() {
        System.out.println("\nProf. " + name + "'s Teaching Schedule:");
        System.out.println("Department: " + department);
        System.out.println("Courses:");
        for (UniversityCourse course : teachingCourses) {
            System.out.println("  - " + course.getCourseName() + " (" + course.getCourseCode() + ")");
        }
    }
    
    public String getName() {
        return name;
    }
    
    public String getProfessorId() {
        return professorId;
    }
    
    public String getDepartment() {
        return department;
    }
}

// UniversityManagementSystem class
class UniversityManagementSystem {
    private String universityName;
    private ArrayList<UniversityStudent> students;
    private ArrayList<Professor> professors;
    private ArrayList<UniversityCourse> courses;
    
    public UniversityManagementSystem(String universityName) {
        this.universityName = universityName;
        this.students = new ArrayList<>();
        this.professors = new ArrayList<>();
        this.courses = new ArrayList<>();
    }
    
    public void admitStudent(UniversityStudent student) {
        students.add(student);
        System.out.println(student.getName() + " admitted to " + universityName);
    }
    
    public void hireProfessor(Professor professor) {
        professors.add(professor);
        System.out.println("Prof. " + professor.getName() + " hired at " + universityName);
    }
    
    public void addCourse(UniversityCourse course) {
        courses.add(course);
        System.out.println(course.getCourseName() + " added to course catalog");
    }
    
    public void displayUniversityStats() {
        System.out.println("\n=== " + universityName + " Statistics ===");
        System.out.println("Total Students: " + students.size());
        System.out.println("Total Professors: " + professors.size());
        System.out.println("Total Courses: " + courses.size());
    }
    
    public String getUniversityName() {
        return universityName;
    }
}

// Main class
public class SelfProblem5_UniversityManagementSystem {
    public static void main(String[] args) {
        // Create university management system
        UniversityManagementSystem university = new UniversityManagementSystem("Tech University");
        
        // Create professors
        Professor prof1 = new Professor("Dr. Robert Martin", "P001", "Computer Science");
        Professor prof2 = new Professor("Dr. Barbara Liskov", "P002", "Computer Science");
        Professor prof3 = new Professor("Dr. Donald Knuth", "P003", "Mathematics");
        Professor prof4 = new Professor("Dr. Grace Hopper", "P004", "Computer Science");
        
        // Hire professors
        university.hireProfessor(prof1);
        university.hireProfessor(prof2);
        university.hireProfessor(prof3);
        university.hireProfessor(prof4);
        
        System.out.println();
        
        // Create courses
        UniversityCourse course1 = new UniversityCourse("Data Structures", "CS201", 4);
        UniversityCourse course2 = new UniversityCourse("Algorithms", "CS301", 4);
        UniversityCourse course3 = new UniversityCourse("Database Systems", "CS202", 3);
        UniversityCourse course4 = new UniversityCourse("Operating Systems", "CS302", 4);
        UniversityCourse course5 = new UniversityCourse("Discrete Mathematics", "MATH201", 3);
        
        // Add courses to university
        university.addCourse(course1);
        university.addCourse(course2);
        university.addCourse(course3);
        university.addCourse(course4);
        university.addCourse(course5);
        
        System.out.println("\n--- Assigning Professors to Courses ---");
        
        // Assign professors to courses (communication)
        course1.assignProfessor(prof1);
        course2.assignProfessor(prof2);
        course3.assignProfessor(prof4);
        course4.assignProfessor(prof1);
        course5.assignProfessor(prof3);
        
        System.out.println("\n--- Admitting Students ---");
        
        // Create and admit students
        UniversityStudent student1 = new UniversityStudent("Alice Johnson", "S001", "Computer Science");
        UniversityStudent student2 = new UniversityStudent("Bob Wilson", "S002", "Computer Science");
        UniversityStudent student3 = new UniversityStudent("Carol Davis", "S003", "Software Engineering");
        UniversityStudent student4 = new UniversityStudent("David Brown", "S004", "Computer Science");
        
        university.admitStudent(student1);
        university.admitStudent(student2);
        university.admitStudent(student3);
        university.admitStudent(student4);
        
        System.out.println("\n--- Students Enrolling in Courses ---");
        
        // Students enroll in courses (communication)
        student1.enrollCourse(course1);
        student1.enrollCourse(course2);
        student1.enrollCourse(course5);
        
        student2.enrollCourse(course1);
        student2.enrollCourse(course3);
        student2.enrollCourse(course5);
        
        student3.enrollCourse(course2);
        student3.enrollCourse(course3);
        student3.enrollCourse(course4);
        
        student4.enrollCourse(course1);
        student4.enrollCourse(course4);
        student4.enrollCourse(course5);
        
        // Display information
        university.displayUniversityStats();
        
        // Students view their schedules
        student1.viewSchedule();
        student2.viewSchedule();
        student3.viewSchedule();
        student4.viewSchedule();
        
        // Professors view their teaching schedules
        prof1.viewTeachingSchedule();
        prof2.viewTeachingSchedule();
        prof3.viewTeachingSchedule();
        prof4.viewTeachingSchedule();
        
        // Display course information
        course1.displayCourseInfo();
        course2.displayCourseInfo();
        
        // Demonstrate dropping a course
        System.out.println("\n--- Course Changes ---");
        student2.dropCourse(course5);
        student2.enrollCourse(course4);
        
        student2.viewSchedule();
        course5.displayCourseInfo();
    }
}
