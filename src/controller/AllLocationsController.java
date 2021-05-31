package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Location;
import model.dao.DAOManager;
import static model.Location.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import static main.Main.*;

public class AllLocationsController extends Controller implements Initializable {

    @FXML
    private TableView<Location> tableview;

    @FXML
    private TableColumn<Location, String> client;

    @FXML
    private TableColumn<Location, String> vehicule;

    @FXML
    private TableColumn<Location, String> dateDebut;

    @FXML
    private TableColumn<Location, String> dateFin;

    @FXML
    private TableColumn<Location, Integer> prixTotal;

    @FXML
    private TableColumn<Location, HBox> actions;

    @FXML
    private Button addLocationButton;

    @FXML
    private ComboBox<String> comboBox;



    @FXML
    void addLocation(ActionEvent event) {
        openLocationCreateStage();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        locationsMenu.setStyle("-fx-background-color:  #339933; -fx-alignment: center;");
        client.setCellValueFactory(new PropertyValueFactory<Location,String>("clientString"));
        vehicule.setCellValueFactory(new PropertyValueFactory<Location,String>("vehiculeString"));
        dateDebut.setCellValueFactory(new PropertyValueFactory<Location,String>("dateDebutString"));
        dateFin.setCellValueFactory(new PropertyValueFactory<Location,String>("dateFinString"));
        prixTotal.setCellValueFactory(new PropertyValueFactory<Location,Integer>("prixTotal"));
        actions.setCellValueFactory(new PropertyValueFactory<Location,HBox>("buttons"));

        ObservableList<Location> Locations = FXCollections.<Location>observableArrayList();
        Locations.addAll(getLocationsWithButtons(DAOManager.getAllLocations()));
        tableview.setItems(Locations);
    }


    public List<Location> getLocationsWithButtons(List<Location> Locations){

        for(Location location: Locations){
            Button devisButton = new Button("Devis");
            Font font1 = Font.font("Courier New", FontWeight.BOLD, 12);
            devisButton.setFont(font1);
            devisButton.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent actionEvent) {  openDevisStage();}});

            Button factureButton = new Button("Facture");
            Font font3 = Font.font("Courier New", FontWeight.BOLD, 12);
            factureButton.setFont(font3);
            factureButton.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent actionEvent) {
                System.out.println("google");
                if(devisButton.getText().equals("Facture")) openFactureStage();
                else{
                    System.out.println(location.getVehicule().getCategorie());
                    openValidateLocationStage(location);
                }
            }});

            Button supprimer = new Button("Supprimer");
            Font font2 = Font.font("Courier New", FontWeight.BOLD, 12);
            supprimer.setFont(font2);
            supprimer.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent actionEvent) { deleteLocation(location.getDevis().getNumero());}});

            location.setDevisButton(devisButton);
            location.setFactureButton(factureButton);
            location.setSupprimer(supprimer);
        }
        return Locations;
    }


    public void deleteLocation(String numero_devis){
        if(!DAOManager.deleteLocation(numero_devis)){
            refresh();
        }else{

        }
    }

    public void refresh(){
        try{
            Stage window = (Stage)addLocationButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/allLocations.fxml"));
            window.setTitle(AppTitle);
            window.setScene(new Scene(root, WIDTH, HEIGHT));
            window.setResizable(false);
            window.show();
            System.out.println("oh yes");
        }catch (IOException e){

        }
    }

    public void openLocationCreateStage() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/location.fxml"));
            Parent root = loader.load();
            LocationController locationController = loader.getController();
            locationController.setAllLocationsController(this);
            Stage stage = new Stage();
            stage.setTitle("Nouvelle Location");
            stage.setScene(new Scene(root, 487  , 492));
            stage.setResizable(false);
            stage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void openFactureStage() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/facture.fxml"));
            Parent root = loader.load();
            FactureController factureController = loader.getController();
           // factureController.setInfos();
            Stage stage = new Stage();
            stage.setTitle("Facture");
            stage.setScene(new Scene(root, 487  , 492));
            stage.setResizable(false);
            stage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void openDevisStage() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/devis.fxml"));
            Parent root = loader.load();
            DevisController devisController = loader.getController();
            //devisController.setInfos();
            Stage stage = new Stage();
            stage.setTitle("Devis");
            stage.setScene(new Scene(root, 487  , 492));
            stage.setResizable(false);
            stage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void openValidateLocationStage(Location location) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/validateLocation.fxml"));
            Parent root = loader.load();
            ValidateLocationController validateLocationController = loader.getController();
            validateLocationController.setAllLocationsController(this);
            validateLocationController.setLocation(location);
            Stage stage = new Stage();
            stage.setTitle("Validation du devis numéro "+location.getDevis().getNumero());
            stage.setScene(new Scene(root, 487  , 492));
            stage.setResizable(false);
            stage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
