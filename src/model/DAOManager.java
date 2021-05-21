package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOManager {
    private static Connection connection;
    private static String url = "jdbc:mysql://localhost:3306/rentcar";
    private static String login = "siewe";
    private static String password = "bonjour";

    public static void initialize(){
        try{
            connection = DriverManager.getConnection(url,login,password);
            System.out.println("MySQL connection established !!!\n");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static List<Client> getAllClients(){
        List<Client> list = new ArrayList<Client>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Client INNER JOIN Adresse USING(Id_adresse)");
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()){
                int id_client = res.getInt("id_client");
                String nom = res.getString("nom");
                String prenom = res.getString("prenom");
                int tel = res.getInt("tel");
                int id_adresse = res.getInt("id_adresse");
                String rue = res.getString("rue");
                String ville = res.getString("ville");
                int codePostal = res.getInt("codePostal");

                Adresse adresse = new Adresse(id_adresse,rue,ville,codePostal);
                Client client = new Client(id_client,nom,prenom,tel,adresse);
                list.add(client);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

   /* public boolean updateNbHeures(String nomCours, int nbHeures){
        try{
            Statement statement = connection.createStatement();
            int val = statement.executeUpdate("UPDATE COURS SET NBHEURES=NBHEURES+"+nbHeures+" WHERE NOM='"+nomCours+"'");
            if(val == 1){
                System.out.println("mise à jour du cours "+nomCours+" réussie\n");
                return true;
            }
            else{
                System.out.println("échec de la mise à jour");
                return false;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<CoursDAO> getCoursPS(){
        List<CoursDAO> list = new ArrayList<CoursDAO>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from COURS");
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()){
                int num = res.getInt("NUM_COURS");
                int nbheures = res.getInt("NBHEURES");
                int annee = res.getInt("ANNEE");
                String nom = res.getString("NOM");

                CoursDAO cours = new CoursDAO(num,nom,nbheures,annee);
                list.add(cours);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }*/

    public boolean updateNbHeuresPS(String nomCours, int nbHeures){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE COURS SET NBHEURES=NBHEURES+? WHERE NOM=?");
            preparedStatement.setInt(1,nbHeures);
            preparedStatement.setString(2,nomCours);
            int val = preparedStatement.executeUpdate();
            if(val == 1){
                System.out.println("mise à jour du cours "+nomCours+" réussie\n");
                return true;
            }
            else{
                System.out.println("échec de la mise à jour\n");
                return false;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }


    public void close(){
        try{
            connection.close();
            System.out.println("Fermeture de la connexion à la base de données !!!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}