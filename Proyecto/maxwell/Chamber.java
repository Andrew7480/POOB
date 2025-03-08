import java.util.ArrayList;
import java.util.*;
/**
 * Write a description of class Chamber here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Chamber
{
    private Rectangle chamberForm;
    private Rectangle chamberCenter;
    private ArrayList<Particle> particules = new ArrayList<>();
    private ArrayList<DemonFace> devils = new ArrayList<>();
    private ArrayList<Hole> holes = new ArrayList<>();
    private int width;
    private int height;
    //private boolean theLastActionWasSucces = false;
    
    public Chamber(int h, int w){
        width=w;
        height=h;
        chamberForm = new Rectangle(h,w);
        ArrayList<Integer> position = getCenter();
        
        chamberForm.moveTo(position.get(0)-w/2, position.get(1)-h);
        chamberCenter = new Rectangle(chamberForm.getXPosition()-1+(w/2),chamberForm.getYPosition(),2,h,"green");
    }
    

    
    public void addParticle(String color,boolean isRed, int px, int py, int vx, int vy){
        if (isRed){
            addParticleRed( color, px,  py,  vx,  vy); 
        }
        else{
            addParticleNoRed( color, px,  py,  vx,  vy);
        }
    }
    
    public void addParticleRed(String color,int px, int py, int vx, int vy){
        int chamberXPos = chamberCenter.getXPosition()+1;
        int chamberYPos =chamberCenter.getYPosition()+height;
        int auxXMin=-width/2;  //-300 
        int auxXMax=0;         //0
        int auxYMin=0;
        int auxYMax= height; 
        
        
        if (px> auxXMin  && auxXMax>px  && py>auxYMin  && py< auxYMax  ){
            Particle c = new Particle(color, px + chamberXPos, chamberYPos - py, vx, vy, true);
            c.makeVisibleParticle();
            particules.add(c);
            
        }
       
        
    }
    public void addParticleNoRed(String color,int px, int py, int vx, int vy){
        int chamberXPos = chamberCenter.getXPosition()+1;
        int chamberYPos =chamberCenter.getYPosition()+height;
        int auxXMin=0;  
        int auxXMax=width/2; 
        int auxYMin=0;
        int auxYMax= height; 
        
        if (px> auxXMin  && auxXMax>px  && py>auxYMin  && py< auxYMax  ){
            Particle c = new Particle(color, px+ chamberXPos, chamberYPos - py, vx, vy, false);
            c.makeVisibleParticle();
            particules.add(c);
        }
       
        
    }
    
    public boolean addDemon(int d){ // va de 0 a h
        for (DemonFace de : devils){
            if(de.getD() == d){
                
                return false;
            }
        }
        DemonFace demon = new DemonFace(chamberCenter.getXPosition(), chamberCenter.getYPosition(),width, height, d);
        devils.add(demon);  
        return true;
    }
    public boolean delDemon(int d){
        boolean theLastActionWasSucces = false;
        for (int i = 0; i < devils.size(); i++){
            if(devils.get(i).getD() == d){
                devils.get(i).makeInvisible();
                devils.remove(i);
                theLastActionWasSucces = true;
            }
        }
        return theLastActionWasSucces;
    }
    public boolean addHole(int px, int py, int particles){
        int chamberXPos = chamberCenter.getXPosition()+1;
        int chamberYPos =chamberCenter.getYPosition()+height;
        int auxXMin=-width/2;  //-(chamberXPos-(width/2));
        int auxXMax=width/2; //chamberXPos+(width/2); 
        //System.out.println(auxXMin+" "+ auxXMax);
        int auxYMin=0;
        int auxYMax= height;  // -(chamberYPos-height-chamberYPos); 
        if (px> auxXMin  && auxXMax>px  && py>auxYMin  && py< auxYMax  ){ 
            px = chamberXPos + px; 
            py = chamberYPos - py; 
            Hole h = new Hole(px,py, particles);
            h.makeVisibleHole();
            holes.add(h);
            return true;
        }
        //System.out.println("salto");
        return false;
    }
    public ArrayList<Integer> demons(){ // [0,1];
        ArrayList<Integer> posDemons = new ArrayList<>();
        for (int i = 0; i < devils.size(); i ++){
            posDemons.add(devils.get(i).getD());
        }
        Collections.sort(posDemons);
        return posDemons;
    }
    
    public ArrayList<Particle> getParticules(){
        return particules;
    }
    public void changeSize(int newHeight, int newWidth){
        chamberForm.changeSize(newHeight,newWidth);
    }
    public void makeVisible(){
        chamberForm.makeVisible();
        chamberCenter.makeVisible();
        //chamberForm.showCenter();
    }
    public void makeInvisible(){
        chamberForm.makeInvisible();
        chamberCenter.makeInvisible();
    }
    public int sizeOf(){
        return devils.size();
    }
    public ArrayList<Integer> getCenter(){
        return chamberForm.getCenterXY();
    }
    public void showCenter(){
        chamberCenter.showCenter();
    }
    
}
