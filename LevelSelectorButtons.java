import greenfoot.*;

/**
 * Used to model the buttons used for level selection.
 * 
 * @author Gary Yu
 * @version January 14, 2016
 */
public class LevelSelectorButtons extends IntroElements
{  
    private boolean mouseOver = false;
    private String fileName ;
    GreenfootImage base ; // Base image to refer back to once hover image needs no longer to be shown 
    
    /**
     * Since there is only one class for all level selector buttons, this requires every 
     * new level selector button made to include the image name
     * 
     * @param s Filename (without extension such as .png) of the image
     */
    public LevelSelectorButtons (String s) {
        setImage (s + ".png");
        fileName = s;
    }
    
    /**
     * If the user hovers over the button, the colour would change to indicate user is about to 
     * select that level
     */
    public void act() 
    {
        if (!mouseOver && Greenfoot.mouseMoved(this)) {
            base = getImage();
            setImage (fileName + "Hover.png");
            mouseOver = true;
        }
        if (mouseOver && Greenfoot.mouseMoved(null) && ! Greenfoot.mouseMoved(this)) {
            setImage(base);
            mouseOver = false;
        }
    }    
}
