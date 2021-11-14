package Models;

//import Confidentiel.mdp;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;


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

        //Connection
        String url = "jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb";
        Moi moi = new Moi();
        Connection cnt = DriverManager.getConnection(url,"marzouk7u", moi.mdp);

        String res = "";
        /*Creation de 3 requetes
        * Une pour compter le total des catégories existantes dans la table catégorie
        * Une autre pour compter le nombre de catégories utilisées dans la table vehicule
        * et enfin une requete pour selectionner les agences ayant un nombre de catégories donné*/
        String categorieRequete = "select count(code_categ) from categorie";
        String VehiculeCategorieRequete = "select count(code_categ) from VEHICULE group by CODE_CATEG";
        String VehiculeRequete = "select CODE_AG from VEHICULE group by CODE_AG having count(distinct CODE_CATEG) = ?";

        //préparation de l'execution de la 1ère requete
        PreparedStatement categorieStt = cnt.prepareStatement(categorieRequete);
        //execution de la requete comptant le nombre de categorie dans la table categorie
        ResultSet rsCategorie = categorieStt.executeQuery();
        //stockage du nombre de categorie
        int nbCateg = 0;
        while (rsCategorie.next()){
            int n = rsCategorie.getInt("count(code_categ)");
            nbCateg = n;
        }

        //préparation de l'execution de la 2ème requete
        PreparedStatement VerifCategorieVehiculeStt = cnt.prepareStatement(VehiculeCategorieRequete);
        //éxecution de la requete comptant le nombre de categorie inséré dans la table véhicule
        ResultSet rsVerifCategorieVehicule = VerifCategorieVehiculeStt.executeQuery();
        //stockage du nombre de categorie dans la table vehicule
        int nbVerifCateg = 0;
        while (rsVerifCategorieVehicule.next()){
            nbVerifCateg += 1;
        }

        //préparation de l'execution de la 3ème requete
        PreparedStatement VerifCategorieStt = cnt.prepareStatement(VehiculeRequete);
        //On définit le nombre total de catégories
        VerifCategorieStt.setInt(1, nbCateg);
        //Execution de la requete qui a pour but de selectionner les agences selon le nombre de categories qu'elles possèdent
        ResultSet rsVerifCategorie = VerifCategorieStt.executeQuery();
        /*Vérification du nombre de catégorie
        * si le nombre de type de catégories dans véhicule est égal au nombre total de catégorie dans catégorie
        * alors on séléctionne les agences ayant ce nombre de catégories
        * sinon on retourne une chaine indiquant qu'aucune agence ne possède toutes les catégories*/
        if (nbVerifCateg == nbCateg) {
            while (rsVerifCategorie.next()) {
                String codeAgence = rsVerifCategorie.getString("code_ag");
                res = res + " " + codeAgence;
            }
        } else {
            res = "Aucune agence ne possede toutes les categories";
        }

        categorieStt.close();
        VerifCategorieStt.close();
        rsCategorie.close();
        rsVerifCategorie.close();
        cnt.close();

        return res;
    }

    public static String affichageClient(String modele1, String modele2) throws SQLException {

        String res = "";

        String url = "jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb";
        Moi moi = new Moi();
        Connection cnt = DriverManager.getConnection(url,"marzouk7u", moi.mdp);

        //Création d'une requete qui selectionne les noms, villes et codes postales des clients selon le modèle de véhicule qu'ils possèdent
        String clientRequete = "select nom, ville, codpostal from CLIENT inner join dossier on dossier.CODE_CLI" +
                " = CLIENT.CODE_CLI inner join VEHICULE on DOSSIER.NO_IMM = VEHICULE.NO_IMM where MODELE = ?";

        //Préparation de 2 éxecutions de la meme requete
        PreparedStatement clientStt1 = cnt.prepareStatement(clientRequete);
        PreparedStatement clientStt2 = cnt.prepareStatement(clientRequete);

        //Définition des différents modèles indiqués en paramètre si ces modèles sont différents
        if(modele1 != modele2) {
            clientStt1.setString(1, modele1);
            clientStt2.setString(1, modele2);
        } else {
            res = "les modeles sont les memes";
            return res;
        }

        //Exectution des 2 requetes selon leur modèle de vehicule chacune
        ResultSet rs1 = clientStt1.executeQuery();
        ResultSet rs2 = clientStt2.executeQuery();

        String res1 = "";
        String res2 = "";
        Boolean bool = true;
        //Création de 2 listes contenant les valeurs séléctionnées de chaque execution
        ArrayList<String> listRS1 = new ArrayList<>();
        ArrayList<String> listRS2 = new ArrayList<>();

        //Parcours des 2 exectutions en 1 seule fois
        while(bool){
            //Parcours des 2 executions qui stocke chaque ligne parcouru dans leurs listes dédiées
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

        /*Pour chaque element de la listRS1
        * on verifie si la listRS2 contient les éléments de la liste listRS1
        * si c'est le cas, alors la méthode retourne les éléments en question*/
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
