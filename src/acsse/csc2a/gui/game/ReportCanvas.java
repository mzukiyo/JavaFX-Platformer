package acsse.csc2a.gui.game;

import java.util.Random;
import acsse.csc2a.model.Player;
import acsse.csc2a.model.Terrain;
import acsse.csc2a.model.Token;
import acsse.csc2a.pool.TerrainPool;
import acsse.csc2a.pool.TokenPool;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The ReportCanvas class extends {@link Canvas} class, and this class sets up the canvas
 * @author KM KIYO
 * @version PX
 *
 */

public class ReportCanvas extends Canvas
{
    private DrawGraphicsVisitor visitor;

    /**
     * player in game
     */
    public Player player;

    /**
     * terrainpool
     */
    public TerrainPool terrain;

    /**
     * tokenpool
     */
    public TokenPool tokens;

    /**
     * platformData
     */
    public ArrayList<Integer> platformData;

    /**
     * grahpics context - for canvas
     */
    public GraphicsContext gc = getGraphicsContext2D();

    /**
     * camera of x-coordinate
     */
    public int cameraX;

    /**
     * determines when to draw terrain and tokens
     */
    public int offset;


    /**
     * Parametised constructor 
     * @param player player
     * @param platformData data for platform construction
     */
    public ReportCanvas(Player player, ArrayList<Integer> platformData)
    {
        visitor = new DrawGraphicsVisitor();
        this.player = player;
        terrain = TerrainPool.getInstance();
        tokens = TokenPool.getInstance();
        this.platformData = platformData;

        reset(platformData);
        player.setTerrain(terrain);
        player.setTokens(tokens);

        setWidth(1080);
        setHeight(720);
    }

    /**
     * draws on the canvas
     */
    public void draw()
    {
        redrawCanvas();
    }
    
    /** 
     * @param offset used to set camera
     * @param platformData platform data
     */
    public void createTerrain(int offset, ArrayList<Integer> platformData)
    {
        int size = 50;
        Random generate = new Random();

        int index = generate.nextInt(1, 14+1);
        int random;
        
        // There are 14 different platforms which will be randomly generated as the player moves in the game
        if(index == 1)
        {

            for(int i = 0; i < 14; i++)
            {
                random = generate.nextInt(0, 100);
                terrain.addToTerrainPool(new Terrain(offset + i*50, platformData.get(0), size, size));
                if(random >= 50) tokens.addToTokenPool(new Token(offset + i*50, platformData.get(0)-30));
            }
        }
        
        else if(index == 2)
        {   
            for(int i = 0; i < 5; i++)
            {
                random = generate.nextInt(0, 100);
                terrain.addToTerrainPool(new Terrain(offset + i*50, platformData.get(1), size, size));
                if(random >= 50) tokens.addToTokenPool(new Token(offset + i*50, platformData.get(1)-30));
            }
            
            Collections.addAll(terrain.getTerrain(), 
            new Terrain(offset + 500, platformData.get(2), size, size),
            new Terrain(offset + 550, platformData.get(3), size, size),
            new Terrain(offset + 600, platformData.get(4), size, size),
            new Terrain(offset + 650, platformData.get(5), size, size),
            new Terrain(offset + 700, platformData.get(6), size, size),
            new Terrain(offset + 750, platformData.get(7), size, size));

        }

        else if(index == 3)
        {   
            random = generate.nextInt(0, 100);

            for(int i = 0; i < 14; i++) terrain.addToTerrainPool(new Terrain(offset + i*50, platformData.get(8), size, size));
            if(random >= 50) tokens.addToTokenPool(new Token(offset + 67, 520)); // checked

            for(int i = 0; i < 12; i++) terrain.addToTerrainPool(new Terrain(offset + 50 + i*50, platformData.get(9), size, size));
            if(random >= 50) tokens.addToTokenPool(new Token(offset + 115, 470));

            for(int i = 0; i < 10; i++) terrain.addToTerrainPool(new Terrain(offset + 100 + i*50, platformData.get(10), size, size));
            if(random >= 50) tokens.addToTokenPool(new Token(offset + 163, 420));

            for(int i = 0; i < 8; i++) terrain.addToTerrainPool(new Terrain(offset + 150 + i*50, platformData.get(11), size, size));
            
            for(int i = 0; i < 6; i++) 
            {
                random = generate.nextInt(0, 100);
                terrain.addToTerrainPool(new Terrain(offset + 200 + i*50, platformData.get(12), size, size));
                if(random >= 50) tokens.addToTokenPool(new Token(offset + 211 + 48*i, 370));
            }   
        }

        else if(index == 4)
        {
            random = generate.nextInt(0, 100);

            if(random >= 50) tokens.addToTokenPool(new Token(offset - 240, 420));
            for(int i = 0; i < 5; i++) terrain.addToTerrainPool(new Terrain(offset + i*50, platformData.get(13), size, size));
            if(random >= 50) tokens.addToTokenPool(new Token(offset + 210, 420));
            for(int i = 0; i < 5; i++) terrain.addToTerrainPool(new Terrain(offset + 450 + i*50, platformData.get(14), size, size));

            Collections.addAll(terrain.getTerrain(), 
            new Terrain(offset + 150, platformData.get(15), size, size),
            new Terrain(offset + 200, platformData.get(16), size, size),
            new Terrain(offset + 200, platformData.get(17), size, size),
            new Terrain(offset + 200, platformData.get(18), size, size),
            new Terrain(offset + 500, platformData.get(19), size, size),
            new Terrain(offset + 450, platformData.get(20), size, size),
            new Terrain(offset + 450, platformData.get(21), size, size),
            new Terrain(offset + 450, platformData.get(22), size, size));
        }

        else if(index == 5)
        {
            random = generate.nextInt(0, 100);

            if(random >= 50) tokens.addToTokenPool(new Token(offset + 163, 420));
            if(random >= 50) tokens.addToTokenPool(new Token(offset + 210, 420));
            for(int i = 0; i < 5; i++) 
            {
                random = generate.nextInt(0, 100);
                terrain.addToTerrainPool(new Terrain(offset + i*50, platformData.get(23), size, size));    
                if(random >= 50) tokens.addToTokenPool(new Token(offset - 183 + i*50, 560));
            }
            for(int i = 0; i < 4; i++) terrain.addToTerrainPool(new Terrain(offset + 50 + i*50, platformData.get(24), size, size));
            for(int i = 0; i < 3; i++) terrain.addToTerrainPool(new Terrain(offset + 100 + i*50, platformData.get(25), size, size));
            for(int i = 0; i < 2; i++) terrain.addToTerrainPool(new Terrain(offset + 150 + i*50, platformData.get(26), size, size));
            for(int i = 0; i < 4; i++) terrain.addToTerrainPool(new Terrain(offset + 500 + i*50, platformData.get(27), size, size));
        }

        else if(index == 6)
        {
            random = generate.nextInt(0, 100);

            for(int i = 0; i < 5; i++)
            {
                random = generate.nextInt(0, 100);
                terrain.addToTerrainPool(new Terrain(offset + i*50, platformData.get(28), size, size));
            }

            for(int i = 0; i < 3; i++) 
            {
                random = generate.nextInt(0, 100);
                terrain.addToTerrainPool(new Terrain(offset + 100 + i*50, platformData.get(29), size, size));
            }

            for(int i = 0; i < 5; i++)
            {
                random = generate.nextInt(0, 100);
                terrain.addToTerrainPool(new Terrain(offset + 250 + i*50, platformData.get(30), size, size));
            }

            for(int i = 0; i < 7; i++) 
            {
                random = generate.nextInt(0, 100);
                terrain.addToTerrainPool(new Terrain(offset + 350 + 50 + i*50, platformData.get(31), size, size));
                if(i != 2)
                {
                    if(random >= 50) tokens.addToTokenPool(new Token(offset + 33 + i*50, 560));
                }
            }

            for(int i = 0; i < 2; i++) terrain.addToTerrainPool(new Terrain(offset + 550, platformData.get(32) + i*50, size, size));
            if(random >= 50) tokens.addToTokenPool(new Token(offset + 133, 460));

        }
        else if(index == 7)
        {
            for(int i = 0; i < 14; i++) terrain.addToTerrainPool(new Terrain(offset + i*50, platformData.get(33), size, size));
            for(int i = 0; i < 7; i++)
            {
                random = generate.nextInt(0, 100);
                terrain.addToTerrainPool(new Terrain(offset + 200 + i*50, platformData.get(34), size, size));
                if(random >= 50) tokens.addToTokenPool(new Token(offset + 200 + i*50, 510));
            }
        }

        else if(index == 8)
        {
            Collections.addAll(terrain.getTerrain(), 
            new Terrain(offset, platformData.get(35), size, size),
            new Terrain(offset + 50, platformData.get(36), size, size),

            new Terrain(offset + 150, platformData.get(37), size, size),
            new Terrain(offset + 200, platformData.get(38), size, size),
            
            new Terrain(offset + 300, platformData.get(39), size, size),
            new Terrain(offset + 350, platformData.get(40), size, size));

            {
                random = generate.nextInt(0, 100);
                if(random > 50) tokens.addToTokenPool(new Token(offset, platformData.get(35) - 30));
                if(random < 50) tokens.addToTokenPool(new Token(offset + 50, platformData.get(35) - 30));
            }

            {
                random = generate.nextInt(0, 100);
                if(random > 50) tokens.addToTokenPool(new Token(offset + 150, platformData.get(37) - 30));
                if(random < 50) tokens.addToTokenPool(new Token(offset + 220, platformData.get(37) - 30));
            }

            {
                random = generate.nextInt(0, 100);
                if(random > 50)  tokens.addToTokenPool(new Token(offset + 300, platformData.get(39) - 30));
                if(random < 50)  tokens.addToTokenPool(new Token(offset + 350, platformData.get(39) - 30));
            }
        }

        else if(index == 9)
        {
            for(int i = 0; i < 14; i++)
            {
                random = generate.nextInt(0, 100);
                terrain.addToTerrainPool(new Terrain(offset + i*50, platformData.get(41), size, size));
                if(random > 90) tokens.addToTokenPool(new Token(offset + i*50, platformData.get(41) - 30));
            }
            
            for(int i = 0; i < 7; i++)
            {
                random = generate.nextInt(0, 100);
                terrain.addToTerrainPool(new Terrain(offset + 200 + i*50, platformData.get(42), size, size));
                if(random > 50) tokens.addToTokenPool(new Token(offset + 200 + i*50, platformData.get(42) - 30));
            }
        }

        else if(index == 10)
        {
            Collections.addAll(terrain.getTerrain(), 
            new Terrain(offset, platformData.get(43), size, size),
            new Terrain(offset + 50, platformData.get(44), size, size),

            new Terrain(offset + 150, platformData.get(45), size, size),
            new Terrain(offset + 200, platformData.get(46), size, size),
            
            new Terrain(offset + 300, platformData.get(47), size, size),
            new Terrain(offset + 350, platformData.get(48), size, size),

            new Terrain(offset + 450, platformData.get(49), size, size),
            new Terrain(offset + 500, platformData.get(50), size, size),

            new Terrain(offset + 600, platformData.get(51), size, size),
            new Terrain(offset + 650, platformData.get(52), size, size));

            {
                random = generate.nextInt(0, 100);
                if(random >= 50) tokens.addToTokenPool(new Token(offset, platformData.get(43) - 30));
                if(random >= 50) tokens.addToTokenPool(new Token(offset + 50, platformData.get(43) - 30));
            }

            {
                random = generate.nextInt(0, 100);
                if(random >= 50) tokens.addToTokenPool(new Token(offset + 150, platformData.get(45) - 30));
                if(random >= 50) tokens.addToTokenPool(new Token(offset + 200, platformData.get(45) - 30));
            }

            {
                random = generate.nextInt(0, 100);
                if(random >= 50) tokens.addToTokenPool(new Token(offset + 300, platformData.get(47) - 30));
                if(random >= 50) tokens.addToTokenPool(new Token(offset + 350, platformData.get(47) - 30));
            }

            {
                random = generate.nextInt(0, 100);
                if(random >= 50) tokens.addToTokenPool(new Token(offset + 450, platformData.get(49) - 30));
                if(random >= 50) tokens.addToTokenPool(new Token(offset + 500, platformData.get(49) - 30));
            }

            {
                random = generate.nextInt(0, 100);
                if(random >= 50) tokens.addToTokenPool(new Token(offset + 600, platformData.get(51) - 30));
                if(random >= 50) tokens.addToTokenPool(new Token(offset + 650, platformData.get(51) - 30));
            }
        }

        else if(index == 11)
        {
            Collections.addAll(terrain.getTerrain(), 
            new Terrain(offset, platformData.get(53), size, size),
            new Terrain(offset + 150, platformData.get(54), size, size),
            new Terrain(offset + 300, platformData.get(55), size, size),
            new Terrain(offset + 450, platformData.get(56), size, size),
            new Terrain(offset + 600, platformData.get(57), size, size));

            {
                random = generate.nextInt(0, 100);
                if(random >= 50) tokens.addToTokenPool(new Token(offset, platformData.get(53) - 30));
                if(random >= 50) tokens.addToTokenPool(new Token(offset + 300, platformData.get(55) - 30));
                if(random >= 50) tokens.addToTokenPool(new Token(offset + 150, platformData.get(54) - 30));
            }

            
            {
                random = generate.nextInt(0, 100);
                if(random >= 50) tokens.addToTokenPool(new Token(offset + 450, platformData.get(56) - 30));
                if(random >= 50) tokens.addToTokenPool(new Token(offset + 600, platformData.get(57) - 30));
            }

        }

        else if(index == 12)
        {
            Collections.addAll(terrain.getTerrain(), 
            new Terrain(offset, platformData.get(58), size, size),
            new Terrain(offset + 100, platformData.get(59), size, size),
            new Terrain(offset + 200, platformData.get(60), size, size),
            new Terrain(offset + 300, platformData.get(61), size, size),
            new Terrain(offset + 400, platformData.get(62), size, size));

            
            {
                random = generate.nextInt(0, 100);
                if(random >= 90) tokens.addToTokenPool(new Token(offset, platformData.get(58) - 30));
                if(random >= 90) tokens.addToTokenPool(new Token(offset + 100, platformData.get(59) - 30));
                if(random >= 90) tokens.addToTokenPool(new Token(offset + 200, platformData.get(60) - 30));
            }
            
            {
                random = generate.nextInt(0, 100);
                if(random >= 90) tokens.addToTokenPool(new Token(offset + 400, platformData.get(62) - 30));
                if(random >= 90) tokens.addToTokenPool(new Token(offset + 300, platformData.get(61) - 30));
            }
        }

        else if(index == 13)
        {
            Collections.addAll(terrain.getTerrain(), 
            new Terrain(offset, platformData.get(63), size, size),
            new Terrain(offset + 50, platformData.get(64), size, size),

            new Terrain(offset + 150, platformData.get(65), size, size),
            new Terrain(offset + 200, platformData.get(66), size, size),
            
            new Terrain(offset + 300, platformData.get(67), size, size),
            new Terrain(offset + 350, platformData.get(68), size, size),

            new Terrain(offset + 450, platformData.get(69), size, size),
            new Terrain(offset + 500, platformData.get(70), size, size),

            new Terrain(offset + 600, platformData.get(71), size, size),
            new Terrain(offset + 650, platformData.get(72), size, size));

            random = generate.nextInt(0, 100);

            if(random >= 50) tokens.addToTokenPool(new Token(offset, platformData.get(63) - 30));
            if(random >= 50) tokens.addToTokenPool(new Token(offset + 50, platformData.get(63) - 30));

            if(random >= 50) tokens.addToTokenPool(new Token(offset + 150, platformData.get(65) - 30));
            if(random >= 50) tokens.addToTokenPool(new Token(offset + 200, platformData.get(65) - 30));

            if(random >= 50) tokens.addToTokenPool(new Token(offset + 300, platformData.get(67) - 30));
            if(random >= 50) tokens.addToTokenPool(new Token(offset + 350, platformData.get(67) - 30));

            if(random >= 50) tokens.addToTokenPool(new Token(offset + 450, platformData.get(69) - 30));
            if(random >= 50) tokens.addToTokenPool(new Token(offset + 500, platformData.get(69) - 30));

            if(random >= 50) tokens.addToTokenPool(new Token(offset + 600, platformData.get(71) - 30));
            if(random >= 50) tokens.addToTokenPool(new Token(offset + 650, platformData.get(71) - 30));
        }

        else if(index == 14)
        {
            for(int i = 0; i < 5; i++) terrain.addToTerrainPool(new Terrain(offset + i*50, platformData.get(73), size, size));
            for(int i = 0; i < 3; i++) terrain.addToTerrainPool(new Terrain(offset + 100 + i*50, platformData.get(74), size, size));

            for(int i = 0; i < 3; i++) terrain.addToTerrainPool(new Terrain(offset + 350 + i*50, platformData.get(75), size, size));
            for(int i = 0; i < 3; i++) terrain.addToTerrainPool(new Terrain(offset + 550, platformData.get(76) + i*50, size, size));
        }
    }

    /**
     * renders the terrain and tokens on the canvas by accepting them
     */

    private void drawTerrain()
    {
        for(Token t : tokens.getTokens())
        {
            t.accept(visitor);
        }

        for(Terrain t : terrain.getTerrain())
        {
            t.accept(visitor);
        }
    }

    // redraws the canvas
    private void redrawCanvas()
    {
        gc = getGraphicsContext2D();
        visitor.setGraphicsContext(gc);        
        player.accept(visitor);
        drawTerrain();
    }

    /**
     * clears the screen
     */
    public void clearScreen()
    {
        gc.clearRect(0, 0, 1080, 720);
    }

    
    /** 
     * resets the game
     * @param platformData platform data
     */
    public void reset(ArrayList<Integer> platformData)
    {
        player.setXCoord(200);
        player.setYCoord(150);
        cameraX = 150;

        player.setXVelocity(0);
        player.setYVelocity(0);
        
        terrain.purgeTerrainPool();
        tokens.purgeTokenPool();

        offset = -150;
        createTerrain(offset, platformData);
    }
}
