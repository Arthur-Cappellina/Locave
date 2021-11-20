package Views;

import Controleurs.ControleurBouton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VueAffichageAgence extends Observateur {


    public static final String NAME = "Agence";


    public VueAffichageAgence(ControleurBouton cB) {
        name = NAME;
        JLabel label = new JLabel("Calcul du montant de la location : ");

        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

        setBorder(new EmptyBorder(10, 10, 10, 10));

        setLayout(new BorderLayout());
        add(label, BorderLayout.NORTH);

        JPanel inputs = new JPanel();

        inputs.setBorder(new EmptyBorder(10, 0, 10, 0));
        inputs.setLayout(new GridLayout(6, 2));

        JLabel modelTexte = new JLabel("Entrez votre modele : ");

        JTextField field = new JTextField();
        field.setColumns(5);


        inputs.add(new JPanel());
        inputs.add(new JPanel());
        inputs.add(modelTexte);
        inputs.add(field);

        JLabel joursTexte = new JLabel("Entrez le nombre de jours : ");

        JSpinner joursField = new JSpinner();

        inputs.add(new JPanel());
        inputs.add(new JPanel());
        inputs.add(joursTexte);
        inputs.add(joursField);
        inputs.add(new JPanel());
        inputs.add(new JPanel());

        JButton rechercher = new JButton("Rechercher");

        inputs.add(new JPanel());

        inputs.add(rechercher);
        add(inputs, BorderLayout.CENTER);


        JPanel answer = new JPanel();

        JLabel lab = new JLabel("Reponse ici");

        answer.add(lab);
        add(answer, BorderLayout.SOUTH);

    }
}