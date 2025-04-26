package test;
import domain.*;
import static org.junit.Assert.*;
import java.beans.Transient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DMaxwellTest {
    @Test
    public void shouldCreateADMaxwell(){
        DMaxwell d = new DMaxwell();
        int [][] p = {{43,52,139,254,291,343,67,201,228,310},{48,55,126,336,79,112,193,277,326,360},{116,129,175,288,356,364},{20,61,102,143,184,225,266,307,348,389,430}};
        assertArrayEquals(p,d.container());
    }
    @Test
    public void shouldMakeTheMoveUp(){
        DMaxwell d = new DMaxwell();
        try{
            d.movement('u');
            int afterMove = d.container()[0][0];
            assertEquals(2, afterMove);
        }catch(DMaxwellException e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void shouldMakeTheMoveDown(){
        DMaxwell d = new DMaxwell();
        try{
            d.movement('d');
            int afterMove = d.container()[0][0];
            assertEquals(84,afterMove);
        }catch(DMaxwellException e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void shouldMakeTheMoveRight(){
        DMaxwell d = new DMaxwell();
        try{
            d.movement('r');
            int afterMove = d.container()[0][0];
            assertEquals(44,afterMove);
        }catch(DMaxwellException e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void shouldMakeTheMoveleft(){
        DMaxwell d = new DMaxwell();
        try{
            d.movement('l');
            int afterMove = d.container()[0][0];
            assertEquals(42,afterMove);
        }catch(DMaxwellException e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void shouldDissapearParticleForHole(){
        //La particula roja tras diversos movimientos
        //desaparecera eventualmente por lo que la primera particula
        //roja va a ser la siguiente que se encontraria en una posicion diferente.
        DMaxwell d = new DMaxwell();
        try{
            d.movement('l');
            d.movement('d');
            d.movement('d');
            int afterMove = d.container()[1][0];
            assertEquals(136,afterMove);
        }catch(DMaxwellException e){
            System.out.println(e.getMessage());
        }
    }
}
