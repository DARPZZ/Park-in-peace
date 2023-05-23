package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import java.sql.*;
import static Model.Implements.Connection.*;

public class ProfilePage extends Header{
    private static final int NUM_COLS = 2;
       public ProfilePage(){
            

            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(10));
            gridPane.setVgap(10);
            gridPane.setHgap(10);

            // Create the text fields
            TextField nameField = new TextField();
            TextField addressField = new TextField();
            TextField phoneField = new TextField();
            TextField emailField = new TextField();
            TextField companyField = new TextField();
            TextField bankField = new TextField();
            TextField accountField = new TextField();

            // Add the text fields to the GridPane
            gridPane.addRow(0, createLabel("Name"), nameField);
            gridPane.addRow(1, createLabel("Address"), addressField);
            gridPane.addRow(2, createLabel("Phone no."), phoneField);
            gridPane.addRow(3, createLabel("Email"), emailField);
            gridPane.addRow(4, createLabel("Company name"), companyField);
            gridPane.addRow(5, createLabel("Bank name"), bankField);
            gridPane.addRow(6, createLabel("Account no."), accountField);

            // Configure the column constraints
            for (int i = 0; i < NUM_COLS; i++) {
                ColumnConstraints columnConstraints = new ColumnConstraints();
                columnConstraints.setHgrow(Priority.ALWAYS);
                gridPane.getColumnConstraints().add(columnConstraints);
            }

            Button saveButton = new Button("Save");

            gridPane.add(saveButton, 0, 7, NUM_COLS, 1);


    }

    private TextField createLabel(String labelText) {
        TextField label = new TextField(labelText);
        label.setDisable(true);
        label.setStyle("-fx-opacity: 1;");
        return label;
    }
/*
    private void fetchUserInfoFromDatabase (
            TextField nameField, TextField addressField, TextField phoneField,
            TextField emailField, TextField companyField, TextField bankField,
            TextField accountField) {

        try {
            // Create a database connection
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:" + Port + ";databaseName=" + databaseName, userName, password);

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute the query to fetch user information
            ResultSet resultSet = statement.executeQuery("SELECT * FROM userinfo");

            // Check if there are any rows returned
            if (resultSet.next()) {
                // Retrieve the values from the result set
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String company = resultSet.getString("company");
                String bank = resultSet.getString("bank");
                String account = resultSet.getString("account");

                // Update the text fields with the retrieved values
                nameField.setText(name);
                addressField.setText(address);
                phoneField.setText(phone);
                emailField.setText(email);
                companyField.setText(company);
                bankField.setText(bank);
                accountField.setText(account);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

}
