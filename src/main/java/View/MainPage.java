package View;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class MainPage extends Header
{
    public MainPage()
    {
        ScrollPane sp = new ScrollPane();
        sp.setLayoutX(this.X_MARGIN);
        sp.prefHeightProperty().bind(this.SCENE.heightProperty().subtract(this.getYMargin() + 50));
        sp.prefWidthProperty().bind(this.SCENE.widthProperty().subtract(this.X_MARGIN*2));
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setPannable(true);
        //scrollPane.setStyle("-fx-background-color:transparent;");

        GridPane gp = new GridPane();
        gp.prefHeightProperty().bind(sp.prefHeightProperty());
        gp.prefWidthProperty().bind(sp.prefWidthProperty());
        gp.setGridLinesVisible(true); // debug

        sp.setContent(gp);

        AnchorPane.setTopAnchor(sp, this.getYMargin());
        this.AP.getChildren().add(sp);
    }
}
