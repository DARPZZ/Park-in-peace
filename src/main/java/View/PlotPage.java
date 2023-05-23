package View;

import com.example.park.HelloApplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        Rectangle backGround = new Rectangle(1280,768);



        String[] labelNames = {"Adresse","Post NR","Beskrivelse","Størrelse","Lav Pris","Middel Pris", "Høj Pris"};
        int formStartX = 10;
        int formStartY = 10;
        int formOffSetX = 50;
        int formOffSetY = 60;

        backGround.setStyle("-fx-background-color: BLACK;-fx-opacity: 0.8");
        backGround.setVisible(false);
        backGround.setDisable(true);

        backGround.setOnMouseClicked(event -> {
                dialog.close();
                backGround.setVisible(false);
                backGround.setDisable(true);

        });

        createPlot.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                backGround.setVisible(true);
                backGround.setDisable(false);
                AnchorPane popUp = new AnchorPane();
                popUp.setPrefSize(800 ,600);
                for (int i = 0; i <labelNames.length ; i++)
                {
                    Label label = new Label(labelNames[i]);
                    label.setLayoutY(formStartY+ formStartX*i);
                    popUp.getChildren().add(label);

                }
                Scene dialogScene = new Scene(popUp, 800, 600);
                dialog.setScene(dialogScene);
                dialog.show();



            }
        });
        ANCHOR_PANE.getChildren().addAll(createPlot,backGround);
    }


}
