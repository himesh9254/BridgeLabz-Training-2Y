import java.util.*;

class Player {
    private String playerId;
    private String name;
    private int points;

    public Player(String playerId, String name) {
        this.playerId = playerId;
        this.name = name;
        this.points = 0;
    }

    public String getPlayerId() { return playerId; }
    public String getName() { return name; }
    public int getPoints() { return points; }
    public void addPoints(int p) { this.points += p; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerId.equals(player.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId);
    }

    @Override
    public String toString() {
        return String.format("Player[%s, %s, Points:%d]", playerId, name, points);
    }
}

class Match {
    private String matchId;
    private Player player1;
    private Player player2;

    public Match(String matchId, Player player1, Player player2) {
        this.matchId = matchId;
        this.player1 = player1;
        this.player2 = player2;
    }

    public String getMatchId() { return matchId; }
    public Player getPlayer1() { return player1; }
    public Player getPlayer2() { return player2; }

    @Override
    public String toString() {
        return String.format("Match[%s: %s vs %s]", matchId, player1.getName(), player2.getName());
    }
}

class Result {
    private Match match;
    private Player winner;
    private int winnerScore;
    private int loserScore;

    public Result(Match match, Player winner, int winnerScore, int loserScore) {
        this.match = match;
        this.winner = winner;
        this.winnerScore = winnerScore;
        this.loserScore = loserScore;
    }

    public Match getMatch() { return match; }
    public Player getWinner() { return winner; }

    @Override
    public String toString() {
        return String.format("Result[%s - Winner: %s (%d-%d)]", 
            match.getMatchId(), winner.getName(), winnerScore, loserScore);
    }
}

class Score implements Comparable<Score> {
    private Player player;
    private int totalPoints;

    public Score(Player player) {
        this.player = player;
        this.totalPoints = player.getPoints();
    }

    public Player getPlayer() { return player; }
    public int getTotalPoints() { return totalPoints; }
    public void updatePoints() { this.totalPoints = player.getPoints(); }

    @Override
    public int compareTo(Score other) {
        int pointCompare = Integer.compare(other.totalPoints, this.totalPoints);
        if (pointCompare != 0) return pointCompare;
        return this.player.getPlayerId().compareTo(other.player.getPlayerId());
    }

    @Override
    public String toString() {
        return String.format("%s: %d points", player.getName(), totalPoints);
    }
}

public class OnlineGamingTournament {
    private Set<Player> registeredPlayers;
    private Queue<Match> upcomingMatches;
    private List<Result> matchResults;
    private TreeSet<Score> leaderboard;
    private Map<String, Player> playerMap;

    public OnlineGamingTournament() {
        registeredPlayers = new HashSet<>();
        upcomingMatches = new LinkedList<>();
        matchResults = new ArrayList<>();
        leaderboard = new TreeSet<>();
        playerMap = new HashMap<>();
    }

    public boolean registerPlayer(Player player) {
        if (registeredPlayers.add(player)) {
            playerMap.put(player.getPlayerId(), player);
            leaderboard.add(new Score(player));
            System.out.println("Registered: " + player);
            return true;
        }
        System.out.println("Player already registered: " + player.getPlayerId());
        return false;
    }

    public void scheduleMatch(String matchId, String player1Id, String player2Id) {
        Player p1 = playerMap.get(player1Id);
        Player p2 = playerMap.get(player2Id);

        if (p1 == null || p2 == null) {
            System.out.println("Invalid player IDs for match!");
            return;
        }

        Match match = new Match(matchId, p1, p2);
        upcomingMatches.add(match);
        System.out.println("Match scheduled: " + match);
    }

    public void processNextMatch() {
        if (upcomingMatches.isEmpty()) {
            System.out.println("No matches to process!");
            return;
        }

        Match match = upcomingMatches.poll();
        System.out.println("\n=== Processing: " + match + " ===");

        Random random = new Random();
        int score1 = random.nextInt(10) + 1;
        int score2 = random.nextInt(10) + 1;

        Player winner;
        int winnerScore, loserScore;

        if (score1 >= score2) {
            winner = match.getPlayer1();
            winnerScore = score1;
            loserScore = score2;
        } else {
            winner = match.getPlayer2();
            winnerScore = score2;
            loserScore = score1;
        }

        winner.addPoints(3);
        if (score1 != score2) {
            Player loser = (winner == match.getPlayer1()) ? match.getPlayer2() : match.getPlayer1();
        }

        Result result = new Result(match, winner, winnerScore, loserScore);
        matchResults.add(result);
        updateLeaderboard();

        System.out.println("Result: " + result);
    }

    private void updateLeaderboard() {
        leaderboard.clear();
        for (Player player : registeredPlayers) {
            leaderboard.add(new Score(player));
        }
    }

    public void displayLeaderboard() {
        System.out.println("\n=== LEADERBOARD (Sorted by Points) ===");
        int rank = 1;
        for (Score score : leaderboard) {
            System.out.println(rank + ". " + score);
            rank++;
        }
    }

    public void displayUpcomingMatches() {
        System.out.println("\n=== Upcoming Matches ===");
        for (Match match : upcomingMatches) {
            System.out.println("  " + match);
        }
    }

    public void displayMatchResults() {
        System.out.println("\n=== Match Results ===");
        for (Result result : matchResults) {
            System.out.println("  " + result);
        }
    }

    public void displayRegisteredPlayers() {
        System.out.println("\n=== Registered Players ===");
        for (Player player : registeredPlayers) {
            System.out.println("  " + player);
        }
    }

    public static void main(String[] args) {
        OnlineGamingTournament tournament = new OnlineGamingTournament();

        System.out.println("=== Player Registration ===");
        tournament.registerPlayer(new Player("P001", "Alex"));
        tournament.registerPlayer(new Player("P002", "Bob"));
        tournament.registerPlayer(new Player("P003", "Charlie"));
        tournament.registerPlayer(new Player("P004", "Diana"));
        tournament.registerPlayer(new Player("P001", "Alex Duplicate"));

        tournament.displayRegisteredPlayers();

        System.out.println("\n=== Scheduling Matches ===");
        tournament.scheduleMatch("M001", "P001", "P002");
        tournament.scheduleMatch("M002", "P003", "P004");
        tournament.scheduleMatch("M003", "P001", "P003");
        tournament.scheduleMatch("M004", "P002", "P004");

        tournament.displayUpcomingMatches();

        System.out.println("\n=== Processing Matches ===");
        tournament.processNextMatch();
        tournament.processNextMatch();
        tournament.processNextMatch();
        tournament.processNextMatch();

        tournament.displayMatchResults();
        tournament.displayLeaderboard();
    }
}
