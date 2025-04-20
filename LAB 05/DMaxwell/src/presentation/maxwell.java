package presentation;
import javax.swing.*;
import java.awt.*;


public class maxwell extends JPanel{
        private JPanel Panel;
        private static Color color1 = Color.RED;
        private static Color color2 = Color.BLUE;
        private static final Color Hole = Color.BLACK;
        private int h;
        private int w;

        private  int[] holes;
        private  int[] particulasRed;
        private  int[] ParticulasBlue;

    public maxwell(int newH, int newW,int [][] info ){
        this(newH,newW);
        setVariables(info);
        paintComponents();
    }
    public maxwell(int [][] info){
        this(11,20);
        setVariables(info);
        paintComponents();
    }
    public maxwell(int newH, int newW){
        h = newH;
        w = newW;
        //setBackground(Color.WHITE);
        setLayout(new GridLayout(1,1));
        setSize(getWidth(), getHeight());
        setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        prepareElements();
    }

    private void prepareElements(){
        Panel = new JPanel(new GridLayout(h, (2*w)+1));
        for (int i = 0; i < h * ((2*w)+1); i++) {
            JPanel celd = new JPanel();
            celd.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            Panel.add(celd);
        }
        paintCenter();
        Panel.setBorder(getBorder());
        add(Panel);
    }
    private void paintCenter(){ //451
        int variable = h*((2*w) +1);
        int i = w;
        while(i < variable){
            Panel.getComponent(i).setBackground(Color.BLACK);
            if( (int) variable/2 == i ){
                Panel.getComponent(i).setBackground(Color.GRAY);
            }
            i+=((2*w)+1);
        }
    }

    public void paintComponents(){
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

    public void setColor1(Color color){
        color1 = color;
    }
    public void setColor2(Color color){
        color2 = color;
    }


    private void setWhite(){
        Component[] componentesDer = Panel.getComponents();
        for (Component i:componentesDer){
            i.setBackground(Color.WHITE);
        }
    }

    private void setVariables(int[][] info){
        ParticulasBlue = info[0];
        particulasRed = info[1];
        holes = info[2];
    }

    public void refresh(int[][] info){
        setWhite();
        setVariables(info);
        paintCenter();
        paintComponents();
        Panel.repaint();
        repaint();
    }
    



}