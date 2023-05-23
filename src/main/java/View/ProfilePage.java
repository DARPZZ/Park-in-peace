package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

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


}
