package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Adresse;
import model.Client;
import model.dao.DAOManager;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    private Button validationButton;

    @FXML
    private TextField nom;

    @FXML
    private TextField id;

    @FXML
    private TextField prenom;

    @FXML
    private TextField tel;

    @FXML
    private TextField codePostal;

    @FXML
    private TextField rue;

    @FXML
    private TextField ville;

    private static AllCustomersController allCustomersController;

    @FXML
    void validate(ActionEvent event) {
       if(!nom.getText().isEmpty() && !prenom.getText().isEmpty() && !tel.getText().isEmpty() && !rue.getText().isEmpty() && !ville.getText().isEmpty() && !codePostal.getText().isEmpty()){
            String nom_ = nom.getText();
            String prenom_ = prenom.getText();
            int tel_ = Integer.valueOf(tel.getText());
            String rue_ = rue.getText();
            int codePostal_ = Integer.valueOf(codePostal.getText());
            String ville_ = ville.getText();
            int id_client = id.getText().isEmpty() ? 0 : Integer.valueOf(id.getText());
            System.out.println(id_client);
            Adresse adresse = new Adresse(rue_,ville_,codePostal_);
            Client client = new Client(id_client,nom_,prenom_,tel_,adresse);
            if(id_client == 0) {
                DAOManager.insertClient(client);
                allCustomersController.refresh();
            }
            else if(DAOManager.updateClient(client) == 1) allCustomersController.refresh();
            Stage a = (Stage)(this.codePostal.getScene().getWindow()); a.close();
        }else{
            System.out.println("Veuillez remplir tous les champs !");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setDisable(true);
    }

    public void setClient(Client client){
        nom.setText(client.getNom());
        prenom.setText(client.getPrenom());
        tel.setText(Integer.toString(client.getTel()));
        rue.setText(client.getAdresse().getRue());
        ville.setText(client.getAdresse().getVille());
        codePostal.setText(Integer.toString(client.getAdresse().getCodePostal()));
        id.setText(Integer.toString(client.getId_client()));
    }

    public void setAllCustomersController(AllCustomersController allCustomersController_){
        allCustomersController = allCustomersController_;
    }
}
