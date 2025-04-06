package test;
import domain.*;


import static org.junit.Assert.*;

import java.beans.Transient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CoreTest{
   
 
    @Test
    public void shouldCalculateTheCreditsOfACoreCostume(){
        try {
            Core c = new Core("NFCC", "Nucleo de Formacion Comun por Campo", 50);
            c.addCourse(new Course("PRI1","Proyecto Integrador 1", 3, 3));
            c.addCourse(new Course("DDYA", "Diseño de Datos y Algoritmos", 4, 4));
            c.addCourse(new Course("MPIN", "Matematicas para Informatica", 3, 4));
           assertEquals(10,c.credits());
        } catch (Plan15Exception e){
            fail("Threw a exception");
        }    
    }   
    
   @Test
    public void shouldCalculateTheCreditsEstimatedOfACore(){
        try {
            Core c = new Core("NFCC", "Nucleo de Formacion Comun por Campo", 50);
            c.addCourse(new Course("PRI1","Proyecto Integrador 1", 3, 3));
            c.addCourse(new Course("DDYA", "Diseño de Datos y Algoritmos", 4, 4));
            c.addCourse(new Course("MPIN", "Matematicas para Informatica", 3, 4));
           assertEquals(10,c.creditsEstimated());
        } catch (Plan15Exception e){
            fail("Threw a exception");
        }    
    }    
    
    @Test
    public void shouldCalculateTheCreditsEstimated(){
        try {
            Core c = new Core("NFCC", "Nucleo de Formacion Comun por Campo", 50);
            c.addCourse(new Course("PRI1","Proyecto Integrador 1"));
            c.addCourse(new Course("DDYA", "Diseño de Datos y Algoritmos",1,4));
            c.addCourse(new Course("MPIN", "Matematicas para Informatica", 3));
           assertEquals(9,c.creditsEstimated());
        } catch (Plan15Exception e){
            fail("Threw a exception");
        }    
    }

    
    @Test
    public void shouldThrowExceptionIfCoreHasNoCourse(){
        try { 
            Core c = new Core("NFCC", "Nucleo de Formacion Comun por Campo", 50);
           int price=c.credits();
           fail("Did not throw exception");
        } catch (Plan15Exception e) {
            assertEquals(Plan15Exception.IMPOSSIBLE,e.getMessage());
        }    
    }    
    
    
   @Test
    public void shouldThrowExceptionIfThereIsErrorInCredits(){
        try { 
            Core c = new Core("NFCC", "Nucleo de Formacion Comun por Campo", 50);
            c.addCourse(new Course("PRI1","Proyecto Integrador 1", -3, 3));
            c.addCourse(new Course("DDYA", "Diseño de Datos y Algoritmos", 4, 4));
            c.addCourse(new Course("MPIN", "Matematicas para Informatica", 3, 4));
           int price=c.credits();
           fail("Did not throw exception");
        } catch (Plan15Exception e) {
            assertEquals(Plan15Exception.CREDITS_ERROR,e.getMessage());
        }    
    }     
    
   @Test
    public void shouldThrowExceptionIfCreditsIsNotKnown(){
        try { 
            Core c = new Core("NFCC", "Nucleo de Formacion Comun por Campo", 50);
            c.addCourse(new Course("PRI1","Proyecto Integrador 1", 3, 3));
            c.addCourse(new Course("DDYA", "Diseño de Datos y Algoritmos", 4, 4));
            c.addCourse(new Course("MPIN", "Matematicas para Informatica"));
           int price=c.credits();
           fail("Did not throw exception");
        } catch (Plan15Exception e) {
            assertEquals(Plan15Exception.CREDITS_UNKNOWN,e.getMessage());
        }    
    }  
    
}