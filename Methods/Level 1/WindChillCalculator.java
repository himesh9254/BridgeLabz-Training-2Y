import java.util.Scanner;

class WindChillCalculator {
    
    public static double calculateWindChill(double temperature, double windSpeed) {
        return 35.74 + 0.6215 * temperature + 
               (0.4275 * temperature - 35.75) * Math.pow(windSpeed, 0.16);
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter temperature (in Fahrenheit): ");
        double temperature = input.nextDouble();
        
        System.out.print("Enter wind speed (in mph): ");
        double windSpeed = input.nextDouble();
        
        if (windSpeed < 0) {
            System.out.println("Wind speed cannot be negative.");
        } else {
            double windChill = calculateWindChill(temperature, windSpeed);
            
            System.out.printf("Wind Chill Temperature: %.2f°F%n", windChill);
            System.out.println("Given Temperature: " + temperature + "°F");
            System.out.println("Given Wind Speed: " + windSpeed + " mph");
        }
        
        input.close();
    }
}
