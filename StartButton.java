import greenfoot.*;

/**
 * In charge of changing images for start button when hovered, clicked etc. 
 * 
 * @author Gary Yu 
 * @version January 13, 2016
 */
public class StartButton extends IntroElements
{
    private boolean mouseClicked = false; // Detects whether the mouse has been clicked or not  
    private boolean objectRemoved = false; // Detects whether object has been removed 
    private int speed = 2; 

    /**
     * Changes the images for start button when hovered, clicked etc. 
     */
    public void act() 
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) { 
            // If mouse is over the start button, the image should glow darker as 
            // an indicator 
            if (mouse.getX() >= 322 && mouse.getX() <= 487 && mouse.getY() <= 669 && 
            mouse.getY() >= 616) { 
                setImage (new GreenfootImage ("SBHover.png")) ; 
            } else { 
                setImage (new GreenfootImage ("StartButton.png")); 
            }
        }
        // If mouse is clicked, then image has a dark grey filter to symbolize it's clicked 
        if (Greenfoot.mouseClicked (this)) { 
            mouseClicked = true;
            setImage (new GreenfootImage ("SBClicked.png")); 
        }
        // If clicked, the start button will dissapear to the right 
        if (mouseClicked) {
            speed += 1; 
            setLocation (getX() + speed, getY()) ; 
            if (getX() == 799) { 
                getWorld().removeObject (this); 
                objectRemoved = true; 
            }
        }
    }   

    /**
     * @return Whether the mouse has clicked or not 
     */
    public boolean checkMouseClick () { 
        if (mouseClicked) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return Whether the start button has been removed or not 
     */
    public boolean checkObjectRemoved () { 
        return objectRemoved; 
    }
}
