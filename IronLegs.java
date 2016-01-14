import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An armor piece.
 * 
 * @author Tiger Zhao
 * @version January 14, 2016
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
}
