import java.util.Scanner;

/**
 * Problem 8: Count down the number from the user input value to 1 using a while loop for a rocket launch
 * Hint: Create a variable counter to take user inputted value for the countdown.
 * Use the while loop to check if the counter is 1
 * Inside a while loop, print the value of the counter and decrement the counter.
 */
public class RocketCountdownWhile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter countdown start value: ");
        int counter = scanner.nextInt();
        
        System.out.println("Rocket Launch Countdown:");
        
        // While loop countdown
        while (counter >= 1) {
            System.out.println(counter);
            counter--;
        }
        
        System.out.println("Blast Off!");
        
        scanner.close();
    }
}
