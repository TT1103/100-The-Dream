import greenfoot.*;

/**
 * In charge of changing to the next screen
 * 
 * @author Gary Yu 
 * @version January 13, 2016
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
     * Changes the next button image to grey, dark grey etc. depending on when the mouse hovers over it
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
        // Set of animations when first popping up 
        if (getY() >= 705 && !firstRiseCompleted && !secondRiseCompleted) { 
            speed += 12; 
            setLocation (getX(), getY() - speed); 
            if (getY() == 704) {
                firstRiseCompleted = true;
            }
        } 
        // Slowing down of button when it has popped over equilibrium 
        if (getY() < 705 && getY() > 555 && !secondRiseCompleted) { 
            speed -= 1;
            if (speed > 0) { 
                setLocation (getX(), getY() - speed) ; 
            }
            if (speed == 0) { 
                secondRiseCompleted = true;
            }
        }
        // Button will descend after it has reached to max height 
        if (getY() >= 555 && getY() <= 715 && secondRiseCompleted) {
            speed += 1; 
            if (speed >= 0 || getY() != 715) { 
                setLocation (getX(), getY() + speed); 
            } else if (getY() == 715) { 
                setLocation (getX(), 715) ; 
            }
        }
        // Once button in position, the next button will be in "full function" (Able to click to go to
        // next screen)
        if (getY() >= 718 && getY() <= 722) {
            buttonAppeared = true;
        }
    }
    
    /**
     * @return Whether the next button has appeared 
     */
    public boolean nextButtonHere() {
        return buttonAppeared;
    }
    
    /**
     * @return Whether the button has been clicked 
     */
    public boolean buttonClicked() {
        return mouseClicked;
    }
    
    /**
     * If button is not clicked, mouse clicked would be false 
     */
    public void buttonNotClicked() {
        mouseClicked = false;
    }
}
