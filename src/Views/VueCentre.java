package Views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class VueCentre extends JPanel {

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
        add(disponibilites);
        add(calculerMontantLocation);
        add(miseAJourReservation);
        add(affichageAgences);
        add(affichageClients);
    }
}
