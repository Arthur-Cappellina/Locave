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

    /**
     * Constructeur d'un modèle en fonction d'une fenêtre et d'une requête
     * @param fen Fenêtre
     * @param r Requête
     */
    public Modele(Fenetre fen, Requete r) {
        this.fen = fen;
        this.rq = r;
        menuActuel = VueHome.NAME;
    }

    /**
     * Méthode permettant de changer le menu
     * @param menu Nom du menu
     */
    public void changeMenu(String menu) {
        menuActuel = menu;
        try {
            fen.actualiser(menu, this);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Méthode qui actualise la fenêtre
     */
    public void actualiser(){
        try {
            fen.actualiser(menuActuel, this);
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    /**
     * La méthode récupère les agences
     * @return Résultat de la recherche des agences
     * @throws SQLException
     */
    public String getAgences() throws SQLException {
        return rq.affichageAgence();
    }

    /**
     * La méthode récupère les véhicules disponibles selon la catégorie et entre 2 dates
     * @param categorie catégorie de véhicule
     * @param dateDebut date de début de la période vérifiant la disponibilité
     * @param dateFin date de fin de la période vérifiant la disponibilité
     * @return Liste des véhicules disponibles sous forme de String
     */
    public List<String> getVehiculesDisponible(String categorie, String dateDebut, String dateFin){
        try{
            return  rq.afficherListeVehicule(categorie, dateDebut, dateFin);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * La méthode récupère le résultat de la méthode permettant de faire une mise à jour sur le calendrier des réservations
     * @param estDisponible boolean qui a pour objectif de vérifier la disponibilité
     * @param dateDebut date de début de la période
     * @param dateFin date de fin de la période
     * @param immatriculation immatriculation du véhicule
     * @return boolean en fonction du nombre de dates
     * @throws SQLException
     */
    public boolean mettreAJourDisponibilite(boolean estDisponible, String dateDebut, String dateFin, String immatriculation) throws SQLException {
        try{
            return rq.miseAJourCalendrier(estDisponible, dateDebut, dateFin, immatriculation);
        } catch (Exception e){
            return false;
        }
    }

    /**
     * La méthode récupère le montant de la location d'un véhicule en fonction du modèle et du nombre de jours
     * @param modele Modèle du véhicule
     * @param nbJours Nombre de jours de la location
     * @return prix de la location après le calcul effectué
     */
    public int calculerMontantLocation(String modele, int nbJours){
        try{
            return rq.calculMontant(modele, nbJours);
        } catch(Exception e){
            return -1;
        }
    }

    /**
     * La méthode récupère les clients ayant 2 modèles différents
     * @param modele1 1er modèle du client
     * @param modele2 2ème modèle du client
     * @return Résultat de la recherche des clients
     */
    public String afficherClient(String modele1, String modele2){
        try{
            return rq.affichageClient(modele1, modele2);
        } catch(Exception e){
            return "Pas de client trouvé";
        }
    }
}
