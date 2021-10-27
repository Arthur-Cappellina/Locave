import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb";
        Connection cnt = DriverManager.getConnection(url,"cappelli6u", mdp.mdp);

        String req = "SELECT * FROM agence";

        PreparedStatement agences = cnt.prepareStatement(req);


        ResultSet rs = agences.executeQuery();

        while(rs.next()) {
            System.out.println(rs.getString(1));
        }

        rs.close();
        cnt.close();
        agences.close();
    }
}
