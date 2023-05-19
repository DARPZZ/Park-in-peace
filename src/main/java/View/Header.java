package View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public abstract class Header
{
    private Scene scene;
    private AnchorPane ap;
    private Button profileBtn;
    private Button homeBtn;
    private Button bookingsBtn;
    private Button mySpacesBtn;

    private final double LIMIT = 130.0;
    private double xMargin = 60;

    public Header()
    {
        ap = new AnchorPane();
        ap.setOnMouseClicked(event -> ap.requestFocus());
        scene = new Scene(ap, 1280, 720);

        profileBtn = new Button("⚙");
        homeBtn = new Button("Hjem");
        bookingsBtn = new Button("Reservationer");
        mySpacesBtn = new Button("Mine pladser");

        // Sets default button sizes
        final int WIDTH = 150;
        final int HEIGHT = 30;
        final int GAP = WIDTH + 15;
        final int Y_LAYOUT = 80;

        AnchorPane.setRightAnchor(profileBtn, 60.0);
        profileBtn.setLayoutY(20);
        profileBtn.setPrefSize(35,HEIGHT);

        homeBtn.setLayoutY(Y_LAYOUT);
        AnchorPane.setLeftAnchor(homeBtn, xMargin);
        homeBtn.setPrefSize(WIDTH, HEIGHT);

        AnchorPane.setLeftAnchor(bookingsBtn, AnchorPane.getLeftAnchor(homeBtn) + GAP);
        bookingsBtn.setLayoutY(Y_LAYOUT);
        bookingsBtn.setPrefSize(WIDTH, HEIGHT);

        AnchorPane.setLeftAnchor(mySpacesBtn, AnchorPane.getLeftAnchor(bookingsBtn) + GAP);
        mySpacesBtn.setLayoutY(Y_LAYOUT);
        mySpacesBtn.setPrefSize(WIDTH, HEIGHT);

        ap.getChildren().addAll(profileBtn, homeBtn, bookingsBtn, mySpacesBtn);
    }

    //region getter/setter
    public Scene getScene()
    {
        return scene;
    }

    public AnchorPane getAp()
    {
        return ap;
    }

    public Button getProfileBtn()
    {
        return profileBtn;
    }

    public Button getHomeBtn()
    {
        return homeBtn;
    }

    public Button getBookingsBtn()
    {
        return bookingsBtn;
    }

    public Button getMySpacesBtn()
    {
        return mySpacesBtn;
    }

    public double getLIMIT()
    {
        return LIMIT;
    }

    public double getxMargin()
    {
        return xMargin;
    }
    //endregion
}
