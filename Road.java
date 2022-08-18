/**
 * Klassen reprænsterer en vej på spillet.
 * 
 * @author Martin Pugdal Pedersen
 * @version 2021-11-14
 */
public class Road implements Comparable<Road>
{
    private City from;
    private City to;
    private int length;

    /**
     * Denne metode opretter vores vej objekt med fra en by og til en by og længde mellem de to byer.
     *
     * @param from  En by objekt.
     * @param to  En by objekt.
     * @param length  Længde mellem de to byer.
     */
    public Road(City from, City to, int length)
    {
        this.from = from;
        this.to = to;
        this.length = length;
    }

    /**
     * Denne metode returner vores by hvor vi kommer fra.
     * 
     * @return from  Byen vi kommer fra.
     */
    public City getFrom() {
        return from;
    }

    /**
     * Denne metode returner vores by hvor vi kommer til.
     * 
     * @return to  Byen vi kommer til.
     */
    public City getTo() {
        return to;
    }
    
    /**
     * Denne metode returner vores længde mellem byerne.
     * 
     * @return length  længden mellem de to byer.
     */
    public int getLength() {
        return length;
    }
    
    /**
     * Returner et string, der giver en tekststreng.
     * 
     * @return String  En tekst der beskriver fra by til by, hvor længde bliver også skrevet.
     */
    @Override
    public String toString() {
        return from+" -> "+to+" : "+this.length;
    }
    
    /**
     * Returner et tal værdi der er med til at sammeligne vejene.
     * 
     * @param r Vejen vi sammenligner med.
     * @return int
     */
    @Override
    public int compareTo(Road r) {
        if (from.equals(r.from)) {
            if (to.equals(r.to)) {
                return length - r.length;
            }
            return to.compareTo(r.to);
        }
        return from.compareTo(r.from);
    }

    /**
     * Returner en equals boolean for vores vej.
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
        Road other = (Road) o;
        return from == (other).from && to == (other).to && length == (other).length;
    }
  
    /**
     * Returner en hashcode værdi for vores vej.
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return 11 * from.hashCode() + 13 * to.hashCode() + 17 * new Integer(length).hashCode();
    }
}
