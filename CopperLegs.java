import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An armor piece.
 * 
 * @author Tiger Zhao
 * @version January 14, 2016
 */
public class CopperLegs extends Equipment
{
    public CopperLegs(){
        defense =3;
        name = "copperlegs";
        itemImage = new GreenfootImage("copperlegs_item_image.png");
        type="legs";
        tooltip ="Copper Legs\nProvides low defense.\nDefense: "+String.valueOf(defense);
    }
}
