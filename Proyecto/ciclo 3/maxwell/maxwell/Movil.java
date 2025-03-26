package maxwell;


/**
 * Write a description of class movil here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Movil extends Hole
{
    public Movil(int xPosition, int yPosition, int particles,boolean whereIs){
        super(xPosition,yPosition, particles,whereIs);
        blackHole.changeColor("charcoal");
        state = MOVIL;
    }
    
    
}
