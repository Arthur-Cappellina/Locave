import Confidentiel.mdp;
import Models.*;
import Views.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Requete requete = new Requete("cappelli6u", mdp.mdp);
        requete.miseAJourCalendrier(false, "2015-10-24", "2015-10-27", "5213ye54");

        VueTitre vue = new VueTitre();
        vue.setPreferredSize(new Dimension(500,100));

        VueCentre boutons = new VueCentre();
        boutons.setPreferredSize(new Dimension(500,500));

        JFrame frame = new JFrame("Jeu lumière");
        frame.setPreferredSize(new Dimension(500,600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pan = (JPanel) frame.getContentPane();
        pan.setLayout(new BorderLayout());
        pan.add(vue, BorderLayout.NORTH);
        pan.add(boutons, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }
}
