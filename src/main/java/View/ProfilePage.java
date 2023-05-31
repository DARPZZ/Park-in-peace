package View;

import Model.DaoObject.User;
import Model.DatabaseWorker.BlackList;
import com.example.park.UserPublisher;
import com.example.park.UserSubscriber;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.sql.*;
import java.text.BreakIterator;
import java.util.ArrayList;

import static Model.Implements.Connection.*;

public class ProfilePage extends Header implements UserSubscriber {

    private ArrayList<TextField> textFields = new ArrayList<>();
    private User loggedIn;
    Pane popUpBackground = new Pane();
    AnchorPane popUpContent = new AnchorPane();

    public void onUserReceived(User user){

        loggedIn = user;
    }

    private static final int NUM_COLS = 2;
       public ProfilePage(){

            //Image pp = new Image("C:\\Users\\jakob\\Desktop\\Dokumenter\\lola-color.png");

            ImageView profilePic = new ImageView();
            //profilePic.setImage(pp);
            profilePic.setX(100);
            profilePic.setY(200);
            profilePic.prefHeight(300);
            profilePic.prefWidth(300);


            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(10));
            gridPane.setVgap(10);
            gridPane.setHgap(10);
            gridPane.setLayoutX(500);
            gridPane.setLayoutY(200);


            // Create the text fields
            TextField nameField = new TextField();
            TextField addressField = new TextField();
            TextField phoneField = new TextField();
            TextField emailField = new TextField();
            TextField companyField = new TextField();
            TextField bankField = new TextField();
            TextField accountField = new TextField();
            textFields.add(0, nameField);
            textFields.add(1, addressField);
            textFields.add(2, phoneField);
            textFields.add(3, emailField);
            textFields.add(4, accountField);


            // Add the text fields to the GridPane
            gridPane.addRow(0, createLabel("Navn"), nameField);
            gridPane.addRow(1, createLabel("Adresse"), addressField);
            gridPane.addRow(2, createLabel("Telefon"), phoneField);
            gridPane.addRow(3, createLabel("Email"), emailField);
            gridPane.addRow(4, createLabel("Virksomhed"), companyField);
            gridPane.addRow(5, createLabel("Bank"), bankField);
            gridPane.addRow(6, createLabel("Konto nr."), accountField);

            // Configure the column constraints
            for (int i = 0; i < NUM_COLS; i++) {
                ColumnConstraints columnConstraints = new ColumnConstraints();
                columnConstraints.setHgrow(Priority.ALWAYS);
                gridPane.getColumnConstraints().add(columnConstraints);
            }

            Button saveButton = new Button("Save");
            saveButton.setOnMouseClicked(event -> {
                    loggedIn.setName(nameField.getText());
                BlackList.getSingleton().UpdateUser(loggedIn,"fldName",loggedIn.getName());
                    loggedIn.setAddress(addressField.getText());
                BlackList.getSingleton().UpdateUser(loggedIn,"fldAddress",loggedIn.getAddress());
                    loggedIn.setPhoneNumber(phoneField.getText());
                BlackList.getSingleton().UpdateUser(loggedIn,"fldPhoneNumber",loggedIn.getPhoneNumber());
                    loggedIn.setEmail(emailField.getText());
                BlackList.getSingleton().UpdateUser(loggedIn,"fldEmail",loggedIn.getEmail());
                    loggedIn.setAcounterNumber(Integer.parseInt(accountField.getText()));
                BlackList.getSingleton().UpdateUser(loggedIn,"fldAcountNumber",String.valueOf(loggedIn.getAcounterNumber()));
                showPopUp();
            });


            gridPane.add(saveButton, 0, 7, NUM_COLS, 1);

            this.ANCHOR_PANE.getChildren().addAll(gridPane,profilePic);

           setupPopUpBackground();
    }

    private TextField createLabel(String labelText)
    {
        TextField label = new TextField(labelText);
        label.setDisable(true);
        label.setStyle("-fx-opacity: 1;");
        return label;
    }

    public void broadcast(){
        textFields.get(0).setText(loggedIn.getName());
        textFields.get(1).setText(loggedIn.getAddress());
        textFields.get(2).setText(loggedIn.getPhoneNumber());
        textFields.get(3).setText(loggedIn.getEmail());
        if (loggedIn.getAcounterNumber() >=0)
        {textFields.get(4).setText(String.valueOf(loggedIn.getAcounterNumber()));}
    }

    private void setupPopUpBackground()
    {
        // Dark background which hides the popUp when clicked
        popUpBackground = new Pane();
        popUpBackground.setLayoutX(768);
        popUpBackground.setLayoutY(1080);
        popUpBackground.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        popUpBackground.setVisible(false);
        popUpBackground.setOnMouseClicked(event -> hidePopUp());
        this.ANCHOR_PANE.getChildren().add(popUpBackground);

        // PopUp which contains filter controls
        popUpContent = new AnchorPane();
        popUpContent.setStyle("-fx-background-color: white; -fx-padding: 20;");
        popUpContent.setVisible(false);
        //popUpContent.setPrefSize(150,100);
        popUpContent.setLayoutX(525);
        popUpContent.setLayoutY(50);
        //popUpContent.setLayoutX(525);
        //popUpContent.setLayoutY(150);

        // GridPane to act as layout manager for the filter controls
        GridPane contentGridPane = new GridPane();
        contentGridPane.setPrefSize(popUpContent.getPrefWidth(), popUpContent.getPrefHeight());
        contentGridPane.setAlignment(Pos.CENTER);
        contentGridPane.setLayoutX(25);
        contentGridPane.setLayoutY(150);
        contentGridPane.setHgap(10);
        contentGridPane.setVgap(10);
        Label confirmationLabel = new Label("Opdatering Fuldført");
        Button ok = new Button("OK");
        ok.setOnMouseClicked(event -> {hidePopUp();});
        contentGridPane.add(confirmationLabel,0,0);
        contentGridPane.add(ok,0,1);

        popUpContent.getChildren().add(contentGridPane);
        this.ANCHOR_PANE.getChildren().add(popUpContent);
    }
    private void showPopUp()
    {
        popUpBackground.setVisible(true);
        popUpContent.setVisible(true);
    }

    private void hidePopUp()
    {
        popUpBackground.setVisible(false);
        popUpContent.setVisible(false);
    }


}
