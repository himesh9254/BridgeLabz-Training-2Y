import java.util.ArrayList;

// Faculty class - can exist independently
class Faculty {
    private String name;
    private String facultyId;
    private String specialization;
    
    public Faculty(String name, String facultyId, String specialization) {
        this.name = name;
        this.facultyId = facultyId;
        this.specialization = specialization;
    }
    
    public String getName() {
        return name;
    }
    
    public String getFacultyId() {
        return facultyId;
    }
    
    public String getSpecialization() {
        return specialization;
    }
    
    @Override
    public String toString() {
        return name + " (" + facultyId + ") - " + specialization;
    }
}

// Department class - composition with University
class Department {
    private String departmentName;
    private String departmentCode;
    private ArrayList<Faculty> facultyMembers;
    
    public Department(String departmentName, String departmentCode) {
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
        this.facultyMembers = new ArrayList<>();
    }
    
    public void assignFaculty(Faculty faculty) {
        facultyMembers.add(faculty);
        System.out.println(faculty.getName() + " assigned to " + departmentName);
    }
    
    public void removeFaculty(Faculty faculty) {
        facultyMembers.remove(faculty);
        System.out.println(faculty.getName() + " removed from " + departmentName);
    }
    
    public void displayDepartmentInfo() {
        System.out.println("  Department: " + departmentName + " (" + departmentCode + ")");
        System.out.println("  Faculty Members:");
        for (Faculty faculty : facultyMembers) {
            System.out.println("    - " + faculty);
        }
    }
    
    public String getDepartmentName() {
        return departmentName;
    }
}

// University class
class University {
    private String universityName;
    private ArrayList<Department> departments;
    private ArrayList<Faculty> allFaculty; // Aggregation - faculty can exist independently
    
    public University(String universityName) {
        this.universityName = universityName;
        this.departments = new ArrayList<>();
        this.allFaculty = new ArrayList<>();
        System.out.println("University '" + universityName + "' established");
    }
    
    // Composition - department is created as part of university
    public Department createDepartment(String departmentName, String departmentCode) {
        Department dept = new Department(departmentName, departmentCode);
        departments.add(dept);
        System.out.println("Department '" + departmentName + "' created in " + universityName);
        return dept;
    }
    
    // Aggregation - faculty exists independently
    public void hireFaculty(Faculty faculty) {
        allFaculty.add(faculty);
        System.out.println(faculty.getName() + " hired by " + universityName);
    }
    
    public void displayUniversityStructure() {
        System.out.println("\n=== " + universityName + " Structure ===");
        System.out.println("Departments:");
        for (Department dept : departments) {
            dept.displayDepartmentInfo();
        }
        
        System.out.println("\nAll Faculty Members:");
        for (Faculty faculty : allFaculty) {
            System.out.println("  - " + faculty);
        }
    }
    
    // When university is closed, departments are destroyed (composition)
    // but faculty members continue to exist (aggregation)
    public void closeUniversity() {
        System.out.println("\n!!! Closing " + universityName + " !!!");
        System.out.println("All departments are being dissolved...");
        departments.clear(); // Composition - departments destroyed
        
        System.out.println("\nFaculty members still exist and can work elsewhere:");
        for (Faculty faculty : allFaculty) {
            System.out.println("  - " + faculty.getName() + " is now available");
        }
    }
    
    public String getUniversityName() {
        return universityName;
    }
}

// Main class
public class SelfProblem2_UniversityFacultyDepartments {
    public static void main(String[] args) {
        // Create university
        University stateUniversity = new University("State University");
        
        // Create faculty members independently (aggregation)
        Faculty prof1 = new Faculty("Dr. John Smith", "F001", "Computer Science");
        Faculty prof2 = new Faculty("Dr. Emily Johnson", "F002", "Mathematics");
        Faculty prof3 = new Faculty("Dr. Michael Brown", "F003", "Physics");
        Faculty prof4 = new Faculty("Dr. Sarah Davis", "F004", "Chemistry");
        Faculty prof5 = new Faculty("Dr. Robert Wilson", "F005", "Computer Science");
        
        // Hire faculty
        stateUniversity.hireFaculty(prof1);
        stateUniversity.hireFaculty(prof2);
        stateUniversity.hireFaculty(prof3);
        stateUniversity.hireFaculty(prof4);
        stateUniversity.hireFaculty(prof5);
        
        System.out.println();
        
        // Create departments (composition)
        Department csDept = stateUniversity.createDepartment("Computer Science", "CS");
        Department mathDept = stateUniversity.createDepartment("Mathematics", "MATH");
        Department physicsDept = stateUniversity.createDepartment("Physics", "PHY");
        
        System.out.println("\n--- Assigning Faculty to Departments ---");
        
        // Assign faculty to departments (aggregation)
        csDept.assignFaculty(prof1);
        csDept.assignFaculty(prof5);
        mathDept.assignFaculty(prof2);
        physicsDept.assignFaculty(prof3);
        physicsDept.assignFaculty(prof4);
        
        // Display university structure
        stateUniversity.displayUniversityStructure();
        
        // Faculty can move between departments
        System.out.println("\n--- Faculty Transfer ---");
        physicsDept.removeFaculty(prof4);
        csDept.assignFaculty(prof4);
        
        // Close university - demonstrates composition vs aggregation
        stateUniversity.closeUniversity();
        
        System.out.println("\nDepartments no longer exist, but faculty members do:");
        System.out.println(prof1.getName() + " can now work at another university");
        System.out.println(prof2.getName() + " can now work at another university");
    }
}
