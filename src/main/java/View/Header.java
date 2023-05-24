package View;

import com.example.park.HelloApplication;
import com.example.park.SceneName;
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

    private Label userLabel;
    private Button profileBtn;
    private Button homeBtn;
    private Button bookingsBtn;
    private Button myPlotBtn;

    public static final double X_MARGIN = 60;
    public static final int WIDTH = 150;
    public static final int HEIGHT = 30;
    public static final int GAP = WIDTH + 15;
    public static final int Y_LAYOUT = 80;

    public Header()
    {
        ANCHOR_PANE = new AnchorPane();
        ANCHOR_PANE.setOnMouseClicked(event -> ANCHOR_PANE.requestFocus());
        SCENE = new Scene(ANCHOR_PANE, 1280, 720);

        setupLayout();
        addLine();

        homeBtn.setOnAction(event -> HelloApplication.changeScene(SceneName.Main));
        bookingsBtn.setOnAction(event -> HelloApplication.changeScene(SceneName.Bookings));
        myPlotBtn.setOnAction(event -> HelloApplication.changeScene(SceneName.PlotPage));
    }

    private void setupLayout()
    {
        profileBtn = new Button("⚙");
        homeBtn = new Button("Hjem");
        bookingsBtn = new Button("Reservationer");
        myPlotBtn = new Button("Mine pladser");

        AnchorPane.setRightAnchor(profileBtn, X_MARGIN);
        profileBtn.setLayoutY(20);
        profileBtn.setPrefSize(35,HEIGHT);

        userLabel = new Label("Ingen bruger");
        AnchorPane.setRightAnchor(userLabel, AnchorPane.getRightAnchor(profileBtn) + profileBtn.getPrefWidth() + 15);
        userLabel.setLayoutY(profileBtn.getLayoutY());
        userLabel.setPrefSize(WIDTH, HEIGHT);
        userLabel.setAlignment(Pos.BASELINE_RIGHT);

        homeBtn.setLayoutY(Y_LAYOUT);
        AnchorPane.setLeftAnchor(homeBtn, X_MARGIN);
        homeBtn.setPrefSize(WIDTH, HEIGHT);

        AnchorPane.setLeftAnchor(bookingsBtn, AnchorPane.getLeftAnchor(homeBtn) + GAP);
        bookingsBtn.setLayoutY(Y_LAYOUT);
        bookingsBtn.setPrefSize(WIDTH, HEIGHT);

        AnchorPane.setLeftAnchor(myPlotBtn, AnchorPane.getLeftAnchor(bookingsBtn) + GAP);
        myPlotBtn.setLayoutY(Y_LAYOUT);
        myPlotBtn.setPrefSize(WIDTH, HEIGHT);

        ANCHOR_PANE.getChildren().addAll(profileBtn, homeBtn, bookingsBtn, myPlotBtn, userLabel);
    }

    private void addLine()
    {
        Line line = new Line();
        line.setStartX(15);
        line.setLayoutY(Y_LAYOUT - 15);
        line.setStrokeWidth(2);
        line.endXProperty().bind(SCENE.widthProperty().subtract(15));
        ANCHOR_PANE.getChildren().add(line);
    }

    //region getter/setter
    public double getYMargin()
    {
        return Y_LAYOUT + HEIGHT;
    }

    public Label getUserLabel()
    {
        return userLabel;
    }

    public void setUserLabel(Label userLabel)
    {
        this.userLabel = userLabel;
    }

    public Button getProfileBtn()
    {
        return profileBtn;
    }

    public void setProfileBtn(Button profileBtn)
    {
        this.profileBtn = profileBtn;
    }

    public Button getHomeBtn()
    {
        return homeBtn;
    }

    public void setHomeBtn(Button homeBtn)
    {
        this.homeBtn = homeBtn;
    }

    public Button getBookingsBtn()
    {
        return bookingsBtn;
    }

    public void setBookingsBtn(Button bookingsBtn)
    {
        this.bookingsBtn = bookingsBtn;
    }

    public Button getMyPlotBtn()
    {
        return myPlotBtn;
    }

    public void setMyPlotBtn(Button myPlotBtn)
    {
        this.myPlotBtn = myPlotBtn;
    }

    //endregion
}
