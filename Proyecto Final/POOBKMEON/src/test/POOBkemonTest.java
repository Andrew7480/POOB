package test;
import domain.*;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.TreeMap;

import org.junit.Test;

import domain.PoobkemonException;

/*
 * POOBkemonTest
 */
public class POOBkemonTest {
    @Test
    public void shouldcreate(){
        try{
            POOBkemon poobkemon = new POOBkemon();
            poobkemon.iniciateItemsForSerialization();
        }
        catch(Exception e){
            fail();
        }    
    }
    @Test
    public void shouldopen(){
        try{
            System.out.println("si?");
            POOBkemon poobkemon = new POOBkemon();
            poobkemon.deserializarItems();
            TreeMap<String, Item> prueba = poobkemon.getItems();
            for(String key : prueba.keySet()) {
                System.out.println(key + " : " + prueba.get(key).getDescription());
            }
        }
        catch(Exception e){
            fail();
        }    
    }
}
