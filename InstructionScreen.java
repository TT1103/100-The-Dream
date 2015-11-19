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

    /**
     * Constructor for objects of class InstructionScreen.
     * 
     */
    public InstructionScreen()
    {    
        // Create a new world with 800x800 cells with a cell size of 1x1 pixels.
        super(800, 800, 1);        
        initialSetup ();
    }

    public void initialSetup () {
        addObject (title, 400, 295);
        addObject (start, 405, 643);        
    }

    /**
     * Helps with the screen transitions 
     */
    public void act () {
        // Temporary Methods 
        MouseInfo mouse = Greenfoot.getMouseInfo ();
        if (Greenfoot.mouseClicked(null)) {
            StdOut.println (mouse.getX() + "," + mouse.getY());
        }
        
        if (start.checkObjectRemoved()) { 
            Greenfoot.setWorld (new Map());
        }
    }

    public TitleBar getTitleBar () { 
        return title; 
    }

    public StartButton getStartButton () { 
        return start ; 
    }
}
