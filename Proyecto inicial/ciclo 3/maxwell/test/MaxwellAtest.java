package test;
import maxwell.*;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class maxwellAtest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MaxwellAtest
{
    /**
     * Default constructor for test class maxwellAtest
     */
    public MaxwellAtest()
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
    public void shouldMoveRightBeforeNewTypes(){
        MaxwellContainer a = new MaxwellContainer();
        a.addDemon("Normal",100);
        a.addHole(-180, 80, 2);
        a.addParticle("Normal","orange",true,50,50,-10,10);
        a.addParticle("Normal", "red", true, 100, 50, 15, 5);
        a.addParticle("Normal", "yellow", false, -80, 40, 15, -5);
        a.addParticle("Normal", "pink", true, -110, 80, 4, 2);
        a.addParticle("Normal", "gray", false, -40, 110, 5, 5);
        a.addParticle("Normal", "black", true, 50, 10, -15, 7);
        a.addParticle("Normal", "red", false, 10, 101, 5, 15);
        a.makeVisible();
        a.start(50);
    }
    
    @Test
    public void shouldBeRightAfterNewTypes(){
        MaxwellContainer a = new MaxwellContainer();
        a.addHole("EatParticle",50, 50, 5);
        a.addHole("Movil",-120,120, 10);
        a.addDemon("Blue",50);
        a.addDemon("Blue",80);
        a.addDemon("Weak",150);
        a.addDemon("Weak",140);
        a.addParticle("Flying","turquoise",true,80,80,-15,0);
        a.addParticle("Ephemeral", "red", true, -160, 160, -5, 5);
        a.addParticle("Rotator","turquoise",false,80,110,0,20);
        a.addParticle("Flying", "black", true, 100, 100, 5, -5);
        a.addParticle("Flying", "blue", true, -80, 150, 30, 0);
        a.addParticle("Flying", "blue", true, 80, 140, -30, 0);
        a.addParticle("Flying", "Grey", true, 10, 10, 5, 5);
        a.addParticle("Ephemeral", "red", true, 10, 10, 5, 5);
        a.addParticle("Ephemeral", "red", true, 10, 10, 5, 5);
        a.addParticle("Ephemeral", "red", true, 10, 10, 5, 5);
        a.addParticle("Normal", "red", true, 10, 10, 5, 5);
        a.addParticle("Normal", "red", true, 10, 10, 5, 5);
        a.addParticle("Normal", "blue", false, -80, 50, 5,0);
        a.makeVisible();
        a.start(50);
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
