package Views;

import javax.swing.*;
import java.awt.*;

public class VueTitre extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();
        Font font = new Font("Serif", Font.PLAIN, 20);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Locave",w/2-10 ,20);
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
    }
}
