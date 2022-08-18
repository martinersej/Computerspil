
/**
 * Lav en beskrivelse af klassen MafiaCountry her.
 * 
 * @author Martin Pugdal Pedersen
 * @version 2021-11-28
 */
public class MafiaCountry extends Country
{

    /**
     * Denne metode opretter vores Mafialand objekt ud fra navnet.
     * 
     * @param name  Mafialandets navn.
     */
    public MafiaCountry(String name) {
        super(name);
    }

    /**
     * Denne metode returner vores bonus eller tab, når vi besøger et Mafialand.
     * 
     * @param  value  Bonussen fra landet.
     * @return int   Returner vores tab eller bonus, det afhænger af de 20% om vi bliver røvet. 
     */
    @Override
    public int bonus(int value) {
        if (getGame().getRandom().nextInt(100) < getGame().getSettings().getRisk()) {
            return -getGame().getLoss();
        } else {
            return super.bonus(value);
        }
    }
}