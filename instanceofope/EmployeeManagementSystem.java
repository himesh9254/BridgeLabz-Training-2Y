/**
 * Employee class demonstrating static, this, final, and instanceof concepts
 * Complete employee management system with HR features
 */
public class EmployeeManagementSystem {
    // Static variables - shared by all employees
    private static String companyName = "Tech Innovations Ltd";
    private static int totalEmployees = 0;
    private static double totalSalary = 0.0;
    
    // Instance variables
    private String name;
    private final String id; // final - employee ID cannot be modified after assignment
    private String designation;
    private double salary;
    private String department;
    private java.time.LocalDate joiningDate;
    
    // Constructor using 'this' to initialize name, id, and designation
    public EmployeeManagementSystem(String name, String id, String designation, double salary, String department) {
        // Using 'this' to distinguish between parameter and instance variable
        this.name = name;
        this.id = id; // final variable initialization
        this.designation = designation;
        this.salary = salary;
        this.department = department;
        this.joiningDate = java.time.LocalDate.now();
        
        // Update static counters
        totalEmployees++;
        totalSalary += salary;
        
        System.out.println("Employee hired: " + this.name + " (ID: " + this.id + ")");
    }
    
    // Overloaded constructor with default department
    public EmployeeManagementSystem(String name, String id, String designation, double salary) {
        // Using 'this' to call another constructor (constructor chaining)
        this(name, id, designation, salary, "General");
        System.out.println("Default department 'General' assigned.");
    }
    
    // Constructor with minimal info
    public EmployeeManagementSystem(String name, String designation) {
        this(name, generateEmployeeId(), designation, 0.0, "Unassigned");
        System.out.println("Employee created with minimal information.");
    }
    
    // Static method to display total employees
    public static void displayTotalEmployees() {
        System.out.println("=== Employee Statistics ===");
        System.out.println("Company: " + companyName);
        System.out.println("Total Employees: " + totalEmployees);
        System.out.printf("Total Salary Expense: $%.2f%n", totalSalary);
        if (totalEmployees > 0) {
            System.out.printf("Average Salary: $%.2f%n", totalSalary / totalEmployees);
        }
        System.out.println("==========================");
    }
    
    // Static method to get company name
    public static String getCompanyName() {
        return companyName;
    }
    
    // Static method to update company name
    public static void updateCompanyName(String newCompanyName) {
        if (newCompanyName != null && !newCompanyName.trim().isEmpty()) {
            String oldName = companyName;
            companyName = newCompanyName;
            System.out.println("Company name updated: " + oldName + " -> " + companyName);
            System.out.println("This change affects all " + totalEmployees + " employees!");
        } else {
            System.out.println("Invalid company name provided!");
        }
    }
    
    // Method to update salary using 'this'
    public void updateSalary(double newSalary) {
        if (newSalary >= 0) {
            // Update total salary by removing old and adding new
            totalSalary -= this.salary;
            totalSalary += newSalary;
            
            double oldSalary = this.salary;
            this.salary = newSalary;
            
            System.out.printf("Salary updated for %s (ID: %s): $%.2f -> $%.2f%n", 
                             this.name, this.id, oldSalary, this.salary);
        } else {
            System.out.println("Invalid salary amount!");
        }
    }
    
    // Method to promote employee using 'this'
    public void promoteEmployee(String newDesignation, double salaryIncrease) {
        if (newDesignation != null && !newDesignation.trim().isEmpty()) {
            String oldDesignation = this.designation;
            this.designation = newDesignation;
            
            if (salaryIncrease > 0) {
                this.updateSalary(this.salary + salaryIncrease);
            }
            
            System.out.println("Employee promoted: " + this.name);
            System.out.println("Designation: " + oldDesignation + " -> " + this.designation);
        } else {
            System.out.println("Invalid designation provided!");
        }
    }
    
    // Method to transfer employee to different department using 'this'
    public void transferDepartment(String newDepartment) {
        if (newDepartment != null && !newDepartment.trim().isEmpty()) {
            String oldDepartment = this.department;
            this.department = newDepartment;
            System.out.println("Employee " + this.name + " transferred: " + 
                             oldDepartment + " -> " + this.department);
        } else {
            System.out.println("Invalid department name!");
        }
    }
    
    // Method to calculate years of service using 'this'
    public int getYearsOfService() {
        return java.time.Period.between(this.joiningDate, java.time.LocalDate.now()).getYears();
    }
    
    // Method to display employee details
    public void displayEmployeeDetails() {
        System.out.println("=== Employee Details ===");
        System.out.println("Company: " + companyName); // static variable access
        System.out.println("Name: " + this.name);
        System.out.println("Employee ID: " + this.id); // final variable access
        System.out.println("Designation: " + this.designation);
        System.out.println("Department: " + this.department);
        System.out.printf("Salary: $%.2f%n", this.salary);
        System.out.println("Joining Date: " + this.joiningDate);
        System.out.println("Years of Service: " + this.getYearsOfService());
        System.out.println("========================");
    }
    
    // Static method to validate and process employee using instanceof
    public static void processEmployee(Object obj) {
        // Using instanceof to check object type
        if (obj instanceof EmployeeManagementSystem) {
            System.out.println("✓ Object is a valid Employee instance");
            EmployeeManagementSystem employee = (EmployeeManagementSystem) obj; // Safe casting
            employee.displayEmployeeDetails();
        } else {
            System.out.println("✗ Object is not an Employee instance!");
            System.out.println("Object type: " + (obj != null ? obj.getClass().getSimpleName() : "null"));
        }
    }
    
    // Method to compare employees using 'this'
    public boolean isSameEmployee(EmployeeManagementSystem other) {
        // Using 'this' to refer to current object and final ID for comparison
        return other != null && this.id.equals(other.id);
    }
    
    // Method to check if same department using 'this'
    public boolean isInSameDepartment(EmployeeManagementSystem other) {
        return other != null && this.department.equalsIgnoreCase(other.department);
    }
    
    // Method to check if senior employee using 'this'
    public boolean isSeniorTo(EmployeeManagementSystem other) {
        return other != null && this.joiningDate.isBefore(other.joiningDate);
    }
    
    // Utility method to generate unique employee ID
    private static String generateEmployeeId() {
        return "EMP" + String.format("%05d", totalEmployees + 1);
    }
    
    // Getter methods using 'this'
    public String getName() {
        return this.name;
    }
    
    public String getId() {
        return this.id; // final variable - read-only access
    }
    
    public String getDesignation() {
        return this.designation;
    }
    
    public double getSalary() {
        return this.salary;
    }
    
    public String getDepartment() {
        return this.department;
    }
    
    public java.time.LocalDate getJoiningDate() {
        return this.joiningDate;
    }
    
    // Static getter methods
    public static int getTotalEmployees() {
        return totalEmployees;
    }
    
    public static double getTotalSalary() {
        return totalSalary;
    }
    
    // Method to get employee summary using 'this'
    public String getEmployeeSummary() {
        return String.format("%s (ID: %s) - %s, %s - $%.2f", 
                           this.name, this.id, this.designation, this.department, this.salary);
    }
    
    @Override
    public String toString() {
        return String.format("Employee{name='%s', id='%s', designation='%s', salary=%.2f}", 
                           name, id, designation, salary);
    }
    
    // Main method for testing
    public static void main(String[] args) {
        System.out.println("=== Employee Management System Demo ===\\n");
        
        System.out.println("1. Initial company state:");
        System.out.println("Company: " + getCompanyName());
        displayTotalEmployees();
        
        System.out.println("\\n2. Creating employees (using 'this' in constructors):");
        EmployeeManagementSystem emp1 = new EmployeeManagementSystem("John Smith", "EMP001", "Software Engineer", 75000.0, "IT");
        EmployeeManagementSystem emp2 = new EmployeeManagementSystem("Jane Doe", "EMP002", "Marketing Manager", 65000.0);
        EmployeeManagementSystem emp3 = new EmployeeManagementSystem("Bob Johnson", "Senior Developer");
        
        System.out.println("\\n3. Company statistics after hiring:");
        displayTotalEmployees();
        
        System.out.println("\\n4. Testing instanceof with valid employee:");
        processEmployee(emp1);
        
        System.out.println("\\n5. Testing instanceof with invalid objects:");
        processEmployee("Not an employee");
        processEmployee(42);
        processEmployee(null);
        
        System.out.println("\\n6. Employee operations using 'this':");
        emp1.updateSalary(80000.0);
        emp2.promoteEmployee("Senior Marketing Manager", 10000.0);
        emp3.transferDepartment("Research & Development");
        emp3.updateSalary(70000.0);
        
        System.out.println("\\n7. Updated employee details:");
        processEmployee(emp1);
        processEmployee(emp2);
        
        System.out.println("\\n8. Testing final variable (employee ID cannot be changed):");
        System.out.println("Employee1 ID: " + emp1.getId());
        // emp1.id = "NEW001"; // This would cause compilation error
        System.out.println("Note: Employee ID is final and cannot be modified after initialization");
        
        System.out.println("\\n9. Modifying static variable (affects all employees):");
        updateCompanyName("Advanced Technology Solutions");
        
        System.out.println("\\n10. All employees now show updated company name:");
        processEmployee(emp1);
        processEmployee(emp3);
        
        System.out.println("\\n11. Employee comparisons using 'this':");
        EmployeeManagementSystem emp4 = new EmployeeManagementSystem("Alice Wilson", "EMP004", "HR Manager", 68000.0, "HR");
        System.out.println("Are emp1 and emp4 the same employee? " + emp1.isSameEmployee(emp4));
        System.out.println("Are emp1 and emp3 in same department? " + emp1.isInSameDepartment(emp3));
        System.out.println("Is emp1 senior to emp4? " + emp1.isSeniorTo(emp4));
        
        System.out.println("\\n12. Multiple instanceof checks with mixed objects:");
        Object[] objects = {emp1, emp2, "String", 789, emp3, null, new java.util.Date()};
        
        for (int i = 0; i < objects.length; i++) {
            System.out.printf("Object %d: ", i + 1);
            if (objects[i] instanceof EmployeeManagementSystem) {
                EmployeeManagementSystem emp = (EmployeeManagementSystem) objects[i];
                System.out.println("Employee - " + emp.getEmployeeSummary());
            } else {
                System.out.println("Not an Employee - " + 
                                 (objects[i] != null ? objects[i].getClass().getSimpleName() : "null"));
            }
        }
        
        System.out.println("\\n13. Final company statistics:");
        displayTotalEmployees();
        
        System.out.println("\\n=== Concepts Demonstrated ===");
        System.out.println("✓ Static: companyName and totalEmployees shared across all instances");
        System.out.println("✓ This: Used in constructors and methods to refer to current object");
        System.out.println("✓ Final: Employee ID cannot be changed once assigned");
        System.out.println("✓ Instanceof: Safe type checking before casting and operations");
    }
}
