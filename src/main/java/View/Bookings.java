package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Bookings extends Header
{
TextField textField = new TextField();
public Bookings()
{
    setScene();
}
public void setScene()
{
    ANCHOR_PANE.getChildren().addAll(textField);

}
}
