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

    public void generateAleatory(){
        teamsSurvival.reset();
        pooBkemonGUI.domain.deleteActualListOfPokemons(datos.playerOneName);
        pooBkemonGUI.domain.deleteActualListOfPokemons(datos.playerTwoName);
        pooBkemonGUI.domain.generateRandomSelectionPokemon(datos.playerOneName);
        pooBkemonGUI.domain.generateRandomSelectionPokemon(datos.playerTwoName);
        teamsSurvival.inicializar();
    }

    public void inicializateTeams(String playerOneName, Color colorOne, String playerTwoName, Color colorTwo){
        pooBkemonGUI.createTrainer(playerOneName, colorOne);
        pooBkemonGUI.createTrainer(playerTwoName, colorTwo);
        firstName = playerOneName;
        secondName = playerTwoName;
        try {
            pooBkemonGUI.domain.generateRandomSelectionPokemon(playerOneName);
            pooBkemonGUI.domain.generateRandomSelectionPokemon(playerTwoName);
            }catch (Exception i) {
                JOptionPane.showMessageDialog(null, i.getMessage());
                return;
            }
    }
    public void reset(){
        datos = new DatosTwoPlayersSurvival(pooBkemonGUI,this);
        teamsSurvival = new SelectionFinalSurvival(pooBkemonGUI, this);        
        survivalBatalla = new BattleContainer(pooBkemonGUI);
    }

    public void changePanel(String namePanel){
        cardLayout.show(this,namePanel);
    }

    public void inicializateGame(){
        pooBkemonGUI.domain.inicializateBattlePVsP(firstName, secondName);
        survivalBatalla.inicializate();
        changePanel("Battle");
    }
}
