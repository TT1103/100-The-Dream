import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IronChest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IronChest extends Equipment
{
    public IronChest(){
        defense =15;
        name = "ironchest";
        itemImage = new GreenfootImage("ironchest_item_image.png");
        type="chest";
        tooltip ="Iron Chestplate\nProvides average defense.\nDefense: "+String.valueOf(defense);
    }
    /**
     * Act - do whatever the IronChest wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
