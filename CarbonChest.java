import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An armor piece.
 * 
 * @author Tiger Zhao
 * @version January 14, 2016
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
}
