package domain;
import java.util.*;

/*No olviden adicionar la documentacion*/
public class City{
    static private int SIZE=25;
    private Item[][] locations;

    
    public City() {
        locations=new Item[SIZE][SIZE];
        for (int r=0;r<SIZE;r++){
            for (int c=0;c<SIZE;c++){
                locations[r][c]=null;
            }
        }
        someItems();
    }

    public int  getSize(){
        return SIZE;
    }

    public Item getItem(int r,int c){
        return locations[r][c];
    }

    public void setItem(int r, int c, Item e){
        if (isEmpty(r, c)){
            locations[r][c]=e;
        }
        else{
            System.out.println("Ya hay una en esa posicion?");
        }
    }
    
    public void changeItemPosition(int r, int c,int r1, int c1, Item e){
        locations[r1][c1] = e;
        locations[r][c] = null;
    }

    public void someItems(){
          Person adan = new Person(this,10,10);
          Person eva = new Person(this,15,15);
          Walker messner = new Walker(this, 20,14);
          Walker kukuczka = new Walker(this, 5, 20);
          TrafficLight alarm = new TrafficLight(this,0,0);
          TrafficLight alert = new TrafficLight(this,0,24);
          Slider cardozo = new Slider(this,10,15 );
          Slider riaño = new Slider(this,15,3 );
          Road martinez = new Road(this,3,0 );
          Road sanchez = new Road(this,23,0);
          Schelling tulio = new Schelling(this,16,16 );
          
          
          Person tulio2 = new Person(this,15,0);
          Schelling tulio3 = new Schelling(this,16,0 );
          Schelling tulio4 = new Schelling(this,16,1 );
    }
    
    public int neighbors(int r, int c){
        int num=0;
        if (inLocations(r,c) && locations[r][c]!=null){
            for(int dr=-1; dr<2;dr++){
                for (int dc=-1; dc<2;dc++){
                    if ((dr!=0 || dc!=0) && inLocations(r+dr,c+dc) && 
                    (locations[r+dr][c+dc]!=null) &&  (locations[r][c] instanceof Person && locations[r+dr][c+dc] instanceof Person)) num++;
                }
            }
        }
        return num;
    }
    public int neighborsEquals(int r, int c){
        int num=0;
        if (inLocations(r,c) && locations[r][c]!=null){
            for(int dr=-1; dr<2;dr++){
                for (int dc=-1; dc<2;dc++){
                    if ((dr!=0 || dc!=0) && inLocations(r+dr,c+dc) && 
                    (locations[r+dr][c+dc]!=null) &&  (locations[r][c].getClass()==locations[r+dr][c+dc].getClass())) num++;
                }
            }
        }
        return num;
    }   

    public boolean isEmpty(int r, int c){
        return (inLocations(r,c) && locations[r][c]==null);
    }    
        
    private boolean inLocations(int r, int c){
        return ((0<=r) && (r<SIZE) && (0<=c) && (c<SIZE));
    }
    
    public void ticTac(){  
        ArrayList <Item> items = new ArrayList<>();
        
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                if (locations[i][j] != null){
                    items.add(locations[i][j]);
                }
                
            }
        }
        
        for (Item i: items){
            i.decide();
            i.change();
        }
    }

}
