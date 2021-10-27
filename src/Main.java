import Confidentiel.mdp;
import Models.Requete;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        System.out.println(Requete.afficherListeVehicule("c3", "2015-10-02", "2015-10-07"));
    }
}
