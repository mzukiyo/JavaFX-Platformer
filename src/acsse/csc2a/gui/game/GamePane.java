package acsse.csc2a.gui.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import acsse.csc2a.model.Player;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import acsse.csc2a.gui.menu.MenuScene;

/**
 * GamePane - layout used for game
 * @author KM KIYO
 * @version PX
 */

public class GamePane extends BorderPane
{
    /**
     * Attributes
     */
    private ReportCanvas canvas;
    private Player player;
    private MenuBar mBar;

    /**
     * platformData
     */
    public ArrayList<Integer> platformData;

    /**
     * MenuScene
     */
    public MenuScene mScene;

    /**
     * Parametised constructor
     * @param window the main stage
     * @param mScene menu scene
     */

    public GamePane(Stage window, MenuScene mScene)
    {
        platformData = new ArrayList<>();

        try(Scanner scanner = new Scanner(new File("data/PlatformData.txt")))
        {
            while(scanner.hasNextInt())
            {
                platformData.add(scanner.nextInt());
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }

        player = new Player(100, 200);
        createBackground("assets/gameBackground.jpg");
        canvas = new ReportCanvas(player, platformData);

        player.setCanvas(canvas);
        mBar = new MenuBar();

        Menu Options = new Menu("Options");

        mBar.getMenus().add(Options);
        
        MenuItem newGame = new MenuItem("Back To Menu");
        MenuItem saveGame = new MenuItem("Save Game");
        MenuItem exitGame = new MenuItem("Exit Game");

        newGame.setOnAction(e -> 
        {   
            window.setScene(mScene);
            canvas.reset(platformData);
            if(player.deathCount == 5)
            {
                // resets the counter with each new game

                player.deathCount = 0;
                player.counter = 0;
                createBackground("assets/gameBackground.jpg");
            }
        });

        exitGame.setOnAction(e -> Platform.exit());

        Options.getItems().addAll(newGame, saveGame, new SeparatorMenuItem(), exitGame);

        this.setTop(mBar);
        this.setCenter(canvas);
        this.setWidth(1280);
        this.setHeight(750);
    }

    
    /** 
     * @return Player
     */
    public Player getPlayer()
    {
        return player;
    }

    
    /** 
     * @return ReportCanvas
     */
    public ReportCanvas getCanvas()
    {
        return canvas;
    }

    
    /** 
     * @param file a string with the path 
     */
    public void createBackground(String file)
    {
        try(FileInputStream fStream = new FileInputStream(file))
        {
            Image backgroundImage = new Image(fStream);
            BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, 
            BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);

            setBackground(new Background(background));

        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
