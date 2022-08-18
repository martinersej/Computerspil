

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testklassen RoadTest.
 *
 * @author Martin Pugdal Pedersen
 * @version 22-11-2021
 */
public class RoadTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;
    private Road road1, road2;

    /**
     * Opsæt test-fixtures.
     *
     * Kaldt før hver testmetode.
     */
    @BeforeEach
    public void setUp()
    {
        // Create countries
        country1 = new Country("Country 1");
        country2 = new Country("Country 2");
    
        // Create cities
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 40, country1);
        cityD = new City("City D", 100, country1);
        
        // Create roads
        road1 = new Road(cityA, cityB, 4);
        road2 = new Road(cityC, cityD, 2);
    }

    @Test
    public void constructor() {
        //Vi tjekker om understående er korrekt, om de har de rigtige byer og længder.
        assertEquals(cityA, road1.getFrom());
        assertEquals(cityB, road1.getTo());
        assertEquals(4, road1.getLength());
        
        assertEquals(cityC, road2.getFrom());
        assertEquals(cityD, road2.getTo());
        assertEquals(2, road2.getLength()); 
    }
    
    @Test
    public void testToString() {
        //Vi tjekker om toString() returner den rigtige tekst.
        assertEquals("City A (80) -> City B (60) : 4", road1.toString());
        assertEquals("City C (40) -> City D (100) : 2", road2.toString());
    }
}
