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
public class PotionsPanelSelection extends JPanel {
    private  String backgroundImage = "emerald";
    private JLabel texto;
    private POOBkemonGUI pooBkemonGUI;
    private Color color;
    private JButton come;
    private JButton doneButton; 
    private JPanel panelScroll;
    private ArrayList<String> itemsChoosen;
    private ArrayList<JButton> buttons;
    private final int MAX_POKEMONS=2;

    public PotionsPanelSelection(POOBkemonGUI po){
        pooBkemonGUI = po;
        color = new Color(85, 85, 85, 100);
        prepareElements();
        prepareActions();
    }

    private void prepareElements(){
        come = new JButton("Back");
        pooBkemonGUI.styleButton(come);
        itemsChoosen = new ArrayList<>();
        buttons = new ArrayList<>();

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

        JPanel right = new JPanel(new BorderLayout());
        right.setOpaque(false);
        right.add(new JLabel(" "),BorderLayout.WEST);
        right.add(new JLabel(" "),BorderLayout.CENTER);
        right.add(new JLabel(" "),BorderLayout.EAST);
        add(right, BorderLayout.EAST);

        JPanel left = new JPanel(new BorderLayout());
        left.setOpaque(false);
        left.add(new JLabel(" "),BorderLayout.WEST);
        ImageIcon imagen = new ImageIcon(getClass().getResource("/resources/pokeball1.png"));


        left.add(new JLabel(imagen),BorderLayout.CENTER);
        left.add(new JLabel(" "),BorderLayout.EAST);
        add(left, BorderLayout.WEST);

        JPanel down = new JPanel(new BorderLayout());
        down.setOpaque(false);
        doneButton = new JButton ("Done!");
        //doneButton.setVisible(false);
        pooBkemonGUI.styleButton(doneButton);
        down.add(new JLabel(" "),BorderLayout.NORTH);
        down.add(new JLabel(" "),BorderLayout.CENTER);
        JPanel booton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        booton.setOpaque(false);
        booton.add(come);
        booton.add(doneButton);
        down.add(booton,BorderLayout.SOUTH);
        add(down, BorderLayout.SOUTH);

        JPanel centro = new JPanel(new BorderLayout());
        centro.setOpaque(false);
        //centro.setBackground(color);

        panelScroll = new JPanel(new GridLayout(4,4,1,1)) { //GridBagLayout   DEBERIA SER CALCULADO FILAS Y COLUMNAS   de dominio
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 100));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        //GridBagConstraints gbc = new GridBagConstraints();
        panelScroll.setOpaque(false);
        panelScroll.setBackground(color);
        


        JScrollPane scrollPane = new JScrollPane(panelScroll);
        scrollPane.setBackground(color);
        scrollPane.setOpaque(false);
	    scrollPane.setPreferredSize(new Dimension(300, 400));
	    scrollPane.getViewport().setOpaque(false);
	    scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	    scrollPane.getVerticalScrollBar().setUnitIncrement(30);

        InputMap inputMap = scrollPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = scrollPane.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "up");
	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "down");
	    actionMap.put("up", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            JScrollBar vertical = scrollPane.getVerticalScrollBar();
	            vertical.setValue(vertical.getValue() - vertical.getUnitIncrement());
	        }
	    });
	    actionMap.put("down", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            JScrollBar vertical = scrollPane.getVerticalScrollBar();
	            vertical.setValue(vertical.getValue() + vertical.getUnitIncrement());
	        }
	    });

        JPanel scrollContainer = new JPanel();
	    scrollContainer.setOpaque(false);
	    scrollContainer.setLayout(new BoxLayout(scrollContainer, BoxLayout.Y_AXIS));
	    scrollContainer.add(Box.createVerticalGlue());
	    scrollContainer.add(scrollPane);
	    scrollContainer.add(Box.createVerticalGlue());

	    centro.add(scrollContainer, BorderLayout.CENTER);
        add(centro, BorderLayout.CENTER);

        //scrollContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        //upPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        //down.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        //left.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        //right.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        createButtons();
    }
    public void setColor(Color newColor){
        color = newColor;
        texto.setBackground(color);
    }


    public JButton getButtonBack(){
        return come;
    }
    public JButton getNextBJButton(){
        return doneButton;
    }
    public void createButtons() {
        for (Entry<String, Item> entry : pooBkemonGUI.domain.getItems().entrySet()) {
            String nombre = entry.getKey();
            Item pokemon = entry.getValue();
            String ruta = pokemon.getName().toString() +".png";
            JButton button = createImageButton(nombre, ruta);
            buttons.add(button);
            button.addActionListener(e -> 
            selectionItems(button)
            );
            panelScroll.add(button);
            }
    }
    
    private void selectionItems(JButton button){
        if (itemsChoosen.contains(button.getToolTipText())) {
            button.setBackground(null);
            button.setOpaque(false);
            itemsChoosen.remove(button.getToolTipText());
        }
        else{
            button.setBackground(Color.GREEN);
            button.setOpaque(true);
            itemsChoosen.add(button.getToolTipText());
        }
        System.out.println(itemsChoosen);
    }
    public ArrayList<String> getItemsChoosen(){
        return itemsChoosen;
    }
    public int sizeChoosen(){
        return itemsChoosen.size();
    }
    private JButton createImageButton(String name,String imagePath) {
        int x=1, y=1;
        int width=50, height =50;
        Dimension smallSize = new Dimension(50, 30); 
        JButton button = new JButton();
        button.setBounds(x, y, width, height);
        
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/" + imagePath));
            
            if (imagePath.toLowerCase().endsWith(".gif")){
                button.setIcon(icon);
                button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
            } 
            else {
                Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(scaledImage));
            }
            //button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        }catch (Exception e) {
            button.setText("No imagen");
        }
        button.setPreferredSize(smallSize);
        button.setMinimumSize(smallSize);
        button.setMaximumSize(smallSize); 
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setToolTipText(name);
        
        return button;
    }
    private void prepareActions(){
        doneButton.addActionListener(e -> {
        if (sizeChoosen() < 2) {
            JOptionPane.showMessageDialog(this, 
                "Selecciona al menos 2 Items para la batalla!", 
                "Incompleta", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (sizeChoosen() > MAX_POKEMONS) {
            JOptionPane.showMessageDialog(this,
                "Solo puedes seleccionar máximo " + MAX_POKEMONS + " pokemones",
                "Límite excedido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        //INICIALIZAR POTIONS.
        //pooBkemonGUI.listMovements.infoSelectedPokemons(pokemonesChoosen);
        
            pooBkemonGUI.itemsEscogidos = getItemsChoosen();
            //pooBkemonGUI.addItemsToTrainer(pooBkemonGUI.chooser.getTrainer(),getItemsChoosen());
            pooBkemonGUI.panelInvetory.inicializate(getItemsChoosen());
            pooBkemonGUI.cardLayout.show(pooBkemonGUI.panelContenedor,"chooser");
            
            reset();
            System.out.println(pooBkemonGUI.domain.getTrainers().toString());
        /* 
        try{
        }catch(PoobkemonException i){
                System.out.println(i.getMessage());
            }*/
        
        });
    }

    public void reset(){
        itemsChoosen.clear();
        for (JButton button : buttons){
            button.setBackground(null);
            button.setOpaque(false);
        }
        revalidate();
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".JPG"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}

