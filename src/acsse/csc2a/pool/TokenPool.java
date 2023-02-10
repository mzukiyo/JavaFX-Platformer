package acsse.csc2a.pool;

import java.util.ArrayList;
import acsse.csc2a.model.Token;

/**
 * TokenPool responsible for storing tokens
 * @author KM KIYO
 * @version PX
 *
 */

public class TokenPool 
{
    /**
     * Arrtibutes
     */

    private static TokenPool instance = null;
    private ArrayList<Token> reusables = null;

    /**
     * Constructor
     */
    private TokenPool()
    {
        reusables = new ArrayList<>();
    }

    
    /** 
     * @return TokenPool
     */
    public static TokenPool getInstance()
    {
        if(instance == null)
        {
            instance = new TokenPool();
        }

        return instance;
    }

    
    /** 
     * @param token integer of token in arraylist
     */
    // give token away
    public void removeFromTokenPool(int token)
    {
        if(reusables.isEmpty()) System.err.println("TokenPool is empty");
        else reusables.remove(token);
    }

    
    /** 
     * @param token token ojbect to add
     */
    // add token
    public void addToTokenPool(Token token)
    {
        if(token != null) reusables.add(token);
        else System.out.println("Token is null, add instantiated token...");
    }

    
    /** 
     * @return array
     */
    public ArrayList<Token> getTokens()
    {
        return reusables;
    }

    /**
     * clears tokenpool
     */
    public void purgeTokenPool()
    {
        reusables.clear();
    }
}
