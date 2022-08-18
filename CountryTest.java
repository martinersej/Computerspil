

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import java.util.HashSet;
import java.util.*;
/**
 * Testklassen CountryTest.
 *
 * @author Martin Pugdal Pedersen
 * @version 22-11-2021
 */
public class CountryTest
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

        // Create roads
        road1 = new Road(cityA, cityB, 4);
        road2 = new Road(cityC, cityD, 2);
    }

    @Test
    public void bonus() {
        for(int seed = 0; seed < 100; seed++) { //Vi afprøver forskellige seeds
            game.getRandom().setSeed(seed); //Vi sætter vores seed.
            Set<Integer> values = new HashSet<>();
            int sum = 0;
            for(int i = 0; i < 100000; i++) { //Kalder metoden 100000 gange.
                int bonus = country1.bonus(80); //Gemmer bonussen
                assertTrue(0 <= bonus && bonus <= 80); //Tjekker om bonussen er indenfor intervallet.
                values.add(bonus);
                sum += bonus;
            }
            double expectedSum = 100000 * (80/2.00);
            assertTrue(expectedSum * 0.99 <= sum && sum <= expectedSum * 1.01); //Tjekker om middelværdien er tæt på det forventede.
            assertEquals(80, values.size()-1); //Tjekker om de bonus-værdier vi får er det samme størrelse af alle de bonus-værdier vi har fået..
            assertTrue(0 == country1.bonus(0)); //Tjekker at bonus(0) giver 0.
            int check = country1.bonus(1);
            assertTrue(1 == check || 0 == check); //Tjekker om bonus(1) giver 0 eller 1.
        }
    }
    
    @Test
    public void constructor() {
        assertEquals("Country 1", country1.toString()); //Tjekker om landets navn passer.
        assertEquals("Country 2", country2.toString()); //Tjekker om landets navn passer.
    }
    
    @Test
    public void getCities() {
        assertEquals(4, country1.getCities().size()); //Tjekker om størrelsen af landets byer passer.
        assertEquals(3, country2.getCities().size()); //Tjekker om størrelsen af landets byer passer.
    }

    @Test
    public void reset() {
        cityA.arrive(); cityA.arrive(); cityA.arrive();
        cityE.arrive(); cityE.arrive(); cityE.arrive();
        int valueA = cityA.getValue();
        int valueE = cityE.getValue();
        country1.reset();
        country2.reset();
        assertNotEquals(valueA, cityA.getValue()); //Tjekker om at værdien er blevet reset.
        assertNotEquals(valueE, cityE.getValue()); //Tjekker om at værdien er blevet reset.
    }

    @Test
    public void position() {
        assertEquals(new Position(cityA, cityA, 0), country1.position(cityA)); //Tjekker om positionen er det samme.
        assertEquals(new Position(cityE, cityE, 0), country2.position(cityE)); //Tjekker om positionen er det samme.
    }

    @Test
    public void testToString() {
        //Vi tjekker om toString returner den rigtige tekst.
        assertEquals("Country 1", country1.toString());
        assertEquals("Country 2", country2.toString());
    }

    @Test
    public void getRoads() {
        country1.addRoads(road1.getFrom(), road1.getTo(), road1.getLength());
        country1.addRoads(road2.getFrom(), road2.getTo(), road2.getLength());
        assertTrue(country1.getRoads(cityA).contains(road1)); //Tjekker om vejen findes i vores land.
        assertTrue(country1.getRoads(cityC).contains(road2)); //Tjekker om vejen findes i vores land.
    }
    
    @Test
    public void addCity() {
        City cityTest = new City("City Test", 10, country1);
        assertNotEquals(cityTest, country1.getCity(cityTest.getName())); //Tjekker om landet ikke findes i vores land.
        country1.addCity(cityTest);
        assertEquals(cityTest, country1.getCity(cityTest.getName())); //Tjekker nu om landet findes i vores land.
    }
    
    @Test
    public void getCity() {
        //Tjekker om byen er der eller er null.
        assertTrue(country1.getCity(cityA.getName()) != null);
        assertTrue(cityA.equals(country1.getCity(cityA.getName())));
        
        assertTrue(country1.getCity(cityF.getName()) == null);
        assertFalse(cityF.equals(country1.getCity(cityF.getName())));
    }
    
    @Test
    public void readyToTravel() {
        country1.addRoads(road1.getFrom(), road1.getTo(), road1.getLength());
        country1.addRoads(road2.getFrom(), road2.getTo(), road2.getLength());
        assertEquals(new Position(cityA, cityB, road1.getLength()), country1.readyToTravel(cityA, cityB)); //Tjekker om der er en position fra cityA til cityB og returner en position med en positiv længde.
        assertEquals(new Position(cityA, cityA, 0), country1.readyToTravel(road1.getFrom(), road1.getFrom())); //Tjekker om der er en position fra cityA til cityB og returner en position med 0 i længde.
        assertEquals(new Position(cityB, cityB, 0), country1.readyToTravel(cityB, cityC)); //Tjekker om der er en position fra cityA til cityB og returner en position med 0 i længde.
        assertEquals(new Position(cityC, cityD, road2.getLength()), country1.readyToTravel(cityC, cityD)); //Tjekker om der er en position fra cityA til cityB og returner en position med positiv længde.
        
        assertEquals(new Position(cityA, cityA, 0), country2.readyToTravel(cityA, cityB)); //Tjekker om der er en position fra cityA til cityB i et andet land som ikke har byerne, derfor returner den cityA, cityA og 0.
        assertEquals(new Position(cityA, cityA, 0), country2.readyToTravel(cityA, cityF)); //Tjekker om der er en position fra cityA til cityF i country2 som har ikke cityA, derfor returner den cityA, cityA og 0.
    }

    @Test
    public void addRoads() {
        country1.addRoads(road1.getFrom(), road1.getTo(), road1.getLength());
        country1.addRoads(road2.getFrom(), road2.getTo(), road2.getLength());
        
        assertEquals(1, country1.getRoads(cityA).size()); //Tjekker om størrelsen er korrekt.
        country1.addRoads(cityA, cityD, 0);
        assertEquals(1, country1.getRoads(cityA).size()); //Tjekker om størrelsen er korrekt efter vi har tilføjet en vej.

        assertEquals(1, country1.getRoads(cityA).size()); //Tjekker om størrelsen er korrekt.
        country1.addRoads(cityA, cityC, 5);
        assertEquals(2, country1.getRoads(cityA).size()); //Tjekker om størrelsen er korrekt efter vi har tilføjet en vej.

        assertEquals(1, country1.getRoads(cityB).size()); //Tjekker om størrelsen er korrekt.
        country1.addRoads(cityB, cityD, 2);
        assertEquals(2, country1.getRoads(cityB).size()); //Tjekker om størrelsen er korrekt efter vi har tilføjet en vej.

        assertEquals(2, country1.getRoads(cityD).size()); //Tjekker om størrelsen er korrekt.
        country1.addRoads(cityD, cityC, 4);
        assertEquals(3, country1.getRoads(cityD).size()); //Tjekker om størrelsen er korrekt efter vi har tilføjet en vej.
        
        assertEquals(3, country1.getRoads(cityD).size()); //Tjekker om størrelsen er korrekt.
        country1.addRoads(cityA, cityD, 7);
        assertEquals(4, country1.getRoads(cityD).size()); //Tjekker om størrelsen er korrekt efter vi har tilføjet en vej.
       
        assertEquals(0, country1.getRoads(cityF).size()); //Tjekker om størrelsen er korrekt.
        country1.addRoads(cityA, cityF, 12);
        assertEquals(0, country1.getRoads(cityF).size()); //Tjekker om størrelsen er korrekt efter vi har tilføjet en vej.

        assertEquals(0, country2.getRoads(cityB).size()); //Tjekker om størrelsen er korrekt.
        country2.addRoads(cityB, cityD, 7);
        assertEquals(0, country2.getRoads(cityB).size()); //Tjekker om størrelsen er korrekt.

        assertEquals(0, country2.getRoads(cityF).size()); //Tjekker om størrelsen er korrekt.
        country2.addRoads(cityF, cityD, 6);
        assertEquals(1, country2.getRoads(cityF).size()); //Tjekker om størrelsen er korrekt efter vi har tilføjet en vej.
        
        assertEquals(0, country2.getRoads(cityE).size()); //Tjekker om størrelsen er korrekt.
        country2.addRoads(cityA, cityD, 3);
        assertEquals(0, country2.getRoads(cityE).size()); //Tjekker om størrelsen er korrekt efter vi har tilføjet en vej.

        assertEquals(0, country2.getRoads(cityE).size()); //Tjekker om størrelsen er korrekt.
        country2.addRoads(cityE, cityE, -1);
        assertEquals(0, country2.getRoads(cityE).size()); //Tjekker om størrelsen er korrekt efter vi har tilføjet en vej.

        //Her manipulere vi landene og byene sammen, for at se om den ikke tilføjer vejene
        assertEquals(4, country1.getRoads(cityA).size()); //Tjekker om størrelsen er korrekt.
        assertEquals(0, country2.getRoads(cityB).size()); //Tjekker om størrelsen er korrekt.
        country1.addRoads(cityA, cityB, 3);
        assertEquals(5, country1.getRoads(cityA).size()); //Tjekker om størrelsen er korrekt efter vi har tilføjet en vej.
        assertEquals(0, country2.getRoads(cityB).size()); //Tjekker om størrelsen er korrekt efter vi har tilføjet en vej.
        

        //Her tjekker vi for negativ værdi af længde.
        assertEquals(3, country1.getRoads(cityC).size()); //Tjekker om størrelsen er korrekt.
        country2.addRoads(cityC, cityB, -4);
        assertEquals(3, country1.getRoads(cityC).size()); //Tjekker om størrelsen er korrekt efter vi har tilføjet en vej.

        assertEquals(0, country2.getRoads(cityE).size()); //Tjekker om størrelsen er korrekt.
        country2.addRoads(cityE, cityF, -1);
        assertEquals(0, country2.getRoads(cityE).size()); //Tjekker om størrelsen er korrekt efter vi har tilføjet en vej.
        
        assertEquals(1, country2.getRoads(cityF).size()); //Tjekker om størrelsen er korrekt.
        country2.addRoads(cityA, cityF, -1);
        assertEquals(1, country2.getRoads(cityF).size()); //Tjekker om størrelsen er korrekt efter vi har tilføjet en vej.
    }
}