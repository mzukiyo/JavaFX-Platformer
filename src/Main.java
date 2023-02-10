import acsse.csc2a.gui.game.GamePane;
import acsse.csc2a.gui.game.GameScene;
import acsse.csc2a.gui.menu.MenuPane;
import acsse.csc2a.gui.menu.MenuScene;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main Class to run JavaFX application
 * @author KM KIYO
 * @version PX
 */

public class Main extends Application
{
    /** 
     * @param args command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    /** 
     * @param primaryStage the main stage 
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        MenuPane menuRoot = new MenuPane(primaryStage);
        MenuScene menuScene = new MenuScene(menuRoot);
        
        GamePane gameRoot = new GamePane(primaryStage, menuScene);

        menuRoot.setScene(new GameScene(gameRoot));
    
        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Platformer Game");

        primaryStage.setResizable(true);
        primaryStage.show();
    }
}