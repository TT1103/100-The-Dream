import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IronLegs here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IronLegs extends Equipment
{
    public IronLegs(){
        defense =11;
        name = "ironlegs";
        itemImage = new GreenfootImage("ironlegs_item_image.png");
        type="legs";
        tooltip ="Iron Legs\nProvides average defense.\nDefense: "+String.valueOf(defense);
    }
    /**
     * Act - do whatever the IronLegs wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
