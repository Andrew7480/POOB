package test;
import domain.*;
import static org.junit.Assert.*;
import java.beans.Transient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SistemaTest{
    @Test
    public void shouldCreate(){
        try{
            Sistema sistem = new Sistema();
        }
        catch(TransmilenioException e){
            fail();
        }    
    }

    @Test
    public void shouldStationTime(){
        try{
            Sistema sistem = new Sistema();
            assertEquals(20,sistem.tiempoEsperaEstacion("Mandalay"));
            assertEquals(20,sistem.tiempoEsperaEstacion("Campin"));
            assertEquals(40,sistem.tiempoEsperaEstacion("Banderas"));
        }
        catch(TransmilenioException e){
            fail();
        }    
    }
    @Test
    public void shouldRutasString(){
        try{
            Sistema sistem = new Sistema();
            assertEquals("-> B-3-> F-72-> G-44",sistem.rutasString());
        }
        catch(TransmilenioException e){
            fail();
        }    
    }
    @Test
    public void shouldNumberStops(){
        try{
            Sistema sistem = new Sistema();
            assertEquals(3,sistem.numeroParadas("G-44","Zona Industrial","Banderas"));
        }
        catch(TransmilenioException e){
            fail();
        }    
    }

    @Test
    public void shouldSinTransbordos(){
        try{
            Sistema sistem = new Sistema();
            System.out.println(sistem.rutasSinTransbordoString("Mandalay","Banderas"));
            assertEquals("-> F-72-> G-44",sistem.rutasSinTransbordoString("Mandalay","Banderas"));
        }
        catch(TransmilenioException e){
            fail();
        }    
    }

}

