package presentation;
import javax.swing.*;
import java.awt.*;


public class maxwell extends JPanel{
        private JPanel PanelDerecho;
        private JPanel PanleIzquierdo;
        private static Color color1 = Color.RED;
        private static Color color2 = Color.BLUE;
        private static final Color Hole = Color.BLACK;
        /* 
        private final int[] azulDefaultDer = {22,31,76,128,144,175};
        private final int[] rojoDefaultDer = {27,34,63,168};
        private final int[] holesDefaultDer = {66,91,141};
        private final int[] azulDefaultIz = {25,96,102,162};
        private final int[] holesDefaultIz = {53,167,175};
        private final int[] rojoDefaultIz = {37,49,88,130,158,171};
        */
        private  int[] azulDer;
        private  int[] rojoDer;
        private  int[] holesDer;
        private  int[] azulIz;
        private  int[] holesIz;
        private  int[] rojoIz;

    public maxwell(int[][][] info){
        this(11,20);
        setVariables(info);
        paintComponents();
        //paintDefaultComponents();
    }
    public maxwell(int h, int w){
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1,2));
        setSize(getWidth(), getHeight());
        setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        prepareElements(h, w);
    }

    private void prepareElements(int h, int w){
        PanelDerecho = new JPanel(new GridLayout(h, w));
        PanleIzquierdo = new JPanel(new GridLayout(h, w));
        for (int i = 0; i < h * w; i++) {
            JPanel celd = new JPanel();
            //celd.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            PanelDerecho.add(celd);
        }
        for (int i = 0; i < h * w; i++) {
            JPanel celd = new JPanel();
            //celd.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            PanleIzquierdo.add(celd);
        }
        PanelDerecho.setBorder(getBorder());
        PanleIzquierdo.setBorder(getBorder());
        add(PanelDerecho);
        add(PanleIzquierdo);
    }
    /* 
    private void paintDefaultComponents(){
        for (int num:azulDefaultDer){
            PanelDerecho.getComponent(num).setBackground(color2);
        }
        for(int num:rojoDefaultDer){
            PanelDerecho.getComponent(num).setBackground(color1);
        }
        for(int num: holesDefaultDer){
            PanelDerecho.getComponent(num).setBackground(Hole);
        }
        for (int num:azulDefaultIz){
            PanleIzquierdo.getComponent(num).setBackground(color2);
        }
        for(int num:rojoDefaultIz){
            PanleIzquierdo.getComponent(num).setBackground(color1);
        }
        for(int num: holesDefaultIz){
            PanleIzquierdo.getComponent(num).setBackground(Hole);
        }
    }*/

    public void paintComponents(){
        for (int num:azulDer){
            PanelDerecho.getComponent(num).setBackground(color2);
        }
        for(int num:rojoDer){
            PanelDerecho.getComponent(num).setBackground(color1);
        }
        for(int num: holesDer){
            PanelDerecho.getComponent(num).setBackground(Hole);
        }
        for (int num:azulIz){
            PanleIzquierdo.getComponent(num).setBackground(color2);
        }
        for(int num:rojoIz){
            PanleIzquierdo.getComponent(num).setBackground(color1);
        }
        for(int num: holesIz){
            PanleIzquierdo.getComponent(num).setBackground(Hole);
        }
    }

    public void setColor1(Color color){
        color1 = color;
    }
    public void setColor2(Color color){
        color2 = color;
    }


    private void setWhite(){
        Component[] componentesDer = PanelDerecho.getComponents();
        Component[] componentesIz = PanleIzquierdo.getComponents();
        for (Component i:componentesDer){
            i.setBackground(Color.WHITE);
        }
        for (Component i:componentesIz){
            i.setBackground(Color.WHITE);
        }
    }
    private void setVariables(int[][][] info){
        azulDer = info[0][0];
        rojoDer = info[0][1];
        holesDer = info[0][2];
        azulIz = info[1][0];
        rojoIz = info[1][1];
        holesIz = info[1][2];
    }
    public void refresh(int[][][] info){
        setWhite();
        setVariables(info);
        paintComponents();
        PanelDerecho.repaint();
        PanleIzquierdo.repaint();
        repaint();
    }
    



}