package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Client;
import model.Devis;
import model.Location;
import model.Vehicule;
import model.dao.DAOManager;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.sql.Date;

public class LocationController implements Initializable {

    @FXML
    private Button validationButton;

    @FXML
    private ComboBox<String> clients;

    @FXML
    private ComboBox<String> vehicules;

    @FXML
    private DatePicker dateFin;

    @FXML
    private DatePicker dateDebut;

    @FXML
    private RadioButton assurance;


    private static AllLocationsController allLocationsController;
    private List<Vehicule> vehiculesList = new ArrayList<Vehicule>();
    private List<Client> clientList = new ArrayList<Client>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateDebut.setValue(LocalDate.now());
        List<String> list1 = new ArrayList<String>();
        for(Vehicule vehicule: DAOManager.getAllVehicules()){
            list1.add(vehicule.getImmatriculation());
        }
        fillComboBox(vehicules, list1);

        List<String> list2 = new ArrayList<String>();
        for(Client client: DAOManager.getAllClients()){
            list2.add(client.getId_client()+"- "+client.getNom()+" "+client.getPrenom());
        }
        fillComboBox(clients, list2);
    }

    @FXML
    void validate(ActionEvent event) {
        if(!vehicules.getValue().isEmpty() && !clients.getValue().isEmpty() && !dateFin.getValue().toString().isEmpty()){
            /* Devis */
            boolean assurance_ = assurance.selectedProperty().getValue();
            Date dateDebut_ = Date.valueOf(dateDebut.getValue());
            Date dateFin_ = Date.valueOf(dateFin.getValue());
            String numero_devis = Devis.generateString();
            String immatriculation = vehicules.getValue();
            int id_client = Integer.valueOf(clients.getValue().substring(0,clients.getValue().indexOf("-")));

            Devis devis = new Devis(numero_devis, dateDebut_, dateFin_, assurance_);
            Location location = new Location(new Client(id_client),new Vehicule(immatriculation), devis);

            DAOManager.insertDevis(devis);
            DAOManager.insertLocation(location);
            allLocationsController.refresh();
            Stage a = (Stage)(this.vehicules.getScene().getWindow()); a.close();

        }else{
            System.out.println("Veuillez remplir tous les champs !");
        }
    }

    public void fillComboBox(ComboBox comboBox, List<String> list){
        ObservableList<String> combos = FXCollections.<String>observableArrayList();
        combos.addAll(list);
        comboBox.setItems(combos);
    }

    public void setAllLocationsController(AllLocationsController allLocationsController_){
        allLocationsController = allLocationsController_;
    }
}
