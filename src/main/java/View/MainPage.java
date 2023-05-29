package View;

import Model.DaoObject.Combine;
import Model.Implements.DaoCombine;
import Service.CombinePublisher;
import com.example.park.HelloApplication;
import com.example.park.SceneName;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainPage extends Header
{
    private final List<Combine> advertisementList;
    private List<Combine> filteredList;
    private TextField searchField;
    private TilePane tilePane;
    private Pane popUpBackground;
    private AnchorPane popUpContent;
    private Button filterBtn;

    private boolean toiletFilter;
    private boolean electricityFilter;
    private boolean waterFilter;
    private boolean wifiFilter;

    public MainPage()
    {
        setupFilterControlsLayout();
        setupScrollPaneLayout();
        advertisementList = new DaoCombine().GetAll();
        fillAds(advertisementList);
        setupPopUpBackground();
    }

    private void setupFilterControlsLayout()
    {
        searchField = new TextField();
        searchField.setPromptText("\uD83D\uDD0E Søg");
        searchField.setPrefSize((GAP * 3) - 15, HEIGHT);
        searchField.setLayoutX(60);
        searchField.setLayoutY(this.getYMargin() + 30);

        Button filterBtn = new Button("▼");
        filterBtn.setPrefSize(40, HEIGHT);
        filterBtn.setLayoutX(searchField.getLayoutX() + searchField.getPrefWidth() + 20);
        filterBtn.setLayoutY(searchField.getLayoutY());
        filterBtn.setOnAction(event -> showPopUp());

        this.ANCHOR_PANE.getChildren().addAll(searchField, filterBtn);
    }

    private void setupScrollPaneLayout()
    {
        // Scrollable pane to hold the thumbnails
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(X_MARGIN);
        AnchorPane.setTopAnchor(scrollPane, searchField.getLayoutY() + searchField.getPrefHeight() + 20);
        scrollPane.prefHeightProperty().bind(this.SCENE.heightProperty().subtract(this.getYMargin() + 100));
        scrollPane.prefWidthProperty().bind(this.SCENE.widthProperty().subtract(X_MARGIN * 2));
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(false);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:transparent;");

        // TilePane to act as thumbnails
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

    private void setupPopUpBackground()
    {
        // Dark background which hides the popUp when clicked
        popUpBackground = new Pane();
        popUpBackground.prefHeightProperty().bind(SCENE.heightProperty());
        popUpBackground.prefWidthProperty().bind(SCENE.widthProperty());
        popUpBackground.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        popUpBackground.setVisible(false);
        popUpBackground.setOnMouseClicked(event -> hidePopUp());
        this.ANCHOR_PANE.getChildren().add(popUpBackground);

        // PopUp which contains filter controls
        popUpContent = new AnchorPane();
        popUpContent.setStyle("-fx-background-color: white; -fx-padding: 20;");
        popUpContent.setVisible(false);
        popUpContent.setPrefSize(SCENE.getWidth() / 2.5,SCENE.getHeight() / 1.2);
        popUpContent.setLayoutX(SCENE.getWidth()/2 - (popUpContent.getPrefWidth()/2));
        popUpContent.setLayoutY(SCENE.getHeight()/2 - (popUpContent.getPrefHeight()/2));

        // GridPane to act as layout manager for the filter controls
        GridPane gp = new GridPane();
        gp.setPrefSize(popUpContent.getPrefWidth(), popUpContent.getPrefHeight());
        gp.setAlignment(Pos.CENTER);
        gp.setLayoutX(25);
        gp.setLayoutY(-150);
        gp.setHgap(10);
        gp.setVgap(10);
        fillPopUpContent(gp);
        popUpContent.getChildren().add(gp);

        this.ANCHOR_PANE.getChildren().add(popUpContent);
    }

    private void fillPopUpContent(GridPane gp)
    {
        filterBtn = new Button("Filtrer");
        filterBtn.setPrefWidth(100);
        AnchorPane.setBottomAnchor(filterBtn, 10.0);
        AnchorPane.setRightAnchor(filterBtn, 10.0);
        popUpContent.getChildren().add(filterBtn);
        filterBtn.setOnAction(event ->
        {
            fillAds(filteredList);
            hidePopUp();
        });

        Label priceRangeLabel = new Label("Prisramme");
        gp.add(priceRangeLabel, 0, 0, 2, 1);

        TextField minPriceTf = new TextField();
        minPriceTf.setPromptText("Minimumspris");
        gp.add(minPriceTf, 0, 1);

        TextField maxPriceTf = new TextField();
        maxPriceTf.setPromptText("Maksimumspris");
        gp.add(maxPriceTf, 2, 1);

        Label binderLabel = new Label("-");
        gp.add(binderLabel, 1, 1);

        gp.add(new Separator(), 0, 2, 3, 1);

        Label service = new Label("Faciliteter");
        gp.add(service, 0, 3, 2, 1);

        CheckBox wifiCb = new CheckBox("Trådløst Internet");
        gp.add(wifiCb, 0, 4);
        wifiCb.setOnAction(event -> filterWifi(wifiCb.isSelected()));

        CheckBox toiletCb = new CheckBox("Toilet");
        gp.add(toiletCb, 2, 4);
        toiletCb.setOnAction(event -> filterToilet(toiletCb.isSelected()));

        CheckBox electricityCb = new CheckBox("Strøm");
        gp.add(electricityCb, 0, 5);
        electricityCb.setOnAction(event -> filterEl(electricityCb.isSelected()));

        CheckBox waterCb = new CheckBox("Vand");
        gp.add(waterCb, 2, 5);
        waterCb.setOnAction(event -> filterWater(waterCb.isSelected()));

        Button clearFilterBtn = new Button("Ryd alle");
        clearFilterBtn.setPrefWidth(100);
        AnchorPane.setBottomAnchor(clearFilterBtn, 10.0);
        AnchorPane.setLeftAnchor(clearFilterBtn, 10.0);
        popUpContent.getChildren().add(clearFilterBtn);
        clearFilterBtn.setOnAction(event ->
        {
            resetFilter();
            toiletCb.setSelected(false);
            electricityCb.setSelected(false);
            waterCb.setSelected(false);
            filterBtn.setText("Filter");
            maxPriceTf.clear();
            minPriceTf.clear();
            fillAds(advertisementList);
        });

        gp.add(new Separator(), 0, 6, 3, 1);
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

    private void fillAds(List<Combine> list)
    {
        tilePane.getChildren().clear();
        CombinePublisher publish = CombinePublisher.getInstance();
        for (Combine ad : list)
        {
            if(ad != null)
            {
                try
                {
                    Thumbnail thumbnail = new Thumbnail(new Image(ad.getImage()),
                            ad.getLocation() + ", " + ad.getZipCode(),
                            String.format("%.0f kr", ad.getMidSeasonPrice()));
                    thumbnail.setOnMouseReleased(event ->
                    {
                        publish.publish(SceneName.Main, ad);
                        fillAds(advertisementList);
                        HelloApplication.changeScene(SceneName.Advertisement);
                    });
                    tilePane.getChildren().add(thumbnail);
                }
                catch (Exception e)
                {
                    System.err.println("An error occured " + e.getMessage());
                }
            }
        }
    }


    private void sortAds()
    {

    }

    private void filterToilet(boolean isCheckboxSelected)
    {
        toiletFilter = isCheckboxSelected;
        applyFilters();
        updateFilterButton(filterBtn);
    }

    private void filterWater(boolean isCheckboxSelected)
    {
        waterFilter = isCheckboxSelected;
        applyFilters();
        updateFilterButton(filterBtn);
    }

    private void filterEl(boolean isCheckboxSelected)
    {
        electricityFilter = isCheckboxSelected;
        applyFilters();
        updateFilterButton(filterBtn);
    }

    private void filterWifi(boolean isCheckboxSelected)
    {
        wifiFilter = isCheckboxSelected;
        applyFilters();
        updateFilterButton(filterBtn);
    }

    private void applyFilters()
    {
        Predicate<Combine> filterCondition = data ->
                (!toiletFilter || data.isToilet()) &&
                        (!electricityFilter || data.isEl()) &&
                        (!waterFilter || data.isWater());

        filteredList = advertisementList.stream()
                .filter(filterCondition)
                .collect(Collectors.toList());
    }

    private void updateFilterButton(Button filterBtn)
    {
        int numMatches = filteredList.size();
        filterBtn.setText(String.format("%d match%s", numMatches, numMatches != 1 ? "es" : ""));
    }

    private void resetFilter()
    {
        filteredList = new ArrayList<>(advertisementList);
    }
}