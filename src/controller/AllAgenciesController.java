package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Agence;
import model.Agence;
import model.dao.DAOManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AllAgenciesController extends Controller implements Initializable {

    @FXML
    private TableView<Agence> tableview;

    @FXML
    private TableColumn<Agence, Integer> id;

    @FXML
    private TableColumn<Agence, String> nom;

    @FXML
    private TableColumn<Agence, Integer> tel;

    @FXML
    private TableColumn<Agence, Integer> latitude;

    @FXML
    private TableColumn<Agence, Integer> longitude;

    @FXML
    private TableColumn<Agence, Integer> nbMaxVehicule;

    @FXML
    private TableColumn<Agence, String> adresse;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        agenciesMenu.setStyle("-fx-background-color:  #339933; -fx-alignment: center;");

        id.setCellValueFactory(new PropertyValueFactory<Agence,Integer>("id_agence"));
        nom.setCellValueFactory(new PropertyValueFactory<Agence,String>("nom"));
        tel.setCellValueFactory(new PropertyValueFactory<Agence,Integer>("tel"));
        latitude.setCellValueFactory(new PropertyValueFactory<Agence,Integer>("latitude"));
        longitude.setCellValueFactory(new PropertyValueFactory<Agence,Integer>("longitude"));
        nbMaxVehicule.setCellValueFactory(new PropertyValueFactory<Agence,Integer>("nb_max_vehicule"));
        adresse.setCellValueFactory(new PropertyValueFactory<Agence,String>("adresseString"));

        ObservableList<Agence> Agences = FXCollections.<Agence>observableArrayList();
        Agences.addAll(DAOManager.getAllAgencies());
        tableview.setItems(Agences);
    }

}
