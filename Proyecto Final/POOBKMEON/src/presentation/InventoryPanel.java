package presentation;
import java.awt.*;
import javax.swing.*;
import domain.Item;
import java.awt.event.*;
import java.util.*;

public class InventoryPanel extends JPanel {
    private  String backgroundImage = "emerald";
    private JLabel texto;
    private POOBkemonGUI pooBkemonGUI;
    private Color color;
    private JButton come;
    private JButton doneButton; 
    private JPanel panelScroll;
    private ArrayList<String> itemSelected;
    private ArrayList<String> items;
    private ArrayList<JButton> buttons;
    public final int MAX_ITEM_SELECT = 1;

    public InventoryPanel(POOBkemonGUI po){
        pooBkemonGUI = po;
        color = new Color(85, 85, 85, 100);
        come = new JButton("Back To Battle!");
        doneButton = new JButton ("Use Item");
        texto = new JLabel("Player");
    }

    public void inicializate(){
        items = pooBkemonGUI.domain.getCurrentItems();
        itemSelected = new ArrayList<>();
        setColor(pooBkemonGUI.domain.getCurrentColor());
        prepareElements();
        prepareActions();
    }


    private JPanel upPanel(){
        JPanel up = new JPanel(new BorderLayout());
        up.setOpaque(false);
        texto.setOpaque(true);
        texto.setBackground(color);
        texto.setHorizontalAlignment(JLabel.CENTER);
        texto.setForeground(color);
        up.add(new JLabel(" "),BorderLayout.CENTER);
        up.add(texto, BorderLayout.NORTH);
        return up;
    }

    private JPanel rightPanel(){
        JPanel right = new JPanel(new BorderLayout());
        right.setOpaque(false);
        right.add(new JLabel(" "),BorderLayout.WEST);
        right.add(new JLabel(" "),BorderLayout.CENTER);
        right.add(new JLabel(" "),BorderLayout.EAST);
        return right;
    }

    private JPanel leftPanel(){
        JPanel left = new JPanel(new BorderLayout());
        left.setOpaque(false);
        left.add(new JLabel(" "),BorderLayout.WEST);
        ImageIcon imagen = new ImageIcon(getClass().getResource("/resources/pokeball1.png"));
        left.add(new JLabel(imagen),BorderLayout.CENTER);
        left.add(new JLabel(" "),BorderLayout.EAST);
        return left;
    }

    private JPanel downPanel(){
        JPanel down = new JPanel(new BorderLayout());
        down.setOpaque(false);
        //doneButton.setVisible(false);
        down.add(new JLabel(" "),BorderLayout.NORTH);
        down.add(new JLabel(" "),BorderLayout.CENTER);
        JPanel booton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        booton.setOpaque(false);
        booton.add(come);
        booton.add(doneButton);
        down.add(booton,BorderLayout.SOUTH);
        return down;
    }

    private void prepareElements(){
        buttons = new ArrayList<>();
        setLayout(new BorderLayout());
        setOpaque(false);
        pooBkemonGUI.styleButton(come);
        pooBkemonGUI.styleButton(doneButton);
        JPanel upPanel = upPanel();
        JPanel right = rightPanel();
        JPanel left = leftPanel();
        JPanel down = downPanel();
        add(upPanel,BorderLayout.NORTH);
        add(right, BorderLayout.EAST);
        add(left, BorderLayout.WEST);
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
    public void actualizar(){
        reset();
        color = pooBkemonGUI.domain.getCurrentColor();
        items = pooBkemonGUI.domain.getCurrentItems();
        itemSelected.clear(); 
        inicializate();
    }

    public JButton getButtonBack(){
        return come;
    }
    public JButton getNextBJButton(){
        return doneButton;
    }
    public void createButtons() {
        for (String itemSelected : items) {
            Item po1 = pooBkemonGUI.domain.getItems().get(itemSelected);
            String nombre = po1.getName();
            String ruta = po1.getName() +".png";
            JButton button = createImageButton(nombre, ruta);
            buttons.add(button);
            button.addActionListener(e -> 
            selectionItems(button)
            );
            panelScroll.add(button);
            }
    }
    
    private void selectionItems(JButton button){
        String itemName = button.getToolTipText();
        if (itemSelected.contains(itemName)) {
            button.setBackground(null);
            button.setOpaque(false);
            itemSelected.remove(itemName);
        }
        else{
            button.setBackground(Color.GREEN);
            button.setOpaque(true);
            itemSelected.add(itemName);
        }
        System.out.println(itemSelected);
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
        editButton(button, smallSize, name);
        return button;
    }

    private JButton editButton(JButton button, Dimension smallSize,String name){
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
    public boolean isOneOption(){
        return itemSelected.size() == 1;
    }
    public String itemSelected(){
        return itemSelected.get(0);
    }

    private void prepareActions(){}

    public void reset(){
        itemSelected.clear();
        items.clear();
        for (JButton button : buttons){
            button.setBackground(null);
            button.setOpaque(false);
        }
        removeAll();
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

