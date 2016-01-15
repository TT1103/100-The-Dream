import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An armor piece.
 * 
 * @author Tiger Zhao
 * @version January 14, 2016
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
}
