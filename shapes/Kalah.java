import java.util.ArrayList;
/**
 * Write a description of class Kalah here.
 * 
 * @author Andrés Cardozo && Tulio Riaño
 * @version (a version number or a date)
 */
public class Kalah
{
    ArrayList<Pit> jugador_1 = new ArrayList<>();
    ArrayList<Pit> jugador_2 = new ArrayList<>();
    private Rectangle board = new Rectangle();
    
    private Pit almacen_n = new Pit(true);
    private Pit almacen_s = new Pit(true);
    
   
    public Kalah(){
        
        board.setChangeSize(750, 1350);
        board.setPositionY(200);
        board.setPositionX(200);
        board.setColor("white");
        board.makeVisible();
        generatePlayerX();

    }
    private void generatePlayerX(){
        int houses = 6;
        int distancesX = 200;
        int distanceY = 90;
        int distanceYPlayer2 = 657;
        for (int i = 0; i < houses; i++){
            Pit p = new Pit(true);
            Pit p2 = new Pit(true);
            p.moveTo(distancesX, distanceY);
            p2.moveTo(distancesX, distanceYPlayer2);
            p.changeColors("green", "red");
            p2.changeColors("yellow", "red");
            jugador_1.add(p);
            jugador_2.add(p2);
            distancesX += 200;
        }
    }
    
}
