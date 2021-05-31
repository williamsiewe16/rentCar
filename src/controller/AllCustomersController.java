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
import model.Client;
import model.dao.DAOManager;
import static model.Client.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import static main.Main.*;

public class AllCustomersController extends Controller implements Initializable {

    @FXML
    private TableView<Client> tableview;

    @FXML
    private TableColumn<Client, Integer> id;

    @FXML
    private TableColumn<Client, String> nom;

    @FXML
    private TableColumn<Client, String> prenom;

    @FXML
    private TableColumn<Client, Integer> tel;

    @FXML
    private TableColumn<Client, String> adresse;

    @FXML
    private TableColumn<Client, HBox> actions;

    @FXML
    private Button menu;

    @FXML
    private Button addClientButton;

    @FXML
    private ComboBox<String> comboBox;



    @FXML
    void addClient(ActionEvent event) {
        openCustomerCreateStage();
    }

    @FXML
    void openMenu(ActionEvent event){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clientsMenu.setStyle("-fx-background-color:  #339933; -fx-alignment: center;");
        id.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id_client"));
        nom.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Client,String>("prenom"));
        tel.setCellValueFactory(new PropertyValueFactory<Client,Integer>("tel"));
        adresse.setCellValueFactory(new PropertyValueFactory<Client,String>("adresseString"));
        actions.setCellValueFactory(new PropertyValueFactory<Client,HBox>("buttons"));

        if(init) {
            clients = DAOManager.getAllClients();
            init=!init;
        }

        if(!all){
            addClientButton.setDisable(true);
        }else{
            clients = DAOManager.getAllClients();
        }

        ObservableList<String> combos = FXCollections.<String>observableArrayList();
        combos.addAll("---Aucune sélection---","Clients Gold","Location en cours","aucune location");
        comboBox.setItems(combos);
        comboBox.setValue(comboBox.getItems().get(comboIndex));


        comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(t1 == "---Aucune sélection---") filterClients(DAOManager.getAllClients(), true,0);
                else if(t1 == "Clients Gold") filterClients(DAOManager.getGoldClients(), false,1);
                else if(t1 == "Location en cours") filterClients(DAOManager.getLocationPendingClients(), false,2);
                else if(t1 == "aucune location") filterClients(DAOManager.getNoLocationClients(), false,3);
            }
        });

        ObservableList<Client> clients = FXCollections.<Client>observableArrayList();
        clients.addAll(getClientsWithButtons(Client.clients));
        tableview.setItems(clients);
    }

    public void filterClients(List<Client> clients_, boolean all_, int index){
        all=all_;
        comboIndex=index;
        System.out.println(clients_.size());
        clients = clients_;
        refresh();
    }

    public List<Client> getClientsWithButtons(List<Client> clients){

        for(Client client: clients){
            Button modifier = new Button("Modifier");
            Font font1 = Font.font("Courier New", FontWeight.BOLD, 12);
            modifier.setFont(font1);
            modifier.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent actionEvent) { updateClient(client.getId_client()); }});

            Button supprimer = new Button("Supprimer");
            Font font2 = Font.font("Courier New", FontWeight.BOLD, 12);
            supprimer.setFont(font2);
            supprimer.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent actionEvent) { deleteClient(client.getId_client()); }});

            if(!all){
                modifier.setDisable(true);
                supprimer.setDisable(true);
            }

            client.setModifier(modifier);
            client.setSupprimer(supprimer);
        }
        return clients;
    }


    public void updateClient(int id_client){
        openCustomerUpdateStage(DAOManager.getClient(id_client));

    }

    public void deleteClient(int id_client){
        if(!DAOManager.deleteClient(id_client)){
            refresh();
        }else{

        }
    }

    public void refresh(){
        try{
            Stage window = (Stage)addClientButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/allCustomers.fxml"));
            window.setTitle(AppTitle);
            window.setScene(new Scene(root, WIDTH, HEIGHT));
            window.setResizable(false);
            window.show();
            System.out.println("oh yes");
        }catch (IOException e){

        }
    }

    public void openCustomerCreateStage() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/customer.fxml"));
            Parent root = loader.load();
            CustomerController customerController = loader.getController();
            customerController.setAllCustomersController(this);
            Stage stage = new Stage();
            stage.setTitle("Ajouter un client");
            stage.setScene(new Scene(root, 487  , 492));
            stage.setResizable(false);
            stage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void openCustomerUpdateStage(Client client) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/customer.fxml"));
            Parent root = loader.load();
            CustomerController customerController = loader.getController();
            customerController.setClient(client);
            customerController.setAllCustomersController(this);
            Stage stage = new Stage();
            stage.setTitle("Modifier un client");
            stage.setScene(new Scene(root, 487  , 492));
            stage.setResizable(false);
            stage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public Stage getWindow(){
        Stage window = (Stage)addClientButton.getScene().getWindow();
        return window;
    }
}
