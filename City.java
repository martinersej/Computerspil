/**
 * Klassen reprænsterer en by i spillet.
 * 
 * @author Martin Pugdal Pedersen
 * @version 2021-11-14
 */
public class City implements Comparable<City> {

    public String name;
    private int value;
    private int initialValue;
    private Country country;

    /**
     * Denne metode opretter vores by objekt med fra en by og til en by og længde mellem de to byer.
     *
     * @param name  Navnet på byen.
     * @param value  Byens værdi.
     * @param country  Vores land.
     */
    public City(String name, int value, Country country) {
        this.name = name;
        this.value = value;
        this.initialValue = value;
        this.country = country;
    }
    
    /**
     * Denne metode returner navnet på vores by.
     * 
     * @return name  Byens navn.
     */
    public String getName() {
        return name;
    }

    /**
     * Denne metode returner byens værdi.
     * 
     * @return value  Byens værdi.
     */
    public int getValue() {
        return value;
    }

    /**
     * Denne metode returner byens startværdi.
     * 
     * @return initialValue  Byens startværdi.
     */
    public int getInitialValue() {
        return initialValue;
    }

    /**
     * Denne metode returner byens land, altså hvor byen ligger i.
     * 
     * @return country  Landet som byen ligger i.
     */
    public Country getCountry() {
        return country;
    }
    
    /**
     * Denne metode ændrer byens værdi.
    */
    public void changeValue(int amount) {
        this.value += amount;
    }

    /**
     * Denne metode nustiller vores værdi til startværdien.
     */
    public void reset() {
        this.value = this.initialValue;
    }

    /**
     * Denne metode returner vores ankomst bonus.
     * 
     * @return arriveBonus  Ankomst bonusen.
     */
    public int arrive() {
        int arriveBonus = country.bonus(value);
        if (arriveBonus > 0) {
            value = value - arriveBonus;
        }
        return arriveBonus;
    }
    
    /**
     * Denne metode returner vores arrive() metode.
     * 
     * @return arrive()  Ankomst bonusen.
     */
    public int arrive(Player p) {
        return arrive();
    }
    
    /**
     * Returner et string, der giver en tekststreng.
     * 
     * @return String  En tekst der beskriver byen og dens værdi.
     */
    @Override
    public String toString() {
        return this.name + " (" + this.value + ")";
    }

    /**
     * Returner et tal værdi der er med til at sammeligne byene.
     * 
     * @param c Byen vi sammenligner med.
     * @return int
     */
    @Override
    public int compareTo(City c) {
        return name.compareTo(c.name);
    }

    /**
     * Returner en equals boolean for vores by.
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
        City other = (City) o;
        return name.equals(other.name) && country.equals(other.country);
    }

    /**
     * Returner en hashcode værdi for vores by.
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return 11 * name.hashCode() + 13 * country.hashCode();
    }
}
