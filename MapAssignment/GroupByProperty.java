import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private String department;
    private double salary;
    
    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
    
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    
    @Override
    public String toString() {
        return name;
    }
}

public class GroupByProperty {
    public static Map<String, List<Employee>> groupByDepartment(List<Employee> employees) {
        Map<String, List<Employee>> grouped = new HashMap<>();
        
        for (Employee emp : employees) {
            grouped.computeIfAbsent(emp.getDepartment(), k -> new ArrayList<>()).add(emp);
        }
        
        return grouped;
    }
    
    public static Map<String, List<Employee>> groupByDepartmentUsingStreams(List<Employee> employees) {
        return employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment));
    }
    
    public static Map<String, Double> averageSalaryByDepartment(List<Employee> employees) {
        return employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)
            ));
    }
    
    public static Map<String, Long> countByDepartment(List<Employee> employees) {
        return employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.counting()
            ));
    }
    
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", "HR", 50000));
        employees.add(new Employee("Bob", "IT", 70000));
        employees.add(new Employee("Carol", "HR", 55000));
        employees.add(new Employee("David", "IT", 75000));
        employees.add(new Employee("Eve", "Finance", 60000));
        employees.add(new Employee("Frank", "IT", 72000));
        employees.add(new Employee("Grace", "HR", 52000));
        employees.add(new Employee("Henry", "Finance", 65000));
        
        System.out.println("=== Employees Grouped by Department ===");
        Map<String, List<Employee>> grouped = groupByDepartment(employees);
        for (Map.Entry<String, List<Employee>> entry : grouped.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        System.out.println("\n=== Using Streams ===");
        Map<String, List<Employee>> groupedStreams = groupByDepartmentUsingStreams(employees);
        groupedStreams.forEach((dept, empList) -> 
            System.out.println(dept + ": " + empList));
        
        System.out.println("\n=== Employee Count by Department ===");
        Map<String, Long> countMap = countByDepartment(employees);
        countMap.forEach((dept, count) -> 
            System.out.println(dept + ": " + count + " employees"));
        
        System.out.println("\n=== Average Salary by Department ===");
        Map<String, Double> avgSalary = averageSalaryByDepartment(employees);
        avgSalary.forEach((dept, avg) -> 
            System.out.printf("%s: $%.2f%n", dept, avg));
        
        System.out.println("\n=== Detailed Department View ===");
        for (Map.Entry<String, List<Employee>> entry : grouped.entrySet()) {
            String dept = entry.getKey();
            List<Employee> deptEmployees = entry.getValue();
            double totalSalary = deptEmployees.stream()
                .mapToDouble(Employee::getSalary).sum();
            
            System.out.println("\nDepartment: " + dept);
            System.out.println("  Employees: " + deptEmployees);
            System.out.println("  Count: " + deptEmployees.size());
            System.out.printf("  Total Salary: $%.2f%n", totalSalary);
            System.out.printf("  Average Salary: $%.2f%n", totalSalary / deptEmployees.size());
        }
    }
}
