package Views;

import Controleurs.ControleurBouton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VueHome extends Observateur {

    public List<JButton> buttons ;

    public static final String NAME = "Home";

    public VueHome(ControleurBouton cB) {
        name = NAME;
        GridLayout gL = new GridLayout(5,1);
        gL.setVgap(10);
        setLayout(gL);

        setBorder(new EmptyBorder(10, 10, 10, 10));

        // On cree les boutons du menu home
        ArrayList<JButton> buttons = new ArrayList<JButton>();

        buttons.add(new JButton("Lister véhicules d'une catégorie disponible"));
        buttons.add(new JButton("Mettre à jour des réservations"));
        buttons.add(new JButton("Calculer le montant d'une location"));
        buttons.add(new JButton("Afficher la liste des agences possédant toutes les catégories de véhicule"));
        buttons.add(new JButton("Afficher la liste des clients qui ont loués deux modèle de voitures différentes"));

        for(JButton btn : buttons){
            btn.addActionListener(cB);
            add(btn);
        }

        this.buttons = buttons;
    }

}
