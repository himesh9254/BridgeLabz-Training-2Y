import java.util.Scanner;

class UnitConverterDistance {
    
    public static double convertKmToMiles(double km) {
        double km2miles = 0.621371;
        return km * km2miles;
    }
    
    public static double convertMilesToKm(double miles) {
        double miles2km = 1.60934;
        return miles * miles2km;
    }
    
    public static double convertMetersToFeet(double meters) {
        double meters2feet = 3.28084;
        return meters * meters2feet;
    }
    
    public static double convertFeetToMeters(double feet) {
        double feet2meters = 0.3048;
        return feet * feet2meters;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Distance Unit Converter");
        System.out.println("1. Kilometers to Miles");
        System.out.println("2. Miles to Kilometers");
        System.out.println("3. Meters to Feet");
        System.out.println("4. Feet to Meters");
        System.out.print("Choose option (1-4): ");
        int choice = input.nextInt();
        
        System.out.print("Enter value to convert: ");
        double value = input.nextDouble();
        
        double result = 0;
        String fromUnit = "";
        String toUnit = "";
        
        switch (choice) {
            case 1:
                result = convertKmToMiles(value);
                fromUnit = "kilometers";
                toUnit = "miles";
                break;
            case 2:
                result = convertMilesToKm(value);
                fromUnit = "miles";
                toUnit = "kilometers";
                break;
            case 3:
                result = convertMetersToFeet(value);
                fromUnit = "meters";
                toUnit = "feet";
                break;
            case 4:
                result = convertFeetToMeters(value);
                fromUnit = "feet";
                toUnit = "meters";
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
