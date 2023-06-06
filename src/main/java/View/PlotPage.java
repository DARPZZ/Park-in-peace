package View;

import Model.DaoObject.Plot;
import Model.DaoObject.User;
import Model.DatabaseWorker.PlotList;
import com.example.park.HelloApplication;

import com.example.park.UserSubscriber;
import javafx.beans.binding.FloatBinding;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;
import java.util.zip.DeflaterInputStream;

public class PlotPage extends Header implements UserSubscriber
{
    TilePane tilePane;
    private User activeUser;
    private ArrayList<TextField> textFieldList = new ArrayList<>();
    private ArrayList<Label> labelList = new ArrayList<>();
    private ArrayList<CheckBox> checkBoxes =new ArrayList<>();
    private GridPane plotview = new GridPane();
    private String[] servicesNames ={"🚽","\uD83D\uDCA7","⚡"};
    private String[] labelNames = {"Adresse","Post NR","Størrelse","Lav Pris","Middel Pris", "Høj Pris"};
    private ArrayList<Plot> plotArrayList =new ArrayList<>();



    public  PlotPage()  {


        plotview.setAlignment(Pos.CENTER);
        plotview.setLayoutX(25);
        plotview.setLayoutY(300);
        plotview.setHgap(10);
        plotview.setVgap(10);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutY(getYMargin()+50);
        scrollPane.setLayoutX(10);
        scrollPane.setPrefHeight(700);
        scrollPane.setPrefWidth(1265);
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

        scrollPane.setContent(tilePane);
        anchorPane.getChildren().add(plotview);
        anchorPane.getChildren().add(scrollPane);


    }
    public void createPopUpCreatePlot()
    {
        Stage dialog = new Stage();
        dialog.initOwner(HelloApplication.getStage());
        dialog.initStyle(StageStyle.TRANSPARENT);

        Button createPlot = new Button("Opret");
        createPlot.setLayoutY(getYMargin());
        createPlot.setLayoutX(60);
        //region background back button
        Rectangle backGround = new Rectangle(1280,768);
        backGround.setStyle("-fx-background-color: GREY;-fx-opacity: 0.8");
        backGround.setVisible(false);
        backGround.setDisable(true);
        backGround.setOnMouseClicked(event -> {
            dialog.close();
            backGround.setVisible(false);
            backGround.setDisable(true);

        });
        //endregion


        int formStartX = 10;
        int formStartY = 110;
        int formOffSetX = 30;
        int formOffSetY = 60;


        for (int i = 0; i <labelNames.length-4 ; i++) // Lav UI for Adresse, zip
        {
            Label label = new Label(labelNames[i]);
            label.setLayoutY(formStartY+ formOffSetY);
            label.setLayoutX(formStartX);
            labelList.add(label);

            Label labelCheckmark = new Label(servicesNames[i]);
            labelCheckmark.setLayoutY(formStartY+ formOffSetY);
            labelCheckmark.setLayoutX(formOffSetX+formOffSetX+200);
            labelList.add(labelCheckmark);

            CheckBox services = new CheckBox();
            services.setLayoutY(formStartY+ formOffSetY);
            services.setLayoutX(formOffSetX+formOffSetX+230);
            checkBoxes.add(services);


            TextField textField = new TextField();
            textField.setLayoutX(formOffSetX+formOffSetX);
            textField.setLayoutY(formStartY+ formOffSetY);
            textFieldList.add(textField);
            formStartY +=formOffSetY ;
        }
        // lav ui for size

        Label labelSize = new Label(labelNames[2]);
        labelSize.setLayoutY(formStartY+ formOffSetY);
        labelSize.setLayoutX(formStartX);
        labelList.add(labelSize);

        ComboBox<String> sizePicker= new ComboBox<>();
        sizePicker.getItems().addAll(PlotList.getSingleton().getAllSizeTypes());
        sizePicker.setLayoutX(labelSize.getLayoutX()+75);
        sizePicker.setLayoutY(formStartY+formOffSetY-5);

        Label labelCheckmark = new Label(servicesNames[2]);
        labelCheckmark.setLayoutY(formStartY+ formOffSetY);
        labelCheckmark.setLayoutX(formOffSetX+formOffSetX+200);
        labelList.add(labelCheckmark);

        CheckBox services = new CheckBox();
        services.setLayoutY(formStartY+ formOffSetY);
        services.setLayoutX(formOffSetX+formOffSetX+230);
        checkBoxes.add(services);


        formStartY+= formOffSetY;


        //region lav ui for beskrivelse label+textarea
        Label description = new Label("Beskrivelse");
        description.setLayoutX(formStartX);
        description.setLayoutY(formStartY+formOffSetY);
        formStartY+=formOffSetY+20;
        labelList.add(description);

        TextArea descriptionField = new TextArea();
        descriptionField.setLayoutX(formStartX);
        descriptionField.setLayoutY(formStartY);
        descriptionField.prefHeight(200);
        descriptionField.prefWidth(300);
        //endregion

        formStartX += 500;
        formOffSetX+=30;
        for (int i = labelNames.length-3; i <labelNames.length ; i++) // Lav UI for priserne
        {
            Label label = new Label(labelNames[i]);
            label.setLayoutX(formStartX);
            label.setLayoutY(formStartY);
            labelList.add(label);

            TextField textField = new TextField();
            textField.setLayoutY(formStartY);
            textField.setLayoutX(formStartX+formOffSetX+10);
            textFieldList.add(textField);
            formStartY+=formOffSetY;
        }

        //region placeholder for image indsæt
        Rectangle imagePlaceholder = new Rectangle(400,100,300,200);
        imagePlaceholder.setStyle("-fx-background-color: GREY");
        //endregion

        Button confirmForm = new Button("Bekræft");
        confirmForm.setLayoutY(560);
        confirmForm.setLayoutX(400);
        confirmForm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                Plot plotNew = new Plot
                        (activeUser.getUserId(), // userid
                                textFieldList.get(0).getText(),//location
                        descriptionField.getText(),// description
                        "PLACEHOLDER", //imagepth
                        sizePicker.getValue(),//size
                        Integer.parseInt(textFieldList.get(1).getText()), //zip
                        checkBoxes.get(0).isSelected(), //toilet
                                checkBoxes.get(1).isSelected(), // water
                                checkBoxes.get(2).isSelected(), //el
                        Float.parseFloat(textFieldList.get(2).getText()), //priceLow
                        Float.parseFloat(textFieldList.get(3).getText()),// priceMed
                        Float.parseFloat(textFieldList.get(4).getText())); //priceHigh
                        PlotList.getSingleton().CreatePlot(plotNew);
                // makes a new plot, list of: "Adresse","Post NR","Størrelse","beskrivelse","Lav Pris","Middel Pris", "Høj Pris"
                for (TextField t:textFieldList)
                {
                        t.clear();
                }
                plotArrayList.add(plotNew);
                tilePane.getChildren().clear();
                preparePlotGrid();
                dialog.close();
                backGround.setVisible(false);
                backGround.setDisable(true);

            }
        });


        createPlot.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                backGround.setVisible(true);
                backGround.setDisable(false);

                AnchorPane popUp = new AnchorPane();
                popUp.setPrefSize(800 ,600);
                popUp.getChildren().addAll(textFieldList);
                popUp.getChildren().addAll(labelList);
                popUp.getChildren().addAll(descriptionField,imagePlaceholder,confirmForm);
                popUp.getChildren().addAll(checkBoxes);
                popUp.getChildren().add(sizePicker);
                Scene dialogScene = new Scene(popUp, 800, 600);

                dialog.setScene(dialogScene);
                dialog.show();



            }
        });
        anchorPane.getChildren().addAll(createPlot,backGround);
    }

    public void createPopUpPlotInfo(Plot plot)
    {
        Stage dialogBox = new Stage();
        dialogBox.initOwner(HelloApplication.getStage());
        dialogBox.initStyle(StageStyle.TRANSPARENT);

        AnchorPane popup = new AnchorPane();
        popup.setPrefSize(800 ,600);

        Rectangle backGroundBox = new Rectangle(1280,768);
        backGroundBox.setStyle("-fx-background-color: GREY;-fx-opacity: 0.8");
        backGroundBox.setVisible(false);
        backGroundBox.setDisable(true);
        backGroundBox.setOnMouseClicked(event -> {
            dialogBox.close();
            backGroundBox.setVisible(false);
            backGroundBox.setDisable(true);
        });
        anchorPane.getChildren().add(backGroundBox);
        int formStartX = 10;
        int formStartY = 170;
        int formOffSetX = 60;
        int formOffSetY = 60;

        Label adressLabel = new Label("Adresse:");
        adressLabel.setLayoutX(formStartX);
        adressLabel.setLayoutY(formStartY);
        popup.getChildren().add(adressLabel);

        TextField adresse = new TextField(plot.getLocation());
        adresse.setLayoutX(adressLabel.getLayoutX()+formOffSetX);
        adresse.setLayoutY(adressLabel.getLayoutY());
        adresse.setEditable(false);
        adresse.textProperty().bindBidirectional(plot.locationProperty());
        popup.getChildren().add(adresse);

        Label postNrLabel = new Label("Post Nr:");
        postNrLabel.setLayoutX(formStartX);
        postNrLabel.setLayoutY(adresse.getLayoutY()+formOffSetY);
        popup.getChildren().add(postNrLabel);


        TextField postNr = new TextField(String.valueOf(plot.getZipCode()));
        postNr.setLayoutX(postNrLabel.getLayoutX()+formOffSetX);
        postNr.setLayoutY(postNrLabel.getLayoutY());
        postNr.setEditable(false);
        //postNr.textProperty().bindBidirectional(plot.zipCodeProperty());
        popup.getChildren().add(postNr);

        Label sizeLabel = new Label("Størrelse:");
        sizeLabel.setLayoutX(postNrLabel.getLayoutX());
        sizeLabel.setLayoutY(postNr.getLayoutY()+formOffSetY);
        popup.getChildren().add(sizeLabel);

        ComboBox<String> sizePicker= new ComboBox<>();
        sizePicker.getItems().addAll(PlotList.getSingleton().getAllSizeTypes());
        sizePicker.setLayoutX(sizeLabel.getLayoutX()+formOffSetX);
        sizePicker.setLayoutY(sizeLabel.getLayoutY());
        sizePicker.setEditable(false);
        sizePicker.valueProperty().bindBidirectional(plot.plotSizeProperty());
        sizePicker.getSelectionModel().select(plot.getPlotSize());
        sizePicker.setDisable(true);
        popup.getChildren().add(sizePicker);

        Label desriptionLabel = new Label("Beskrivelse:");
        desriptionLabel.setLayoutX(sizeLabel.getLayoutX());
        desriptionLabel.setLayoutY(sizePicker.getLayoutY()+formOffSetY);
        popup.getChildren().add(desriptionLabel);

        TextArea description = new TextArea();
        description.prefHeight(200);
        description.prefWidth(300);
        description.setLayoutX(desriptionLabel.getLayoutX());
        description.setLayoutY(desriptionLabel.getLayoutY()+30);
        description.textProperty().bindBidirectional(plot.descriptionProperty());
        description.setEditable(false);
        popup.getChildren().add(description);

        Label toiletLabel = new Label(servicesNames[0]);
        toiletLabel.setLayoutY(adresse.getLayoutY());
        toiletLabel.setLayoutX(adresse.getLayoutX()+formOffSetX*3);
        System.out.println(adresse.getLayoutX()+formOffSetX);
        popup.getChildren().add(toiletLabel);

        CheckBox toiletBox = new CheckBox();
        toiletBox.setLayoutY(toiletLabel.getLayoutY());
        toiletBox.setLayoutX(toiletLabel.getLayoutX()+20);
        System.out.println(toiletLabel.getLayoutX()+20);
        toiletBox.selectedProperty().bindBidirectional(plot.toiletProperty());
        //toiletBox.selectedProperty().set(plot.isToilet());
        popup.getChildren().add(toiletBox);

        Label waterLabel = new Label(servicesNames[1]);
        waterLabel.setLayoutY(postNr.getLayoutY());
        waterLabel.setLayoutX(postNr.getLayoutX()+formOffSetX*3);
        popup.getChildren().add(waterLabel);

        CheckBox waterBox = new CheckBox();
        waterBox.setLayoutY(waterLabel.getLayoutY());
        waterBox.setLayoutX(waterLabel.getLayoutX()+20);
        waterBox.selectedProperty().bindBidirectional(plot.waterProperty());
        //waterBox.selectedProperty().set(plot.isWater());
        popup.getChildren().add(waterBox);

        Label electricLabel = new Label(servicesNames[2]);
        electricLabel.setLayoutY(sizeLabel.getLayoutY());
        electricLabel.setLayoutX(waterLabel.getLayoutX());
        popup.getChildren().add(electricLabel);

        CheckBox electricBox = new CheckBox();
        electricBox.setLayoutY(electricLabel.getLayoutY());
        electricBox.setLayoutX(electricLabel.getLayoutX()+20);
        electricBox.selectedProperty().bindBidirectional(plot.electricProperty());
        //electricBox.selectedProperty().set(plot.isElectric());
        popup.getChildren().add(electricBox);

        Label lowLabel = new Label("Lav Sæson:");
        lowLabel.setLayoutX(500);
        lowLabel.setLayoutY(description.getLayoutY());
        popup.getChildren().add(lowLabel);

        TextField lowPrice = new TextField();
        lowPrice.setLayoutX(lowLabel.getLayoutX()+formOffSetX+10);
        lowPrice.setLayoutY(lowLabel.getLayoutY());
        lowPrice.setText(String.valueOf(plot.getLowPrice()));
        lowPrice.editableProperty().set(false);
        popup.getChildren().add(lowPrice);

        Label medLabel = new Label("Alm. Sæson:");
        medLabel.setLayoutX(lowLabel.getLayoutX());
        medLabel.setLayoutY(lowLabel.getLayoutY()+formOffSetY);
        popup.getChildren().add(medLabel);

        TextField medPrice = new TextField();
        medPrice.setLayoutX(medLabel.getLayoutX()+formOffSetX+10);
        medPrice.setLayoutY(medLabel.getLayoutY());
        medPrice.setText(String.valueOf(plot.getMidPrice()));
        medPrice.setEditable(false);
        popup.getChildren().add(medPrice);

        Label highLabel = new Label("Høj Sæson:");
        highLabel.setLayoutX(medLabel.getLayoutX());
        highLabel.setLayoutY(medLabel.getLayoutY()+formOffSetY);
        popup.getChildren().add(highLabel);

        TextField highPrice = new TextField();
        highPrice.setLayoutX(highLabel.getLayoutX()+formOffSetX+10);
        highPrice.setLayoutY(highLabel.getLayoutY());
        highPrice.setText(String.valueOf(plot.getMidPrice()));
        highPrice.setEditable(false);
        popup.getChildren().add(highPrice);

        Button edit = new Button("🔧");
        edit.setLayoutX(650);
        edit.setLayoutY(50);


        Button exitEdit = new Button("❌");
        exitEdit.setLayoutX(650);
        exitEdit.setLayoutY(50);
        exitEdit.setVisible(false);
        exitEdit.setDisable(true);


        Button comfirm = new Button("Bekræft");
        comfirm.setLayoutX(350);
        comfirm.setLayoutY(565);
        comfirm.setDisable(true);

        edit.setOnMouseClicked(event -> {
            comfirm.setDisable(false);
            exitEdit.setDisable(false);

            highPrice.setEditable(true);
            medPrice.setEditable(true);
            lowPrice.setEditable(true);
            sizePicker.setDisable(false);

            description.setEditable(true);
            sizePicker.setEditable(true);
            postNr.setEditable(true);
            adresse.setEditable(true);

            edit.setDisable(true);
            edit.setVisible(false);
            exitEdit.setVisible(true);

        });
        exitEdit.setOnMouseClicked(event -> {
            comfirm.setDisable(true);
            exitEdit.setDisable(true);

            highPrice.setEditable(false);
            medPrice.setEditable(false);
            lowPrice.setEditable(false);
            sizePicker.setDisable(true);

            description.setEditable(false);
            sizePicker.setEditable(false);
            postNr.setEditable(false);
            adresse.setEditable(false);
            edit.setDisable(false);
            edit.setVisible(true);
            exitEdit.setVisible(false);
        });
        comfirm.setOnMouseClicked(event -> {
            plot.setLowPrice(Float.parseFloat(lowPrice.getText()));
            plot.setMidPrice(Float.parseFloat(medPrice.getText()));
            plot.setHighPrice(Float.parseFloat(highPrice.getText()));
            plot.setImagePath("PLACEHOLDER IMAGE");
            plot.setZipCode(Integer.valueOf(postNr.getText()));
            PlotList.getSingleton().UpdatePlot(plot);
        });

        popup.getChildren().add(comfirm);
        popup.getChildren().add(exitEdit);
        popup.getChildren().add(edit);

        backGroundBox.setVisible(true);
        backGroundBox.setDisable(false);
        Scene dialogboxscene = new Scene(popup,800,600);
        dialogBox.setScene(dialogboxscene);
        dialogBox.show();











    }

    public void preparePlotGrid()
    {
        for (Plot plot: plotArrayList)
        {
            Image thumbnailImage = new Image("C:\\Java\\Billeder\\MVC pattrn.PNG");
            Thumbnail plotThumbnail = new Thumbnail(thumbnailImage,plot.getLocation());
            //plotview.add(plotThumbnail,columnCount,rowCount);
            plotThumbnail.setOnMouseClicked(event -> {
                System.out.println("meme");
                createPopUpPlotInfo(plot);
            });
            tilePane.getChildren().add(plotThumbnail);
        }
        System.out.println("");
    }
    public void initPlotPage()
        {
          for (Plot p: PlotList.getSingleton().getList())
            {
           if (p.getUserID() == activeUser.getUserId())
            {
                 plotArrayList.add(p);
             }
                }
            System.out.println("");
        }

    @Override
    public void onUserReceived(User user)
    {
        System.out.println("");
     activeUser = user;
    }


}
