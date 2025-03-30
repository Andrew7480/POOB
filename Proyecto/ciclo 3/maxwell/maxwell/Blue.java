package maxwell;


/**
 * Its a Blue DemonFace
 * 
 * @author Andrés Cardozo && Tulio Riaño 
 * @version (a version number or a date)
 */
public class Blue extends DemonFace
{   
    /**
     * Constructor of demon face weak
     * @param int xPosition
     * @param int yPosition
     * @param int width
     * @param int d
       */
    public Blue(int newXPosition, int newYPosition, int width, int height, int d){
        super(newXPosition,newYPosition, width, height, d);
        faceDemon.changeColor("neon blue");
        state = BLUE;
    }
}
