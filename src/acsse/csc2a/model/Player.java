package acsse.csc2a.model;

import java.util.ArrayList;
import acsse.csc2a.gui.game.ReportCanvas;
import acsse.csc2a.interfaces.IDrawVisitor;
import acsse.csc2a.interfaces.IDrawable;
import acsse.csc2a.pool.TerrainPool;
import acsse.csc2a.pool.TokenPool;
import java.awt.Rectangle;

/**
 * Player class responsible for player movement and collisions
 * @author KM KIYO
 * @version PX
 *
 */
public class Player implements IDrawable
{
    /**
     * Attributes
     */
    private int xCoord;
    private int yCoord;
    private int width;
    private int height;
    private ReportCanvas canvas;
    private double xVelocity;
    private double yVelocity;
    TerrainPool terrain;
    TokenPool tokens;
    Rectangle collisionRect;

    /**
     * counter - for tokens
     */
    public int counter;

    /**
     * deathCount
     */
    public int deathCount;

    /**
     * Parametised constructor 
     * @param x x-coordinate of initial location
     * @param y y-coordinate of initial location
     */
    public Player(int x, int y)
    {
        this.xCoord = x;
        this.yCoord = y;

        width = 50;
        height = 63;
        collisionRect = new Rectangle(x, y, width, height);
    }

    
    /** 
     * @return int
     */
    public int getXCoord() 
    { 
        return xCoord; 
    }

    
    /** 
     * @param xCoord x-coordinate
     */
    public void setXCoord(int xCoord)
    {
        this.xCoord = xCoord;
    }

    
    /** 
     * @return int
     */
    public int getYCoord() 
    { 
        return yCoord; 
    }

    
    /** 
     * @param yCoord y-coordinate
     */
    public void setYCoord(int yCoord)
    {
        this.yCoord = yCoord;
    }

    
    /** 
     * @param canvas canvas
     */
    public void setCanvas(ReportCanvas canvas)
    {
        this.canvas = canvas;
    }

    
    /** 
     * @param xVelocity velocity of player in x-direction
     */
    public void setXVelocity(int xVelocity)
    {
        this.xVelocity = xVelocity;
    }

    
    /** 
     * @param yVelocity velocity of player in x-direction
     */
    public void setYVelocity(int yVelocity)
    {
        this.yVelocity = yVelocity;
    }

    
    /** 
     * @return int
     */
    public int getWidth() 
    { 
        return width; 
    }

    
    /** 
     * @return int
     */
    public int getHeight() 
    { 
        return height; 
    }

    
    /** 
     * @param terrain terrain
     */
    public void setTerrain(TerrainPool terrain)
    {
        this.terrain = terrain; 
    }

    
    /** 
     * @param tokens tokens
     */
    public void setTokens(TokenPool tokens)
    {
        this.tokens = tokens;
    }

    
    /** 
     * @param input keyboard input from user 
     */
    public void move(ArrayList<String> input)
    {
        final boolean SPACE = input.contains("SPACE");
        final boolean LEFT = input.contains("LEFT");
        final boolean RIGHT = input.contains("RIGHT");

        // left and right movement
        if((LEFT && RIGHT) || (!LEFT && !RIGHT))
           {
               xVelocity *= 0.9;
           }
        else if(LEFT && !RIGHT)
        {
            xVelocity--;
        }
        else if(RIGHT && !LEFT)
        {
            xVelocity++;
        }

        // prevents slow movement when keys aren't being pressed
        if(xVelocity > 0 && xVelocity < 0.75) xVelocity = 0;
        if(xVelocity < 0 && xVelocity > -0.75) xVelocity = 0;

        if(xVelocity > 7) xVelocity = 7;
        if(xVelocity < -7) xVelocity = -7;

        yVelocity += 0.7;

        // jumping movement
        if(SPACE)
        {
            collisionRect.y ++;
            for(Terrain w : terrain.getTerrain()) if(w.collisionRect.intersects(collisionRect)) yVelocity = -14;
            collisionRect.y--;
        }

        // horizontal collsions
        collisionRect.x += xVelocity;
        for(Terrain t : terrain.getTerrain())
        {
            if(collisionRect.intersects(t.collisionRect))
            {
                collisionRect.x -= xVelocity;
                while(!t.collisionRect.intersects(collisionRect)) collisionRect.x += Math.signum(xVelocity);
                collisionRect.x -= Math.signum(xVelocity);
                canvas.cameraX += xCoord - collisionRect.x;
                xVelocity = 0;
                collisionRect.x = xCoord;
            }
        }

        // Token Collision
        for(int i = 0; i < tokens.getTokens().size(); i++)
        {
            Token t = tokens.getTokens().get(i);

            if(collisionRect.intersects(t.collisionRect))
            {
                tokens.removeFromTokenPool(i);
                counter++;
            }
        }

        // vertical collsions
        collisionRect.y += yVelocity;
        for(Terrain t : terrain.getTerrain())
        {
            if(collisionRect.intersects(t.collisionRect))
            {
                collisionRect.y -= yVelocity;
                while(!t.collisionRect.intersects(collisionRect)) collisionRect.y += Math.signum(yVelocity);
                collisionRect.y -= Math.signum(yVelocity);
                yVelocity = 0;
                yCoord = collisionRect.y;
            }
        }

        canvas.cameraX -= xVelocity;
        yCoord += yVelocity;

        collisionRect.x = xCoord;
        collisionRect.y = yCoord;

        // if player fell off-screen - respawn the player (5 times is the max)
        if(yCoord > 800)
        {
            canvas.reset(canvas.platformData);
            deathCount++;
        }
    }

    
    /** 
     * @param visitor for rendering the player
     */
    public void accept(IDrawVisitor visitor)
    {
        visitor.visit(this);
    }
}