package presentation;
import javax.swing.*;
import domain.PoobkemonException;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ModePlayerVsMachine extends JPanel {
    private String backgroundImage = "fondoAnimado2";
    private CardLayout cardLayout;
    protected DatosOnePlayer datos;
    protected SelectionPokemonItemPlayer inventory;
    protected SelectionMovementsPlayer movements;
    protected InicialPokemonsPlayer inicialPoks;
    protected BattleContainer batalla;

    protected String nameTrainer;
    protected HashMap<String, ArrayList<String>> pokemonsWithMovs;
    protected ArrayList<String> itemsEscogidos;

    protected String trainerEscogidoMachine;

    private POOBkemonGUI pooBkemonGUI;


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

    private void prepareActions(){}

    public void changePanel(String namePanel){
        cardLayout.show(this,namePanel);
    }

    public void inicializateBattle(Color firstColor, String firstPok){
        
        pooBkemonGUI.createTrainer(nameTrainer,firstColor);
        try{
            pooBkemonGUI.addPokemonsToTrainer(nameTrainer,pokemonsWithMovs);

            pooBkemonGUI.addItemsToTrainer(nameTrainer,itemsEscogidos);

            pooBkemonGUI.domain.inicialTrainerPokemon(nameTrainer,firstPok);

            pooBkemonGUI.domain.inicialTrainerPokemon(trainerEscogidoMachine,"Tulio");
            }
        catch(PoobkemonException i){
            JOptionPane.showMessageDialog(null, i.getMessage());
            return;
        }
        pooBkemonGUI.domain.inicializateBattle(nameTrainer,trainerEscogidoMachine);
        batalla.inicializate();
    }

    public void actualizar(){
        batalla.actualizar();
    }

}   
