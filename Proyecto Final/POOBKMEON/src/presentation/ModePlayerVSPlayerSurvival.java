package presentation;
import java.awt.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import domain.PoobkemonException;
public class ModePlayerVSPlayerSurvival extends JPanel {
    protected BattleContainer survivalBatalla;
    protected DatosTwoPlayersSurvival datos;
    private CardLayout cardLayout;
    protected String firstName;
    protected String secondName;

    private POOBkemonGUI po;

    public ModePlayerVSPlayerSurvival(POOBkemonGUI newPo){
        po = newPo;
        prepareElements();
    }

    private void prepareElements(){
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        datos = new DatosTwoPlayersSurvival(po,this);
        add(datos, "Datos");
        survivalBatalla = new BattleContainer(po);
        add(survivalBatalla, "Battle");

    }

    public void inicializate(String player1Name, Color color1, String player2Name, Color color2){
        po.createTrainer(player1Name, color1);
        po.createTrainer(player2Name, color2);
        try {
            po.domain.generateRandomSelectionPokemon(player1Name);
            po.domain.generateRandomSelectionPokemon(player2Name);
            }catch (Exception i) {
                JOptionPane.showMessageDialog(null, i.getMessage());
                return;
            }
            po.domain.inicializateBattle(player1Name, player2Name);
            survivalBatalla.inicializate();
    }

    public void changePanel(String namePanel){
        cardLayout.show(this,namePanel);
    }
}
