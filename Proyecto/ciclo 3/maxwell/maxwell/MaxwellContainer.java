package maxwell;
import shapes.*;
import java.util.*;
import javax.swing.JOptionPane;
/**
 * It's a MaxwellContainer
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
     * @param int h -> height of MaxwellContainer
     * @param int w -> width of MaxwellContainer
       */
    public MaxwellContainer(int h, int w){
        if (h < 0 ||  w <0){
            theLastActionWasSuccess = false;
            if (!chamber.getIsVisible()){
                JOptionPane.showMessageDialog(null, "Negative values ");
            }
            return;
        }
        chamber = new Chamber(h, 2*w);
        theLastActionWasSuccess = true;
        width = w;
        height = h;
        makeInvisible();
    }
    //PARA LA SUSTENTACIÓN
    public MaxwellContainer(){
        int h =200;
        int w=300;
        chamber = new Chamber(h, 2*w);
        theLastActionWasSuccess = true;
        width = w;
        height = h;
    }
    // ESTO ES PARA LA SUSTENTACIÓN
    public void addDemons(){
        theLastActionWasSuccess = chamber.addDemon("Normal",100);
        theLastActionWasSuccess = chamber.addDemon("Blue",50);
        theLastActionWasSuccess = chamber.addDemon("Weak",150);
    }
    // ESTO ES PARA LA SUSTENTACIÓN
    public void addParticles(){
        //addParticle("Normal","red",true,50,50,-10,10); // Prueba para el demonio
        //addParticle("Normal","blue",false,280,80,10,10); // Bounce   
        
        //addParticle("Rotator","red",true,50,50,0,10);
        //addParticle("Rotator","yellow",true,-80,10,15,2);
        //addParticle("Rotator","turquoise",false,-80,110,0,4);
        addParticle("Rotator","red",false,-80,20,5,4);
        /*
        addParticle("Rotator","turquoise",false,-1,110,10,4);
        addParticle("Ephemeral","yellow",false,-80,180,4,3);
        addParticle("Ephemeral","pastel blue",false,-80,150,4,5);
        addParticle("Ephemeral","red",false,80,50,5,4);
        addParticle("Flying","turquoise",false,1,110,10,4);
        addParticle("Ephemeral","yellow",false,80,180,4,3);
        addParticle("Ephemeral","pastel blue",false,80,150,4,5);
        
        
        /*
        addParticle("Flying","pastel blue",true,-100,100,5,0); // vy = 0
        addParticle("Flying","turquoise",false,-80,130,0,4);
        addParticle("Flying","pastel blue",true,-100,140,5,0); // vy = 0
        addParticle("Flying","turquoise",false,80,130,0,4);
        
        //chamber.addParticle("turquoise",false,-250,40,-5,5);
        
        addParticle("Normal","yellow",false,-210,90,5,-5);
        addParticle("Normal","yellow",false,-210,90,5,-5);
        addParticle("Normal","yellow",false,-210,90,5,-5);
        addParticle("Normal","yellow",false,-210,90,5,-5);
        addParticle("Normal","yellow",false,-210,90,5,-5);
        addParticle("Normal","yellow",false,-210,90,5,-5);
        addParticle("Normal","yellow",false,-210,90,5,-5);
        
        addParticle("Normal","yellow",true,80,40,-4,9);
        addParticle("orange",true,20,180,9,-4);
        addParticle("Normal","green",true,200,199,-10,10);
        addParticle("Normal","charcoal",true,100,150,-15,5);
        addParticle("Normal","pastel blue",false,180,40,-10,-10);
        addParticle("Normal","bronze",false,80,40,-4,9);
        addParticle("orange",true,20,180,9,-4);
        addParticle("green",true,200,199,-10,10);
        addParticle("neon yellow",false,1,199,-1,1);
        addParticle("slime",true,5,65,5,-5);
        addParticle("ivory",true,10,65,-5,-5);
        addParticle("Normal","golden",false,5,55,5,5);
        addParticle("Normal","ivory",true,-10,10,5,5);
        */
        
    }
    
    // ESTO ES PARA LA SUSTENTACIÓN
    public void addHoles(){
        theLastActionWasSuccess = chamber.addHole("EatParticle",40,60,7);
        theLastActionWasSuccess = chamber.addHole("Movil",-100,100,90);
        theLastActionWasSuccess = chamber.addHole("Movil",-20,180,17);
        theLastActionWasSuccess = chamber.addHole("EatParticle",-180,10,89);
        theLastActionWasSuccess = chamber.addHole("Normal",10,189,42);
        theLastActionWasSuccess = chamber.addHole("EatParticle",-80,40,42);
    }
    
    /**
     * Constructor of MaxwellContainer
     * @param int h - height of the MaxwellContainer
     * @param int w - width of the MaxwellContainer
     * @param int d - position of the demon in MaxwellContainer
     * @param int b - amount of particles blue
     * @param int r - amount of particles red
     * @param ArrayList<ArrayList<Integer>> particles
       */
    public MaxwellContainer(int h, int w, int d, int b, int r, int[][] particles){
        if (h < 0 || w<0) {
            theLastActionWasSuccess = false;
            if (!chamber.getIsVisible()){
                JOptionPane.showMessageDialog(null, "Negative values ");
            }
            return;
        }

        chamber = new Chamber(h, 2*w);
        ArrayList<ArrayList<Integer>> particules = convertToArrayListArrayList(particles);
        theLastActionWasSuccess = true;
        width = w;
        height = h;
        addDemon(d);
        int total = b+r;
        
        for (int i=0;i<particules.size();i++){
            if (total <=r){
                addParticle("blue", false, particules.get(i).get(0),particules.get(i).get(1),particules.get(i).get(2), particules.get(i).get(3));
                continue;
            }
            addParticle("red", true, particules.get(i).get(0),particules.get(i).get(1),particules.get(i).get(2), particules.get(i).get(3));
            total -=1;
        }
        makeInvisible();
    }
    /**
     * add demons in the container
     * @param int d -> this number cannot be negative or be the same of any of the demons in the board.
       */
    public void addDemon(int d){
        if (d<0) {
            if (!chamber.getIsVisible()){
                JOptionPane.showMessageDialog(null, "d can't be negative");
            }
            theLastActionWasSuccess = false;  
            return;
        }
         
        theLastActionWasSuccess = chamber.addDemon("Normal",d);
        
    }
    /**
     * add a specific demons in the container
     * @param String type .> Represents the demon's type
     * @param int d -> this number cannot be negative or be the same of any of the demons in the board.
       */
    public void addDemon(String type, int d){
        if (d<0) {
            if (!chamber.getIsVisible()){
                JOptionPane.showMessageDialog(null, "d can't be negative");
            }
            theLastActionWasSuccess = false;  
            return;
        }
        theLastActionWasSuccess = chamber.addDemon(type,d);
    }

    /**
     * delete demons of the container
     * @param int d -> this number cannot be negative.
       */
    public void delDemon(int d){
        theLastActionWasSuccess = chamber.delDemon(d);
    }
    
    /**
     * add a particle
     * @param String color -> color of the particle
     * @param boolean isRed -> verify if is red
     * @param int px -> position in x-axis of the particle
     * @param int py -> position in y-axis of the particle
     * @param int vx -> velocity in x of the particle
     * @param int vy -> velocity in y of the particle
       */
    public void addParticle(String color, boolean isRed, int px, int py, int vx, int vy){
        if (py<0){
            theLastActionWasSuccess = false;
            if (!chamber.getIsVisible()){
               JOptionPane.showMessageDialog(null, "py can't be negative");
            }
            return;    
        }
        chamber.addParticle("Normal",color,isRed,px,py,vx,vy);
        theLastActionWasSuccess=true;
    }
    /**
     * add a specific particle
     * @param String type -> Represents the Particle's type
     * @param String color -> color of the particle
     * @param boolean isRed -> verify if is red
     * @param int px -> position in x-axis of the particle
     * @param int py -> position in y-axis of the particle
     * @param int vx -> velocity in x of the particle
     * @param int vy -> velocity in y of the particle
       */
    public void addParticle(String type, String color, boolean isRed, int px, int py, int vx, int vy){
        if (py<0){
            theLastActionWasSuccess = false;
            if (!chamber.getIsVisible()){
               JOptionPane.showMessageDialog(null, "py can't be negative");
            }
            return;    
        }
        chamber.addParticle(type,color,isRed,px,py,vx,vy);
        theLastActionWasSuccess=true;
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
     * @param int px -> position of the hole in x-axis
     * @param int py -> position of the hole in y-axis
     * @param int particles -> amount particles that can hold the hole
       */
    public void addHole(int px, int py, int particles){
        if (py<0){
            theLastActionWasSuccess=false;
            if (!chamber.getIsVisible()){
                JOptionPane.showMessageDialog(null, "py can't be negative");
            }
            return;
        }        
        theLastActionWasSuccess = chamber.addHole("Normal",px,py,particles);
    }
    /**
     * add a specific hole to the container
     * @param int px -> position of the hole in x-axis
     * @param int py -> position of the hole in y-axis
     * @param int particles -> amount particles that can hold the hole
       */
    public void addHole(String type, int px, int py, int particles){
        if (py > 0){
            chamber.addHole(type,px,py,particles);
        }
        else{
            JOptionPane.showMessageDialog(null, "py can't be negative");
        }
    }
    
    /**
     * start the simulation ticks time
     * @param int ticks -> times to move of the each particle.
       */
    public void start(int ticks){
        for (int i = 0; i < ticks; i++){
            ArrayList<Particle> p = new ArrayList<>(chamber.getParticules());
            ArrayList<Hole> holes = new ArrayList<>(chamber.getHolesMoviles());
            for (Hole movil : holes){
                movil.makeInvisibleHole();
                chamber.movementMoviles(movil);
                movil.makeVisibleHole();
            }
            for (Particle h : p){
                h.makeInvisibleParticle();
                chamber.movement(h);    
                h.makeVisibleParticle();
            }
            chamber.particulesDelete();
        }
        isGoal();
    }
    /**
     * After the movement in the MaxwellContainer verify if all particles in the left chamber are red or if all particles in the right are blue.
       */
    public boolean isGoal(){
        ArrayList<Particle> particle = chamber.getParticules();
        for(Particle par: particle) {
            if (par.getIsLeft()){
                if(!par.getColorForBoolean().equalsIgnoreCase("red")){
                    return false;
                }
            }
            if (!par.getIsLeft()){
                if(!par.getColorForBoolean().equalsIgnoreCase("blue")){
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
     * return the type demons
       */
    public ArrayList<Integer> demons(String type){
        return chamber.demons(type);
    }
    
    /**
     * return the positions and velocity of each particle [px,py,vx,vy] in order from lowest to highest.
     * px -> position in x-axis
     * py -> position in y-axis
     * vx -> velocity in x-axis
     * vy -> velocity in y-axis
       */
    public ArrayList<ArrayList<Integer>> particles(){ // [[px,py,vx,vy]];
        ArrayList<ArrayList<Integer>> particlesInfo = chamber.getParticlesInfo();
        Collections.sort(particlesInfo, new Comparator<ArrayList<Integer>>(){
            @Override
            public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2){
                return list1.get(0).compareTo(list2.get(0));
            }
        });
        return particlesInfo;
    }
    
    public ArrayList<ArrayList<Integer>> particles(String type){
        ArrayList<ArrayList<Integer>> particlesInfo = chamber.getParticlesInfo(type);
        Collections.sort(particlesInfo, new Comparator<ArrayList<Integer>>(){
            @Override
            public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2){
                return list1.get(0).compareTo(list2.get(0));
            }
        });
        return particlesInfo;
    }
    
    /**
     * return the positions and particules remains [px, py, particles] in order from lowest to highest
     * px -> position of the hole in x-axis
     * py -> position of the hole in y-axis
     * particles -> amount of particles that can hold the hole.
       */
    public ArrayList<ArrayList<Integer>> holes(){
        ArrayList<ArrayList<Integer>> holesInfo = chamber.getHolesInfo();
        Collections.sort(holesInfo, new Comparator<ArrayList<Integer>>(){
            @Override
            public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2){
                return list1.get(0).compareTo(list2.get(0));
            }
        });
        theLastActionWasSuccess=true;
        return holesInfo;
    }
    
    /**
     * return the positions and particules remains [px, py, particles] in order from lowest to highest
     * px -> position of the hole in x-axis
     * py -> position of the hole in y-axis
     * particles -> amount of particles that can hold the hole.
       */
    public ArrayList<ArrayList<Integer>> holes(String type){
        ArrayList<ArrayList<Integer>> holesInfo = chamber.getHolesInfo(type);
        Collections.sort(holesInfo, new Comparator<ArrayList<Integer>>(){
            @Override
            public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2){
                return list1.get(0).compareTo(list2.get(0));
            }
        });
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
        theLastActionWasSuccess=true;
    }
    /**
     * determine if the action was success
       */
    public boolean ok(){
        return theLastActionWasSuccess;
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
    /*
     * Method that convert to ArrayList of ArrayList
     * @param int [][] m -> vector 2D
       */
    private ArrayList<ArrayList<Integer>> convertToArrayListArrayList(int [][] m){
        ArrayList<ArrayList<Integer>> l = new ArrayList<>();
        for (int [] fila : m){
            ArrayList<Integer> f = new ArrayList<>();
            for (int e : fila){
                f.add(e);
            }
            l.add(f);
        }
        return l;
    }
    /**
     * return the amount of particles in the chamber
       */
    public ArrayList<Particle> getParticulesChamber(){
        return chamber.getParticules();
    }
    /**
     * Makes the right convertion to coordenates canvas to board (chamber)
     * @param int x - is the position of the particle in the x-axis
     * @param int y - is the position of the particle in the y-axis
       */
    public ArrayList<Integer> convertionsCanvasToBoard(int x, int y){
        return chamber.convertionsCanvasToBoard(x, y);
    }
    /**
     * Makes the right convertion to coordenates board(chamber) to canvas
     * @param int x - is the position of the particle in board
     * @param int y - is the position of the particle in canvas*/
    public ArrayList<Integer> convertionsBoardToCanvas(int x, int y){
        return chamber.convertionsBoardToCanvas(x, y);
    }
    public ArrayList<DemonFace> getDemonFaces(){
        return chamber.getDevils();
    }
    public ArrayList<Hole> getEatParticlesHoles(){
        return chamber.getEatParticlesHoles();
    }
}
