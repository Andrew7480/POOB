package presentation;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import domain.Item;
import domain.Pokemon;
import domain.PoobkemonException;

import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class SelectionPokemonItemPlayers extends JPanel{
    private  String backgroundImage = "emerald";

    private POOBkemonGUI pooBkemonGUI;
    private JButton come;
    private JButton doneButton; 

    public SelectionPokemonItemPlayers(POOBkemonGUI po){
        pooBkemonGUI = po;
        prepareElements();
        prepareActions();
    }
    

    private void prepareElements(){
        setLayout(new BorderLayout());
        setOpaque(false);
        JPanel temp = new JPanel(new GridLayout(1,2));
        temp.setOpaque(false);
        temp.add(new Selection(pooBkemonGUI, new Color(1,2,4,100)));
        temp.add(new Selection(pooBkemonGUI, new Color(30,100,30,100)));

        add(temp, BorderLayout.CENTER);

        JPanel down = new JPanel(new BorderLayout());
        down.setOpaque(false);
        doneButton = new JButton ("Done!");
        come = new JButton("Back..");
        //doneButton.setVisible(false);
        pooBkemonGUI.styleButton(doneButton);
        JPanel booton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        booton.setOpaque(false);
        booton.add(come);
        booton.add(doneButton);
        down.add(booton,BorderLayout.SOUTH);
        add(down, BorderLayout.SOUTH);
    }

    private void prepareActions(){
        
    }

    public JButton getButtonBack(){
        return come;
    }

    public JButton getNextBJButton(){
        return doneButton;
    }

    
    

    public void reset(){ 
        System.out.println("resetea todo de la seleccion players");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".JPG"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
