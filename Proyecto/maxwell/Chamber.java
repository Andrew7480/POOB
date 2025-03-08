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
        int positionEsperadaX = p.getXPositionC();
        int positionEsperadaY = p.getYPositionC();
        if (p.getIsLeft()){
            positionEsperadaX = -(chamberXPos - positionEsperadaX); 
            positionEsperadaY = chamberYPos - positionEsperadaY;
        }
        if(!p.getIsLeft()){
            positionEsperadaX = positionEsperadaX - chamberXPos; 
            positionEsperadaY = chamberYPos - positionEsperadaY;
        }
        System.out.println((positionEsperadaX + p.getVelocityX()) + " " + (positionEsperadaY + p.getVelocityY()));
        if (verifyLimits(p, positionEsperadaX + p.getVelocityX(), positionEsperadaY + p.getVelocityY()) && (positionEsperadaY + p.getVelocityY() > 0)){
            p.moveHorizontal(p.getVelocityX());
            p.moveVertical(p.getVelocityY()*-1);
        }
        if (!verifyLimits(p, positionEsperadaX + p.getVelocityX(), positionEsperadaY + p.getVelocityY())){
            bounce(p);
            p.moveHorizontal(p.getVelocityX());
            p.moveVertical(p.getVelocityY()*-1);
            System.out.println("!VerifyLimits");
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
            if (x > auxXMin  && auxXMax > x  && y > auxYMin  && y < auxYMax){
                return true;
            }
        }
        else{
            auxXMin=0;  
            auxXMax=width/2; 
            if (x > auxXMin  && auxXMax > x  && y > auxYMin  && y < auxYMax){
                return true;
            }
        }
        return false;
    }
    
    public void bounce(Particle p){
        // SOBREPASAR
        if (p.getIsLeft()){
            bounceLeft(p);
        }
    }
    public void bounceLeft(Particle p) {
        int x = p.getXPositionC();
        int y = p.getYPositionC();
    
        // Rebote en las paredes verticales (línea verde en x = 0 o pared izquierda en x = -width/2)
        if (x >= 0 || x <= -width / 2) {
            p.setVelocityX(-p.getVelocityX()); // Invierte la velocidad en X
            System.out.println("Rebote en pared vertical: Nueva VelocidadX = " + p.getVelocityX());
        }
    
        // Rebote en las paredes horizontales (parte superior en y = height o suelo en y = 0)
        if (y >= height || y <= 0) {
            p.setVelocityY(-p.getVelocityY()); // Invierte la velocidad en Y
            System.out.println("Rebote en pared horizontal: Nueva VelocidadY = " + p.getVelocityY());
        }
    }

}
