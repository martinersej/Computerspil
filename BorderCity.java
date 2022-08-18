
/**
 * Lav en beskrivelse af klassen BorderCity her.
 * 
 * @author Martin Pugdal Pedersen
 * @version 2021-11-28
 */
public class BorderCity extends City
{

    /**
     * Denne metode opretter vores grænse objekt ud fra 3 forskellige argumenter.
     * 
     * @param name  Grænsens navn.
     * @param value  Grænsens værdi.
     * @param country  Grænsens land.
     */
    public BorderCity(String name, int value, Country country) {
        super(name, value, country);
    }

    /**
     * Denne metode returner vores ankomst bonus, hvor der beregnes told og trækkes fra hvis vi ankommer fra et andet land.
     * 
     * @param  p  Spilleren
     * @return int   Ankomst bonussen hvor told er trukket fra, hvis vi ankommer fra et andet land ellers trækkes told ikke fra. 
     */
    public int arrive(Player p) {
        int arrive = super.arrive(p);
        if (p.getFromCountry().equals(getCountry())) {
            return arrive;
        }
        float tollRatio = getCountry().getGame().getSettings().getTollToBePaid() / 100.f;
        int tollAmount = (int) (p.getMoney() * tollRatio);
        changeValue(tollAmount);
        return arrive - tollAmount;
    }
}
