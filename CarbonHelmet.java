import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CarbonHelmet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CarbonHelmet extends Equipment
{
    public CarbonHelmet(){
        defense =20;
        name = "carbonhelmet";
        itemImage = new GreenfootImage("carbonhelmet_item_image.png");
        type="head";
        tooltip ="Carbon Helmet\nProvides high defense.\nDefense: "+String.valueOf(defense);
    }
    /**
     * Act - do whatever the CarbonHelmet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
