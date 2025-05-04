package presentation;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;
public class InventoryPanel extends JPanel{
    private String backgroundImage = "fondo1";
    private JButton back;
    private JButton up;
    private JButton down;
    private POOBkemonGUI po;
    private JPanel listPanelitems;
    private JScrollPane scrollPane;
    public InventoryPanel(POOBkemonGUI newPo){
        po = newPo;
        setLayout(new BorderLayout());
        add(prepareExternalPanel(),BorderLayout.SOUTH);
        add(preparePanelToSwitch(),BorderLayout.NORTH);
    }

    private JPanel prepareExternalPanel(){
        back = new JButton("Back");
        po.styleButton(back);
        JPanel buttonPanel = po.invisiblePanelWithOpacity();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,20,10));
        buttonPanel.add(back);
        return buttonPanel;
    }

    private JPanel preparePanelToSwitch(){
        JPanel switche = new JPanel(new BorderLayout());
        switche.setOpaque(false);

        listPanelitems = new JPanel();
        listPanelitems.setLayout(new BoxLayout(listPanelitems, BoxLayout.Y_AXIS));
        listPanelitems.setOpaque(false);


        scrollPane = new JScrollPane(listPanelitems);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        switche.add(scrollPane, BorderLayout.CENTER);

        

        return switche;
    }


    public JButton getButtonBack(){
        return back;
    }
    public JButton getButtonUp(){
        return up;
    }
    public JButton getButtonDown(){
        return down;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".JPG"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}