

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testklassen BorderCityTest.
 *
 * @author (dit navn her)
 * @version (versionsnummer eller dato her)
 */
public class BorderCityTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;

    /**
     * Opsæt test-fixtures.
     *
     * Kaldt før hver testmetode.
     */
    @BeforeEach
    public void setUp()
    {
        // Create game object
        game = new Game(0);
        
        // Create countries
        country1 = new Country("Country 1");
        country2 = new Country("Country 2");
        country1.setGame(game);
        country2.setGame(game);

        // Create cities
        cityA = new City("City A", 80, country1);
        cityB = new BorderCity("City B", 60, country1);
        cityC = new BorderCity("City C", 40, country1);
        cityE = new City("City E", 50, country2);

        // Connect cities to countries
        country1.addCity(cityA);
        country1.addCity(cityB);
        country1.addCity(cityC);

        country2.addCity(cityE);
    }

    @Test
    public void arriveFromOtherCountry() {
        for(int seed = 0; seed < 1000; seed++) {
            Player player = new GUIPlayer(new Position(cityE, cityC, 0), 250);
            game.getRandom().setSeed(seed); // Set seed
            int bonus = country1.bonus(40); // Remember bonus
            float tollRatio = game.getSettings().getTollToBePaid() / 100.f; //Hvor mange % er vores told på.
            int toll = (int) (player.getMoney() * tollRatio); // 20% of 250
            int total = bonus - toll; //Hvor meget vi skal betale efter told er trukket fra.
            game.getRandom().setSeed(seed); // Reset seed
            assertEquals(total, cityC.arrive(player)); // Same bonus
            assertEquals(40-total, cityC.getValue()); //Vi tjekker om byens værdi er det samme efter vi har trukket bonus fra og told til.
            cityC.reset();
        }     
    }

    @Test
    public void arriveFromSameCountry() {
        for(int seed = 0; seed < 1000; seed++) {
            Player player = new GUIPlayer(new Position(cityA, cityB, 0), 300);
            game.getRandom().setSeed(seed); // Set seed
            int bonus = country1.bonus(60); // Remember bonus
            game.getRandom().setSeed(seed); // Reset seed
            assertEquals(bonus, cityB.arrive(player)); // Same bonus
            assertEquals(60-bonus, cityB.getValue());
            cityB.reset();
        }     
    }
}
