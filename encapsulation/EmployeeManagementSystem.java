/**
 * Employee Management System demonstrating:
 * - Abstract classes and methods
 * - Inheritance and polymorphism
 * - Interface implementation
 * - Encapsulation with proper getters/setters
 * - Data validation and security
 */

import java.util.*;
import java.time.LocalDate;

// Interface for Department management
interface Department {
    void assignDepartment(String departmentName);
    String getDepartmentDetails();
}

// Abstract Employee class with encapsulation
abstract class Employee implements Department {
    // Private fields - encapsulated data
    private String employeeId;
    private String name;
    private double baseSalary;
    private String departmentName;
    private LocalDate joinDate;
    private boolean isActive;
    
    // Constructor
    public Employee(String employeeId, String name, double baseSalary) {
        if (employeeId == null || employeeId.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be null or empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee name cannot be null or empty");
        }
        if (baseSalary < 0) {
            throw new IllegalArgumentException("Base salary cannot be negative");
        }
        
        this.employeeId = employeeId;
        this.name = name;
        this.baseSalary = baseSalary;
        this.joinDate = LocalDate.now();
        this.isActive = true;
        this.departmentName = "Unassigned";
    }
    
    // Abstract method to be implemented by subclasses
    public abstract double calculateSalary();
    
    // Concrete method for displaying employee details
    public void displayDetails() {
        System.out.println("=== Employee Details ===");
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Base Salary: $" + String.format("%.2f", baseSalary));
        System.out.println("Department: " + departmentName);
        System.out.println("Join Date: " + joinDate);
        System.out.println("Status: " + (isActive ? "Active" : "Inactive"));
        System.out.println("Calculated Salary: $" + String.format("%.2f", calculateSalary()));
        System.out.println("========================");
    }
    
    // Encapsulated getters
    public String getEmployeeId() {
        return employeeId;
    }
    
    public String getName() {
        return name;
    }
    
    public double getBaseSalary() {
        return baseSalary;
    }
    
    public String getDepartmentName() {
        return departmentName;
    }
    
    public LocalDate getJoinDate() {
        return joinDate;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    // Encapsulated setters with validation
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }
    
    public void setBaseSalary(double baseSalary) {
        if (baseSalary < 0) {
            throw new IllegalArgumentException("Base salary cannot be negative");
        }
        this.baseSalary = baseSalary;
    }
    
    public void setActive(boolean active) {
        this.isActive = active;
    }
    
    // Department interface implementation
    @Override
    public void assignDepartment(String departmentName) {
        if (departmentName == null || departmentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Department name cannot be null or empty");
        }
        this.departmentName = departmentName;
        System.out.println("Employee " + name + " assigned to " + departmentName + " department.");
    }
    
    @Override
    public String getDepartmentDetails() {
        return "Employee: " + name + " | Department: " + departmentName + " | Join Date: " + joinDate;
    }
}

// Full-time employee implementation
class FullTimeEmployee extends Employee {
    private double monthlyAllowances;
    private int workingDays;
    private static final int STANDARD_WORKING_DAYS = 22;
    
    public FullTimeEmployee(String employeeId, String name, double baseSalary, double monthlyAllowances) {
        super(employeeId, name, baseSalary);
        this.monthlyAllowances = monthlyAllowances;
        this.workingDays = STANDARD_WORKING_DAYS;
    }
    
    @Override
    public double calculateSalary() {
        // Full-time employees get base salary + allowances
        return getBaseSalary() + monthlyAllowances;
    }
    
    // Encapsulated getters and setters
    public double getMonthlyAllowances() {
        return monthlyAllowances;
    }
    
    public void setMonthlyAllowances(double monthlyAllowances) {
        if (monthlyAllowances < 0) {
            throw new IllegalArgumentException("Monthly allowances cannot be negative");
        }
        this.monthlyAllowances = monthlyAllowances;
    }
    
    public int getWorkingDays() {
        return workingDays;
    }
    
    public void setWorkingDays(int workingDays) {
        if (workingDays < 0 || workingDays > 31) {
            throw new IllegalArgumentException("Working days must be between 0 and 31");
        }
        this.workingDays = workingDays;
    }
    
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Employee Type: Full-Time");
        System.out.println("Monthly Allowances: $" + String.format("%.2f", monthlyAllowances));
        System.out.println("Working Days: " + workingDays);
        System.out.println("========================");
    }
}

// Part-time employee implementation
class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;
    private static final int MAX_HOURS_PER_MONTH = 200;
    
    public PartTimeEmployee(String employeeId, String name, double baseSalary, int hoursWorked, double hourlyRate) {
        super(employeeId, name, baseSalary);
        setHoursWorked(hoursWorked);
        setHourlyRate(hourlyRate);
    }
    
    @Override
    public double calculateSalary() {
        // Part-time employees get hourly rate * hours worked
        return hoursWorked * hourlyRate;
    }
    
    // Encapsulated getters and setters
    public int getHoursWorked() {
        return hoursWorked;
    }
    
    public void setHoursWorked(int hoursWorked) {
        if (hoursWorked < 0 || hoursWorked > MAX_HOURS_PER_MONTH) {
            throw new IllegalArgumentException("Hours worked must be between 0 and " + MAX_HOURS_PER_MONTH);
        }
        this.hoursWorked = hoursWorked;
    }
    
    public double getHourlyRate() {
        return hourlyRate;
    }
    
    public void setHourlyRate(double hourlyRate) {
        if (hourlyRate < 0) {
            throw new IllegalArgumentException("Hourly rate cannot be negative");
        }
        this.hourlyRate = hourlyRate;
    }
    
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Employee Type: Part-Time");
        System.out.println("Hours Worked: " + hoursWorked);
        System.out.println("Hourly Rate: $" + String.format("%.2f", hourlyRate));
        System.out.println("========================");
    }
}

// Employee Management System main class
public class EmployeeManagementSystem {
    private List<Employee> employees;
    private static int employeeCounter = 1;
    
    public EmployeeManagementSystem() {
        this.employees = new ArrayList<>();
    }
    
    // Method to add employee (demonstrates polymorphism)
    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        employees.add(employee);
        System.out.println("Employee added successfully: " + employee.getName());
    }
    
    // Method to display all employees (demonstrates polymorphism)
    public void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        
        System.out.println("\n=== ALL EMPLOYEES ===");
        for (Employee employee : employees) {
            employee.displayDetails(); // Polymorphic call
            System.out.println();
        }
    }
    
    // Method to calculate total payroll
    public void calculateTotalPayroll() {
        double totalPayroll = 0;
        
        System.out.println("\n=== PAYROLL CALCULATION ===");
        for (Employee employee : employees) {
            double salary = employee.calculateSalary(); // Polymorphic call
            totalPayroll += salary;
            System.out.printf("%s (%s): $%.2f%n", 
                employee.getName(), 
                employee.getClass().getSimpleName(), 
                salary);
        }
        System.out.printf("Total Payroll: $%.2f%n", totalPayroll);
        System.out.println("============================");
    }
    
    // Method to find employee by ID
    public Employee findEmployeeById(String employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId().equals(employeeId)) {
                return employee;
            }
        }
        return null;
    }
    
    // Method to get employees by department
    public List<Employee> getEmployeesByDepartment(String departmentName) {
        List<Employee> departmentEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getDepartmentName().equalsIgnoreCase(departmentName)) {
                departmentEmployees.add(employee);
            }
        }
        return departmentEmployees;
    }
    
    // Method to demonstrate department operations
    public void demonstrateDepartmentOperations() {
        System.out.println("\n=== DEPARTMENT OPERATIONS ===");
        for (Employee employee : employees) {
            System.out.println(employee.getDepartmentDetails());
        }
        System.out.println("=============================");
    }
    
    // Utility method to generate employee ID
    public static String generateEmployeeId() {
        return "EMP" + String.format("%04d", employeeCounter++);
    }
    
    // Main method demonstrating all concepts
    public static void main(String[] args) {
        System.out.println("=== Employee Management System Demo ===\n");
        
        EmployeeManagementSystem ems = new EmployeeManagementSystem();
        
        try {
            // Create different types of employees (Polymorphism)
            System.out.println("1. Creating Employees (Demonstrating Encapsulation & Inheritance):");
            
            FullTimeEmployee emp1 = new FullTimeEmployee(
                generateEmployeeId(), 
                "John Smith", 
                5000.0, 
                1500.0
            );
            emp1.assignDepartment("Engineering");
            
            PartTimeEmployee emp2 = new PartTimeEmployee(
                generateEmployeeId(), 
                "Alice Johnson", 
                0.0, // Base salary not used for part-time
                120, 
                25.0
            );
            emp2.assignDepartment("Marketing");
            
            FullTimeEmployee emp3 = new FullTimeEmployee(
                generateEmployeeId(), 
                "Bob Wilson", 
                6000.0, 
                2000.0
            );
            emp3.assignDepartment("Finance");
            
            PartTimeEmployee emp4 = new PartTimeEmployee(
                generateEmployeeId(), 
                "Carol Davis", 
                0.0,
                80, 
                30.0
            );
            emp4.assignDepartment("HR");
            
            // Add employees to system
            ems.addEmployee(emp1);
            ems.addEmployee(emp2);
            ems.addEmployee(emp3);
            ems.addEmployee(emp4);
            
            System.out.println("\n2. Displaying All Employees (Demonstrating Polymorphism):");
            ems.displayAllEmployees();
            
            System.out.println("\n3. Payroll Calculation (Polymorphic Method Calls):");
            ems.calculateTotalPayroll();
            
            System.out.println("\n4. Department Operations (Interface Implementation):");
            ems.demonstrateDepartmentOperations();
            
            System.out.println("\n5. Testing Encapsulation (Getters/Setters with Validation):");
            
            // Test valid updates
            System.out.println("Testing valid updates:");
            emp1.setName("John Smith Jr.");
            emp1.setMonthlyAllowances(1800.0);
            emp2.setHoursWorked(150);
            emp2.setHourlyRate(28.0);
            System.out.println("✓ Valid updates successful");
            
            // Test invalid updates (will throw exceptions)
            System.out.println("\nTesting invalid updates (Exception handling):");
            try {
                emp1.setBaseSalary(-1000); // Should throw exception
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Caught expected exception: " + e.getMessage());
            }
            
            try {
                emp2.setHoursWorked(250); // Should throw exception
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Caught expected exception: " + e.getMessage());
            }
            
            System.out.println("\n6. Employee Search and Filtering:");
            
            // Find employee by ID
            Employee foundEmployee = ems.findEmployeeById("EMP0001");
            if (foundEmployee != null) {
                System.out.println("Found employee:");
                foundEmployee.displayDetails();
            }
            
            // Get employees by department
            List<Employee> engineeringEmps = ems.getEmployeesByDepartment("Engineering");
            System.out.println("Engineering Department Employees: " + engineeringEmps.size());
            
            System.out.println("\n7. Updated Employee Details After Modifications:");
            ems.displayAllEmployees();
            
            System.out.println("\n=== Concepts Demonstrated ===");
            System.out.println("✓ Abstract Classes: Employee class with abstract calculateSalary()");
            System.out.println("✓ Inheritance: FullTimeEmployee and PartTimeEmployee extend Employee");
            System.out.println("✓ Polymorphism: Employee references calling overridden methods");
            System.out.println("✓ Interface: Department interface with assignDepartment() and getDepartmentDetails()");
            System.out.println("✓ Encapsulation: Private fields with validated getters/setters");
            System.out.println("✓ Data Validation: Input validation in setters preventing invalid data");
            System.out.println("✓ Exception Handling: Proper error handling for invalid inputs");
            
        } catch (Exception e) {
            System.err.println("Error in Employee Management System: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
