package acsse.csc2a.model;
import java.awt.Rectangle;

import acsse.csc2a.interfaces.IDrawVisitor;
import acsse.csc2a.interfaces.IDrawable;

/**
 * Terrain class responsible for platforns
 * @author KM KIYO
 * @version PX
 *
 */

public class Terrain implements IDrawable
{
    /**
     * Attributes
     */

    private int xCoord;
    private int yCoord;
    private int width;
    private int height;
    int startX;
    Rectangle collisionRect;

    /**
     * Parametised constructor
     * @param x x-coordinate
     * @param y x-coordinate
     * @param width width of platform
     * @param height height of platform
     */
    public Terrain(int x, int y, int width, int height)
    {
        this.xCoord = x;
        this.yCoord = y;
        this.width = width;
        this.height = height;

        // controls camera
        startX = x;

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
     * @return int
     */
    public int getYCoord() 
    { 
        return yCoord; 
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
     * @param cameraX camera in x-direction
     * @return int
     */
    public int set(int cameraX)
    {
        xCoord = startX + cameraX;
        collisionRect.x = xCoord;

        return xCoord;
    }
    
    /** 
     * @param visitor for rendering the player
     */
    public void accept(IDrawVisitor visitor)
    {
        visitor.visit(this);
    }
}
