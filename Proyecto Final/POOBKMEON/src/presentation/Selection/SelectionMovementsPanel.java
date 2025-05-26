package presentation.Selection;
import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import presentation.POOBkemonGUI;

import domain.Movement;
import domain.Pokemon;
import java.awt.*;
import java.util.*;

public class SelectionMovementsPanel extends JPanel{
    private POOBkemonGUI po;
    private JPanel centralPanel;
    private JLabel texto;
    private Color color;
    private HashMap<String, ArrayList<String>> movimientosSeleccionados = new HashMap<>();
    private ArrayList<String> chosenPok;


    public SelectionMovementsPanel(POOBkemonGUI newPo){
        po = newPo;
        color = new Color(85, 85, 85, 100);
        chosenPok = new ArrayList<>();
        prepareElements();
    }

    public void infoSelectedPokemons(ArrayList <String> chosenPokemons){
        reset();
        for (String s :chosenPokemons){
            chosenPok.add(s);
        }

        ArrayList<Pokemon> temp = new ArrayList<>();
        for (String s:chosenPokemons){
            temp.add(po.pokemones.get(s));
            movimientosSeleccionados.put(s, new ArrayList<>(Arrays.asList("", "", "", "")));
        }
        ArrayList<String> temp1 = new ArrayList<>();

        for (String s : chosenPokemons){
            TreeMap<String,Movement> validMoves = po.domain.validMovements(po.domain.getPokedex().get(s));
            for (String moveKey : validMoves.keySet()){
                temp1.add(moveKey);
            };
        }
        for (int i = 0; i < chosenPokemons.size(); i++){
            JPanel movementPanel = createMovementPanel(temp.get(i).getName(), temp1,temp.get(i).getPokedexIndex().toString());
            centralPanel.add(movementPanel);
        }
        
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
        panel.add(nameLabel, BorderLayout.CENTER);

        JPanel movesPanel = new JPanel(new GridLayout(2, 1));
        movesPanel.setOpaque(false);
        JPanel Arriba = new JPanel(new FlowLayout());
        JPanel Abajo = new JPanel(new FlowLayout());
        Arriba.setOpaque(false);
        Abajo.setOpaque(false);

        for(int i = 0; i<4;i++){
            final int buttonIndex = i;
            JButton moveButton = new JButton("Selecciona");
            
            moveButton.setFocusPainted(false);
            moveButton.setContentAreaFilled(true);
            moveButton.setOpaque(false);
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
                        moveButton.setToolTipText(po.domain.getMovements().get(selectedMove).createMovementForToolTip());
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
            if (i % 2 ==0)Arriba.add(moveButton);
            else{Abajo.add(moveButton);}
        }

        movesPanel.add(Arriba);
        movesPanel.add(Abajo);
        
        panel.add(movesPanel, BorderLayout.SOUTH);
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
