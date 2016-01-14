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
    GreenfootImage base ;
    
    public LevelSelectorButtons (String s) {
        setImage (s + ".png");
        fileName = s;
    }
    
    /**
     * Act - do whatever the LevelSelectorButtons wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
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
