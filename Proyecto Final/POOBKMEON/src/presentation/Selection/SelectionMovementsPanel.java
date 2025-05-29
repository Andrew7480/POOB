package presentation.Selection;
import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import presentation.POOBkemonGUI;

import java.awt.*;
import java.util.*;

public class SelectionMovementsPanel extends JPanel{
    private POOBkemonGUI poobkemonGUI;
    private JPanel centralPanel;
    private JLabel texto;
    private Color color;
    private HashMap<String, ArrayList<String>> movimientosSeleccionados = new HashMap<>();
    private ArrayList<String> chosenPok;


    public SelectionMovementsPanel(POOBkemonGUI newPo){
        poobkemonGUI = newPo;
        color = new Color(85, 85, 85, 100);
        chosenPok = new ArrayList<>();
        prepareElements();
        //setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
    }

    public void infoSelectedPokemons(ArrayList <String> chosenPokemons){
        reset();
        for (String s :chosenPokemons){
            chosenPok.add(s);
            movimientosSeleccionados.put(s, new ArrayList<>(Arrays.asList("", "", "", "")));
        }

        for (String s :chosenPokemons){
            ArrayList<String> tempOne = getValidMovements(s);
            JPanel movementPanel = createMovementPanel(s, tempOne,poobkemonGUI.domain.getPokedexIndexByName(s));
            centralPanel.add(movementPanel);
        }    
    }

    private ArrayList<String> getValidMovements(String pokName){
        ArrayList<String> tempOne = new ArrayList<>();
        ArrayList<String> validMoves = poobkemonGUI.domain.validMovements(poobkemonGUI.domain.getPokedex().get(pokName));
            for (String moveKey : validMoves){
                tempOne.add(moveKey);
            };
        return tempOne;
    }

    private void prepareElements(){
        
        setLayout(new BorderLayout());
        setOpaque(false);
        JPanel upPanel = new JPanel(new BorderLayout());
        upPanel.setOpaque(false); 

        texto = new JLabel("Player");
        texto.setOpaque(true);
        texto.setBackground(color);
        texto.setHorizontalAlignment(JLabel.CENTER);
        texto.setForeground(color);
        upPanel.add(new JLabel(" "),BorderLayout.CENTER);
        upPanel.add(texto, BorderLayout.NORTH);

        add(upPanel,BorderLayout.NORTH);
        centralPanel = new JPanel(new GridLayout(2,3,10,10));
        centralPanel.setOpaque(false);

        add(centralPanel, BorderLayout.CENTER);

        
        JPanel southPanel = new JPanel();
        southPanel.setOpaque(false);
        
        add(southPanel, BorderLayout.SOUTH);
    }
    public void setColor(Color newColor){
        color = newColor;
        texto.setBackground(color);
    }
    public Color  getColor(){
        return color;
    }
    
    protected JPanel createMovementPanel(String namePokemon, ArrayList<String> movements, String imagePath){ 
        if (namePokemon.equals("") || movements ==null || imagePath.equals("")) return new JPanel();
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/"+imagePath +".png"));
        Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(imageLabel, BorderLayout.NORTH);

        JLabel nameLabel = new JLabel(namePokemon, SwingConstants.CENTER);
        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.setOpaque(false);
        labelPanel.add(nameLabel, BorderLayout.CENTER);
        panel.add(labelPanel, BorderLayout.CENTER);

        JPanel container = new JPanel(new BorderLayout());
        container.setOpaque(false);
        JPanel movesPanel = new JPanel();
        movesPanel.setLayout(new BoxLayout(movesPanel, BoxLayout.Y_AXIS));
        movesPanel.setOpaque(false);    

        for(int i = 0; i<4;i++){
            final int buttonIndex = i;
            JButton moveButton = new JButton("Selecciona");
            
            moveButton.setFocusPainted(false);
            moveButton.setContentAreaFilled(true);
            moveButton.setOpaque(false);
            moveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            moveButton.addActionListener(e -> {
            JPopupMenu popupMenu = new JPopupMenu();

            JList<String> movesList = new JList<>(movements.toArray(new String[0]));
            movesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            movesList.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    if (evt.getClickCount() == 1) {
                        int index = movesList.locationToIndex(evt.getPoint());
                        String selectedMove = movesList.getModel().getElementAt(index);
                        moveButton.setText(selectedMove);
                        moveButton.setToolTipText(poobkemonGUI.domain.getMovements().get(selectedMove).createMovementForToolTip());
                        movimientosSeleccionados.get(namePokemon).set(buttonIndex, selectedMove);
                        popupMenu.setVisible(false);
                    }
                }
            });
            JScrollPane scrollPane = new JScrollPane(movesList);
            int visibleRows = Math.min(6, movements.size());
            scrollPane.setPreferredSize(new Dimension(120, visibleRows * 30));
            popupMenu.add(scrollPane);
            popupMenu.show(moveButton, moveButton.getWidth() / 2, moveButton.getHeight() / 2);
            });
            movesPanel.add(Box.createVerticalStrut(1));
            movesPanel.add(moveButton);
        }
        container.add(movesPanel, BorderLayout.CENTER);
        panel.add(container, BorderLayout.SOUTH);
        return panel;
    }

    public HashMap<String, ArrayList<String>> getMovementsMap() {
        HashMap<String, ArrayList<String>> deepCopy = new HashMap<>();
        for (Map.Entry<String, ArrayList<String>> entry : movimientosSeleccionados.entrySet()) {
            ArrayList<String> valuesCopy = new ArrayList<>(entry.getValue());
            deepCopy.put(entry.getKey(), valuesCopy);
        }
        return deepCopy;
    }

    public boolean isSelectedMovements(){
        for (Map.Entry<String, ArrayList<String>> entry: movimientosSeleccionados.entrySet()){
            for (String e :entry.getValue()){
                if(e.equals("")){
                     return false;
                }
            }
        }
        return true;
    }
    public ArrayList<String> getPokemonChoosen(){
        return chosenPok;
    }

    public HashMap<String, ArrayList<String>> getPokemonMovs(){
        return movimientosSeleccionados;
    }
    
    public void reset(){
        centralPanel.removeAll();
        movimientosSeleccionados.clear();
        chosenPok.clear();
        revalidate();
        repaint();
    }

}
