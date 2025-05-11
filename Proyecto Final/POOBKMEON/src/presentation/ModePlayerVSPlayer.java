package presentation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModePlayerVSPlayer extends JPanel {
    private String backgroundImage = "fondoAnimado2";
    private JButton btnRegresarNormal;
    private JButton continuar;
    private CardLayout cardLayout;
    protected DatosTwoPlayers datos;
    protected SelectionPokemonItemPlayers inventory;
    
    private POOBkemonGUI po;


    public ModePlayerVSPlayer(POOBkemonGUI newPo){
        po = newPo;
        prepareElements();
        prepareActions();
    }
    
    private void prepareElements(){
        cardLayout = new CardLayout();
        btnRegresarNormal = new JButton("Back");
        continuar = new JButton("Continuar");
        setLayout(cardLayout);

        datos = new DatosTwoPlayers(po,this);
        add(datos, "Datos");

        inventory = new SelectionPokemonItemPlayers(po,this);
        add(inventory, "Inventory");
    }

    private void prepareActions(){

    }
    
    public JButton getBtnRegresarNormal(){
        return btnRegresarNormal;
    }
    
    public JButton getButtonContinuar(){
        return continuar;
    }
    public void changePanel(String namePanel){
        cardLayout.show(this,namePanel);
    }


}