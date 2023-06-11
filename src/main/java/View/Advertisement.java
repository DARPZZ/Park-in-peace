package View;

import Model.DaoObject.Plot;
import Model.DaoObject.Resevations;
import Model.DaoObject.User;
import Model.Implements.DaoResevations;
import Service.PlotPublisher;
import com.example.park.SceneName;
import Service.UserSubscriber;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Advertisement extends Header implements UserSubscriber
{
    private Plot advertisement;
    private ImageView imageView;
    private Pane imageHolder;
    private GridPane reserveDateGridPane;
    private GridPane descriptionGridPane;
    private Label pricePrDayLabel;
    private Label totalPriceLabel;
    private Label addressDataLabel;
    private TextFlow descriptionDataTextField;
    private Label plotSizeDataLabel;
    private User user;

    private CheckBox wifiCheckBox;
    private CheckBox toiletsCheckBox;
    private CheckBox waterCheckBox;
    private CheckBox electricityCheckBox;

    public Advertisement()
    {
        PlotPublisher subscribe = PlotPublisher.getInstance();
        subscribe.subscribe(SceneName.Main, this::handleAdvertisementUpdate);
        setupLayout();
    }

    private void setupLayout()
    {
        setupImageView();
        createReserveDateGridPane();
        createDescriptionGridPane();
        this.anchorPane.getChildren().addAll(reserveDateGridPane, imageHolder, descriptionGridPane);
    }

    private void setupImageView()
    {
        imageView = new ImageView();
        imageHolder = new Pane(imageView);

        imageHolder.setLayoutX(X_MARGIN);
        imageHolder.setLayoutY(this.getYMargin() + 80);
        imageHolder.setMaxHeight(scene.getHeight() - imageHolder.getLayoutY() - 50);
        imageHolder.setMaxWidth(400);

        imageView.setPreserveRatio(true);
        imageView.setFitHeight(imageHolder.getMaxHeight());
        imageView.setFitWidth(imageHolder.getMaxWidth());
    }

    private void createReserveDateGridPane()
    {
        reserveDateGridPane = new GridPane();
        reserveDateGridPane.setPrefWidth(300);
        reserveDateGridPane.setVgap(5);
        reserveDateGridPane.setLayoutX(this.scene.getWidth() - 350);
        reserveDateGridPane.setLayoutY(this.getYMargin() + 80);

        DatePicker startDatePicker = new DatePicker();
        DatePicker endDatePicker = new DatePicker();
        setupDatePickers(startDatePicker, endDatePicker);

        Button reserveButton = new Button("Reserver");
        reserveButton.setPrefSize(reserveDateGridPane.getPrefWidth(), reserveDateGridPane.getPrefHeight()/ reserveDateGridPane.getRowCount());
        reserveButton.setOnAction(event -> reserveDate(startDatePicker.getValue(), endDatePicker.getValue()));

        Label pricePrDayPromptLabel = new Label("Pris pr. day: ");
        pricePrDayLabel = new Label();
        Label totalPricePromptLabel = new Label("I alt: ");
        totalPriceLabel = new Label();
        totalPriceLabel.setVisible(false);

        reserveDateGridPane.add(startDatePicker,0,0);
        reserveDateGridPane.add(endDatePicker,1, 0);
        reserveDateGridPane.add(reserveButton,0, 1, 3, 1);
        reserveDateGridPane.add(pricePrDayPromptLabel, 0, 2);
        reserveDateGridPane.add(pricePrDayLabel, 1, 2);
        reserveDateGridPane.add(totalPricePromptLabel, 0, 3);
        reserveDateGridPane.add(totalPriceLabel, 1, 3);
    }

    private void createDescriptionGridPane()
    {
        descriptionGridPane = new GridPane();
        descriptionGridPane.setVgap(10);
        descriptionGridPane.setHgap(10);
        descriptionGridPane.setLayoutX(imageHolder.getLayoutX() + imageHolder.getMaxWidth() + 50);
        descriptionGridPane.setLayoutY(imageHolder.getLayoutY());
        descriptionGridPane.setMaxWidth(reserveDateGridPane.getLayoutX() - descriptionGridPane.getLayoutY() - reserveDateGridPane.getPrefWidth() - 100);

        Label addressLabel = new Label("Adresse: ");
        descriptionGridPane.add(addressLabel, 0, 0);
        addressDataLabel = new Label();
        descriptionGridPane.add(addressDataLabel, 1, 0);

        createSeparator(descriptionGridPane,2);
        descriptionDataTextField = new TextFlow();
        descriptionGridPane.add(descriptionDataTextField, 0, 3, 3, 1);
        createSeparator(descriptionGridPane, 4);
        Label plotSizeLabel = new Label("Størrelse: ");
        descriptionGridPane.add(plotSizeLabel, 0, 1);
        plotSizeDataLabel = new Label();
        descriptionGridPane.add(plotSizeDataLabel, 1, 1);

        wifiCheckBox = new CheckBox("\uD83C\uDF10");
        wifiCheckBox.setDisable(true);
        wifiCheckBox.setStyle("-fx-opacity: 1");
        descriptionGridPane.add(wifiCheckBox, 0, 5);

        electricityCheckBox = new CheckBox("⚡");
        electricityCheckBox.setDisable(true);
        electricityCheckBox.setStyle("-fx-opacity: 1");
        descriptionGridPane.add(electricityCheckBox, 2, 5);

        waterCheckBox = new CheckBox("\uD83D\uDCA7");
        waterCheckBox.setDisable(true);
        waterCheckBox.setStyle("-fx-opacity: 1");
        descriptionGridPane.add(waterCheckBox, 0, 6);

        toiletsCheckBox = new CheckBox("🚽");
        toiletsCheckBox.setDisable(true);
        toiletsCheckBox.setStyle("-fx-opacity: 1");
        descriptionGridPane.add(toiletsCheckBox, 2, 6);
    }

    private void createSeparator(GridPane gridPane, int row)
    {
        gridPane.add(new Separator(), 0, row, 3, 1);
    }


    private void handleAdvertisementUpdate(Plot updatedAdvertisement)
    {
        advertisement = updatedAdvertisement;
        if (advertisement != null)
        {
            imageView.setImage(new Image(advertisement.getImageRealPath()));
            setLabelValues();
            String address = String.format("%s, %s", advertisement.getLocation(), advertisement.getZipCode());
            addressDataLabel.setText(address);
            plotSizeDataLabel.setText(advertisement.getPlotSize());
            descriptionDataTextField.getChildren().clear();
            descriptionDataTextField.getChildren().add(new Text(advertisement.getDescription()));
            toiletsCheckBox.selectedProperty().bind(advertisement.toiletProperty());
            waterCheckBox.selectedProperty().bind(advertisement.waterProperty());
            electricityCheckBox.selectedProperty().bind(advertisement.electricProperty());
        }
    }

    private void setupDatePickers(DatePicker startDatePicker, DatePicker endDatePicker)
    {
        setupStartDatePicker(startDatePicker);
        setupEndDatePicker(endDatePicker);
        setupStartDateListener(startDatePicker, endDatePicker);
        setupStartDateAction(startDatePicker, endDatePicker);
        setupEndDateAction(startDatePicker, endDatePicker);
    }

    private void setupStartDatePicker(DatePicker startDatePicker)
    {
        startDatePicker.setPromptText("Start dato");
        startDatePicker.setDayCellFactory(callback -> new DateCell()
        {
            @Override
            public void updateItem(LocalDate date, boolean empty)
            {
                super.updateItem(date, empty);
                LocalDate minDate = LocalDate.now().minusDays(1);
                setDisable(date.isBefore(minDate));
            }
        });
    }

    private void setupEndDatePicker(DatePicker endDatePicker)
    {
        endDatePicker.setPromptText("Slut dato");
        endDatePicker.setEditable(false);
        endDatePicker.setDisable(true);
    }

    private void setupStartDateListener(DatePicker startDatePicker, DatePicker endDatePicker)
    {
        startDatePicker.valueProperty().addListener((observable, oldValue, newValue) ->
                endDatePicker.setDayCellFactory(callback -> new DateCell()
                {
                    @Override
                    public void updateItem(LocalDate date, boolean empty)
                    {
                        super.updateItem(date, empty);
                        setDisable(date.isBefore(startDatePicker.getValue().plusDays(1)));
                    }
                }));
    }

    private void setupStartDateAction(DatePicker startDatePicker, DatePicker endDatePicker)
    {
        startDatePicker.setOnAction(event ->
        {
            if (endDatePicker.getValue() == null)
            {
                endDatePicker.setDisable(false);
            }
            else
            {
                endDatePicker.setValue(null);
            }
        });
    }

    private void setupEndDateAction(DatePicker startDatePicker, DatePicker endDatePicker)
    {
        endDatePicker.setOnAction(event ->
        {
            if (endDatePicker.getValue() != null)
            {
                String pricePrDay = pricePrDayLabel.getText().substring(0, pricePrDayLabel.getText().indexOf(" "));
                String totalPrice = String.format("%.0f kr", ChronoUnit.DAYS.between(startDatePicker.getValue(), endDatePicker.getValue()) * Double.parseDouble(pricePrDay));
                totalPriceLabel.setText(totalPrice);
                totalPriceLabel.setVisible(true);
            }
        });
    }

    private void reserveDate(LocalDate startDatePicker, LocalDate endDatePicker)
    {
        try
        {
            new DaoResevations().Create(new Resevations(startDatePicker, endDatePicker, user.getUserId(), advertisement.getPlotID()));
        }
        catch (Exception e)
        {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private void setLabelValues()
    {
        try
        {
            String standardPrice = String.format("%.0f kr",advertisement.getMidPrice());
            pricePrDayLabel.setText(standardPrice);
        }
        catch (Exception e)
        {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    @Override
    public void onUserReceived(User user)
    {
        this.user = user;
    }
}