import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IronHelmet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IronHelmet extends Equipment
{
    public IronHelmet(){
        defense =10;
        name = "ironhelmet";
        itemImage = new GreenfootImage("ironhelmet_item_image.png");
        type="head";
        tooltip ="Iron Helmet\nProvides average defense.\nDefense: "+String.valueOf(defense);
    }
    /**
     * Act - do whatever the IronHelmet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
