package Views;

import Controleurs.ControleurBouton;
import Models.Modele;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VueAffichageClient extends Observateur {

    public static final String NAME = "AffichageClient";

    private JTextField modele1Field, modele2Field;

    private JLabel reponse;


    public VueAffichageClient(ControleurBouton cB){
        name = NAME;
        JLabel label = new JLabel("Afficher les clients possédant 2 modèles de véhicules : ");

        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

        setBorder(new EmptyBorder(10, 10, 10, 10));

        setLayout(new BorderLayout());
        add(label, BorderLayout.NORTH);

        JPanel inputs = new JPanel();

        inputs.setBorder(new EmptyBorder(10, 0, 10, 0));
        inputs.setLayout(new GridLayout(6, 2));

        JLabel modelTexte = new JLabel("Entrez votre modele : ");

        modele1Field = new JTextField();

        inputs.add(new JPanel());
        inputs.add(new JPanel());
        inputs.add(modelTexte);
        inputs.add(modele1Field);

        JLabel joursTexte = new JLabel("Entrez le nombre de jours : ");

        modele2Field = new JTextField();

        inputs.add(new JPanel());
        inputs.add(new JPanel());
        inputs.add(joursTexte);
        inputs.add(modele2Field);
        inputs.add(new JPanel());
        inputs.add(new JPanel());

        JButton rechercher = new JButton("Trouver clients");
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
        System.out.println("Appel");
        if(modele1Field.getText().equals("")) return;
        reponse.setText(m.afficherClient(modele1Field.getText(), modele2Field.getText()));
    }
}
