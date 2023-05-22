package com.example.park;

import Model.DaoObject.Resevations;
import Model.Implements.DaoResevations;
import Model.Implements.DaoUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class HelloApplication extends Application
{

    Label toggleLabel = new Label("Press here:");
    private final int HEIGHT = 768;
    private final int WIDTH = 1280;
    private static Stage primaryStageHolder = null;
    private static HashMap<SceneName, Scene> sceneMap = new HashMap<>();
    Login login = new Login();
    @Override
    public void start(Stage stage) throws IOException
    {

        Model.Implements.DaoResevations daoResevations = new DaoResevations();
        primaryStageHolder = stage;
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
        if (sceneMap.containsKey(sceneName)) {
            primaryStageHolder.setScene(sceneMap.get(sceneName));
        }
    }

    public void createScene(AnchorPane anchorPane)
    {
        Button loginButton = new Button("Login");
        ToggleButton toggleButton = new ToggleButton();
        toggleLabel.setLayoutX(600);
        toggleLabel.setLayoutY(20);

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
                toggleButton.setText("Create User");
                toggleLabel.setText("Press here to login:");
                login.createUser(anchorPane,loginButton);
                loginButton.setText("create a new user");

            } else {
                toggleButton.setText("Login");
                toggleLabel.setText("Press here to create a new user:");
                loginButton.setText("login");
            }
        });
        anchorPane.getChildren().addAll(loginButton, toggleButton, toggleLabel);
    }

}
