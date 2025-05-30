package presentation.Battle;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import domain.LogPOOBKEMON;
import domain.PoobkemonException;
import presentation.POOBkemonGUI;


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
        battle.getSacrificableButton().addActionListener(e ->{
            actualizar();
            sacrificar();
            changePanel("Battle");actualizar();
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

    public JButton getSalvarPartida(){
        return battle.getSalvarPartida();
    }
    public JButton getCargarPartida(){
        return battle.getCargarPartida();
    }

    private void useItem(){
        if (inventoryItems.isOneOption()){
                try{
                    ArrayList<String> deadPokemons = pooBkemonGUI.domain.getDeadCurrentPokemons();
                    if (inventoryItems.itemSelected().equals("revive") && deadPokemons.size()>0) {
                        inventoryItems.opcionRevive(deadPokemons);
                    }
                    else{pooBkemonGUI.domain.actionUseItem(inventoryItems.itemSelected());}
                    actualizar();
                    changePanel("Battle");
                }catch(PoobkemonException h){
                    System.out.println(h.getMessage());
                }
                catch (Exception e) {
                        LogPOOBKEMON.record(e);
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
        else if (inventoryPokemons.sizeChoosen() > inventoryPokemons.MAX_CHANGED){
            JOptionPane.showMessageDialog(this, "Solo puedes escoger uno para cambiar " + inventoryPokemons.MAX_CHANGED + "pokemon", 
            "Límite excedido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        else if(pooBkemonGUI.domain.getCurrentAlivePokemons().contains(inventoryPokemons.getSelectedPokemon())){
            changePokemonDomain();
            actualizar();
            changePanel("Battle");
        }
        else if (pooBkemonGUI.domain.getBattle().getCurrentTrainer().getInventory().getAlivePokemons().contains(pooBkemonGUI.domain.getPokedex().get(inventoryPokemons.getSelectedPokemon()))){
            changePokemonDomain();
            actualizar();
            changePanel("Battle");
        }
        else{
            JOptionPane.showMessageDialog(this, "El pokemon por el que tratas de cambiar esta muerto ", 
            "No se puede realizar esta acción", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }

    private void changePokemonDomain() {
        String pokemonName = inventoryPokemons.getSelectedPokemon();
        try{pooBkemonGUI.domain.actionCambiar(pokemonName);}
        catch(PoobkemonException e){System.out.println(e.getMessage());}
        catch (Exception e) {LogPOOBKEMON.record(e);}
        System.out.println("Cambio" + pokemonName);
        
    }

    public void sacrificar(){
        opcionSacrificarTo(pooBkemonGUI.domain.getCurrentAlivePokemonsWithoutCurrent());
        System.out.println("Se ha sacrificado un pokemon.");
    }

    public void opcionSacrificarTo(ArrayList<String> vivos) {        
        String[] opciones = vivos.toArray(new String[0]);
        if (opciones.length <=0) return;
        int seleccion = JOptionPane.showOptionDialog(
                null,
                "Selecciona un pokemon para dar el sacrificio:",
                "Sacrificio",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion != -1) {
            JOptionPane.showMessageDialog(null, "Elegiste: " + opciones[seleccion]);
            try{
                pooBkemonGUI.domain.sacrificateCurentPokemon(opciones[seleccion]);
            }
            catch(PoobkemonException e){
                System.out.println(e.getMessage());
            }catch (Exception e) {LogPOOBKEMON.record(e);}
        } else {
            JOptionPane.showMessageDialog(null, "No elegiste ninguna opción.");
        }
    }

    public void actualizar(){
        battle.actualizar();
        inventoryItems.actualizar();
        inventoryPokemons.actualizar();
    }
    
    public JButton getRunButton(){
        return battle.getRunButton();
    }
    public void stopTimer(){
        battle.stopTimer();
    }
}
