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

    private POOBkemonGUI po;


    public ModePlayerVSPlayer(POOBkemonGUI newPo){
        po = newPo;
        prepareElements();
        prepareActions();
    }
    
    private void prepareElements(){
        cardLayout = new CardLayout();
        
        setLayout(cardLayout);

        datos = new DatosTwoPlayers(po,this);
        add(datos, "Datos");

        inventory = new SelectionPokemonItemPlayers(po,this);
        add(inventory, "Inventory");

        movements = new SelectionMovementsTwoPlayers(po, this);
        add(movements,"Movimientos");
        
        inicialPoks = new InicialPokemonsPlayers(po, this);
        add(inicialPoks, "Iniciales");

        batalla = new BattleContainer(po);
        add(batalla, "Battle");
    }

    private void prepareActions(){}

    public void changePanel(String namePanel){
        cardLayout.show(this,namePanel);
    }

    public void inicializateBattle(Color firstColor, Color secondColor, String firstPok, String secondPok){
        
        po.createTrainer(firstName,firstColor);
        po.createTrainer(secondName,secondColor);
        try{
            po.addPokemonsToTrainer(firstName,firstPokemonMovs);
            po.addPokemonsToTrainer(secondName,secondPokemonMovs);
            po.addItemsToTrainer(firstName,firstItems);
            po.addItemsToTrainer(secondName,secondItems);
            po.domain.inicialTrainerPokemon(firstName,firstPok);
            po.domain.inicialTrainerPokemon(secondName,secondPok);
            }
        catch(PoobkemonException i){
            JOptionPane.showMessageDialog(null, i.getMessage());
            return;
        }
        po.domain.inicializateBattle(firstName,secondName);
        batalla.inicializate();
    }
    
    public void actualizar(){
        batalla.actualizar();
    }

}