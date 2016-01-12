import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CarbonLegs here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CarbonLegs extends Equipment
{
    public CarbonLegs(){
        defense =25;
        name = "carbonlegs";
        itemImage = new GreenfootImage("carbonlegs_item_image.png");
        type="legs";
        tooltip ="Carbon Legs\nProvides high defense.\nDefense: "+String.valueOf(defense);
    }
    /**
     * Act - do whatever the CarbonLegs wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
