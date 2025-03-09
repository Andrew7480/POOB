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
