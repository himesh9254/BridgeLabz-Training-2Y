import java.util.Scanner;

class UnitConverterGeneral {
    
    public static double convertFahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }
    
    public static double convertCelsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }
    
    public static double convertPoundsToKilograms(double pounds) {
        double pounds2kilograms = 0.453592;
        return pounds * pounds2kilograms;
    }
    
    public static double convertKilogramsToPounds(double kilograms) {
        double kilograms2pounds = 2.20462;
        return kilograms * kilograms2pounds;
    }
    
    public static double convertGallonsToLiters(double gallons) {
        double gallons2liters = 3.78541;
        return gallons * gallons2liters;
    }
    
    public static double convertLitersToGallons(double liters) {
        double liters2gallons = 0.264172;
        return liters * liters2gallons;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("General Unit Converter");
        System.out.println("1. Fahrenheit to Celsius");
        System.out.println("2. Celsius to Fahrenheit");
        System.out.println("3. Pounds to Kilograms");
        System.out.println("4. Kilograms to Pounds");
        System.out.println("5. Gallons to Liters");
        System.out.println("6. Liters to Gallons");
        System.out.print("Choose option (1-6): ");
        int choice = input.nextInt();
        
        System.out.print("Enter value to convert: ");
        double value = input.nextDouble();
        
        double result = 0;
        String fromUnit = "";
        String toUnit = "";
        
        switch (choice) {
            case 1:
                result = convertFahrenheitToCelsius(value);
                fromUnit = "°F";
                toUnit = "°C";
                break;
            case 2:
                result = convertCelsiusToFahrenheit(value);
                fromUnit = "°C";
                toUnit = "°F";
                break;
            case 3:
                result = convertPoundsToKilograms(value);
                fromUnit = "pounds";
                toUnit = "kilograms";
                break;
            case 4:
                result = convertKilogramsToPounds(value);
                fromUnit = "kilograms";
                toUnit = "pounds";
                break;
            case 5:
                result = convertGallonsToLiters(value);
                fromUnit = "gallons";
                toUnit = "liters";
                break;
            case 6:
                result = convertLitersToGallons(value);
                fromUnit = "liters";
                toUnit = "gallons";
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
