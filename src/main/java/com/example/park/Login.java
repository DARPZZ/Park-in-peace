package com.example.park;
import Model.DaoObject.User;
import Model.DatabaseWorker.BlackList;
import Model.DatabaseWorker.PlotList;
import Model.DatabaseWorker.ReservationList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class Login implements UserPublisher
{
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
                insertInformation();
                toggleButton.setText("Login");
                anchorPane.getChildren().clear();
                toggleLabel.setText("Press here to create a new user:");
                loginScene(anchorPane,loginButton);
                loginButton.setText("login");
                anchorPane.getChildren().addAll(loginButton, toggleButton, toggleLabel);
            }
        });
        anchorPane.getChildren().addAll(name, PhoneNumber, password, adress, email, zipCode);
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
        Tooltip tooltip = new Tooltip();
        tooltip.setText("Invalid");
        boolean Error = false;
        tooltip.setShowDelay(Duration.ZERO);
        if (Objects.equals(name.getText(), "")) {
            name.setTooltip(tooltip);
            Error = true;
        }
        boolean isNumeric = PhoneNumber.getText().chars().allMatch( Character::isDigit );
        if (Objects.equals(PhoneNumber.getText(), "")|| !isNumeric) {
            PhoneNumber.setTooltip(tooltip);
            Error = true;
        }
        if (Objects.equals(password.getText(), "")) {
            password.setTooltip(tooltip);
            Error = true;
        }
        if (Objects.equals(adress.getText(), "")) {
            adress.setTooltip(tooltip);
            Error = true;
        }
        if (Objects.equals(email.getText(), "")) {
            email.setTooltip(tooltip);
            Error = true;
        }
        isNumeric = zipCode.getText().chars().allMatch( Character::isDigit );
        if (Objects.equals(zipCode.getText(), "")|| !isNumeric) {
            zipCode.setTooltip(tooltip);
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
                String kodeord = password.getText();
                String username = name.getText();
                setLoginName(username);
                System.out.println("before checklogin done"+System.currentTimeMillis());
                user = BlackList.getSingleton().checkLogin(username,kodeord);  // wrong password
                System.out.println("checklogin done"+System.currentTimeMillis());
                PlotList.getSingleton().setList();
                System.out.println("set plotlist done"+System.currentTimeMillis());
                ReservationList.getSingleton().setList();
                System.out.println("meme");
                //BlackList.getSingleton().setBlackList(user);
                //Model.Implements.DaoUser daoUser = new DaoUser();
                //region update getuser method - userLoginCheck storedprocedure er lavet
                userPublisher.notifySubscribers(user);

                HelloApplication.plotPage.initPlotPage();// stuff jeg helst vill kører i contructoren
                HelloApplication.plotPage.createPopUpUI();//
                HelloApplication.plotPage.preparePlotGrid();//

                HelloApplication.changeScene(SceneName.Main);
                System.out.println("Login successful!");

                /*
                List<User> userList = daoUser.GetAll();

                boolean validCredentials = false;
                for (User userIterate : userList) {
                    if (userIterate.getName().equals(username) && userIterate.getPassword().equals(kodeord)) {
                        userPublisher.notifySubscribers(userIterate);
                        user = userIterate;
                        validCredentials = true;
                        break;
                    }
                }
                //region end
                if (validCredentials) {
                    HelloApplication.changeScene(SceneName.Main);
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Login Failed");
                }
                ReservationList.getSingleton().setList();
                PlotList.getSingleton().setList();
                BlackList.getSingleton().setBlackList(user);

                 */
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


    public void setUserPublisher(UserPublisher userPublisher) {
        this.userPublisher = userPublisher;
    }
}
