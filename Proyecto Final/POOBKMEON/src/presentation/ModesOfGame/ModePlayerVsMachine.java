package presentation.ModesOfGame;
import javax.swing.*;

import domain.LogPOOBKEMON;
import domain.PoobkemonException;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import presentation.POOBkemonGUI;
import presentation.Datos.DatosOnePlayer;
import presentation.Selection.SelectionPokemonItemPlayer;
import presentation.Selection.SelectionMovementsPlayer;
import presentation.InicialSelection.InicialPokemonsPlayer;
import presentation.Battle.BattleContainer;

public class ModePlayerVsMachine extends JPanel {
    private String backgroundImage = "fondoAnimado2";
    private CardLayout cardLayout;
    protected DatosOnePlayer datos;
    public SelectionPokemonItemPlayer inventory;
    public SelectionMovementsPlayer movements;
    public InicialPokemonsPlayer inicialPoks;
    protected BattleContainer batalla;

    public String nameTrainer;
    public HashMap<String, ArrayList<String>> pokemonsWithMovs;
    public ArrayList<String> itemsEscogidos;

    public String trainerEscogidoMachine;

    private presentation.POOBkemonGUI pooBkemonGUI;


    public ModePlayerVsMachine(POOBkemonGUI newPo){
        pooBkemonGUI = newPo;
        prepareElements();
        prepareActions();
    }
    private void prepareElements(){
        cardLayout = new CardLayout();
        
        setLayout(cardLayout);

        datos = new DatosOnePlayer(pooBkemonGUI,this);
        add(datos, "Datos");

        inventory = new SelectionPokemonItemPlayer(pooBkemonGUI,this);
        add(inventory, "Inventory");

        movements = new SelectionMovementsPlayer(pooBkemonGUI, this);
        add(movements,"Movimientos");
        
        inicialPoks = new InicialPokemonsPlayer(pooBkemonGUI, this);
        add(inicialPoks, "Iniciales");

        batalla = new BattleContainer(pooBkemonGUI);
        add(batalla, "Battle");
    }

    private void prepareActions(){
        batalla.getRunButton().addActionListener(e ->{
            pooBkemonGUI.changePanel("inicio");
            changePanel("Datos");
            pooBkemonGUI.domain.endBattle();
        });
    }

    public void changePanel(String namePanel){
        cardLayout.show(this,namePanel);
    }

    public void inicializateBattle(Color firstColor, String firstPok){
        pooBkemonGUI.createTrainer(nameTrainer,firstColor);
        try{
            pooBkemonGUI.addPokemonsToTrainer(nameTrainer,pokemonsWithMovs);
            pooBkemonGUI.addItemsToTrainer(nameTrainer,itemsEscogidos);

            }
        catch(PoobkemonException i){
            JOptionPane.showMessageDialog(null, "message0: "+i.getMessage());
            return;
        }
        try{
            pooBkemonGUI.domain.inicialTrainerPokemon(nameTrainer,firstPok);
            pooBkemonGUI.domain.inicialTrainerPokemon(trainerEscogidoMachine,"Tulio");
            }
        catch(PoobkemonException i){
            JOptionPane.showMessageDialog(null, "message1: "+i.getMessage());
            return;
        }
        pooBkemonGUI.domain.inicializateBattle(nameTrainer,trainerEscogidoMachine);
        batalla.inicializate();
    }
    public void reset(){
        datos.reset();
        inventory.reset();
        movements.reset();
        inicialPoks.reset();
    }

    public void actualizar(){
        batalla.actualizar();
    }

}   
