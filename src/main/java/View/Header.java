package View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public abstract class Header
{
    public final Scene SCENE;
    public final AnchorPane ANCHOR_PANE;
    public final Label USER_LABEL;
    public final Button profileBtn;
    public final Button homeBtn;
    public final Button bookingsBtn;
    public final Button myPlotBtn;
    public final double X_MARGIN = 60;
    public final int WIDTH = 150;
    public final int HEIGHT = 30;
    public final int GAP = WIDTH + 15;
    public final int Y_LAYOUT = 80;

    public Header()
    {
        ANCHOR_PANE = new AnchorPane();
        ANCHOR_PANE.setOnMouseClicked(event -> ANCHOR_PANE.requestFocus());
        SCENE = new Scene(ANCHOR_PANE, 1280, 720);

        profileBtn = new Button("⚙");
        homeBtn = new Button("Hjem");
        bookingsBtn = new Button("Reservationer");
        myPlotBtn = new Button("Mine pladser");

        AnchorPane.setRightAnchor(profileBtn, X_MARGIN);
        profileBtn.setLayoutY(20);
        profileBtn.setPrefSize(35,HEIGHT);

        USER_LABEL = new Label("Ingen bruger");
        AnchorPane.setRightAnchor(USER_LABEL, AnchorPane.getRightAnchor(profileBtn) + profileBtn.getPrefWidth() + 15);
        USER_LABEL.setLayoutY(profileBtn.getLayoutY());
        USER_LABEL.setPrefSize(WIDTH, HEIGHT);
        USER_LABEL.setAlignment(Pos.BASELINE_RIGHT);

        homeBtn.setLayoutY(Y_LAYOUT);
        AnchorPane.setLeftAnchor(homeBtn, X_MARGIN);
        homeBtn.setPrefSize(WIDTH, HEIGHT);

        AnchorPane.setLeftAnchor(bookingsBtn, AnchorPane.getLeftAnchor(homeBtn) + GAP);
        bookingsBtn.setLayoutY(Y_LAYOUT);
        bookingsBtn.setPrefSize(WIDTH, HEIGHT);

        AnchorPane.setLeftAnchor(myPlotBtn, AnchorPane.getLeftAnchor(bookingsBtn) + GAP);
        myPlotBtn.setLayoutY(Y_LAYOUT);
        myPlotBtn.setPrefSize(WIDTH, HEIGHT);

        Line line = new Line();
        line.setStartX(15);
        line.setLayoutY(Y_LAYOUT - 15);
        line.setStrokeWidth(2);
        line.endXProperty().bind(SCENE.widthProperty().subtract(15));

        ANCHOR_PANE.getChildren().addAll(profileBtn, homeBtn, bookingsBtn, myPlotBtn, line, USER_LABEL);
    }

    //region getter/setter
    public double getYMargin()
    {
        return Y_LAYOUT + HEIGHT;
    }
    //endregion
}
