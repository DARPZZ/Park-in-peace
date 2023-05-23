package View;

import Model.DaoObject.Plot;
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

    public  PlotPage()
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
        ArrayList<TextField> textFieldList = new ArrayList<>();
        ArrayList<Label> labelList = new ArrayList<>();
        ArrayList<CheckBox> checkBoxes =new ArrayList<>();
        String[] servicesNames ={"🚽","\uD83D\uDCA7","⚡"};
        String[] labelNames = {"Adresse","Post NR","Størrelse","Lav Pris","Middel Pris", "Høj Pris"};

        int formStartX = 10;
        int formStartY = 110;
        int formOffSetX = 30;
        int formOffSetY = 60;


        for (int i = 0; i <labelNames.length-3 ; i++) // Lav UI for Adresse, zip og size
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

                Plot plotNew = new Plot(textFieldList.get(0).getText(),
                        textFieldList.get(3).getText(),
                        textFieldList.get(2).getText(),
                        Integer.parseInt( textFieldList.get(1).getText()),
                        Integer.parseInt(textFieldList.get(4).getText()),
                        Integer.parseInt(textFieldList.get(5).getText()),
                        Integer.parseInt(textFieldList.get(6).getText()),
                        checkBoxes.get(0).isSelected(),checkBoxes.get(1).isSelected(),checkBoxes.get(2).isSelected());
                HelloApplication.plotMap.put(1,plotNew);

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
                popUp.getChildren().add(descriptionField);
                popUp.getChildren().add(imagePlaceholder);
                popUp.getChildren().add(confirmForm);
                popUp.getChildren().addAll(checkBoxes);
                Scene dialogScene = new Scene(popUp, 800, 600);
                dialog.setScene(dialogScene);
                dialog.show();



            }
        });
        ANCHOR_PANE.getChildren().addAll(createPlot,backGround);
    }


}
