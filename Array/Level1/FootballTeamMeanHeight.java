import java.util.Scanner;

public class FootballTeamMeanHeight {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] heights = new double[11]; // Array for 11 players
        double sum = 0.0;
        
        System.out.println("Enter the heights of 11 football players (in cm):");
        
        // Get input for heights of all 11 players
        for (int i = 0; i < heights.length; i++) {
            System.out.print("Enter height of player " + (i + 1) + ": ");
            heights[i] = scanner.nextDouble();
            sum += heights[i]; // Add to sum while taking input
        }
        
        // Calculate mean height
        double meanHeight = sum / heights.length;
        
        // Display results
        System.out.println("\nFootball Team Height Analysis:");
        System.out.println("==============================");
        
        System.out.println("Heights of all players:");
        for (int i = 0; i < heights.length; i++) {
            System.out.println("Player " + (i + 1) + ": " + heights[i] + " cm");
        }
        
        System.out.println("\nSum of all heights: " + sum + " cm");
        System.out.println("Number of players: " + heights.length);
        System.out.println("Mean height of the football team: " + String.format("%.2f", meanHeight) + " cm");
        
        scanner.close();
    }
}
