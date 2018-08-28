import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

interface FoodStore {
    String getFreeDish(String name);
}

class Movie {

    private String name;
    private String genre;
    private LocalDate releaseDate;
    private Type type;
    private Boolean isBlockbuster;

    Movie(String name, String genre, String date, Type type) {
        this.name = name;
        this.genre = genre;
        this.type = type;
        try {
            releaseDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            System.out.println("Improper date format");
        }
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Type getType() {
        return type;
    }

    /**
     * On first call, generates and assigns a random Boolean value to indicate if the movie
     * is a blockbuster or not. Returns the assigned Boolean value.
     *
     * @return Whether a movie a blockbuster or not
     */
    boolean isBlockbuster() {
        if (isBlockbuster == null) isBlockbuster = new Random().nextBoolean();
        return isBlockbuster;

    }

    public String toString() {
        return this.name;
    }

    enum Type {
        BOLLYWOOD("Hindi", 100000), HOLLYWOOD("English", 1000000), TOLLYWOOD("Tamil", 10000);
        String language;
        long productionCost;

        Type(String language, long productionCost) {
            this.language = language;
            this.productionCost = productionCost;
        }
    }

}

public class MovieTheatre implements FoodStore {
    private List<Movie> movies = new ArrayList<>();

    public static void main(String[] args) {
        MovieTheatre m = new MovieTheatre();
        m.movies.add(new Movie("DDLJ", "Romcom", "1992-08-09", Movie.Type.BOLLYWOOD));
        m.movies.add(new Movie("Men in Black", "Sci-fi", "2000-03-02", Movie.Type.HOLLYWOOD));
        m.movies.add(new Movie("Kahani", "Thriller", "2010-03-01", Movie.Type.BOLLYWOOD));
        m.movies.add(new Movie("Bahubali", "Action", "2014-10-09", Movie.Type.TOLLYWOOD));
        m.movies.add(new Movie("Coco", "Fantasy", "2018-02-22", Movie.Type.HOLLYWOOD));
        m.getAll();
        m.getType("HOLLYWOOD");
        m.getType("TOLLYWOOD");
        System.out.println(m.getFreeDish("ddlj"));
        System.out.println(m.findMovieByName("coco").isBlockbuster());
    }

    /**
     * Prints out a list of all the movies in the database.
     */
    private void getAll() {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    /**
     * Prints out a list of all the movies of a specific type in the database.
     *
     * @param type The type of movies to be retrieved
     */
    private void getType(String type) {
        for (Movie movie : movies) {
            if (movie.getType().toString().equalsIgnoreCase(type)) {
                System.out.println(movie);
            }
        }
    }


    /**
     * Searches the database for a movie with a specific name and checks the movie type.
     * Returns a dish based on the type of the movie. If movie doesn't exist returns Invalid
     * movie message.
     *
     * @param name Name of the movie to be searched
     * @return A free dish based on type of Movie.
     */
    @Override
    public String getFreeDish(String name) {
        Movie movie = findMovieByName(name);
        try {
            switch (movie.getType()) {
                case BOLLYWOOD:
                    return "Dal Makhni";
                case TOLLYWOOD:
                    return "Dosa";
                case HOLLYWOOD:
                    return "Pepper Steak";
                default:
                    return "Invalid Movie! No food for you!";
            }
        } catch (NullPointerException e) {
            return "Invalid Movie! No food for you!";
        }
    }

    /**
     * Searches the database for a movie with a specific name. If found returns a movie object
     * with the reference to that movie. If no movie with that name exists return null
     *
     * @param name Name of the movie to be searched
     * @return Reference of the Movie object with matching name
     */
    private Movie findMovieByName(String name) {
        for (Movie movie : movies) {
            if (name.equalsIgnoreCase(movie.getName())) {
                return movie;
            }
        }
        return null;
    }

}

