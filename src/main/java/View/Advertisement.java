package View;

import Model.DaoObject.Combine;
import Service.CombinePublisher;
import Service.Topic;
import com.example.park.HelloApplication;
import com.example.park.SceneName;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Advertisement extends Header
{
    private CombinePublisher subscribe = CombinePublisher.getInstance();
    private Combine advertisement;
    private ImageView imageView;
    private Label pricePrDayValue;
    private Label totalPriceValue;

    public Advertisement()
    {
        imageView = new ImageView();
        subscribe.subscribe(Topic.MainPage, this::handleAdvertisementUpdate);
        imageView.setLayoutX(X_MARGIN);
        imageView.setLayoutY(this.getYMargin() + 80);
        setupLayout();
        setupBackBtn();
    }

    private void setupLayout()
    {
        GridPane gp = new GridPane();
        gp.setPrefWidth(300);
        gp.setVgap(5);
        gp.setLayoutX(this.SCENE.getWidth() - 400);
        gp.setLayoutY(this.getYMargin() + 80);

        DatePicker startDate = new DatePicker();
        DatePicker endDate = new DatePicker();

        Button reserveBtn = new Button("Reserver");
        reserveBtn.setPrefSize(gp.getPrefWidth(), gp.getPrefHeight()/gp.getRowCount());

        Label pricePrDay = new Label("Pris pr. day: ");
        pricePrDayValue = new Label();
        Label totalPrice = new Label("I alt");
        totalPriceValue = new Label("");

        gp.add(startDate,0,0);
        gp.add(endDate,1, 0);
        gp.add(reserveBtn,0, 1, 3, 1);
        gp.add(pricePrDay, 0, 2);
        gp.add(pricePrDayValue, 1, 2);
        gp.add(totalPrice, 0, 3);
        gp.add(totalPriceValue, 1, 3);

        this.ANCHOR_PANE.getChildren().addAll(gp, imageView);
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
            pricePrDayValue.setText("" + advertisement.getMidSeasonPrice());
        }
        catch (Exception e)
        {
            System.err.println("An error occured: " + e.getMessage());
        }
    }
}