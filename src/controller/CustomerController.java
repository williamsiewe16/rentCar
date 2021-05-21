package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Adresse;
import model.Client;
import model.DAOManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

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
    private Button menu;



    @FXML
    private Button addClientButton;

    @FXML
    void addClient(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/client.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Ajouter un client");
        stage.setScene(new Scene(root, 500, 500));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void openMenu(ActionEvent event){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id_client"));
        nom.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Client,String>("prenom"));
        tel.setCellValueFactory(new PropertyValueFactory<Client,Integer>("tel"));
        adresse.setCellValueFactory(new PropertyValueFactory<Client,String>("adresseString"));

        ObservableList<Client> clients = FXCollections.<Client>observableArrayList();
        clients.addAll(DAOManager.getAllClients());
        tableview.setItems(clients);
        System.out.println("heyo");
    }
}
