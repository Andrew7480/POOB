package test;


import maxwell.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * The test class maxwellCC4test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MaxwellCC4test
{
    /**
     * Default constructor for test class maxwellCC4test
     */
    public MaxwellCC4test()
    {
    }

    @Test
    public void accordingCRShouldCreateTheNewParticles(){
        MaxwellContainer a = new MaxwellContainer();
        a.addParticle("Flying", "red", true, 100, 100, -5, 5);
        a.addParticle("Rotator", "red", true, 200, 90, -10, 5);
        a.addParticle("Ephemeral", "red", true, 50, 80, -15, 5);
        //como guardamos las posiciones de canvas hacemos la convercion, por ejemplo de la primera: 100+350= 450 y 350-100=250
        ArrayList<ArrayList<Integer>> prueba = new ArrayList<>();
        prueba.add(new ArrayList<>(Arrays.asList(450, 250, -5, 5)));
        assertEquals(prueba,a.particles("Flying"));
        
        ArrayList<ArrayList<Integer>> prueba1 = new ArrayList<>();
        prueba1.add(new ArrayList<>(Arrays.asList(550, 260, -10, 5)));
        assertEquals(prueba1,a.particles("Rotator"));
        
        ArrayList<ArrayList<Integer>> prueba2 = new ArrayList<>();
        prueba2.add(new ArrayList<>(Arrays.asList(400, 270, -15, 5)));
        assertEquals(prueba2,a.particles("Ephemeral"));
        
        a.finish();
    }
    
    @Test
    public void accordingCRShouldCreateTheNewHoles(){
        MaxwellContainer a = new MaxwellContainer();
        a.addHole("Movil",180, 100, 5);
        a.addHole("EatParticle",-200, 100, 15);
        
        //como guardamos las posiciones de canvas hacemos la convercion, por ejemplo de la primera: 180+350= 530 y 350-100=250
        ArrayList<ArrayList<Integer>> prueba = new ArrayList<>();
        prueba.add(new ArrayList<>(Arrays.asList(530, 250, 5)));
        assertEquals(prueba,a.holes("Movil"));
        
        ArrayList<ArrayList<Integer>> prueba1 = new ArrayList<>();
        prueba1.add(new ArrayList<>(Arrays.asList(150, 250,15)));
        assertEquals(prueba1,a.holes("EatParticle"));
        
        a.finish();
        
    }
    
    @Test
    public void accordingCRShouldCreateTheNewDemons(){
        MaxwellContainer a = new MaxwellContainer();
        a.addDemon("Blue", 20);
        a.addDemon("Weak", 40);
        
        ArrayList<Integer> prueba = new ArrayList<>(Arrays.asList(20));
        assertEquals(prueba,a.demons("Blue"));
        
        ArrayList<Integer> prueba1 = new ArrayList<>(Arrays.asList(40));
        assertEquals(prueba1,a.demons("Weak"));
        
        a.finish();
        
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
