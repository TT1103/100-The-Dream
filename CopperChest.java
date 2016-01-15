import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An armor piece.
 * 
 * @author Tiger Zhao
 * @version January 14, 2016
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
}
