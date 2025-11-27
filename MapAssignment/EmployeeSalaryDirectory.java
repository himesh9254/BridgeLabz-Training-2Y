import java.util.*;

public class EmployeeSalaryDirectory {
    private Map<String, Double> salaryDirectory;
    
    public EmployeeSalaryDirectory() {
        salaryDirectory = new HashMap<>();
    }
    
    public void addEmployee(String name, double salary) {
        if (salaryDirectory.containsKey(name)) {
            System.out.println("Employee already exists: " + name);
            return;
        }
        salaryDirectory.put(name, salary);
        System.out.printf("Added: %s with salary $%.2f%n", name, salary);
    }
    
    public void updateSalary(String name, double newSalary) {
        if (!salaryDirectory.containsKey(name)) {
            System.out.println("Employee not found: " + name);
            return;
        }
        double oldSalary = salaryDirectory.get(name);
        salaryDirectory.put(name, newSalary);
        System.out.printf("Updated %s: $%.2f -> $%.2f%n", name, oldSalary, newSalary);
    }
    
    public void giveRaise(String name, double percentage) {
        if (!salaryDirectory.containsKey(name)) {
            System.out.println("Employee not found: " + name);
            return;
        }
        double oldSalary = salaryDirectory.get(name);
        double raise = oldSalary * (percentage / 100.0);
        double newSalary = oldSalary + raise;
        salaryDirectory.put(name, newSalary);
        System.out.printf("%s received %.1f%% raise: $%.2f -> $%.2f (+$%.2f)%n", 
            name, percentage, oldSalary, newSalary, raise);
    }
    
    public void giveRaiseToAll(double percentage) {
        System.out.println("\n=== Giving " + percentage + "% raise to all employees ===");
        for (String name : salaryDirectory.keySet()) {
            giveRaise(name, percentage);
        }
    }
    
    public void removeEmployee(String name) {
        if (salaryDirectory.remove(name) != null) {
            System.out.println("Removed employee: " + name);
        } else {
            System.out.println("Employee not found: " + name);
        }
    }
    
    public double getAverageSalary() {
        if (salaryDirectory.isEmpty()) return 0.0;
        
        double sum = 0;
        for (double salary : salaryDirectory.values()) {
            sum += salary;
        }
        return sum / salaryDirectory.size();
    }
    
    public double getTotalPayroll() {
        double total = 0;
        for (double salary : salaryDirectory.values()) {
            total += salary;
        }
        return total;
    }
    
    public void displayAllEmployees() {
        System.out.println("\n=== Employee Salary Directory ===");
        TreeMap<String, Double> sorted = new TreeMap<>(salaryDirectory);
        
        for (Map.Entry<String, Double> entry : sorted.entrySet()) {
            System.out.printf("%s: $%.2f%n", entry.getKey(), entry.getValue());
        }
    }
    
    public void displaySortedBySalary() {
        System.out.println("\n=== Employees Sorted by Salary (Descending) ===");
        
        List<Map.Entry<String, Double>> sorted = new ArrayList<>(salaryDirectory.entrySet());
        sorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        int rank = 1;
        for (Map.Entry<String, Double> entry : sorted) {
            System.out.printf("%d. %s: $%.2f%n", rank++, entry.getKey(), entry.getValue());
        }
    }
    
    public void displayStatistics() {
        System.out.println("\n=== Salary Statistics ===");
        System.out.println("Total Employees: " + salaryDirectory.size());
        System.out.printf("Total Payroll: $%.2f%n", getTotalPayroll());
        System.out.printf("Average Salary: $%.2f%n", getAverageSalary());
        
        if (!salaryDirectory.isEmpty()) {
            double max = Collections.max(salaryDirectory.values());
            double min = Collections.min(salaryDirectory.values());
            
            String highestPaid = "";
            String lowestPaid = "";
            
            for (Map.Entry<String, Double> entry : salaryDirectory.entrySet()) {
                if (entry.getValue() == max) highestPaid = entry.getKey();
                if (entry.getValue() == min) lowestPaid = entry.getKey();
            }
            
            System.out.printf("Highest Paid: %s ($%.2f)%n", highestPaid, max);
            System.out.printf("Lowest Paid: %s ($%.2f)%n", lowestPaid, min);
        }
    }
    
    public static void main(String[] args) {
        EmployeeSalaryDirectory directory = new EmployeeSalaryDirectory();
        
        System.out.println("=== Adding Employees ===");
        directory.addEmployee("Alice", 75000.00);
        directory.addEmployee("Bob", 65000.00);
        directory.addEmployee("Charlie", 85000.00);
        directory.addEmployee("Diana", 72000.00);
        directory.addEmployee("Eve", 58000.00);
        directory.addEmployee("Frank", 92000.00);
        
        directory.displayAllEmployees();
        directory.displayStatistics();
        
        System.out.println("\n=== Individual Raises ===");
        directory.giveRaise("Alice", 10.0);
        directory.giveRaise("Eve", 15.0);
        
        directory.giveRaiseToAll(5.0);
        
        directory.displaySortedBySalary();
        directory.displayStatistics();
        
        System.out.println("\n=== Employee Leaving ===");
        directory.removeEmployee("Bob");
        
        directory.displayAllEmployees();
        directory.displayStatistics();
    }
}
