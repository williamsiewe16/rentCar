package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DAOManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        DAOManager.initialize();
        Parent root = FXMLLoader.load(getClass().getResource("/view/allClients.fxml"));
        primaryStage.setTitle("RentCar");
        primaryStage.setScene(new Scene(root, 1191, 686));
        primaryStage.setResizable(false);
      //  primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
