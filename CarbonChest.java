import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CarbonChest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CarbonChest extends Equipment
{
    public CarbonChest(){
        defense =35;
        name = "carbonchest";
        itemImage = new GreenfootImage("carbonchest_item_image.png");
        type="chest";
        tooltip ="Carbon Chestplate\nProvides high defense.\nDefense: "+String.valueOf(defense);
    }
    /**
     * Act - do whatever the CarbonChest wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
