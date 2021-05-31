package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.CategorieVehicule;
import model.Client;
import model.MarqueVehicule;
import model.Vehicule;
import model.dao.DAOManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static main.Main.*;
import static model.Vehicule.*;

public class AllCarsController extends Controller implements Initializable {

    @FXML
    private TableView<Vehicule> tableview;

    @FXML
    private TableColumn<Vehicule, String> immatriculation;

    @FXML
    private TableColumn<Vehicule, Integer> kilometrage;

    @FXML
    private TableColumn<Vehicule, String> climatisation;

    @FXML
    private TableColumn<Vehicule, String> categorie;

    @FXML
    private TableColumn<Vehicule, String> typeCarburant;

    @FXML
    private TableColumn<Vehicule, String> typeBoite;

    @FXML
    private TableColumn<Vehicule, String> modele;

    @FXML
    private TableColumn<Vehicule, String> agence;

    @FXML
    private ComboBox<String> categorieComboBox;

    @FXML
    private ComboBox<String> marqueComboBox;

    @FXML
    private RadioButton location;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        carsMenu.setStyle("-fx-background-color:  #339933; -fx-alignment: center;");
        immatriculation.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("immatriculation"));
        kilometrage.setCellValueFactory(new PropertyValueFactory<Vehicule,Integer>("kilometrage"));
        climatisation.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("climatisationString"));
        categorie.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("categorieString"));
        typeCarburant.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("typeCarburantString"));
        typeBoite.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("typeBoiteString"));
        modele.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("modeleString"));
        agence.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("agenceString"));


        if(init) {
            vehicules = DAOManager.getAllVehicules();
            init=!init;
        }

        if(!all){
          //  addClientButton.setDisable(true);
        }else{
            vehicules = DAOManager.getAllVehicules();
            comboMarqueIndex=0;
            comboCategoryIndex=0;
            locationBool=false;
        }

        /* combo Boxes */
        List<String> categorieList = new ArrayList<String>(); categorieList.add("---Aucune sélection---");
        for(CategorieVehicule cat: CategorieVehicule.values()){
            categorieList.add(cat.getDesignation());
        }
        fillComboBox(categorieComboBox, categorieList, 0);

        List<String> marqueList = new ArrayList<String>(); marqueList.add("---Aucune sélection---");
        for(MarqueVehicule marque: MarqueVehicule.values()){
            marqueList.add(marque.getDesignation());
        }
        fillComboBox(marqueComboBox, marqueList, 1);

        /* radioButton */
        location.selectedProperty().setValue(locationBool);
        location.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                locationBool = t1;
                if(t1) filterVehicules(DAOManager.getCurrentlyRentedCars(), false);
                else filterVehicules(DAOManager.getAllVehicules(), true);
            }
        });

        /* TableView */
        ObservableList<Vehicule> Vehicules = FXCollections.<Vehicule>observableArrayList();
        Vehicules.addAll(vehicules);
        tableview.setItems(Vehicules);
    }


    public void fillComboBox(ComboBox comboBox, List<String> list, int index){
        ObservableList<String> combos = FXCollections.<String>observableArrayList();
        combos.addAll(list);
        comboBox.setItems(combos);
        if(index==0){
            comboBox.setValue(comboBox.getItems().get(comboCategoryIndex));
        }else{
            comboBox.setValue(comboBox.getItems().get(comboMarqueIndex));
        }

        comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(t1 == "---Aucune sélection---") filterVehicules(DAOManager.getAllVehicules(), true);
                else{
                    if (index == 0) {
                        comboCategoryIndex=categorieComboBox.getItems().indexOf(t1);
                        filterVehicules(DAOManager.getCarsByCategory(t1), false);
                    } else {
                        comboMarqueIndex=marqueComboBox.getItems().indexOf(t1);
                        filterVehicules(DAOManager.getCarsByMarque(t1), false);
                    }
                }
            }
        });
    }

    public void filterVehicules(List<Vehicule> vehicules_, boolean all_){
        all=all_;
        vehicules = vehicules_;
        refresh();
    }

    public void refresh(){
        try{
            Stage window = (Stage)tableview.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/allCars.fxml"));
            window.setTitle(AppTitle);
            window.setScene(new Scene(root, WIDTH, HEIGHT));
            window.setResizable(false);
            window.show();
            System.out.println("oh yes");
        }catch (IOException e){

        }
    }

}
