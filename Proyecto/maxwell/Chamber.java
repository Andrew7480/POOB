import java.util.ArrayList;
import java.util.*;
import javax.swing.JOptionPane;
import java.math.*;
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
        chamberCenter = new Rectangle(chamberForm.getXPosition()+(w/2),chamberForm.getYPosition(),2,h,"green");
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
        if (isInLeft(px,py)){
            CreateInLeft(color,isRed, px,py,vx,vy);
        }
        else if (isInRight(px,py)){
            createInRight(color, isRed, px,py,vx,vy);
        }
        
    }
    private boolean isInLeft(int px, int py){
        int chamberXPos = chamberCenter.getXPosition();
        int chamberYPos =chamberCenter.getYPosition()+height;
        int auxXMin=-width/2;  //-300 
        int auxXMax=0;         //0
        int auxYMin=0;
        int auxYMax= height; 
        return (px> auxXMin  && auxXMax>px  && py>auxYMin  && py< auxYMax  );
    }
    
    private boolean isInRight(int px, int py){
        int chamberXPos = chamberCenter.getXPosition();
        int chamberYPos =chamberCenter.getYPosition()+height;
        int auxXMin=0;  
        int auxXMax=width/2; 
        int auxYMin=0;
        int auxYMax= height; 
        return (px> auxXMin  && auxXMax>px  && py>auxYMin  && py< auxYMax);
    }
    
    /**
     * In case the particle is in Left, add it in chamber
     * @param String color
     * @param int px
     * @param int py
     * @param int vx
     * @param int vy
       */
    private void CreateInLeft(String color,boolean isRed,int px, int py, int vx, int vy){
        int chamberXPos = chamberCenter.getXPosition();
        int chamberYPos =chamberCenter.getYPosition()+height;
        Particle c = new Particle(color, px + chamberXPos, chamberYPos - py, vx, vy, true,isRed);
        if(isVisible){
            c.makeVisibleParticle();
        }
        particules.add(c);              
    }
    /**
     * In case the particle is in Right, add it in chamber
     * @param String color
     * @param int px
     * @param int py
     * @param int vx
     * @param int vy
       */
    private void createInRight(String color,boolean isRed,int px, int py, int vx, int vy){
        int chamberXPos = chamberCenter.getXPosition();
        int chamberYPos =chamberCenter.getYPosition()+height;
        Particle c = new Particle(color, px+ chamberXPos, chamberYPos - py, vx, vy, false,isRed);
        if(isVisible){
            c.makeVisibleParticle();
        }
        particules.add(c);
        
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
        int chamberXPos = chamberCenter.getXPosition();
        int chamberYPos =chamberCenter.getYPosition()+height;
        int positionEsperadaX = p.getXPositionC()- chamberXPos;
        int positionEsperadaY = chamberYPos - p.getYPositionC() ;
        if  (positionEsperadaX > -width/4 ||positionEsperadaX < width/4){
            if (isInDemonPos(p,positionEsperadaX,positionEsperadaY)){
                if(!(p.getIsRed() == p.getIsLeft())) {
                    p.changeIsLeft();
                    System.out.println(p.getIsLeft());
                    p.moveHorizontal(p.getVelocityX());
                    p.moveVertical(-p.getVelocityY());
                    return;
                }
                
            }
        }
        boolean verify = verifyLimits(p, positionEsperadaX + p.getVelocityX(), positionEsperadaY + p.getVelocityY()) && (positionEsperadaY + p.getVelocityY() >= 0);
        
        if (verify){
            p.moveHorizontal(p.getVelocityX());
            p.moveVertical(-p.getVelocityY());
        }
        
        else if (!verify){
            bounce(p,positionEsperadaX+ p.getVelocityX(),positionEsperadaY+ p.getVelocityY());
            p.makeInvisibleParticle();
            p.moveHorizontal(p.getVelocityX());
            p.moveVertical(-p.getVelocityY());
            p.makeVisibleParticle();
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
        if (p.getIsLeft()){
            auxXMin=-width/2;
            auxXMax=0;
            return  x >= auxXMin  && x <= auxXMax  && y >= auxYMin  && y <= auxYMax;
        }        
        if (!p.getIsLeft()){
            auxXMin=0;  
            auxXMax=width/2; 
            return x >= auxXMin  && x <= auxXMax  && y >= auxYMin  && y <= auxYMax;

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
    // X Y Y SON LOS VALORES DEL CANVAS
    private ArrayList<Integer> convertionsCanvasToBoard(int x, int y){
        ArrayList<Integer> convertions = new ArrayList<>();
        int chamberXPos = chamberCenter.getXPosition();
        int chamberYPos =chamberCenter.getYPosition()+height;
        int positionEsperadaX = x - chamberXPos;
        int positionEsperadaY = chamberYPos - y;
        convertions.add(positionEsperadaX);
        convertions.add(positionEsperadaY);
        return convertions;
    }
    // X Y Y SON LOS VALORES DEL BOARD
    private ArrayList<Integer> convertionsBoardToCanvas(int x, int y){
        ArrayList<Integer> convertions = new ArrayList<>();
        int chamberXPos = chamberCenter.getXPosition();
        int chamberYPos =chamberCenter.getYPosition()+height;
        int positionEsperadaX = x + chamberXPos;
        int positionEsperadaY = chamberYPos - y;
        convertions.add(positionEsperadaX);
        convertions.add(positionEsperadaY);
        return convertions;
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
        int newWidth = width/2;
        int convertXBoard = convertionsCanvasToBoard(p.getXPositionC(), p.getYPositionC()).get(0);
        int convertYBoard = convertionsCanvasToBoard(p.getXPositionC(), p.getYPositionC()).get(1);
        p.makeInvisibleParticle();
        if ((espeX == 0  || espeX == width/2) && (espeY == 0  || espeY == height)){
            bounceInCorner(p, espeX,espeY);
        }
        else if ((espeX > width/2 ) && (espeY > height)){
            p.setPositionParticle(chamberCenter.getXPosition()+(width/2),chamberCenter.getYPosition());
            bounceInCorner(p, espeX,espeY);
        }
        else if ((espeX > width/2 ) && (espeY < 0)){
            p.setPositionParticle(chamberCenter.getXPosition()+(width/2),chamberCenter.getYPosition()+height);
            bounceInCorner(p, espeX,espeY);
        }
        else if ((espeX < 0 ) && (espeY > height)){
            p.setPositionParticle(chamberCenter.getXPosition(),chamberCenter.getYPosition());
            bounceInCorner(p, espeX,espeY);
        }
        else if ((espeX < 0) && (espeY < 0)){
            p.setPositionParticle(chamberCenter.getXPosition(),chamberCenter.getYPosition()+height);
            bounceInCorner(p, espeX,espeY);
        }
        if (espeX >= width/2){ // PARED DERECHA
                float t = ((float)(newWidth - convertXBoard) / velociX);
                int  n = chamberCenter.getYPosition() + height  -((int)(convertYBoard + (velociY * t)));
                p.setPositionParticle(chamberCenter.getXPosition() + width/2, n);
                p.setVelocityX(velociX*(-1));
        }
        else if (espeX <= 0 && (espeY <= height && espeY >= 0)){ // PARED IZQUIERDA
                float t = ((float)(0 - convertXBoard) / velociX);
                int  n = chamberCenter.getYPosition() + height - ((int)(convertYBoard + (velociY * t)));
                p.setPositionParticle(chamberCenter.getXPosition(), n);
                p.setVelocityX(velociX*(-1));
        }
        else if ((espeY < 0) && (espeX >= 0 && espeX <= width/2)){ // - PISO
            float t = -((float) (convertYBoard-0) / velociY);
            System.out.println(t);
            int  n = chamberCenter.getXPosition() + ((int)(convertXBoard + (velociX * t)));
            p.setPositionParticle(n, chamberCenter.getYPosition()+height);
            p.setVelocityY(velociY*(-1)); // termina siendo la y positiva
        }
        else if ((espeY >= height) && (espeX >= 0 && espeX <= width/2)){ // TECHO
            float t = (float) Math.abs((height - p.getYPositionC()) / velociY);
            p.setPositionParticle((int) (p.getXPositionC() + velociX * t), height);
            p.setVelocityY(velociY*(-1)); // termina siendo la y positiva
        }
        p.makeVisibleParticle();
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
        int newWidth = width/2;
        int convertXBoard = convertionsCanvasToBoard(p.getXPositionC(), p.getYPositionC()).get(0);
        int convertYBoard = convertionsCanvasToBoard(p.getXPositionC(), p.getYPositionC()).get(1);
        if ((espeX == 0 || espeX == -width/2) && (espeY == 0  || espeY == height)){
            bounceInCorner(p, espeX,espeY);
        }
        else if ((espeX > 0 ) && (espeY > height)){
            p.setPositionParticle(chamberCenter.getXPosition(),chamberCenter.getYPosition());
            bounceInCorner(p, espeX,espeY);
        }
        else if ((espeX > 0 ) && (espeY < 0)){
            p.setPositionParticle(chamberCenter.getXPosition(),chamberCenter.getYPosition()+height);
            bounceInCorner(p, espeX,espeY);
        }
        else if ((espeX < -width/2 ) && (espeY > height)){
            p.setPositionParticle(chamberCenter.getXPosition()-(width/2),chamberCenter.getYPosition());
            bounceInCorner(p, espeX,espeY);
        }
        else if ((espeX < -width/2 ) && (espeY < 0)){
            p.setPositionParticle(chamberCenter.getXPosition()-(width/2),chamberCenter.getYPosition()+height);
            bounceInCorner(p, espeX,espeY);
        }
        else if (espeX <= -width/2){ // PARED
             if (velociX< 0 && velociY < 0 || (velociX < 0 && velociY > 0)){
                float t = ((float)(convertXBoard + newWidth) / velociX);
                int  n = chamberCenter.getYPosition() + height  -((int)(convertYBoard + (velociY * t)));
                p.setPositionParticle(chamberCenter.getXPosition() - width/2, n);
                p.setVelocityX(p.getVelocityX()*(-1)); // termina siendo la X Positiva 
            }
        }
        else if (espeX >= 0 && (espeY <= height && espeY >= 0)){ // PAREDES
            if (velociX > 0 && velociY > 0  || velociX > 0 && velociY < 0){
                float t = ((float)(convertXBoard-0) / velociX);
                int  n = chamberCenter.getYPosition() + height - ((int)(convertYBoard + (velociY * t)));
                p.setPositionParticle(chamberCenter.getXPosition(), n);
                p.setVelocityX(p.getVelocityX()*(-1)); // termina siendo la X negativa
            }
        }
        else if((espeY < 0 && ((espeX <= 0) && espeX >= -width/2))){ // PISO
            if(velociX > 0 && velociY < 0 || velociX < 0 && velociY < 0 ){
                float t = -((float) (0-convertYBoard) / velociY);
                System.out.println(t);
                int  n = chamberCenter.getXPosition() + ((int)(convertXBoard + (velociX * t)));
                p.setPositionParticle(n, chamberCenter.getYPosition()+height);
                p.setVelocityY(p.getVelocityY()*(-1));
            }
        }
        else if ((espeY >= height) && (espeX <= 0 && espeX >= -width/2)){
            if (velociX > 0 && velociY > 0 || velociX < 0 && velociY > 0 ){
                float t = (float) Math.abs((p.getYPositionC()-height)/velociY);
                p.setPositionParticle((int) (p.getXPositionC() + velociX * t), height);
                p.setVelocityY(p.getVelocityY()*(-1));
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
    
    private boolean isInDemonPos(Particle p ,int x, int y){
        Canvas canvas = Canvas.getCanvas();
        boolean isLeft = p.getIsLeft();
        int posXaf = x+p.getVelocityX();
        int posYaf = y+p.getVelocityY();

        for (DemonFace d : devils){
            if (posXaf==0 && posYaf == d.getPosD()){
                    return true;
                }
            //System.out.println("De particula "+ x +  "  " + y + " " );
            //System.out.println("De se supone "+ posXaf +  "  " + posYaf + " " );
            
            if (isLeft){
                if (p.getVelocityY() <0){
                    while (x< posXaf && y< posYaf){
                        x +=1;
                        y -=1;
                        if (x==0 && y== d.getPosD()){
                        return true;
                    }
                    }
                }
                else if (p.getVelocityY() >0){
                    while (x< posXaf && y< posYaf){
                        x +=1;
                        y +=1;
                        if (x==0 && y== d.getPosD()){
                        return true;
                    }
                    }
                }
            }
            if (!isLeft){
                if (p.getVelocityY() <0){
                    while (x< posXaf && y< posYaf){
                        x -=1;
                        y -=1;
                        if (x==0 && y== d.getPosD()){
                        return true;
                    }
                    }
                }
                else if (p.getVelocityY() >0){
                    while (x< posXaf && y< posYaf){
                        x -=1;
                        y +=1;
                        if (x==0 && y== d.getPosD()){
                        return true;
                    }
                    }
                }
            }

        }
        return false;
        }


    
}
