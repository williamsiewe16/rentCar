package model.dao;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO {

    public static List<Location> getAllLocations(Connection connection){
        List<Location> list = new ArrayList<Location>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from louer INNER JOIN Client USING(id_Client)\n" +
                    "INNER JOIN Vehicule USING(Immatriculation)\n" +
                    "                 INNER JOIN (select Id_TypeBoite, designation as typeBoite FROM TypeBoite) as t1 USING(Id_TypeBoite)\n" +
                    "                    INNER JOIN (select Id_CategorieVehicule, designation as categorieVehicule FROM CategorieVehicule) as t2 USING(Id_CategorieVehicule)\n" +
                    "                    INNER JOIN (select Id_TypeCarburant, designation as typeCarburant FROM TypeCarburant) as t3 USING(Id_TypeCarburant)\n" +
                    "                   INNER JOIN (select Id_modele, designation as modele, Id_Marque FROM modele) as t4 USING(Id_modele)\n" +
                    "                   INNER JOIN (select Id_Marque, designation as marque FROM Marque) as t5 USING(Id_Marque) \n" +
                    "                    INNER JOIN Devis USING(numero_devis)\n" +
                    "                    INNER JOIN Adresse USING(Id_adresse)");

            ResultSet res = preparedStatement.executeQuery();
            while (res.next()){
                /* client */
                int id_client = res.getInt("id_client");
                String nom = res.getString("nom");
                String prenom = res.getString("prenom");
                int tel = res.getInt("tel");

                /* adresse */
                int id_adresse = res.getInt("id_adresse");
                String rue = res.getString("rue");
                String ville = res.getString("ville");
                int codePostal = res.getInt("codePostal");

                /* vehicule */
                String immatriculation = res.getString("Immatriculation");
                TypeBoite typeBoite = TypeBoite.getByName(res.getString("typeBoite"));
                TypeCarburant typeCarburant = TypeCarburant.getByName(res.getString("typeCarburant"));
                CategorieVehicule categorieVehicule = CategorieVehicule.getByName(res.getString("categorieVehicule"));
                ModeleVehicule modeleVehicule = ModeleVehicule.getByName(res.getString("modele"));
                boolean climatisation = res.getBoolean("Climatisation");
                int kilometrage = res.getInt("Kilométrage");

                /* devis */
                String numero_devis = res.getString("numero_devis");
                Date dateDebut = res.getDate("date_debut");
                Date dateFin = res.getDate("date_fin");
                boolean assurance = res.getBoolean("assurance");

                Adresse adresse = new Adresse(id_adresse,rue,ville,codePostal);
                Client client = new Client(id_client,nom,prenom,tel,adresse);
                Vehicule vehicule = new Vehicule(immatriculation,kilometrage,climatisation,typeBoite,modeleVehicule,categorieVehicule,typeCarburant);
                Devis devis = new Devis(numero_devis,dateDebut,dateFin,assurance);

                Date dateRetour = res.getDate("dateFinReelle");
                double prixTotal = res.getDouble("prixTotal");
                Location location = new Location(client,vehicule,devis,dateRetour,prixTotal);
                list.add(location);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static boolean deleteLocation(Connection connection, String devis_numero){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM louer WHERE numero_devis=(select numero_devis FROM Devis WHERE numero_devis=?)");
            preparedStatement.setString(1,devis_numero);
            preparedStatement.execute();
            return DAOManager.deleteDevis(devis_numero);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static int insertLocation(Connection connection, Location location){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO louer (id_Client,numero_devis,Immatriculation) VALUES (?,?,?)");
            preparedStatement.setInt(1,location.getClient().getId_client());
            preparedStatement.setString(2,location.getDevis().getNumero());
            preparedStatement.setString(3,location.getVehicule().getImmatriculation());
            return preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public static void validateLocation(Connection connection, Location location){
        try{
            location.setPrixTotal();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE louer set etat_vehicule=?, niveau_carburant=?, dateFinReelle=?, prixTotal=? WHERE numero_devis=?");
            preparedStatement.setInt(1,location.getEtatVehicule().getValue());
            preparedStatement.setDouble(2,location.getNiveauCarburant());
            preparedStatement.setDate(3,location.getDateFinReelle());
            preparedStatement.setDouble(4,location.getPrixTotal());
            preparedStatement.setString(5,location.getDevis().getNumero());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
