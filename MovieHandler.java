import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

interface FoodStore {
    public String getFreeDish(Movie m);
}

class Movie {
    String name, genre, dish;
    LocalDate releaseDate;
    Boolean isBlockbuster;
    Type type;

    public Movie(String name, String genre, String date, Type type) {
        this.name = name;
        this.genre = genre;
        this.type = type;
        try {
            releaseDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            System.out.println("Improper date format");
        }
    }

    public boolean isBlockbuster() {
        if (isBlockbuster == null) {
            isBlockbuster = new Random().nextBoolean();
        }
        return isBlockbuster;

    }

    public String getFood() {
        return dish;
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

public class MovieHandler implements FoodStore {
    Map<String, Movie> movies = new HashMap<>();

    public static void main(String[] args) {
        MovieHandler m = new MovieHandler();
        m.movies.put("DDLJ", new Movie("DDLJ", "Romcom", "1992-08-09", Movie.Type.BOLLYWOOD));
        m.movies.put("Men in Black", new Movie("Men in Black", "Sci-fi", "2000-03-02", Movie.Type.HOLLYWOOD));
        m.movies.put("Kahani", new Movie("Kahani", "Thriller", "2010-03-01", Movie.Type.BOLLYWOOD));
        m.movies.put("Bahubali", new Movie("Bahubali", "Action", "2014-10-09", Movie.Type.TOLLYWOOD));
        m.movies.put("Coco", new Movie("Coco", "Fantasy", "2018-02-22", Movie.Type.HOLLYWOOD));
        m.getAll();
        m.getType("HOLLYWOOD");
    }

    public void getAll() {
        for (String key : movies.keySet()) {
            System.out.println(movies.get(key));
        }
    }
    
    public void getType(String type) {
        for (String key : movies.keySet()) {
            if (movies.get(key).type.toString() == type) {
                System.out.println(movies.get(key));
            }
        }
    }

    @Override
    public String getFreeDish(Movie m) {
        switch (m.type) {
            case BOLLYWOOD:
                return "Dal Makhni";
            case TOLLYWOOD:
                return "Dosa";
            case HOLLYWOOD:
                return "Pepper Steak";
            default:
                return null;
        }
    }
}

