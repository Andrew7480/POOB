package maxwell;


/**
 * It's a EatParticle Hole
 * 
 * @author Andrés Cardozo && Tulio Riaño 
 * @version (a version number or a date)
 */
public class EatParticle extends Hole
{
    /**
     * Constructor of Hole
     * @param int xPosition
     * @param int yPosition
     * @param int particles
     * @param boolean whereIs
       */
    public EatParticle(int xPosition, int yPosition, int particles,boolean whereIs){
        super(xPosition,yPosition,particles,whereIs);
        blackHole.changeColor("dark aquamarine");
        state = EATPARTICLE;
    }
    
    @Override
    public void reduceMaxParticles(){
        maxParticles--;
        blackHole.plusSize();
        attractor += 5;
    }
    public int getwidth(){
        return blackHole.getwidth();
    }
}
