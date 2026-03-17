public class MovieManagement {
    private static class Movie {
        private final String title;
        private final String director;
        private final int releaseYear;
        private double rating;

        private Movie(String title, String director, int releaseYear, double rating) {
            this.title = title;
            this.director = director;
            this.releaseYear = releaseYear;
            this.rating = rating;
        }

        @Override
        public String toString() {
            return title + " | " + director + " | " + releaseYear + " | rating=" + rating;
        }
    }

    private static class MovieNode {
        private Movie movie;
        private MovieNode prev;
        private MovieNode next;

        private MovieNode(Movie movie) {
            this.movie = movie;
        }
    }

    private static class MovieList {
        private MovieNode head;
        private MovieNode tail;
        private int size;

        private void addAtBeginning(String title, String director, int releaseYear, double rating) {
            MovieNode node = new MovieNode(new Movie(title, director, releaseYear, rating));

            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.next = head;
                head.prev = node;
                head = node;
            }

            size++;
        }

        private void addAtEnd(String title, String director, int releaseYear, double rating) {
            MovieNode node = new MovieNode(new Movie(title, director, releaseYear, rating));

            if (tail == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }

            size++;
        }

        private boolean addAtPosition(String title, String director, int releaseYear, double rating, int position) {
            if (position < 1 || position > size + 1) {
                return false;
            }

            if (position == 1) {
                addAtBeginning(title, director, releaseYear, rating);
                return true;
            }

            if (position == size + 1) {
                addAtEnd(title, director, releaseYear, rating);
                return true;
            }

            MovieNode current = head;

            for (int index = 1; index < position - 1; index++) {
                current = current.next;
            }

            MovieNode node = new MovieNode(new Movie(title, director, releaseYear, rating));
            node.next = current.next;
            node.prev = current;
            current.next.prev = node;
            current.next = node;
            size++;
            return true;
        }

        private boolean removeByTitle(String title) {
            MovieNode node = findNodeByTitle(title);

            if (node == null) {
                return false;
            }

            if (node == head) {
                head = node.next;
            }

            if (node == tail) {
                tail = node.prev;
            }

            if (node.prev != null) {
                node.prev.next = node.next;
            }

            if (node.next != null) {
                node.next.prev = node.prev;
            }

            size--;
            return true;
        }

        private boolean updateRating(String title, double newRating) {
            MovieNode node = findNodeByTitle(title);

            if (node == null) {
                return false;
            }

            node.movie.rating = newRating;
            return true;
        }

        private String searchByDirector(String director) {
            StringBuilder builder = new StringBuilder();
            MovieNode current = head;

            while (current != null) {
                if (current.movie.director.equalsIgnoreCase(director)) {
                    if (builder.length() > 0) {
                        builder.append(System.lineSeparator());
                    }

                    builder.append(current.movie);
                }

                current = current.next;
            }

            return builder.length() == 0 ? "No movies found" : builder.toString();
        }

        private String searchByRating(double rating) {
            StringBuilder builder = new StringBuilder();
            MovieNode current = head;

            while (current != null) {
                if (Double.compare(current.movie.rating, rating) == 0) {
                    if (builder.length() > 0) {
                        builder.append(System.lineSeparator());
                    }

                    builder.append(current.movie);
                }

                current = current.next;
            }

            return builder.length() == 0 ? "No movies found" : builder.toString();
        }

        private String displayForward() {
            StringBuilder builder = new StringBuilder();
            MovieNode current = head;

            while (current != null) {
                if (builder.length() > 0) {
                    builder.append(System.lineSeparator());
                }

                builder.append(current.movie);
                current = current.next;
            }

            return builder.toString();
        }

        private String displayReverse() {
            StringBuilder builder = new StringBuilder();
            MovieNode current = tail;

            while (current != null) {
                if (builder.length() > 0) {
                    builder.append(System.lineSeparator());
                }

                builder.append(current.movie);
                current = current.prev;
            }

            return builder.toString();
        }

        private MovieNode findNodeByTitle(String title) {
            MovieNode current = head;

            while (current != null) {
                if (current.movie.title.equalsIgnoreCase(title)) {
                    return current;
                }

                current = current.next;
            }

            return null;
        }
    }

    public static void main(String[] args) {
        MovieList movies = new MovieList();
        movies.addAtBeginning("Inception", "Christopher Nolan", 2010, 9.0);
        movies.addAtEnd("Interstellar", "Christopher Nolan", 2014, 9.2);
        movies.addAtEnd("Avatar", "James Cameron", 2009, 8.5);
        movies.addAtPosition("The Prestige", "Christopher Nolan", 2006, 8.8, 2);

        System.out.println("Forward");
        System.out.println(movies.displayForward());
        System.out.println();

        System.out.println("Reverse");
        System.out.println(movies.displayReverse());
        System.out.println();

        System.out.println("Search Director");
        System.out.println(movies.searchByDirector("Christopher Nolan"));
        System.out.println();

        System.out.println("Search Rating");
        System.out.println(movies.searchByRating(9.2));
        System.out.println();

        movies.updateRating("Avatar", 8.9);
        movies.removeByTitle("Inception");

        System.out.println("Updated Forward");
        System.out.println(movies.displayForward());
    }
}
