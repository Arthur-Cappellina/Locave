package Views;

import Models.Requete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class VueBoutons extends JPanel implements ActionListener{

    private JButton disponibilites, calculerMontantLocation, miseAJourReservation, affichageAgences, affichageClients;
    //Requete requete = new Requete();

    public VueBoutons(){
        setLayout(new GridLayout(5,1));
        disponibilites = new JButton("Regarder les disponibilités");
        calculerMontantLocation = new JButton("Calculer montant location");
        miseAJourReservation = new JButton("Mise a jour réservation");
        affichageAgences = new JButton("Affichages des agences");
        affichageClients = new JButton("Affichages des clients");
        this.add(disponibilites);
        this.add(calculerMontantLocation);
        this.add(miseAJourReservation);
        this.add(affichageAgences);
        this.add(affichageClients);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == disponibilites){
            try {
                System.out.println("disponibilites");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
