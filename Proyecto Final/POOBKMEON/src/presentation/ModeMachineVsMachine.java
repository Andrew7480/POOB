package presentation;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ModeMachineVsMachine extends JPanel {
    private POOBkemonGUI pooBkemonGUI;
    private CardLayout cardLayout;

    protected DatosMachine datos;
    private BattlePanelMvsM batalla;
    protected SelectionFinalMachines selecionFinal;

    protected ArrayList<String> firstItems;
    protected ArrayList<String> secondItems;
    protected HashMap<String, ArrayList<String>> firstPokemonMovs;
    protected HashMap<String, ArrayList<String>> secondPokemonMovs;

    protected Color colorPlayerOne = Color.ORANGE;
    protected Color colorPlayerTwo = Color.GREEN;

    public ModeMachineVsMachine(POOBkemonGUI newPo){
        pooBkemonGUI = newPo;
        prepareElements();
        prepareActions();
    }
    private void prepareElements(){
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        datos = new DatosMachine(pooBkemonGUI,this);
        add(datos, "Datos");

        selecionFinal = new SelectionFinalMachines(pooBkemonGUI,this);
        add(selecionFinal, "seleccion machines");

        batalla = new BattlePanelMvsM(pooBkemonGUI);
        add(batalla, "Batalla");
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

    public void inicializate(){
        firstPokemonMovs = pooBkemonGUI.domain.infoTrainer(datos.machineTrainerFirst);
        secondPokemonMovs = pooBkemonGUI.domain.infoTrainer(datos.machineTrainerSecond);
        try{
            pooBkemonGUI.domain.inicialTrainerPokemon(datos.machineTrainerFirst, "machine one");
            pooBkemonGUI.domain.inicialTrainerPokemon(datos.machineTrainerSecond, "machine two");
            pooBkemonGUI.domain.inicializateBattle(datos.machineTrainerFirst, datos.machineTrainerSecond);
            batalla.inicializate(pooBkemonGUI.domain.inicialTrainerMovements(datos.machineTrainerFirst));
            changePanel("Batalla");
        } catch (Exception e){
            JOptionPane.showMessageDialog(ModeMachineVsMachine.this, e.getMessage());
        }
    }

    public void actualizar(){
        batalla.actualizar();
    }
}