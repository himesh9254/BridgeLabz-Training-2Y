import java.util.Scanner;

/**
 * Problem 9: Rewrite program 8 to do the countdown using the for-loop
 */
public class RocketCountdownFor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter countdown start value: ");
        int startValue = scanner.nextInt();
        
        System.out.println("Rocket Launch Countdown:");
        
        // For loop countdown
        for (int counter = startValue; counter >= 1; counter--) {
            System.out.println(counter);
        }
        
        System.out.println("Blast Off!");
        
        scanner.close();
    }
}
