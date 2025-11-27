import java.util.*;

public class VotingCount {
    private Map<String, Integer> voteCount;
    private int totalVotes;
    
    public VotingCount() {
        voteCount = new HashMap<>();
        totalVotes = 0;
    }
    
    public void castVote(String candidateName) {
        voteCount.put(candidateName, voteCount.getOrDefault(candidateName, 0) + 1);
        totalVotes++;
        System.out.println("Vote cast for: " + candidateName);
    }
    
    public void displayResults() {
        System.out.println("\n=== Election Results ===");
        System.out.println("Total Votes Cast: " + totalVotes);
        System.out.println("\nCandidate-wise Results:");
        
        for (Map.Entry<String, Integer> entry : voteCount.entrySet()) {
            double percentage = (entry.getValue() * 100.0) / totalVotes;
            System.out.printf("%s: %d votes (%.2f%%)%n", entry.getKey(), entry.getValue(), percentage);
        }
    }
    
    public void displayResultsSorted() {
        System.out.println("\n=== Results Sorted by Votes ===");
        
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(voteCount.entrySet());
        sortedList.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        int rank = 1;
        for (Map.Entry<String, Integer> entry : sortedList) {
            double percentage = (entry.getValue() * 100.0) / totalVotes;
            System.out.printf("%d. %s: %d votes (%.2f%%)%n", rank++, entry.getKey(), entry.getValue(), percentage);
        }
    }
    
    public String getWinner() {
        String winner = null;
        int maxVotes = 0;
        
        for (Map.Entry<String, Integer> entry : voteCount.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                winner = entry.getKey();
            }
        }
        
        return winner;
    }
    
    public List<String> getCandidatesWithVotes(int votes) {
        List<String> candidates = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : voteCount.entrySet()) {
            if (entry.getValue() == votes) {
                candidates.add(entry.getKey());
            }
        }
        return candidates;
    }
    
    public void declareWinner() {
        String winner = getWinner();
        int winnerVotes = voteCount.get(winner);
        
        List<String> tiedCandidates = getCandidatesWithVotes(winnerVotes);
        
        System.out.println("\n=============================");
        if (tiedCandidates.size() > 1) {
            System.out.println("TIE! The following candidates have " + winnerVotes + " votes each:");
            for (String candidate : tiedCandidates) {
                System.out.println("- " + candidate);
            }
        } else {
            System.out.println("WINNER: " + winner + " with " + winnerVotes + " votes!");
            double percentage = (winnerVotes * 100.0) / totalVotes;
            System.out.printf("Vote Share: %.2f%%%n", percentage);
        }
        System.out.println("=============================");
    }
    
    public static void main(String[] args) {
        VotingCount election = new VotingCount();
        
        System.out.println("=== Class Election: Casting Votes ===\n");
        
        String[] votes = {
            "Alice", "Bob", "Charlie",
            "Alice", "Alice", "Bob",
            "Charlie", "Alice", "Bob",
            "Charlie", "Alice", "Bob",
            "Charlie", "Diana", "Alice",
            "Bob", "Charlie", "Diana",
            "Alice", "Bob"
        };
        
        for (String vote : votes) {
            election.castVote(vote);
        }
        
        election.displayResults();
        election.displayResultsSorted();
        election.declareWinner();
        
        System.out.println("\n=== Another Election Scenario ===\n");
        
        VotingCount election2 = new VotingCount();
        String[] votes2 = {"A", "B", "C", "A", "B", "C", "A", "B", "C"};
        
        for (String vote : votes2) {
            election2.castVote(vote);
        }
        
        election2.displayResults();
        election2.declareWinner();
    }
}
