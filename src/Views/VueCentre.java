package Views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueCentre extends JPanel implements ActionListener {

    private JButton disponibilites, calculerMontantLocation, miseAJourReservation, affichageAgences, affichageClients;

    public VueCentre(){
        setLayout(new GridLayout(5,1));


        Border compound = BorderFactory.createLineBorder(Color.black);


        disponibilites = new JButton("Regarder les disponibilités");
        disponibilites.setBorder(compound);

        disponibilites.setSize(new Dimension(5,5));

        calculerMontantLocation = new JButton("Calculer montant location");
        miseAJourReservation = new JButton("Mise a jour réservation");
        affichageAgences = new JButton("Affichages des agences");
        affichageClients = new JButton("Affichages des clients");
        affichageClients.addActionListener(this);
        add(disponibilites);
        add(calculerMontantLocation);
        add(miseAJourReservation);
        add(affichageAgences);
        add(affichageClients);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(affichageClients)){
            JFrame frame = new JFrame();
            VueAffichageClient vac = new VueAffichageClient();
            frame.setPreferredSize(new Dimension(1000, 300));
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(vac);
            frame.pack();
            frame.setVisible(true);
        }
    }
}
