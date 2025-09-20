class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void displayBasicInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }

    void displayRole() {
        System.out.println("Role: Person");
    }
}

class Teacher extends Person {
    String subject;
    int experienceYears;

    Teacher(String name, int age, String subject, int experienceYears) {
        super(name, age);
        this.subject = subject;
        this.experienceYears = experienceYears;
    }

    @Override
    void displayRole() {
        System.out.println("Role: Teacher");
    }

    void teachClass() {
        System.out.println(name + " is teaching " + subject);
    }

    void displayInfo() {
        displayBasicInfo();
        displayRole();
        System.out.println("Subject: " + subject);
        System.out.println("Experience: " + experienceYears + " years");
    }
}

class Student extends Person {
    int grade;
    String studentId;

    Student(String name, int age, int grade, String studentId) {
        super(name, age);
        this.grade = grade;
        this.studentId = studentId;
    }

    @Override
    void displayRole() {
        System.out.println("Role: Student");
    }

    void attendClass() {
        System.out.println(name + " is attending class");
    }

    void displayInfo() {
        displayBasicInfo();
        displayRole();
        System.out.println("Grade: " + grade);
        System.out.println("Student ID: " + studentId);
    }
}

class Staff extends Person {
    String department;
    String position;

    Staff(String name, int age, String department, String position) {
        super(name, age);
        this.department = department;
        this.position = position;
    }

    @Override
    void displayRole() {
        System.out.println("Role: Staff");
    }

    void performDuties() {
        System.out.println(name + " is working in " + department + " department");
    }

    void displayInfo() {
        displayBasicInfo();
        displayRole();
        System.out.println("Department: " + department);
        System.out.println("Position: " + position);
    }
}

public class SchoolRoles {
    public static void main(String[] args) {
        Teacher mathTeacher = new Teacher("Dr. Smith", 45, "Mathematics", 20);
        Student student = new Student("Alice Johnson", 16, 11, "STU2025001");
        Staff adminStaff = new Staff("John Davis", 38, "Administration", "Office Manager");

        System.out.println("=== School Management System ===\n");
        
        System.out.println("Teacher Information:");
        mathTeacher.displayInfo();
        mathTeacher.teachClass();
        
        System.out.println("\nStudent Information:");
        student.displayInfo();
        student.attendClass();
        
        System.out.println("\nStaff Information:");
        adminStaff.displayInfo();
        adminStaff.performDuties();
        
        System.out.println("\nHierarchical Inheritance:");
        System.out.println("Person <- Teacher");
        System.out.println("Person <- Student");
        System.out.println("Person <- Staff");
    }
}