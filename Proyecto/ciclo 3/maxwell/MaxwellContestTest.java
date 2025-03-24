

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class MaxwellContestTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MaxwellContestTest
{
    /**
     * Default constructor for test class MaxwellContestTest
     */
    public MaxwellContestTest()
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
    public void shouldDoTheRightAnswer(){
        int [][] vector2D = {{-3,1,2,0},{2,1,-4,0}};
        int [][] vector2 = {{2,1,-4,0},{-3,1,2,0}};
        int [][] vector2D1 = {{3,1,2,2},{-2,3,-2,-1},{3,2,1,-2},{-2,2,2,2}};
        int [][] vector2D2 = {{-1,1,2,2}};
        int [][] vector2D3 = {{1,1,-2,2}};
        int [][] vector2D4 = {{-2,1,1,-1}};
        int [][] vector2D5 = {{-3,4,-1,-1}};
        int [][] vector2D6 = {{}};
        MaxwellContest b = new MaxwellContest();
        
        assertEquals(2.0,b.solve(4,7,1,1,1,vector2D));
        
        assertEquals(0.0,b.solve(4,7,1,1,1,vector2)); // ya estan en su chamber
        
        assertEquals(-1.0,b.solve(4,4,1,2,2,vector2D1));
        
        assertEquals(1.0,b.solve(4,4,2,1,0,vector2D2));
        
        assertEquals(1.0,b.solve(4,4,2,0,1,vector2D3));
        
        assertEquals(3.0,b.solve(4,4,1,1,0,vector2D4)); //despues de un bounce
        
        assertEquals(7.0,b.solve(5,4,1,1,0,vector2D5)); //despues de dos bounce
        
    }
    @Test
    public void shouldNotDoTheRightAnswer(){
        int [][] vector2D = {{-3,1,2,0},{2,1,-4,0}};
        int [][] vector2 = {{2,1,-4,0},{-3,1,2,0}};
        int [][] vector2D1 = {{3,1,2,2},{-2,3,-2,-1},{3,2,1,-2},{-2,2,2,2}};
        int [][] vector2D2 = {{-1,1,2,2}};
        int [][] vector2D3 = {{1,1,-2,2}};
        int [][] vector2D4 = {{-2,1,1,-1}};
        int [][] vector2D5 = {{-3,4,-1,-1}};
        MaxwellContest b = new MaxwellContest();
        
        assertFalse(3.0 == b.solve(4,7,1,1,1,vector2D));
        
        assertFalse(8.0 == b.solve(4,7,1,1,1,vector2)); // ya estan en su chamber
        
        assertFalse(-12.0 == b.solve(4,4,1,2,2,vector2D1));
        
        assertFalse(31.0 == b.solve(4,4,2,1,0,vector2D2));
        
        assertFalse(52.0 == b.solve(4,4,2,0,1,vector2D3));
        
        assertFalse(1.0 == b.solve(4,4,1,1,0,vector2D4)); //despues de un bounce
        
        assertFalse(6.0 == b.solve(5,4,1,1,0,vector2D5)); //despues de dos bounce
    }
    @Test
    public void shouldDoTheRightSimulation(){
        MaxwellContest b = new MaxwellContest();
        int [][] vector2D = {{-3,1,2,0},{2,1,-4,0}};
        b.solve(4,7,1,1,1,vector2D);
        MaxwellContainer solution = new MaxwellContainer(4,7,1,1,1,vector2D);
        solution.makeVisible();
        assertTrue(solution.ok());
    }
    @Test
    public void shouldDoNothing(){
        int [][] vector2D6 = {{}};
        MaxwellContest b = new MaxwellContest();
        assertEquals(0.0,b.solve(5,4,1,0,0,vector2D6));
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
