import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LevelSelector here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelSelector extends World
{
    //variables for game data
    String[] completedLevels;
    Player player; //Stores the player stuff
    String[] inventory;

    // Buttons for level selection
    private Level1 one = new Level1();
    private Level2 two = new Level2();
    private Level3 three = new Level3();
    private Level4 four = new Level4();
    private Level5 five = new Level5();

    /**
     * Constructor for objects of class LevelSelector.
     * 
     */
    public LevelSelector()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1,false); 

        // Add all the level selector buttons 
        addObject (one, 96, 481);
        addObject (two, 222, 481);
        addObject (three, 345, 481);
        addObject (four, 469, 481);
        addObject (five, 587, 481);

    }

    public void act(){
        // Check for mouse click of each buttons to determine level selection
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (Greenfoot.mouseClicked(one)) {
            Greenfoot.setWorld (new Map(1));
        } else if (Greenfoot.mouseClicked(two)) {
            Greenfoot.setWorld (new Map(2));
        } else if (Greenfoot.mouseClicked(three)) {
            Greenfoot.setWorld (new Map(3));
        } else if (Greenfoot.mouseClicked(four)) {
            Greenfoot.setWorld (new Map(4));
        } else if (Greenfoot.mouseClicked(five)) {
            Greenfoot.setWorld (new Map(5));
        }
    }

}
