package presentation;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import domain.PoobkemonException;

public class BattleContainer extends JPanel {
    protected BattlePanel battle;
    protected InventoryPanel inventoryItems;
    protected ListPokemonAvailable inventoryPokemons;
    protected POOBkemonGUI pooBkemonGUI;
    private CardLayout cardLayout;


    public BattleContainer(POOBkemonGUI newpPooBkemonGUI){
        pooBkemonGUI = newpPooBkemonGUI;
        prepareElements();
        prepareActions();
    
    }

    private void prepareElements(){
        battle = new BattlePanel(pooBkemonGUI);
        inventoryItems = new InventoryPanel(pooBkemonGUI);
        inventoryPokemons = new ListPokemonAvailable(pooBkemonGUI);

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        add(battle, "Battle");
        add(inventoryItems, "Items");
        add(inventoryPokemons, "Change");
    }

    public void inicializate(){
        inventoryPokemons.inicializate();
        inventoryItems.inicializate();
        battle.inicializate();


    }

    public void changePanel(String namePanel){
        cardLayout.show(this,namePanel);
    }

    private void prepareActions(){
        battle.getInventoryButton().addActionListener(e -> {
            changePanel("Items");
            actualizar();
        });
        battle.getPokemonButton().addActionListener(e -> 
        {changePanel("Change");actualizar();});
        inventoryItems.getNextBJButton().addActionListener(e -> 
        useItem());
        inventoryItems.getButtonBack().addActionListener(e -> 
        {changePanel("Battle");actualizar();});
        inventoryPokemons.getBackButton().addActionListener(e -> 
        {changePanel("Battle");actualizar();});
        inventoryPokemons.getDoneButton().addActionListener(e -> 
        changePokemon());
    }

    private void useItem(){
        if (inventoryItems.isOneOption()){
                try{
                    pooBkemonGUI.domain.actionUseItem(inventoryItems.itemSelected());
                    actualizar();
                    changePanel("Battle");
                }catch(PoobkemonException h){
                    System.out.println(h.getMessage());
                }
            }
        else{
            JOptionPane.showMessageDialog(this, "Solo puedes utilizar una poción por pókemon", "Límite excedido", JOptionPane.WARNING_MESSAGE);
            }
    }
    private void changePokemon(){
        if (inventoryPokemons.sizeChoosen() < 1){
            JOptionPane.showMessageDialog(this, "Debes escoger un pokemon", 
            "Límite excedido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (inventoryPokemons.sizeChoosen() > inventoryPokemons.MAX_CHANGED){
            JOptionPane.showMessageDialog(this, "Solo puedes escoger uno para cambiar " + inventoryPokemons.MAX_CHANGED + "pokemon", 
            "Límite excedido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        changePokemonDomain();
        actualizar();
        changePanel("Battle");
        System.out.println(pooBkemonGUI.domain.getCurrentPokemonName());
        System.out.println(pooBkemonGUI.domain.getMovementsStringCurrent().toString());
        
    }

    private void changePokemonDomain() {
        String pokemonName = inventoryPokemons.getSelectedPokemon();
        try{pooBkemonGUI.domain.actionCambiar(pokemonName);}
        catch(PoobkemonException e){System.out.println(e.getMessage());}
        System.out.println("Cambio" + pokemonName);
        
    }

    public void actualizar(){
        battle.actualizar();
        inventoryItems.actualizar();
        inventoryPokemons.actualizar();
    }
}
