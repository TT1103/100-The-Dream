import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CopperChest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CopperChest extends Equipment
{
    public CopperChest(){
        defense =5;
        name = "copperchest";
        itemImage = new GreenfootImage("copperchest_item_image.png");
        type="chest";
        tooltip ="Copper Chestplate\nProvides low defense.\nDefense: "+String.valueOf(defense);
    }
    /**
     * Act - do whatever the CopperChest wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
