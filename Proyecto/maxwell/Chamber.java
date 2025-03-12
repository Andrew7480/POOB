import java.util.ArrayList;
import java.util.*;
import javax.swing.JOptionPane;
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
    
    private boolean isVisible = false;
    //private boolean theLastActionWasSucces = false;
    /**
     * create a chamber
     * @param int h
     * @param int w
       */
    public Chamber(int h, int w){
        width=w;
        height=h;
        chamberForm = new Rectangle(h,w);
        ArrayList<Integer> position = getCenter();
        
        chamberForm.moveTo(position.get(0)-w/2, position.get(1)-h);
        chamberCenter = new Rectangle(chamberForm.getXPosition()-1+(w/2),chamberForm.getYPosition(),2,h,"green");
    }
    /**
     * Add particles to the chamber
     * @param String color
     * @param boolean isRed
     * @param int px
     * @param int py
     * @param int vx
     * @param int vy
       */
    public void addParticle(String color,boolean isRed, int px, int py, int vx, int vy){
        if (isRed){
            addParticleRed( color, px,  py,  vx,  vy); 
        }
        else{
            addParticleNoRed( color, px,  py,  vx,  vy);
        }
    }
    /**
     * In case the particle is red add to the left chamber
     * @param String color
     * @param int px
     * @param int py
     * @param int vx
     * @param int vy
       */
    private void addParticleRed(String color,int px, int py, int vx, int vy){
        int chamberXPos = chamberCenter.getXPosition()+1;
        int chamberYPos =chamberCenter.getYPosition()+height;
        int auxXMin=-width/2;  //-300 
        int auxXMax=0;         //0
        int auxYMin=0;
        int auxYMax= height; 
        if (px> auxXMin  && auxXMax>px  && py>auxYMin  && py< auxYMax  ){
            Particle c = new Particle(color, px + chamberXPos, chamberYPos - py, vx, vy, true);
            if(isVisible){
                c.makeVisibleParticle();
            }
            particules.add(c);
            
        }        
    }
    /**
     * In the other case creates the particle in the right chamber
     * @param String color
     * @param int px
     * @param int py
     * @param int vx
     * @param int vy
       */
    private void addParticleNoRed(String color,int px, int py, int vx, int vy){
        int chamberXPos = chamberCenter.getXPosition()+1;
        int chamberYPos =chamberCenter.getYPosition()+height;
        int auxXMin=0;  
        int auxXMax=width/2; 
        int auxYMin=0;
        int auxYMax= height; 
        
        if (px> auxXMin  && auxXMax>px  && py>auxYMin  && py< auxYMax  ){
            Particle c = new Particle(color, px+ chamberXPos, chamberYPos - py, vx, vy, false);
            if(isVisible){
                c.makeVisibleParticle();
            }
            particules.add(c);
        }
    }
    /**
     * deletes the particules depends of the color
     * @param String color
       */
    public boolean delParticle(String color){
        boolean theLastActionWasSuccess = false;
        for (int i = 0; i < particules.size(); i++){
            if (particules.get(i).getColor() == color){
                particules.get(i).makeInvisibleParticle();
                theLastActionWasSuccess = true;
            }
        }
        return theLastActionWasSuccess;
    }
    
    /**
     * if the position is no repeated creates a new demon in d
     * @param int d
       */
    public boolean addDemon(int d){ // va de 0 a h
        for (DemonFace de : devils){
            if(de.getPosD() == d){
                if (!isVisible){
                    JOptionPane.showMessageDialog(null, "There is already a demon in that position");
                }
                return false;
            }
        }
        DemonFace demon = new DemonFace(chamberCenter.getXPosition(), chamberCenter.getYPosition(),width, height, d);
        devils.add(demon);
        if(isVisible){
                demon.makeVisible();
            }
        return true;
    }
    /**
     * deletes the demon in the position d
     * @param int d
       */
    public boolean delDemon(int d){
        boolean theLastActionWasSucces = false;
        for (int i = 0; i < devils.size(); i++){
            if(devils.get(i).getPosD() == d){
                devils.get(i).makeInvisible();
                devils.remove(i);
                theLastActionWasSucces = true;
            }
        }
        return theLastActionWasSucces;
    }
    /**
     * add hole in a certain position return if its possible to add
     * @param int px
     * @param int py
     * @param int particles
       */
    public boolean addHole(int px, int py, int particles){
        int chamberXPos = chamberCenter.getXPosition()+1;
        int chamberYPos =chamberCenter.getYPosition()+height;
        int auxXMin=-width/2;  //-(chamberXPos-(width/2));
        int auxXMax=width/2; //chamberXPos+(width/2); 
        int auxYMin=0;
        int auxYMax= height;  // -(chamberYPos-height-chamberYPos); 
        if (px> auxXMin  && auxXMax>px  && py>auxYMin  && py< auxYMax  ){ 
            px = chamberXPos + px; 
            py = chamberYPos - py;
            Hole h = new Hole(px,py, particles);
            if (isVisible){
                h.makeVisibleHole();
            }
            holes.add(h);
            return true;
        }
        return false;
    }
    /**
     * return the array list of integers in order of lowest to highest
       */
    public ArrayList<Integer> demons(){ // [0,1];
        ArrayList<Integer> posDemons = new ArrayList<>();
        for (int i = 0; i < devils.size(); i ++){
            posDemons.add(devils.get(i).getPosD());
        }
        Collections.sort(posDemons);
        return posDemons;
    }
    /**
     * return the total amount of the particles
       */
    public ArrayList<Particle> getParticules(){
        return particules;
    }
    /**
     * change size of the chamber
     * @param int newHeight
     * @param int newWidth
       */
    public void changeSize(int newHeight, int newWidth){
        chamberForm.changeSize(newHeight,newWidth);
    }
    /**
     * make visible everything in the chamber
       */
    public void makeVisible(){
        chamberForm.makeVisible();
        chamberCenter.makeVisible();
        for (Particle h : particules){
            h.makeVisibleParticle();
        }
        for (DemonFace c : devils){
            c.makeVisible();
        }
        for (Hole o : holes){
            o.makeVisibleHole();
        }
        isVisible = true;
    }
    /**
     * make invisible everything in the chamber
       */
    public void makeInvisible(){
        for (Particle h : particules){
            h.makeInvisibleParticle();
        }
        for (DemonFace c : devils){
            c.makeInvisible();
        }
        for (Hole o : holes){
            o.makeInvisibleHole();
        }
        chamberForm.makeInvisible();
        chamberCenter.makeInvisible();
        isVisible = false;
    }
    /**
     * return the amount of demons in the maxwell container
       */
    public int sizeOf(){
        return devils.size();
    }
    /**
     * return the center XY of the chamber
       */
    public ArrayList<Integer> getCenter(){
        return chamberForm.getCenterXY();
    }
    /**
     * show the center of the chamber
       */
    public void showCenter(){
        chamberCenter.showCenter();
    }
    /**
     * makes the movement of a certain particle
     * @param Particle p
       */
    public void movement(Particle p){
        int chamberXPos = chamberCenter.getXPosition()+1;
        int chamberYPos =chamberCenter.getYPosition()+height;
        int positionEsperadaX = p.getXPositionC()- chamberXPos;
        int positionEsperadaY = chamberYPos - p.getYPositionC() ;
        boolean verify = verifyLimits(p, positionEsperadaX + p.getVelocityX(), positionEsperadaY + p.getVelocityY()) && (positionEsperadaY + p.getVelocityY() >= 0);
        if (verify){
            p.moveHorizontal(p.getVelocityX());
            p.moveVertical(-p.getVelocityY());
        }
        
        if (!verify){
            bounce(p,positionEsperadaX+ p.getVelocityX(),positionEsperadaY+ p.getVelocityY());
            p.moveHorizontal(p.getVelocityX());
            p.moveVertical(-p.getVelocityY());
        }
    }
    /**
     * verify limits of the movement of each particle
     * @param Particle p
     * @param int x
     * @param int y
       */
    private boolean verifyLimits(Particle p, int x, int y){
        int auxXMin;
        int auxXMax;
        int auxYMin = 0;
        int auxYMax = height;
        /*
        if (isInDemonPos(x,y)){
            p.changeIsLeft();
            return true;
        }
        */
        
        if (p.getIsLeft()){
            auxXMin=-width/2;
            auxXMax=0;
            return  x > auxXMin  && x < auxXMax  && y > auxYMin  && y < auxYMax;
        }        
        if (!p.getIsLeft()){
            auxXMin=0;  
            auxXMax=width/2; 
            return x > auxXMin  && x < auxXMax  && y > auxYMin  && y < auxYMax;

        }
        return false;
    }
    /**
     * if bounce is in the left or right
     * @param Particle p
     * @param int x
     * @param int y
       */
    private void bounce(Particle p, int x, int y){
        if (p.getIsLeft()){
            bounceLeft(p,x,y);
        }
        if (!p.getIsLeft()){
            bounceRight(p,x,y);
        }
    }
    /**
     * if bounce is in the right, evaluates the possible cases and make the respective bounce
     * @param Particle p
     * @param int espeX
     * @param int espeY
       */
    private void bounceRight(Particle p,int espeX, int espeY){
        int velociX = p.getVelocityX();
        int velociY = p.getVelocityY();
        if (espeX >= width/2){ // PARED
            if (velociX > 0 && velociY > 0){ 
                p.setVelocityX(p.getVelocityX()*(-1)); // termina siendo la X negativa
            } // O
            else if (velociX > 0 && velociY < 0){
                p.setVelocityX(p.getVelocityX()*(-1)); // termina siendo la x positiva
            }
        }
        else if (espeX <= 0 && (espeY <= height && espeY >= 0)){ // PAREDES
            if (velociX < 0 && velociY < 0){
                p.setVelocityX(p.getVelocityX()*(-1)); // termina siendo la X Positiva Y Negativa
            }
            else if (velociX < 0 && velociY > 0){
                p.setVelocityX(p.getVelocityX()*(-1)); // termina siendo X positiva
            }
        }
        else if ((espeY < 0 || espeY >= height) && (espeX >= 0 && espeX <= width/2)){
            if (velociX < 0 && velociY < 0){
                p.setVelocityY(p.getVelocityY()*(-1)); // termina siendo la y positiva
            }
            else if (velociX > 0 && velociY < 0){
                p.setVelocityY(p.getVelocityY()*(-1)); // termina siendo la x positiva
            }
            else if (velociX > 0 && velociY > 0){
                p.setVelocityY(p.getVelocityY()*(-1)); // termina siendo la y negativa
            }
            else if (velociX < 0 && velociY > 0){
                p.setVelocityY(p.getVelocityY()*(-1)); // termina siendo Y POSITIVA    
            }
        }
                
    }
    /**
     * if the bounce is in the corner makes the right movement
     * @param Particle p
     * @param int x
     * @param int y
       */
    private void bounceInCorner(Particle p, int x,int y){
        p.setVelocityX(p.getVelocityX()*(-1));
        p.setVelocityY(p.getVelocityY()*(-1));
    }
    /**
     * if the bounce is in the left evaluates the cases and make the movement
     * @param Particle p
     * @param int espeX
     * @param int espeY
       */
    private void bounceLeft(Particle p,int espeX, int espeY){
        int velociX = p.getVelocityX();
        int velociY = p.getVelocityY();
        if ((espeX == 0 || espeX == -width/2) && (espeY == 0 || espeY == height)){
            bounceInCorner(p, espeX,espeY);
        }
        else if ((espeX > 0 ) && (espeY > height)){
            bounceInCorner(p, espeX,espeY);
        }
        else if ((espeX > 0 ) && (espeY < 0)){
            bounceInCorner(p, espeX,espeY);
        }
        else if ((espeX < -width/2 ) && (espeY > height)){
            bounceInCorner(p, espeX,espeY);
        }
        else if ((espeX < -width/2 ) && (espeY < 0)){
            bounceInCorner(p, espeX,espeY);
        }
        else if (espeX <= -width/2){ // PARED
             if (velociX< 0 && velociY < 0){
                p.setVelocityX(p.getVelocityX()*(-1)); // termina siendo la X Positiva Y Negativa
            }
            else if (velociX < 0 && velociY > 0){
                p.setVelocityX(p.getVelocityX()*(-1)); // termina siendo X positiva
            }
        }
        
        else if (espeX >= 0 && (espeY <= height && espeY >= 0)){ // PAREDES
            if (velociX > 0 && velociY > 0){ 
                p.setVelocityX(p.getVelocityX()*(-1)); // termina siendo la X negativa
            }
            else if (velociX > 0 && velociY < 0){
                p.setVelocityX(p.getVelocityX()*(-1)); // termina siendo la x positiva
            }
        }
        else if ((espeY < 0 || espeY >= height) && (espeX <= 0 && espeX >= -width/2)){
            if (velociX > 0 && velociY > 0){
                p.setVelocityY(p.getVelocityY()*(-1)); // termina siendo la y negativa
            }
            else if (velociX < 0 && velociY < 0){
                p.setVelocityY(p.getVelocityY()*(-1)); // termina siendo la y positiva
            }
            else if (velociX > 0 && velociY < 0){
                p.setVelocityY(p.getVelocityY()*(-1)); // termina siendo la y positiva
            }
            else if (velociX < 0 && velociY > 0){
                p.setVelocityY(p.getVelocityY()*(-1)); // termina siendo Y POSITIVA    
            }
        }
        
    }
    /**
     * return the array list of the particles with a certain information (px,py,vx,vy)
       */
    public ArrayList<ArrayList<Integer>> getParticlesInfo(){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(Particle p :particules){
            ArrayList <Integer> info = new ArrayList<>();
            info.add(p.getXPositionC());
            info.add(p.getYPositionC());
            info.add(p.getVelocityX());
            info.add(p.getVelocityY());
            list.add(info);
        }
        return list;
    }
    /**
     * return the matrix of holes with a specific information (px, py, particles)
       */
    public ArrayList<ArrayList<Integer>> getHolesInfo(){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(Hole h :holes){
            ArrayList <Integer> info = new ArrayList<>();
            info.add(h.getXPosition());
            info.add(h.getYPosition());
            info.add(h.getMaxParticles());
            list.add(info);
        }
        return list;
    }
    public boolean getIsVisible(){
        return isVisible;
    }
    
    private boolean isInDemonPos(int x, int y){
        for (DemonFace d:devils){
            if (x==0){
                if (d.getPosD()==y){
                    return true;
                }
            }
        }
        return false;
    }
    
}
