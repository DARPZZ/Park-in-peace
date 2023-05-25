package View;

import Model.DaoObject.Combine;
import Service.CombinePublisher;
import com.example.park.HelloApplication;
import com.example.park.SceneName;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Advertisement extends Header
{
    private Combine advertisement;
    private ImageView imageView;
    private Label pricePrDayLabel;
    private Label totalPriceLabel;

    private final DoubleProperty TOTAL_PRICE_VALUE = new SimpleDoubleProperty();

    public Advertisement()
    {
        imageView = new ImageView();
        CombinePublisher subscribe = CombinePublisher.getInstance();
        subscribe.subscribe(SceneName.Main, this::handleAdvertisementUpdate);
        imageView.setLayoutX(X_MARGIN);
        imageView.setLayoutY(this.getYMargin() + 80);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(600);
        imageView.setFitWidth(200);
        setupLayout();
        setupBackBtn();
    }

    private void setupLayout()
    {
        GridPane gpDateReserve = new GridPane();
        gpDateReserve.setPrefWidth(300);
        gpDateReserve.setVgap(5);
        gpDateReserve.setLayoutX(this.SCENE.getWidth() - 400);
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
        gpDescription.setLayoutX(imageView.getLayoutX());
        gpDescription.setLayoutY(imageView.getLayoutY());
        gpDescription.setGridLinesVisible(true);

        Label addressLabel = new Label("Adresse ");
        gpDescription.add(addressLabel, 0, 0);
        Label addressDataLabel = new Label();
        gpDescription.add(addressDataLabel, 1, 0);

        this.ANCHOR_PANE.getChildren().addAll(gpDateReserve, imageView, gpDescription);
    }

    private void setupBackBtn()
    {
        Button backBtn = new Button();
        backBtn.setLayoutY(this.getProfileBtn().getLayoutY());
        backBtn.setLayoutX(X_MARGIN);
        backBtn.setPrefSize(this.getProfileBtn().getPrefWidth(), this.getProfileBtn().getPrefHeight());
        backBtn.setOnAction(event -> HelloApplication.changeScene(SceneName.Main));
        this.ANCHOR_PANE.getChildren().add(backBtn);
    }

    private void handleAdvertisementUpdate(Combine updatedAdvertisement)
    {
        advertisement = updatedAdvertisement;
        if (advertisement != null)
        {
            imageView.setImage(new Image(advertisement.getImage()));
            setLabelValues();
        }
    }

    private void setLabelValues()
    {
        try
        {
            pricePrDayLabel.setText("" + advertisement.getMidSeasonPrice());
        }
        catch (Exception e)
        {
            System.err.println("An error occurred: " + e.getMessage());
        }
        totalPriceLabel.textProperty().bind(TOTAL_PRICE_VALUE.asString());
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
                TOTAL_PRICE_VALUE.setValue(ChronoUnit.DAYS.between(startDate.getValue(), endDate.getValue()) * Double.parseDouble(pricePrDayLabel.getText()));
                totalPriceLabel.setVisible(true);
            }
        });
    }

    private void reserveDate(LocalDate startDate, LocalDate endDate)
    {
        // DaoResevations daoResevations = new DaoResevations().Create(new Resevations(startDate, endDate, ));
    }
}