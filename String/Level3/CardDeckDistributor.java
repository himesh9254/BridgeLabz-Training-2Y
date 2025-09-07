import java.util.Scanner;

public class CardDeckDistributor {
    
    // Method to initialize deck of cards
    public static String[] initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        
        int numOfCards = suits.length * ranks.length;
        String[] deck = new String[numOfCards];
        
        int index = 0;
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                deck[index] = ranks[j] + " of " + suits[i];
                index++;
            }
        }
        
        return deck;
    }
    
    // Method to shuffle the deck
    public static String[] shuffleDeck(String[] deck) {
        int n = deck.length;
        
        for (int i = 0; i < n; i++) {
            // Generate random card number between i and n
            int randomCardNumber = i + (int)(Math.random() * (n - i));
            
            // Swap current card with random card
            String temp = deck[i];
            deck[i] = deck[randomCardNumber];
            deck[randomCardNumber] = temp;
        }
        
        return deck;
    }
    
    // Method to distribute cards to players
    public static String[][] distributeCards(String[] deck, int numCards, int numPlayers) {
        // Check if cards can be distributed
        if (numCards * numPlayers > deck.length) {
            return null; // Cannot distribute
        }
        
        String[][] players = new String[numPlayers][numCards];
        int cardIndex = 0;
        
        for (int i = 0; i < numCards; i++) {
            for (int j = 0; j < numPlayers; j++) {
                players[j][i] = deck[cardIndex];
                cardIndex++;
            }
        }
        
        return players;
    }
    
    // Method to print players and their cards
    public static void printPlayersCards(String[][] players, int numCards) {
        System.out.println("\n--- Card Distribution ---");
        
        for (int i = 0; i < players.length; i++) {
            System.out.println("Player " + (i + 1) + " cards:");
            for (int j = 0; j < numCards; j++) {
                System.out.println("  " + (j + 1) + ". " + players[i][j]);
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Card Deck Distribution System ===");
        
        // Initialize and display deck
        String[] deck = initializeDeck();
        System.out.println("Deck initialized with " + deck.length + " cards.");
        
        System.out.print("\nEnter number of players: ");
        int numPlayers = scanner.nextInt();
        
        System.out.print("Enter number of cards per player: ");
        int numCards = scanner.nextInt();
        
        // Validate input
        if (numPlayers <= 0 || numCards <= 0) {
            System.out.println("Number of players and cards must be positive!");
            scanner.close();
            return;
        }
        
        if (numCards * numPlayers > deck.length) {
            System.out.println("Cannot distribute " + numCards + " cards to " + numPlayers + 
                             " players. Total required: " + (numCards * numPlayers) + 
                             ", Available: " + deck.length);
            scanner.close();
            return;
        }
        
        // Shuffle deck
        System.out.println("\nShuffling deck...");
        deck = shuffleDeck(deck);
        System.out.println("Deck shuffled successfully!");
        
        // Distribute cards
        String[][] players = distributeCards(deck, numCards, numPlayers);
        
        if (players != null) {
            System.out.println("Cards distributed successfully!");
            printPlayersCards(players, numCards);
            
            // Summary
            System.out.println("--- Distribution Summary ---");
            System.out.println("Total players: " + numPlayers);
            System.out.println("Cards per player: " + numCards);
            System.out.println("Total cards distributed: " + (numCards * numPlayers));
            System.out.println("Remaining cards in deck: " + (deck.length - (numCards * numPlayers)));
        } else {
            System.out.println("Failed to distribute cards!");
        }
        
        scanner.close();
    }
}
