package acsse.csc2a.gui.game;
import java.io.FileInputStream;
import java.io.IOException;
import acsse.csc2a.interfaces.IDrawVisitor;
import acsse.csc2a.model.Player;
import acsse.csc2a.model.Terrain;
import acsse.csc2a.model.Token;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * DrawGraphicsVisitor which defines concrete rendering methods
 * @author KM KIYO
 * @version PX
 */

public class DrawGraphicsVisitor implements IDrawVisitor
{
    /**
     * Attributes
     */

    private GraphicsContext gc;
    
    /** 
     * @param gc graphicsContext
     */
    public void setGraphicsContext(GraphicsContext gc)
    {
        this.gc = gc;
    }
    
    
    /** 
     * @param player player class
     */
    public void visit(Player player)
    {
        try(FileInputStream fStream = new FileInputStream("assets/player.png"))
        {
            Image tokenImage = new Image(fStream);
            gc.drawImage(tokenImage, player.getXCoord(), player.getYCoord());

        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /** 
     * @param terrain the platforms in game
     */
    public void visit(Terrain terrain)
    {
        gc.setFill(Color.GREENYELLOW);
        gc.fillRect(terrain.getXCoord(), terrain.getYCoord(), terrain.getWidth(), terrain.getHeight());
        gc.setFill(Color.BLACK);
        gc.strokeRect(terrain.getXCoord(), terrain.getYCoord(), terrain.getWidth(), terrain.getHeight());
    }
    
    /** 
     * @param token the items the player collects
     */
    public void visit(Token token)
    {
        gc.setFill(Color.GREEN);
        gc.fillRect(token.getXCoord(), token.getYCoord(), token.getWidth(), token.getHeight());        
    }
}
