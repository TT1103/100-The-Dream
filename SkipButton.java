import greenfoot.*;

/**
 * Write a description of class SkipButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SkipButton extends IntroElements
{
    private boolean mouseClicked = false;

    /**
     * Act - do whatever the SkipButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
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
    
    public boolean buttonClicked() {
        return mouseClicked;
    }
}
