package View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public abstract class Header
{
    public final Scene SCENE;
    public final AnchorPane AP;
    public final Button profileBtn;
    public final Button homeBtn;
    public final Button bookingsBtn;
    public final Button mySpacesBtn;
    public final double X_MARGIN = 60;

    public Header()
    {
        AP = new AnchorPane();
        AP.setOnMouseClicked(event -> AP.requestFocus());
        SCENE = new Scene(AP, 1280, 720);

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
        AnchorPane.setLeftAnchor(homeBtn, X_MARGIN);
        homeBtn.setPrefSize(WIDTH, HEIGHT);

        AnchorPane.setLeftAnchor(bookingsBtn, AnchorPane.getLeftAnchor(homeBtn) + GAP);
        bookingsBtn.setLayoutY(Y_LAYOUT);
        bookingsBtn.setPrefSize(WIDTH, HEIGHT);

        AnchorPane.setLeftAnchor(mySpacesBtn, AnchorPane.getLeftAnchor(bookingsBtn) + GAP);
        mySpacesBtn.setLayoutY(Y_LAYOUT);
        mySpacesBtn.setPrefSize(WIDTH, HEIGHT);

        AP.getChildren().addAll(profileBtn, homeBtn, bookingsBtn, mySpacesBtn);
    }

    //region getter/setter
    public double getYMargin()
    {
        return 130.00;
    }
    //endregion
}
