import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

/**
 * Write a description of class Kalah here.
 * 
 * @author Andrés Cardozo && Tulio Riaño
 * @version (a version number or a date)
 */
public class Kalah
{
    ArrayList<Pit> playerN = new ArrayList<>();
    ArrayList<Pit> playerS = new ArrayList<>();
    ArrayList<Pit> counterclockwise = new ArrayList<>();
    
    private Rectangle board = new Rectangle();
    
    private Pit warehousePlayerN = new Pit(true);
    private Pit warehousePlayerS = new Pit(true);
    private char timeToPlayer = 'N';
    
    private Scanner texto = new Scanner(System.in);
    
   
    public Kalah(){
        board.setChangeSize(400, 700);
        board.setPositionY(100);
        board.setPositionX(50);
        board.setColor("white");
        board.makeVisible();
        generatePlayerX();
    }
    
    public void inizializarJuego(){
        //siempre comienza playerN
        System.out.println("Comieza playerN: ");
        while (housesSeeds()){
        int houseInt1 = texto.nextInt();
        System.out.println(""+timeToPlayer);
        if(timeToPlayer == 'N'){
            if (houseInt1<=0 || houseInt1 >= 6){
                messagesError();
            }
            if (counterclockwise.get(houseInt1).seeds()==0){
                messagesError2();
            }
            moveSeedsN(houseInt1-1);
            }
        else{
            if (houseInt1 <= 7 || houseInt1 >= 13){
                messagesError();
            }
            if (counterclockwise.get(houseInt1).seeds()==0){
                messagesError2();
            }
            moveSeedsS(houseInt1-1);
            }
        }
    }
        
    
    private void moveSeedsN(int houseInt){ //cuando es n o s
        
        //n 0-5 6-almacen
        //s 7-12 13-almacen
        int seedsToMove = counterclockwise.get(houseInt).seeds();
        counterclockwise.get(houseInt).removeSeeds(seedsToMove);
        houseInt += 1;
        for (int i = houseInt; 0!=seedsToMove ;i++){
            if (counterclockwise.get(houseInt).equals(warehousePlayerS)){
                houseInt += 1;
                continue;
            }
            //Pit house1 = counterclockwise.get(houseInt);
            //house1.addSeed();
            counterclockwise.get(houseInt).addSeed();
            houseInt += 1;
            seedsToMove -=1;
            if (counterclockwise.get(houseInt).equals(warehousePlayerN) && seedsToMove == 0){
                timeToPlayer = 'N';
            }
            if (!(counterclockwise.get(houseInt).equals(warehousePlayerN)) && seedsToMove == 0){
                timeToPlayer = 'S';
            }
            
        }
    }
    private void moveSeedsS(int houseInt){ //cuando es n o s
        
        //n 0-5 6-almacen
        //s 7-12 13-almacen
        int seedsToMove = counterclockwise.get(houseInt).seeds();
        counterclockwise.get(houseInt).removeSeeds(seedsToMove);
        houseInt += 1;
        for (int i = houseInt; 0!=seedsToMove ;i++){
            if (counterclockwise.get(houseInt).equals(warehousePlayerN)){
                houseInt += 1;
                continue;
            }
            //Pit house1 = counterclockwise.get(houseInt);
            //house1.addSeed();
            counterclockwise.get(houseInt).addSeed();
            houseInt += 1;
            seedsToMove -=1;
            if (counterclockwise.get(houseInt).equals(warehousePlayerS) && seedsToMove == 0){
                timeToPlayer = 'S';
            }
            if (!(counterclockwise.get(houseInt).equals(warehousePlayerS)) && seedsToMove == 0){
                timeToPlayer = 'N';
            }
            
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
        
        for (int i=5;i>-1 ;i--){
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
    private void messagesError(){
        JOptionPane.showMessageDialog(null, "Solo se hay 6 casas por jugador", "kalah POOB", JOptionPane.INFORMATION_MESSAGE);
    }
    private void messagesError2(){
        JOptionPane.showMessageDialog(null, "Solo se pueden sacar semillas de casas CON semillas", "kalah POOB", JOptionPane.INFORMATION_MESSAGE);
    }
}
