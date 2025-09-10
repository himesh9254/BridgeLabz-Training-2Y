class EmployeeBonusSystem {
    public static int[][] generateEmployeeData() {
        int[][] data = new int[10][2];
        for (int i = 0; i < 10; i++) {
            data[i][0] = (int) (Math.random() * 90000) + 10000;
            data[i][1] = (int) (Math.random() * 10) + 1;
        }
        return data;
    }
    
    public static double[][] calculateBonusAndNewSalary(int[][] employeeData) {
        double[][] results = new double[10][2];
        
        for (int i = 0; i < 10; i++) {
            int salary = employeeData[i][0];
            int yearsOfService = employeeData[i][1];
            
            double bonusPercentage = yearsOfService > 5 ? 0.05 : 0.02;
            double bonus = salary * bonusPercentage;
            double newSalary = salary + bonus;
            
            results[i][0] = newSalary;
            results[i][1] = bonus;
        }
        return results;
    }
    
    public static void displayResults(int[][] employeeData, double[][] bonusData) {
        double totalOldSalary = 0;
        double totalNewSalary = 0;
        double totalBonus = 0;
        
        System.out.println("Employee\tOld Salary\tYears\tNew Salary\tBonus");
        for (int i = 0; i < 10; i++) {
            totalOldSalary += employeeData[i][0];
            totalNewSalary += bonusData[i][0];
            totalBonus += bonusData[i][1];
            
            System.out.printf("%d\t\t%d\t\t%d\t%.2f\t\t%.2f%n",
                i + 1, employeeData[i][0], employeeData[i][1], bonusData[i][0], bonusData[i][1]);
        }
        
        System.out.printf("%nTotal Old Salary: %.2f%n", totalOldSalary);
        System.out.printf("Total New Salary: %.2f%n", totalNewSalary);
        System.out.printf("Total Bonus: %.2f%n", totalBonus);
    }
    
    public static void main(String[] args) {
        int[][] employeeData = generateEmployeeData();
        double[][] bonusData = calculateBonusAndNewSalary(employeeData);
        displayResults(employeeData, bonusData);
    }
}