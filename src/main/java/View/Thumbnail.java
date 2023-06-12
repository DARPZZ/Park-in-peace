package View;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class Thumbnail extends VBox
{
    private ImageView imageView;
    private Label titleLabel;
    private Label priceLabel;

    public Thumbnail(Image image, String title)
    {
        imageView = new ImageView(image);
        titleLabel = new Label(title);
        setupLayout();
    }

    public Thumbnail(Image image, String title, String price)
    {
        imageView = new ImageView(image);
        titleLabel = new Label(title);
        priceLabel = new Label(price);
        setupLayout();
    }

    private void setupLayout()
    {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(5);
        imageView.setFitWidth(200);
        imageView.setFitHeight(150);

        Rectangle roundCorners = new Rectangle();
        roundCorners.setWidth(imageView.getFitWidth());
        roundCorners.setHeight(imageView.getFitHeight());
        roundCorners.setArcWidth(10);
        roundCorners.setArcHeight(10);
        imageView.setClip(roundCorners);

        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        this.getChildren().addAll(imageView, titleLabel);

        if (priceLabel != null)
        {
            this.getChildren().add(priceLabel);
        }
    }

    //region getter/setter
    public ImageView getImageView()
    {
        return imageView;
    }

    public void setImageView(ImageView imageView)
    {
        this.imageView = imageView;
    }

    public Label getTitleLabel()
    {
        return titleLabel;
    }

    public void setTitleLabel(Label titleLabel)
    {
        this.titleLabel = titleLabel;
    }
    //endregion
}