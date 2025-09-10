import java.util.Scanner;

public class EmployeeBonusCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Arrays to store employee data
        double[] salaries = new double[10];
        double[] yearsOfService = new double[10];
        double[] bonuses = new double[10];
        double[] newSalaries = new double[10];
        
        // Variables for totals
        double totalBonus = 0.0;
        double totalOldSalary = 0.0;
        double totalNewSalary = 0.0;
        
        System.out.println("=== ZARA EMPLOYEE BONUS CALCULATOR ===");
        System.out.println("Bonus Policy: 5% for >5 years service, 2% for ≤5 years service");
        System.out.println();
        
        // Take input for 10 employees with validation
        for (int i = 0; i < 10; i++) {
            System.out.println("Employee " + (i + 1) + ":");
            
            // Get salary with validation
            while (true) {
                System.out.print("Enter salary: $");
                salaries[i] = scanner.nextDouble();
                if (salaries[i] <= 0) {
                    System.out.println("Invalid salary! Please enter a positive amount.");
                    i--; // Decrement to re-enter this employee's data
                    break;
                } else {
                    break;
                }
            }
            
            if (salaries[i] <= 0) continue; // Skip to next iteration if invalid
            
            // Get years of service with validation
            while (true) {
                System.out.print("Enter years of service: ");
                yearsOfService[i] = scanner.nextDouble();
                if (yearsOfService[i] < 0) {
                    System.out.println("Invalid years of service! Please enter a non-negative number.");
                    i--; // Decrement to re-enter this employee's data
                    break;
                } else {
                    break;
                }
            }
            
            if (yearsOfService[i] < 0) continue; // Skip to next iteration if invalid
            
            System.out.println();
        }
        
        // Calculate bonuses and new salaries
        for (int i = 0; i < 10; i++) {
            // Determine bonus percentage based on years of service
            double bonusPercentage;
            if (yearsOfService[i] > 5) {
                bonusPercentage = 0.05; // 5%
            } else {
                bonusPercentage = 0.02; // 2%
            }
            
            // Calculate bonus and new salary
            bonuses[i] = salaries[i] * bonusPercentage;
            newSalaries[i] = salaries[i] + bonuses[i];
            
            // Add to totals
            totalOldSalary += salaries[i];
            totalBonus += bonuses[i];
            totalNewSalary += newSalaries[i];
        }
        
        // Display results
        System.out.println("=== EMPLOYEE BONUS REPORT ===");
        System.out.println("==============================");
        System.out.printf("%-10s %-12s %-8s %-10s %-12s %-12s%n", 
                "Employee", "Old Salary", "Years", "Bonus %", "Bonus Amt", "New Salary");
        System.out.println("------------------------------------------------------------------------");
        
        for (int i = 0; i < 10; i++) {
            String bonusPercent = (yearsOfService[i] > 5) ? "5%" : "2%";
            System.out.printf("%-10d $%-11.2f %-8.1f %-10s $%-11.2f $%-11.2f%n",
                    (i + 1), salaries[i], yearsOfService[i], bonusPercent, bonuses[i], newSalaries[i]);
        }
        
        System.out.println("------------------------------------------------------------------------");
        System.out.printf("TOTALS:    $%-11.2f %8s %10s $%-11.2f $%-11.2f%n",
                totalOldSalary, "", "", totalBonus, totalNewSalary);
        
        System.out.println("\\n=== SUMMARY ===");
        System.out.printf("Total Old Salary:  $%.2f%n", totalOldSalary);
        System.out.printf("Total Bonus Payout: $%.2f%n", totalBonus);
        System.out.printf("Total New Salary:  $%.2f%n", totalNewSalary);
        System.out.printf("Average Bonus per Employee: $%.2f%n", totalBonus / 10);
        
        scanner.close();
    }
}
