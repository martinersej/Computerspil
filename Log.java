import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

/**
 * Lav en beskrivelse af klassen Log her.
 * 
 * @author Martin Pugdal Pedersen
 * @version 2021-12-12
 */
public class Log implements Serializable
{
    private int seed;
    private Settings settings;
    private Map<Integer, String> choices;


    /**
     * Konstruktør for objekter af klassen Log
     */
    public Log(int seed, Settings settings) {
        this.seed = seed;
        this.settings = settings;
        this.choices = new HashMap<>();
    }

    public int getSeed() {
        return seed;
    }

    public Settings getSettings() {
        return settings;
    }

    public String getChoice(int step) {
        return choices.get(step);
    }

    void add(int step, City city) {
        choices.put(step, city.getName());
    }

}
