package View;

import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;

public class MainPage extends Header
{
    public MainPage()
    {
        TextField searchField = new TextField();
        searchField.setPromptText("\uD83D\uDD0E Søg");
        searchField.setPrefSize((this.GAP * 3) - 15, this.HEIGHT);
        searchField.setLayoutX(60);
        searchField.setLayoutY(this.getYMargin() + 30);

        RadioButton toiletBtn = new RadioButton("Toilet");
        toiletBtn.setLayoutX(searchField.getLayoutX() + searchField.getPrefWidth() + 20);
        toiletBtn.setLayoutY(searchField.getLayoutY() + 5);

        RadioButton electricityBtn = new RadioButton("Strøm");
        electricityBtn.setLayoutX(toiletBtn.getLayoutX() + 100);
        electricityBtn.setLayoutY(toiletBtn.getLayoutY());

        RadioButton waterBtn = new RadioButton("Vand");
        waterBtn.setLayoutX(electricityBtn.getLayoutX() + 100);
        waterBtn.setLayoutY(electricityBtn.getLayoutY());

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(this.X_MARGIN);
        AnchorPane.setTopAnchor(scrollPane, searchField.getLayoutY() + searchField.getPrefHeight() + 20);
        scrollPane.prefHeightProperty().bind(this.SCENE.heightProperty().subtract(this.getYMargin() + 100));
        scrollPane.prefWidthProperty().bind(this.SCENE.widthProperty().subtract(this.X_MARGIN * 2));
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(false);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:transparent;");

        TilePane tilePane = new TilePane(10, 10);
        tilePane.setPadding(new Insets(10,0,10,0));
        tilePane.setHgap(25);
        tilePane.setVgap(25);
        tilePane.setPrefRows(5);
        tilePane.prefHeightProperty().bind(scrollPane.prefHeightProperty());
        tilePane.prefWidthProperty().bind(scrollPane.prefWidthProperty());
        scrollPane.setContent(tilePane);

        tilePane.getChildren().add(new Thumbnail(new Image("C:\\tmp\\cat2.jpg"), "test"));
        tilePane.getChildren().add(new Thumbnail(new Image("C:\\tmp\\cat2.jpg"), "test"));
        tilePane.getChildren().add(new Thumbnail(new Image("C:\\tmp\\cat2.jpg"), "test"));
        tilePane.getChildren().add(new Thumbnail(new Image("C:\\tmp\\cat2.jpg"), "test"));
        tilePane.getChildren().add(new Thumbnail(new Image("C:\\tmp\\cat2.jpg"), "test"));

        this.ANCHOR_PANE.getChildren().addAll(scrollPane, searchField, toiletBtn, electricityBtn, waterBtn);
    }

    private void fillAds()
    {

    }

    private void sortAds()
    {

    }

    private void filterAds()
    {

    }
}