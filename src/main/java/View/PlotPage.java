package View;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class PlotPage extends Header
{

    public  PlotPage()
    {
        Button createPlot = new Button("Opret");
        createPlot.setLayoutY(getYMargin());
        createPlot.setLayoutY(60);
        ANCHOR_PANE.getChildren().add(createPlot);
    }


}
