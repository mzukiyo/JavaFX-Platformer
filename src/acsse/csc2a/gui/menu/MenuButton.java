package acsse.csc2a.gui.menu;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

/**
 * Class for menu buttons
 * @author KM KIYO
 * @version PX
 */

public class MenuButton extends Button
{
	/**
	 * Attributes
	 */

    private final String FONT_PATH = "assets/kenvector_future.ttf";
	private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-text-fill: white;";
	private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-text-fill: white;";

	/**
	 * Parametised constructor
	 * @param text text used on button
	 */
    public MenuButton(String text) 
    {
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(49);
		setStyle(BUTTON_FREE_STYLE);
		initializeButtonListeners();	
	}

	// setter
	private void setButtonFont() 
    {
		try(FileInputStream fStream = new FileInputStream(FONT_PATH))
        {
            setFont(Font.loadFont(fStream, 23));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
	}
	
	// setter
	private void setButtonPressedStyle() 
    {
		setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(45);
		setLayoutY(getLayoutY() + 4);	
	}

	// setter
	private void setButtonReleasedStyle() 
    {
		setStyle(BUTTON_FREE_STYLE);
		setPrefHeight(45);
		setLayoutY(getLayoutY() - 4);
		
	}

	/**
	 * controls event handlers
	 */
	private void initializeButtonListeners() 
    {
		setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) 
                {
					setButtonPressedStyle();
				}
			}
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>() 
        {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) 
                {
					setButtonReleasedStyle();
				}
			}
		});
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) 
            {
				setEffect(new DropShadow());	
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) 
            {
				setEffect(null);	
			}
		});	
	}
}
