package acsse.csc2a.model;
import java.awt.Rectangle;

import acsse.csc2a.interfaces.IDrawVisitor;
import acsse.csc2a.interfaces.IDrawable;

/**
 * Token class responsible for tokens
 * @author KM KIYO
 * @version PX
 *
 */

public class Token implements IDrawable
{
    /**
     * Attributes
     */

    private int xCoord;
    private int yCoord;
    private int width = 20;
    private int height = 20;
    int startX;
    int num;

    Rectangle collisionRect;

    /**
     * Parametised constructor
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Token(int x, int y)
    {
        this.xCoord = x;
        this.yCoord = y;

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
