package test;
import domain.*;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;

import org.junit.jupiter.api.Test;

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
            StatusEffect Paralyze = new StatusMovil("Paralisis", "Paralisa al pokemon reduciendo su velocidad.", 2,paraly,1);
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
// Efectos de tributo adicionales
            TributeEffect confusionEffect = new TributeEffect("Efecto de confusión", "Puede hacer que el Pokémon se ataque a sí mismo.", 3,
                    new HashMap<String, Integer>() {{
                        put("Accuracy", -20);
                    }});

            TributeEffect flinchEffect = new TributeEffect("Efecto de retroceso", "Impide que el Pokémon actúe en su turno.", 1,
                    new HashMap<String, Integer>() {{
                        put("Priority", -100);
                    }});

            TributeEffect badlyPoisonEffect = new TributeEffect("Efecto de envenenamiento grave", "Causa daño creciente en cada turno.", 5,
                    new HashMap<String, Integer>() {{
                        put("PS", -15);
                        // El daño aumenta cada turno, representado por el tiempo de duración
                    }});

            TributeEffect infatuationEffect = new TributeEffect("Efecto de enamoramiento", "El Pokémon puede no atacar por amor.", 4,
                    new HashMap<String, Integer>() {{
                        put("Accuracy", -50);
                    }});

            TributeEffect leechSeedEffect = new TributeEffect("Efecto de drenadoras", "Drena PS cada turno y los transfiere al oponente.", 8,
                    new HashMap<String, Integer>() {{
                        put("PS", -20);
                    }});

            TributeEffect raiseAttackEffect = new TributeEffect("Aumentar Ataque", "Aumenta considerablemente el ataque del Pokémon.", 3,
                    new HashMap<String, Integer>() {{
                        put("Attack", 80);
                    }});

            TributeEffect raiseDefenseEffect = new TributeEffect("Aumentar Defensa", "Aumenta considerablemente la defensa del Pokémon.", 3,
                    new HashMap<String, Integer>() {{
                        put("Defense", 80);
                    }});

            TributeEffect raiseSpecialEffect = new TributeEffect("Aumentar Especial", "Aumenta el ataque y defensa especial del Pokémon.", 3,
                    new HashMap<String, Integer>() {{
                        put("Special Attack", 50);
                        put("Special Defense", 50);
                    }});

            TributeEffect raiseAttackSpeedEffect = new TributeEffect("Aumentar Ataque y Velocidad", "Aumenta el ataque y la velocidad del Pokémon.", 3,
                    new HashMap<String, Integer>() {{
                        put("Attack", 50);
                        put("Velocity", 50);
                    }});

    // Creación de estados de efecto
            StatusEffect Confusion = new StatusMovil("Confusión", "El Pokémon está confundido y puede dañarse a sí mismo.", 3, confusionEffect, 0.33);
            StatusEffect Flinch = new StatusEffect("Retroceso", "El Pokémon retrocede y no puede moverse este turno.", 1, 1.0);
            StatusEffect BadlyPoison = new StatusMovil("Gravemente Envenenado", "El veneno causa daño creciente cada turno.", 5, badlyPoisonEffect, 0.95);
            StatusEffect Infatuation = new StatusMovil("Enamoramiento", "El Pokémon está enamorado y puede no atacar.", 4, infatuationEffect, 0.50);
            StatusEffect LeechSeed = new StatusMovil("Drenadoras", "Semillas que drenan PS en cada turno y los transfieren al oponente.", 8, leechSeedEffect, 1.0);
            TributeEffect RaiseAttack = new TributeEffect("Aumentar Ataque", "Aumenta considerablemente el ataque.", 3,
                    new HashMap<String, Integer>() {{
                        put("Attack", 80);
                    }});
            TributeEffect RaiseDefense = new TributeEffect("Aumentar Defensa", "Aumenta considerablemente la defensa.", 3,
                    new HashMap<String, Integer>() {{
                        put("Defense", 80);
                    }});
            TributeEffect RaiseSpecial = new TributeEffect("Aumentar Especial", "Aumenta el ataque y defensa especial.", 3,
                    new HashMap<String, Integer>() {{
                        put("Special Attack", 50);
                        put("Special Defense", 50);
                    }});
            TributeEffect RaiseAttackSpeed = new TributeEffect("Aumentar Ataque y Velocidad", "Aumenta el ataque y velocidad.", 3,
                    new HashMap<String, Integer>() {{
                        put("Attack", 50);
                        put("Velocity", 50);
                    }});

            
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

            /*MovementState confused = new MovementState("Confundir", "Confunde al oponente y puede atacarse a sí mismo.", 20, 0, 100, PokemonType.PSIQUICO, Confusion, 70, 0);
            MovementState flinch = new MovementState("Intimidar", "Puede hacer retroceder al oponente.", 15, 65, 85, PokemonType.SINIESTRO, Flinch, 30, 0);
            MovementState toxic = new MovementState("Tóxico", "Envenena gravemente al oponente con daño creciente.", 10, 0, 90, PokemonType.VENENO, BadlyPoison, 100, 0);
            MovementState attract = new MovementState("Atracción", "Enamora al oponente del género opuesto.", 15, 0, 100, PokemonType.NORMAL, Infatuation, 100, 0);
            MovementState leechSeed = new MovementState("Drenadoras", "Planta semillas que drenan PS del oponente cada turno.", 10, 0, 90, PokemonType.PLANTA, LeechSeed, 100, 0);
			*/
            MovementTribute swordsDance = new MovementTribute("Danza Espada", "Eleva mucho el ataque.", 20, 0, 100, PokemonType.NORMAL, RaiseAttack, 0);
            MovementTribute ironDefense = new MovementTribute("Defensa Férrea", "Aumenta considerablemente la defensa.", 15, 0, 100, PokemonType.ACERO, RaiseDefense, 0);
            MovementTribute calmMind = new MovementTribute("Paz Mental", "Aumenta ataque especial y defensa especial.", 20, 0, 100, PokemonType.PSIQUICO, RaiseSpecial, 0);
            MovementTribute dragonDance = new MovementTribute("Danza Dragón", "Aumenta ataque y velocidad.", 20, 0, 100, PokemonType.DRAGON, RaiseAttackSpeed, 0);
            MovementTribute willOWisp = new MovementTribute("Fuego Fatuo", "Quema al oponente.", 15, 0, 85, PokemonType.FUEGO, Burn, 100);
            
            PhysicalMovement closeCombat = new PhysicalMovement("A Bocajarro", "Poderoso ataque que reduce defensas propias.", 5, 120, 100, PokemonType.LUCHA, 0);
            PhysicalMovement outrage = new PhysicalMovement("Enfado", "Ataca durante 2-3 turnos pero confunde al usuario.", 10, 120, 100, PokemonType.DRAGON, 0);
            PhysicalMovement rockSlide = new PhysicalMovement("Avalancha", "Lanza rocas que pueden hacer retroceder.", 10, 75, 90, PokemonType.ROCA, 30);
            PhysicalMovement ironHead = new PhysicalMovement("Cabeza Hierro", "Golpea con cabeza metálica, puede hacer retroceder.", 15, 80, 100, PokemonType.ACERO, 30);
            PhysicalMovement crunch = new PhysicalMovement("Triturar", "Muerde con colmillos afilados, puede bajar defensa.", 15, 80, 100, PokemonType.SINIESTRO, 20);
            PhysicalMovement leafBlade = new PhysicalMovement("Hoja Aguda", "Corta con hojas afiladas, alta prob. de crítico.", 15, 90, 100, PokemonType.PLANTA, 0);
            PhysicalMovement xScissor = new PhysicalMovement("Tijera X", "Corta al oponente en forma de X con guadañas.", 15, 80, 100, PokemonType.BICHO, 0);
            PhysicalMovement playRough = new PhysicalMovement("Carantoña", "Juega rudamente, puede bajar el ataque del rival.", 10, 90, 90, PokemonType.HADA, 10);
            PhysicalMovement poisonJab = new PhysicalMovement("Puya Nociva", "Ataca con tentáculo o brazo tóxico, puede envenenar.", 20, 80, 100, PokemonType.VENENO, 30);
            PhysicalMovement aquaJet = new PhysicalMovement("Acua Jet", "Ataque rápido de agua que siempre golpea primero.", 20, 40, 100, PokemonType.AGUA, 1);
            PhysicalMovement bravebird = new PhysicalMovement("Pájaro Osado", "Ataque temerario que también daña al usuario.", 15, 120, 100, PokemonType.VOLADOR, 0);

    // MOVIMIENTOS ESPECIALES
            SpecialMovement flamethrower = new SpecialMovement("Lanzallamas", "Lanza fuego intenso que puede quemar.", 15, 90, 100, PokemonType.FUEGO, 10);
            SpecialMovement thunderbolt = new SpecialMovement("Rayo", "Descarga eléctrica que puede paralizar.", 15, 90, 100, PokemonType.ELECTRICO, 10);
            SpecialMovement iceBeam = new SpecialMovement("Rayo Hielo", "Rayo congelante que puede congelar al oponente.", 10, 90, 100, PokemonType.HIELO, 10);
            SpecialMovement darkPulse = new SpecialMovement("Pulso Umbrío", "Onda siniestra que puede hacer retroceder.", 15, 80, 100, PokemonType.SINIESTRO, 20);
            SpecialMovement energyBall = new SpecialMovement("Energibola", "Bombardea con energía natural, puede bajar def. especial.", 10, 90, 100, PokemonType.PLANTA, 10);
            SpecialMovement moonblast = new SpecialMovement("Fuerza Lunar", "Ataca con poder lunar, puede bajar ataque especial.", 15, 95, 100, PokemonType.HADA, 30);
            SpecialMovement flashCannon = new SpecialMovement("Foco Resplandor", "Rayo de luz que puede bajar defensa especial.", 10, 80, 100, PokemonType.ACERO, 10);
            SpecialMovement bugBuzz = new SpecialMovement("Zumbido", "Vibración de alas que puede bajar defensa especial.", 10, 90, 100, PokemonType.BICHO, 10);
            SpecialMovement earthPower = new SpecialMovement("Tierra Viva", "La tierra explota bajo el rival, puede bajar def. especial.", 10, 90, 100, PokemonType.TIERRA, 10);
            SpecialMovement dragonPulse = new SpecialMovement("Pulso Dragón", "Onda de choque con forma de dragón.", 10, 85, 100, PokemonType.DRAGON, 0);
            SpecialMovement hurricane = new SpecialMovement("Vendaval", "Poderoso tornado que puede confundir.", 10, 110, 70, PokemonType.VOLADOR, 30);
            SpecialMovement sludgeBomb = new SpecialMovement("Bomba Lodo", "Lanza fango que puede envenenar.", 10, 90, 100, PokemonType.VENENO, 30);
            SpecialMovement focusBlast = new SpecialMovement("Onda Certera", "Concentra energía y libera un golpe, puede bajar def. especial.", 5, 120, 70, PokemonType.LUCHA, 10);
            SpecialMovement hydroPump = new SpecialMovement("Hidrobomba", "Potente chorro de agua a presión.", 5, 110, 80, PokemonType.AGUA, 0);
            SpecialMovement powerGem = new SpecialMovement("Joya de Luz", "Ataca con rayos de luz que parecen joyas.", 20, 80, 100, PokemonType.ROCA, 0);
            SpecialMovement dreameater = new SpecialMovement("Come Sueños", "Absorbe PS de un oponente dormido.", 15, 100, 100, PokemonType.PSIQUICO, 0);

    // MOVIMIENTOS MIXTOS (FÍSICOS/ESPECIALES ÚNICOS)
            Movement solarBeam = new SpecialMovement("Rayo Solar", "Absorbe luz un turno y ataca en el siguiente.", 10, 120, 100, PokemonType.PLANTA, 0);
            Movement fakeOut = new PhysicalMovement("Intimidación", "Siempre ataca primero y hace retroceder, sólo funciona el primer turno.", 10, 40, 100, PokemonType.NORMAL, 100);
            Movement megaDrain = new SpecialMovement("Mega Drenado", "Absorbe la mitad del daño causado.", 15, 40, 100, PokemonType.PLANTA, 0);
            Movement hyperVoice = new SpecialMovement("Vozarrón", "Ataca con un potente grito.", 10, 90, 100, PokemonType.NORMAL, 0);
            Movement thunderPunch = new PhysicalMovement("Puño Trueno", "Puñetazo eléctrico que puede paralizar.", 15, 75, 100, PokemonType.ELECTRICO, 10);
            Movement firePunch = new PhysicalMovement("Puño Fuego", "Puñetazo ardiente que puede quemar.", 15, 75, 100, PokemonType.FUEGO, 10);
            Movement icePunch = new PhysicalMovement("Puño Hielo", "Puñetazo helado que puede congelar.", 15, 75, 100, PokemonType.HIELO, 10);
            Movement drillPeck = new PhysicalMovement("Pico Taladro", "Picotea con pico giratorio a gran velocidad.", 20, 80, 100, PokemonType.VOLADOR, 0);
            Movement stoneedge = new PhysicalMovement("Roca Afilada", "Ataque con piedras puntiagudas, alta prob. de crítico.", 5, 100, 80, PokemonType.ROCA, 0);
            Movement waterPulse = new SpecialMovement("Pulso Agua", "Ondas de agua que pueden confundir.", 20, 60, 100, PokemonType.AGUA, 20);
            Movement gigaDrain = new SpecialMovement("Giga Drenado", "Absorbe la mitad del daño causado.", 10, 75, 100, PokemonType.PLANTA, 0);
            Movement airSlash = new SpecialMovement("Tajo Aéreo", "Corta con aire comprimido, puede hacer retroceder.", 15, 75, 95, PokemonType.VOLADOR, 30);
            Movement zenHeadbutt = new PhysicalMovement("Cabezazo Zen", "Concentra poder psíquico y golpea, puede hacer retroceder.", 15, 80, 90, PokemonType.PSIQUICO, 20);
            Movement shadowClaw = new PhysicalMovement("Garra Umbría", "Ataca con sombra afilada, alta prob. de crítico.", 15, 70, 100, PokemonType.FANTASMA, 0);
            Movement psychoCut = new PhysicalMovement("Psicocorte", "Corta con cuchillas psíquicas, alta prob. de crítico.", 20, 70, 100, PokemonType.PSIQUICO, 0);
            Movement poisonFang = new PhysicalMovement("Colmillo Veneno", "Muerde con colmillos tóxicos, puede envenenar gravemente.", 15, 50, 100, PokemonType.VENENO, 50);

            //movimientos que puede escoger el usuario para un pokemon
            try{
                poobkemon.addMovement(swordsDance);
                poobkemon.addMovement(ironDefense);
                poobkemon.addMovement(willOWisp);
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
                poobkemon.addMovement(surf);
                //poobkemon.addMovement(confused);
                //poobkemon.addMovement(flinch);
                //poobkemon.addMovement(toxic);
                //poobkemon.addMovement(attract);
                //poobkemon.addMovement(leechSeed);

                //poobkemon.addMovement(swordsDance);
                //poobkemon.addMovement(ironDefense);
                poobkemon.addMovement(calmMind);
                poobkemon.addMovement(dragonDance);
                //poobkemon.addMovement(willOWisp);

                poobkemon.addMovement(closeCombat);
                poobkemon.addMovement(outrage);
                poobkemon.addMovement(rockSlide);
                poobkemon.addMovement(ironHead);
                poobkemon.addMovement(crunch);
                poobkemon.addMovement(leafBlade);
                poobkemon.addMovement(xScissor);
                poobkemon.addMovement(playRough);
                poobkemon.addMovement(poisonJab);
                poobkemon.addMovement(aquaJet);
                poobkemon.addMovement(bravebird);

                // Añadir los movimientos especiales
                poobkemon.addMovement(flamethrower);
                poobkemon.addMovement(thunderbolt);
                poobkemon.addMovement(iceBeam);
                poobkemon.addMovement(darkPulse);
                poobkemon.addMovement(energyBall);
                poobkemon.addMovement(moonblast);
                poobkemon.addMovement(flashCannon);
                poobkemon.addMovement(bugBuzz);
                poobkemon.addMovement(earthPower);
                poobkemon.addMovement(dragonPulse);
                poobkemon.addMovement(hurricane);
                poobkemon.addMovement(sludgeBomb);
                poobkemon.addMovement(focusBlast);
                poobkemon.addMovement(hydroPump);
                poobkemon.addMovement(powerGem);
                poobkemon.addMovement(dreameater);

                // Añadir los movimientos mixtos
                poobkemon.addMovement(solarBeam);
                poobkemon.addMovement(fakeOut);
                poobkemon.addMovement(megaDrain);
                poobkemon.addMovement(hyperVoice);
                poobkemon.addMovement(thunderPunch);
                poobkemon.addMovement(firePunch);
                poobkemon.addMovement(icePunch);
                poobkemon.addMovement(drillPeck);
                poobkemon.addMovement(stoneedge);
                poobkemon.addMovement(waterPulse);
                poobkemon.addMovement(gigaDrain);
                poobkemon.addMovement(airSlash);
                poobkemon.addMovement(zenHeadbutt);
                poobkemon.addMovement(shadowClaw);
                poobkemon.addMovement(psychoCut);
                poobkemon.addMovement(poisonFang);

                System.out.println("si sirve");
            }    
            catch(PoobkemonException e){
                System.out.println(e.getMessage());
            }

            Pokemon venusaur   = new Pokemon("Venusaur", 100, 364, 289, 328, 291, 328, 284, PokemonType.PLANTA, PokemonType.VENENO, 3);
            Pokemon charizard  = new Pokemon("Charizard", 100, 360, 293, 348, 280, 295, 328, PokemonType.FUEGO, PokemonType.VOLADOR, 6);
            Pokemon blastoise  = new Pokemon("Blastoise", 100, 362, 291, 295, 328, 339, 280, PokemonType.AGUA, null, 9);
            Pokemon raichu     = new Pokemon("Raichu", 100, 324, 306, 306, 229, 284, 350, PokemonType.ELECTRICO, null, 26);
            Pokemon nidoking   = new Pokemon("Nidoking", 100, 364, 328, 295, 284, 273, 295, PokemonType.VENENO, PokemonType.TIERRA, 34);
            Pokemon clefable   = new Pokemon("Clefable", 100, 394, 251, 284, 317, 328, 251, PokemonType.HADA, null, 36);
            Pokemon rapidash   = new Pokemon("Rapidash", 100, 331, 289, 258, 269, 289, 339, PokemonType.FUEGO, null, 78);
            Pokemon alakazam   = new Pokemon("Alakazam", 100, 314, 189, 339, 369, 249, 299, PokemonType.PSIQUICO, null, 65);
            Pokemon machamp    = new Pokemon("Machamp",100,384,394,251,284,295,229,PokemonType.LUCHA,null,68);
            Pokemon victreebel = new Pokemon("Victreebel", 100, 344, 339, 258, 289, 259, 289, PokemonType.PLANTA, PokemonType.VENENO, 71);
            Pokemon slowbro    = new Pokemon("Slowbro",100,394,306,328,339,273,174,PokemonType.AGUA,PokemonType.PSIQUICO,80);
            Pokemon magneton   = new Pokemon("Magneton", 100, 261, 289, 328, 349, 239, 249, PokemonType.ELECTRICO, PokemonType.ACERO, 82);
            Pokemon dodrio     = new Pokemon("Dodrio", 100, 321, 349, 239, 249, 289, 339, PokemonType.NORMAL, PokemonType.VOLADOR, 85);
            Pokemon arcanine   = new Pokemon("Arcanine", 100, 384, 350, 317, 295, 284, 328, PokemonType.FUEGO, null, 59);
            Pokemon hitmonlee  = new Pokemon("Hitmonlee", 100, 281, 339, 239, 249, 289, 299, PokemonType.LUCHA, null, 106);
            Pokemon hitmonchan = new Pokemon("Hitmonchan", 100, 281, 289, 239, 249, 289, 299, PokemonType.LUCHA, null, 107);
            Pokemon gengar     = new Pokemon("Gengar", 100, 324, 251, 394, 240, 273, 350, PokemonType.FANTASMA, PokemonType.VENENO, 94);
            Pokemon mrMime     = new Pokemon("Mr. Mime", 100, 304, 239, 309, 329, 289, 319, PokemonType.PSIQUICO, PokemonType.HADA, 122);
            Pokemon magmar     = new Pokemon("Magmar", 100, 323, 339, 239, 329, 289, 309, PokemonType.FUEGO, null, 126);
            Pokemon tauros     = new Pokemon("Tauros", 100, 353, 329, 239, 249, 337, 309, PokemonType.NORMAL, null, 128);
            Pokemon lapras     = new Pokemon("Lapras", 100, 464, 289, 259, 299, 289, 209, PokemonType.AGUA, PokemonType.HIELO, 131);
            Pokemon rhydon     = new Pokemon("Rhydon", 100, 414, 394, 306, 262, 273, 196, PokemonType.TIERRA, PokemonType.ROCA, 112);
            Pokemon gyarados   = new Pokemon("Gyarados", 100, 394, 383, 284, 317, 328, 287, PokemonType.AGUA, PokemonType.VOLADOR, 130);
            Pokemon snorlax    = new Pokemon("Snorlax", 100, 524, 350, 251, 251, 350, 174, PokemonType.NORMAL, null, 143);
            Pokemon moltres    = new Pokemon("Moltres", 100, 384, 328, 317, 361, 284, 328, PokemonType.FUEGO, PokemonType.VOLADOR, 146);
            Pokemon dragonite  = new Pokemon("Dragonite", 100, 386, 403, 328, 317, 328, 284, PokemonType.DRAGON, PokemonType.VOLADOR, 149);
            Pokemon mewtwo     = new Pokemon("Mewtwo", 100, 416, 349, 309, 369, 309, 359, PokemonType.PSIQUICO, null, 150);
            Pokemon typhlosion = new Pokemon("Typhlosion",100,360,293,317,328,306,328,PokemonType.FUEGO,null,157);
            Pokemon feraligatr = new Pokemon("Feraligatr",100,384,339,306,295,284,306,PokemonType.AGUA,null,160);
            Pokemon scizor     = new Pokemon("Scizor", 100, 344, 389, 259, 249, 289, 259, PokemonType.BICHO, PokemonType.ACERO, 212);
            Pokemon togetic    = new Pokemon("Togetic", 100, 314, 196, 284, 295, 339, 196, PokemonType.HADA, PokemonType.VOLADOR, 176);
            Pokemon ursaring   = new Pokemon("Ursaring", 100, 424, 394, 273, 251, 273, 229, PokemonType.NORMAL, null, 217);
            Pokemon delibird   = new Pokemon("Delibird", 100, 294, 229, 251, 207, 207, 273, PokemonType.HIELO, PokemonType.VOLADOR, 225);
            Pokemon donphan    = new Pokemon("Donphan", 100, 384, 372, 240, 372, 240, 218, PokemonType.TIERRA, null, 232);
            Pokemon tyranitar  = new Pokemon("Tyranitar", 100, 404, 403, 317, 350, 328, 243, PokemonType.ROCA, PokemonType.SINIESTRO, 248);
            Pokemon sceptile   = new Pokemon("Sceptile", 100, 344, 289, 299, 349, 299, 339, PokemonType.PLANTA, null, 254);
            Pokemon blaziken   = new Pokemon("Blaziken", 100, 364, 372, 284, 339, 273, 328, PokemonType.FUEGO, PokemonType.LUCHA, 257);
            Pokemon swampert   = new Pokemon("Swampert", 100, 404, 349, 299, 259, 299, 219, PokemonType.AGUA, PokemonType.TIERRA, 260);
            Pokemon gardevoir  = new Pokemon("Gardevoir", 100, 340, 251, 383, 251, 361, 284, PokemonType.PSIQUICO, PokemonType.HADA, 282);
            Pokemon slaking    = new Pokemon("Slaking", 100, 504, 460, 273, 328, 284, 273, PokemonType.NORMAL, null, 289);
            Pokemon hariyama   = new Pokemon("Hariyama", 100, 474, 359, 239, 249, 239, 199, PokemonType.LUCHA, null, 297);
            Pokemon aggron     = new Pokemon("Aggron", 100, 344, 429, 259, 259, 259, 199, PokemonType.ACERO, PokemonType.ROCA, 306);
            Pokemon manectric  = new Pokemon("Manectric", 100, 324, 249, 309, 339, 279, 319, PokemonType.ELECTRICO, null, 310);
            Pokemon crawdaunt  = new Pokemon("Crawdaunt", 100, 314, 349, 239, 299, 269, 209, PokemonType.AGUA, PokemonType.SINIESTRO, 342);
            Pokemon milotic    = new Pokemon("Milotic", 100, 394, 249, 289, 329, 349, 219, PokemonType.AGUA, null, 350);
            Pokemon metagross  = new Pokemon("Metagross", 100, 364, 404, 317, 394, 306, 262, PokemonType.ACERO, PokemonType.PSIQUICO, 376);
            
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
            poobkemon.addPokemon(nidoking);
            poobkemon.addPokemon(clefable);
            poobkemon.addPokemon(arcanine);
            poobkemon.addPokemon(slowbro);
            poobkemon.addPokemon(rhydon);
            poobkemon.addPokemon(gyarados);
            poobkemon.addPokemon(moltres);
            poobkemon.addPokemon(typhlosion);
            poobkemon.addPokemon(feraligatr);
            poobkemon.addPokemon(ursaring);
            poobkemon.addPokemon(blaziken);
            poobkemon.addPokemon(slaking);
            poobkemon.addPokemon(victreebel);
            poobkemon.addPokemon(magneton);
            poobkemon.addPokemon(dodrio);
            poobkemon.addPokemon(hitmonlee);
            poobkemon.addPokemon(hitmonchan);
            poobkemon.addPokemon(mrMime);
            poobkemon.addPokemon(magmar);
            poobkemon.addPokemon(tauros);
            poobkemon.addPokemon(lapras);
            poobkemon.addPokemon(rapidash);
            poobkemon.addPokemon(alakazam);
            poobkemon.addPokemon(sceptile);
            poobkemon.addPokemon(scizor);
            poobkemon.addPokemon(hariyama);
            poobkemon.addPokemon(aggron);
            poobkemon.addPokemon(manectric);
            poobkemon.addPokemon(crawdaunt);
            poobkemon.addPokemon(milotic);
            poobkemon.addPokemon(mewtwo);
            poobkemon.addPokemon(swampert);

            Pokemon charizardOne   = charizard.copyPokemon();
            Pokemon venusaurOne    = venusaur.copyPokemon();
            Pokemon blastoiseOne   = blastoise.copyPokemon();
            Pokemon raichuOne      = raichu.copyPokemon();
            Pokemon nidokingOne    = nidoking.copyPokemon();
            Pokemon clefableOne    = clefable.copyPokemon();
            Pokemon rapidashOne    = rapidash.copyPokemon();
            Pokemon alakazamOne    = alakazam.copyPokemon();
            Pokemon victreebelOne  = victreebel.copyPokemon();
            Pokemon magnetonOne    = magneton.copyPokemon();
            Pokemon dodrioOne      = dodrio.copyPokemon();
            Pokemon arcanineOne    = arcanine.copyPokemon();
            Pokemon machampOne     = machamp.copyPokemon();
            Pokemon slowbroOne     = slowbro.copyPokemon();
            Pokemon gengarOne      = gengar.copyPokemon();
            Pokemon hitmonleeOne   = hitmonlee.copyPokemon();
            Pokemon hitmonchanOne  = hitmonchan.copyPokemon();
            Pokemon rhydonOne      = rhydon.copyPokemon();
            Pokemon mrMimeOne      = mrMime.copyPokemon();
            Pokemon magmarOne      = magmar.copyPokemon();
            Pokemon taurosOne      = tauros.copyPokemon();
            Pokemon gyaradosOne    = gyarados.copyPokemon();
            Pokemon laprasOne      = lapras.copyPokemon();
            Pokemon snorlaxOne     = snorlax.copyPokemon();
            Pokemon moltresOne     = moltres.copyPokemon();
            Pokemon dragoniteOne   = dragonite.copyPokemon();
            Pokemon mewtwoOne      = mewtwo.copyPokemon();
            Pokemon typhlosionOne  = typhlosion.copyPokemon();
            Pokemon feraligatrOne  = feraligatr.copyPokemon();
            Pokemon togeticOne     = togetic.copyPokemon();
            Pokemon ursaringOne    = ursaring.copyPokemon();
            Pokemon scizorOne      = scizor.copyPokemon();
            Pokemon delibirdOne    = delibird.copyPokemon();
            Pokemon donphanOne     = donphan.copyPokemon();
            Pokemon tyranitarOne   = tyranitar.copyPokemon();
            Pokemon sceptileOne    = sceptile.copyPokemon();
            Pokemon blazikenOne    = blaziken.copyPokemon();
            Pokemon swampertOne    = swampert.copyPokemon();
            Pokemon gardevoirOne   = gardevoir.copyPokemon();
            Pokemon slakingOne     = slaking.copyPokemon();
            Pokemon hariyamaOne    = hariyama.copyPokemon();
            Pokemon aggronOne      = aggron.copyPokemon();
            Pokemon manectricOne   = manectric.copyPokemon();
            Pokemon crawdauntOne   = crawdaunt.copyPokemon();
            Pokemon miloticOne     = milotic.copyPokemon();
            Pokemon metagrossOne   = metagross.copyPokemon();
            try{charizardOne.setMovements(new Movement[] {paralyze, burn, flamethrower, energyBall});
                snorlaxOne.setMovements(new Movement[] {shadowBall,sleep,flamethrower,surf});
                blastoiseOne.setMovements(new Movement[] {surf, aquaJet, hydroPump, iceBeam});
                venusaurOne.setMovements(new Movement[] {paralyze, poison, gigaDrain,hydroPump});
                gengarOne.setMovements(new Movement[] {willOWisp, thunderbolt,flamethrower,firePunch});
                dragoniteOne.setMovements(new Movement[] {swordsDance, hyperBeam, flamethrower,firePunch});
                delibirdOne.setMovements(new Movement[] {freeze, iceBeam, icePunch, xScissor});
                raichuOne.setMovements(new Movement[] {electrocuted, thunderbolt, thunderPunch,xScissor});
                nidokingOne.setMovements(new Movement[] {earthquake, poison, poisonJab, earthPower});
                clefableOne.setMovements(new Movement[] {xScissor, bugBuzz, poisonJab, sludgeBomb});
                slowbroOne.setMovements(new Movement[] {surf, iceBeam, hydroPump, aquaJet});
                rhydonOne.setMovements(new Movement[] {earthquake, earthPower,iceBeam,hydroPump});
                gyaradosOne.setMovements(new Movement[] {surf, hydroPump, aquaJet, iceBeam});
                moltresOne.setMovements(new Movement[] {flamethrower, burn, firePunch,hydroPump});
                typhlosionOne.setMovements(new Movement[] {flamethrower, burn, firePunch,aquaJet});
                feraligatrOne.setMovements(new Movement[] {surf, hydroPump, iceBeam, aquaJet});
                ursaringOne.setMovements(new Movement[] {flamethrower,shadowBall, shadowClaw, hydroPump});
                blazikenOne.setMovements(new Movement[] {flamethrower, burn, firePunch, thunderbolt});
                slakingOne.setMovements(new Movement[] {shadowBall, surf, hydroPump, iceBeam});
                victreebelOne.setMovements(new Movement[] {gigaDrain, poison, energyBall, sludgeBomb});
                donphanOne.setMovements(new Movement[] {earthquake, hydroPump, icePunch,xScissor});
                machampOne.setMovements(new Movement[] {xScissor, bugBuzz, moonblast, poisonJab});
                magnetonOne.setMovements(new Movement[] {thunderbolt, thunderPunch, electrocuted,bugBuzz});
                dodrioOne.setMovements(new Movement[] {xScissor, shadowBall, shadowClaw, energyBall});
                hitmonleeOne.setMovements(new Movement[] {xScissor, bugBuzz, moonblast, poisonJab});
                hitmonchanOne.setMovements(new Movement[] {xScissor, bugBuzz, moonblast, poisonJab});
                mrMimeOne.setMovements(new Movement[] {psychic, zenHeadbutt, dreameater, calmMind});
                magmarOne.setMovements(new Movement[] {flamethrower, burn, firePunch,shadowClaw});
                taurosOne.setMovements(new Movement[] {shadowBall, shadowClaw,hydroPump,surf});
                laprasOne.setMovements(new Movement[] {iceBeam, surf, hydroPump, freeze});
                rapidashOne.setMovements(new Movement[] {flamethrower, burn, firePunch,surf});
                alakazamOne.setMovements(new Movement[] {psychic, zenHeadbutt, dreameater, calmMind});
                sceptileOne.setMovements(new Movement[] {gigaDrain, energyBall, leafBlade, megaDrain});
                scizorOne.setMovements(new Movement[] {burn, flamethrower, firePunch,surf});
                hariyamaOne.setMovements(new Movement[] {xScissor, bugBuzz, moonblast, poisonJab});
                aggronOne.setMovements(new Movement[] {burn, flamethrower, firePunch,thunderPunch});
                manectricOne.setMovements(new Movement[] {thunderbolt, thunderPunch, electrocuted,aquaJet});
                crawdauntOne.setMovements(new Movement[] {surf, aquaJet, iceBeam, hydroPump});
                miloticOne.setMovements(new Movement[] {hydroPump, surf, aquaJet, waterPulse});
                mewtwoOne.setMovements(new Movement[] {psychic, zenHeadbutt, dreameater, calmMind});
                swampertOne.setMovements(new Movement[] {surf, aquaJet, hydroPump, waterPulse});
                arcanineOne.setMovements(new Movement[] {psychic,hydroPump,flamethrower,firePunch});
                togeticOne.setMovements(new Movement[] {flamethrower, burn, firePunch,bugBuzz});
                gardevoirOne.setMovements(new Movement[] {psychic,hydroPump,firePunch,freeze});
                metagrossOne.setMovements(new Movement[] {burn, firePunch, flamethrower, poison});
                tyranitarOne.setMovements(new Movement[] {burn,crunch,flamethrower,bravebird});
                }
            catch(PoobkemonException e){
                System.out.println(e.getMessage());
                fail();
            }

            System.out.println(mewtwoOne.getMovements().toString());
            System.out.println(swampertOne.getMovements().toString());

            Pokemon mewtwoTwo = mewtwoOne.copyWithMovements();
            Pokemon swampertTwo = swampertOne.copyWithMovements();

            System.out.println(mewtwoTwo.getMovements().toString());
            System.out.println(swampertTwo.getMovements().toString());

            //items
            Item revive = new Revive();
            Item potion = new PsPotion("potion","A medicinal spray that restores a Pokémon's HP.",PotionType.NORMAL);
            Item psSuperPotion = new PsPotion("superPotion", "A medicinal spray that restores a Pokémon's HP.", PotionType.SUPER);
            Item psNormalPotion = new PsPotion("hyperPotion", "A medicinal spray that restores a Pokémon's HP.", PotionType.HYPER);

            Item defenseNormalPotion = new DefensePotion("Defense hyperPotion", "Aumenta la defensa.", PotionType.NORMAL);
            Item defenseSuperPotion = new DefensePotion("Defense hyperPotion", "Aumenta la defensa.", PotionType.SUPER);
            Item defenseHyperPotion = new DefensePotion("Defense hyperPotion", "Aumenta la defensa.", PotionType.HYPER);

            Item attackNormalPotion = new AttackPotion("Attack potion", "Aumenta el ataque.", PotionType.NORMAL);
            Item attackSuperPotion = new AttackPotion("Attack superPotion", "Aumenta el ataque.", PotionType.SUPER);
            Item attackSHyperPotion = new AttackPotion("Attack hyperPotion", "Aumenta el ataque.", PotionType.HYPER);

            Item superPotion = new SuperPotion("All superPotion","Aumenta los atributos basicos de un pokemon.",PotionType.SUPER);
            Item hyperPotion = new HyperPotion("All hyperPotion","Aumenta los atributos especiales de un pokemon.",PotionType.HYPER);

            poobkemon.addItem(defenseNormalPotion);
            poobkemon.addItem(attackNormalPotion);
            poobkemon.addItem(psNormalPotion);

            poobkemon.addItem(defenseSuperPotion);
            poobkemon.addItem(attackSuperPotion);
            poobkemon.addItem(psSuperPotion);

            poobkemon.addItem(defenseHyperPotion);
            poobkemon.addItem(attackSHyperPotion);
            
            poobkemon.addItem(revive);
            poobkemon.addItem(potion);
            poobkemon.addItem(superPotion);
            poobkemon.addItem(hyperPotion);
            //creacion trainers
            Trainer defensiveOne = new DefensiveTrainer("Defensive",new Color(0,1,255));
            Trainer expert = new ExpertTrainer("Expert",new Color(3,0,255));
            Trainer changingOne = new ChangingTrainer("Changing",new Color(0,4,255));
            Trainer attacking = new AttackingTrainer("Attacking",new Color(0,50,255));

            Trainer defensive = new PlayerTrainer("tulio",new Color(0,0,255));
            Trainer changing = new PlayerTrainer("andrew",new Color(255,0,0));

            poobkemon.addTrainer(defensive);
            poobkemon.addTrainer(changing);
            poobkemon.addTrainer(expert);
            poobkemon.addTrainer(attacking);

            poobkemon.addTrainer(changingOne);
            poobkemon.addTrainer(defensiveOne);
        try{
            //inventarios
            //1 denfensive
            Inventory inventarioDefensive = new Inventory(); 
            defensive.setInventory(inventarioDefensive);
            defensive.addPokemon(snorlaxOne.copyWithMovements());
            defensive.addPokemon(rhydonOne.copyWithMovements());
            defensive.addPokemon(blastoiseOne.copyWithMovements());
            defensive.addPokemon(gyaradosOne.copyWithMovements());
            defensive.addPokemon(moltresOne.copyWithMovements());
            defensive.addPokemon(typhlosionOne.copyWithMovements());
            inventarioDefensive.addItem(defenseNormalPotion);
            inventarioDefensive.addItem(psNormalPotion);
            inventarioDefensive.addItem(revive);
            inventarioDefensive.addItem(attackSuperPotion);
            inventarioDefensive.addItem(revive);
            inventarioDefensive.addItem(superPotion);

            //2
            Inventory inventarioChanging = new Inventory();
            changing.setInventory(inventarioChanging);
            changing.addPokemon(clefableOne.copyWithMovements());
            changing.addPokemon(machampOne.copyWithMovements());
            changing.addPokemon(slakingOne.copyWithMovements());
            changing.addPokemon(feraligatrOne.copyWithMovements());
            changing.addPokemon(ursaringOne.copyWithMovements());
            changing.addPokemon(rhydonOne.copyWithMovements());
            inventarioChanging.addItem(psNormalPotion);
            inventarioChanging.addItem(defenseNormalPotion);
            inventarioChanging.addItem(revive);
            inventarioChanging.addItem(revive);


            //3
            Inventory inventarioChangingOne = new Inventory();
            changingOne.setInventory(inventarioChangingOne);
            changingOne.addPokemon(dragoniteOne.copyWithMovements());
            changingOne.addPokemon(venusaurOne.copyWithMovements());
            changingOne.addPokemon(tyranitarOne.copyWithMovements());
            changingOne.addPokemon(gyaradosOne.copyWithMovements());
            changingOne.addPokemon(moltresOne.copyWithMovements());
            changingOne.addPokemon(blazikenOne.copyWithMovements());
            inventarioChangingOne.addItem(psNormalPotion);
            inventarioChangingOne.addItem(defenseNormalPotion);
            inventarioChangingOne.addItem(revive);
            inventarioChangingOne.addItem(revive);
            System.out.println("Tarea completada.");
            //4
            Inventory inventarioAttacking = new Inventory();
            attacking.setInventory(inventarioAttacking);
            attacking.addPokemon(raichuOne.copyWithMovements());
            attacking.addPokemon(tyranitarOne.copyWithMovements());
            attacking.addPokemon(dragoniteOne.copyWithMovements());
            attacking.addPokemon(blazikenOne.copyWithMovements());
            attacking.addPokemon(gardevoirOne.copyWithMovements());
            attacking.addPokemon(gyaradosOne.copyWithMovements());
            inventarioAttacking.addItem(psNormalPotion);
            inventarioAttacking.addItem(defenseNormalPotion);
            inventarioAttacking.addItem(revive);
            inventarioAttacking.addItem(revive);
            //5
            Inventory inventarioExpert = new Inventory();
            expert.setInventory(inventarioExpert);
            expert.addPokemon(venusaurOne.copyWithMovements());
            expert.addPokemon(gyaradosOne.copyWithMovements());
            expert.addPokemon(metagrossOne.copyWithMovements());
            expert.addPokemon(dragoniteOne.copyWithMovements());
            expert.addPokemon(gardevoirOne.copyWithMovements());
            expert.addPokemon(rhydonOne.copyWithMovements());
            inventarioExpert.addItem(psNormalPotion);
            inventarioExpert.addItem(defenseNormalPotion);
            inventarioExpert.addItem(revive);
            inventarioExpert.addItem(revive);

            //6
            Inventory inventarioDefensiveOne = new Inventory();
            defensiveOne.setInventory(inventarioDefensiveOne);
            defensiveOne.addPokemon(venusaurOne.copyWithMovements());
            defensiveOne.addPokemon(slowbroOne.copyWithMovements());
            defensiveOne.addPokemon(clefableOne.copyWithMovements());
            defensiveOne.addPokemon(rhydonOne.copyWithMovements());
            defensiveOne.addPokemon(togeticOne.copyWithMovements());
            defensiveOne.addPokemon(tyranitarOne.copyWithMovements());
            inventarioDefensiveOne.addItem(psNormalPotion);
            inventarioDefensiveOne.addItem(defenseNormalPotion);
            inventarioDefensiveOne.addItem(revive);
            inventarioDefensiveOne.addItem(revive);


            System.out.println("Tarea completada.");
            System.out.println("Tarea completada.");
            System.out.println("Tarea completada inventarios.");
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
        Trainer trainerOne = po.getTrainer("tulio");
        assertEquals(trainerOne.getInventory().getAlivePokemons().get(0).getLevel(),100);
    }
    @Test
    public void shouldDoTheRightMovement(){
        POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        Trainer t1 = po.getTrainer("tulio");
        Trainer t2 = po.getTrainer("andrew");
        try{
            t1.setPokemonInUse("Snorlax");
            t2.setPokemonInUse("Clefable");
        }
        catch(PoobkemonException e){ System.out.println(e.getMessage());fail();}

        int psInicial = t2.getPokemonInUse().getPs();

        po.inicializateBattlePVsP("tulio", "andrew");
        try{
            po.movementPerformed("Hyper Beam");
            int psFinal = t2.getPokemonInUse().getPs();
            assertNotEquals(psInicial,psFinal); // Es falso ya que el pokemon fue afectado por el movimiento

        }catch (PoobkemonException e){
            fail();
        }
    }
    @Test
    public void shouldChangePokemon(){
        POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        Trainer t1 = po.getTrainer("tulio");
        Trainer t2 = po.getTrainer("andrew");
        Pokemon pokemonStart = t2.getPokemonInUse();

        po.inicializateBattlePVsP("tulio", "andrew");
        try {
            po.movementPerformed("dragonClaw");
            t2.changePokemon(t2.getInventory().getAlivePokemons().get(1));
            Pokemon pokemonAfter = t2.getPokemonInUse();
            assertNotEquals(pokemonStart, pokemonAfter); // ESTE TEST FUNCIONA YA QUE BLASTOISE TRAS RECIBIR X CANTIDAD DE DAÑO LO CAMBIO POR CHARIZARD
            // POR LO QUE TANTO EL POKEMON INICIAL COMO EL FINAL SON DIFERENTES;
        } catch (Exception e) {
            System.out.println("Los pokemones son iguales, tras la funcionalidad de cambio ");
        }
    }
    @Test
    public void shouldDiePokemon(){
        POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        Trainer t1 = po.getTrainer("tulio");
        Trainer t2 = po.getTrainer("andrew");
        po.inicializateBattlePVsP("tulio", "andrew");
        try {
            po.movementPerformed(t1.getPokemonInUse().getMovements().get(0).getName());
            po.movementPerformed(t1.getPokemonInUse().getMovements().get(0).getName());
            po.movementPerformed(t1.getPokemonInUse().getMovements().get(0).getName());
            po.movementPerformed(t1.getPokemonInUse().getMovements().get(0).getName());
            po.movementPerformed(t1.getPokemonInUse().getMovements().get(0).getName());
            po.movementPerformed(t1.getPokemonInUse().getMovements().get(0).getName());
            po.movementPerformed(t1.getPokemonInUse().getMovements().get(0).getName());
            po.movementPerformed(t1.getPokemonInUse().getMovements().get(0).getName());
            po.movementPerformed(t1.getPokemonInUse().getMovements().get(0).getName());
            po.movementPerformed(t1.getPokemonInUse().getMovements().get(0).getName());
            po.movementPerformed(t1.getPokemonInUse().getMovements().get(0).getName());
            po.movementPerformed(t1.getPokemonInUse().getMovements().get(0).getName());
            assertNotEquals(t2.getInventory().getPokemons().get("Blastoise").isAlive(), true);
            System.out.println(t2.getPokemonInUse().getName());
        } catch (PoobkemonException e) {
            System.out.println(t2.getPokemonInUse().getName());
        }
        //Tras recibir varios tipos de ataques blastoise muere y si queremos imprimir el pokemon que tendria este entrenador seria el siguiente
        //vivo como es Charizard
    }
    @Test
    public void shouldFightMachineVsMachine(){

        POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        Trainer t1 = po.getTrainer("Changing"); //changing
        Trainer t2 = po.getTrainer("Attacking"); //Attacking

        po.inicializateBattleMvsM("Changing", "Attacking");

        int psInicial1 = t1.getPokemonInUse().getPs();
        int psInicial2 = t2.getPokemonInUse().getPs();
        try {
            po.movementPerformed(t2.getPokemonInUse().getMovements().get(0).getName());
            int psFinal1 = t1.getPokemonInUse().getPs();
            int psFinal2 = t2.getPokemonInUse().getPs();
            assertNotEquals(psInicial1,psFinal1);
        } catch (PoobkemonException e) {
            fail("Son iguales los ps, no fue exitoso el ataque entre maquinas");
        }
        //Las maquinas escogen respectivamente los mejores movimientos posibles
        //Debido a la prioridad que hay un pokemon se ve afectado o no
        //En este caso el pokemon del entrenador 1 es quien se ve afectado
        //Por lo que no van a ser iguales sus ps iniciales como finales.
    }
    @Test
    public void shouldInicializateBattle() {
    	POOBkemon poobkemon = new POOBkemon();
    	Trainer t1 = new PlayerTrainer("Ash", new Color(255,255,0));
    	Trainer t2 = new PlayerTrainer("Gary", new Color(0, 0, 255));
    	poobkemon.addTrainer(t1);
    	poobkemon.addTrainer(t2);
    	poobkemon.inicializateBattlePVsP(t1.getName(), t2.getName());
    	assertEquals(t1,poobkemon.getTrainer(t1.getName()));
    	/*
    	 * Al inicializar una batalla, se agregan dos nuevos entrenadores, verificamos que si se agreguen al TreeMap que tenemos definido
    	 * en poobkemon.
    	 * */
    }
    @Test
    public void testItemRevivePokemon() {
    	Pokemon p = new Pokemon("Gengar",100,324,251,394,240,273,350,PokemonType.FANTASMA, PokemonType.VENENO,94);
    	p.losePS(324);
    	Item revive = new Revive();
    	assertFalse(p.isAlive());
    	try {
    		revive.useItem(p);
    		assertTrue(p.isAlive());
    	}catch(PoobkemonException e) {
    		fail("El Pokemon no fue revivido");
    	}
    }
    @Test 
    public void shouldCopy(){
        Movement hyperBeam = new PhysicalMovement("Hyper Beam","A devastating attack that requires a turn to recharge.",5,150,90,PokemonType.NORMAL,0);
        Movement quickAttack = new PhysicalMovement("Quick Attack","A fast attack that always strikes first.",30,40,100,PokemonType.NORMAL,1);

        Pokemon charizard  = new Pokemon("Charizard", 100, 360, 293, 348, 280, 295, 328, PokemonType.FUEGO, PokemonType.VOLADOR, 6);
        Pokemon blastoise  = new Pokemon("Blastoise", 100, 362, 291, 295, 328, 339, 280, PokemonType.AGUA, null, 9);
        try{charizard.setMovements(new Movement[] {hyperBeam});
        blastoise.setMovements(new Movement[] {quickAttack});}catch(PoobkemonException e){System.out.println(e.getMessage());}
        
        Pokemon charizard1  = charizard.copyWithMovements();
        Pokemon blastoise1  = blastoise.copyWithMovements();
        
        assertFalse(charizard.getMovements().get(0).equals(charizard1.getMovements().get(0)));
        assertFalse(blastoise.getMovements().get(0).equals(blastoise1.getMovements().get(0)));
        assertFalse(charizard.equals(charizard1));
        assertFalse(blastoise.equals(blastoise1));
    }

    @Test
    public void shouldAddMovementToPoobkemon() {
    	POOBkemon poobkemon = new POOBkemon();
    	Movement move = new PhysicalMovement("Tackle","Physical attack",35,40,100,PokemonType.NORMAL, 0);
    	try {
	    	poobkemon.addMovement(move);
	    	assertTrue(poobkemon.getMovements().containsKey("Tackle"));
    	} catch(PoobkemonException e) {
    		fail ("El pokemon no fue añadido de manera correcta al TreeMap de movimientos");
    	}
    }
    @Test
    public void testAddItemToPoobkemon() {
    	POOBkemon poobkemon = new POOBkemon();
    	Item potion = new PsPotion("Potion","Heals 20 Hp", PotionType.NORMAL);
    	poobkemon.addItem(potion);
    	assertTrue(poobkemon.getItems().containsKey("Potion"));
    }
    @Test
    public void shouldHealThePokemon() {
    	Pokemon p = new Pokemon("Gengar",100,324,251,394,240,273,350,PokemonType.FANTASMA, PokemonType.VENENO,94);
    	Item potion = new PsPotion("Potion","Heals 20 Hp", PotionType.NORMAL);
    	p.losePS(200);
    	try {
    	potion.useItem(p);
    	assertTrue(p.getPs() == 144);
    	} catch(PoobkemonException e) {
    		fail("El pokemon no recupero 20 puntos de salud");
    	}
    }
    @Test
    public void shouldHealLikeHyperPotionThePokemon() {
    	Pokemon p = new Pokemon("Gengar",100,324,251,394,240,273,350,PokemonType.FANTASMA, PokemonType.VENENO,94);
    	Item potion = new PsPotion("Ps Potion","A medicinal spray that restores a Pokémon's HP.",PotionType.HYPER);
    	p.losePS(200);
    	try {
    	potion.useItem(p);
    	assertTrue(p.getPs() == 324);
    	} catch(PoobkemonException e) {
    		fail("El pokemon no recupero 20 puntos de salud");
    	}
    }  
    @Test
    public void shouldHealLikeSuperPotionThePokemon() {
    	Pokemon p = new Pokemon("Gengar",100,324,251,394,240,273,350,PokemonType.FANTASMA, PokemonType.VENENO,94);
    	Item superPotion = new SuperPotion("superPotion","A medicinal spray that restores a Pokémon's HP.",PotionType.SUPER);
    	p.losePS(200);
    	try {
    	superPotion.useItem(p);
    	assertTrue(p.getPs() == 174);
    	} catch(PoobkemonException e) {
    		fail("El pokemon no recupero 50 puntos de salud");
    	}
    }
    @Test
    public void shouldActiveStruggleMovement() {
    	Pokemon p = new Pokemon("Gengar",100,324,251,394,240,273,350,PokemonType.FANTASMA, PokemonType.VENENO,94);
    	Pokemon p1 = new Pokemon("Gengar",100,300,251,394,240,273,350,PokemonType.AGUA, PokemonType.DRAGON,94);
    	Movement m = new PhysicalMovement("Tackle","Physical attack",2,40,100,PokemonType.NORMAL, 0);
    	try{p.setMovements(new Movement[] { m } );}catch(PoobkemonException e){System.out.println(e.getMessage());}
    	int initialPs = p.getPs();
    	try {
    		m.losePP();
    		m.losePP();
    	}catch(PoobkemonException e) {
    		fail("No se activo el movimiento de struggle");
    	}
        try {
    		p.useMovement(m, p1);
    	}catch(PoobkemonException e) {
    		fail("No se activo el movimiento de struggle");
    	}
        assertFalse(initialPs == p.getPs());
    }
    @Test
    public void shouldGenerateARandomSelectionForMovements() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon poobkemon = kemon.deserializateGame();
    	try{
            ArrayList<Movement> prueba = poobkemon.generateRandomMovementForPokemon(poobkemon.getPokemon("Gengar"));
            assertTrue(prueba.size() == 4);
        }
        catch(PoobkemonException e){fail();}
    	
    }
    @Test
    public void shouldGenerateAValidMovements() {//es debil al fantasma y si acepta normal
        POOBkemon kemon = new POOBkemon();
        Pokemon p = new Pokemon("Gengar",100,324,251,394,240,273,350,PokemonType.FANTASMA, PokemonType.VENENO,94);
        kemon.addPokemon(p);
        Movement shadowBall = new SpecialMovement("Shadow ball","Throws a ball of dark energy. You can lower the special defense.",15,80,100,PokemonType.FANTASMA,0);
        Movement hyperBeam = new PhysicalMovement("Hyper Beam","A devastating attack that requires a turn to recharge.",5,150,90,PokemonType.NORMAL,0);
        try{kemon.addMovement(hyperBeam);kemon.addMovement(shadowBall);}
        catch(PoobkemonException e){System.out.println(e.getMessage());}

        ArrayList<String> pruebaMovimientosValidos = kemon.validMovements(p);
        assertTrue(pruebaMovimientosValidos.size() == 1);
    }

    @Test
    public void shouldBeEqualsThePokemons() {
    	Pokemon p = new Pokemon("Gengar",100,324,251,394,240,273,350,PokemonType.FANTASMA, PokemonType.VENENO,94);
    	Pokemon pequals = new Pokemon("Gengar",100,324,251,394,240,273,350,PokemonType.FANTASMA, PokemonType.VENENO,94);
    	assertTrue(p.equals(pequals));
    }
    @Test
    public void shouldGetThePokemonTypesRight() {
    	Pokemon p = new Pokemon("Gengar",100,324,251,394,240,273,350,PokemonType.FANTASMA, PokemonType.VENENO,94);
    	ArrayList<PokemonType> prueba = p.getTypes();
    	assertEquals(prueba.get(0),PokemonType.FANTASMA);
    }
    
    @Test
    public void shouldGetTheNonWeakMovements() {
    	POOBkemon kemon = new POOBkemon();
        POOBkemon poobkemon = kemon.deserializateGame();
    	Pokemon p = new Pokemon("Gengar",100,324,251,394,240,273,350,PokemonType.AGUA, PokemonType.FUEGO,94);
    	ArrayList<String> movementsNoWeak = poobkemon.validMovements(p);
    	assertTrue(movementsNoWeak.size() > 0);
    }

    @Test
    public void shoudlBeAbleToDoTheMove() {
    	Movement m = new PhysicalMovement("Tackle","Physical attack",2,40,100,PokemonType.NORMAL, 0);
    	assertTrue(m.canMakeMove());
    }
    @Test
    public void shouldDoTheMovementSpecialRight() {
    	Pokemon venusaur = new Pokemon("Venusaur",100,364,289,328,291,328,284,PokemonType.PLANTA,PokemonType.VENENO,3);
    	Pokemon charizard = new Pokemon("Charizard",100,360,293,348,280,295,328,PokemonType.FUEGO,PokemonType.VOLADOR,6);
    	Movement solarBeam = new SpecialMovement("Rayo Solar", "Absorbe luz un turno y ataca en el siguiente.", 10, 120, 100, PokemonType.PLANTA, 0);
    	try{venusaur.setMovements(new Movement[] {solarBeam});}catch(PoobkemonException e){System.out.println(e.getMessage());}
    	int inicialPs = charizard.getPs();
    	try {
    	venusaur.useMovement(solarBeam, charizard);
    	assertNotEquals(inicialPs,charizard.getPs());
    	} catch(PoobkemonException e) {
    		fail("El movimiento no pudo ser usado.");
    	}
    }
    @Test
    public void shouldFightAttackingTrainer() {
    	POOBkemon kemon = new POOBkemon();
        POOBkemon poobkemon = kemon.deserializateGame();
    	Trainer t1 = poobkemon.getTrainer("Attacking");
        Trainer t2 = poobkemon.getTrainer("tulio");
    	try {
            poobkemon.inicializateBattlePVsM(t1.getName(),t2.getName());
            poobkemon.movementPerformed(t1.getPokemonInUse().getMovements().get(0).getName());
            assertTrue(t2.getPokemonInUse().getPs() < t2.getPokemonInUse().getMaxPs());
    	}catch(PoobkemonException e) {
    		fail("El attacking trainer fallo o no se pudo hacer movimiento");
    	}
    }
    @Test
    public void shouldDoSomethingDefensiveTrainer() {
    	POOBkemon kemon = new POOBkemon();
    	POOBkemon poobkemon = kemon.deserializateGame();
    	Trainer t1 = poobkemon.getTrainer("Defensive");
        Trainer t2 = poobkemon.getTrainer("andrew");
    	try {
            poobkemon.inicializateBattlePVsM(t1.getName(),t2.getName());
            poobkemon.movementPerformed(t1.getPokemonInUse().getMovements().get(0).getName());
            poobkemon.movementPerformed(t2.getPokemonInUse().getMovements().get(0).getName());

    	}catch(PoobkemonException e) {
    		fail("El Movement Defensive fallo o no se pudo hacer movimiento");
    	}
    }
    @Test
    public void shouldImproveTheAttack() {
    	Pokemon venusaur = new Pokemon("Venusaur",100,364,289,328,291,328,284,PokemonType.PLANTA,PokemonType.VENENO,3);
    	Item attackNormalPotion = new AttackPotion("hyperPotion", "Aumenta el ataque.", PotionType.NORMAL);
    	try {
    		attackNormalPotion.useItem(venusaur);
    		assertTrue(venusaur.getAttack() == 309);
    	} catch (PoobkemonException e) {
    		fail("La attack potion no aumento las estadisticas");
    	}
    }
    @Test
    public void shouldImproveTheDefense() {
    	Pokemon venusaur = new Pokemon("Venusaur",100,364,289,328,291,328,284,PokemonType.PLANTA,PokemonType.VENENO,3);
    	Item defenseHyperPotion = new DefensePotion("hyperPotion", "Aumenta la defensa.", PotionType.HYPER);
    	int defenseInitial = venusaur.getDefense();
    	try {
    		defenseHyperPotion.useItem(venusaur);
    		assertFalse(defenseInitial == venusaur.getDefense());
    	} catch (PoobkemonException e) {
    		fail("La attack potion no aumento las estadisticas");
    	}
    }
    @Test
    public void shouldGetTurnTrainers() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon poobkemon = kemon.deserializateGame();
        Trainer t1 = poobkemon.getTrainer("tulio");
        Trainer t2 = poobkemon.getTrainer("andrew");
    	poobkemon.inicializateBattlePVsP(t1.getName(), t2.getName());
		ArrayList<Trainer> listaTra = poobkemon.getBattle().getTurnTrainers(); 
		System.out.println(listaTra.size());
		assertTrue(listaTra.get(0).getName() == t1.getName());
    }
    
    @Test
    public void shouldGetOpponentTrainer() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon poobkemon = kemon.deserializateGame();
        Trainer t1 = poobkemon.getTrainer("tulio");
        Trainer t2 = poobkemon.getTrainer("andrew");
        poobkemon.inicializateBattlePVsP(t1.getName(), t2.getName());
        Trainer opponentTrainer = poobkemon.getBattle().getOpponentTrainer();
        assertEquals(t2.getName(), opponentTrainer.getName());
    }

    @Test
    public void shouldGetCurrentPokemonName() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon poobkemon = kemon.deserializateGame();
        Trainer t1 = poobkemon.getTrainer("tulio");
        Trainer t2 = poobkemon.getTrainer("andrew");
        poobkemon.inicializateBattlePVsP(t1.getName(), t2.getName());
        String pokemonName = poobkemon.getBattle().getCurrentPokemonName();
        assertEquals(t1.getPokemonInUse().getName(), pokemonName);
    }

    @Test
    public void shouldGetCurrentPokemonLevel() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon poobkemon = kemon.deserializateGame();
        Trainer t1 = poobkemon.getTrainer("tulio");
        Trainer t2 = poobkemon.getTrainer("andrew");
        poobkemon.inicializateBattlePVsP(t1.getName(), t2.getName());
        int pokemonLevel = poobkemon.getBattle().getCurrentPokemonLevel();
        assertEquals(t1.getPokemonInUse().getLevel(), pokemonLevel);
    }

    @Test
    public void shouldGetCurrentPokemonPs() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon poobkemon = kemon.deserializateGame();
        Trainer t1 = poobkemon.getTrainer("tulio");
        Trainer t2 = poobkemon.getTrainer("andrew");
        poobkemon.inicializateBattlePVsP(t1.getName(), t2.getName());
        int pokemonPs = poobkemon.getBattle().getCurrentPokemonPs();
        assertEquals(t1.getPokemonInUse().getPs(), pokemonPs);
    }

    @Test
    public void shouldGetCurrentPokemonPokedexIndex() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon poobkemon = kemon.deserializateGame();
        Trainer t1 = poobkemon.getTrainer("tulio");
        Trainer t2 = poobkemon.getTrainer("andrew");
        poobkemon.inicializateBattlePVsP(t1.getName(), t2.getName());
        int pokedexIndex = poobkemon.getBattle().getCurrentPokemonPokedexIndex();
        assertEquals(t1.getPokemonInUse().getPokedexIndex(), pokedexIndex);
    }

    @Test
    public void shouldGetCurrentMaxPs() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon poobkemon = kemon.deserializateGame();
        Trainer t1 = poobkemon.getTrainer("tulio");
        Trainer t2 = poobkemon.getTrainer("andrew");
        poobkemon.inicializateBattlePVsP(t1.getName(), t2.getName());
        int maxPs = poobkemon.getBattle().getCurrentMaxPs();
        assertEquals(t1.getPokemonInUse().getMaxPs(), maxPs);
    }

    @Test
    public void shouldGetCurrentColor() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon poobkemon = kemon.deserializateGame();
        Trainer t1 = poobkemon.getTrainer("tulio");
        Trainer t2 = poobkemon.getTrainer("andrew");
        poobkemon.inicializateBattlePVsP(t1.getName(), t2.getName());
        Color color = poobkemon.getBattle().getCurrentColor();
        assertEquals(t1.getColor(), color);
    }

    @Test
    public void shouldGetOponentPokemonName() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon poobkemon = kemon.deserializateGame();
        Trainer t1 = poobkemon.getTrainer("tulio");
        Trainer t2 = poobkemon.getTrainer("andrew");
        poobkemon.inicializateBattlePVsP(t1.getName(), t2.getName());
        String pokemonName = poobkemon.getBattle().getOponentPokemonName();
        assertEquals(t2.getPokemonInUse().getName(), pokemonName);
    }

    @Test
    public void shouldGetOponentPokemonLevel() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon poobkemon = kemon.deserializateGame();
        Trainer t1 = poobkemon.getTrainer("tulio");
        Trainer t2 = poobkemon.getTrainer("andrew");
        poobkemon.inicializateBattlePVsP(t1.getName(), t2.getName());
        int pokemonLevel = poobkemon.getBattle().getOponentPokemonLevel();
        assertEquals(t2.getPokemonInUse().getLevel(), pokemonLevel);
    }

    @Test
    public void shouldGetOponentPokemonPs() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon poobkemon = kemon.deserializateGame();
        Trainer t1 = poobkemon.getTrainer("tulio");
        Trainer t2 = poobkemon.getTrainer("andrew");
        poobkemon.inicializateBattlePVsP(t1.getName(), t2.getName());
        int pokemonPs = poobkemon.getBattle().getOponentPokemonPs();
        assertEquals(t2.getPokemonInUse().getPs(), pokemonPs);
    }

    @Test
    public void shouldGetOponentPokemonPokedexIndex() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon poobkemon = kemon.deserializateGame();
        Trainer t1 = poobkemon.getTrainer("tulio");
        Trainer t2 = poobkemon.getTrainer("andrew");
        poobkemon.inicializateBattlePVsP(t1.getName(), t2.getName());
        int pokedexIndex = poobkemon.getBattle().getOponentPokemonPokedexIndex();
        assertEquals(t2.getPokemonInUse().getPokedexIndex(), pokedexIndex);
    }

    @Test
    public void shouldGetOponentMaxPs() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon poobkemon = kemon.deserializateGame();
        Trainer t1 = poobkemon.getTrainer("tulio");
        Trainer t2 = poobkemon.getTrainer("andrew");
        poobkemon.inicializateBattlePVsP(t1.getName(), t2.getName());
        int maxPs = poobkemon.getBattle().getOponentMaxPs();
        assertEquals(t2.getPokemonInUse().getMaxPs(), maxPs);
    }

    @Test
    public void shouldCheckIfCurrentPokemonIsAlive() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon poobkemon = kemon.deserializateGame();
        Trainer t1 = poobkemon.getTrainer("tulio");
        Trainer t2 = poobkemon.getTrainer("andrew");
        poobkemon.inicializateBattlePVsP(t1.getName(), t2.getName());
        boolean isAlive = poobkemon.getBattle().isAliveCurrentPokemon();
        assertTrue(isAlive);
    }

    @Test
    public void shouldCheckIfOpponentPokemonIsAlive() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon poobkemon = kemon.deserializateGame();
        Trainer t1 = poobkemon.getTrainer("tulio");
        Trainer t2 = poobkemon.getTrainer("andrew");
        poobkemon.inicializateBattlePVsP(t1.getName(), t2.getName());
        boolean isAlive = poobkemon.getBattle().isAliveOpponentPokemon();
        assertTrue(isAlive);
    }

    @Test
    public void shouldGetMovementsStringCurrent() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon poobkemon = kemon.deserializateGame();
        Trainer t1 = poobkemon.getTrainer("tulio");
        Trainer t2 = poobkemon.getTrainer("andrew");
        poobkemon.inicializateBattlePVsP(t1.getName(), t2.getName());
        ArrayList<String> movements = poobkemon.getBattle().getMovementsStringCurrent();
        assertNotNull(movements);
        assertFalse(movements.isEmpty());
    }

    @Test
    public void shouldGetMovementsStringOponent() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon poobkemon = kemon.deserializateGame();
        Trainer t1 = poobkemon.getTrainer("tulio");
        Trainer t2 = poobkemon.getTrainer("andrew");
        poobkemon.inicializateBattlePVsP(t1.getName(), t2.getName());
        ArrayList<String> movements = poobkemon.getBattle().getMovementsStringOponent();
        assertNotNull(movements);
        assertFalse(movements.isEmpty());
    }
    
    
    @Test
    public void shouldAddItemToInventory() {
    	Inventory inventory = new Inventory();
    	Pokemon venusaur = new Pokemon("Venusaur", 100, 364, 289, 328, 291, 328, 284, 
                PokemonType.PLANTA, PokemonType.VENENO, 3);
    	Pokemon charizard = new Pokemon("Charizard", 100, 360, 293, 348, 280, 295, 328, 
                PokemonType.FUEGO, PokemonType.VOLADOR, 6);
    	Item potion =  new PsPotion("potion", "Restores HP", PotionType.NORMAL);
    	Item revive = new Revive();
    	Item superPotion = new SuperPotion("superPotion", "Restores more HP", PotionType.SUPER);
    	try {
    		inventory.addItem(potion);
    		assertTrue(inventory.contains(potion));;
    	} catch (PoobkemonException e) {
    		fail("El inventario no pudo agregar este item");
    	}
    }
    
    @Test
    public void shouldAddMultipleItemsToInventory() {
    	Inventory inventory = new Inventory();
    	Pokemon venusaur = new Pokemon("Venusaur", 100, 364, 289, 328, 291, 328, 284, 
                PokemonType.PLANTA, PokemonType.VENENO, 3);
    	Pokemon charizard = new Pokemon("Charizard", 100, 360, 293, 348, 280, 295, 328, 
                PokemonType.FUEGO, PokemonType.VOLADOR, 6);
    	Item potion =  new PsPotion("potion", "Restores HP", PotionType.NORMAL);
    	Item revive = new Revive();
    	Item superPotion = new SuperPotion("superPotion", "Restores more HP", PotionType.SUPER);
    	try {
    		inventory.addItem(potion);
    		inventory.addItem(revive);
    		inventory.addItem(superPotion);
    		assertEquals(3,inventory.getItemsName().size());
    	}catch (PoobkemonException e) {
    		fail("El inventario no pudo agregar este item");
    	}
    }
    
    @Test
    public void shouldThrowAnException() {
    	Inventory inventory = new Inventory();
    	Pokemon venusaur = new Pokemon("Venusaur", 100, 364, 289, 328, 291, 328, 284, 
                PokemonType.PLANTA, PokemonType.VENENO, 3);
    	Pokemon charizard = new Pokemon("Charizard", 100, 360, 293, 348, 280, 295, 328, 
                PokemonType.FUEGO, PokemonType.VOLADOR, 6);
    	Item potion =  new PsPotion("potion", "Restores HP", PotionType.NORMAL);
    	Item revive = new Revive();
    	Item superPotion = new SuperPotion("superPotion", "Restores more HP", PotionType.SUPER);
    	
    	try {
    		inventory.addItem(potion);
    		inventory.addItem(potion);
    		assertThrows(PoobkemonException.class, () -> inventory.addItem(potion));
    	}catch(PoobkemonException e) {
    		fail("No ha lanzado la excepción esperada ");
    	}
    	
    }
    
    @Test
    public void shouldAddMultiplePokemonToInventory() {
    	Inventory inventory = new Inventory();
    	Pokemon venusaur = new Pokemon("Venusaur", 100, 364, 289, 328, 291, 328, 284, 
                PokemonType.PLANTA, PokemonType.VENENO, 3);
    	Pokemon charizard = new Pokemon("Charizard", 100, 360, 293, 348, 280, 295, 328, 
                PokemonType.FUEGO, PokemonType.VOLADOR, 6);
    	Item potion =  new PsPotion("potion", "Restores HP", PotionType.NORMAL);
    	Item revive = new Revive();
    	Item superPotion = new SuperPotion("superPotion", "Restores more HP", PotionType.SUPER);
        try {
            inventory.addPokemon(venusaur);
            inventory.addPokemon(charizard);
            
            assertTrue(inventory.contains(venusaur));
            assertTrue(inventory.contains(charizard));
        } catch (PoobkemonException e) {
            fail("No se podrian agregar multiples pokemones " + e.getMessage());
        }
    }
    
    @Test
    public void shouldThrowAddingSamePokemonTwice() {
    	Inventory inventory = new Inventory();
    	Pokemon venusaur = new Pokemon("Venusaur", 100, 364, 289, 328, 291, 328, 284, 
                PokemonType.PLANTA, PokemonType.VENENO, 3);
    	Pokemon charizard = new Pokemon("Charizard", 100, 360, 293, 348, 280, 295, 328, 
                PokemonType.FUEGO, PokemonType.VOLADOR, 6);
    	Item potion =  new PsPotion("potion", "Restores HP", PotionType.NORMAL);
    	Item revive = new Revive();
    	Item superPotion = new SuperPotion("superPotion", "Restores more HP", PotionType.SUPER);
    	try {
    		inventory.addPokemon(venusaur);
    		assertThrows(PoobkemonException.class, () -> inventory.addPokemon(venusaur));
    	}catch(PoobkemonException e) {
    		fail("No ha lanzado la correspondiente excepción ");
    	}
    }
    @Test
    public void shouldGetItemByName() {
    	Inventory inventory = new Inventory();
    	Pokemon venusaur = new Pokemon("Venusaur", 100, 364, 289, 328, 291, 328, 284, 
                PokemonType.PLANTA, PokemonType.VENENO, 3);
    	Pokemon charizard = new Pokemon("Charizard", 100, 360, 293, 348, 280, 295, 328, 
                PokemonType.FUEGO, PokemonType.VOLADOR, 6);
    	Item potion =  new PsPotion("potion", "Aumenta la vida.", PotionType.NORMAL);
    	Item revive = new Revive();
    	Item superPotion = new SuperPotion("superPotion", "Restores more HP", PotionType.SUPER);
    	try {
    		inventory.addItem(potion);
    		inventory.addItem(revive);
    		
    		Item pruebaItem = inventory.getItemByName(potion.getName());
    		assertEquals(potion, pruebaItem);
    		
    		pruebaItem = inventory.getItemByName(revive.getName());
    		assertEquals(revive, pruebaItem);
    		
    	}catch(PoobkemonException e) {
    		fail("Ha ocurrido un error con el nombre de los items");
    	}
    }
    @Test
    public void shouldGetAllPokemonFromInventory() {
    	Inventory inventory = new Inventory();
    	Pokemon venusaur = new Pokemon("Venusaur", 100, 364, 289, 328, 291, 328, 284, 
                PokemonType.PLANTA, PokemonType.VENENO, 3);
    	Pokemon charizard = new Pokemon("Charizard", 100, 360, 293, 348, 280, 295, 328, 
                PokemonType.FUEGO, PokemonType.VOLADOR, 6);
    	Item potion =  new PsPotion("potion", "Aumenta la vida.", PotionType.NORMAL);
    	Item revive = new Revive();
    	Item superPotion = new SuperPotion("superPotion", "Restores more HP", PotionType.SUPER);
        try {
            inventory.addPokemon(venusaur);
            inventory.addPokemon(charizard);
            
            assertEquals(2, inventory.getPokemons().size());
            assertTrue(inventory.getPokemons().containsKey("Venusaur"));
            assertTrue(inventory.getPokemons().containsKey("Charizard"));
        } catch (PoobkemonException e) {
            fail("No se encuentran todos los pokemones en el inventario " + e.getMessage());
        }
    }
    
    @Test
    public void shouldGetAlivePokemon() {
    	Inventory inventory = new Inventory();
    	Pokemon venusaur = new Pokemon("Venusaur", 100, 364, 289, 328, 291, 328, 284, 
                PokemonType.PLANTA, PokemonType.VENENO, 3);
    	Pokemon charizard = new Pokemon("Charizard", 100, 360, 293, 348, 280, 295, 328, 
                PokemonType.FUEGO, PokemonType.VOLADOR, 6);
    	Item potion =  new PsPotion("potion", "Aumenta la vida.", PotionType.NORMAL);
    	Item revive = new Revive();
    	Item superPotion = new SuperPotion("superPotion", "Restores more HP", PotionType.SUPER);
        try {
            inventory.addPokemon(venusaur);
            inventory.addPokemon(charizard);
          
            assertEquals(2, inventory.getAlivePokemons().size());
            
            venusaur.losePS(venusaur.getPs());
            
            assertEquals(1, inventory.getAlivePokemons().size());
            assertEquals(charizard, inventory.getAlivePokemons().get(0));
        } catch (PoobkemonException e) {
            fail("Exception thrown when testing alive Pokemon: " + e.getMessage());
        }
    }
    
    @Test
    public void shouldCheckIfCanStillFight() {
    	Inventory inventory = new Inventory();
    	Pokemon venusaur = new Pokemon("Venusaur", 100, 364, 289, 328, 291, 328, 284, 
                PokemonType.PLANTA, PokemonType.VENENO, 3);
    	Pokemon charizard = new Pokemon("Charizard", 100, 360, 293, 348, 280, 295, 328, 
                PokemonType.FUEGO, PokemonType.VOLADOR, 6);
    	Item potion =  new PsPotion("potion", "Aumenta la vida.", PotionType.NORMAL);
    	Item revive = new Revive();
    	Item superPotion = new SuperPotion("superPotion", "Restores more HP", PotionType.SUPER);
        try {
            assertFalse(inventory.canStillFighting());
            
            inventory.addPokemon(venusaur);
            assertTrue(inventory.canStillFighting());
            
            venusaur.losePS(venusaur.getPs());
            assertFalse(inventory.canStillFighting());
            
            inventory.addPokemon(charizard);
            assertTrue(inventory.canStillFighting());
        } catch (PoobkemonException e) {
            fail("Los pokemones no podrian continuar peleando");
        }
    }
    
    @Test
    public void shouldCreatePlayerTrainer() {
    	PlayerTrainer newTrainer = new PlayerTrainer("Gary", new Color(0,0,255));
    	assertEquals("Gary", newTrainer.getName());
    	assertEquals(new Color(0,0,255), newTrainer.getColor());
    }
    
    @Test
    public void shouldChangePokemonByObject() {
        PlayerTrainer trainer = new PlayerTrainer("Ash", new Color(255, 0, 0));
        Pokemon pikachu = new Pokemon("Pikachu", 50, 200, 150, 120, 110, 130, 160, 
                              PokemonType.ELECTRICO, null, 25);
        Pokemon charizard = new Pokemon("Charizard", 60, 280, 180, 200, 150, 170, 190,       
        		PokemonType.FUEGO, PokemonType.VOLADOR, 6);
        Pokemon faintedPokemon = new Pokemon("Magikarp", 20, 100, 50, 40, 60, 40, 80, 
                                   PokemonType.AGUA, null, 129);
        faintedPokemon.losePS(faintedPokemon.getPs());
        Item potion = new PsPotion("Potion", "Restores 20 HP", PotionType.NORMAL);
        Item revive = new Revive();
        Inventory inventory = new Inventory();
        trainer.setInventory(inventory);
        try {
        inventory.addPokemon(pikachu);
        inventory.addPokemon(charizard);
        inventory.addPokemon(faintedPokemon);
        inventory.addItem(potion);
        inventory.addItem(revive);
        trainer.setPokemonInUse(pikachu);
        trainer.changePokemon(charizard);
        assertEquals(charizard, trainer.getPokemonInUse());
        } catch(PoobkemonException e) {
        	fail("Ha fallado el cambio");
        }
    }
    
    @Test
    public void shouldChangePokemonByName() {
        PlayerTrainer trainer = new PlayerTrainer("Ash", new Color(255, 0, 0));
        Pokemon pikachu = new Pokemon("Pikachu", 50, 200, 150, 120, 110, 130, 160, 
                              PokemonType.ELECTRICO, null, 25);
        Pokemon charizard = new Pokemon("Charizard", 60, 280, 180, 200, 150, 170, 190,       
        		PokemonType.FUEGO, PokemonType.VOLADOR, 6);
        Pokemon faintedPokemon = new Pokemon("Magikarp", 20, 100, 50, 40, 60, 40, 80, 
                                   PokemonType.AGUA, null, 129);
        faintedPokemon.losePS(faintedPokemon.getPs());
        Item potion = new PsPotion("Potion", "Restores 20 HP", PotionType.NORMAL);
        Item revive = new Revive();
        Inventory inventory = new Inventory();
        trainer.setInventory(inventory);
        try {
        inventory.addPokemon(pikachu);
        inventory.addPokemon(charizard);
        inventory.addPokemon(faintedPokemon);
        inventory.addItem(potion);
        inventory.addItem(revive);
        trainer.setPokemonInUse(pikachu);
        trainer.changePokemon("Charizard");
        
        assertEquals(charizard, trainer.getPokemonInUse());
        
        } catch(PoobkemonException e) {
        	fail("Ha fallado el cambio");
        }
    }
    
    @Test
    public void shouldUseItem() {
        PlayerTrainer trainer = new PlayerTrainer("Ash", new Color(255, 0, 0));
        Pokemon pikachu = new Pokemon("Pikachu", 50, 200, 150, 120, 110, 130, 160, 
                              PokemonType.ELECTRICO, null, 25);
        Pokemon charizard = new Pokemon("Charizard", 60, 280, 180, 200, 150, 170, 190,       
        		PokemonType.FUEGO, PokemonType.VOLADOR, 6);
        Pokemon faintedPokemon = new Pokemon("Magikarp", 20, 100, 50, 40, 60, 40, 80, 
                                   PokemonType.AGUA, null, 129);
        faintedPokemon.losePS(faintedPokemon.getPs());
        Item potion = new PsPotion("Potion", "Restores 20 HP", PotionType.NORMAL);
        Item revive = new Revive();
        Inventory inventory = new Inventory();
        trainer.setInventory(inventory);
        try {
        inventory.addPokemon(pikachu);
        inventory.addPokemon(charizard);
        inventory.addPokemon(faintedPokemon);
        inventory.addItem(potion);
        inventory.addItem(revive);
        
        } catch(PoobkemonException e) {
        	fail("Ha fallado el uso del item");
        }
        try {
        int initialPs = charizard.getPs();
        charizard.losePS(50);
        trainer.setPokemonInUse(charizard);
        trainer.useItem(potion);
        
        assertTrue(charizard.getPs() == 280-50+20);
        
        } catch(PoobkemonException e) {
        	fail("Ha fallado el uso del item");
        }
    }
    @Test
    public void shouldUseItemByName() {
        PlayerTrainer trainer = new PlayerTrainer("Ash", new Color(255, 0, 0));
        Pokemon pikachu = new Pokemon("Pikachu", 50, 200, 150, 120, 110, 130, 160, 
                              PokemonType.ELECTRICO, null, 25);
        Pokemon charizard = new Pokemon("Charizard", 60, 280, 180, 200, 150, 170, 190,       
        		PokemonType.FUEGO, PokemonType.VOLADOR, 6);
        Pokemon faintedPokemon = new Pokemon("Magikarp", 20, 100, 50, 40, 60, 40, 80, 
                                   PokemonType.AGUA, null, 129);
        faintedPokemon.losePS(faintedPokemon.getPs());
        Item potion = new PsPotion("Potion", "Restores 20 HP", PotionType.NORMAL);
        Item revive = new Revive();
        Inventory inventory = new Inventory();
        trainer.setInventory(inventory);
        try {
        inventory.addPokemon(pikachu);
        inventory.addPokemon(charizard);
        inventory.addPokemon(faintedPokemon);
        inventory.addItem(potion);
        inventory.addItem(revive);
        trainer.setPokemonInUse(pikachu);
        
        pikachu.losePS(50);
        int initialPs = pikachu.getPs();
        
        trainer.useItem(potion.getName());
        
        assertTrue(pikachu.getPs() > initialPs);
        
        } catch(PoobkemonException e) {
        	fail("Ha fallado el uso del item");
        }
    }
    @Test
    public void shouldLoseSpecialAttack() {
        Pokemon charizard = new Pokemon("Charizard", 60, 280, 180, 200, 150, 170, 190,       
        		PokemonType.FUEGO, PokemonType.VOLADOR, 6);
        int initialSpecialAttack = charizard.getSpecialAttack();
        try{
        	charizard.loseSpecialAttack(150);
        	assertTrue(initialSpecialAttack > charizard.getSpecialAttack());
        }catch(PoobkemonException e) {
        	fail("fallo");
        }
        
    }
    @Test
    public void shouldLoseAndGainVelocity() {
        Pokemon charizard = new Pokemon("Charizard", 60, 280, 180, 200, 150, 170, 190,       
        		PokemonType.FUEGO, PokemonType.VOLADOR, 6);
        int initialVelocity = charizard.getVelocity();
        try {
        	charizard.loseVelocity(100);
        	charizard.gainVelocity(100);
        	assertTrue(initialVelocity == charizard.getVelocity());
        }catch(PoobkemonException e) {
        	System.out.println(e.getMessage());
        }
    }
    @Test
    public void shouldGetInformationAboutSituations() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        Trainer t1 = po.getTrainer("tulio");
        Trainer t2 = po.getTrainer("andrew");
        po.inicializateBattlePVsP(t1.getName(), t2.getName());
        po.getcurrentMaxPs();
        po.getOponentMaxPs();
        po.isAliveOpponentPokemon();
        po.getCurrentColor();
        assertTrue(po.isAliveCurrentPokemon());
    }
    @Test
    public void shouldGiveTheCurrentElements() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        Trainer t1 = po.getTrainer("tulio");
        Trainer t2 = po.getTrainer("andrew");
        po.inicializateBattlePVsP(t1.getName(), t2.getName());
        po.getCurrentItems();
        po.getCurrentPokemons();
        po.getItemsName();
        po.getPokemonsName();
        po.GameIsOVer();
        assertTrue(po.getCurrentPokemons().size() > 0);
    }
    @Test
    public void shouldGiveMoreInformation() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        Trainer t1 = po.getTrainer("tulio");
        Trainer t2 = po.getTrainer("andrew");
        po.inicializateBattlePVsP(t1.getName(), t2.getName());
        po.getMovementsStringCurrent();
        po.getMovementsStringOponent();
        try {
        	int pp = po.getPPInBattle(t1.getPokemonInUse().getMovements().get(0).getName());
        	assertTrue(pp > 0);
        }catch(PoobkemonException e) {
        	System.out.println(e.getMessage());
        }
    }
    @Test
    public void shouldLoseStats() {
        Pokemon charizard = new Pokemon("Charizard", 60, 280, 180, 200, 150, 170, 190,       
        		PokemonType.FUEGO, PokemonType.VOLADOR, 6);
        int initialAttack = charizard.getAttack();
        try {
        	charizard.loseAttack(100);
        	charizard.loseDefense(20);
        	charizard.gainAttack(100);
        	assertTrue(initialAttack == charizard.getAttack());
        }catch(PoobkemonException e) {
        	System.out.println(e.getMessage());
        }
    }
    @Test
    public void shouldGiveInformationInGeneral() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        Trainer t1 = po.getTrainer("tulio");
        Trainer t2 = po.getTrainer("andrew");
        po.inicializateBattlePVsP(t1.getName(), t2.getName());
        po.opponentIsAffected();
        po.currentIsAffected();
        po.getPokemons();
        po.inicialTrainerMovements(t1.getName());
        po.getCurrentPokemonName();
        po.getCurrentPokemonLevel();
        po.getCurrentPokemonPs();
        po.getCurrentPokemonPokedexIndex();
        po.getOponentPokemonName();
        po.getOponentPokemonLevel();
        po.getOponentPokemonPs();
        po.getOponentPokemonPokedexIndex();
        try {
        po.actionCambiar(t1.getInventory().getAlivePokemons().get(2).getName());
        po.actuinHuir();
        }catch(PoobkemonException e) {
        	System.out.println(e.getMessage());
        }
    }
    @Test
    public void shouldGetPokemonByName() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        Pokemon charizard = new Pokemon("Charizard", 60, 280, 180, 200, 150, 170, 190,       
        		PokemonType.FUEGO, PokemonType.VOLADOR, 6);
        po.addPokemon(charizard);
        assertTrue(po.getPokemon(charizard.getName()).getName() == "Charizard");
    }
    @Test
    public void shouldReturnThePokedex() {
    	POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        assertTrue(po.getPokedex().size() > 0);
    }
    @Test
    public void shouldReturnTheTrainers() {
    	POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        assertTrue(po.getTrainers().size() > 0);
    }
    @Test
    public void shouldAddNewPokemon() {
    	POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        try {
        	po.addNewTrainer("Jose", new Color(255,0,0));
            Pokemon charizardLvL60 = new Pokemon("Charizard", 60, 280, 180, 200, 150, 170, 190,       
            		PokemonType.FUEGO, PokemonType.VOLADOR, 6);
            Movement solarBeam = new SpecialMovement("Rayo Solar", "Absorbe luz un turno y ataca en el siguiente.", 10, 120, 100, PokemonType.PLANTA, 0);
            Movement fakeOut = new PhysicalMovement("Intimidación", "Siempre ataca primero y hace retroceder, sólo funciona el primer turno.", 10, 40, 100, PokemonType.NORMAL, 100);
            Movement megaDrain = new SpecialMovement("Mega Drenado", "Absorbe la mitad del daño causado.", 15, 40, 100, PokemonType.PLANTA, 0);
            Movement hyperVoice = new SpecialMovement("Vozarrón", "Ataca con un potente grito.", 10, 90, 100, PokemonType.NORMAL, 0);
            po.addNewPokemon("Jose", charizardLvL60.getName(), solarBeam, fakeOut, megaDrain, hyperVoice);
        }catch(PoobkemonException e) {
        	System.out.println(e.getMessage());
        }
    }
    @Test
    public void shouldGenerateRandomSelectionPokemon() {
    	POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        try {
        	po.addNewTrainer("Joaquín", new Color(255,0,0));
        	po.generateRandomSelectionPokemon("Joaquín");
        	po.infoTrainer("Joaquín");
        	po.getPokemonAlives("Joaquín");
        }catch(PoobkemonException e) {
        	System.out.println(e.getMessage());
        }
    }
    @Test
    public void shouldDoNothing() { //aveces falla por prob de aplicar el estado
        POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        try {
            po.addNewTrainer("Ash", new Color(255,0,0));
            Pokemon charizardLvL60 = new Pokemon("Charizard", 60, 280, 180, 2000, 150, 170, 190,
                    PokemonType.FUEGO, PokemonType.VOLADOR, 6);
            StatusEffect Sleep = new StatusEffect("Dormido","Un Pokémon dormido no puede realizar ningún movimiento durante su turno",5,1);
            MovementState sleep = new MovementState("Dormir","",10, 15,50, PokemonType.NORMAL,Sleep, 100, 0);
            Trainer t1 = po.getTrainer("Ash");
            Trainer t2 = po.getTrainer("tulio");
            charizardLvL60.setMovements(new Movement[] {sleep});
            t1.addPokemon(charizardLvL60);
            po.inicializateBattlePVsP(t1.getName(), t2.getName());
            po.movementPerformed(t1.getPokemonInUse().getMovements().get(0).getName());
            po.movementPerformed(t2.getPokemonInUse().getMovements().get(0).getName());
            assertTrue(t2.getPokemonInUse().isAffected());
        } catch (PoobkemonException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void shouldGetTheStatusEffect() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        TributeEffect paraly  = new TributeEffect("Efecto de paralizar ", "Reduce la velocidad", 2,new HashMap<String,Integer>(){{
            put("Velocity",-50);}});
        StatusEffect Paralyze = new StatusMovil("Paralisis", "Paralisa al pokemon reduciendo su velocidad.", 2,paraly,0.75);
        try {
        	Paralyze.affectPokemon(po.getPokemon("Charizard"));
        	paraly.affectPokemon(po.getPokemon("Raichu"));
        }catch(PoobkemonException e) {
        	fail(e.getMessage());
        }
    }
    @Test
    public void shouldGetInformationAboutInventoryOfATrainer() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        Trainer t1 = po.getTrainer("tulio");
        ArrayList<String> prue = t1.getInventory().getDeadCurrentPokemons();
        assertFalse(prue.size() > 0);
    }
    @Test
    public void shouldGetCurrentAlivePokemonsAndGetThePokemon() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        Trainer t1 = po.getTrainer("tulio");
        ArrayList<Pokemon> prue = t1.getInventory().getAlivePokemons();
        ArrayList<String> pro = t1.getInventory().getCurrentAlivePokemons();
        ArrayList<Pokemon> actualPokemon = t1.getInventory().getAlivePokemons(t1.getInventory().getAlivePokemons().get(0));
        ArrayList<String> a = t1.getInventory().getCurrentAlivePokemonsWithoutCurrent(t1.getInventory().getAlivePokemons().get(0));
        assertTrue(prue.size() > 0);
        assertTrue(pro.size() > 0);
    }
    @Test
    public void shouldFightExpertTrainer() {
        POOBkemon kemon = new POOBkemon();
        POOBkemon po = kemon.deserializateGame();
        Trainer t1 = po.getTrainer("Expert");
        Trainer t2 = po.getTrainer("tulio");
        Trainer t3 = po.getTrainer("Defensive");
        po.inicializateBattlePVsM(t1.getName(), t2.getName());
        try {
        	t1.decide(t1.getPokemonInUse());
        	t3.decide(t3.getPokemonInUse());
        	po.movementPerformed(t1.getPokemonInUse().getMovements().get(0).getName());
        }catch(PoobkemonException e) {
        	fail(e.getMessage());
        }
    }
}
    
    
    
    
