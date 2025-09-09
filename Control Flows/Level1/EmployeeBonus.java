import java.util.Scanner;

/**
 * Problem 17: Create a program to find the bonus of employees based on their years of service
 * Hint: Zara decided to give a bonus of 5% to employees whose year of service is more than 5 years.
 * Take salary and year of service in the year as input.
 * Print the bonus amount.
 */
public class EmployeeBonus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter employee's salary: ");
        double salary = scanner.nextDouble();
        System.out.print("Enter years of service: ");
        int yearsOfService = scanner.nextInt();
        
        double bonusAmount = 0.0;
        
        // Check if employee is eligible for bonus (more than 5 years of service)
        if (yearsOfService > 5) {
            bonusAmount = salary * 0.05; // 5% bonus
            System.out.println("Employee is eligible for bonus!");
            System.out.println("Bonus amount: $" + bonusAmount);
        } else {
            System.out.println("Employee is not eligible for bonus.");
            System.out.println("Years of service must be more than 5 years.");
            System.out.println("Bonus amount: $" + bonusAmount);
        }
        
        scanner.close();
    }
}
