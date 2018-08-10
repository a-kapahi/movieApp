import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
abstract class movie implements Comparable<movie>{
    String name, language, genre, dish;
    Long productionCost;
    LocalDate releaseDate;
    boolean isBlockbuster, blockbusterAssigned;

    public movie(String name){
        Scanner in = new Scanner(System.in);
        this.name = name;
        System.out.print("Genre: ");
        //genre = in.nextLine();
        System.out.print("Date(yyyy-mm-dd): ");
        try {
        //    releaseDate = LocalDate.parse(in.nextLine());
        }
        catch (DateTimeParseException e){
            //Handle Exception
        }

    }
    public boolean isBlockbuster(){
        if(!blockbusterAssigned){
            isBlockbuster = new Random().nextBoolean();
            blockbusterAssigned = true;
        }
        return isBlockbuster;

    }
    public String getFood(){
        return dish;
    }

    public int compareTo(movie m){
        if(m instanceof Bollywood)
            return 1;
        else if(m instanceof Hollywood)
            if(this instanceof Bollywood)
                return -1;
            else return 1;
        else
            return -1;
    }

    public String toString(){
        return this.name;
    }
}

class Bollywood extends movie{
    public Bollywood(String name){
        super(name);
        language = "Hindi";
        dish = "Dal Makhni";
        productionCost = 100000000L;
    }

}

class Tollywood extends movie{
    public Tollywood(String name){
        super(name);
        language = "Tamil";
        dish = "Dosa";
        productionCost = 10000000L;
    }

}

class Hollywood extends movie{
    public Hollywood(String name){
        super(name);
        language = "English";
        dish = "Pepper Steak";
        productionCost = 1000000000L;
    }

}

public class movieHandler{
    Map<String, movie> movies = new HashMap<String, movie>();
    enum movieType{BOLLYWOOD, HOLLYWOOD, TOLLYWOOD;}
    public static void main(String[] args){
        movieHandler m = new movieHandler();
        //user input?
        m.movies.put("DDLJ",new Bollywood("DDLJ"));
        m.movies.put("Men in Black",new Hollywood("Men in Black"));
        m.movies.put("Kahani",new Bollywood("Kahani"));
        m.movies.put("Bahubali", new Tollywood("Bahubali"));
        m.movies.put("Coco",new Hollywood("Coco"));
        m.checkBlockbuster("Coco");
        m.checkBlockbuster("Coco");
        m.checkBlockbuster("Coco");
        m.getAll();
        m.gettype(movieType.BOLLYWOOD);



    }

    public void getAll(){
        for (String keys:movies.keySet()) System.out.println(movies.get(keys));
    }

    public void checkBlockbuster(String name){
        if(movies.containsKey(name))
            if(movies.get(name).isBlockbuster())
                System.out.println(name+" was a blockbuster");
            else
                System.out.println(name+" was not a blockbuster");
        else
            System.out.println(name+" doesn't exist");
    }

    public void gettype(movieType type){
        switch (type) {
            case BOLLYWOOD: {
                for (String keys : movies.keySet()) {
                    if (movies.get(keys) instanceof Bollywood)
                        System.out.println(movies.get(keys));
                }
                break;
            }
            case HOLLYWOOD:{
                for (String keys : movies.keySet()) {
                    if (movies.get(keys) instanceof Hollywood)
                        System.out.println(movies.get(keys));
                }
                break;
            }
            case TOLLYWOOD: {
                for (String keys : movies.keySet()) {
                    if (movies.get(keys) instanceof Tollywood)
                        System.out.println(movies.get(keys));
                }
                break;
            }
        }
    }

}

