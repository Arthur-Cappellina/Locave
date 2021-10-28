package Views;

import javax.swing.*;
import java.awt.*;

public class VueBoutons extends JPanel {

    private JButton disponibilites, calculerMontantLocation, miseAJourReservation;

    public VueBoutons(){
        setLayout(new GridLayout(5,1));
        disponibilites = new JButton("Regarder les disponibilités");
        calculerMontantLocation = new JButton("Calculer montant location");
        miseAJourReservation = new JButton("Mise a jour réservation");
        this.add(disponibilites);
        this.add(calculerMontantLocation);
        this.add(miseAJourReservation);
    }
}
