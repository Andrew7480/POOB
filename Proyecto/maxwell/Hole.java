import java.util.ArrayList;

/**
 * Write a description of class Holes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hole
{
    private Rectangle blackHole;
    private ArrayList<Particle> particles = new ArrayList<>();
    private int maxParticles;
    
    public Hole(int xPosition, int yPosition, int particles){
        blackHole = new Rectangle(xPosition, yPosition, 10, 10);
        maxParticles = particles;
        makeVisibleHole();
    }
    
    public void makeVisibleHole(){
        blackHole.makeVisible();
    }
    public void makeInvisibleHole(){
        blackHole.makeInvisible();
    }
    public void setPositionHole(int px, int py){
        blackHole.makeInvisible();
        blackHole.setPosition(px, py);
        blackHole.makeVisible();
    }
    public int getMaxParticles(){
        return maxParticles;
    }
    public int getParticlesSize(){
        return particles.size();   
    }
    public void addParticles(int particles1){ //mal
        
        //Particle p = new Particle("Yellow",0,0,0,0);
        return;    
        
        
    }
}
