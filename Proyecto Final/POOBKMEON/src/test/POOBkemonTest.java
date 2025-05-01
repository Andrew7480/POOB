package test;
import domain.*;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.TreeMap;

import org.junit.Test;

/*
 * POOBkemonTest
 */
public class POOBkemonTest {
    @Test
    public void shouldSaveGamePre(){
        try{
            POOBkemon poobkemon = new POOBkemon();

            //creacion estados de effecto
            /* 
            StatusEffect Paralyze = new EffectVelocity("Paralisis", "Paralisa al pokemon reduciendo su velocidad.", 1, -10);
            StatusEffect Burn  = new EffectPs("Quemadura ", "Le inflinge daño al inicio de turno.", 2, -30);
            StatusEffect poison  = new EffectPs("Quemadura ", "Le inflinge daño al inicio de turno.", 3, -50);
    
            StatusEffect defense = new MultipleEffect("Descansar", "Descansa ese turno y recupera sus estadisticas principales.", 1, 30);
            StatusEffect regenerate  = new EffectPs("Regenerar ", "", 1, 60);
            StatusEffect electrocuted  = new EffectPs("Electrocutar ", "Le inflinge daño al inicio de turno.", 10, -50);
            */
            
            //creacion trainers
            Trainer defensive = new DefensiveTrainer("defensivo");
            Trainer expert = new ExpertTrainer("experto");
            Trainer changing = new ChangingTrainer("asustado");
            Trainer attacking = new AttackingTrainer("agresivo");
            Trainer player1 = new PlayerTrainer("tulio");
            Trainer player2 = new PlayerTrainer("andrew");
            poobkemon.addTrainer(defensive.getName(), defensive);
            poobkemon.addTrainer(expert.getName(), expert);
            poobkemon.addTrainer(changing.getName(), changing);
            poobkemon.addTrainer(attacking.getName(), attacking);
            poobkemon.addTrainer(player1.getName(), player1);
            poobkemon.addTrainer(player2.getName(), player2);


        }
        catch(Exception e){
            fail();
        }    
    }

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
            poobkemon.deserializateItems();
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
