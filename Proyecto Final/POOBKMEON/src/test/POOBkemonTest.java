package test;
import domain.*;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.*;

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
            
            StatusEffect Paralyze = new StatusEffect("Paralisis", "Paralisa al pokemon reduciendo su velocidad.", 1);
            
            TributeEffect Burn  = new TributeEffect("Quemadura ", "Le inflinge daño al inicio de turno.",2, 
            new HashMap<String, Integer>() {{
                put("Defense", -10);
                put("PS", -30);}}  );
            TributeEffect Poison  = new TributeEffect("veneno ", "Le inflinge daño al inicio de turno.", 3,new HashMap<String, Integer>() {{
                put("Defense", -30);
                put("PS", -90);}});
            TributeEffect Defense = new TributeEffect("Descansar", "Descansa ese turno y recupera sus estadisticas principales.", 1,new HashMap<String, Integer>() {{
                put("Defense", 50);}});
            TributeEffect Regenerate  = new TributeEffect("Regenerar ", "", 1,new HashMap<String, Integer>() {{
                put("PS", 50);}});
            TributeEffect Electrocuted  = new TributeEffect("Electrocutar ", "Le inflinge daño al inicio de turno.", 4,new HashMap<String, Integer>() {{
                put("Attack", -20);
                put("PS", -30);}});
            
            //Movimientos con sus efectos
            MovementState paralyze = new MovementState("Paralyze","",40, 30,50, PokemonType.PLANTA,Paralyze, 60, 0);
            MovementTribute burn = new MovementTribute("Burn", "", 30, 39, 80, PokemonType.FUEGO, Burn, 10);
            MovementTribute poison = new MovementTribute("Super Burn", "", 15, 60, 80, PokemonType.VENENO, Poison, 0);
            MovementTribute defense = new MovementTribute("Defense", "", 25, 0, 100, PokemonType.NORMAL, Defense, 0);
            MovementTribute regenerate = new MovementTribute("Regenerar", "", 25, 0, 100, PokemonType.NORMAL, Regenerate, 0);
            MovementTribute electrocuted = new MovementTribute("Electrocutar", "", 12, 30, 70, PokemonType.ELECTRICO, Electrocuted, 30);
            //Movimiento Normales -> FISICOS -> ESPECIALES?
            Movement hyperBeam = new Movement("Hyper Beam","A devastating attack that requires a turn to recharge.",5,150,90,PokemonType.NORMAL,0);
            Movement quickAttack = new Movement("Quick Attack","A fast attack that always strikes first.",30,40,100,PokemonType.NORMAL,1);
            Movement earthquake = new Movement("Earthqueake","Causes an earthquake that affects all Pokémon on the field.",10,100,100,PokemonType.TIERRA,0);
            Movement psychic = new Movement("Psychic","A mental attack that can reduce the enemy's special defense.",10,90,100,PokemonType.PSIQUICO,0);
            Movement dragonClaw = new Movement("Dragon Claw","Strike the enemy with a sharp claw of dragon energy.",15,80,100,PokemonType.DRAGON,0);
            Movement shadowBall = new Movement("Shadow ball","Throws a ball of dark energy. You can lower the special defense.",15,80,100,PokemonType.FANTASMA,0);
            Movement brickBreak = new Movement("Brick break","Break the barriers of Reflection and Light Display.",15,75,100,PokemonType.LUCHA,0);
            Movement surf = new Movement("Surf","A water attack that hits all Pokémon in battle, except your partner in double battles.",15,90,100,PokemonType.AGUA,0);
            

            //Pokemones con esos movs
            Pokemon charizard = new Pokemon("Charizard", 90, 360, 293, 348, 280, 295, 328, PokemonType.FUEGO, PokemonType.VOLADOR);
            charizard.setMovements(new Movement[] {paralyze,burn,quickAttack,dragonClaw});
            
            Pokemon snorlax = new Pokemon("Snorlax",100,524,350,251,251,350,174,PokemonType.NORMAL,null);
            snorlax.setMovements(new Movement[]{hyperBeam,dragonClaw,brickBreak,surf});
            
            Pokemon blastoise = new Pokemon("Blastoise",100,362,291,295,328,339,280,PokemonType.AGUA,null);
            blastoise.setMovements(new Movement[]{surf,psychic,earthquake,hyperBeam});
            
            Pokemon charizard1 = new Pokemon("Charizard", 90, 360, 293, 348, 280, 295, 328, PokemonType.FUEGO, PokemonType.VOLADOR);
            charizard1.setMovements(new Movement[] {paralyze,burn,quickAttack,dragonClaw});
            
            Pokemon snorlax1 = new Pokemon("Snorlax",100,524,350,251,251,350,174,PokemonType.NORMAL,null);
            snorlax1.setMovements(new Movement[]{hyperBeam,dragonClaw,brickBreak,surf});
            
            Pokemon blastoise1 = new Pokemon("Blastoise",100,362,291,295,328,339,280,PokemonType.AGUA,null);
            blastoise1.setMovements(new Movement[]{surf,psychic,earthquake,hyperBeam});

            poobkemon.addPokemon(charizard.getName(), charizard);
            poobkemon.addPokemon(snorlax.getName(), snorlax);
            poobkemon.addPokemon(blastoise.getName(), blastoise);

            poobkemon.addPokemon(charizard.getName(), charizard1);
            poobkemon.addPokemon(snorlax.getName(), snorlax1);
            poobkemon.addPokemon(blastoise.getName(), blastoise1);
            //items


            //para player1
            Item revivePlayerOne = new Revive();
            Item defenseNormalPotion = new DefensePotion("Posion Defensa", "Aumenta la defensa.", PotionType.NORMAL);
            Item attackNormalPotion = new AttackPotion("Posion ataque", "Aumenta el ataque.", PotionType.NORMAL);
            Item psNormalPotion = new PsPotion("Posion vida", "Aumenta la vida.", PotionType.NORMAL);
            Item defenseSuperPotion = new DefensePotion("Posion Defensa", "Aumenta la defensa.", PotionType.SUPER);
            Item attackSuperPotion = new AttackPotion("Posion ataque", "Aumenta el ataque.", PotionType.SUPER);
            Item psSuperPotion = new PsPotion("Posion vida", "Aumenta la vida.", PotionType.SUPER);
            Item potion = new PsPotion("Potion","",PotionType.NORMAL);
            Item superPotion = new SuperPotion("Potion","A medicinal spray that restores a Pokémon's HP.",PotionType.SUPER);
            Item hyperPotion = new SuperPotion("Potion","A medicinal spray that restores a Pokémon's HP.",PotionType.HYPER);
            
            Item defenseNormalPotion1 = new DefensePotion("Posion Defensa", "Aumenta la defensa.", PotionType.NORMAL);
            Item attackNormalPotion1 = new AttackPotion("Posion ataque", "Aumenta el ataque.", PotionType.NORMAL);
            Item psNormalPotion1 = new PsPotion("Posion vida", "Aumenta la vida.", PotionType.NORMAL);
            Item defenseSuperPotion1 = new DefensePotion("Posion Defensa", "Aumenta la defensa.", PotionType.SUPER);
            Item attackSuperPotion1 = new AttackPotion("Posion ataque", "Aumenta el ataque.", PotionType.SUPER);
            Item psSuperPotion1 = new PsPotion("Posion vida", "Aumenta la vida.", PotionType.SUPER);
            Item potion1 = new PsPotion("Potion","",PotionType.NORMAL);
            Item superPotion1 = new SuperPotion("Potion","A medicinal spray that restores a Pokémon's HP.",PotionType.SUPER);
            Item hyperPotion1 = new SuperPotion("Potion","A medicinal spray that restores a Pokémon's HP.",PotionType.HYPER);
            
            //player 2
            Item revivePokemonPlayerTwo = new Revive();
            Item defenseNormalPotionPlayerTwo = new DefensePotion("Posion Defensa", "Aumenta la defensa.", PotionType.NORMAL);
            Item attackNormalPotionPlayerTwo = new AttackPotion("Posion ataque", "Aumenta el ataque.", PotionType.NORMAL);
            Item psNormalPotionPlayerTwo = new PsPotion("Posion vida", "Aumenta la vida.", PotionType.NORMAL);
            Item defenseSuperPotionPlayerTwo = new DefensePotion("Posion Defensa", "Aumenta la defensa.", PotionType.SUPER);
            Item attackSuperPotionPlayerTwo = new AttackPotion("Posion ataque", "Aumenta el ataque.", PotionType.SUPER);
            Item psSuperPotionPlayerTwo = new PsPotion("Posion vida", "Aumenta la vida.", PotionType.SUPER);
            Item potionPlayerTwo = new PsPotion("Potion","",PotionType.NORMAL);
            Item superPotionPlayerTwo = new SuperPotion("Potion","A medicinal spray that restores a Pokémon's HP.",PotionType.SUPER);
            Item hyperPotionPlayerTwo = new SuperPotion("Potion","A medicinal spray that restores a Pokémon's HP.",PotionType.HYPER);            

            
            Item revivePokemonPlayerTwoO = new Revive();
            Item defenseNormalPotionPlayerTwoO = new DefensePotion("Posion Defensa", "Aumenta la defensa.", PotionType.NORMAL);
            Item attackNormalPotionPlayerTwoO = new AttackPotion("Posion ataque", "Aumenta el ataque.", PotionType.NORMAL);
            Item psNormalPotionPlayerTwoO = new PsPotion("Posion vida", "Aumenta la vida.", PotionType.NORMAL);
            Item defenseSuperPotionPlayerTwoO = new DefensePotion("Posion Defensa", "Aumenta la defensa.", PotionType.SUPER);
            Item attackSuperPotionPlayerTwoO = new AttackPotion("Posion ataque", "Aumenta el ataque.", PotionType.SUPER);
            Item psSuperPotionPlayerTwoO = new PsPotion("Posion vida", "Aumenta la vida.", PotionType.SUPER);
            Item potionPlayerTwoO = new PsPotion("Potion","",PotionType.NORMAL);
            Item superPotionPlayerTwoO = new SuperPotion("Potion","A medicinal spray that restores a Pokémon's HP.",PotionType.SUPER);
            Item hyperPotionPlayerTwoO = new SuperPotion("Potion","A medicinal spray that restores a Pokémon's HP.",PotionType.HYPER);
            


            //creacion trainers
            //Trainer defensive = new DefensiveTrainer("defensivo");
            //Trainer expert = new ExpertTrainer("experto");
            //Trainer changing = new ChangingTrainer("asustado");
            //Trainer attacking = new AttackingTrainer("agresivo");
            Trainer defensive = new PlayerTrainer("tulio");
            Trainer changing = new PlayerTrainer("andrew");
            poobkemon.addTrainer(defensive.getName(), defensive);
            //poobkemon.addTrainer(expert.getName(), expert);
            poobkemon.addTrainer(changing.getName(), changing);
            //poobkemon.addTrainer(attacking.getName(), attacking);
            //poobkemon.addTrainer(player1.getName(), player1);
            //poobkemon.addTrainer(player2.getName(), player2);


            //inventarios
            //1 denfensive
            Inventory inventarioDefensive = new Inventory(); 
            inventarioDefensive.addPokemon(snorlax1);
            inventarioDefensive.addPokemon(charizard1);
            inventarioDefensive.addPokemon(blastoise1);
            
            inventarioDefensive.addItem(revivePlayerOne);
            inventarioDefensive.addItem(defenseNormalPotion);
            inventarioDefensive.addItem(psNormalPotion);
            inventarioDefensive.addItem(defenseSuperPotion);
            inventarioDefensive.addItem(attackSuperPotion);
            inventarioDefensive.addItem(psSuperPotion);
            inventarioDefensive.addItem(potion);
            inventarioDefensive.addItem(superPotion);


            //2
            Inventory inventarioChanging = new Inventory();
            inventarioChanging.addPokemon(snorlax);
            inventarioChanging.addPokemon(charizard);
            inventarioChanging.addPokemon(blastoise);
            inventarioChanging.addItem(revivePokemonPlayerTwo);
            inventarioChanging.addItem(hyperPotionPlayerTwoO);
            inventarioChanging.addItem(potionPlayerTwo);
            inventarioChanging.addItem(attackSuperPotionPlayerTwoO);
            
            

            
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
