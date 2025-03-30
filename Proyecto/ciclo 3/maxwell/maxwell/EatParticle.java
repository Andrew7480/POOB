package maxwell;


/**
 * Write a description of class EatParticles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EatParticle extends Hole
{
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
}
