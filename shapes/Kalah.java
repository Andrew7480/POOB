
/**
 * Write a description of class Kalah here.
 * 
 * @author Andrés Cardozo && Tulio Riaño
 * @version (a version number or a date)
 */
public class Kalah
{
    private Pit casa_1 = new Pit(true);
    private Pit casa_2 = new Pit(true);
    private Pit casa_3 = new Pit(true);
    private Pit casa_4 = new Pit(true);
    private Pit casa_5 = new Pit(true);
    private Pit casa_6 = new Pit(true);
    
    private Pit casa_7 = new Pit(true);
    private Pit casa_8 = new Pit(true);
    private Pit casa_9 = new Pit(true);
    private Pit casa_10 = new Pit(true);
    private Pit casa_11 = new Pit(true);
    private Pit casa_12 = new Pit(true);
    
    private Rectangle board = new Rectangle();
    
    private Pit almacen_n = new Pit(true);
    private Pit almacen_s = new Pit(true);
    
    public Kalah(){
        board.setChangeSize(750, 1350);
        board.setPositionY(200);
        board.setPositionX(200);
        board.setColor("white");
        board.makeVisible();
        casa_1.changeColors("green", "red");
        casa_1.moveTo(200, 90);
        casa_1.putSeeds2(3);
        casa_2.changeColors("green", "red");
        casa_2.moveTo(400, 90);
        casa_2.putSeeds2(3);
        casa_3.changeColors("green", "red");
        casa_3.moveTo(600, 90);
        casa_3.putSeeds2(3);
        casa_4.changeColors("green", "red");
        casa_4.moveTo(800, 90);
        casa_4.putSeeds2(3);
        casa_5.changeColors("green", "red");
        casa_5.moveTo(1000, 90);
        casa_5.putSeeds2(3);
        casa_6.changeColors("green", "red");
        casa_6.moveTo(1200, 90);
        casa_6.putSeeds2(3);
        almacen_n.changeColors("green", "red");
        almacen_n.moveTo(120,400);
        
        casa_7.changeColors("yellow", "red");
        casa_7.moveTo(200, 657);
        casa_7.putSeeds2(3);
        casa_8.changeColors("yellow", "red");
        casa_8.moveTo(400, 657);
        casa_8.putSeeds2(3);
        casa_9.changeColors("yellow", "red");
        casa_9.moveTo(600, 657);
        casa_9.putSeeds2(3);
        casa_10.changeColors("yellow", "red");
        casa_10.moveTo(800, 657);
        casa_10.putSeeds2(3);
        casa_11.changeColors("yellow", "red");
        casa_11.moveTo(1000, 657);
        casa_11.putSeeds2(3);
        casa_12.changeColors("yellow", "red");
        casa_12.moveTo(1200, 657);
        casa_12.putSeeds2(3);
        almacen_s.changeColors("yellow", "red");
        almacen_s.moveTo(1270, 400);
    }
    
}
