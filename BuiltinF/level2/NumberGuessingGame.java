import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    
    public static void main(String[] args) {
        System.out.println("=== Number Guessing Game ===");
        System.out.println("Think of a number between 1 and 100.");
        System.out.println("I'll try to guess it!");
        System.out.println("For each guess, respond with:");
        System.out.println("'h' - if my guess is too high");
        System.out.println("'l' - if my guess is too low");
        System.out.println("'c' - if my guess is correct");
        System.out.println();
        
        playGame();
        scanner.close();
    }
    
    /**
     * Main game logic - coordinates the guessing process
     */
    public static void playGame() {
        int attempts = 0;
        int minRange = 1;
        int maxRange = 100;
        boolean gameWon = false;
        
        while (!gameWon && attempts < 10) {
            attempts++;
            int guess = generateGuess(minRange, maxRange);
            System.out.println("Attempt " + attempts + ": My guess is " + guess);
            
            char feedback = getUserFeedback();
            
            if (feedback == 'c') {
                displayWinMessage(attempts);
                gameWon = true;
            } else {
                int[] newRange = updateRange(guess, feedback, minRange, maxRange);
                minRange = newRange[0];
                maxRange = newRange[1];
                
                if (minRange > maxRange) {
                    System.out.println("Something went wrong! Please check your responses.");
                    return;
                }
            }
        }
        
        if (!gameWon) {
            System.out.println("I couldn't guess your number in 10 attempts!");
            System.out.println("You win this time!");
        }
    }
    
    /**
     * Generates a random guess within the current range
     */
    public static int generateGuess(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
    
    /**
     * Gets user feedback on the current guess
     */
    public static char getUserFeedback() {
        char feedback;
        do {
            System.out.print("Enter 'h' (high), 'l' (low), or 'c' (correct): ");
            String input = scanner.nextLine().toLowerCase();
            if (input.length() == 1) {
                feedback = input.charAt(0);
                if (feedback == 'h' || feedback == 'l' || feedback == 'c') {
                    return feedback;
                }
            }
            System.out.println("Invalid input! Please enter 'h', 'l', or 'c'.");
        } while (true);
    }
    
    /**
     * Updates the guessing range based on user feedback
     */
    public static int[] updateRange(int guess, char feedback, int currentMin, int currentMax) {
        int[] newRange = new int[2];
        
        if (feedback == 'h') {
            // Guess is too high, so the number is lower
            newRange[0] = currentMin;
            newRange[1] = guess - 1;
            System.out.println("Noted: Your number is lower than " + guess);
        } else if (feedback == 'l') {
            // Guess is too low, so the number is higher
            newRange[0] = guess + 1;
            newRange[1] = currentMax;
            System.out.println("Noted: Your number is higher than " + guess);
        }
        
        System.out.println("New range: " + newRange[0] + " to " + newRange[1]);
        System.out.println();
        return newRange;
    }
    
    /**
     * Displays winning message
     */
    public static void displayWinMessage(int attempts) {
        System.out.println();
        System.out.println("🎉 Excellent! I guessed your number!");
        System.out.println("It took me " + attempts + " attempt(s) to find it.");
        
        if (attempts <= 3) {
            System.out.println("I'm getting really good at this!");
        } else if (attempts <= 6) {
            System.out.println("Not bad! That was a fun challenge.");
        } else {
            System.out.println("That was tricky! You chose a good number.");
        }
    }
}
