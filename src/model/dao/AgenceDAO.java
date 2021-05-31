package model.dao;

import model.Adresse;
import model.Agence;
import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgenceDAO {

    public static List<Agence> getAllAgencies(Connection connection){
        List<Agence> list = new ArrayList<Agence>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Agence INNER JOIN Adresse USING(Id_adresse) ORDER BY id_agence");
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()){
                int id_agence = res.getInt("id_agence");
                String nom = res.getString("nom");
                int tel = res.getInt("tel");
                int latitude = res.getInt("latitude");
                int longitude = res.getInt("longitude");
                int nb_max_vehicule = res.getInt("nb_max_vehicule");
                int id_adresse = res.getInt("id_adresse");
                String rue = res.getString("rue");
                String ville = res.getString("ville");
                int codePostal = res.getInt("codePostal");

                Adresse adresse = new Adresse(id_adresse,rue,ville,codePostal);
                Agence agence = new Agence(id_agence,nom,tel,latitude,longitude,nb_max_vehicule,adresse);
                list.add(agence);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }
}
