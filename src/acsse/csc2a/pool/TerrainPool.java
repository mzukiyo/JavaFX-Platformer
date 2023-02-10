package acsse.csc2a.pool;

import java.util.ArrayList;
import acsse.csc2a.model.Terrain;

/**
 * TerrainPool responsible for storing terrain
 * @author KM KIYO
 * @version PX
 *
 */

public class TerrainPool 
{
    /**
     * Attributes
     */

    private static TerrainPool instance = null;
    private ArrayList<Terrain> reusables = null;

    /**
     * Constructor
     */
    private TerrainPool()
    {
        reusables = new ArrayList<>();
    }

    
    /** 
     * @return TerrainPool
     */
    public static TerrainPool getInstance()
    {
        if(instance == null)
        {
            instance = new TerrainPool();
        }

        return instance;
    }

    
    /** 
     * @param terrain integer of terrain in arraylist
     */
    // give token away
    public void removeFromTerrainPool(int terrain)
    {
        if(reusables.isEmpty()) System.err.println("TerrainPool is empty");
        else reusables.remove(terrain);
    }

    
    /** 
     * @param terrain terrain ojbect to add
     */
    // add token
    public void addToTerrainPool(Terrain terrain)
    {
        if(terrain != null) reusables.add(terrain);
        else System.out.println("Terrain is null, add instantiated token...");
    }

    
    /** 
     * @return array
     */
    public ArrayList<Terrain> getTerrain()
    {
        return reusables;
    }

    /**
     * clears tokenpool
     */
    public void purgeTerrainPool()
    {
        reusables.clear();
    }
}

