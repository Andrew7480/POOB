import java.util.*;
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
    
    public MaxwellContainer(int h, int w){
        if (h < 0 && w < 0) theLastActionWasSuccess = false;
        chamber = new Chamber(h, 2*w);
        theLastActionWasSuccess = true;
        width = w;
        height = h;
        makeVisible();
    }
    
    public MaxwellContainer(){
        int h =200;
        int w=300;
        if (h < 0 && w < 0) theLastActionWasSuccess = false;
        
        chamber = new Chamber(h, 2*w);
        theLastActionWasSuccess = true;
        width = w;
        height = h;
        makeVisible();
    }
    
    
    public MaxwellContainer(int h, int w, int d, int b, int r, ArrayList<ArrayList<Integer>> particles){
        
    }
    
    public void addDemon(int d){
        if (d>=0) {
            theLastActionWasSuccess = chamber.addDemon(d);   
        }
        else {
            theLastActionWasSuccess =false;    
        }
    }
    
    public void delDemon(int d){
        theLastActionWasSuccess = chamber.delDemon(d);
    }
    
    public void addParticle(String color, boolean isRed, int px, int py, int vx, int vy){
        // w ++ vx
        if (py<0){
            theLastActionWasSuccess=false;
            return;    
        }
        
        chamber.addParticle(color,isRed,px,py,vx,vy);
    }
    public void delParticle(String color){
        ArrayList<Particle> particules_left = chamber.getParticules();
        for (int i = 0; i < particules_left.size(); i++){
            if (particules_left.get(i).getColor() == color){
                particules_left.get(i).makeInvisibleParticle();
                theLastActionWasSuccess = true;
            }
            else{
                theLastActionWasSuccess = false;
            }
        }
    }    
    public void addHole(int px, int py, int particles){
        if (py<0){
            theLastActionWasSuccess=false;
            return;
        }        
        //System.out.println("vamos bien??");
        theLastActionWasSuccess = chamber.addHole(px,py,particles);
    }
    public void start(int ticks){
        //metodo verificar?
        //check hole - demons
        //delay -- wait -- thread.sleep(1).
        ArrayList<Particle> p = chamber.getParticules();
        for (int i = 0; i < ticks; i++){
            for (Particle h : p){
                chamber.movement(h);
            }
        }
    }
    public boolean isGoal(){
        return true;
    }
    public ArrayList<Integer> demons(){ // [0,1];
        return chamber.demons();
    }
    public ArrayList<ArrayList<Integer>> particles(){ // [[px,py,vx,vy]];
        return null;
    }
    public ArrayList<ArrayList<Integer>> holes(){
        return null;
    }
    /**
     * 
       */
    public void makeVisible(){
        chamber.makeVisible();
    }
    /**
     * 
        */
    public void makeInvisible(){
        chamber.makeInvisible();
    }
    public void finish(){
        
    }
    public boolean ok(){
        return theLastActionWasSuccess;
    }
    public void showCenter(){
        chamber.showCenter();   
    }
}
