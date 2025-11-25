import java.util.*;

class Movie {
    private String movieId;
    private String title;
    private String genre;
    private int durationMinutes;

    public Movie(String movieId, String title, String genre, int durationMinutes) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
    }

    public String getMovieId() { return movieId; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getDurationMinutes() { return durationMinutes; }

    @Override
    public String toString() {
        return String.format("Movie[%s, %s, %s, %dmin]", movieId, title, genre, durationMinutes);
    }
}

public class OnlineStreamingWatchHistory {
    private Stack<Movie> watchHistory;
    private List<Movie> allMovies;
    private Set<String> watchedGenres;
    private Queue<Movie> upNextQueue;

    public OnlineStreamingWatchHistory() {
        watchHistory = new Stack<>();
        allMovies = new ArrayList<>();
        watchedGenres = new HashSet<>();
        upNextQueue = new LinkedList<>();
    }

    public void addMovieToLibrary(Movie movie) {
        allMovies.add(movie);
        System.out.println("Added to library: " + movie.getTitle());
    }

    public void addToUpNext(String movieId) {
        for (Movie movie : allMovies) {
            if (movie.getMovieId().equals(movieId)) {
                upNextQueue.add(movie);
                System.out.println("Added to Up Next: " + movie.getTitle());
                return;
            }
        }
        System.out.println("Movie not found: " + movieId);
    }

    public void watchNextMovie() {
        if (upNextQueue.isEmpty()) {
            System.out.println("No movies in Up Next queue.");
            return;
        }
        Movie movie = upNextQueue.poll();
        watchHistory.push(movie);
        watchedGenres.add(movie.getGenre());
        System.out.println("Watching: " + movie.getTitle() + " (" + movie.getGenre() + ")");
    }

    public Movie getLastWatched() {
        if (watchHistory.isEmpty()) {
            System.out.println("No watch history.");
            return null;
        }
        return watchHistory.peek();
    }

    public void rewatchLastMovie() {
        if (watchHistory.isEmpty()) {
            System.out.println("No watch history to rewatch.");
            return;
        }
        Movie movie = watchHistory.peek();
        System.out.println("Rewatching: " + movie.getTitle());
    }

    public void displayRecommendations() {
        System.out.println("\n=== Recommendations Based on Watch History ===");
        if (watchedGenres.isEmpty()) {
            System.out.println("  Watch some movies first to get recommendations!");
            return;
        }

        System.out.println("Genres you've enjoyed: " + watchedGenres);
        System.out.println("\nRecommended movies:");

        for (Movie movie : allMovies) {
            if (watchedGenres.contains(movie.getGenre())) {
                boolean alreadyWatched = false;
                for (Movie watched : watchHistory) {
                    if (watched.getMovieId().equals(movie.getMovieId())) {
                        alreadyWatched = true;
                        break;
                    }
                }
                if (!alreadyWatched) {
                    System.out.println("  " + movie);
                }
            }
        }
    }

    public void displayLibrary() {
        System.out.println("\n=== Movie Library (" + allMovies.size() + ") ===");
        for (Movie movie : allMovies) {
            System.out.println("  " + movie);
        }
    }

    public void displayUpNext() {
        System.out.println("\n=== Up Next Queue ===");
        if (upNextQueue.isEmpty()) {
            System.out.println("  Queue is empty");
            return;
        }
        for (Movie movie : upNextQueue) {
            System.out.println("  " + movie.getTitle());
        }
    }

    public void displayWatchHistory() {
        System.out.println("\n=== Watch History (Most Recent First) ===");
        if (watchHistory.isEmpty()) {
            System.out.println("  No watch history");
            return;
        }
        Stack<Movie> temp = new Stack<>();
        temp.addAll(watchHistory);
        while (!temp.isEmpty()) {
            Movie movie = temp.pop();
            System.out.println("  " + movie.getTitle() + " (" + movie.getGenre() + ")");
        }
    }

    public void displayWatchedGenres() {
        System.out.println("\n=== Genres Watched ===");
        System.out.println("  " + watchedGenres);
    }

    public static void main(String[] args) {
        OnlineStreamingWatchHistory streaming = new OnlineStreamingWatchHistory();

        System.out.println("=== Building Movie Library ===");
        streaming.addMovieToLibrary(new Movie("MOV001", "The Matrix", "Sci-Fi", 136));
        streaming.addMovieToLibrary(new Movie("MOV002", "Inception", "Sci-Fi", 148));
        streaming.addMovieToLibrary(new Movie("MOV003", "The Godfather", "Crime", 175));
        streaming.addMovieToLibrary(new Movie("MOV004", "Pulp Fiction", "Crime", 154));
        streaming.addMovieToLibrary(new Movie("MOV005", "The Dark Knight", "Action", 152));
        streaming.addMovieToLibrary(new Movie("MOV006", "Interstellar", "Sci-Fi", 169));
        streaming.addMovieToLibrary(new Movie("MOV007", "Avengers", "Action", 143));

        streaming.displayLibrary();

        System.out.println("\n=== Adding Movies to Up Next ===");
        streaming.addToUpNext("MOV001");
        streaming.addToUpNext("MOV003");
        streaming.addToUpNext("MOV005");

        streaming.displayUpNext();

        System.out.println("\n=== Watching Movies ===");
        streaming.watchNextMovie();
        streaming.watchNextMovie();

        streaming.displayWatchHistory();
        streaming.displayWatchedGenres();

        System.out.println("\n=== Last Watched: " + streaming.getLastWatched().getTitle());
        streaming.rewatchLastMovie();

        streaming.displayRecommendations();
    }
}
