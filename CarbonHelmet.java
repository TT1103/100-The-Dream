import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An armor piece.
 * 
 * @author Tiger Zhao
 * @version January 14, 2016
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
}
