package acsse.csc2a.interfaces;

import acsse.csc2a.model.Player;
import acsse.csc2a.model.Terrain;
import acsse.csc2a.model.Token;

/**
 * Interface for visitor classes
 * @author KM KIYO
 * @version PX
 */
public interface IDrawVisitor 
{
    /**
     * visits the player
     * @param player player
     */
    public void visit(Player player);

    /**
     * visits the terrain
     * @param terrain terrain
     */
    public void visit(Terrain terrain);
    
    /**
     * visits the token
     * @param token token
     */
    public void visit(Token token);
}
