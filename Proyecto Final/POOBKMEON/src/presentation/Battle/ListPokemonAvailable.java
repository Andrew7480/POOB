package presentation.Battle;
import java.awt.*;
import javax.swing.*;

import domain.LogPOOBKEMON;
import java.awt.event.*;
import java.util.*;
import presentation.POOBkemonGUI;

public class ListPokemonAvailable extends JPanel{
    private String backgroundImage = "emerald";
    private ArrayList<String> pokemonsChosen;
    private ArrayList<String> pokemonChange;
    private ArrayList<JButton> buttons;
    private JPanel panelScroll;
    private JButton doneButton;
    private Color color;
    private POOBkemonGUI poobKemonGUI;
    private JLabel texto;
    private JButton come;
    public int MAX_CHANGED = 1;

    public ListPokemonAvailable(POOBkemonGUI newPo){
        poobKemonGUI = newPo;
        color = new Color(0, 0, 255);
        come = new JButton("Back");
        doneButton = new JButton ("Change");
        prepareActions();
    }
    public void inicializate(){
        pokemonsChosen = poobKemonGUI.domain.getCurrentPokemons();
        pokemonChange = new ArrayList<>();
        setColor(poobKemonGUI.domain.getCurrentColor());
        prepareElements();
    }

    private void prepareElements(){
        
        poobKemonGUI.styleButton(come);
        buttons = new ArrayList<>();

        setLayout(new BorderLayout());
        setOpaque(false);


        JPanel upPanel = new JPanel(new BorderLayout());
        upPanel.setOpaque(false); 
        texto = new JLabel("Player");
        texto.setOpaque(true);
        texto.setBackground(new Color(51, 50, 50));
        texto.setHorizontalAlignment(JLabel.CENTER);
        texto.setForeground(Color.BLUE);
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
        
        poobKemonGUI.styleButton(doneButton);
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

        panelScroll = new JPanel(new GridLayout(4,4,1,1)) { //GridBagLayout   DEBERIA SER CALCULADO FILAS Y COLUMNAS   de dominio
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 100));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        JScrollPane scrollPane = new JScrollPane(panelScroll);
        scrollPane.setBackground(Color.BLUE);
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

        panelScroll.setOpaque(false);
        panelScroll.setBackground(Color.BLUE);

        centro.add(scrollContainer, BorderLayout.CENTER);
        add(centro, BorderLayout.CENTER);

        createButtons();
    }

    private void prepareActions(){}

    public int sizeChoosen(){
        return pokemonChange.size();
    }
    public boolean isOneOption(){
        return pokemonChange.size()==1;
    }
    public String getSelectedPokemon(){
        return pokemonChange.get(0);
    }

    private void createButtons(){
        for (String pokemonSelected : pokemonsChosen){
            String ruta = poobKemonGUI.domain.getPokedexIndexByName(pokemonSelected) + ".png";
            JButton button = createImageButton(pokemonSelected,ruta);
            buttons.add(button);
            button.addActionListener(e -> selectionPokemons(button));
            panelScroll.add(button);
        }
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
        }catch (Exception e) {
            button.setText("No imagen");
            LogPOOBKEMON.record(e);
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
    private void setColor(Color newCorlor){
        color = newCorlor;
    }

    private void selectionPokemons(JButton button){
        for (JButton b : buttons){
            b.setBackground(null);
            b.setOpaque(false);
        }
        pokemonChange.clear();
        button.setBackground(Color.GREEN);
        button.setOpaque(true);
        pokemonChange.add(button.getToolTipText());
        System.out.println("Inicial: "+pokemonChange.toString());
    }

    public JButton getBackButton(){
        return come;
    }

    public JButton getDoneButton(){
        return doneButton;
    }

    public void actualizar(){
        reset();
        color = poobKemonGUI.domain.getCurrentColor();
        pokemonsChosen = poobKemonGUI.domain.getCurrentPokemons();
        pokemonChange.clear();    
        inicializate();
    }

    public void reset(){
        System.out.println("resetea todo del cambio de pokemon");
        pokemonsChosen.clear();
        pokemonChange.clear();
        for (JButton button : buttons){
            button.setBackground(null);
            button.setOpaque(false);
            button.revalidate();
            button.repaint();
        }
        removeAll();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".JPG"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

}