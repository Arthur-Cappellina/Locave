import Confidentiel.mdp;
import Controleurs.ControleurBouton;
import Models.*;
import Views.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws SQLException{
        //  Moi moi = new Moi();
         Requete requete = new Requete("cappelli6u", mdp.mdp);
        //  System.out.println(requete.afficherListeVehicule("c3", "2015-10-02", "2015-10-05"));
         requete.miseAJourCalendrier(false, "2015-10-02", "2015-10-05", "7418yc54");
        //  System.out.println(requete.affichageClient("twingo", "xsara1.4sx"));
        //  System.out.println(requete.afficherListeVehicule("c1", "2015-10-27", "2015-10-28"));--

        JFrame fenetreConnexion = new JFrame("Connexion");


        JPanel pan = (JPanel) fenetreConnexion.getContentPane();
        pan.setLayout(new BorderLayout());
        pan.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel connexionPanel = new JPanel();

        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        fenetreConnexion.setPreferredSize(new Dimension(500,600));
        fenetreConnexion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connexionPanel.setBorder(new EmptyBorder(150, 50, 150, 50));
        connexionPanel.setLayout(new GridLayout(5,2));

        JButton connexion = new JButton("Se connecter");

        connexionPanel.add(new JLabel("Nom d'utilisateur"));
        connexionPanel.add(userField);
        connexionPanel.add(new JPanel());
        connexionPanel.add(new JPanel());
        connexionPanel.add(new JLabel("Mot de passe"));
        connexionPanel.add(passField);
        connexionPanel.add(new JPanel());
        connexionPanel.add(new JPanel());
        connexionPanel.add(new JLabel(""));
        connexionPanel.add(connexion);

        pan.add(new JLabel("Bienvenue sur Locave, veuillez vous connecter.", SwingConstants.CENTER), BorderLayout.NORTH);
        pan.add(connexionPanel, BorderLayout.CENTER);

        connexion.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    startLocave(new Requete(userField.getText(), passField.getText()));
                    fenetreConnexion.dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        fenetreConnexion.pack();
        fenetreConnexion.setVisible(true);
    }

    public static void startLocave(Requete requete){
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
        panels.add(new VueAffichageClient(cB));
        panels.add(new VueAffichageAgence(cB));
        panels.add(new VueMiseAJour(cB));

        // Creation de la vueTitre
        VueTitre vueTitre = new VueTitre(homeButton);
        vueTitre.setPreferredSize(new Dimension(500, 100));


        // On cree la fentre qui va tout contenir
        Fenetre f = new Fenetre("Locave", vueTitre, panels);

        // Le modele qui va tout gerer
        Modele m = new Modele(f, requete);

        // On definit le model au controleur
        cB.setModele(m);
    }
}
