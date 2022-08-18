

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testklassen CityTest.
 *
 * @author Martin Pugdal Pedersen
 * @version 22-11-2021
 */
public class CityTest
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
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 40, country1);
        cityD = new City("City D", 100, country1);
        cityE = new City("City E", 50, country2);
        cityF = new City("City F", 90, country2);
        cityG = new City("City G", 70, country2);

        // Connect cities to countries
        country1.addCity(cityA);
        country1.addCity(cityB);
        country1.addCity(cityC);
        country1.addCity(cityD);

        country2.addCity(cityE);
        country2.addCity(cityF);
        country2.addCity(cityG);
    }

    @Test
    public void arrive(){
        for(int seed = 0; seed < 1000; seed++) { //Afprøver 100 forskellige seeds.
            game.getRandom().setSeed(seed); //Sætter vores seeds.
            int bonus = country1.bonus(80); //Gemmer bonus.
            game.getRandom().setSeed(seed);
            assertEquals(bonus, cityA.arrive()); //Tjekker om de to passer.
            assertEquals(80-bonus, cityA.getValue()); //TJekker om værdien passer med 80-bonus.
            cityA.reset(); 
        }
        assertEquals(0, country1.bonus(0));
    }
    
    @Test
    public void constructor() {
        assertEquals("City A", cityA.getName()); //Tjekker om navnet er korrekt.
        assertEquals(80, cityA.getInitialValue()); //Tjekker om værdien er korrekt.
        assertEquals(country1, cityA.getCountry()); //Tjekker om landet er korrekt.
        
        assertEquals("City B", cityB.getName()); //Tjekker om navnet er korrekt.
        assertEquals(60, cityB.getInitialValue()); //Tjekker om værdien er korrekt.
        assertEquals(country1, cityB.getCountry()); //Tjekker om landet er korrekt.
    }
    
    @Test
    public void changeValue() {
        //Vi tjekker om værdien er korrekt, også efter vi har ændret værdien.
        assertEquals(80, cityA.getValue());
        cityA.changeValue(20);
        assertEquals(100, cityA.getValue());
        
        assertEquals(60, cityB.getValue());
        cityB.changeValue(-10);
        assertEquals(50, cityB.getValue());

        assertEquals(40, cityC.getValue());
        cityC.changeValue(0);
        assertEquals(40, cityC.getValue());
    }

    @Test
    public void reset() {
        //Vi ændrer værdien inden vi resetter den, hvor vi tjekker om den nye værdi passer inden vi ændrer den til den oprindelige værdi igen.
        assertEquals(80, cityA.getValue());
        cityA.changeValue(50);
        assertEquals(130, cityA.getValue());
        cityA.reset();
        assertEquals(80, cityA.getValue());

        assertEquals(60, cityB.getValue());
        cityB.changeValue(-90);
        assertEquals(-30, cityB.getValue());
        cityB.reset();
        assertEquals(60, cityB.getValue());
    }
    
    @Test
    public void testToString() {
        //Vi tjekker om toString returner den rigtige tekst.
        assertEquals("City A (80)", cityA.toString());
        assertEquals("City B (60)", cityB.toString());
    }
}
