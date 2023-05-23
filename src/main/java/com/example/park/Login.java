package com.example.park;
import Model.DaoObject.User;
import Model.Implements.DaoUser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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

    private String loginName = "";
    TextField name = new TextField();
    TextField PhoneNumber = new TextField();
    TextField password = new TextField();
    TextField adress = new TextField();
    TextField email = new TextField();
    TextField zipCode = new TextField();
    private final int LAYOUT_x = 600;

    public void createUser(AnchorPane anchorPane, Button loginButton)
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
                setLoginName(name.getText());
                insertInformation();
            }
        });
        anchorPane.getChildren().addAll(name, PhoneNumber, password, adress, email, zipCode);
    }

    public void insertInformation()
    {
        Model.Implements.DaoUser daoUser = new DaoUser();
            if (validateUser()) {
                User user = new User(name.getText(), PhoneNumber.getText(), password.getText(), adress.getText(), 0, email.getText(), Integer.parseInt(zipCode.getText()), 1);
                daoUser.Create(user);
                userPublisher.notifySubscribers(user);
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
            HelloApplication.changeScene(SceneName.Main);
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
                Model.Implements.DaoUser daoUser = new DaoUser();
                List<User> userList = daoUser.GetAll();

                boolean validCredentials = false;
                for (User user : userList) {
                    if (user.getName().equals(username) && user.getPassword().equals(kodeord)) {
                        validCredentials = true;
                        break;
                    }
                }
                System.out.println(getLoginName());

                if (validCredentials) {
                    HelloApplication.changeScene(SceneName.Main);
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Login Failed");
                }
            }
        });
        anchorPane.getChildren().addAll(name,password);
    }
    public User createUserObject() {
        String nameValue = name.getText();
        String phoneNumberValue = PhoneNumber.getText();
        String passwordValue = password.getText();
        String addressValue = adress.getText();
        String emailValue = email.getText();
        String zipCodeValue = zipCode.getText();
        User user = new User(nameValue, phoneNumberValue, passwordValue, addressValue, 0, emailValue, Integer.parseInt(zipCodeValue), 1);

        return user;
    }

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
    private UserPublisher userPublisher;

    public void setUserPublisher(UserPublisher userPublisher) {
        this.userPublisher = userPublisher;
    }
}
