package test;


import maxwell.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The test class maxwellC4test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MaxwellC4test
{
    /**
     * Default constructor for test class maxwellC4test
     */
    public MaxwellC4test()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }
    
    @Test
    public void shouldCreateTheNewParticles(){
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
    public void shouldFLy(){
        MaxwellContainer a = new MaxwellContainer();
        a.addParticle("Flying", "red", true, 100, 100, -5, 5);
        a.addHole(95, 105, 10);
        a.start(2);
        
        ArrayList<ArrayList<Integer>> prueba2 = new ArrayList<>();
        prueba2.add(new ArrayList<>(Arrays.asList(440, 240, -5, 5)));
        
        assertEquals(false,a.particles().isEmpty());
        assertEquals(prueba2,a.particles("Flying"));
        a.finish();
    }
    
    @Test
    public void shouldMakeTheRotatorRight(){
        MaxwellContainer a = new MaxwellContainer();
        a.addParticle("Rotator","turquoise",false,80,110,0,20);
        //a.addParticle("Rotator","turquoise",false,-80,110,20,0);
        a.start(5);
        int vx = a.particles("Rotator").get(0).get(2);
        int vy = a.particles("Rotator").get(0).get(3);
        assertEquals(-20,vx);
        assertEquals(0,vy);
        
        a.finish();
    }
    
    
    @Test
    public void shouldDisappear(){
        MaxwellContainer a = new MaxwellContainer(3,3);
        a.addParticle("Ephemeral", "red", true, 1, 1, -1, 1);
        a.start(100);
        
        assertEquals(true,a.particles().isEmpty());
        
        a.finish();
    }
    
    @Test
    public void shouldCreateTheNewHoles(){
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
    public void shouldMoveMovil(){
        MaxwellContainer a = new MaxwellContainer(3,3);
        a.addHole("Movil",1, 1, 5);
        a.start(1);
        
        ArrayList<ArrayList<Integer>> prueba = new ArrayList<>();
        prueba.add(new ArrayList<>(Arrays.asList(352, 348, 5)));
        assertEquals(prueba,a.holes("Movil"));
        
        a.start(1);
        
        ArrayList<ArrayList<Integer>> prueba1 = new ArrayList<>();
        prueba1.add(new ArrayList<>(Arrays.asList(353, 347, 5)));
        assertEquals(prueba1,a.holes("Movil"));
        
        a.start(1);
        
        ArrayList<ArrayList<Integer>> prueba2 = new ArrayList<>();
        prueba2.add(new ArrayList<>(Arrays.asList(354, 346, 5)));
        assertEquals(prueba2,a.holes("Movil"));

        a.start(3);
        assertEquals(prueba2,a.holes("Movil"));
        a.finish();
    }
    
    @Test
    public void shouldGetBigger(){
        MaxwellContainer a = new MaxwellContainer();
        a.addHole("EatParticle",1, 1, 5);
        a.addParticle("Normal", "red", true, 2, 2, -1, -1);
        a.addParticle("Normal", "red", true, 2, 2, -1, -1);
        a.addParticle("Normal", "red", true, 2, 2, -1, -1);
        a.addParticle("Normal", "red", true, 2, 2, -1, -1);
        a.addParticle("Normal", "red", true, 2, 2, -1, -1);
        a.addParticle("Normal", "red", true, 2, 2, -1, -1);
        
        assertEquals(5,a.holes("EatParticle").get(0).get(2));
        EatParticle p = (EatParticle) a.getEatParticlesHoles().get(0);
        assertEquals(10,p.getwidth());
        
        a.start(1);
        assertEquals(0,a.holes("EatParticle").get(0).get(2));
        assertEquals(25,p.getwidth());
        assertEquals(false,a.particles().isEmpty());
        assertEquals(1,a.particles().size());
        
        a.finish();
    }
    
    @Test
    public void shouldCreateTheNewDemons(){
        MaxwellContainer a = new MaxwellContainer();
        a.addDemon("Blue", 20);
        a.addDemon("Weak", 40);
        
        ArrayList<Integer> prueba = new ArrayList<>(Arrays.asList(20));
        assertEquals(prueba,a.demons("Blue"));
        
        ArrayList<Integer> prueba1 = new ArrayList<>(Arrays.asList(40));
        assertEquals(prueba1,a.demons("Weak"));
        
        a.finish();
        
    }
    
    @Test
    public void shouldPassOnlyBlues(){
        MaxwellContainer a = new MaxwellContainer();
        a.addDemon("Blue", 20);
        a.addParticle("red", true, 2, 20, -1, 0);
        a.addParticle("red", false, 2, 20, -1, 0);        
        a.start(2);
        
        assertEquals(false,a.getParticulesChamber().get(0).getIsLeft());
        assertEquals(true,a.getParticulesChamber().get(1).getIsLeft() );
        
        a.finish();
        
    }
    
    @Test
    public void shouldDestroyItself(){
        MaxwellContainer a = new MaxwellContainer();
        a.addDemon("Weak", 20);
        a.addParticle("red", true, 2, 20, -1, 0);    
        a.start(2);
        
        assertEquals(true,a.getParticulesChamber().get(0).getIsLeft());
        assertEquals(true, a.getDemonFaces().isEmpty());
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
