// 1. Temperature Alert System
// Alert if temperature crosses threshold using Predicate<Double>

import java.util.function.Predicate;

public class TemperatureAlert {
    public static void main(String[] args) {
        // Define threshold temperature
        double threshold = 35.0;
        
        // Create a Predicate to check if temperature exceeds threshold
        Predicate<Double> isHighTemperature = temp -> temp > threshold;
        
        // Test temperatures
        double[] temperatures = {25.5, 32.0, 36.5, 40.0, 28.0, 38.5};
        
        System.out.println("Temperature Alert System (Threshold: " + threshold + "°C)");
        System.out.println("=".repeat(50));
        
        for (double temp : temperatures) {
            if (isHighTemperature.test(temp)) {
                System.out.println("⚠️  ALERT: Temperature " + temp + "°C exceeds threshold!");
            } else {
                System.out.println("✓  Normal: Temperature " + temp + "°C is within safe range");
            }
        }
        
        // Advanced: Combine predicates for multiple conditions
        Predicate<Double> isCritical = temp -> temp > 40.0;
        Predicate<Double> isWarning = temp -> temp > 35.0 && temp <= 40.0;
        
        System.out.println("\n" + "Advanced Alert Levels".toUpperCase());
        System.out.println("=".repeat(50));
        
        for (double temp : temperatures) {
            if (isCritical.test(temp)) {
                System.out.println("🚨 CRITICAL: " + temp + "°C - Immediate action required!");
            } else if (isWarning.test(temp)) {
                System.out.println("⚠️  WARNING: " + temp + "°C - Monitor closely");
            } else {
                System.out.println("✓  NORMAL: " + temp + "°C");
            }
        }
    }
}
