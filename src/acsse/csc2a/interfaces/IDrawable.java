package acsse.csc2a.interfaces;

/**
 * Interface for drawable classes
 * @author KM KIYO
 * @version PX
 */

public interface IDrawable 
{
    /**
     * used to render onto screen
     * @param visitor visitor
     */
    public void accept(IDrawVisitor visitor);    
}
