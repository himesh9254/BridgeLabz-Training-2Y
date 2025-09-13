public class Employee {
    // Attributes
    private String name;
    private int id;
    private double salary;
    
    // Constructor
    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }
    
    // Method to display employee details
    public void displayDetails() {
        System.out.println("=== Employee Details ===");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Salary: $" + salary);
        System.out.println("========================");
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    // Main method to test the class
    public static void main(String[] args) {
        // Create employee objects
        Employee emp1 = new Employee("John Doe", 101, 50000.0);
        Employee emp2 = new Employee("Jane Smith", 102, 60000.0);
        
        // Display employee details
        emp1.displayDetails();
        emp2.displayDetails();
    }
}
