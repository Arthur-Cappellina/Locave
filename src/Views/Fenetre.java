package Views;


import Models.Modele;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class Fenetre extends JFrame {

    JPanel center;

    List<Observateur> obs;

    public Fenetre(String nom, VueTitre vueTitre, List<Observateur> obs){
        super(nom);
        setPreferredSize(new Dimension(500,600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        JPanel pan = (JPanel) getContentPane();
        pan.setLayout(new BorderLayout());
        pan.add(vueTitre, BorderLayout.NORTH);

        center = new JPanel(new CardLayout());

        this.obs = obs;

        for(Observateur panel : obs){
            System.out.println("Panneau affiche " + panel.getPanelName());
            center.add(panel, panel.getPanelName());
        }

        pan.add(center, BorderLayout.CENTER);

    }


    public void actualiser(String name, Modele m) throws SQLException {
        CardLayout card = (CardLayout) center.getLayout();
        card.show(center, name);
        for(Observateur o : obs){
            if(name.equals(o.getPanelName())) {
                o.actualiser(m);
            }
        }
    }



}
