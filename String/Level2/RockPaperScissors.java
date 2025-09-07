import java.util.Scanner;

public class RockPaperScissors {
    
    public static String getComputerChoice() {
        int choice = (int)(Math.random() * 3);
        switch (choice) {
            case 0: return "rock";
            case 1: return "paper";
            case 2: return "scissors";
            default: return "rock";
        }
    }
    
    public static String findWinner(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return "tie";
        }
        
        if ((userChoice.equals("rock") && computerChoice.equals("scissors")) ||
            (userChoice.equals("paper") && computerChoice.equals("rock")) ||
            (userChoice.equals("scissors") && computerChoice.equals("paper"))) {
            return "user";
        }
        
        return "computer";
    }
    
    public static String[][] calculateStats(int userWins, int computerWins, int ties, int totalGames) {
        String[][] stats = new String[3][3];
        
        double userPercentage = (double)userWins / totalGames * 100;
        double computerPercentage = (double)computerWins / totalGames * 100;
        double tiePercentage = (double)ties / totalGames * 100;
        
        stats[0][0] = "User";
        stats[0][1] = String.valueOf(userWins);
        stats[0][2] = String.format("%.1f%%", userPercentage);
        
        stats[1][0] = "Computer";
        stats[1][1] = String.valueOf(computerWins);
        stats[1][2] = String.format("%.1f%%", computerPercentage);
        
        stats[2][0] = "Ties";
        stats[2][1] = String.valueOf(ties);
        stats[2][2] = String.format("%.1f%%", tiePercentage);
        
        return stats;
    }
    
    public static void displayGameResults(String[][] gameResults, String[][] stats) {
        System.out.println("\n--- Game Results ---");
        System.out.println("Game\tUser\t\tComputer\tWinner");
        System.out.println("-----------------------------------------------");
        
        for (int i = 0; i < gameResults.length; i++) {
            System.out.println((i + 1) + "\t" + gameResults[i][0] + "\t\t" + 
                             gameResults[i][1] + "\t\t" + gameResults[i][2]);
        }
        
        System.out.println("\n--- Final Statistics ---");
        System.out.println("Player\t\tWins\tPercentage");
        System.out.println("--------------------------------");
        
        for (int i = 0; i < stats.length; i++) {
            System.out.println(stats[i][0] + "\t\t" + stats[i][1] + "\t" + stats[i][2]);
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number of games to play: ");
        int numGames = scanner.nextInt();
        
        String[][] gameResults = new String[numGames][3];
        int userWins = 0, computerWins = 0, ties = 0;
        
        for (int i = 0; i < numGames; i++) {
            System.out.print("\nGame " + (i + 1) + " - Enter your choice (rock/paper/scissors): ");
            String userChoice = scanner.next().toLowerCase();
            
            if (!userChoice.equals("rock") && !userChoice.equals("paper") && !userChoice.equals("scissors")) {
                System.out.println("Invalid choice! Using 'rock' as default.");
                userChoice = "rock";
            }
            
            String computerChoice = getComputerChoice();
            String winner = findWinner(userChoice, computerChoice);
            
            gameResults[i][0] = userChoice;
            gameResults[i][1] = computerChoice;
            gameResults[i][2] = winner;
            
            if (winner.equals("user")) {
                userWins++;
            } else if (winner.equals("computer")) {
                computerWins++;
            } else {
                ties++;
            }
            
            System.out.println("You: " + userChoice + ", Computer: " + computerChoice + " -> " + winner + " wins!");
        }
        
        String[][] stats = calculateStats(userWins, computerWins, ties, numGames);
        displayGameResults(gameResults, stats);
        
        scanner.close();
    }
}
