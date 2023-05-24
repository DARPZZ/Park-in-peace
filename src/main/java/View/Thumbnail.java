package View;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Thumbnail extends VBox
{
    private ImageView imageView;
    private Label titleLabel;

    public Thumbnail(Image image, String title)
    {
        imageView = new ImageView(image);
        titleLabel = new Label(title);
        setupLayout();
    }

    private void setupLayout()
    {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        imageView.setFitWidth(205);
        imageView.setFitHeight(150);
        imageView.setStyle("-fx-background-radius: 20; -fx-border-radius: 20; -fx-background-color: black;");
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16");
        this.getChildren().addAll(imageView, titleLabel);
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