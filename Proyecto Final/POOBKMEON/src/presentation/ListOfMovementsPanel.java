package presentation;
import javax.swing.*;
import javax.swing.border.Border;

import domain.Pokemon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
public class ListOfMovementsPanel extends JPanel{
    private String BACKGROUND_IMAGE = "fondoanimado1";
    private POOBkemonGUI po;
    private JButton nextButton;
    private JButton come;
    private JPanel centralPanel;

    public ListOfMovementsPanel(POOBkemonGUI newPo){
        po = newPo;
        come = new JButton("Volver");
        setLayout(new BorderLayout());

        centralPanel = new JPanel(new GridLayout(2,3,10,10));
        centralPanel.setOpaque(false);

        add(centralPanel, BorderLayout.CENTER);

        nextButton = new JButton("Siguiente");
        po.styleButton(nextButton);
        po.styleButton(come);
        JPanel southPanel = new JPanel();
        southPanel.setOpaque(false);
        southPanel.add(nextButton);
        southPanel.add(come);
        add(southPanel, BorderLayout.SOUTH);
    }
    public void infoSelectedPokemons(ArrayList <String> chosenPokemons){
        ArrayList<Pokemon> temp = new ArrayList<>();
        for (String s:chosenPokemons){
            temp.add(po.pokemones.get(s));
        }
        ArrayList<String> temp1 = new ArrayList<>();
        for (String p: po.movimientos.keySet()){
            temp1.add(p);
        }
        for (int i = 0; i < chosenPokemons.size(); i++){
            JPanel movementPanel = createMovementPanel(temp.get(i).getName(), temp1,temp.get(i).getPokedexIndex().toString());
            centralPanel.add(movementPanel);
        }
    }

    private JPanel createMovementPanel(String namePokemon, ArrayList<String> movements, String imagePath){ 
        if (namePokemon.equals("") || movements ==null || imagePath.equals("")) return new JPanel();
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/"+imagePath +".png"));
        Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(imageLabel, BorderLayout.NORTH);

        JLabel nameLabel = new JLabel(namePokemon, SwingConstants.CENTER);
        panel.add(nameLabel, BorderLayout.CENTER);

        JPanel movesPanel = new JPanel(new GridLayout(movements.size(), 1));
        movesPanel.setOpaque(false);

        for (String move : movements){
            JButton moveButton = new JButton(move);
            moveButton.setFocusPainted(false);
            moveButton.setContentAreaFilled(false);
            moveButton.setOpaque(false);
            moveButton.addActionListener(new MoveButtonListener(movements));
            movesPanel.add(moveButton);
        }

        panel.add(movesPanel, BorderLayout.SOUTH);

        return panel;
    }

    private class MoveButtonListener implements ActionListener{
        private ArrayList<String> movements;
        
        public MoveButtonListener(ArrayList<String> moves){
            movements = moves;
        }

        @Override
        public void actionPerformed(ActionEvent e){
            JButton sourceButton = (JButton)e.getSource();
            JPopupMenu popupMenu = new JPopupMenu();

            for (String move : movements){
                JMenuItem menuItem = new JMenuItem(move);
                menuItem.addActionListener(ev -> {
                    sourceButton.setText(move);
                });
                popupMenu.add(menuItem);
            }
            popupMenu.show(sourceButton, sourceButton.getWidth() / 2, sourceButton.getHeight() / 2);
        }
    }

    public JButton getNextButton(){
        return nextButton;
    }
    public JButton getComeButton(){
        return come;
    }
    

    public void resetPokemonChosen(){
        centralPanel.removeAll();
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ BACKGROUND_IMAGE+".GIF"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}