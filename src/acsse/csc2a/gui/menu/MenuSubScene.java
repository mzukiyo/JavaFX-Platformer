package acsse.csc2a.gui.menu;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Class that manages sub scenes and their animations
 * @author KM KIYO
 * @version PX
 */

public class MenuSubScene extends SubScene 
{
    /**
     * Attributes
     */

    private boolean isHidden;
    private final int width = 500;
    private final int height = 400;

    /**
     * Parametised constructor
     * @param imagePath the path of image
     */
    public MenuSubScene(String imagePath)
    {
        super(new AnchorPane(), 800, 600);
        prefWidth(width);
        prefHeight(height);

        try(FileInputStream fStream = new FileInputStream(imagePath))
        {
            Image img = new Image(fStream, width, height, false, false);

            BackgroundImage image = new BackgroundImage(img, 
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

            AnchorPane root = (AnchorPane)this.getRoot();
            root.setBackground(new Background(image));

            this.isHidden = true;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        setLayoutX(1100);
        setLayoutY(141);
    }

    /**
     * moves the subscene using nice animation
     */
    public void moveSubScene()
    {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.6));
        transition.setNode(this);

        if(isHidden)
        {
            transition.setToX(-616);
            isHidden = false;
        } else
        {
            transition.setToX(0);
            isHidden = true;
        }

        transition.play();
    }

    /** 
     * @return AnchorPane (type of node used)
     */
    public AnchorPane getPane()
    {
        return (AnchorPane) this.getRoot();
    }
}
