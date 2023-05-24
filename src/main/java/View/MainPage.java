package View;

import Model.DaoObject.Combine;
import Model.Implements.DaoCombine;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import java.util.TreeMap;

public class MainPage extends Header
{
    private TreeMap<Integer, Combine> advertisementTree;
    private TextField searchField;
    private Button filterBtn;
    private RadioButton toiletBtn;
    private RadioButton electricityBtn;
    private RadioButton waterBtn;
    private ScrollPane scrollPane;
    private TilePane tilePane;

    public MainPage()
    {
        setupFilterControlsLayout();
        setupScrollPaneLayout();
        fillAds(tilePane);
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

        toiletBtn = new RadioButton("Toilet");
        toiletBtn.setLayoutX(filterBtn.getLayoutX() + filterBtn.getWidth() + 50);
        toiletBtn.setLayoutY(searchField.getLayoutY() + 5);

        electricityBtn = new RadioButton("Strøm");
        electricityBtn.setLayoutX(toiletBtn.getLayoutX() + 100);
        electricityBtn.setLayoutY(toiletBtn.getLayoutY());

        waterBtn = new RadioButton("Vand");
        waterBtn.setLayoutX(electricityBtn.getLayoutX() + 100);
        waterBtn.setLayoutY(electricityBtn.getLayoutY());

        this.ANCHOR_PANE.getChildren().addAll(searchField, filterBtn, toiletBtn, electricityBtn, waterBtn);
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
        DaoCombine DaoAdvertisements = new DaoCombine();
        advertisementTree = new TreeMap<>();

        for (Combine ad : DaoAdvertisements.GetAll())
        {
            advertisementTree.put(ad.getPlotID(), ad);
            tp.getChildren().add(new Thumbnail(new Image(ad.getImage()), ad.getLocation()));
        }
    }

    private void sortAds()
    {

    }

    private void filterAds()
    {

    }

    //region getter/setter

    public TreeMap<Integer, Combine> getAdvertisementTree()
    {
        return advertisementTree;
    }

    public void setAdvertisementTree(TreeMap<Integer, Combine> advertisementTree)
    {
        this.advertisementTree = advertisementTree;
    }

    public TextField getSearchField()
    {
        return searchField;
    }

    public void setSearchField(TextField searchField)
    {
        this.searchField = searchField;
    }

    public Button getFilterBtn()
    {
        return filterBtn;
    }

    public void setFilterBtn(Button filterBtn)
    {
        this.filterBtn = filterBtn;
    }

    public RadioButton getToiletBtn()
    {
        return toiletBtn;
    }

    public void setToiletBtn(RadioButton toiletBtn)
    {
        this.toiletBtn = toiletBtn;
    }

    public RadioButton getElectricityBtn()
    {
        return electricityBtn;
    }

    public void setElectricityBtn(RadioButton electricityBtn)
    {
        this.electricityBtn = electricityBtn;
    }

    public RadioButton getWaterBtn()
    {
        return waterBtn;
    }

    public void setWaterBtn(RadioButton waterBtn)
    {
        this.waterBtn = waterBtn;
    }

    public ScrollPane getScrollPane()
    {
        return scrollPane;
    }

    public void setScrollPane(ScrollPane scrollPane)
    {
        this.scrollPane = scrollPane;
    }

    public TilePane getTilePane()
    {
        return tilePane;
    }

    public void setTilePane(TilePane tilePane)
    {
        this.tilePane = tilePane;
    }
    //endregion
}