import Confidentiel.mdp;
import Controleurs.ControleurBouton;
import Models.*;
import Views.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws SQLException{
        //  Moi moi = new Moi();
        Requete requete = new Requete("marzouk7u", mdp.mdp);
        //  System.out.println(requete.afficherListeVehicule("c3", "2015-10-02", "2015-10-05"));
        //  requete.miseAJourCalendrier(false, "2015-10-02", "2015-10-05", "7418yc54");
        //  System.out.println(requete.affichageClient("twingo", "xsara1.4sx"));
        //  System.out.println(requete.afficherListeVehicule("c1", "2015-10-27", "2015-10-28"));--

        System.out.println(requete.affichageClient());

        // On cree le controleur qui va se charger des boutons
        ControleurBouton cB = new ControleurBouton();


        // Bouton home
        JButton homeButton = new JButton("Accueil");
        homeButton.addActionListener(cB);

        // Menu calcul location

        // Liste des panels
        ArrayList<Observateur> panels = new ArrayList<>();

        panels.add(new VueHome(cB));
        panels.add(new VueAfficherVehicules(cB));
        panels.add(new VueCalculLocation(cB));
        panels.add(new VueAffichageClient());
        panels.add(new VueAffichageAgence(cB));

        // Creation de la vueTitre
        VueTitre vueTitre = new VueTitre(homeButton);
        vueTitre.setPreferredSize(new Dimension(500, 100));


        // On cree la fentre qui va tout contenir
        Fenetre f = new Fenetre("Locave", vueTitre, panels);

        // Le modele qui va tout gerer
        Modele m = new Modele(f);

        // On definit le model au controleur
        cB.setModele(m);
    }
}
