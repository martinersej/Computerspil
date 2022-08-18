import java.util.*;
import java.util.stream.Collectors;

/**
 * Klassen reprænsterer et land i spillet.
 * 
 * @author Martin Pugdal Pedersen
 * @version 2021-11-14
 */
public class Country
{

    private String name;
    private Map<City, Set<Road>> network;
    private Game game;

    /**
     * Denne metode opretter vores land objekt navnet på landet.
     *
     * @param name  Navnet af landet.
     */
    public Country(String name) {
        this.name = name;
        this.network = new TreeMap<>();
    }

    /**
     * Denne metode returner navnet på landet.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Denne metode returner en liste med alle byer i landet.
     *
     * @return byerne.
     */
    public Set<City> getCities() {
        return network.keySet();
    }

    /**
     * Denne metode finder en by ud fra navnet.
     *
     * @param name Navnet på byen vi skal finde.
     * @return byen eller null.
     */
    public City getCity(String name) {
        return network.keySet().stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
    }

    /**
     * Denne metode returner en liste med alle veje fra byen.
     *
     * @param c En by objekt.
     * @return Vejene eller en ny TreeSet.
     */
    public Set<Road> getRoads(City c) {
        return network.getOrDefault(c, new TreeSet<>());
    }

    /**
     * Denne metode nustiller alle byer, så de har deres startværdi igen.
     */
    public void reset() {
        for (City c : network.keySet()) {
            c.reset();
        }
    }

    /**
     * Denne metode returner bonusen som spilleren får, når de ankommer til en by,
     * hvor bonusen er baseret ud fra byens nuværende værdi.
     *
     * @param value Byens værdi.
     * @return Bonusen som spilleren får.
     */
    public int bonus(int value) {
        if (value > 0) {
            return game.getRandom().nextInt(value+1);
        }
        return 0;
    }

    /**
     * Denne metode tilføjer en by til netværket.
     *
     * @param c En by objekt.
     */
    public void addCity(City c) {
        network.put(c, new TreeSet<>());
    }

    /**
     * Denne metode tilføjer veje mellem byen a og byen b.
     *
     * @param a En by objekt.
     * @param b En by objekt.
     * @param length Længde mellem byen a og b.
     */
    public void addRoads(City a, City b, int length) {
        if (!a.equals(b) && length > 0) {
            if (network.get(a) != null) {
                network.get(a).add(new Road(a,b,length));
            }
            if (network.get(b) != null) {
                network.get(b).add(new Road(b,a,length));
            }
        }
    }

    /**
     * Returner en Position objekt, der er oprettet i metoden.
     * 
     * @return Ny Position objekt.
     */
    public Position position(City city) {
        return new Position(city, city, 0);
    }

    /**
     * Metoden returner positionen som man befinder sig på.
     * 
     * @return Position  Ens position som man er på.
     */
    public Position readyToTravel(City from, City to) {
        if (from.equals(to) || !from.getCountry().equals(this)) {
            return position(from);
        }
        Road road = getRoads(from).stream()
        .filter(r -> r.getTo().equals(to))
        .findFirst().orElse(null);
        if (road == null) {
            return position(from);
        }
        return new Position(from, to, road.getLength());
    }
    
    /**
     * Denne metode returner game objektet.
     *
     * @param game En game objekt.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Denne metode sætter spillet som landet tilhører til.
     *
     * @param game En game objekt.
     */
    public void setGame(Game game) {
        this.game = game;
    }
    
    /**
     * Returner et string, der giver en tekststreng.
     * 
     * @return String  Landets navn.
     */
    @Override
    public String toString() {
        return this.name;
    }
    
    /**
     * Returner en equals boolean for vores land.
     *
     * @param o Objektet som vi vil kontrollere.
     * @return boolean  Om den er ens eller ej.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != getClass()) {
            return false;
        }
        Country other = (Country) o;
        return this.name.equals(other.name);
    }

    /**
     * Returner en hashcode værdi for vores land
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return 11 * name.hashCode();
    }
}
