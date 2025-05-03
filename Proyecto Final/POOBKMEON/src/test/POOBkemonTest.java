package test;
import domain.*;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import org.junit.Test;

/*
 * POOBkemonTest
 */
public class POOBkemonTest implements Serializable {
    @Test
    public void shouldSaveGamePre(){
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
            Movement hyperBeam = new PhysicalMovement("Hyper Beam","A devastating attack that requires a turn to recharge.",5,150,90,PokemonType.NORMAL,0);
            Movement quickAttack = new PhysicalMovement("Quick Attack","A fast attack that always strikes first.",30,40,100,PokemonType.NORMAL,1);
            Movement earthquake = new PhysicalMovement("Earthqueake","Causes an earthquake that affects all Pokémon on the field.",10,100,100,PokemonType.TIERRA,0);
            Movement psychic = new SpecialMovement("Psychic","A mental attack that can reduce the enemy's special defense.",10,90,100,PokemonType.PSIQUICO,0);
            Movement dragonClaw = new SpecialMovement("Dragon Claw","Strike the enemy with a sharp claw of dragon energy.",15,80,100,PokemonType.DRAGON,0);
            Movement shadowBall = new SpecialMovement("Shadow ball","Throws a ball of dark energy. You can lower the special defense.",15,80,100,PokemonType.FANTASMA,0);
            Movement brickBreak = new PhysicalMovement("Brick break","Break the barriers of Reflection and Light Display.",15,75,100,PokemonType.LUCHA,0);
            Movement surf = new SpecialMovement("Surf","A water attack that hits all Pokémon in battle, except your partner in double battles.",15,90,100,PokemonType.AGUA,0);
            
            //movimientos que puede escoger el usuario para un pokemon
            try{
                poobkemon.addMovement(paralyze);
                poobkemon.addMovement(burn);
                poobkemon.addMovement(poison);
                poobkemon.addMovement(defense);
                poobkemon.addMovement(regenerate);
                poobkemon.addMovement(electrocuted);
                poobkemon.addMovement(hyperBeam);
                poobkemon.addMovement(quickAttack);
                poobkemon.addMovement(earthquake);
                poobkemon.addMovement(psychic);
                poobkemon.addMovement(dragonClaw);
                poobkemon.addMovement(shadowBall);
                poobkemon.addMovement(brickBreak);
                poobkemon.addMovement(surf);}
            catch(PoobkemonException e){

            }

            //Pokemones con esos movs
            Pokemon charizard = new Pokemon("Charizard", 90, 360, 293, 348, 280, 295, 328, PokemonType.FUEGO, PokemonType.VOLADOR);            
            Pokemon snorlax = new Pokemon("Snorlax",100,524,350,251,251,350,174,PokemonType.NORMAL,null);
            Pokemon blastoise = new Pokemon("Blastoise",100,362,291,295,328,339,280,PokemonType.AGUA,null);
            Pokemon venusaur = new Pokemon("Venasur",100,364,289,328,291,328,284,PokemonType.PLANTA,PokemonType.VENENO);
            Pokemon gengar = new Pokemon("Gengar",100,324,251,394,240,273,350,PokemonType.FANTASMA,PokemonType.VENENO);
            Pokemon dragonite = new Pokemon("Dragonite",100,386,403,328,317,328,284,PokemonType.DRAGON,PokemonType.VOLADOR);
            Pokemon togetic = new Pokemon("Togetic",100,314,196,284,295,339,196,PokemonType.HADA,PokemonType.VOLADOR);
            Pokemon tyranitar = new Pokemon("Tyranitar",100,404,403,317,350,328,243,PokemonType.ROCA,PokemonType.SINIESTRO);
            Pokemon gardevoir = new Pokemon("Gardevoir",100,340,251,383,251,361,284,PokemonType.PSIQUICO,PokemonType.HADA);
            Pokemon metagross = new Pokemon("Metagross",100,364,404,317,394,306,262,PokemonType.ACERO,PokemonType.PSIQUICO);
            Pokemon donphan = new Pokemon("Donphan",100,384,372,240,372,240,218,PokemonType.TIERRA,null);
            Pokemon machamp = new Pokemon("Machamp",100,384,394,251,284,295,229,PokemonType.LUCHA,null);
            Pokemon delibird = new Pokemon("Delibird",100,294,229,251,207,207,273,PokemonType.HIELO,PokemonType.VOLADOR);
            Pokemon raichu = new Pokemon("Raichu",100,324,306,306,229,284,350,PokemonType.ELECTRICO,null);
            
            Pokemon charizard1 = new Pokemon("Charizard", 90, 360, 293, 348, 280, 295, 328, PokemonType.FUEGO, PokemonType.VOLADOR);
            Pokemon snorlax1 = new Pokemon("Snorlax",100,524,350,251,251,350,174,PokemonType.NORMAL,null);
            Pokemon blastoise1 = new Pokemon("Blastoise",100,362,291,295,328,339,280,PokemonType.AGUA,null);
            /*
            Pokemon venusaur1 = new Pokemon("Venasur",100,364,289,328,291,328,284,PokemonType.PLANTA,PokemonType.VENENO);
            Pokemon gengar1 = new Pokemon("Gengar",100,324,251,394,240,273,350,PokemonType.FANTASMA,PokemonType.VENENO);
            Pokemon dragonite1 = new Pokemon("Dragonite",100,386,403,328,317,328,284,PokemonType.DRAGON,PokemonType.VOLADOR);
            Pokemon togetic1 = new Pokemon("Togetic",100,314,196,284,295,339,196,PokemonType.HADA,PokemonType.VOLADOR);
            Pokemon tyranitar1 = new Pokemon("Tyranitar",100,404,403,317,350,328,243,PokemonType.ROCA,PokemonType.SINIESTRO);
            Pokemon gardevoir1 = new Pokemon("Gardevoir",100,340,251,383,251,361,284,PokemonType.PSIQUICO,PokemonType.HADA);
            Pokemon metagross1 = new Pokemon("Metagross",100,364,404,317,394,306,262,PokemonType.ACERO,PokemonType.PSIQUICO);
            Pokemon donphan1 = new Pokemon("Donphan",100,384,372,240,372,240,218,PokemonType.TIERRA,null);
            Pokemon machamp1 = new Pokemon("Machamp",100,384,394,251,284,295,229,PokemonType.LUCHA,null);
            Pokemon delibird1 = new Pokemon("Delibird",100,294,229,251,207,207,273,PokemonType.HIELO,PokemonType.VOLADOR);
            Pokemon raichu1 = new Pokemon("Raichu",100,324,306,306,229,284,350,PokemonType.ELECTRICO,null);
            */
            poobkemon.addPokemon(charizard);
            poobkemon.addPokemon(snorlax);
            poobkemon.addPokemon(blastoise);
            poobkemon.addPokemon(venusaur);
            poobkemon.addPokemon(gengar);
            poobkemon.addPokemon(dragonite);
            poobkemon.addPokemon(togetic);
            poobkemon.addPokemon(tyranitar);
            poobkemon.addPokemon(gardevoir);
            poobkemon.addPokemon(metagross);
            poobkemon.addPokemon(donphan);
            poobkemon.addPokemon(machamp);
            poobkemon.addPokemon(delibird);
            poobkemon.addPokemon(raichu);


            /*
            poobkemon.addPokemon(charizard1);
            poobkemon.addPokemon(snorlax1);
            poobkemon.addPokemon(blastoise1);
            poobkemon.addPokemon(venusaur1);
            poobkemon.addPokemon(gengar1);
            poobkemon.addPokemon(dragonite1);
            poobkemon.addPokemon(togetic1);
            poobkemon.addPokemon(tyranitar1);
            poobkemon.addPokemon(gardevoir1);
            poobkemon.addPokemon(metagross1);
            poobkemon.addPokemon(donphan1);
            poobkemon.addPokemon(machamp1);
            poobkemon.addPokemon(delibird1);
            poobkemon.addPokemon(raichu1);
             */

            //pokemones con movimientos predefinidos
            charizard.setMovements(new Movement[] {paralyze,burn,quickAttack,dragonClaw});
            snorlax.setMovements(new Movement[]{hyperBeam,dragonClaw,brickBreak,surf});
            blastoise.setMovements(new Movement[]{surf,psychic,earthquake,hyperBeam});

            /*
            charizard1.setMovements(new Movement[] {paralyze,burn,quickAttack,dragonClaw});
            snorlax1.setMovements(new Movement[]{hyperBeam,dragonClaw,brickBreak,surf});
            blastoise1.setMovements(new Movement[]{surf,psychic,earthquake,hyperBeam});
            */

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
            /*
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
            */


            //creacion trainers
            //Trainer defensive = new DefensiveTrainer("defensivo");
            //Trainer expert = new ExpertTrainer("experto");
            //Trainer changing = new ChangingTrainer("asustado");
            //Trainer attacking = new AttackingTrainer("agresivo");
            Trainer defensive = new PlayerTrainer("tulio");
            Trainer changing = new PlayerTrainer("andrew");
            poobkemon.addTrainer(defensive);
            //poobkemon.addTrainer(expert.getName(), expert);
            poobkemon.addTrainer(changing);
            //poobkemon.addTrainer(attacking.getName(), attacking);
            //poobkemon.addTrainer(player1.getName(), player1);
            //poobkemon.addTrainer(player2.getName(), player2);
        try{
            //inventarios
            //1 denfensive
            Inventory inventarioDefensive = new Inventory(); 
            defensive.setInventory(inventarioDefensive);
            defensive.addPokemon(snorlax1);
            defensive.addPokemon(charizard1);
            defensive.addPokemon(blastoise1);
            inventarioDefensive.addItem(defenseNormalPotion);
            inventarioDefensive.addItem(psNormalPotion);
            inventarioDefensive.addItem(revivePlayerOne);
            inventarioDefensive.addItem(attackSuperPotion);
            inventarioDefensive.addItem(revivePlayerOne);
            inventarioDefensive.addItem(superPotion);

            //2
            Inventory inventarioChanging = new Inventory();
            changing.setInventory(inventarioChanging);
            changing.addPokemon(snorlax);
            changing.addPokemon(charizard);
            changing.addPokemon(blastoise);
            inventarioChanging.addItem(psNormalPotion);
            inventarioChanging.addItem(defenseNormalPotion);
            inventarioChanging.addItem(revivePlayerOne);
            inventarioChanging.addItem(revivePlayerOne);
            System.out.println("sIIIIIIIIII");
        
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace(); // Esto mostrará la causa exacta del error
            fail(); // Esto marcará explícitamente la prueba como fallida
        }
        try {
            poobkemon.serializateGame();
        } catch (Exception e) {
            System.out.println("Error: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace(); // Esto mostrará la causa exacta del error
            fail(); 
        }
        
    }
    @Test
    public void deserializateGame(){
        try{
            POOBkemon poobkemon = new POOBkemon();
            poobkemon.deserializateGame();
        }
        catch(Exception e){
            System.out.println("Error: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace(); // Esto mostrará la causa exacta del error
            fail(); 
        }    
    }

    
}
