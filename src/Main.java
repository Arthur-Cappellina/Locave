import Models.Requete;
import Views.VueBoutons;
import Views.VueTitre;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Requete requete = new Requete();

        VueTitre vue = new VueTitre();
        vue.setPreferredSize(new Dimension(500,100));

        VueBoutons boutons = new VueBoutons();
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
        //System.out.println(Requete.afficherListeVehicule("c3", "2015-10-02", "2015-10-07"));
    }
}
