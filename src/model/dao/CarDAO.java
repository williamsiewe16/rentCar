package model.dao;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    public static List<Vehicule> getAllCars(Connection connection){
        List<Vehicule> list = new ArrayList<Vehicule>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("Select Agence.nom as nomAgence, Id_agence, Immatriculation, Kilométrage, Climatisation, TypeBoite.designation as typeBoite, TypeCarburant.designation as typeCarburant," +
                                                                                    " modele.designation as modele, CategorieVehicule.designation as categorieVehicule, CategorieVehicule.tarifJournalier as tarifJournalier " +
                                                                                            " FROM Vehicule INNER JOIN TypeBoite USING(Id_TypeBoite)" +
                                                                                                        " INNER JOIN TypeCarburant USING(Id_TypeCarburant)" +
                                                                                                        " INNER JOIN modele USING(Id_modele) INNER JOIN CategorieVehicule USING(Id_CategorieVehicule)" +
                                                                                                        " INNER JOIN Agence USING(Id_agence) ORDER BY Immatriculation");
            ResultSet res = preparedStatement.executeQuery();
           while (res.next()){
                int id_agence = res.getInt("Id_agence");
                String nomAgence = res.getString("nomAgence");

                String immatriculation = res.getString("Immatriculation");
                int kilometrage = res.getInt("Kilométrage");
                boolean climatisation = res.getInt("Climatisation") == 1 ? true: false;

                TypeBoite typeBoite = TypeBoite.getByName(res.getString("typeBoite"));
                TypeCarburant typeCarburant = TypeCarburant.getByName(res.getString("typeCarburant"));
                ModeleVehicule modele = ModeleVehicule.getByName(res.getString("modele"));
                CategorieVehicule categorieVehicule = CategorieVehicule.getByName(res.getString("categorieVehicule"));
                int tarifJournalier = res.getInt("tarifJournalier");

                Agence agence = new Agence(id_agence,nomAgence);
                Vehicule vehicule = new Vehicule(immatriculation,kilometrage,climatisation,agence,typeBoite,modele,categorieVehicule,typeCarburant);
                list.add(vehicule);
            }
           return list;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static List<Vehicule> getCarsByCategory(Connection connection, String category){
        List<Vehicule> list = new ArrayList<Vehicule>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from (Select Agence.nom as nomAgence, Id_agence, Immatriculation, Kilométrage, Climatisation, TypeBoite.designation as typeBoite, TypeCarburant.designation as typeCarburant," +
                                                                                    " modele.designation as modele, CategorieVehicule.designation as categorieVehicule, CategorieVehicule.tarifJournalier as tarifJournalier " +
                                                                                            " FROM Vehicule INNER JOIN TypeBoite USING(Id_TypeBoite)" +
                                                                                                        " INNER JOIN TypeCarburant USING(Id_TypeCarburant)" +
                                                                                                        " INNER JOIN modele USING(Id_modele) INNER JOIN CategorieVehicule USING(Id_CategorieVehicule)" +

                                                                                                        " INNER JOIN Agence USING(Id_agence) ORDER BY Immatriculation) as tab1 WHERE categorieVehicule=?");
            preparedStatement.setString(1,category);
            ResultSet res = preparedStatement.executeQuery();
           while (res.next()){
                int id_agence = res.getInt("Id_agence");
                String nomAgence = res.getString("nomAgence");

                String immatriculation = res.getString("Immatriculation");
                int kilometrage = res.getInt("Kilométrage");
                boolean climatisation = res.getInt("Climatisation") == 1 ? true: false;

                TypeBoite typeBoite = TypeBoite.getByName(res.getString("typeBoite"));
                TypeCarburant typeCarburant = TypeCarburant.getByName(res.getString("typeCarburant"));
                ModeleVehicule modele = ModeleVehicule.getByName(res.getString("modele"));
                CategorieVehicule categorieVehicule = CategorieVehicule.getByName(res.getString("categorieVehicule"));
                int tarifJournalier = res.getInt("tarifJournalier");

                Agence agence = new Agence(id_agence,nomAgence);
                Vehicule vehicule = new Vehicule(immatriculation,kilometrage,climatisation,agence,typeBoite,modele,categorieVehicule,typeCarburant);
                list.add(vehicule);
            }
           return list;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static List<Vehicule> getCarsByMarque(Connection connection, String marque){
        List<Vehicule> list = new ArrayList<Vehicule>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from (Select Agence.nom as nomAgence, Id_agence, Immatriculation, Kilométrage, Climatisation, TypeBoite.designation as typeBoite, TypeCarburant.designation as typeCarburant, modele.designation as modele, modele.Id_Marque, CategorieVehicule.designation as categorieVehicule, CategorieVehicule.tarifJournalier as tarifJournalier " +
                    "FROM Vehicule INNER JOIN TypeBoite USING(Id_TypeBoite) INNER JOIN TypeCarburant USING(Id_TypeCarburant) INNER JOIN modele USING(Id_modele) INNER JOIN CategorieVehicule USING(Id_CategorieVehicule) INNER JOIN Agence USING(id_agence) ORDER BY Immatriculation) as tab INNER JOIN Marque USING(Id_Marque) " +
                    "WHERE Marque.designation=?");
            preparedStatement.setString(1,marque);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()){
                int id_agence = res.getInt("Id_agence");
                String nomAgence = res.getString("nomAgence");

                String immatriculation = res.getString("Immatriculation");
                int kilometrage = res.getInt("Kilométrage");
                boolean climatisation = res.getInt("Climatisation") == 1 ? true: false;

                TypeBoite typeBoite = TypeBoite.getByName(res.getString("typeBoite"));
                TypeCarburant typeCarburant = TypeCarburant.getByName(res.getString("typeCarburant"));
                ModeleVehicule modele = ModeleVehicule.getByName(res.getString("modele"));
                CategorieVehicule categorieVehicule = CategorieVehicule.getByName(res.getString("categorieVehicule"));
                int tarifJournalier = res.getInt("tarifJournalier");

                Agence agence = new Agence(id_agence,nomAgence);
                Vehicule vehicule = new Vehicule(immatriculation,kilometrage,climatisation,agence,typeBoite,modele,categorieVehicule,typeCarburant);
                list.add(vehicule);
            }
            return list;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static List<Vehicule> getCurrentlyRentedCars(Connection connection){
        List<Vehicule> list = new ArrayList<Vehicule>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from (Select Agence.*, Immatriculation, Kilométrage, Climatisation, TypeBoite.designation as typeBoite, TypeCarburant.designation as typeCarburant, modele.designation as modele, CategorieVehicule.designation as categorieVehicule, CategorieVehicule.tarifJournalier as tarifJournalier" +
                    " FROM Vehicule INNER JOIN TypeBoite USING(Id_TypeBoite) INNER JOIN TypeCarburant USING(Id_TypeCarburant) INNER JOIN modele USING(Id_modele) INNER JOIN CategorieVehicule USING(Id_CategorieVehicule) INNER JOIN Agence USING(id_agence) ORDER BY Immatriculation) as tab INNER JOIN louer USING(Immatriculation)" +
                    "    WHERE dateFinReelle is null");
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()){
                int id_agence = res.getInt("Id_agence");
                String nomAgence = res.getString("nomAgence");

                String immatriculation = res.getString("Immatriculation");
                int kilometrage = res.getInt("Kilométrage");
                boolean climatisation = res.getInt("Climatisation") == 1 ? true: false;

                TypeBoite typeBoite = TypeBoite.getByName(res.getString("typeBoite"));
                TypeCarburant typeCarburant = TypeCarburant.getByName(res.getString("typeCarburant"));
                ModeleVehicule modele = ModeleVehicule.getByName(res.getString("modele"));
                CategorieVehicule categorieVehicule = CategorieVehicule.getByName(res.getString("categorieVehicule"));
                int tarifJournalier = res.getInt("tarifJournalier");

                Agence agence = new Agence(id_agence,nomAgence);
                Vehicule vehicule = new Vehicule(immatriculation,kilometrage,climatisation,agence,typeBoite,modele,categorieVehicule,typeCarburant);
                list.add(vehicule);
            }
            return list;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

}
