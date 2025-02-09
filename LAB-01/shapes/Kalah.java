import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.lang.Math.*;
import java.util.Random;

/**
 * Game Kalah
 * 
 * @author Andrés Cardozo and Tulio Riaño
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
     private int moveRandomMachineN(){
        int machineMoveN = (int) (Math.random() * 6) + 1;
        System.out.println("El número elegido por la máquina fue: "+machineMoveN);
        return machineMoveN;
    }
    private int moveRandomMachineS(){
        // Utilizando libreria Random
        Random random = new Random();
        int min = 8;
        int max = 13;
        int machineMoveS = random.nextInt(max - min + 1) + min;
        System.out.println("El número elegido por la máquina fue: "+machineMoveS);
        return machineMoveS;
    }
    
    /**
     * Initialize the game Kalah
       */
    public void startGame(){
        int restartGameInt = 0;
        int houseInt2;
        while (housesSeedsN() && housesSeedsS()){
            System.out.println("Ahora player:  "+ timeToPlayer);
            System.out.println("-1 -> status. ");
            System.out.println("-2 -> restart. ");
            System.out.println("-4 -> La maquina decide. ");
            int houseInt1 = texto.nextInt();
            if (houseInt1 < 0){
                if (houseInt1 == -1){
                    statusGame();
                    System.out.println("Ahora player:  "+ timeToPlayer);
                    System.out.println("Vuelve a ingresar el valor de la casa ");
                    houseInt1 = texto.nextInt();
                }
                else if (houseInt1 == -2){
                    restartGame();
                    System.out.println("Ahora player:  "+ timeToPlayer);
                    System.out.println("Vuelve a ingresar el valor de la casa ");
                    houseInt1 = texto.nextInt();
                }
                else if(houseInt1 == -4){
                    if (timeToPlayer == 'N'){
                        System.out.println("Ahora player:  "+ timeToPlayer);
                        houseInt1 = moveRandomMachineN();
                    }
                    else{
                        System.out.println("Ahora player:  "+ timeToPlayer);
                        houseInt1 = moveRandomMachineS();
                    }
                }
                else {
                    System.out.println("Ahora player:  "+ timeToPlayer);
                    System.out.println("Vuelve a ingresar el valor de la casa ");
                    houseInt1 = texto.nextInt();
                }
            }
            if(houseInt1 >0){
                if(timeToPlayer == 'N'){
                    if (houseInt1-1<0 || houseInt1-1 > 5){         
                        houseInt1 = messagesError();
                    }
                    if (counterclockwise.get(houseInt1-1).seeds()==0){
                        houseInt1 = messagesError2();
                    }
                    moveSeedsN(houseInt1-1);
                    }
                else if(timeToPlayer == 'S'){
                    if (houseInt1-1 <7 || houseInt1-1 >12){
                        houseInt1 = messagesError3();
                    }
                    if (counterclockwise.get(houseInt1-1).seeds()==0){
                        houseInt1 = messagesError2();
                    }
                    moveSeedsS(houseInt1-1);
                }
            }
        }
        if (warehousePlayerN.seeds() > warehousePlayerS.seeds()){
            restartGameInt = messagesCongratsN();
        }
        if (warehousePlayerS.seeds() > warehousePlayerN.seeds()){
            restartGameInt = messagesCongratsS();
        }
        if (restartGameInt == -3){
            restartGame();
            startGame();
        }
    }
      
    /**
     * Restart the game
       */
    public void restartGame(){
        makeInvisible();
        board.makeVisible();
        generatePlayerX();
        
    }
    
    private void moveSeedsN(int houseInt){ 
        //System.out.println("se supone N  "+ timeToPlayer);
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
        }
        if (counterclockwise.get(houseInt-1).equals(warehousePlayerN)){
            timeToPlayer = 'N';
            
        }
        else if (!(counterclockwise.get(houseInt-1).equals(warehousePlayerN))){
            if ((houseInt-1)!=7 || (houseInt-1)!=14 || (houseInt-1)!=21 || (houseInt-1)!=28){
            emptyHouse(houseInt);
            }
            timeToPlayer = 'S';  
        }
        //System.out.println("ahora "+timeToPlayer+":" ); 
    }
    private void moveSeedsS(int houseInt){ 
        
        //System.out.println("se supone S  "+ timeToPlayer);
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
        }
        if (counterclockwise.get(houseInt-1).equals(warehousePlayerS)){
            timeToPlayer = 'S';
        }
        else if (!(counterclockwise.get(houseInt-1).equals(warehousePlayerS))){
            if ((houseInt-1)!=7 || (houseInt-1)!=14 || (houseInt-1)!=21 || (houseInt-1)!=28){
            emptyHouse(houseInt);
            }
            timeToPlayer = 'N';
        }
        //System.out.println("ahora "+timeToPlayer+":" );
    }

    /**
     * Check the amount of seeds in each warehouse
       */
    public void statusGame(){
        System.out.println("Almacen N:  "+warehousePlayerN.seeds()+ "   Almacen S:  " +warehousePlayerS.seeds());
    }
    
    private boolean housesSeedsN(){
        int count =0;
        for (Pit i : playerN){
            count += i.seeds();
        }
        //System.out.println(count);
        if (count==0){
            return false;
        }
        return true;
    }
    private boolean housesSeedsS(){
        int count = 0;
        for (Pit i : playerS){
            count += i.seeds();
        }
        if (count == 0){
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
        
        counterclockwise.addAll(counterclockwise);        
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
    private int messagesError(){
        //JOptionPane.showMessageDialog(null, "El jugador N puede escoger solamente las casas 1 a 6", "kalah POOB", JOptionPane.INFORMATION_MESSAGE);
        String number = JOptionPane.showInputDialog("El jugador N puede escoger solamente las casas 1 a 6");
        return Integer.parseInt(number);
    }
    private int messagesError2(){
        //JOptionPane.showMessageDialog(null, "Solo se pueden sacar semillas de casas CON semillas", "kalah POOB", JOptionPane.INFORMATION_MESSAGE);
        String number = JOptionPane.showInputDialog("Solo se pueden sacar semillas de casas CON semillas. Vuelve a intentar");
        return Integer.parseInt(number);
    }
    private int messagesError3(){
        //JOptionPane.showMessageDialog(null, "El jugador S puede escoger solamente casas del 8 al 13", "kalah POOB", JOptionPane.INFORMATION_MESSAGE);
        String number = JOptionPane.showInputDialog("El jugador S puede escoger solamente casas del 8 al 13");
        return Integer.parseInt(number);
    }
    private int messagesCongratsN(){
        System.out.println("Si quieres jugar de nuevo, ingresa -3"); 
        String Congratulations = JOptionPane.showInputDialog("El jugador N ha ganado, Felicitaciones!"); 
        timeToPlayer ='N';
        return Integer.parseInt(Congratulations);
    }
    private int messagesCongratsS(){
        System.out.println("Si quieres jugar de nuevo, ingresa -3"); 
        String Congratulations = JOptionPane.showInputDialog("El jugador S ha ganado, Felicitaciones!");
        timeToPlayer ='S';
        return Integer.parseInt(Congratulations);
    }
    private void makeInvisible(){
        warehousePlayerN.makeInvisible();
        warehousePlayerS.makeInvisible();
        warehousePlayerN.removeSeeds(warehousePlayerN.seeds());
        warehousePlayerS.removeSeeds(warehousePlayerS.seeds());
        board.makeInvisible();
        
        counterclockwise.clear();
        playerN.clear();
        playerS.clear();
        //System.out.println(playerN+""+playerS+""+counterclockwise);
    }
    
    private void emptyHouse(int houseL){
        //System.out.println("casa a ver:"+houseL+"  "+timeToPlayer+ " "+counterclockwise.get(houseL-1).seeds());
        int seedsNumber =0;
        if (counterclockwise.get(houseL-1).seeds()==1){
            
            if (timeToPlayer =='S'){ //8--14 y 22 a
                
            if (houseL>7 && houseL<13){
                //houseL+=1;
                houseL -=8;
                //System.out.println("casa a ver:"+houseL+""+timeToPlayer);
                seedsNumber +=playerN.get(houseL).seeds();  //encuentra de al frente
                if (seedsNumber==0){
                    return;
                }
                playerN.get(houseL).removeSeeds(seedsNumber); //las remueve
                seedsNumber += 1;  // 
                for (int i = 0;i<seedsNumber;i++){ 
                    warehousePlayerS.addSeed();
            }
                playerS.get(houseL).removeSeeds(1);
            }
            
            else if (houseL>21){
                //houseL +=1;
                //System.out.println("casa a ver:"+houseL+""+timeToPlayer);
                houseL -=22;
                seedsNumber +=playerN.get(houseL).seeds();
                if (seedsNumber==0){
                    return;
                }
                playerN.get(houseL).removeSeeds(seedsNumber);
                seedsNumber += 1;
                for (int i = 0;i<seedsNumber;i++){ 
                    warehousePlayerS.addSeed();
            }
                playerS.get(houseL).removeSeeds(1);
            }
        }
            if (timeToPlayer =='N'){ //1-6 y 15 a 20
                
            if (houseL<7){
                counterclockwise.get(houseL-1).removeSeeds(1);
                if(houseL==6){
                    houseL = 0;
                }
                else if(houseL==5){
                    houseL = 1;
                }  
                else if(houseL==4){
                    houseL = 2;
                }
                else if(houseL==3){
                    houseL = 3;
                }
                else if(houseL==2){
                    houseL = 4;
                }
                else if(houseL==1){
                    houseL = 5;
                }
                //System.out.println("casa a ver:"+houseL+""+timeToPlayer);
                seedsNumber += playerS.get(houseL).seeds(); //obtiene la de alfrente
                if (seedsNumber==0){
                    return;
                }
                playerS.get(houseL).removeSeeds(seedsNumber);
                seedsNumber +=1;
                for (int i = 0;i<seedsNumber;i++){ 
                    warehousePlayerN.addSeed();
                }
                
            }   
            else if(houseL>14 && houseL<21){
                playerN.get(houseL).removeSeeds(1);
                if(houseL==15){
                    houseL = 5;
                }
                else if(houseL==16){
                    houseL = 4;
                }
                else if(houseL==17){
                    houseL = 3;
                }
                else if(houseL==18){
                    houseL = 2;
                }
                else if(houseL==19){
                    houseL = 1;
                }
                else if(houseL==20){
                    houseL = 0;
                }
                //System.out.println("casa a ver:"+houseL+""+timeToPlayer);
                seedsNumber += playerS.get(houseL).seeds(); //obtiene la de alfrente
                if (seedsNumber==0){
                    return;
                }
                playerS.get(houseL).removeSeeds(seedsNumber);
                seedsNumber +=1;
                for (int i = 0;i<seedsNumber;i++){ 
                    warehousePlayerN.addSeed();
                }
                
            }
            }
        }
        }
}

