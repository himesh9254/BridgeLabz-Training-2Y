import java.util.*;

public class EmployeeDepartmentMapping {
    private Map<String, String> employeeToDepartment;
    
    public EmployeeDepartmentMapping() {
        employeeToDepartment = new HashMap<>();
    }
    
    public void addEmployee(String employeeId, String department) {
        if (employeeToDepartment.containsKey(employeeId)) {
            System.out.println("Employee already exists: " + employeeId);
            return;
        }
        employeeToDepartment.put(employeeId, department);
        System.out.println("Added: " + employeeId + " -> " + department);
    }
    
    public void updateDepartment(String employeeId, String newDepartment) {
        if (!employeeToDepartment.containsKey(employeeId)) {
            System.out.println("Employee not found: " + employeeId);
            return;
        }
        String oldDept = employeeToDepartment.get(employeeId);
        employeeToDepartment.put(employeeId, newDepartment);
        System.out.println("Transferred " + employeeId + " from " + oldDept + " to " + newDepartment);
    }
    
    public void removeEmployee(String employeeId) {
        if (employeeToDepartment.remove(employeeId) != null) {
            System.out.println("Removed employee: " + employeeId);
        } else {
            System.out.println("Employee not found: " + employeeId);
        }
    }
    
    public String getDepartment(String employeeId) {
        return employeeToDepartment.get(employeeId);
    }
    
    public List<String> getEmployeesByDepartment(String department) {
        List<String> employees = new ArrayList<>();
        for (Map.Entry<String, String> entry : employeeToDepartment.entrySet()) {
            if (entry.getValue().equals(department)) {
                employees.add(entry.getKey());
            }
        }
        return employees;
    }
    
    public void lookupEmployee(String employeeId) {
        String dept = getDepartment(employeeId);
        if (dept != null) {
            System.out.println(employeeId + " works in " + dept);
        } else {
            System.out.println("Employee not found: " + employeeId);
        }
    }
    
    public void lookupDepartment(String department) {
        List<String> employees = getEmployeesByDepartment(department);
        if (employees.isEmpty()) {
            System.out.println("No employees in " + department);
        } else {
            System.out.println(department + " has " + employees.size() + " employee(s): " + employees);
        }
    }
    
    public void displayAllEmployees() {
        System.out.println("\n=== All Employee-Department Mappings ===");
        TreeMap<String, String> sorted = new TreeMap<>(employeeToDepartment);
        
        for (Map.Entry<String, String> entry : sorted.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
    
    public void displayByDepartment() {
        System.out.println("\n=== Employees Grouped by Department ===");
        
        Map<String, List<String>> deptToEmployees = new TreeMap<>();
        for (Map.Entry<String, String> entry : employeeToDepartment.entrySet()) {
            String dept = entry.getValue();
            deptToEmployees.computeIfAbsent(dept, k -> new ArrayList<>()).add(entry.getKey());
        }
        
        for (Map.Entry<String, List<String>> entry : deptToEmployees.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (String emp : entry.getValue()) {
                System.out.println("  - " + emp);
            }
        }
    }
    
    public void displayDepartmentStats() {
        System.out.println("\n=== Department Statistics ===");
        
        Map<String, Integer> deptCount = new HashMap<>();
        for (String dept : employeeToDepartment.values()) {
            deptCount.put(dept, deptCount.getOrDefault(dept, 0) + 1);
        }
        
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(deptCount.entrySet());
        sorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        for (Map.Entry<String, Integer> entry : sorted) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " employee(s)");
        }
        
        System.out.println("\nTotal Employees: " + employeeToDepartment.size());
        System.out.println("Total Departments: " + deptCount.size());
    }
    
    public static void main(String[] args) {
        EmployeeDepartmentMapping system = new EmployeeDepartmentMapping();
        
        System.out.println("=== Adding Employees ===");
        system.addEmployee("E001", "Engineering");
        system.addEmployee("E002", "Engineering");
        system.addEmployee("E003", "Marketing");
        system.addEmployee("E004", "HR");
        system.addEmployee("E005", "Engineering");
        system.addEmployee("E006", "Finance");
        system.addEmployee("E007", "Marketing");
        system.addEmployee("E008", "HR");
        system.addEmployee("E009", "Finance");
        system.addEmployee("E010", "Engineering");
        
        system.displayAllEmployees();
        system.displayByDepartment();
        
        System.out.println("\n=== Employee Lookup (Forward) ===");
        system.lookupEmployee("E001");
        system.lookupEmployee("E004");
        system.lookupEmployee("E999");
        
        System.out.println("\n=== Department Lookup (Reverse) ===");
        system.lookupDepartment("Engineering");
        system.lookupDepartment("Marketing");
        system.lookupDepartment("Legal");
        
        System.out.println("\n=== Department Transfers ===");
        system.updateDepartment("E003", "Sales");
        system.updateDepartment("E005", "Management");
        
        System.out.println("\n=== Employee Leaving ===");
        system.removeEmployee("E008");
        
        system.displayByDepartment();
        system.displayDepartmentStats();
    }
}
