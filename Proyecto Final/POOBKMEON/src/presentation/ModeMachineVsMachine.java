package presentation;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;

public class ModeMachineVsMachine extends JPanel {
    private POOBkemonGUI po;
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
        po = newPo;
        prepareElements();
        prepareActions();
    }
    private void prepareElements(){
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        datos = new DatosMachine(po,this);
        add(datos, "Datos");

        selecionFinal = new SelectionFinalMachines(po,this);
        add(selecionFinal, "seleccion machines");

        batalla = new BattlePanelMvsM(po);
        add(batalla, "Batalla");
   }
    private void prepareActions(){}

    public void changePanel(String namePanel){
        cardLayout.show(this,namePanel);
    }

    public void inicializate(){
        firstPokemonMovs = po.domain.infoTrainer(datos.machineTrainerFirst);
        secondPokemonMovs = po.domain.infoTrainer(datos.machineTrainerSecond);
        try{
            po.domain.inicialTrainerPokemon(datos.machineTrainerFirst, "machine one");
            po.domain.inicialTrainerPokemon(datos.machineTrainerSecond, "machine two");
            po.domain.inicializateBattle(datos.machineTrainerFirst, datos.machineTrainerSecond);
            batalla.inicializate(po.domain.inicialTrainerMovements(datos.machineTrainerFirst));
            changePanel("Batalla");
            
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(ModeMachineVsMachine.this, e.getMessage());
        }
    }

    public void actualizar(){
        batalla.actualizar();
    }

    


}