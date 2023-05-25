package View;

import Model.DaoObject.Combine;
import Model.Implements.DaoCombine;
import Service.CombinePublisher;
import com.example.park.HelloApplication;
import com.example.park.SceneName;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends Header
{
    private List<Combine> advertisementList;
    private TextField searchField;
    private Button filterBtn;
    private ScrollPane scrollPane;
    private TilePane tilePane;

    private Pane popUpBackground;
    private AnchorPane popUpContent;

    public MainPage()
    {
        setupFilterControlsLayout();
        setupScrollPaneLayout();
        fillAds(tilePane);
        setupPopUpBackground();
    }

    private void setupFilterControlsLayout()
    {
        searchField = new TextField();
        searchField.setPromptText("\uD83D\uDD0E Søg");
        searchField.setPrefSize((GAP * 3) - 15, HEIGHT);
        searchField.setLayoutX(60);
        searchField.setLayoutY(this.getYMargin() + 30);

        filterBtn = new Button("Filter");
        filterBtn.setPrefSize(40, HEIGHT);
        filterBtn.setLayoutX(searchField.getLayoutX() + searchField.getPrefWidth() + 20);
        filterBtn.setLayoutY(searchField.getLayoutY());
        filterBtn.setOnAction(event -> showPopUp());

        this.ANCHOR_PANE.getChildren().addAll(searchField, filterBtn);
    }

    private void setupScrollPaneLayout()
    {
        scrollPane = new ScrollPane();
        scrollPane.setLayoutX(X_MARGIN);
        AnchorPane.setTopAnchor(scrollPane, searchField.getLayoutY() + searchField.getPrefHeight() + 20);
        scrollPane.prefHeightProperty().bind(this.SCENE.heightProperty().subtract(this.getYMargin() + 100));
        scrollPane.prefWidthProperty().bind(this.SCENE.widthProperty().subtract(X_MARGIN * 2));
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(false);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:transparent;");

        tilePane = new TilePane(10, 10);
        tilePane.setPadding(new Insets(10,0,10,0));
        tilePane.setHgap(25);
        tilePane.setVgap(25);
        tilePane.setPrefRows(5);
        tilePane.prefHeightProperty().bind(scrollPane.prefHeightProperty());
        tilePane.prefWidthProperty().bind(scrollPane.prefWidthProperty());
        scrollPane.setContent(tilePane);
        this.ANCHOR_PANE.getChildren().add(scrollPane);
    }

    private void fillAds(TilePane tp)
    {
        advertisementList = new ArrayList<>(new DaoCombine().GetAll());
        CombinePublisher publish = CombinePublisher.getInstance();

        for (Combine ad : advertisementList)
        {
            if(ad != null)
            {
                try
                {
                    Thumbnail thumbnail = new Thumbnail(new Image(ad.getImage()), ad.getLocation());
                    thumbnail.setOnMouseReleased(event ->
                    {
                        publish.publish(SceneName.Main, ad);
                        HelloApplication.changeScene(SceneName.Advertisement);
                    });
                    tp.getChildren().add(thumbnail);
                }
                catch (Exception e)
                {
                    System.err.println("An error occured " + e.getMessage());
                }
            }
        }
    }

    private void filterAds()
    {

    }

    private void setupPopUpBackground()
    {
        popUpBackground = new Pane();
        popUpBackground.prefHeightProperty().bind(SCENE.heightProperty());
        popUpBackground.prefWidthProperty().bind(SCENE.widthProperty());
        popUpBackground.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        popUpBackground.setVisible(false);
        popUpBackground.setOnMouseClicked(event -> hidePopUp());
        this.ANCHOR_PANE.getChildren().add(popUpBackground);

        popUpContent = new AnchorPane();
        popUpContent.setStyle("-fx-background-color: white; -fx-padding: 20;");
        popUpContent.setVisible(false);
        popUpContent.setPrefSize(SCENE.getWidth() / 2.5,SCENE.getHeight() / 1.2);
        popUpContent.setLayoutX(SCENE.getWidth()/2 - (popUpContent.getPrefWidth()/2));
        popUpContent.setLayoutY(SCENE.getHeight()/2 - (popUpContent.getPrefHeight()/2));

        GridPane gp = new GridPane();
        gp.setPrefSize(popUpContent.getPrefWidth(), popUpContent.getPrefHeight());
        gp.setGridLinesVisible(true);
        popUpContent.getChildren().add(gp);

        Label priceRangeLabel = new Label("Prisramme");
        gp.add(priceRangeLabel, 0, 0, 2, 1);

        TextField minPriceTf = new TextField();
        minPriceTf.setPromptText("Minimumspris");
        gp.add(minPriceTf, 0, 1);

        TextField maxPriceTf = new TextField();
        maxPriceTf.setPromptText("Maksimumspris");
        gp.add(maxPriceTf, 1, 1);

        Label service = new Label("Faciliteter");
        gp.add(service, 0, 4, 2, 1);

        RadioButton toiletBtn = new RadioButton("Toilet");
        gp.add(toiletBtn, 0, 5);

        RadioButton electricityBtn = new RadioButton("Strøm");
        gp.add(electricityBtn, 1, 5);

        RadioButton waterBtn = new RadioButton("Vand");
        gp.add(waterBtn, 0, 6);

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