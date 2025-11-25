import java.util.*;

class Team implements Comparable<Team> {
    private String teamId;
    private String teamName;
    private int points;
    private int matchesPlayed;
    private int wins;
    private int losses;

    public Team(String teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.points = 0;
        this.matchesPlayed = 0;
        this.wins = 0;
        this.losses = 0;
    }

    public String getTeamId() { return teamId; }
    public String getTeamName() { return teamName; }
    public int getPoints() { return points; }
    public int getMatchesPlayed() { return matchesPlayed; }
    public int getWins() { return wins; }
    public int getLosses() { return losses; }

    public void addWin() {
        matchesPlayed++;
        wins++;
        points += 3;
    }

    public void addLoss() {
        matchesPlayed++;
        losses++;
    }

    public void addDraw() {
        matchesPlayed++;
        points += 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return teamId.equals(team.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId);
    }

    @Override
    public int compareTo(Team other) {
        int pointCompare = Integer.compare(other.points, this.points);
        if (pointCompare != 0) return pointCompare;
        int winCompare = Integer.compare(other.wins, this.wins);
        if (winCompare != 0) return winCompare;
        return this.teamId.compareTo(other.teamId);
    }

    @Override
    public String toString() {
        return String.format("Team[%s, P:%d, W:%d, L:%d, Pts:%d]", 
            teamName, matchesPlayed, wins, losses, points);
    }
}

class SportMatch {
    private String matchId;
    private Team team1;
    private Team team2;

    public SportMatch(String matchId, Team team1, Team team2) {
        this.matchId = matchId;
        this.team1 = team1;
        this.team2 = team2;
    }

    public String getMatchId() { return matchId; }
    public Team getTeam1() { return team1; }
    public Team getTeam2() { return team2; }

    @Override
    public String toString() {
        return String.format("Match[%s: %s vs %s]", matchId, team1.getTeamName(), team2.getTeamName());
    }
}

class MatchResult {
    private SportMatch match;
    private Team winner;
    private int team1Score;
    private int team2Score;
    private boolean isDraw;

    public MatchResult(SportMatch match, Team winner, int team1Score, int team2Score) {
        this.match = match;
        this.winner = winner;
        this.team1Score = team1Score;
        this.team2Score = team2Score;
        this.isDraw = (team1Score == team2Score);
    }

    @Override
    public String toString() {
        String result = isDraw ? "Draw" : "Winner: " + winner.getTeamName();
        return String.format("Result[%s - %d:%d, %s]", 
            match.getMatchId(), team1Score, team2Score, result);
    }
}

public class SportsTournamentScheduler {
    private Set<Team> registeredTeams;
    private Queue<SportMatch> matchQueue;
    private List<MatchResult> results;
    private TreeSet<Team> rankings;
    private Map<String, Team> teamMap;

    public SportsTournamentScheduler() {
        registeredTeams = new HashSet<>();
        matchQueue = new LinkedList<>();
        results = new ArrayList<>();
        rankings = new TreeSet<>();
        teamMap = new HashMap<>();
    }

    public boolean registerTeam(Team team) {
        if (registeredTeams.add(team)) {
            teamMap.put(team.getTeamId(), team);
            rankings.add(team);
            System.out.println("Team registered: " + team.getTeamName());
            return true;
        }
        System.out.println("Team already registered: " + team.getTeamId());
        return false;
    }

    public void scheduleMatch(String matchId, String team1Id, String team2Id) {
        Team t1 = teamMap.get(team1Id);
        Team t2 = teamMap.get(team2Id);

        if (t1 == null || t2 == null) {
            System.out.println("Invalid team IDs!");
            return;
        }

        SportMatch match = new SportMatch(matchId, t1, t2);
        matchQueue.add(match);
        System.out.println("Match scheduled: " + match);
    }

    public void processNextMatch() {
        if (matchQueue.isEmpty()) {
            System.out.println("No matches to process!");
            return;
        }

        SportMatch match = matchQueue.poll();
        System.out.println("\n=== Playing: " + match + " ===");

        Random random = new Random();
        int score1 = random.nextInt(5);
        int score2 = random.nextInt(5);

        Team winner = null;
        rankings.remove(match.getTeam1());
        rankings.remove(match.getTeam2());

        if (score1 > score2) {
            winner = match.getTeam1();
            match.getTeam1().addWin();
            match.getTeam2().addLoss();
        } else if (score2 > score1) {
            winner = match.getTeam2();
            match.getTeam2().addWin();
            match.getTeam1().addLoss();
        } else {
            match.getTeam1().addDraw();
            match.getTeam2().addDraw();
        }

        rankings.add(match.getTeam1());
        rankings.add(match.getTeam2());

        MatchResult result = new MatchResult(match, winner, score1, score2);
        results.add(result);
        System.out.println("Result: " + result);
    }

    public void displayTeams() {
        System.out.println("\n=== Registered Teams (" + registeredTeams.size() + ") ===");
        for (Team team : registeredTeams) {
            System.out.println("  " + team);
        }
    }

    public void displayUpcomingMatches() {
        System.out.println("\n=== Upcoming Matches ===");
        if (matchQueue.isEmpty()) {
            System.out.println("  No matches scheduled");
            return;
        }
        for (SportMatch match : matchQueue) {
            System.out.println("  " + match);
        }
    }

    public void displayResults() {
        System.out.println("\n=== Match Results ===");
        for (MatchResult result : results) {
            System.out.println("  " + result);
        }
    }

    public void displayLeaderboard() {
        System.out.println("\n=== LEADERBOARD ===");
        System.out.println("Rank | Team          | Played | Won | Lost | Points");
        System.out.println("--------------------------------------------------");
        int rank = 1;
        for (Team team : rankings) {
            System.out.printf("%4d | %-13s | %6d | %3d | %4d | %6d%n",
                rank, team.getTeamName(), team.getMatchesPlayed(), 
                team.getWins(), team.getLosses(), team.getPoints());
            rank++;
        }
    }

    public static void main(String[] args) {
        SportsTournamentScheduler tournament = new SportsTournamentScheduler();

        System.out.println("=== Registering Teams ===");
        tournament.registerTeam(new Team("T001", "Red Lions"));
        tournament.registerTeam(new Team("T002", "Blue Hawks"));
        tournament.registerTeam(new Team("T003", "Green Tigers"));
        tournament.registerTeam(new Team("T004", "Yellow Eagles"));
        tournament.registerTeam(new Team("T001", "Duplicate Team"));

        tournament.displayTeams();

        System.out.println("\n=== Scheduling Matches ===");
        tournament.scheduleMatch("M001", "T001", "T002");
        tournament.scheduleMatch("M002", "T003", "T004");
        tournament.scheduleMatch("M003", "T001", "T003");
        tournament.scheduleMatch("M004", "T002", "T004");
        tournament.scheduleMatch("M005", "T001", "T004");
        tournament.scheduleMatch("M006", "T002", "T003");

        tournament.displayUpcomingMatches();

        System.out.println("\n=== Processing All Matches ===");
        while (!tournament.matchQueue.isEmpty()) {
            tournament.processNextMatch();
        }

        tournament.displayResults();
        tournament.displayLeaderboard();
    }
}
