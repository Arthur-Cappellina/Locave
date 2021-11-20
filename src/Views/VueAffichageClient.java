package Views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class VueAffichageClient extends Observateur {

    private String Smodele1, Smodele2;
    private JLabel JLmodele1, JLmodele2;
    private JTextField TFmodele1, TFmodele2;
    private JTextArea TFres;
    private JButton OK;

    public static final String NAME = "MiseAJour";

    public VueAffichageClient(){
        name = NAME;
        setLayout(new GridLayout(2, 3));

        Smodele1 = "Selectionnez votre 1er modele";
        Smodele2 = "Selectionnez votre 2eme modele";
        JLmodele1 = new JLabel(Smodele1);
        JLmodele2 = new JLabel(Smodele2);
        TFmodele1 = new JTextField();
        TFmodele2 = new JTextField();
        TFres = new JTextArea();
        OK = new JButton("OK");

        add(JLmodele1);
        add(TFmodele1);
        add(OK);
        add(JLmodele2);
        add(TFmodele2);
        add(TFres);
    }
}
