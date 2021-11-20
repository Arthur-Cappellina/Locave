package Views;

import javax.swing.*;

public abstract class Observateur extends JPanel {

    public String getPanelName() {
        return name;
    }

    protected String name;
}
