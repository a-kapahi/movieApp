import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

abstract class Movie {
    String name, language, genre, dish;
    Long productionCost;
    LocalDate releaseDate;
    boolean isBlockbuster, blockbusterAssigned;

    public Movie(String name, String genre, String date) {
        this.name = name;
        this.genre = genre;
        try {
            releaseDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            System.out.println("Improper date format");
        }

    }

    public boolean isBlockbuster() {
        if (!blockbusterAssigned) {
            isBlockbuster = new Random().nextBoolean();
            blockbusterAssigned = true;
        }
        return isBlockbuster;

    }

    public String getFood() {
        return dish;
    }


    public String toString() {
        return this.name;
    }
}

class Bollywood extends Movie {
    public Bollywood(String name, String genre, String date) {
        super(name, genre, date);
        language = "Hindi";
        dish = "Dal Makhni";
        productionCost = 100000000L;
    }

}

class Tollywood extends Movie {
    public Tollywood(String name, String genre, String date) {
        super(name, genre, date);
        language = "Tamil";
        dish = "Dosa";
        productionCost = 10000000L;
    }

}

class Hollywood extends Movie {
    public Hollywood(String name, String genre, String date) {
        super(name, genre, date);
        language = "English";
        dish = "Pepper Steak";
        productionCost = 1000000000L;
    }

}

public class MovieHandler {
    Map<String, Movie> movies = new HashMap<>();

    public static void main(String[] args) {
        MovieHandler m = new MovieHandler();
        m.movies.put("DDLJ", new Bollywood("DDLJ", "Romcom", "1992-08-09"));
        m.movies.put("Men in Black", new Hollywood("Men in Black", "Sci-fi", "2000-03-02"));
        m.movies.put("Kahani", new Bollywood("Kahani", "Thriller", "2010-03-01"));
        m.movies.put("Bahubali", new Tollywood("Bahubali", "Action", "2014-10-09"));
        m.movies.put("Coco", new Hollywood("Coco", "Fantasy", "2018-02-22"));
        m.checkBlockbuster("Coco");
        m.getAll();
        m.getType("HOLLYWOOD");
    }

    public void getAll() {
        for (String keys : movies.keySet())
            System.out.println(movies.get(keys));
    }

    public void checkBlockbuster(String name) {
        if (movies.containsKey(name))
            if (movies.get(name).isBlockbuster())
                System.out.println(name + " was a blockbuster");
            else
                System.out.println(name + " was not a blockbuster");
        else
            System.out.println(name + " doesn't exist");
    }

    public void getType(String type) {
        for (String keys : movies.keySet()) {
            if (movies.get(keys).getClass().getSimpleName().equalsIgnoreCase(type)) {
                System.out.println(movies.get(keys));
            }
        }
    }

}

