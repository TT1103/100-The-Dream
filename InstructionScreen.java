import greenfoot.*;

/**
 * Write a description of class InstructionScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructionScreen extends World
{
    private TitleBar title = new TitleBar (); 
    private StartButton start = new StartButton ();
    private SkipButton skip = new SkipButton ();
    private NextButton next = new NextButton ();
    private BackButton back = new BackButton ();
    private boolean popInstructions = false;
    private boolean delayedInstructions = false;
    private int i = 0; // Used for changing instruction screens

    // Arrays to store instruction screen images
    // NOTE: may implement 12U methods if possible 
    String images[] = new String [2];

    /**
     * Constructor for objects of class InstructionScreen.
     * 
     */
    public InstructionScreen()
    {    
        // Create a new world with 800x800 cells with a cell size of 1x1 pixels.
        super(800, 800, 1);        

        // Adding the title bar and start button necessary
        addObject (title, 400, 295);
        addObject (start, 405, 643); 

        for (int i = 0 ; i < 2 ; i ++) {
            images[i] = "Instruction" + (i) + ".png";
        }
    }

    /**
     * Helps with the screen transitions 
     */
    public void act () {
        // NOTE: Get coordinate method is temporary!
        MouseInfo mouse = Greenfoot.getMouseInfo ();
        if (Greenfoot.mouseClicked(null)) {
            StdOut.println (mouse.getX() + "," + mouse.getY());
        }

        if (title.titleBarGone() && start.checkObjectRemoved()) {
            if (!delayedInstructions) {
                setBackground (new GreenfootImage (images[0]));
                delayedInstructions = true;
            }
            if (!addNextIcon()) {
                addNextIcon();
            }
            if (!addSkipIcon()) {
                addSkipIcon();
            }
            //addObject (back, 79, 720);
            popInstructions = true;
        }

        // If skip button is clicked, game will auto-start with no instructions
        if (skip.buttonClicked()) {
            Greenfoot.delay (50);
            Greenfoot.setWorld (new Map());
        }

        changeInstructionScreens();
    }

    public TitleBar getTitleBar () { 
        return title; 
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
                setBackground (new GreenfootImage (images[++i]));
                if (i == 1) {
                    addObject (back, 79, 705);
                } 
            } else {
                Greenfoot.setWorld (new Map());
            }
            next.buttonNotClicked();
        }
        if (back.buttonClicked()) {
            setBackground (new GreenfootImage (images[--i]));
            if (i == 0) {
                back.buttonNotClicked();
                removeObject (back);
            }
        }
    }
}
