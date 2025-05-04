package test;
import domain.*;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.awt.Color;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;


/*
 * POOBkemonTest
 */
public class POOBkemonTest implements Serializable {
    @Test
    public void shouldSaveGamePre(){
        POOBkemon poobkemon = new POOBkemon();
        TributeEffect paraly  = new TributeEffect("Efecto de paralizar ", "Reduce la velocidad", 2,new HashMap<String,Integer>(){{
            put("Velocity",-50);}});

        //creacion estados de effecto
            StatusEffect Paralyze = new StatusMovil("Paralisis", "Paralisa al pokemon reduciendo su velocidad.", 2,paraly,0.25);
            StatusEffect Freeze = new StatusEffect("congelado","Un Pokémon congelado",10,0.90);
            StatusEffect Sleep = new StatusEffect("Dormido","Un Pokémon dormido no puede realizar ningún movimiento durante su turno",5,1);
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
            MovementState paralyze = new MovementState("Paralyze","",30, 30,50, PokemonType.PLANTA,Paralyze, 60, 0);
            MovementState freeze = new MovementState("Congelar","",20, 20,50, PokemonType.HIELO,Freeze, 60, 0);
            MovementState sleep = new MovementState("Dormir","",10, 15,50, PokemonType.NORMAL,Sleep, 60, 0);
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
                poobkemon.addMovement(freeze);
                poobkemon.addMovement(sleep);
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
                System.out.println(e.getMessage());
            }

            //Pokemones con esos movs
            Pokemon venusaur = new Pokemon("Venasur",100,364,289,328,291,328,284,PokemonType.PLANTA,PokemonType.VENENO,3);
            Pokemon charizard = new Pokemon("Charizard",90,360,293,348,280,295,328,PokemonType.FUEGO,PokemonType.VOLADOR,6);
            Pokemon blastoise = new Pokemon("Blastoise",100,362,291,295,328,339,280,PokemonType.AGUA,null,9);
            Pokemon raichu = new Pokemon("Raichu",100,324,306,306,229,284,350,PokemonType.ELECTRICO,null,26);
            Pokemon machamp = new Pokemon("Machamp",100,384,394,251,284,295,229,PokemonType.LUCHA,null,68);
            Pokemon gengar = new Pokemon("Gengar",100,324,251,394,240,273,350,PokemonType.FANTASMA,PokemonType.VENENO,94);
            Pokemon dragonite = new Pokemon("Dragonite",100,386,403,328,317,328,284,PokemonType.DRAGON,PokemonType.VOLADOR,149);
            Pokemon togetic = new Pokemon("Togetic",100,314,196,284,295,339,196,PokemonType.HADA,PokemonType.VOLADOR,176);
            Pokemon donphan = new Pokemon("Donphan",100,384,372,240,372,240,218,PokemonType.TIERRA,null,232);
            Pokemon tyranitar = new Pokemon("Tyranitar",100,404,403,317,350,328,243,PokemonType.ROCA,PokemonType.SINIESTRO,248);
            Pokemon snorlax = new Pokemon("Snorlax",100,524,350,251,251,350,174,PokemonType.NORMAL,null,143);
            Pokemon delibird = new Pokemon("Delibird",100,294,229,251,207,207,273,PokemonType.HIELO,PokemonType.VOLADOR,225);
            Pokemon gardevoir = new Pokemon("Gardevoir",100,340,251,383,251,361,284,PokemonType.PSIQUICO,PokemonType.HADA,282);
            Pokemon metagross = new Pokemon("Metagross",100,364,404,317,394,306,262,PokemonType.ACERO,PokemonType.PSIQUICO,376);
            
            Pokemon venusaur1 = new Pokemon("Venasur",100,364,289,328,291,328,284,PokemonType.PLANTA,PokemonType.VENENO,3);
            Pokemon charizard1 = new Pokemon("Charizard",90,360,293,348,280,295,328,PokemonType.FUEGO,PokemonType.VOLADOR,6);
            Pokemon blastoise1 = new Pokemon("Blastoise",100,362,291,295,328,339,280,PokemonType.AGUA,null,9);
            Pokemon raichu1 = new Pokemon("Raichu",100,324,306,306,229,284,350,PokemonType.ELECTRICO,null,26);
            Pokemon machamp1 = new Pokemon("Machamp",100,384,394,251,284,295,229,PokemonType.LUCHA,null,68);
            Pokemon gengar1 = new Pokemon("Gengar",100,324,251,394,240,273,350,PokemonType.FANTASMA,PokemonType.VENENO,94);
            Pokemon dragonite1 = new Pokemon("Dragonite",100,386,403,328,317,328,284,PokemonType.DRAGON,PokemonType.VOLADOR,149);
            Pokemon togetic1 = new Pokemon("Togetic",100,314,196,284,295,339,196,PokemonType.HADA,PokemonType.VOLADOR,176);
            Pokemon donphan1 = new Pokemon("Donphan",100,384,372,240,372,240,218,PokemonType.TIERRA,null,232);
            Pokemon tyranitar1 = new Pokemon("Tyranitar",100,404,403,317,350,328,243,PokemonType.ROCA,PokemonType.SINIESTRO,248);
            Pokemon snorlax1 = new Pokemon("Snorlax",100,524,350,251,251,350,174,PokemonType.NORMAL,null,143);
            Pokemon delibird1 = new Pokemon("Delibird",100,294,229,251,207,207,273,PokemonType.HIELO,PokemonType.VOLADOR,225);
            Pokemon gardevoir1 = new Pokemon("Gardevoir",100,340,251,383,251,361,284,PokemonType.PSIQUICO,PokemonType.HADA,282);
            Pokemon metagross1 = new Pokemon("Metagross",100,364,404,317,394,306,262,PokemonType.ACERO,PokemonType.PSIQUICO,376);

                        
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


            //pokemones con movimientos predefinidos
            charizard1.setMovements(new Movement[] {paralyze, burn, quickAttack, dragonClaw});
            snorlax1.setMovements(new Movement[] {hyperBeam, dragonClaw, brickBreak, surf});
            blastoise1.setMovements(new Movement[] {surf, psychic, earthquake, hyperBeam});
            venusaur1.setMovements(new Movement[] {paralyze, poison, regenerate, earthquake});
            gengar1.setMovements(new Movement[] {shadowBall, psychic, poison, sleep});
            dragonite1.setMovements(new Movement[] {dragonClaw, earthquake, quickAttack, hyperBeam});
            togetic1.setMovements(new Movement[] {sleep, psychic, shadowBall, regenerate});
            tyranitar1.setMovements(new Movement[] {earthquake, burn, brickBreak, hyperBeam});
            gardevoir1.setMovements(new Movement[] {psychic, sleep, regenerate, shadowBall});
            metagross1.setMovements(new Movement[] {brickBreak, psychic, earthquake, burn});
            donphan1.setMovements(new Movement[] {earthquake, quickAttack, defense, burn});
            machamp1.setMovements(new Movement[] {brickBreak, quickAttack, hyperBeam, regenerate});
            delibird1.setMovements(new Movement[] {freeze, quickAttack, surf, paralyze});
            raichu1.setMovements(new Movement[] {electrocuted, quickAttack, paralyze, shadowBall});

            charizard.setMovements(new Movement[] {paralyze, burn, quickAttack, dragonClaw});
            snorlax.setMovements(new Movement[] {hyperBeam, dragonClaw, brickBreak, surf});
            blastoise.setMovements(new Movement[] {surf, psychic, earthquake, hyperBeam});
            venusaur.setMovements(new Movement[] {paralyze, poison, regenerate, earthquake});
            gengar.setMovements(new Movement[] {shadowBall, psychic, poison, sleep});
            dragonite.setMovements(new Movement[] {dragonClaw, earthquake, quickAttack, hyperBeam});
            togetic.setMovements(new Movement[] {sleep, psychic, shadowBall, regenerate});
            tyranitar.setMovements(new Movement[] {earthquake, burn, brickBreak, hyperBeam});
            gardevoir.setMovements(new Movement[] {psychic, sleep, regenerate, shadowBall});
            metagross.setMovements(new Movement[] {brickBreak, psychic, earthquake, burn});
            donphan.setMovements(new Movement[] {earthquake, quickAttack, defense, burn});
            machamp.setMovements(new Movement[] {brickBreak, quickAttack, hyperBeam, regenerate});
            delibird.setMovements(new Movement[] {freeze, quickAttack, surf, paralyze});
            raichu.setMovements(new Movement[] {electrocuted, quickAttack, paralyze, shadowBall});

            //items
            Item revive = new Revive();
            Item potion = new PsPotion("Potion","",PotionType.NORMAL);
            Item superPotion = new SuperPotion("Potion","A medicinal spray that restores a Pokémon's HP.",PotionType.SUPER);
            Item hyperPotion = new SuperPotion("Potion","A medicinal spray that restores a Pokémon's HP.",PotionType.HYPER);
            
            Item defenseNormalPotion = new DefensePotion("Posion Defensa", "Aumenta la defensa.", PotionType.NORMAL);
            Item attackNormalPotion = new AttackPotion("Posion ataque", "Aumenta el ataque.", PotionType.NORMAL);
            Item psNormalPotion = new PsPotion("Posion vida", "Aumenta la vida.", PotionType.NORMAL);
            Item defenseSuperPotion = new DefensePotion("Posion Defensa", "Aumenta la defensa.", PotionType.SUPER);
            Item attackSuperPotion = new AttackPotion("Posion ataque", "Aumenta el ataque.", PotionType.SUPER);
            Item psSuperPotion = new PsPotion("Posion vida", "Aumenta la vida.", PotionType.SUPER);
            Item defenseHyperPotion = new DefensePotion("Posion Defensa", "Aumenta la defensa.", PotionType.HYPER);
            Item attackSHyperPotion = new AttackPotion("Posion ataque", "Aumenta el ataque.", PotionType.HYPER);
            Item psHyperPotion = new PsPotion("Posion vida", "Aumenta la vida.", PotionType.HYPER);
            
            poobkemon.addItem(revive);
            poobkemon.addItem(potion);
            poobkemon.addItem(superPotion);
            poobkemon.addItem(hyperPotion);

            poobkemon.addItem(defenseNormalPotion);
            poobkemon.addItem(attackNormalPotion);
            poobkemon.addItem(psNormalPotion);

            poobkemon.addItem(defenseSuperPotion);
            poobkemon.addItem(attackSuperPotion);
            poobkemon.addItem(psSuperPotion);

            poobkemon.addItem(defenseHyperPotion);
            poobkemon.addItem(attackSHyperPotion);
            poobkemon.addItem(psHyperPotion);

            //creacion trainers
            Trainer defensive1 = new DefensiveTrainer("defensivo",new Color(0,1,255));
            Trainer expert = new ExpertTrainer("experto",new Color(3,0,255));
            Trainer changing1 = new ChangingTrainer("asustado",new Color(0,4,255));
            Trainer attacking = new AttackingTrainer("agresivo",new Color(0,50,255));

            Trainer defensive = new PlayerTrainer("tulio",new Color(0,0,255));
            Trainer changing = new PlayerTrainer("andrew",new Color(255,0,0));
            poobkemon.addTrainer(defensive);
            //poobkemon.addTrainer(expert.getName(), expert);
            poobkemon.addTrainer(changing);
            poobkemon.addTrainer(defensive1);
            poobkemon.addTrainer(expert);
            poobkemon.addTrainer(changing1);
            poobkemon.addTrainer(attacking);
            //poobkemon.addTrainer(attacking.getName(), attacking);
            //poobkemon.addTrainer(player1.getName(), player1);
            //poobkemon.addTrainer(player2.getName(), player2);
        try{
            //inventarios
            //1 denfensive
            Inventory inventarioDefensive = new Inventory(); 
            defensive.setInventory(inventarioDefensive);
            defensive.addPokemon(snorlax);
            defensive.addPokemon(charizard);
            defensive.addPokemon(blastoise);
            inventarioDefensive.addItem(defenseNormalPotion);
            inventarioDefensive.addItem(psNormalPotion);
            inventarioDefensive.addItem(revive);
            inventarioDefensive.addItem(attackSuperPotion);
            inventarioDefensive.addItem(revive);
            inventarioDefensive.addItem(superPotion);

            //2
            Inventory inventarioChanging = new Inventory();
            changing.setInventory(inventarioChanging);
            changing.addPokemon(snorlax);
            changing.addPokemon(charizard);
            changing.addPokemon(blastoise);
            inventarioChanging.addItem(psNormalPotion);
            inventarioChanging.addItem(defenseNormalPotion);
            inventarioChanging.addItem(revive);
            inventarioChanging.addItem(revive);
            System.out.println("Tarea completada.");
        
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace(); 
            fail(); 
        }
        try {
            poobkemon.serializateGame();
        } catch (Exception e) {
            System.out.println("Error: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
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
            e.printStackTrace();
            fail(); 
        }    
    }
    @Test
    public void shouldTrainerHavePokemons(){
        POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        Trainer t1 = po.getTrainer("tulio");
        assertEquals(t1.getInventory().getAlivePokemons().get(0).getLevel(),100);
    }

    @Test
    public void shouldDoTheRightMovement(){
        POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        Trainer t1 = po.getTrainer("tulio");
        Trainer t2 = po.getTrainer("andrew");
        int psInicial = t2.getPokemonInUse().getPs();
        po.movementPerfomrmed(t1,t2,t1.getPokemonInUse().getMovements().get(1),t2.getPokemonInUse().getMovements().get(0));
        int psFinal = t2.getPokemonInUse().getPs();
        assertNotEquals(psInicial,psFinal); // Es falso ya que el pokemon fue afectado por el movimiento
    }

    @Test
    public void shouldChangePokemon(){
        POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        Trainer t1 = po.getTrainer("tulio");
        Trainer t2 = po.getTrainer("andrew");
        Pokemon pokemonStart = t2.getPokemonInUse();
        po.movementPerfomrmed(t1,t2,t1.getPokemonInUse().getMovements().get(1),t2.getPokemonInUse().getMovements().get(0));
        try {
            t2.changePokemon(t2.getInventory().getAlivePokemons().get(1));
            Pokemon pokemonAfter = t2.getPokemonInUse();
            assertNotEquals(pokemonStart, pokemonAfter); // ESTE TEST FUNCIONA YA QUE BLASTOISE TRAS RECIBIR X CANTIDAD DE DAÑO LO CAMBIO POR CHARIZARD
            // POR LO QUE TANTO EL POKEMON INICIAL COMO EL FINAL SON DIFERENTES;
        } catch (Exception e) {
            System.out.println("Hola profe");
        }
    }
    @Test
    public void shouldDiePokemon(){
        POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        Trainer t1 = po.getTrainer("tulio");
        Trainer t2 = po.getTrainer("andrew");
        po.movementPerfomrmed(t1,t2,t1.getPokemonInUse().getMovements().get(1),t2.getPokemonInUse().getMovements().get(0));
        po.movementPerfomrmed(t1,t2,t1.getPokemonInUse().getMovements().get(1),t2.getPokemonInUse().getMovements().get(0));
        po.movementPerfomrmed(t1,t2,t1.getPokemonInUse().getMovements().get(1),t2.getPokemonInUse().getMovements().get(0));
        po.movementPerfomrmed(t1,t2,t1.getPokemonInUse().getMovements().get(1),t2.getPokemonInUse().getMovements().get(0));
        assertNotEquals(t2.getInventory().getPokemons().get("Blastoise").isAlive(), true);
        //Tras recibir varios tipos de ataques blastoise muere y si queremos imprimir el pokemon que tendria este entrenador seria el siguiente
        //vivo como es Charizard
        System.out.println(t2.getPokemonInUse().getName());
    }

    @Test
    public void shouldFightMachineVsMachine(){
        POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        Trainer t1 = po.getTrainer("asustado"); //changing
        Trainer t2 = po.getTrainer("agresivo"); //Attacking

        po.setTrainerTurns(t1, t2);

        int psInicial1 = t1.getPokemonInUse().getPs();
        int psInicial2 = t2.getPokemonInUse().getPs();

        po.actionOrderMM();

        int psFinal1 = t1.getPokemonInUse().getPs();
        int psFinal2 = t2.getPokemonInUse().getPs();

        assertNotEquals(psInicial1,psFinal1);

        //Las maquinas escogen respectivamente los mejores movimientos posibles
        //Debido a la prioridad que hay un pokemon se ve afectado o no
        //En este caso el pokemon del entrenador 1 es quien se ve afectado
        //Por lo que no van a ser iguales sus ps iniciales como finales.
    }
}
