package View;

import Model.DaoObject.Plot;
import Model.Implements.DaoPlot;
import Model.DatabaseWorker.PlotList;
import com.example.park.HelloApplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class PlotPage extends Header
{
    ArrayList<TextField> textFieldList = new ArrayList<>();
    ArrayList<Label> labelList = new ArrayList<>();
    ArrayList<CheckBox> checkBoxes =new ArrayList<>();
    String[] servicesNames ={"🚽","\uD83D\uDCA7","⚡"};
    String[] labelNames = {"Adresse","Post NR","Størrelse","Lav Pris","Middel Pris", "Høj Pris"};
    private int userID = 0;

    public  PlotPage()
    {
        createUI();
    }
    private void createUI()
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
                Plot plotNew = new Plot //findID() , can find plot id
                        (userID,textFieldList.get(0).getText(),
                        descriptionField.getText(),
                        "PLACEHOLDER",
                        textFieldList.get(2).getText(),
                        Integer.parseInt(textFieldList.get(1).getText()),
                        checkBoxes.get(0).isSelected(),
                                checkBoxes.get(1).isSelected(),
                                checkBoxes.get(2).isSelected(),
                        Float.parseFloat(textFieldList.get(3).getText()),
                        Float.parseFloat(textFieldList.get(4).getText()),
                        Float.parseFloat(textFieldList.get(5).getText()));
                        PlotList.getSingleton().CreatePlot(plotNew);
                // makes a new plot, list of: "Adresse","Post NR","Størrelse","beskrivelse","Lav Pris","Middel Pris", "Høj Pris"


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
                Scene dialogScene = new Scene(popUp, 800, 600);

                dialog.setScene(dialogScene);
                dialog.show();



            }
        });
        anchorPane.getChildren().addAll(createPlot,backGround);
    }

    private int findID()
    {
        int id =0;
        for (Plot p :PlotList.getSingleton().getList())
        {
            if (p.getPlotID() >= id);
            {id= p.getPlotID();}
        }
        return id+1;
    }

}
