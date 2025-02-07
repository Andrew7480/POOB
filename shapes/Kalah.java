import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

/**
 * Write a description of class Kalah here.
 * 
 * @author Andrés Cardozo && Tulio Riaño
 * @version (a version number or a date)
 */
public class kalah
{
    ArrayList<Pit> playerN = new ArrayList<>();
    ArrayList<Pit> playerS = new ArrayList<>();
    ArrayList<Pit> counterclockwise = new ArrayList<>();
    
    private Rectangle board = new Rectangle();
    
    private Pit warehousePlayerN = new Pit(true);
    private Pit warehousePlayerS = new Pit(true);
    
    private Scanner texto = new Scanner(System.in);
    
   
    public kalah(){
        board.setChangeSize(400, 700);
        board.setPositionY(100);
        board.setPositionX(50);
        board.setColor("white");
        board.makeVisible();
        generatePlayerX();
    }
    
    public void inizializarJuego(){
        //siempre comienza playerN
        System.out.println("Comieza playerN, ");        
        int houseInt = texto.nextInt();
        if (houseInt <0 || houseInt>6){
            JOptionPane.showMessageDialog(null, "Solo se hay 6 casas por jugador", "kalah POOB", JOptionPane.INFORMATION_MESSAGE);
        }
        moveSeeds(houseInt-1);    
        while (housesSeeds()){
        //int house1 = texto.nextInt();
        
        int houseInt1 = texto.nextInt();
        if (houseInt <0 || houseInt>6){
            JOptionPane.showMessageDialog(null, "Solo se hay 6 casas por jugador", "kalah POOB", JOptionPane.INFORMATION_MESSAGE);
        }
        if (counterclockwise.get(houseInt).seeds()==0){
            JOptionPane.showMessageDialog(null, "Solo se pueden sacar semillas de casas CON semillas", "kalah POOB", JOptionPane.INFORMATION_MESSAGE);
        }
        moveSeeds(houseInt1-1);
    }          
        
    }
    
    
    private void moveSeeds(int houseInt){ //cuando es n o s
        
        //n 0-5 6-almacen
        //s 7-12 13-almacen
        
        int seedsToMove = playerN.get(houseInt).seeds();
        playerN.get(houseInt).removeSeeds(seedsToMove);
        
        for (int i = houseInt; 0!=seedsToMove ;i++){
            
            //Pit house1 = counterclockwise.get(houseInt);
            //house1.addSeed();
            counterclockwise.get(houseInt).addSeed();
            houseInt += 1;
            seedsToMove -=1;
            
        }
    }


    public void statusGame(){
        System.out.println("Almacen N:  "+warehousePlayerN.seeds()+ "   Almacen S:  " +warehousePlayerS.seeds());
    }
    
    public boolean housesSeeds(){
        int count =0;
        for (Pit i : playerN){
            count += i.seeds();
        }
        int count2 =0;
        for (Pit i : playerS){
            count2 += i.seeds();
        }
        System.out.println(count + " " + count2);

        if (count==0 || count2==0){
            return false;
            
        }
        return true;

         
    }

    private void generatePlayerX(){
        warehousePlayerN.changeColors("green","red" );
        warehousePlayerN.moveTo(5, 200);
        warehousePlayerS.moveTo(625,200);
        int houses = 6;
        int distancesX = 50;
        for (int i = 0; i < houses; i++){
            Pit pitN = new Pit(false);
            Pit pitS = new Pit(false);
            pitN.moveTo(distancesX, 100);
            pitS.moveTo(distancesX, 410);
            pitN.changeColors("green", "red");
            pitS.changeColors("yellow", "red");
            playerN.add(pitN);
            playerS.add(pitS);
            distancesX += 122;
            
        }
        for (Pit i : playerN){
            i.putSeeds(3);
        }
        for (Pit i : playerS){
            i.putSeeds(3);
        }
        counterclockwiseBoard();
    }
    
    private void counterclockwiseBoard(){
        for (int i=5;i>-1 ;i--){
            counterclockwise.add(playerN.get(i));
        }
        
        counterclockwise.add(warehousePlayerN);
        for (Pit i : playerS){
            counterclockwise.add(i);
        }
        counterclockwise.add(warehousePlayerS);
        
        for (int i=5;i>3 ;i--){
            counterclockwise.add(playerN.get(i));
        }
        //System.out.println(counterclockwise);
    }
    
    private void makeVisibleBoard(){
        board.makeVisible();
        warehousePlayerN.makeVisible();
        warehousePlayerS.makeVisible();
        
        for (Pit i : playerN){
            i.makeVisible();
        }
        for (Pit i : playerS){
            i.makeVisible();
        }
    }
}
