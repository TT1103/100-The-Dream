import greenfoot.*;

/**
 * Write a description of class Level4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level4 extends LevelSelectorButtons
{
    /**
     * Act - do whatever the Level4 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (!mouseOver && Greenfoot.mouseMoved(this)) {
            setImage("Level4Hover.png");
            mouseOver = true;
        }
        if (mouseOver && Greenfoot.mouseMoved(null) && ! Greenfoot.mouseMoved(this)) {
            setImage("Level4.png");
            mouseOver = false;
        }
    }    
}
