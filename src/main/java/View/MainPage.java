package View;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;

public class MainPage extends Header
{
    public MainPage()
    {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(this.X_MARGIN);
        scrollPane.prefHeightProperty().bind(this.SCENE.heightProperty().subtract(this.getYMargin() + 50));
        scrollPane.prefWidthProperty().bind(this.SCENE.widthProperty().subtract(this.X_MARGIN*2));
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(false);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:transparent;");

        TilePane tilePane = new TilePane(10, 10);
        tilePane.setPadding(new Insets(10,0,10,0));
        tilePane.setHgap(25);
        tilePane.setVgap(25);
        tilePane.setPrefRows(4);
        tilePane.prefHeightProperty().bind(scrollPane.prefHeightProperty());
        tilePane.prefWidthProperty().bind(scrollPane.prefWidthProperty());
        scrollPane.setContent(tilePane);

        //tilePane.getChildren().add(new Thumbnail(new Image("C:\\tmp\\cat2.jpg"), "test"));
        //tilePane.getChildren().add(new Thumbnail(new Image("C:\\tmp\\cat2.jpg"), "test"));
        //tilePane.getChildren().add(new Thumbnail(new Image("C:\\tmp\\cat2.jpg"), "test"));
        //tilePane.getChildren().add(new Thumbnail(new Image("C:\\tmp\\cat2.jpg"), "test"));
        AnchorPane.setTopAnchor(scrollPane, this.getYMargin());
        this.ANCHOR_PANE.getChildren().add(scrollPane);
    }
}
