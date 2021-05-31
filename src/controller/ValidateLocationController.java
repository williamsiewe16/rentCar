package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import model.dao.DAOManager;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.sql.Date;

public class ValidateLocationController implements Initializable {

    @FXML
    private Button validationButton;

    @FXML
    private ComboBox<String> etatVehicule;

    @FXML
    private ComboBox<Double> niveauCarburant;

    @FXML
    private DatePicker dateRetour;


    private static AllLocationsController allLocationsController;
    private static Location location;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateRetour.setValue(LocalDate.now());
        List<String> list1 = new ArrayList<String>();
        for(EtatVehicule etat: EtatVehicule.values()){
            list1.add(etat.getDesignation());
        }
        fillComboBox(etatVehicule, list1);

        List<Double> list2 = new ArrayList<Double>(){{
            add(1.0); add(0.75); add(0.5); add(0.25); add(0.0);
        }};

        fillComboBox(niveauCarburant, list2);
    }

    @FXML
    void validate(ActionEvent event) {
        if(!etatVehicule.getValue().isEmpty() && !dateRetour.getValue().toString().isEmpty()){
            location.setDateFinReelle(Date.valueOf(dateRetour.getValue()));
            location.setEtatVehicule(EtatVehicule.getByName(etatVehicule.getValue()));
            location.setNiveauCarburant(niveauCarburant.getValue());
            DAOManager.validateLocation(location);
            allLocationsController.refresh();
            Stage a = (Stage)(this.niveauCarburant.getScene().getWindow()); a.close();

        }else{
            System.out.println("Veuillez remplir tous les champs !");
        }
    }

    public void fillComboBox(ComboBox comboBox, List list){
        ObservableList combos = FXCollections.<String>observableArrayList();
        combos.addAll(list);
        comboBox.setItems(combos);
    }

    public void setAllLocationsController(AllLocationsController allLocationsController_){
        allLocationsController = allLocationsController_;
    }

    public void setLocation(Location location_){
        location=location_;
        System.out.println(location_.getVehicule());
    }
}
