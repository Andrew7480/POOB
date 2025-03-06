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
    public Hole(int xPosition, int yPosition, int width, int height){
        blackHole = new Rectangle(xPosition, yPosition, width/8, height/8);
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
    public void addParticles(int particles1){
        for (int i = 0; i < particles1; i ++){
            Particle p = new Particle("Yellow",0,0,0,0);
            particles.add(p);
        }
        
    }
}
