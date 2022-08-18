import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testklassen CapitalCityTest.
 *
 * @author (dit navn her)
 * @version (versionsnummer eller dato her)
 */
public class CapitalCityTest
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
        cityB = new CapitalCity("City B", 60, country1);
        cityC = new CapitalCity("City C", 40, country1);
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
            int remaining = (player.getMoney() + bonus - toll);
            int fee = game.getRandom().nextInt(remaining+1); // Får fees.
            int total = bonus-toll-fee; //Hvor meget vi har efter.
            game.getRandom().setSeed(seed); // Reset seed
            assertEquals(total, cityC.arrive(player)); // Same bonus
            assertEquals(40-total, cityC.getValue());
            cityC.reset();
        }     
    }

    @Test
    public void arriveFromSameCountry() {
        for(int seed = 0; seed < 1000; seed++) {
            Player player = new GUIPlayer(new Position(cityA, cityB, 0), 300);
            game.getRandom().setSeed(seed); // Set seed
            int bonus = country1.bonus(60); // Remember bonus
            int remaining = (300 + bonus);
            int fee = game.getRandom().nextInt(remaining+1); // Får fees.
            int total = bonus-fee; //Hvor meget vi har efter.
            game.getRandom().setSeed(seed); // Reset seed
            assertEquals(total, cityB.arrive(player)); // Same bonus
            assertEquals(60-total, cityB.getValue());
            cityB.reset();
        }
    }
}
