import java.util.*;
import javax.swing.JOptionPane;
/**
 * Write a description of class MaxwellContainer here.
 * 
 * @author Andrés Cardozo && Tulio Riaño
 * @version (a version number or a date)
 */
public class MaxwellContainer
{
    private boolean theLastActionWasSuccess;
    
    private Chamber chamber;
    private int width;
    private int height;
    private int xPosition;
    private int yPosition;
    /**
     * Constructor of MaxwellContainer
     * @param int h
     * @param int w
       */
    public MaxwellContainer(int h, int w){
        if (h < 0 ||  w <0){
            theLastActionWasSuccess = false;
            JOptionPane.showMessageDialog(null, "Negative values ");
            return;
        }
        chamber = new Chamber(h, 2*w);
        theLastActionWasSuccess = true;
        width = w;
        height = h;
        makeInvisible();
        //makeVisible();
    }
    //PARA LA SUSTENTACIÓN
    public MaxwellContainer(){
        int h =200;
        int w=300;
        chamber = new Chamber(h, 2*w);
        theLastActionWasSuccess = true;
        width = w;
        height = h;
        //makeInvisible();
        //makeVisible();
    }
    /**
     * Constructor of MaxwellContainer
     * @param int h
     * @param int w
     * @param int d
     * @param int b
     * @param int r
     * @param ArrayList<ArrayList<Integer>> particles
       */
    public MaxwellContainer(int h, int w, int d, int b, int r, ArrayList<ArrayList<Integer>> particles){
        if (h < 0 || w<0) {
            theLastActionWasSuccess = false;
            JOptionPane.showMessageDialog(null, "Negative values ");
            return;
        }
        chamber = new Chamber(h, 2*w);
        theLastActionWasSuccess = true;
        width = w;
        height = h;
        //makeVisible();
        addDemon(d);
        int total = b+r;
        
        
        for (int i=0;i<particles.size();i++){
            if (total <=r){
                addParticle("blue", false, particles.get(i).get(0),particles.get(i).get(1),particles.get(i).get(2), particles.get(i).get(3));
                continue;
            }
            addParticle("red", true, particles.get(i).get(0),particles.get(i).get(1),particles.get(i).get(2), particles.get(i).get(3));
            total -=1;
        }
        makeInvisible();
    }
    /**
     * add demons in the container
     * @param int d
       */
    public void addDemon(int d){
        if (d>=0) {
            theLastActionWasSuccess = chamber.addDemon(d);
            return;
        }
        JOptionPane.showMessageDialog(null, "d can't be negative");
        theLastActionWasSuccess =false;    
        
    }
    // ESTO ES PARA LA SUSTENTACIÓN
    public void addDemons(){
        theLastActionWasSuccess = chamber.addDemon(60);   
        theLastActionWasSuccess = chamber.addDemon(70);   
        theLastActionWasSuccess = chamber.addDemon(80);   
        theLastActionWasSuccess = chamber.addDemon(20);   
        theLastActionWasSuccess = chamber.addDemon(100);  
        theLastActionWasSuccess = chamber.addDemon(110);
        theLastActionWasSuccess = chamber.addDemon(120);   
        theLastActionWasSuccess = chamber.addDemon(200);   
        theLastActionWasSuccess = chamber.addDemon(140);   
        theLastActionWasSuccess = chamber.addDemon(150);   
        
    }
    /**
     * delete demons of the container
     * @param int d
       */
    public void delDemon(int d){
        theLastActionWasSuccess = chamber.delDemon(d);
    }
    /**
     * add particles depends of is red or not to the container
     * @param String color
     * @param boolean isRed
     * @param int px
     * @param int py
     * @param int vx
     * @param int vy
       */
    public void addParticle(String color, boolean isRed, int px, int py, int vx, int vy){
        if (py<0){
            theLastActionWasSuccess = false;
            JOptionPane.showMessageDialog(null, "py can't be negative");
            return;    
        }
        
        chamber.addParticle(color,isRed,px,py,vx,vy);
        theLastActionWasSuccess=true;
    }
    // ESTO ES PARA LA SUSTENTACIÓN
    public void addParticles(){
        boolean isRed=true;
        chamber.addParticle("red",isRed,-100,150,5,5);
        chamber.addParticle("blue",isRed,-180,40,-10,-10);
        chamber.addParticle("yellow",isRed,-80,40,-4,9);
        chamber.addParticle("orange",isRed,-20,180,9,-4);
        chamber.addParticle("green",isRed,-200,199,-10,10);
        chamber.addParticle("red",!isRed,-100,150,5,5);
        chamber.addParticle("blue",!isRed,180,40,-10,-10);
        chamber.addParticle("yellow",!isRed,80,40,-4,9);
        chamber.addParticle("orange",!isRed,20,180,9,-4);
        chamber.addParticle("green",!isRed,200,199,-10,10);
        chamber.addParticle("green",!isRed,1,199,-1,1);
        
        chamber.addParticle("blue",!isRed,5,65,-5,-5);
        chamber.addParticle("blue",!isRed,10,65,-5,-5);
        chamber.addParticle("blue",!isRed,5,55,5,5);
    }
    /**
     * deletes the particules depends of the color
     * @param String color
       */
    public void delParticle(String color){
        theLastActionWasSuccess = chamber.delParticle(color);
    }
    /**
     * add holes to the container
     * @param int px
     * @param int py
     * @param int particles
       */
    public void addHole(int px, int py, int particles){
        if (py<0){
            theLastActionWasSuccess=false;
            JOptionPane.showMessageDialog(null, "py can't be negative");
            return;
        }        
        theLastActionWasSuccess = chamber.addHole(px,py,particles);
    }
    
    // ESTO ES PARA LA SUSTENTACIÓN
    public void addHoles(){
        theLastActionWasSuccess = chamber.addHole(100,100,7);
        theLastActionWasSuccess = chamber.addHole(-100,100,90);
        theLastActionWasSuccess = chamber.addHole(-20,180,17);
        theLastActionWasSuccess = chamber.addHole(-180,10,89);
        theLastActionWasSuccess = chamber.addHole(10,189,42);
    }
    /**
     * start the simulation ticks time
     * @param int ticks
       */
    public void start(int ticks){
        //metodo verificar?
        //check hole - demons
        //delay -- wait -- thread.sleep(1).
        ArrayList<Particle> p = chamber.getParticules();
        for (int i = 0; i < ticks; i++){
            for (Particle h : p){
                h.makeInvisibleParticle();
                chamber.movement(h);
                h.makeVisibleParticle();
            }
        }
        isGoal();
    }
    public boolean isGoal(){
        ArrayList<Particle> particle = chamber.getParticules();
        for(Particle par: particle) {
            if (par.getIsLeft()){
                if(!par.getColorForBoolean().equalsIgnoreCase("red")){
                    return false;
                }
            }
            if (!par.getIsLeft()){
                if(!par.getColorForBoolean().equalsIgnoreCase("azul")){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * return the positions of the demons from lowest to highest.
       */
    public ArrayList<Integer> demons(){ // [0,1];
        return chamber.demons();
    }
    /**
     * return the positions and velocity of each particle [px,py,vx,vy] in order from lowest to highest.
       */
    public ArrayList<ArrayList<Integer>> particles(){ // [[px,py,vx,vy]];
        ArrayList<ArrayList<Integer>> particlesInfo = chamber.getParticlesInfo();
        Collections.sort(particlesInfo, new Comparator<ArrayList<Integer>>(){
            @Override
            public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2){
                return list1.get(0).compareTo(list2.get(0));
            }
        });
        //System.out.println(particlesInfo);
        return particlesInfo;
    }
    /**
     * return the positions and particules remains [px, py, particles] in order from lowest to highest
       */
    public ArrayList<ArrayList<Integer>> holes(){
        ArrayList<ArrayList<Integer>> holesInfo = chamber.getHolesInfo();
        Collections.sort(holesInfo, new Comparator<ArrayList<Integer>>(){
            @Override
            public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2){
                return list1.get(0).compareTo(list2.get(0));
            }
        });
        //System.out.println(holesInfo);
        theLastActionWasSuccess=true;
        return holesInfo;
    }
    /**
     * makes visible the Maxwell Container
       */
    public void makeVisible(){
        chamber.makeVisible();
        theLastActionWasSuccess=true;
    }
    /**
     * makes invisible the Maxwell Container
        */
    public void makeInvisible(){
        chamber.makeInvisible();
        theLastActionWasSuccess=true;
    }
    /**
     * finish the simulation of maxwell container
       */
    public void finish(){
        Canvas.getCanvas().close();
        //theLastActionWasSuccess=true;
    }
    /**
     * determine if the action was success
       */
    public boolean ok(){
        return theLastActionWasSuccess;
    }
    /**
     * gives the center of the chamber
       */
    public void showCenter(){
        chamber.showCenter();
        theLastActionWasSuccess=true;
    }
    /**
     * gives the width of the container
       */
    public int getWidthContainer(){
        theLastActionWasSuccess=true;
        return width;
    }
    /**
     * gives the height of the container
       */
    public int getHeightContainer(){
        theLastActionWasSuccess=true;
        return height;
    }
    @Override
    public boolean equals(Object obj){
        return equals((MaxwellContainer) obj);
    }
    /**
     * compare equals
       */
    public boolean equals(MaxwellContainer a){
        return a.getWidthContainer() == width && a.getHeightContainer() == height;
    }
}
