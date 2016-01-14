import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An armor piece.
 * 
 * @author Tiger Zhao
 * @version January 14, 2016
 */
public class CopperHelmet extends Equipment
{
    public CopperHelmet(){
        defense =2;
        name = "copperhelmet";
        itemImage = new GreenfootImage("copperhelmet_item_image.png");
        type="head";
        tooltip ="Copper Helmet\nProvides low defense.\nDefense: "+String.valueOf(defense);
    }   
}
