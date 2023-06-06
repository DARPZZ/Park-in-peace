package com.example.park;
import Model.DaoObject.User;
import Model.DatabaseWorker.BlackList;
import Model.DatabaseWorker.PlotList;
import Model.DatabaseWorker.ReservationList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class Login implements UserPublisher
{
    Tooltip tooltip = new Tooltip();
    double strengthPercentage = 0;
    Label str = new Label("Password strenght");
    private List<UserSubscriber> subscribers = new ArrayList<>();
    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }
    User user = new User();
    private String loginName = "";
     TextField name = new TextField();
    TextField PhoneNumber = new TextField();
    TextField password = new TextField();
    TextField adress = new TextField();
    TextField email = new TextField();
    TextField zipCode = new TextField();
    private final int LAYOUT_x = 600;
    private ProgressBar passwordStrengthBar;
    private ProgressIndicator indicator;
    public void createUser(AnchorPane anchorPane, Button loginButton, ToggleButton toggleButton, Label toggleLabel)
    {
        name.setPromptText("Enter name");
        name.setLayoutX(LAYOUT_x);
        name.setLayoutY(100);
        PhoneNumber.setPromptText("Enter phone number");
        PhoneNumber.setLayoutX(LAYOUT_x);
        PhoneNumber.setLayoutY(150);
        password.setPromptText("Enter password");
        password.setLayoutX(LAYOUT_x);
        password.setLayoutY(200);
        adress.setPromptText("Enter address");
        adress.setLayoutX(LAYOUT_x);
        adress.setLayoutY(250);
        email.setPromptText("Enter email");
        email.setLayoutX(LAYOUT_x);
        email.setLayoutY(300);
        zipCode.setPromptText("Enter zip code");
        zipCode.setLayoutX(LAYOUT_x);
        zipCode.setLayoutY(350);


        loginButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                loginButton.getStyleClass().add("login-button-animation");
                if(validateUser()) {
                    insertInformation();
                    toggleButton.setText("Login");
                    anchorPane.getChildren().clear();
                    toggleLabel.setText("Tryk her for at lave en ny bruger:");
                    loginScene(anchorPane, loginButton);
                    loginButton.setText("login");
                    anchorPane.getChildren().addAll(loginButton, toggleButton, toggleLabel);
                }
            }
        });
      passwordStrenghts();
        password.textProperty().addListener((observable, oldValue, newValue) -> updatePasswordStrength(newValue));


        anchorPane.getChildren().addAll(name, PhoneNumber, password, adress, email, zipCode,passwordStrengthBar,str);
    }

    public void insertInformation()
    {
        //Model.Implements.DaoUser daoUser = new DaoUser();

            if (validateUser()) {
                 user = new User(name.getText(), PhoneNumber.getText(), password.getText(), adress.getText(), email.getText(), Integer.parseInt(zipCode.getText()));
                BlackList.getSingleton().CreateUser(user);
                //userPublisher.notifySubscribers(user);
            }
    }

    public boolean validateUser()
    {

        tooltip.setText("Invalid");
        boolean Error = false;
        tooltip.setShowDelay(Duration.ZERO);
        if (Objects.equals(name.getText(), "")) {
            name.setTooltip(tooltip);
            name.setId("labelError");
            Error = true;
        }
        boolean isNumeric = PhoneNumber.getText().chars().allMatch( Character::isDigit );
        if (Objects.equals(PhoneNumber.getText(), "")|| !isNumeric) {
            PhoneNumber.setTooltip(tooltip);
            PhoneNumber.getStyleClass().add("warning-badge");
            Error = true;
        }
        if (Objects.equals(password.getText(), "")) {
            password.setTooltip(tooltip);
            password.getStyleClass().add("warning-badge");
            password.setId("labelError");
            Error = true;
        }
        if (Objects.equals(adress.getText(), "")) {
            adress.setTooltip(tooltip);

            adress.getStyleClass().add("warning-badge");
            Error = true;
        }
        if (Objects.equals(email.getText(), "")) {
            email.setTooltip(tooltip);

            email.getStyleClass().add("warning-badge");
            Error = true;
        }
        isNumeric = zipCode.getText().chars().allMatch( Character::isDigit );
        if (Objects.equals(zipCode.getText(), "")|| !isNumeric) {
            zipCode.setTooltip(tooltip);
            zipCode.getStyleClass().add("warning-badge");

            Error = true;
        }
        if (Error)
        {
            return false;
        }else {

            return true;

        }
    }
    public void loginScene(AnchorPane anchorPane, Button logIn)
    {

        name.setLayoutX(LAYOUT_x);
        name.setLayoutY(100);
        password.setLayoutX(LAYOUT_x);
        password.setLayoutY(150);

        logIn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                tooltip.setShowDelay(Duration.ZERO);
                tooltip.setText("Invalid");
                String kodeord = password.getText();
                String username = name.getText();
                setLoginName(username);
                user = BlackList.getSingleton().checkLogin(username,kodeord);
                //region update getuser method - userLoginCheck storedprocedure er lavet
                if (user == null || !user.getName().equals(username) || !user.getPassword().equals(kodeord))
                {
                    name.getStyleClass().add("warning-badge");
                    password.getStyleClass().add("warning-badge");
                    System.out.println("wrong pass word ");
                    name.setTooltip(tooltip);
                    password.setTooltip(tooltip);
                }else
                {
                    userPublisher.notifySubscribers(user);
                    PlotList.getSingleton().setList();
                    HelloApplication.plotPage.initPlotPage();// stuff jeg helst vill kører i contructoren
                    HelloApplication.plotPage.createPopUpCreatePlot();//
                    HelloApplication.plotPage.preparePlotGrid();//

                    ReservationList.getSingleton().setList();
                    BlackList.getSingleton().setBlackList(user);
                    HelloApplication.changeScene(SceneName.Main);
                    System.out.println("Login successful!");


                }
            }
        });
        anchorPane.getChildren().addAll(name,password);
    }
    private UserPublisher userPublisher;

    @Override
    public void subscribe(UserSubscriber subscriber)
    {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(UserSubscriber subscriber)
    {
        subscribers.remove(subscriber);

    }

    @Override
    public void notifySubscribers(User user)
    {
        for (UserSubscriber subscriber : subscribers) {
            subscriber.onUserReceived(user);
        }
    }
    public void passwordStrenghts()
    {
        passwordStrengthBar = new ProgressBar(0);
        passwordStrengthBar.setLayoutX(LAYOUT_x);
        passwordStrengthBar.setLayoutY(425);
        passwordStrengthBar.setPrefWidth(150);
        str.setLayoutX(passwordStrengthBar.getLayoutX());
        str.setLayoutY(passwordStrengthBar.getLayoutY()-25);

        indicator = new ProgressIndicator(0);
        indicator.setLayoutX(LAYOUT_x + 220);
        indicator.setLayoutY(400);
        indicator.setPrefSize(30, 30);

    }
    public void updatePasswordStrength(String password) {
        int passwordStrength = caclStrenght(password);
         strengthPercentage = (double) passwordStrength / 100.0;
        passwordStrengthBar.setProgress(strengthPercentage);
        indicator.setProgress(strengthPercentage);
        setStr();
    }

    private int caclStrenght(String password) {
        int strength = 0;
        int length = password.length();
        boolean hasNumbers = password.matches(".*\\d+.*");
        boolean hasSpecialChars = !password.matches("[A-Za-z0-9 ]*");

        strength += length * 4;
        if (hasNumbers) {
            strength += 10;
        }
        if (hasSpecialChars) {
            strength += 10;
        }

        return Math.min(strength, 100);
    }

    public void setUserPublisher(UserPublisher userPublisher) {
        this.userPublisher = userPublisher;
    }

    public void setStr()
    {
        if (strengthPercentage<0.25)
        {
         passwordStrengthBar.setStyle("-fx-accent: red;");
             str.setText("Password strenght: " + "BAD");
        }else if (strengthPercentage<=0.5)
        {
            passwordStrengthBar.setStyle("-fx-accent: yellow;");
            str.setText("Password strenght:" + "OKAY");
        }else if (strengthPercentage<0.7)
        {
            passwordStrengthBar.setStyle("-fx-accent: green;");
            str.setText("Password strenght:" + "GOOD");
        }
    }

}
