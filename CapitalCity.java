
/**
 * Lav en beskrivelse af klassen CapitalCity her.
 * 
 * @author Martin Pugdal Pedersen
 * @version 2021-11-28
 */
public class CapitalCity extends BorderCity
{

    /**
     * Denne metode opretter vores hovedstad objekt ud fra 3 forskellige argumenter.
     * 
     * @param name  Hovedstadens navn.
     * @param value  Hovedstadens værdi.
     * @param country  Hovedstadens land.
     */
    public CapitalCity(String name, int value, Country country) {
        super(name, value, country);
    }

    /**
     * Denne metode returner vores ankomst bonus, hvor der beregnes told og trækkes fra, hvis spilleren har penge.
     * 
     * @param  p  Spilleren
     * @return int   Bonus og told, hvor fees trækkes fra. 
     */
    @Override
    public int arrive(Player p) {
        int bonus_Toll = super.arrive(p);
        int remaining = (p.getMoney() + bonus_Toll);
        if (remaining < 0) {
            remaining = 0;
        }
        int fee = getCountry().getGame().getRandom().nextInt(remaining + 1);
        changeValue(fee);
        return bonus_Toll - fee;
    }

}
