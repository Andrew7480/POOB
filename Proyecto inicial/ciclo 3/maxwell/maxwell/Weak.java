package maxwell;


/**
 * Its a Weak DemonFace
 * 
 * @author Andrés Cardozo && Tulio Riaño 
 * @version (a version number or a date)
 */
public class Weak extends DemonFace
{
    /**
     * Constructor of demon face weak
     * @param int xPosition
     * @param int yPosition
     * @param int width
     * @param int d
       */
    public Weak(int newXPosition, int newYPosition, int width, int height, int d){
        super(newXPosition,newYPosition, width, height, d);
        faceDemon.changeColor("olive");
        state = WEAK;
    }
}
