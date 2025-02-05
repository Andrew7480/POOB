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
    boolean big;
    
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
    public void putSeeds(int seeds){
        int distance = 0;
        ArrayList<Integer> pos = new ArrayList<>();
        pos.add(120); 
        pos.add(posY);
        pos.add(distance);
        for(int i = 0; i < seeds; i++){
            Rectangle p = new Rectangle();
            p.setColor("black");
            p.setChangeSize(15, 15);
            distance = rangeVerify(posX + distance, pos.get(1), pos, distance);
            System.out.println(pos.get(0)+" "+" "+pos.get(1)+" "+distance);
            p.setPositionX(pos.get(0));
            p.setPositionY(pos.get(1));
            p.makeVisible();
            distance += 20;
            seedsPit.add(p);
        }
    }
    private int rangeVerify(int verifyX, int verifyY, ArrayList<Integer> pos, int distance){
        if (verifyX >= 240){
            verifyX = posX;
            verifyY = pos.get(1) + 18;
            pos.set(0, verifyX);
            pos.set(1, verifyY);
            distance = 0;
        }
        else{
            verifyX = posX + distance;
        }
        pos.set(0, verifyX);
        return distance;
    }
    
    public void putSeeds2(int Seeds){
        if (Seeds < 0 || Seeds > 10) {
        System.out.println("El número de semillas debe estar entre 0 y 10.");
        return;
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
            seed.setPositionX(120+changeX);
            seed.setPositionY(140+changeY);
            seed.makeVisible();
            seedsPit.add(seed);
            changeX +=32;
            //System.out.println("aqui" + seedsPit);
            }
        }
            
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

    public int seeds(){
        System.out.println(seedsPit);
        seedsPit.removeFirst();
        System.out.println(seedsPit);
        return seedsPit.size();
    }
    public void makeVisible(){
        square.makeVisible();
        square2.makeVisible();
    }
    public void makeInvisible(){
        square.makeInvisible();
        square2.makeInvisible();
    }
    public void changeColors(String pit, String seeds){
        
    }
    
    public void moveTo(int x, int y){
        square.makeInvisible();
        square2.makeInvisible();
        square2.moveHorizontal(x);
        square2.moveVertical(y);
        square.moveHorizontal(x);
        square.moveVertical(y);
        square.makeVisible();
        square2.makeVisible();
    }
}
