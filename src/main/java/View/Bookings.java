package View;

import com.example.park.Login;
import javafx.scene.control.TextField;
public class Bookings extends Header
{
    Login login = new Login();
TextField textField = new TextField();
public Bookings()
{
    setScene();
}
public void setScene()
{
   String name = login.getLoginName();

    ANCHOR_PANE.getChildren().addAll(textField);

}
}
