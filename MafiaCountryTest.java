

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

/**
 * Testklassen MafiaCountryTest.
 *
 * @author (dit navn her)
 * @version (versionsnummer eller dato her)
 */
public class MafiaCountryTest
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
        country2 = new MafiaCountry("Country 2");
        country1.setGame(game);
        country2.setGame(game);
    }

    @Test
    public void bonus() {
        int testAmount = 50000;
        for(int seed = 0; seed < 1000; seed++) {
            game.getRandom().setSeed(seed);
            int robs = 0;
            int loss = 0;
            int sum = 0;
            Set<Integer> valuesRobbery = new HashSet<>();
            Set<Integer> valuesNoRobbery = new HashSet<>();
            for(int i = 0; i<testAmount; i++) {
                int bonus = country2.bonus(80);
                if(bonus < 0) { // Robbery
                    robs++;
                    assertTrue(10 <= Math.abs(bonus) && Math.abs(bonus) <= 50);
                    loss -= bonus;
                    valuesRobbery.add(-bonus);
                } else { // No Robbery
                    assertTrue(0 <= bonus && bonus <= 80);
                    sum += bonus;
                    valuesNoRobbery.add(bonus);
                } 
            }
            double expectedRobs = testAmount * 20 / 100;
            double expectedLoss = testAmount * 30 * 20 / 100;
            double expectedSum = testAmount * 40 * 80 / 100;

            //Afvigelserne må sættes lidt mere op, da der er 20% chance for at blive røvet. Så der kan godt være afvigelser pga. den lille mængde loop.
            assertTrue(expectedRobs * 0.97 <= robs && robs <= expectedRobs * 1.03); //Tjekker om middelværdien er tæt på det forventede.
            assertTrue(expectedLoss * 0.97 <= loss && loss <= expectedLoss * 1.03); //Tjekker om middelværdien er tæt på det forventede.
            assertTrue(expectedSum * 0.97 <= sum && sum <= expectedSum * 1.03); //Tjekker om middelværdien er tæt på det forventede.
            
            assertEquals(Math.abs(10-50), valuesRobbery.size()-1); //Tjekker om de bonus-værdier vi får er det samme størrelse af alle de bonus-værdier vi har fået.
            assertEquals(80, valuesNoRobbery.size()-1); //Tjekker om de bonus-værdier vi får er det samme størrelse af alle de bonus-værdier vi har fået.
        }
    }
}
