package Views;

import Controleurs.ControleurBouton;
import Models.Modele;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VueAfficherVehicules extends Observateur {


    public static final String NAME = "AfficherVehicules";

    private JTextField categorieField, anneeDate1, moisDate1, joursDate1, anneeDate2, moisDate2, joursDate2;

    private JLabel reponse;


    public VueAfficherVehicules(ControleurBouton cB) {
        name = NAME;

        JLabel label = new JLabel("Afficher les véhicules disponibles : ");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());
        add(label, BorderLayout.NORTH);

        // Le panneau des inputs
        JPanel inputs = new JPanel();

        inputs.setBorder(new EmptyBorder(10, 0, 10, 0));
        inputs.setLayout(new GridLayout(6, 2));

        JLabel modelTexte = new JLabel("Entrez la catégorie  : ");
        categorieField = new JTextField();

        inputs.add(new JPanel());
        inputs.add(new JPanel());
        inputs.add(modelTexte);
        inputs.add(categorieField);

        JLabel dateDebutText = new JLabel("Entrez la date de début  : ");
        inputs.add(dateDebutText);

        JPanel dateDebut = new JPanel();

        dateDebut.setLayout(new GridLayout(1,3));
        anneeDate1 = new JTextField("année");
        moisDate1 = new JTextField("mois");
        joursDate1 = new JTextField("jour");
        dateDebut.add(anneeDate1);
        dateDebut.add(moisDate1);
        dateDebut.add(joursDate1);

        inputs.add(dateDebut);

        JLabel dateFinText = new JLabel("Entrez la date de fin  : ");
        inputs.add(dateFinText);

        JPanel dateFin = new JPanel();

        dateFin.setLayout(new GridLayout(1,3));
        anneeDate2 = new JTextField("année");
        moisDate2 = new JTextField("mois");
        joursDate2 = new JTextField("jour");
        dateFin.add(anneeDate2);
        dateFin.add(moisDate2);
        dateFin.add(joursDate2);

        inputs.add(dateFin);

        inputs.add(new JPanel());
        inputs.add(new JPanel());

        JButton rechercher = new JButton("Rechercher véhicule");
        rechercher.addActionListener(cB);

        inputs.add(new JPanel());

        inputs.add(rechercher);
        add(inputs, BorderLayout.CENTER);


        JPanel answer = new JPanel();

        reponse = new JLabel("");

        answer.add(reponse);
        add(answer, BorderLayout.SOUTH);

    }


    @Override
    public void actualiser(Modele m) {
        if(!categorieField.getText().equals("")){
            String dateDebut = anneeDate1.getText() + "-" + moisDate1.getText() + "-" +  joursDate1.getText();
            String dateFin = anneeDate2.getText() + "-" + moisDate2.getText() + "-" +  joursDate2.getText();
            String res = "";
            List<String> vehicules =  m.getVehiculesDisponible(categorieField.getText(), dateDebut, dateFin);
            if(vehicules == null) {
                reponse.setText("Vous avez mal entré les données.");
                return;
            }
            for(String s : vehicules){
                res += s + " ; ";
            }
            reponse.setText(res);
        }
    }
}