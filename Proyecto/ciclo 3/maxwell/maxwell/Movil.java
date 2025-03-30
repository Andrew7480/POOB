package maxwell;


/**
 * It's a Movil Hole
 * 
 * @author Andrés Cardozo && Tulio Riaño 
 * @version (a version number or a date)
 */
public class Movil extends Hole
{
    /**
     * Constructor of Hole
     * @param int xPosition
     * @param int yPosition
     * @param int particles
     * @param boolean whereIs
       */
    public Movil(int xPosition, int yPosition, int particles,boolean whereIs){
        super(xPosition,yPosition, particles,whereIs);
        blackHole.changeColor("charcoal");
        state = MOVIL;
    }
    
    
}
