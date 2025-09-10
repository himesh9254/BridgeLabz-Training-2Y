import java.util.Scanner;

class AthleteRunCalculator {
    
    public static int calculateRounds(double side1, double side2, double side3) {
        double perimeter = side1 + side2 + side3;
        double targetDistance = 5000;
        return (int) Math.ceil(targetDistance / perimeter);
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter the three sides of the triangular park (in meters):");
        System.out.print("Side 1: ");
        double side1 = input.nextDouble();
        
        System.out.print("Side 2: ");
        double side2 = input.nextDouble();
        
        System.out.print("Side 3: ");
        double side3 = input.nextDouble();
        
        double perimeter = side1 + side2 + side3;
        int rounds = calculateRounds(side1, side2, side3);
        
        System.out.println("Perimeter of the triangular park: " + perimeter + " meters");
        System.out.println("Number of rounds needed to complete 5 km run: " + rounds);
        
        input.close();
    }
}
