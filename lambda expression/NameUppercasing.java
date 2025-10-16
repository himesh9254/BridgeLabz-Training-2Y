import java.util.*;
import java.util.stream.*;

class Employee {
    private String id;
    private String name;
    private String department;
    
    public Employee(String id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }
    
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
}

public class NameUppercasing {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("E001", "John Smith", "Engineering"),
            new Employee("E002", "Emma Johnson", "HR"),
            new Employee("E003", "Michael Brown", "Finance"),
            new Employee("E004", "Sarah Davis", "Marketing"),
            new Employee("E005", "Robert Wilson", "Engineering"),
            new Employee("E006", "Lisa Anderson", "Sales"),
            new Employee("E007", "David Martinez", "Operations")
        );
        
        System.out.println("=== HR Department - Official Letter Generator ===\n");
        
        System.out.println("ORIGINAL EMPLOYEE NAMES:");
        System.out.println("-".repeat(60));
        employees.stream()
                 .map(Employee::getName)
                 .forEach(System.out::println);
        
        System.out.println("\n\nMETHOD 1: Using String::toUpperCase");
        System.out.println("-".repeat(60));
        employees.stream()
                 .map(Employee::getName)
                 .map(String::toUpperCase)
                 .forEach(System.out::println);
        
        System.out.println("\n\nMETHOD 2: Formatted Letter Header");
        System.out.println("-".repeat(60));
        employees.stream()
                 .map(Employee::getName)
                 .map(String::toUpperCase)
                 .map(name -> "Dear " + name + ",")
                 .forEach(System.out::println);
        
        System.out.println("\n\nMETHOD 3: Complete Letter Format");
        System.out.println("=".repeat(60));
        System.out.println("                    OFFICIAL HR LETTER");
        System.out.println("=".repeat(60));
        
        employees.stream()
                 .limit(3)
                 .forEach(emp -> {
                     System.out.println("\nTO: " + emp.getName().toUpperCase());
                     System.out.println("EMPLOYEE ID: " + emp.getId());
                     System.out.println("DEPARTMENT: " + emp.getDepartment().toUpperCase());
                     System.out.println("\nDear " + emp.getName().toUpperCase() + ",");
                     System.out.println("\nThis is to inform you about the annual performance review.");
                     System.out.println("\nRegards,");
                     System.out.println("HR Department");
                     System.out.println("-".repeat(60));
                 });
        
        System.out.println("\n\nMETHOD 4: Employee List for Notice Board");
        System.out.println("=".repeat(60));
        System.out.println("EMPLOYEES TO BE NOTIFIED:");
        System.out.println("=".repeat(60));
        
        List<String> uppercaseNames = employees.stream()
                                               .map(Employee::getName)
                                               .map(String::toUpperCase)
                                               .collect(Collectors.toList());
        
        for (int i = 0; i < uppercaseNames.size(); i++) {
            System.out.println((i + 1) + ". " + uppercaseNames.get(i));
        }
        
        System.out.println("\n\nMETHOD 5: Department-wise Uppercase Names");
        System.out.println("=".repeat(60));
        
        Map<String, List<String>> departmentMap = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.mapping(
                    emp -> emp.getName().toUpperCase(),
                    Collectors.toList()
                )
            ));
        
        departmentMap.forEach((dept, names) -> {
            System.out.println("\n" + dept.toUpperCase() + ":");
            names.forEach(name -> System.out.println("  - " + name));
        });
        
        System.out.println("\n\nMETHOD 6: Sorted Uppercase Names");
        System.out.println("=".repeat(60));
        employees.stream()
                 .map(Employee::getName)
                 .map(String::toUpperCase)
                 .sorted()
                 .forEach(System.out::println);
    }
}
