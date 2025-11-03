import java.util.ArrayList;

// Employee class
class Employee {
    private String name;
    private String employeeId;
    private String position;
    
    public Employee(String name, String employeeId, String position) {
        this.name = name;
        this.employeeId = employeeId;
        this.position = position;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmployeeId() {
        return employeeId;
    }
    
    public String getPosition() {
        return position;
    }
    
    @Override
    public String toString() {
        return name + " (" + employeeId + ") - " + position;
    }
}

// Department class
class Department {
    private String departmentName;
    private ArrayList<Employee> employees;
    
    public Department(String departmentName) {
        this.departmentName = departmentName;
        this.employees = new ArrayList<>();
    }
    
    public void addEmployee(String name, String employeeId, String position) {
        Employee emp = new Employee(name, employeeId, position);
        employees.add(emp);
        System.out.println(name + " added to " + departmentName);
    }
    
    public void displayEmployees() {
        System.out.println("  Department: " + departmentName);
        for (Employee emp : employees) {
            System.out.println("    - " + emp);
        }
    }
    
    public String getDepartmentName() {
        return departmentName;
    }
}

// Company class - demonstrates composition
class Company {
    private String companyName;
    private ArrayList<Department> departments;
    
    public Company(String companyName) {
        this.companyName = companyName;
        this.departments = new ArrayList<>();
        System.out.println("Company '" + companyName + "' created");
    }
    
    public Department createDepartment(String departmentName) {
        Department dept = new Department(departmentName);
        departments.add(dept);
        System.out.println("Department '" + departmentName + "' created in " + companyName);
        return dept;
    }
    
    public void displayStructure() {
        System.out.println("\n=== " + companyName + " Structure ===");
        for (Department dept : departments) {
            dept.displayEmployees();
        }
    }
    
    // Simulate company deletion - all departments and employees are destroyed
    public void closeCompany() {
        System.out.println("\n!!! Closing " + companyName + " !!!");
        System.out.println("All departments and employees are being terminated...");
        departments.clear(); // In composition, child objects are destroyed
        System.out.println(companyName + " has been closed. All departments and employees removed.");
    }
    
    public String getCompanyName() {
        return companyName;
    }
}

// Main class to demonstrate composition
public class AssistedProblem3_CompanyDepartments {
    public static void main(String[] args) {
        // Create company
        Company techCorp = new Company("TechCorp Inc.");
        
        // Create departments (composition - departments belong to company)
        Department engineering = techCorp.createDepartment("Engineering");
        Department hr = techCorp.createDepartment("Human Resources");
        Department sales = techCorp.createDepartment("Sales");
        
        // Add employees to departments
        engineering.addEmployee("John Doe", "E001", "Software Engineer");
        engineering.addEmployee("Jane Smith", "E002", "Senior Developer");
        engineering.addEmployee("Mike Johnson", "E003", "DevOps Engineer");
        
        hr.addEmployee("Sarah Williams", "H001", "HR Manager");
        hr.addEmployee("Tom Brown", "H002", "Recruiter");
        
        sales.addEmployee("Emily Davis", "S001", "Sales Manager");
        sales.addEmployee("David Wilson", "S002", "Sales Representative");
        
        // Display company structure
        techCorp.displayStructure();
        
        // Demonstrate composition - when company is deleted, all departments and employees go with it
        techCorp.closeCompany();
        
        // After closing, trying to display would show empty structure
        techCorp.displayStructure();
    }
}
