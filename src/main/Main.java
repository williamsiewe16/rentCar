package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.dao.DAOManager;

import java.util.Optional;

public class Main extends Application {

    public static int WIDTH = 1191;
    public static int HEIGHT = 686;
    public static String AppTitle = "RentCar";

    @Override
    public void start(Stage primaryStage) throws Exception {
        DAOManager.initialize();
        Parent root = FXMLLoader.load(getClass().getResource("/view/allCustomers.fxml"));
        primaryStage.setTitle(AppTitle);
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.setResizable(false);
      //  primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Voulez-vous vraiment quitter l'application?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    //Libération des ressources
                    DAOManager.close();
                    Platform.exit();
                } else {
                    windowEvent.consume();
                    alert.close();
                }
            }
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
