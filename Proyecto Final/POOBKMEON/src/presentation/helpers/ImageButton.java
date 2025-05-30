package presentation.helpers;

import java.awt.*;
import javax.swing.*;

public class ImageButton extends JButton {
    private final Image img;
    private final String label;
    public ImageButton(String label, Image img) {
        super("");
        this.img = img;
        this.label = label;
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText(label);
        setActionCommand(label);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            int w = getWidth();
            int h = getHeight();
            int textHeight = 18;
            int imgW = img.getWidth(this);
            int imgH = img.getHeight(this);
            float aspect = (float) imgW / imgH;
            int drawH = h - textHeight - 4;
            int drawW = w;
            if (drawW / (float) drawH > aspect) {
                drawW = (int) (drawH * aspect);
            } else {
                drawH = (int) (drawW / aspect);
            }
            int x = (w - drawW) / 2;
            int y = (h - textHeight - drawH) / 2;
            g.drawImage(img, x, y, drawW, drawH, this);

            g.setFont(g.getFont().deriveFont(Font.BOLD, 12f));
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(label);
            g.setColor(Color.BLACK);
            g.drawString(label, (w - textWidth) / 2, h - 6);
        }
    }
}