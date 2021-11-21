package Controleurs;

import Models.Modele;
import Views.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ControleurBouton implements ActionListener {

    private Modele modele;

    public ControleurBouton(Modele modele) {
        this.modele = modele;
    }

    public ControleurBouton() {
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        System.out.println(btn.getText());
        if(btn.getText().equals("Accueil")){
            modele.changeMenu(VueHome.NAME);
        } else if(btn.getText().equals("Calculer le montant d'une location")){
            modele.changeMenu(VueCalculLocation.NAME);
        } else if(btn.getText().equals("Lister véhicules d'une catégorie disponible")){
            modele.changeMenu(VueAfficherVehicules.NAME);
        } else if(btn.getText().equals("Mettre à jour des réservations")){
            modele.changeMenu(VueMiseAJour.NAME);
        } else if(btn.getText().equals("Afficher la liste des agences possédant toutes les catégories de véhicule")){
            modele.changeMenu(VueAffichageAgence.NAME);
        } else if(btn.getText().equals("Afficher la liste des clients qui ont loués deux modèle de voitures différentes")){
            modele.changeMenu(VueAffichageClient.NAME);
        } else{
            modele.actualiser();
        }
    }
}
