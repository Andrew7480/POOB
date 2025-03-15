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
    @Test
    public void accordingCRShouldMakeTheRightConvertion(){
        MaxwellContainer a = new MaxwellContainer();
        int newX = a.convertionsBoardToCanvas(0,0).get(0);
        int newY = a.convertionsBoardToCanvas(0,0).get(1);
        //Deberia hacer la conversión con el objetivo de que se muestren las coordenadas del centro de canvas.
        assertEquals(350,newX);
        assertEquals(400,newY);
        newX = a.convertionsCanvasToBoard(350,400).get(0);
        newY = a.convertionsCanvasToBoard(350,400).get(1);
        assertEquals(0,newX);
        assertEquals(0,newY);
    }
    @Test
    public void accordingCRShouldBeInTheRightPositionTheDemon(){
        MaxwellContainer a = new MaxwellContainer();
        a.addDemons();
        DemonFace d = a.getDemonFaces().get(0);
        //Verifica la posición en la que se encuentra el demonio en el tablero de canvas.
        assertEquals(0,a.convertionsCanvasToBoard(d.getPosX(),d.getPosY()).get(0));
        assertEquals(100,a.convertionsCanvasToBoard(d.getPosX(),d.getPosY()).get(1));
    }
    @Test
    public void accordingCRShouldVerifyIfTheParticleIsInTheDemonPositionCanvas(){
        MaxwellContainer a = new MaxwellContainer();
        a.addParticles();
        a.addDemons();
        a.start(5);
        DemonFace d = a.getDemonFaces().get(0);
        Particle p = a.getParticulesChamber().get(0);
        assertEquals(p.getXPositionC(),d.getPosX());
        assertEquals(p.getYPositionC(),d.getPosY());
    }
    @Test
    public void accordingCRShouldVerifyIfTheParticleIsInTheDemonPositionBoard(){
        MaxwellContainer a = new MaxwellContainer();
        a.addParticles();
        a.addDemons();
        a.start(5);
        DemonFace d = a.getDemonFaces().get(0);
        Particle p = a.getParticulesChamber().get(0);
        assertEquals(a.convertionsCanvasToBoard(p.getXPositionC(),p.getYPositionC()),a.convertionsCanvasToBoard(d.getPosX(), d.getPosY()));
        assertEquals(a.convertionsCanvasToBoard(p.getXPositionC(),p.getYPositionC()),a.convertionsCanvasToBoard(d.getPosX(),d.getPosY()));
    }
    @Test
    public void accordingCRShouldDoTheBounceRight(){
        MaxwellContainer a = new MaxwellContainer();
        a.addParticles();
        a.start(2);
        Particle p = a.getParticulesChamber().get(1);
        assertEquals(a.convertionsCanvasToBoard(p.getXPositionC(), p.getYPositionC()).get(0),300);
        assertEquals(a.convertionsCanvasToBoard(p.getXPositionC(), p.getYPositionC()).get(1),100);
        a.start(1);
        assertEquals(a.convertionsCanvasToBoard(p.getXPositionC(), p.getYPositionC()).get(0),290);
        assertEquals(a.convertionsCanvasToBoard(p.getXPositionC(), p.getYPositionC()).get(1),110);
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
