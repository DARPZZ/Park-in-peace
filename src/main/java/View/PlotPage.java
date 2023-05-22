package View;

import com.example.park.HelloApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PlotPage extends Header
{

    public  PlotPage()
    {
        Button createPlot = new Button("Opret");
        createPlot.setLayoutY(getYMargin());
        createPlot.setLayoutX(60);
        createPlot.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(HelloApplication.getStage());

                dialog.initStyle(StageStyle.TRANSPARENT);
                AnchorPane meme = new AnchorPane();
                meme.setPrefSize(100,200);
                Scene dialogScene = new Scene(meme, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();
            }
        });
        ANCHOR_PANE.getChildren().add(createPlot);
    }


}
