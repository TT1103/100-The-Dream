import greenfoot.*;

/**
 * Write a description of class Level1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level1 extends LevelSelectorButtons
{
    /**
     * Act - do whatever the Level1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (!mouseOver && Greenfoot.mouseMoved(this)) {
            setImage("Level1Hover.png");
            mouseOver = true;
        }
        if (mouseOver && Greenfoot.mouseMoved(null) && ! Greenfoot.mouseMoved(this)) {
            setImage("Level1.png");
            mouseOver = false;
        }
    }
}
