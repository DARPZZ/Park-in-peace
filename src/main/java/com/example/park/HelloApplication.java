package com.example.park;

import View.MainPage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class HelloApplication extends Application
{

    private static Stage primaryStageHolder = null;
    private static final HashMap<SceneName, Scene> SCENE_MAP = new HashMap<>();

    @Override
    public void start(Stage stage) throws IOException
    {
        primaryStageHolder = stage;
        primaryStageHolder.setMinWidth(400);
        SCENE_MAP.put(SceneName.Main,new MainPage().getScene());
        Scene scene = SCENE_MAP.get(SceneName.Main);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }

    public static void changeScene(SceneName sceneName)
    {
        if (SCENE_MAP.containsKey(sceneName))
        {
            primaryStageHolder.setScene(SCENE_MAP.get(sceneName));
        }
    }
}