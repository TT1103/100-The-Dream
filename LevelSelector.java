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
    /**
     * Constructor for objects of class LevelSelector.
     * 
     */
    public LevelSelector()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1,false); 
        
        //hard code levels here
        
        /*
         *  
         *  if level is selected.
         *      load first map of the level
         *      Greenfoot.set
         *      Map m = new Map("file name of map to load", player);
         *      Greenfoot.setWorld(m);
         *      
         */
        
    }
    
    
    public void parseData(){
        //read data from file and add to game
        /*
         * player.inventory = inventory
         */
    }
}
