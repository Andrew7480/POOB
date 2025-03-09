import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class MaxwellContainerC1Test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MaxwellContainerCC1Test
{
    /**
     * Default constructor for test class MaxwellContainerC1Test
     */
    public MaxwellContainerCC1Test()
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
    public void accordingCRShouldCreateMaxwellContainer(){
        MaxwellContainer a = new MaxwellContainer();
        MaxwellContainer b = new MaxwellContainer(200,300);
        
        assertTrue(a.equals(b));
    }
    @Test
    public void accordingCRShouldStart(){
        MaxwellContainer a = new MaxwellContainer();
        MaxwellContainer b = new MaxwellContainer();
        a.start(100);
        b.start(100);
        
        assertEquals(a.particles(),b.particles());
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void accordingCRshouldCreateMaxwellContainer(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        assertTrue(a.ok());
    }
    @Test
    public void accordingCRshouldCreateMaxwellContainerTest(){
        MaxwellContainer a = new MaxwellContainer(100,200,80,5,3,new java.util.ArrayList<>(java.util.Arrays.asList(  
        new java.util.ArrayList<>(java.util.Arrays.asList(-80, 100, 4, 1)),  
        new java.util.ArrayList<>(java.util.Arrays.asList(-30, 180, 2, 1)),  
        new java.util.ArrayList<>(java.util.Arrays.asList(-50, 90, 8, 7)),  
        new java.util.ArrayList<>(java.util.Arrays.asList(-67, 30, -1, 4)),  
        new java.util.ArrayList<>(java.util.Arrays.asList(-10, 120, 14, 16)),      
        new java.util.ArrayList<>(java.util.Arrays.asList(187, 8, -15, -20)),  
        new java.util.ArrayList<>(java.util.Arrays.asList(100, 190, 30, 40)),  
        new java.util.ArrayList<>(java.util.Arrays.asList(11, 22, 33, 30))  )) );
        assertTrue(a.ok());
    }
    @Test
    public void accordingCRshouldAddDemon(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addDemon(20);
        assertTrue(a.ok());
        
    }
    @Test
    public void accordingCRshouldDelDemon(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addDemon(20);
        a.delDemon(20);
        assertTrue(a.ok());
        
    }
    @Test
    public void accordingCRshouldAddParticle(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addParticle("green", true, 20, 20, 5, 4);
        assertTrue(a.ok());
        
    }
    @Test
    public void accordingCRshouldDelParticle(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addParticle("green", false, 20, 20, 5, 4);
        a.delParticle("green");
        assertTrue(a.ok());
        
    }

    @Test
    public void accordingCRshouldAddHole(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addHole(50, 50, 7);
        assertTrue(a.ok());
        
    }
    
    @Test
    public void accordingCRshouldStart(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addHole(100, 100, 7);
        a.addParticle("green", true, 20, 20, 5, 4);
        a.addDemon(20);
        a.start(20);
        assertTrue(a.ok());
        
    }
    @Test
    public void accordingCRshouldIsGoal(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addHole(100, 100, 7);
        a.addParticle("green", true, 20, 20, 5, 4);
        a.addDemon(20);
        a.start(20);
        a.isGoal();
        assertTrue(a.ok());
        
    }
    @Test
    public void accordingCRshouldShowDemons(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addDemon(20);
        a.demons();
        assertTrue(a.ok());
    }
    
    @Test
    public void accordingCRshouldShowParticles(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addParticle("green", true, 20, 20, 5, 4);
        a.particles();
        assertTrue(a.ok());
    }
    
    @Test
    public void accordingCRshouldShowHoles(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.addHole(100, 100, 7);
        a.holes();
        assertTrue(a.ok());
    }
    
    @Test
    public void accordingCRshouldMakeVisible(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.makeVisible();
        assertTrue(a.ok());
    }
    @Test
    public void accordingCRshouldMakeInvisible(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.makeInvisible();
        assertTrue(a.ok());
    }
    @Test
    public void accordingCRshouldFinish(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.finish();
        assertTrue(a.ok());
    }
    
    @Test
    public void accordingCRshouldGetWidthContainer(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.getWidthContainer();
        assertTrue(a.ok());
    }
    
    @Test
    public void accordingCRshouldGetHeightContainer(){
        MaxwellContainer a = new MaxwellContainer(100,200);
        a.getHeightContainer();
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
