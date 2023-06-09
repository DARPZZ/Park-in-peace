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
    public final Scene scene;
    public final AnchorPane anchorPane;

    private Button profileButton;
    private Button homeButton;
    private Button bookingsButton;
    private Button myPlotButton;

    public static final double X_MARGIN = 60;
    public static final int WIDTH = 150;
    public static final int HEIGHT = 30;
    public static final int GAP = WIDTH + 15;
    public static final int Y_LAYOUT = 80;

    public Header()
    {
        anchorPane = new AnchorPane();
        anchorPane.setOnMousePressed(event -> anchorPane.requestFocus());
        scene = new Scene(anchorPane, 1280, 720);
        String css = this.getClass().getResource("/Style.css").toExternalForm();
        scene.getStylesheets().add(css);

        setupLayout();
    }

    private void setupLayout()
    {
        createTaskBarButtons();
        createProfileButton();
        Label userLabel = createUserLabel();
        handleTaskBarButtonsClick();
        addLine();
        anchorPane.getChildren().addAll(profileButton, homeButton, bookingsButton, myPlotButton, userLabel);
    }

    private void createTaskBarButtons()
    {
        profileButton = new Button("⚙");
        homeButton = new Button("Hjem");
        bookingsButton = new Button("Reservationer");
        myPlotButton = new Button("Mine pladser");

        homeButton.setLayoutY(Y_LAYOUT);
        AnchorPane.setLeftAnchor(homeButton, X_MARGIN);
        homeButton.setPrefSize(WIDTH, HEIGHT);

        AnchorPane.setLeftAnchor(bookingsButton, AnchorPane.getLeftAnchor(homeButton) + GAP);
        bookingsButton.setLayoutY(Y_LAYOUT);
        bookingsButton.setPrefSize(WIDTH, HEIGHT);

        AnchorPane.setLeftAnchor(myPlotButton, AnchorPane.getLeftAnchor(bookingsButton) + GAP);
        myPlotButton.setLayoutY(Y_LAYOUT);
        myPlotButton.setPrefSize(WIDTH, HEIGHT);
    }

    private void createProfileButton()
    {
        AnchorPane.setRightAnchor(profileButton, X_MARGIN);
        profileButton.setLayoutY(20);
        profileButton.setPrefSize(35,HEIGHT);
    }

    private Label createUserLabel()
    {
        Label userLabel = new Label();
        AnchorPane.setRightAnchor(userLabel, AnchorPane.getRightAnchor(profileButton) + profileButton.getPrefWidth() + 15);
        userLabel.setLayoutY(profileButton.getLayoutY());
        userLabel.setPrefSize(WIDTH, HEIGHT);
        userLabel.setAlignment(Pos.BASELINE_RIGHT);
        //userLabel.setText("Ingen bruger");

        return userLabel;
    }

    private void handleTaskBarButtonsClick()
    {
        homeButton.setOnAction(event -> HelloApplication.changeScene(SceneName.Main));
        bookingsButton.setOnAction(event -> HelloApplication.changeScene(SceneName.Bookings));
        myPlotButton.setOnAction(event -> HelloApplication.changeScene(SceneName.PlotPage));
        profileButton.setOnAction(event ->
        {
            HelloApplication.changeScene(SceneName.ProfilePage);
            HelloApplication.profilePage.broadcast();
            HelloApplication.profilePage.setupPopUpBackground();
        });
    }

    private void addLine()
    {
        Line line = new Line();
        line.setStartX(15);
        line.setLayoutY(Y_LAYOUT - 15);
        line.setStrokeWidth(2);
        line.endXProperty().bind(scene.widthProperty().subtract(15));
        anchorPane.getChildren().add(line);
    }

    public double getYMargin()
    {
        return Y_LAYOUT + HEIGHT;
    }

    public Button getBookingsButton()
    {
        return bookingsButton;
    }
}
