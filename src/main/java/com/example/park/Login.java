package com.example.park;
import Controller.LoginController;
import Controller.PlotController;
import Model.DaoObject.User;
import Model.DatabaseWorker.BlackList;
import Model.DatabaseWorker.ReservationList;
import Service.UserPublisher;
import Service.UserSubscriber;
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
    Tooltip tooltip = new Tooltip();
    LoginController loginController = new LoginController();
    Label str = new Label("Adgangskodestyrke");
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

    /**
     * Createsa new user
     * @param anchorPane
     * @param loginButton
     * @param toggleButton
     * @param toggleLabel
     */
    public void createUser(AnchorPane anchorPane, Button loginButton, ToggleButton toggleButton, Label toggleLabel)
    {
        name.setPromptText("Indtast name");
        name.setLayoutX(LAYOUT_x);
        name.setLayoutY(100);
        PhoneNumber.setPromptText("Indtast mobil nummer");
        PhoneNumber.setLayoutX(LAYOUT_x);
        PhoneNumber.setLayoutY(150);
        password.setPromptText("Indtast adgangskode");
        password.setLayoutX(LAYOUT_x);
        password.setLayoutY(200);
        adress.setPromptText("Indtast adresse");
        adress.setLayoutX(LAYOUT_x);
        adress.setLayoutY(250);
        email.setPromptText("Indtast email");
        email.setLayoutX(LAYOUT_x);
        email.setLayoutY(300);
        zipCode.setPromptText("Indtast postnummer");
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
         passwordStrength();
        password.textProperty().addListener((observable, oldValue, newValue) -> updatePasswordStrength(newValue));


        anchorPane.getChildren().addAll(name, PhoneNumber, password, adress, email, zipCode,passwordStrengthBar,str);
    }

    /**
     * If  ValiedateUser goes right we create a new user, else we do not create a new user
     */
    public void insertInformation()
    {
            if (validateUser()) {
                 user = new User(name.getText(), PhoneNumber.getText(), password.getText(), adress.getText(), email.getText(), Integer.parseInt(zipCode.getText()));
                BlackList.getSingleton().CreateUser(user);
            }
    }

    /**
     * Validates the user input for creating a new user
     * @return Returns either true or false. depending on if the information is correct or not
     */
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

    /**
     *   If the user enters the correct password and username they will get login
     * @param anchorPane the anchorpane for the scene
     * @param logIn button for login
     */
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
                tooltip.setText("Ugyldig");
                String kodeord = password.getText();
                String username = name.getText();
                setLoginName(username);
                user = BlackList.getSingleton().checkLogin(username,kodeord);
                if (user == null || !user.getName().equals(username) || !user.getPassword().equals(kodeord))
                {
                    failLogin();
                }else
                {
                    startRest();
                }
            }
        });
        anchorPane.getChildren().addAll(name,password);
    }

    /**
     * It will notify all the subcribers that a new user has made a login
     * Then we initialise the rest of the program
     */
    public void startRest()
    {
        PlotController pc = new PlotController();
        subscribe(pc);
        userPublisher.notifySubscribers(user);
        pc.initPlotPage();
        HelloApplication.plotPage.initPlotController(pc);
        HelloApplication.plotPage.createPopUpCreatePlot();
        HelloApplication.plotPage.preparePlotGrid();
        ReservationList.getSingleton().setList();
        BlackList.getSingleton().setBlackList(user);
        HelloApplication.changeScene(SceneName.Main);
    }

    /**
     * Adds feedback if the username or password is wrong
     */
    public void failLogin()
    {
        name.getStyleClass().add("warning-badge");
        password.getStyleClass().add("warning-badge");
        name.setTooltip(tooltip);
        password.setTooltip(tooltip);
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

    /**
     *  creates the password strength bar
     */
    public void passwordStrength()
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

    /**
     * Updates the progressbar for the password
     * @param password the password the user enters
     */
    public void updatePasswordStrength(String password) {
       loginController.updatePasswordStrength(password,passwordStrengthBar,indicator);
        setStr();
    }


    public void setUserPublisher(UserPublisher userPublisher) {
        this.userPublisher = userPublisher;
    }

    /**
     * Sets the text and colour depending on how strong the password is
     */
    public void setStr()
    {
        if (loginController.getStrengthPercentage()<0.4)
        {
         passwordStrengthBar.setStyle("-fx-accent: red;");
             str.setText("Password strenght: " + "BAD");
        }else if (loginController.getStrengthPercentage()<=0.6)
        {
            passwordStrengthBar.setStyle("-fx-accent: yellow;");
            str.setText("Password strenght:" + "OKAY");
        }else if (loginController.getStrengthPercentage()<0.7)
        {
            passwordStrengthBar.setStyle("-fx-accent: green;");
            str.setText("Password strenght:" + "GOOD");
        }
    }

}
