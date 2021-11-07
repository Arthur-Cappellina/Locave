import Models.*;
import Views.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Requete requete = new Requete();

        VueTitre vue = new VueTitre();
        vue.setPreferredSize(new Dimension(500,100));

        VueBoutons boutons = new VueBoutons();
        boutons.setPreferredSize(new Dimension(500,200));

        VueTexte texte = new VueTexte();
        texte.setPreferredSize(new Dimension(500, 300));

        JFrame frame = new JFrame("Jeu lumière");
        frame.setPreferredSize(new Dimension(1000,600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pan = (JPanel) frame.getContentPane();
        pan.setLayout(new BorderLayout());
        pan.add(vue, BorderLayout.NORTH);
        pan.add(boutons, BorderLayout.SOUTH);
        pan.add(texte, BorderLayout.EAST);

        frame.pack();
        frame.setVisible(true);
        //System.out.println(Requete.afficherListeVehicule("c3", "2015-10-02", "2015-10-07"));
    }
}
