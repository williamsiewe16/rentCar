package model.dao;

import model.Adresse;
import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    public static List<Client> getAllClients(Connection connection){
        List<Client> list = new ArrayList<Client>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Client INNER JOIN Adresse USING(Id_adresse) ORDER BY id_client");
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

    public static Client getClient(Connection connection,int id){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Client INNER JOIN Adresse USING(Id_adresse) WHERE id_client=?");
            preparedStatement.setInt(1,id);
            ResultSet res = preparedStatement.executeQuery();
            res.next();
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
            return client;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static List<Client> getAllClientsByName(Connection connection,String val){
        List<Client> list = new ArrayList<Client>();
        val = val.toLowerCase();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Client INNER JOIN Adresse USING(Id_adresse) WHERE lower(nom) like '?%' ORDER BY id_client");
            preparedStatement.setString(1,val);
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

    public static List<Client> getGoldClients(Connection connection){
        List<Client> list = new ArrayList<Client>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Client INNER JOIN Adresse USING(id_adresse) LIMIT 4");
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
            return list;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static List<Client> getNoLocationClients(Connection connection){
        List<Client> list = new ArrayList<Client>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Client INNER JOIN Adresse USING(Id_adresse) WHERE id_client NOT IN (select id_client from louer)");
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
            }
            return list;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static List<Client> getLocationPendingClients(Connection connection){
        List<Client> list = new ArrayList<Client>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Client INNER JOIN Adresse USING(Id_adresse) INNER JOIN louer USING(id_client) WHERE dateFinReelle is NULL");
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
            }
            return list;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static int updateClient(Connection connection, Client client){
        int id_adresse = createAddress(connection, client.getAdresse());
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Client SET nom=?, prenom=?, tel=?, id_adresse=? WHERE id_client=?");
            preparedStatement.setString(1,client.getNom());
            preparedStatement.setString(2,client.getPrenom());
            preparedStatement.setInt(3,client.getTel());
            preparedStatement.setInt(4,id_adresse);
            preparedStatement.setInt(5,client.getId_client());
            return preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public static int insertClient(Connection connection, Client client){
        int id_adresse = createAddress(connection, client.getAdresse());
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Client (nom,prenom,tel,id_adresse) VALUES (?,?,?,?)");
            preparedStatement.setString(1,client.getNom());
            preparedStatement.setString(2,client.getPrenom());
            preparedStatement.setInt(3,client.getTel());
            preparedStatement.setInt(4,id_adresse);
            return preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public static int createAddress(Connection connection, Adresse adresse){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Adresse WHERE lower(rue)=? AND lower(ville)=? AND codePostal=?");
            preparedStatement.setString(1,adresse.getRue().toLowerCase());
            preparedStatement.setString(2,adresse.getVille().toLowerCase());
            preparedStatement.setInt(3,adresse.getCodePostal());
            ResultSet res = preparedStatement.executeQuery();
            if(!res.next()){
                PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO Adresse (rue,ville,codePostal) VALUES (?,?,?)");
                preparedStatement1.setString(1,adresse.getRue());
                preparedStatement1.setString(2,adresse.getVille());
                preparedStatement1.setInt(3,adresse.getCodePostal());
                preparedStatement1.executeUpdate();
                PreparedStatement preparedStatement2 = connection.prepareStatement("select id_adresse from Adresse ORDER BY id_adresse DESC LIMIT 1");
                ResultSet res1 = preparedStatement2.executeQuery();
                res1.next();
                return res1.getInt("id_adresse");
            }else{
                return res.getInt("id_adresse");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean deleteClient(Connection connection, int id_client){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Client WHERE id_client=?");
            preparedStatement.setInt(1,id_client);
            return preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
