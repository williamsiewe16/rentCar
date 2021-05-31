package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.dao.DAOManager;

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
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
