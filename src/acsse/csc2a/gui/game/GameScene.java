package acsse.csc2a.gui.game;

import java.util.ArrayList;
import acsse.csc2a.model.Terrain;
import acsse.csc2a.model.Token;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameScene extends Scene
{
    ReportCanvas canvas = null;
    ArrayList<Terrain> terrain = null;
    ArrayList<Token> tokens = null;

    public GameScene(GamePane pane)
    {
        super(pane, 1080, 720);

        canvas = pane.getCanvas();
        terrain = canvas.terrain.getTerrain();
        tokens = canvas.tokens.getTokens();

        // arraylist used to store input
        ArrayList<String> input = new ArrayList<>();

        setOnKeyPressed(e -> 
        {
            String code = e.getCode().toString();

            if(!input.contains(code))
                input.add(code);
        });

        setOnKeyReleased(e -> 
        {
            String code = e.getCode().toString();
            input.remove(code);
        });

        // main game loop
        new AnimationTimer()
        {
            public void handle(long now)
            {
                // creates new terrain ahead of the player
                if(terrain.get(terrain.size() - 1).getXCoord() < 1300)
                {
                    canvas.offset += 700;
                    canvas.createTerrain(canvas.offset, pane.platformData);
                }

                pane.getPlayer().move(input);

                // sets the x-coordinates for the platforms and the camera
                for(Terrain t : terrain) t.set(canvas.cameraX);
                for(Token t : tokens) t.set(canvas.cameraX);

                canvas.clearScreen();

                // removes terrain that has left the screen - for performance
                for(int i = 0; i < terrain.size(); i++)
                {
                    if(terrain.get(i).getXCoord() < -800)
                    {
                        canvas.terrain.removeFromTerrainPool(i);
                    }
                }

                // removes tokens that has left the screen - for performance
                for(int i = 0; i < tokens.size(); i++)
                {
                    if(tokens.get(i).getXCoord() < -800)
                    {
                        canvas.tokens.removeFromTokenPool(i);
                    }
                }

                // continue drawing if player is yet to win or lose
                if(canvas.player.deathCount < 5 && canvas.player.counter < 150)
                {
                    canvas.draw();
                    canvas.gc.setFont(new Font("assets/kenvector_future.ttf", 40));
                    canvas.gc.setStroke(Color.BLACK);
                    canvas.gc.fillText("Tokens: " + canvas.player.counter, 50, 100);
                    canvas.gc.fillText("Lives: " + (5 - canvas.player.deathCount), 50, 150);
                }
                // if player loses all their lives
                else if(canvas.player.deathCount == 5)
                {
                    canvas.clearScreen();
                    pane.createBackground("assets/you-lose-bg.png");
                    canvas.gc.setFont(new Font("assets/kenvector_future.ttf", 36));
                    canvas.gc.setStroke(Color.RED);
                    canvas.gc.strokeText("Total Number of Tokens Collected: " + canvas.player.counter, 250, 500);
                    canvas.gc.strokeText("Click 'Options' above to try again...", 250, 550);
                    canvas.gc.setStroke(Color.BLACK);

                    input.clear();
                }
                // if player wins
                else if(canvas.player.counter == 150)
                {
                    canvas.clearScreen();
                    pane.createBackground("assets/you-win-bg.jpg");
                    canvas.gc.setFont(new Font("assets/kenvector_future.ttf", 36));
                    canvas.gc.setStroke(Color.GREEN);
                    canvas.gc.strokeText("Total Number of Tokens Collected: " + canvas.player.counter, 250, 500);
                    canvas.gc.strokeText("Click 'Options' above to see if you can win again :-)", 150, 550);
                    canvas.gc.setStroke(Color.BLACK);
                    
                    input.clear();
                }
            }
        }.start();  // starts the game loop at 60 frames per second
    }    
}
