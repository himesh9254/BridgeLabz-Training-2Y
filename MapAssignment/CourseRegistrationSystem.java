import java.util.*;

public class CourseRegistrationSystem {
    private Map<String, Integer> courseEnrollment;
    private Map<String, Integer> courseCapacity;
    private Map<String, Set<String>> courseStudents;
    
    public CourseRegistrationSystem() {
        courseEnrollment = new HashMap<>();
        courseCapacity = new HashMap<>();
        courseStudents = new HashMap<>();
    }
    
    public void addCourse(String courseName, int capacity) {
        courseEnrollment.put(courseName, 0);
        courseCapacity.put(courseName, capacity);
        courseStudents.put(courseName, new HashSet<>());
        System.out.println("Course added: " + courseName + " (Capacity: " + capacity + ")");
    }
    
    public boolean registerStudent(String studentName, String courseName) {
        if (!courseEnrollment.containsKey(courseName)) {
            System.out.println("Course not found: " + courseName);
            return false;
        }
        
        Set<String> students = courseStudents.get(courseName);
        if (students.contains(studentName)) {
            System.out.println(studentName + " is already registered for " + courseName);
            return false;
        }
        
        int current = courseEnrollment.get(courseName);
        int capacity = courseCapacity.get(courseName);
        
        if (current >= capacity) {
            System.out.println("Course " + courseName + " is full! Cannot register " + studentName);
            return false;
        }
        
        students.add(studentName);
        courseEnrollment.put(courseName, current + 1);
        System.out.println(studentName + " registered for " + courseName + 
            " (" + (current + 1) + "/" + capacity + ")");
        return true;
    }
    
    public boolean dropStudent(String studentName, String courseName) {
        if (!courseEnrollment.containsKey(courseName)) {
            System.out.println("Course not found: " + courseName);
            return false;
        }
        
        Set<String> students = courseStudents.get(courseName);
        if (!students.contains(studentName)) {
            System.out.println(studentName + " is not registered for " + courseName);
            return false;
        }
        
        students.remove(studentName);
        courseEnrollment.put(courseName, courseEnrollment.get(courseName) - 1);
        System.out.println(studentName + " dropped from " + courseName);
        return true;
    }
    
    public void displayCourseStatus(String courseName) {
        if (!courseEnrollment.containsKey(courseName)) {
            System.out.println("Course not found: " + courseName);
            return;
        }
        
        int enrolled = courseEnrollment.get(courseName);
        int capacity = courseCapacity.get(courseName);
        Set<String> students = courseStudents.get(courseName);
        
        System.out.println("\n=== " + courseName + " ===");
        System.out.println("Enrollment: " + enrolled + "/" + capacity);
        System.out.println("Status: " + (enrolled >= capacity ? "FULL" : "OPEN"));
        System.out.println("Students: " + students);
    }
    
    public void displayAllCourses() {
        System.out.println("\n=== All Courses ===");
        TreeMap<String, Integer> sorted = new TreeMap<>(courseEnrollment);
        
        for (String course : sorted.keySet()) {
            int enrolled = courseEnrollment.get(course);
            int capacity = courseCapacity.get(course);
            String status = enrolled >= capacity ? "[FULL]" : "[OPEN]";
            System.out.printf("%s: %d/%d %s%n", course, enrolled, capacity, status);
        }
    }
    
    public void displayPopularCourses() {
        System.out.println("\n=== Courses by Popularity ===");
        
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(courseEnrollment.entrySet());
        sorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        int rank = 1;
        for (Map.Entry<String, Integer> entry : sorted) {
            int capacity = courseCapacity.get(entry.getKey());
            double fillRate = (entry.getValue() * 100.0) / capacity;
            System.out.printf("%d. %s: %d students (%.1f%% full)%n", 
                rank++, entry.getKey(), entry.getValue(), fillRate);
        }
    }
    
    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();
        
        System.out.println("=== Setting Up Courses ===");
        system.addCourse("Java Programming", 3);
        system.addCourse("Data Structures", 4);
        system.addCourse("Web Development", 2);
        system.addCourse("Machine Learning", 3);
        
        System.out.println("\n=== Student Registrations ===");
        system.registerStudent("Alice", "Java Programming");
        system.registerStudent("Bob", "Java Programming");
        system.registerStudent("Charlie", "Java Programming");
        system.registerStudent("Diana", "Java Programming");
        
        system.registerStudent("Alice", "Data Structures");
        system.registerStudent("Bob", "Data Structures");
        system.registerStudent("Eve", "Data Structures");
        
        system.registerStudent("Frank", "Web Development");
        system.registerStudent("Grace", "Web Development");
        system.registerStudent("Henry", "Web Development");
        
        system.registerStudent("Alice", "Machine Learning");
        
        system.displayAllCourses();
        
        System.out.println("\n=== Dropping Students ===");
        system.dropStudent("Bob", "Java Programming");
        system.dropStudent("Unknown", "Java Programming");
        
        System.out.println("\n=== Re-registration ===");
        system.registerStudent("Diana", "Java Programming");
        
        system.displayCourseStatus("Java Programming");
        system.displayCourseStatus("Data Structures");
        
        system.displayPopularCourses();
    }
}
