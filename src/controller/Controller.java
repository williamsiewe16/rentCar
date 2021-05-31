package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static main.Main.*;

import java.io.IOException;

public abstract class Controller {
    @FXML
    protected Button dashboardMenu;

    @FXML
    protected Button agenciesMenu;

    @FXML
    protected Button clientsMenu;

    @FXML
    protected Button EmployeesMenu;

    @FXML
    protected Button carsMenu;

    @FXML
    protected Button reservationsMenu;

    @FXML
    protected Button locationsMenu;


    @FXML
    void openCarsMenu(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/allCars.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)clientsMenu.getScene().getWindow();
            stage.setTitle(AppTitle);
            stage.setScene(new Scene(root, WIDTH, HEIGHT));
            stage.setResizable(false);
            stage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void openAgenciesMenu(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/allAgencies.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)clientsMenu.getScene().getWindow();
            stage.setTitle(AppTitle);
            stage.setScene(new Scene(root, WIDTH, HEIGHT));
            stage.setResizable(false);
            stage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void openClientsMenu(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/allCustomers.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)clientsMenu.getScene().getWindow();
            stage.setTitle(AppTitle);
            stage.setScene(new Scene(root, WIDTH, HEIGHT));
            stage.setResizable(false);
            stage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void openDashboardMenu(ActionEvent event) {
        System.out.println("working soon");
       /* try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/allAgencies.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)clientsMenu.getScene().getWindow();
            stage.setTitle(AppTitle);
            stage.setScene(new Scene(root, WIDTH, HEIGHT));
            stage.setResizable(false);
            stage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }*/
    }

    @FXML
    void openEmployeesMenu(ActionEvent event) {
        System.out.println("working soon");
        /*try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/allEmployees.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)clientsMenu.getScene().getWindow();
            stage.setTitle(AppTitle);
            stage.setScene(new Scene(root, WIDTH, HEIGHT));
            stage.setResizable(false);
            stage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }*/
    }

    @FXML
    void openLocationsMenu(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/allLocations.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)clientsMenu.getScene().getWindow();
            stage.setTitle(AppTitle);
            stage.setScene(new Scene(root, WIDTH, HEIGHT));
            stage.setResizable(false);
            stage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void openReservationsMenu(ActionEvent event) {
        System.out.println("working soon");
       /* try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/allAgencies.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)clientsMenu.getScene().getWindow();
            stage.setTitle(AppTitle);
            stage.setScene(new Scene(root, WIDTH, HEIGHT));
            stage.setResizable(false);
            stage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }*/
    }

}
