package acsse.csc2a.gui.menu;

import javafx.scene.Scene;

/**
 * Class that manages menuPane
 * @author KM KIYO
 * @version PX
 */

public class MenuScene extends Scene
{
    /**
     * Parametised constructor that calls super 
     * @param menu menupane
     */
    public MenuScene(MenuPane menu)
    {
        super(menu, 1080, 720);
    }

}
