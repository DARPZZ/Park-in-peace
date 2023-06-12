package com.example.park;

import Model.DaoObject.*;
import Model.DatabaseWorker.PlotList;
import Model.Implements.*;
import View.*;
//import View.BookingsUd;
import View.Bookings;
import View.Advertisement;
import View.MainPage;
import View.PlotPage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class HelloApplication extends Application
{
    Login login = new Login();
    public static PlotPage plotPage = new PlotPage();
    public static ProfilePage profilePage = new ProfilePage(); //Technicaly not nice, but profilePage is only refered to in a static context anyways
    Bookings bookings = new Bookings();
    Label toggleLabel = new Label("Press here to create user:");
    private final int HEIGHT = 768;
    private final int WIDTH = 1280;
    private static Stage primaryStageHolder = null;
    private static final HashMap<SceneName, Scene> SCENE_MAP = new HashMap<>();


    @Override
    public void start(Stage stage) throws IOException
    {
        PlotList.getSingleton().setList();
        Advertisement advertisement = new Advertisement();


        login.setUserPublisher(login); // Giveren
        login.subscribe(bookings); //tager
        login.subscribe(profilePage);
        login.setUserPublisher(login); // Giveren
        login.subscribe(advertisement);
        //login.subscribe(plotPage);

        //login.subscribe(bookingsUd);

        primaryStageHolder = stage;
        primaryStageHolder.setMinWidth(400);
        SCENE_MAP.put(SceneName.Advertisement, advertisement.scene);
        SCENE_MAP.put(SceneName.Main, new MainPage().scene);
        SCENE_MAP.put(SceneName.Bookings, bookings.scene);
        SCENE_MAP.put(SceneName.PlotPage, plotPage.scene);
        SCENE_MAP.put(SceneName.ProfilePage, profilePage.scene);
        AnchorPane anchorPane = new AnchorPane();
        //Scene scene = SCENE_MAP.get(SceneName.Main);
        Scene scene = new Scene(anchorPane, WIDTH, HEIGHT);
        

        String css = this.getClass().getResource("/Style.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Park in Peace");
        stage.setScene(scene);
        createScene(anchorPane,scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }

    public static Stage getStage()
    {
        return primaryStageHolder;
    }

    public static void changeScene(SceneName sceneName)
    {
        if (SCENE_MAP.containsKey(sceneName))
        {
            primaryStageHolder.setScene(SCENE_MAP.get(sceneName));
        }
    }

    public void createScene(AnchorPane anchorPane, Scene scene)
    {
        Button loginButton = new Button("Login");
        ToggleButton toggleButton = new ToggleButton();
        toggleLabel.setLayoutX(600);
        toggleLabel.setLayoutY(20);
        login.loginScene(anchorPane, loginButton);
        toggleButton.setLayoutY(50);
        toggleButton.setLayoutX(600);
        toggleButton.setPrefWidth(150);
        toggleButton.setText("Create User");
        loginButton.setLayoutX(600);
        loginButton.setLayoutY(600);
        loginButton.setPrefWidth(150);
        String css = this.getClass().getResource("/Style.css").toExternalForm();
        scene.getStylesheets().add(css);
        toggleButton.getStyleClass().add("button");
        toggleButton.setOnAction(event ->
        {
            if (toggleButton.isSelected())
            {
                anchorPane.getChildren().clear();
                toggleButton.setText("Create User");
                toggleLabel.setText("Press here to login:");

                login.createUser(anchorPane, loginButton, toggleButton, toggleLabel);

                loginButton.setText("create a new user");

            }
            else
            {
                toggleButton.setText("Login");
                anchorPane.getChildren().clear();
                toggleLabel.setText("Press here to create a new user:");
                login.loginScene(anchorPane, loginButton);
                loginButton.setText("login");
            }
            anchorPane.getChildren().addAll(loginButton, toggleButton, toggleLabel);
        });
        anchorPane.getChildren().addAll(loginButton, toggleButton, toggleLabel);
    }
}