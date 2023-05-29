package com.example.park;

import Model.DaoObject.*;
import Model.Implements.*;
import View.*;
//import View.BookingsUd;
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
    Login login = new Login();
    public static ProfilePage profilePage = new ProfilePage(); //Technicaly not nice, but profilePage is only refered to in a static context anyways
    //Bookings bookings = new Bookings();
    //BookingsUd bookingsUd = new BookingsUd();
    Label toggleLabel = new Label("Press here to create user:");
    private final int HEIGHT = 768;
    private final int WIDTH = 1280;
    private static Stage primaryStageHolder = null;
    private static final HashMap<SceneName, Scene> SCENE_MAP = new HashMap<>();



    @Override
    public void start(Stage stage) throws IOException
    {





        login.setUserPublisher(login); // Giveren
        //login.subscribe(bookings); //tager
        login.subscribe(profilePage);
        login.setUserPublisher(login); // Giveren
        //login.subscribe(bookingsUd);

        primaryStageHolder = stage;
        primaryStageHolder.setMinWidth(400);
        SCENE_MAP.put(SceneName.Main,new MainPage().SCENE);
        //SCENE_MAP.put(SceneName.Bookings,bookings.SCENE);
        SCENE_MAP.put(SceneName.PlotPage, new PlotPage().SCENE);
        SCENE_MAP.put(SceneName.ProfilePage, profilePage.SCENE);
        //SCENE_MAP.put(SceneName.BookingsUd,bookingsUd.SCENE);
        AnchorPane anchorPane = new AnchorPane();

            Scene scene = new Scene(anchorPane, WIDTH, HEIGHT);
        String css = this.getClass().getResource("/Style.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("Park in Peace");
        stage.setScene(scene);
        createScene(anchorPane);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
    public static Stage getStage()
    {return primaryStageHolder;}
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
        toggleButton.setText("Create User");
        loginButton.setLayoutX(600);
        loginButton.setLayoutY(600);
        loginButton.setPrefWidth(150);

        toggleButton.setOnAction(event ->
        {
            if (toggleButton.isSelected()) {
                anchorPane.getChildren().clear();
                toggleButton.setText("Create User");
                toggleLabel.setText("Press here to login:");

                login.createUser(anchorPane,loginButton,toggleButton,toggleLabel);

                loginButton.setText("create a new user");

            } else {
                toggleButton.setText("Login");
                anchorPane.getChildren().clear();
                toggleLabel.setText("Press here to create a new user:");
                login.loginScene(anchorPane,loginButton);
                loginButton.setText("login");
            }
            anchorPane.getChildren().addAll(loginButton, toggleButton, toggleLabel);
        });
        anchorPane.getChildren().addAll(loginButton, toggleButton, toggleLabel);

    }
    }
