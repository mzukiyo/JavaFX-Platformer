package acsse.csc2a.gui.menu;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import acsse.csc2a.gui.game.GameScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;

/**
 * Class that manages menu layout
 * @author KM KIYO
 * @version PX
 */

public class MenuPane extends AnchorPane
{
    /**
     * Attributes
     */

    private ArrayList<MenuButton> menuButtons;
    private Stage window;
    private GameScene gameScene;
    private final static int MENU_BUTTON_X = 50; 
    private final static int MENU_BUTTON_Y = 150;
    private MenuSubScene sceneToHide;

    private MenuSubScene creditsSubScene;
    private MenuSubScene helpSubScene;
    private MenuSubScene scoreSubScene;

    /**
     * Parametised constructor
     * @param window main stage
     */
    public MenuPane(Stage window)
    {
        this.window = window;
        this.menuButtons = new ArrayList<>();

        createBackground();
        createButtons();
        createSubScenes();
    }

    
    /** 
     * @param gameScene the game scene
     */
    public void setScene(GameScene gameScene)
    {
        this.gameScene = gameScene;
    }
    
    // helper to create buttons
    private void createButtons()
    {
        createStartButton();
		createScoresButton();
		createHelpButton();
		createCreditsButton();
		createExitButton();   
    }

    /** 
     * @param subScene scene within a scene
     */
    private void showSubscene(MenuSubScene subScene)
    {
        if(sceneToHide != null)
        {
            sceneToHide.moveSubScene();
        }

        subScene.moveSubScene();
        sceneToHide = subScene;
    }

    // helper to create scenes
    private void createSubScenes()
    {
        creditsSubScene = new MenuSubScene("assets/references.png");
        helpSubScene = new MenuSubScene("assets/controls.png");
        scoreSubScene = new MenuSubScene("assets/grey_panel.png");
        this.getChildren().addAll(helpSubScene, creditsSubScene, scoreSubScene);
    }

    
    /** 
     * @param button menu button
     */
    private void addMenuButton(MenuButton button)
    {
        button.setLayoutX(MENU_BUTTON_X);
        button.setLayoutY(MENU_BUTTON_Y + menuButtons.size() * 75);
        menuButtons.add(button);
        this.getChildren().add(button);
        
    }

    // start button
    private void createStartButton()
    {
        MenuButton startButton = new MenuButton("PLAY");
        addMenuButton(startButton);

        startButton.setOnAction(e -> window.setScene(gameScene));
    }
    
    // score button
    private void createScoresButton()
    {
        MenuButton scoresButton = new MenuButton("SCORES");
        addMenuButton(scoresButton);

        scoresButton.setOnAction(e -> {
            showSubscene(scoreSubScene);
        });
    }

    private void createHelpButton()
    {
        MenuButton helpButton = new MenuButton("HELP");
        addMenuButton(helpButton);

        helpButton.setOnAction(e -> {
            showSubscene(helpSubScene);
        });
    }

    // credits button
    private void createCreditsButton()
    {
        MenuButton creditsButton = new MenuButton("CREDITS");
        addMenuButton(creditsButton);

        creditsButton.setOnAction(e -> {
            showSubscene(creditsSubScene);
        });
    }

    // exit button
    private void createExitButton()
    {
        MenuButton exitButton = new MenuButton("EXIT");
        addMenuButton(exitButton);

        exitButton.setOnAction(e -> {
            window.close();
        });
    }

    // background
    private void createBackground()
    {
        try(FileInputStream fStream = new FileInputStream("assets/menuBackground.png"))
        {
            Image backgroundImage = new Image(fStream);
            BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, 
            BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);

            this.setBackground(new Background(background));

        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
