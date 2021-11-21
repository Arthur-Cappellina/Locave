package Views;

import Controleurs.ControleurBouton;
import Models.Modele;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VueCalculLocation extends Observateur {


    public static final String NAME = "Calcul";

    private JTextField modeleField;

    private JSpinner nombreDeJoursSpinner;

    private JLabel reponse;


    public VueCalculLocation(ControleurBouton cB){
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

        modeleField = new JTextField();


        inputs.add(new JPanel());
        inputs.add(new JPanel());
        inputs.add(modelTexte);
        inputs.add(modeleField);

        JLabel joursTexte = new JLabel("Entrez le nombre de jours : ");

        nombreDeJoursSpinner = new JSpinner();

        inputs.add(new JPanel());
        inputs.add(new JPanel());
        inputs.add(joursTexte);
        inputs.add(nombreDeJoursSpinner);
        inputs.add(new JPanel());
        inputs.add(new JPanel());

        JButton rechercher = new JButton("Calculer montant");
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
        if(modeleField.getText().equals("")) return;

        int res = m.calculerMontantLocation(modeleField.getText(),(Integer) nombreDeJoursSpinner.getValue());
        if(res == -1) reponse.setText("Vous avez mal entrée les données");
        else reponse.setText("Cela coutera : " + res);
    }
}
