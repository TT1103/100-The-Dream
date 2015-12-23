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
    StartButton sb;
    /**
     * Constructor for objects of class LevelSelector.
     * 
     */
    public LevelSelector()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1,false); 
        
        sb = new StartButton();
        addObject(sb, 400,400);
       
        
    }
    
    public void act(){
         if(sb.checkMouseClick()){
            Greenfoot.setWorld(new Map(1));
        }
    }
    

}
