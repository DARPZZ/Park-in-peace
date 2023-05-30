package View;

import Model.DaoObject.Combine;
import Model.DaoObject.Resevations;
import Model.DaoObject.User;
import Model.Implements.DaoResevations;
import Service.CombinePublisher;
import com.example.park.SceneName;
import com.example.park.UserSubscriber;
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
    private Combine advertisement;
    private ImageView imageView;
    private Pane imageHolder;
    private Label pricePrDayLabel;
    private Label totalPriceLabel;
    private Label addressDataLabel;
    private TextFlow descriptionDataTf;
    private Label plotSizeDataLabel;
    private User user;

    private CheckBox haveWifi;
    private CheckBox toiletsCheckBox;
    private CheckBox waterCheckBox;
    private CheckBox electricityCheckBox;

    public Advertisement()
    {
        CombinePublisher subscribe = CombinePublisher.getInstance();
        subscribe.subscribe(SceneName.Main, this::handleAdvertisementUpdate);
        setupLayout();
    }

    private void setupLayout()
    {
        setupImageView();

        GridPane gpDateReserve = new GridPane();
        gpDateReserve.setPrefWidth(300);
        gpDateReserve.setVgap(5);
        gpDateReserve.setLayoutX(this.SCENE.getWidth() - 350);
        gpDateReserve.setLayoutY(this.getYMargin() + 80);

        DatePicker startDate = new DatePicker();
        DatePicker endDate = new DatePicker();
        setupDatePickers(startDate, endDate);

        Button reserveBtn = new Button("Reserver");
        reserveBtn.setPrefSize(gpDateReserve.getPrefWidth(), gpDateReserve.getPrefHeight()/gpDateReserve.getRowCount());
        reserveBtn.setOnAction(event -> reserveDate(startDate.getValue(), endDate.getValue()));

        Label pricePrDayPromptLabel = new Label("Pris pr. day: ");
        pricePrDayLabel = new Label();
        Label totalPricePromptLabel = new Label("I alt: ");
        totalPriceLabel = new Label();
        totalPriceLabel.setVisible(false);

        gpDateReserve.add(startDate,0,0);
        gpDateReserve.add(endDate,1, 0);
        gpDateReserve.add(reserveBtn,0, 1, 3, 1);
        gpDateReserve.add(pricePrDayPromptLabel, 0, 2);
        gpDateReserve.add(pricePrDayLabel, 1, 2);
        gpDateReserve.add(totalPricePromptLabel, 0, 3);
        gpDateReserve.add(totalPriceLabel, 1, 3);

        GridPane gpDescription = new GridPane();
        gpDescription.setVgap(10);
        gpDescription.setHgap(10);
        gpDescription.setLayoutX(imageHolder.getLayoutX() + imageHolder.getMaxWidth() + 50);
        gpDescription.setLayoutY(imageHolder.getLayoutY());
        gpDescription.setMaxWidth(gpDateReserve.getLayoutX() - gpDescription.getLayoutY() - gpDateReserve.getPrefWidth() - 100);

        Label addressLabel = new Label("Adresse: ");
        gpDescription.add(addressLabel, 0, 0);
        addressDataLabel = new Label();
        gpDescription.add(addressDataLabel, 1, 0);

        gpDescription.add(new Separator(), 0,2, 3, 1);
        descriptionDataTf = new TextFlow();
        gpDescription.add(descriptionDataTf, 0, 3, 3, 1);

        gpDescription.add(new Separator(), 0, 4, 3, 1);
        Label plotSizeLabel = new Label("Størrelse: ");
        gpDescription.add(plotSizeLabel, 0, 1);
        plotSizeDataLabel = new Label();
        gpDescription.add(plotSizeDataLabel, 1, 1);

        haveWifi = new CheckBox("\uD83C\uDF10");
        haveWifi.setDisable(true);
        haveWifi.setStyle("-fx-opacity: 1");
        gpDescription.add(haveWifi, 0, 5);

        electricityCheckBox = new CheckBox("⚡");
        electricityCheckBox.setDisable(true);
        electricityCheckBox.setStyle("-fx-opacity: 1");
        gpDescription.add(electricityCheckBox, 2, 5);

        waterCheckBox = new CheckBox("\uD83D\uDCA7");
        waterCheckBox.setDisable(true);
        waterCheckBox.setStyle("-fx-opacity: 1");
        gpDescription.add(waterCheckBox, 0, 6);

        toiletsCheckBox = new CheckBox("🚽");
        toiletsCheckBox.setDisable(true);
        toiletsCheckBox.setStyle("-fx-opacity: 1");
        gpDescription.add(toiletsCheckBox, 2, 6);

        this.ANCHOR_PANE.getChildren().addAll(gpDateReserve, imageHolder, gpDescription);
    }

    private void setupImageView()
    {
        imageView = new ImageView();
        imageHolder = new Pane(imageView);
        imageHolder.setLayoutX(X_MARGIN);
        imageHolder.setLayoutY(this.getYMargin() + 80);
        imageHolder.setMaxHeight(SCENE.getHeight() - imageHolder.getLayoutY() - 50);
        imageHolder.setMaxWidth(400);

        imageView.setPreserveRatio(true);
        imageView.setFitHeight(imageHolder.getMaxHeight());
        imageView.setFitWidth(imageHolder.getMaxWidth());
    }

    private void handleAdvertisementUpdate(Combine updatedAdvertisement)
    {
        advertisement = updatedAdvertisement;
        if (advertisement != null)
        {
            imageView.setImage(new Image(advertisement.getImage()));
            setLabelValues();
            String address = String.format("%s, %s", advertisement.getLocation(), advertisement.getZipCode());
            addressDataLabel.setText(address);
            plotSizeDataLabel.setText(advertisement.getPlotSize());
            descriptionDataTf.getChildren().clear();
            descriptionDataTf.getChildren().add(new Text(advertisement.getDescription()));
            toiletsCheckBox.selectedProperty().bind(advertisement.toiletProperty());
            waterCheckBox.selectedProperty().bind(advertisement.waterProperty());
            electricityCheckBox.selectedProperty().bind(advertisement.elProperty());
        }
    }

    private void setLabelValues()
    {
        try
        {
            String standardPrice = String.format("%.0f kr",advertisement.getMidSeasonPrice());
            pricePrDayLabel.setText(standardPrice);
        }
        catch (Exception e)
        {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private void setupDatePickers(DatePicker startDate, DatePicker endDate)
    {
        startDate.setPromptText("Start dato");
        startDate.setDayCellFactory(callback -> new DateCell()
        {
            @Override
            public void updateItem(LocalDate date, boolean empty)
            {
                super.updateItem(date, empty);
                LocalDate minDate = LocalDate.now().minusDays(1);
                setDisable(date.isBefore(minDate));
            }
        });

        endDate.setPromptText("Slut dato");
        endDate.setEditable(false);
        endDate.setDisable(true);

        startDate.valueProperty().addListener((observable, oldValue, newValue) ->
                endDate.setDayCellFactory(callback -> new DateCell()
                {
                    @Override
                    public void updateItem(LocalDate date, boolean empty)
                    {
                        super.updateItem(date, empty);
                        setDisable(date.isBefore(startDate.getValue().plusDays(1)));
                    }
                }));

        startDate.setOnAction(event ->
        {
            if (endDate.getValue() == null)
            {
                endDate.setDisable(false);
            }
            else
            {
                endDate.setValue(null);
            }
        });

        endDate.setOnAction(event ->
        {
            if (endDate.getValue() != null)
            {
                String pricePrDay = pricePrDayLabel.getText().substring(0, pricePrDayLabel.getText().indexOf(" "));
                String totalPrice = String.format("%.0f kr", ChronoUnit.DAYS.between(startDate.getValue(), endDate.getValue()) * Double.parseDouble(pricePrDay));
                totalPriceLabel.setText(totalPrice);
                totalPriceLabel.setVisible(true);
            }
        });
    }

    private void reserveDate(LocalDate startDate, LocalDate endDate)
    {
        try
        {
            new DaoResevations().Create(new Resevations(startDate, endDate, user.getUserId(), advertisement.getPlotID()));
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