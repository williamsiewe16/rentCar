package model.dao;
import model.Adresse;
import model.Agence;
import model.Client;
import model.Vehicule;

import java.sql.*;
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

    /** Clients */
    public static List<Client> getAllClients(){
        return ClientDAO.getAllClients(connection);
    }

    public static Client getClient(int id){
        return ClientDAO.getClient(connection, id);
    }

    public static List<Client> getAllClientsByName(String val){
        return ClientDAO.getAllClientsByName(connection, val);
    }

    public static List<Client> getGoldClients(){
        return ClientDAO.getGoldClients(connection);
    }

    public static List<Client> getNoLocationClients(){
        return ClientDAO.getNoLocationClients(connection);
    }

    public static List<Client> getLocationPendingClients(){
        return ClientDAO.getLocationPendingClients(connection);
    }

    public static int updateClient(Client client){
        return ClientDAO.updateClient(connection, client);
    }

    public static int insertClient(Client client){
        return  ClientDAO.insertClient(connection, client);
    }

    public static boolean deleteClient(int id_client){
        return ClientDAO.deleteClient(connection, id_client);
    }

    /** Agences */
    public static List<Agence> getAllAgencies(){
        return AgenceDAO.getAllAgencies(connection);
    }

    /** Vehicules */
    public static List<Vehicule> getAllVehicules(){
        return CarDAO.getAllCars(connection);
    }
    public static List<Vehicule> getCarsByCategory(String category){
        return CarDAO.getCarsByCategory(connection,category);
    }  public static List<Vehicule> getCarsByMarque(String marque){
        return CarDAO.getCarsByMarque(connection, marque);
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