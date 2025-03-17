import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class MaxwellContainerC1Test.
 *
 * @author  Andrés Cardozo && Tulio Riaño
 * @version (a version number or a date)
 */
public class MaxwellContestCTest
{
    /**
     * Default constructor for test class MaxwellContainerC1Test
     */
    public MaxwellContestCTest()
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
    public void accordingCRShouldDoTheRightAnswer(){
        int [][] vector2D = {{-3,1,2,0},{2,1,-4,0}};
        int [][] vector2 = {{2,1,-4,0},{-3,1,2,0}};
        int [][] vector2D1 = {{3,1,2,2},{-2,3,-2,-1},{3,2,1,-2},{-2,2,2,2}};
        int [][] vector2D2 = {{-1,1,2,2}};
        int [][] vector2D3 = {{1,1,-2,2}};
        int [][] vector2D4 = {{-2,1,1,-1}};
        int [][] vector2D5 = {{-3,4,-1,-1}};
        MaxwellContest b = new MaxwellContest();
        
        assertEquals(2.0,b.solve(4,7,1,1,1,vector2D));
        
        assertEquals(0.0,b.solve(4,7,1,1,1,vector2)); // ya estan en su chamber
        
        assertEquals(-1.0,b.solve(4,4,1,2,2,vector2D1));
        
        assertEquals(1.0,b.solve(4,4,2,1,0,vector2D2));
        
        assertEquals(1.0,b.solve(4,4,2,0,1,vector2D3));
        
        assertEquals(3.0,b.solve(4,4,1,1,0,vector2D4)); //despues de un bounce
        
        assertEquals(7.0,b.solve(5,4,1,1,0,vector2D5)); //despues de dos bounce
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
