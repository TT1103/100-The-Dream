import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class UpPassage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UpPassage extends Actor
{
    /**
     * Act - do whatever the UpPassage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (getOneIntersectingObject(Player.class) !=null){
            ((Map)getWorld()).changeMap(0,1);
        }
    }    
}
