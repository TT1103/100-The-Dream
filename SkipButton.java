import greenfoot.*;

/**
 * In charge of skipping to the level selection screen 
 * 
 * @author Gary Yu 
 * @version January 13,2016
 */
public class SkipButton extends IntroElements
{
    private boolean mouseClicked = false;

    /**
     * Changes the skip button to different colours to indicate whether user is 
     * hovering or clicking the button
     */
    public void act() 
    {
        // Changes images when mouse is hovering over or clicking on the button 
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) { 
            if (mouse.getX() >= 493 && mouse.getX() <= 627 && mouse.getY() <= 731 && 
            mouse.getY() >= 695) { 
                setImage (new GreenfootImage ("SkipButtonHover.png")) ; 
            } else { 
                setImage (new GreenfootImage ("SkipButton.png")); 
            }
        }
        if (Greenfoot.mouseClicked (null) && (mouse.getX() >= 493 && mouse.getX() <= 627 && mouse.getY() <= 731 && 
            mouse.getY() >= 695)) { 
            setImage (new GreenfootImage ("SkipButtonClicked.png")); 
            mouseClicked = true; 
        }
    }  
    
    /**
     * @return Whether this skip button has been clicked
     */
    public boolean buttonClicked() {
        return mouseClicked;
    }
}
