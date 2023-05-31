package View;

import Model.DaoObject.Combine;
import Model.DaoObject.Plot;
import Model.DaoObject.User;
import Model.DatabaseWorker.PlotList;
import com.example.park.HelloApplication;

import com.example.park.UserSubscriber;
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

public class PlotPage extends Header implements UserSubscriber
{
    private User activeUser;
    private ArrayList<TextField> textFieldList = new ArrayList<>();
    private ArrayList<Label> labelList = new ArrayList<>();
    private   ArrayList<CheckBox> checkBoxes =new ArrayList<>();
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
        scrollPane.setLayoutY(getYMargin());
        scrollPane.setLayoutX(1230);
        scrollPane.setPrefHeight(500);
        scrollPane.setPrefWidth(50);
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

        scrollPane.setContent(tilePane);
        ANCHOR_PANE.getChildren().add(plotview);
        ANCHOR_PANE.getChildren().add(scrollPane);


    }
    public void createPopUpUI()
    {
        Stage dialog = new Stage();
        dialog.initOwner(HelloApplication.getStage());
        dialog.initStyle(StageStyle.TRANSPARENT);

        Button createPlot = new Button("Opret");
        createPlot.setLayoutY(getYMargin());
        createPlot.setLayoutX(60);
        //region background back button
        Rectangle backGround = new Rectangle(1280,768);
        backGround.setStyle("-fx-background-color: BLACK;-fx-opacity: 0.8");
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
        ANCHOR_PANE.getChildren().addAll(createPlot,backGround);
    }

    public void preparePlotGrid()
    {
        int columnCount =0;
        int rowCount =0;
        for (int i = 0; i < plotArrayList.size() ; i++)
        {

            Image thumbnailImage = new Image("C:\\Java\\Billeder\\MVC pattrn.PNG");
            Thumbnail plotThumbnail = new Thumbnail(thumbnailImage,plotArrayList.get(i).getLocation());
            plotview.add(plotThumbnail,columnCount,rowCount);
            rowCount++;
            if (4%1 ==0)
            {
                columnCount++;
                rowCount=0;
            }
        }
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
        }

    @Override
    public void onUserReceived(User user)
    {
        System.out.println("");
     activeUser = user;
    }


}
