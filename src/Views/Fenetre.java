package Views;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Fenetre extends JFrame {

    JPanel center;

    public Fenetre(String nom, VueTitre vueTitre, List<Observateur> obs){
        super(nom);
        setPreferredSize(new Dimension(500,600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pan = (JPanel) getContentPane();
        pan.setLayout(new BorderLayout());
        pan.add(vueTitre, BorderLayout.NORTH);

        center = new JPanel(new CardLayout());

        for(Observateur panel : obs){
            System.out.println("Panneau affiche " + panel.getPanelName());
            center.add(panel, panel.getPanelName());
        }

        pan.add(center, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }


    public void actualiser(String name){
        CardLayout card = (CardLayout) center.getLayout();
        card.show(center, name);
        System.out.println(name);
    }



}
