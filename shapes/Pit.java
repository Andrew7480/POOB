import java.util.ArrayList;
/**
 * Write a description of class Pit here.
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
    //boolean big;
    
    public Pit(boolean big){
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
    }
    
    public void putSeeds2(int Seeds){
        if (Seeds < 0 || Seeds > 10) {
        System.out.println("El número de semillas debe estar entre 0 y 10.");
        }
        int changeX = 0;//20;
        int changeY = 0;//18;   
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
    /**
     * Method that remove seeds of pit
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
        System.out.println(seedsPit);
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
        square2.moveHorizontal(x);
        square2.moveVertical(y);
        square.moveHorizontal(x);
        square.moveVertical(y);
        posX += x;
        posY += y;
        for (Rectangle j : seedsPit){
            j.moveHorizontal(x);
            j.moveVertical(y);
        }
        makeVisible();
    }
}
