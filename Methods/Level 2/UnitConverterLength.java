import java.util.Scanner;

class UnitConverterLength {
    
    public static double convertYardsToFeet(double yards) {
        double yards2feet = 3;
        return yards * yards2feet;
    }
    
    public static double convertFeetToYards(double feet) {
        double feet2yards = 0.333333;
        return feet * feet2yards;
    }
    
    public static double convertMetersToInches(double meters) {
        double meters2inches = 39.3701;
        return meters * meters2inches;
    }
    
    public static double convertInchesToMeters(double inches) {
        double inches2meters = 0.0254;
        return inches * inches2meters;
    }
    
    public static double convertInchesToCm(double inches) {
        double inches2cm = 2.54;
        return inches * inches2cm;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Length Unit Converter");
        System.out.println("1. Yards to Feet");
        System.out.println("2. Feet to Yards");
        System.out.println("3. Meters to Inches");
        System.out.println("4. Inches to Meters");
        System.out.println("5. Inches to Centimeters");
        System.out.print("Choose option (1-5): ");
        int choice = input.nextInt();
        
        System.out.print("Enter value to convert: ");
        double value = input.nextDouble();
        
        double result = 0;
        String fromUnit = "";
        String toUnit = "";
        
        switch (choice) {
            case 1:
                result = convertYardsToFeet(value);
                fromUnit = "yards";
                toUnit = "feet";
                break;
            case 2:
                result = convertFeetToYards(value);
                fromUnit = "feet";
                toUnit = "yards";
                break;
            case 3:
                result = convertMetersToInches(value);
                fromUnit = "meters";
                toUnit = "inches";
                break;
            case 4:
                result = convertInchesToMeters(value);
                fromUnit = "inches";
                toUnit = "meters";
                break;
            case 5:
                result = convertInchesToCm(value);
                fromUnit = "inches";
                toUnit = "centimeters";
                break;
            default:
                System.out.println("Invalid choice!");
                input.close();
                return;
        }
        
        System.out.printf("%.2f %s = %.2f %s%n", value, fromUnit, result, toUnit);
        
        input.close();
    }
}
