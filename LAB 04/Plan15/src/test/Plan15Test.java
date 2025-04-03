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
        Plan15 plan = new Plan15();
        plan.addCourse("DOSW", "Desarrollo y Operaciones Software", "4", "4");
        plan.addCourse("IPRO","Introduccion a la programación", "4", "4");
        plan.addCore("NFPE", "Nucleo de formación específica", "100", "DOSW\nIPRO");
        System.out.println(plan.numberUnits());
        /* Hay 4 que se generan automaticamente por lo que al agregar 2 cursos y 1 core esperaremos 7 */
        assertEquals(7,plan.numberUnits());
    }

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
}
