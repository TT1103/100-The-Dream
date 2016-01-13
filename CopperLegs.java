import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CopperLegs here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
    /**
     * Act - do whatever the CopperLegs wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
