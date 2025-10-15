// 2. Unit Conversion Tool
// Logistics software needs standard unit conversions
// Implement conversions as static interface methods

interface UnitConverter {
    // Distance conversions
    static double kmToMiles(double km) {
        return km * 0.621371;
    }
    
    static double milesToKm(double miles) {
        return miles * 1.60934;
    }
    
    // Weight conversions
    static double kgToLbs(double kg) {
        return kg * 2.20462;
    }
    
    static double lbsToKg(double lbs) {
        return lbs * 0.453592;
    }
    
    // Temperature conversions
    static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }
    
    static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }
    
    // Volume conversions
    static double litersToGallons(double liters) {
        return liters * 0.264172;
    }
    
    static double gallonsToLiters(double gallons) {
        return gallons * 3.78541;
    }
}

public class UnitConversionTool {
    public static void main(String[] args) {
        System.out.println("=== Logistics Unit Conversion Tool ===\n");
        
        // Distance conversions
        System.out.println("DISTANCE CONVERSIONS:");
        System.out.println("-".repeat(40));
        double distance = 100;
        System.out.println(distance + " km = " + 
                          String.format("%.2f", UnitConverter.kmToMiles(distance)) + " miles");
        double miles = 50;
        System.out.println(miles + " miles = " + 
                          String.format("%.2f", UnitConverter.milesToKm(miles)) + " km");
        
        // Weight conversions
        System.out.println("\nWEIGHT CONVERSIONS:");
        System.out.println("-".repeat(40));
        double weight = 75;
        System.out.println(weight + " kg = " + 
                          String.format("%.2f", UnitConverter.kgToLbs(weight)) + " lbs");
        double lbs = 150;
        System.out.println(lbs + " lbs = " + 
                          String.format("%.2f", UnitConverter.lbsToKg(lbs)) + " kg");
        
        // Temperature conversions
        System.out.println("\nTEMPERATURE CONVERSIONS:");
        System.out.println("-".repeat(40));
        double tempC = 25;
        System.out.println(tempC + "°C = " + 
                          String.format("%.2f", UnitConverter.celsiusToFahrenheit(tempC)) + "°F");
        double tempF = 77;
        System.out.println(tempF + "°F = " + 
                          String.format("%.2f", UnitConverter.fahrenheitToCelsius(tempF)) + "°C");
        
        // Volume conversions
        System.out.println("\nVOLUME CONVERSIONS:");
        System.out.println("-".repeat(40));
        double volume = 20;
        System.out.println(volume + " liters = " + 
                          String.format("%.2f", UnitConverter.litersToGallons(volume)) + " gallons");
        double gallons = 10;
        System.out.println(gallons + " gallons = " + 
                          String.format("%.2f", UnitConverter.gallonsToLiters(gallons)) + " liters");
        
        // Practical logistics example
        System.out.println("\n=== LOGISTICS SHIPMENT EXAMPLE ===");
        System.out.println("-".repeat(40));
        double shipmentWeight = 500; // kg
        double shipmentDistance = 250; // km
        
        System.out.println("Shipment Details:");
        System.out.println("  Weight: " + shipmentWeight + " kg (" + 
                          String.format("%.2f", UnitConverter.kgToLbs(shipmentWeight)) + " lbs)");
        System.out.println("  Distance: " + shipmentDistance + " km (" + 
                          String.format("%.2f", UnitConverter.kmToMiles(shipmentDistance)) + " miles)");
    }
}
