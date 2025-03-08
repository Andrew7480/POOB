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
            if(de.getPosD() == d){
                
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
            if(devils.get(i).getPosD() == d){
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
        return false;
    }
    public ArrayList<Integer> demons(){ // [0,1];
        ArrayList<Integer> posDemons = new ArrayList<>();
        for (int i = 0; i < devils.size(); i ++){
            posDemons.add(devils.get(i).getPosD());
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
        for (Particle h : particules){
            h.makeVisibleParticle();
        }
        for (DemonFace c : devils){
            c.makeVisible();
        }
        for (Hole o : holes){
            o.makeVisibleHole();
        }
    }
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
    public boolean verifyLimits(Particle p, int x, int y){
        int auxXMin;
        int auxXMax;
        int auxYMin = 0;
        int auxYMax = height;
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
    
    public void bounce(Particle p, int x, int y){
        // SOBREPASAR
        if (p.getIsLeft()){
            
            bounceLeft(p,x,y);
        }
        if (!p.getIsLeft()){
            bounceRight(p,x,y);
        }
    }
    public void bounceRight(Particle p,int espeX, int espeY){
        int velociX = p.getVelocityX();
        int velociY = p.getVelocityY();
        if (espeX >= width/2){ // PARED
            if (velociX > 0 && velociY > 0){ 
                p.setVelocityX(p.getVelocityX()*(-1)); // termina siendo la X negativa
            }
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
    public void bounceInCorner(Particle p, int x,int y){
        p.setVelocityX(p.getVelocityX()*(-1));
        p.setVelocityY(p.getVelocityY()*(-1));
    }
    public void bounceLeft(Particle p,int espeX, int espeY){
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
            if (velociX > 0 && velociY > 0){ 
                p.setVelocityX(p.getVelocityX()*(-1)); // termina siendo la X negativa
            }
            else if (velociX< 0 && velociY < 0){
                p.setVelocityX(p.getVelocityX()*(-1)); // termina siendo la X Positiva Y Negativa
            }
            else if (velociX > 0 && velociY < 0){
                p.setVelocityY(p.getVelocityY()*(-1)); // termina siendo la y positiva
            }
            else if (velociX < 0 && velociY > 0){
                p.setVelocityX(p.getVelocityX()*(-1)); // termina siendo X positiva
            }
        }
        
        else if (espeX >= 0 && (espeY <= height && espeY >= 0)){ // PAREDES
            if (velociX > 0 && velociY > 0){ 
                p.setVelocityX(p.getVelocityX()*(-1)); // termina siendo la X negativa
            }
            else if (velociX < 0 && velociY < 0){
                p.setVelocityX(p.getVelocityX()*(-1)); // termina siendo la X Positiva Y Negativa
            }
            else if (velociX > 0 && velociY < 0){
                p.setVelocityX(p.getVelocityX()*(-1)); // termina siendo la x positiva
            }
            else if (velociX < 0 && velociY > 0){
                p.setVelocityX(p.getVelocityX()*(-1)); // termina siendo X positiva
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
    public ArrayList<ArrayList<Integer>> getHolesInfo(){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(Hole h :holes){
            ArrayList <Integer> info = new ArrayList<>();
            info.add(h.getMaxParticles());
            info.add(h.getXPosition());
            info.add(h.getYPosition());
            list.add(info);
        }
        return list;
    }
}
