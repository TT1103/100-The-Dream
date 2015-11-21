import greenfoot.*;

/**
 * Write a description of class StartButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartButton extends IntroElements
{
    private boolean mouseClicked = false; 
    private boolean objectRemoved = false; 
    private int speed = 2; 

    /**
     * Act - do whatever the StartButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) { 
            if (mouse.getX() >= 322 && mouse.getX() <= 487 && mouse.getY() <= 669 && 
            mouse.getY() >= 616) { 
                setImage (new GreenfootImage ("SBHover.png")) ; 
            } else { 
                setImage (new GreenfootImage ("StartButton.png")); 
            }
        }
        if (Greenfoot.mouseClicked (null) && (mouse.getX() >= 322 && mouse.getX() <= 487 && 
            mouse.getY() <= 669 && mouse.getY() >= 616)) { 
            setImage (new GreenfootImage ("SBClicked.png")); 
            mouseClicked = true; 
        }
        if (mouseClicked) { 
            speed += 1; 
            setLocation (getX() + speed, getY()) ; 
            if (getX() == 799) { 
                getWorld().removeObject (this); 
                objectRemoved = true; 
            }
        }
    }   

    public boolean checkMouseClick () { 
        if (mouseClicked) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkObjectRemoved () { 
        return objectRemoved; 
    }
}
