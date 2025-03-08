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
        if (h < 0 ) theLastActionWasSuccess = false;
        chamber = new Chamber(h, 2*w);
        theLastActionWasSuccess = true;
        width = w;
        height = h;
        makeVisible();
    }
    
    public MaxwellContainer(){
        int h =200;
        int w=300;
        if (h < 0 ) theLastActionWasSuccess = false;
        
        chamber = new Chamber(h, 2*w);
        theLastActionWasSuccess = true;
        width = w;
        height = h;
        makeVisible();
    }
    
    
    public MaxwellContainer(int h, int w, int d, int b, int r, ArrayList<ArrayList<Integer>> particles){
        if (h < 0 ) theLastActionWasSuccess = false;
        chamber = new Chamber(h, 2*w);
        theLastActionWasSuccess = true;
        width = w;
        height = h;
        makeVisible();
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
        
    }
    
    public void addDemon(int d){
        if (d>=0) {
            theLastActionWasSuccess = chamber.addDemon(d);   
        }
        else {
            theLastActionWasSuccess =false;    
        }
    }
    public void addDemons(){
        theLastActionWasSuccess = chamber.addDemon(60);   
        theLastActionWasSuccess = chamber.addDemon(20);   
        theLastActionWasSuccess = chamber.addDemon(100);   
        theLastActionWasSuccess = chamber.addDemon(200);   
        theLastActionWasSuccess = chamber.addDemon(140);   
        
    }
    
    public void delDemon(int d){
        theLastActionWasSuccess = chamber.delDemon(d);
    }
    public void addParticle(String color, boolean isRed, int px, int py, int vx, int vy){
        if (py<0){
            theLastActionWasSuccess=false;
            return;    
        }
        
        chamber.addParticle(color,isRed,px,py,vx,vy);
    }
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
        theLastActionWasSuccess = chamber.addHole(px,py,particles);
    }
    public void addHoles(){
        theLastActionWasSuccess = chamber.addHole(100,100,7);
        theLastActionWasSuccess = chamber.addHole(-100,100,90);
        theLastActionWasSuccess = chamber.addHole(-20,180,17);
        theLastActionWasSuccess = chamber.addHole(-180,10,89);
        theLastActionWasSuccess = chamber.addHole(10,189,42);
    }
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
        return true;
    }
    public ArrayList<Integer> demons(){ // [0,1];
        return chamber.demons();
    }
    public ArrayList<ArrayList<Integer>> particles(){ // [[px,py,vx,vy]];
        //System.out.println(chamber.getParticlesInfp());
        return chamber.getParticlesInfo();
    }
    public ArrayList<ArrayList<Integer>> holes(){
        System.out.println(chamber.getHolesInfo());
        return chamber.getHolesInfo();
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
