package Views;

import Controleurs.ControleurBouton;
import Models.Modele;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

public class VueAffichageAgence extends Observateur {


    public static final String NAME = "Agence";

    private static String agences = "";


    public VueAffichageAgence(ControleurBouton cB) {
        name = NAME;
        JLabel titre = new JLabel("Les agences possédant tout les véhicules : ");
        add(titre);
    }

    public void actualiser(Modele m) throws SQLException {
        if(agences.equals("")){
            agences = m.getAgences();
            JLabel label = new JLabel(agences);
            add(label);
        }
    }
}