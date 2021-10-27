package Models;

import Confidentiel.mdp;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Requete {

    public static ArrayList<String> afficherListeVehicule(String categorie, String startDate, String endDate) throws SQLException {
        // Connexion
        String url = "jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb";
        Connection cnt = DriverManager.getConnection(url,"cappelli6u", mdp.mdp);

        // On cree 2 requetes, une pour trouve les vehicules existant pour une categorie donnée et une autre pour savoir si ses vehicules sont libres
        String vehiculesRequetes = "SELECT * FROM vehicule where code_categ = ?";

        String immatriculationRequete =  "SELECT * FROM calendrier where no_imm = ?";

        // On prepare l'execution des requetes
        PreparedStatement vehiculesStt = cnt.prepareStatement(vehiculesRequetes);
        PreparedStatement immatriculationStt = cnt.prepareStatement(immatriculationRequete);

        // On definit la categorie que l'on recherche
        vehiculesStt.setString(1, categorie);

        // On recupere toutes les dates existantes entre 2 dates
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<Date> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(Date.valueOf(start));
            start = start.plusDays(1);
        }

        // Execution de la requete pour recupere les immatriculations disponibles
        ResultSet rs = vehiculesStt.executeQuery();
        ResultSet resImmatriculation = null;

        // Cette map stock toutes les dates disponibles d'un vehicules (immatriculation)
        HashMap<String, ArrayList<Date>> disponibilite = new HashMap<>();

        // On traite chaque vehicule recupérés
        while(rs.next()) {
            immatriculationStt.setString(1, rs.getString(1));
            resImmatriculation = immatriculationStt.executeQuery();

            // Pour chaque vehicule disponible
            while(resImmatriculation.next()){
                // On ajoute chaque date disponbile pour chaque vehicule
                if(resImmatriculation.getString(3) == null){
                    if(disponibilite.containsKey(resImmatriculation.getString(1))) {
                        disponibilite.get(resImmatriculation.getString(1)).add(resImmatriculation.getDate(2));
                    } else {
                        ArrayList<Date> dates = new ArrayList<>();
                        dates.add(resImmatriculation.getDate(2));
                        disponibilite.put(resImmatriculation.getString(1), dates);
                    }
                }
            }
        }

        // On créé finalement la liste des véhicules disponibles
        ArrayList<String> vehiculesDispo = new ArrayList<>();

        // Pour cela on vérifie on vérifie que chaque date est libre pour tout les véhicules
        for(String vehicule : disponibilite.keySet()){
            boolean estDisponible = true;
            for(Date dateBesoin : totalDates){
                if(!disponibilite.get(vehicule).contains(dateBesoin)) estDisponible = false;
            }

            if(estDisponible) vehiculesDispo.add(vehicule);
        }

        rs.close();
        immatriculationStt.close();
        resImmatriculation.close();
        vehiculesStt.close();
        vehiculesStt.close();
        cnt.close();

        return vehiculesDispo;
    }
}
