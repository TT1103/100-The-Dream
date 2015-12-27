import greenfoot.*;

/**
 * Write a description of class TitleBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleBar extends IntroElements
{
    private boolean firstImageSlid = false;
    private boolean allImagesSlid = false;
    private int speed = 2; 

    /**
     * Act - do whatever the TitleBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    { 
        InstructionScreen w = (InstructionScreen) getWorld (); 
        StartButton startButton = w.getStartButton (); 
        if (startButton.checkMouseClick()) { 
            slide();
        }
    }    

    public void slide() { 
        setImage (new GreenfootImage ("TitleBarBlank.png")); 
        if (getY () > 1 && !firstImageSlid) {  
            speed += 1; 
            setLocation (getX(), getY() - speed) ; 
        } else {
            firstImageSlid = true;
            setImage (new GreenfootImage ("TitleBarBlank2.png"));
            setLocation (getX(), 15);
            if (getY() <= 20) {   
                setLocation (getX(), getY() - speed);
                if (getY() <= 1) {
                    getWorld().removeObject (this);
                    allImagesSlid = true;
                }
            }
        }
    }  
    
    public boolean titleBarGone () {
        return allImagesSlid;
    }
}
