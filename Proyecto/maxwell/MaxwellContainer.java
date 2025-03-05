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
    private Rectangle chamberLeft = new Rectangle();
    private Rectangle chamberRight = new Rectangle();
    private ArrayList<Circle> particules = new ArrayList<>();
    private ArrayList<DemonFace> devils = new ArrayList<>();
    private int width;
    private int height;
    public MaxwellContainer(int h, int w){
        if (h < 0 && w < 0) theLastActionWasSuccess = false;
        chamberLeft.changeSize(h,w);
        chamberRight.changeSize(h,w);
        chamberRight.moveHorizontal(w+10);
        theLastActionWasSuccess = true;
        width = w;
        height = h;
    }
    public MaxwellContainer(int h, int w, int d, int b, int r, ArrayList<ArrayList<Integer>> particles){
        
    }
    public void addDemon(int d){
        boolean test = true;
        for (DemonFace de : devils){
            if(de.getD() == d){
                test = false;
                theLastActionWasSuccess = false;
            }
        }
        if (test == true){
            DemonFace demon = new DemonFace(chamberLeft.getXPosition(), chamberLeft.getYPosition(),width, height, d);
            devils.add(demon);
            theLastActionWasSuccess = true;
        }
    }
    public void delDemon(int d){
        for (int i = 0; i < devils.size(); i++){
            if(devils.get(i).getD() == d){
                devils.get(i).makeInvisible();
                devils.remove(i);
                theLastActionWasSuccess = true;
            }
        }
        
    }
    public void addParticle(String color, boolean isRed, int px, int py, int vx, int vy){
        // w ++ vx
        if (isRed) chamberLeft.addParticle(color,px,py,vx,vy);
        else{
            chamberRight.addParticle(color,px,py,vx,vy);
        }
        // w ++ vy
    }
    public void delParticle(String color){
        ArrayList<Circle> particules_left = chamberLeft.getParticules();
        ArrayList<Circle> particules_right = chamberRight.getParticules();
        particules_left.addAll(particules_right);
        for (int i = 0; i < particules_left.size(); i++){
            if (particules_left.get(i).getColor() == color){
                particules_left.get(i).makeInvisible();
                particules.remove(i);
                theLastActionWasSuccess = true;
            }
            else{
                theLastActionWasSuccess = false;
            }
        }
    }
    public void addHole(int px, int py, int particles){
        
    }
    public void start(int ticks){
        
    }
    public boolean isGoal(){
        return true;
    }
    public ArrayList<Integer> demons(){ // [0,1];
        ArrayList<Integer> posDemons = new ArrayList<>();
        for (int i = 0; i < devils.size(); i ++){
            posDemons.add(devils.get(i).getD());
        }
        Collections.sort(posDemons);
        return posDemons;
    }
    public ArrayList<ArrayList<Integer>> particles(){ // [[0,1]];
        return null;
    }
    public ArrayList<ArrayList<Integer>> holes(){
        return null;
    }
    /**
     * 
       */
    public void makeVisible(){
        chamberLeft.makeVisible();
        chamberRight.makeVisible();
    }
    /**
     * 
        */
    public void makeInvisible(){
        chamberLeft.makeInvisible();
        chamberRight.makeInvisible();
    }
    public void finish(){
        
    }
    public boolean ok(){
        return theLastActionWasSuccess;
    }
    public int sizeOf(){
        return devils.size();
    }
}
