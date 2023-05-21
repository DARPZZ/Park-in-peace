package com.example.park;

import Model.DaoObject.Resevations;
import Model.Implements.DaoResevations;
import Model.Implements.DaoUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class HelloApplication extends Application
{

    private static Stage primaryStageHolder = null;
    private static HashMap<SceneName, Scene> sceneMap = new HashMap<>();

    @Override
    public void start(Stage stage) throws IOException
    {
        Model.Implements.DaoResevations daoResevations = new DaoResevations();

        /*

        List<Resevations> reservations = daoResevations.GetAll();

        for (Resevations reservation : reservations) {
            System.out.println("Reservation ID: " + reservation.getReservationID());
            System.out.println("Start Date: " + reservation.getStartDate());
            System.out.println("End Date: " + reservation.getEndDate());
            System.out.println("User ID: " + reservation.getUserID());
            System.out.println("Plot ID: " + reservation.getPlotID());
            System.out.println("----------------------------------");
        }
 */

        primaryStageHolder = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }

    public static void changeScene(SceneName sceneName)
    {
        if (sceneMap.containsKey(sceneName))
        {
            primaryStageHolder.setScene(sceneMap.get(sceneName));
        }
    }
}