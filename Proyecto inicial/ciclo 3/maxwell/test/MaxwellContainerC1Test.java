package test;
import maxwell.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class MaxwellContainerC1Test.
 *
 * @author  Andrés Cardozo && Tulio Riaño
 * @version (a version number or a date)
 *
 */
public class MaxwellContainerC1Test
{
    /**
     * Default constructor for test class MaxwellContainerC1Test
     */
    public MaxwellContainerC1Test()
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
    public void shouldCreateMaxwellContainer(){
        MaxwellContainer a = new MaxwellContainer();
        MaxwellContainer b = new MaxwellContainer(200,300);
        assertTrue(a.equals(b));
    }
    @Test
    public void shouldStartA(){
        MaxwellContainer a = new MaxwellContainer();
        MaxwellContainer b = new MaxwellContainer();
        a.start(100);
        b.start(100);
        assertEquals(a.particles(),b.particles());
    }
    @Test
    public void CreateMaxwellContainer(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        assertTrue(a.ok());
    }
    @Test
    public void createMaxwellContainerTest(){
        /*
        MaxwellContainer a = new MaxwellContainer(100,200,80,5,3,new java.util.ArrayList<>(java.util.Arrays.asList(  
        new java.util.ArrayList<>(java.util.Arrays.asList(-80, 100, 4, 1)),  
        new java.util.ArrayList<>(java.util.Arrays.asList(-30, 180, 2, 1)),  
        new java.util.ArrayList<>(java.util.Arrays.asList(-50, 90, 8, 7)),  
        new java.util.ArrayList<>(java.util.Arrays.asList(-67, 30, -1, 4)),  
        new java.util.ArrayList<>(java.util.Arrays.asList(-10, 120, 14, 16)),      
        new java.util.ArrayList<>(java.util.Arrays.asList(187, 8, -15, -20)),  
        new java.util.ArrayList<>(java.util.Arrays.asList(100, 190, 30, 40)),  
        new java.util.ArrayList<>(java.util.Arrays.asList(11, 22, 33, 30))  )) );
        a.finish();
        assertTrue(a.ok());
        */
    }
    @Test
    public void shouldAddDemon(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addDemon(20);
        int posDemon = 20;
        int posX = 200;
        int posY = (100/2)+20;
        assertEquals(20,a.demons().get(0));
        assertEquals(350-20,a.convertionsBoardToCanvas(200,a.demons().get(0)).get(1));
    }
    @Test
    public void shouldNotAddDemon(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addDemon(-20);
        assertTrue(a.demons().size() == 0);        
    }
    @Test
    public void shouldDelDemon(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addDemon(20);
        a.delDemon(20);
        assertTrue(a.demons().size() == 0); 
    }
    @Test
    public void shouldAddParticle(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addParticle("green", true, -20, 20, 5, 4);
        assertTrue(a.ok());
    }
    @Test
    public void shouldNotAddParticle(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addParticle("green", false, 20, -20, 5, 4);
        assertFalse(a.ok());
        
    }
    @Test
    public void shouldDelParticle(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addParticle("green", false, 20, 20, 5, 4);
        a.delParticle("green");
        assertTrue(a.ok());
        
    }

    @Test
    public void shouldAddHole(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addHole(50, 50, 7);
        assertTrue(a.ok());
        a.finish();
    }
    @Test
    public void shouldNotAddHole(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addHole(50, -5000, 7);
        assertFalse(a.ok());
        a.finish();
    }    
    @Test
    public void shouldStartB(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addHole(100, 100, 7);
        a.addParticle("green", true, 20, 20, 5, 4);
        a.addDemon(20);
        a.start(20);
        assertTrue(a.ok());
        a.finish();
    }
    @Test
    public void shouldIsGoal(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addHole(100, 100, 7);
        a.addParticle("green", true, 20, 20, 5, 4);
        a.addDemon(20);
        a.start(20);
        a.isGoal();
        a.finish();
        assertTrue(a.ok());
        
    }
    @Test
    public void shouldShowDemons(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addDemon(20);
        a.demons();
        a.finish();
        assertTrue(a.ok());
    }
    
    @Test
    public void shouldShowParticles(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addParticle("green", true, 20, 20, 5, 4);
        a.particles();
        a.finish();
        assertTrue(a.ok());
    }
    
    @Test
    public void shouldShowHoles(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addHole(100, 100, 7);
        a.holes();
        a.finish();
        assertTrue(a.ok());
    }
    
    @Test
    public void shouldMakeVisible(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.makeVisible();
        a.finish();
        assertTrue(a.ok());
    }
    @Test
    public void shouldMakeInvisible(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.makeInvisible();
        a.finish();
        assertTrue(a.ok());
    }
    @Test
    public void shouldFinish(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.finish();
        assertTrue(a.ok());
    }
    
    @Test
    public void shouldGetWidthContainer(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.getWidthContainer();
        a.finish();
        assertTrue(a.ok());
    }
    
    @Test
    public void shouldGetHeightContainer(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.getHeightContainer();
        a.finish();
        assertTrue(a.ok());
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
