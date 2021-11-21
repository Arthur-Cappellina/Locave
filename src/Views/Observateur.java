package Views;

import Models.Modele;

import javax.swing.*;
import java.sql.SQLException;

public abstract class Observateur extends JPanel {

    public String getPanelName() {
        return name;
    }

    protected String name;

    public abstract void actualiser(Modele m) throws SQLException;
}
