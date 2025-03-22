package Test;
import domain.*;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CTest
{
    /**
     * Default constructor for test class CTest
     */
    public CTest()
    {
    }
    @Test
    public void shouldBeCreateAWalker(){
        City city = new City();
        Walker prueba = new Walker(city, 20, 20);
        assertFalse(city.isEmpty(20,20));
        assertTrue(prueba.isIndifferent());
    }
    @Test
    public void shouldWalkerMoveToTheNorth(){
        City city = new City();
        Walker prueba = new Walker(city, 20, 20);
        city.ticTac();
        //Tras un tic tac el walker debe dirigirse hacia el norte, es decir, en la posición 19, 20 debe encontrar no puede ser nulo.
        assertFalse(city.isEmpty(19,20));
        assertTrue(city.isEmpty(20,20));
    }
    @Test
    public void shouldWalkerBeHappy(){
        City city = new City();
        Walker messnerP = new Walker(city, 18, 16);
        city.ticTac();
        city.ticTac();
        city.ticTac();
        
        assertTrue(messnerP.isHappy());
        
    }
    @Test
    public void shouldCreateATrafficLight(){
        City city = new City();
        TrafficLight light = new TrafficLight(city,24,24);
        assertFalse(city.isEmpty(24,24));
    }
    @Test
    public void shouldChangeToTheRightColorTheTrafficLight(){
        City city = new City();
        TrafficLight light = new TrafficLight(city,24,24);
        assertTrue(light.getColor() == Color.red);
        city.ticTac();
        System.out.println(light.getColor());
        assertTrue(light.getColor() == Color.yellow);
        city.ticTac();
        assertTrue(light.getColor() == Color.green);
        city.ticTac();
        assertTrue(light.getColor() == Color.yellow);
    }
    @Test
    public void shouldCreateANewKindOfPerson(){
        City city = new City();
        Slider slide = new Slider(city,22,0);
        assertFalse(city.isEmpty(22,0));
    }
    @Test
    public void shouldNotCreateANewKindOfPerson(){
        City city = new City();
        Slider slide = new Slider(city,0,0);
        assertFalse(city.isEmpty(0,0));
    }
    @Test
    public void shouldMakeTheRightMovements(){ // Significativa
        City city = new City();
        Slider slide = new Slider(city, 22,2);
        Person prueba = new Person(city, 22,4);
        TrafficLight light = new TrafficLight(city,22,0);
        city.ticTac();
        city.ticTac();
        assertTrue(slide.isDissatisfied());
        city.ticTac();
        city.ticTac();
        city.ticTac();
        city.ticTac();
        assertTrue(slide.isDissatisfied());
    }
    @Test
    public void shouldNotCreateANewKindOfItem(){
        City city = new City();
        Road roadC = new Road(city,0,0);
        assertFalse(city.isEmpty(0,0));
    }
    @Test
    public void shouldCreateANewKindOfItem(){
        City city = new City();
        Road roadT = new Road(city,21,0);
        assertFalse(city.isEmpty(21,0));
    }
    @Test
    public void shouldMakeTheRightFunction(){ // Significativa
        City city = new City();
        Road roadT = new Road(city,21,0);
        for (int i = 1; i < 25; i++){
            city.ticTac();
        }
        assertFalse(city.isEmpty(21,0) && city.isEmpty(21,24) && city.isEmpty(21, 16)); // Escogiendo posiciones de manera horizontal se evidencia que se esta construyendo la carretera
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        City Thecity = new City();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}
