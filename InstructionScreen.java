import greenfoot.*;

/**
 * Write a description of class InstructionScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructionScreen extends World
{
    private GifImage myWorldBackground = new GifImage ("MyWorld.gif");
    private StartButton start = new StartButton ();
    private SkipButton skip = new SkipButton ();
    private NextButton next = new NextButton ();
    private BackButton back = new BackButton ();
    private boolean popInstructions = false;
    private boolean delayedInstructions = false;
    private int i = 0; // Used for changing instruction screens

    // Arrays to store instruction screen images
    String images[] = new String [2];

    /**
     * Constructor for objects of class InstructionScreen.
     * 
     */
    public InstructionScreen()
    {    
        // Create a new world with 800x800 cells with a cell size of 1x1 pixels.
        super(800, 800, 1);  

        // Adding the necessary start button
        addObject (start, 405, 643); 

        for (int i = 0 ; i < images.length ; i ++) {
            images[i] = "Instruction" + (i) + ".gif";
        }
    }

    /**
     * Helps with the screen transitions 
     */
    public void act () {
        // NOTE: Get coordinate method is temporary!
        MouseInfo mouse = Greenfoot.getMouseInfo ();
        if (Greenfoot.mouseClicked(null)) {
            System.out.println (mouse.getX() + "," + mouse.getY());
        }

        if (start.checkObjectRemoved()) {
            if (!delayedInstructions) {
                myWorldBackground = new GifImage (images[0]);
                delayedInstructions = true;
            }
            if (!addNextIcon()) {
                addNextIcon();
            }
            if (!addSkipIcon()) {
                addSkipIcon();
            }
            popInstructions = true;
        }

        // If skip button is clicked, game will auto-start with no instructions
        if (skip.buttonClicked()) {
            Greenfoot.setWorld (new LevelSelector());
        }

        changeInstructionScreens();
        
        setBackground (myWorldBackground.getCurrentImage());
    }

    public StartButton getStartButton () { 
        return start ; 
    }

    public boolean poppedInstructions () {
        return popInstructions;
    }

    public boolean addNextIcon () {
        if (popInstructions) {
            addObject (next, 704, 705);
            return true;
        } else {
            return false;
        }
    }

    public boolean addSkipIcon () {
        if (next.nextButtonHere()) {
            addObject (skip, 560, 720);
            return true;
        } else {
            return false;
        }
    }

    public void changeInstructionScreens () {
        if (next.buttonClicked()) {
            if (i <= images.length-2) {
                myWorldBackground = new GifImage (images[++i]);
                if (i == 1) {
                    addObject (back, 79, 705);
                } 
            } else {
                Greenfoot.setWorld (new LevelSelector());
            }
            next.buttonNotClicked();
        }
        if (back.buttonClicked()) {
            myWorldBackground = new GifImage (images[--i]);
            if (i == 0) {
                back.buttonNotClicked();
                removeObject (back);
            }
            back.buttonNotClicked();
        }
    }
}
