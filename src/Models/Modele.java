package Models;

import Views.Fenetre;
import Views.VueCalculLocation;
import Views.VueHome;

public class Modele {

    private Fenetre fen;

    public Modele(Fenetre fen) {
        this.fen = fen;
    }

    public void changeMenu(String menu){
        fen.actualiser(menu);
    }
}
