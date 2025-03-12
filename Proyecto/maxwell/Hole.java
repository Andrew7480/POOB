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
    /**
     * Constructor of Hole
     * @param int xPosition
     * @param int yPosition
     * @param int particles
       */
    public Hole(int xPosition, int yPosition, int particles){
        blackHole = new Rectangle(xPosition, yPosition, 10, 10);
        maxParticles = particles;
        
    }
    /**
     * Method that make visible of hole
       */
    public void makeVisibleHole(){
        blackHole.makeVisible();
    }
    /**
     * Method that make invisible the hole
       */
    public void makeInvisibleHole(){
        blackHole.makeInvisible();
    }
    /**
     * Method that set the position of the hole
     * @param int px
     * @param int py
       */
    public void setPositionHole(int px, int py){
        blackHole.makeInvisible();
        blackHole.setPosition(px, py);
        blackHole.makeVisible();
    }
    /**
     * return the max particles that can have the particle
       */
    public int getMaxParticles(){
        return maxParticles;
    }
    /**
     * return the size of particles
       */
    public int getParticlesSize(){
        return particles.size();   
    }
    /**
     * return the x position of the hole
       */
    public int getXPosition(){
        return blackHole.getXPosition();
    }
    /**
     * return the y position of the hole
       */
    public int getYPosition(){
        return blackHole.getYPosition();
    }
}
