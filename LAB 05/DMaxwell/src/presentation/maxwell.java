package presentation;
import javax.swing.*;
import java.awt.*;

/**
 * Maxwell
 * 
 * @author  Tulio Riaño and Andrés Cardozo
 * @version 95%
 */
public class Maxwell extends JPanel{
        private JPanel Panel;
        private static Color color1 = Color.RED;
        private static Color color2 = Color.BLUE;
        private static final Color Hole = Color.BLACK;
        private int h;
        private int w;
        private  int[] holes;
        private  int[] particulasRed;
        private  int[] ParticulasBlue;
        private  int[] wall;

    /**
     * Constructor of Maxwell
     * @param newH
     * @param newW
     * @param info
     */
    public Maxwell(int newH, int newW,int [][] info ){
        this(newH,newW);
        setVariables(info);
        paintComponents();
    }
    /**
     * Constructor of Maxwell
     * @param info
     */
    public Maxwell(int [][] info){
        this(11,20);
        setVariables(info);
        paintComponents();
    }
    /**
     * Constructor of Maxwell
     * @param newH
     * @param newW
     */
    public Maxwell(int newH, int newW){
        h = newH;
        w = newW;
        setLayout(new GridLayout(1,1));
        setSize(getWidth(), getHeight());
        setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        prepareElements();
    }
    /*
     * prepare elements of the grid layout that is, the board
     */
    private void prepareElements(){
        Panel = new JPanel(new GridLayout(h, (2*w)+1));
        for (int i = 0; i < h * ((2*w)+1); i++) {
            JPanel celd = new JPanel();
            celd.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            Panel.add(celd);
        }
        Panel.setBorder(getBorder());
        add(Panel);
    }
    /*
     * paint the center of the board
     */
    private void paintCenter(){ //451
        int variable = h*((2*w) +1);
        int centroPar = (h/2) * (2 * w + 1) + w;
        for(int i: wall){
            Panel.getComponent(i).setBackground(Color.BLACK);
            if((int) variable/2 == i){
                Panel.getComponent(i).setBackground(Color.GRAY);
            }
            if (i == centroPar){
                Panel.getComponent(i).setBackground(Color.GRAY);
            }
            else if (i == centroPar){
                Panel.getComponent(i).setBackground(Color.GRAY);
            }
        }
    }
    /*
     * paints the jpanel
     */
    public void paintComponents(){
        paintCenter();
        for (int num:ParticulasBlue){
            Panel.getComponent(num).setBackground(color2);
        }
        for(int num:particulasRed){
            Panel.getComponent(num).setBackground(color1);
        }
        for(int num: holes){
            Panel.getComponent(num).setBackground(Hole);
        }
        
    }
    /**
     * Set the color of the particle 1
     * @param color
     */
    public void setColor1(Color color){
        color1 = color;
    }
    /**
     * Set the color of the particle 2
     * @param color
     */
    public void setColor2(Color color){
        color2 = color;
    }

    /*
     * Set white the board
     */
    private void setWhite(){
        Component[] componentesDer = Panel.getComponents();
        for (Component i:componentesDer){
            i.setBackground(Color.WHITE);
        }
    }
    /*
     * Set variables with the matrix of info
     * @param int[][] info
     */
    private void setVariables(int[][] info){
        ParticulasBlue = info[0];
        particulasRed = info[1];
        holes = info[2];
        wall = info[3];
    }
    /*
     * refresh the board with the matrix of info
     * @param int [][] info
     */
    public void refresh(int[][] info){
        setWhite();
        setVariables(info);
        paintCenter();
        paintComponents();
        Panel.revalidate();
        Panel.repaint();
        revalidate();
        repaint();

    }
    /**
     * reset the colors
     */
    public void reset(){
        color1 = Color.RED;
        color2 = Color.BLUE;
    }



}