import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LeftPassage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LeftPassage extends Passage
{
    /**
     * Act - do whatever the LeftPassage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Player p = (Player)getOneIntersectingObject(Player.class);
        if (p!=null &&  Math.abs(getX()-p.getX()) <range){
            ((Map)getWorld()).changeMap(-1,0);
        }
    }    
}
