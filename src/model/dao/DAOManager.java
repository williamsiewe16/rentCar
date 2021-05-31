package model.dao;
import model.*;

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
    }
    public static List<Vehicule> getCarsByMarque(String marque){
        return CarDAO.getCarsByMarque(connection, marque);
    }

    public static List<Vehicule> getCurrentlyRentedCars(){
        return CarDAO.getCurrentlyRentedCars(connection);
    }

    /** Locations */
    public static List<Location> getAllLocations(){
        return LocationDAO.getAllLocations(connection);
    }

    public static boolean deleteLocation(String devis_numero){
        return LocationDAO.deleteLocation(connection, devis_numero);
    }
    public static int insertLocation(Location location){
        return  LocationDAO.insertLocation(connection, location);
    }
    public static void validateLocation(Location location){ LocationDAO.validateLocation(connection,location); }

    /** Devis */
    public static int insertDevis(Devis devis){
        return  DevisDAO.insertDevis(connection, devis);
    }

    public static boolean deleteDevis(String devis_numero){
        return DevisDAO.deleteDevis(connection, devis_numero);
    }


    public static void close(){
        try{
            connection.close();
            System.out.println("Fermeture de la connexion à la base de données !!!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}