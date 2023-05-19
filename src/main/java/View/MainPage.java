package View;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class MainPage extends Header
{
    public MainPage()
    {
        ScrollPane sp = new ScrollPane();
        sp.setLayoutX(this.getxMargin());
        sp.prefHeightProperty().bind(this.getScene().heightProperty().subtract(this.getLIMIT() + 50));
        sp.prefWidthProperty().bind(this.getScene().widthProperty().subtract(this.getxMargin()*2));
        //scrollPane.setStyle("-fx-background-color:transparent;");

        GridPane gp = new GridPane();
        gp.prefHeightProperty().bind(sp.prefHeightProperty());
        gp.prefWidthProperty().bind(sp.prefWidthProperty());
        gp.setGridLinesVisible(true); // debug

        AnchorPane.setTopAnchor(sp, this.getLIMIT());
        this.getAp().getChildren().add(sp);
    }
}
