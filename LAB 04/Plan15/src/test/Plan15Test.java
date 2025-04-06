package test;
import domain.*;

import static org.junit.Assert.*;

import java.beans.Transient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Plan15Test {
    
    @Test
    public void shouldAddCourseAndCores(){
        try{
            Plan15 plan = new Plan15();
            plan.addCourse("DOSW", "Desarrollo y Operaciones Software", "4", "4");
            plan.addCourse("IPRO","Introduccion a la programación", "4", "4");
            plan.addCore("NFPE", "Nucleo de formación específica", "10", "DOSW\nIPRO");
            System.out.println(plan.numberUnits());
            // Hay 4 que se generan automaticamente por lo que al agregar 2 cursos y 1 core esperaremos 7 
            assertEquals(7,plan.numberUnits());
        } catch (Plan15Exception e){
            fail("Threw a exception");
        } 
    }
    
    @Test
    public void shouldExceptionA(){
        try{Plan15 plan = new Plan15();
        plan.addCore("NFPE", null, "100", "DOSW\nIPRO");
        assertEquals(null, plan.consult("NFPE").name());}
        catch (Plan15Exception e){
            assertEquals(Plan15Exception.NULL_VALUES,e.getMessage());
        } 
    }

    @Test
    public void shouldExceptionB(){
        try{Plan15 plan = new Plan15();
        plan.addCourse("DOSW", "Desarrollo y Operaciones Software", "4", "4");
        plan.addCore("NFPE", "Nucleo de formación específica", "12.n", "DOSW\n");}
        catch (Plan15Exception e){
            assertEquals(Plan15Exception.INT_ERROR,e.getMessage());
        } 
    }

    @Test
    public void shouldExceptionC(){
        try{Plan15 plan = new Plan15();
        plan.addCourse("DOSW", "Desarrollo y Operaciones Software", "4", "4");
        plan.addCore("NFPE", "Nucleo de formación específica", "120", "DOSW\n");}
        catch (Plan15Exception e){
            assertEquals(Plan15Exception.PERCENTAGE_ERROR,e.getMessage());
        } 
    }

    @Test
    public void shouldExceptionD(){
        try{Plan15 plan = new Plan15();
        plan.addCore("NFPE", "Nucleo de formación específica", "80", "DOSW\n");}
        catch (Plan15Exception e){
            assertEquals(Plan15Exception.COURSE_NOT_FOUND,e.getMessage());
        } 
    }
/*
    @Test
    public void shouldDoToString(){
        Plan15 plan = new Plan15();
        plan.addCourse("IPRO","Introduccion a la programación", "4", "4");
        System.out.println("Unidades actuales: " + plan.numberUnits());
        assertEquals("5 unidades\r\n" + //
                        ">PRI1: Proyecto Integrador. Creditos:9[3+24]\r\n" + //
                        ">DDYA: Diseño de Datos y Algoritmos. Creditos:4[4+8]\r\n" + //
                        ">MPIN: Matematicas para Informatica. Creditos:3[4+5]\r\n" + //
                        ">FCC: Nucleo formacion por comun por campo. [50%]\r\n" + //
                        "\tPRI1: Proyecto Integrador. Creditos:9[3+24]\r\n" + //
                        "\tDDYA: Diseño de Datos y Algoritmos. Creditos:4[4+8]\r\n" + //
                        "\tMPIN: Matematicas para Informatica. Creditos:3[4+5]\r\n" + //
                        ">IPRO: Introduccion a la programación. Creditos:4[4+8]\n",plan.toString());
    }
*/
}
