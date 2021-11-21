package Models;

import Views.Fenetre;
import Views.VueCalculLocation;
import Views.VueHome;

import java.sql.SQLException;
import java.util.List;

public class Modele {

    private Fenetre fen;

    private Requete rq;

    private String menuActuel;

    public Modele(Fenetre fen, Requete r) {
        this.fen = fen;
        this.rq = r;
        menuActuel = VueHome.NAME;
    }

    public void changeMenu(String menu) {
        menuActuel = menu;
        try {
            fen.actualiser(menu, this);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void actualiser(){
        try {
            fen.actualiser(menuActuel, this);
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    public String getAgences() throws SQLException {
        return rq.affichageAgence();
    }

    public List<String> getVehiculesDisponible(String categorie, String dateDebut, String dateFin){
        try{
            return  rq.afficherListeVehicule(categorie, dateDebut, dateFin);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean mettreAJourDisponibilite(boolean estDisponible, String dateDebut, String dateFin, String immatriculation) throws SQLException {
        try{
            return rq.miseAJourCalendrier(estDisponible, dateDebut, dateFin, immatriculation);
        } catch (Exception e){
            return false;
        }
    }

    public int calculerMontantLocation(String modele, int nbJours){
        try{
            return rq.calculMontant(modele, nbJours);
        } catch(Exception e){
            return -1;
        }
    }

    public String afficherClient(String modele1, String modele2){
        try{
            return rq.affichageClient(modele1, modele2);
        } catch(Exception e){
            return "Pas de client trouvé";
        }
    }
}
