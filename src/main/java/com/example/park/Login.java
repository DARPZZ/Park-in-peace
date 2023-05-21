package com.example.park;

import Model.DaoObject.User;
import Model.Implements.DaoUser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.security.PrivateKey;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class Login
{

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
        if (Objects.equals(PhoneNumber.getText(), "")) {
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
        if (Objects.equals(zipCode.getText(), "")) {
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
}