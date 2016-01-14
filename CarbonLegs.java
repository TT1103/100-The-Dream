import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An armor piece.
 * 
 * @author Tiger Zhao
 * @version January 14, 2016
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
}
