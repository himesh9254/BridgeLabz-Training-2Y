import java.util.Scanner;

public class TemperatureConverter {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== Temperature Converter ===");
        System.out.println("This program converts temperatures between Fahrenheit and Celsius.");
        System.out.println();
        
        boolean continueProgram = true;
        
        while (continueProgram) {
            // Display menu
            displayMenu();
            
            // Get user choice
            int choice = getUserChoice();
            
            // Process user choice
            switch (choice) {
                case 1:
                    performCelsiusToFahrenheit();
                    break;
                case 2:
                    performFahrenheitToCelsius();
                    break;
                case 3:
                    showTemperatureTable();
                    break;
                case 4:
                    showTemperatureComparisons();
                    break;
                case 5:
                    continueProgram = false;
                    System.out.println("Thank you for using the Temperature Converter!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            
            if (continueProgram) {
                System.out.println();
                System.out.print("Press Enter to continue...");;
                scanner.nextLine();
                System.out.println();
            }
        }
        
        scanner.close();
    }
    
    /**
     * Displays the main menu options
     */
    public static void displayMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. Convert Celsius to Fahrenheit");
        System.out.println("2. Convert Fahrenheit to Celsius");
        System.out.println("3. Show Temperature Conversion Table");
        System.out.println("4. Show Temperature Comparisons");
        System.out.println("5. Exit");
        System.out.println();
    }
    
    /**
     * Gets user menu choice with validation
     * @return valid menu choice (1-5)
     */
    public static int getUserChoice() {
        while (true) {
            try {
                System.out.print("Enter your choice (1-5): ");
                int choice = Integer.parseInt(scanner.nextLine());
                
                if (choice >= 1 && choice <= 5) {
                    return choice;
                } else {
                    System.out.println("Please enter a number between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
    
    /**
     * Gets temperature input from user
     * @param prompt the message to display
     * @return valid temperature as double
     */
    public static double getTemperatureInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
    
    /**
     * Converts Celsius to Fahrenheit
     * @param celsius temperature in Celsius
     * @return temperature in Fahrenheit
     */
    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9.0 / 5.0) + 32.0;
    }
    
    /**
     * Converts Fahrenheit to Celsius
     * @param fahrenheit temperature in Fahrenheit
     * @return temperature in Celsius
     */
    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32.0) * 5.0 / 9.0;
    }
    
    /**
     * Converts Celsius to Kelvin
     * @param celsius temperature in Celsius
     * @return temperature in Kelvin
     */
    public static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }
    
    /**
     * Converts Fahrenheit to Kelvin
     * @param fahrenheit temperature in Fahrenheit
     * @return temperature in Kelvin
     */
    public static double fahrenheitToKelvin(double fahrenheit) {
        double celsius = fahrenheitToCelsius(fahrenheit);
        return celsiusToKelvin(celsius);
    }
    
    /**
     * Converts Kelvin to Celsius
     * @param kelvin temperature in Kelvin
     * @return temperature in Celsius
     */
    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }
    
    /**
     * Converts Kelvin to Fahrenheit
     * @param kelvin temperature in Kelvin
     * @return temperature in Fahrenheit
     */
    public static double kelvinToFahrenheit(double kelvin) {
        double celsius = kelvinToCelsius(kelvin);
        return celsiusToFahrenheit(celsius);
    }
    
    /**
     * Performs Celsius to Fahrenheit conversion with user interaction
     */
    public static void performCelsiusToFahrenheit() {
        System.out.println();
        System.out.println("=== Celsius to Fahrenheit Conversion ===");
        
        double celsius = getTemperatureInput("Enter temperature in Celsius: ");
        double fahrenheit = celsiusToFahrenheit(celsius);
        
        displayConversionResult(celsius, "°C", fahrenheit, "°F");
        showConversionFormula("Celsius to Fahrenheit", "F = (C × 9/5) + 32");
        
        // Show additional conversions
        double kelvin = celsiusToKelvin(celsius);
        System.out.println("Bonus conversion to Kelvin: " + String.format("%.2f", kelvin) + " K");
        
        // Show temperature classification
        classifyTemperature(celsius, "Celsius");
    }
    
    /**
     * Performs Fahrenheit to Celsius conversion with user interaction
     */
    public static void performFahrenheitToCelsius() {
        System.out.println();
        System.out.println("=== Fahrenheit to Celsius Conversion ===");
        
        double fahrenheit = getTemperatureInput("Enter temperature in Fahrenheit: ");
        double celsius = fahrenheitToCelsius(fahrenheit);
        
        displayConversionResult(fahrenheit, "°F", celsius, "°C");
        showConversionFormula("Fahrenheit to Celsius", "C = (F - 32) × 5/9");
        
        // Show additional conversions
        double kelvin = fahrenheitToKelvin(fahrenheit);
        System.out.println("Bonus conversion to Kelvin: " + String.format("%.2f", kelvin) + " K");
        
        // Show temperature classification
        classifyTemperature(celsius, "Celsius");
    }
    
    /**
     * Displays conversion result in a formatted manner
     * @param inputTemp input temperature value
     * @param inputUnit input temperature unit
     * @param outputTemp output temperature value
     * @param outputUnit output temperature unit
     */
    public static void displayConversionResult(double inputTemp, String inputUnit, 
                                             double outputTemp, String outputUnit) {
        System.out.println();
        System.out.println("=== Conversion Result ===");
        System.out.printf("%.2f %s = %.2f %s%n", inputTemp, inputUnit, outputTemp, outputUnit);
        System.out.printf("Rounded: %.0f %s = %.0f %s%n", inputTemp, inputUnit, 
                         Math.round(outputTemp), outputUnit);
    }
    
    /**
     * Shows the conversion formula used
     * @param conversionType type of conversion
     * @param formula the mathematical formula
     */
    public static void showConversionFormula(String conversionType, String formula) {
        System.out.println();
        System.out.println("Formula used:");
        System.out.println(conversionType + ": " + formula);
    }
    
    /**
     * Classifies temperature based on common reference points
     * @param temp temperature value
     * @param unit temperature unit
     */
    public static void classifyTemperature(double temp, String unit) {
        System.out.println();
        System.out.println("Temperature Classification:");
        
        if (unit.equals("Celsius")) {
            if (temp < -273.15) {
                System.out.println("⚠️  Below absolute zero (impossible!)");
            } else if (temp < -40) {
                System.out.println("🥶 Extremely cold (colder than Antarctic winter)");
            } else if (temp < 0) {
                System.out.println("🧊 Freezing (water freezes at 0°C)");
            } else if (temp < 10) {
                System.out.println("❄️  Cold (winter weather)");
            } else if (temp < 20) {
                System.out.println("😊 Cool (comfortable)");
            } else if (temp < 30) {
                System.out.println("☀️  Warm (pleasant weather)");
            } else if (temp < 40) {
                System.out.println("🔥 Hot (summer heat)");
            } else if (temp < 100) {
                System.out.println("🌡️  Very hot (dangerous heat)");
            } else if (temp >= 100) {
                System.out.println("💨 Boiling point of water or higher!");
            }
        }
    }
    
    /**
     * Shows a temperature conversion table
     */
    public static void showTemperatureTable() {
        System.out.println();
        System.out.println("=== Temperature Conversion Table ===");
        System.out.println("Celsius | Fahrenheit | Kelvin  | Description");
        System.out.println("---------|------------|---------|------------------");
        
        double[] celsiusValues = {-273.15, -40, -20, 0, 10, 20, 25, 30, 37, 50, 100};
        String[] descriptions = {
            "Absolute Zero",
            "Equal C & F",
            "Very Cold",
            "Water Freezes",
            "Cool",
            "Room Temp",
            "Comfortable",
            "Warm",
            "Body Temp",
            "Hot",
            "Water Boils"
        };
        
        for (int i = 0; i < celsiusValues.length; i++) {
            double c = celsiusValues[i];
            double f = celsiusToFahrenheit(c);
            double k = celsiusToKelvin(c);
            
            System.out.printf("%7.1f | %10.1f | %7.1f | %s%n", c, f, k, descriptions[i]);
        }
    }
    
    /**
     * Shows temperature comparisons and interesting facts
     */
    public static void showTemperatureComparisons() {
        System.out.println();
        System.out.println("=== Temperature Comparisons & Facts ===");
        
        System.out.println("Common Temperature References:");
        System.out.println("• Absolute Zero: -273.15°C = -459.67°F = 0 K");
        System.out.println("• Water Freezes: 0°C = 32°F = 273.15 K");
        System.out.println("• Room Temperature: ~20°C = ~68°F = ~293 K");
        System.out.println("• Human Body: 37°C = 98.6°F = 310.15 K");
        System.out.println("• Water Boils: 100°C = 212°F = 373.15 K");
        
        System.out.println();
        System.out.println("Interesting Facts:");
        System.out.println("• -40°C = -40°F (the only point where C and F are equal)");
        System.out.println("• Fahrenheit scale: 0°F = very cold winter day in 1724");
        System.out.println("• Celsius scale: 0°C = water freezing point");
        System.out.println("• Kelvin scale: 0 K = absolute zero (no molecular motion)");
        
        System.out.println();
        System.out.println("Extreme Temperatures:");
        System.out.println("• Coldest on Earth: -89.2°C (-128.6°F) in Antarctica");
        System.out.println("• Hottest on Earth: 56.7°C (134°F) in Death Valley");
        System.out.println("• Sun's Surface: ~5,500°C (~10,000°F)");
        System.out.println("• Human Body Fever: >38°C (>100.4°F)");
        
        System.out.println();
        System.out.println("Quick Mental Conversion Tips:");
        System.out.println("• C to F: Double C, subtract 10%, add 32");
        System.out.println("• F to C: Subtract 32, halve it, add 10%");
        System.out.println("• Example: 20°C ≈ (20×2) - (4) + 32 = 68°F");
        
        // Interactive comparison
        System.out.println();
        System.out.print("Want to compare a specific temperature? Enter Celsius (or 'skip'): ");
        String input = scanner.nextLine().trim();
        
        if (!input.equalsIgnoreCase("skip")) {
            try {
                double celsius = Double.parseDouble(input);
                double fahrenheit = celsiusToFahrenheit(celsius);
                double kelvin = celsiusToKelvin(celsius);
                
                System.out.println();
                System.out.println("Your Temperature Comparison:");
                System.out.printf("%.1f°C = %.1f°F = %.1f K%n", celsius, fahrenheit, kelvin);
                
                // Compare to reference points
                System.out.println("Compared to references:");
                if (celsius < 0) {
                    System.out.printf("%.1f°C colder than water freezing%n", Math.abs(celsius));
                } else if (celsius > 0) {
                    System.out.printf("%.1f°C warmer than water freezing%n", celsius);
                }
                
                if (celsius < 20) {
                    System.out.printf("%.1f°C colder than room temperature%n", 20 - celsius);
                } else if (celsius > 20) {
                    System.out.printf("%.1f°C warmer than room temperature%n", celsius - 20);
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, skipping comparison.");
            }
        }
    }
}
