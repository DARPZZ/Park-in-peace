package View;

import Model.DaoObject.Plot;
import Model.DatabaseWorker.PlotList;
import Service.PlotPublisher;
import com.example.park.HelloApplication;
import com.example.park.SceneName;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainPage extends Header
{
    private final List<Plot> advertisementList;
    private List<Plot> filteredList;
    private TilePane tilePane;
    private Pane popUpBackground;
    private AnchorPane popUpContent;

    private Button filterButton;
    private TextField searchTextField;
    private TextField minPriceTextfield;
    private TextField maxPriceTextField;
    private CheckBox toiletCheckBox;
    private CheckBox waterCheckBox;
    private CheckBox electricityCheckBox;
    private CheckBox wifiCheckBox;

    private boolean toiletFilter;
    private boolean electricityFilter;
    private boolean waterFilter;
    private boolean wifiFilter;
    private IntegerProperty minPrice;
    private IntegerProperty maxPrice;

    public MainPage()
    {
        setupFilterControlsLayout();
        setupScrollPaneLayout();
        advertisementList = PlotList.getSingleton().getList();
        populateWithAds(advertisementList);
        setupPopUpBackground();
    }

    private void setupFilterControlsLayout()
    {
        searchTextField = new TextField();
        searchTextField.setPromptText("\uD83D\uDD0E Søg");
        searchTextField.setPrefSize((GAP * 3) - 15, HEIGHT);
        searchTextField.setLayoutX(60);
        searchTextField.setLayoutY(this.getYMargin() + 30);
        setupSearchFieldListener();

        Button filterSettingsButton = new Button("▼");
        filterSettingsButton.setPrefSize(40, HEIGHT);
        filterSettingsButton.setLayoutX(searchTextField.getLayoutX() + searchTextField.getPrefWidth() + 20);
        filterSettingsButton.setLayoutY(searchTextField.getLayoutY());
        filterSettingsButton.setOnAction(event -> showPopUp());
        this.anchorPane.getChildren().addAll(searchTextField, filterSettingsButton);
    }

    private void setupScrollPaneLayout()
    {
        // Scrollable pane to hold the thumbnails
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(X_MARGIN);
        AnchorPane.setTopAnchor(scrollPane, searchTextField.getLayoutY() + searchTextField.getPrefHeight() + 20);
        scrollPane.prefHeightProperty().bind(this.scene.heightProperty().subtract(this.getYMargin() + 100));
        scrollPane.prefWidthProperty().bind(this.scene.widthProperty().subtract(X_MARGIN * 2));
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

        this.anchorPane.getChildren().add(scrollPane);
    }

    private void setupPopUpBackground()
    {
        // Dark background which hides the popUp when clicked
        popUpBackground = new Pane();
        popUpBackground.prefHeightProperty().bind(scene.heightProperty());
        popUpBackground.prefWidthProperty().bind(scene.widthProperty());
        popUpBackground.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        popUpBackground.setVisible(false);
        popUpBackground.setOnMouseClicked(event -> hidePopUp());
        this.anchorPane.getChildren().add(popUpBackground);

        // PopUp which contains filter controls
        popUpContent = new AnchorPane();
        popUpContent.setStyle("-fx-background-color: white; -fx-padding: 20;");
        popUpContent.setVisible(false);
        popUpContent.setPrefSize(scene.getWidth() / 2.5, scene.getHeight() / 1.2);
        popUpContent.setLayoutX(scene.getWidth()/2 - (popUpContent.getPrefWidth()/2));
        popUpContent.setLayoutY(scene.getHeight()/2 - (popUpContent.getPrefHeight()/2));

        // GridPane to act as layout manager for the filter controls
        GridPane contentGridPane = new GridPane();
        contentGridPane.setPrefSize(popUpContent.getPrefWidth(), popUpContent.getPrefHeight());
        contentGridPane.setAlignment(Pos.CENTER);
        contentGridPane.setLayoutX(25);
        contentGridPane.setLayoutY(-150);
        contentGridPane.setHgap(10);
        contentGridPane.setVgap(10);
        fillPopUpContent(contentGridPane);
        popUpContent.getChildren().add(contentGridPane);
        this.anchorPane.getChildren().add(popUpContent);
    }

    private void fillPopUpContent(GridPane gridPane)
    {
        createFilterBtn();
        createPriceRangeSection(gridPane);
        createSeparator(gridPane,2);
        createServicesSection(gridPane);
        createClearFilterBtn();
        createSeparator(gridPane,6);
    }

    private void createFilterBtn()
    {
        filterButton = new Button("Filtrer");
        filterButton.setPrefWidth(100);
        AnchorPane.setBottomAnchor(filterButton, 10.0);
        AnchorPane.setRightAnchor(filterButton, 10.0);
        popUpContent.getChildren().add(filterButton);
        filterButton.setOnAction(event -> handleFilterButtonClick());
    }

    private void createPriceRangeSection(GridPane gridPane)
    {
        Label priceRangeLabel = new Label("Prisramme");
        gridPane.add(priceRangeLabel, 0, 0, 2, 1);

        minPriceTextfield = new NumberOnlyTextField();
        minPriceTextfield.setPromptText("Minimumspris");
        gridPane.add(minPriceTextfield, 0, 1);
        this.minPrice = new SimpleIntegerProperty();
        setTextFieldBindings(minPriceTextfield, minPrice);

        maxPriceTextField = new NumberOnlyTextField();
        maxPriceTextField.setPromptText("Maksimumspris");
        gridPane.add(maxPriceTextField, 2, 1);
        this.maxPrice = new SimpleIntegerProperty();
        setTextFieldBindings(maxPriceTextField, maxPrice);

        Label binderLabel = new Label("-");
        gridPane.add(binderLabel, 1, 1);
    }

    private void createServicesSection(GridPane gridPane)
    {
        Label serviceLabel = new Label("Faciliteter");
        gridPane.add(serviceLabel, 0, 3, 2, 1);

        wifiCheckBox = new CheckBox("Trådløst Internet");
        gridPane.add(wifiCheckBox, 0, 4);
        wifiCheckBox.setOnAction(event -> filterCheckBox(wifiCheckBox.isSelected(), FilterTypes.WIFI));

        toiletCheckBox = new CheckBox("Toilet");
        gridPane.add(toiletCheckBox, 2, 4);
        toiletCheckBox.setOnAction(event -> filterCheckBox(electricityCheckBox.isSelected(), FilterTypes.TOILET));

        electricityCheckBox = new CheckBox("Strøm");
        gridPane.add(electricityCheckBox, 0, 5);
        electricityCheckBox.setOnAction(event -> filterCheckBox(electricityCheckBox.isSelected(), FilterTypes.ELECTRICITY));

        waterCheckBox = new CheckBox("Vand");
        gridPane.add(waterCheckBox, 2, 5);
        waterCheckBox.setOnAction(event -> filterCheckBox(waterCheckBox.isSelected(), FilterTypes.WATER));
    }

    private void createClearFilterBtn()
    {
        Button clearFilterButton = new Button("Ryd alle");
        clearFilterButton.setPrefWidth(100);
        AnchorPane.setBottomAnchor(clearFilterButton, 10.0);
        AnchorPane.setLeftAnchor(clearFilterButton, 10.0);
        popUpContent.getChildren().add(clearFilterButton);
        clearFilterButton.setOnAction(event -> handleClearFilterButtonClick());
    }

    private void createSeparator(GridPane gridPane, int rowIndex)
    {
        gridPane.add(new Separator(), 0, rowIndex, 3, 1);
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

    private void filterCheckBox(boolean isCheckBoxSelected, FilterTypes filterTypes)
    {
        switch (filterTypes)
        {
            case WIFI -> wifiFilter = isCheckBoxSelected;
            case WATER -> waterFilter = isCheckBoxSelected;
            case TOILET -> toiletFilter = isCheckBoxSelected;
            case ELECTRICITY -> electricityFilter = isCheckBoxSelected;
        }
        applyFilters();
    }

    private void applyFilters()
    {
        Predicate<Plot> filterMinMax = data ->
                (minPrice.get() == 0 || minPrice.get() < data.getMidPrice()) &&
                (maxPrice.get() == 0 || maxPrice.get() > data.getMidPrice());

        Predicate<Plot> filterCondition = data ->
                (!toiletFilter || data.toiletProperty().get()) &&
                (!electricityFilter || data.electricProperty().get()) &&
                (!waterFilter || data.waterProperty().get());

        filteredList = advertisementList.stream()
                .filter(filterCondition.and(filterMinMax))
                .collect(Collectors.toList());

        populateWithAds(filteredList);
        updateFilterButtonLabel(filterButton);
    }

    private void updateFilterButtonLabel(Button filterBtn)
    {
        int numMatches = filteredList.size();
        filterBtn.setText(String.format("%d plads%s", numMatches, numMatches != 1 ? "er" : ""));
    }

    private void setupSearchFieldListener()
    {
        searchTextField.setOnKeyReleased(event ->
        {
            if (event.getCode() == KeyCode.ENTER)
            {
                if (event.getText().equals(""))
                {
                    resetFilter();
                    populateWithAds(filteredList);
                }
                resetFilter();
                Predicate<Plot> filterCondition = data ->
                        data.getLocation().toLowerCase().contains(searchTextField.getText().toLowerCase()) ||
                        String.valueOf(data.getZipCode()).toLowerCase().contains(searchTextField.getText());

                filteredList = advertisementList.stream()
                        .filter(filterCondition)
                        .collect(Collectors.toList());
                populateWithAds(filteredList);
            }
        });
    }

    private void setTextFieldBindings(TextField priceTf, IntegerProperty priceProperty)
    {
        priceTf.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if (!newValue)
            {
                applyFilters();
            }
        });
        priceProperty.bind(Bindings.createIntegerBinding(() ->
        {
            try
            {
                return Integer.parseInt(priceTf.getText());
            }
            catch (NumberFormatException e)
            {
                return 0;
            }
        }, priceTf.textProperty()));

        priceTf.setOnKeyPressed(key ->
        {
            if (key.getCode() == KeyCode.ENTER)
            {
                applyFilters();
            }
        });
    }

    private void handleFilterButtonClick()
    {
        applyFilters();
        hidePopUp();
    }

    private void handleClearFilterButtonClick()
    {
        resetFilter();
        toiletCheckBox.setSelected(false);
        electricityCheckBox.setSelected(false);
        waterCheckBox.setSelected(false);
        filterButton.setText("Filter");
        maxPriceTextField.clear();
        minPriceTextfield.clear();
        populateWithAds(advertisementList);
    }

    private void resetFilter()
    {
        filteredList = new ArrayList<>(advertisementList);
    }

    private void populateWithAds(List<Plot> list)
    {
        tilePane.getChildren().clear();
        PlotPublisher publish = PlotPublisher.getInstance();
        for (Plot ad : list)
        {
            if(ad != null)
            {
                try
                {
                    Thumbnail thumbnail = new Thumbnail(new Image(ad.getImageRealPath()),
                            ad.getLocation() + ", " + ad.getZipCode(),
                            String.format("%.0f kr", ad.getMidPrice()));
                    thumbnail.setOnMouseReleased(event ->
                    {
                        publish.publish(SceneName.Main, ad);
                        handleClearFilterButtonClick();
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

    public enum FilterTypes
    {
        WIFI,
        TOILET,
        ELECTRICITY,
        WATER,
    }
}