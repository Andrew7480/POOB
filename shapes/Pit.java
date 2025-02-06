import java.util.ArrayList;
/**
 * Pit es una clase para crear 'huecos' para guardar semillas.
 * 
 * @author Andrés Cardozo && Tulio Riaño
 * @version (a version number or a date)
 */
public class Pit
{
    private Rectangle square;
    private Rectangle square2;
    private int posX;
    private int posY;
    private ArrayList<Rectangle> seedsPit = new ArrayList<>();
    private boolean big;
    
    /**
     * Create a new pit.
     */
    public Pit(boolean big){
        this.big = big;
        if (big==true) {
            square = new Rectangle();
            square.setColor("yellow");
            square.setPositionX(90);
            square.setPositionY(110);
            square.setChangeSize(180, 180);
            square2 = new Rectangle();
            square2.setColor("blue");
            square2.setPositionX(120);
            square2.setPositionY(140);
            square2.setChangeSize(120, 120);
            posX = square2.getPositionX();
            posY = square2.getPositionY();
            
        }
        else {
            square = new Rectangle();
            square.setColor("yellow");
            square.setPositionX(90);
            square.setPositionY(110);
            square.setChangeSize(90, 90);
            square2 = new Rectangle();
            square2.setColor("blue");
            square2.setPositionX(106);
            square2.setPositionY(125);
            square2.setChangeSize(60, 60);
            posX = square2.getPositionX();
            posY = square2.getPositionY();
        }
    }
    /**
     * Put seeds in the Pit.
     */
    public void putSeeds(int Seeds){
        if (Seeds < 0 || Seeds > 10) {
        System.out.println("El número de semillas debe estar entre 0 y 10.");
        return;
        }
        int changeX = 0;
        int changeY = 0;   
        if (big){
            for(int i = 0; i < Seeds; i++){
                
                if (changeX>=100){
                    changeY +=40;
                    changeX = 0;
                }
                if (i == 8){
                    changeX =20;
                }
                if (i == 9){
                    changeX =70;
                }
                Rectangle seed = new Rectangle();
                seed.setColor("red");
                seed.setChangeSize(23, 23);
                seed.setPositionX(posX+changeX);
                seed.setPositionY(posY+changeY);
                seed.makeVisible();
                seedsPit.add(seed);
                changeX +=32;
                //System.out.println("aqui" + seedsPit);
                }
            }
        else{
            for(int i = 0; i < Seeds; i++){
                
                if (changeX>=70){
                    changeY +=20;
                    changeX = 0;
                }
                if (i == 8){
                    changeX =10;
                }
                if (i == 9){
                    changeX =40;
                }
                Rectangle seed = new Rectangle();
                seed.setColor("red");
                seed.setChangeSize(9, 9);
                seed.setPositionX(posX+changeX);
                seed.setPositionY(posY+changeY);
                seed.makeVisible();
                seedsPit.add(seed);
                changeX +=18;
                //System.out.println("aqui" + seedsPit);
                }
            
        }
        }
    /**
     * Method that remove seeds of a Pit.
       */    
    public void removeSeeds(int seeds){
        int count = 0;
        for (Rectangle j : seedsPit){
            if (count < seeds){
                j.makeInvisible();
                seedsPit.set(count, null);
                count ++;
            }
        }
        removeSeedsDefinitly();
        //System.out.println(seedsPit);
        }
    private void removeSeedsDefinitly(){
        while(seedsPit.get(0) == null){
            seedsPit.remove(0);
            }
        }
        
    /**
     * Calculate the amount of seeds in the pit.
       */
    public int seeds(){
        return seedsPit.size();
    }
    
    /**
     * Make visible the pit.
       */
    public void makeVisible(){
        square.makeVisible();
        square2.makeVisible();
        for (Rectangle j : seedsPit){
            j.makeVisible();
        }
    }
    
    /**
     * Make invisible the pit.
       */
    public void makeInvisible(){
        for (Rectangle i : seedsPit){
            i.makeInvisible();
        }
        square.makeInvisible();
        square2.makeInvisible();
    }
    
    /**
     * 
       */
    public void changeColors(String background, String seeds){
        makeInvisible();
        square.setColor(background);
        for (Rectangle k : seedsPit){
            k.setColor(seeds);
        }
        makeVisible();
    }
    
    /**
     * Move the square, pit and seeds.
       */
    public void moveTo(int x, int y){
        makeInvisible();
        square2.setPositionX(x+30);
        square2.setPositionY(y+30);
        square.setPositionX(x);
        square.setPositionY(y);
        posX += x;
        posY += y;
        for (Rectangle j : seedsPit){
            j.moveHorizontal(x);
            j.moveVertical(y);
        }
        makeVisible();
    }
}
