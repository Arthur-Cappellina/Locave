package Models;

//import Confidentiel.mdp;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Requete {

    public static ArrayList<String> afficherListeVehicule(String categorie, String startDate, String endDate) throws SQLException {
        // Connexion
        String url = "jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb";
        Moi moi = new Moi();
        Connection cnt = DriverManager.getConnection(url,"marzouk7u", moi.mdp);

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

    public static String affichageAgence() throws SQLException{

        String url = "jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb";
        Moi moi = new Moi();
        Connection cnt = DriverManager.getConnection(url,"marzouk7u", moi.mdp);

        String res = "";

        /*String agenceRequete = "select distinct agence.CODE_AG from AGENCE inner join VEHICULE on AGENCE.CODE_AG = VEHICULE.CODE_AG " +
                "inner join CATEGORIE on VEHICULE.CODE_CATEG = CATEGORIE.CODE_CATEG where VEHICULE.CODE_CATEG = CATEGORIE.CODE_CATEG";

        PreparedStatement agenceStt = cnt.prepareStatement(agenceRequete);
        ResultSet rs = agenceStt.executeQuery();

        while (rs.next()){
            String code_ag = rs.getString("code_ag");
            res = code_ag;
        }

        agenceStt.close();
        rs.close();
        cnt.close();*/
        String agenceRequete = "select distinct agence.CODE_AG from AGENCE inner join VEHICULE on AGENCE.CODE_AG = VEHICULE.CODE_AG ";
        String categorieRequete = "select distinct code_categ from categorie";
        String VerifCategorieRequete = "select code_categ from vehicule";

        PreparedStatement agenceStt = cnt.prepareStatement(agenceRequete);
        PreparedStatement categorieStt = cnt.prepareStatement(categorieRequete);
        PreparedStatement VerifCategorieStt = cnt.prepareStatement(VerifCategorieRequete);

        ResultSet rsAgence = agenceStt.executeQuery();
        ResultSet rsCategorie = categorieStt.executeQuery();
        ResultSet rsVerifCategorie = VerifCategorieStt.executeQuery();

        ArrayList<String> listRsCategorie = new ArrayList<>();

        while (rsCategorie.next()){
            String categ = rsCategorie.getString("code_categ");
            listRsCategorie.add(categ);
        }

        while (rsAgence.next()){
            String codeAgence = rsAgence.getString("code_ag");
            while (rsVerifCategorie.next()){
                String codeCateg = rsVerifCategorie.getString("code_categ");
                for (int i = 0; i < listRsCategorie.size(); i++) {
                    if (codeCateg == listRsCategorie.get(i)){
                        System.out.println(codeAgence);
                    } else {
                        System.out.println("pas d'agence ayant toutes les categories");
                    }
                }
            }
        }

        /*for (int i = 0; i < listRsCategorie.size(); i++) {
                agenceStt.setString(1, listRsCategorie.get(i));
            }*/

        return res;
    }

    public static String affichageClient(String modele1, String modele2) throws SQLException {

        String res = "";

        String url = "jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb";
        Moi moi = new Moi();
        Connection cnt = DriverManager.getConnection(url,"marzouk7u", moi.mdp);

        String clientRequete = "select nom, ville, codpostal from CLIENT inner join dossier on dossier.CODE_CLI" +
                " = CLIENT.CODE_CLI inner join VEHICULE on DOSSIER.NO_IMM = VEHICULE.NO_IMM where MODELE = ?";

        PreparedStatement clientStt1 = cnt.prepareStatement(clientRequete);
        PreparedStatement clientStt2 = cnt.prepareStatement(clientRequete);

        if(modele1 != modele2) {
            clientStt1.setString(1, modele1);
            clientStt2.setString(1, modele2);
        } else {
            res = "les modeles sont les memes";
            return res;
        }

        ResultSet rs1 = clientStt1.executeQuery();
        ResultSet rs2 = clientStt2.executeQuery();

        String res1 = "";
        String res2 = "";
        Boolean bool = true;
        ArrayList<String> listRS1 = new ArrayList<>();
        ArrayList<String> listRS2 = new ArrayList<>();

        while(bool){
            while (rs1.next()){
                String client1nom = rs1.getString("nom");
                String client1ville = rs1.getString("ville");
                String client1codpostal = rs1.getString("codpostal");
                res1 = client1nom + " " + client1ville + " " + client1codpostal;
                listRS1.add(res1);
            }
            while (rs2.next()) {
                String client2nom = rs2.getString("nom");
                String client2ville = rs2.getString("ville");
                String client2codpostal = rs2.getString("codpostal");
                res2 = client2nom + " " + client2ville + " " + client2codpostal;
                listRS2.add(res2);
            }
            bool = false;
        }

        for (String o: listRS1) {
            if (listRS2.contains(o)){
                res += o + "\n";
            }
        }

        clientStt1.close();
        clientStt2.close();
        rs1.close();
        rs2.close();
        cnt.close();

        return res;
    }
}
