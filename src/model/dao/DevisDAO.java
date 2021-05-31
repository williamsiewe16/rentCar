package model.dao;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DevisDAO {

    public static int insertDevis(Connection connection, Devis devis){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Devis (numero_devis,date_debut,date_fin,assurance) VALUES (?,?,?,?)");
            preparedStatement.setString(1,devis.getNumero());
            preparedStatement.setDate(2,devis.getDateDebut());
            preparedStatement.setDate(3,devis.getDateFin());
            preparedStatement.setBoolean(4,devis.getAssurance());
            return preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean deleteDevis(Connection connection, String devis_numero){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Devis WHERE numero_devis=?");
            preparedStatement.setString(1,devis_numero);
            return preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
