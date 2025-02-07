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
    private boolean big;
    // Comentario
    
    public Pit(boolean big){
        this.big = big;
        if (big) {
            bigToTrue();
        }
        else{
          bigToFalse();  
        }
    }

    public void putSeeds(int seeds){
        if (seeds < 0) {
        System.out.println("El número de semillas debe ser positivo");
        return;
        }
        if (big){
            putSeedsBig(seeds);
        }
        else{
            putSeedsNoBig(seeds);
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
        }
    private void removeSeedsDefinitly(){
        while(seedsPit.size()>0){
            if (seedsPit.get(0) != null){
                return;
            }
            seedsPit.remove(0);
        }
    }
    
    
    public void addSeed(){
        int aux = seeds();
        removeSeeds(aux);
        putSeeds(aux+1);
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
        if (big){
            posX = x+30;
            posY = y+30;
            moveToBig(x, y);
        }
        else{
            posX = x+15;
            posY = y+15;
           moveToNoBig(x, y); 
        }

    }
    private void moveToNoBig(int x, int y){
        int aux = 0;
        square2.setPositionX(x+15);
        square2.setPositionY(y+15);
        square.setPositionX(x);
        square.setPositionY(y);
        aux = seeds();
        if (aux >0){
            removeSeeds(aux);
            putSeeds(aux);
        }
        makeVisible();
        makeVisible();
    }
    private void moveToBig(int x, int y){
        int aux = 0;
        square2.setPositionX(x+30);
        square2.setPositionY(y+30);
        square.setPositionX(x);
        square.setPositionY(y);
        aux = seeds();
        if (aux >0){
            removeSeeds(aux);
            putSeeds(aux);
        }
        makeVisible();
    }
    private void bigToTrue(){
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
    private void bigToFalse(){
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
    private void putSeedsBig(int seeds){
        if (seeds >25){
            System.out.println("El número de semillas de un almacen no es mayor a 25.");
            return;
        }
        int changeX = 0;
        int changeY = 0;   
        for(int i = 0; i < seeds; i++){
            if (changeX>=100){
                changeY +=25;
                changeX = 0;
            }

            Rectangle seed = new Rectangle();
            seed.setColor("red");
            seed.setChangeSize(16, 16);
            seed.setPositionX(posX+changeX);
            seed.setPositionY(posY+changeY);
            seed.makeVisible();
            seedsPit.add(seed);
            changeX +=24;
            }
        }
        
    private void putSeedsNoBig(int seeds){
        if (seeds >15){
            System.out.println("El número de semillas de una casa no es mayor a 15.");
            return;
        }
        int changeX = 0;
        int changeY = 0;
        for(int i = 0; i < seeds; i++){
            if (changeX>=50){
                changeY +=17;
                changeX = 0;
            }
            Rectangle seed = new Rectangle();
            seed.setColor("red");
            seed.setChangeSize(8, 8);
            seed.setPositionX(posX+changeX);
            seed.setPositionY(posY+changeY);
            seed.makeVisible();
            seedsPit.add(seed);
            changeX +=16;
            }
        }
}
