package com.example.park;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class HelloApplication extends Application
{

    private static Stage primaryStageHolder = null;
    private static HashMap<SceneName, Scene> sceneMap = new HashMap<>();

    @Override
    public void start(Stage stage) throws IOException
    {
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