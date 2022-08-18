/**
 * Klassen reprænsterer en position for spillerne i spillet.
 * 
 * @author Martin Pugdal Pedersen
 * @version 2021-11-14
 */
public class Position
{

    private City from;
    private City to;
    private int distance;
    private int total;
    
    /**
     * Denne metode opretter vores position objekt med fra en by og til en by og længde mellem de to byer.
     *
     * @param from  En by objekt.
     * @param to  En by objekt.
     * @param distance  Længde mellem de to byer.
     */
    public Position(City from, City to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.total = distance;
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
     * Denne metode returner vores afstand mellem de to byer.
     * 
     * @return distance  Afstanden mellem de to byer.
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Returner det totale afstand mellem vores to byer.
     * 
     * @return total  Det totale afstand. 
     */
    public int getTotal() {
        return total;
    }

    /**
     * Denne metode flytter vores biler ved hjælp af ændring af distance.
     * 
     * @return boolean  Om vi rykker eller ej.
     */
    public boolean move() {
        if (distance > 0) {
            distance--;
            return true;
        }
        return false;
    }

    /**
     * Denne metode får vores bil til at vende rundt i spillet.
     */
    public void turnAround() {
        City temp_var = from;
        from = to;
        to = temp_var;
        distance = total - distance;
    }
    
    /**
     * Denne metode checker om vi er ankommet eller ej ved tjekning af distance er lig med 0.
     * @return boolean  Om vi er ankommet eller ej.
     */
    public boolean hasArrived() {
        return distance == 0;
    }

    /**
     * Returner et string, der giver en tekststreng.
     * 
     * @return String  En tekst der beskriver fra by til by, hvor der vises det manglede afstand ud af det totale.
     */
    @Override
    public String toString() {
        return from+" -> "+to+" : "+distance+"/"+total;
    }
    
    /**
     * Returner en equals boolean for vores position.
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
        Position other = (Position) o;
        return from == (other).from && to == (other).to && distance == (other).distance && total == total;
    }
    
    /**
     * Returner en hashcode værdi for vores position.
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return 11 * from.hashCode() + 13 * to.hashCode() + 17 * new Integer(distance).hashCode() + 19 * new Integer(total).hashCode();
    }
}
