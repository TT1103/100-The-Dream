import greenfoot.*;

/**
 * Write a description of class NextButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NextButton extends IntroElements
{
    // Button pops up over equilibrium point then drops back down 
    private boolean mouseClicked = false;
    private boolean firstRiseCompleted = false;
    private boolean secondRiseCompleted = false;
    private boolean buttonAppeared = false;
    private int speed = 2;

    /**
     * Act - do whatever the NextButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        InstructionScreen w = (InstructionScreen) getWorld();
        if (w.poppedInstructions()) {
            popUpAnimation(); // Does the pop up animation
        }

        // Changes images when mouse is hovering over or clicking on the button 
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) { 
            if (mouse.getX() >= 673 && mouse.getX() <= 733 && mouse.getY() <= 749 && 
            mouse.getY() >= 686) { 
                setImage (new GreenfootImage ("NextButtonHover.png")) ; 
            } else { 
                setImage (new GreenfootImage ("NextButton.png")); 
            }
        }
        if (Greenfoot.mouseClicked (null) && (mouse.getX() >= 673 && mouse.getX() <= 733 && mouse.getY() <= 749 && 
            mouse.getY() >= 686)) { 
            setImage (new GreenfootImage ("NextButtonClicked.png")); 
            mouseClicked = true; 
        }
    }    

    /**
     * Animations required to pop button up and down 
     */
    public void popUpAnimation() {
        // 704, 720
        if (getY() >= 705 && !firstRiseCompleted && !secondRiseCompleted) { 
            speed += 12; 
            setLocation (getX(), getY() - speed); 
            if (getY() == 704) {
                firstRiseCompleted = true;
            }
        } 
        if (getY() < 705 && getY() > 555 && !secondRiseCompleted) { 
            speed -= 1;
            if (speed > 0) { 
                setLocation (getX(), getY() - speed) ; 
            }
            if (speed == 0) { 
                secondRiseCompleted = true;
            }
        }
        if (getY() >= 555 && getY() <= 715 && secondRiseCompleted) {
            speed += 1; 
            if (speed >= 0 || getY() != 715) { 
                setLocation (getX(), getY() + speed); 
            } else if (getY() == 715) { 
                setLocation (getX(), 715) ; 
            }
        }
        if (getY() >= 718 && getY() <= 722) {
            buttonAppeared = true;
        }
    }
    
    public boolean nextButtonHere() {
        return buttonAppeared;
    }
    
    public boolean buttonClicked() {
        return mouseClicked;
    }
    
    public void buttonNotClicked() {
        mouseClicked = false;
    }
}
