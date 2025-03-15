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
    // EL CENTRO EN EL MAXWELL CONTAINER SIEMPRE VA A SER 350 - 400
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
    public void accordingCRShouldPutRightAParticle(){
        MaxwellContainer a = new MaxwellContainer();
        a.addParticle("red", true, 120, 60, 5, 5);
        a.makeVisible();
        // En nuestro tablero del centro es 350, para que sea la posición positiva en X debe ser 120 + 350.
        assertEquals(120+350,a.getParticulesChamber().get(0).getXPositionC());
    }
    @Test
    public void accordingCRShouldBeInTheRightPositionAfterMove(){
        //Al pasar un tick la particula se debe encontrar en una nueva posición es decir en la particula esperada.
        MaxwellContainer a = new MaxwellContainer();
        a.addParticle("red", true, 120, 60, 5, 5);
        a.start(1);
        a.addParticle("red", true, 125, 65, 5, 5);
        //a.makeVisible();
        assertEquals(a.particles().get(1),a.particles().get(0));
        a.addParticle("blue", false, 180, 80, -5, -5);
        a.start(1);
        a.addParticle("blue", false, 175, 75, -5,-5);
        assertEquals(a.particles().get(2),a.particles().get(3));
        a.addParticle("red", true, -180, 80, -5, 5);
        a.start(1);
        a.addParticle("red", true, -185, 85, -5, 5);
        assertEquals(a.particles().get(4),a.particles().get(5));
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
