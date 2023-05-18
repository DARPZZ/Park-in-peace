package View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public abstract class Header
{
    private Scene scene;
    private AnchorPane anchorPane;
    private Button profileBtn;
    private Button homeBtn;
    private Button bookingsBtn;
    private Button mySpacesBtn;

    private final int headerLimit = 120;

    public Header()
    {
        anchorPane = new AnchorPane();
        anchorPane.setOnMouseClicked(event -> anchorPane.requestFocus());
        scene = new Scene(anchorPane, 1280, 720);

        profileBtn = new Button("\u2699");
        homeBtn = new Button("Hjem");
        bookingsBtn = new Button("Reservationer");
        mySpacesBtn = new Button("Mine pladser");

        // Sets default button sizes
        final int WIDTH = 150;
        final int HEIGHT = 30;
        final int GAP = WIDTH + 20;
        final int Y_LAYOUT = 100;

        profileBtn.setLayoutX(scene.getWidth() - 50);
        profileBtn.setLayoutY(20);
        profileBtn.setPrefSize(35,HEIGHT);

        homeBtn.setLayoutY(Y_LAYOUT);
        homeBtn.setLayoutX(60);
        homeBtn.setPrefSize(WIDTH, HEIGHT);

        bookingsBtn.setLayoutX(homeBtn.getLayoutX() + GAP);
        bookingsBtn.setLayoutY(Y_LAYOUT);
        bookingsBtn.setPrefSize(WIDTH, HEIGHT);

        mySpacesBtn.setLayoutX(bookingsBtn.getLayoutX() + GAP);
        mySpacesBtn.setLayoutY(Y_LAYOUT);
        mySpacesBtn.setPrefSize(WIDTH, HEIGHT);

        anchorPane.getChildren().addAll(profileBtn, homeBtn, bookingsBtn, mySpacesBtn);
    }

    public Scene getScene()
    {
        return scene;
    }

    public AnchorPane getAnchorPane()
    {
        return anchorPane;
    }
}
