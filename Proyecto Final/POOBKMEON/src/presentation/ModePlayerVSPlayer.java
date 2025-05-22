package presentation;
import javax.swing.*;
import domain.PoobkemonException;
import java.awt.*;
import java.util.*;

public class ModePlayerVSPlayer extends JPanel {
    private String backgroundImage = "fondoAnimado2";
    private CardLayout cardLayout;
    protected DatosTwoPlayers datos;
    protected SelectionPokemonItemPlayers inventory;
    protected SelectionMovementsTwoPlayers movements;
    protected InicialPokemonsPlayers inicialPoks;
    protected BattleContainer batalla;

    protected String firstName;
    protected String secondName;
    protected ArrayList<String> firstItems;
    protected ArrayList<String> secondItems;
    protected HashMap<String, ArrayList<String>> firstPokemonMovs;
    protected HashMap<String, ArrayList<String>> secondPokemonMovs;

    private POOBkemonGUI pooBkemonGUI;


    public ModePlayerVSPlayer(POOBkemonGUI newPo){
        pooBkemonGUI = newPo;
        prepareElements();
        prepareActions();
    }
    
    private void prepareElements(){
        cardLayout = new CardLayout();
        
        setLayout(cardLayout);

        datos = new DatosTwoPlayers(pooBkemonGUI,this);
        add(datos, "Datos");

        inventory = new SelectionPokemonItemPlayers(pooBkemonGUI,this);
        add(inventory, "Inventory");

        movements = new SelectionMovementsTwoPlayers(pooBkemonGUI, this);
        add(movements,"Movimientos");
        
        inicialPoks = new InicialPokemonsPlayers(pooBkemonGUI, this);
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

    public void inicializateBattle(Color firstColor, Color secondColor, String firstPok, String secondPok){
        
        pooBkemonGUI.createTrainer(firstName,firstColor);
        pooBkemonGUI.createTrainer(secondName,secondColor);
        try{
            pooBkemonGUI.addPokemonsToTrainer(firstName,firstPokemonMovs);
            pooBkemonGUI.addPokemonsToTrainer(secondName,secondPokemonMovs);
            pooBkemonGUI.addItemsToTrainer(firstName,firstItems);
            pooBkemonGUI.addItemsToTrainer(secondName,secondItems);
            pooBkemonGUI.domain.inicialTrainerPokemon(firstName,firstPok);
            pooBkemonGUI.domain.inicialTrainerPokemon(secondName,secondPok);
            }
        catch(PoobkemonException i){
            JOptionPane.showMessageDialog(null, i.getMessage());
            return;
        }
        pooBkemonGUI.domain.inicializateBattle(firstName,secondName);
        batalla.inicializate();
    }
    
    public void actualizar(){
        batalla.actualizar();
    }

}