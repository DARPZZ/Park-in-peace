package com.example.park;

import Model.DaoObject.Resevations;
import Model.Implements.DaoResevations;
import Model.Implements.DaoUser;
import View.MainPage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class HelloApplication extends Application
{

    Label toggleLabel = new Label("Press here to create user:");
    private final int HEIGHT = 768;
    private final int WIDTH = 1280;
    private static Stage primaryStageHolder = null;
    private static final HashMap<SceneName, Scene> SCENE_MAP = new HashMap<>();

    private static HashMap<SceneName, Scene> sceneMap = new HashMap<>();
    Login login = new Login();
    @Override
    public void start(Stage stage) throws IOException
    {

        Model.Implements.DaoResevations daoResevations = new DaoResevations();
        primaryStageHolder = stage;
        primaryStageHolder.setMinWidth(400);
        SCENE_MAP.put(SceneName.Main,new MainPage().SCENE);
        AnchorPane anchorPane = new AnchorPane();

        Scene scene = new Scene(anchorPane, WIDTH, HEIGHT);
        createScene(anchorPane);


        stage.setTitle("Park in Peace");
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

    public void createScene(AnchorPane anchorPane)
    {
        Button loginButton = new Button("Login");
        ToggleButton toggleButton = new ToggleButton();
        toggleLabel.setLayoutX(600);
        toggleLabel.setLayoutY(20);
        login.loginScene(anchorPane,loginButton);
        toggleButton.setLayoutY(50);
        toggleButton.setLayoutX(600);
        toggleButton.setPrefWidth(150);
        toggleButton.setText("Login");
        loginButton.setLayoutX(600);
        loginButton.setLayoutY(600);
        loginButton.setPrefWidth(150);

        toggleButton.setOnAction(event ->
        {
            if (toggleButton.isSelected()) {
                anchorPane.getChildren().clear();
                toggleButton.setText("Create User");
                toggleLabel.setText("Press here to login:");
                login.createUser(anchorPane,loginButton);
                loginButton.setText("create a new user");
                anchorPane.getChildren().addAll(loginButton, toggleButton, toggleLabel);

            } else {
                toggleButton.setText("Login");
                anchorPane.getChildren().clear();
                toggleLabel.setText("Press here to create a new user:");
                login.loginScene(anchorPane,loginButton);
                loginButton.setText("login");
                anchorPane.getChildren().addAll(loginButton, toggleButton, toggleLabel);
            }
        });
        anchorPane.getChildren().addAll(loginButton, toggleButton, toggleLabel);

    }

}
