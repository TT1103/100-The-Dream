import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An armor piece.
 * 
 * @author Tiger Zhao
 * @version January 14, 2016
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
}
