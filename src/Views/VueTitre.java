package Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VueTitre extends JPanel {

    public VueTitre(JButton accueil) {
        setLayout(new GridLayout(1, 3));
        JPanel pan = new JPanel();
        pan.setLayout(new BorderLayout());
        pan.add(accueil, BorderLayout.CENTER);
        pan.setBorder(new EmptyBorder(30, 10, 30, 100));
        add(pan);
        JLabel titre = new JLabel("Locave");
        titre.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        add(titre);
    }

}
