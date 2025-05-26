package presentation.ModesOfGame;
import java.awt.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import presentation.Battle.BattleContainer;
import presentation.POOBkemonGUI;
import presentation.Datos.DatosTwoPlayersSurvival;
import presentation.Selection.SelectionFinalSurvival;

import domain.PoobkemonException;

public class ModePlayerVSPlayerSurvival extends JPanel {
    protected BattleContainer survivalBatalla;
    protected DatosTwoPlayersSurvival datos;
    public SelectionFinalSurvival teamsSurvival;
    private CardLayout cardLayout;
    public String firstName;
    public String secondName;

    private POOBkemonGUI pooBkemonGUI;

    public ModePlayerVSPlayerSurvival(POOBkemonGUI newPo){
        pooBkemonGUI = newPo;
        prepareElements();
        prepareActions();
    }

    private void prepareElements(){
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        datos = new DatosTwoPlayersSurvival(pooBkemonGUI,this);
        add(datos, "Datos");

        teamsSurvival = new SelectionFinalSurvival(pooBkemonGUI, this);
        add(teamsSurvival, "teams");
        
        survivalBatalla = new BattleContainer(pooBkemonGUI);
        add(survivalBatalla, "Battle");
    }
    
    private void prepareActions(){
        survivalBatalla.getRunButton().addActionListener(e ->{
            pooBkemonGUI.changePanel("inicio");
            changePanel("Datos");
            pooBkemonGUI.domain.endBattle();
        });
    }

    

    public void inicializate(String player1Name, Color color1, String player2Name, Color color2){
        pooBkemonGUI.createTrainer(player1Name, color1);
        pooBkemonGUI.createTrainer(player2Name, color2);
        try {
            pooBkemonGUI.domain.generateRandomSelectionPokemon(player1Name);
            pooBkemonGUI.domain.generateRandomSelectionPokemon(player2Name);
            }catch (Exception i) {
                JOptionPane.showMessageDialog(null, i.getMessage());
                return;
            }
            pooBkemonGUI.domain.inicializateBattle(player1Name, player2Name);
            survivalBatalla.inicializate();
    }
    public void reset(){
        //datos.reset();
        teamsSurvival.reset();
    }

    public void changePanel(String namePanel){
        cardLayout.show(this,namePanel);
    }
}
